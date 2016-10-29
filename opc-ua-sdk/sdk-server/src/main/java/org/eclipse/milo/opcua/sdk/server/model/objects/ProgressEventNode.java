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

package org.eclipse.milo.opcua.sdk.server.model.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.model.objects.ProgressEventType;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

@UaObjectNode(typeName = "0:ProgressEventType")
public class ProgressEventNode extends BaseEventNode implements ProgressEventType {

    public ProgressEventNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        Optional<LocalizedText> description,
        Optional<UInteger> writeMask,
        Optional<UInteger> userWriteMask,
        UByte eventNotifier) {

        super(nodeManager, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public Object getContext() {
        Optional<Object> property = getProperty(ProgressEventType.CONTEXT);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getContextNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgressEventType.CONTEXT.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setContext(Object value) {
        setProperty(ProgressEventType.CONTEXT, value);
    }

    @Override
    public UShort getProgress() {
        Optional<UShort> property = getProperty(ProgressEventType.PROGRESS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getProgressNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgressEventType.PROGRESS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setProgress(UShort value) {
        setProperty(ProgressEventType.PROGRESS, value);
    }

}
