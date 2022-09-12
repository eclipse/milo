package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.12.2/#5.12.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.12.2/#5.12.2.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class CreateMonitoredItemsRequest extends Structure implements UaRequestMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=749");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=751");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=750");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15323");

    private final RequestHeader requestHeader;

    private final UInteger subscriptionId;

    private final TimestampsToReturn timestampsToReturn;

    private final MonitoredItemCreateRequest[] itemsToCreate;

    public CreateMonitoredItemsRequest(RequestHeader requestHeader, UInteger subscriptionId,
                                       TimestampsToReturn timestampsToReturn, MonitoredItemCreateRequest[] itemsToCreate) {
        this.requestHeader = requestHeader;
        this.subscriptionId = subscriptionId;
        this.timestampsToReturn = timestampsToReturn;
        this.itemsToCreate = itemsToCreate;
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

    public TimestampsToReturn getTimestampsToReturn() {
        return timestampsToReturn;
    }

    public MonitoredItemCreateRequest[] getItemsToCreate() {
        return itemsToCreate;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 751),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("SubscriptionId", LocalizedText.NULL_VALUE, new NodeId(0, 288), -1, null, UInteger.valueOf(0), false),
                new StructureField("TimestampsToReturn", LocalizedText.NULL_VALUE, new NodeId(0, 625), -1, null, UInteger.valueOf(0), false),
                new StructureField("ItemsToCreate", LocalizedText.NULL_VALUE, new NodeId(0, 743), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<CreateMonitoredItemsRequest> {
        @Override
        public Class<CreateMonitoredItemsRequest> getType() {
            return CreateMonitoredItemsRequest.class;
        }

        @Override
        public CreateMonitoredItemsRequest decodeType(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            TimestampsToReturn timestampsToReturn = TimestampsToReturn.from(decoder.readEnum("TimestampsToReturn"));
            MonitoredItemCreateRequest[] itemsToCreate = (MonitoredItemCreateRequest[]) decoder.readStructArray("ItemsToCreate", MonitoredItemCreateRequest.TYPE_ID);
            return new CreateMonitoredItemsRequest(requestHeader, subscriptionId, timestampsToReturn, itemsToCreate);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               CreateMonitoredItemsRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeUInt32("SubscriptionId", value.getSubscriptionId());
            encoder.writeEnum("TimestampsToReturn", value.getTimestampsToReturn());
            encoder.writeStructArray("ItemsToCreate", value.getItemsToCreate(), MonitoredItemCreateRequest.TYPE_ID);
        }
    }
}
