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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.3.2/#5.3.2.1">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.3.2/#5.3.2.1</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class PriorityMappingEntryType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=25220");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=25239");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=25243");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=25247");

    private final String mappingUri;

    private final String priorityLabel;

    private final UByte priorityValuePcp;

    private final UInteger priorityValueDscp;

    public PriorityMappingEntryType(String mappingUri, String priorityLabel, UByte priorityValuePcp,
                                    UInteger priorityValueDscp) {
        this.mappingUri = mappingUri;
        this.priorityLabel = priorityLabel;
        this.priorityValuePcp = priorityValuePcp;
        this.priorityValueDscp = priorityValueDscp;
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

    public String getMappingUri() {
        return mappingUri;
    }

    public String getPriorityLabel() {
        return priorityLabel;
    }

    public UByte getPriorityValuePcp() {
        return priorityValuePcp;
    }

    public UInteger getPriorityValueDscp() {
        return priorityValueDscp;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 25239),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("MappingUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("PriorityLabel", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("PriorityValue_PCP", LocalizedText.NULL_VALUE, new NodeId(0, 3), -1, null, UInteger.valueOf(0), false),
                new StructureField("PriorityValue_DSCP", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<PriorityMappingEntryType> {
        @Override
        public Class<PriorityMappingEntryType> getType() {
            return PriorityMappingEntryType.class;
        }

        @Override
        public PriorityMappingEntryType decodeType(SerializationContext context, UaDecoder decoder) {
            String mappingUri = decoder.readString("MappingUri");
            String priorityLabel = decoder.readString("PriorityLabel");
            UByte priorityValuePcp = decoder.readByte("PriorityValue_PCP");
            UInteger priorityValueDscp = decoder.readUInt32("PriorityValue_DSCP");
            return new PriorityMappingEntryType(mappingUri, priorityLabel, priorityValuePcp, priorityValueDscp);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               PriorityMappingEntryType value) {
            encoder.writeString("MappingUri", value.getMappingUri());
            encoder.writeString("PriorityLabel", value.getPriorityLabel());
            encoder.writeByte("PriorityValue_PCP", value.getPriorityValuePcp());
            encoder.writeUInt32("PriorityValue_DSCP", value.getPriorityValueDscp());
        }
    }
}
