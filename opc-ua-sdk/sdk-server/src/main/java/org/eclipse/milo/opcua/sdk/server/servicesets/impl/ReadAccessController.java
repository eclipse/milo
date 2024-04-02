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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.servicesets.impl.AbstractAccessController.AccessControlContext.AccessControlAttributes;
import org.eclipse.milo.opcua.sdk.server.servicesets.impl.AccessController.AccessResult;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

import static org.eclipse.milo.opcua.sdk.core.util.GroupMapCollate.groupMapCollate;

public class ReadAccessController extends AbstractAccessController {

    public ReadAccessController(OpcUaServer server) {
        super(server);
    }

    /**
     * Check if the current Session has read access to the Nodes and Attributes identified by
     * {@code readValueIds}.
     *
     * @param session the Session to check access for.
     * @param readValueIds the Nodes and Attributes to check access for.
     * @return a List of {@link AccessResult} indicating the access status for each
     *     {@link ReadValueId}.
     */
    public List<AccessResult> checkReadAccess(Session session, List<ReadValueId> readValueIds) {
        var context = new DefaultAccessControlContext(server, session);

        return checkReadAccess(context, readValueIds);
    }

    List<AccessResult> checkReadAccess(AccessControlContext context, List<ReadValueId> readValueIds) {
        List<PendingReadCheck> allPendingChecks = readValueIds.stream()
            .map(PendingReadCheck::new)
            .toList();

        List<NodeId> nodeIds = readValueIds.stream()
            .map(ReadValueId::getNodeId).toList();

        Map<NodeId, AccessControlAttributes> accessControlAttributes =
            context.readAccessControlAttributes(nodeIds);

        List<AccessResult> accessRestrictionResults =
            checkAccessRestrictions(context, nodeIds, accessControlAttributes);

        for (int i = 0; i < accessRestrictionResults.size(); i++) {
            allPendingChecks.get(i).result = accessRestrictionResults.get(i);
        }

        // Filter out any nodes that have been denied by AccessRestrictions and check the rest
        // against role permissions and/or access levels.
        List<PendingReadCheck> remainingChecks = allPendingChecks.stream()
            .filter(pc -> pc.result != AccessResult.DENIED)
            .toList();

        List<ReadValueId> remainingNodes = remainingChecks.stream()
            .map(pc -> pc.readValueId)
            .toList();

        List<AccessResult> remainingResults = groupMapCollate(
            remainingNodes,
            ReadValueId::getAttributeId,
            id -> group -> {
                if (AttributeId.Value.uid().equals(id)) {
                    return checkValueAttributeAccess(context, group, accessControlAttributes);
                } else if (AttributeId.RolePermissions.uid().equals(id)) {
                    return checkRolePermissionsAttributeAccess(context, group, accessControlAttributes);
                } else {
                    return Collections.nCopies(group.size(), AccessResult.ALLOWED);
                }
            }
        );

        for (int i = 0; i < remainingNodes.size(); i++) {
            remainingChecks.get(i).result = remainingResults.get(i);
        }

        return allPendingChecks.stream().map(pc -> pc.result).toList();
    }

    private static List<AccessResult> checkValueAttributeAccess(
        AccessControlContext context,
        List<ReadValueId> readValueIds,
        Map<NodeId, AccessControlAttributes> accessControlAttributes
    ) {

        var results = new ArrayList<AccessResult>();

        for (ReadValueId readValueId : readValueIds) {
            AccessControlAttributes attributes =
                accessControlAttributes.get(readValueId.getNodeId());

            UByte userAccessLevel = attributes.userAccessLevel();
            RolePermissionType[] userRolePermissions = attributes.userRolePermissions();

            List<NodeId> roleIds = context.getRoleIds().orElse(null);

            if (roleIds != null && userRolePermissions != null) {
                boolean hasReadPermission = Stream.of(userRolePermissions)
                    .filter(rp -> roleIds.contains(rp.getRoleId()))
                    .anyMatch(rp -> rp.getPermissions().getRead());

                if (hasReadPermission) {
                    results.add(AccessResult.ALLOWED);
                } else {
                    results.add(AccessResult.DENIED);
                }
            } else if (userAccessLevel != null) {
                Set<AccessLevel> accessLevels =
                    AccessLevel.fromValue(userAccessLevel.byteValue());

                if (accessLevels.contains(AccessLevel.CurrentRead)) {
                    results.add(AccessResult.ALLOWED);
                } else {
                    results.add(AccessResult.DENIED);
                }
            } else {
                results.add(AccessResult.ALLOWED);
            }
        }

        return results;
    }

    private static List<AccessResult> checkRolePermissionsAttributeAccess(
        AccessControlContext context,
        List<ReadValueId> readValueIds,
        Map<NodeId, AccessControlAttributes> accessControlAttributes
    ) {

        var results = new ArrayList<AccessResult>();

        for (ReadValueId readValueId : readValueIds) {
            AccessControlAttributes attributes =
                accessControlAttributes.get(readValueId.getNodeId());

            RolePermissionType[] userRolePermissions = attributes.userRolePermissions();

            List<NodeId> roleIds = context.getRoleIds().orElse(null);

            if (roleIds != null && userRolePermissions != null) {
                boolean hasReadPermission = Stream.of(userRolePermissions)
                    .filter(rp -> roleIds.contains(rp.getRoleId()))
                    .anyMatch(rp -> rp.getPermissions().getReadRolePermissions());

                if (hasReadPermission) {
                    results.add(AccessResult.ALLOWED);
                } else {
                    results.add(AccessResult.DENIED);
                }
            } else {
                results.add(AccessResult.ALLOWED);
            }
        }

        return results;
    }

    private static class PendingReadCheck {
        AccessResult result = null;
        final ReadValueId readValueId;

        protected PendingReadCheck(ReadValueId readValueId) {
            this.readValueId = readValueId;
        }
    }

}
