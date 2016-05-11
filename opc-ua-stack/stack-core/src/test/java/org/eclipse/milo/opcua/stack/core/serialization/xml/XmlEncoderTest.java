/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 * 	http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * 	http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.serialization.xml;

import java.io.StringWriter;
import javax.xml.stream.XMLStreamException;

import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.testng.annotations.Test;

public class XmlEncoderTest {

    @Test
    public void testByteString() throws XMLStreamException {
        ByteString bs = ByteString.NULL_VALUE;

        StringWriter writer = new StringWriter(128);

        XmlEncoder encoder = new XmlEncoder();
        encoder.setOutput(writer);
        encoder.encodeByteString("Value", bs);

        String output = writer.toString();
        System.out.println(output);
    }

}
