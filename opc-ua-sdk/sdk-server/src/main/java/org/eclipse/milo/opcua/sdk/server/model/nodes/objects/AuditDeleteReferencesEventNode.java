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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditDeleteReferencesEventType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;

public class AuditDeleteReferencesEventNode extends AuditNodeManagementEventNode implements AuditDeleteReferencesEventType {
  public AuditDeleteReferencesEventNode(UaNodeContext context, NodeId nodeId,
                                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                        UInteger writeMask, UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public AuditDeleteReferencesEventNode(UaNodeContext context, NodeId nodeId,
                                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                        UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
  }

  public PropertyNode getReferencesToDeleteNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(AuditDeleteReferencesEventType.REFERENCES_TO_DELETE);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public DeleteReferencesItem[] getReferencesToDelete() {
    Optional<DeleteReferencesItem[]> propertyValue = getProperty(AuditDeleteReferencesEventType.REFERENCES_TO_DELETE);
    return propertyValue.orElse(null);
  }

  public void setReferencesToDelete(DeleteReferencesItem[] value) {
    setProperty(AuditDeleteReferencesEventType.REFERENCES_TO_DELETE, value);
  }
}
