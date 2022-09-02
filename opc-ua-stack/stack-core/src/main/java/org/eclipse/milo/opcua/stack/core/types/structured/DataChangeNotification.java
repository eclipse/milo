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
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.25.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.25.2</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class DataChangeNotification extends NotificationData implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=809");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=811");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=810");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15345");

    private final MonitoredItemNotification[] monitoredItems;

    private final DiagnosticInfo[] diagnosticInfos;

    public DataChangeNotification(MonitoredItemNotification[] monitoredItems,
                                  DiagnosticInfo[] diagnosticInfos) {
        this.monitoredItems = monitoredItems;
        this.diagnosticInfos = diagnosticInfos;
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

    public MonitoredItemNotification[] getMonitoredItems() {
        return monitoredItems;
    }

    public DiagnosticInfo[] getDiagnosticInfos() {
        return diagnosticInfos;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 811),
            new NodeId(0, 945),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("MonitoredItems", LocalizedText.NULL_VALUE, new NodeId(0, 806), 1, null, UInteger.valueOf(0), false),
                new StructureField("DiagnosticInfos", LocalizedText.NULL_VALUE, new NodeId(0, 25), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DataChangeNotification> {
        @Override
        public Class<DataChangeNotification> getType() {
            return DataChangeNotification.class;
        }

        @Override
        public DataChangeNotification decode(SerializationContext context, UaDecoder decoder) {
            MonitoredItemNotification[] monitoredItems = (MonitoredItemNotification[]) decoder.readStructArray("MonitoredItems", MonitoredItemNotification.TYPE_ID);
            DiagnosticInfo[] diagnosticInfos = decoder.readDiagnosticInfoArray("DiagnosticInfos");
            return new DataChangeNotification(monitoredItems, diagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           DataChangeNotification value) {
            encoder.writeStructArray("MonitoredItems", value.getMonitoredItems(), MonitoredItemNotification.TYPE_ID);
            encoder.writeDiagnosticInfoArray("DiagnosticInfos", value.getDiagnosticInfos());
        }
    }
}
