/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class EmptyNodeManager implements NodeManager<UaNode> {

    public static final EmptyNodeManager INSTANCE = new EmptyNodeManager();

    private EmptyNodeManager() {}

    @Override
    public void addNode(UaNode node) {}

    @Override
    public boolean containsNode(NodeId nodeId) {
        return false;
    }

    @Override
    public Optional<UaNode> getNode(NodeId nodeId) {
        return Optional.empty();
    }

    @Override
    public Optional<UaNode> removeNode(NodeId nodeId) {
        return Optional.empty();
    }

    @Override
    public void addReference(Reference reference) {}

    @Override
    public void addVirtualReference(Reference reference) {}

    @Override
    public void removeReference(Reference reference) {}

    @Override
    public void removeVirtualReference(Reference reference) {}

    @Override
    public List<Reference> getReferences(NodeId nodeId) {
        return Collections.emptyList();
    }

}
