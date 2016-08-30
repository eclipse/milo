/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 * 	http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * 	http://www.eclipse.org/org/documents/edl-v10.html.
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
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;

import static java.util.stream.Collectors.toList;

public interface ViewManager {

    default void browse(BrowseContext context,
                        ViewDescription view,
                        UInteger maxReferencesPerNode,
                        List<BrowseDescription> nodesToBrowse) {

        OpcUaServer server = context.getServer();

        List<CompletableFuture<BrowseResult>> futures = nodesToBrowse.stream()
            .map(browseDescription -> BrowseHelper.browse(server, view, maxReferencesPerNode, browseDescription))
            .collect(toList());

        FutureUtils.sequence(futures).thenAccept(context::complete);
    }

    /**
     * If the node identified by {@code nodeId} exists return all {@link Reference}s.
     *
     * @param nodeId the {@link NodeId} identifying the node.
     * @return a {@link CompletableFuture} containing the {@link Reference}s. If the node is unknown, complete the
     * future exceptionally.
     */
    CompletableFuture<List<Reference>> getReferences(NodeId nodeId);


    final class BrowseContext extends OperationContext<BrowseDescription, BrowseResult> {
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
