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

import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;

public class SessionSecurityDiagnostics {

    private final Session session;

    public SessionSecurityDiagnostics(Session session) {
        this.session = session;
    }

    public NodeId getSessionId() {
        return session.getSessionId();
    }

    public String getClientUserIdOfSession() {
        return null; // TODO diagnostics
    }

    public String[] getClientUserIdHistory() {
        return null; // TODO diagnostics
    }

    public String getAuthenticationMechanism() {
        return null; // TODO diagnostics
    }

    public String getEncoding() {
        return null;
    }

    public String getTransportProtocol() {
        String endpointUrl = session.getEndpoint().getEndpointUrl();
        return EndpointUtil.getScheme(endpointUrl);
    }

    public MessageSecurityMode getSecurityMode() {
        return session.getSecurityConfiguration().getSecurityMode();
    }

    public String getSecurityPolicyUri() {
        return session.getSecurityConfiguration().getSecurityPolicy().getUri();
    }

    public ByteString getClientCertificate() {
        try {
            return session.getSecurityConfiguration().getClientCertificateBytes();
        } catch (UaException e) {
            return ByteString.NULL_VALUE;
        }
    }

    public SessionSecurityDiagnosticsDataType getSessionSecurityDiagnosticsDataType() {
        return new SessionSecurityDiagnosticsDataType(
            getSessionId(),
            getClientUserIdOfSession(),
            getClientUserIdHistory(),
            getAuthenticationMechanism(),
            getEncoding(),
            getTransportProtocol(),
            getSecurityMode(),
            getSecurityPolicyUri(),
            getClientCertificate()
        );
    }

}
