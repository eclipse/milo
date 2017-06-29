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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class ProgramDiagnosticDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.ProgramDiagnosticDataType;
    public static final NodeId BinaryEncodingId = Identifiers.ProgramDiagnosticDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ProgramDiagnosticDataType_Encoding_DefaultXml;

    protected final NodeId createSessionId;
    protected final String createClientName;
    protected final DateTime invocationCreationTime;
    protected final DateTime lastTransitionTime;
    protected final String lastMethodCall;
    protected final NodeId lastMethodSessionId;
    protected final Argument[] lastMethodInputArguments;
    protected final Argument[] lastMethodOutputArguments;
    protected final DateTime lastMethodCallTime;
    protected final StatusResult lastMethodReturnStatus;

    public ProgramDiagnosticDataType() {
        this.createSessionId = null;
        this.createClientName = null;
        this.invocationCreationTime = null;
        this.lastTransitionTime = null;
        this.lastMethodCall = null;
        this.lastMethodSessionId = null;
        this.lastMethodInputArguments = null;
        this.lastMethodOutputArguments = null;
        this.lastMethodCallTime = null;
        this.lastMethodReturnStatus = null;
    }

    public ProgramDiagnosticDataType(NodeId createSessionId, String createClientName, DateTime invocationCreationTime, DateTime lastTransitionTime, String lastMethodCall, NodeId lastMethodSessionId, Argument[] lastMethodInputArguments, Argument[] lastMethodOutputArguments, DateTime lastMethodCallTime, StatusResult lastMethodReturnStatus) {
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

    public NodeId getCreateSessionId() { return createSessionId; }

    public String getCreateClientName() { return createClientName; }

    public DateTime getInvocationCreationTime() { return invocationCreationTime; }

    public DateTime getLastTransitionTime() { return lastTransitionTime; }

    public String getLastMethodCall() { return lastMethodCall; }

    public NodeId getLastMethodSessionId() { return lastMethodSessionId; }

    @Nullable
    public Argument[] getLastMethodInputArguments() { return lastMethodInputArguments; }

    @Nullable
    public Argument[] getLastMethodOutputArguments() { return lastMethodOutputArguments; }

    public DateTime getLastMethodCallTime() { return lastMethodCallTime; }

    public StatusResult getLastMethodReturnStatus() { return lastMethodReturnStatus; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("CreateSessionId", createSessionId)
            .add("CreateClientName", createClientName)
            .add("InvocationCreationTime", invocationCreationTime)
            .add("LastTransitionTime", lastTransitionTime)
            .add("LastMethodCall", lastMethodCall)
            .add("LastMethodSessionId", lastMethodSessionId)
            .add("LastMethodInputArguments", lastMethodInputArguments)
            .add("LastMethodOutputArguments", lastMethodOutputArguments)
            .add("LastMethodCallTime", lastMethodCallTime)
            .add("LastMethodReturnStatus", lastMethodReturnStatus)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ProgramDiagnosticDataType> {

        @Override
        public Class<ProgramDiagnosticDataType> getType() {
            return ProgramDiagnosticDataType.class;
        }

        @Override
        public ProgramDiagnosticDataType decode(UaDecoder decoder) throws UaSerializationException {
            NodeId createSessionId = decoder.readNodeId("CreateSessionId");
            String createClientName = decoder.readString("CreateClientName");
            DateTime invocationCreationTime = decoder.readDateTime("InvocationCreationTime");
            DateTime lastTransitionTime = decoder.readDateTime("LastTransitionTime");
            String lastMethodCall = decoder.readString("LastMethodCall");
            NodeId lastMethodSessionId = decoder.readNodeId("LastMethodSessionId");
            Argument[] lastMethodInputArguments =
                decoder.readBuiltinStructArray(
                    "LastMethodInputArguments",
                    Argument.class
                );
            Argument[] lastMethodOutputArguments =
                decoder.readBuiltinStructArray(
                    "LastMethodOutputArguments",
                    Argument.class
                );
            DateTime lastMethodCallTime = decoder.readDateTime("LastMethodCallTime");
            StatusResult lastMethodReturnStatus = (StatusResult) decoder.readBuiltinStruct("LastMethodReturnStatus", StatusResult.class);

            return new ProgramDiagnosticDataType(createSessionId, createClientName, invocationCreationTime, lastTransitionTime, lastMethodCall, lastMethodSessionId, lastMethodInputArguments, lastMethodOutputArguments, lastMethodCallTime, lastMethodReturnStatus);
        }

        @Override
        public void encode(ProgramDiagnosticDataType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("CreateSessionId", value.createSessionId);
            encoder.writeString("CreateClientName", value.createClientName);
            encoder.writeDateTime("InvocationCreationTime", value.invocationCreationTime);
            encoder.writeDateTime("LastTransitionTime", value.lastTransitionTime);
            encoder.writeString("LastMethodCall", value.lastMethodCall);
            encoder.writeNodeId("LastMethodSessionId", value.lastMethodSessionId);
            encoder.writeBuiltinStructArray(
                "LastMethodInputArguments",
                value.lastMethodInputArguments,
                Argument.class
            );
            encoder.writeBuiltinStructArray(
                "LastMethodOutputArguments",
                value.lastMethodOutputArguments,
                Argument.class
            );
            encoder.writeDateTime("LastMethodCallTime", value.lastMethodCallTime);
            encoder.writeBuiltinStruct("LastMethodReturnStatus", value.lastMethodReturnStatus, StatusResult.class);
        }
    }

}
