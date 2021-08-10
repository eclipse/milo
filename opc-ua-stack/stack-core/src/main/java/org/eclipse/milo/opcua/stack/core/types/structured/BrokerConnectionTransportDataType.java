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

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class BrokerConnectionTransportDataType extends ConnectionTransportDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15007");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15479");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15579");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15726");

    private final String resourceUri;

    private final String authenticationProfileUri;

    public BrokerConnectionTransportDataType(String resourceUri, String authenticationProfileUri) {
        this.resourceUri = resourceUri;
        this.authenticationProfileUri = authenticationProfileUri;
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

    public String getResourceUri() {
        return resourceUri;
    }

    public String getAuthenticationProfileUri() {
        return authenticationProfileUri;
    }

    public static final class Codec extends GenericDataTypeCodec<BrokerConnectionTransportDataType> {
        @Override
        public Class<BrokerConnectionTransportDataType> getType() {
            return BrokerConnectionTransportDataType.class;
        }

        @Override
        public BrokerConnectionTransportDataType decode(SerializationContext context,
                                                        UaDecoder decoder) {
            String resourceUri = decoder.readString("ResourceUri");
            String authenticationProfileUri = decoder.readString("AuthenticationProfileUri");
            return new BrokerConnectionTransportDataType(resourceUri, authenticationProfileUri);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           BrokerConnectionTransportDataType value) {
            encoder.writeString("ResourceUri", value.getResourceUri());
            encoder.writeString("AuthenticationProfileUri", value.getAuthenticationProfileUri());
        }
    }
}
