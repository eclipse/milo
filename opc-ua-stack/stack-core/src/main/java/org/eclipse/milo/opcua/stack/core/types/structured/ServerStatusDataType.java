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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;

@UaDataType("ServerStatusDataType")
public class ServerStatusDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.ServerStatusDataType;
    public static final NodeId BinaryEncodingId = Identifiers.ServerStatusDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ServerStatusDataType_Encoding_DefaultXml;

    protected final DateTime _startTime;
    protected final DateTime _currentTime;
    protected final ServerState _state;
    protected final BuildInfo _buildInfo;
    protected final UInteger _secondsTillShutdown;
    protected final LocalizedText _shutdownReason;

    public ServerStatusDataType() {
        this._startTime = null;
        this._currentTime = null;
        this._state = null;
        this._buildInfo = null;
        this._secondsTillShutdown = null;
        this._shutdownReason = null;
    }

    public ServerStatusDataType(DateTime _startTime, DateTime _currentTime, ServerState _state, BuildInfo _buildInfo, UInteger _secondsTillShutdown, LocalizedText _shutdownReason) {
        this._startTime = _startTime;
        this._currentTime = _currentTime;
        this._state = _state;
        this._buildInfo = _buildInfo;
        this._secondsTillShutdown = _secondsTillShutdown;
        this._shutdownReason = _shutdownReason;
    }

    public DateTime getStartTime() { return _startTime; }

    public DateTime getCurrentTime() { return _currentTime; }

    public ServerState getState() { return _state; }

    public BuildInfo getBuildInfo() { return _buildInfo; }

    public UInteger getSecondsTillShutdown() { return _secondsTillShutdown; }

    public LocalizedText getShutdownReason() { return _shutdownReason; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StartTime", _startTime)
            .add("CurrentTime", _currentTime)
            .add("State", _state)
            .add("BuildInfo", _buildInfo)
            .add("SecondsTillShutdown", _secondsTillShutdown)
            .add("ShutdownReason", _shutdownReason)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ServerStatusDataType> {
        @Override
        public ServerStatusDataType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            DateTime _startTime = reader.readDateTime();
            DateTime _currentTime = reader.readDateTime();
            ServerState _state = ServerState.from(reader.readInt32());
            BuildInfo _buildInfo = (BuildInfo) context.decode(BuildInfo.BinaryEncodingId, reader);
            UInteger _secondsTillShutdown = reader.readUInt32();
            LocalizedText _shutdownReason = reader.readLocalizedText();

            return new ServerStatusDataType(_startTime, _currentTime, _state, _buildInfo, _secondsTillShutdown, _shutdownReason);
        }

        @Override
        public void encode(SerializationContext context, ServerStatusDataType value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeDateTime(value._startTime);
            writer.writeDateTime(value._currentTime);
            writer.writeInt32(value._state != null ? value._state.getValue() : 0);
            context.encode(BuildInfo.BinaryEncodingId, value._buildInfo, writer);
            writer.writeUInt32(value._secondsTillShutdown);
            writer.writeLocalizedText(value._shutdownReason);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ServerStatusDataType> {
        @Override
        public ServerStatusDataType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            DateTime _startTime = reader.readDateTime("StartTime");
            DateTime _currentTime = reader.readDateTime("CurrentTime");
            ServerState _state = ServerState.from(reader.readInt32("State"));
            BuildInfo _buildInfo = (BuildInfo) context.decode(BuildInfo.XmlEncodingId, reader);
            UInteger _secondsTillShutdown = reader.readUInt32("SecondsTillShutdown");
            LocalizedText _shutdownReason = reader.readLocalizedText("ShutdownReason");

            return new ServerStatusDataType(_startTime, _currentTime, _state, _buildInfo, _secondsTillShutdown, _shutdownReason);
        }

        @Override
        public void encode(SerializationContext context, ServerStatusDataType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeDateTime("StartTime", encodable._startTime);
            writer.writeDateTime("CurrentTime", encodable._currentTime);
            writer.writeInt32("State", encodable._state != null ? encodable._state.getValue() : 0);
            context.encode(BuildInfo.XmlEncodingId, encodable._buildInfo, writer);
            writer.writeUInt32("SecondsTillShutdown", encodable._secondsTillShutdown);
            writer.writeLocalizedText("ShutdownReason", encodable._shutdownReason);
        }
    }

}
