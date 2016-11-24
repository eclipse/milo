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

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.Session;

public interface AccessContext {

    /**
     * Get the {@link Session} associated with this access request, if present.
     * <p>
     * If empty, the access request is internal and no user- or session-related restrictions should be applied.
     *
     * @return the {@link Session} associated with this access request, if present.
     */
    Optional<Session> getSession();

}
