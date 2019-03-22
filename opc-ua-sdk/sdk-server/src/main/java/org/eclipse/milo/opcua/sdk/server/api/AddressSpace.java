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

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

/**
 * An {@link AddressSpace} is an implementation of the services defined by {@link AddressSpaceServices} that can be
 * invoked for any operation targeting a {@link NodeId} that passes the {@link AddressSpaceFilter} obtained from
 * {@link #getFilter()}.
 */
public interface AddressSpace extends AddressSpaceServices {

    /**
     * Get the {@link AddressSpaceFilter} for this {@link AddressSpace}.
     *
     * @return the {@link AddressSpaceFilter} for this {@link AddressSpace}.
     */
    AddressSpaceFilter getFilter();


}
