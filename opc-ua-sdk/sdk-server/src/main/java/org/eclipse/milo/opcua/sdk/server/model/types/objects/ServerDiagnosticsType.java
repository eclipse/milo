package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SamplingIntervalDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.ServerDiagnosticsSummaryType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SubscriptionDiagnosticsArrayType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.SamplingIntervalDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerDiagnosticsSummaryDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

public interface ServerDiagnosticsType extends BaseObjectType {
    QualifiedProperty<Boolean> ENABLED_FLAG = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EnabledFlag",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    PropertyType getEnabledFlagNode();

    Boolean getEnabledFlag();

    void setEnabledFlag(Boolean value);

    ServerDiagnosticsSummaryType getServerDiagnosticsSummaryNode();

    ServerDiagnosticsSummaryDataType getServerDiagnosticsSummary();

    void setServerDiagnosticsSummary(ServerDiagnosticsSummaryDataType value);

    SamplingIntervalDiagnosticsArrayType getSamplingIntervalDiagnosticsArrayNode();

    SamplingIntervalDiagnosticsDataType[] getSamplingIntervalDiagnosticsArray();

    void setSamplingIntervalDiagnosticsArray(SamplingIntervalDiagnosticsDataType[] value);

    SubscriptionDiagnosticsArrayType getSubscriptionDiagnosticsArrayNode();

    SubscriptionDiagnosticsDataType[] getSubscriptionDiagnosticsArray();

    void setSubscriptionDiagnosticsArray(SubscriptionDiagnosticsDataType[] value);

    SessionsDiagnosticsSummaryType getSessionsDiagnosticsSummaryNode();
}
