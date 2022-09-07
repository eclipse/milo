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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.12.3/#5.12.3.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.12.3/#5.12.3.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class MonitoredItemModifyRequest extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=755");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=757");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=756");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15325");

    private final UInteger monitoredItemId;

    private final MonitoringParameters requestedParameters;

    public MonitoredItemModifyRequest(UInteger monitoredItemId,
                                      MonitoringParameters requestedParameters) {
        this.monitoredItemId = monitoredItemId;
        this.requestedParameters = requestedParameters;
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

    public UInteger getMonitoredItemId() {
        return monitoredItemId;
    }

    public MonitoringParameters getRequestedParameters() {
        return requestedParameters;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 757),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("MonitoredItemId", LocalizedText.NULL_VALUE, new NodeId(0, 288), -1, null, UInteger.valueOf(0), false),
                new StructureField("RequestedParameters", LocalizedText.NULL_VALUE, new NodeId(0, 740), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<MonitoredItemModifyRequest> {
        @Override
        public Class<MonitoredItemModifyRequest> getType() {
            return MonitoredItemModifyRequest.class;
        }

        @Override
        public MonitoredItemModifyRequest decode(SerializationContext context, UaDecoder decoder) {
            UInteger monitoredItemId = decoder.readUInt32("MonitoredItemId");
            MonitoringParameters requestedParameters = (MonitoringParameters) decoder.readStruct("RequestedParameters", MonitoringParameters.TYPE_ID);
            return new MonitoredItemModifyRequest(monitoredItemId, requestedParameters);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           MonitoredItemModifyRequest value) {
            encoder.writeUInt32("MonitoredItemId", value.getMonitoredItemId());
            encoder.writeStruct("RequestedParameters", value.getRequestedParameters(), MonitoringParameters.TYPE_ID);
        }
    }
}
