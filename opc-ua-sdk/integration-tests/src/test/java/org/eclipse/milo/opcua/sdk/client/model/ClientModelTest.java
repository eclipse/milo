package org.eclipse.milo.opcua.sdk.client.model;

import org.eclipse.milo.opcua.sdk.client.model.variables.ServerStatusTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClientModelTest extends AbstractClientServerTest {

    @Test
    public void serverStatusTypeNode() throws UaException {
        // Just a basic sanity check that some generated model methods works as expected

        UaNode node = client.getAddressSpace().getNode(NodeIds.Server_ServerStatus);

        ServerStatusTypeNode serverStatus = (ServerStatusTypeNode) node;

        assertNotNull(serverStatus.getStartTime());
        assertNotNull(serverStatus.getCurrentTime());
        assertNotNull(serverStatus.getState());
        assertNotNull(serverStatus.getBuildInfo());
        assertNotNull(serverStatus.getSecondsTillShutdown());
        assertNotNull(serverStatus.getShutdownReason());

        assertNotNull(serverStatus.getStartTimeNode());
        assertNotNull(serverStatus.getCurrentTimeNode());
        assertNotNull(serverStatus.getStateNode());
        assertNotNull(serverStatus.getBuildInfoNode());
        assertNotNull(serverStatus.getSecondsTillShutdownNode());
        assertNotNull(serverStatus.getShutdownReasonNode());

        assertNotNull(serverStatus.readStartTime());
        assertNotNull(serverStatus.readCurrentTime());
        assertNotNull(serverStatus.readState());
        assertNotNull(serverStatus.readBuildInfo());
        assertNotNull(serverStatus.readSecondsTillShutdown());
        assertNotNull(serverStatus.readShutdownReason());
    }

}
