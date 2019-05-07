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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class BrowseResult extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=522");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=524");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=523");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15183");

    private final StatusCode statusCode;

    private final ByteString continuationPoint;

    private final ReferenceDescription[] references;

    public BrowseResult(StatusCode statusCode, ByteString continuationPoint,
                        ReferenceDescription[] references) {
        this.statusCode = statusCode;
        this.continuationPoint = continuationPoint;
        this.references = references;
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

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public ByteString getContinuationPoint() {
        return continuationPoint;
    }

    public ReferenceDescription[] getReferences() {
        return references;
    }

    public static final class Codec extends GenericDataTypeCodec<BrowseResult> {
        @Override
        public Class<BrowseResult> getType() {
            return BrowseResult.class;
        }

        @Override
        public BrowseResult decode(SerializationContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            ByteString continuationPoint = decoder.readByteString("ContinuationPoint");
            ReferenceDescription[] references = (ReferenceDescription[]) decoder.readStructArray("References", ReferenceDescription.TYPE_ID);
            return new BrowseResult(statusCode, continuationPoint, references);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, BrowseResult value) {
            encoder.writeStatusCode("StatusCode", value.getStatusCode());
            encoder.writeByteString("ContinuationPoint", value.getContinuationPoint());
            encoder.writeStructArray("References", value.getReferences(), ReferenceDescription.TYPE_ID);
        }
    }
}
