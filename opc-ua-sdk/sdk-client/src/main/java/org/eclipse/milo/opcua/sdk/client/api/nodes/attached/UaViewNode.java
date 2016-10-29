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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

public interface UaViewNode extends UaNode {

    /**
     * Read the ContainsNoLoops attribute {@link DataValue}.
     *
     * @return the ContainsNoLoops attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readContainsNoLoops();

    /**
     * Read the ContainsNoLoops attribute value.
     *
     * @return the ContainsNoLoops attribute value.
     */
    default CompletableFuture<Boolean> readContainsNoLoopsAttribute() {
        return readContainsNoLoops().thenApply(v -> (Boolean) v.getValue().getValue());
    }

    /**
     * Read the EventNotifier attribute {@link DataValue}.
     *
     * @return the EventNotifier attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readEventNotifier();

    /**
     * Read the EventNotifier attribute value.
     *
     * @return the EventNotifier attribute value.
     */
    default CompletableFuture<UByte> readEventNotifierAttribute() {
        return readEventNotifier().thenApply(v -> (UByte) v.getValue().getValue());
    }

    /**
     * Write a {@link DataValue} to the ContainsNoLoops attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeContainsNoLoops(DataValue value);

    /**
     * Write a {@link Boolean} to the ContainsNoLoops attribute.
     *
     * @param containsNoLoops the {@link Boolean} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    default CompletableFuture<StatusCode> writeContainsNoLoopsAttribute(Boolean containsNoLoops) {
        return writeContainsNoLoops(new DataValue(new Variant(containsNoLoops)));
    }

    /**
     * Write a {@link DataValue} to the EventNotifier attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeEventNotifier(DataValue value);

    /**
     * Write a {@link UByte} to the EventNotifier attribute.
     *
     * @param eventNotifier the {@link UByte} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    default CompletableFuture<StatusCode> writeEventNotifierAttribute(UByte eventNotifier) {
        return writeEventNotifier(new DataValue(new Variant(eventNotifier)));
    }

}
