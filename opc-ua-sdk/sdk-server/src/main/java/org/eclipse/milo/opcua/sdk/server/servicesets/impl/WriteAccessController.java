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
import org.eclipse.milo.opcua.sdk.server.servicesets.impl.AccessController.AccessControlContext.AccessControlAttributes;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;

import static org.eclipse.milo.opcua.sdk.core.util.GroupMapCollate.groupMapCollate;

public class WriteAccessController extends AccessController {

    public WriteAccessController(OpcUaServer server) {
        super(server);
    }

    /**
     * Check if the current Session has write access to the Nodes and Attributes identified by
     * {@code writeValues}.
     *
     * @param session the Session to check access for.
     * @param writeValues the Nodes and Attributes to check access for.
     * @return a List of {@link AccessCheckResult} indicating the access status for each
     *     {@link WriteValue}.
     */
    public List<AccessCheckResult> checkWriteAccess(Session session, List<WriteValue> writeValues) {
        var context = new DefaultAccessControlContext(server, session);

        return checkWriteAccess(context, writeValues);
    }

    List<AccessCheckResult> checkWriteAccess(AccessControlContext context, List<WriteValue> writeValues) {
        List<PendingWriteCheck> allPendingChecks = writeValues.stream()
            .map(PendingWriteCheck::new)
            .toList();

        List<NodeId> nodeIds = writeValues.stream()
            .map(WriteValue::getNodeId).toList();

        Map<NodeId, AccessControlAttributes> accessControlAttributes =
            context.readAccessControlAttributes(nodeIds);

        List<AccessCheckResult> accessRestrictionResults =
            checkAccessRestrictions(context, nodeIds, accessControlAttributes);

        for (int i = 0; i < accessRestrictionResults.size(); i++) {
            allPendingChecks.get(i).result = accessRestrictionResults.get(i);
        }

        List<PendingWriteCheck> remainingChecks = allPendingChecks.stream()
            .filter(pwc -> pwc.result != AccessCheckResult.DENIED)
            .toList();

        List<WriteValue> remainingWriteValues = remainingChecks.stream()
            .map(pwc -> pwc.writeValue)
            .toList();

        List<AccessCheckResult> remainingResults = groupMapCollate(
            remainingWriteValues,
            WriteValue::getAttributeId,
            id -> group -> {
                if (AttributeId.Value.uid().equals(id)) {
                    return checkValueAccess(context, group, accessControlAttributes);
                } else {
                    return Collections.nCopies(group.size(), AccessCheckResult.ALLOWED);
                }
            }
        );

        for (int i = 0; i < remainingChecks.size(); i++) {
            remainingChecks.get(i).result = remainingResults.get(i);
        }

        return allPendingChecks.stream().map(pwc -> pwc.result).toList();
    }

    private static List<AccessCheckResult> checkValueAccess(
        AccessControlContext context,
        List<WriteValue> writeValues,
        Map<NodeId, AccessControlAttributes> accessControlAttributes
    ) {

        var results = new ArrayList<AccessCheckResult>();

        for (WriteValue writeValue : writeValues) {
            AccessControlAttributes attributes =
                accessControlAttributes.get(writeValue.getNodeId());

            UByte userAccessLevel = attributes.userAccessLevel();
            RolePermissionType[] userRolePermissions = attributes.userRolePermissions();

            List<NodeId> roleIds = context.getRoleIds().orElse(null);

            if (roleIds != null && userRolePermissions != null) {
                boolean hasWritePermission = Stream.of(userRolePermissions)
                    .filter(rp -> roleIds.contains(rp.getRoleId()))
                    .anyMatch(rp -> rp.getPermissions().getWrite());

                if (hasWritePermission) {
                    results.add(AccessCheckResult.ALLOWED);
                } else {
                    results.add(AccessCheckResult.DENIED);
                }
            } else if (userAccessLevel != null) {
                Set<AccessLevel> accessLevels =
                    AccessLevel.fromValue(userAccessLevel);

                if (accessLevels.contains(AccessLevel.CurrentWrite)) {
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

    private static class PendingWriteCheck {
        final WriteValue writeValue;
        AccessCheckResult result;

        PendingWriteCheck(WriteValue writeValue) {
            this.writeValue = writeValue;
        }
    }

}
