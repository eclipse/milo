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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.10/#6.2.10.4">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.10/#6.2.10.4</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class StandaloneSubscribedDataSetRefDataType extends SubscribedDataSetDataType implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=23599");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=23851");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=23919");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=23987");

    private final String dataSetName;

    public StandaloneSubscribedDataSetRefDataType(String dataSetName) {
        this.dataSetName = dataSetName;
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

    public String getDataSetName() {
        return dataSetName;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 23851),
            new NodeId(0, 15630),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("DataSetName", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<StandaloneSubscribedDataSetRefDataType> {
        @Override
        public Class<StandaloneSubscribedDataSetRefDataType> getType() {
            return StandaloneSubscribedDataSetRefDataType.class;
        }

        @Override
        public StandaloneSubscribedDataSetRefDataType decodeType(SerializationContext context,
                                                                 UaDecoder decoder) {
            String dataSetName = decoder.readString("DataSetName");
            return new StandaloneSubscribedDataSetRefDataType(dataSetName);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               StandaloneSubscribedDataSetRefDataType value) {
            encoder.writeString("DataSetName", value.getDataSetName());
        }
    }
}
