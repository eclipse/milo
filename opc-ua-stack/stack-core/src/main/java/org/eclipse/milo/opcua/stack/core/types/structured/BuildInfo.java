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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.4">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.4</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class BuildInfo extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=338");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=340");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=339");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15361");

    private final String productUri;

    private final String manufacturerName;

    private final String productName;

    private final String softwareVersion;

    private final String buildNumber;

    private final DateTime buildDate;

    public BuildInfo(String productUri, String manufacturerName, String productName,
                     String softwareVersion, String buildNumber, DateTime buildDate) {
        this.productUri = productUri;
        this.manufacturerName = manufacturerName;
        this.productName = productName;
        this.softwareVersion = softwareVersion;
        this.buildNumber = buildNumber;
        this.buildDate = buildDate;
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

    public String getProductUri() {
        return productUri;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getProductName() {
        return productName;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public DateTime getBuildDate() {
        return buildDate;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 340),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ProductUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("ManufacturerName", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("ProductName", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("SoftwareVersion", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("BuildNumber", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("BuildDate", LocalizedText.NULL_VALUE, new NodeId(0, 294), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<BuildInfo> {
        @Override
        public Class<BuildInfo> getType() {
            return BuildInfo.class;
        }

        @Override
        public BuildInfo decodeType(SerializationContext context, UaDecoder decoder) {
            String productUri = decoder.readString("ProductUri");
            String manufacturerName = decoder.readString("ManufacturerName");
            String productName = decoder.readString("ProductName");
            String softwareVersion = decoder.readString("SoftwareVersion");
            String buildNumber = decoder.readString("BuildNumber");
            DateTime buildDate = decoder.readDateTime("BuildDate");
            return new BuildInfo(productUri, manufacturerName, productName, softwareVersion, buildNumber, buildDate);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, BuildInfo value) {
            encoder.writeString("ProductUri", value.getProductUri());
            encoder.writeString("ManufacturerName", value.getManufacturerName());
            encoder.writeString("ProductName", value.getProductName());
            encoder.writeString("SoftwareVersion", value.getSoftwareVersion());
            encoder.writeString("BuildNumber", value.getBuildNumber());
            encoder.writeDateTime("BuildDate", value.getBuildDate());
        }
    }
}
