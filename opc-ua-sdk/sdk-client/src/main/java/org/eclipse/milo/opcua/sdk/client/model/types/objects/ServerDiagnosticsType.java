/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SamplingIntervalDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ServerDiagnosticsSummaryType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SubscriptionDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
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

    CompletableFuture<? extends PropertyType> getEnabledFlagNode();

    CompletableFuture<Boolean> getEnabledFlag();

    CompletableFuture<StatusCode> setEnabledFlag(Boolean value);

    CompletableFuture<? extends ServerDiagnosticsSummaryType> getServerDiagnosticsSummaryNode();

    CompletableFuture<ServerDiagnosticsSummaryDataType> getServerDiagnosticsSummary();

    CompletableFuture<StatusCode> setServerDiagnosticsSummary(ServerDiagnosticsSummaryDataType value);

    CompletableFuture<? extends SamplingIntervalDiagnosticsArrayType> getSamplingIntervalDiagnosticsArrayNode();

    CompletableFuture<SamplingIntervalDiagnosticsDataType[]> getSamplingIntervalDiagnosticsArray();

    CompletableFuture<StatusCode> setSamplingIntervalDiagnosticsArray(SamplingIntervalDiagnosticsDataType[] value);

    CompletableFuture<? extends SubscriptionDiagnosticsArrayType> getSubscriptionDiagnosticsArrayNode();

    CompletableFuture<SubscriptionDiagnosticsDataType[]> getSubscriptionDiagnosticsArray();

    CompletableFuture<StatusCode> setSubscriptionDiagnosticsArray(SubscriptionDiagnosticsDataType[] value);

    CompletableFuture<? extends SessionsDiagnosticsSummaryType> getSessionsDiagnosticsSummaryNode();
}
