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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditUpdateMethodEventType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class AuditUpdateMethodEventNode extends AuditEventNode implements AuditUpdateMethodEventType {
    public AuditUpdateMethodEventNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                      LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                      UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AuditUpdateMethodEventNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                      LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                      UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getMethodIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditUpdateMethodEventType.METHOD_ID);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public NodeId getMethodId() {
        Optional<NodeId> propertyValue = getProperty(AuditUpdateMethodEventType.METHOD_ID);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMethodId(NodeId value) {
        setProperty(AuditUpdateMethodEventType.METHOD_ID, value);
    }

    @Override
    public PropertyNode getInputArgumentsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditUpdateMethodEventType.INPUT_ARGUMENTS);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Object[] getInputArguments() {
        Optional<Object[]> propertyValue = getProperty(AuditUpdateMethodEventType.INPUT_ARGUMENTS);
        return propertyValue.orElse(null);
    }

    @Override
    public void setInputArguments(Object[] value) {
        setProperty(AuditUpdateMethodEventType.INPUT_ARGUMENTS, value);
    }
}
