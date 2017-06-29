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

package org.eclipse.milo.opcua.stack.core.serialization.codec;

import java.io.StringWriter;
import javax.xml.stream.XMLStreamException;

import org.eclipse.milo.opcua.stack.core.serialization.xml.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class OpcXmlStreamWriterTest {

    @Test
    public void testByteString() throws XMLStreamException {
        ByteString bs = ByteString.NULL_VALUE;

        StringWriter writer = new StringWriter(128);

        OpcXmlStreamWriter encoder = new OpcXmlStreamWriter();
        encoder.setOutput(writer);
        encoder.writeByteString("Value", bs);

        String output = writer.toString();
        System.out.println(output);
    }

    @Test
    public void testEncodeReferenceDescription() throws XMLStreamException {
        StringWriter sw = new StringWriter(128);

        OpcXmlStreamWriter writer = new OpcXmlStreamWriter();
        writer.setOutput(sw);

        writer.writeNodeId("ReferenceTypeId", NodeId.parse("ns=0;i=46"));
        writer.writeBoolean("IsForward", Boolean.TRUE);
        writer.writeExpandedNodeId("NodeId", ExpandedNodeId.parse("svr=0;i=2994"));
        writer.writeQualifiedName("BrowseName", QualifiedName.parse("0:Auditing"));
        writer.writeLocalizedText("DisplayName", new LocalizedText("", "Auditing"));
        writer.writeEnumeration("NodeClass", NodeClass.Variable);
        writer.writeExpandedNodeId("TypeDefinition", ExpandedNodeId.parse("svr=0;i=68"));

        String output = sw.toString();
        System.out.println(output);

        assertTrue(output.contains("<ReferenceTypeId><Identifier>ns=0;i=46</Identifier></ReferenceTypeId>"));
        assertTrue(output.contains("<IsForward>true</IsForward>"));
        assertTrue(output.contains("<NodeId><Identifier>svr=0;i=2994</Identifier></NodeId>"));
        assertTrue(output.contains("<BrowseName><NamespaceIndex>0</NamespaceIndex><Name>Auditing</Name></BrowseName>"));
        assertTrue(output.contains("<DisplayName>") && output.contains("<Text>Auditing</Text>"));
        assertTrue(output.contains("<NodeClass>Variable_2</NodeClass>"));
        assertTrue(output.contains("<TypeDefinition><Identifier>svr=0;i=68</Identifier></TypeDefinition>"));
    }

}
