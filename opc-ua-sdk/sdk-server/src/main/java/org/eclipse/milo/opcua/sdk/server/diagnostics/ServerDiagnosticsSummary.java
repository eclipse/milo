/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.diagnostics;

import java.util.concurrent.atomic.LongAdder;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerDiagnosticsSummaryDataType;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class ServerDiagnosticsSummary {

    private final LongAdder cumulatedSessionCount = new LongAdder();
    private final LongAdder securityRejectedSessionCount = new LongAdder();
    private final LongAdder rejectedSessionCount = new LongAdder();
    private final LongAdder sessionTimeoutCount = new LongAdder();
    private final LongAdder sessionAbortCount = new LongAdder();
    private final LongAdder cumulatedSubscriptionCount = new LongAdder();

    private final OpcUaServer server;

    public ServerDiagnosticsSummary(OpcUaServer server) {
        this.server = server;
    }

    public UInteger getCurrentViewCount() {
        return server.getAddressSpaceManager().getViewCount();
    }

    /**
     * @return the number of sessions currently established in the server.
     */
    public UInteger getCurrentSessionCount() {
        return server.getSessionManager().getCurrentSessionCount();
    }

    /**
     * @return the cumulative number of client sessions that have been established in the server since the server was
     * started (or restarted). This includes the current session count.
     */
    public LongAdder getCumulatedSessionCount() {
        return cumulatedSessionCount;
    }

    /**
     * @return the number of client session establishment requests (ActivateSession and CreateSession) that were
     * rejected due to security constraints since the server was started (or restarted).
     */
    public LongAdder getSecurityRejectedSessionCount() {
        return securityRejectedSessionCount;
    }

    /**
     * @return the number of client session establishment requests (ActivateSession and CreateSession) that were
     * rejected since the server was started (or restarted). This number includes the security rejected session count.
     */
    public LongAdder getRejectedSessionCount() {
        return rejectedSessionCount;
    }

    /**
     * @return the number of client sessions that were closed due to timeout since the server was started (or
     * restarted).
     */
    public LongAdder getSessionTimeoutCount() {
        return sessionTimeoutCount;
    }

    public LongAdder getSessionAbortCount() {
        return sessionAbortCount;
    }

    public UInteger getCurrentSubscriptionCount() {
        return uint(server.getSubscriptions().size());
    }

    public LongAdder getCumulatedSubscriptionCount() {
        return cumulatedSubscriptionCount;
    }

    public UInteger getPublishingIntervalCount() {
        return uint(
            server.getSubscriptions()
                .values()
                .stream()
                .map(Subscription::getPublishingInterval)
                .distinct()
                .count()
        );
    }

    public UInteger getSecurityRejectedRequestCount() {
        return uint(server.getStackServer().getSecurityRejectedRequestCount().sum());
    }

    public UInteger getRejectedRequestCount() {
        return uint(server.getStackServer().getRejectedRequestCount().sum());
    }

    public ServerDiagnosticsSummaryDataType getServerDiagnosticsSummaryDataType() {
        return new ServerDiagnosticsSummaryDataType(
            getCurrentViewCount(),
            getCurrentSessionCount(),
            uint(getCumulatedSessionCount().sum()),
            uint(getSecurityRejectedSessionCount().sum()),
            uint(getRejectedSessionCount().sum()),
            uint(getSessionTimeoutCount().sum()),
            uint(getSessionAbortCount().sum()),
            getCurrentSubscriptionCount(),
            uint(getCumulatedSubscriptionCount().sum()),
            getPublishingIntervalCount(),
            getSecurityRejectedRequestCount(),
            getRejectedRequestCount()
        );
    }

}
