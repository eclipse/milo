/*
 * Copyright (c) 2016 Kevin Herron
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

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class StringSerializationTest extends BinarySerializationFixture {

    @DataProvider(name = "StringProvider")
    public Object[][] getStrings() {
        return new Object[][]{
            {null},
            {""},
            {"Hello, world!"},
            {"水Boy"}
        };
    }

    @Test(dataProvider = "StringProvider")
    public void testStringRoundTrip(String value) {
        writer.writeString(value);
        String decoded = reader.readString();

        assertEquals(decoded, value);
    }

}
