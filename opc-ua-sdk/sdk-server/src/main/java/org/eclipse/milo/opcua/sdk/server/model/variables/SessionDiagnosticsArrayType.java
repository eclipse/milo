package org.eclipse.milo.opcua.sdk.server.model.variables;

import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/7.13">https://reference.opcfoundation.org/v105/Core/docs/Part5/7.13</a>
 */
public interface SessionDiagnosticsArrayType extends BaseDataVariableType {
    SessionDiagnosticsVariableType getSessionDiagnosticsNode();

    SessionDiagnosticsDataType getSessionDiagnostics();

    void setSessionDiagnostics(SessionDiagnosticsDataType value);
}
