/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.client.transport;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;

public interface UaTransport {

    /**
     * Connect this transport.
     * <p>
     * Some transports may actually be connection-less (e.g. HTTP), making this method a misnomer, but it should be
     * called to initialize the transport regardless.
     *
     * @return the connected {@link UaTransport} instance.
     */
    CompletableFuture<UaTransport> connect();

    /**
     * Disconnect this transport.
     * <p>
     * Some transports may actually be connection-less (e.g. HTTP), making this method a misnomer, but it should be
     * called to un-initialize the transport regardless.
     *
     * @return the disconnected {@link UaTransport} instance.
     */
    CompletableFuture<UaTransport> disconnect();

    /**
     * Send a {@link UaRequestMessage}, returning a {@link CompletableFuture} representing the result of the operation.
     *
     * @param request the {@link UaRequestMessage} to send.
     * @return a {@link CompletableFuture} representing the result of the operation.
     */
    CompletableFuture<UaResponseMessage> sendRequest(UaRequestMessage request);

}
