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

public class ServerDiagnostics {

    private final LongAdder currentSessionCount = new LongAdder();
    private final LongAdder cumulativeSessionCount = new LongAdder();
    private final LongAdder securityRejectedSessionCount = new LongAdder();
    private final LongAdder rejectedSessionCount = new LongAdder();
    private final LongAdder sessionTimeoutCount = new LongAdder();
    private final LongAdder securityRejectedRequestCount = new LongAdder();
    private final LongAdder rejectedRequestCount = new LongAdder();

    /**
     * @return the number of sessions currently established in the server.
     */
    public LongAdder getCurrentSessionCount() {
        return currentSessionCount;
    }

    /**
     * @return the cumulative number of client sessions that have been established in the server since the server was
     * started (or restarted). This includes the current session count.
     */
    public LongAdder getCumulativeSessionCount() {
        return cumulativeSessionCount;
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

    public LongAdder getSecurityRejectedRequestCount() {
        return securityRejectedRequestCount;
    }

    public LongAdder getRejectedRequestCount() {
        return rejectedRequestCount;
    }

}
