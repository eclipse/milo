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
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part11/6.8.4/#6.8.4.1">https://reference.opcfoundation.org/v104/Core/docs/Part11/6.8.4/#6.8.4.1</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class UpdateEventDetails extends HistoryUpdateDetails implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=683");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=685");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=684");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15282");

    private final PerformUpdateType performInsertReplace;

    private final EventFilter filter;

    private final HistoryEventFieldList[] eventData;

    public UpdateEventDetails(NodeId nodeId, PerformUpdateType performInsertReplace,
                              EventFilter filter, HistoryEventFieldList[] eventData) {
        super(nodeId);
        this.performInsertReplace = performInsertReplace;
        this.filter = filter;
        this.eventData = eventData;
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

    public PerformUpdateType getPerformInsertReplace() {
        return performInsertReplace;
    }

    public EventFilter getFilter() {
        return filter;
    }

    public HistoryEventFieldList[] getEventData() {
        return eventData;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 685),
            new NodeId(0, 677),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("NodeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("PerformInsertReplace", LocalizedText.NULL_VALUE, new NodeId(0, 11293), -1, null, UInteger.valueOf(0), false),
                new StructureField("Filter", LocalizedText.NULL_VALUE, new NodeId(0, 725), -1, null, UInteger.valueOf(0), false),
                new StructureField("EventData", LocalizedText.NULL_VALUE, new NodeId(0, 920), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<UpdateEventDetails> {
        @Override
        public Class<UpdateEventDetails> getType() {
            return UpdateEventDetails.class;
        }

        @Override
        public UpdateEventDetails decode(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            PerformUpdateType performInsertReplace = (PerformUpdateType) decoder.readEnum("PerformInsertReplace", PerformUpdateType.class);
            EventFilter filter = (EventFilter) decoder.readStruct("Filter", EventFilter.TYPE_ID);
            HistoryEventFieldList[] eventData = (HistoryEventFieldList[]) decoder.readStructArray("EventData", HistoryEventFieldList.TYPE_ID);
            return new UpdateEventDetails(nodeId, performInsertReplace, filter, eventData);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, UpdateEventDetails value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeEnum("PerformInsertReplace", value.getPerformInsertReplace());
            encoder.writeStruct("Filter", value.getFilter(), EventFilter.TYPE_ID);
            encoder.writeStructArray("EventData", value.getEventData(), HistoryEventFieldList.TYPE_ID);
        }
    }
}
