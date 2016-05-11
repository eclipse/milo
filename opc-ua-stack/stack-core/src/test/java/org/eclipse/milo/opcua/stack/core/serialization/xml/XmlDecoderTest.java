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


import java.io.ByteArrayInputStream;
import java.io.StringReader;
import javax.xml.stream.XMLStreamException;

import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class XmlDecoderTest {

    @Test
    public void testDecodeBoolean() throws XMLStreamException {
        XmlDecoder decoder = new XmlDecoder();

        decoder.setInput(new ByteArrayInputStream("<Value>true</Value>".getBytes()));
        assertTrue(decoder.decodeBoolean("Value"));

        decoder.setInput(new ByteArrayInputStream("<Value>false</Value>".getBytes()));
        assertFalse(decoder.decodeBoolean("Value"));

        decoder.setInput(new ByteArrayInputStream("<Value>true</Value>".getBytes()));
        assertTrue(decoder.decodeBoolean(null));

        decoder.setInput(new ByteArrayInputStream("<Value>false</Value>".getBytes()));
        assertFalse(decoder.decodeBoolean(null));
    }

    @Test
    public void testDecodeStatusCode() throws XMLStreamException {
        XmlDecoder decoder = new XmlDecoder();

        decoder.setInput(new ByteArrayInputStream("<Value><Code>0</Code></Value>".getBytes()));
        assertEquals(decoder.decodeStatusCode("Value").getValue(), 0);

        decoder.setInput(new ByteArrayInputStream("<Value><Code>1</Code></Value>".getBytes()));
        assertEquals(decoder.decodeStatusCode("Value").getValue(), 1);
    }


    @Test
    public void testDecodeQualifiedName() throws XMLStreamException {
        XmlDecoder decoder = new XmlDecoder();

        decoder.setInput(new ByteArrayInputStream("<Value><NamespaceIndex>1</NamespaceIndex><Name>test</Name></Value>".getBytes()));
        QualifiedName qualifiedName = decoder.decodeQualifiedName("Value");

        assertEquals(qualifiedName.getNamespaceIndex(), ushort(1));
        assertEquals(qualifiedName.getName(), "test");
    }

    @Test
    public void testDecodeVariant() throws XMLStreamException {
        XmlDecoder decoder = new XmlDecoder();

        decoder.setInput(new ByteArrayInputStream("<Variant><Value><Float>3.14</Float></Value></Variant>".getBytes()));

        Variant variant = decoder.decodeVariant("Variant");
        assertEquals(variant.getValue(), Float.parseFloat("3.14"));
    }

    @Test
    public void testDecodeVariantListOfLocalizedText() throws XMLStreamException {
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

        XmlDecoder decoder = new XmlDecoder();

        decoder.setInput(new ByteArrayInputStream(xmlString.getBytes()));

        Variant variant = decoder.decodeVariant(null);

        System.out.println(variant);
    }

    @Test
    public void testDecodeVariantListOfExtensionObject() throws XMLStreamException {
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

        XmlDecoder decoder = new XmlDecoder();

        decoder.setInput(new ByteArrayInputStream(xmlString.getBytes()));

        Variant variant = decoder.decodeVariant(null);

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

        XmlDecoder decoder = new XmlDecoder();
        decoder.setInput(new StringReader(xmlString));

        Object value = decoder.decodeVariantValue();

        System.out.println(value);
    }

}
