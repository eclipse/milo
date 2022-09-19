/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;

public abstract class AbstractClient {

    private final OpcTransport transport;

    public AbstractClient(OpcTransport transport) {
        this.transport = transport;
    }

    public void connect() throws UaException {
        try {
            transport.connect().get();
        } catch (InterruptedException | ExecutionException e) {
            throw UaException.extract(e)
                .orElseGet(() -> new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public void disconnect() throws UaException {
        try {
            transport.disconnect().get();
        } catch (InterruptedException | ExecutionException e) {
            throw UaException.extract(e)
                .orElseGet(() -> new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public UaResponseMessageType sendRequest(UaRequestMessageType request) throws UaException {
        try {
            return transport.sendRequestMessage(request).get();
        } catch (InterruptedException | ExecutionException e) {
            throw UaException.extract(e)
                .orElseGet(() -> new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<UaResponseMessageType> sendRequestAsync(UaRequestMessageType request) {
        return transport.sendRequestMessage(request);
    }

}
