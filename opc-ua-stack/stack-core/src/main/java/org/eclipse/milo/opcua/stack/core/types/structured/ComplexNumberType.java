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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part8/5.6.4">https://reference.opcfoundation.org/v105/Core/docs/Part8/5.6.4</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ComplexNumberType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=12171");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=12181");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=12173");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15377");

    private final Float real;

    private final Float imaginary;

    public ComplexNumberType(Float real, Float imaginary) {
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

    public Float getReal() {
        return real;
    }

    public Float getImaginary() {
        return imaginary;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 12181),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Real", LocalizedText.NULL_VALUE, new NodeId(0, 10), -1, null, UInteger.valueOf(0), false),
                new StructureField("Imaginary", LocalizedText.NULL_VALUE, new NodeId(0, 10), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ComplexNumberType> {
        @Override
        public Class<ComplexNumberType> getType() {
            return ComplexNumberType.class;
        }

        @Override
        public ComplexNumberType decodeType(SerializationContext context, UaDecoder decoder) {
            Float real = decoder.readFloat("Real");
            Float imaginary = decoder.readFloat("Imaginary");
            return new ComplexNumberType(real, imaginary);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               ComplexNumberType value) {
            encoder.writeFloat("Real", value.getReal());
            encoder.writeFloat("Imaginary", value.getImaginary());
        }
    }
}
