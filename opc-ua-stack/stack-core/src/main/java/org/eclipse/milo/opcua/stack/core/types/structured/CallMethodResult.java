/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

@UaDataType("CallMethodResult")
public class CallMethodResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.CallMethodResult;
    public static final NodeId BinaryEncodingId = Identifiers.CallMethodResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CallMethodResult_Encoding_DefaultXml;

    protected final StatusCode _statusCode;
    protected final StatusCode[] _inputArgumentResults;
    protected final DiagnosticInfo[] _inputArgumentDiagnosticInfos;
    protected final Variant[] _outputArguments;

    public CallMethodResult() {
        this._statusCode = null;
        this._inputArgumentResults = null;
        this._inputArgumentDiagnosticInfos = null;
        this._outputArguments = null;
    }

    public CallMethodResult(StatusCode _statusCode, StatusCode[] _inputArgumentResults, DiagnosticInfo[] _inputArgumentDiagnosticInfos, Variant[] _outputArguments) {
        this._statusCode = _statusCode;
        this._inputArgumentResults = _inputArgumentResults;
        this._inputArgumentDiagnosticInfos = _inputArgumentDiagnosticInfos;
        this._outputArguments = _outputArguments;
    }

    public StatusCode getStatusCode() { return _statusCode; }

    @Nullable
    public StatusCode[] getInputArgumentResults() { return _inputArgumentResults; }

    @Nullable
    public DiagnosticInfo[] getInputArgumentDiagnosticInfos() { return _inputArgumentDiagnosticInfos; }

    @Nullable
    public Variant[] getOutputArguments() { return _outputArguments; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StatusCode", _statusCode)
            .add("InputArgumentResults", _inputArgumentResults)
            .add("InputArgumentDiagnosticInfos", _inputArgumentDiagnosticInfos)
            .add("OutputArguments", _outputArguments)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<CallMethodResult> {
        @Override
        public CallMethodResult decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode();
            StatusCode[] _inputArgumentResults = reader.readArray(reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _inputArgumentDiagnosticInfos = reader.readArray(reader::readDiagnosticInfo, DiagnosticInfo.class);
            Variant[] _outputArguments = reader.readArray(reader::readVariant, Variant.class);

            return new CallMethodResult(_statusCode, _inputArgumentResults, _inputArgumentDiagnosticInfos, _outputArguments);
        }

        @Override
        public void encode(SerializationContext context, CallMethodResult encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode(encodable._statusCode);
            writer.writeArray(encodable._inputArgumentResults, writer::writeStatusCode);
            writer.writeArray(encodable._inputArgumentDiagnosticInfos, writer::writeDiagnosticInfo);
            writer.writeArray(encodable._outputArguments, writer::writeVariant);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<CallMethodResult> {
        @Override
        public CallMethodResult decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode("StatusCode");
            StatusCode[] _inputArgumentResults = reader.readArray("InputArgumentResults", reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _inputArgumentDiagnosticInfos = reader.readArray("InputArgumentDiagnosticInfos", reader::readDiagnosticInfo, DiagnosticInfo.class);
            Variant[] _outputArguments = reader.readArray("OutputArguments", reader::readVariant, Variant.class);

            return new CallMethodResult(_statusCode, _inputArgumentResults, _inputArgumentDiagnosticInfos, _outputArguments);
        }

        @Override
        public void encode(SerializationContext context, CallMethodResult encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode("StatusCode", encodable._statusCode);
            writer.writeArray("InputArgumentResults", encodable._inputArgumentResults, writer::writeStatusCode);
            writer.writeArray("InputArgumentDiagnosticInfos", encodable._inputArgumentDiagnosticInfos, writer::writeDiagnosticInfo);
            writer.writeArray("OutputArguments", encodable._outputArguments, writer::writeVariant);
        }
    }

}
