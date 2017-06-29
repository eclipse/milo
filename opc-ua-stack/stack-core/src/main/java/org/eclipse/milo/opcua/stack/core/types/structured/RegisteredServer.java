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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;

public class RegisteredServer implements UaStructure {

    public static final NodeId TypeId = Identifiers.RegisteredServer;
    public static final NodeId BinaryEncodingId = Identifiers.RegisteredServer_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RegisteredServer_Encoding_DefaultXml;

    protected final String serverUri;
    protected final String productUri;
    protected final LocalizedText[] serverNames;
    protected final ApplicationType serverType;
    protected final String gatewayServerUri;
    protected final String[] discoveryUrls;
    protected final String semaphoreFilePath;
    protected final Boolean isOnline;

    public RegisteredServer() {
        this.serverUri = null;
        this.productUri = null;
        this.serverNames = null;
        this.serverType = null;
        this.gatewayServerUri = null;
        this.discoveryUrls = null;
        this.semaphoreFilePath = null;
        this.isOnline = null;
    }

    public RegisteredServer(String serverUri, String productUri, LocalizedText[] serverNames, ApplicationType serverType, String gatewayServerUri, String[] discoveryUrls, String semaphoreFilePath, Boolean isOnline) {
        this.serverUri = serverUri;
        this.productUri = productUri;
        this.serverNames = serverNames;
        this.serverType = serverType;
        this.gatewayServerUri = gatewayServerUri;
        this.discoveryUrls = discoveryUrls;
        this.semaphoreFilePath = semaphoreFilePath;
        this.isOnline = isOnline;
    }

    public String getServerUri() { return serverUri; }

    public String getProductUri() { return productUri; }

    @Nullable
    public LocalizedText[] getServerNames() { return serverNames; }

    public ApplicationType getServerType() { return serverType; }

    public String getGatewayServerUri() { return gatewayServerUri; }

    @Nullable
    public String[] getDiscoveryUrls() { return discoveryUrls; }

    public String getSemaphoreFilePath() { return semaphoreFilePath; }

    public Boolean getIsOnline() { return isOnline; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ServerUri", serverUri)
            .add("ProductUri", productUri)
            .add("ServerNames", serverNames)
            .add("ServerType", serverType)
            .add("GatewayServerUri", gatewayServerUri)
            .add("DiscoveryUrls", discoveryUrls)
            .add("SemaphoreFilePath", semaphoreFilePath)
            .add("IsOnline", isOnline)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<RegisteredServer> {

        @Override
        public Class<RegisteredServer> getType() {
            return RegisteredServer.class;
        }

        @Override
        public RegisteredServer decode(UaDecoder decoder) throws UaSerializationException {
            String serverUri = decoder.readString("ServerUri");
            String productUri = decoder.readString("ProductUri");
            LocalizedText[] serverNames = decoder.readArray("ServerNames", decoder::readLocalizedText, LocalizedText.class);
            ApplicationType serverType = ApplicationType.from(decoder.readInt32("ServerType"));
            String gatewayServerUri = decoder.readString("GatewayServerUri");
            String[] discoveryUrls = decoder.readArray("DiscoveryUrls", decoder::readString, String.class);
            String semaphoreFilePath = decoder.readString("SemaphoreFilePath");
            Boolean isOnline = decoder.readBoolean("IsOnline");

            return new RegisteredServer(serverUri, productUri, serverNames, serverType, gatewayServerUri, discoveryUrls, semaphoreFilePath, isOnline);
        }

        @Override
        public void encode(RegisteredServer value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeString("ServerUri", value.serverUri);
            encoder.writeString("ProductUri", value.productUri);
            encoder.writeArray("ServerNames", value.serverNames, encoder::writeLocalizedText);
            encoder.writeInt32("ServerType", value.serverType != null ? value.serverType.getValue() : 0);
            encoder.writeString("GatewayServerUri", value.gatewayServerUri);
            encoder.writeArray("DiscoveryUrls", value.discoveryUrls, encoder::writeString);
            encoder.writeString("SemaphoreFilePath", value.semaphoreFilePath);
            encoder.writeBoolean("IsOnline", value.isOnline);
        }
    }

}
