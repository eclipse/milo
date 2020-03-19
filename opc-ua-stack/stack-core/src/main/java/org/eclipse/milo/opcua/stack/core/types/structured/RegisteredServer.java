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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class RegisteredServer extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=432");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=433");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=434");

    private final String serverUri;

    private final String productUri;

    private final LocalizedText[] serverNames;

    private final ApplicationType serverType;

    private final String gatewayServerUri;

    private final String[] discoveryUrls;

    private final String semaphoreFilePath;

    private final Boolean isOnline;

    public RegisteredServer(String serverUri, String productUri, LocalizedText[] serverNames,
                            ApplicationType serverType, String gatewayServerUri, String[] discoveryUrls,
                            String semaphoreFilePath, Boolean isOnline) {
        this.serverUri = serverUri;
        this.productUri = productUri;
        this.serverNames = serverNames;
        this.serverType = serverType;
        this.gatewayServerUri = gatewayServerUri;
        this.discoveryUrls = discoveryUrls;
        this.semaphoreFilePath = semaphoreFilePath;
        this.isOnline = isOnline;
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

    public String getServerUri() {
        return serverUri;
    }

    public String getProductUri() {
        return productUri;
    }

    public LocalizedText[] getServerNames() {
        return serverNames;
    }

    public ApplicationType getServerType() {
        return serverType;
    }

    public String getGatewayServerUri() {
        return gatewayServerUri;
    }

    public String[] getDiscoveryUrls() {
        return discoveryUrls;
    }

    public String getSemaphoreFilePath() {
        return semaphoreFilePath;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public static final class Codec extends GenericDataTypeCodec<RegisteredServer> {
        @Override
        public Class<RegisteredServer> getType() {
            return RegisteredServer.class;
        }

        @Override
        public RegisteredServer decode(SerializationContext context, UaDecoder decoder) {
            String serverUri = decoder.readString("ServerUri");
            String productUri = decoder.readString("ProductUri");
            LocalizedText[] serverNames = decoder.readLocalizedTextArray("ServerNames");
            ApplicationType serverType = decoder.readEnum("ServerType", ApplicationType.class);
            String gatewayServerUri = decoder.readString("GatewayServerUri");
            String[] discoveryUrls = decoder.readStringArray("DiscoveryUrls");
            String semaphoreFilePath = decoder.readString("SemaphoreFilePath");
            Boolean isOnline = decoder.readBoolean("IsOnline");
            return new RegisteredServer(serverUri, productUri, serverNames, serverType, gatewayServerUri, discoveryUrls, semaphoreFilePath, isOnline);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, RegisteredServer value) {
            encoder.writeString("ServerUri", value.getServerUri());
            encoder.writeString("ProductUri", value.getProductUri());
            encoder.writeLocalizedTextArray("ServerNames", value.getServerNames());
            encoder.writeEnum("ServerType", value.getServerType());
            encoder.writeString("GatewayServerUri", value.getGatewayServerUri());
            encoder.writeStringArray("DiscoveryUrls", value.getDiscoveryUrls());
            encoder.writeString("SemaphoreFilePath", value.getSemaphoreFilePath());
            encoder.writeBoolean("IsOnline", value.getIsOnline());
        }
    }
}
