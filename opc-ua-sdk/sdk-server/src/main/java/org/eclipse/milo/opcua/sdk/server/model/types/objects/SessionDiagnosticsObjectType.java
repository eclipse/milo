/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.server.model.types.variables.SessionDiagnosticsVariableType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SessionSecurityDiagnosticsType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SubscriptionDiagnosticsArrayType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

public interface SessionDiagnosticsObjectType extends BaseObjectType {
    SessionDiagnosticsVariableType getSessionDiagnosticsNode();

    SessionDiagnosticsDataType getSessionDiagnostics();

    void setSessionDiagnostics(SessionDiagnosticsDataType value);

    SessionSecurityDiagnosticsType getSessionSecurityDiagnosticsNode();

    SessionSecurityDiagnosticsDataType getSessionSecurityDiagnostics();

    void setSessionSecurityDiagnostics(SessionSecurityDiagnosticsDataType value);

    SubscriptionDiagnosticsArrayType getSubscriptionDiagnosticsArrayNode();

    SubscriptionDiagnosticsDataType[] getSubscriptionDiagnosticsArray();

    void setSubscriptionDiagnosticsArray(SubscriptionDiagnosticsDataType[] value);
}
