/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.serialization;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;

public interface SerializationContext {

    /**
     * Get the {@link EncodingLimits}.
     *
     * @return the {@link EncodingLimits}.
     */
    EncodingLimits getEncodingLimits();

    /**
     * Get the {@link NamespaceTable}.
     *
     * @return the {@link NamespaceTable}.
     */
    NamespaceTable getNamespaceTable();

    /**
     * Get the {@link DataTypeManager}.
     *
     * @return the {@link DataTypeManager}.
     */
    DataTypeManager getDataTypeManager();

    default Object decode(
        String namespaceUri,
        String description,
        OpcUaBinaryStreamDecoder decoder
    ) throws UaSerializationException {

        DataTypeCodec codec = getDataTypeManager().getCodec(namespaceUri, description);

        if (codec instanceof OpcUaBinaryDataTypeCodec) {
            //noinspection unchecked
            return codec.decode(this, decoder);
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                String.format(
                    "no OpcBinaryDataTypeCodec registered for description=%s under namespaceUri=%s",
                    description, namespaceUri)
            );
        }
    }

    default Object decode(
        String namespaceUri,
        String description,
        OpcUaXmlStreamDecoder decoder
    ) throws UaSerializationException {

        DataTypeCodec codec = getDataTypeManager().getCodec(namespaceUri, description);

        if (codec instanceof OpcUaXmlDataTypeCodec) {
            //noinspection unchecked
            return codec.decode(this, decoder);
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                String.format(
                    "no OpcXmlDataTypeCodec registered for description=%s under namespaceUri=%s",
                    description, namespaceUri)
            );
        }
    }


    default void encode(
        String namespaceUri,
        String description,
        Object value,
        OpcUaBinaryStreamEncoder encoder
    ) throws UaSerializationException {

        DataTypeCodec codec = getDataTypeManager().getCodec(namespaceUri, description);

        if (codec instanceof OpcUaBinaryDataTypeCodec) {
            //noinspection unchecked
            codec.encode(this, encoder, value);
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingError,
                String.format(
                    "no OpcBinaryDataTypeCodec registered for description=%s under namespaceUri=%s",
                    description, namespaceUri)
            );
        }
    }

    default void encode(
        String namespaceUri,
        String description,
        Object value,
        OpcUaXmlStreamEncoder encoder
    ) throws UaSerializationException {

        DataTypeCodec codec = getDataTypeManager().getCodec(namespaceUri, description);

        if (codec instanceof OpcUaXmlDataTypeCodec) {
            //noinspection unchecked
            codec.encode(this, encoder, value);
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingError,
                String.format(
                    "no OpcXmlDataTypeCodec registered for description=%s under namespaceUri=%s",
                    description, namespaceUri)
            );
        }
    }

}
