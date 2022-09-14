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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.6">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.6</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class BrowseResult extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=522");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=524");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=523");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15183");

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

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 524),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("StatusCode", LocalizedText.NULL_VALUE, new NodeId(0, 19), -1, null, UInteger.valueOf(0), false),
                new StructureField("ContinuationPoint", LocalizedText.NULL_VALUE, new NodeId(0, 521), -1, null, UInteger.valueOf(0), false),
                new StructureField("References", LocalizedText.NULL_VALUE, new NodeId(0, 518), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<BrowseResult> {
        @Override
        public Class<BrowseResult> getType() {
            return BrowseResult.class;
        }

        @Override
        public BrowseResult decodeType(SerializationContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.decodeStatusCode("StatusCode");
            ByteString continuationPoint = decoder.decodeByteString("ContinuationPoint");
            ReferenceDescription[] references = (ReferenceDescription[]) decoder.decodeStructArray("References", ReferenceDescription.TYPE_ID);
            return new BrowseResult(statusCode, continuationPoint, references);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, BrowseResult value) {
            encoder.encodeStatusCode("StatusCode", value.getStatusCode());
            encoder.encodeByteString("ContinuationPoint", value.getContinuationPoint());
            encoder.encodeStructArray("References", value.getReferences(), ReferenceDescription.TYPE_ID);
        }
    }
}
