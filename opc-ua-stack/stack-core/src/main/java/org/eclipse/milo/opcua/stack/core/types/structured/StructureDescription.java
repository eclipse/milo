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

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class StructureDescription extends DataTypeDescription implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15487");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=126");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15589");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15058");

    private final StructureDefinition structureDefinition;

    public StructureDescription(NodeId dataTypeId, QualifiedName name,
                                StructureDefinition structureDefinition) {
        super(dataTypeId, name);
        this.structureDefinition = structureDefinition;
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

    public StructureDefinition getStructureDefinition() {
        return structureDefinition;
    }

    public static final class Codec extends GenericDataTypeCodec<StructureDescription> {
        @Override
        public Class<StructureDescription> getType() {
            return StructureDescription.class;
        }

        @Override
        public StructureDescription decode(SerializationContext context, UaDecoder decoder) {
            NodeId dataTypeId = decoder.readNodeId("DataTypeId");
            QualifiedName name = decoder.readQualifiedName("Name");
            StructureDefinition structureDefinition = (StructureDefinition) decoder.readStruct("StructureDefinition", StructureDefinition.TYPE_ID);
            return new StructureDescription(dataTypeId, name, structureDefinition);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           StructureDescription value) {
            encoder.writeNodeId("DataTypeId", value.getDataTypeId());
            encoder.writeQualifiedName("Name", value.getName());
            encoder.writeStruct("StructureDefinition", value.getStructureDefinition(), StructureDefinition.TYPE_ID);
        }
    }
}
