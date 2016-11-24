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

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SamplingIntervalDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ServerDiagnosticsSummaryType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SubscriptionDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
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


    CompletableFuture<? extends PropertyType> enabledFlag();

    CompletableFuture<Boolean> getEnabledFlag();

    CompletableFuture<StatusCode> setEnabledFlag(Boolean value);

    CompletableFuture<? extends SessionsDiagnosticsSummaryType> sessionsDiagnosticsSummary();

    CompletableFuture<? extends ServerDiagnosticsSummaryType> serverDiagnosticsSummary();

    CompletableFuture<ServerDiagnosticsSummaryDataType> getServerDiagnosticsSummary();

    CompletableFuture<StatusCode> setServerDiagnosticsSummary(ServerDiagnosticsSummaryDataType value);

    CompletableFuture<? extends SamplingIntervalDiagnosticsArrayType> samplingIntervalDiagnosticsArray();

    CompletableFuture<SamplingIntervalDiagnosticsDataType[]> getSamplingIntervalDiagnosticsArray();

    CompletableFuture<StatusCode> setSamplingIntervalDiagnosticsArray(SamplingIntervalDiagnosticsDataType[] value);

    CompletableFuture<? extends SubscriptionDiagnosticsArrayType> subscriptionDiagnosticsArray();

    CompletableFuture<SubscriptionDiagnosticsDataType[]> getSubscriptionDiagnosticsArray();

    CompletableFuture<StatusCode> setSubscriptionDiagnosticsArray(SubscriptionDiagnosticsDataType[] value);

}