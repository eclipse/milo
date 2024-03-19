package org.eclipse.milo.opcua.test.types;

import java.util.StringJoiner;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.eclipse.milo.opcua.stack.core.types.structured.Structure;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureField;
import org.eclipse.milo.opcua.stack.core.util.codegen.EqualsBuilder;
import org.eclipse.milo.opcua.stack.core.util.codegen.HashCodeBuilder;
import org.jetbrains.annotations.Nullable;

public class StructWithOptionalArrayFields extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=1;i=3007");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("ns=1;i=5017");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("ns=1;i=5019");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("ns=1;i=5018");

    private final Integer @Nullable [] int32;

    private final Integer @Nullable [] optionalInt32;

    private final String @Nullable [] string;

    private final String @Nullable [] optionalString;

    private final Double @Nullable [] duration;

    private final Double @Nullable [] optionalDuration;

    private final ConcreteTestType @Nullable [] concreteTestType;

    private final ConcreteTestType @Nullable [] optionalConcreteTestType;

    public StructWithOptionalArrayFields(Integer @Nullable [] int32,
                                         Integer @Nullable [] optionalInt32, String @Nullable [] string,
                                         String @Nullable [] optionalString, Double @Nullable [] duration,
                                         Double @Nullable [] optionalDuration, ConcreteTestType @Nullable [] concreteTestType,
                                         ConcreteTestType @Nullable [] optionalConcreteTestType) {
        this.int32 = int32;
        this.optionalInt32 = optionalInt32;
        this.string = string;
        this.optionalString = optionalString;
        this.duration = duration;
        this.optionalDuration = optionalDuration;
        this.concreteTestType = concreteTestType;
        this.optionalConcreteTestType = optionalConcreteTestType;
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
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public Integer @Nullable [] getInt32() {
        return int32;
    }

    public Integer @Nullable [] getOptionalInt32() {
        return optionalInt32;
    }

    public String @Nullable [] getString() {
        return string;
    }

    public String @Nullable [] getOptionalString() {
        return optionalString;
    }

    public Double @Nullable [] getDuration() {
        return duration;
    }

    public Double @Nullable [] getOptionalDuration() {
        return optionalDuration;
    }

    public ConcreteTestType @Nullable [] getConcreteTestType() {
        return concreteTestType;
    }

    public ConcreteTestType @Nullable [] getOptionalConcreteTestType() {
        return optionalConcreteTestType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object == null || getClass() != object.getClass()) {
            return false;
        }
        StructWithOptionalArrayFields that = (StructWithOptionalArrayFields) object;
        var eqb = new EqualsBuilder();
        eqb.append(getInt32(), that.getInt32());
        eqb.append(getOptionalInt32(), that.getOptionalInt32());
        eqb.append(getString(), that.getString());
        eqb.append(getOptionalString(), that.getOptionalString());
        eqb.append(getDuration(), that.getDuration());
        eqb.append(getOptionalDuration(), that.getOptionalDuration());
        eqb.append(getConcreteTestType(), that.getConcreteTestType());
        eqb.append(getOptionalConcreteTestType(), that.getOptionalConcreteTestType());
        return eqb.build();
    }

    @Override
    public int hashCode() {
        var hcb = new HashCodeBuilder();
        hcb.append(getInt32());
        hcb.append(getOptionalInt32());
        hcb.append(getString());
        hcb.append(getOptionalString());
        hcb.append(getDuration());
        hcb.append(getOptionalDuration());
        hcb.append(getConcreteTestType());
        hcb.append(getOptionalConcreteTestType());
        return hcb.build();
    }

    @Override
    public String toString() {
        var joiner = new StringJoiner(", ", StructWithOptionalArrayFields.class.getSimpleName() + "[", "]");
        joiner.add("int32=" + java.util.Arrays.toString(getInt32()));
        joiner.add("optionalInt32=" + java.util.Arrays.toString(getOptionalInt32()));
        joiner.add("string=" + java.util.Arrays.toString(getString()));
        joiner.add("optionalString=" + java.util.Arrays.toString(getOptionalString()));
        joiner.add("duration=" + java.util.Arrays.toString(getDuration()));
        joiner.add("optionalDuration=" + java.util.Arrays.toString(getOptionalDuration()));
        joiner.add("concreteTestType=" + java.util.Arrays.toString(getConcreteTestType()));
        joiner.add("optionalConcreteTestType=" + java.util.Arrays.toString(getOptionalConcreteTestType()));
        return joiner.toString();
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            ExpandedNodeId.parse("nsu=https://github.com/eclipse/milo/DataTypeTest;i=5017").toNodeId(namespaceTable).orElseThrow(),
            ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=22").toNodeId(namespaceTable).orElseThrow(),
            StructureType.StructureWithOptionalFields,
            new StructureField[]{
                new StructureField("Int32", LocalizedText.NULL_VALUE, new NodeId(0, 6), 1, new UInteger[]{UInteger.valueOf(3)}, UInteger.valueOf(0), false),
                new StructureField("OptionalInt32", LocalizedText.NULL_VALUE, new NodeId(0, 6), 1, new UInteger[]{UInteger.valueOf(3)}, UInteger.valueOf(0), true),
                new StructureField("String", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, new UInteger[]{UInteger.valueOf(3)}, UInteger.valueOf(0), false),
                new StructureField("OptionalString", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, new UInteger[]{UInteger.valueOf(3)}, UInteger.valueOf(0), true),
                new StructureField("Duration", LocalizedText.NULL_VALUE, new NodeId(0, 290), 1, new UInteger[]{UInteger.valueOf(0)}, UInteger.valueOf(0), false),
                new StructureField("OptionalDuration", LocalizedText.NULL_VALUE, new NodeId(0, 290), 1, new UInteger[]{UInteger.valueOf(0)}, UInteger.valueOf(0), true),
                new StructureField("ConcreteTestType", LocalizedText.NULL_VALUE, ExpandedNodeId.parse("nsu=https://github.com/eclipse/milo/DataTypeTest;i=3006").toNodeId(namespaceTable).orElseThrow(), 1, new UInteger[]{UInteger.valueOf(0)}, UInteger.valueOf(0), false),
                new StructureField("OptionalConcreteTestType", LocalizedText.NULL_VALUE, ExpandedNodeId.parse("nsu=https://github.com/eclipse/milo/DataTypeTest;i=3006").toNodeId(namespaceTable).orElseThrow(), 1, new UInteger[]{UInteger.valueOf(0)}, UInteger.valueOf(0), true)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<StructWithOptionalArrayFields> {
        @Override
        public Class<StructWithOptionalArrayFields> getType() {
            return StructWithOptionalArrayFields.class;
        }

        @Override
        public StructWithOptionalArrayFields decodeType(EncodingContext context, UaDecoder decoder) {
            long encodingMask = decoder.decodeUInt32("EncodingMask").longValue();
            Integer[] int32 = decoder.decodeInt32Array("Int32");
            Integer[] optionalInt32 = null;
            if ((encodingMask & (1L << 0)) != 0) {
                optionalInt32 = decoder.decodeInt32Array("OptionalInt32");
            }
            String[] string = decoder.decodeStringArray("String");
            String[] optionalString = null;
            if ((encodingMask & (1L << 1)) != 0) {
                optionalString = decoder.decodeStringArray("OptionalString");
            }
            Double[] duration = decoder.decodeDoubleArray("Duration");
            Double[] optionalDuration = null;
            if ((encodingMask & (1L << 2)) != 0) {
                optionalDuration = decoder.decodeDoubleArray("OptionalDuration");
            }
            ConcreteTestType[] concreteTestType = (ConcreteTestType[]) decoder.decodeStructArray("ConcreteTestType", ConcreteTestType.TYPE_ID);
            ConcreteTestType[] optionalConcreteTestType = null;
            if ((encodingMask & (1L << 3)) != 0) {
                optionalConcreteTestType = (ConcreteTestType[]) decoder.decodeStructArray("OptionalConcreteTestType", ConcreteTestType.TYPE_ID);
            }
            return new StructWithOptionalArrayFields(int32, optionalInt32, string, optionalString, duration, optionalDuration, concreteTestType, optionalConcreteTestType);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               StructWithOptionalArrayFields value) {
            long encodingMask = 0L;
            if (value.getOptionalInt32() != null) {
                encodingMask |= (1L << 0);
            }
            if (value.getOptionalString() != null) {
                encodingMask |= (1L << 1);
            }
            if (value.getOptionalDuration() != null) {
                encodingMask |= (1L << 2);
            }
            if (value.getOptionalConcreteTestType() != null) {
                encodingMask |= (1L << 3);
            }
            encoder.encodeUInt32("EncodingMask", Unsigned.uint(encodingMask));
            encoder.encodeInt32Array("Int32", value.getInt32());
            if (value.getOptionalInt32() != null) {
                encoder.encodeInt32Array("OptionalInt32", value.getOptionalInt32());
            }
            encoder.encodeStringArray("String", value.getString());
            if (value.getOptionalString() != null) {
                encoder.encodeStringArray("OptionalString", value.getOptionalString());
            }
            encoder.encodeDoubleArray("Duration", value.getDuration());
            if (value.getOptionalDuration() != null) {
                encoder.encodeDoubleArray("OptionalDuration", value.getOptionalDuration());
            }
            encoder.encodeStructArray("ConcreteTestType", value.getConcreteTestType(), ConcreteTestType.TYPE_ID);
            if (value.getOptionalConcreteTestType() != null) {
                encoder.encodeStructArray("OptionalConcreteTestType", value.getOptionalConcreteTestType(), ConcreteTestType.TYPE_ID);
            }
        }
    }
}
