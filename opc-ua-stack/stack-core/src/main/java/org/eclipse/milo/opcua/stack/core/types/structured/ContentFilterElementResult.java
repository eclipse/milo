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

public class ContentFilterElementResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.ContentFilterElementResult;
    public static final NodeId BinaryEncodingId = Identifiers.ContentFilterElementResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ContentFilterElementResult_Encoding_DefaultXml;

    protected final StatusCode statusCode;
    protected final StatusCode[] operandStatusCodes;
    protected final DiagnosticInfo[] operandDiagnosticInfos;

    public ContentFilterElementResult() {
        this.statusCode = null;
        this.operandStatusCodes = null;
        this.operandDiagnosticInfos = null;
    }

    public ContentFilterElementResult(StatusCode statusCode, StatusCode[] operandStatusCodes, DiagnosticInfo[] operandDiagnosticInfos) {
        this.statusCode = statusCode;
        this.operandStatusCodes = operandStatusCodes;
        this.operandDiagnosticInfos = operandDiagnosticInfos;
    }

    public StatusCode getStatusCode() { return statusCode; }

    @Nullable
    public StatusCode[] getOperandStatusCodes() { return operandStatusCodes; }

    @Nullable
    public DiagnosticInfo[] getOperandDiagnosticInfos() { return operandDiagnosticInfos; }

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
            .add("OperandStatusCodes", operandStatusCodes)
            .add("OperandDiagnosticInfos", operandDiagnosticInfos)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ContentFilterElementResult> {

        @Override
        public Class<ContentFilterElementResult> getType() {
            return ContentFilterElementResult.class;
        }

        @Override
        public ContentFilterElementResult decode(UaDecoder decoder) throws UaSerializationException {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            StatusCode[] operandStatusCodes = decoder.readArray("OperandStatusCodes", decoder::readStatusCode, StatusCode.class);
            DiagnosticInfo[] operandDiagnosticInfos = decoder.readArray("OperandDiagnosticInfos", decoder::readDiagnosticInfo, DiagnosticInfo.class);

            return new ContentFilterElementResult(statusCode, operandStatusCodes, operandDiagnosticInfos);
        }

        @Override
        public void encode(ContentFilterElementResult value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeStatusCode("StatusCode", value.statusCode);
            encoder.writeArray("OperandStatusCodes", value.operandStatusCodes, encoder::writeStatusCode);
            encoder.writeArray("OperandDiagnosticInfos", value.operandDiagnosticInfos, encoder::writeDiagnosticInfo);
        }
    }

}
