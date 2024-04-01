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
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.util.GroupMapCollate;
import org.eclipse.milo.opcua.sdk.server.AddressSpace.ReadContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.servicesets.impl.AccessController.AccessControlContext.AccessControlAttributes;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.NodeIds;
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
        Map<NodeId, AccessControlAttributes> attributes =
            context.readAccessControlAttributes(List.of(nodeId));

        // TODO AccessRestrictions might affect browse permissions.

        List<NodeId> roleIds = context.getRoleIds().orElse(null);

        if (roleIds != null) {
            // If non-null, there is a Session and Server has been configured with a
            // RoleManager that provides Identity to RoleId mappings, so we can proceed with
            // checking the RolePermissions and UserRolePermissions attributes.

            RolePermissionType[] userRolePermissions =
                attributes.get(nodeId).userRolePermissions();

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

        Map<NodeId, AccessControlAttributes> accessControlAttributes = context.readAccessControlAttributes(
            nodesToRead.stream()
                .map(ReadValueId::getNodeId).toList()
        );

        List<PermissionCheckResult> accessRestrictionResults =
            checkAccessRestrictions(context, nodesToRead, accessControlAttributes);

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
                        return checkAccessLevels(group, accessControlAttributes);
                    } else {
                        List<NodeId> roleIds = context.getRoleIds().orElseThrow();
                        return checkRolePermissions(group, accessControlAttributes, roleIds);
                    }
                } else {
                    return Collections.nCopies(group.size(), PermissionCheckResult.ALLOWED);
                }
            }
        );

        for (int i = 0; i < remainingNodes.size(); i++) {
            remainingChecks.get(i).result = remainingResults.get(i);
        }

        // The RolePermissions attribute is a special case, only SecurityAdmins can read it.
        context.getRoleIds().ifPresent(roleIds -> {
            for (PendingPermissionCheck ppc : allPendingChecks) {
                if (Objects.equals(AttributeId.RolePermissions.uid(), ppc.readValueId.getAttributeId())) {
                    if (roleIds.stream().noneMatch(id -> id.equals(NodeIds.WellKnownRole_SecurityAdmin))) {
                        ppc.result = PermissionCheckResult.DENIED;
                    }
                }
            }
        });

        return allPendingChecks.stream()
            .map(r -> r.result == PermissionCheckResult.ALLOWED)
            .toList();
    }

    private static List<PermissionCheckResult> checkAccessRestrictions(
        AccessControlContext context,
        List<ReadValueId> nodesToRead,
        Map<NodeId, AccessControlAttributes> accessControlAttributes
    ) {

        var results = new ArrayList<PermissionCheckResult>();

        MessageSecurityMode securityMode = context.getSecurityMode();

        for (ReadValueId readValueId : nodesToRead) {
            NodeId nodeId = readValueId.getNodeId();
            AccessControlAttributes attributes = accessControlAttributes.get(nodeId);
            NodeClass nodeClass = attributes.nodeClass();
            AccessRestrictionType accessRestrictions = attributes.accessRestrictions();

            if (nodeClass == NodeClass.Variable) {
                if (accessRestrictions != null) {
                    if (accessRestrictions.getEncryptionRequired()) {
                        PermissionCheckResult result =
                            securityMode == MessageSecurityMode.SignAndEncrypt ?
                                PermissionCheckResult.ALLOWED : PermissionCheckResult.DENIED;

                        results.add(result);
                    } else if (accessRestrictions.getSigningRequired()) {
                        PermissionCheckResult result =
                            (securityMode == MessageSecurityMode.Sign
                                || securityMode == MessageSecurityMode.SignAndEncrypt) ?
                                PermissionCheckResult.ALLOWED : PermissionCheckResult.DENIED;

                        results.add(result);
                    } else {
                        results.add(PermissionCheckResult.ALLOWED);
                    }
                } else {
                    // TODO check if there are Namespace-level restrictions
                    results.add(PermissionCheckResult.ALLOWED);
                }
            } else {
                // AccessRestrictions only apply to Variables.
                results.add(PermissionCheckResult.ALLOWED);
            }
        }

        return results;
    }

    private static List<PermissionCheckResult> checkAccessLevels(
        List<ReadValueId> nodesToRead,
        Map<NodeId, AccessControlAttributes> accessControlAttributes
    ) {

        var results = new ArrayList<PermissionCheckResult>();

        for (ReadValueId readValueId : nodesToRead) {
            NodeId nodeId = readValueId.getNodeId();
            AccessControlAttributes attributes = accessControlAttributes.get(nodeId);
            NodeClass nodeClass = attributes.nodeClass;
            UByte userAccessLevel = attributes.userAccessLevel;

            if (nodeClass == NodeClass.Variable) {
                if (userAccessLevel != null) {
                    Set<AccessLevel> userAccessLevels = AccessLevel.fromValue(userAccessLevel);

                    if (userAccessLevels.contains(AccessLevel.CurrentRead)) {
                        results.add(PermissionCheckResult.ALLOWED);
                    } else {
                        results.add(PermissionCheckResult.DENIED);
                    }
                } else {
                    // If these are null the Node probably doesn't exist, allow it to be read
                    // and fail later in the Read service.
                    results.add(PermissionCheckResult.ALLOWED);
                }
            } else {
                // AccessLevel and UserAccessLevel only apply to Value attribute of Variables.
                results.add(PermissionCheckResult.ALLOWED);
            }
        }

        return results;
    }

    private static List<PermissionCheckResult> checkRolePermissions(
        List<ReadValueId> nodesToRead,
        Map<NodeId, AccessControlAttributes> accessControlAttributes,
        List<NodeId> roleIds
    ) {

        // TODO NamespaceMetadataType might define
        //  DefaultRolePermissions and DefaultUserRolePermissions

        List<PendingPermissionCheck> pending = nodesToRead.stream()
            .map(PendingPermissionCheck::new)
            .toList();

        for (int i = 0; i < nodesToRead.size(); i++) {
            NodeId nodeId = nodesToRead.get(i).getNodeId();
            AccessControlAttributes attributes = accessControlAttributes.get(nodeId);
            NodeClass nodeClass = attributes.nodeClass();
            RolePermissionType[] userRolePermissions = attributes.userRolePermissions();

            if (nodeClass == NodeClass.Variable) {
                if (userRolePermissions != null) {
                    boolean hasReadPermission = Stream.of(userRolePermissions)
                        .filter(rp -> roleIds.contains(rp.getRoleId()))
                        .anyMatch(rp -> rp.getPermissions().getRead());

                    pending.get(i).result = hasReadPermission ?
                        PermissionCheckResult.ALLOWED : PermissionCheckResult.DENIED;
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
                checkAccessLevels(remainingReadValueIds, accessControlAttributes);

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

        Map<NodeId, AccessControlAttributes> readAccessControlAttributes(List<NodeId> nodeIds);

        record AccessControlAttributes(
            @Nullable NodeClass nodeClass,
            @Nullable AccessRestrictionType accessRestrictions,
            @Nullable UByte userAccessLevel,
            RolePermissionType @Nullable [] userRolePermissions
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
            return session.getRoleIds();
        }

        @Override
        public MessageSecurityMode getSecurityMode() {
            return session.getEndpoint().getSecurityMode();
        }

        @Override
        public Map<NodeId, AccessControlAttributes> readAccessControlAttributes(List<NodeId> nodeIds) {
            List<ReadValueId> readValueIds = nodeIds.stream()
                .flatMap(id -> {
                    List<ReadValueId> list = List.of(
                        new ReadValueId(
                            id, AttributeId.NodeClass.uid(), null, null),
                        new ReadValueId(
                            id, AttributeId.AccessRestrictions.uid(), null, null),
                        new ReadValueId(
                            id, AttributeId.UserAccessLevel.uid(), null, null),
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

            var attributes = new HashMap<NodeId, AccessControlAttributes>();

            for (int i = 0; i < readValueIds.size(); i += 4) {
                NodeId nodeId = readValueIds.get(i).getNodeId();

                Object v0 = values.get(i).getValue().getValue();
                Object v1 = values.get(i + 1).getValue().getValue();
                Object v2 = values.get(i + 2).getValue().getValue();
                Object v3 = values.get(i + 3).getValue().getValue();

                NodeClass nodeClass = null;
                AccessRestrictionType accessRestrictions = null;
                UByte userAccessLevel = null;
                RolePermissionType[] userRolePermissions = null;

                if (v0 instanceof NodeClass nc) {
                    nodeClass = nc;
                }
                if (v1 instanceof AccessRestrictionType art) {
                    accessRestrictions = art;
                }
                if (v2 instanceof UByte ual) {
                    userAccessLevel = ual;
                }
                if (v3 instanceof RolePermissionType[] rpt) {
                    userRolePermissions = rpt;
                }

                AccessControlAttributes aca = new AccessControlAttributes(
                    nodeClass, accessRestrictions, userAccessLevel, userRolePermissions);

                attributes.put(nodeId, aca);
            }

            return attributes;
        }
    }

}
