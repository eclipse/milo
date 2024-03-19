package org.eclipse.milo.opcua.test.types;

import java.util.StringJoiner;
import java.util.UUID;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureField;
import org.eclipse.milo.opcua.stack.core.util.codegen.EqualsBuilder;
import org.eclipse.milo.opcua.stack.core.util.codegen.HashCodeBuilder;
import org.jetbrains.annotations.Nullable;

public class StructWithArrayFieldsEx extends StructWithArrayFields implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=1;i=3008");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("ns=1;i=5035");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("ns=1;i=5040");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("ns=1;i=5036");

    private final Double @Nullable [] duration;

    public StructWithArrayFieldsEx(Boolean @Nullable [] _boolean, Byte @Nullable [] sByte,
                                   UByte @Nullable [] _byte, Short @Nullable [] int16, UShort @Nullable [] uInt16,
                                   Integer @Nullable [] int32, UInteger @Nullable [] uInt32, Long @Nullable [] int64,
                                   ULong @Nullable [] uInt64, Float @Nullable [] _float, Double @Nullable [] _double,
                                   String @Nullable [] string, DateTime @Nullable [] dateTime, UUID @Nullable [] guid,
                                   ByteString @Nullable [] byteString, XmlElement @Nullable [] xmlElement,
                                   NodeId @Nullable [] nodeId, ExpandedNodeId @Nullable [] expandedNodeId,
                                   StatusCode @Nullable [] statusCode, QualifiedName @Nullable [] qualifiedName,
                                   LocalizedText @Nullable [] localizedText, DataValue @Nullable [] dataValue,
                                   Variant @Nullable [] variant, Double @Nullable [] duration) {
        super(_boolean, sByte, _byte, int16, uInt16, int32, uInt32, int64, uInt64, _float, _double, string, dateTime, guid, byteString, xmlElement, nodeId, expandedNodeId, statusCode, qualifiedName, localizedText, dataValue, variant);
        this.duration = duration;
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

    public Double @Nullable [] getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object == null || getClass() != object.getClass()) {
            return false;
        }
        StructWithArrayFieldsEx that = (StructWithArrayFieldsEx) object;
        var eqb = new EqualsBuilder();
        eqb.appendSuper(super.equals(object));
        eqb.append(getDuration(), that.getDuration());
        return eqb.build();
    }

    @Override
    public int hashCode() {
        var hcb = new HashCodeBuilder();
        hcb.append(getDuration());
        hcb.appendSuper(super.hashCode());
        return hcb.build();
    }

    @Override
    public String toString() {
        var joiner = new StringJoiner(", ", StructWithArrayFieldsEx.class.getSimpleName() + "[", "]");
        joiner.add("duration=" + java.util.Arrays.toString(getDuration()));
        return joiner.toString();
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            ExpandedNodeId.parse("nsu=https://github.com/eclipse/milo/DataTypeTest;i=5035").toNodeId(namespaceTable).orElseThrow(),
            ExpandedNodeId.parse("nsu=https://github.com/eclipse/milo/DataTypeTest;i=3019").toNodeId(namespaceTable).orElseThrow(),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Boolean", LocalizedText.NULL_VALUE, new NodeId(0, 1), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("SByte", LocalizedText.NULL_VALUE, new NodeId(0, 2), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("Byte", LocalizedText.NULL_VALUE, new NodeId(0, 3), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("Int16", LocalizedText.NULL_VALUE, new NodeId(0, 4), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("UInt16", LocalizedText.NULL_VALUE, new NodeId(0, 5), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("Int32", LocalizedText.NULL_VALUE, new NodeId(0, 6), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("UInt32", LocalizedText.NULL_VALUE, new NodeId(0, 7), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("Int64", LocalizedText.NULL_VALUE, new NodeId(0, 8), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("UInt64", LocalizedText.NULL_VALUE, new NodeId(0, 9), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("Float", LocalizedText.NULL_VALUE, new NodeId(0, 10), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("Double", LocalizedText.NULL_VALUE, new NodeId(0, 11), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("String", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("DateTime", LocalizedText.NULL_VALUE, new NodeId(0, 13), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("Guid", LocalizedText.NULL_VALUE, new NodeId(0, 14), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("ByteString", LocalizedText.NULL_VALUE, new NodeId(0, 15), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("XmlElement", LocalizedText.NULL_VALUE, new NodeId(0, 16), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("NodeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("ExpandedNodeId", LocalizedText.NULL_VALUE, new NodeId(0, 18), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("StatusCode", LocalizedText.NULL_VALUE, new NodeId(0, 19), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("QualifiedName", LocalizedText.NULL_VALUE, new NodeId(0, 20), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("LocalizedText", LocalizedText.NULL_VALUE, new NodeId(0, 21), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("DataValue", LocalizedText.NULL_VALUE, new NodeId(0, 23), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("Variant", LocalizedText.NULL_VALUE, new NodeId(0, 24), 1, new UInteger[]{UInteger.valueOf(4)}, UInteger.valueOf(0), false),
                new StructureField("Duration", LocalizedText.NULL_VALUE, new NodeId(0, 290), 1, new UInteger[]{UInteger.valueOf(3)}, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<StructWithArrayFieldsEx> {
        @Override
        public Class<StructWithArrayFieldsEx> getType() {
            return StructWithArrayFieldsEx.class;
        }

        @Override
        public StructWithArrayFieldsEx decodeType(EncodingContext context, UaDecoder decoder) {
            Boolean[] _boolean = decoder.decodeBooleanArray("Boolean");
            Byte[] sByte = decoder.decodeSByteArray("SByte");
            UByte[] _byte = decoder.decodeByteArray("Byte");
            Short[] int16 = decoder.decodeInt16Array("Int16");
            UShort[] uInt16 = decoder.decodeUInt16Array("UInt16");
            Integer[] int32 = decoder.decodeInt32Array("Int32");
            UInteger[] uInt32 = decoder.decodeUInt32Array("UInt32");
            Long[] int64 = decoder.decodeInt64Array("Int64");
            ULong[] uInt64 = decoder.decodeUInt64Array("UInt64");
            Float[] _float = decoder.decodeFloatArray("Float");
            Double[] _double = decoder.decodeDoubleArray("Double");
            String[] string = decoder.decodeStringArray("String");
            DateTime[] dateTime = decoder.decodeDateTimeArray("DateTime");
            UUID[] guid = decoder.decodeGuidArray("Guid");
            ByteString[] byteString = decoder.decodeByteStringArray("ByteString");
            XmlElement[] xmlElement = decoder.decodeXmlElementArray("XmlElement");
            NodeId[] nodeId = decoder.decodeNodeIdArray("NodeId");
            ExpandedNodeId[] expandedNodeId = decoder.decodeExpandedNodeIdArray("ExpandedNodeId");
            StatusCode[] statusCode = decoder.decodeStatusCodeArray("StatusCode");
            QualifiedName[] qualifiedName = decoder.decodeQualifiedNameArray("QualifiedName");
            LocalizedText[] localizedText = decoder.decodeLocalizedTextArray("LocalizedText");
            DataValue[] dataValue = decoder.decodeDataValueArray("DataValue");
            Variant[] variant = decoder.decodeVariantArray("Variant");
            Double[] duration = decoder.decodeDoubleArray("Duration");
            return new StructWithArrayFieldsEx(_boolean, sByte, _byte, int16, uInt16, int32, uInt32, int64, uInt64, _float, _double, string, dateTime, guid, byteString, xmlElement, nodeId, expandedNodeId, statusCode, qualifiedName, localizedText, dataValue, variant, duration);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               StructWithArrayFieldsEx value) {
            encoder.encodeBooleanArray("Boolean", value.getBoolean());
            encoder.encodeSByteArray("SByte", value.getSByte());
            encoder.encodeByteArray("Byte", value.getByte());
            encoder.encodeInt16Array("Int16", value.getInt16());
            encoder.encodeUInt16Array("UInt16", value.getUInt16());
            encoder.encodeInt32Array("Int32", value.getInt32());
            encoder.encodeUInt32Array("UInt32", value.getUInt32());
            encoder.encodeInt64Array("Int64", value.getInt64());
            encoder.encodeUInt64Array("UInt64", value.getUInt64());
            encoder.encodeFloatArray("Float", value.getFloat());
            encoder.encodeDoubleArray("Double", value.getDouble());
            encoder.encodeStringArray("String", value.getString());
            encoder.encodeDateTimeArray("DateTime", value.getDateTime());
            encoder.encodeGuidArray("Guid", value.getGuid());
            encoder.encodeByteStringArray("ByteString", value.getByteString());
            encoder.encodeXmlElementArray("XmlElement", value.getXmlElement());
            encoder.encodeNodeIdArray("NodeId", value.getNodeId());
            encoder.encodeExpandedNodeIdArray("ExpandedNodeId", value.getExpandedNodeId());
            encoder.encodeStatusCodeArray("StatusCode", value.getStatusCode());
            encoder.encodeQualifiedNameArray("QualifiedName", value.getQualifiedName());
            encoder.encodeLocalizedTextArray("LocalizedText", value.getLocalizedText());
            encoder.encodeDataValueArray("DataValue", value.getDataValue());
            encoder.encodeVariantArray("Variant", value.getVariant());
            encoder.encodeDoubleArray("Duration", value.getDuration());
        }
    }
}
