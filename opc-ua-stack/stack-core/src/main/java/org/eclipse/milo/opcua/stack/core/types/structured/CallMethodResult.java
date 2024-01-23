/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import java.util.StringJoiner;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.11.2/#5.11.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.11.2/#5.11.2.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
public class CallMethodResult extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=707");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=709");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=708");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15290");

    private final StatusCode statusCode;

    private final StatusCode @Nullable [] inputArgumentResults;

    private final DiagnosticInfo @Nullable [] inputArgumentDiagnosticInfos;

    private final Variant @Nullable [] outputArguments;

    public CallMethodResult(StatusCode statusCode, StatusCode @Nullable [] inputArgumentResults,
                            DiagnosticInfo @Nullable [] inputArgumentDiagnosticInfos,
                            Variant @Nullable [] outputArguments) {
        this.statusCode = statusCode;
        this.inputArgumentResults = inputArgumentResults;
        this.inputArgumentDiagnosticInfos = inputArgumentDiagnosticInfos;
        this.outputArguments = outputArguments;
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

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public StatusCode @Nullable [] getInputArgumentResults() {
        return inputArgumentResults;
    }

    public DiagnosticInfo @Nullable [] getInputArgumentDiagnosticInfos() {
        return inputArgumentDiagnosticInfos;
    }

    public Variant @Nullable [] getOutputArguments() {
        return outputArguments;
    }

    @Override
    public String toString() {
        var joiner = new StringJoiner(", ", CallMethodResult.class.getSimpleName() + "[", "]");
        joiner.add("statusCode=" + getStatusCode());
        joiner.add("inputArgumentResults=" + java.util.Arrays.toString(getInputArgumentResults()));
        joiner.add("inputArgumentDiagnosticInfos=" + java.util.Arrays.toString(getInputArgumentDiagnosticInfos()));
        joiner.add("outputArguments=" + java.util.Arrays.toString(getOutputArguments()));
        return joiner.toString();
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 709),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("StatusCode", LocalizedText.NULL_VALUE, new NodeId(0, 19), -1, null, UInteger.valueOf(0), false),
                new StructureField("InputArgumentResults", LocalizedText.NULL_VALUE, new NodeId(0, 19), 1, null, UInteger.valueOf(0), false),
                new StructureField("InputArgumentDiagnosticInfos", LocalizedText.NULL_VALUE, new NodeId(0, 25), 1, null, UInteger.valueOf(0), false),
                new StructureField("OutputArguments", LocalizedText.NULL_VALUE, new NodeId(0, 24), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<CallMethodResult> {
        @Override
        public Class<CallMethodResult> getType() {
            return CallMethodResult.class;
        }

        @Override
        public CallMethodResult decodeType(EncodingContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.decodeStatusCode("StatusCode");
            StatusCode[] inputArgumentResults = decoder.decodeStatusCodeArray("InputArgumentResults");
            DiagnosticInfo[] inputArgumentDiagnosticInfos = decoder.decodeDiagnosticInfoArray("InputArgumentDiagnosticInfos");
            Variant[] outputArguments = decoder.decodeVariantArray("OutputArguments");
            return new CallMethodResult(statusCode, inputArgumentResults, inputArgumentDiagnosticInfos, outputArguments);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder, CallMethodResult value) {
            encoder.encodeStatusCode("StatusCode", value.getStatusCode());
            encoder.encodeStatusCodeArray("InputArgumentResults", value.getInputArgumentResults());
            encoder.encodeDiagnosticInfoArray("InputArgumentDiagnosticInfos", value.getInputArgumentDiagnosticInfos());
            encoder.encodeVariantArray("OutputArguments", value.getOutputArguments());
        }
    }
}
