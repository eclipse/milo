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

import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class XmlElementSerializationTest extends BinarySerializationFixture {

    @DataProvider(name = "XmlElementProvider")
    public Object[][] getXmlElements() {
        return new Object[][]{
            {new XmlElement(null)},
            {new XmlElement("<tag>hello, world</tag>")},
        };
    }

    @Test(dataProvider = "XmlElementProvider", description = "XmlElement is round-trip serializable.")
    public void testXmlElementRoundTrip(XmlElement element) throws Exception {
        writer.writeXmlElement(element);
        XmlElement decoded = reader.readXmlElement();

        assertEquals(decoded, element);
    }

}
