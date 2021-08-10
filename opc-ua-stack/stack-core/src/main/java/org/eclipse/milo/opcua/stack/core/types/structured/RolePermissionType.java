/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class RolePermissionType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=96");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=128");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16126");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15062");

    private final NodeId roleId;

    private final PermissionType permissions;

    public RolePermissionType(NodeId roleId, PermissionType permissions) {
        this.roleId = roleId;
        this.permissions = permissions;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public NodeId getRoleId() {
        return roleId;
    }

    public PermissionType getPermissions() {
        return permissions;
    }

    public static final class Codec extends GenericDataTypeCodec<RolePermissionType> {
        @Override
        public Class<RolePermissionType> getType() {
            return RolePermissionType.class;
        }

        @Override
        public RolePermissionType decode(SerializationContext context, UaDecoder decoder) {
            NodeId roleId = decoder.readNodeId("RoleId");
            PermissionType permissions = new PermissionType(decoder.readUInt32("Permissions"));
            return new RolePermissionType(roleId, permissions);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, RolePermissionType value) {
            encoder.writeNodeId("RoleId", value.getRoleId());
            encoder.writeUInt32("Permissions", value.getPermissions().getValue());
        }
    }
}
