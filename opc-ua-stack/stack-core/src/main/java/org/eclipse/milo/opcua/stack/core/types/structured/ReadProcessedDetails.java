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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part11/6.4.4/#6.4.4.1">https://reference.opcfoundation.org/v104/Core/docs/Part11/6.4.4/#6.4.4.1</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class ReadProcessedDetails extends HistoryReadDetails implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=650");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=652");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=651");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15264");

    private final DateTime startTime;

    private final DateTime endTime;

    private final Double processingInterval;

    private final NodeId[] aggregateType;

    private final AggregateConfiguration aggregateConfiguration;

    public ReadProcessedDetails(DateTime startTime, DateTime endTime, Double processingInterval,
                                NodeId[] aggregateType, AggregateConfiguration aggregateConfiguration) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.processingInterval = processingInterval;
        this.aggregateType = aggregateType;
        this.aggregateConfiguration = aggregateConfiguration;
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

    public DateTime getStartTime() {
        return startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public Double getProcessingInterval() {
        return processingInterval;
    }

    public NodeId[] getAggregateType() {
        return aggregateType;
    }

    public AggregateConfiguration getAggregateConfiguration() {
        return aggregateConfiguration;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 652),
            new NodeId(0, 641),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("StartTime", LocalizedText.NULL_VALUE, new NodeId(0, 294), -1, null, UInteger.valueOf(0), false),
                new StructureField("EndTime", LocalizedText.NULL_VALUE, new NodeId(0, 294), -1, null, UInteger.valueOf(0), false),
                new StructureField("ProcessingInterval", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("AggregateType", LocalizedText.NULL_VALUE, new NodeId(0, 17), 1, null, UInteger.valueOf(0), false),
                new StructureField("AggregateConfiguration", LocalizedText.NULL_VALUE, new NodeId(0, 948), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ReadProcessedDetails> {
        @Override
        public Class<ReadProcessedDetails> getType() {
            return ReadProcessedDetails.class;
        }

        @Override
        public ReadProcessedDetails decodeType(SerializationContext context, UaDecoder decoder) {
            DateTime startTime = decoder.readDateTime("StartTime");
            DateTime endTime = decoder.readDateTime("EndTime");
            Double processingInterval = decoder.readDouble("ProcessingInterval");
            NodeId[] aggregateType = decoder.readNodeIdArray("AggregateType");
            AggregateConfiguration aggregateConfiguration = (AggregateConfiguration) decoder.readStruct("AggregateConfiguration", AggregateConfiguration.TYPE_ID);
            return new ReadProcessedDetails(startTime, endTime, processingInterval, aggregateType, aggregateConfiguration);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               ReadProcessedDetails value) {
            encoder.writeDateTime("StartTime", value.getStartTime());
            encoder.writeDateTime("EndTime", value.getEndTime());
            encoder.writeDouble("ProcessingInterval", value.getProcessingInterval());
            encoder.writeNodeIdArray("AggregateType", value.getAggregateType());
            encoder.writeStruct("AggregateConfiguration", value.getAggregateConfiguration(), AggregateConfiguration.TYPE_ID);
        }
    }
}
