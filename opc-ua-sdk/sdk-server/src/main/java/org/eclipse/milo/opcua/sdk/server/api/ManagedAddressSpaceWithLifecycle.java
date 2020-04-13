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

/**
 * A {@link ManagedAddressSpace} that has a {@link Lifecycle} associated with it.
 * <p>
 * This AddressSpace and its {@link UaNodeManager} will be registered with the server on startup and unregistered on
 * shutdown.
 * <p>
 * Subclasses can register additional startup/shutdown tasks with the {@link LifecycleManager} obtained from
 * {@link #getLifecycleManager()}.
 */
public abstract class ManagedAddressSpaceWithLifecycle extends ManagedAddressSpace implements Lifecycle {

    private final LifecycleManager lifecycleManager = new LifecycleManager();

    public ManagedAddressSpaceWithLifecycle(OpcUaServer server) {
        super(server);

        getLifecycleManager().addLifecycle(new Lifecycle() {
            @Override
            public void startup() {
                registerAddressSpace(ManagedAddressSpaceWithLifecycle.this);
                registerNodeManager(getNodeManager());
            }

            @Override
            public void shutdown() {
                unregisterAddressSpace(ManagedAddressSpaceWithLifecycle.this);
                unregisterNodeManager(getNodeManager());
            }
        });
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
     * Get the {@link LifecycleManager} for this {@link ManagedAddressSpaceWithLifecycle}.
     *
     * @return the {@link LifecycleManager} for this {@link ManagedAddressSpaceWithLifecycle}.
     */
    protected LifecycleManager getLifecycleManager() {
        return lifecycleManager;
    }

    /**
     * Register this {@link ManagedAddressSpace} with its managing entity.
     * <p>
     * The default implementation registers it with the Server's {@link AddressSpaceManager}.
     * <p>
     * ManagedAddressSpaces that belong to a {@link AddressSpaceComposite} other than Server's AddressSpaceManager
     * should override this to register with that composite.
     *
     * @param addressSpace the {@link AddressSpace} to register.
     */
    protected void registerAddressSpace(AddressSpace addressSpace) {
        getServer().getAddressSpaceManager().register(addressSpace);
    }

    /**
     * Unregister this {@link ManagedAddressSpace} with its managing entity.
     * <p>
     * The default implementation unregisters it with the Server's {@link AddressSpaceManager}.
     * <p>
     * ManagedAddressSpaces that belong to a {@link AddressSpaceComposite} other than Server's AddressSpaceManager
     * should override this to unregister with that composite.
     *
     * @param addressSpace the {@link AddressSpace} to unregister.
     */
    protected void unregisterAddressSpace(AddressSpace addressSpace) {
        getServer().getAddressSpaceManager().unregister(addressSpace);
    }

    /**
     * Register this AddressSpace's {@link UaNodeManager} with its managing entity.
     * <p>
     * The default implementation registers it with the server's {@link AddressSpaceManager}.
     *
     * @param nodeManager the {@link UaNodeManager} to register.
     */
    protected void registerNodeManager(UaNodeManager nodeManager) {
        getServer().getAddressSpaceManager().register(nodeManager);
    }

    /**
     * Unregister this AddressSpace's {@link UaNodeManager} with its managing entity.
     * <p>
     * The default implementation unregisters it with the server's {@link AddressSpaceManager}.
     *
     * @param nodeManager the {@link UaNodeManager} to unregister.
     */
    protected void unregisterNodeManager(UaNodeManager nodeManager) {
        getServer().getAddressSpaceManager().unregister(nodeManager);
    }

}
