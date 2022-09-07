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

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.3/#9.1.3.7.4">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.3/#9.1.3.7.4</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class PubSubConfigurationValueDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=25520");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=25532");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=25548");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=25564");

    private final PubSubConfigurationRefDataType configurationElement;

    private final String name;

    private final Variant identifier;

    public PubSubConfigurationValueDataType(PubSubConfigurationRefDataType configurationElement,
                                            String name, Variant identifier) {
        this.configurationElement = configurationElement;
        this.name = name;
        this.identifier = identifier;
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

    public PubSubConfigurationRefDataType getConfigurationElement() {
        return configurationElement;
    }

    public String getName() {
        return name;
    }

    public Variant getIdentifier() {
        return identifier;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 25532),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ConfigurationElement", LocalizedText.NULL_VALUE, new NodeId(0, 25519), -1, null, UInteger.valueOf(0), false),
                new StructureField("Name", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Identifier", LocalizedText.NULL_VALUE, new NodeId(0, 24), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<PubSubConfigurationValueDataType> {
        @Override
        public Class<PubSubConfigurationValueDataType> getType() {
            return PubSubConfigurationValueDataType.class;
        }

        @Override
        public PubSubConfigurationValueDataType decode(SerializationContext context,
                                                       UaDecoder decoder) {
            PubSubConfigurationRefDataType configurationElement = (PubSubConfigurationRefDataType) decoder.readStruct("ConfigurationElement", PubSubConfigurationRefDataType.TYPE_ID);
            String name = decoder.readString("Name");
            Variant identifier = decoder.readVariant("Identifier");
            return new PubSubConfigurationValueDataType(configurationElement, name, identifier);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           PubSubConfigurationValueDataType value) {
            encoder.writeStruct("ConfigurationElement", value.getConfigurationElement(), PubSubConfigurationRefDataType.TYPE_ID);
            encoder.writeString("Name", value.getName());
            encoder.writeVariant("Identifier", value.getIdentifier());
        }
    }
}
