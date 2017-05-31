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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
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

@UaDataType("SetTriggeringResponse")
public class SetTriggeringResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.SetTriggeringResponse;
    public static final NodeId BinaryEncodingId = Identifiers.SetTriggeringResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SetTriggeringResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final StatusCode[] _addResults;
    protected final DiagnosticInfo[] _addDiagnosticInfos;
    protected final StatusCode[] _removeResults;
    protected final DiagnosticInfo[] _removeDiagnosticInfos;

    public SetTriggeringResponse() {
        this._responseHeader = null;
        this._addResults = null;
        this._addDiagnosticInfos = null;
        this._removeResults = null;
        this._removeDiagnosticInfos = null;
    }

    public SetTriggeringResponse(ResponseHeader _responseHeader, StatusCode[] _addResults, DiagnosticInfo[] _addDiagnosticInfos, StatusCode[] _removeResults, DiagnosticInfo[] _removeDiagnosticInfos) {
        this._responseHeader = _responseHeader;
        this._addResults = _addResults;
        this._addDiagnosticInfos = _addDiagnosticInfos;
        this._removeResults = _removeResults;
        this._removeDiagnosticInfos = _removeDiagnosticInfos;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    @Nullable
    public StatusCode[] getAddResults() { return _addResults; }

    @Nullable
    public DiagnosticInfo[] getAddDiagnosticInfos() { return _addDiagnosticInfos; }

    @Nullable
    public StatusCode[] getRemoveResults() { return _removeResults; }

    @Nullable
    public DiagnosticInfo[] getRemoveDiagnosticInfos() { return _removeDiagnosticInfos; }

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
            .add("AddResults", _addResults)
            .add("AddDiagnosticInfos", _addDiagnosticInfos)
            .add("RemoveResults", _removeResults)
            .add("RemoveDiagnosticInfos", _removeDiagnosticInfos)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<SetTriggeringResponse> {
        @Override
        public SetTriggeringResponse decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.BinaryEncodingId, reader);
            StatusCode[] _addResults = reader.readArray(reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _addDiagnosticInfos = reader.readArray(reader::readDiagnosticInfo, DiagnosticInfo.class);
            StatusCode[] _removeResults = reader.readArray(reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _removeDiagnosticInfos = reader.readArray(reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new SetTriggeringResponse(_responseHeader, _addResults, _addDiagnosticInfos, _removeResults, _removeDiagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, SetTriggeringResponse encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.BinaryEncodingId, encodable._responseHeader, writer);
            writer.writeArray(encodable._addResults, writer::writeStatusCode);
            writer.writeArray(encodable._addDiagnosticInfos, writer::writeDiagnosticInfo);
            writer.writeArray(encodable._removeResults, writer::writeStatusCode);
            writer.writeArray(encodable._removeDiagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<SetTriggeringResponse> {
        @Override
        public SetTriggeringResponse decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.XmlEncodingId, reader);
            StatusCode[] _addResults = reader.readArray("AddResults", reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _addDiagnosticInfos = reader.readArray("AddDiagnosticInfos", reader::readDiagnosticInfo, DiagnosticInfo.class);
            StatusCode[] _removeResults = reader.readArray("RemoveResults", reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _removeDiagnosticInfos = reader.readArray("RemoveDiagnosticInfos", reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new SetTriggeringResponse(_responseHeader, _addResults, _addDiagnosticInfos, _removeResults, _removeDiagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, SetTriggeringResponse encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.XmlEncodingId, encodable._responseHeader, writer);
            writer.writeArray("AddResults", encodable._addResults, writer::writeStatusCode);
            writer.writeArray("AddDiagnosticInfos", encodable._addDiagnosticInfos, writer::writeDiagnosticInfo);
            writer.writeArray("RemoveResults", encodable._removeResults, writer::writeStatusCode);
            writer.writeArray("RemoveDiagnosticInfos", encodable._removeDiagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

}
