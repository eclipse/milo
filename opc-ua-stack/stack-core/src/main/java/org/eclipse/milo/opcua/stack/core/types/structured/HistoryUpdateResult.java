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

@UaDataType("HistoryUpdateResult")
public class HistoryUpdateResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryUpdateResult;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryUpdateResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryUpdateResult_Encoding_DefaultXml;

    protected final StatusCode _statusCode;
    protected final StatusCode[] _operationResults;
    protected final DiagnosticInfo[] _diagnosticInfos;

    public HistoryUpdateResult() {
        this._statusCode = null;
        this._operationResults = null;
        this._diagnosticInfos = null;
    }

    public HistoryUpdateResult(StatusCode _statusCode, StatusCode[] _operationResults, DiagnosticInfo[] _diagnosticInfos) {
        this._statusCode = _statusCode;
        this._operationResults = _operationResults;
        this._diagnosticInfos = _diagnosticInfos;
    }

    public StatusCode getStatusCode() { return _statusCode; }

    @Nullable
    public StatusCode[] getOperationResults() { return _operationResults; }

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
            .add("StatusCode", _statusCode)
            .add("OperationResults", _operationResults)
            .add("DiagnosticInfos", _diagnosticInfos)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<HistoryUpdateResult> {
        @Override
        public HistoryUpdateResult decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode();
            StatusCode[] _operationResults = reader.readArray(reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _diagnosticInfos = reader.readArray(reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new HistoryUpdateResult(_statusCode, _operationResults, _diagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, HistoryUpdateResult encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode(encodable._statusCode);
            writer.writeArray(encodable._operationResults, writer::writeStatusCode);
            writer.writeArray(encodable._diagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<HistoryUpdateResult> {
        @Override
        public HistoryUpdateResult decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode("StatusCode");
            StatusCode[] _operationResults = reader.readArray("OperationResults", reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _diagnosticInfos = reader.readArray("DiagnosticInfos", reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new HistoryUpdateResult(_statusCode, _operationResults, _diagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, HistoryUpdateResult encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode("StatusCode", encodable._statusCode);
            writer.writeArray("OperationResults", encodable._operationResults, writer::writeStatusCode);
            writer.writeArray("DiagnosticInfos", encodable._diagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

}
