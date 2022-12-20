/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesResponse;

public interface NodeManagementServices {

    CompletableFuture<AddNodesResponse> addNodesAsync(List<AddNodesItem> nodesToAdd);

    CompletableFuture<AddReferencesResponse> addReferencesAsync(List<AddReferencesItem> referencesToAdd);

    CompletableFuture<DeleteNodesResponse> deleteNodesAsync(List<DeleteNodesItem> nodesToDelete);

    CompletableFuture<DeleteReferencesResponse> deleteReferencesAsync(List<DeleteReferencesItem> referencesToDelete);

}
