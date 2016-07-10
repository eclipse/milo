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

package org.eclipse.milo.opcua.sdk.server.api;

import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;

public interface MonitoredItem {

    /**
     * @return the server-side id of this item.
     */
    UInteger getId();

    /**
     * @return the id of the {@link Subscription} this item belongs to.
     */
    UInteger getSubscriptionId();

    /**
     * @return the {@link ReadValueId} being monitored.
     */
    ReadValueId getReadValueId();

    /**
     * @return the {@link TimestampsToReturn}.
     */
    TimestampsToReturn getTimestampsToReturn();

    /**
     * @return {@code true} if this item should be sampled.
     */
    boolean isSamplingEnabled();

}
