/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ProgressEventType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public class ProgressEventNode extends BaseEventNode implements ProgressEventType {
    public ProgressEventNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                             LocalizedText displayName, LocalizedText description, UInteger writeMask,
                             UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public ProgressEventNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                             LocalizedText displayName, LocalizedText description, UInteger writeMask,
                             UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getContextNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgressEventType.CONTEXT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Object getContext() {
        Optional<Object> propertyValue = getProperty(ProgressEventType.CONTEXT);
        return propertyValue.orElse(null);
    }

    @Override
    public void setContext(Object value) {
        setProperty(ProgressEventType.CONTEXT, value);
    }

    @Override
    public PropertyNode getProgressNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgressEventType.PROGRESS);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UShort getProgress() {
        Optional<UShort> propertyValue = getProperty(ProgressEventType.PROGRESS);
        return propertyValue.orElse(null);
    }

    @Override
    public void setProgress(UShort value) {
        setProperty(ProgressEventType.PROGRESS, value);
    }
}
