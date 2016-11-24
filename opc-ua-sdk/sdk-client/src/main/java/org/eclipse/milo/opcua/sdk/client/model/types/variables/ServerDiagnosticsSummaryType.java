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

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;


public interface ServerDiagnosticsSummaryType extends BaseDataVariableType {


    CompletableFuture<? extends BaseDataVariableType> serverViewCount();

    CompletableFuture<UInteger> getServerViewCount();

    CompletableFuture<StatusCode> setServerViewCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> currentSessionCount();

    CompletableFuture<UInteger> getCurrentSessionCount();

    CompletableFuture<StatusCode> setCurrentSessionCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> cumulatedSessionCount();

    CompletableFuture<UInteger> getCumulatedSessionCount();

    CompletableFuture<StatusCode> setCumulatedSessionCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> securityRejectedSessionCount();

    CompletableFuture<UInteger> getSecurityRejectedSessionCount();

    CompletableFuture<StatusCode> setSecurityRejectedSessionCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> rejectedSessionCount();

    CompletableFuture<UInteger> getRejectedSessionCount();

    CompletableFuture<StatusCode> setRejectedSessionCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> sessionTimeoutCount();

    CompletableFuture<UInteger> getSessionTimeoutCount();

    CompletableFuture<StatusCode> setSessionTimeoutCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> sessionAbortCount();

    CompletableFuture<UInteger> getSessionAbortCount();

    CompletableFuture<StatusCode> setSessionAbortCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> publishingIntervalCount();

    CompletableFuture<UInteger> getPublishingIntervalCount();

    CompletableFuture<StatusCode> setPublishingIntervalCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> currentSubscriptionCount();

    CompletableFuture<UInteger> getCurrentSubscriptionCount();

    CompletableFuture<StatusCode> setCurrentSubscriptionCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> cumulatedSubscriptionCount();

    CompletableFuture<UInteger> getCumulatedSubscriptionCount();

    CompletableFuture<StatusCode> setCumulatedSubscriptionCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> securityRejectedRequestsCount();

    CompletableFuture<UInteger> getSecurityRejectedRequestsCount();

    CompletableFuture<StatusCode> setSecurityRejectedRequestsCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> rejectedRequestsCount();

    CompletableFuture<UInteger> getRejectedRequestsCount();

    CompletableFuture<StatusCode> setRejectedRequestsCount(UInteger value);


}