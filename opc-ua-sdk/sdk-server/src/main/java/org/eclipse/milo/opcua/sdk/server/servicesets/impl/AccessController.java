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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.util.GroupMapCollate;
import org.eclipse.milo.opcua.sdk.server.AddressSpace.ReadContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.servicesets.impl.AccessController.AccessControlContext.AccessLevelAttributes;
import org.eclipse.milo.opcua.sdk.server.servicesets.impl.AccessController.AccessControlContext.AccessRestrictionAttributes;
import org.eclipse.milo.opcua.sdk.server.servicesets.impl.AccessController.AccessControlContext.RolePermissionAttributes;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;
import org.jetbrains.annotations.Nullable;

public class AccessController {

    private final OpcUaServer server;

    public AccessController(OpcUaServer server) {
        this.server = server;
    }

    /**
     * Check if the current Session has Browse permission for the given {@code nodeId}.
     *
     * @param session the {@link Session} to check Browse permission for.
     * @param nodeId the {@link NodeId} to check Browse permission for.
     * @return {@code true} if the current Session has Browse permission for {@code nodeId}.
     */
    public boolean checkBrowsePermission(Session session, NodeId nodeId) {
        var context = new DefaultAccessControlContext(server, session);

        return checkBrowsePermission(context, nodeId);
    }

    /**
     * Check if the current Session has Read permissions for the given {@code nodesToRead}.
     *
     * @param session the {@link Session} to check Read permissions for.
     * @param nodesToRead the {@link ReadValueId}s to check Read permissions for.
     * @return a Map of each ReadValueId to a Boolean indicating whether it has Read permission.
     */
    public Map<ReadValueId, Boolean> checkReadPermissions(Session session, List<ReadValueId> nodesToRead) {
        var context = new DefaultAccessControlContext(server, session);

        List<Boolean> permissions = checkReadPermissions(context, nodesToRead);

        var permissionsMap = new HashMap<ReadValueId, Boolean>();
        for (int i = 0; i < nodesToRead.size(); i++) {
            permissionsMap.put(nodesToRead.get(i), permissions.get(i));
        }
        return permissionsMap;
    }

    static boolean checkBrowsePermission(AccessControlContext context, NodeId nodeId) {
        List<NodeId> roleIds = context.getRoleIds().orElse(null);

        if (roleIds != null) {
            // If non-null, there is a Session and Server has been configured with a
            // RoleManager that provides Identity to RoleId mappings, so we can proceed with
            // checking the RolePermissions and UserRolePermissions attributes.

            List<RolePermissionAttributes> attributes =
                context.readRolePermissionsAttributes(List.of(nodeId));

            RolePermissionType[] rolePermissions = attributes.get(0).rolePermissions();
            RolePermissionType[] userRolePermissions = attributes.get(0).userRolePermissions();

            if (rolePermissions != null) {
                if (Stream.of(rolePermissions)
                    .noneMatch(rp -> rp.getPermissions().getBrowse())) {

                    return false;
                }
            }

            if (userRolePermissions != null) {
                return Stream.of(userRolePermissions)
                    .filter(rp -> roleIds.contains(rp.getRoleId()))
                    .anyMatch(rp -> rp.getPermissions().getBrowse());
            }
        }

        // Node not found or no RolePermissions/UserRolePermissions attribute.
        return true;
    }

    static List<Boolean> checkReadPermissions(AccessControlContext context, List<ReadValueId> nodesToRead) {
        List<PendingPermissionCheck> allPendingChecks = nodesToRead.stream()
            .map(PendingPermissionCheck::new)
            .toList();

        List<PermissionCheckResult> accessRestrictionResults =
            checkAccessRestrictions(context, nodesToRead);

        for (int i = 0; i < accessRestrictionResults.size(); i++) {
            allPendingChecks.get(i).result = accessRestrictionResults.get(i);
        }

        // Filter out any nodes that have been denied by AccessRestrictions and check the rest
        // against role permissions and/or access levels.
        List<PendingPermissionCheck> remainingChecks = allPendingChecks.stream()
            .filter(pc -> pc.result != PermissionCheckResult.DENIED)
            .toList();

        List<ReadValueId> remainingNodes = remainingChecks.stream()
            .map(pc -> pc.readValueId)
            .toList();

        List<PermissionCheckResult> remainingResults = GroupMapCollate.groupMapCollate(
            remainingNodes,
            ReadValueId::getAttributeId,
            id -> group -> {
                if (AttributeId.Value.uid().equals(id)) {
                    if (context.getRoleIds().isEmpty()) {
                        return checkAccessLevels(context, group);
                    } else {
                        return checkRolePermissions(context, group);
                    }
                } else {
                    return Collections.nCopies(group.size(), PermissionCheckResult.ALLOWED);
                }
            }
        );

        for (int i = 0; i < remainingNodes.size(); i++) {
            remainingChecks.get(i).result = remainingResults.get(i);
        }

        return allPendingChecks.stream()
            .map(r -> r.result == PermissionCheckResult.ALLOWED)
            .toList();
    }

    private static List<PermissionCheckResult> checkAccessRestrictions(
        AccessControlContext context,
        List<ReadValueId> nodesToRead
    ) {

        // TODO NamespaceMetadataType might define DefaultAccessRestrictions

        List<PendingPermissionCheck> pending = nodesToRead.stream()
            .map(PendingPermissionCheck::new)
            .toList();

        List<PermissionCheckResult> results = GroupMapCollate.groupMapCollate(
            nodesToRead,
            rvi -> rvi.getNodeId().getNamespaceIndex(),
            idx -> group -> {
                List<PendingPermissionCheck> pendingChecks = group.stream()
                    .map(PendingPermissionCheck::new)
                    .toList();

                List<AccessRestrictionAttributes> attributes = context.readAccessRestrictionAttributes(
                    group.stream()
                        .map(ReadValueId::getNodeId).toList()
                );

                MessageSecurityMode securityMode = context.getSecurityMode();

                for (int i = 0; i < group.size(); i++) {
                    AccessRestrictionAttributes attribute = attributes.get(i);
                    AccessRestrictionType accessRestrictions = attribute.accessRestrictions();

                    if (accessRestrictions != null) {
                        if (accessRestrictions.getEncryptionRequired()) {
                            pendingChecks.get(i).result =
                                securityMode == MessageSecurityMode.SignAndEncrypt ?
                                    PermissionCheckResult.ALLOWED : PermissionCheckResult.DENIED;
                        } else if (accessRestrictions.getSigningRequired()) {
                            pendingChecks.get(i).result =
                                (securityMode == MessageSecurityMode.Sign
                                    || securityMode == MessageSecurityMode.SignAndEncrypt) ?
                                    PermissionCheckResult.ALLOWED : PermissionCheckResult.DENIED;
                        } else {
                            pendingChecks.get(i).result = PermissionCheckResult.ALLOWED;
                        }
                    } else {
                        pendingChecks.get(i).result = PermissionCheckResult.ALLOWED;
                    }
                }

                return pendingChecks.stream().map(pc -> pc.result).toList();
            }
        );

        for (int i = 0; i < results.size(); i++) {
            pending.get(i).result = results.get(i);
        }

        return pending.stream().map(pc -> pc.result).toList();
    }

    private static List<PermissionCheckResult> checkAccessLevels(
        AccessControlContext context,
        List<ReadValueId> nodesToRead
    ) {

        List<PendingPermissionCheck> pending = nodesToRead.stream()
            .map(PendingPermissionCheck::new)
            .toList();

        List<AccessLevelAttributes> accessLevelAttributes = context.readAccessLevelAttributes(
            nodesToRead.stream()
                .map(ReadValueId::getNodeId).toList()
        );

        for (int i = 0; i < nodesToRead.size(); i++) {
            AccessLevelAttributes attributes = accessLevelAttributes.get(i);
            NodeClass nodeClass = attributes.nodeClass;
            UByte accessLevel = attributes.accessLevel;
            UByte userAccessLevel = attributes.userAccessLevel;

            if (nodeClass == NodeClass.Variable) {
                if (accessLevel != null && userAccessLevel != null) {
                    Set<AccessLevel> accessLevels = AccessLevel.fromValue(accessLevel);
                    Set<AccessLevel> userAccessLevels = AccessLevel.fromValue(userAccessLevel);

                    boolean hasReadAccess = accessLevels.contains(AccessLevel.CurrentRead)
                        && userAccessLevels.contains(AccessLevel.CurrentRead);

                    pending.get(i).result = hasReadAccess ?
                        PermissionCheckResult.ALLOWED : PermissionCheckResult.DENIED;
                } else {
                    // If these are null the Node probably doesn't exist, allow it to be read
                    // and fail later in the Read service.
                    pending.get(i).result = PermissionCheckResult.ALLOWED;
                }
            } else {
                // AccessLevel and UserAccessLevel only apply to Value attribute of Variables.
                pending.get(i).result = PermissionCheckResult.ALLOWED;
            }
        }

        return pending.stream().map(pc -> pc.result).toList();
    }

    private static List<PermissionCheckResult> checkRolePermissions(
        AccessControlContext context,
        List<ReadValueId> nodesToRead
    ) {

        // TODO NamespaceMetadataType might define
        //  DefaultRolePermissions and DefaultUserRolePermissions

        List<PendingPermissionCheck> pending = nodesToRead.stream()
            .map(PendingPermissionCheck::new)
            .toList();

        List<RolePermissionAttributes> attributes = context.readRolePermissionsAttributes(
            nodesToRead.stream()
                .map(ReadValueId::getNodeId).toList()
        );

        List<NodeId> roleIds = context.getRoleIds().orElse(List.of());

        for (int i = 0; i < nodesToRead.size(); i++) {
            RolePermissionAttributes attribute = attributes.get(i);
            NodeClass nodeClass = attribute.nodeClass();
            RolePermissionType[] rolePermissions = attribute.rolePermissions();
            RolePermissionType[] userRolePermissions = attribute.userRolePermissions();

            if (nodeClass == NodeClass.Variable) {
                if (rolePermissions != null && userRolePermissions != null) {
                    if (Stream.of(rolePermissions).noneMatch(rp -> rp.getPermissions().getRead())) {
                        pending.get(i).result = PermissionCheckResult.DENIED;
                    } else {
                        boolean hasReadPermission = Stream.of(userRolePermissions)
                            .filter(rp -> roleIds.contains(rp.getRoleId()))
                            .anyMatch(rp -> rp.getPermissions().getRead());

                        pending.get(i).result = hasReadPermission ?
                            PermissionCheckResult.ALLOWED : PermissionCheckResult.DENIED;
                    }
                }
                // Else: leave it to a subsequent AccessLevel check.
            } else {
                pending.get(i).result = PermissionCheckResult.ALLOWED;
            }
        }

        List<PendingPermissionCheck> remainingChecks = pending.stream()
            .filter(pc -> pc.result == null)
            .toList();

        if (!remainingChecks.isEmpty()) {
            List<ReadValueId> remainingReadValueIds = remainingChecks.stream()
                .map(pc -> pc.readValueId)
                .toList();

            List<PermissionCheckResult> results =
                checkAccessLevels(context, remainingReadValueIds);

            for (int i = 0; i < remainingChecks.size(); i++) {
                remainingChecks.get(i).result = results.get(i);
            }
        }

        return pending.stream().map(pc -> pc.result).toList();
    }

    private static class PendingPermissionCheck {
        PermissionCheckResult result = null;
        private final ReadValueId readValueId;

        private PendingPermissionCheck(ReadValueId readValueId) {
            this.readValueId = readValueId;
        }
    }

    private enum PermissionCheckResult {
        ALLOWED,
        DENIED
    }

    interface AccessControlContext {

        Optional<List<NodeId>> getRoleIds();

        MessageSecurityMode getSecurityMode();

        List<AccessRestrictionAttributes> readAccessRestrictionAttributes(List<NodeId> nodesToRead);

        List<AccessLevelAttributes> readAccessLevelAttributes(List<NodeId> nodesToRead);

        List<RolePermissionAttributes> readRolePermissionsAttributes(List<NodeId> nodesToRead);

        record AccessRestrictionAttributes(@Nullable AccessRestrictionType accessRestrictions) {}

        record AccessLevelAttributes(
            @Nullable NodeClass nodeClass,
            @Nullable UByte accessLevel,
            @Nullable UByte userAccessLevel
        ) {}

        record RolePermissionAttributes(
            @Nullable NodeClass nodeClass,
            @Nullable RolePermissionType[] rolePermissions,
            @Nullable RolePermissionType[] userRolePermissions
        ) {}

    }

    static class DefaultAccessControlContext implements AccessControlContext {

        private final OpcUaServer server;
        private final Session session;

        public DefaultAccessControlContext(OpcUaServer server, Session session) {
            this.server = server;
            this.session = session;
        }

        @Override
        public Optional<List<NodeId>> getRoleIds() {
            return Optional.empty();
        }

        @Override
        public MessageSecurityMode getSecurityMode() {
            return null;
        }

        @Override
        public List<AccessRestrictionAttributes> readAccessRestrictionAttributes(List<NodeId> nodesToRead) {
            List<ReadValueId> readValueIds = nodesToRead.stream()
                .map(id ->
                    new ReadValueId(
                        id,
                        AttributeId.AccessRestrictions.uid(),
                        null,
                        null
                    )
                )
                .toList();

            List<DataValue> values = server.getAddressSpaceManager().read(
                new ReadContext(server, session),
                0.0,
                TimestampsToReturn.Neither,
                readValueIds
            );

            var attributes = new ArrayList<AccessRestrictionAttributes>();

            for (int i = 0; i < readValueIds.size(); i++) {
                Object v = values.get(i).getValue().getValue();
                AccessRestrictionType accessRestrictions = null;

                if (v instanceof AccessRestrictionType art) {
                    accessRestrictions = art;
                }

                attributes.add(new AccessRestrictionAttributes(accessRestrictions));
            }

            return attributes;
        }

        @Override
        public List<AccessLevelAttributes> readAccessLevelAttributes(List<NodeId> nodesToRead) {
            List<ReadValueId> readValueIds = nodesToRead.stream()
                .flatMap(id -> {
                    List<ReadValueId> list = List.of(
                        new ReadValueId(
                            id, AttributeId.NodeClass.uid(), null, null),
                        new ReadValueId(
                            id, AttributeId.AccessLevel.uid(), null, null),
                        new ReadValueId(
                            id, AttributeId.UserAccessLevel.uid(), null, null)
                    );

                    return list.stream();
                })
                .toList();

            List<DataValue> values = server.getAddressSpaceManager().read(
                new ReadContext(server, session),
                0.0,
                TimestampsToReturn.Neither,
                readValueIds
            );

            var attributes = new ArrayList<AccessLevelAttributes>();

            for (int i = 0; i < readValueIds.size(); i += 3) {
                Object v0 = values.get(i).getValue().getValue();
                Object v1 = values.get(i + 1).getValue().getValue();
                Object v2 = values.get(i + 2).getValue().getValue();

                NodeClass nodeClass = null;
                UByte accessLevel = null;
                UByte userAccessLevel = null;

                if (v0 instanceof NodeClass nc) {
                    nodeClass = nc;
                }
                if (v1 instanceof UByte al) {
                    accessLevel = al;
                }
                if (v2 instanceof UByte ual) {
                    userAccessLevel = ual;
                }

                attributes.add(new AccessLevelAttributes(nodeClass, accessLevel, userAccessLevel));
            }

            return attributes;
        }

        @Override
        public List<RolePermissionAttributes> readRolePermissionsAttributes(
            List<NodeId> nodesToRead
        ) {

            List<ReadValueId> readValueIds = nodesToRead.stream()
                .flatMap(id -> {
                    List<ReadValueId> list = List.of(
                        new ReadValueId(
                            id, AttributeId.NodeClass.uid(), null, null),
                        new ReadValueId(
                            id, AttributeId.RolePermissions.uid(), null, null),
                        new ReadValueId(
                            id, AttributeId.UserRolePermissions.uid(), null, null)
                    );

                    return list.stream();
                })
                .toList();

            List<DataValue> values = server.getAddressSpaceManager().read(
                new ReadContext(server, session),
                0.0,
                TimestampsToReturn.Neither,
                readValueIds
            );

            var attributes = new ArrayList<RolePermissionAttributes>();

            for (int i = 0; i < readValueIds.size(); i += 3) {
                Object v0 = values.get(i).getValue().getValue();
                Object v1 = values.get(i + 1).getValue().getValue();
                Object v2 = values.get(i + 2).getValue().getValue();

                NodeClass nodeClass = null;
                RolePermissionType[] rolePermissions = null;
                RolePermissionType[] userRolePermissions = null;

                if (v0 instanceof NodeClass nc) {
                    nodeClass = nc;
                }
                if (v1 instanceof RolePermissionType[] rpt) {
                    rolePermissions = rpt;
                }
                if (v2 instanceof RolePermissionType[] rpt) {
                    userRolePermissions = rpt;
                }

                attributes.add(new RolePermissionAttributes(nodeClass, rolePermissions, userRolePermissions));
            }

            return attributes;
        }

    }

}
