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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class HistoryEventFieldList extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=920");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=922");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=921");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15349");

    private final Variant[] eventFields;

    public HistoryEventFieldList(Variant[] eventFields) {
        this.eventFields = eventFields;
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

    public Variant[] getEventFields() {
        return eventFields;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 922),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("EventFields", LocalizedText.NULL_VALUE, new NodeId(0, 24), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<HistoryEventFieldList> {
        @Override
        public Class<HistoryEventFieldList> getType() {
            return HistoryEventFieldList.class;
        }

        @Override
        public HistoryEventFieldList decodeType(SerializationContext context, UaDecoder decoder) {
            Variant[] eventFields = decoder.readVariantArray("EventFields");
            return new HistoryEventFieldList(eventFields);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               HistoryEventFieldList value) {
            encoder.writeVariantArray("EventFields", value.getEventFields());
        }
    }
}
