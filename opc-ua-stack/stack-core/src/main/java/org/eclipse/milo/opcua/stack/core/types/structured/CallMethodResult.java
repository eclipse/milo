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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

public class CallMethodResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.CallMethodResult;
    public static final NodeId BinaryEncodingId = Identifiers.CallMethodResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CallMethodResult_Encoding_DefaultXml;

    protected final StatusCode statusCode;
    protected final StatusCode[] inputArgumentResults;
    protected final DiagnosticInfo[] inputArgumentDiagnosticInfos;
    protected final Variant[] outputArguments;

    public CallMethodResult() {
        this.statusCode = null;
        this.inputArgumentResults = null;
        this.inputArgumentDiagnosticInfos = null;
        this.outputArguments = null;
    }

    public CallMethodResult(StatusCode statusCode, StatusCode[] inputArgumentResults, DiagnosticInfo[] inputArgumentDiagnosticInfos, Variant[] outputArguments) {
        this.statusCode = statusCode;
        this.inputArgumentResults = inputArgumentResults;
        this.inputArgumentDiagnosticInfos = inputArgumentDiagnosticInfos;
        this.outputArguments = outputArguments;
    }

    public StatusCode getStatusCode() { return statusCode; }

    @Nullable
    public StatusCode[] getInputArgumentResults() { return inputArgumentResults; }

    @Nullable
    public DiagnosticInfo[] getInputArgumentDiagnosticInfos() { return inputArgumentDiagnosticInfos; }

    @Nullable
    public Variant[] getOutputArguments() { return outputArguments; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StatusCode", statusCode)
            .add("InputArgumentResults", inputArgumentResults)
            .add("InputArgumentDiagnosticInfos", inputArgumentDiagnosticInfos)
            .add("OutputArguments", outputArguments)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<CallMethodResult> {

        @Override
        public Class<CallMethodResult> getType() {
            return CallMethodResult.class;
        }

        @Override
        public CallMethodResult decode(UaDecoder decoder) throws UaSerializationException {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            StatusCode[] inputArgumentResults = decoder.readArray("InputArgumentResults", decoder::readStatusCode, StatusCode.class);
            DiagnosticInfo[] inputArgumentDiagnosticInfos = decoder.readArray("InputArgumentDiagnosticInfos", decoder::readDiagnosticInfo, DiagnosticInfo.class);
            Variant[] outputArguments = decoder.readArray("OutputArguments", decoder::readVariant, Variant.class);

            return new CallMethodResult(statusCode, inputArgumentResults, inputArgumentDiagnosticInfos, outputArguments);
        }

        @Override
        public void encode(CallMethodResult value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeStatusCode("StatusCode", value.statusCode);
            encoder.writeArray("InputArgumentResults", value.inputArgumentResults, encoder::writeStatusCode);
            encoder.writeArray("InputArgumentDiagnosticInfos", value.inputArgumentDiagnosticInfos, encoder::writeDiagnosticInfo);
            encoder.writeArray("OutputArguments", value.outputArguments, encoder::writeVariant);
        }
    }

}
