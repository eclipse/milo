/*
 * Copyright (c) 2016 Kevin Herron
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
