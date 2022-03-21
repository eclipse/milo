/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.serialization;

import java.io.IOException;
import java.io.StringReader;

import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OpcUaJsonDecoderTest {

    @Test
    void readFloat() throws IOException {
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

        decoder.reset(new StringReader("0.0"));
        assertEquals(0.0f, decoder.readFloat(null));

        decoder.reset(new StringReader(String.valueOf(Float.MIN_VALUE)));
        assertEquals(Float.MIN_VALUE, decoder.readFloat(null));

        decoder.reset(new StringReader(String.valueOf(Float.MAX_VALUE)));
        assertEquals(Float.MAX_VALUE, decoder.readFloat(null));

        decoder.reset(new StringReader("\"Infinity\""));
        assertEquals(Float.POSITIVE_INFINITY, decoder.readFloat(null));

        decoder.reset(new StringReader("\"-Infinity\""));
        assertEquals(Float.NEGATIVE_INFINITY, decoder.readFloat(null));

        decoder.reset(new StringReader("\"NaN\""));
        assertEquals(Float.NaN, decoder.readFloat(null));

        decoder.reset(new StringReader("{\"foo\":0.0}"));
        decoder.jsonReader.beginObject();
        assertEquals(0.0f, decoder.readFloat("foo"));
        decoder.jsonReader.endObject();

        assertThrows(UaSerializationException.class, () -> {
            decoder.reset(new StringReader("{\"foo\":0.0}"));
            decoder.jsonReader.beginObject();
            assertEquals(0.0f, decoder.readFloat("bar"));
            decoder.jsonReader.endObject();
        });
    }

}
