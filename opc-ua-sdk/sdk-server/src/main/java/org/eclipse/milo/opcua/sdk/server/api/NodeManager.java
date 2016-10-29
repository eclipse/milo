/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server.api;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesResult;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;

public interface NodeManager {

    default void addNode(AddNodesContext context, List<AddNodesItem> nodesToAdd) {
        AddNodesResult result = new AddNodesResult(
            new StatusCode(StatusCodes.Bad_NotSupported),
            NodeId.NULL_VALUE);

        context.complete(Collections.nCopies(nodesToAdd.size(), result));
    }


    default void deleteNode(DeleteNodesContext context, List<DeleteNodesItem> nodesToDelete) {
        StatusCode statusCode = new StatusCode(StatusCodes.Bad_NotSupported);

        context.complete(Collections.nCopies(nodesToDelete.size(), statusCode));
    }

    default void addReference(AddReferencesContext context, List<AddReferencesItem> referencesToAdd) {
        StatusCode statusCode = new StatusCode(StatusCodes.Bad_NotSupported);

        context.complete(Collections.nCopies(referencesToAdd.size(), statusCode));
    }

    default void deleteReference(DeleteReferencesContext context, List<DeleteReferencesItem> referencesToDelete) {
        StatusCode statusCode = new StatusCode(StatusCodes.Bad_NotSupported);

        context.complete(Collections.nCopies(referencesToDelete.size(), statusCode));
    }

    final class AddNodesContext extends OperationContext<AddNodesItem, AddNodesResult> {
        public AddNodesContext(OpcUaServer server, @Nullable Session session,
                               DiagnosticsContext<AddNodesItem> diagnosticsContext) {

            super(server, session, diagnosticsContext);
        }
    }

    final class DeleteNodesContext extends OperationContext<DeleteNodesItem, StatusCode> {
        public DeleteNodesContext(OpcUaServer server, @Nullable Session session,
                                  DiagnosticsContext<DeleteNodesItem> diagnosticsContext) {

            super(server, session, diagnosticsContext);
        }
    }

    final class AddReferencesContext extends OperationContext<AddReferencesItem, StatusCode> {
        public AddReferencesContext(OpcUaServer server, @Nullable Session session,
                                    DiagnosticsContext<AddReferencesItem> diagnosticsContext) {

            super(server, session, diagnosticsContext);
        }
    }

    final class DeleteReferencesContext extends OperationContext<DeleteReferencesItem, StatusCode> {
        public DeleteReferencesContext(OpcUaServer server, @Nullable Session session,
                                       DiagnosticsContext<DeleteReferencesItem> diagnosticsContext) {

            super(server, session, diagnosticsContext);
        }
    }

}
