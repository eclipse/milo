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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class CallMethodResult extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=707");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=709");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=708");

    private final StatusCode statusCode;

    private final StatusCode[] inputArgumentResults;

    private final DiagnosticInfo[] inputArgumentDiagnosticInfos;

    private final Variant[] outputArguments;

    public CallMethodResult(StatusCode statusCode, StatusCode[] inputArgumentResults,
                            DiagnosticInfo[] inputArgumentDiagnosticInfos, Variant[] outputArguments) {
        this.statusCode = statusCode;
        this.inputArgumentResults = inputArgumentResults;
        this.inputArgumentDiagnosticInfos = inputArgumentDiagnosticInfos;
        this.outputArguments = outputArguments;
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

    public StatusCode[] getInputArgumentResults() {
        return inputArgumentResults;
    }

    public DiagnosticInfo[] getInputArgumentDiagnosticInfos() {
        return inputArgumentDiagnosticInfos;
    }

    public Variant[] getOutputArguments() {
        return outputArguments;
    }

    public static final class Codec extends GenericDataTypeCodec<CallMethodResult> {
        @Override
        public Class<CallMethodResult> getType() {
            return CallMethodResult.class;
        }

        @Override
        public CallMethodResult decode(SerializationContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            StatusCode[] inputArgumentResults = decoder.readStatusCodeArray("InputArgumentResults");
            DiagnosticInfo[] inputArgumentDiagnosticInfos = decoder.readDiagnosticInfoArray("InputArgumentDiagnosticInfos");
            Variant[] outputArguments = decoder.readVariantArray("OutputArguments");
            return new CallMethodResult(statusCode, inputArgumentResults, inputArgumentDiagnosticInfos, outputArguments);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, CallMethodResult value) {
            encoder.writeStatusCode("StatusCode", value.getStatusCode());
            encoder.writeStatusCodeArray("InputArgumentResults", value.getInputArgumentResults());
            encoder.writeDiagnosticInfoArray("InputArgumentDiagnosticInfos", value.getInputArgumentDiagnosticInfos());
            encoder.writeVariantArray("OutputArguments", value.getOutputArguments());
        }
    }
}
