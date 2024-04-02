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
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.servicesets.impl.AbstractAccessController.AccessControlContext;
import org.eclipse.milo.opcua.sdk.server.servicesets.impl.AbstractAccessController.AccessControlContext.AccessControlAttributes;
import org.eclipse.milo.opcua.sdk.server.servicesets.impl.AbstractAccessController.DefaultAccessControlContext;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;

public class DefaultAccessController implements AccessController {

    private final OpcUaServer server;

    public DefaultAccessController(OpcUaServer server) {
        this.server = server;
    }

    //region Read

    @Override
    public List<AccessResult> checkReadAccess(Session session, List<ReadValueId> readValueIds) {
        var context = new DefaultAccessControlContext(server, session);

        return checkReadAccess(context, readValueIds);
    }

    static List<AccessResult> checkReadAccess(AccessControlContext context, List<ReadValueId> readValueIds) {
        List<PendingResult<ReadValueId>> pending =
            readValueIds.stream().map(PendingResult::new).toList();

        List<NodeId> nodeIds =
            readValueIds.stream().map(ReadValueId::getNodeId).toList();

        Map<NodeId, AccessControlAttributes> attributes =
            context.readAccessControlAttributes(nodeIds);

        checkAccessRestrictions(context, pending, attributes, ReadValueId::getNodeId);

        for (PendingResult<ReadValueId> p : pending) {
            if (p.result == AccessResult.DENIED) {
                continue;
            }

            NodeId nodeId = p.value.getNodeId();
            UInteger attributeId = p.value.getAttributeId();

            if (AttributeId.Value.uid().equals(attributeId)) {
                UByte userAccessLevel = attributes.get(nodeId).userAccessLevel();

                if (userAccessLevel != null) {
                    Set<AccessLevel> accessLevels = AccessLevel.fromValue(userAccessLevel);
                    if (!accessLevels.contains(AccessLevel.CurrentRead)) {
                        p.result = AccessResult.DENIED;
                    }
                }
            } else if (AttributeId.RolePermissions.uid().equals(attributeId)) {
                List<NodeId> roleIds = context.getRoleIds().orElse(null);

                RolePermissionType[] userRolePermissions =
                    attributes.get(nodeId).userRolePermissions();

                if (roleIds != null && userRolePermissions != null) {
                    boolean hasAccess = Stream.of(userRolePermissions)
                        .filter(rp -> roleIds.contains(rp.getRoleId()))
                        .anyMatch(rp -> rp.getPermissions().getReadRolePermissions());

                    if (!hasAccess) {
                        p.result = AccessResult.DENIED;
                    }
                }
            }
        }

        return pending.stream().map(p -> p.result).toList();
    }

    //endregion

    //region Write

    @Override
    public List<AccessResult> checkWriteAccess(Session session, List<WriteValue> writeValues) {
        var context = new DefaultAccessControlContext(server, session);

        return checkWriteAccess(context, writeValues);
    }

    static List<AccessResult> checkWriteAccess(AccessControlContext context, List<WriteValue> writeValues) {
        List<PendingResult<WriteValue>> pending =
            writeValues.stream().map(PendingResult::new).toList();

        List<NodeId> nodeIds =
            writeValues.stream().map(WriteValue::getNodeId).toList();

        Map<NodeId, AccessControlAttributes> attributes =
            context.readAccessControlAttributes(nodeIds);

        checkAccessRestrictions(context, pending, attributes, WriteValue::getNodeId);

        for (PendingResult<WriteValue> p : pending) {
            if (p.result == AccessResult.DENIED) {
                continue;
            }

            NodeId nodeId = p.value.getNodeId();
            UInteger attributeId = p.value.getAttributeId();

            if (AttributeId.Value.uid().equals(attributeId)) {
                UByte userAccessLevel = attributes.get(nodeId).userAccessLevel();

                if (userAccessLevel != null) {
                    Set<AccessLevel> accessLevels = AccessLevel.fromValue(userAccessLevel);
                    if (!accessLevels.contains(AccessLevel.CurrentWrite)) {
                        p.result = AccessResult.DENIED;
                    }
                }
            } else if (AttributeId.RolePermissions.uid().equals(attributeId)) {
                List<NodeId> roleIds = context.getRoleIds().orElse(null);

                RolePermissionType[] userRolePermissions =
                    attributes.get(nodeId).userRolePermissions();

                if (roleIds != null && userRolePermissions != null) {
                    boolean hasAccess = Stream.of(userRolePermissions)
                        .filter(rp -> roleIds.contains(rp.getRoleId()))
                        .anyMatch(rp -> rp.getPermissions().getWriteRolePermissions());

                    if (!hasAccess) {
                        p.result = AccessResult.DENIED;
                    }
                }
            } else if (AttributeId.Historizing.uid().equals(attributeId)) {
                List<NodeId> roleIds = context.getRoleIds().orElse(null);

                RolePermissionType[] userRolePermissions =
                    attributes.get(nodeId).userRolePermissions();

                if (roleIds != null && userRolePermissions != null) {
                    boolean hasAccess = Stream.of(userRolePermissions)
                        .filter(rp -> roleIds.contains(rp.getRoleId()))
                        .anyMatch(rp -> rp.getPermissions().getWriteHistorizing());

                    if (!hasAccess) {
                        p.result = AccessResult.DENIED;
                    }
                }
            }
        }

        return pending.stream().map(p -> p.result).toList();
    }

    //endregion

    //region Browse

    @Override
    public List<AccessResult> checkBrowseAccess(Session session, List<NodeId> nodeIds) {
        var context = new DefaultAccessControlContext(server, session);

        return checkBrowseAccess(context, nodeIds);
    }

    static List<AccessResult> checkBrowseAccess(AccessControlContext context, List<NodeId> nodeIds) {
        List<PendingResult<NodeId>> pending =
            nodeIds.stream().map(PendingResult::new).toList();

        Map<NodeId, AccessControlAttributes> attributes =
            context.readAccessControlAttributes(nodeIds);

        checkAccessRestrictions(context, pending, attributes, Function.identity());

        for (PendingResult<NodeId> p : pending) {
            if (p.result == AccessResult.DENIED) {
                continue;
            }

            NodeId nodeId = p.value;
            List<NodeId> roleIds = context.getRoleIds().orElse(null);
            RolePermissionType[] userRolePermissions = attributes.get(nodeId).userRolePermissions();

            if (roleIds != null && userRolePermissions != null) {
                boolean hasAccess = Stream.of(userRolePermissions)
                    .filter(rp -> roleIds.contains(rp.getRoleId()))
                    .anyMatch(rp -> rp.getPermissions().getBrowse());

                if (!hasAccess) {
                    p.result = AccessResult.DENIED;
                }
            }
        }

        return pending.stream().map(p -> p.result).toList();
    }

    //endregion

    //region Call

    @Override
    public List<AccessResult> checkCallAccess(Session session, List<CallMethodRequest> requests) {
        var context = new DefaultAccessControlContext(server, session);

        return checkCallAccess(context, requests);
    }

    static List<AccessResult> checkCallAccess(AccessControlContext context, List<CallMethodRequest> requests) {
        List<PendingResult<NodeId>> pending = requests.stream()
            .flatMap(r -> Stream.of(
                new PendingResult<>(r.getObjectId()),
                new PendingResult<>(r.getMethodId())
            ))
            .toList();

        var nodeIds = new ArrayList<NodeId>();

        for (CallMethodRequest request : requests) {
            nodeIds.add(request.getObjectId());
            nodeIds.add(request.getMethodId());
        }

        Map<NodeId, AccessControlAttributes> attributes =
            context.readAccessControlAttributes(nodeIds);

        checkAccessRestrictions(context, pending, attributes, Function.identity());

        for (int i = 0; i < pending.size(); i += 2) {
            PendingResult<NodeId> p0 = pending.get(i);
            PendingResult<NodeId> p1 = pending.get(i + 1);

            if (p0.result == AccessResult.DENIED || p1.result == AccessResult.DENIED) {
                continue;
            }

            AccessControlAttributes objectAttributes = attributes.get(p0.value);
            AccessControlAttributes methodAttributes = attributes.get(p1.value);

            RolePermissionType[] objectPermissions = objectAttributes.userRolePermissions();
            RolePermissionType[] methodPermissions = methodAttributes.userRolePermissions();

            List<NodeId> roleIds = context.getRoleIds().orElse(null);

            if (roleIds != null && objectPermissions != null && methodPermissions != null) {
                boolean objectPermission = Stream.of(objectPermissions)
                    .filter(rp -> roleIds.contains(rp.getRoleId()))
                    .anyMatch(rp -> rp.getPermissions().getCall());

                boolean methodPermission = Stream.of(methodPermissions)
                    .filter(rp -> roleIds.contains(rp.getRoleId()))
                    .anyMatch(rp -> rp.getPermissions().getCall());

                if (!objectPermission || !methodPermission) {
                    p0.result = AccessResult.DENIED;
                    p1.result = AccessResult.DENIED;
                }
            }
        }

        var results = new ArrayList<AccessResult>();

        for (int i = 0; i < pending.size(); i += 2) {
            PendingResult<NodeId> p0 = pending.get(i);
            PendingResult<NodeId> p1 = pending.get(i + 1);

            boolean allowed = p0.result == AccessResult.ALLOWED
                && p1.result == AccessResult.ALLOWED;

            results.add(allowed ? AccessResult.ALLOWED : AccessResult.DENIED);
        }

        return results;
    }

    //endregion

    @Override
    public List<AccessResult> checkAddReferencesAccess(Session session, List<NodeId> nodeIds) {
        return null;
    }

    @Override
    public List<AccessResult> checkDeleteNodesAccess(Session session, List<NodeId> nodeIds) {
        return null;
    }

    @Override
    public List<AccessResult> checkDeleteReferencesAccess(Session session, List<NodeId> nodeIds) {
        return null;
    }

    private static <T> void checkAccessRestrictions(
        AccessControlContext context,
        List<PendingResult<T>> pending,
        Map<NodeId, AccessControlAttributes> attributes,
        Function<T, NodeId> getNodeId
    ) {

        MessageSecurityMode securityMode = context.getSecurityMode();

        List<NodeId> nodeIds = pending.stream()
            .map(p -> getNodeId.apply(p.value)).toList();

        for (int i = 0; i < nodeIds.size(); i++) {
            NodeId nodeId = nodeIds.get(i);
            AccessRestrictionType accessRestrictions =
                attributes.get(nodeId).accessRestrictions();

            if (accessRestrictions != null) {
                if (accessRestrictions.getEncryptionRequired()) {
                    if (securityMode != MessageSecurityMode.SignAndEncrypt) {
                        pending.get(i).result = AccessResult.DENIED;
                    }
                } else if (accessRestrictions.getSigningRequired()) {
                    if (securityMode != MessageSecurityMode.Sign
                        && securityMode != MessageSecurityMode.SignAndEncrypt) {

                        pending.get(i).result = AccessResult.DENIED;
                    }
                }
            }
        }
    }

    private static class PendingResult<T> {
        private AccessResult result = AccessResult.ALLOWED;
        private final T value;

        private PendingResult(T value) {
            this.value = value;
        }
    }

}
