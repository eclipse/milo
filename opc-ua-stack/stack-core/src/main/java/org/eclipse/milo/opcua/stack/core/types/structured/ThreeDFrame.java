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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.30">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.30</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class ThreeDFrame extends Frame implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=18814");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=18823");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=18859");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=19072");

    private final ThreeDCartesianCoordinates cartesianCoordinates;

    private final ThreeDOrientation orientation;

    public ThreeDFrame(ThreeDCartesianCoordinates cartesianCoordinates,
                       ThreeDOrientation orientation) {
        this.cartesianCoordinates = cartesianCoordinates;
        this.orientation = orientation;
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

    public ThreeDCartesianCoordinates getCartesianCoordinates() {
        return cartesianCoordinates;
    }

    public ThreeDOrientation getOrientation() {
        return orientation;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 18823),
            new NodeId(0, 18813),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("CartesianCoordinates", LocalizedText.NULL_VALUE, new NodeId(0, 18810), -1, null, UInteger.valueOf(0), false),
                new StructureField("Orientation", LocalizedText.NULL_VALUE, new NodeId(0, 18812), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ThreeDFrame> {
        @Override
        public Class<ThreeDFrame> getType() {
            return ThreeDFrame.class;
        }

        @Override
        public ThreeDFrame decodeType(SerializationContext context, UaDecoder decoder) {
            ThreeDCartesianCoordinates cartesianCoordinates = (ThreeDCartesianCoordinates) decoder.readStruct("CartesianCoordinates", ThreeDCartesianCoordinates.TYPE_ID);
            ThreeDOrientation orientation = (ThreeDOrientation) decoder.readStruct("Orientation", ThreeDOrientation.TYPE_ID);
            return new ThreeDFrame(cartesianCoordinates, orientation);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, ThreeDFrame value) {
            encoder.writeStruct("CartesianCoordinates", value.getCartesianCoordinates(), ThreeDCartesianCoordinates.TYPE_ID);
            encoder.writeStruct("Orientation", value.getOrientation(), ThreeDOrientation.TYPE_ID);
        }
    }
}
