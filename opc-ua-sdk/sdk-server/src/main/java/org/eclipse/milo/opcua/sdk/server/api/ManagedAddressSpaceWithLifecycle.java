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

import org.eclipse.milo.opcua.sdk.server.Lifecycle;
import org.eclipse.milo.opcua.sdk.server.LifecycleManager;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;

public abstract class ManagedAddressSpaceWithLifecycle extends ManagedAddressSpace implements Lifecycle {

    private final LifecycleManager lifecycleManager = new LifecycleManager();

    public ManagedAddressSpaceWithLifecycle(OpcUaServer server) {
        super(server);
    }

    public ManagedAddressSpaceWithLifecycle(OpcUaServer server, UaNodeManager nodeManager) {
        super(server, nodeManager);
    }

    @Override
    public final void startup() {
        lifecycleManager.startup();
    }

    @Override
    public final void shutdown() {
        lifecycleManager.shutdown();
    }

    /**
     * Get the {@link LifecycleManager} for this {@link ManagedAddressSpaceFragmentWithLifecycle}.
     *
     * @return the {@link LifecycleManager} for this {@link ManagedAddressSpaceFragmentWithLifecycle}.
     */
    protected LifecycleManager getLifecycleManager() {
        return lifecycleManager;
    }

}
