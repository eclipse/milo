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

package org.eclipse.milo.opcua.binaryschema.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.eclipse.milo.opcua.binaryschema.BsdParserTest;
import org.eclipse.milo.opcua.binaryschema.parser.BsdParser;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.testng.annotations.Test;

public class JsonBsdParserTest extends BsdParserTest {

    @Override
    protected BsdParser createBsdParser() {
        return new JsonBsdParser();
    }

    @Test
    public void testFoo() {
        JsonObject foo = new JsonObject();
        foo.add("Bar", new JsonPrimitive(0));
        foo.add("Baz", new JsonPrimitive("hello"));

        OpcUaBinaryDataTypeCodec<Object> codec = getCodec("Foo");

        assertRoundTrip("Foo", foo, codec);
    }

    @Test
    public void testOptionals_AllPresent() {
        JsonObject optionals = new JsonObject();
        optionals.add("OptionalInt32Specified", new JsonPrimitive(1));
        optionals.add("OptionalStringSpecified", new JsonPrimitive(1));
        optionals.add("Reserved1", new JsonPrimitive(0));
        optionals.add("OptionalInt32", new JsonPrimitive(0));
        optionals.add("OptionalString", new JsonPrimitive("hello"));

        OpcUaBinaryDataTypeCodec<Object> codec = getCodec("Optionals");

        assertRoundTrip("Optionals", optionals, codec);
    }

    @Test
    public void testOptionals_OnePresent() {
        JsonObject optionals = new JsonObject();
        optionals.add("OptionalInt32Specified", new JsonPrimitive(1));
        optionals.add("OptionalStringSpecified", new JsonPrimitive(0));
        optionals.add("Reserved1", new JsonPrimitive(0));
        optionals.add("OptionalInt32", new JsonPrimitive(0));

        OpcUaBinaryDataTypeCodec<Object> codec = getCodec("Optionals");

        assertRoundTrip("Optionals", optionals, codec);
    }

    @Test
    public void testOptionals_NonePresent() {
        JsonObject optionals = new JsonObject();
        optionals.add("OptionalInt32Specified", new JsonPrimitive(0));
        optionals.add("OptionalStringSpecified", new JsonPrimitive(0));
        optionals.add("Reserved1", new JsonPrimitive(0));

        OpcUaBinaryDataTypeCodec<Object> codec = getCodec("Optionals");

        assertRoundTrip("Optionals", optionals, codec);
    }

    @Test
    public void testArrayContainer() {
        JsonObject arrayContainer = new JsonObject();

        arrayContainer.add("IntArrayLen", new JsonPrimitive(3));

        JsonArray intArray = new JsonArray();
        intArray.add(1);
        intArray.add(2);
        intArray.add(3);
        arrayContainer.add("IntArray", intArray);

        arrayContainer.add("BitField", new JsonPrimitive(0b10001111));

        arrayContainer.add("StringArrayLen", new JsonPrimitive(2));

        JsonArray stringArray = new JsonArray();
        stringArray.add("hello");
        stringArray.add("world");
        arrayContainer.add("StringArray", stringArray);

        OpcUaBinaryDataTypeCodec<Object> codec = getCodec("ArrayContainer");

        assertRoundTrip("ArrayContainer", arrayContainer, codec);
    }

    @Test
    public void testBar() throws Exception {
        JsonObject foo = new JsonObject();
        foo.add("Bar", new JsonPrimitive(0));
        foo.add("Baz", new JsonPrimitive("hello"));

        JsonObject bar = new JsonObject();
        bar.add("Foo", foo);
        bar.add("Int", new JsonPrimitive(1));
        bar.add("Str", new JsonPrimitive("goodbye"));

        OpcUaBinaryDataTypeCodec<Object> codec = getCodec("Bar");

        assertRoundTrip("Bar", bar, codec);
    }

}