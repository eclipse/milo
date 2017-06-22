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

@UaDataType("ParsingResult")
public class ParsingResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.ParsingResult;
    public static final NodeId BinaryEncodingId = Identifiers.ParsingResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ParsingResult_Encoding_DefaultXml;

    protected final StatusCode _statusCode;
    protected final StatusCode[] _dataStatusCodes;
    protected final DiagnosticInfo[] _dataDiagnosticInfos;

    public ParsingResult() {
        this._statusCode = null;
        this._dataStatusCodes = null;
        this._dataDiagnosticInfos = null;
    }

    public ParsingResult(StatusCode _statusCode, StatusCode[] _dataStatusCodes, DiagnosticInfo[] _dataDiagnosticInfos) {
        this._statusCode = _statusCode;
        this._dataStatusCodes = _dataStatusCodes;
        this._dataDiagnosticInfos = _dataDiagnosticInfos;
    }

    public StatusCode getStatusCode() { return _statusCode; }

    @Nullable
    public StatusCode[] getDataStatusCodes() { return _dataStatusCodes; }

    @Nullable
    public DiagnosticInfo[] getDataDiagnosticInfos() { return _dataDiagnosticInfos; }

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
            .add("DataStatusCodes", _dataStatusCodes)
            .add("DataDiagnosticInfos", _dataDiagnosticInfos)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ParsingResult> {
        @Override
        public ParsingResult decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode();
            StatusCode[] _dataStatusCodes = reader.readArray(reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _dataDiagnosticInfos = reader.readArray(reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new ParsingResult(_statusCode, _dataStatusCodes, _dataDiagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, ParsingResult value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode(value._statusCode);
            writer.writeArray(value._dataStatusCodes, writer::writeStatusCode);
            writer.writeArray(value._dataDiagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ParsingResult> {
        @Override
        public ParsingResult decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode("StatusCode");
            StatusCode[] _dataStatusCodes = reader.readArray("DataStatusCodes", reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _dataDiagnosticInfos = reader.readArray("DataDiagnosticInfos", reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new ParsingResult(_statusCode, _dataStatusCodes, _dataDiagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, ParsingResult encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode("StatusCode", encodable._statusCode);
            writer.writeArray("DataStatusCodes", encodable._dataStatusCodes, writer::writeStatusCode);
            writer.writeArray("DataDiagnosticInfos", encodable._dataDiagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

}
