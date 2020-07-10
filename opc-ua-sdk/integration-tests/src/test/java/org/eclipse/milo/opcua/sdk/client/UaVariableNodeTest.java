/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client;

import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableTypeNode;
import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UaVariableNodeTest extends AbstractClientServerTest {

    @Test
    public void getVariableComponent() throws UaException {
        UaVariableNode variableNode = client.getAddressSpace().getVariableNode(Identifiers.Server_ServerStatus);

        assertNotNull(variableNode.getVariableComponent("CurrentTime"));
    }

    @Test
    public void getTypeDefinition() throws UaException {
        UaVariableNode variableNode = client.getAddressSpace().getVariableNode(Identifiers.Server_ServerStatus);

        UaVariableTypeNode variableTypeNode = variableNode.getTypeDefinition();

        assertEquals(Identifiers.ServerStatusType, variableTypeNode.getNodeId());
    }

}
