/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface SamplingIntervalDiagnosticsType extends BaseDataVariableType {
    CompletableFuture<? extends BaseDataVariableType> getSamplingIntervalNode();

    CompletableFuture<Double> getSamplingInterval();

    CompletableFuture<StatusCode> setSamplingInterval(Double value);

    CompletableFuture<? extends BaseDataVariableType> getSampledMonitoredItemsCountNode();

    CompletableFuture<UInteger> getSampledMonitoredItemsCount();

    CompletableFuture<StatusCode> setSampledMonitoredItemsCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getMaxSampledMonitoredItemsCountNode();

    CompletableFuture<UInteger> getMaxSampledMonitoredItemsCount();

    CompletableFuture<StatusCode> setMaxSampledMonitoredItemsCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getDisabledMonitoredItemsSamplingCountNode();

    CompletableFuture<UInteger> getDisabledMonitoredItemsSamplingCount();

    CompletableFuture<StatusCode> setDisabledMonitoredItemsSamplingCount(UInteger value);
}
