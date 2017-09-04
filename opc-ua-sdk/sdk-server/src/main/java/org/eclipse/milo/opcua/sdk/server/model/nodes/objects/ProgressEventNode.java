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

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ProgressEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public class ProgressEventNode extends BaseEventNode implements ProgressEventType {
    public ProgressEventNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                             LocalizedText displayName, LocalizedText description, UInteger writeMask,
                             UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public ProgressEventNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                             LocalizedText displayName, LocalizedText description, UInteger writeMask,
                             UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public PropertyNode getContextNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgressEventType.CONTEXT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Object getContext() {
        Optional<Object> propertyValue = getProperty(ProgressEventType.CONTEXT);
        return propertyValue.orElse(null);
    }

    public void setContext(Object value) {
        setProperty(ProgressEventType.CONTEXT, value);
    }

    public PropertyNode getProgressNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgressEventType.PROGRESS);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UShort getProgress() {
        Optional<UShort> propertyValue = getProperty(ProgressEventType.PROGRESS);
        return propertyValue.orElse(null);
    }

    public void setProgress(UShort value) {
        setProperty(ProgressEventType.PROGRESS, value);
    }
}
