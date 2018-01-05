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

import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ExtensionObjectSerializationTest extends BinarySerializationFixture {

    @DataProvider
    public Object[][] getExtensionObjects() {
        return new Object[][]{
            {new ExtensionObject(ByteString.of(new byte[]{1, 2, 3, 4}), new NodeId(1, 2))},
            {new ExtensionObject(XmlElement.of("<a>hello</a>"), new NodeId(1, 2))},
        };
    }

    @Test(dataProvider = "getExtensionObjects", description = "ExtensionObject is round-trip serializable.")
    public void testExtensionObjectRoundTrip(ExtensionObject xo) throws Exception {
        writer.writeExtensionObject(xo);
        ExtensionObject decoded = reader.readExtensionObject();

        assertEquals(decoded, xo);
    }

}
