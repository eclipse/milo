/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.services.helpers;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.AccessContext;
import org.eclipse.milo.opcua.sdk.server.api.services.AttributeServices.ReadContext;
import org.eclipse.milo.opcua.sdk.server.api.services.ViewServices.BrowseContext;
import org.eclipse.milo.opcua.sdk.server.services.ServiceAttributes;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
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
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.util.ExecutionQueue;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.NonceUtil;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;
import org.slf4j.LoggerFactory;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.sdk.server.util.UaEnumUtil.browseResultMasks;
import static org.eclipse.milo.opcua.sdk.server.util.UaEnumUtil.nodeClasses;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class BrowseHelper {

    /**
     * Browsing is a relatively expensive operation, so limit the concurrency based on the available CPU cores.
     */
    private static final ExecutionQueue BROWSE_EXECUTION_QUEUE = new ExecutionQueue(
        Stack.sharedExecutor(),
        Runtime.getRuntime().availableProcessors()
    );

    private static final StatusCode BAD_CONTINUATION_POINT_INVALID =
        new StatusCode(StatusCodes.Bad_ContinuationPointInvalid);

    private static final StatusCode BAD_NO_CONTINUATION_POINTS =
        new StatusCode(StatusCodes.Bad_NoContinuationPoints);

    private static final BrowseResult NODE_ID_UNKNOWN_RESULT = new BrowseResult(
        new StatusCode(StatusCodes.Bad_NodeIdUnknown),
        ByteString.NULL_VALUE, new ReferenceDescription[0]);

    public void browseNext(ServiceRequest service) {
        OpcUaServer server = service.attr(ServiceAttributes.SERVER_KEY).get();

        BrowseNextRequest request = (BrowseNextRequest) service.getRequest();

        List<ByteString> continuationPoints = l(request.getContinuationPoints());

        if (continuationPoints.size() >
            server.getConfig().getLimits().getMaxBrowseContinuationPoints().intValue()) {

            service.setServiceFault(StatusCodes.Bad_TooManyOperations);
        } else {
            server.getExecutorService().execute(new BrowseNext(server, service));
        }
    }

    public static CompletableFuture<BrowseResult> browse(
        AccessContext context,
        OpcUaServer server,
        ViewDescription viewDescription,
        UInteger maxReferencesPerNode,
        BrowseDescription browseDescription) {

        Browse browse = new Browse(
            context,
            server,
            viewDescription,
            maxReferencesPerNode,
            browseDescription
        );

        return browse.browse();
    }

    private static class Browse {

        private final CompletableFuture<BrowseResult> future = new CompletableFuture<>();

        private final AccessContext context;
        private final OpcUaServer server;
        private final ViewDescription view;
        private final UInteger maxReferencesPerNode;
        private final BrowseDescription browseDescription;

        private Browse(AccessContext context,
                       OpcUaServer server,
                       ViewDescription view,
                       UInteger maxReferencesPerNode,
                       BrowseDescription browseDescription) {

            this.context = context;
            this.server = server;
            this.view = view;
            this.browseDescription = browseDescription;
            this.maxReferencesPerNode = maxReferencesPerNode;
        }

        public CompletableFuture<BrowseResult> browse() {
            BROWSE_EXECUTION_QUEUE.submit(() -> {

                BrowseContext browseContext = new BrowseContext(
                    server,
                    context.getSession().orElse(null)
                );

                server.getAddressSpaceManager().browse(browseContext, view, browseDescription.getNodeId());

                CompletableFuture<List<Reference>> referencesFuture = browseContext.getFuture();

                referencesFuture.whenComplete((references, ex) -> {
                    if (references != null) {
                        browse(references).whenComplete((result, ex2) -> {
                            if (result != null) future.complete(result);
                            else future.complete(NODE_ID_UNKNOWN_RESULT);
                        });
                    } else {
                        future.complete(NODE_ID_UNKNOWN_RESULT);
                    }
                });
            });

            return future;
        }

        private CompletableFuture<BrowseResult> browse(List<Reference> references) {
            List<CompletableFuture<ReferenceDescription>> fs = references.stream()
                .filter(this::directionFilter)
                .filter(this::referenceTypeFilter)
                .map(this::referenceDescription)
                .collect(toList());

            return FutureUtils.sequence(fs).thenApply(referenceDescriptions -> {
                int max = maxReferencesPerNode.longValue() == 0 ?
                    Integer.MAX_VALUE :
                    Ints.saturatedCast(maxReferencesPerNode.longValue());

                return browseResult(
                    max,
                    referenceDescriptions
                        .stream()
                        .filter(this::nodeClassFilter)
                        .collect(toList())
                );
            });
        }

        private BrowseResult browseResult(int max, List<ReferenceDescription> references) {
            if (references.size() > max) {
                if (server.getBrowseContinuationPoints().size() >
                    server.getConfig().getLimits().getMaxBrowseContinuationPoints().intValue()) {

                    return new BrowseResult(BAD_NO_CONTINUATION_POINTS, null, new ReferenceDescription[0]);
                } else {
                    List<ReferenceDescription> subList = references.subList(0, max);
                    List<ReferenceDescription> current = Lists.newArrayList(subList);
                    subList.clear();

                    BrowseContinuationPoint c = new BrowseContinuationPoint(references, max);
                    server.getBrowseContinuationPoints().put(c.identifier, c);

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

        private CompletableFuture<ReferenceDescription> referenceDescription(Reference reference) {
            EnumSet<BrowseResultMask> masks = browseResultMasks(browseDescription.getResultMask().longValue());

            ExpandedNodeId targetNodeId = reference.getTargetNodeId();

            NodeId referenceTypeId = masks.contains(BrowseResultMask.ReferenceTypeId) ?
                reference.getReferenceTypeId() : NodeId.NULL_VALUE;

            return targetNodeId.local(server.getNamespaceTable()).map(nodeId -> {
                CompletableFuture<BrowseAttributes> attributesFuture = browseAttributes(nodeId, masks);

                CompletableFuture<ReferenceDescription> referenceFuture = attributesFuture.thenCompose(attributes -> {
                    if (attributes.nodeClass == NodeClass.Object || attributes.nodeClass == NodeClass.Variable) {
                        // If this is an Object or Variable then we
                        // need to browse for the TypeDefinitionId...
                        return getTypeDefinition(nodeId).thenApply(
                            typeDefinition ->
                                new ReferenceDescription(
                                    referenceTypeId,
                                    reference.isForward(),
                                    targetNodeId,
                                    attributes.getBrowseName(),
                                    attributes.getDisplayName(),
                                    attributes.getNodeClass(),
                                    typeDefinition
                                )
                        );
                    } else {
                        // Not an Object or Variable; we're done.
                        return completedFuture(new ReferenceDescription(
                            referenceTypeId,
                            reference.isForward(),
                            targetNodeId,
                            attributes.getBrowseName(),
                            attributes.getDisplayName(),
                            attributes.getNodeClass(),
                            ExpandedNodeId.NULL_VALUE
                        ));
                    }
                });

                return referenceFuture.whenComplete((r, ex) -> {
                    if (ex != null) {
                        LoggerFactory.getLogger(BrowseHelper.class).warn(
                            "failed to get browse attributes for: {}",
                            reference.getSourceNodeId(), ex
                        );
                    }
                });
            }).orElseGet(() -> {
                LoggerFactory.getLogger(BrowseHelper.class).warn(
                    "reference target not local: {} -> {}",
                    reference.getSourceNodeId(),
                    targetNodeId
                );

                return completedFuture(
                    new ReferenceDescription(
                        referenceTypeId,
                        reference.isForward(),
                        targetNodeId,
                        QualifiedName.NULL_VALUE,
                        LocalizedText.NULL_VALUE,
                        NodeClass.Unspecified,
                        ExpandedNodeId.NULL_VALUE
                    )
                );
            });
        }

        private CompletableFuture<BrowseAttributes> browseAttributes(NodeId nodeId, EnumSet<BrowseResultMask> masks) {
            List<ReadValueId> readValueIds = Lists.newArrayList();

            readValueIds.add(new ReadValueId(nodeId, AttributeId.BrowseName.uid(), null, QualifiedName.NULL_VALUE));
            readValueIds.add(new ReadValueId(nodeId, AttributeId.DisplayName.uid(), null, QualifiedName.NULL_VALUE));
            readValueIds.add(new ReadValueId(nodeId, AttributeId.NodeClass.uid(), null, QualifiedName.NULL_VALUE));

            ReadContext context = new ReadContext(server, null);

            server.getAddressSpaceManager().read(
                context,
                0.0,
                TimestampsToReturn.Neither,
                readValueIds
            );

            return context.getFuture().thenApply(values -> {
                QualifiedName browseName = QualifiedName.NULL_VALUE;
                LocalizedText displayName = LocalizedText.NULL_VALUE;
                NodeClass nodeClass = NodeClass.Unspecified;

                if (masks.contains(BrowseResultMask.BrowseName)) {
                    DataValue value0 = values.get(0);
                    if (value0.getStatusCode() == null || value0.getStatusCode().isGood()) {
                        browseName = (QualifiedName) value0.getValue().getValue();
                    }
                }

                if (masks.contains(BrowseResultMask.DisplayName)) {
                    DataValue value1 = values.get(1);
                    if (value1.getStatusCode() == null || value1.getStatusCode().isGood()) {
                        displayName = (LocalizedText) value1.getValue().getValue();
                    }
                }

                if (masks.contains(BrowseResultMask.NodeClass)) {
                    DataValue value2 = values.get(2);
                    if (value2.getStatusCode() == null || value2.getStatusCode().isGood()) {
                        nodeClass = (NodeClass) value2.getValue().getValue();
                    }
                }

                return new BrowseAttributes(browseName, displayName, nodeClass);
            });
        }

        private CompletableFuture<ExpandedNodeId> getTypeDefinition(NodeId nodeId) {
            Optional<ExpandedNodeId> typeDefinitionId = server.getAddressSpaceManager()
                .getManagedReferences(nodeId, Reference.HAS_TYPE_DEFINITION_PREDICATE)
                .stream()
                .findFirst()
                .map(Reference::getTargetNodeId);

            return typeDefinitionId.map(CompletableFuture::completedFuture).orElseGet(() -> {
                LoggerFactory.getLogger(BrowseHelper.class)
                    .trace("No managed TypeDefinition for nodeId={}, browsing...", nodeId);

                BrowseContext browseContext = new BrowseContext(
                    server,
                    context.getSession().orElse(null)
                );

                server.getAddressSpaceManager().browse(browseContext, nodeId);

                CompletableFuture<List<Reference>> browseFuture = browseContext.getFuture();

                return browseFuture.thenApply(
                    references ->
                        references.stream()
                            .filter(r -> Identifiers.HasTypeDefinition.equals(r.getReferenceTypeId()))
                            .findFirst()
                            .map(Reference::getTargetNodeId)
                            .orElse(ExpandedNodeId.NULL_VALUE)
                );
            });

        }

    }

    private static class BrowseNext implements Runnable {

        private final OpcUaServer server;
        private final ServiceRequest service;

        private BrowseNext(OpcUaServer server, ServiceRequest service) {

            this.server = server;
            this.service = service;
        }

        @Override
        public void run() {
            BrowseNextRequest request = (BrowseNextRequest) service.getRequest();

            List<BrowseResult> results = Lists.newArrayList();

            ByteString[] cs = request.getContinuationPoints() != null ?
                request.getContinuationPoints() : new ByteString[0];

            for (ByteString bs : cs) {
                if (request.getReleaseContinuationPoints()) {
                    results.add(release(bs));
                } else {
                    results.add(references(bs));
                }
            }

            ResponseHeader header = service.createResponseHeader();
            BrowseNextResponse response = new BrowseNextResponse(
                header, results.toArray(new BrowseResult[0]), new DiagnosticInfo[0]);

            service.setResponse(response);
        }

        private BrowseResult release(ByteString bs) {
            BrowseContinuationPoint c = server.getBrowseContinuationPoints().remove(bs);

            return c != null ?
                new BrowseResult(StatusCode.GOOD, null, null) :
                new BrowseResult(BAD_CONTINUATION_POINT_INVALID, null, null);
        }

        private BrowseResult references(ByteString bs) {
            BrowseContinuationPoint c = server.getBrowseContinuationPoints().remove(bs);

            if (c != null) {
                int max = c.max;
                List<ReferenceDescription> references = c.references;

                if (references.size() > max) {
                    List<ReferenceDescription> subList = references.subList(0, max);
                    List<ReferenceDescription> current = Lists.newArrayList(subList);
                    subList.clear();

                    server.getBrowseContinuationPoints().put(c.identifier, c);

                    return new BrowseResult(
                        StatusCode.GOOD,
                        c.identifier,
                        current.toArray(new ReferenceDescription[0]));
                } else {
                    return new BrowseResult(
                        StatusCode.GOOD,
                        null,
                        references.toArray(new ReferenceDescription[0]));
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
