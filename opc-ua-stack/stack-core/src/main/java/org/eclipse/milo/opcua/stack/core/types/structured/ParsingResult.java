/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ParsingResult extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=610");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=612");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=611");

    private final StatusCode statusCode;

    private final StatusCode[] dataStatusCodes;

    private final DiagnosticInfo[] dataDiagnosticInfos;

    public ParsingResult(StatusCode statusCode, StatusCode[] dataStatusCodes,
                         DiagnosticInfo[] dataDiagnosticInfos) {
        this.statusCode = statusCode;
        this.dataStatusCodes = dataStatusCodes;
        this.dataDiagnosticInfos = dataDiagnosticInfos;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public StatusCode[] getDataStatusCodes() {
        return dataStatusCodes;
    }

    public DiagnosticInfo[] getDataDiagnosticInfos() {
        return dataDiagnosticInfos;
    }

    public static final class Codec extends GenericDataTypeCodec<ParsingResult> {
        @Override
        public Class<ParsingResult> getType() {
            return ParsingResult.class;
        }

        @Override
        public ParsingResult decode(SerializationContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            StatusCode[] dataStatusCodes = decoder.readStatusCodeArray("DataStatusCodes");
            DiagnosticInfo[] dataDiagnosticInfos = decoder.readDiagnosticInfoArray("DataDiagnosticInfos");
            return new ParsingResult(statusCode, dataStatusCodes, dataDiagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ParsingResult value) {
            encoder.writeStatusCode("StatusCode", value.getStatusCode());
            encoder.writeStatusCodeArray("DataStatusCodes", value.getDataStatusCodes());
            encoder.writeDiagnosticInfoArray("DataDiagnosticInfos", value.getDataDiagnosticInfos());
        }
    }
}
