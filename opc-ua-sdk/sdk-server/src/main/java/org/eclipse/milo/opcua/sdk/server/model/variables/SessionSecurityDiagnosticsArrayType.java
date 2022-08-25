package org.eclipse.milo.opcua.sdk.server.model.variables;

import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/7.15">https://reference.opcfoundation.org/v105/Core/docs/Part5/7.15</a>
 */
public interface SessionSecurityDiagnosticsArrayType extends BaseDataVariableType {
    SessionSecurityDiagnosticsType getSessionSecurityDiagnosticsNode();

    SessionSecurityDiagnosticsDataType getSessionSecurityDiagnostics();

    void setSessionSecurityDiagnostics(SessionSecurityDiagnosticsDataType value);
}
