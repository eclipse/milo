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
import org.eclipse.milo.opcua.stack.core.types.enumerated.AxisScaleEnumeration;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part8/5.6.6">https://reference.opcfoundation.org/v105/Core/docs/Part8/5.6.6</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class AxisInformation extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=12079");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=12089");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=12081");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15379");

    private final EUInformation engineeringUnits;

    private final Range euRange;

    private final LocalizedText title;

    private final AxisScaleEnumeration axisScaleType;

    private final Double[] axisSteps;

    public AxisInformation(EUInformation engineeringUnits, Range euRange, LocalizedText title,
                           AxisScaleEnumeration axisScaleType, Double[] axisSteps) {
        this.engineeringUnits = engineeringUnits;
        this.euRange = euRange;
        this.title = title;
        this.axisScaleType = axisScaleType;
        this.axisSteps = axisSteps;
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

    public EUInformation getEngineeringUnits() {
        return engineeringUnits;
    }

    public Range getEuRange() {
        return euRange;
    }

    public LocalizedText getTitle() {
        return title;
    }

    public AxisScaleEnumeration getAxisScaleType() {
        return axisScaleType;
    }

    public Double[] getAxisSteps() {
        return axisSteps;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 12089),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("EngineeringUnits", LocalizedText.NULL_VALUE, new NodeId(0, 887), -1, null, UInteger.valueOf(0), false),
                new StructureField("EURange", LocalizedText.NULL_VALUE, new NodeId(0, 884), -1, null, UInteger.valueOf(0), false),
                new StructureField("Title", LocalizedText.NULL_VALUE, new NodeId(0, 21), -1, null, UInteger.valueOf(0), false),
                new StructureField("AxisScaleType", LocalizedText.NULL_VALUE, new NodeId(0, 12077), -1, null, UInteger.valueOf(0), false),
                new StructureField("AxisSteps", LocalizedText.NULL_VALUE, new NodeId(0, 11), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<AxisInformation> {
        @Override
        public Class<AxisInformation> getType() {
            return AxisInformation.class;
        }

        @Override
        public AxisInformation decodeType(SerializationContext context, UaDecoder decoder) {
            EUInformation engineeringUnits = (EUInformation) decoder.readStruct("EngineeringUnits", EUInformation.TYPE_ID);
            Range euRange = (Range) decoder.readStruct("EURange", Range.TYPE_ID);
            LocalizedText title = decoder.readLocalizedText("Title");
            AxisScaleEnumeration axisScaleType = AxisScaleEnumeration.from(decoder.readEnum("AxisScaleType"));
            Double[] axisSteps = decoder.readDoubleArray("AxisSteps");
            return new AxisInformation(engineeringUnits, euRange, title, axisScaleType, axisSteps);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, AxisInformation value) {
            encoder.writeStruct("EngineeringUnits", value.getEngineeringUnits(), EUInformation.TYPE_ID);
            encoder.writeStruct("EURange", value.getEuRange(), Range.TYPE_ID);
            encoder.writeLocalizedText("Title", value.getTitle());
            encoder.writeEnum("AxisScaleType", value.getAxisScaleType());
            encoder.writeDoubleArray("AxisSteps", value.getAxisSteps());
        }
    }
}
