/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.api.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowsePath;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RelativePath;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.UnregisterNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;

import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public interface ViewServices {

    /**
     * This service is used to discover the references of a specified node.
     * <p>
     * The browse can be further limited by the use of a view. The Browse service also supports a primitive filtering
     * capability.
     *
     * @param viewDescription      a description of the view to browse. An empty {@link ViewDescription} indicates a
     *                             view of the entire address space.
     * @param maxReferencesPerNode the maximum number of references to return for each starting node specified in the
     *                             request.
     * @param nodesToBrowse        a list of nodes to browse.
     * @return a {@link CompletableFuture} containing the {@link BrowseResponse}.
     */
    CompletableFuture<BrowseResponse> browse(ViewDescription viewDescription,
                                             UInteger maxReferencesPerNode,
                                             List<BrowseDescription> nodesToBrowse);

    /**
     * Browse a single node, with no view and no max references specified.
     *
     * @param nodeToBrowse the node to browse.
     * @return a {@link CompletableFuture} containing the {@link BrowseResult}.
     */
    default CompletableFuture<BrowseResult> browse(BrowseDescription nodeToBrowse) {
        return browse(newArrayList(nodeToBrowse)).thenApply(rs -> rs.get(0));
    }

    /**
     * Browse a list of nodes, with no view and no max references specified.
     *
     * @param nodesToBrowse the nodes to browse.
     * @return a {@link CompletableFuture} containing the {@link BrowseResult}s.
     */
    default CompletableFuture<List<BrowseResult>> browse(List<BrowseDescription> nodesToBrowse) {
        return browse(new ViewDescription(NodeId.NULL_VALUE, DateTime.MIN_VALUE, uint(0)), uint(0), nodesToBrowse)
            .thenApply(r -> l(r.getResults()));
    }

    /**
     * This service is used to request the next set of Browse or BrowseNext response information that is too large to
     * be sent in a single response.
     * <p>
     * “Too large” in this context means that the server is not able to return a larger response or that the number of
     * results to return exceeds the maximum number of results to return that was specified by the client in the
     * original Browse request.
     *
     * @param releaseContinuationPoints if {@code true}, passed continuationPoints shall be reset to free resources in
     *                                  the server. If {@code false}, passed continuationPoints shall be used to get the
     *                                  next set of browse information.
     * @param continuationPoints        a list of server-defined opaque values that represent continuation points.
     * @return a {@link CompletableFuture} containing the {@link BrowseNextResponse}.
     */
    CompletableFuture<BrowseNextResponse> browseNext(boolean releaseContinuationPoints,
                                                     List<ByteString> continuationPoints);

    /**
     * This service is used to request the next set of Browse or BrowseNext response information that is too large to
     * be sent in a single response.
     * <p>
     * “Too large” in this context means that the server is not able to return a larger response or that the number of
     * results to return exceeds the maximum number of results to return that was specified by the client in the
     * original Browse request.
     *
     * @param releaseContinuationPoint if {@code true}, passed continuationPoint shall be reset to free resources in
     *                                 the server. If {@code false}, passed continuationPoint shall be used to get the
     *                                 next set of browse information.
     * @param continuationPoint        a server-defined opaque value that represents the next continuation point.
     * @return a {@link CompletableFuture} containing the {@link BrowseResult}.
     */
    default CompletableFuture<BrowseResult> browseNext(
        boolean releaseContinuationPoint,
        ByteString continuationPoint) {

        return browseNext(releaseContinuationPoint, newArrayList(continuationPoint))
            .thenApply(response -> response.getResults()[0]);
    }

    /**
     * This service is used to request that the server translates one or more browse paths to {@link NodeId}s.
     * <p>
     * Each browse path is constructed of a starting {@link NodeId} and a {@link RelativePath}. The specified starting
     * node identifies the node from which the {@link RelativePath} is based.
     *
     * @param browsePaths a list of browse paths for which {@link NodeId}s are being requested.
     * @return a {@link CompletableFuture} containing the {@link TranslateBrowsePathsToNodeIdsResponse}.
     */
    CompletableFuture<TranslateBrowsePathsToNodeIdsResponse> translateBrowsePaths(List<BrowsePath> browsePaths);

    /**
     * The RegisterNodes service can be used by clients to register the nodes that they know they will access repeatedly
     * (e.g. Write, Call). It allows servers to set up anything needed so that the access operations will be more
     * efficient.
     *
     * @param nodesToRegister a list of {@link NodeId}s to register.
     * @return a {@link CompletableFuture} containing the {@link RegisterNodesResponse}.
     */
    CompletableFuture<RegisterNodesResponse> registerNodes(List<NodeId> nodesToRegister);

    /**
     * This service is used to unregister {@link NodeId}s that have been register via the RegisterNodes service.
     *
     * @param nodesToUnregister a list of {@link NodeId}s to unregister.
     * @return a {@link CompletableFuture} containing the {@link UnregisterNodesResponse}.
     */
    CompletableFuture<UnregisterNodesResponse> unregisterNodes(List<NodeId> nodesToUnregister);

}
