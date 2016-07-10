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

package org.eclipse.milo.opcua.sdk.client.api.nodes.attached;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

public interface UaMethodNode extends UaNode {

    /**
     * Read the Executable attribute {@link DataValue}.
     *
     * @return the Executable attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readExecutable();

    /**
     * Read the Executable attribute.
     * <p>
     * If quality and timestamps are required, see {@link #readExecutable()}.
     *
     * @return the Executable attribute.
     */
    default CompletableFuture<Boolean> readExecutableAttribute() {
        return readExecutable().thenApply(v -> (Boolean) v.getValue().getValue());
    }

    /**
     * Read the UserExecutable attribute {@link DataValue}.
     *
     * @return the UserExecutable attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readUserExecutable();

    /**
     * Read the UserExecutable attribute.
     * <p>
     * If quality and timestamps are required, see {@link #readUserExecutable()}.
     *
     * @return the UserExecutable attribute.
     */
    default CompletableFuture<Boolean> readUserExecutableAttribute() {
        return readUserExecutable().thenApply(v -> (Boolean) v.getValue().getValue());
    }

    /**
     * Write a {@link DataValue} to the Executable attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeExecutable(DataValue value);

    /**
     * Write a {@link Boolean} to the Executable attribute.
     *
     * @param executable the {@link Boolean} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    default CompletableFuture<StatusCode> writeExecutableAttribute(Boolean executable) {
        return writeExecutable(new DataValue(new Variant(executable)));
    }

    /**
     * Write a {@link DataValue} to the UserExecutable attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeUserExecutable(DataValue value);

    /**
     * Write a {@link Boolean} to the UserExecutable attribute.
     *
     * @param userExecutable the {@link Boolean} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    default CompletableFuture<StatusCode> writeUserExecutableAttribute(Boolean userExecutable) {
        return writeExecutable(new DataValue(new Variant(userExecutable)));
    }

}
