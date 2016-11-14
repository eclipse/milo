package org.eclipse.milo.opcua.sdk.server.nodes;

import java.util.Optional;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.OperationContext;

public class AttributeContext {

    private final OpcUaServer server;
    private final Session session;

    public AttributeContext(OpcUaServer server) {
        this(server, null);
    }

    public AttributeContext(OperationContext<?, ?> operationContext) {
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
