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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ServerRedundancyType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.RedundancySupport;

public class ServerRedundancyNode extends BaseObjectNode implements ServerRedundancyType {
  public ServerRedundancyNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                              LocalizedText displayName, LocalizedText description, UInteger writeMask,
                              UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public ServerRedundancyNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                              LocalizedText displayName, LocalizedText description, UInteger writeMask,
                              UInteger userWriteMask, UByte eventNotifier) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
  }

  public PropertyNode getRedundancySupportNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerRedundancyType.REDUNDANCY_SUPPORT);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public RedundancySupport getRedundancySupport() {
    Optional<RedundancySupport> propertyValue = getProperty(ServerRedundancyType.REDUNDANCY_SUPPORT);
    return propertyValue.orElse(null);
  }

  public void setRedundancySupport(RedundancySupport value) {
    setProperty(ServerRedundancyType.REDUNDANCY_SUPPORT, value);
  }
}
