package org.eclipse.milo.opcua.stack.core.types.enumerated.future;

import javax.annotation.Nullable;

import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

public enum StructureType implements UaEnumeration {
    Structure(0),

    StructureWithOptionalFields(1),

    Union(2);

    private final int value;

    StructureType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static StructureType from(int value) {
        switch (value) {
            case 0:
                return Structure;
            case 1:
                return StructureWithOptionalFields;
            case 2:
                return Union;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=98");
    }

    public static class Codec extends GenericDataTypeCodec<StructureType> {
        @Override
        public Class<StructureType> getType() {
            return StructureType.class;
        }

        @Override
        public StructureType decode(SerializationContext context, UaDecoder decoder) {
            return StructureType.from(decoder.readInt32(null));
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, StructureType value) {
            encoder.writeInt32(null, value.getValue());
        }
    }
}
