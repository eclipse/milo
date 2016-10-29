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

package org.eclipse.milo.opcua.sdk.client.api.nodes;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaDataTypeNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaMethodNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaObjectNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaObjectTypeNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaReferenceTypeNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaVariableNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaViewNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface AddressSpace {

    CompletableFuture<UaNode> getNode(NodeId nodeId);

    UaDataTypeNode getDataTypeNode(NodeId nodeId);

    UaMethodNode getMethodNode(NodeId nodeId);

    UaObjectNode getObjectNode(NodeId nodeId);

    UaObjectTypeNode getObjectTypeNode(NodeId nodeId);

    UaReferenceTypeNode getReferenceTypeNode(NodeId nodeId);

    UaVariableNode getVariableNode(NodeId nodeId);

    UaVariableTypeNode getVariableTypeNode(NodeId nodeId);

    UaViewNode getViewNode(NodeId nodeId);

}
