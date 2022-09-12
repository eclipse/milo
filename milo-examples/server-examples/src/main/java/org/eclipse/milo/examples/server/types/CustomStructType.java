/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.examples.server.types;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.eclipse.milo.examples.server.ExampleNamespace;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class CustomStructType implements UaStructuredType {

    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse(String.format(
        "nsu=%s;s=%s",
        ExampleNamespace.NAMESPACE_URI,
        "DataType.CustomStructType"
    ));

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse(String.format(
        "nsu=%s;s=%s",
        ExampleNamespace.NAMESPACE_URI,
        "DataType.CustomStructType.BinaryEncoding"
    ));

    private final String foo;
    private final UInteger bar;
    private final boolean baz;

    private final CustomEnumType customEnumType;

    public CustomStructType() {
        this(null, uint(0), false, CustomEnumType.Field0);
    }

    public CustomStructType(String foo, UInteger bar, boolean baz, CustomEnumType customEnumType) {
        this.foo = foo;
        this.bar = bar;
        this.baz = baz;
        this.customEnumType = customEnumType;
    }

    public String getFoo() {
        return foo;
    }

    public UInteger getBar() {
        return bar;
    }

    public boolean isBaz() {
        return baz;
    }

    public CustomEnumType getCustomEnumType() {
        return customEnumType;
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
        // XML encoding not supported
        return ExpandedNodeId.NULL_VALUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomStructType that = (CustomStructType) o;
        return baz == that.baz &&
            Objects.equal(foo, that.foo) &&
            Objects.equal(bar, that.bar) &&
            customEnumType == that.customEnumType;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(foo, bar, baz, customEnumType);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("foo", foo)
            .add("bar", bar)
            .add("baz", baz)
            .add("customEnumType", customEnumType)
            .toString();
    }

    public static class Codec extends GenericDataTypeCodec<CustomStructType> {
        @Override
        public Class<CustomStructType> getType() {
            return CustomStructType.class;
        }

        @Override
        public CustomStructType decodeType(
            SerializationContext context,
            UaDecoder decoder
        ) throws UaSerializationException {

            String foo = decoder.readString("Foo");
            UInteger bar = decoder.readUInt32("Bar");
            boolean baz = decoder.readBoolean("Baz");
            CustomEnumType customEnumType = CustomEnumType.from(decoder.readEnum("CustomEnumType"));

            return new CustomStructType(foo, bar, baz, customEnumType);
        }

        @Override
        public void encodeType(
            SerializationContext context,
            UaEncoder encoder, CustomStructType value
        ) throws UaSerializationException {

            encoder.writeString("Foo", value.foo);
            encoder.writeUInt32("Bar", value.bar);
            encoder.writeBoolean("Baz", value.baz);
            encoder.writeEnum("CustomEnumType", value.customEnumType);
        }
    }

}
