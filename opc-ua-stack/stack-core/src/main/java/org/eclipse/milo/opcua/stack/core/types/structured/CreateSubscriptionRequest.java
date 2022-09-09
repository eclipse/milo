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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.13.2/#5.13.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.13.2/#5.13.2.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class CreateSubscriptionRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=785");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=787");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=786");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15337");

    private final RequestHeader requestHeader;

    private final Double requestedPublishingInterval;

    private final UInteger requestedLifetimeCount;

    private final UInteger requestedMaxKeepAliveCount;

    private final UInteger maxNotificationsPerPublish;

    private final Boolean publishingEnabled;

    private final UByte priority;

    public CreateSubscriptionRequest(RequestHeader requestHeader, Double requestedPublishingInterval,
                                     UInteger requestedLifetimeCount, UInteger requestedMaxKeepAliveCount,
                                     UInteger maxNotificationsPerPublish, Boolean publishingEnabled, UByte priority) {
        this.requestHeader = requestHeader;
        this.requestedPublishingInterval = requestedPublishingInterval;
        this.requestedLifetimeCount = requestedLifetimeCount;
        this.requestedMaxKeepAliveCount = requestedMaxKeepAliveCount;
        this.maxNotificationsPerPublish = maxNotificationsPerPublish;
        this.publishingEnabled = publishingEnabled;
        this.priority = priority;
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

    public Double getRequestedPublishingInterval() {
        return requestedPublishingInterval;
    }

    public UInteger getRequestedLifetimeCount() {
        return requestedLifetimeCount;
    }

    public UInteger getRequestedMaxKeepAliveCount() {
        return requestedMaxKeepAliveCount;
    }

    public UInteger getMaxNotificationsPerPublish() {
        return maxNotificationsPerPublish;
    }

    public Boolean getPublishingEnabled() {
        return publishingEnabled;
    }

    public UByte getPriority() {
        return priority;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 787),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("RequestedPublishingInterval", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("RequestedLifetimeCount", LocalizedText.NULL_VALUE, new NodeId(0, 289), -1, null, UInteger.valueOf(0), false),
                new StructureField("RequestedMaxKeepAliveCount", LocalizedText.NULL_VALUE, new NodeId(0, 289), -1, null, UInteger.valueOf(0), false),
                new StructureField("MaxNotificationsPerPublish", LocalizedText.NULL_VALUE, new NodeId(0, 289), -1, null, UInteger.valueOf(0), false),
                new StructureField("PublishingEnabled", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("Priority", LocalizedText.NULL_VALUE, new NodeId(0, 3), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<CreateSubscriptionRequest> {
        @Override
        public Class<CreateSubscriptionRequest> getType() {
            return CreateSubscriptionRequest.class;
        }

        @Override
        public CreateSubscriptionRequest decodeType(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            Double requestedPublishingInterval = decoder.readDouble("RequestedPublishingInterval");
            UInteger requestedLifetimeCount = decoder.readUInt32("RequestedLifetimeCount");
            UInteger requestedMaxKeepAliveCount = decoder.readUInt32("RequestedMaxKeepAliveCount");
            UInteger maxNotificationsPerPublish = decoder.readUInt32("MaxNotificationsPerPublish");
            Boolean publishingEnabled = decoder.readBoolean("PublishingEnabled");
            UByte priority = decoder.readByte("Priority");
            return new CreateSubscriptionRequest(requestHeader, requestedPublishingInterval, requestedLifetimeCount, requestedMaxKeepAliveCount, maxNotificationsPerPublish, publishingEnabled, priority);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               CreateSubscriptionRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeDouble("RequestedPublishingInterval", value.getRequestedPublishingInterval());
            encoder.writeUInt32("RequestedLifetimeCount", value.getRequestedLifetimeCount());
            encoder.writeUInt32("RequestedMaxKeepAliveCount", value.getRequestedMaxKeepAliveCount());
            encoder.writeUInt32("MaxNotificationsPerPublish", value.getMaxNotificationsPerPublish());
            encoder.writeBoolean("PublishingEnabled", value.getPublishingEnabled());
            encoder.writeByte("Priority", value.getPriority());
        }
    }
}
