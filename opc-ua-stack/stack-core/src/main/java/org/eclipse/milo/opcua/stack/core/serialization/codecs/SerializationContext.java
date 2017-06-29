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

package org.eclipse.milo.opcua.stack.core.serialization.codecs;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamEncoder;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager;

public interface SerializationContext {

    SerializationContext INTERNAL = OpcUaDataTypeManager::getInstance;

    /**
     * @return the {@link DataTypeManager}.
     */
    DataTypeManager getTypeManager();

    default Object decode(
        String namespaceUri,
        String description,
        OpcUaBinaryStreamDecoder decoder) throws UaSerializationException {

        OpcUaBinaryDataTypeCodec<?> codec = getTypeManager().getBinaryCodec(namespaceUri, description);

        if (codec == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                String.format(
                    "no OpcBinaryDataTypeCodec registered for description=%s under namespaceUri=%s",
                    description, namespaceUri)
            );
        }

        return codec.decode(this, decoder);
    }

    default Object decode(
        String namespaceUri,
        String description,
        OpcUaXmlStreamDecoder decoder) throws UaSerializationException {

        OpcUaXmlDataTypeCodec<?> codec = getTypeManager().getXmlCodec(namespaceUri, description);

        if (codec == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                String.format(
                    "no OpcXmlDataTypeCodec registered for description=%s under namespaceUri=%s",
                    description, namespaceUri)
            );
        }

        return codec.decode(this, decoder);
    }


    default void encode(
        String namespaceUri,
        String description,
        Object value,
        OpcUaBinaryStreamEncoder encoder) throws UaSerializationException {

        @SuppressWarnings("unchecked")
        OpcUaBinaryDataTypeCodec<Object> codec =
            (OpcUaBinaryDataTypeCodec<Object>) getTypeManager().getBinaryCodec(namespaceUri, description);

        if (codec == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingError,
                String.format(
                    "no OpcBinaryDataTypeCodec registered for description=%s under namespaceUri=%s",
                    description, namespaceUri)
            );
        }

        codec.encode(this, value, encoder);
    }

    default void encode(
        String namespaceUri,
        String description,
        Object value,
        OpcUaXmlStreamEncoder encoder) throws UaSerializationException {

        @SuppressWarnings("unchecked")
        OpcUaXmlDataTypeCodec<Object> codec =
            (OpcUaXmlDataTypeCodec<Object>) getTypeManager().getXmlCodec(namespaceUri, description);

        if (codec == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingError,
                String.format(
                    "no OpcXmlDataTypeCodec registered for description=%s under namespaceUri=%s",
                    description, namespaceUri)
            );
        }

        codec.encode(this, value, encoder);
    }

}
