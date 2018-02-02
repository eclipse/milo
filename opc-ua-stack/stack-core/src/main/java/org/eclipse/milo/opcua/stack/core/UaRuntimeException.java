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

package org.eclipse.milo.opcua.stack.core;

import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class UaRuntimeException extends RuntimeException implements UaExceptionStatus {

    private final StatusCode statusCode;

    public UaRuntimeException(Throwable cause) {
        super(cause);

        this.statusCode = new StatusCode(StatusCodes.Bad_InternalError);
    }

    public UaRuntimeException(long statusCode) {
        this.statusCode = new StatusCode(statusCode);
    }

    public UaRuntimeException(long statusCode, String message) {
        super(message);

        this.statusCode = new StatusCode(statusCode);
    }

    public UaRuntimeException(long statusCode, Throwable cause) {
        super(cause);

        this.statusCode = new StatusCode(statusCode);
    }
    
    @Override
    public StatusCode getStatusCode() {
        return statusCode;
    }

}
