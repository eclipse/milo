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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("NetworkGroupDataType")
public class NetworkGroupDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.NetworkGroupDataType;
    public static final NodeId BinaryEncodingId = Identifiers.NetworkGroupDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.NetworkGroupDataType_Encoding_DefaultXml;

    protected final String _serverUri;
    protected final EndpointUrlListDataType[] _networkPaths;

    public NetworkGroupDataType() {
        this._serverUri = null;
        this._networkPaths = null;
    }

    public NetworkGroupDataType(String _serverUri, EndpointUrlListDataType[] _networkPaths) {
        this._serverUri = _serverUri;
        this._networkPaths = _networkPaths;
    }

    public String getServerUri() { return _serverUri; }

    @Nullable
    public EndpointUrlListDataType[] getNetworkPaths() { return _networkPaths; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ServerUri", _serverUri)
            .add("NetworkPaths", _networkPaths)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<NetworkGroupDataType> {
        @Override
        public NetworkGroupDataType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _serverUri = reader.readString();
            EndpointUrlListDataType[] _networkPaths =
                reader.readArray(
                    () -> (EndpointUrlListDataType) context.decode(
                        EndpointUrlListDataType.BinaryEncodingId, reader),
                    EndpointUrlListDataType.class
                );

            return new NetworkGroupDataType(_serverUri, _networkPaths);
        }

        @Override
        public void encode(SerializationContext context, NetworkGroupDataType value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(value._serverUri);
            writer.writeArray(
                value._networkPaths,
                e -> context.encode(EndpointUrlListDataType.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<NetworkGroupDataType> {
        @Override
        public NetworkGroupDataType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _serverUri = reader.readString("ServerUri");
            EndpointUrlListDataType[] _networkPaths =
                reader.readArray(
                    "NetworkPaths",
                    f -> (EndpointUrlListDataType) context.decode(
                        EndpointUrlListDataType.XmlEncodingId, reader),
                    EndpointUrlListDataType.class
                );

            return new NetworkGroupDataType(_serverUri, _networkPaths);
        }

        @Override
        public void encode(SerializationContext context, NetworkGroupDataType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("ServerUri", encodable._serverUri);
            writer.writeArray(
                "NetworkPaths",
                encodable._networkPaths,
                (f, e) -> context.encode(EndpointUrlListDataType.XmlEncodingId, e, writer)
            );
        }
    }

}
