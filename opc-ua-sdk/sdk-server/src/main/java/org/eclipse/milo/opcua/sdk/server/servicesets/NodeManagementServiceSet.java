/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.servicesets;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesResponse;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;

public interface NodeManagementServiceSet {

    CompletableFuture<AddNodesResponse> onAddNodes(ServiceRequestContext context, AddNodesRequest request);

    CompletableFuture<DeleteNodesResponse> onDeleteNodes(ServiceRequestContext context, DeleteNodesRequest request);

    CompletableFuture<AddReferencesResponse> onAddReferences(
        ServiceRequestContext context,
        AddReferencesRequest request
    );

    CompletableFuture<DeleteReferencesResponse> onDeleteReferences(
        ServiceRequestContext context,
        DeleteReferencesRequest request
    );

}
