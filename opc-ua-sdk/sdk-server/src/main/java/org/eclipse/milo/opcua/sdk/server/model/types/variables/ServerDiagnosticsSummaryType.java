package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface ServerDiagnosticsSummaryType extends BaseDataVariableType {
    BaseDataVariableType getServerViewCountNode();

    UInteger getServerViewCount();

    void setServerViewCount(UInteger value);

    BaseDataVariableType getCurrentSessionCountNode();

    UInteger getCurrentSessionCount();

    void setCurrentSessionCount(UInteger value);

    BaseDataVariableType getCumulatedSessionCountNode();

    UInteger getCumulatedSessionCount();

    void setCumulatedSessionCount(UInteger value);

    BaseDataVariableType getSecurityRejectedSessionCountNode();

    UInteger getSecurityRejectedSessionCount();

    void setSecurityRejectedSessionCount(UInteger value);

    BaseDataVariableType getRejectedSessionCountNode();

    UInteger getRejectedSessionCount();

    void setRejectedSessionCount(UInteger value);

    BaseDataVariableType getSessionTimeoutCountNode();

    UInteger getSessionTimeoutCount();

    void setSessionTimeoutCount(UInteger value);

    BaseDataVariableType getSessionAbortCountNode();

    UInteger getSessionAbortCount();

    void setSessionAbortCount(UInteger value);

    BaseDataVariableType getPublishingIntervalCountNode();

    UInteger getPublishingIntervalCount();

    void setPublishingIntervalCount(UInteger value);

    BaseDataVariableType getCurrentSubscriptionCountNode();

    UInteger getCurrentSubscriptionCount();

    void setCurrentSubscriptionCount(UInteger value);

    BaseDataVariableType getCumulatedSubscriptionCountNode();

    UInteger getCumulatedSubscriptionCount();

    void setCumulatedSubscriptionCount(UInteger value);

    BaseDataVariableType getSecurityRejectedRequestsCountNode();

    UInteger getSecurityRejectedRequestsCount();

    void setSecurityRejectedRequestsCount(UInteger value);

    BaseDataVariableType getRejectedRequestsCountNode();

    UInteger getRejectedRequestsCount();

    void setRejectedRequestsCount(UInteger value);
}
