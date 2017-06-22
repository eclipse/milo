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

import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LocalizedTextSerializationTest extends BinarySerializationFixture {

    @DataProvider
    public Object[][] getLocalizedTexts() {
        return new Object[][]{
            {new LocalizedText(null, null)},
            {new LocalizedText("locale", null)},
            {new LocalizedText(null, "text")},
            {LocalizedText.english("hello, world!")},
        };
    }

    @Test(dataProvider = "getLocalizedTexts", description = "LocalizedText is round-trip serializable.")
    public void testLocalizedText(LocalizedText localizedText) throws Exception {
        writer.writeLocalizedText(localizedText);
        LocalizedText decoded = reader.readLocalizedText();

        assertEquals(decoded, localizedText);
    }

}
