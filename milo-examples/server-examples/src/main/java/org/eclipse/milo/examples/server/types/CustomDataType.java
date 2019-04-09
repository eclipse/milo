/*
 * Copyright (c) 2019 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class CustomDataType {

    private final String foo;
    private final UInteger bar;
    private final boolean baz;

    public CustomDataType() {
        this(null, uint(0), false);
    }

    public CustomDataType(String foo, UInteger bar, boolean baz) {
        this.foo = foo;
        this.bar = bar;
        this.baz = baz;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomDataType that = (CustomDataType) o;
        return baz == that.baz &&
            Objects.equal(foo, that.foo) &&
            Objects.equal(bar, that.bar);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(foo, bar, baz);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("foo", foo)
            .add("bar", bar)
            .add("baz", baz)
            .toString();
    }

    public static class Codec extends GenericDataTypeCodec<CustomDataType> {
        @Override
        public Class<CustomDataType> getType() {
            return CustomDataType.class;
        }

        @Override
        public CustomDataType decode(
            SerializationContext context,
            UaDecoder decoder) throws UaSerializationException {

            String foo = decoder.readString("Foo");
            UInteger bar = decoder.readUInt32("Bar");
            boolean baz = decoder.readBoolean("Baz");

            return new CustomDataType(foo, bar, baz);
        }

        @Override
        public void encode(
            SerializationContext context,
            UaEncoder encoder, CustomDataType value) throws UaSerializationException {

            encoder.writeString("Foo", value.foo);
            encoder.writeUInt32("Bar", value.bar);
            encoder.writeBoolean("Baz", value.baz);
        }
    }

}
