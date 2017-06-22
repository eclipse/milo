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


import java.io.ByteArrayInputStream;
import java.io.StringReader;
import javax.xml.stream.XMLStreamException;

import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class OpcXmlStreamReaderTest {

    @Test
    public void testReadBoolean() throws XMLStreamException {
        OpcXmlStreamReader reader = new OpcXmlStreamReader();

        reader.setInput(new ByteArrayInputStream("<Value>true</Value>".getBytes()));
        assertTrue(reader.readBoolean("Value"));

        reader.setInput(new ByteArrayInputStream("<Value>false</Value>".getBytes()));
        assertFalse(reader.readBoolean("Value"));

        reader.setInput(new ByteArrayInputStream("<Value>true</Value>".getBytes()));
        assertTrue(reader.readBoolean(null));

        reader.setInput(new ByteArrayInputStream("<Value>false</Value>".getBytes()));
        assertFalse(reader.readBoolean(null));
    }

    @Test
    public void testReadStatusCode() throws XMLStreamException {
        OpcXmlStreamReader reader = new OpcXmlStreamReader();

        reader.setInput(new ByteArrayInputStream("<Value><Code>0</Code></Value>".getBytes()));
        assertEquals(reader.readStatusCode("Value").getValue(), 0);

        reader.setInput(new ByteArrayInputStream("<Value><Code>1</Code></Value>".getBytes()));
        assertEquals(reader.readStatusCode("Value").getValue(), 1);
    }


    @Test
    public void testReadQualifiedName() throws XMLStreamException {
        OpcXmlStreamReader reader = new OpcXmlStreamReader();

        reader.setInput(new ByteArrayInputStream("<Value><NamespaceIndex>1</NamespaceIndex><Name>test</Name></Value>".getBytes()));
        QualifiedName qualifiedName = reader.readQualifiedName("Value");

        assertEquals(qualifiedName.getNamespaceIndex(), ushort(1));
        assertEquals(qualifiedName.getName(), "test");
    }

    @Test
    public void testReadVariant() throws XMLStreamException {
        OpcXmlStreamReader reader = new OpcXmlStreamReader();

        reader.setInput(new ByteArrayInputStream("<Variant><Value><Float>3.14</Float></Value></Variant>".getBytes()));

        Variant variant = reader.readVariant("Variant");
        assertEquals(variant.getValue(), Float.parseFloat("3.14"));
    }

    @Test
    public void testReadVariantListOfLocalizedText() throws XMLStreamException {
        String xmlString =
            "    <Value>\n" +
                "      <ListOfLocalizedText xmlns=\"http://opcfoundation.org/UA/2008/02/Types.xsd\">\n" +
                "        <LocalizedText>\n" +
                "          <Locale>\n" +
                "          </Locale>\n" +
                "          <Text>Numeric</Text>\n" +
                "        </LocalizedText>\n" +
                "        <LocalizedText>\n" +
                "          <Locale>\n" +
                "          </Locale>\n" +
                "          <Text>String</Text>\n" +
                "        </LocalizedText>\n" +
                "        <LocalizedText>\n" +
                "          <Locale>\n" +
                "          </Locale>\n" +
                "          <Text>Guid</Text>\n" +
                "        </LocalizedText>\n" +
                "        <LocalizedText>\n" +
                "          <Locale>\n" +
                "          </Locale>\n" +
                "          <Text>Opaque</Text>\n" +
                "        </LocalizedText>\n" +
                "      </ListOfLocalizedText>\n" +
                "    </Value>";

        OpcXmlStreamReader reader = new OpcXmlStreamReader();

        reader.setInput(new ByteArrayInputStream(xmlString.getBytes()));

        Variant variant = reader.readVariant(null);

        System.out.println(variant);
    }

    @Test
    public void testReadVariantListOfExtensionObject() throws XMLStreamException {
        String xmlString =
            "    <Value>\n" +
                "      <ListOfExtensionObject xmlns=\"http://opcfoundation.org/UA/2008/02/Types.xsd\">\n" +
                "        <ExtensionObject>\n" +
                "          <TypeId>\n" +
                "            <Identifier>i=7616</Identifier>\n" +
                "          </TypeId>\n" +
                "          <Body>\n" +
                "            <EnumValueType>\n" +
                "              <Value>1</Value>\n" +
                "              <DisplayName>\n" +
                "                <Locale>\n" +
                "                </Locale>\n" +
                "                <Text>Mandatory</Text>\n" +
                "              </DisplayName>\n" +
                "              <Description>\n" +
                "                <Locale>\n" +
                "                </Locale>\n" +
                "                <Text>The BrowseName must appear in all instances of the type.</Text>\n" +
                "              </Description>\n" +
                "            </EnumValueType>\n" +
                "          </Body>\n" +
                "        </ExtensionObject>\n" +
                "        <ExtensionObject>\n" +
                "          <TypeId>\n" +
                "            <Identifier>i=7616</Identifier>\n" +
                "          </TypeId>\n" +
                "          <Body>\n" +
                "            <EnumValueType>\n" +
                "              <Value>2</Value>\n" +
                "              <DisplayName>\n" +
                "                <Locale>\n" +
                "                </Locale>\n" +
                "                <Text>Optional</Text>\n" +
                "              </DisplayName>\n" +
                "              <Description>\n" +
                "                <Locale>\n" +
                "                </Locale>\n" +
                "                <Text>The BrowseName may appear in an instance of the type.</Text>\n" +
                "              </Description>\n" +
                "            </EnumValueType>\n" +
                "          </Body>\n" +
                "        </ExtensionObject>\n" +
                "        <ExtensionObject>\n" +
                "          <TypeId>\n" +
                "            <Identifier>i=7616</Identifier>\n" +
                "          </TypeId>\n" +
                "          <Body>\n" +
                "            <EnumValueType>\n" +
                "              <Value>3</Value>\n" +
                "              <DisplayName>\n" +
                "                <Locale>\n" +
                "                </Locale>\n" +
                "                <Text>Constraint</Text>\n" +
                "              </DisplayName>\n" +
                "              <Description>\n" +
                "                <Locale>\n" +
                "                </Locale>\n" +
                "                <Text>The modelling rule defines a constraint and the BrowseName is not used in an instance of the type.</Text>\n" +
                "              </Description>\n" +
                "            </EnumValueType>\n" +
                "          </Body>\n" +
                "        </ExtensionObject>\n" +
                "      </ListOfExtensionObject>\n" +
                "    </Value>";

        OpcXmlStreamReader reader = new OpcXmlStreamReader();

        reader.setInput(new ByteArrayInputStream(xmlString.getBytes()));

        Variant variant = reader.readVariant(null);

        System.out.println(variant);
    }

    @Test
    public void test() throws XMLStreamException {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<ListOfExtensionObject xmlns=\"http://opcfoundation.org/UA/2008/02/Types.xsd\" xmlns:ns2=\"http://opcfoundation.org/UA/2011/03/UANodeSet.xsd\">" +
            "<ExtensionObject>" +
            "<TypeId><Identifier>i=297</Identifier></TypeId>" +
            "<Body>" +
            "   <Argument>" +
            "       <Name>FileHandle</Name>" +
            "       <DataType><Identifier>i=7</Identifier></DataType>" +
            "       <ValueRank>-1</ValueRank><ArrayDimensions/>" +
            "       <Description xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>" +
            "   </Argument>" +
            "</Body>" +
            "</ExtensionObject>" +
            "</ListOfExtensionObject>";

        OpcXmlStreamReader reader = new OpcXmlStreamReader();
        reader.setInput(new StringReader(xmlString));

        Object value = reader.readVariantValue();

        System.out.println(value);
    }

    @Test
    public void testReadNodeId() throws XMLStreamException {
        OpcXmlStreamReader reader = new OpcXmlStreamReader();

        reader.setInput(new ByteArrayInputStream("<ReferenceTypeId><Identifier>ns=0;i=46</Identifier></ReferenceTypeId>".getBytes()));

        NodeId nodeId = reader.readNodeId("ReferenceTypeId");
        assertEquals(nodeId, NodeId.parse("ns=0;i=46"));
    }

    @Test
    public void testReadExpandedNodeId() throws XMLStreamException {
        OpcXmlStreamReader reader = new OpcXmlStreamReader();

        reader.setInput(new ByteArrayInputStream("<NodeId><Identifier>svr=0;i=2994</Identifier></NodeId>".getBytes()));

        ExpandedNodeId nodeId = reader.readExpandedNodeId("NodeId");
        assertEquals(nodeId, ExpandedNodeId.parse("svr=0;i=2994"));


    }

    @Test
    public void testReadReferenceDescription() throws XMLStreamException {
        OpcXmlStreamReader reader = new OpcXmlStreamReader();
        String xmlString =
            "<ReferenceDescription><ReferenceTypeId><Identifier>ns=0;i=46</Identifier></ReferenceTypeId><IsForward>true</IsForward><NodeId><Identifier>svr=0;i=2994</Identifier></NodeId><BrowseName><NamespaceIndex>0</NamespaceIndex><Name>Auditing</Name></BrowseName><DisplayName><Locale></Locale><Text>Auditing</Text></DisplayName><NodeClass>Variable_2</NodeClass><TypeDefinition><Identifier>svr=0;i=68</Identifier></TypeDefinition></ReferenceDescription>";

        reader.setInput(new ByteArrayInputStream(xmlString.getBytes()));
        reader.skipElement();

        NodeId referenceTypeId = reader.readNodeId("ReferenceTypeId");
        assertEquals(referenceTypeId, NodeId.parse("ns=0;i=46"));

        Boolean isForward = reader.readBoolean("IsForward");
        assertTrue(isForward);

        ExpandedNodeId nodeId = reader.readExpandedNodeId("NodeId");
        assertEquals(nodeId, ExpandedNodeId.parse("svr=0;i=2994"));

        QualifiedName browseName = reader.readQualifiedName("BrowseName");
        assertEquals(browseName, QualifiedName.parse("0:Auditing"));

        LocalizedText displayName = reader.readLocalizedText("DisplayName");
        assertEquals(displayName, new LocalizedText("", "Auditing"));

        NodeClass nodeClass = reader.readEnumeration("NodeClass", NodeClass.class);
        assertEquals(nodeClass, NodeClass.Variable);

        ExpandedNodeId typeDefinition = reader.readExpandedNodeId("TypeDefinition");
        assertEquals(typeDefinition, ExpandedNodeId.parse("svr=0;i=68"));

        xmlString =
            "<ReferenceDescription><ReferenceTypeId><Identifier>ns=4;i=46</Identifier></ReferenceTypeId><IsForward>false</IsForward><NodeId><Identifier>svr=4;i=2994</Identifier></NodeId><BrowseName><NamespaceIndex>6</NamespaceIndex><Name>Auditing</Name></BrowseName><DisplayName><Locale>en</Locale><Text>Auditing</Text></DisplayName><NodeClass>Unspecified_0</NodeClass><TypeDefinition><Identifier>svr=4;i=68</Identifier></TypeDefinition></ReferenceDescription>";

        reader.setInput(new ByteArrayInputStream(xmlString.getBytes()));
        reader.skipElement();

        referenceTypeId = reader.readNodeId("ReferenceTypeId");
        assertEquals(referenceTypeId, NodeId.parse("ns=4;i=46"));

        isForward = reader.readBoolean("IsForward");
        assertFalse(isForward);

        nodeId = reader.readExpandedNodeId("NodeId");
        assertEquals(nodeId, ExpandedNodeId.parse("svr=4;i=2994"));

        browseName = reader.readQualifiedName("BrowseName");
        assertEquals(browseName, QualifiedName.parse("6:Auditing"));

        displayName = reader.readLocalizedText("DisplayName");
        assertEquals(displayName, LocalizedText.english("Auditing"));

        nodeClass = reader.readEnumeration("NodeClass", NodeClass.class);
        assertEquals(nodeClass, NodeClass.Unspecified);

        typeDefinition = reader.readExpandedNodeId("TypeDefinition");
        assertEquals(typeDefinition, ExpandedNodeId.parse("svr=4;i=68"));

    }

}
