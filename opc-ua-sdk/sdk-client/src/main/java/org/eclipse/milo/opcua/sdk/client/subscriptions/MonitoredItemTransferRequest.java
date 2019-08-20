/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.subscriptions;

import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;

public class MonitoredItemTransferRequest {

    private final UInteger clientHandle;
    private final UInteger serverHandle;

    private final ReadValueId itemToMonitor;
    private final MonitoringMode monitoringMode;
    private final MonitoringParameters requestedParameters;

    public MonitoredItemTransferRequest(
        ReadValueId itemToMonitor,
        MonitoringMode monitoringMode,
        MonitoringParameters requestedParameters,
        UInteger clientHandle,
        UInteger serverHandle
    ) {

        this.clientHandle = clientHandle;
        this.serverHandle = serverHandle;

        this.itemToMonitor = itemToMonitor;
        this.monitoringMode = monitoringMode;
        this.requestedParameters = requestedParameters;
    }

    UInteger getClientHandle() {
        return clientHandle;
    }

    UInteger getServerHandle() {
        return serverHandle;
    }

    ReadValueId getItemToMonitor() {
        return itemToMonitor;
    }

    MonitoringMode getMonitoringMode() {
        return monitoringMode;
    }

    MonitoringParameters getRequestedParameters() {
        return requestedParameters;
    }

}
