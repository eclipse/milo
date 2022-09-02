package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.13.5/#5.13.5.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.13.5/#5.13.5.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class PublishRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=824");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=826");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=825");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15352");

    private final RequestHeader requestHeader;

    private final SubscriptionAcknowledgement[] subscriptionAcknowledgements;

    public PublishRequest(RequestHeader requestHeader,
                          SubscriptionAcknowledgement[] subscriptionAcknowledgements) {
        this.requestHeader = requestHeader;
        this.subscriptionAcknowledgements = subscriptionAcknowledgements;
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

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public SubscriptionAcknowledgement[] getSubscriptionAcknowledgements() {
        return subscriptionAcknowledgements;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 826),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("SubscriptionAcknowledgements", LocalizedText.NULL_VALUE, new NodeId(0, 821), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<PublishRequest> {
        @Override
        public Class<PublishRequest> getType() {
            return PublishRequest.class;
        }

        @Override
        public PublishRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            SubscriptionAcknowledgement[] subscriptionAcknowledgements = (SubscriptionAcknowledgement[]) decoder.readStructArray("SubscriptionAcknowledgements", SubscriptionAcknowledgement.TYPE_ID);
            return new PublishRequest(requestHeader, subscriptionAcknowledgements);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, PublishRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeStructArray("SubscriptionAcknowledgements", value.getSubscriptionAcknowledgements(), SubscriptionAcknowledgement.TYPE_ID);
        }
    }
}
