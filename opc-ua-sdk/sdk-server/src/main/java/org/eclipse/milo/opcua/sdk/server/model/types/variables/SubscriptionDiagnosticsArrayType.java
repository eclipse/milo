package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

public interface SubscriptionDiagnosticsArrayType extends BaseDataVariableType {
    SubscriptionDiagnosticsType getSubscriptionDiagnosticsNode();

    SubscriptionDiagnosticsDataType getSubscriptionDiagnostics();

    void setSubscriptionDiagnostics(SubscriptionDiagnosticsDataType value);
}
