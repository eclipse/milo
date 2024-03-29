package org.eclipse.milo.opcua.sdk.server.servicesets.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.util.GroupMapCollate;
import org.eclipse.milo.opcua.sdk.server.AddressSpace.ReadContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class AccessControlManager {

    private final OpcUaServer server;

    public AccessControlManager(OpcUaServer server) {
        this.server = server;
    }

    public List<Boolean> checkReadPermissions(Session session, List<ReadValueId> nodesToRead) {
        // TODO check AccessRestrictions attribute (AccessRestrictionType)

        List<PermissionCheckResult> results = GroupMapCollate.groupMapCollate(
            nodesToRead,
            ReadValueId::getAttributeId,
            id -> group -> {
                if (AttributeId.Value.uid().equals(id)) {
                    return checkReadPermissionsInternal(session, group);
                } else {
                    return Collections.nCopies(group.size(), PermissionCheckResult.ALLOWED);
                }
            }
        );

        return results.stream().map(r -> r == PermissionCheckResult.ALLOWED).toList();
    }

    private List<PermissionCheckResult> checkReadPermissionsInternal(
        Session session,
        List<ReadValueId> nodesToRead
    ) {

        assert nodesToRead.stream()
            .allMatch(rvi -> rvi.getAttributeId().equals(AttributeId.Value.uid()));

        List<PendingPermissionCheck> pendingChecks = nodesToRead.stream()
            .map(PendingPermissionCheck::new)
            .toList();

        if (session.getRoleIds().isEmpty()) {
            checkAccessLevels(server, session, nodesToRead, pendingChecks);
        } else {
            checkRolePermissions(server, session, nodesToRead, pendingChecks);

            // Check if any permissions are still pending.
            // This can happen if a Node's RolePermissions or UserRolePermissions was not
            // configured even though a RoleManager is configured.
            List<ReadValueId> noRolePermissionNodes = pendingChecks.stream()
                .filter(pc -> pc.result == PermissionCheckResult.PENDING)
                .map(pc -> pc.readValueId)
                .toList();

            List<PendingPermissionCheck> noRolePermissionChecks = pendingChecks.stream()
                .filter(pc -> pc.result == PermissionCheckResult.PENDING)
                .toList();

            if (!noRolePermissionNodes.isEmpty() && !noRolePermissionChecks.isEmpty()) {
                checkAccessLevels(server, session, noRolePermissionNodes, noRolePermissionChecks);
            }
        }

        return pendingChecks.stream().map(pc -> pc.result).toList();
    }

    private static void checkAccessLevels(
        OpcUaServer server,
        Session session,
        List<ReadValueId> nodesToRead,
        List<PendingPermissionCheck> pendingChecks
    ) {

        // Check the AccessLevel and UserAccessLevel attributes instead.
        List<ReadValueId> readValueIds = nodesToRead.stream()
            .flatMap(rvi -> {
                List<ReadValueId> list = List.of(
                    new ReadValueId(
                        rvi.getNodeId(), AttributeId.NodeClass.uid(), null, null),
                    new ReadValueId(
                        rvi.getNodeId(), AttributeId.AccessLevel.uid(), null, null),
                    new ReadValueId(
                        rvi.getNodeId(), AttributeId.UserAccessLevel.uid(), null, null)
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

        for (int i = 0; i < readValueIds.size(); i++) {
            Object v0 = values.get(i).getValue().getValue();
            Object v1 = values.get(i + 1).getValue().getValue();
            Object v2 = values.get(i + 2).getValue().getValue();

            if (v0 instanceof NodeClass nodeClass && nodeClass == NodeClass.Variable) {
                if (v1 instanceof UByte accessLevel
                    && v2 instanceof UByte userAccessLevel) {

                    Set<AccessLevel> accessLevels = AccessLevel.fromValue(accessLevel);
                    Set<AccessLevel> userAccessLevels = AccessLevel.fromValue(userAccessLevel);

                    boolean hasReadAccess = accessLevels.contains(AccessLevel.CurrentRead)
                        && userAccessLevels.contains(AccessLevel.CurrentRead);

                    pendingChecks.get(i).result = hasReadAccess ?
                        PermissionCheckResult.ALLOWED : PermissionCheckResult.DENIED;
                } else {
                    pendingChecks.get(i).result = PermissionCheckResult.DENIED;
                }
            } else {
                // AccessLevel and UserAccessLevel only apply to Value attribute of Variables.
                pendingChecks.get(i).result = PermissionCheckResult.ALLOWED;
            }
        }
    }

    private static void checkRolePermissions(
        OpcUaServer server,
        Session session,
        List<ReadValueId> nodesToRead,
        List<PendingPermissionCheck> pendingChecks
    ) {

        List<NodeId> roleIds = session.getRoleIds().orElse(List.of());

        // The Server has been configured with a RoleManager that provides Identity to RoleId
        // mappings, so we can check the RolePermissions and UserRolePermissions attributes.
        List<ReadValueId> readValueIds = nodesToRead.stream()
            .flatMap(rvi -> {
                List<ReadValueId> list = List.of(
                    new ReadValueId(
                        rvi.getNodeId(), AttributeId.NodeClass.uid(), null, null),
                    new ReadValueId(
                        rvi.getNodeId(), AttributeId.RolePermissions.uid(), null, null),
                    new ReadValueId(
                        rvi.getNodeId(), AttributeId.UserRolePermissions.uid(), null, null)
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

        for (int i = 0; i < readValueIds.size(); i++) {
            Object v0 = values.get(i).getValue().getValue();
            Object v1 = values.get(i + 1).getValue().getValue();
            Object v2 = values.get(i + 2).getValue().getValue();

            if (v0 instanceof NodeClass nodeClass && nodeClass == NodeClass.Variable) {
                if (v1 instanceof RolePermissionType[] rolePermissions
                    && v2 instanceof RolePermissionType[] userRolePermissions) {

                    if (Stream.of(rolePermissions).noneMatch(rp -> rp.getPermissions().getRead())) {
                        pendingChecks.get(i).result = PermissionCheckResult.DENIED;
                    } else {
                        boolean hasReadPermission = Stream.of(userRolePermissions)
                            .filter(rp -> roleIds.contains(rp.getRoleId()))
                            .anyMatch(rp -> rp.getPermissions().getRead());

                        pendingChecks.get(i).result = hasReadPermission ?
                            PermissionCheckResult.ALLOWED : PermissionCheckResult.DENIED;
                    }
                }
                // Else: leave it to a subsequent AccessLevel check.
            } else {
                pendingChecks.get(i).result = PermissionCheckResult.ALLOWED;
            }
        }
    }

    private static class PendingPermissionCheck {
        PermissionCheckResult result = PermissionCheckResult.PENDING;
        private final ReadValueId readValueId;

        private PendingPermissionCheck(ReadValueId readValueId) {
            this.readValueId = readValueId;
        }
    }

    private enum PermissionCheckResult {
        ALLOWED,
        DENIED,
        PENDING
    }

}
