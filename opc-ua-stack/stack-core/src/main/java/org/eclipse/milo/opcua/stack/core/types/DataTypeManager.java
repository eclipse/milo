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

package org.eclipse.milo.opcua.stack.core.types;

import javax.annotation.Nullable;

import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface DataTypeManager {

    /**
     * Register a {@link DataTypeDictionary} with this {@link DataTypeManager}.
     * <p>
     * If the available codecs in a dictionary change it should be re-registered with this {@link DataTypeManager}.
     *
     * @param dataTypeDictionary the {@link DataTypeDictionary}.
     */
    void registerTypeDictionary(DataTypeDictionary<?> dataTypeDictionary);

    /**
     * Get the {@link DataTypeDictionary} registered under {@code namespaceUri}.
     *
     * @param namespaceUri the namespace URI of the {@link DataTypeDictionary}.
     * @return the {@link DataTypeDictionary} registered under {@code namespaceUri}, or {@code null} if none was found.
     */
    @Nullable
    DataTypeDictionary getTypeDictionary(String namespaceUri);

    /**
     * Get the {@link DataTypeCodec} identified by {@code encodingId}. ().
     *
     * @param encodingId the {@link NodeId} of the DataTypeEncoding Node for the DataType.
     * @return a {@link DataTypeCodec}, or {@code null} if none was found.
     */
    @Nullable
    DataTypeCodec getCodec(NodeId encodingId);

    /**
     * Get the {@link OpcUaBinaryDataTypeCodec} identified by {@code encodingId}.
     *
     * @param encodingId the {@link NodeId} of the DataTypeEncoding node for the DataType of the requested codec.
     * @return an {@link OpcUaBinaryDataTypeCodec}, or {@code null} if none was found.
     */
    @Nullable
    OpcUaBinaryDataTypeCodec<?> getBinaryCodec(NodeId encodingId);

    /**
     * Get the {@link OpcUaXmlDataTypeCodec} identified by {@code encodingId}.
     *
     * @param encodingId the {@link NodeId} of the DataTypeEncoding node for the DataType of the requested codec.
     * @return an {@link OpcUaXmlDataTypeCodec}, or {@code null} if none was found.
     */
    @Nullable
    OpcUaXmlDataTypeCodec<?> getXmlCodec(NodeId encodingId);

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
     * Get the {@link OpcUaBinaryDataTypeCodec} identified by {@code description} from the dictionary identified by
     * {@code namespaceUri}.
     *
     * @param namespaceUri the namespace URI of the dictionary.
     * @param description  the value of the DataTypeDescription that identifies the codec in the dictionary.
     * @return an {@link OpcUaBinaryDataTypeCodec}, or {@code null} if none was found.
     */
    @Nullable
    default OpcUaBinaryDataTypeCodec<?> getBinaryCodec(String namespaceUri, String description) {
        DataTypeCodec codec = getCodec(namespaceUri, description);

        if (codec instanceof OpcUaBinaryDataTypeCodec) {
            return (OpcUaBinaryDataTypeCodec) codec;
        } else {
            return null;
        }
    }

    /**
     * Get the {@link OpcUaXmlDataTypeCodec} identified by {@code description} from the dictionary identified by
     * {@code namespaceUri}.
     *
     * @param namespaceUri the namespace URI of the dictionary.
     * @param description  the value of the DataTypeDescription that identifies the codec in the dictionary.
     * @return an {@link OpcUaXmlDataTypeCodec}, or {@code null} if none was found.
     */
    @Nullable
    default OpcUaXmlDataTypeCodec<?> getXmlCodec(String namespaceUri, String description) {
        DataTypeCodec codec = getCodec(namespaceUri, description);

        if (codec instanceof OpcUaXmlDataTypeCodec) {
            return (OpcUaXmlDataTypeCodec<?>) codec;
        } else {
            return null;
        }
    }

}
