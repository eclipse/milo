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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class QueryDataDescription implements UaStructure {

    public static final NodeId TypeId = Identifiers.QueryDataDescription;
    public static final NodeId BinaryEncodingId = Identifiers.QueryDataDescription_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.QueryDataDescription_Encoding_DefaultXml;

    protected final RelativePath relativePath;
    protected final UInteger attributeId;
    protected final String indexRange;

    public QueryDataDescription() {
        this.relativePath = null;
        this.attributeId = null;
        this.indexRange = null;
    }

    public QueryDataDescription(RelativePath relativePath, UInteger attributeId, String indexRange) {
        this.relativePath = relativePath;
        this.attributeId = attributeId;
        this.indexRange = indexRange;
    }

    public RelativePath getRelativePath() { return relativePath; }

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
            .add("RelativePath", relativePath)
            .add("AttributeId", attributeId)
            .add("IndexRange", indexRange)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<QueryDataDescription> {

        @Override
        public Class<QueryDataDescription> getType() {
            return QueryDataDescription.class;
        }

        @Override
        public QueryDataDescription decode(UaDecoder decoder) throws UaSerializationException {
            RelativePath relativePath = (RelativePath) decoder.readBuiltinStruct("RelativePath", RelativePath.class);
            UInteger attributeId = decoder.readUInt32("AttributeId");
            String indexRange = decoder.readString("IndexRange");

            return new QueryDataDescription(relativePath, attributeId, indexRange);
        }

        @Override
        public void encode(QueryDataDescription value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RelativePath", value.relativePath, RelativePath.class);
            encoder.writeUInt32("AttributeId", value.attributeId);
            encoder.writeString("IndexRange", value.indexRange);
        }
    }

}
