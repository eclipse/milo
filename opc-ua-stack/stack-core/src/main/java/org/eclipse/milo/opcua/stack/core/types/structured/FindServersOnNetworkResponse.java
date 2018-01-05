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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class FindServersOnNetworkResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.FindServersOnNetworkResponse;
    public static final NodeId BinaryEncodingId = Identifiers.FindServersOnNetworkResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.FindServersOnNetworkResponse_Encoding_DefaultXml;

    protected final ResponseHeader responseHeader;
    protected final DateTime lastCounterResetTime;
    protected final ServerOnNetwork[] servers;

    public FindServersOnNetworkResponse() {
        this.responseHeader = null;
        this.lastCounterResetTime = null;
        this.servers = null;
    }

    public FindServersOnNetworkResponse(ResponseHeader responseHeader, DateTime lastCounterResetTime, ServerOnNetwork[] servers) {
        this.responseHeader = responseHeader;
        this.lastCounterResetTime = lastCounterResetTime;
        this.servers = servers;
    }

    public ResponseHeader getResponseHeader() { return responseHeader; }

    public DateTime getLastCounterResetTime() { return lastCounterResetTime; }

    @Nullable
    public ServerOnNetwork[] getServers() { return servers; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", responseHeader)
            .add("LastCounterResetTime", lastCounterResetTime)
            .add("Servers", servers)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<FindServersOnNetworkResponse> {

        @Override
        public Class<FindServersOnNetworkResponse> getType() {
            return FindServersOnNetworkResponse.class;
        }

        @Override
        public FindServersOnNetworkResponse decode(UaDecoder decoder) throws UaSerializationException {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readBuiltinStruct("ResponseHeader", ResponseHeader.class);
            DateTime lastCounterResetTime = decoder.readDateTime("LastCounterResetTime");
            ServerOnNetwork[] servers =
                decoder.readBuiltinStructArray(
                    "Servers",
                    ServerOnNetwork.class
                );

            return new FindServersOnNetworkResponse(responseHeader, lastCounterResetTime, servers);
        }

        @Override
        public void encode(FindServersOnNetworkResponse value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ResponseHeader", value.responseHeader, ResponseHeader.class);
            encoder.writeDateTime("LastCounterResetTime", value.lastCounterResetTime);
            encoder.writeBuiltinStructArray(
                "Servers",
                value.servers,
                ServerOnNetwork.class
            );
        }
    }

}
