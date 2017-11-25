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

package org.eclipse.milo.opcua.binaryschema;

import org.eclipse.milo.opcua.binaryschema.parser.BsdParser;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.testng.annotations.Test;

public class GenericBsdParserTest extends BsdParserTest {

    @Override
    protected BsdParser createBsdParser() {
        return new GenericBsdParser();
    }

    @Test
    public void testFoo() {
        Struct foo = Struct.builder("Foo")
            .addMember("Bar", 0)
            .addMember("Baz", "hello")
            .build();

        OpcUaBinaryDataTypeCodec<Object> codec = getCodec("Foo");

        assertRoundTrip("Foo", foo, codec);
    }

    @Test
    public void testOptionals_AllPresent() {
        Struct optionals = Struct.builder("Optionals")
            .addMember("OptionalInt32Specified", 1)
            .addMember("OptionalStringSpecified", 1)
            .addMember("Reserved1", 0)

            .addMember("OptionalInt32", 0)
            .addMember("OptionalString", "hello")
            .build();

        OpcUaBinaryDataTypeCodec<Object> codec = getCodec("Optionals");

        assertRoundTrip("Optionals", optionals, codec);
    }

    @Test
    public void testOptionals_OnePresent() {
        Struct optionals = Struct.builder("Optionals")
            .addMember("OptionalInt32Specified", 1)
            .addMember("OptionalStringSpecified", 0)
            .addMember("Reserved1", 0)

            .addMember("OptionalInt32", 0)
            .build();

        OpcUaBinaryDataTypeCodec<Object> codec = getCodec("Optionals");

        assertRoundTrip("Optionals", optionals, codec);
    }

    @Test
    public void testOptionals_NonePresent() {
        Struct optionals = Struct.builder("Optionals")
            .addMember("OptionalInt32Specified", 0)
            .addMember("OptionalStringSpecified", 0)
            .addMember("Reserved1", 0)
            .build();

        OpcUaBinaryDataTypeCodec<Object> codec = getCodec("Optionals");

        assertRoundTrip("Optionals", optionals, codec);
    }

}