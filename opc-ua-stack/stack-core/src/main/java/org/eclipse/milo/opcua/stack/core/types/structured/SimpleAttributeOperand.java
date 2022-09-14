/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.7.4/#7.7.4.5">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.7.4/#7.7.4.5</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class SimpleAttributeOperand extends FilterOperand implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=601");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=603");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=602");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15210");

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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 603),
            new NodeId(0, 589),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("TypeDefinitionId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("BrowsePath", LocalizedText.NULL_VALUE, new NodeId(0, 20), 1, null, UInteger.valueOf(0), false),
                new StructureField("AttributeId", LocalizedText.NULL_VALUE, new NodeId(0, 288), -1, null, UInteger.valueOf(0), false),
                new StructureField("IndexRange", LocalizedText.NULL_VALUE, new NodeId(0, 291), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<SimpleAttributeOperand> {
        @Override
        public Class<SimpleAttributeOperand> getType() {
            return SimpleAttributeOperand.class;
        }

        @Override
        public SimpleAttributeOperand decodeType(SerializationContext context, UaDecoder decoder) {
            NodeId typeDefinitionId = decoder.readNodeId("TypeDefinitionId");
            QualifiedName[] browsePath = decoder.readQualifiedNameArray("BrowsePath");
            UInteger attributeId = decoder.readUInt32("AttributeId");
            String indexRange = decoder.readString("IndexRange");
            return new SimpleAttributeOperand(typeDefinitionId, browsePath, attributeId, indexRange);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               SimpleAttributeOperand value) {
            encoder.writeNodeId("TypeDefinitionId", value.getTypeDefinitionId());
            encoder.writeQualifiedNameArray("BrowsePath", value.getBrowsePath());
            encoder.writeUInt32("AttributeId", value.getAttributeId());
            encoder.writeString("IndexRange", value.getIndexRange());
        }
    }
}
