/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.binaryschema;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.eclipse.milo.opcua.binaryschema.parser.BsdParser;
import org.eclipse.milo.opcua.binaryschema.parser.DictionaryDescription;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.BuiltinDataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager;
import org.testng.annotations.BeforeSuite;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public abstract class BsdParserTest {

    private static final String BSD_PARSER_TEST_NAMESPACE_URI = "https://github.com/eclipse/milo";

    private final Table<String, String, OpcUaBinaryDataTypeCodec<?>> codecTable = HashBasedTable.create();

    private final SerializationContext context = new SerializationContext() {

        private final NamespaceTable namespaceTable = new NamespaceTable();

        @Override
        public DataTypeManager getDataTypeManager() {
            return OpcUaDataTypeManager.getInstance();
        }

        @Override
        public EncodingLimits getEncodingLimits() {
            return EncodingLimits.DEFAULT;
        }

        @Override
        public NamespaceTable getNamespaceTable() {
            return namespaceTable;
        }

        @Override
        public Object decode(
            String namespaceUri,
            String typeName,
            OpcUaBinaryStreamDecoder decoder
        ) throws UaSerializationException {

            return codecTable.get(namespaceUri, typeName).decode(context, decoder);
        }

        @Override
        public void encode(
            String namespaceUri,
            String typeName,
            Object encodable,
            OpcUaBinaryStreamEncoder encoder
        ) throws UaSerializationException {

            @SuppressWarnings("unchecked")
            OpcUaBinaryDataTypeCodec<Object> codec =
                (OpcUaBinaryDataTypeCodec<Object>) codecTable.get(namespaceUri, typeName);

            codec.encode(context, encoder, encodable);
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
        ByteBuf buffer = Unpooled.buffer();
        codec.encode(context, new OpcUaBinaryStreamEncoder(context).setBuffer(buffer), originalValue);

        ByteBuf encodedValue = buffer.copy();
        System.out.println("encodedValue:\t" + ByteBufUtil.hexDump(encodedValue));

        Object decodedValue = codec.decode(context, new OpcUaBinaryStreamDecoder(context).setBuffer(buffer));
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
    protected void assertRoundTripUsingToString(
        String type,
        Object originalValue,
        OpcUaBinaryDataTypeCodec<Object> codec
    ) {

        System.out.printf("--- assertRoundTrip Type: %s ---\n", type);

        System.out.println("originalValue:\t" + originalValue);
        ByteBuf buffer = Unpooled.buffer();
        codec.encode(context, new OpcUaBinaryStreamEncoder(context).setBuffer(buffer), originalValue);

        ByteBuf encodedValue = buffer.copy();
        System.out.println("encodedValue:\t" + ByteBufUtil.hexDump(encodedValue));

        Object decodedValue = codec.decode(context, new OpcUaBinaryStreamDecoder(context).setBuffer(buffer));
        assertEquals(decodedValue.toString(), originalValue.toString());
        System.out.println("decodedValue:\t" + decodedValue);
    }

}
