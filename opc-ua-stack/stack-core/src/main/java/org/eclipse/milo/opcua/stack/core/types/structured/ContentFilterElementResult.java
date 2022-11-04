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
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.7.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.7.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ContentFilterElementResult extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=604");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=606");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=605");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15211");

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

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 606),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("StatusCode", LocalizedText.NULL_VALUE, new NodeId(0, 19), -1, null, UInteger.valueOf(0), false),
                new StructureField("OperandStatusCodes", LocalizedText.NULL_VALUE, new NodeId(0, 19), 1, null, UInteger.valueOf(0), false),
                new StructureField("OperandDiagnosticInfos", LocalizedText.NULL_VALUE, new NodeId(0, 25), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ContentFilterElementResult> {
        @Override
        public Class<ContentFilterElementResult> getType() {
            return ContentFilterElementResult.class;
        }

        @Override
        public ContentFilterElementResult decodeType(EncodingContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.decodeStatusCode("StatusCode");
            StatusCode[] operandStatusCodes = decoder.decodeStatusCodeArray("OperandStatusCodes");
            DiagnosticInfo[] operandDiagnosticInfos = decoder.decodeDiagnosticInfoArray("OperandDiagnosticInfos");
            return new ContentFilterElementResult(statusCode, operandStatusCodes, operandDiagnosticInfos);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               ContentFilterElementResult value) {
            encoder.encodeStatusCode("StatusCode", value.getStatusCode());
            encoder.encodeStatusCodeArray("OperandStatusCodes", value.getOperandStatusCodes());
            encoder.encodeDiagnosticInfoArray("OperandDiagnosticInfos", value.getOperandDiagnosticInfos());
        }
    }
}
