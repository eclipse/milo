/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

public interface SessionSecurityDiagnosticsArrayType extends BaseDataVariableType {
    CompletableFuture<? extends SessionSecurityDiagnosticsType> getSessionSecurityDiagnosticsNode();

    CompletableFuture<SessionSecurityDiagnosticsDataType> getSessionSecurityDiagnostics();

    CompletableFuture<StatusCode> setSessionSecurityDiagnostics(SessionSecurityDiagnosticsDataType value);
}
