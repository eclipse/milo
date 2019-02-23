/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

public interface SessionSecurityDiagnosticsArrayType extends BaseDataVariableType {
    SessionSecurityDiagnosticsType getSessionSecurityDiagnosticsNode();

    SessionSecurityDiagnosticsDataType getSessionSecurityDiagnostics();

    void setSessionSecurityDiagnostics(SessionSecurityDiagnosticsDataType value);
}
