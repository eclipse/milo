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

    private final ReadValueId itemToMonitor;
    private final MonitoringMode monitoringMode;
    private final MonitoringParameters requestedParameters;
    private final UInteger monitoredItemId;

    public MonitoredItemTransferRequest(
        ReadValueId itemToMonitor,
        MonitoringMode monitoringMode,
        MonitoringParameters requestedParameters,
        UInteger monitoredItemId
    ) {

        this.itemToMonitor = itemToMonitor;
        this.monitoringMode = monitoringMode;
        this.requestedParameters = requestedParameters;
        this.monitoredItemId = monitoredItemId;
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

    UInteger getMonitoredItemId() {
        return monitoredItemId;
    }

}
