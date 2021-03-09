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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ProgramDiagnosticDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=894");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=895");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=896");

    private final NodeId createSessionId;

    private final String createClientName;

    private final DateTime invocationCreationTime;

    private final DateTime lastTransitionTime;

    private final String lastMethodCall;

    private final NodeId lastMethodSessionId;

    private final Argument[] lastMethodInputArguments;

    private final Argument[] lastMethodOutputArguments;

    private final DateTime lastMethodCallTime;

    private final StatusResult lastMethodReturnStatus;

    public ProgramDiagnosticDataType(NodeId createSessionId, String createClientName,
                                     DateTime invocationCreationTime, DateTime lastTransitionTime, String lastMethodCall,
                                     NodeId lastMethodSessionId, Argument[] lastMethodInputArguments,
                                     Argument[] lastMethodOutputArguments, DateTime lastMethodCallTime,
                                     StatusResult lastMethodReturnStatus) {
        this.createSessionId = createSessionId;
        this.createClientName = createClientName;
        this.invocationCreationTime = invocationCreationTime;
        this.lastTransitionTime = lastTransitionTime;
        this.lastMethodCall = lastMethodCall;
        this.lastMethodSessionId = lastMethodSessionId;
        this.lastMethodInputArguments = lastMethodInputArguments;
        this.lastMethodOutputArguments = lastMethodOutputArguments;
        this.lastMethodCallTime = lastMethodCallTime;
        this.lastMethodReturnStatus = lastMethodReturnStatus;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
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

    public DateTime getLastMethodCallTime() {
        return lastMethodCallTime;
    }

    public StatusResult getLastMethodReturnStatus() {
        return lastMethodReturnStatus;
    }

    public static final class Codec extends GenericDataTypeCodec<ProgramDiagnosticDataType> {
        @Override
        public Class<ProgramDiagnosticDataType> getType() {
            return ProgramDiagnosticDataType.class;
        }

        @Override
        public ProgramDiagnosticDataType decode(SerializationContext context, UaDecoder decoder) {
            NodeId createSessionId = decoder.readNodeId("CreateSessionId");
            String createClientName = decoder.readString("CreateClientName");
            DateTime invocationCreationTime = decoder.readDateTime("InvocationCreationTime");
            DateTime lastTransitionTime = decoder.readDateTime("LastTransitionTime");
            String lastMethodCall = decoder.readString("LastMethodCall");
            NodeId lastMethodSessionId = decoder.readNodeId("LastMethodSessionId");
            Argument[] lastMethodInputArguments = (Argument[]) decoder.readStructArray("LastMethodInputArguments", Argument.TYPE_ID);
            Argument[] lastMethodOutputArguments = (Argument[]) decoder.readStructArray("LastMethodOutputArguments", Argument.TYPE_ID);
            DateTime lastMethodCallTime = decoder.readDateTime("LastMethodCallTime");
            StatusResult lastMethodReturnStatus = (StatusResult) decoder.readStruct("LastMethodReturnStatus", StatusResult.TYPE_ID);
            return new ProgramDiagnosticDataType(createSessionId, createClientName, invocationCreationTime, lastTransitionTime, lastMethodCall, lastMethodSessionId, lastMethodInputArguments, lastMethodOutputArguments, lastMethodCallTime, lastMethodReturnStatus);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ProgramDiagnosticDataType value) {
            encoder.writeNodeId("CreateSessionId", value.getCreateSessionId());
            encoder.writeString("CreateClientName", value.getCreateClientName());
            encoder.writeDateTime("InvocationCreationTime", value.getInvocationCreationTime());
            encoder.writeDateTime("LastTransitionTime", value.getLastTransitionTime());
            encoder.writeString("LastMethodCall", value.getLastMethodCall());
            encoder.writeNodeId("LastMethodSessionId", value.getLastMethodSessionId());
            encoder.writeStructArray("LastMethodInputArguments", value.getLastMethodInputArguments(), Argument.TYPE_ID);
            encoder.writeStructArray("LastMethodOutputArguments", value.getLastMethodOutputArguments(), Argument.TYPE_ID);
            encoder.writeDateTime("LastMethodCallTime", value.getLastMethodCallTime());
            encoder.writeStruct("LastMethodReturnStatus", value.getLastMethodReturnStatus(), StatusResult.TYPE_ID);
        }
    }
}
