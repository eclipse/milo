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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class BinaryDecoderTest extends BinarySerializationFixture {

    @Test(description = "a null array, once encoded, should decode to a null array")
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
            OpcUaDataTypeManager.getInstance().getBinaryCodec(Argument.BinaryEncodingId);

        assertNotNull(codec);

        codec.encode(SerializationContext.INTERNAL, argument, writer);
        Argument decoded = codec.decode(SerializationContext.INTERNAL, reader);

        assertEquals(decoded.getName(), argument.getName());

        assertNull(decoded.getArrayDimensions());
    }

}
