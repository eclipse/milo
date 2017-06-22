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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;

@UaDataType("RegisteredServer")
public class RegisteredServer implements UaStructure {

    public static final NodeId TypeId = Identifiers.RegisteredServer;
    public static final NodeId BinaryEncodingId = Identifiers.RegisteredServer_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RegisteredServer_Encoding_DefaultXml;

    protected final String _serverUri;
    protected final String _productUri;
    protected final LocalizedText[] _serverNames;
    protected final ApplicationType _serverType;
    protected final String _gatewayServerUri;
    protected final String[] _discoveryUrls;
    protected final String _semaphoreFilePath;
    protected final Boolean _isOnline;

    public RegisteredServer() {
        this._serverUri = null;
        this._productUri = null;
        this._serverNames = null;
        this._serverType = null;
        this._gatewayServerUri = null;
        this._discoveryUrls = null;
        this._semaphoreFilePath = null;
        this._isOnline = null;
    }

    public RegisteredServer(String _serverUri, String _productUri, LocalizedText[] _serverNames, ApplicationType _serverType, String _gatewayServerUri, String[] _discoveryUrls, String _semaphoreFilePath, Boolean _isOnline) {
        this._serverUri = _serverUri;
        this._productUri = _productUri;
        this._serverNames = _serverNames;
        this._serverType = _serverType;
        this._gatewayServerUri = _gatewayServerUri;
        this._discoveryUrls = _discoveryUrls;
        this._semaphoreFilePath = _semaphoreFilePath;
        this._isOnline = _isOnline;
    }

    public String getServerUri() { return _serverUri; }

    public String getProductUri() { return _productUri; }

    @Nullable
    public LocalizedText[] getServerNames() { return _serverNames; }

    public ApplicationType getServerType() { return _serverType; }

    public String getGatewayServerUri() { return _gatewayServerUri; }

    @Nullable
    public String[] getDiscoveryUrls() { return _discoveryUrls; }

    public String getSemaphoreFilePath() { return _semaphoreFilePath; }

    public Boolean getIsOnline() { return _isOnline; }

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
            .add("ProductUri", _productUri)
            .add("ServerNames", _serverNames)
            .add("ServerType", _serverType)
            .add("GatewayServerUri", _gatewayServerUri)
            .add("DiscoveryUrls", _discoveryUrls)
            .add("SemaphoreFilePath", _semaphoreFilePath)
            .add("IsOnline", _isOnline)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<RegisteredServer> {
        @Override
        public RegisteredServer decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _serverUri = reader.readString();
            String _productUri = reader.readString();
            LocalizedText[] _serverNames = reader.readArray(reader::readLocalizedText, LocalizedText.class);
            ApplicationType _serverType = ApplicationType.from(reader.readInt32());
            String _gatewayServerUri = reader.readString();
            String[] _discoveryUrls = reader.readArray(reader::readString, String.class);
            String _semaphoreFilePath = reader.readString();
            Boolean _isOnline = reader.readBoolean();

            return new RegisteredServer(_serverUri, _productUri, _serverNames, _serverType, _gatewayServerUri, _discoveryUrls, _semaphoreFilePath, _isOnline);
        }

        @Override
        public void encode(SerializationContext context, RegisteredServer value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(value._serverUri);
            writer.writeString(value._productUri);
            writer.writeArray(value._serverNames, writer::writeLocalizedText);
            writer.writeInt32(value._serverType != null ? value._serverType.getValue() : 0);
            writer.writeString(value._gatewayServerUri);
            writer.writeArray(value._discoveryUrls, writer::writeString);
            writer.writeString(value._semaphoreFilePath);
            writer.writeBoolean(value._isOnline);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<RegisteredServer> {
        @Override
        public RegisteredServer decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _serverUri = reader.readString("ServerUri");
            String _productUri = reader.readString("ProductUri");
            LocalizedText[] _serverNames = reader.readArray("ServerNames", reader::readLocalizedText, LocalizedText.class);
            ApplicationType _serverType = ApplicationType.from(reader.readInt32("ServerType"));
            String _gatewayServerUri = reader.readString("GatewayServerUri");
            String[] _discoveryUrls = reader.readArray("DiscoveryUrls", reader::readString, String.class);
            String _semaphoreFilePath = reader.readString("SemaphoreFilePath");
            Boolean _isOnline = reader.readBoolean("IsOnline");

            return new RegisteredServer(_serverUri, _productUri, _serverNames, _serverType, _gatewayServerUri, _discoveryUrls, _semaphoreFilePath, _isOnline);
        }

        @Override
        public void encode(SerializationContext context, RegisteredServer encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("ServerUri", encodable._serverUri);
            writer.writeString("ProductUri", encodable._productUri);
            writer.writeArray("ServerNames", encodable._serverNames, writer::writeLocalizedText);
            writer.writeInt32("ServerType", encodable._serverType != null ? encodable._serverType.getValue() : 0);
            writer.writeString("GatewayServerUri", encodable._gatewayServerUri);
            writer.writeArray("DiscoveryUrls", encodable._discoveryUrls, writer::writeString);
            writer.writeString("SemaphoreFilePath", encodable._semaphoreFilePath);
            writer.writeBoolean("IsOnline", encodable._isOnline);
        }
    }

}
