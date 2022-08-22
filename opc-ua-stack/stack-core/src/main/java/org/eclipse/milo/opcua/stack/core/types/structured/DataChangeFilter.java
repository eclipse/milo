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
import org.eclipse.milo.opcua.stack.core.types.enumerated.DataChangeTrigger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.22.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.22.2</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class DataChangeFilter extends MonitoringFilter implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=722");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=724");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=723");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15294");

    private final DataChangeTrigger trigger;

    private final UInteger deadbandType;

    private final Double deadbandValue;

    public DataChangeFilter(DataChangeTrigger trigger, UInteger deadbandType, Double deadbandValue) {
        this.trigger = trigger;
        this.deadbandType = deadbandType;
        this.deadbandValue = deadbandValue;
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

    public DataChangeTrigger getTrigger() {
        return trigger;
    }

    public UInteger getDeadbandType() {
        return deadbandType;
    }

    public Double getDeadbandValue() {
        return deadbandValue;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 724),
            new NodeId(0, 719),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Trigger", LocalizedText.NULL_VALUE, new NodeId(0, 717), -1, null, UInteger.valueOf(0), false),
                new StructureField("DeadbandType", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("DeadbandValue", LocalizedText.NULL_VALUE, new NodeId(0, 11), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DataChangeFilter> {
        @Override
        public Class<DataChangeFilter> getType() {
            return DataChangeFilter.class;
        }

        @Override
        public DataChangeFilter decode(SerializationContext context, UaDecoder decoder) {
            DataChangeTrigger trigger = (DataChangeTrigger) decoder.readEnum("Trigger", DataChangeTrigger.class);
            UInteger deadbandType = decoder.readUInt32("DeadbandType");
            Double deadbandValue = decoder.readDouble("DeadbandValue");
            return new DataChangeFilter(trigger, deadbandType, deadbandValue);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, DataChangeFilter value) {
            encoder.writeEnum("Trigger", value.getTrigger());
            encoder.writeUInt32("DeadbandType", value.getDeadbandType());
            encoder.writeDouble("DeadbandValue", value.getDeadbandValue());
        }
    }
}
