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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("ResponseHeader")
public class ResponseHeader implements UaStructure {

    public static final NodeId TypeId = Identifiers.ResponseHeader;
    public static final NodeId BinaryEncodingId = Identifiers.ResponseHeader_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ResponseHeader_Encoding_DefaultXml;

    protected final DateTime _timestamp;
    protected final UInteger _requestHandle;
    protected final StatusCode _serviceResult;
    protected final DiagnosticInfo _serviceDiagnostics;
    protected final String[] _stringTable;
    protected final ExtensionObject _additionalHeader;

    public ResponseHeader() {
        this._timestamp = null;
        this._requestHandle = null;
        this._serviceResult = null;
        this._serviceDiagnostics = null;
        this._stringTable = null;
        this._additionalHeader = null;
    }

    public ResponseHeader(DateTime _timestamp, UInteger _requestHandle, StatusCode _serviceResult, DiagnosticInfo _serviceDiagnostics, String[] _stringTable, ExtensionObject _additionalHeader) {
        this._timestamp = _timestamp;
        this._requestHandle = _requestHandle;
        this._serviceResult = _serviceResult;
        this._serviceDiagnostics = _serviceDiagnostics;
        this._stringTable = _stringTable;
        this._additionalHeader = _additionalHeader;
    }

    public DateTime getTimestamp() { return _timestamp; }

    public UInteger getRequestHandle() { return _requestHandle; }

    public StatusCode getServiceResult() { return _serviceResult; }

    public DiagnosticInfo getServiceDiagnostics() { return _serviceDiagnostics; }

    @Nullable
    public String[] getStringTable() { return _stringTable; }

    public ExtensionObject getAdditionalHeader() { return _additionalHeader; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Timestamp", _timestamp)
            .add("RequestHandle", _requestHandle)
            .add("ServiceResult", _serviceResult)
            .add("ServiceDiagnostics", _serviceDiagnostics)
            .add("StringTable", _stringTable)
            .add("AdditionalHeader", _additionalHeader)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ResponseHeader> {
        @Override
        public ResponseHeader decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            DateTime _timestamp = reader.readDateTime();
            UInteger _requestHandle = reader.readUInt32();
            StatusCode _serviceResult = reader.readStatusCode();
            DiagnosticInfo _serviceDiagnostics = reader.readDiagnosticInfo();
            String[] _stringTable = reader.readArray(reader::readString, String.class);
            ExtensionObject _additionalHeader = reader.readExtensionObject();

            return new ResponseHeader(_timestamp, _requestHandle, _serviceResult, _serviceDiagnostics, _stringTable, _additionalHeader);
        }

        @Override
        public void encode(SerializationContext context, ResponseHeader encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeDateTime(encodable._timestamp);
            writer.writeUInt32(encodable._requestHandle);
            writer.writeStatusCode(encodable._serviceResult);
            writer.writeDiagnosticInfo(encodable._serviceDiagnostics);
            writer.writeArray(encodable._stringTable, writer::writeString);
            writer.writeExtensionObject(encodable._additionalHeader);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ResponseHeader> {
        @Override
        public ResponseHeader decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            DateTime _timestamp = reader.readDateTime("Timestamp");
            UInteger _requestHandle = reader.readUInt32("RequestHandle");
            StatusCode _serviceResult = reader.readStatusCode("ServiceResult");
            DiagnosticInfo _serviceDiagnostics = reader.readDiagnosticInfo("ServiceDiagnostics");
            String[] _stringTable = reader.readArray("StringTable", reader::readString, String.class);
            ExtensionObject _additionalHeader = reader.readExtensionObject("AdditionalHeader");

            return new ResponseHeader(_timestamp, _requestHandle, _serviceResult, _serviceDiagnostics, _stringTable, _additionalHeader);
        }

        @Override
        public void encode(SerializationContext context, ResponseHeader encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeDateTime("Timestamp", encodable._timestamp);
            writer.writeUInt32("RequestHandle", encodable._requestHandle);
            writer.writeStatusCode("ServiceResult", encodable._serviceResult);
            writer.writeDiagnosticInfo("ServiceDiagnostics", encodable._serviceDiagnostics);
            writer.writeArray("StringTable", encodable._stringTable, writer::writeString);
            writer.writeExtensionObject("AdditionalHeader", encodable._additionalHeader);
        }
    }

}
