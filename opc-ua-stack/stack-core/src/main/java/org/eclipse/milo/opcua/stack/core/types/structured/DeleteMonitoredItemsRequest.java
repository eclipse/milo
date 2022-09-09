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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.12.6/#5.12.6.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.12.6/#5.12.6.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class DeleteMonitoredItemsRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=779");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=781");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=780");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15335");

    private final RequestHeader requestHeader;

    private final UInteger subscriptionId;

    private final UInteger[] monitoredItemIds;

    public DeleteMonitoredItemsRequest(RequestHeader requestHeader, UInteger subscriptionId,
                                       UInteger[] monitoredItemIds) {
        this.requestHeader = requestHeader;
        this.subscriptionId = subscriptionId;
        this.monitoredItemIds = monitoredItemIds;
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

    public UInteger[] getMonitoredItemIds() {
        return monitoredItemIds;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 781),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("SubscriptionId", LocalizedText.NULL_VALUE, new NodeId(0, 288), -1, null, UInteger.valueOf(0), false),
                new StructureField("MonitoredItemIds", LocalizedText.NULL_VALUE, new NodeId(0, 288), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DeleteMonitoredItemsRequest> {
        @Override
        public Class<DeleteMonitoredItemsRequest> getType() {
            return DeleteMonitoredItemsRequest.class;
        }

        @Override
        public DeleteMonitoredItemsRequest decodeType(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            UInteger[] monitoredItemIds = decoder.readUInt32Array("MonitoredItemIds");
            return new DeleteMonitoredItemsRequest(requestHeader, subscriptionId, monitoredItemIds);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               DeleteMonitoredItemsRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeUInt32("SubscriptionId", value.getSubscriptionId());
            encoder.writeUInt32Array("MonitoredItemIds", value.getMonitoredItemIds());
        }
    }
}
