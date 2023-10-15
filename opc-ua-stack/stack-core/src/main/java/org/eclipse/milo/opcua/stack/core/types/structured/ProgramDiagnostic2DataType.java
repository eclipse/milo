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
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part10/5.2.8">https://reference.opcfoundation.org/v105/Core/docs/Part10/5.2.8</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ProgramDiagnostic2DataType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=24033");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=24034");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=24038");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=24042");

    private final NodeId createSessionId;

    private final @Nullable String createClientName;

    private final DateTime invocationCreationTime;

    private final DateTime lastTransitionTime;

    private final @Nullable String lastMethodCall;

    private final NodeId lastMethodSessionId;

    private final Argument @Nullable [] lastMethodInputArguments;

    private final Argument @Nullable [] lastMethodOutputArguments;

    private final Variant @Nullable [] lastMethodInputValues;

    private final Variant @Nullable [] lastMethodOutputValues;

    private final DateTime lastMethodCallTime;

    private final StatusCode lastMethodReturnStatus;

    public ProgramDiagnostic2DataType(NodeId createSessionId, @Nullable String createClientName,
                                      DateTime invocationCreationTime, DateTime lastTransitionTime, @Nullable String lastMethodCall,
                                      NodeId lastMethodSessionId, Argument @Nullable [] lastMethodInputArguments,
                                      Argument @Nullable [] lastMethodOutputArguments, Variant @Nullable [] lastMethodInputValues,
                                      Variant @Nullable [] lastMethodOutputValues, DateTime lastMethodCallTime,
                                      StatusCode lastMethodReturnStatus) {
        this.createSessionId = createSessionId;
        this.createClientName = createClientName;
        this.invocationCreationTime = invocationCreationTime;
        this.lastTransitionTime = lastTransitionTime;
        this.lastMethodCall = lastMethodCall;
        this.lastMethodSessionId = lastMethodSessionId;
        this.lastMethodInputArguments = lastMethodInputArguments;
        this.lastMethodOutputArguments = lastMethodOutputArguments;
        this.lastMethodInputValues = lastMethodInputValues;
        this.lastMethodOutputValues = lastMethodOutputValues;
        this.lastMethodCallTime = lastMethodCallTime;
        this.lastMethodReturnStatus = lastMethodReturnStatus;
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

    public NodeId getCreateSessionId() {
        return createSessionId;
    }

    public @Nullable String getCreateClientName() {
        return createClientName;
    }

    public DateTime getInvocationCreationTime() {
        return invocationCreationTime;
    }

    public DateTime getLastTransitionTime() {
        return lastTransitionTime;
    }

    public @Nullable String getLastMethodCall() {
        return lastMethodCall;
    }

    public NodeId getLastMethodSessionId() {
        return lastMethodSessionId;
    }

    public Argument @Nullable [] getLastMethodInputArguments() {
        return lastMethodInputArguments;
    }

    public Argument @Nullable [] getLastMethodOutputArguments() {
        return lastMethodOutputArguments;
    }

    public Variant @Nullable [] getLastMethodInputValues() {
        return lastMethodInputValues;
    }

    public Variant @Nullable [] getLastMethodOutputValues() {
        return lastMethodOutputValues;
    }

    public DateTime getLastMethodCallTime() {
        return lastMethodCallTime;
    }

    public StatusCode getLastMethodReturnStatus() {
        return lastMethodReturnStatus;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 24034),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("CreateSessionId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("CreateClientName", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("InvocationCreationTime", LocalizedText.NULL_VALUE, new NodeId(0, 294), -1, null, UInteger.valueOf(0), false),
                new StructureField("LastTransitionTime", LocalizedText.NULL_VALUE, new NodeId(0, 294), -1, null, UInteger.valueOf(0), false),
                new StructureField("LastMethodCall", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("LastMethodSessionId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("LastMethodInputArguments", LocalizedText.NULL_VALUE, new NodeId(0, 296), 1, null, UInteger.valueOf(0), false),
                new StructureField("LastMethodOutputArguments", LocalizedText.NULL_VALUE, new NodeId(0, 296), 1, null, UInteger.valueOf(0), false),
                new StructureField("LastMethodInputValues", LocalizedText.NULL_VALUE, new NodeId(0, 24), 1, null, UInteger.valueOf(0), false),
                new StructureField("LastMethodOutputValues", LocalizedText.NULL_VALUE, new NodeId(0, 24), 1, null, UInteger.valueOf(0), false),
                new StructureField("LastMethodCallTime", LocalizedText.NULL_VALUE, new NodeId(0, 294), -1, null, UInteger.valueOf(0), false),
                new StructureField("LastMethodReturnStatus", LocalizedText.NULL_VALUE, new NodeId(0, 19), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ProgramDiagnostic2DataType> {
        @Override
        public Class<ProgramDiagnostic2DataType> getType() {
            return ProgramDiagnostic2DataType.class;
        }

        @Override
        public ProgramDiagnostic2DataType decodeType(EncodingContext context, UaDecoder decoder) {
            NodeId createSessionId = decoder.decodeNodeId("CreateSessionId");
            String createClientName = decoder.decodeString("CreateClientName");
            DateTime invocationCreationTime = decoder.decodeDateTime("InvocationCreationTime");
            DateTime lastTransitionTime = decoder.decodeDateTime("LastTransitionTime");
            String lastMethodCall = decoder.decodeString("LastMethodCall");
            NodeId lastMethodSessionId = decoder.decodeNodeId("LastMethodSessionId");
            Argument[] lastMethodInputArguments = (Argument[]) decoder.decodeStructArray("LastMethodInputArguments", Argument.TYPE_ID);
            Argument[] lastMethodOutputArguments = (Argument[]) decoder.decodeStructArray("LastMethodOutputArguments", Argument.TYPE_ID);
            Variant[] lastMethodInputValues = decoder.decodeVariantArray("LastMethodInputValues");
            Variant[] lastMethodOutputValues = decoder.decodeVariantArray("LastMethodOutputValues");
            DateTime lastMethodCallTime = decoder.decodeDateTime("LastMethodCallTime");
            StatusCode lastMethodReturnStatus = decoder.decodeStatusCode("LastMethodReturnStatus");
            return new ProgramDiagnostic2DataType(createSessionId, createClientName, invocationCreationTime, lastTransitionTime, lastMethodCall, lastMethodSessionId, lastMethodInputArguments, lastMethodOutputArguments, lastMethodInputValues, lastMethodOutputValues, lastMethodCallTime, lastMethodReturnStatus);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               ProgramDiagnostic2DataType value) {
            encoder.encodeNodeId("CreateSessionId", value.getCreateSessionId());
            encoder.encodeString("CreateClientName", value.getCreateClientName());
            encoder.encodeDateTime("InvocationCreationTime", value.getInvocationCreationTime());
            encoder.encodeDateTime("LastTransitionTime", value.getLastTransitionTime());
            encoder.encodeString("LastMethodCall", value.getLastMethodCall());
            encoder.encodeNodeId("LastMethodSessionId", value.getLastMethodSessionId());
            encoder.encodeStructArray("LastMethodInputArguments", value.getLastMethodInputArguments(), Argument.TYPE_ID);
            encoder.encodeStructArray("LastMethodOutputArguments", value.getLastMethodOutputArguments(), Argument.TYPE_ID);
            encoder.encodeVariantArray("LastMethodInputValues", value.getLastMethodInputValues());
            encoder.encodeVariantArray("LastMethodOutputValues", value.getLastMethodOutputValues());
            encoder.encodeDateTime("LastMethodCallTime", value.getLastMethodCallTime());
            encoder.encodeStatusCode("LastMethodReturnStatus", value.getLastMethodReturnStatus());
        }
    }
}
