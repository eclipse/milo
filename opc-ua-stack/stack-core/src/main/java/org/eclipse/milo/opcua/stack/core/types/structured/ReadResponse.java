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
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("ReadResponse")
public class ReadResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.ReadResponse;
    public static final NodeId BinaryEncodingId = Identifiers.ReadResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final DataValue[] _results;
    protected final DiagnosticInfo[] _diagnosticInfos;

    public ReadResponse() {
        this._responseHeader = null;
        this._results = null;
        this._diagnosticInfos = null;
    }

    public ReadResponse(ResponseHeader _responseHeader, DataValue[] _results, DiagnosticInfo[] _diagnosticInfos) {
        this._responseHeader = _responseHeader;
        this._results = _results;
        this._diagnosticInfos = _diagnosticInfos;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    @Nullable
    public DataValue[] getResults() { return _results; }

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
            .add("Results", _results)
            .add("DiagnosticInfos", _diagnosticInfos)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ReadResponse> {
        @Override
        public ReadResponse decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", reader);
            DataValue[] _results = reader.readArray(reader::readDataValue, DataValue.class);
            DiagnosticInfo[] _diagnosticInfos = reader.readArray(reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new ReadResponse(_responseHeader, _results, _diagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, ReadResponse encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", encodable._responseHeader, writer);
            writer.writeArray(encodable._results, writer::writeDataValue);
            writer.writeArray(encodable._diagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ReadResponse> {
        @Override
        public ReadResponse decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", reader);
            DataValue[] _results = reader.readArray("Results", reader::readDataValue, DataValue.class);
            DiagnosticInfo[] _diagnosticInfos = reader.readArray("DiagnosticInfos", reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new ReadResponse(_responseHeader, _results, _diagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, ReadResponse encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", encodable._responseHeader, writer);
            writer.writeArray("Results", encodable._results, writer::writeDataValue);
            writer.writeArray("DiagnosticInfos", encodable._diagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

}
