package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;

public interface SessionDiagnosticsArrayType extends BaseDataVariableType {
    SessionDiagnosticsVariableType getSessionDiagnosticsNode();

    SessionDiagnosticsDataType getSessionDiagnostics();

    void setSessionDiagnostics(SessionDiagnosticsDataType value);
}
