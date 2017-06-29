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

public class SetTriggeringResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.SetTriggeringResponse;
    public static final NodeId BinaryEncodingId = Identifiers.SetTriggeringResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SetTriggeringResponse_Encoding_DefaultXml;

    protected final ResponseHeader responseHeader;
    protected final StatusCode[] addResults;
    protected final DiagnosticInfo[] addDiagnosticInfos;
    protected final StatusCode[] removeResults;
    protected final DiagnosticInfo[] removeDiagnosticInfos;

    public SetTriggeringResponse() {
        this.responseHeader = null;
        this.addResults = null;
        this.addDiagnosticInfos = null;
        this.removeResults = null;
        this.removeDiagnosticInfos = null;
    }

    public SetTriggeringResponse(ResponseHeader responseHeader, StatusCode[] addResults, DiagnosticInfo[] addDiagnosticInfos, StatusCode[] removeResults, DiagnosticInfo[] removeDiagnosticInfos) {
        this.responseHeader = responseHeader;
        this.addResults = addResults;
        this.addDiagnosticInfos = addDiagnosticInfos;
        this.removeResults = removeResults;
        this.removeDiagnosticInfos = removeDiagnosticInfos;
    }

    public ResponseHeader getResponseHeader() { return responseHeader; }

    @Nullable
    public StatusCode[] getAddResults() { return addResults; }

    @Nullable
    public DiagnosticInfo[] getAddDiagnosticInfos() { return addDiagnosticInfos; }

    @Nullable
    public StatusCode[] getRemoveResults() { return removeResults; }

    @Nullable
    public DiagnosticInfo[] getRemoveDiagnosticInfos() { return removeDiagnosticInfos; }

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
            .add("AddResults", addResults)
            .add("AddDiagnosticInfos", addDiagnosticInfos)
            .add("RemoveResults", removeResults)
            .add("RemoveDiagnosticInfos", removeDiagnosticInfos)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<SetTriggeringResponse> {

        @Override
        public Class<SetTriggeringResponse> getType() {
            return SetTriggeringResponse.class;
        }

        @Override
        public SetTriggeringResponse decode(UaDecoder decoder) throws UaSerializationException {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readBuiltinStruct("ResponseHeader", ResponseHeader.class);
            StatusCode[] addResults = decoder.readArray("AddResults", decoder::readStatusCode, StatusCode.class);
            DiagnosticInfo[] addDiagnosticInfos = decoder.readArray("AddDiagnosticInfos", decoder::readDiagnosticInfo, DiagnosticInfo.class);
            StatusCode[] removeResults = decoder.readArray("RemoveResults", decoder::readStatusCode, StatusCode.class);
            DiagnosticInfo[] removeDiagnosticInfos = decoder.readArray("RemoveDiagnosticInfos", decoder::readDiagnosticInfo, DiagnosticInfo.class);

            return new SetTriggeringResponse(responseHeader, addResults, addDiagnosticInfos, removeResults, removeDiagnosticInfos);
        }

        @Override
        public void encode(SetTriggeringResponse value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ResponseHeader", value.responseHeader, ResponseHeader.class);
            encoder.writeArray("AddResults", value.addResults, encoder::writeStatusCode);
            encoder.writeArray("AddDiagnosticInfos", value.addDiagnosticInfos, encoder::writeDiagnosticInfo);
            encoder.writeArray("RemoveResults", value.removeResults, encoder::writeStatusCode);
            encoder.writeArray("RemoveDiagnosticInfos", value.removeDiagnosticInfos, encoder::writeDiagnosticInfo);
        }
    }

}
