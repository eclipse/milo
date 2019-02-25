/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.AbstractNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.Namespace;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;

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

        reference.invert().ifPresent(
            virtual ->
                server.getNodeManager().addVirtualReference(virtual)
        );
    }

    @Override
    public void removeReference(Reference reference) {
        super.removeReference(reference);

        reference.invert().ifPresent(
            virtual ->
                server.getNodeManager().removeVirtualReference(virtual)
        );
    }

}
