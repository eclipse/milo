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

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.server.model.types.variables.SessionDiagnosticsVariableType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SessionSecurityDiagnosticsType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SubscriptionDiagnosticsArrayType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

public interface SessionDiagnosticsObjectType extends BaseObjectType {

    SessionDiagnosticsDataType getSessionDiagnostics();

    SessionDiagnosticsVariableType getSessionDiagnosticsNode();

    void setSessionDiagnostics(SessionDiagnosticsDataType value);

    SessionSecurityDiagnosticsDataType getSessionSecurityDiagnostics();

    SessionSecurityDiagnosticsType getSessionSecurityDiagnosticsNode();

    void setSessionSecurityDiagnostics(SessionSecurityDiagnosticsDataType value);

    SubscriptionDiagnosticsDataType[] getSubscriptionDiagnosticsArray();

    SubscriptionDiagnosticsArrayType getSubscriptionDiagnosticsArrayNode();

    void setSubscriptionDiagnosticsArray(SubscriptionDiagnosticsDataType[] value);
}
