package org.eclipse.milo.opcua.stack.server.tcp;

import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.stack.SecurityFixture;
import org.eclipse.milo.opcua.stack.server.config.UaTcpStackServerConfig;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SocketServersTest extends SecurityFixture {

    @Test
    public void testShutdownRemovesInstance() throws ExecutionException, InterruptedException {
        UaTcpStackServerConfig config = UaTcpStackServerConfig.builder()
            .setServerName("test")
            .setCertificateManager(serverCertificateManager)
            .setCertificateValidator(serverCertificateValidator)
            .build();

        UaTcpStackServer server = new UaTcpStackServer(config);

        server.addEndpoint("opc.tcp://localhost:12685/test", null);

        server.startup().get();
        assertFalse(SocketServers.SERVERS.isEmpty());

        server.shutdown().get();
        assertTrue(SocketServers.SERVERS.isEmpty());
    }

}
