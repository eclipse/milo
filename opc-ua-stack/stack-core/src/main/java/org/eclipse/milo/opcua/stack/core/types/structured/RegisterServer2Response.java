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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class RegisterServer2Response implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.RegisterServer2Response;
    public static final NodeId BinaryEncodingId = Identifiers.RegisterServer2Response_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RegisterServer2Response_Encoding_DefaultXml;

    protected final ResponseHeader responseHeader;
    protected final StatusCode[] configurationResults;
    protected final DiagnosticInfo[] diagnosticInfos;

    public RegisterServer2Response() {
        this.responseHeader = null;
        this.configurationResults = null;
        this.diagnosticInfos = null;
    }

    public RegisterServer2Response(ResponseHeader responseHeader, StatusCode[] configurationResults, DiagnosticInfo[] diagnosticInfos) {
        this.responseHeader = responseHeader;
        this.configurationResults = configurationResults;
        this.diagnosticInfos = diagnosticInfos;
    }

    public ResponseHeader getResponseHeader() { return responseHeader; }

    @Nullable
    public StatusCode[] getConfigurationResults() { return configurationResults; }

    @Nullable
    public DiagnosticInfo[] getDiagnosticInfos() { return diagnosticInfos; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", responseHeader)
            .add("ConfigurationResults", configurationResults)
            .add("DiagnosticInfos", diagnosticInfos)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<RegisterServer2Response> {

        @Override
        public Class<RegisterServer2Response> getType() {
            return RegisterServer2Response.class;
        }

        @Override
        public RegisterServer2Response decode(UaDecoder decoder) throws UaSerializationException {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readBuiltinStruct("ResponseHeader", ResponseHeader.class);
            StatusCode[] configurationResults = decoder.readArray("ConfigurationResults", decoder::readStatusCode, StatusCode.class);
            DiagnosticInfo[] diagnosticInfos = decoder.readArray("DiagnosticInfos", decoder::readDiagnosticInfo, DiagnosticInfo.class);

            return new RegisterServer2Response(responseHeader, configurationResults, diagnosticInfos);
        }

        @Override
        public void encode(RegisterServer2Response value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ResponseHeader", value.responseHeader, ResponseHeader.class);
            encoder.writeArray("ConfigurationResults", value.configurationResults, encoder::writeStatusCode);
            encoder.writeArray("DiagnosticInfos", value.diagnosticInfos, encoder::writeDiagnosticInfo);
        }
    }

}
