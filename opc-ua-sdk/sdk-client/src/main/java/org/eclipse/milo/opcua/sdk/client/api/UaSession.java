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

package org.eclipse.milo.opcua.sdk.client.api;

import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;

public interface UaSession {

    /**
     * Get the authentication token assigned by the server.
     *
     * @return a unique {@link NodeId} assigned by the server to the session.
     */
    NodeId getAuthenticationToken();

    /**
     * Get the session id assigned by the server.
     * <p>
     * This identifier is used to access the diagnostics information for the session in the server address space. It is
     * also used in the audit logs and any events that report information related to the session.
     *
     * @return a unique {@link NodeId} assigned by the server to the session.
     */
    NodeId getSessionId();

    /**
     * @return the human-readable name assigned to this session by the client.
     */
    String getSessionName();

    /**
     * Get the revised session timeout, that is, the number of milliseconds a session may remain open without activity.
     *
     * @return the revised session timeout.
     */
    Double getSessionTimeout();

    /**
     * @return the maximum allowable size for any request sent to the server.
     */
    UInteger getMaxRequestSize();

    /**
     * @return the last nonce received from the server.
     */
    ByteString getServerNonce();

    /**
     * @return the server application instance certificate.
     */
    ByteString getServerCertificate();

    /**
     * @return the server {@link SignedSoftwareCertificate}s.
     */
    SignedSoftwareCertificate[] getServerSoftwareCertificates();

}
