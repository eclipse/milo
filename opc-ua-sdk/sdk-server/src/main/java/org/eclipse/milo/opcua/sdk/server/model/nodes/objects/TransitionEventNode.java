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
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.StateVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.TransitionVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.TransitionEventType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class TransitionEventNode extends BaseEventNode implements TransitionEventType {
  public TransitionEventNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                             LocalizedText displayName, LocalizedText description, UInteger writeMask,
                             UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public TransitionEventNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                             LocalizedText displayName, LocalizedText description, UInteger writeMask,
                             UInteger userWriteMask, UByte eventNotifier) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
  }

  public TransitionVariableNode getTransitionNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "Transition");
    return (TransitionVariableNode) component.orElse(null);
  }

  public LocalizedText getTransition() {
    Optional<VariableNode> component = getVariableComponent("Transition");
    return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setTransition(LocalizedText value) {
    getVariableComponent("Transition").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public StateVariableNode getFromStateNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "FromState");
    return (StateVariableNode) component.orElse(null);
  }

  public LocalizedText getFromState() {
    Optional<VariableNode> component = getVariableComponent("FromState");
    return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setFromState(LocalizedText value) {
    getVariableComponent("FromState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public StateVariableNode getToStateNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ToState");
    return (StateVariableNode) component.orElse(null);
  }

  public LocalizedText getToState() {
    Optional<VariableNode> component = getVariableComponent("ToState");
    return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setToState(LocalizedText value) {
    getVariableComponent("ToState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }
}
