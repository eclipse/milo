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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("ProgramDiagnosticDataType")
public class ProgramDiagnosticDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.ProgramDiagnosticDataType;
    public static final NodeId BinaryEncodingId = Identifiers.ProgramDiagnosticDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ProgramDiagnosticDataType_Encoding_DefaultXml;

    protected final NodeId _createSessionId;
    protected final String _createClientName;
    protected final DateTime _invocationCreationTime;
    protected final DateTime _lastTransitionTime;
    protected final String _lastMethodCall;
    protected final NodeId _lastMethodSessionId;
    protected final Argument[] _lastMethodInputArguments;
    protected final Argument[] _lastMethodOutputArguments;
    protected final DateTime _lastMethodCallTime;
    protected final StatusResult _lastMethodReturnStatus;

    public ProgramDiagnosticDataType() {
        this._createSessionId = null;
        this._createClientName = null;
        this._invocationCreationTime = null;
        this._lastTransitionTime = null;
        this._lastMethodCall = null;
        this._lastMethodSessionId = null;
        this._lastMethodInputArguments = null;
        this._lastMethodOutputArguments = null;
        this._lastMethodCallTime = null;
        this._lastMethodReturnStatus = null;
    }

    public ProgramDiagnosticDataType(NodeId _createSessionId, String _createClientName, DateTime _invocationCreationTime, DateTime _lastTransitionTime, String _lastMethodCall, NodeId _lastMethodSessionId, Argument[] _lastMethodInputArguments, Argument[] _lastMethodOutputArguments, DateTime _lastMethodCallTime, StatusResult _lastMethodReturnStatus) {
        this._createSessionId = _createSessionId;
        this._createClientName = _createClientName;
        this._invocationCreationTime = _invocationCreationTime;
        this._lastTransitionTime = _lastTransitionTime;
        this._lastMethodCall = _lastMethodCall;
        this._lastMethodSessionId = _lastMethodSessionId;
        this._lastMethodInputArguments = _lastMethodInputArguments;
        this._lastMethodOutputArguments = _lastMethodOutputArguments;
        this._lastMethodCallTime = _lastMethodCallTime;
        this._lastMethodReturnStatus = _lastMethodReturnStatus;
    }

    public NodeId getCreateSessionId() { return _createSessionId; }

    public String getCreateClientName() { return _createClientName; }

    public DateTime getInvocationCreationTime() { return _invocationCreationTime; }

    public DateTime getLastTransitionTime() { return _lastTransitionTime; }

    public String getLastMethodCall() { return _lastMethodCall; }

    public NodeId getLastMethodSessionId() { return _lastMethodSessionId; }

    @Nullable
    public Argument[] getLastMethodInputArguments() { return _lastMethodInputArguments; }

    @Nullable
    public Argument[] getLastMethodOutputArguments() { return _lastMethodOutputArguments; }

    public DateTime getLastMethodCallTime() { return _lastMethodCallTime; }

    public StatusResult getLastMethodReturnStatus() { return _lastMethodReturnStatus; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("CreateSessionId", _createSessionId)
            .add("CreateClientName", _createClientName)
            .add("InvocationCreationTime", _invocationCreationTime)
            .add("LastTransitionTime", _lastTransitionTime)
            .add("LastMethodCall", _lastMethodCall)
            .add("LastMethodSessionId", _lastMethodSessionId)
            .add("LastMethodInputArguments", _lastMethodInputArguments)
            .add("LastMethodOutputArguments", _lastMethodOutputArguments)
            .add("LastMethodCallTime", _lastMethodCallTime)
            .add("LastMethodReturnStatus", _lastMethodReturnStatus)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ProgramDiagnosticDataType> {
        @Override
        public ProgramDiagnosticDataType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _createSessionId = reader.readNodeId();
            String _createClientName = reader.readString();
            DateTime _invocationCreationTime = reader.readDateTime();
            DateTime _lastTransitionTime = reader.readDateTime();
            String _lastMethodCall = reader.readString();
            NodeId _lastMethodSessionId = reader.readNodeId();
            Argument[] _lastMethodInputArguments =
                reader.readArray(
                    () -> (Argument) context.decode(
                        Argument.BinaryEncodingId, reader),
                    Argument.class
                );
            Argument[] _lastMethodOutputArguments =
                reader.readArray(
                    () -> (Argument) context.decode(
                        Argument.BinaryEncodingId, reader),
                    Argument.class
                );
            DateTime _lastMethodCallTime = reader.readDateTime();
            StatusResult _lastMethodReturnStatus = (StatusResult) context.decode(StatusResult.BinaryEncodingId, reader);

            return new ProgramDiagnosticDataType(_createSessionId, _createClientName, _invocationCreationTime, _lastTransitionTime, _lastMethodCall, _lastMethodSessionId, _lastMethodInputArguments, _lastMethodOutputArguments, _lastMethodCallTime, _lastMethodReturnStatus);
        }

        @Override
        public void encode(SerializationContext context, ProgramDiagnosticDataType encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(encodable._createSessionId);
            writer.writeString(encodable._createClientName);
            writer.writeDateTime(encodable._invocationCreationTime);
            writer.writeDateTime(encodable._lastTransitionTime);
            writer.writeString(encodable._lastMethodCall);
            writer.writeNodeId(encodable._lastMethodSessionId);
            writer.writeArray(
                encodable._lastMethodInputArguments,
                e -> context.encode(Argument.BinaryEncodingId, e, writer)
            );
            writer.writeArray(
                encodable._lastMethodOutputArguments,
                e -> context.encode(Argument.BinaryEncodingId, e, writer)
            );
            writer.writeDateTime(encodable._lastMethodCallTime);
            context.encode(StatusResult.BinaryEncodingId, encodable._lastMethodReturnStatus, writer);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ProgramDiagnosticDataType> {
        @Override
        public ProgramDiagnosticDataType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _createSessionId = reader.readNodeId("CreateSessionId");
            String _createClientName = reader.readString("CreateClientName");
            DateTime _invocationCreationTime = reader.readDateTime("InvocationCreationTime");
            DateTime _lastTransitionTime = reader.readDateTime("LastTransitionTime");
            String _lastMethodCall = reader.readString("LastMethodCall");
            NodeId _lastMethodSessionId = reader.readNodeId("LastMethodSessionId");
            Argument[] _lastMethodInputArguments =
                reader.readArray(
                    "LastMethodInputArguments",
                    f -> (Argument) context.decode(
                        Argument.XmlEncodingId, reader),
                    Argument.class
                );
            Argument[] _lastMethodOutputArguments =
                reader.readArray(
                    "LastMethodOutputArguments",
                    f -> (Argument) context.decode(
                        Argument.XmlEncodingId, reader),
                    Argument.class
                );
            DateTime _lastMethodCallTime = reader.readDateTime("LastMethodCallTime");
            StatusResult _lastMethodReturnStatus = (StatusResult) context.decode(StatusResult.XmlEncodingId, reader);

            return new ProgramDiagnosticDataType(_createSessionId, _createClientName, _invocationCreationTime, _lastTransitionTime, _lastMethodCall, _lastMethodSessionId, _lastMethodInputArguments, _lastMethodOutputArguments, _lastMethodCallTime, _lastMethodReturnStatus);
        }

        @Override
        public void encode(SerializationContext context, ProgramDiagnosticDataType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("CreateSessionId", encodable._createSessionId);
            writer.writeString("CreateClientName", encodable._createClientName);
            writer.writeDateTime("InvocationCreationTime", encodable._invocationCreationTime);
            writer.writeDateTime("LastTransitionTime", encodable._lastTransitionTime);
            writer.writeString("LastMethodCall", encodable._lastMethodCall);
            writer.writeNodeId("LastMethodSessionId", encodable._lastMethodSessionId);
            writer.writeArray(
                "LastMethodInputArguments",
                encodable._lastMethodInputArguments,
                (f, e) -> context.encode(Argument.XmlEncodingId, e, writer)
            );
            writer.writeArray(
                "LastMethodOutputArguments",
                encodable._lastMethodOutputArguments,
                (f, e) -> context.encode(Argument.XmlEncodingId, e, writer)
            );
            writer.writeDateTime("LastMethodCallTime", encodable._lastMethodCallTime);
            context.encode(StatusResult.XmlEncodingId, encodable._lastMethodReturnStatus, writer);
        }
    }

}
