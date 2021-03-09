/*
 * Copyright (c) 2019 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class EnumDescription extends DataTypeDescription implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15488");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=127");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15590");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15059");

    private final EnumDefinition enumDefinition;

    private final UByte builtInType;

    public EnumDescription(NodeId dataTypeId, QualifiedName name, EnumDefinition enumDefinition,
                           UByte builtInType) {
        super(dataTypeId, name);
        this.enumDefinition = enumDefinition;
        this.builtInType = builtInType;
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

    public EnumDefinition getEnumDefinition() {
        return enumDefinition;
    }

    public UByte getBuiltInType() {
        return builtInType;
    }

    public static final class Codec extends GenericDataTypeCodec<EnumDescription> {
        @Override
        public Class<EnumDescription> getType() {
            return EnumDescription.class;
        }

        @Override
        public EnumDescription decode(SerializationContext context, UaDecoder decoder) {
            NodeId dataTypeId = decoder.readNodeId("DataTypeId");
            QualifiedName name = decoder.readQualifiedName("Name");
            EnumDefinition enumDefinition = (EnumDefinition) decoder.readStruct("EnumDefinition", EnumDefinition.TYPE_ID);
            UByte builtInType = decoder.readByte("BuiltInType");
            return new EnumDescription(dataTypeId, name, enumDefinition, builtInType);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, EnumDescription value) {
            encoder.writeNodeId("DataTypeId", value.getDataTypeId());
            encoder.writeQualifiedName("Name", value.getName());
            encoder.writeStruct("EnumDefinition", value.getEnumDefinition(), EnumDefinition.TYPE_ID);
            encoder.writeByte("BuiltInType", value.getBuiltInType());
        }
    }
}
