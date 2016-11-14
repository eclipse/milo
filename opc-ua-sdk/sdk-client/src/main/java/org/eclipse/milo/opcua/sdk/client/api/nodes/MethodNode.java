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

package org.eclipse.milo.opcua.sdk.client.api.nodes;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface MethodNode extends Node {

    /**
     * Get the Executable attribute.
     * <p>
     * If quality and timestamps are required, see {@link #readExecutable()}.
     *
     * @return the Executable attribute.
     */
    CompletableFuture<Boolean> getExecutable();

    /**
     * Get the UserExecutable attribute.
     * <p>
     * If quality and timestamps are required, see {@link #readUserExecutable()}.
     *
     * @return the UserExecutable attribute.
     */
    CompletableFuture<Boolean> getUserExecutable();

    /**
     * Set the Executable attribute.
     *
     * @param executable the {@link Boolean} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setExecutable(boolean executable);

    /**
     * Set the UserExecutable attribute.
     *
     * @param userExecutable the {@link Boolean} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setUserExecutable(boolean userExecutable);

    /**
     * Read the UserExecutable attribute {@link DataValue}.
     *
     * @return the UserExecutable attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readExecutable();

    /**
     * Read the UserExecutable attribute {@link DataValue}.
     *
     * @return the UserExecutable attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readUserExecutable();

    /**
     * Write a {@link DataValue} to the Executable attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeExecutable(DataValue value);

    /**
     * Write a {@link DataValue} to the UserExecutable attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeUserExecutable(DataValue value);

}
