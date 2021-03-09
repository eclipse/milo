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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class QueryDataDescription extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=570");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=572");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=571");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15200");

    private final RelativePath relativePath;

    private final UInteger attributeId;

    private final String indexRange;

    public QueryDataDescription(RelativePath relativePath, UInteger attributeId, String indexRange) {
        this.relativePath = relativePath;
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

    public RelativePath getRelativePath() {
        return relativePath;
    }

    public UInteger getAttributeId() {
        return attributeId;
    }

    public String getIndexRange() {
        return indexRange;
    }

    public static final class Codec extends GenericDataTypeCodec<QueryDataDescription> {
        @Override
        public Class<QueryDataDescription> getType() {
            return QueryDataDescription.class;
        }

        @Override
        public QueryDataDescription decode(SerializationContext context, UaDecoder decoder) {
            RelativePath relativePath = (RelativePath) decoder.readStruct("RelativePath", RelativePath.TYPE_ID);
            UInteger attributeId = decoder.readUInt32("AttributeId");
            String indexRange = decoder.readString("IndexRange");
            return new QueryDataDescription(relativePath, attributeId, indexRange);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           QueryDataDescription value) {
            encoder.writeStruct("RelativePath", value.getRelativePath(), RelativePath.TYPE_ID);
            encoder.writeUInt32("AttributeId", value.getAttributeId());
            encoder.writeString("IndexRange", value.getIndexRange());
        }
    }
}
