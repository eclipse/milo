/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.serialization.binary;

import io.netty.buffer.ByteBuf;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.util.BufferUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;
import static org.testng.Assert.assertEquals;

public class DataValueSerializationTest {

    private final OpcUaBinaryStreamEncoder encoder = new OpcUaBinaryStreamEncoder();
    private final OpcUaBinaryStreamDecoder decoder = new OpcUaBinaryStreamDecoder();

    @Test(dataProvider = "getValues")
    public void testDataValueRoundTrip(DataValue value) {
        ByteBuf buffer = BufferUtil.buffer();
        encoder.setBuffer(buffer);
        encoder.writeDataValue(value);

        decoder.setBuffer(buffer);
        DataValue decodedValue = decoder.readDataValue();

        assertEquals(decodedValue, value);
    }

    @DataProvider
    public Object[][] getValues() {
        return new Object[][]{
            {
                new DataValue(
                    Variant.NULL_VALUE,
                    StatusCode.GOOD,
                    DateTime.now(),
                    ushort(1),
                    DateTime.now(),
                    ushort(1))
            },
            {
                new DataValue(
                    Variant.NULL_VALUE,
                    StatusCode.GOOD,
                    DateTime.now(),
                    null,
                    DateTime.now(),
                    ushort(1))
            },
            {
                new DataValue(
                    Variant.NULL_VALUE,
                    StatusCode.GOOD,
                    DateTime.now(),
                    ushort(1),
                    DateTime.now(),
                    null)
            },
            {
                new DataValue(
                    Variant.NULL_VALUE,
                    StatusCode.GOOD,
                    DateTime.now(),
                    DateTime.now())
            },
            {
                new DataValue(
                    Variant.NULL_VALUE,
                    StatusCode.GOOD,
                    DateTime.MIN_VALUE,
                    DateTime.now())
            },
            {
                new DataValue(
                    Variant.NULL_VALUE,
                    StatusCode.GOOD,
                    DateTime.now(),
                    DateTime.MIN_VALUE)
            },
            {
                new DataValue(
                    new Variant(1),
                    StatusCode.GOOD,
                    DateTime.now(),
                    DateTime.MIN_VALUE)
            },
            {
                new DataValue(
                    new Variant(1),
                    StatusCode.BAD,
                    DateTime.now(),
                    DateTime.MIN_VALUE)
            },
            {
                new DataValue(
                    Variant.NULL_VALUE,
                    StatusCode.BAD,
                    DateTime.now(),
                    DateTime.MIN_VALUE)
            }
        };
    }

}
