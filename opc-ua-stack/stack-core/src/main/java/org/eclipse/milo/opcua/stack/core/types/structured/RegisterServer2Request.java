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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class RegisterServer2Request extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12193");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12211");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12199");

    private final RequestHeader requestHeader;

    private final RegisteredServer server;

    private final ExtensionObject[] discoveryConfiguration;

    public RegisterServer2Request(RequestHeader requestHeader, RegisteredServer server,
                                  ExtensionObject[] discoveryConfiguration) {
        this.requestHeader = requestHeader;
        this.server = server;
        this.discoveryConfiguration = discoveryConfiguration;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public RegisteredServer getServer() {
        return server;
    }

    public ExtensionObject[] getDiscoveryConfiguration() {
        return discoveryConfiguration;
    }

    public static final class Codec extends GenericDataTypeCodec<RegisterServer2Request> {
        @Override
        public Class<RegisterServer2Request> getType() {
            return RegisterServer2Request.class;
        }

        @Override
        public RegisterServer2Request decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            RegisteredServer server = (RegisteredServer) decoder.readStruct("Server", RegisteredServer.TYPE_ID);
            ExtensionObject[] discoveryConfiguration = decoder.readExtensionObjectArray("DiscoveryConfiguration");
            return new RegisterServer2Request(requestHeader, server, discoveryConfiguration);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           RegisterServer2Request value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeStruct("Server", value.getServer(), RegisteredServer.TYPE_ID);
            encoder.writeExtensionObjectArray("DiscoveryConfiguration", value.getDiscoveryConfiguration());
        }
    }
}
