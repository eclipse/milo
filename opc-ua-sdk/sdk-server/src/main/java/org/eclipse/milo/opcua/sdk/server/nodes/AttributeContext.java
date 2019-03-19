/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes;

import java.util.Optional;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.ServiceOperationContext;

public class AttributeContext {

    private final OpcUaServer server;
    private final Session session;

    public AttributeContext(OpcUaServer server) {
        this(server, null);
    }

    public AttributeContext(ServiceOperationContext<?, ?> operationContext) {
        this(operationContext.getServer(), operationContext.getSession().orElse(null));
    }

    public AttributeContext(OpcUaServer server, @Nullable Session session) {
        this.server = server;
        this.session = session;
    }

    public OpcUaServer getServer() {
        return server;
    }

    public Optional<Session> getSession() {
        return Optional.ofNullable(session);
    }

}
