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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ResponseHeader implements UaStructure {

    public static final NodeId TypeId = Identifiers.ResponseHeader;
    public static final NodeId BinaryEncodingId = Identifiers.ResponseHeader_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ResponseHeader_Encoding_DefaultXml;

    protected final DateTime timestamp;
    protected final UInteger requestHandle;
    protected final StatusCode serviceResult;
    protected final DiagnosticInfo serviceDiagnostics;
    protected final String[] stringTable;
    protected final ExtensionObject additionalHeader;

    public ResponseHeader() {
        this.timestamp = null;
        this.requestHandle = null;
        this.serviceResult = null;
        this.serviceDiagnostics = null;
        this.stringTable = null;
        this.additionalHeader = null;
    }

    public ResponseHeader(DateTime timestamp, UInteger requestHandle, StatusCode serviceResult, DiagnosticInfo serviceDiagnostics, String[] stringTable, ExtensionObject additionalHeader) {
        this.timestamp = timestamp;
        this.requestHandle = requestHandle;
        this.serviceResult = serviceResult;
        this.serviceDiagnostics = serviceDiagnostics;
        this.stringTable = stringTable;
        this.additionalHeader = additionalHeader;
    }

    public DateTime getTimestamp() { return timestamp; }

    public UInteger getRequestHandle() { return requestHandle; }

    public StatusCode getServiceResult() { return serviceResult; }

    public DiagnosticInfo getServiceDiagnostics() { return serviceDiagnostics; }

    @Nullable
    public String[] getStringTable() { return stringTable; }

    public ExtensionObject getAdditionalHeader() { return additionalHeader; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Timestamp", timestamp)
            .add("RequestHandle", requestHandle)
            .add("ServiceResult", serviceResult)
            .add("ServiceDiagnostics", serviceDiagnostics)
            .add("StringTable", stringTable)
            .add("AdditionalHeader", additionalHeader)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ResponseHeader> {

        @Override
        public Class<ResponseHeader> getType() {
            return ResponseHeader.class;
        }

        @Override
        public ResponseHeader decode(UaDecoder decoder) throws UaSerializationException {
            DateTime timestamp = decoder.readDateTime("Timestamp");
            UInteger requestHandle = decoder.readUInt32("RequestHandle");
            StatusCode serviceResult = decoder.readStatusCode("ServiceResult");
            DiagnosticInfo serviceDiagnostics = decoder.readDiagnosticInfo("ServiceDiagnostics");
            String[] stringTable = decoder.readArray("StringTable", decoder::readString, String.class);
            ExtensionObject additionalHeader = decoder.readExtensionObject("AdditionalHeader");

            return new ResponseHeader(timestamp, requestHandle, serviceResult, serviceDiagnostics, stringTable, additionalHeader);
        }

        @Override
        public void encode(ResponseHeader value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeDateTime("Timestamp", value.timestamp);
            encoder.writeUInt32("RequestHandle", value.requestHandle);
            encoder.writeStatusCode("ServiceResult", value.serviceResult);
            encoder.writeDiagnosticInfo("ServiceDiagnostics", value.serviceDiagnostics);
            encoder.writeArray("StringTable", value.stringTable, encoder::writeString);
            encoder.writeExtensionObject("AdditionalHeader", value.additionalHeader);
        }
    }

}
