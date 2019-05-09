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

import org.eclipse.milo.opcua.sdk.server.model.types.variables.SessionDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SessionSecurityDiagnosticsArrayType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

public interface SessionsDiagnosticsSummaryType extends BaseObjectType {
    SessionDiagnosticsArrayType getSessionDiagnosticsArrayNode();

    SessionDiagnosticsDataType[] getSessionDiagnosticsArray();

    void setSessionDiagnosticsArray(SessionDiagnosticsDataType[] value);

    SessionSecurityDiagnosticsArrayType getSessionSecurityDiagnosticsArrayNode();

    SessionSecurityDiagnosticsDataType[] getSessionSecurityDiagnosticsArray();

    void setSessionSecurityDiagnosticsArray(SessionSecurityDiagnosticsDataType[] value);
}
