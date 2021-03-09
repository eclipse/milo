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
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ContentFilterElementResult extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=604");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=606");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=605");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15211");

    private final StatusCode statusCode;

    private final StatusCode[] operandStatusCodes;

    private final DiagnosticInfo[] operandDiagnosticInfos;

    public ContentFilterElementResult(StatusCode statusCode, StatusCode[] operandStatusCodes,
                                      DiagnosticInfo[] operandDiagnosticInfos) {
        this.statusCode = statusCode;
        this.operandStatusCodes = operandStatusCodes;
        this.operandDiagnosticInfos = operandDiagnosticInfos;
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

    public StatusCode[] getOperandStatusCodes() {
        return operandStatusCodes;
    }

    public DiagnosticInfo[] getOperandDiagnosticInfos() {
        return operandDiagnosticInfos;
    }

    public static final class Codec extends GenericDataTypeCodec<ContentFilterElementResult> {
        @Override
        public Class<ContentFilterElementResult> getType() {
            return ContentFilterElementResult.class;
        }

        @Override
        public ContentFilterElementResult decode(SerializationContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            StatusCode[] operandStatusCodes = decoder.readStatusCodeArray("OperandStatusCodes");
            DiagnosticInfo[] operandDiagnosticInfos = decoder.readDiagnosticInfoArray("OperandDiagnosticInfos");
            return new ContentFilterElementResult(statusCode, operandStatusCodes, operandDiagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ContentFilterElementResult value) {
            encoder.writeStatusCode("StatusCode", value.getStatusCode());
            encoder.writeStatusCodeArray("OperandStatusCodes", value.getOperandStatusCodes());
            encoder.writeDiagnosticInfoArray("OperandDiagnosticInfos", value.getOperandDiagnosticInfos());
        }
    }
}
