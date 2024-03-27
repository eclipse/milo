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
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.AccessContext;
import org.eclipse.milo.opcua.sdk.server.AddressSpace.BrowseContext;
import org.eclipse.milo.opcua.sdk.server.AddressSpace.ReadContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowsePath;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowsePathResult;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowsePathTarget;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RelativePath;
import org.eclipse.milo.opcua.stack.core.types.structured.RelativePathElement;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.util.Lists;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.sdk.server.servicesets.AbstractServiceSet.createResponseHeader;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.sequence;

public class BrowsePathsHelper {

    private final AccessContext context;
    private final OpcUaServer server;

    public BrowsePathsHelper(AccessContext context, OpcUaServer server) {
        this.context = context;
        this.server = server;
    }

    public CompletableFuture<TranslateBrowsePathsToNodeIdsResponse> translateBrowsePaths(
        TranslateBrowsePathsToNodeIdsRequest request
    ) {

        List<BrowsePath> browsePaths = Lists.ofNullable(request.getBrowsePaths());

        if (browsePaths.isEmpty()) {
            return failedUaFuture(StatusCodes.Bad_NothingToDo);
        }
        if (browsePaths.size() >
            server.getConfig().getLimits().getMaxNodesPerTranslateBrowsePathsToNodeIds().intValue()) {

            return failedUaFuture(StatusCodes.Bad_TooManyOperations);
        }

        var futures = new ArrayList<CompletableFuture<BrowsePathResult>>(browsePaths.size());

        for (BrowsePath browsePath : browsePaths) {
            futures.add(translate(browsePath));
        }

        return sequence(futures).thenComposeAsync(results -> {
            ResponseHeader header = createResponseHeader(request);

            var response = new TranslateBrowsePathsToNodeIdsResponse(
                header,
                results.toArray(BrowsePathResult[]::new),
                new DiagnosticInfo[0]
            );

            return CompletableFuture.completedFuture(response);
        }, server.getExecutorService());
    }

    private CompletableFuture<BrowsePathResult> translate(BrowsePath browsePath) {
        CompletableFuture<BrowsePathResult> future = new CompletableFuture<>();

        NodeId startingNode = browsePath.getStartingNode();
        RelativePath relativePath = browsePath.getRelativePath();

        if (startingNode.isNull()) {
            future.complete(new BrowsePathResult(
                new StatusCode(StatusCodes.Bad_NodeIdInvalid),
                new BrowsePathTarget[0]
            ));

            return future;
        }

        List<RelativePathElement> relativePathElements = Lists.ofNullable(relativePath.getElements());

        if (relativePathElements.isEmpty()) {
            future.complete(new BrowsePathResult(
                new StatusCode(StatusCodes.Bad_NothingToDo),
                new BrowsePathTarget[0]
            ));

            return future;
        }

        follow(startingNode, relativePathElements).whenComplete((targets, ex) -> {
            if (targets != null) {
                BrowsePathResult result;

                if (!targets.isEmpty()) {
                    result = new BrowsePathResult(
                        StatusCode.GOOD,
                        targets.toArray(new BrowsePathTarget[0])
                    );
                } else {
                    result = new BrowsePathResult(
                        new StatusCode(StatusCodes.Bad_NoMatch),
                        new BrowsePathTarget[0]
                    );
                }

                future.complete(result);
            } else {
                StatusCode statusCode = UaException.extractStatusCode(ex)
                    .orElse(new StatusCode(StatusCodes.Bad_NoMatch));

                BrowsePathResult result = new BrowsePathResult(
                    statusCode,
                    new BrowsePathTarget[0]
                );

                future.complete(result);
            }
        });

        return future;
    }

    private CompletableFuture<List<BrowsePathTarget>> follow(
        NodeId nodeId,
        List<RelativePathElement> elements
    ) {

        if (elements.isEmpty()) {
            return completedFuture(Collections.emptyList());
        } else if (elements.size() == 1) {
            return target(nodeId, elements.get(0)).thenApply(targets ->
                targets.stream()
                    .map(n -> new BrowsePathTarget(n, UInteger.MAX))
                    .collect(toList())
            );
        } else {
            RelativePathElement e = elements.get(0);

            return next(nodeId, e).thenCompose(nextExId -> {
                if (nextExId.isNull()) {
                    // There was no match for the target name
                    return failedUaFuture(StatusCodes.Bad_NoMatch);
                }

                List<RelativePathElement> nextElements = elements.subList(1, elements.size());

                Optional<NodeId> nextId = nextExId.toNodeId(server.getNamespaceTable());

                if (nextId.isPresent()) {
                    return follow(nextId.get(), nextElements);
                } else {
                    UInteger remaining = nextElements.isEmpty() ?
                        UInteger.MAX : uint(nextElements.size());

                    List<BrowsePathTarget> targets = List.of(
                        new BrowsePathTarget(nextExId, remaining)
                    );

                    return completedFuture(targets);
                }
            });
        }
    }

    private CompletableFuture<ExpandedNodeId> next(NodeId nodeId, RelativePathElement element) {
        NodeId referenceTypeId = element.getReferenceTypeId();
        boolean includeSubtypes = element.getIncludeSubtypes();
        QualifiedName targetName = element.getTargetName();

        if (targetName.isNull()) {
            return failedUaFuture(StatusCodes.Bad_BrowseNameInvalid);
        }

        var browseContext = new BrowseContext(
            server,
            context.getSession().orElse(null)
        );

        try {
            var view = new ViewDescription(NodeId.NULL_VALUE, DateTime.NULL_VALUE, UInteger.valueOf(0));
            List<Reference> references = server.getAddressSpaceManager().browse(browseContext, view, nodeId);

            List<ExpandedNodeId> targetNodeIds = references.stream()
                /* Filter for references of the requested type or its subtype, if allowed... */
                .filter(r -> referenceTypeId.isNull() ||
                    r.getReferenceTypeId().equals(referenceTypeId) ||
                    (includeSubtypes && r.subtypeOf(referenceTypeId, server.getReferenceTypes())))

                /* Filter for reference direction... */
                .filter(r -> r.isInverse() == element.getIsInverse())

                /* Map to target ExpandedNodeId... */
                .map(Reference::getTargetNodeId)
                .collect(toList());

            if (targetNodeIds.isEmpty()) {
                return failedUaFuture(StatusCodes.Bad_NoMatch);
            } else {
                List<QualifiedName> browseNames = readTargetBrowseNames(targetNodeIds);

                for (int i = 0; i < targetNodeIds.size(); i++) {
                    ExpandedNodeId targetNodeId = targetNodeIds.get(i);
                    QualifiedName browseName = browseNames.get(i);
                    if (browseName.equals(targetName)) {
                        return CompletableFuture.completedFuture(targetNodeId);
                    }
                }

                return CompletableFuture.completedFuture(ExpandedNodeId.NULL_VALUE);
            }
        } catch (Exception e) {
            return failedUaFuture(StatusCodes.Bad_NoMatch);
        }
    }

    private CompletableFuture<List<ExpandedNodeId>> target(NodeId nodeId, RelativePathElement element) {
        NodeId referenceTypeId = element.getReferenceTypeId();
        boolean includeSubtypes = element.getIncludeSubtypes();
        QualifiedName targetName = element.getTargetName();

        if (targetName.isNull()) {
            return failedUaFuture(StatusCodes.Bad_BrowseNameInvalid);
        }

        BrowseContext browseContext = new BrowseContext(
            server,
            context.getSession().orElse(null)
        );

        try {
            var view = new ViewDescription(NodeId.NULL_VALUE, DateTime.NULL_VALUE, UInteger.valueOf(0));
            List<Reference> references = server.getAddressSpaceManager().browse(browseContext, view, nodeId);

            List<ExpandedNodeId> targetNodeIds = references.stream()
                /* Filter for references of the requested type or its subtype, if allowed... */
                .filter(r -> referenceTypeId.isNull() ||
                    r.getReferenceTypeId().equals(referenceTypeId) ||
                    (includeSubtypes && r.subtypeOf(referenceTypeId, server.getReferenceTypes())))

                /* Filter for reference direction... */
                .filter(r -> r.isInverse() == element.getIsInverse())

                /* Map to target ExpandedNodeId... */
                .map(Reference::getTargetNodeId)
                .collect(toList());

            if (targetNodeIds.isEmpty()) {
                return failedUaFuture(StatusCodes.Bad_NoMatch);
            } else {
                List<QualifiedName> browseNames = readTargetBrowseNames(targetNodeIds);
                var targets = new ArrayList<ExpandedNodeId>();

                for (int i = 0; i < targetNodeIds.size(); i++) {
                    ExpandedNodeId targetNodeId = targetNodeIds.get(i);
                    QualifiedName browseName = browseNames.get(i);
                    if (matchesTarget(browseName, targetName)) {
                        targets.add(targetNodeId);
                    }
                }

                return CompletableFuture.completedFuture(targets);
            }
        } catch (Exception e) {
            return failedUaFuture(StatusCodes.Bad_NoMatch);
        }
    }

    private List<QualifiedName> readTargetBrowseNames(List<ExpandedNodeId> targetNodeIds) {
        var browseNames = new ArrayList<QualifiedName>();

        for (ExpandedNodeId xni : targetNodeIds) {
            try {
                NodeId nodeId = xni.toNodeIdOrThrow(server.getNamespaceTable());

                ReadValueId readValueId = new ReadValueId(
                    nodeId,
                    AttributeId.BrowseName.uid(),
                    null,
                    QualifiedName.NULL_VALUE
                );

                var context = new ReadContext(server, null);

                List<DataValue> values = server.getAddressSpaceManager().read(
                    context,
                    0.0,
                    TimestampsToReturn.Neither,
                    List.of(readValueId)
                );

                browseNames.add((QualifiedName) values.get(0).getValue().getValue());
            } catch (Exception ignored) {
                browseNames.add(QualifiedName.NULL_VALUE);
            }
        }

        return browseNames;
    }

    private boolean matchesTarget(QualifiedName browseName, QualifiedName targetName) {
        return targetName == null ||
            targetName.equals(QualifiedName.NULL_VALUE) ||
            targetName.equals(browseName);
    }

}
