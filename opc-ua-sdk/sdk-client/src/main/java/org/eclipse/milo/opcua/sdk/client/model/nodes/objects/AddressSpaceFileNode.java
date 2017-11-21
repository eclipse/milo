/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AddressSpaceFileType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class AddressSpaceFileNode extends FileNode implements AddressSpaceFileType {
    public AddressSpaceFileNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }
}
