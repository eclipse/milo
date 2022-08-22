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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.13.7/#5.13.7.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.13.7/#5.13.7.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class TransferSubscriptionsRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=839");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=841");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=840");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15357");

    private final RequestHeader requestHeader;

    private final UInteger[] subscriptionIds;

    private final Boolean sendInitialValues;

    public TransferSubscriptionsRequest(RequestHeader requestHeader, UInteger[] subscriptionIds,
                                        Boolean sendInitialValues) {
        this.requestHeader = requestHeader;
        this.subscriptionIds = subscriptionIds;
        this.sendInitialValues = sendInitialValues;
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

    public UInteger[] getSubscriptionIds() {
        return subscriptionIds;
    }

    public Boolean getSendInitialValues() {
        return sendInitialValues;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 841),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("SubscriptionIds", LocalizedText.NULL_VALUE, new NodeId(0, 288), 1, null, UInteger.valueOf(0), false),
                new StructureField("SendInitialValues", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<TransferSubscriptionsRequest> {
        @Override
        public Class<TransferSubscriptionsRequest> getType() {
            return TransferSubscriptionsRequest.class;
        }

        @Override
        public TransferSubscriptionsRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            UInteger[] subscriptionIds = decoder.readUInt32Array("SubscriptionIds");
            Boolean sendInitialValues = decoder.readBoolean("SendInitialValues");
            return new TransferSubscriptionsRequest(requestHeader, subscriptionIds, sendInitialValues);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           TransferSubscriptionsRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeUInt32Array("SubscriptionIds", value.getSubscriptionIds());
            encoder.writeBoolean("SendInitialValues", value.getSendInitialValues());
        }
    }
}
