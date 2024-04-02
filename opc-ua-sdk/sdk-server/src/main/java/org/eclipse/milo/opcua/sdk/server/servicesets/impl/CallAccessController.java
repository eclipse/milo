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
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.servicesets.impl.AbstractAccessController.AccessControlContext.AccessControlAttributes;
import org.eclipse.milo.opcua.sdk.server.servicesets.impl.AccessController.AccessResult;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class CallAccessController extends AbstractAccessController {

    public CallAccessController(OpcUaServer server) {
        super(server);
    }

    public List<AccessResult> checkCallAccess(Session session, List<CallMethodRequest> requests) {
        var context = new DefaultAccessControlContext(server, session);

        return checkCallAccess(context, requests);
    }

    List<AccessResult> checkCallAccess(AccessControlContext context, List<CallMethodRequest> requests) {
        List<PendingCallCheck> allPendingChecks = requests.stream()
            .map(PendingCallCheck::new)
            .toList();

        var nodeIds = new ArrayList<NodeId>();

        for (CallMethodRequest request : requests) {
            nodeIds.add(request.getObjectId());
            nodeIds.add(request.getMethodId());
        }

        Map<NodeId, AccessControlAttributes> accessControlAttributes =
            context.readAccessControlAttributes(nodeIds);

        List<AccessResult> accessRestrictionResults =
            checkAccessRestrictions(context, nodeIds, accessControlAttributes);

        for (int i = 0, j = 0; i < allPendingChecks.size(); i++, j += 2) {
            PendingCallCheck pending = allPendingChecks.get(i);
            AccessResult r0 = accessRestrictionResults.get(j);
            AccessResult r1 = accessRestrictionResults.get(j + 1);

            if (r0 == AccessResult.ALLOWED && r1 == AccessResult.ALLOWED) {
                pending.result = AccessResult.ALLOWED;
            } else {
                pending.result = AccessResult.DENIED;
            }
        }

        List<PendingCallCheck> remainingChecks = allPendingChecks.stream()
            .filter(p -> p.result != AccessResult.DENIED)
            .toList();

        for (PendingCallCheck pendingCheck : remainingChecks) {
            CallMethodRequest request = pendingCheck.request;

            AccessControlAttributes objectAttributes =
                accessControlAttributes.get(request.getObjectId());
            AccessControlAttributes methodAttributes =
                accessControlAttributes.get(request.getMethodId());

            RolePermissionType[] objectUserRolePermissions = objectAttributes.userRolePermissions();
            RolePermissionType[] methodUserRolePermissions = methodAttributes.userRolePermissions();

            List<NodeId> roleIds = context.getRoleIds().orElse(null);

            if (roleIds != null && objectUserRolePermissions != null && methodUserRolePermissions != null) {
                boolean objectPermission = Stream.of(objectUserRolePermissions)
                    .filter(rp -> roleIds.contains(rp.getRoleId()))
                    .anyMatch(rp -> rp.getPermissions().getCall());
                boolean methodPermission = Stream.of(methodUserRolePermissions)
                    .filter(rp -> roleIds.contains(rp.getRoleId()))
                    .anyMatch(rp -> rp.getPermissions().getCall());

                if (objectPermission && methodPermission) {
                    pendingCheck.result = AccessResult.ALLOWED;
                } else {
                    pendingCheck.result = AccessResult.DENIED;
                }
            } else {
                pendingCheck.result = AccessResult.ALLOWED;
            }
        }

        return allPendingChecks.stream().map(p -> p.result).toList();
    }

    private static class PendingCallCheck {
        final CallMethodRequest request;
        AccessResult result;

        PendingCallCheck(CallMethodRequest request) {
            this.request = request;
        }
    }

}
