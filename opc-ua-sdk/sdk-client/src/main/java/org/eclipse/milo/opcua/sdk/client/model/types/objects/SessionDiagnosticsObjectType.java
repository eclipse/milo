/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionDiagnosticsVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionSecurityDiagnosticsType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SubscriptionDiagnosticsArrayType;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

public interface SessionDiagnosticsObjectType extends BaseObjectType {
    CompletableFuture<? extends SessionDiagnosticsVariableType> getSessionDiagnosticsNode();

    CompletableFuture<SessionDiagnosticsDataType> getSessionDiagnostics();

    CompletableFuture<StatusCode> setSessionDiagnostics(SessionDiagnosticsDataType value);

    CompletableFuture<? extends SessionSecurityDiagnosticsType> getSessionSecurityDiagnosticsNode();

    CompletableFuture<SessionSecurityDiagnosticsDataType> getSessionSecurityDiagnostics();

    CompletableFuture<StatusCode> setSessionSecurityDiagnostics(SessionSecurityDiagnosticsDataType value);

    CompletableFuture<? extends SubscriptionDiagnosticsArrayType> getSubscriptionDiagnosticsArrayNode();

    CompletableFuture<SubscriptionDiagnosticsDataType[]> getSubscriptionDiagnosticsArray();

    CompletableFuture<StatusCode> setSubscriptionDiagnosticsArray(SubscriptionDiagnosticsDataType[] value);
}
