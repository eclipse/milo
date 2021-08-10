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

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class SubscribedDataSetMirrorDataType extends SubscribedDataSetDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15635");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15713");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16012");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16311");

    private final String parentNodeName;

    private final RolePermissionType[] rolePermissions;

    public SubscribedDataSetMirrorDataType(String parentNodeName,
                                           RolePermissionType[] rolePermissions) {
        this.parentNodeName = parentNodeName;
        this.rolePermissions = rolePermissions;
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

    public String getParentNodeName() {
        return parentNodeName;
    }

    public RolePermissionType[] getRolePermissions() {
        return rolePermissions;
    }

    public static final class Codec extends GenericDataTypeCodec<SubscribedDataSetMirrorDataType> {
        @Override
        public Class<SubscribedDataSetMirrorDataType> getType() {
            return SubscribedDataSetMirrorDataType.class;
        }

        @Override
        public SubscribedDataSetMirrorDataType decode(SerializationContext context, UaDecoder decoder) {
            String parentNodeName = decoder.readString("ParentNodeName");
            RolePermissionType[] rolePermissions = (RolePermissionType[]) decoder.readStructArray("RolePermissions", RolePermissionType.TYPE_ID);
            return new SubscribedDataSetMirrorDataType(parentNodeName, rolePermissions);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           SubscribedDataSetMirrorDataType value) {
            encoder.writeString("ParentNodeName", value.getParentNodeName());
            encoder.writeStructArray("RolePermissions", value.getRolePermissions(), RolePermissionType.TYPE_ID);
        }
    }
}
