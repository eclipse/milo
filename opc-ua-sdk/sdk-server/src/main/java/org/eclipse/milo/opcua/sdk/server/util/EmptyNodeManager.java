/*
 * Copyright (c) 2018 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
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
