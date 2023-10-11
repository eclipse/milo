/*
 * Copyright (c) 2023 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.NumericRange;
import org.eclipse.milo.opcua.sdk.core.nodes.Node;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaServerNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.encoding.binary.OpcUaDefaultBinaryEncoding;
import org.eclipse.milo.opcua.stack.core.types.DataTypeEncoding;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessLevelExType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;
import org.eclipse.milo.opcua.stack.core.util.ArrayUtil;
import org.jetbrains.annotations.Nullable;

import static org.eclipse.milo.opcua.stack.core.util.ArrayUtil.transformArray;

public class AttributeReader {

    public static DataValue readAttribute(
        AccessContext context,
        UaServerNode node,
        UInteger attributeId,
        @Nullable TimestampsToReturn timestamps,
        @Nullable String indexRange,
        @Nullable QualifiedName encodingName
    ) {

        return AttributeId.from(attributeId)
            .map(id -> readAttribute(context, node, id, timestamps, indexRange, encodingName))
            .orElseGet(() -> new DataValue(StatusCodes.Bad_AttributeIdInvalid));
    }

    public static DataValue readAttribute(
        AccessContext context,
        UaServerNode node,
        AttributeId attributeId,
        @Nullable TimestampsToReturn timestamps,
        @Nullable String indexRange,
        @Nullable QualifiedName encodingName
    ) {

        if (!AttributeId.getAttributes(node.getNodeClass()).contains(attributeId)) {
            return new DataValue(StatusCodes.Bad_AttributeIdInvalid);
        }

        if (attributeId == AttributeId.Value && node instanceof VariableNode) {
            VariableNode variableNode = (VariableNode) node;

            AccessLevelExType accessLevelEx = variableNode.getAccessLevelEx();

            if (accessLevelEx != null) {
                if (!accessLevelEx.getCurrentRead()) {
                    return new DataValue(StatusCodes.Bad_NotReadable);
                }
            } else {
                Set<AccessLevel> accessLevels = AccessLevel.fromValue(variableNode.getAccessLevel());
                if (!accessLevels.contains(AccessLevel.CurrentRead)) {
                    return new DataValue(StatusCodes.Bad_NotReadable);
                }
            }

            Set<AccessLevel> userAccessLevels = AccessLevel.fromValue(
                (UByte) node.getAttribute(context, AttributeId.UserAccessLevel)
            );
            if (!userAccessLevels.contains(AccessLevel.CurrentRead)) {
                return new DataValue(StatusCodes.Bad_UserAccessDenied);
            }
        }

        List<NodeId> roleIds = context.getSession().flatMap(Session::getRoleIds).orElse(null);

        if (roleIds != null) {
            // If non-null, there is a Session and Server has been configured with a
            // RoleManager that provides Identity to RoleId mappings, so we can proceed with
            // checking the RolePermissions and UserRolePermissions attributes.

            RolePermissionType[] rolePermissions = node.getRolePermissions();

            if (rolePermissions != null) {
                boolean hasReadPermission = Stream.of(rolePermissions)
                    .anyMatch(rp -> rp.getPermissions().getRead());

                if (!hasReadPermission) {
                    return new DataValue(StatusCodes.Bad_UserAccessDenied);
                }
            }

            RolePermissionType[] userRolePermissions = (RolePermissionType[]) node.getAttribute(
                context,
                AttributeId.UserRolePermissions
            );

            if (userRolePermissions != null) {
                boolean hasReadPermission = Arrays.stream(userRolePermissions)
                    .filter(rp -> roleIds.contains(rp.getRoleId()))
                    .anyMatch(rp -> rp.getPermissions().getRead());

                if (!hasReadPermission) {
                    return new DataValue(StatusCodes.Bad_UserAccessDenied);
                }
            }
        }

        if (encodingName != null && encodingName.isNotNull()) {
            if (attributeId != AttributeId.Value) {
                return new DataValue(StatusCodes.Bad_DataEncodingInvalid);
            }

            NodeId dataTypeId;
            if (node instanceof VariableNode) {
                dataTypeId = ((VariableNode) node).getDataType();
            } else if (node instanceof VariableTypeNode) {
                dataTypeId = ((VariableTypeNode) node).getDataType();
            } else {
                return new DataValue(StatusCodes.Bad_DataEncodingInvalid);
            }

            if (!isStructureSubtype(node.getNodeContext().getServer(), dataTypeId)) {
                return new DataValue(StatusCodes.Bad_DataEncodingInvalid);
            }
        }

        Object value;
        try {
            value = node.readAttribute(context, attributeId);
        } catch (UaException e) {
            return new DataValue(e.getStatusCode());
        }

        if (attributeId == AttributeId.Value) {
            DataValue dv = (DataValue) value;
            DataValue.Builder dvb = dv.copy();

            if (encodingName != null && encodingName.isNotNull() && dv.getValue().isNotNull()) {
                final Object valueObject = dvb.value.getValue();

                Class<?> valueClazz = valueObject.getClass();

                if (valueClazz.isArray() && ArrayUtil.getType(valueObject) == ExtensionObject.class) {
                    Object newValue = transformArray(
                        valueObject,
                        (ExtensionObject xo) ->
                            transcode(node, xo, encodingName),
                        ExtensionObject.class
                    );

                    dvb.setValue(new Variant(newValue));
                } else if (valueClazz == ExtensionObject.class) {
                    ExtensionObject xo = (ExtensionObject) valueObject;

                    Object newValue = transcode(node, xo, encodingName);

                    dvb.setValue(new Variant(newValue));
                }
            }

            if (indexRange != null) {
                try {
                    NumericRange range = NumericRange.parse(indexRange);

                    Object valueAtRange = NumericRange.readFromValueAtRange(dv.getValue().getValue(), range);

                    return dvb.setValue(Variant.of(valueAtRange))
                        .applyTimestamps(attributeId, timestamps)
                        .build();
                } catch (UaException e) {
                    return new DataValue(e.getStatusCode());
                }
            } else {
                return dvb.applyTimestamps(attributeId, timestamps).build();
            }
        } else {
            // These attributes are either structures or primitive types, and should
            // not expose a null value to clients, so if they are null in the Node
            // that means they are not implemented/supported for the node, and we need
            // to return Bad_AttributeIdInvalid.

            switch (attributeId) {
                case DataTypeDefinition:
                case RolePermissions:
                case UserRolePermissions:
                case AccessRestrictions:
                case AccessLevelEx:
                    if (value == null) {
                        return new DataValue(StatusCodes.Bad_AttributeIdInvalid);
                    }
            }

            if (indexRange != null) {
                try {
                    NumericRange range = NumericRange.parse(indexRange);

                    value = NumericRange.readFromValueAtRange(value, range);
                } catch (UaException e) {
                    return new DataValue(e.getStatusCode());
                }
            }

            return DataValue.newValue()
                .setValue(Variant.of(value))
                .setStatus(StatusCode.GOOD)
                .applyTimestamps(attributeId, timestamps)
                .build();
        }
    }

    private static boolean isStructureSubtype(OpcUaServer server, NodeId dataTypeId) {
        UaNode dataTypeNode = server.getAddressSpaceManager()
            .getManagedNode(dataTypeId)
            .orElse(null);

        if (dataTypeNode != null) {
            Optional<NodeId> superTypeId = dataTypeNode.getReferences().stream()
                .filter(r -> r.isInverse() && r.getReferenceTypeId().equals(NodeIds.HasSubtype))
                .flatMap(r -> r.getTargetNodeId().toNodeId(server.getNamespaceTable()).stream())
                .findFirst();

            return superTypeId
                .map(id -> id.equals(NodeIds.Structure) || isStructureSubtype(server, id))
                .orElse(false);
        } else {
            return false;
        }
    }

    private static ExtensionObject transcode(
        UaServerNode node,
        ExtensionObject xo,
        QualifiedName encodingName
    ) {

        if (xo == null || xo.isNull()) {
            return xo;
        }

        if (encodingName == null || encodingName.isNull()) {
            // TODO default encoding should be derived from session/transport
            encodingName = OpcUaDefaultBinaryEncoding.ENCODING_NAME;
        }

        DataTypeEncoding newEncoding;
        if (OpcUaDefaultBinaryEncoding.ENCODING_NAME.equals(encodingName)) {
            newEncoding = OpcUaDefaultBinaryEncoding.getInstance();
        } else {
            newEncoding = node.getNodeContext().getServer().getEncodingManager().getEncoding(encodingName);
        }

        NodeId newEncodingId = getEncodingId(node, encodingName);

        if (newEncodingId != null) {
            return xo.transcode(
                node.getNodeContext().getServer().getEncodingContext(),
                newEncodingId,
                newEncoding
            );
        } else {
            return xo;
        }
    }

    private static @Nullable NodeId getEncodingId(UaServerNode node, QualifiedName encodingName) {
        // TODO avoid dynamic lookup by registering codecs with their associated DataType and Encoding name
        NodeId dataTypeId;
        if (node instanceof VariableNode) {
            dataTypeId = ((VariableNode) node).getDataType();
        } else if (node instanceof VariableTypeNode) {
            dataTypeId = ((VariableTypeNode) node).getDataType();
        } else {
            return null;
        }

        AddressSpaceManager addressSpaceManager = node.getNodeContext().getServer().getAddressSpaceManager();

        UaNode dataTypeNode = addressSpaceManager.getManagedNode(dataTypeId).orElse(null);

        if (dataTypeNode != null) {
            return dataTypeNode.getReferences().stream()
                .filter(r -> r.isForward() && NodeIds.HasEncoding.equals(r.getReferenceTypeId()))
                .flatMap(r -> addressSpaceManager.getManagedNode(r.getTargetNodeId()).stream())
                .filter(n -> encodingName.equals(n.getBrowseName()))
                .map(Node::getNodeId)
                .findFirst()
                .orElse(null);
        } else {
            return null;
        }
    }

}
