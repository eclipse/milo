package org.eclipse.milo.examples.server;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig;
import org.eclipse.milo.opcua.stack.core.application.DefaultCertificateManager;
import org.eclipse.milo.opcua.stack.core.application.DefaultCertificateValidator;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.io.Files.createTempDir;
import static java.util.Collections.singletonList;
import static org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig.USER_TOKEN_POLICY_ANONYMOUS;

public class ExampleServer {

    private final OpcUaServer server;

    public ExampleServer() {
        OpcUaServerConfig serverConfig = OpcUaServerConfig.builder()
            .setApplicationUri("urn:eclipse:milo:examples:server")
            .setBindAddresses(newArrayList("localhost"))
            .setBindPort(12686)
            .setCertificateManager(new DefaultCertificateManager())
            .setCertificateValidator(new DefaultCertificateValidator(createTempDir()))
            .setServerName("example")
            .setUserTokenPolicies(singletonList(USER_TOKEN_POLICY_ANONYMOUS))
            .build();

        server = new OpcUaServer(serverConfig);

        server.getNamespaceManager().registerAndAdd(
            ExampleNamespace.NAMESPACE_URI,
            idx -> new ExampleNamespace(server, idx));

        server.startup();
    }

    public OpcUaServer getServer() {
        return server;
    }

    public void shutdown() {
        server.shutdown();
    }

}
