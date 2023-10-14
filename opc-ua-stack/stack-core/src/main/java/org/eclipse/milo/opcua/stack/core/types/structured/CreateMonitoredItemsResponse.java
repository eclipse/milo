/*
 * Copyright (c) 2023 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.12.2/#5.12.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.12.2/#5.12.2.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class CreateMonitoredItemsResponse extends Structure implements UaResponseMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=752");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=754");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=753");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15324");

    private final ResponseHeader responseHeader;

    private final MonitoredItemCreateResult @Nullable [] results;

    private final DiagnosticInfo @Nullable [] diagnosticInfos;

    public CreateMonitoredItemsResponse(ResponseHeader responseHeader,
                                        MonitoredItemCreateResult @Nullable [] results, DiagnosticInfo @Nullable [] diagnosticInfos) {
        this.responseHeader = responseHeader;
        this.results = results;
        this.diagnosticInfos = diagnosticInfos;
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

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public MonitoredItemCreateResult @Nullable [] getResults() {
        return results;
    }

    public DiagnosticInfo @Nullable [] getDiagnosticInfos() {
        return diagnosticInfos;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 754),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ResponseHeader", LocalizedText.NULL_VALUE, new NodeId(0, 392), -1, null, UInteger.valueOf(0), false),
                new StructureField("Results", LocalizedText.NULL_VALUE, new NodeId(0, 746), 1, null, UInteger.valueOf(0), false),
                new StructureField("DiagnosticInfos", LocalizedText.NULL_VALUE, new NodeId(0, 25), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<CreateMonitoredItemsResponse> {
        @Override
        public Class<CreateMonitoredItemsResponse> getType() {
            return CreateMonitoredItemsResponse.class;
        }

        @Override
        public CreateMonitoredItemsResponse decodeType(EncodingContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.decodeStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            MonitoredItemCreateResult[] results = (MonitoredItemCreateResult[]) decoder.decodeStructArray("Results", MonitoredItemCreateResult.TYPE_ID);
            DiagnosticInfo[] diagnosticInfos = decoder.decodeDiagnosticInfoArray("DiagnosticInfos");
            return new CreateMonitoredItemsResponse(responseHeader, results, diagnosticInfos);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               CreateMonitoredItemsResponse value) {
            encoder.encodeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.encodeStructArray("Results", value.getResults(), MonitoredItemCreateResult.TYPE_ID);
            encoder.encodeDiagnosticInfoArray("DiagnosticInfos", value.getDiagnosticInfos());
        }
    }
}
