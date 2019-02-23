/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.channel;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class MessageAbortedException extends UaException {

    private final long requestId;

    MessageAbortedException(StatusCode statusCode, String message, long requestId) {
        super(statusCode, message);

        this.requestId = requestId;
    }

    public long getRequestId() {
        return requestId;
    }

}
