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
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.34">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.34</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class EnumDescription extends DataTypeDescription implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15488");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=127");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15590");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15059");

    private final EnumDefinition enumDefinition;

    private final UByte builtInType;

    public EnumDescription(NodeId dataTypeId, QualifiedName name, EnumDefinition enumDefinition,
                           UByte builtInType) {
        super(dataTypeId, name);
        this.enumDefinition = enumDefinition;
        this.builtInType = builtInType;
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

    public EnumDefinition getEnumDefinition() {
        return enumDefinition;
    }

    public UByte getBuiltInType() {
        return builtInType;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 127),
            new NodeId(0, 14525),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("DataTypeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("Name", LocalizedText.NULL_VALUE, new NodeId(0, 20), -1, null, UInteger.valueOf(0), false),
                new StructureField("EnumDefinition", LocalizedText.NULL_VALUE, new NodeId(0, 100), -1, null, UInteger.valueOf(0), false),
                new StructureField("BuiltInType", LocalizedText.NULL_VALUE, new NodeId(0, 3), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<EnumDescription> {
        @Override
        public Class<EnumDescription> getType() {
            return EnumDescription.class;
        }

        @Override
        public EnumDescription decodeType(SerializationContext context, UaDecoder decoder) {
            NodeId dataTypeId = decoder.readNodeId("DataTypeId");
            QualifiedName name = decoder.readQualifiedName("Name");
            EnumDefinition enumDefinition = (EnumDefinition) decoder.readStruct("EnumDefinition", EnumDefinition.TYPE_ID);
            UByte builtInType = decoder.readByte("BuiltInType");
            return new EnumDescription(dataTypeId, name, enumDefinition, builtInType);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, EnumDescription value) {
            encoder.writeNodeId("DataTypeId", value.getDataTypeId());
            encoder.writeQualifiedName("Name", value.getName());
            encoder.writeStruct("EnumDefinition", value.getEnumDefinition(), EnumDefinition.TYPE_ID);
            encoder.writeByte("BuiltInType", value.getBuiltInType());
        }
    }
}
