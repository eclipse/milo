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

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface SerializationContext {

    SerializationContext INTERNAL = OpcUaDataTypeManager::getInstance;

    DataTypeManager getTypeManager();

    default Object decode(NodeId encodingId, OpcBinaryStreamReader reader) throws UaSerializationException {
        OpcBinaryDataTypeCodec<?> codec = getTypeManager().getBinaryCodec(encodingId);

        if (codec == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                String.format("no OpcBinaryDataTypeCodec registered for encodingId=%s", encodingId)
            );
        }

        return codec.decode(this, reader);
    }

    default Object decode(NodeId encodingId, OpcXmlStreamReader reader) throws UaSerializationException {
        OpcXmlDataTypeCodec<?> codec = getTypeManager().getXmlCodec(encodingId);

        if (codec == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                String.format("no OpcXmlDataTypeCodec registered for encodingId=%s", encodingId)
            );
        }

        return codec.decode(this, reader);
    }

    default Object decode(
        String namespaceUri,
        String description,
        OpcBinaryStreamReader reader) throws UaSerializationException {

        OpcBinaryDataTypeCodec<?> codec = getTypeManager().getBinaryCodec(namespaceUri, description);

        if (codec == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                String.format(
                    "no OpcBinaryDataTypeCodec registered for description=%s under namespaceUri=%s",
                    description, namespaceUri)
            );
        }

        return codec.decode(this, reader);
    }

    default Object decode(
        String namespaceUri,
        String description,
        OpcXmlStreamReader reader) throws UaSerializationException {

        OpcXmlDataTypeCodec<?> codec = getTypeManager().getXmlCodec(namespaceUri, description);

        if (codec == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                String.format(
                    "no OpcXmlDataTypeCodec registered for description=%s under namespaceUri=%s",
                    description, namespaceUri)
            );
        }

        return codec.decode(this, reader);
    }

    default void encode(
        NodeId encodingId,
        Object encodable,
        OpcBinaryStreamWriter writer) throws UaSerializationException {

        @SuppressWarnings("unchecked")
        OpcBinaryDataTypeCodec<Object> codec =
            (OpcBinaryDataTypeCodec<Object>) getTypeManager().getBinaryCodec(encodingId);

        if (codec == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingError,
                String.format("no OpcBinaryDataTypeCodec registered for encodingId=%s", encodingId));
        }

        codec.encode(this, encodable, writer);
    }

    default void encode(
        String namespaceUri,
        String description,
        Object encodable,
        OpcBinaryStreamWriter writer) throws UaSerializationException {

        @SuppressWarnings("unchecked")
        OpcBinaryDataTypeCodec<Object> codec =
            (OpcBinaryDataTypeCodec<Object>) getTypeManager().getBinaryCodec(namespaceUri, description);

        if (codec == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingError,
                String.format(
                    "no OpcBinaryDataTypeCodec registered for description=%s under namespaceUri=%s",
                    description, namespaceUri)
            );
        }

        codec.encode(this, encodable, writer);
    }

    default void encode(
        String namespaceUri,
        String description,
        Object encodable,
        OpcXmlStreamWriter writer) throws UaSerializationException {

        @SuppressWarnings("unchecked")
        OpcXmlDataTypeCodec<Object> codec =
            (OpcXmlDataTypeCodec<Object>) getTypeManager().getXmlCodec(namespaceUri, description);

        if (codec == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingError,
                String.format(
                    "no OpcXmlDataTypeCodec registered for description=%s under namespaceUri=%s",
                    description, namespaceUri)
            );
        }

        codec.encode(this, encodable, writer);
    }

}
