/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part18/4.4.2">https://reference.opcfoundation.org/v105/Core/docs/Part18/4.4.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class EndpointType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15528");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15671");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15949");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16150");

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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15671),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("EndpointUrl", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityMode", LocalizedText.NULL_VALUE, new NodeId(0, 302), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityPolicyUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("TransportProfileUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<EndpointType> {
        @Override
        public Class<EndpointType> getType() {
            return EndpointType.class;
        }

        @Override
        public EndpointType decodeType(EncodingContext context, UaDecoder decoder) {
            String endpointUrl = decoder.decodeString("EndpointUrl");
            MessageSecurityMode securityMode = MessageSecurityMode.from(decoder.decodeEnum("SecurityMode"));
            String securityPolicyUri = decoder.decodeString("SecurityPolicyUri");
            String transportProfileUri = decoder.decodeString("TransportProfileUri");
            return new EndpointType(endpointUrl, securityMode, securityPolicyUri, transportProfileUri);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder, EndpointType value) {
            encoder.encodeString("EndpointUrl", value.getEndpointUrl());
            encoder.encodeEnum("SecurityMode", value.getSecurityMode());
            encoder.encodeString("SecurityPolicyUri", value.getSecurityPolicyUri());
            encoder.encodeString("TransportProfileUri", value.getTransportProfileUri());
        }
    }
}
