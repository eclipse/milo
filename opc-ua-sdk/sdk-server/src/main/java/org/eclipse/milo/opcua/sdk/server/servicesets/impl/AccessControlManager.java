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
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class AccessControlManager {

    private final OpcUaServer server;

    public AccessControlManager(OpcUaServer server) {
        this.server = server;
    }

    public List<Boolean> checkReadPermissions(Session session, List<ReadValueId> nodesToRead) {
        List<PendingPermissionCheck> allPendingChecks = nodesToRead.stream()
            .map(PendingPermissionCheck::new)
            .toList();

        checkAccessRestrictions(server, session, allPendingChecks);

        // Filter out any nodes that have been denied by AccessRestrictions and check the rest
        // against role permissions and/or access levels.
        List<PendingPermissionCheck> remainingPendingChecks = allPendingChecks.stream()
            .filter(pc -> pc.result != PermissionCheckResult.DENIED)
            .toList();

        List<PermissionCheckResult> remainingResults = GroupMapCollate.groupMapCollate(
            remainingPendingChecks,
            p -> p.readValueId.getAttributeId(),
            id -> group -> {
                if (AttributeId.Value.uid().equals(id)) {
                    if (session.getRoleIds().isEmpty()) {
                        checkAccessLevels(server, session, group);
                    } else {
                        checkRolePermissions(server, session, group);
                    }

                    return group.stream().map(pc -> pc.result).toList();
                } else {
                    return Collections.nCopies(group.size(), PermissionCheckResult.ALLOWED);
                }
            }
        );

        for (int i = 0; i < remainingPendingChecks.size(); i++) {
            remainingPendingChecks.get(i).result = remainingResults.get(i);
        }

        return allPendingChecks.stream()
            .map(r -> r.result == PermissionCheckResult.ALLOWED)
            .toList();
    }

    private static void checkAccessRestrictions(
        OpcUaServer server,
        Session session,
        List<PendingPermissionCheck> pendingPermissionChecks
    ) {

        // TODO NamespaceMetadataType might define DefaultAccessRestrictions

        List<ReadValueId> nodesToRead = pendingPermissionChecks.stream()
            .map(pc -> pc.readValueId)
            .toList();

        List<PermissionCheckResult> results = GroupMapCollate.groupMapCollate(
            nodesToRead,
            rvi -> rvi.getNodeId().getNamespaceIndex(),
            idx -> group -> {
                List<PendingPermissionCheck> pendingChecks = group.stream()
                    .map(PendingPermissionCheck::new)
                    .toList();

                List<ReadValueId> readValueIds = nodesToRead.stream()
                    .map(rvi ->
                        new ReadValueId(
                            rvi.getNodeId(),
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

                MessageSecurityMode securityMode = session.getEndpoint().getSecurityMode();

                for (int i = 0; i < readValueIds.size(); i++) {
                    Object v = values.get(i).getValue().getValue();
                    if (v instanceof AccessRestrictionType accessRestrictions) {
                        if (accessRestrictions.getSigningRequired()) {
                            if (accessRestrictions.getEncryptionRequired()) {
                                pendingChecks.get(i).result =
                                    securityMode == MessageSecurityMode.SignAndEncrypt ?
                                        PermissionCheckResult.ALLOWED : PermissionCheckResult.DENIED;
                            } else {
                                pendingChecks.get(i).result =
                                    (securityMode == MessageSecurityMode.Sign
                                        || securityMode == MessageSecurityMode.SignAndEncrypt) ?
                                        PermissionCheckResult.ALLOWED : PermissionCheckResult.DENIED;
                            }
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
            pendingPermissionChecks.get(i).result = results.get(i);
        }
    }

    private static void checkAccessLevels(
        OpcUaServer server,
        Session session,
        List<PendingPermissionCheck> pendingChecks
    ) {

        List<ReadValueId> nodesToRead = pendingChecks.stream()
            .map(pc -> pc.readValueId)
            .toList();

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
        List<PendingPermissionCheck> pendingChecks
    ) {

        // TODO NamespaceMetadataType might define
        //  DefaultRolePermissions and DefaultUserRolePermissions

        List<ReadValueId> nodesToRead = pendingChecks.stream()
            .map(pc -> pc.readValueId)
            .toList();

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

        List<PendingPermissionCheck> remainingChecks = pendingChecks.stream()
            .filter(pc -> pc.result == null)
            .toList();

        if (!remainingChecks.isEmpty()) {
            checkAccessLevels(server, session, remainingChecks);
        }
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

}
