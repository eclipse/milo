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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ServerStatusDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=862");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=863");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=864");

    private final DateTime startTime;

    private final DateTime currentTime;

    private final ServerState state;

    private final BuildInfo buildInfo;

    private final UInteger secondsTillShutdown;

    private final LocalizedText shutdownReason;

    public ServerStatusDataType(DateTime startTime, DateTime currentTime, ServerState state,
                                BuildInfo buildInfo, UInteger secondsTillShutdown, LocalizedText shutdownReason) {
        this.startTime = startTime;
        this.currentTime = currentTime;
        this.state = state;
        this.buildInfo = buildInfo;
        this.secondsTillShutdown = secondsTillShutdown;
        this.shutdownReason = shutdownReason;
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

    public DateTime getStartTime() {
        return startTime;
    }

    public DateTime getCurrentTime() {
        return currentTime;
    }

    public ServerState getState() {
        return state;
    }

    public BuildInfo getBuildInfo() {
        return buildInfo;
    }

    public UInteger getSecondsTillShutdown() {
        return secondsTillShutdown;
    }

    public LocalizedText getShutdownReason() {
        return shutdownReason;
    }

    public static final class Codec extends GenericDataTypeCodec<ServerStatusDataType> {
        @Override
        public Class<ServerStatusDataType> getType() {
            return ServerStatusDataType.class;
        }

        @Override
        public ServerStatusDataType decode(SerializationContext context, UaDecoder decoder) {
            DateTime startTime = decoder.readDateTime("StartTime");
            DateTime currentTime = decoder.readDateTime("CurrentTime");
            ServerState state = decoder.readEnum("State", ServerState.class);
            BuildInfo buildInfo = (BuildInfo) decoder.readStruct("BuildInfo", BuildInfo.TYPE_ID);
            UInteger secondsTillShutdown = decoder.readUInt32("SecondsTillShutdown");
            LocalizedText shutdownReason = decoder.readLocalizedText("ShutdownReason");
            return new ServerStatusDataType(startTime, currentTime, state, buildInfo, secondsTillShutdown, shutdownReason);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ServerStatusDataType value) {
            encoder.writeDateTime("StartTime", value.getStartTime());
            encoder.writeDateTime("CurrentTime", value.getCurrentTime());
            encoder.writeEnum("State", value.getState());
            encoder.writeStruct("BuildInfo", value.getBuildInfo(), BuildInfo.TYPE_ID);
            encoder.writeUInt32("SecondsTillShutdown", value.getSecondsTillShutdown());
            encoder.writeLocalizedText("ShutdownReason", value.getShutdownReason());
        }
    }
}
