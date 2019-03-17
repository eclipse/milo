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

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.ServiceOperationContext;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesResult;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;

public interface NodeManagementServices {

    default void addNodes(AddNodesContext context, List<AddNodesItem> nodesToAdd) {
        AddNodesResult result = new AddNodesResult(
            new StatusCode(StatusCodes.Bad_NotSupported),
            NodeId.NULL_VALUE
        );

        context.success(Collections.nCopies(nodesToAdd.size(), result));
    }

    default void deleteNodes(DeleteNodesContext context, List<DeleteNodesItem> nodesToDelete) {
        StatusCode statusCode = new StatusCode(StatusCodes.Bad_NotSupported);

        context.success(Collections.nCopies(nodesToDelete.size(), statusCode));
    }

    default void addReferences(AddReferencesContext context, List<AddReferencesItem> referencesToAdd) {
        StatusCode statusCode = new StatusCode(StatusCodes.Bad_NotSupported);

        context.success(Collections.nCopies(referencesToAdd.size(), statusCode));
    }

    default void deleteReferences(DeleteReferencesContext context, List<DeleteReferencesItem> referencesToDelete) {
        StatusCode statusCode = new StatusCode(StatusCodes.Bad_NotSupported);

        context.success(Collections.nCopies(referencesToDelete.size(), statusCode));
    }

    final class AddNodesContext extends ServiceOperationContext<AddNodesItem, AddNodesResult> {

        public AddNodesContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public AddNodesContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<AddNodesItem> diagnosticsContext
        ) {

            super(server, session, diagnosticsContext);
        }

    }

    final class DeleteNodesContext extends ServiceOperationContext<DeleteNodesItem, StatusCode> {

        public DeleteNodesContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public DeleteNodesContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<DeleteNodesItem> diagnosticsContext) {

            super(server, session, diagnosticsContext);
        }

    }

    final class AddReferencesContext extends ServiceOperationContext<AddReferencesItem, StatusCode> {

        public AddReferencesContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public AddReferencesContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<AddReferencesItem> diagnosticsContext
        ) {

            super(server, session, diagnosticsContext);
        }

    }

    final class DeleteReferencesContext extends ServiceOperationContext<DeleteReferencesItem, StatusCode> {

        public DeleteReferencesContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public DeleteReferencesContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<DeleteReferencesItem> diagnosticsContext) {

            super(server, session, diagnosticsContext);
        }

    }

}
