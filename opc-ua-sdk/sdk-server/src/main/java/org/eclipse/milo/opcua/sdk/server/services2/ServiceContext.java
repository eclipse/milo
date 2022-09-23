/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.services2;

import java.util.Optional;

import io.netty.channel.Channel;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.channel.SecureChannel;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;

public class ServiceContext implements ServiceRequestContext {

    private volatile Session session;

    private final OpcUaServer server;
    private final ServiceRequestContext serviceRequestContext;

    public ServiceContext(OpcUaServer server, ServiceRequestContext serviceRequestContext) {
        this.server = server;
        this.serviceRequestContext = serviceRequestContext;
    }

    public OpcUaServer getServer() {
        return server;
    }

    public Optional<Session> getSession() {
        return Optional.ofNullable(session);
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String getEndpointUrl() {
        return serviceRequestContext.getEndpointUrl();
    }

    @Override
    public Channel getChannel() {
        return serviceRequestContext.getChannel();
    }

    @Override
    public SecureChannel getSecureChannel() {
        return serviceRequestContext.getSecureChannel();
    }

    @Override
    public Long receivedAtNanos() {
        return serviceRequestContext.receivedAtNanos();
    }

    static ResponseHeader createResponseHeader(UaRequestMessageType request) {
        return createResponseHeader(request, StatusCode.GOOD);
    }

    static ResponseHeader createResponseHeader(UaRequestMessageType request, long statusCode) {
        return createResponseHeader(request, new StatusCode(statusCode));
    }

    static ResponseHeader createResponseHeader(UaRequestMessageType request, StatusCode serviceResult) {
        return new ResponseHeader(
            DateTime.now(),
            request.getRequestHeader().getRequestHandle(),
            serviceResult,
            null, null, null
        );
    }

    static ResponseHeader createResponseHeader(UaRequestMessageType request, DiagnosticInfo[] diagnosticInfos) {
        // TODO use DiagnosticInfo to create crazy header...
        return createResponseHeader(request, StatusCode.GOOD);
    }

}
