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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class SimpleAttributeOperand extends FilterOperand implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=601");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=602");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=603");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15210");

    private final NodeId typeDefinitionId;

    private final QualifiedName[] browsePath;

    private final UInteger attributeId;

    private final String indexRange;

    public SimpleAttributeOperand(NodeId typeDefinitionId, QualifiedName[] browsePath,
                                  UInteger attributeId, String indexRange) {
        this.typeDefinitionId = typeDefinitionId;
        this.browsePath = browsePath;
        this.attributeId = attributeId;
        this.indexRange = indexRange;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    public NodeId getTypeDefinitionId() {
        return typeDefinitionId;
    }

    public QualifiedName[] getBrowsePath() {
        return browsePath;
    }

    public UInteger getAttributeId() {
        return attributeId;
    }

    public String getIndexRange() {
        return indexRange;
    }

    public static final class Codec extends GenericDataTypeCodec<SimpleAttributeOperand> {
        @Override
        public Class<SimpleAttributeOperand> getType() {
            return SimpleAttributeOperand.class;
        }

        @Override
        public SimpleAttributeOperand decode(SerializationContext context, UaDecoder decoder) {
            NodeId typeDefinitionId = decoder.readNodeId("TypeDefinitionId");
            QualifiedName[] browsePath = decoder.readQualifiedNameArray("BrowsePath");
            UInteger attributeId = decoder.readUInt32("AttributeId");
            String indexRange = decoder.readString("IndexRange");
            return new SimpleAttributeOperand(typeDefinitionId, browsePath, attributeId, indexRange);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           SimpleAttributeOperand value) {
            encoder.writeNodeId("TypeDefinitionId", value.getTypeDefinitionId());
            encoder.writeQualifiedNameArray("BrowsePath", value.getBrowsePath());
            encoder.writeUInt32("AttributeId", value.getAttributeId());
            encoder.writeString("IndexRange", value.getIndexRange());
        }
    }
}
