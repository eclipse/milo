/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.serialization.binary;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.TestSerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDefaultBinaryEncoding;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BinaryDecoderTest extends BinarySerializationFixture {

    @Test
    @DisplayName("a null array, once encoded, should decode to a null array")
    public void testDecodeNullArray() {
        Argument argument = new Argument(
            "test",
            Identifiers.Int16,
            1,
            null,
            LocalizedText.NULL_VALUE
        );

        @SuppressWarnings("unchecked")
        OpcUaBinaryDataTypeCodec<Argument> codec = (OpcUaBinaryDataTypeCodec<Argument>)
            OpcUaDataTypeManager.getInstance().getCodec(
                OpcUaDefaultBinaryEncoding.ENCODING_NAME,
                Argument.TYPE_ID.toNodeId(new NamespaceTable()).get()
            );

        assertNotNull(codec);

        codec.encode(new TestSerializationContext(), writer, argument);
        Argument decoded = codec.decode(new TestSerializationContext(), reader);

        assertEquals(decoded.getName(), argument.getName());

        assertNull(decoded.getArrayDimensions());
    }

    @Test
    public void testEnumScalar() {
        writer.writeEnum(null, ApplicationType.Client);
        ApplicationType decoded = reader.readEnum(null, ApplicationType.class);

        assertEquals(decoded, ApplicationType.Client);
    }

    @Test
    public void testEnumArray() {
        ApplicationType[] array = new ApplicationType[]{
            ApplicationType.Client,
            ApplicationType.ClientAndServer
        };
        writer.writeEnumArray(null, array);
        ApplicationType[] decoded = (ApplicationType[]) reader.readEnumArray(null, ApplicationType.class);

        assertArrayEquals(decoded, array);
    }

}
