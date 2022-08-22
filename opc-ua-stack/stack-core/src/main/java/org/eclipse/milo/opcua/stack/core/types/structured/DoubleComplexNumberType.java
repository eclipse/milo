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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part8/5.6.5">https://reference.opcfoundation.org/v105/Core/docs/Part8/5.6.5</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class DoubleComplexNumberType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=12172");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=12182");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=12174");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15378");

    private final Double real;

    private final Double imaginary;

    public DoubleComplexNumberType(Double real, Double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
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

    public Double getReal() {
        return real;
    }

    public Double getImaginary() {
        return imaginary;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 12182),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Real", LocalizedText.NULL_VALUE, new NodeId(0, 11), -1, null, UInteger.valueOf(0), false),
                new StructureField("Imaginary", LocalizedText.NULL_VALUE, new NodeId(0, 11), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DoubleComplexNumberType> {
        @Override
        public Class<DoubleComplexNumberType> getType() {
            return DoubleComplexNumberType.class;
        }

        @Override
        public DoubleComplexNumberType decode(SerializationContext context, UaDecoder decoder) {
            Double real = decoder.readDouble("Real");
            Double imaginary = decoder.readDouble("Imaginary");
            return new DoubleComplexNumberType(real, imaginary);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           DoubleComplexNumberType value) {
            encoder.writeDouble("Real", value.getReal());
            encoder.writeDouble("Imaginary", value.getImaginary());
        }
    }
}
