/*
 * Copyright (c) 2021 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ProgramDiagnostic2DataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15396");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15397");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15401");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15405");

    private final NodeId createSessionId;

    private final String createClientName;

    private final DateTime invocationCreationTime;

    private final DateTime lastTransitionTime;

    private final String lastMethodCall;

    private final NodeId lastMethodSessionId;

    private final Argument[] lastMethodInputArguments;

    private final Argument[] lastMethodOutputArguments;

    private final Variant[] lastMethodInputValues;

    private final Variant[] lastMethodOutputValues;

    private final DateTime lastMethodCallTime;

    private final StatusResult lastMethodReturnStatus;

    public ProgramDiagnostic2DataType(NodeId createSessionId, String createClientName,
                                      DateTime invocationCreationTime, DateTime lastTransitionTime, String lastMethodCall,
                                      NodeId lastMethodSessionId, Argument[] lastMethodInputArguments,
                                      Argument[] lastMethodOutputArguments, Variant[] lastMethodInputValues,
                                      Variant[] lastMethodOutputValues, DateTime lastMethodCallTime,
                                      StatusResult lastMethodReturnStatus) {
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

    public String getCreateClientName() {
        return createClientName;
    }

    public DateTime getInvocationCreationTime() {
        return invocationCreationTime;
    }

    public DateTime getLastTransitionTime() {
        return lastTransitionTime;
    }

    public String getLastMethodCall() {
        return lastMethodCall;
    }

    public NodeId getLastMethodSessionId() {
        return lastMethodSessionId;
    }

    public Argument[] getLastMethodInputArguments() {
        return lastMethodInputArguments;
    }

    public Argument[] getLastMethodOutputArguments() {
        return lastMethodOutputArguments;
    }

    public Variant[] getLastMethodInputValues() {
        return lastMethodInputValues;
    }

    public Variant[] getLastMethodOutputValues() {
        return lastMethodOutputValues;
    }

    public DateTime getLastMethodCallTime() {
        return lastMethodCallTime;
    }

    public StatusResult getLastMethodReturnStatus() {
        return lastMethodReturnStatus;
    }

    public static final class Codec extends GenericDataTypeCodec<ProgramDiagnostic2DataType> {
        @Override
        public Class<ProgramDiagnostic2DataType> getType() {
            return ProgramDiagnostic2DataType.class;
        }

        @Override
        public ProgramDiagnostic2DataType decode(SerializationContext context, UaDecoder decoder) {
            NodeId createSessionId = decoder.readNodeId("CreateSessionId");
            String createClientName = decoder.readString("CreateClientName");
            DateTime invocationCreationTime = decoder.readDateTime("InvocationCreationTime");
            DateTime lastTransitionTime = decoder.readDateTime("LastTransitionTime");
            String lastMethodCall = decoder.readString("LastMethodCall");
            NodeId lastMethodSessionId = decoder.readNodeId("LastMethodSessionId");
            Argument[] lastMethodInputArguments = (Argument[]) decoder.readStructArray("LastMethodInputArguments", Argument.TYPE_ID);
            Argument[] lastMethodOutputArguments = (Argument[]) decoder.readStructArray("LastMethodOutputArguments", Argument.TYPE_ID);
            Variant[] lastMethodInputValues = decoder.readVariantArray("LastMethodInputValues");
            Variant[] lastMethodOutputValues = decoder.readVariantArray("LastMethodOutputValues");
            DateTime lastMethodCallTime = decoder.readDateTime("LastMethodCallTime");
            StatusResult lastMethodReturnStatus = (StatusResult) decoder.readStruct("LastMethodReturnStatus", StatusResult.TYPE_ID);
            return new ProgramDiagnostic2DataType(createSessionId, createClientName, invocationCreationTime, lastTransitionTime, lastMethodCall, lastMethodSessionId, lastMethodInputArguments, lastMethodOutputArguments, lastMethodInputValues, lastMethodOutputValues, lastMethodCallTime, lastMethodReturnStatus);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ProgramDiagnostic2DataType value) {
            encoder.writeNodeId("CreateSessionId", value.getCreateSessionId());
            encoder.writeString("CreateClientName", value.getCreateClientName());
            encoder.writeDateTime("InvocationCreationTime", value.getInvocationCreationTime());
            encoder.writeDateTime("LastTransitionTime", value.getLastTransitionTime());
            encoder.writeString("LastMethodCall", value.getLastMethodCall());
            encoder.writeNodeId("LastMethodSessionId", value.getLastMethodSessionId());
            encoder.writeStructArray("LastMethodInputArguments", value.getLastMethodInputArguments(), Argument.TYPE_ID);
            encoder.writeStructArray("LastMethodOutputArguments", value.getLastMethodOutputArguments(), Argument.TYPE_ID);
            encoder.writeVariantArray("LastMethodInputValues", value.getLastMethodInputValues());
            encoder.writeVariantArray("LastMethodOutputValues", value.getLastMethodOutputValues());
            encoder.writeDateTime("LastMethodCallTime", value.getLastMethodCallTime());
            encoder.writeStruct("LastMethodReturnStatus", value.getLastMethodReturnStatus(), StatusResult.TYPE_ID);
        }
    }
}
