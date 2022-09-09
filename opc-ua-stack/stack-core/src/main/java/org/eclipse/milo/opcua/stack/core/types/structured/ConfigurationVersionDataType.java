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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.3/#6.2.3.2.5">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.3/#6.2.3.2.5</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ConfigurationVersionDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=14593");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=14847");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=14803");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15049");

    private final UInteger majorVersion;

    private final UInteger minorVersion;

    public ConfigurationVersionDataType(UInteger majorVersion, UInteger minorVersion) {
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
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

    public UInteger getMajorVersion() {
        return majorVersion;
    }

    public UInteger getMinorVersion() {
        return minorVersion;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 14847),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("MajorVersion", LocalizedText.NULL_VALUE, new NodeId(0, 20998), -1, null, UInteger.valueOf(0), false),
                new StructureField("MinorVersion", LocalizedText.NULL_VALUE, new NodeId(0, 20998), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ConfigurationVersionDataType> {
        @Override
        public Class<ConfigurationVersionDataType> getType() {
            return ConfigurationVersionDataType.class;
        }

        @Override
        public ConfigurationVersionDataType decodeType(SerializationContext context,
                                                       UaDecoder decoder) {
            UInteger majorVersion = decoder.readUInt32("MajorVersion");
            UInteger minorVersion = decoder.readUInt32("MinorVersion");
            return new ConfigurationVersionDataType(majorVersion, minorVersion);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               ConfigurationVersionDataType value) {
            encoder.writeUInt32("MajorVersion", value.getMajorVersion());
            encoder.writeUInt32("MinorVersion", value.getMinorVersion());
        }
    }
}
