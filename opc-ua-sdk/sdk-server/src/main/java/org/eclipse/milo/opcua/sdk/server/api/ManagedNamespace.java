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

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public abstract class ManagedNamespace extends ManagedAddressSpace implements Namespace {

    private final UShort namespaceIndex;

    public ManagedNamespace(OpcUaServer server, UShort namespaceIndex) {
        super(server);

        this.namespaceIndex = namespaceIndex;
    }

    @Override
    protected void onStartup() {
        super.onStartup();

        getServer().getAddressSpaceManager().register(this);
    }

    @Override
    protected void onShutdown() {
        super.onShutdown();

        getServer().getAddressSpaceManager().unregister(this);
    }

    @Override
    public final boolean filter(NodeId nodeId) {
        return nodeId.getNamespaceIndex().equals(namespaceIndex);
    }

    @Override
    public final UShort getNamespaceIndex() {
        return namespaceIndex;
    }

}
