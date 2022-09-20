/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.client.uasc;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.jetbrains.annotations.Nullable;

public class UascMessage {

    private final long requestId;

    private UascMessage(long requestId) {
        this.requestId = requestId;
    }

    public long getRequestId() {
        return requestId;
    }

    public static final class UascRequest extends UascMessage {

        private final UaRequestMessageType requestMessage;

        public UascRequest(long requestId, UaRequestMessageType requestMessage) {
            super(requestId);

            this.requestMessage = requestMessage;
        }

        public UaRequestMessageType getRequestMessage() {
            return requestMessage;
        }

    }

    public static final class UascResponse extends UascMessage {

        private final UaResponseMessageType responseMessage;
        private final UaException exception;

        private UascResponse(
            long requestId,
            @Nullable UaResponseMessageType responseMessage,
            @Nullable UaException exception
        ) {

            super(requestId);

            this.responseMessage = responseMessage;
            this.exception = exception;

            // response can't both succeed and fail, one must be null
            assert responseMessage == null || exception == null;
        }

        public @Nullable UaResponseMessageType getResponseMessage() {
            return responseMessage;
        }

        public @Nullable UaException getException() {
            return exception;
        }

        public boolean isSuccess() {
            return responseMessage != null;
        }

        public boolean isFailure() {
            return exception != null;
        }

        public static UascResponse success(long requestId, UaResponseMessageType responseMessage) {
            return new UascResponse(requestId, responseMessage, null);
        }

        public static UascResponse failure(long requestId, UaException exception) {
            return new UascResponse(requestId, null, exception);
        }

    }

}
