/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.servicesets.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.jetbrains.annotations.Nullable;

public interface AccessController {

    /**
     * Check if the current Session has read access to the Nodes and Attributes identified by
     * {@code readValueIds}.
     *
     * @param session the Session to check access for.
     * @param readValueIds the Nodes and Attributes to check access for.
     * @return a List of {@link AccessResult} indicating the access status for each
     *     {@link ReadValueId}.
     */
    List<AccessResult> checkReadAccess(Session session, List<ReadValueId> readValueIds);

    /**
     * Check if the current Session has write access to the Nodes and Attributes identified by
     * {@code writeValues}.
     *
     * @param session the Session to check access for.
     * @param writeValues the Nodes and Attributes to check access for.
     * @return a List of {@link AccessResult} indicating the access status for each
     *     {@link WriteValue}.
     */
    List<AccessResult> checkWriteAccess(Session session, List<WriteValue> writeValues);

    /**
     * Check if the current Session has permission to browse the Nodes identified by
     * {@code nodeIds}.
     *
     * @param session the Session to check access for.
     * @param nodeIds the {@link NodeId}s of Nodes to check access for.
     * @return a List of {@link AccessResult} indicating the access status for each
     *     {@link NodeId}.
     */
    List<AccessResult> checkBrowseAccess(Session session, List<NodeId> nodeIds);

    /**
     * Check if the current Session has permission to call Methods on the Objects or ObjectTypes
     * identified in each {@link CallMethodRequest}.
     *
     * @param session the Session to check access for.
     * @param requests the {@link CallMethodRequest}s to check access for.
     * @return a List of {@link AccessResult} indicating the access status for each
     *     {@link CallMethodRequest}.
     */
    List<AccessResult> checkCallAccess(Session session, List<CallMethodRequest> requests);

    /**
     * Check if the current Session has permission to add References to the Nodes identified by
     * {@code nodeIds}.
     *
     * @param session the Session to check access for.
     * @param nodeIds the {@link NodeId}s of Nodes to check access for.
     * @return a List of {@link AccessResult} indicating the access status for each
     *     {@link NodeId}.
     */
    List<AccessResult> checkAddReferencesAccess(Session session, List<NodeId> nodeIds);

    /**
     * Check if the current Session has permission to delete Nodes identified by {@code nodeIds}.
     *
     * @param session the Session to check access for.
     * @param nodeIds the {@link NodeId}s of Nodes to check access for.
     * @return a List of {@link AccessResult} indicating the access status for each
     *     {@link NodeId}.
     */
    List<AccessResult> checkDeleteNodesAccess(Session session, List<NodeId> nodeIds);

    /**
     * Check if the current Session has permission to delete References from the Nodes identified
     * by {@code nodeIds}.
     *
     * @param session the Session to check access for.
     * @param nodeIds the {@link NodeId}s of Nodes to check access for.
     * @return a List of {@link AccessResult} indicating the access status for each
     *     {@link NodeId}.
     */
    List<AccessResult> checkDeleteReferencesAccess(Session session, List<NodeId> nodeIds);

    enum AccessResult {
        ALLOWED,
        DENIED
    }

    record AccessControlAttributes(
        @Nullable NodeClass nodeClass,
        @Nullable AccessRestrictionType accessRestrictions,
        @Nullable UByte userAccessLevel,
        RolePermissionType @Nullable [] userRolePermissions
    ) {}

    interface AccessControlContext {

        Optional<List<NodeId>> getRoleIds();

        MessageSecurityMode getSecurityMode();

        Map<NodeId, AccessController.AccessControlAttributes> readAccessControlAttributes(List<NodeId> nodeIds);

    }

}
