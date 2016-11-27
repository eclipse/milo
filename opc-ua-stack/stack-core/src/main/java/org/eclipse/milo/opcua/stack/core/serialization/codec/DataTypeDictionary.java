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

public interface DataTypeDictionary<T extends DataTypeCodec> {

    /**
     * @return the namespace URI this {@link DataTypeDictionary} belongs to.
     */
    String getNamespaceUri();

    /**
     * Register a {@link DataTypeCodec}.
     *
     * @param codec       the {@link DataTypeCodec} to register.
     * @param description the value of the DataTypeDescription that identifies the codec in the dictionary.
     */
    void registerCodec(T codec, String description);

    /**
     * Get a {@link DataTypeCodec} registered with this dictionary.
     *
     * @param description the value of the DataTypeDescription that identifies the codec in the dictionary.
     * @return a {@link DataTypeCodec} for {@code description}, or {@code null} if none is found.
     */
    T getCodec(String description);

}
