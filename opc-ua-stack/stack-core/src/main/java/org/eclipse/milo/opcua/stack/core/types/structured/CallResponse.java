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
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.11.2/#5.11.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.11.2/#5.11.2.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class CallResponse extends Structure implements UaResponseMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=713");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=715");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=714");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15292");

    private final ResponseHeader responseHeader;

    private final CallMethodResult[] results;

    private final DiagnosticInfo[] diagnosticInfos;

    public CallResponse(ResponseHeader responseHeader, CallMethodResult[] results,
                        DiagnosticInfo[] diagnosticInfos) {
        this.responseHeader = responseHeader;
        this.results = results;
        this.diagnosticInfos = diagnosticInfos;
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

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public CallMethodResult[] getResults() {
        return results;
    }

    public DiagnosticInfo[] getDiagnosticInfos() {
        return diagnosticInfos;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 715),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ResponseHeader", LocalizedText.NULL_VALUE, new NodeId(0, 392), -1, null, UInteger.valueOf(0), false),
                new StructureField("Results", LocalizedText.NULL_VALUE, new NodeId(0, 707), 1, null, UInteger.valueOf(0), false),
                new StructureField("DiagnosticInfos", LocalizedText.NULL_VALUE, new NodeId(0, 25), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<CallResponse> {
        @Override
        public Class<CallResponse> getType() {
            return CallResponse.class;
        }

        @Override
        public CallResponse decodeType(SerializationContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.decodeStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            CallMethodResult[] results = (CallMethodResult[]) decoder.decodeStructArray("Results", CallMethodResult.TYPE_ID);
            DiagnosticInfo[] diagnosticInfos = decoder.decodeDiagnosticInfoArray("DiagnosticInfos");
            return new CallResponse(responseHeader, results, diagnosticInfos);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, CallResponse value) {
            encoder.encodeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.encodeStructArray("Results", value.getResults(), CallMethodResult.TYPE_ID);
            encoder.encodeDiagnosticInfoArray("DiagnosticInfos", value.getDiagnosticInfos());
        }
    }
}
