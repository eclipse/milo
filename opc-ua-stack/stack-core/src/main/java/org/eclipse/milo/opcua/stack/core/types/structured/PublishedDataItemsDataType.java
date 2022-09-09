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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.3/#6.2.3.7.2">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.3/#6.2.3.7.2</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class PublishedDataItemsDataType extends PublishedDataSetSourceDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15581");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15679");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15953");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16154");

    private final PublishedVariableDataType[] publishedData;

    public PublishedDataItemsDataType(PublishedVariableDataType[] publishedData) {
        this.publishedData = publishedData;
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

    public PublishedVariableDataType[] getPublishedData() {
        return publishedData;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15679),
            new NodeId(0, 15580),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("PublishedData", LocalizedText.NULL_VALUE, new NodeId(0, 14273), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<PublishedDataItemsDataType> {
        @Override
        public Class<PublishedDataItemsDataType> getType() {
            return PublishedDataItemsDataType.class;
        }

        @Override
        public PublishedDataItemsDataType decodeType(SerializationContext context, UaDecoder decoder) {
            PublishedVariableDataType[] publishedData = (PublishedVariableDataType[]) decoder.readStructArray("PublishedData", PublishedVariableDataType.TYPE_ID);
            return new PublishedDataItemsDataType(publishedData);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               PublishedDataItemsDataType value) {
            encoder.writeStructArray("PublishedData", value.getPublishedData(), PublishedVariableDataType.TYPE_ID);
        }
    }
}
