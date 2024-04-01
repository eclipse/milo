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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.AddressSpace.ReadContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.servicesets.impl.AccessController.AccessControlContext.AccessControlAttributes;
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

public abstract class AccessController {

    protected final OpcUaServer server;

    public AccessController(OpcUaServer server) {
        this.server = server;
    }

    protected static List<AccessCheckResult> checkAccessRestrictions(
        AccessControlContext context,
        List<NodeId> nodesToCheck,
        Map<NodeId, AccessControlAttributes> accessControlAttributes
    ) {

        var results = new ArrayList<AccessCheckResult>();

        MessageSecurityMode securityMode = context.getSecurityMode();

        for (NodeId nodeId : nodesToCheck) {
            AccessControlAttributes attributes = accessControlAttributes.get(nodeId);
            AccessRestrictionType accessRestrictions = attributes.accessRestrictions();

            if (accessRestrictions != null) {
                if (accessRestrictions.getEncryptionRequired()) {
                    AccessCheckResult result =
                        securityMode == MessageSecurityMode.SignAndEncrypt ?
                            AccessCheckResult.ALLOWED : AccessCheckResult.DENIED;

                    results.add(result);
                } else if (accessRestrictions.getSigningRequired()) {
                    AccessCheckResult result =
                        (securityMode == MessageSecurityMode.Sign
                            || securityMode == MessageSecurityMode.SignAndEncrypt) ?
                            AccessCheckResult.ALLOWED : AccessCheckResult.DENIED;

                    results.add(result);
                } else {
                    results.add(AccessCheckResult.ALLOWED);
                }
            } else {
                // TODO check if there are Namespace-level restrictions
                results.add(AccessCheckResult.ALLOWED);
            }
        }

        return results;
    }

    public enum AccessCheckResult {
        ALLOWED,
        DENIED
    }

    protected interface AccessControlContext {

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
