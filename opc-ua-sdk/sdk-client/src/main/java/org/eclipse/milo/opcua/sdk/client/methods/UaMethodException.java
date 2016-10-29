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

package org.eclipse.milo.opcua.sdk.client.methods;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class UaMethodException extends UaException {

    private final StatusCode[] inputArgumentResults;
    private final DiagnosticInfo[] inputArgumentDiagnostics;

    public UaMethodException(StatusCode statusCode,
                             StatusCode[] inputArgumentResults,
                             DiagnosticInfo[] inputArgumentDiagnostics) {
        super(statusCode);

        this.inputArgumentResults = inputArgumentResults;
        this.inputArgumentDiagnostics = inputArgumentDiagnostics;
    }

    public UaMethodException(long statusCode,
                             StatusCode[] inputArgumentResults,
                             DiagnosticInfo[] inputArgumentDiagnostics) {
        super(statusCode);

        this.inputArgumentResults = inputArgumentResults;
        this.inputArgumentDiagnostics = inputArgumentDiagnostics;
    }

    public StatusCode[] getInputArgumentResults() {
        return inputArgumentResults;
    }

    public DiagnosticInfo[] getInputArgumentDiagnostics() {
        return inputArgumentDiagnostics;
    }

}
