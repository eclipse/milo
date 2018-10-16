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

package org.eclipse.milo.opcua.sdk.server.events;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;

public class ValidationException extends UaException {

    private final DiagnosticInfo diagnosticInfo;

    public ValidationException(long statusCode) {
        this(statusCode, DiagnosticInfo.NULL_VALUE);
    }

    public ValidationException(long statusCode, DiagnosticInfo diagnosticInfo) {
        super(statusCode);
        this.diagnosticInfo = diagnosticInfo;
    }

    public DiagnosticInfo getDiagnosticInfo() {
        return diagnosticInfo;
    }

}
