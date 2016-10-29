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

public class UaRuntimeException extends RuntimeException {

    private final long statusCode;

    public UaRuntimeException(Throwable cause) {
        super(cause);

        this.statusCode = StatusCodes.Bad_InternalError;
    }

    public UaRuntimeException(long statusCode) {
        this.statusCode = statusCode;
    }

    public UaRuntimeException(long statusCode, String message) {
        super(message);

        this.statusCode = statusCode;
    }

    public UaRuntimeException(long statusCode, Throwable cause) {
        super(cause);

        this.statusCode = statusCode;
    }

    public long getStatusCode() {
        return statusCode;
    }

}
