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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class MonitoringFilterResult extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=731");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=733");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=732");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15313");

    public MonitoringFilterResult() {
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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 733),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{

            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<MonitoringFilterResult> {
        @Override
        public Class<MonitoringFilterResult> getType() {
            return MonitoringFilterResult.class;
        }

        @Override
        public MonitoringFilterResult decode(SerializationContext context, UaDecoder decoder) {
            return new MonitoringFilterResult();
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           MonitoringFilterResult value) {
        }
    }
}
