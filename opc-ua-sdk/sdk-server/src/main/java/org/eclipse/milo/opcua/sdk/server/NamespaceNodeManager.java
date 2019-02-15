/*
 * Copyright (c) 2016 Kevin Herron and others
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

package org.eclipse.milo.opcua.sdk.server;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.AbstractNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.Namespace;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

/**
 * A smart {@link NodeManager} implementation suitable for use by {@link Namespace}s.
 * <p>
 * When references are added or removed, a virtual inverse reference is automatically added or removed from the
 * appropriate {@link NodeManager} by way of the {@link ServerNodeManager}.
 */
public class NamespaceNodeManager extends AbstractNodeManager<UaNode> {

    private final OpcUaServer server;

    public NamespaceNodeManager(OpcUaServer server) {
        this.server = server;
    }

    @Override
    public void addReference(Reference reference) {
        super.addReference(reference);

        virtualInverse(reference).ifPresent(virtual -> server.getNodeManager().addVirtualReference(virtual));
    }

    @Override
    public void removeReference(Reference reference) {
        super.removeReference(reference);

        virtualInverse(reference).ifPresent(virtual -> server.getNodeManager().removeVirtualReference(virtual));
    }

    private Optional<Reference> virtualInverse(Reference reference) {
        return reference.getTargetNodeId().local().flatMap(sourceNodeId -> {
            // Target NodeClass is NodeClass of Node identified by original sourceNodeId
            Optional<UaNode> node = getNode(reference.getSourceNodeId());

            Optional<NodeClass> targetNodeClass = node.map(Node::getNodeClass);

            return targetNodeClass.map(nodeClass ->
                new Reference(
                    sourceNodeId,
                    reference.getReferenceTypeId(),
                    reference.getSourceNodeId().expanded(),
                    nodeClass,
                    !reference.isForward()
                )
            );
        });
    }

}
