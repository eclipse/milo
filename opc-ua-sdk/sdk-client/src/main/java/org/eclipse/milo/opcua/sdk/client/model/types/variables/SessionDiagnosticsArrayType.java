/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;

public interface SessionDiagnosticsArrayType extends BaseDataVariableType {
    CompletableFuture<? extends SessionDiagnosticsVariableType> getSessionDiagnosticsNode();

    CompletableFuture<SessionDiagnosticsDataType> getSessionDiagnostics();

    CompletableFuture<StatusCode> setSessionDiagnostics(SessionDiagnosticsDataType value);
}
