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

        getServer().getAddressSpaceManager().register(this);
    }

    @Override
    protected void onShutdown() {
        super.onShutdown();

        getServer().getAddressSpaceManager().unregister(this);
    }

}
