/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class SimpleAttributeOperand extends FilterOperand {

    public static final NodeId TypeId = Identifiers.SimpleAttributeOperand;
    public static final NodeId BinaryEncodingId = Identifiers.SimpleAttributeOperand_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SimpleAttributeOperand_Encoding_DefaultXml;

    protected final NodeId typeDefinitionId;
    protected final QualifiedName[] browsePath;
    protected final UInteger attributeId;
    protected final String indexRange;

    public SimpleAttributeOperand() {
        super();
        this.typeDefinitionId = null;
        this.browsePath = null;
        this.attributeId = null;
        this.indexRange = null;
    }

    public SimpleAttributeOperand(NodeId typeDefinitionId, QualifiedName[] browsePath, UInteger attributeId, String indexRange) {
        super();
        this.typeDefinitionId = typeDefinitionId;
        this.browsePath = browsePath;
        this.attributeId = attributeId;
        this.indexRange = indexRange;
    }

    public NodeId getTypeDefinitionId() { return typeDefinitionId; }

    @Nullable
    public QualifiedName[] getBrowsePath() { return browsePath; }

    public UInteger getAttributeId() { return attributeId; }

    public String getIndexRange() { return indexRange; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("TypeDefinitionId", typeDefinitionId)
            .add("BrowsePath", browsePath)
            .add("AttributeId", attributeId)
            .add("IndexRange", indexRange)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<SimpleAttributeOperand> {

        @Override
        public Class<SimpleAttributeOperand> getType() {
            return SimpleAttributeOperand.class;
        }

        @Override
        public SimpleAttributeOperand decode(UaDecoder decoder) throws UaSerializationException {
            NodeId typeDefinitionId = decoder.readNodeId("TypeDefinitionId");
            QualifiedName[] browsePath = decoder.readArray("BrowsePath", decoder::readQualifiedName, QualifiedName.class);
            UInteger attributeId = decoder.readUInt32("AttributeId");
            String indexRange = decoder.readString("IndexRange");

            return new SimpleAttributeOperand(typeDefinitionId, browsePath, attributeId, indexRange);
        }

        @Override
        public void encode(SimpleAttributeOperand value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("TypeDefinitionId", value.typeDefinitionId);
            encoder.writeArray("BrowsePath", value.browsePath, encoder::writeQualifiedName);
            encoder.writeUInt32("AttributeId", value.attributeId);
            encoder.writeString("IndexRange", value.indexRange);
        }
    }

}
