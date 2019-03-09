/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.services.helpers.BrowseHelper;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;

import static java.util.stream.Collectors.toList;

public interface ViewServices {

    default void browse(BrowseContext context,
                        ViewDescription view,
                        UInteger maxReferencesPerNode,
                        List<BrowseDescription> nodesToBrowse) {

        OpcUaServer server = context.getServer();

        List<CompletableFuture<BrowseResult>> results = nodesToBrowse
            .stream()
            .map(
                browseDescription ->
                    BrowseHelper.browse(
                        context,
                        server,
                        view,
                        maxReferencesPerNode,
                        browseDescription
                    )
            )
            .collect(toList());

        FutureUtils.sequence(results).thenAccept(context::complete);
    }

    /**
     * If the node identified by {@code nodeId} exists return all {@link Reference}s.
     * <p>
     * The {@link AccessContext} can be ignored unless the server wishes to impose some restriction upon which users
     * can browse which nodes. Note that this only obscures what is returned in the browse; nothing prevents a client
     * from addressing a {@link NodeId} in other service requests, whether they browsed it or not.
     *
     * @param context the {@link AccessContext} this request is being made under.
     * @param nodeId  the {@link NodeId} identifying the node.
     * @return a {@link CompletableFuture} containing the {@link Reference}s. If the node is unknown, complete the
     * future exceptionally.
     */
    CompletableFuture<List<Reference>> browse(AccessContext context, NodeId nodeId);

    final class BrowseContext extends OperationContext<BrowseDescription, BrowseResult> implements AccessContext {
        public BrowseContext(OpcUaServer server,
                             @Nullable Session session,
                             DiagnosticsContext<BrowseDescription> diagnosticsContext) {

            super(server, session, diagnosticsContext);
        }

        public BrowseContext(OpcUaServer server,
                             @Nullable Session session,
                             CompletableFuture<List<BrowseResult>> future,
                             DiagnosticsContext<BrowseDescription> diagnosticsContext) {

            super(server, session, future, diagnosticsContext);
        }
    }

}
