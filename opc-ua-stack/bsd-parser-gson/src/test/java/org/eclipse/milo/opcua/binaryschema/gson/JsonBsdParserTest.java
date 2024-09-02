/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.binaryschema.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

        JsonArray intArray = new JsonArray();
        intArray.add(1);
        intArray.add(2);
        intArray.add(3);
        arrayContainer.add("IntArray", intArray);

        arrayContainer.add("BitField", new JsonPrimitive(0b10001111));

        JsonArray stringArray = new JsonArray();
        stringArray.add("hello");
        stringArray.add("world");
        arrayContainer.add("StringArray", stringArray);

        OpcUaBinaryDataTypeCodec<Object> codec = getCodec("ArrayContainer");

        assertRoundTrip("ArrayContainer", arrayContainer, codec);
    }

    @Test
    public void testBar() {
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

    @Test
    public void testScanSettings() {
        /*
        {
            "LocationTypeSpecified": 1,
            "Reserved1": 0,
            "Duration": 0.0,
            "Cycles": 0,
            "DataAvailable": false,
            "LocationType": 0
        }
        */

        JsonObject scanSettings = new JsonObject();
        scanSettings.add("LocationTypeSpecified", new JsonPrimitive(1));
        scanSettings.add("Reserved1", new JsonPrimitive(0));
        scanSettings.add("Duration", new JsonPrimitive(0.0));
        scanSettings.add("Cycles", new JsonPrimitive(0));
        scanSettings.add("DataAvailable", new JsonPrimitive(false));
        scanSettings.add("LocationType", new JsonPrimitive(0));

        OpcUaBinaryDataTypeCodec<Object> codec = getCodec("ScanSettings");

        assertRoundTrip("ScanSettings", scanSettings, codec);
    }

    @Test
    public void testScanSettingsJson() {
        String json = "{\n" +
            "    \"LocationTypeSpecified\": 1,\n" +
            "    \"Reserved1\": 0,\n" +
            "    \"Duration\": 0.0,\n" +
            "    \"Cycles\": 0,\n" +
            "    \"DataAvailable\": false,\n" +
            "    \"LocationType\": 0\n" +
            "}";

        JsonObject scanSettings = JsonParser.parseString(json).getAsJsonObject();

        OpcUaBinaryDataTypeCodec<Object> codec = getCodec("ScanSettings");

        assertRoundTrip("ScanSettings", scanSettings, codec);
    }

}
