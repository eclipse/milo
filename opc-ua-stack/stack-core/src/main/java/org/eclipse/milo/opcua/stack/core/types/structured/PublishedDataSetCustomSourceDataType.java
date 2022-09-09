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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.3/#6.2.3.9.2">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.3/#6.2.3.9.2</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class PublishedDataSetCustomSourceDataType extends PublishedDataSetSourceDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=25269");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=25529");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=25545");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=25561");

    private final Boolean cyclicDataSet;

    public PublishedDataSetCustomSourceDataType(Boolean cyclicDataSet) {
        this.cyclicDataSet = cyclicDataSet;
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

    public Boolean getCyclicDataSet() {
        return cyclicDataSet;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 25529),
            new NodeId(0, 15580),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("CyclicDataSet", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<PublishedDataSetCustomSourceDataType> {
        @Override
        public Class<PublishedDataSetCustomSourceDataType> getType() {
            return PublishedDataSetCustomSourceDataType.class;
        }

        @Override
        public PublishedDataSetCustomSourceDataType decodeType(SerializationContext context,
                                                               UaDecoder decoder) {
            Boolean cyclicDataSet = decoder.readBoolean("CyclicDataSet");
            return new PublishedDataSetCustomSourceDataType(cyclicDataSet);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               PublishedDataSetCustomSourceDataType value) {
            encoder.writeBoolean("CyclicDataSet", value.getCyclicDataSet());
        }
    }
}
