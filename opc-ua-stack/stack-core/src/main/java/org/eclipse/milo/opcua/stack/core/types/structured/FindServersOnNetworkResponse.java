/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
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

@UaDataType("FindServersOnNetworkResponse")
public class FindServersOnNetworkResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.FindServersOnNetworkResponse;
    public static final NodeId BinaryEncodingId = Identifiers.FindServersOnNetworkResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.FindServersOnNetworkResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final DateTime _lastCounterResetTime;
    protected final ServerOnNetwork[] _servers;

    public FindServersOnNetworkResponse() {
        this._responseHeader = null;
        this._lastCounterResetTime = null;
        this._servers = null;
    }

    public FindServersOnNetworkResponse(ResponseHeader _responseHeader, DateTime _lastCounterResetTime, ServerOnNetwork[] _servers) {
        this._responseHeader = _responseHeader;
        this._lastCounterResetTime = _lastCounterResetTime;
        this._servers = _servers;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public DateTime getLastCounterResetTime() { return _lastCounterResetTime; }

    @Nullable
    public ServerOnNetwork[] getServers() { return _servers; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", _responseHeader)
            .add("LastCounterResetTime", _lastCounterResetTime)
            .add("Servers", _servers)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<FindServersOnNetworkResponse> {
        @Override
        public FindServersOnNetworkResponse decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", reader);
            DateTime _lastCounterResetTime = reader.readDateTime();
            ServerOnNetwork[] _servers =
                reader.readArray(
                    () -> (ServerOnNetwork) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ServerOnNetwork", reader),
                    ServerOnNetwork.class
                );

            return new FindServersOnNetworkResponse(_responseHeader, _lastCounterResetTime, _servers);
        }

        @Override
        public void encode(SerializationContext context, FindServersOnNetworkResponse encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", encodable._responseHeader, writer);
            writer.writeDateTime(encodable._lastCounterResetTime);
            writer.writeArray(
                encodable._servers,
                e -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ServerOnNetwork", e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<FindServersOnNetworkResponse> {
        @Override
        public FindServersOnNetworkResponse decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", reader);
            DateTime _lastCounterResetTime = reader.readDateTime("LastCounterResetTime");
            ServerOnNetwork[] _servers =
                reader.readArray(
                    "Servers",
                    f -> (ServerOnNetwork) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ServerOnNetwork", reader),
                    ServerOnNetwork.class
                );

            return new FindServersOnNetworkResponse(_responseHeader, _lastCounterResetTime, _servers);
        }

        @Override
        public void encode(SerializationContext context, FindServersOnNetworkResponse encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", encodable._responseHeader, writer);
            writer.writeDateTime("LastCounterResetTime", encodable._lastCounterResetTime);
            writer.writeArray(
                "Servers",
                encodable._servers,
                (f, e) -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ServerOnNetwork", e, writer)
            );
        }
    }

}
