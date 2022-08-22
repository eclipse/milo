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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part8/5.6.3/#5.6.3.2">https://reference.opcfoundation.org/v105/Core/docs/Part8/5.6.3/#5.6.3.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class EUInformation extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=887");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=889");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=888");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15376");

    private final String namespaceUri;

    private final Integer unitId;

    private final LocalizedText displayName;

    private final LocalizedText description;

    public EUInformation(String namespaceUri, Integer unitId, LocalizedText displayName,
                         LocalizedText description) {
        this.namespaceUri = namespaceUri;
        this.unitId = unitId;
        this.displayName = displayName;
        this.description = description;
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

    public String getNamespaceUri() {
        return namespaceUri;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public LocalizedText getDisplayName() {
        return displayName;
    }

    public LocalizedText getDescription() {
        return description;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 889),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("NamespaceUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("UnitId", LocalizedText.NULL_VALUE, new NodeId(0, 6), -1, null, UInteger.valueOf(0), false),
                new StructureField("DisplayName", LocalizedText.NULL_VALUE, new NodeId(0, 21), -1, null, UInteger.valueOf(0), false),
                new StructureField("Description", LocalizedText.NULL_VALUE, new NodeId(0, 21), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<EUInformation> {
        @Override
        public Class<EUInformation> getType() {
            return EUInformation.class;
        }

        @Override
        public EUInformation decode(SerializationContext context, UaDecoder decoder) {
            String namespaceUri = decoder.readString("NamespaceUri");
            Integer unitId = decoder.readInt32("UnitId");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            return new EUInformation(namespaceUri, unitId, displayName, description);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, EUInformation value) {
            encoder.writeString("NamespaceUri", value.getNamespaceUri());
            encoder.writeInt32("UnitId", value.getUnitId());
            encoder.writeLocalizedText("DisplayName", value.getDisplayName());
            encoder.writeLocalizedText("Description", value.getDescription());
        }
    }
}
