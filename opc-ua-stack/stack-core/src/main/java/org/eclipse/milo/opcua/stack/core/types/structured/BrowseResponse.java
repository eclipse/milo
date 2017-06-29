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

public class BrowseResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.BrowseResponse;
    public static final NodeId BinaryEncodingId = Identifiers.BrowseResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowseResponse_Encoding_DefaultXml;

    protected final ResponseHeader responseHeader;
    protected final BrowseResult[] results;
    protected final DiagnosticInfo[] diagnosticInfos;

    public BrowseResponse() {
        this.responseHeader = null;
        this.results = null;
        this.diagnosticInfos = null;
    }

    public BrowseResponse(ResponseHeader responseHeader, BrowseResult[] results, DiagnosticInfo[] diagnosticInfos) {
        this.responseHeader = responseHeader;
        this.results = results;
        this.diagnosticInfos = diagnosticInfos;
    }

    public ResponseHeader getResponseHeader() { return responseHeader; }

    @Nullable
    public BrowseResult[] getResults() { return results; }

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
            .add("Results", results)
            .add("DiagnosticInfos", diagnosticInfos)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<BrowseResponse> {

        @Override
        public Class<BrowseResponse> getType() {
            return BrowseResponse.class;
        }

        @Override
        public BrowseResponse decode(UaDecoder decoder) throws UaSerializationException {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readBuiltinStruct("ResponseHeader", ResponseHeader.class);
            BrowseResult[] results =
                decoder.readBuiltinStructArray(
                    "Results",
                    BrowseResult.class
                );
            DiagnosticInfo[] diagnosticInfos = decoder.readArray("DiagnosticInfos", decoder::readDiagnosticInfo, DiagnosticInfo.class);

            return new BrowseResponse(responseHeader, results, diagnosticInfos);
        }

        @Override
        public void encode(BrowseResponse value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ResponseHeader", value.responseHeader, ResponseHeader.class);
            encoder.writeBuiltinStructArray(
                "Results",
                value.results,
                BrowseResult.class
            );
            encoder.writeArray("DiagnosticInfos", value.diagnosticInfos, encoder::writeDiagnosticInfo);
        }
    }

}
