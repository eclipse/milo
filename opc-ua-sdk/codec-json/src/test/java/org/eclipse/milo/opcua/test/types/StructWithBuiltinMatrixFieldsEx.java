package org.eclipse.milo.opcua.test.types;

import java.util.StringJoiner;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.Matrix;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureField;
import org.eclipse.milo.opcua.stack.core.types.structured.XVType;
import org.eclipse.milo.opcua.stack.core.util.codegen.EqualsBuilder;
import org.eclipse.milo.opcua.stack.core.util.codegen.HashCodeBuilder;
import org.jetbrains.annotations.Nullable;

public class StructWithBuiltinMatrixFieldsEx extends StructWithBuiltinMatrixFields implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=1;i=3017");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("ns=1;i=5044");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("ns=1;i=5046");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("ns=1;i=5045");

    private final @Nullable Matrix duration;

    private final @Nullable Matrix applicationType;

    private final @Nullable Matrix testEnumType;

    private final @Nullable Matrix xvType;

    private final @Nullable Matrix concreteTestType;

    private final @Nullable Matrix unionOfScalar;

    private final @Nullable Matrix unionOfArray;

    public StructWithBuiltinMatrixFieldsEx(@Nullable Matrix _boolean, @Nullable Matrix sByte,
                                           @Nullable Matrix _byte, @Nullable Matrix int16, @Nullable Matrix uInt16,
                                           @Nullable Matrix int32, @Nullable Matrix uInt32, @Nullable Matrix int64,
                                           @Nullable Matrix uInt64, @Nullable Matrix _float, @Nullable Matrix _double,
                                           @Nullable Matrix string, @Nullable Matrix dateTime, @Nullable Matrix guid,
                                           @Nullable Matrix byteString, @Nullable Matrix xmlElement, @Nullable Matrix nodeId,
                                           @Nullable Matrix expandedNodeId, @Nullable Matrix statusCode, @Nullable Matrix qualifiedName,
                                           @Nullable Matrix localizedText, @Nullable Matrix dataValue, @Nullable Matrix variant,
                                           @Nullable Matrix duration, @Nullable Matrix applicationType, @Nullable Matrix testEnumType,
                                           @Nullable Matrix xvType, @Nullable Matrix concreteTestType, @Nullable Matrix unionOfScalar,
                                           @Nullable Matrix unionOfArray) {
        super(_boolean, sByte, _byte, int16, uInt16, int32, uInt32, int64, uInt64, _float, _double, string, dateTime, guid, byteString, xmlElement, nodeId, expandedNodeId, statusCode, qualifiedName, localizedText, dataValue, variant);
        this.duration = duration;
        this.applicationType = applicationType;
        this.testEnumType = testEnumType;
        this.xvType = xvType;
        this.concreteTestType = concreteTestType;
        this.unionOfScalar = unionOfScalar;
        this.unionOfArray = unionOfArray;
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

    public @Nullable Matrix getDuration() {
        return duration;
    }

    public @Nullable Matrix getApplicationType() {
        return applicationType;
    }

    public @Nullable Matrix getTestEnumType() {
        return testEnumType;
    }

    public @Nullable Matrix getXvType() {
        return xvType;
    }

    public @Nullable Matrix getConcreteTestType() {
        return concreteTestType;
    }

    public @Nullable Matrix getUnionOfScalar() {
        return unionOfScalar;
    }

    public @Nullable Matrix getUnionOfArray() {
        return unionOfArray;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object == null || getClass() != object.getClass()) {
            return false;
        }
        StructWithBuiltinMatrixFieldsEx that = (StructWithBuiltinMatrixFieldsEx) object;
        var eqb = new EqualsBuilder();
        eqb.appendSuper(super.equals(object));
        eqb.append(getDuration(), that.getDuration());
        eqb.append(getApplicationType(), that.getApplicationType());
        eqb.append(getTestEnumType(), that.getTestEnumType());
        eqb.append(getXvType(), that.getXvType());
        eqb.append(getConcreteTestType(), that.getConcreteTestType());
        eqb.append(getUnionOfScalar(), that.getUnionOfScalar());
        eqb.append(getUnionOfArray(), that.getUnionOfArray());
        return eqb.build();
    }

    @Override
    public int hashCode() {
        var hcb = new HashCodeBuilder();
        hcb.append(getDuration());
        hcb.append(getApplicationType());
        hcb.append(getTestEnumType());
        hcb.append(getXvType());
        hcb.append(getConcreteTestType());
        hcb.append(getUnionOfScalar());
        hcb.append(getUnionOfArray());
        hcb.appendSuper(super.hashCode());
        return hcb.build();
    }

    @Override
    public String toString() {
        var joiner = new StringJoiner(", ", StructWithBuiltinMatrixFieldsEx.class.getSimpleName() + "[", "]");
        joiner.add("duration=" + getDuration());
        joiner.add("applicationType=" + getApplicationType());
        joiner.add("testEnumType=" + getTestEnumType());
        joiner.add("xvType=" + getXvType());
        joiner.add("concreteTestType=" + getConcreteTestType());
        joiner.add("unionOfScalar=" + getUnionOfScalar());
        joiner.add("unionOfArray=" + getUnionOfArray());
        return joiner.toString();
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            ExpandedNodeId.parse("nsu=https://github.com/eclipse/milo/DataTypeTest;i=5044").toNodeId(namespaceTable).orElseThrow(),
            ExpandedNodeId.parse("nsu=https://github.com/eclipse/milo/DataTypeTest;i=3010").toNodeId(namespaceTable).orElseThrow(),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Boolean", LocalizedText.NULL_VALUE, new NodeId(0, 1), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("SByte", LocalizedText.NULL_VALUE, new NodeId(0, 2), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("Byte", LocalizedText.NULL_VALUE, new NodeId(0, 3), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("Int16", LocalizedText.NULL_VALUE, new NodeId(0, 4), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("UInt16", LocalizedText.NULL_VALUE, new NodeId(0, 5), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("Int32", LocalizedText.NULL_VALUE, new NodeId(0, 6), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("UInt32", LocalizedText.NULL_VALUE, new NodeId(0, 7), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("Int64", LocalizedText.NULL_VALUE, new NodeId(0, 8), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("UInt64", LocalizedText.NULL_VALUE, new NodeId(0, 9), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("Float", LocalizedText.NULL_VALUE, new NodeId(0, 10), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("Double", LocalizedText.NULL_VALUE, new NodeId(0, 11), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("String", LocalizedText.NULL_VALUE, new NodeId(0, 12), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("DateTime", LocalizedText.NULL_VALUE, new NodeId(0, 13), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("Guid", LocalizedText.NULL_VALUE, new NodeId(0, 14), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("ByteString", LocalizedText.NULL_VALUE, new NodeId(0, 15), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("XmlElement", LocalizedText.NULL_VALUE, new NodeId(0, 16), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("NodeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("ExpandedNodeId", LocalizedText.NULL_VALUE, new NodeId(0, 18), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("StatusCode", LocalizedText.NULL_VALUE, new NodeId(0, 19), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("QualifiedName", LocalizedText.NULL_VALUE, new NodeId(0, 20), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("LocalizedText", LocalizedText.NULL_VALUE, new NodeId(0, 21), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("DataValue", LocalizedText.NULL_VALUE, new NodeId(0, 23), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("Variant", LocalizedText.NULL_VALUE, new NodeId(0, 24), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("Duration", LocalizedText.NULL_VALUE, new NodeId(0, 290), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("ApplicationType", LocalizedText.NULL_VALUE, new NodeId(0, 307), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("TestEnumType", LocalizedText.NULL_VALUE, ExpandedNodeId.parse("nsu=https://github.com/eclipse/milo/DataTypeTest;i=3011").toNodeId(namespaceTable).orElseThrow(), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("XVType", LocalizedText.NULL_VALUE, new NodeId(0, 12080), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("ConcreteTestType", LocalizedText.NULL_VALUE, ExpandedNodeId.parse("nsu=https://github.com/eclipse/milo/DataTypeTest;i=3006").toNodeId(namespaceTable).orElseThrow(), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("UnionOfScalar", LocalizedText.NULL_VALUE, ExpandedNodeId.parse("nsu=https://github.com/eclipse/milo/DataTypeTest;i=3020").toNodeId(namespaceTable).orElseThrow(), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false),
                new StructureField("UnionOfArray", LocalizedText.NULL_VALUE, ExpandedNodeId.parse("nsu=https://github.com/eclipse/milo/DataTypeTest;i=3023").toNodeId(namespaceTable).orElseThrow(), 2, new UInteger[]{UInteger.valueOf(2), UInteger.valueOf(2)}, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<StructWithBuiltinMatrixFieldsEx> {
        @Override
        public Class<StructWithBuiltinMatrixFieldsEx> getType() {
            return StructWithBuiltinMatrixFieldsEx.class;
        }

        @Override
        public StructWithBuiltinMatrixFieldsEx decodeType(EncodingContext context, UaDecoder decoder) {
            Matrix _boolean = decoder.decodeMatrix("Boolean", BuiltinDataType.Boolean);
            Matrix sByte = decoder.decodeMatrix("SByte", BuiltinDataType.SByte);
            Matrix _byte = decoder.decodeMatrix("Byte", BuiltinDataType.Byte);
            Matrix int16 = decoder.decodeMatrix("Int16", BuiltinDataType.Int16);
            Matrix uInt16 = decoder.decodeMatrix("UInt16", BuiltinDataType.UInt16);
            Matrix int32 = decoder.decodeMatrix("Int32", BuiltinDataType.Int32);
            Matrix uInt32 = decoder.decodeMatrix("UInt32", BuiltinDataType.UInt32);
            Matrix int64 = decoder.decodeMatrix("Int64", BuiltinDataType.Int64);
            Matrix uInt64 = decoder.decodeMatrix("UInt64", BuiltinDataType.UInt64);
            Matrix _float = decoder.decodeMatrix("Float", BuiltinDataType.Float);
            Matrix _double = decoder.decodeMatrix("Double", BuiltinDataType.Double);
            Matrix string = decoder.decodeMatrix("String", BuiltinDataType.String);
            Matrix dateTime = decoder.decodeMatrix("DateTime", BuiltinDataType.DateTime);
            Matrix guid = decoder.decodeMatrix("Guid", BuiltinDataType.Guid);
            Matrix byteString = decoder.decodeMatrix("ByteString", BuiltinDataType.ByteString);
            Matrix xmlElement = decoder.decodeMatrix("XmlElement", BuiltinDataType.XmlElement);
            Matrix nodeId = decoder.decodeMatrix("NodeId", BuiltinDataType.NodeId);
            Matrix expandedNodeId = decoder.decodeMatrix("ExpandedNodeId", BuiltinDataType.ExpandedNodeId);
            Matrix statusCode = decoder.decodeMatrix("StatusCode", BuiltinDataType.StatusCode);
            Matrix qualifiedName = decoder.decodeMatrix("QualifiedName", BuiltinDataType.QualifiedName);
            Matrix localizedText = decoder.decodeMatrix("LocalizedText", BuiltinDataType.LocalizedText);
            Matrix dataValue = decoder.decodeMatrix("DataValue", BuiltinDataType.DataValue);
            Matrix variant = decoder.decodeMatrix("Variant", BuiltinDataType.Variant);
            Matrix duration = decoder.decodeMatrix("Duration", BuiltinDataType.Double);
            Matrix applicationType = decoder.decodeEnumMatrix("ApplicationType");
            Matrix testEnumType = decoder.decodeEnumMatrix("TestEnumType");
            Matrix xvType = (Matrix) decoder.decodeStructMatrix("XVType", XVType.TYPE_ID);
            Matrix concreteTestType = (Matrix) decoder.decodeStructMatrix("ConcreteTestType", ConcreteTestType.TYPE_ID);
            Matrix unionOfScalar = (Matrix) decoder.decodeStructMatrix("UnionOfScalar", UnionOfScalar.TYPE_ID);
            Matrix unionOfArray = (Matrix) decoder.decodeStructMatrix("UnionOfArray", UnionOfArray.TYPE_ID);
            return new StructWithBuiltinMatrixFieldsEx(_boolean, sByte, _byte, int16, uInt16, int32, uInt32, int64, uInt64, _float, _double, string, dateTime, guid, byteString, xmlElement, nodeId, expandedNodeId, statusCode, qualifiedName, localizedText, dataValue, variant, duration, applicationType, testEnumType, xvType, concreteTestType, unionOfScalar, unionOfArray);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               StructWithBuiltinMatrixFieldsEx value) {
            encoder.encodeMatrix("Boolean", value.getBoolean());
            encoder.encodeMatrix("SByte", value.getSByte());
            encoder.encodeMatrix("Byte", value.getByte());
            encoder.encodeMatrix("Int16", value.getInt16());
            encoder.encodeMatrix("UInt16", value.getUInt16());
            encoder.encodeMatrix("Int32", value.getInt32());
            encoder.encodeMatrix("UInt32", value.getUInt32());
            encoder.encodeMatrix("Int64", value.getInt64());
            encoder.encodeMatrix("UInt64", value.getUInt64());
            encoder.encodeMatrix("Float", value.getFloat());
            encoder.encodeMatrix("Double", value.getDouble());
            encoder.encodeMatrix("String", value.getString());
            encoder.encodeMatrix("DateTime", value.getDateTime());
            encoder.encodeMatrix("Guid", value.getGuid());
            encoder.encodeMatrix("ByteString", value.getByteString());
            encoder.encodeMatrix("XmlElement", value.getXmlElement());
            encoder.encodeMatrix("NodeId", value.getNodeId());
            encoder.encodeMatrix("ExpandedNodeId", value.getExpandedNodeId());
            encoder.encodeMatrix("StatusCode", value.getStatusCode());
            encoder.encodeMatrix("QualifiedName", value.getQualifiedName());
            encoder.encodeMatrix("LocalizedText", value.getLocalizedText());
            encoder.encodeMatrix("DataValue", value.getDataValue());
            encoder.encodeMatrix("Variant", value.getVariant());
            encoder.encodeMatrix("Duration", value.getDuration());
            encoder.encodeEnumMatrix("ApplicationType", value.getApplicationType());
            encoder.encodeEnumMatrix("TestEnumType", value.getTestEnumType());
            encoder.encodeStructMatrix("XVType", value.getXvType(), XVType.TYPE_ID);
            encoder.encodeStructMatrix("ConcreteTestType", value.getConcreteTestType(), ConcreteTestType.TYPE_ID);
            encoder.encodeStructMatrix("UnionOfScalar", value.getUnionOfScalar(), UnionOfScalar.TYPE_ID);
            encoder.encodeStructMatrix("UnionOfArray", value.getUnionOfArray(), UnionOfArray.TYPE_ID);
        }
    }
}
