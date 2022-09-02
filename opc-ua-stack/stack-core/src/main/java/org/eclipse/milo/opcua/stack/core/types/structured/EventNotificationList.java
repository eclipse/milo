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
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.25.3">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.25.3</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class EventNotificationList extends NotificationData implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=914");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=916");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=915");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15347");

    private final EventFieldList[] events;

    public EventNotificationList(EventFieldList[] events) {
        this.events = events;
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

    public EventFieldList[] getEvents() {
        return events;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 916),
            new NodeId(0, 945),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Events", LocalizedText.NULL_VALUE, new NodeId(0, 917), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<EventNotificationList> {
        @Override
        public Class<EventNotificationList> getType() {
            return EventNotificationList.class;
        }

        @Override
        public EventNotificationList decode(SerializationContext context, UaDecoder decoder) {
            EventFieldList[] events = (EventFieldList[]) decoder.readStructArray("Events", EventFieldList.TYPE_ID);
            return new EventNotificationList(events);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           EventNotificationList value) {
            encoder.writeStructArray("Events", value.getEvents(), EventFieldList.TYPE_ID);
        }
    }
}
