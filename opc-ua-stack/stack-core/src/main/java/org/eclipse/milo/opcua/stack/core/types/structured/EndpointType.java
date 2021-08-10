/*
 * Copyright (c) 2021 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class EndpointType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15528");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15671");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15949");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16150");

    private final String endpointUrl;

    private final MessageSecurityMode securityMode;

    private final String securityPolicyUri;

    private final String transportProfileUri;

    public EndpointType(String endpointUrl, MessageSecurityMode securityMode,
                        String securityPolicyUri, String transportProfileUri) {
        this.endpointUrl = endpointUrl;
        this.securityMode = securityMode;
        this.securityPolicyUri = securityPolicyUri;
        this.transportProfileUri = transportProfileUri;
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

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public MessageSecurityMode getSecurityMode() {
        return securityMode;
    }

    public String getSecurityPolicyUri() {
        return securityPolicyUri;
    }

    public String getTransportProfileUri() {
        return transportProfileUri;
    }

    public static final class Codec extends GenericDataTypeCodec<EndpointType> {
        @Override
        public Class<EndpointType> getType() {
            return EndpointType.class;
        }

        @Override
        public EndpointType decode(SerializationContext context, UaDecoder decoder) {
            String endpointUrl = decoder.readString("EndpointUrl");
            MessageSecurityMode securityMode = decoder.readEnum("SecurityMode", MessageSecurityMode.class);
            String securityPolicyUri = decoder.readString("SecurityPolicyUri");
            String transportProfileUri = decoder.readString("TransportProfileUri");
            return new EndpointType(endpointUrl, securityMode, securityPolicyUri, transportProfileUri);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, EndpointType value) {
            encoder.writeString("EndpointUrl", value.getEndpointUrl());
            encoder.writeEnum("SecurityMode", value.getSecurityMode());
            encoder.writeString("SecurityPolicyUri", value.getSecurityPolicyUri());
            encoder.writeString("TransportProfileUri", value.getTransportProfileUri());
        }
    }
}
