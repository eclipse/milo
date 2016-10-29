/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.core.model.objects;

import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.sdk.core.model.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.model.variables.SamplingIntervalDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.core.model.variables.ServerDiagnosticsSummaryType;
import org.eclipse.milo.opcua.sdk.core.model.variables.SubscriptionDiagnosticsArrayType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.structured.SamplingIntervalDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerDiagnosticsSummaryDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

public interface ServerDiagnosticsType extends BaseObjectType {

    Property<Boolean> ENABLED_FLAG = new BasicProperty<>(
        QualifiedName.parse("0:EnabledFlag"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Boolean getEnabledFlag();

    PropertyType getEnabledFlagNode();

    void setEnabledFlag(Boolean value);

    SessionsDiagnosticsSummaryType getSessionsDiagnosticsSummaryNode();

    ServerDiagnosticsSummaryDataType getServerDiagnosticsSummary();

    ServerDiagnosticsSummaryType getServerDiagnosticsSummaryNode();

    void setServerDiagnosticsSummary(ServerDiagnosticsSummaryDataType value);

    SamplingIntervalDiagnosticsDataType[] getSamplingIntervalDiagnosticsArray();

    SamplingIntervalDiagnosticsArrayType getSamplingIntervalDiagnosticsArrayNode();

    void setSamplingIntervalDiagnosticsArray(SamplingIntervalDiagnosticsDataType[] value);

    SubscriptionDiagnosticsDataType[] getSubscriptionDiagnosticsArray();

    SubscriptionDiagnosticsArrayType getSubscriptionDiagnosticsArrayNode();

    void setSubscriptionDiagnosticsArray(SubscriptionDiagnosticsDataType[] value);
}
