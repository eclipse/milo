package org.eclipse.milo.opcua.stack.server.tcp;

import org.eclipse.milo.opcua.stack.SecurityFixture;
import org.eclipse.milo.opcua.stack.server.config.UaTcpStackServerConfig;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SocketServerTest extends SecurityFixture {

    @Test
    public void testShutdownRemovesInstance() {
        UaTcpStackServerConfig config = UaTcpStackServerConfig.builder()
            .setServerName("test")
            .setCertificateManager(serverCertificateManager)
            .setCertificateValidator(serverCertificateValidator)
            .build();

        UaTcpStackServer server = new UaTcpStackServer(config);

        server.addEndpoint("opc.tcp://localhost:12685/test", null);

        server.startup();
        assertFalse(SocketServer.SOCKET_SERVERS.isEmpty());

        server.shutdown();
        assertTrue(SocketServer.SOCKET_SERVERS.isEmpty());
    }

}
