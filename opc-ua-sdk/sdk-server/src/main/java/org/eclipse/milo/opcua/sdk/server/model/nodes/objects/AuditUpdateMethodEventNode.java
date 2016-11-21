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

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditUpdateMethodEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:AuditUpdateMethodEventType")
public class AuditUpdateMethodEventNode extends AuditEventNode implements AuditUpdateMethodEventType {

    public AuditUpdateMethodEventNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        UByte eventNotifier) {

        super(nodeManager, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public NodeId getMethodId() {
        Optional<NodeId> property = getProperty(AuditUpdateMethodEventType.METHOD_ID);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMethodIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditUpdateMethodEventType.METHOD_ID.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMethodId(NodeId value) {
        setProperty(AuditUpdateMethodEventType.METHOD_ID, value);
    }

    @Override
    public Object[] getInputArguments() {
        Optional<Object[]> property = getProperty(AuditUpdateMethodEventType.INPUT_ARGUMENTS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getInputArgumentsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditUpdateMethodEventType.INPUT_ARGUMENTS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setInputArguments(Object[] value) {
        setProperty(AuditUpdateMethodEventType.INPUT_ARGUMENTS, value);
    }

}
