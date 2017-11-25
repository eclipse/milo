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

}