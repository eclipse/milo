/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api.services;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.AccessContext;
import org.eclipse.milo.opcua.sdk.server.api.AddressSpace;
import org.eclipse.milo.opcua.sdk.server.api.AsyncOperationContext;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public interface ViewServices {

    /**
     * Like {@link #browse(BrowseContext, ViewDescription, NodeId)} but with a null/empty {@link ViewDescription}.
     *
     * @param context the {@link BrowseContext}.
     * @param nodeId  the {@link NodeId} to browse.
     */
    default void browse(BrowseContext context, NodeId nodeId) {
        ViewDescription view = new ViewDescription(
            NodeId.NULL_VALUE,
            DateTime.NULL_VALUE,
            uint(0)
        );

        browse(context, view, nodeId);
    }

    /**
     * Get all References for which {@code nodeId} is the source.
     * <p>
     * The Node identified by {@code nodeId} is managed by this AddressSpace according to
     * {@link AddressSpace#filter(NodeId)}.
     * <p>
     * If a Node instance for {@code nodeId} does not exist then {@link BrowseContext#failure(StatusCode)} should be
     * invoked with {@link StatusCodes#Bad_NodeIdUnknown}.
     *
     * @param context the {@link BrowseContext}.
     * @param view    the {@link ViewDescription}.
     * @param nodeId  the {@link NodeId} to browse.
     */
    void browse(BrowseContext context, ViewDescription view, NodeId nodeId);

    /**
     * References for which {@code sourceNodeId} is the source are being collected from all AddressSpace instances.
     * Return any References where {@code sourceNodeId} is the source this AddressSpace may have to contribute.
     * <p>
     * The Node identified by {@code sourceNodeId} may be managed by another AddressSpace.
     *
     * @param context TODO
     * @param view    TODO
     * @param nodeId  TODO
     */
    void getReferences(BrowseContext context, ViewDescription view, NodeId nodeId);


    final class BrowseContext extends AsyncOperationContext<List<Reference>> implements AccessContext {

        private final Session session;

        public BrowseContext(OpcUaServer server, @Nullable Session session) {
            super(server);

            this.session = session;
        }

        @Override
        public Optional<Session> getSession() {
            return Optional.ofNullable(session);
        }

    }

}
