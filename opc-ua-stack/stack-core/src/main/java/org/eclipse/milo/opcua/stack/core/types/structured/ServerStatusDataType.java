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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;

public class ServerStatusDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.ServerStatusDataType;
    public static final NodeId BinaryEncodingId = Identifiers.ServerStatusDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ServerStatusDataType_Encoding_DefaultXml;

    protected final DateTime startTime;
    protected final DateTime currentTime;
    protected final ServerState state;
    protected final BuildInfo buildInfo;
    protected final UInteger secondsTillShutdown;
    protected final LocalizedText shutdownReason;

    public ServerStatusDataType() {
        this.startTime = null;
        this.currentTime = null;
        this.state = null;
        this.buildInfo = null;
        this.secondsTillShutdown = null;
        this.shutdownReason = null;
    }

    public ServerStatusDataType(DateTime startTime, DateTime currentTime, ServerState state, BuildInfo buildInfo, UInteger secondsTillShutdown, LocalizedText shutdownReason) {
        this.startTime = startTime;
        this.currentTime = currentTime;
        this.state = state;
        this.buildInfo = buildInfo;
        this.secondsTillShutdown = secondsTillShutdown;
        this.shutdownReason = shutdownReason;
    }

    public DateTime getStartTime() { return startTime; }

    public DateTime getCurrentTime() { return currentTime; }

    public ServerState getState() { return state; }

    public BuildInfo getBuildInfo() { return buildInfo; }

    public UInteger getSecondsTillShutdown() { return secondsTillShutdown; }

    public LocalizedText getShutdownReason() { return shutdownReason; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StartTime", startTime)
            .add("CurrentTime", currentTime)
            .add("State", state)
            .add("BuildInfo", buildInfo)
            .add("SecondsTillShutdown", secondsTillShutdown)
            .add("ShutdownReason", shutdownReason)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ServerStatusDataType> {

        @Override
        public Class<ServerStatusDataType> getType() {
            return ServerStatusDataType.class;
        }

        @Override
        public ServerStatusDataType decode(UaDecoder decoder) throws UaSerializationException {
            DateTime startTime = decoder.readDateTime("StartTime");
            DateTime currentTime = decoder.readDateTime("CurrentTime");
            ServerState state = ServerState.from(decoder.readInt32("State"));
            BuildInfo buildInfo = (BuildInfo) decoder.readBuiltinStruct("BuildInfo", BuildInfo.class);
            UInteger secondsTillShutdown = decoder.readUInt32("SecondsTillShutdown");
            LocalizedText shutdownReason = decoder.readLocalizedText("ShutdownReason");

            return new ServerStatusDataType(startTime, currentTime, state, buildInfo, secondsTillShutdown, shutdownReason);
        }

        @Override
        public void encode(ServerStatusDataType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeDateTime("StartTime", value.startTime);
            encoder.writeDateTime("CurrentTime", value.currentTime);
            encoder.writeInt32("State", value.state != null ? value.state.getValue() : 0);
            encoder.writeBuiltinStruct("BuildInfo", value.buildInfo, BuildInfo.class);
            encoder.writeUInt32("SecondsTillShutdown", value.secondsTillShutdown);
            encoder.writeLocalizedText("ShutdownReason", value.shutdownReason);
        }
    }

}
