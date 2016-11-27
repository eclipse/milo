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

@UaDataType("ContentFilterElementResult")
public class ContentFilterElementResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.ContentFilterElementResult;
    public static final NodeId BinaryEncodingId = Identifiers.ContentFilterElementResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ContentFilterElementResult_Encoding_DefaultXml;

    protected final StatusCode _statusCode;
    protected final StatusCode[] _operandStatusCodes;
    protected final DiagnosticInfo[] _operandDiagnosticInfos;

    public ContentFilterElementResult() {
        this._statusCode = null;
        this._operandStatusCodes = null;
        this._operandDiagnosticInfos = null;
    }

    public ContentFilterElementResult(StatusCode _statusCode, StatusCode[] _operandStatusCodes, DiagnosticInfo[] _operandDiagnosticInfos) {
        this._statusCode = _statusCode;
        this._operandStatusCodes = _operandStatusCodes;
        this._operandDiagnosticInfos = _operandDiagnosticInfos;
    }

    public StatusCode getStatusCode() { return _statusCode; }

    @Nullable
    public StatusCode[] getOperandStatusCodes() { return _operandStatusCodes; }

    @Nullable
    public DiagnosticInfo[] getOperandDiagnosticInfos() { return _operandDiagnosticInfos; }

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
            .add("OperandStatusCodes", _operandStatusCodes)
            .add("OperandDiagnosticInfos", _operandDiagnosticInfos)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ContentFilterElementResult> {
        @Override
        public ContentFilterElementResult decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode();
            StatusCode[] _operandStatusCodes = reader.readArray(reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _operandDiagnosticInfos = reader.readArray(reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new ContentFilterElementResult(_statusCode, _operandStatusCodes, _operandDiagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, ContentFilterElementResult encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode(encodable._statusCode);
            writer.writeArray(encodable._operandStatusCodes, writer::writeStatusCode);
            writer.writeArray(encodable._operandDiagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ContentFilterElementResult> {
        @Override
        public ContentFilterElementResult decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode("StatusCode");
            StatusCode[] _operandStatusCodes = reader.readArray("OperandStatusCodes", reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _operandDiagnosticInfos = reader.readArray("OperandDiagnosticInfos", reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new ContentFilterElementResult(_statusCode, _operandStatusCodes, _operandDiagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, ContentFilterElementResult encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode("StatusCode", encodable._statusCode);
            writer.writeArray("OperandStatusCodes", encodable._operandStatusCodes, writer::writeStatusCode);
            writer.writeArray("OperandDiagnosticInfos", encodable._operandDiagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

}
