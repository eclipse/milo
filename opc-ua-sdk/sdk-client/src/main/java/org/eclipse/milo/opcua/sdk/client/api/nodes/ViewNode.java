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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

public interface ViewNode extends Node {

    /**
     * Get the ContainsNoLoops attribute value.
     * <p>
     * If quality and timestamps are required, see {@link #readContainsNoLoops()}.
     *
     * @return the ContainsNoLoops attribute value.
     */
    CompletableFuture<Boolean> getContainsNoLoops();

    /**
     * Get the EventNotifier attribute value.
     * <p>
     * If quality and timestamps are required, see {@link #readEventNotifier()}.
     *
     * @return the EventNotifier attribute value.
     */
    CompletableFuture<UByte> getEventNotifier();

    /**
     * Set the ContainsNoLoops attribute.
     *
     * @param containsNoLoops the {@link Boolean} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setContainsNoLoops(boolean containsNoLoops);

    /**
     * Set the EventNotifier attribute.
     *
     * @param eventNotifier the {@link UByte} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setEventNotifier(UByte eventNotifier);

    /**
     * Read the ContainsNoLoops attribute {@link DataValue}.
     *
     * @return the ContainsNoLoops attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readContainsNoLoops();

    /**
     * Read the EventNotifier attribute {@link DataValue}.
     *
     * @return the EventNotifier attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readEventNotifier();

    /**
     * Write a {@link DataValue} to the ContainsNoLoops attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeContainsNoLoops(DataValue value);

    /**
     * Write a {@link DataValue} to the EventNotifier attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeEventNotifier(DataValue value);

}
