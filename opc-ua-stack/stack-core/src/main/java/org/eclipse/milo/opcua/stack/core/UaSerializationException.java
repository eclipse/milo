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

public class UaSerializationException extends UaRuntimeException {

    public UaSerializationException(long statusCode, Throwable cause) {
        super(statusCode, cause);
    }

    public UaSerializationException(long statusCode, String message) {
        super(statusCode, message);
    }

}
