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

package org.eclipse.milo.opcua.sdk.server.api.config;

import java.util.concurrent.TimeUnit;

import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public interface OpcUaServerConfigLimits {

    default UInteger getMaxSessionCount() {
        return uint(550);
    }

    /**
     * Get the minimum allowed publishing interval.
     *
     * @return the minimum allowed publishing interval.
     */
    default Double getMinPublishingInterval() {
        return 10.0;
    }

    /**
     * Get the maximum allowed publishing interval.
     *
     * @return the maximum allowed publishing interval.
     */
    default Double getMaxPublishingInterval() {
        return (double) TimeUnit.MILLISECONDS.convert(24, TimeUnit.HOURS);
    }

    /**
     * Get the default publishing interval, used when the requested interval is either invalid or below the minimum.
     *
     * @return the default publishing interval.
     */
    default Double getDefaultPublishingInterval() {
        return 250.0;
    }

    default Double getMinSupportedSampleRate() {
        return 0.0;
    }

    default Double getMaxSupportedSampleRate() {
        return (double) TimeUnit.MILLISECONDS.convert(24, TimeUnit.HOURS);
    }

    default UShort getMaxBrowseContinuationPoints() {
        return ushort(UShort.MAX_VALUE);
    }

    default UShort getMaxQueryContinuationPoints() {
        return ushort(UShort.MAX_VALUE);
    }

    default UShort getMaxHistoryContinuationPoints() {
        return ushort(UShort.MAX_VALUE);
    }

    default UInteger getMaxArrayLength() {
        return uint(0x1FFFF);
    }

    default UInteger getMaxStringLength() {
        return uint(0x1FFFF);
    }

    default UInteger getMaxNodesPerRead() {
        return uint(0x1FFFF);
    }

    default UInteger getMaxNodesPerHistoryReadData() {
        return uint(0x1FFFF);
    }

    default UInteger getMaxNodesPerHistoryReadEvents() {
        return uint(0x1FFFF);
    }

    default UInteger getMaxNodesPerWrite() {
        return uint(0x1FFFF);
    }

    default UInteger getMaxNodesPerHistoryUpdateData() {
        return uint(0x1FFFF);
    }

    default UInteger getMaxNodesPerHistoryUpdateEvents() {
        return uint(0x1FFFF);
    }

    default UInteger getMaxNodesPerMethodCall() {
        return uint(0x1FFFF);
    }

    default UInteger getMaxNodesPerBrowse() {
        return uint(0x1FFFF);
    }

    default UInteger getMaxNodesPerRegisterNodes() {
        return uint(0x1FFFF);
    }

    default UInteger getMaxNodesPerTranslateBrowsePathsToNodeIds() {
        return uint(0x1FFFF);
    }

    default UInteger getMaxNodesPerNodeManagement() {
        return uint(0x1FFFF);
    }

    default UInteger getMaxMonitoredItemsPerCall() {
        return uint(0x1FFFF);
    }

}
