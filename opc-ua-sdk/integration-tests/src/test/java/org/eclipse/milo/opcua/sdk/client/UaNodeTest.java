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

import java.util.List;

import org.eclipse.milo.opcua.sdk.client.AddressSpace.BrowseOptions;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class UaNodeTest extends AbstractClientServerTest {

    @Test
    public void browse() throws UaException {
        AddressSpace addressSpace = client.getAddressSpace();

        UaNode serverNode = addressSpace.getNode(Identifiers.Server);

        BrowseOptions browseOptions = BrowseOptions.builder()
            .setReferenceType(Identifiers.HasProperty)
            .build();

        List<ReferenceDescription> references = serverNode.browse(browseOptions);
        assertEquals(5, references.size());
        assertTrue(references.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.Server_ServerArray)));
        assertTrue(references.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.Server_NamespaceArray)));
        assertTrue(references.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.Server_ServiceLevel)));
        assertTrue(references.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.Server_Auditing)));
        assertTrue(references.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.Server_EstimatedReturnTime)));
    }

    @Test
    public void browseNodes() throws UaException {
        AddressSpace addressSpace = client.getAddressSpace();

        UaNode serverNode = addressSpace.getNode(Identifiers.Server);

        BrowseOptions browseOptions = BrowseOptions.builder()
            .setReferenceType(Identifiers.HasProperty)
            .build();

        List<? extends UaNode> nodes = serverNode.browseNodes(browseOptions);

        assertEquals(5, nodes.size());
        assertTrue(nodes.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.Server_ServerArray)));
        assertTrue(nodes.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.Server_NamespaceArray)));
        assertTrue(nodes.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.Server_ServiceLevel)));
        assertTrue(nodes.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.Server_Auditing)));
        assertTrue(nodes.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.Server_EstimatedReturnTime)));
    }

    @Test
    public void refresh() throws UaException {
        fail("not implemented");
    }


    @Test
    public void synchronize() throws UaException {
        fail("not implemented");
    }

}
