/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
