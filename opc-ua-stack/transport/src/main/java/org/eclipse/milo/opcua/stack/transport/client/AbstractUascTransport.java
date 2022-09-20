/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.client;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.transport.client.uasc.ServiceResponseHandler;

public abstract class AbstractUascTransport extends AbstractTransport implements OpcTransport {

    public AbstractUascTransport(OpcTransportConfig config) {
        super(config);
    }

    public class ServiceResponseHandlerImpl implements ServiceResponseHandler {

        @Override
        public void handleResponse(long requestId, UaResponseMessageType responseMessage) {
            CompletableFuture<UaResponseMessageType> responseFuture = pendingRequests.remove(requestId);

            if (responseFuture != null) {
                cancelRequestTimeout(requestId);

                // TODO use configurable executor
                Stack.sharedExecutor().submit(() -> responseFuture.complete(responseMessage));
            } else {
                logger.warn("Received response for unknown request, requestId={}", requestId);
            }
        }

        @Override
        public void handleSendFailure(long requestId, UaException exception) {
            CompletableFuture<UaResponseMessageType> responseFuture = pendingRequests.remove(requestId);

            if (responseFuture != null) {
                cancelRequestTimeout(requestId);

                // TODO use configurable executor
                Stack.sharedExecutor().submit(() -> responseFuture.completeExceptionally(exception));
            } else {
                logger.warn("Send failed for unknown request, requestId={}", requestId);
            }
        }

        @Override
        public void handleReceiveFailure(long requestId, UaException exception) {
            CompletableFuture<UaResponseMessageType> responseFuture = pendingRequests.remove(requestId);

            if (responseFuture != null) {
                cancelRequestTimeout(requestId);

                // TODO use configurable executor
                Stack.sharedExecutor().submit(() -> responseFuture.completeExceptionally(exception));
            } else {
                logger.warn("Receive failed for unknown request, requestId={}", requestId);
            }
        }

        @Override
        public void handleChannelInactive() {
            // TODO
        }

    }

}
