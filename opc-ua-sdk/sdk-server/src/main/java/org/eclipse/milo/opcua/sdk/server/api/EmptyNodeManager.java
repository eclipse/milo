/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class EmptyNodeManager<T extends Node> implements NodeManager<T> {

    public static EmptyNodeManager<UaNode> INSTANCE = new EmptyNodeManager<>();

    private EmptyNodeManager() {}

    @Override
    public boolean containsNode(NodeId nodeId) {
        return false;
    }

    @Override
    public Optional<T> addNode(T node) {
        return Optional.empty();
    }

    @Override
    public Optional<T> getNode(NodeId nodeId) {
        return Optional.empty();
    }

    @Override
    public Optional<T> removeNode(NodeId nodeId) {
        return Optional.empty();
    }

    @Override
    public void addReference(Reference reference) {}

    @Override
    public void removeReference(Reference reference) {}

    @Override
    public List<Reference> getReferences(NodeId sourceNodeId) {
        return Collections.emptyList();
    }

    @Override
    public List<Reference> getReferences(NodeId sourceNodeId, Predicate<Reference> filter) {
        return Collections.emptyList();
    }

}
