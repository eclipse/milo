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

import javax.annotation.Nullable;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface DataTypeManager {

    void registerTypeDictionary(DataTypeDictionary dataTypeDictionary);

    /**
     * Get the {@link DataTypeDictionary} registered under {@code namespaceUri}.
     *
     * @param namespaceUri the namespace URI of the {@link DataTypeDictionary}.
     * @return the {@link DataTypeDictionary} registered under {@code namespaceUri}, or {@code null} if none was found.
     */
    @Nullable
    DataTypeDictionary getTypeDictionary(String namespaceUri);

    /**
     * Get the {@link DataTypeCodec} in the dictionary identified by {@code namespaceUri} using {@code description} to
     * look up the codec.
     *
     * @param namespaceUri the namespace URI of the {@link DataTypeDictionary}.
     * @param description  the description to use when looking up the codec.
     * @return the {@link DataTypeCodec} in the dictionary identified by {@code namespaceUri} using {@code description}
     * to look up the codec, or {@code null} if none was found.
     */
    @Nullable
    default DataTypeCodec getCodec(String namespaceUri, String description) {
        DataTypeDictionary dictionary = getTypeDictionary(namespaceUri);

        return dictionary != null ? dictionary.getCodec(description) : null;
    }

    /**
     * Get the {@link OpcBinaryDataTypeCodec} identified by {@code encodingId}.
     *
     * @param encodingId the {@link NodeId} of the DataTypeEncoding node for the DataType of the requested codec.
     * @return an {@link OpcBinaryDataTypeCodec}, or {@code null} if none was found.
     */
    @Nullable
    OpcBinaryDataTypeCodec<?> getBinaryCodec(NodeId encodingId);

    /**
     * Get the {@link OpcBinaryDataTypeCodec} identified by {@code description} from the dictionary identified by
     * {@code namespaceUri}.
     *
     * @param namespaceUri the namespace URI of the dictionary.
     * @param description  the value of the DataTypeDescription that identifies the codec in the dictionary.
     * @return an {@link OpcBinaryDataTypeCodec}, or {@code null} if none was found.
     */
    @Nullable
    default OpcBinaryDataTypeCodec<?> getBinaryCodec(String namespaceUri, String description) {
        DataTypeCodec codec = getCodec(namespaceUri, description);

        if (codec instanceof OpcBinaryDataTypeCodec) {
            return (OpcBinaryDataTypeCodec) codec;
        } else {
            return null;
        }
    }

    /**
     * Get the {@link OpcXmlDataTypeCodec} identified by {@code encodingId}.
     *
     * @param encodingId the {@link NodeId} of the DataTypeEncoding node for the DataType of the requested codec.
     * @return an {@link OpcXmlDataTypeCodec}, or {@code null} if none was found.
     */
    @Nullable
    OpcXmlDataTypeCodec<?> getXmlCodec(NodeId encodingId);

    /**
     * Get the {@link OpcXmlDataTypeCodec} identified by {@code description} from the dictionary identified by
     * {@code namespaceUri}.
     *
     * @param namespaceUri the namespace URI of the dictionary.
     * @param description  the value of the DataTypeDescription that identifies the codec in the dictionary.
     * @return an {@link OpcXmlDataTypeCodec}, or {@code null} if none was found.
     */
    @Nullable
    default OpcXmlDataTypeCodec<?> getXmlCodec(String namespaceUri, String description) {
        DataTypeCodec codec = getCodec(namespaceUri, description);

        if (codec instanceof OpcXmlDataTypeCodec) {
            return (OpcXmlDataTypeCodec<?>) codec;
        } else {
            return null;
        }
    }

}
