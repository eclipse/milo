/*
 * Copyright (c) 2018 Kevin Herron
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

package org.eclipse.milo.opcua.stack.client.transport;

import java.util.concurrent.CompletableFuture;

import io.netty.channel.Channel;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;

public interface UaTransport {

    CompletableFuture<UaTransport> connect();

    CompletableFuture<UaTransport> disconnect();

    // TODO remove this
    CompletableFuture<Channel> channel();

    /**
     * Send a {@link UaRequestMessage}, returning a {@link CompletableFuture} representing the result of the operation.
     *
     * @param request the {@link UaRequestMessage} to send.
     * @return a {@link CompletableFuture} representing the result of the operation.
     */
    CompletableFuture<UaResponseMessage> sendRequest(UaRequestMessage request);

}
