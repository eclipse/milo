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
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.25.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.25.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class MonitoredItemNotification extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=806");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=808");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=807");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15346");

    private final UInteger clientHandle;

    private final DataValue value;

    public MonitoredItemNotification(UInteger clientHandle, DataValue value) {
        this.clientHandle = clientHandle;
        this.value = value;
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

    public DataValue getValue() {
        return value;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 808),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ClientHandle", LocalizedText.NULL_VALUE, new NodeId(0, 288), -1, null, UInteger.valueOf(0), false),
                new StructureField("Value", LocalizedText.NULL_VALUE, new NodeId(0, 23), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<MonitoredItemNotification> {
        @Override
        public Class<MonitoredItemNotification> getType() {
            return MonitoredItemNotification.class;
        }

        @Override
        public MonitoredItemNotification decode(SerializationContext context, UaDecoder decoder) {
            UInteger clientHandle = decoder.readUInt32("ClientHandle");
            DataValue value = decoder.readDataValue("Value");
            return new MonitoredItemNotification(clientHandle, value);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           MonitoredItemNotification value) {
            encoder.writeUInt32("ClientHandle", value.getClientHandle());
            encoder.writeDataValue("Value", value.getValue());
        }
    }
}
