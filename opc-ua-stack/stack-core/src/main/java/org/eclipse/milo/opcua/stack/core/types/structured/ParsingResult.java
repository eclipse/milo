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

public class ParsingResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.ParsingResult;
    public static final NodeId BinaryEncodingId = Identifiers.ParsingResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ParsingResult_Encoding_DefaultXml;

    protected final StatusCode statusCode;
    protected final StatusCode[] dataStatusCodes;
    protected final DiagnosticInfo[] dataDiagnosticInfos;

    public ParsingResult() {
        this.statusCode = null;
        this.dataStatusCodes = null;
        this.dataDiagnosticInfos = null;
    }

    public ParsingResult(StatusCode statusCode, StatusCode[] dataStatusCodes, DiagnosticInfo[] dataDiagnosticInfos) {
        this.statusCode = statusCode;
        this.dataStatusCodes = dataStatusCodes;
        this.dataDiagnosticInfos = dataDiagnosticInfos;
    }

    public StatusCode getStatusCode() { return statusCode; }

    @Nullable
    public StatusCode[] getDataStatusCodes() { return dataStatusCodes; }

    @Nullable
    public DiagnosticInfo[] getDataDiagnosticInfos() { return dataDiagnosticInfos; }

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
            .add("DataStatusCodes", dataStatusCodes)
            .add("DataDiagnosticInfos", dataDiagnosticInfos)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ParsingResult> {

        @Override
        public Class<ParsingResult> getType() {
            return ParsingResult.class;
        }

        @Override
        public ParsingResult decode(UaDecoder decoder) throws UaSerializationException {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            StatusCode[] dataStatusCodes = decoder.readArray("DataStatusCodes", decoder::readStatusCode, StatusCode.class);
            DiagnosticInfo[] dataDiagnosticInfos = decoder.readArray("DataDiagnosticInfos", decoder::readDiagnosticInfo, DiagnosticInfo.class);

            return new ParsingResult(statusCode, dataStatusCodes, dataDiagnosticInfos);
        }

        @Override
        public void encode(ParsingResult value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeStatusCode("StatusCode", value.statusCode);
            encoder.writeArray("DataStatusCodes", value.dataStatusCodes, encoder::writeStatusCode);
            encoder.writeArray("DataDiagnosticInfos", value.dataDiagnosticInfos, encoder::writeDiagnosticInfo);
        }
    }

}
