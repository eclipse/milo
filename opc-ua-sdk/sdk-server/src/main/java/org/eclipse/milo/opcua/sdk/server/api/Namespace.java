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

import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public interface Namespace extends NamespaceServices {

    /**
     * @return the index of this {@link Namespace} in the server's namespace array.
     */
    UShort getNamespaceIndex();

    /**
     * @return the URI identifying this {@link Namespace}.
     */
    String getNamespaceUri();

    /**
     * Get the {@link NodeManager} for {@link UaNode}s in this Namespace.
     *
     * @return the {@link NodeManager} for {@link UaNode}s in this Namespace.
     */
    NodeManager<UaNode> getNodeManager();

}
