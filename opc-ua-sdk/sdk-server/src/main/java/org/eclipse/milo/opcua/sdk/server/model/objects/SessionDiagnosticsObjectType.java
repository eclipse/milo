package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.server.model.variables.SessionDiagnosticsVariableType;
import org.eclipse.milo.opcua.sdk.server.model.variables.SessionSecurityDiagnosticsType;
import org.eclipse.milo.opcua.sdk.server.model.variables.SubscriptionDiagnosticsArrayType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.3.5">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.3.5</a>
 */
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
