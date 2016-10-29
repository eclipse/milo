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

package org.eclipse.milo.opcua.sdk.core.model.variables;

import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;


public interface ServerDiagnosticsSummaryType extends BaseDataVariableType {


    UInteger getServerViewCount();

    BaseDataVariableType getServerViewCountNode();

    void setServerViewCount(UInteger value);

    UInteger getCurrentSessionCount();

    BaseDataVariableType getCurrentSessionCountNode();

    void setCurrentSessionCount(UInteger value);

    UInteger getCumulatedSessionCount();

    BaseDataVariableType getCumulatedSessionCountNode();

    void setCumulatedSessionCount(UInteger value);

    UInteger getSecurityRejectedSessionCount();

    BaseDataVariableType getSecurityRejectedSessionCountNode();

    void setSecurityRejectedSessionCount(UInteger value);

    UInteger getRejectedSessionCount();

    BaseDataVariableType getRejectedSessionCountNode();

    void setRejectedSessionCount(UInteger value);

    UInteger getSessionTimeoutCount();

    BaseDataVariableType getSessionTimeoutCountNode();

    void setSessionTimeoutCount(UInteger value);

    UInteger getSessionAbortCount();

    BaseDataVariableType getSessionAbortCountNode();

    void setSessionAbortCount(UInteger value);

    UInteger getPublishingIntervalCount();

    BaseDataVariableType getPublishingIntervalCountNode();

    void setPublishingIntervalCount(UInteger value);

    UInteger getCurrentSubscriptionCount();

    BaseDataVariableType getCurrentSubscriptionCountNode();

    void setCurrentSubscriptionCount(UInteger value);

    UInteger getCumulatedSubscriptionCount();

    BaseDataVariableType getCumulatedSubscriptionCountNode();

    void setCumulatedSubscriptionCount(UInteger value);

    UInteger getSecurityRejectedRequestsCount();

    BaseDataVariableType getSecurityRejectedRequestsCountNode();

    void setSecurityRejectedRequestsCount(UInteger value);

    UInteger getRejectedRequestsCount();

    BaseDataVariableType getRejectedRequestsCountNode();

    void setRejectedRequestsCount(UInteger value);
}
