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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;

public class RedundantServerDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.RedundantServerDataType;
    public static final NodeId BinaryEncodingId = Identifiers.RedundantServerDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RedundantServerDataType_Encoding_DefaultXml;

    protected final String serverId;
    protected final UByte serviceLevel;
    protected final ServerState serverState;

    public RedundantServerDataType() {
        this.serverId = null;
        this.serviceLevel = null;
        this.serverState = null;
    }

    public RedundantServerDataType(String serverId, UByte serviceLevel, ServerState serverState) {
        this.serverId = serverId;
        this.serviceLevel = serviceLevel;
        this.serverState = serverState;
    }

    public String getServerId() { return serverId; }

    public UByte getServiceLevel() { return serviceLevel; }

    public ServerState getServerState() { return serverState; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ServerId", serverId)
            .add("ServiceLevel", serviceLevel)
            .add("ServerState", serverState)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<RedundantServerDataType> {

        @Override
        public Class<RedundantServerDataType> getType() {
            return RedundantServerDataType.class;
        }

        @Override
        public RedundantServerDataType decode(UaDecoder decoder) throws UaSerializationException {
            String serverId = decoder.readString("ServerId");
            UByte serviceLevel = decoder.readByte("ServiceLevel");
            ServerState serverState = ServerState.from(decoder.readInt32("ServerState"));

            return new RedundantServerDataType(serverId, serviceLevel, serverState);
        }

        @Override
        public void encode(RedundantServerDataType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeString("ServerId", value.serverId);
            encoder.writeByte("ServiceLevel", value.serviceLevel);
            encoder.writeInt32("ServerState", value.serverState != null ? value.serverState.getValue() : 0);
        }
    }

}
