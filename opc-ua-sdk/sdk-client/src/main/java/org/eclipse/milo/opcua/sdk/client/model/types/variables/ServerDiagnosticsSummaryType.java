/*
 * Copyright (c) 2017 Kevin Herron
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
    CompletableFuture<? extends BaseDataVariableType> getServerViewCountNode();

    CompletableFuture<UInteger> getServerViewCount();

    CompletableFuture<StatusCode> setServerViewCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getCurrentSessionCountNode();

    CompletableFuture<UInteger> getCurrentSessionCount();

    CompletableFuture<StatusCode> setCurrentSessionCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getCumulatedSessionCountNode();

    CompletableFuture<UInteger> getCumulatedSessionCount();

    CompletableFuture<StatusCode> setCumulatedSessionCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getSecurityRejectedSessionCountNode();

    CompletableFuture<UInteger> getSecurityRejectedSessionCount();

    CompletableFuture<StatusCode> setSecurityRejectedSessionCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getRejectedSessionCountNode();

    CompletableFuture<UInteger> getRejectedSessionCount();

    CompletableFuture<StatusCode> setRejectedSessionCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getSessionTimeoutCountNode();

    CompletableFuture<UInteger> getSessionTimeoutCount();

    CompletableFuture<StatusCode> setSessionTimeoutCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getSessionAbortCountNode();

    CompletableFuture<UInteger> getSessionAbortCount();

    CompletableFuture<StatusCode> setSessionAbortCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getPublishingIntervalCountNode();

    CompletableFuture<UInteger> getPublishingIntervalCount();

    CompletableFuture<StatusCode> setPublishingIntervalCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getCurrentSubscriptionCountNode();

    CompletableFuture<UInteger> getCurrentSubscriptionCount();

    CompletableFuture<StatusCode> setCurrentSubscriptionCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getCumulatedSubscriptionCountNode();

    CompletableFuture<UInteger> getCumulatedSubscriptionCount();

    CompletableFuture<StatusCode> setCumulatedSubscriptionCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getSecurityRejectedRequestsCountNode();

    CompletableFuture<UInteger> getSecurityRejectedRequestsCount();

    CompletableFuture<StatusCode> setSecurityRejectedRequestsCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getRejectedRequestsCountNode();

    CompletableFuture<UInteger> getRejectedRequestsCount();

    CompletableFuture<StatusCode> setRejectedRequestsCount(UInteger value);
}
