package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.3.2/#6.3.2.3.2">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.3.2/#6.3.2.3.2</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class JsonDataSetWriterMessageDataType extends DataSetWriterMessageDataType implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15664");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15724");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=16018");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16394");

    private final JsonDataSetMessageContentMask dataSetMessageContentMask;

    public JsonDataSetWriterMessageDataType(JsonDataSetMessageContentMask dataSetMessageContentMask) {
        this.dataSetMessageContentMask = dataSetMessageContentMask;
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

    public JsonDataSetMessageContentMask getDataSetMessageContentMask() {
        return dataSetMessageContentMask;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15724),
            new NodeId(0, 15605),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("DataSetMessageContentMask", LocalizedText.NULL_VALUE, new NodeId(0, 15658), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<JsonDataSetWriterMessageDataType> {
        @Override
        public Class<JsonDataSetWriterMessageDataType> getType() {
            return JsonDataSetWriterMessageDataType.class;
        }

        @Override
        public JsonDataSetWriterMessageDataType decodeType(SerializationContext context,
                                                           UaDecoder decoder) {
            JsonDataSetMessageContentMask dataSetMessageContentMask = new JsonDataSetMessageContentMask(decoder.readUInt32("DataSetMessageContentMask"));
            return new JsonDataSetWriterMessageDataType(dataSetMessageContentMask);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               JsonDataSetWriterMessageDataType value) {
            encoder.writeUInt32("DataSetMessageContentMask", value.getDataSetMessageContentMask().getValue());
        }
    }
}
