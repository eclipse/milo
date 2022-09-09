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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.21">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.21</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class MonitoringParameters extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=740");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=742");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=741");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15320");

    private final UInteger clientHandle;

    private final Double samplingInterval;

    private final ExtensionObject filter;

    private final UInteger queueSize;

    private final Boolean discardOldest;

    public MonitoringParameters(UInteger clientHandle, Double samplingInterval,
                                ExtensionObject filter, UInteger queueSize, Boolean discardOldest) {
        this.clientHandle = clientHandle;
        this.samplingInterval = samplingInterval;
        this.filter = filter;
        this.queueSize = queueSize;
        this.discardOldest = discardOldest;
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

    public UInteger getClientHandle() {
        return clientHandle;
    }

    public Double getSamplingInterval() {
        return samplingInterval;
    }

    public ExtensionObject getFilter() {
        return filter;
    }

    public UInteger getQueueSize() {
        return queueSize;
    }

    public Boolean getDiscardOldest() {
        return discardOldest;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 742),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ClientHandle", LocalizedText.NULL_VALUE, new NodeId(0, 288), -1, null, UInteger.valueOf(0), false),
                new StructureField("SamplingInterval", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("Filter", LocalizedText.NULL_VALUE, new NodeId(0, 22), -1, null, UInteger.valueOf(0), false),
                new StructureField("QueueSize", LocalizedText.NULL_VALUE, new NodeId(0, 289), -1, null, UInteger.valueOf(0), false),
                new StructureField("DiscardOldest", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<MonitoringParameters> {
        @Override
        public Class<MonitoringParameters> getType() {
            return MonitoringParameters.class;
        }

        @Override
        public MonitoringParameters decodeType(SerializationContext context, UaDecoder decoder) {
            UInteger clientHandle = decoder.readUInt32("ClientHandle");
            Double samplingInterval = decoder.readDouble("SamplingInterval");
            ExtensionObject filter = decoder.readExtensionObject("Filter");
            UInteger queueSize = decoder.readUInt32("QueueSize");
            Boolean discardOldest = decoder.readBoolean("DiscardOldest");
            return new MonitoringParameters(clientHandle, samplingInterval, filter, queueSize, discardOldest);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               MonitoringParameters value) {
            encoder.writeUInt32("ClientHandle", value.getClientHandle());
            encoder.writeDouble("SamplingInterval", value.getSamplingInterval());
            encoder.writeExtensionObject("Filter", value.getFilter());
            encoder.writeUInt32("QueueSize", value.getQueueSize());
            encoder.writeBoolean("DiscardOldest", value.getDiscardOldest());
        }
    }
}
