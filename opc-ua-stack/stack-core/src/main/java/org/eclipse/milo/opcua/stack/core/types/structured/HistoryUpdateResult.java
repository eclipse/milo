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

public class HistoryUpdateResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryUpdateResult;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryUpdateResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryUpdateResult_Encoding_DefaultXml;

    protected final StatusCode statusCode;
    protected final StatusCode[] operationResults;
    protected final DiagnosticInfo[] diagnosticInfos;

    public HistoryUpdateResult() {
        this.statusCode = null;
        this.operationResults = null;
        this.diagnosticInfos = null;
    }

    public HistoryUpdateResult(StatusCode statusCode, StatusCode[] operationResults, DiagnosticInfo[] diagnosticInfos) {
        this.statusCode = statusCode;
        this.operationResults = operationResults;
        this.diagnosticInfos = diagnosticInfos;
    }

    public StatusCode getStatusCode() { return statusCode; }

    @Nullable
    public StatusCode[] getOperationResults() { return operationResults; }

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
            .add("StatusCode", statusCode)
            .add("OperationResults", operationResults)
            .add("DiagnosticInfos", diagnosticInfos)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<HistoryUpdateResult> {

        @Override
        public Class<HistoryUpdateResult> getType() {
            return HistoryUpdateResult.class;
        }

        @Override
        public HistoryUpdateResult decode(UaDecoder decoder) throws UaSerializationException {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            StatusCode[] operationResults = decoder.readArray("OperationResults", decoder::readStatusCode, StatusCode.class);
            DiagnosticInfo[] diagnosticInfos = decoder.readArray("DiagnosticInfos", decoder::readDiagnosticInfo, DiagnosticInfo.class);

            return new HistoryUpdateResult(statusCode, operationResults, diagnosticInfos);
        }

        @Override
        public void encode(HistoryUpdateResult value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeStatusCode("StatusCode", value.statusCode);
            encoder.writeArray("OperationResults", value.operationResults, encoder::writeStatusCode);
            encoder.writeArray("DiagnosticInfos", value.diagnosticInfos, encoder::writeDiagnosticInfo);
        }
    }

}
