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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.10.3/#5.10.3.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.10.3/#5.10.3.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class HistoryReadResult extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=638");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=640");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=639");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15260");

    private final StatusCode statusCode;

    private final ByteString continuationPoint;

    private final ExtensionObject historyData;

    public HistoryReadResult(StatusCode statusCode, ByteString continuationPoint,
                             ExtensionObject historyData) {
        this.statusCode = statusCode;
        this.continuationPoint = continuationPoint;
        this.historyData = historyData;
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

    public ExtensionObject getHistoryData() {
        return historyData;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 640),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("StatusCode", LocalizedText.NULL_VALUE, new NodeId(0, 19), -1, null, UInteger.valueOf(0), false),
                new StructureField("ContinuationPoint", LocalizedText.NULL_VALUE, new NodeId(0, 521), -1, null, UInteger.valueOf(0), false),
                new StructureField("HistoryData", LocalizedText.NULL_VALUE, new NodeId(0, 22), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<HistoryReadResult> {
        @Override
        public Class<HistoryReadResult> getType() {
            return HistoryReadResult.class;
        }

        @Override
        public HistoryReadResult decodeType(SerializationContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.decodeStatusCode("StatusCode");
            ByteString continuationPoint = decoder.decodeByteString("ContinuationPoint");
            ExtensionObject historyData = decoder.decodeExtensionObject("HistoryData");
            return new HistoryReadResult(statusCode, continuationPoint, historyData);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               HistoryReadResult value) {
            encoder.encodeStatusCode("StatusCode", value.getStatusCode());
            encoder.encodeByteString("ContinuationPoint", value.getContinuationPoint());
            encoder.encodeExtensionObject("HistoryData", value.getHistoryData());
        }
    }
}
