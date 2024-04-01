package org.eclipse.milo.opcua.sdk.server.servicesets.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.servicesets.impl.AccessController.AccessControlContext.AccessControlAttributes;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

import static org.eclipse.milo.opcua.sdk.core.util.GroupMapCollate.groupMapCollate;

public class ReadAccessController extends AccessController {

    public ReadAccessController(OpcUaServer server) {
        super(server);
    }

    /**
     * Check if the current Session has read access to the Nodes and Attributes identified by
     * {@code readValueIds}.
     *
     * @param session the Session to check access for.
     * @param readValueIds the Nodes and Attributes to check access for.
     * @return a List of {@link AccessCheckResult} indicating the access status for each
     *     {@link ReadValueId}.
     */
    public List<AccessCheckResult> checkReadAccess(Session session, List<ReadValueId> readValueIds) {
        var context = new DefaultAccessControlContext(server, session);

        return checkReadAccess(context, readValueIds);
    }

    List<AccessCheckResult> checkReadAccess(AccessControlContext context, List<ReadValueId> readValueIds) {
        List<PendingReadCheck> allPendingChecks = readValueIds.stream()
            .map(PendingReadCheck::new)
            .toList();

        List<NodeId> nodeIds = readValueIds.stream()
            .map(ReadValueId::getNodeId).toList();

        Map<NodeId, AccessControlAttributes> accessControlAttributes =
            context.readAccessControlAttributes(nodeIds);

        List<AccessCheckResult> accessRestrictionResults =
            checkAccessRestrictions(context, nodeIds, accessControlAttributes);

        for (int i = 0; i < accessRestrictionResults.size(); i++) {
            allPendingChecks.get(i).result = accessRestrictionResults.get(i);
        }

        // Filter out any nodes that have been denied by AccessRestrictions and check the rest
        // against role permissions and/or access levels.
        List<PendingReadCheck> remainingChecks = allPendingChecks.stream()
            .filter(pc -> pc.result != AccessCheckResult.DENIED)
            .toList();

        List<ReadValueId> remainingNodes = remainingChecks.stream()
            .map(pc -> pc.readValueId)
            .toList();

        List<AccessCheckResult> remainingResults = groupMapCollate(
            remainingNodes,
            ReadValueId::getAttributeId,
            id -> group -> {
                if (AttributeId.Value.uid().equals(id)) {
                    return checkValueAccess(context, group, accessControlAttributes);
                } else {
                    return Collections.nCopies(group.size(), AccessCheckResult.ALLOWED);
                }
            }
        );

        for (int i = 0; i < remainingNodes.size(); i++) {
            remainingChecks.get(i).result = remainingResults.get(i);
        }

        // The RolePermissions attribute is a special case, only SecurityAdmins can read it.
        context.getRoleIds().ifPresent(roleIds -> {
            for (PendingReadCheck p : allPendingChecks) {
                if (Objects.equals(AttributeId.RolePermissions.uid(), p.readValueId.getAttributeId())) {
                    if (roleIds.stream().noneMatch(id -> id.equals(NodeIds.WellKnownRole_SecurityAdmin))) {
                        p.result = AccessCheckResult.DENIED;
                    }
                }
            }
        });

        return allPendingChecks.stream().map(pc -> pc.result).toList();
    }

    private static List<AccessCheckResult> checkValueAccess(
        AccessControlContext context,
        List<ReadValueId> readValueIds,
        Map<NodeId, AccessControlAttributes> accessControlAttributes
    ) {

        var results = new ArrayList<AccessCheckResult>();

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
                    results.add(AccessCheckResult.ALLOWED);
                } else {
                    results.add(AccessCheckResult.DENIED);
                }
            } else if (userAccessLevel != null) {
                Set<AccessLevel> accessLevels =
                    AccessLevel.fromValue(userAccessLevel.byteValue());

                if (accessLevels.contains(AccessLevel.CurrentRead)) {
                    results.add(AccessCheckResult.ALLOWED);
                } else {
                    results.add(AccessCheckResult.DENIED);
                }
            } else {
                results.add(AccessCheckResult.ALLOWED);
            }
        }

        return results;
    }

    private static class PendingReadCheck {
        AccessCheckResult result = null;
        final ReadValueId readValueId;

        protected PendingReadCheck(ReadValueId readValueId) {
            this.readValueId = readValueId;
        }
    }

}
