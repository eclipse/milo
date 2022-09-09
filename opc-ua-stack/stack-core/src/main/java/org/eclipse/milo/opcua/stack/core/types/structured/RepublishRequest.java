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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.13.6/#5.13.6.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.13.6/#5.13.6.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class RepublishRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=830");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=832");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=831");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15354");

    private final RequestHeader requestHeader;

    private final UInteger subscriptionId;

    private final UInteger retransmitSequenceNumber;

    public RepublishRequest(RequestHeader requestHeader, UInteger subscriptionId,
                            UInteger retransmitSequenceNumber) {
        this.requestHeader = requestHeader;
        this.subscriptionId = subscriptionId;
        this.retransmitSequenceNumber = retransmitSequenceNumber;
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

    public UInteger getSubscriptionId() {
        return subscriptionId;
    }

    public UInteger getRetransmitSequenceNumber() {
        return retransmitSequenceNumber;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 832),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("SubscriptionId", LocalizedText.NULL_VALUE, new NodeId(0, 288), -1, null, UInteger.valueOf(0), false),
                new StructureField("RetransmitSequenceNumber", LocalizedText.NULL_VALUE, new NodeId(0, 289), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<RepublishRequest> {
        @Override
        public Class<RepublishRequest> getType() {
            return RepublishRequest.class;
        }

        @Override
        public RepublishRequest decodeType(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            UInteger retransmitSequenceNumber = decoder.readUInt32("RetransmitSequenceNumber");
            return new RepublishRequest(requestHeader, subscriptionId, retransmitSequenceNumber);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               RepublishRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeUInt32("SubscriptionId", value.getSubscriptionId());
            encoder.writeUInt32("RetransmitSequenceNumber", value.getRetransmitSequenceNumber());
        }
    }
}
