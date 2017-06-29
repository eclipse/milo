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

public class StatusResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.StatusResult;
    public static final NodeId BinaryEncodingId = Identifiers.StatusResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.StatusResult_Encoding_DefaultXml;

    protected final StatusCode statusCode;
    protected final DiagnosticInfo diagnosticInfo;

    public StatusResult() {
        this.statusCode = null;
        this.diagnosticInfo = null;
    }

    public StatusResult(StatusCode statusCode, DiagnosticInfo diagnosticInfo) {
        this.statusCode = statusCode;
        this.diagnosticInfo = diagnosticInfo;
    }

    public StatusCode getStatusCode() { return statusCode; }

    public DiagnosticInfo getDiagnosticInfo() { return diagnosticInfo; }

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
            .add("DiagnosticInfo", diagnosticInfo)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<StatusResult> {

        @Override
        public Class<StatusResult> getType() {
            return StatusResult.class;
        }

        @Override
        public StatusResult decode(UaDecoder decoder) throws UaSerializationException {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            DiagnosticInfo diagnosticInfo = decoder.readDiagnosticInfo("DiagnosticInfo");

            return new StatusResult(statusCode, diagnosticInfo);
        }

        @Override
        public void encode(StatusResult value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeStatusCode("StatusCode", value.statusCode);
            encoder.writeDiagnosticInfo("DiagnosticInfo", value.diagnosticInfo);
        }
    }

}
