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

public abstract class ManagedAddressSpace extends ManagedAddressSpaceServices implements AddressSpace {

    public ManagedAddressSpace(OpcUaServer server) {
        super(server);
    }

    @Override
    protected void onStartup() {
        super.onStartup();

        registerAddressSpace(this);
    }

    @Override
    protected void onShutdown() {
        super.onShutdown();

        unregisterAddressSpace(this);
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

}
