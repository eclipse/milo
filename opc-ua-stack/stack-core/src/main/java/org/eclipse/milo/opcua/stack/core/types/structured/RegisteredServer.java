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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
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

    public LocalizedText[] getServerNames() { return _serverNames; }

    public ApplicationType getServerType() { return _serverType; }

    public String getGatewayServerUri() { return _gatewayServerUri; }

    public String[] getDiscoveryUrls() { return _discoveryUrls; }

    public String getSemaphoreFilePath() { return _semaphoreFilePath; }

    public Boolean getIsOnline() { return _isOnline; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(RegisteredServer registeredServer, UaEncoder encoder) {
        encoder.encodeString("ServerUri", registeredServer._serverUri);
        encoder.encodeString("ProductUri", registeredServer._productUri);
        encoder.encodeArray("ServerNames", registeredServer._serverNames, encoder::encodeLocalizedText);
        encoder.encodeEnumeration("ServerType", registeredServer._serverType);
        encoder.encodeString("GatewayServerUri", registeredServer._gatewayServerUri);
        encoder.encodeArray("DiscoveryUrls", registeredServer._discoveryUrls, encoder::encodeString);
        encoder.encodeString("SemaphoreFilePath", registeredServer._semaphoreFilePath);
        encoder.encodeBoolean("IsOnline", registeredServer._isOnline);
    }

    public static RegisteredServer decode(UaDecoder decoder) {
        String _serverUri = decoder.decodeString("ServerUri");
        String _productUri = decoder.decodeString("ProductUri");
        LocalizedText[] _serverNames = decoder.decodeArray("ServerNames", decoder::decodeLocalizedText, LocalizedText.class);
        ApplicationType _serverType = decoder.decodeEnumeration("ServerType", ApplicationType.class);
        String _gatewayServerUri = decoder.decodeString("GatewayServerUri");
        String[] _discoveryUrls = decoder.decodeArray("DiscoveryUrls", decoder::decodeString, String.class);
        String _semaphoreFilePath = decoder.decodeString("SemaphoreFilePath");
        Boolean _isOnline = decoder.decodeBoolean("IsOnline");

        return new RegisteredServer(_serverUri, _productUri, _serverNames, _serverType, _gatewayServerUri, _discoveryUrls, _semaphoreFilePath, _isOnline);
    }

    static {
        DelegateRegistry.registerEncoder(RegisteredServer::encode, RegisteredServer.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(RegisteredServer::decode, RegisteredServer.class, BinaryEncodingId, XmlEncodingId);
    }

}
