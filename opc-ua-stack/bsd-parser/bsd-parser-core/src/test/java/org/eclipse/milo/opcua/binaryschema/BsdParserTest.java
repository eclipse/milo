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

package org.eclipse.milo.opcua.binaryschema;

import java.nio.ByteOrder;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.eclipse.milo.opcua.binaryschema.parser.BsdParser;
import org.eclipse.milo.opcua.binaryschema.parser.DictionaryDescription;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.BuiltinDataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.testng.annotations.BeforeSuite;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public abstract class BsdParserTest {

    private static final String BSD_PARSER_TEST_NAMESPACE_URI = "https://github.com/eclipse/milo";

    private final Table<String, String, OpcUaBinaryDataTypeCodec<?>> codecTable = HashBasedTable.create();

    private final SerializationContext context = new SerializationContext() {
        @Override
        public DataTypeManager getTypeManager() {
            return null;
        }

        @Override
        public Object decode(
            String namespaceUri,
            String typeName,
            OpcUaBinaryStreamDecoder decoder) throws UaSerializationException {

            return codecTable.get(namespaceUri, typeName).decode(context, decoder);
        }

        @Override
        public void encode(
            String namespaceUri,
            String typeName,
            Object encodable,
            OpcUaBinaryStreamEncoder encoder) throws UaSerializationException {

            @SuppressWarnings("unchecked")
            OpcUaBinaryDataTypeCodec<Object> codec =
                (OpcUaBinaryDataTypeCodec<Object>) codecTable.get(namespaceUri, typeName);

            codec.encode(context, encodable, encoder);
        }
    };

    public BsdParserTest() {
        BuiltinDataTypeDictionary.getBinaryInstance().getCodecsByDescription().forEach((d, c) ->
            codecTable.put(BuiltinDataTypeDictionary.BINARY_NAMESPACE_URI, d, c)
        );
    }

    /**
     * Create and return a {@link BsdParser} instance to test.
     *
     * @return a {@link BsdParser} instance to test.
     */
    protected abstract BsdParser createBsdParser();

    @BeforeSuite
    public void parseTypeDictionary() throws Exception {
        BsdParser parser = createBsdParser();

        DictionaryDescription dictionary = parser.parse(
            getClass().getClassLoader().getResourceAsStream(
                "dictionaries/BsdParserTest.bsd.xml")
        );

        dictionary.getStructCodecs().forEach(cd ->
            codecTable.put(
                dictionary.getNamespaceUri(), cd.getDescription(), cd.getCodec())
        );
    }

    @SuppressWarnings("unchecked")
    protected OpcUaBinaryDataTypeCodec<Object> getCodec(String name) {
        OpcUaBinaryDataTypeCodec<?> codec =
            codecTable.get(BSD_PARSER_TEST_NAMESPACE_URI, name);

        assertNotNull(codec);

        return (OpcUaBinaryDataTypeCodec<Object>) codec;
    }

    protected void assertRoundTrip(String type, Object originalValue, OpcUaBinaryDataTypeCodec<Object> codec) {
        System.out.printf("--- assertRoundTrip Type: %s ---\n", type);

        System.out.println("originalValue:\t" + originalValue);
        ByteBuf buffer = Unpooled.buffer().order(ByteOrder.LITTLE_ENDIAN);
        codec.encode(context, originalValue, new OpcUaBinaryStreamEncoder(buffer));

        ByteBuf encodedValue = buffer.copy();
        System.out.println("encodedValue:\t" + ByteBufUtil.hexDump(encodedValue));

        Object decodedValue = codec.decode(context, new OpcUaBinaryStreamDecoder(buffer));
        assertEquals(decodedValue, originalValue);
        System.out.println("decodedValue:\t" + decodedValue);
    }

    /**
     * A weaker version of {@link #assertRoundTrip(String, Object, OpcUaBinaryDataTypeCodec)} for values that don't
     * implement equals and hashcode or values that contain members not implementing equals and  hashcode.
     * <p>
     * Relies on toString() values to be implemented at all levels instead... not great, but since the built-in structs
     * don't implement equals/hashcode it's what we have.
     */
    protected void assertRoundTripUsingToString(String type, Object originalValue, OpcUaBinaryDataTypeCodec<Object> codec) {
        System.out.printf("--- assertRoundTrip Type: %s ---\n", type);

        System.out.println("originalValue:\t" + originalValue);
        ByteBuf buffer = Unpooled.buffer().order(ByteOrder.LITTLE_ENDIAN);
        codec.encode(context, originalValue, new OpcUaBinaryStreamEncoder(buffer));

        ByteBuf encodedValue = buffer.copy();
        System.out.println("encodedValue:\t" + ByteBufUtil.hexDump(encodedValue));

        Object decodedValue = codec.decode(context, new OpcUaBinaryStreamDecoder(buffer));
        assertEquals(decodedValue.toString(), originalValue.toString());
        System.out.println("decodedValue:\t" + decodedValue);
    }

}
