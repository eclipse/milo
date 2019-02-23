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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.LimitAlarmType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class LimitAlarmNode extends AlarmConditionNode implements LimitAlarmType {
  public LimitAlarmNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                        LocalizedText displayName, LocalizedText description, UInteger writeMask,
                        UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public LimitAlarmNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                        LocalizedText displayName, LocalizedText description, UInteger writeMask,
                        UInteger userWriteMask, UByte eventNotifier) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
  }

  public PropertyNode getHighHighLimitNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(LimitAlarmType.HIGH_HIGH_LIMIT);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public Double getHighHighLimit() {
    Optional<Double> propertyValue = getProperty(LimitAlarmType.HIGH_HIGH_LIMIT);
    return propertyValue.orElse(null);
  }

  public void setHighHighLimit(Double value) {
    setProperty(LimitAlarmType.HIGH_HIGH_LIMIT, value);
  }

  public PropertyNode getHighLimitNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(LimitAlarmType.HIGH_LIMIT);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public Double getHighLimit() {
    Optional<Double> propertyValue = getProperty(LimitAlarmType.HIGH_LIMIT);
    return propertyValue.orElse(null);
  }

  public void setHighLimit(Double value) {
    setProperty(LimitAlarmType.HIGH_LIMIT, value);
  }

  public PropertyNode getLowLimitNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(LimitAlarmType.LOW_LIMIT);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public Double getLowLimit() {
    Optional<Double> propertyValue = getProperty(LimitAlarmType.LOW_LIMIT);
    return propertyValue.orElse(null);
  }

  public void setLowLimit(Double value) {
    setProperty(LimitAlarmType.LOW_LIMIT, value);
  }

  public PropertyNode getLowLowLimitNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(LimitAlarmType.LOW_LOW_LIMIT);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public Double getLowLowLimit() {
    Optional<Double> propertyValue = getProperty(LimitAlarmType.LOW_LOW_LIMIT);
    return propertyValue.orElse(null);
  }

  public void setLowLowLimit(Double value) {
    setProperty(LimitAlarmType.LOW_LOW_LIMIT, value);
  }
}
