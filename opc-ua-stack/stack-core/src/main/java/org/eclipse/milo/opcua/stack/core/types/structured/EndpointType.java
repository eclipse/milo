package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
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
public class EndpointType extends Structure implements UaStructure {
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
        public EndpointType decode(SerializationContext context, UaDecoder decoder) {
            String endpointUrl = decoder.readString("EndpointUrl");
            MessageSecurityMode securityMode = (MessageSecurityMode) decoder.readEnum("SecurityMode", MessageSecurityMode.class);
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
