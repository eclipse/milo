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

package org.eclipse.milo.opcua.sdk.client;

import org.eclipse.milo.opcua.sdk.client.api.UaSession;

public interface SessionActivityListener {

    /**
     * An activated {@link UaSession} is now available.
     * <p>
     * Holding a reference to this session is not necessary or advised;
     * it is provided merely for informational purposes.
     */
    default void onSessionActive(UaSession session) {}

    /**
     * The previously activated {@link UaSession} is no longer available.
     * <p>
     * Holding a reference to this session is not necessary or advised;
     * it is provided merely for informational purposes.
     */
    default void onSessionInactive(UaSession session) {}

}
