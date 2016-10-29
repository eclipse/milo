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

package org.eclipse.milo.opcua.sdk.core.model.objects;

import org.eclipse.milo.opcua.sdk.core.model.variables.SessionDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.core.model.variables.SessionSecurityDiagnosticsArrayType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

public interface SessionsDiagnosticsSummaryType extends BaseObjectType {

    SessionDiagnosticsDataType[] getSessionDiagnosticsArray();

    SessionDiagnosticsArrayType getSessionDiagnosticsArrayNode();

    void setSessionDiagnosticsArray(SessionDiagnosticsDataType[] value);

    SessionSecurityDiagnosticsDataType[] getSessionSecurityDiagnosticsArray();

    SessionSecurityDiagnosticsArrayType getSessionSecurityDiagnosticsArrayNode();

    void setSessionSecurityDiagnosticsArray(SessionSecurityDiagnosticsDataType[] value);
}
