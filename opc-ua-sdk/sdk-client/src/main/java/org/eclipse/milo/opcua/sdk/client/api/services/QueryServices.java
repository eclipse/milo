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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.NodeTypeDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryDataSet;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryFirstResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryNextResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;

public interface QueryServices {

    /**
     * This service is used to issue a query to the server.
     *
     * @param view                  specifies a View and temporal context to a server.
     * @param nodeTypes             the {@link NodeTypeDescription}.
     * @param filter                the {@link ContentFilter}. Resulting Nodes shall be limited to the Nodes matching
     *                              the criteria defined by the filter.
     * @param maxDataSetsToReturn   the number of {@link QueryDataSet}s that the client wants the server to return in
     *                              the response and on each subsequent continuation call response. The server is
     *                              allowed to further limit the response, but shall not exceed this limit. A value of 0
     *                              indicates that the client is imposing no limitation.
     * @param maxReferencesToReturn the number of References that the client wants the server to return in the response
     *                              for each {@link QueryDataSet} and on each subsequent continuation call response. The
     *                              server is allowed to further limit the response, but shall not exceed this limit. A
     *                              value of 0 indicates that the client is imposing no limitation.
     * @return a {@link CompletableFuture} containing the {@link QueryFirstResponse}.
     */
    CompletableFuture<QueryFirstResponse> queryFirst(ViewDescription view,
                                                     List<NodeTypeDescription> nodeTypes,
                                                     ContentFilter filter,
                                                     UInteger maxDataSetsToReturn,
                                                     UInteger maxReferencesToReturn);

    /**
     * This Service is used to request the next set of QueryFirst or QueryNext response information that is too large to
     * be sent in a single response.
     *
     * @param releaseContinuationPoint if {@code true}, passed continuationPoints shall be reset to free resources in
     *                                 the server. If {@code false}, passed continuationPoints shall be used to get the
     *                                 next set of browse information.
     * @param continuationPoint        a server-defined opaque value that represents the continuation point.
     * @return a {@link CompletableFuture} containing the {@link QueryNextResponse}.
     */
    CompletableFuture<QueryNextResponse> queryNext(boolean releaseContinuationPoint,
                                                   ByteString continuationPoint);

}
