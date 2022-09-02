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
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.12.2/#5.12.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.12.2/#5.12.2.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class MonitoredItemCreateResult extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=746");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=748");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=747");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15322");

    private final StatusCode statusCode;

    private final UInteger monitoredItemId;

    private final Double revisedSamplingInterval;

    private final UInteger revisedQueueSize;

    private final ExtensionObject filterResult;

    public MonitoredItemCreateResult(StatusCode statusCode, UInteger monitoredItemId,
                                     Double revisedSamplingInterval, UInteger revisedQueueSize, ExtensionObject filterResult) {
        this.statusCode = statusCode;
        this.monitoredItemId = monitoredItemId;
        this.revisedSamplingInterval = revisedSamplingInterval;
        this.revisedQueueSize = revisedQueueSize;
        this.filterResult = filterResult;
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

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public UInteger getMonitoredItemId() {
        return monitoredItemId;
    }

    public Double getRevisedSamplingInterval() {
        return revisedSamplingInterval;
    }

    public UInteger getRevisedQueueSize() {
        return revisedQueueSize;
    }

    public ExtensionObject getFilterResult() {
        return filterResult;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 748),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("StatusCode", LocalizedText.NULL_VALUE, new NodeId(0, 19), -1, null, UInteger.valueOf(0), false),
                new StructureField("MonitoredItemId", LocalizedText.NULL_VALUE, new NodeId(0, 288), -1, null, UInteger.valueOf(0), false),
                new StructureField("RevisedSamplingInterval", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("RevisedQueueSize", LocalizedText.NULL_VALUE, new NodeId(0, 289), -1, null, UInteger.valueOf(0), false),
                new StructureField("FilterResult", LocalizedText.NULL_VALUE, new NodeId(0, 22), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<MonitoredItemCreateResult> {
        @Override
        public Class<MonitoredItemCreateResult> getType() {
            return MonitoredItemCreateResult.class;
        }

        @Override
        public MonitoredItemCreateResult decode(SerializationContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            UInteger monitoredItemId = decoder.readUInt32("MonitoredItemId");
            Double revisedSamplingInterval = decoder.readDouble("RevisedSamplingInterval");
            UInteger revisedQueueSize = decoder.readUInt32("RevisedQueueSize");
            ExtensionObject filterResult = decoder.readExtensionObject("FilterResult");
            return new MonitoredItemCreateResult(statusCode, monitoredItemId, revisedSamplingInterval, revisedQueueSize, filterResult);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           MonitoredItemCreateResult value) {
            encoder.writeStatusCode("StatusCode", value.getStatusCode());
            encoder.writeUInt32("MonitoredItemId", value.getMonitoredItemId());
            encoder.writeDouble("RevisedSamplingInterval", value.getRevisedSamplingInterval());
            encoder.writeUInt32("RevisedQueueSize", value.getRevisedQueueSize());
            encoder.writeExtensionObject("FilterResult", value.getFilterResult());
        }
    }
}
