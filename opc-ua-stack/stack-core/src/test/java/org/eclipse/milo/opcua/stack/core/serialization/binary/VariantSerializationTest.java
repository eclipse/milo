/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.serialization.binary;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.TestSerializationContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceCounterDataType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class VariantSerializationTest extends BinarySerializationFixture {

    public static Object[][] getVariants() {
        return new Object[][]{
            {new Variant(null)},
            {new Variant("hello, world")},
            {new Variant(42)},
            {new Variant(new Integer[]{0, 1, 2, 3})},
            {new Variant(new Integer[][]{{0, 1}, {2, 3}})},
            {new Variant(new Long[]{0L, 1L, 2L, 3L})},
            {new Variant(new Long[][]{{0L, 1L}, {2L, 3L}})},
            {new Variant(new UInteger[]{uint(0), uint(1), uint(2), uint(3)})},
            {new Variant(new UInteger[][]{{uint(0), uint(1)}, {uint(2), uint(3)}})},
            {new Variant(new Variant[]{new Variant(0), new Variant(1), new Variant(2)})}
        };
    }

    @ParameterizedTest
    @MethodSource("getVariants")
    public void testVariantRoundTrip(Variant variant) {
        writer.writeVariant(variant);
        Variant decoded = reader.readVariant();

        assertEquals(variant, decoded);
    }

    @Test
    public void testVariant_UaStructure() {
        ServiceCounterDataType sc1 = new ServiceCounterDataType(
            uint(1),
            uint(2)
        );

        Variant v = new Variant(sc1);
        writer.writeVariant(v);
        Variant decoded = reader.readVariant();

        ExtensionObject extensionObject = (ExtensionObject) decoded.getValue();
        ServiceCounterDataType sc2 = (ServiceCounterDataType) extensionObject.decode(new TestSerializationContext());

        assertEquals(sc2.getTotalCount(), sc1.getTotalCount());
        assertEquals(sc2.getErrorCount(), sc1.getErrorCount());
    }

    public static Object[][] getPrimitiveArrayVariants() {
        return new Object[][]{
            {new Variant(new int[]{0, 1, 2, 3}),
                new Variant(new Integer[]{0, 1, 2, 3})},

            {new Variant(new int[][]{{0, 1}, {2, 3}}),
                new Variant(new Integer[][]{{0, 1}, {2, 3}})},

            {new Variant(new long[]{0L, 1L, 2L, 3L}),
                new Variant(new Long[]{0L, 1L, 2L, 3L})},

            {new Variant(new long[][]{{0L, 1L}, {2L, 3L}}),
                new Variant(new Long[][]{{0L, 1L}, {2L, 3L}})}
        };
    }

    @ParameterizedTest
    @MethodSource("getPrimitiveArrayVariants")
    @DisplayName("Test that after primitive array types given to variants come out as expected after encoding/decoding.")
    public void testPrimitiveArrayVariantRoundTrip(Variant variant, Variant expected) {
        writer.writeVariant(variant);
        Variant decoded = reader.readVariant();

        assertEquals(expected, decoded);
    }

    @Test
    @DisplayName("Test that a Variant containing a null array encoded with a negative array size to indicate a null value decodes properly.")
    public void testNullArrayEncodedWithNegativeArraySize() {
        ByteBuf buffer = Unpooled.buffer();

        buffer.writeByte(BuiltinDataType.Int16.getTypeId() | (1 << 7));
        buffer.writeIntLE(-1);

        OpcUaBinaryStreamDecoder reader = new OpcUaBinaryStreamDecoder(new TestSerializationContext());
        reader.setBuffer(buffer);

        Variant v = reader.readVariant();

        assertNotNull(v);
        assertNull(v.getValue());
    }

}
