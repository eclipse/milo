/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.servicesets.impl.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.primitives.Ints;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.AccessContext;
import org.eclipse.milo.opcua.sdk.server.AddressSpace;
import org.eclipse.milo.opcua.sdk.server.AddressSpace.BrowseContext;
import org.eclipse.milo.opcua.sdk.server.ContinuationPoint;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.util.Lists;
import org.jetbrains.annotations.Nullable;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.sdk.server.util.UaEnumUtil.browseResultMasks;
import static org.eclipse.milo.opcua.sdk.server.util.UaEnumUtil.nodeClasses;

public class BrowseHelper {

    public static List<BrowseResult> browse(
        OpcUaServer server,
        AccessContext context,
        BrowseRequest browseRequest
    ) {

        List<BrowseDescription> nodesToBrowse =
            Lists.ofNullable(browseRequest.getNodesToBrowse());

        var browseContext = new BrowseContext(
            server,
            context.getSession().orElse(null)
        );

        List<AddressSpace.ReferenceResult> referenceLists = server.getAddressSpaceManager().browse(
            browseContext,
            browseRequest.getView(),
            nodesToBrowse.stream()
                .map(BrowseDescription::getNodeId)
                .collect(Collectors.toList())
        );

        List<List<ReferenceDescription>> referenceDescriptionLists = createReferenceDescriptions(
            server,
            nodesToBrowse,
            referenceLists
        );

        int max = browseRequest.getRequestedMaxReferencesPerNode().longValue() == 0 ?
            Integer.MAX_VALUE :
            Ints.saturatedCast(browseRequest.getRequestedMaxReferencesPerNode().longValue());

        var browseResults = new ArrayList<BrowseResult>();

        for (List<ReferenceDescription> referenceDescriptions : referenceDescriptionLists) {
            BrowseResult browseResult = createBrowseResult(
                server,
                context.getSession().orElse(null),
                max,
                referenceDescriptions
            );

            browseResults.add(browseResult);
        }

        return browseResults;
    }

    private static List<List<ReferenceDescription>> createReferenceDescriptions(
        OpcUaServer server,
        List<BrowseDescription> nodesToBrowse,
        List<AddressSpace.ReferenceResult> referenceResults
    ) {

        var referenceDescriptionLists = new ArrayList<List<ReferenceDescription>>();

        for (int i = 0; i < nodesToBrowse.size(); i++) {
            BrowseDescription browseDescription = nodesToBrowse.get(i);
            AddressSpace.ReferenceResult result = referenceResults.get(i);

            List<Reference> references;
            if (result instanceof AddressSpace.ReferenceResult.ReferenceList r) {
                references = r.references();
            } else {
                references = Collections.emptyList();
            }

            List<Reference> stage1Refs = references.stream()
                .filter(r -> filterDirection(browseDescription, r))
                .filter(r -> filterReferenceType(server, browseDescription, r))
                .collect(Collectors.toList());

            // The target of each of these references is going to need "BrowseAttributes" read
            // and possibly the TypeDefinition browsed for. How can we batch these up?

            List<ExpandedNodeId> targetNodeIds = stage1Refs.stream()
                .map(Reference::getTargetNodeId)
                .collect(Collectors.toList());

            List<BrowseAttributes> browseAttributes = readBrowseAttributes(
                server,
                targetNodeIds
            );

            List<ExpandedNodeId> typeDefinitionIds = browseTypeDefinitions(
                server,
                targetNodeIds,
                browseDescription.getResultMask()
            );

            var referenceDescriptions = new ArrayList<ReferenceDescription>();

            for (int j = 0; j < stage1Refs.size(); j++) {
                Reference reference = stage1Refs.get(j);
                BrowseAttributes attributes = browseAttributes.get(j);
                ExpandedNodeId typeDefinitionId = typeDefinitionIds.get(j);

                if (filterNodeClass(browseDescription, attributes.getNodeClass())) {
                    ReferenceDescription referenceDescription = createReferenceDescription(
                        browseDescription,
                        reference,
                        attributes,
                        typeDefinitionId
                    );

                    referenceDescriptions.add(referenceDescription);
                }
            }

            referenceDescriptionLists.add(referenceDescriptions);
        }

        return referenceDescriptionLists;
    }

    private static ReferenceDescription createReferenceDescription(
        BrowseDescription browseDescription,
        Reference reference,
        BrowseAttributes browseAttributes,
        ExpandedNodeId typeDefinitionId
    ) {

        EnumSet<BrowseResultMask> masks = browseResultMasks(
            browseDescription.getResultMask().longValue()
        );

        NodeId referenceTypeId = masks.contains(BrowseResultMask.ReferenceTypeId) ?
            reference.getReferenceTypeId() : NodeId.NULL_VALUE;

        boolean forward = masks.contains(BrowseResultMask.IsForward) && reference.isForward();

        return new ReferenceDescription(
            referenceTypeId,
            forward,
            reference.getTargetNodeId(),
            masks.contains(BrowseResultMask.BrowseName) ?
                browseAttributes.getBrowseName() : QualifiedName.NULL_VALUE,
            masks.contains(BrowseResultMask.DisplayName) ?
                browseAttributes.getDisplayName() : LocalizedText.NULL_VALUE,
            masks.contains(BrowseResultMask.NodeClass) ?
                browseAttributes.getNodeClass() : NodeClass.Unspecified,
            typeDefinitionId
        );
    }

    private static BrowseResult createBrowseResult(
        OpcUaServer server,
        Session session,
        int max,
        List<ReferenceDescription> references
    ) {

        if (references.size() > max) {
            if (session.getBrowseContinuationPoints().size() >
                server.getConfig().getLimits().getMaxBrowseContinuationPoints().intValue()) {

                return new BrowseResult(
                    new StatusCode(StatusCodes.Bad_NoContinuationPoints),
                    null,
                    new ReferenceDescription[0]
                );
            } else {
                List<ReferenceDescription> subList = references.subList(0, max);
                List<ReferenceDescription> current = List.copyOf(subList);
                subList.clear();

                ContinuationPoint c = new ContinuationPoint(references, max);
                session.getBrowseContinuationPoints().put(c.id(), c);

                return new BrowseResult(
                    StatusCode.GOOD,
                    c.id(),
                    current.toArray(new ReferenceDescription[0])
                );
            }
        } else {
            return new BrowseResult(
                StatusCode.GOOD,
                null,
                references.toArray(new ReferenceDescription[0])
            );
        }
    }

    private static List<BrowseAttributes> readBrowseAttributes(
        OpcUaServer server,
        List<ExpandedNodeId> nodeIds
    ) {

        final var browseAttributes = new ArrayList<BrowseAttributes>();

        var readValueIds = new ArrayList<ReadValueId>();

        for (ExpandedNodeId xni : nodeIds) {
            NodeId nodeId = xni.toNodeId(server.getNamespaceTable()).orElse(NodeId.NULL_VALUE);
            readValueIds.add(new ReadValueId(nodeId, AttributeId.BrowseName.uid(), null, QualifiedName.NULL_VALUE));
            readValueIds.add(new ReadValueId(nodeId, AttributeId.DisplayName.uid(), null, QualifiedName.NULL_VALUE));
            readValueIds.add(new ReadValueId(nodeId, AttributeId.NodeClass.uid(), null, QualifiedName.NULL_VALUE));
        }

        var context = new AddressSpace.ReadContext(server, null);

        List<DataValue> values = server.getAddressSpaceManager().read(
            context,
            0.0,
            TimestampsToReturn.Neither,
            readValueIds
        );

        for (int i = 0; i < values.size(); i += 3) {
            QualifiedName browseName = (QualifiedName) values.get(i).getValue().getValue();
            LocalizedText displayName = (LocalizedText) values.get(i + 1).getValue().getValue();
            NodeClass nodeClass = (NodeClass) values.get(i + 2).getValue().getValue();

            browseAttributes.add(new BrowseAttributes(browseName, displayName, nodeClass));
        }

        return browseAttributes;
    }

    private static List<ExpandedNodeId> browseTypeDefinitions(OpcUaServer server, List<ExpandedNodeId> nodeIds, UInteger resultMask) {
        EnumSet<BrowseResultMask> resultMasks = browseResultMasks(resultMask.longValue());

        if (!resultMasks.contains(BrowseResultMask.TypeDefinition)) {
            return Collections.nCopies(nodeIds.size(), ExpandedNodeId.NULL_VALUE);
        }

        return nodeIds.stream()
            .map(xni -> xni.toNodeId(server.getNamespaceTable()).orElse(NodeId.NULL_VALUE))
            .map(nodeId -> {
                try {
                    return browseTypeDefinition(server, nodeId);
                } catch (UaException e) {
                    LoggerFactory.getLogger(BrowseHelper.class)
                        .error("Error browsing TypeDefinition for nodeId={}", nodeId, e);
                    return ExpandedNodeId.NULL_VALUE;
                }
            })
            .collect(Collectors.toList());
    }

    private static ExpandedNodeId browseTypeDefinition(OpcUaServer server, NodeId nodeId) throws UaException {
        ExpandedNodeId typeDefinitionId = server.getAddressSpaceManager()
            .getManagedReferences(nodeId, Reference.HAS_TYPE_DEFINITION_PREDICATE)
            .stream()
            .findFirst()
            .map(Reference::getTargetNodeId)
            .orElse(null);

        if (typeDefinitionId != null) {
            return typeDefinitionId;
        } else {
            LoggerFactory.getLogger(BrowseHelper.class)
                .trace("No managed TypeDefinition for nodeId={}, browsing...", nodeId);

            var browseContext = new BrowseContext(server, null);

            var view = new ViewDescription(NodeId.NULL_VALUE, DateTime.NULL_VALUE, UInteger.valueOf(0));

            AddressSpace.ReferenceResult result =
                server.getAddressSpaceManager()
                    .browse(browseContext, view, List.of(nodeId)).get(0);

            if (result instanceof AddressSpace.ReferenceResult.ReferenceList rl) {
                List<Reference> references = rl.references();

                return references.stream()
                    .filter(r -> NodeIds.HasTypeDefinition.equals(r.getReferenceTypeId()))
                    .findFirst()
                    .map(Reference::getTargetNodeId)
                    .orElse(ExpandedNodeId.NULL_VALUE);
            } else {
                return ExpandedNodeId.NULL_VALUE;
            }
        }
    }

    private static boolean filterDirection(
        BrowseDescription browseDescription, Reference reference) {

        switch (browseDescription.getBrowseDirection()) {
            case Forward:
                return reference.isForward();
            case Inverse:
                return reference.isInverse();
            case Both:
            default:
                return true;
        }
    }

    private static boolean filterReferenceType(
        OpcUaServer server, BrowseDescription browseDescription, Reference reference) {

        NodeId referenceTypeId = browseDescription.getReferenceTypeId();

        boolean includeAny = referenceTypeId == null || referenceTypeId.isNull();
        boolean includeSubtypes = browseDescription.getIncludeSubtypes();

        return includeAny || reference.getReferenceTypeId().equals(referenceTypeId)
            || (includeSubtypes && reference.subtypeOf(referenceTypeId, server.getReferenceTypes()));
    }

    private static boolean filterNodeClass(
        BrowseDescription browseDescription, NodeClass nodeClass) {

        long mask = browseDescription.getNodeClassMask().longValue();

        EnumSet<NodeClass> nodeClasses = (mask == 0L) ?
            EnumSet.allOf(NodeClass.class) : nodeClasses(mask);

        return nodeClasses.contains(nodeClass);
    }

    private static class BrowseAttributes {

        private final QualifiedName browseName;
        private final LocalizedText displayName;
        private final NodeClass nodeClass;

        private BrowseAttributes(
            @Nullable QualifiedName browseName,
            @Nullable LocalizedText displayName,
            @Nullable NodeClass nodeClass
        ) {

            this.browseName = browseName;
            this.displayName = displayName;
            this.nodeClass = nodeClass;
        }

        public @Nullable QualifiedName getBrowseName() {
            return browseName;
        }

        public @Nullable LocalizedText getDisplayName() {
            return displayName;
        }

        public @Nullable NodeClass getNodeClass() {
            return nodeClass;
        }

    }

}
