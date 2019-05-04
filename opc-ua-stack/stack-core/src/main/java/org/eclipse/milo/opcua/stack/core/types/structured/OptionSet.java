package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class OptionSet extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12755");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12757");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12765");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15084");

    private final ByteString value;

    private final ByteString validBits;

    public OptionSet(ByteString value, ByteString validBits) {
        this.value = value;
        this.validBits = validBits;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    public ByteString getValue() {
        return value;
    }

    public ByteString getValidBits() {
        return validBits;
    }

    public static final class Codec extends GenericDataTypeCodec<OptionSet> {
        @Override
        public Class<OptionSet> getType() {
            return OptionSet.class;
        }

        @Override
        public OptionSet decode(SerializationContext context, UaDecoder decoder) {
            ByteString value = decoder.readByteString("Value");
            ByteString validBits = decoder.readByteString("ValidBits");
            return new OptionSet(value, validBits);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, OptionSet value) {
            encoder.writeByteString("Value", value.getValue());
            encoder.writeByteString("ValidBits", value.getValidBits());
        }
    }
}
