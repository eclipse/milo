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
