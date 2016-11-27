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
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@UaDataType("ActivateSessionResponse")
public class ActivateSessionResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.ActivateSessionResponse;
    public static final NodeId BinaryEncodingId = Identifiers.ActivateSessionResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ActivateSessionResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final ByteString _serverNonce;
    protected final StatusCode[] _results;
    protected final DiagnosticInfo[] _diagnosticInfos;

    public ActivateSessionResponse() {
        this._responseHeader = null;
        this._serverNonce = null;
        this._results = null;
        this._diagnosticInfos = null;
    }

    public ActivateSessionResponse(ResponseHeader _responseHeader, ByteString _serverNonce, StatusCode[] _results, DiagnosticInfo[] _diagnosticInfos) {
        this._responseHeader = _responseHeader;
        this._serverNonce = _serverNonce;
        this._results = _results;
        this._diagnosticInfos = _diagnosticInfos;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public ByteString getServerNonce() { return _serverNonce; }

    @Nullable
    public StatusCode[] getResults() { return _results; }

    @Nullable
    public DiagnosticInfo[] getDiagnosticInfos() { return _diagnosticInfos; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", _responseHeader)
            .add("ServerNonce", _serverNonce)
            .add("Results", _results)
            .add("DiagnosticInfos", _diagnosticInfos)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ActivateSessionResponse> {
        @Override
        public ActivateSessionResponse decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", reader);
            ByteString _serverNonce = reader.readByteString();
            StatusCode[] _results = reader.readArray(reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _diagnosticInfos = reader.readArray(reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new ActivateSessionResponse(_responseHeader, _serverNonce, _results, _diagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, ActivateSessionResponse encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", encodable._responseHeader, writer);
            writer.writeByteString(encodable._serverNonce);
            writer.writeArray(encodable._results, writer::writeStatusCode);
            writer.writeArray(encodable._diagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ActivateSessionResponse> {
        @Override
        public ActivateSessionResponse decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", reader);
            ByteString _serverNonce = reader.readByteString("ServerNonce");
            StatusCode[] _results = reader.readArray("Results", reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _diagnosticInfos = reader.readArray("DiagnosticInfos", reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new ActivateSessionResponse(_responseHeader, _serverNonce, _results, _diagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, ActivateSessionResponse encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", encodable._responseHeader, writer);
            writer.writeByteString("ServerNonce", encodable._serverNonce);
            writer.writeArray("Results", encodable._results, writer::writeStatusCode);
            writer.writeArray("DiagnosticInfos", encodable._diagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

}
