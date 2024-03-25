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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import com.google.common.primitives.Ints;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.AccessContext;
import org.eclipse.milo.opcua.sdk.server.AddressSpace.BrowseContext;
import org.eclipse.milo.opcua.sdk.server.AddressSpace.ReadContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
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
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.util.ExecutionQueue;
import org.eclipse.milo.opcua.stack.core.util.Lists;
import org.eclipse.milo.opcua.stack.core.util.NonceUtil;
import org.slf4j.LoggerFactory;

import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.sdk.server.util.UaEnumUtil.browseResultMasks;
import static org.eclipse.milo.opcua.sdk.server.util.UaEnumUtil.nodeClasses;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class BrowseHelper {

    private static final StatusCode BAD_CONTINUATION_POINT_INVALID =
        new StatusCode(StatusCodes.Bad_ContinuationPointInvalid);

    private static final StatusCode BAD_NO_CONTINUATION_POINTS =
        new StatusCode(StatusCodes.Bad_NoContinuationPoints);

    private static final BrowseResult NODE_ID_UNKNOWN_RESULT = new BrowseResult(
        new StatusCode(StatusCodes.Bad_NodeIdUnknown),
        ByteString.NULL_VALUE, new ReferenceDescription[0]
    );

    private static final BrowseResult REFERENCE_TYPE_ID_INVALID_RESULT = new BrowseResult(
        new StatusCode(StatusCodes.Bad_ReferenceTypeIdInvalid),
        ByteString.NULL_VALUE, new ReferenceDescription[0]
    );

    /**
     * Browsing is a relatively expensive operation, so limit the concurrency based on the available CPU cores.
     */
    private final ExecutionQueue browseQueue;

    public BrowseHelper(ExecutorService executor) {
        browseQueue = new ExecutionQueue(
            executor,
            Runtime.getRuntime().availableProcessors()
        );
    }

    public CompletableFuture<BrowseResult> browse(
        AccessContext context,
        OpcUaServer server,
        ViewDescription viewDescription,
        UInteger maxReferencesPerNode,
        BrowseDescription browseDescription
    ) {

        if (browseDescription.getBrowseDirection() == null) {
            BrowseResult result = new BrowseResult(
                new StatusCode(StatusCodes.Bad_BrowseDirectionInvalid),
                ByteString.NULL_VALUE,
                null
            );
            return CompletableFuture.completedFuture(result);
        } else {
            Browse browse = new Browse(
                context,
                server,
                viewDescription,
                maxReferencesPerNode,
                browseDescription
            );

            return browse.browse();
        }
    }

    public CompletableFuture<BrowseResult[]> browseNext(
        OpcUaServer server,
        Session session,
        BrowseNextRequest request
    ) {

        return new BrowseNext2(server, session, request).browseNext();
    }

    private class Browse {

        private final CompletableFuture<BrowseResult> future = new CompletableFuture<>();

        private final Session session;

        private final AccessContext context;
        private final OpcUaServer server;
        private final ViewDescription view;
        private final UInteger maxReferencesPerNode;
        private final BrowseDescription browseDescription;

        private Browse(
            AccessContext context,
            OpcUaServer server,
            ViewDescription view,
            UInteger maxReferencesPerNode,
            BrowseDescription browseDescription
        ) {

            this.context = context;
            this.server = server;
            this.view = view;
            this.browseDescription = browseDescription;
            this.maxReferencesPerNode = maxReferencesPerNode;

            this.session = context.getSession()
                .orElseThrow(() -> new IllegalArgumentException("AccessContext must have a session"));
        }

        public CompletableFuture<BrowseResult> browse() {
            browseQueue.submit(() -> {
                NodeId referenceTypeId = browseDescription.getReferenceTypeId();

                if (referenceTypeId.isNotNull() && !server.getReferenceTypes().containsKey(referenceTypeId)) {
                    future.complete(REFERENCE_TYPE_ID_INVALID_RESULT);
                    return;
                }

                var browseContext = new BrowseContext(
                    server,
                    context.getSession().orElse(null)
                );

                try {
                    List<Reference> references = server.getAddressSpaceManager()
                        .browse(browseContext, view, browseDescription.getNodeId());

                    browse(references).whenComplete((result, ex2) -> {
                        if (result != null) {
                            future.complete(result);
                        } else {
                            future.complete(NODE_ID_UNKNOWN_RESULT);
                        }
                    });
                } catch (UaException e) {
                    future.complete(NODE_ID_UNKNOWN_RESULT);
                }
            });

            return future;
        }

        private CompletableFuture<BrowseResult> browse(List<Reference> references) {
            List<ReferenceDescription> referenceDescriptions = references.stream()
                .filter(this::directionFilter)
                .filter(this::referenceTypeFilter)
                .map(this::referenceDescription)
                .collect(toList());

            int max = maxReferencesPerNode.longValue() == 0 ?
                Integer.MAX_VALUE :
                Ints.saturatedCast(maxReferencesPerNode.longValue());

            BrowseResult browseResult = browseResult(
                max,
                referenceDescriptions
                    .stream()
                    .filter(this::nodeClassFilter)
                    .collect(toList())
            );

            return CompletableFuture.completedFuture(browseResult);
        }

        private BrowseResult browseResult(int max, List<ReferenceDescription> references) {
            if (references.size() > max) {
                if (session.getBrowseContinuationPoints().size() >
                    server.getConfig().getLimits().getMaxBrowseContinuationPoints().intValue()) {

                    return new BrowseResult(BAD_NO_CONTINUATION_POINTS, null, new ReferenceDescription[0]);
                } else {
                    List<ReferenceDescription> subList = references.subList(0, max);
                    List<ReferenceDescription> current = List.copyOf(subList);
                    subList.clear();

                    BrowseContinuationPoint c = new BrowseContinuationPoint(references, max);
                    session.getBrowseContinuationPoints().put(c.identifier, c);

                    return new BrowseResult(
                        StatusCode.GOOD, c.identifier, current.toArray(new ReferenceDescription[0]));
                }
            } else {
                return new BrowseResult(
                    StatusCode.GOOD, null, references.toArray(new ReferenceDescription[0]));
            }
        }

        private boolean directionFilter(Reference reference) {
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

        private boolean referenceTypeFilter(Reference reference) {
            NodeId referenceTypeId = browseDescription.getReferenceTypeId();

            boolean includeAny = referenceTypeId == null || referenceTypeId.isNull();
            boolean includeSubtypes = browseDescription.getIncludeSubtypes();

            return includeAny || reference.getReferenceTypeId().equals(referenceTypeId) ||
                (includeSubtypes && reference.subtypeOf(referenceTypeId, server.getReferenceTypes()));
        }

        private boolean nodeClassFilter(ReferenceDescription referenceDescription) {
            long mask = browseDescription.getNodeClassMask().longValue();

            EnumSet<NodeClass> nodeClasses = (mask == 0L) ?
                EnumSet.allOf(NodeClass.class) : nodeClasses(mask);

            return nodeClasses.contains(referenceDescription.getNodeClass());
        }

        private ReferenceDescription referenceDescription(Reference reference) {
            EnumSet<BrowseResultMask> masks = browseResultMasks(browseDescription.getResultMask().longValue());

            ExpandedNodeId targetNodeId = reference.getTargetNodeId();

            NodeId referenceTypeId = masks.contains(BrowseResultMask.ReferenceTypeId) ?
                reference.getReferenceTypeId() : NodeId.NULL_VALUE;

            boolean forward = masks.contains(BrowseResultMask.IsForward) && reference.isForward();

            NodeId nodeId = targetNodeId.toNodeId(server.getNamespaceTable()).orElse(null);

            if (nodeId != null) {
                BrowseAttributes attributes = browseAttributes(nodeId);

                if (masks.contains(BrowseResultMask.TypeDefinition) &&
                    (attributes.nodeClass == NodeClass.Object || attributes.nodeClass == NodeClass.Variable)) {

                    // If this is an Object or Variable then we
                    // need to browse for the TypeDefinitionId...
                    ExpandedNodeId typeDefinitionId;
                    try {
                        typeDefinitionId = getTypeDefinition(nodeId);
                    } catch (UaException e) {
                        LoggerFactory.getLogger(BrowseHelper.class)
                            .warn("Error browsing for TypeDefinition for nodeId={}", nodeId, e);

                        typeDefinitionId = ExpandedNodeId.NULL_VALUE;
                    }

                    return new ReferenceDescription(
                        referenceTypeId,
                        forward,
                        targetNodeId,
                        masks.contains(BrowseResultMask.BrowseName) ?
                            attributes.getBrowseName() : QualifiedName.NULL_VALUE,
                        masks.contains(BrowseResultMask.DisplayName) ?
                            attributes.getDisplayName() : LocalizedText.NULL_VALUE,
                        masks.contains(BrowseResultMask.NodeClass) ?
                            attributes.getNodeClass() : NodeClass.Unspecified,
                        typeDefinitionId
                    );
                } else {
                    // Not an Object or Variable; we're done.
                    return new ReferenceDescription(
                        referenceTypeId,
                        forward,
                        targetNodeId,
                        masks.contains(BrowseResultMask.BrowseName) ?
                            attributes.getBrowseName() : QualifiedName.NULL_VALUE,
                        masks.contains(BrowseResultMask.DisplayName) ?
                            attributes.getDisplayName() : LocalizedText.NULL_VALUE,
                        masks.contains(BrowseResultMask.NodeClass) ?
                            attributes.getNodeClass() : NodeClass.Unspecified,
                        ExpandedNodeId.NULL_VALUE
                    );
                }
            } else {
                LoggerFactory.getLogger(BrowseHelper.class).warn(
                    "reference target not local: {} -> {}",
                    reference.getSourceNodeId(),
                    targetNodeId
                );

                return new ReferenceDescription(
                    referenceTypeId,
                    forward,
                    targetNodeId,
                    QualifiedName.NULL_VALUE,
                    LocalizedText.NULL_VALUE,
                    NodeClass.Unspecified,
                    ExpandedNodeId.NULL_VALUE
                );
            }
        }

        private BrowseAttributes browseAttributes(NodeId nodeId) {
            var readValueIds = new ArrayList<ReadValueId>();

            readValueIds.add(new ReadValueId(nodeId, AttributeId.BrowseName.uid(), null, QualifiedName.NULL_VALUE));
            readValueIds.add(new ReadValueId(nodeId, AttributeId.DisplayName.uid(), null, QualifiedName.NULL_VALUE));
            readValueIds.add(new ReadValueId(nodeId, AttributeId.NodeClass.uid(), null, QualifiedName.NULL_VALUE));

            var context = new ReadContext(server, null);

            List<DataValue> values = server.getAddressSpaceManager().read(
                context,
                0.0,
                TimestampsToReturn.Neither,
                readValueIds
            );

            QualifiedName browseName = QualifiedName.NULL_VALUE;
            LocalizedText displayName = LocalizedText.NULL_VALUE;
            NodeClass nodeClass = NodeClass.Unspecified;

            DataValue value0 = values.get(0);
            if (value0.getStatusCode() == null || value0.getStatusCode().isGood()) {
                browseName = (QualifiedName) value0.getValue().getValue();
            }

            DataValue value1 = values.get(1);
            if (value1.getStatusCode() == null || value1.getStatusCode().isGood()) {
                displayName = (LocalizedText) value1.getValue().getValue();
            }

            DataValue value2 = values.get(2);
            if (value2.getStatusCode() == null || value2.getStatusCode().isGood()) {
                nodeClass = (NodeClass) value2.getValue().getValue();
            }

            return new BrowseAttributes(browseName, displayName, nodeClass);
        }

        private ExpandedNodeId getTypeDefinition(NodeId nodeId) throws UaException {
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

                var browseContext = new BrowseContext(
                    server,
                    context.getSession().orElse(null)
                );

                var view = new ViewDescription(NodeId.NULL_VALUE, DateTime.NULL_VALUE, UInteger.valueOf(0));
                List<Reference> references = server.getAddressSpaceManager().browse(browseContext, view, nodeId);

                return references.stream()
                    .filter(r -> NodeIds.HasTypeDefinition.equals(r.getReferenceTypeId()))
                    .findFirst()
                    .map(Reference::getTargetNodeId)
                    .orElse(ExpandedNodeId.NULL_VALUE);
            }
        }
    }

    private static class BrowseNext2 {

        private final OpcUaServer server;
        private final Session session;
        private final BrowseNextRequest request;


        private BrowseNext2(OpcUaServer server, Session session, BrowseNextRequest request) {
            this.server = server;
            this.session = session;
            this.request = request;
        }

        public CompletableFuture<BrowseResult[]> browseNext() {
            List<ByteString> continuationPoints = Lists.ofNullable(request.getContinuationPoints());

            if (continuationPoints.isEmpty()) {
                return failedUaFuture(StatusCodes.Bad_NothingToDo);
            }
            if (continuationPoints.size() >
                server.getConfig().getLimits().getMaxBrowseContinuationPoints().intValue()) {

                return failedUaFuture(StatusCodes.Bad_TooManyOperations);
            }

            var results = new ArrayList<BrowseResult>();

            for (ByteString bs : continuationPoints) {
                if (request.getReleaseContinuationPoints()) {
                    results.add(release(bs));
                } else {
                    results.add(references(bs));
                }
            }

            return CompletableFuture.completedFuture(results.toArray(BrowseResult[]::new));
        }

        private BrowseResult release(ByteString bs) {
            BrowseContinuationPoint c = session.getBrowseContinuationPoints().remove(bs);

            return c != null ?
                new BrowseResult(StatusCode.GOOD, null, null) :
                new BrowseResult(BAD_CONTINUATION_POINT_INVALID, null, null);
        }

        private BrowseResult references(ByteString bs) {
            BrowseContinuationPoint c = session.getBrowseContinuationPoints().remove(bs);

            if (c != null) {
                int max = c.max;
                List<ReferenceDescription> references = c.references;

                if (references.size() > max) {
                    List<ReferenceDescription> subList = references.subList(0, max);
                    List<ReferenceDescription> current = List.copyOf(subList);
                    subList.clear();

                    session.getBrowseContinuationPoints().put(c.identifier, c);

                    return new BrowseResult(
                        StatusCode.GOOD,
                        c.identifier,
                        current.toArray(new ReferenceDescription[0])
                    );
                } else {
                    return new BrowseResult(
                        StatusCode.GOOD,
                        null,
                        references.toArray(new ReferenceDescription[0])
                    );
                }
            } else {
                return new BrowseResult(BAD_CONTINUATION_POINT_INVALID, null, null);
            }
        }

    }

    public static class BrowseContinuationPoint {

        private final List<ReferenceDescription> references;
        private final int max;
        private final ByteString identifier;

        public BrowseContinuationPoint(List<ReferenceDescription> references, int max) {
            this(references, max, generateId());
        }

        public BrowseContinuationPoint(List<ReferenceDescription> references, int max, ByteString identifier) {
            this.references = Collections.synchronizedList(references);
            this.max = max;
            this.identifier = identifier;
        }

        public List<ReferenceDescription> getReferences() {
            return references;
        }

        public int getMax() {
            return max;
        }

        public ByteString getIdentifier() {
            return identifier;
        }

        public static ByteString generateId() {
            return NonceUtil.generateNonce(16);
        }

    }

    private static class BrowseAttributes {

        private final QualifiedName browseName;
        private final LocalizedText displayName;
        private final NodeClass nodeClass;

        private BrowseAttributes(QualifiedName browseName, LocalizedText displayName, NodeClass nodeClass) {
            this.browseName = browseName;
            this.displayName = displayName;
            this.nodeClass = nodeClass;
        }

        public QualifiedName getBrowseName() {
            return browseName;
        }

        public LocalizedText getDisplayName() {
            return displayName;
        }

        public NodeClass getNodeClass() {
            return nodeClass;
        }

    }
}
