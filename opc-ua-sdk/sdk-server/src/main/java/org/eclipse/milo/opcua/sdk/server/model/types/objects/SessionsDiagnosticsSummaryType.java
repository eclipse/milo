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
