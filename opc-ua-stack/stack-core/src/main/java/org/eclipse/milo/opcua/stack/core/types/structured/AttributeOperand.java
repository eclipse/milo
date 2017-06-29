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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class AttributeOperand extends FilterOperand {

    public static final NodeId TypeId = Identifiers.AttributeOperand;
    public static final NodeId BinaryEncodingId = Identifiers.AttributeOperand_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AttributeOperand_Encoding_DefaultXml;

    protected final NodeId nodeId;
    protected final String alias;
    protected final RelativePath browsePath;
    protected final UInteger attributeId;
    protected final String indexRange;

    public AttributeOperand() {
        super();
        this.nodeId = null;
        this.alias = null;
        this.browsePath = null;
        this.attributeId = null;
        this.indexRange = null;
    }

    public AttributeOperand(NodeId nodeId, String alias, RelativePath browsePath, UInteger attributeId, String indexRange) {
        super();
        this.nodeId = nodeId;
        this.alias = alias;
        this.browsePath = browsePath;
        this.attributeId = attributeId;
        this.indexRange = indexRange;
    }

    public NodeId getNodeId() { return nodeId; }

    public String getAlias() { return alias; }

    public RelativePath getBrowsePath() { return browsePath; }

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
            .add("NodeId", nodeId)
            .add("Alias", alias)
            .add("BrowsePath", browsePath)
            .add("AttributeId", attributeId)
            .add("IndexRange", indexRange)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<AttributeOperand> {

        @Override
        public Class<AttributeOperand> getType() {
            return AttributeOperand.class;
        }

        @Override
        public AttributeOperand decode(UaDecoder decoder) throws UaSerializationException {
            NodeId nodeId = decoder.readNodeId("NodeId");
            String alias = decoder.readString("Alias");
            RelativePath browsePath = (RelativePath) decoder.readBuiltinStruct("BrowsePath", RelativePath.class);
            UInteger attributeId = decoder.readUInt32("AttributeId");
            String indexRange = decoder.readString("IndexRange");

            return new AttributeOperand(nodeId, alias, browsePath, attributeId, indexRange);
        }

        @Override
        public void encode(AttributeOperand value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("NodeId", value.nodeId);
            encoder.writeString("Alias", value.alias);
            encoder.writeBuiltinStruct("BrowsePath", value.browsePath, RelativePath.class);
            encoder.writeUInt32("AttributeId", value.attributeId);
            encoder.writeString("IndexRange", value.indexRange);
        }
    }

}
