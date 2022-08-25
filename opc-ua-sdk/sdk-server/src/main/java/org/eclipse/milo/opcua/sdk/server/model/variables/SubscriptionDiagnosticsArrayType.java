package org.eclipse.milo.opcua.sdk.server.model.variables;

import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/7.11">https://reference.opcfoundation.org/v105/Core/docs/Part5/7.11</a>
 */
public interface SubscriptionDiagnosticsArrayType extends BaseDataVariableType {
    SubscriptionDiagnosticsType getSubscriptionDiagnosticsNode();

    SubscriptionDiagnosticsDataType getSubscriptionDiagnostics();

    void setSubscriptionDiagnostics(SubscriptionDiagnosticsDataType value);
}
