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

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionSecurityDiagnosticsArrayType;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

public interface SessionsDiagnosticsSummaryType extends BaseObjectType {
    CompletableFuture<? extends SessionDiagnosticsArrayType> getSessionDiagnosticsArrayNode();

    CompletableFuture<SessionDiagnosticsDataType[]> getSessionDiagnosticsArray();

    CompletableFuture<StatusCode> setSessionDiagnosticsArray(SessionDiagnosticsDataType[] value);

    CompletableFuture<? extends SessionSecurityDiagnosticsArrayType> getSessionSecurityDiagnosticsArrayNode();

    CompletableFuture<SessionSecurityDiagnosticsDataType[]> getSessionSecurityDiagnosticsArray();

    CompletableFuture<StatusCode> setSessionSecurityDiagnosticsArray(SessionSecurityDiagnosticsDataType[] value);
}
