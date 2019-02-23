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

import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.CertificateGroupType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class CertificateGroupNode extends BaseObjectNode implements CertificateGroupType {
  public CertificateGroupNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                              LocalizedText displayName, LocalizedText description, UInteger writeMask,
                              UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public CertificateGroupNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                              LocalizedText displayName, LocalizedText description, UInteger writeMask,
                              UInteger userWriteMask, UByte eventNotifier) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
  }

  public PropertyNode getCertificateTypesNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(CertificateGroupType.CERTIFICATE_TYPES);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public NodeId[] getCertificateTypes() {
    Optional<NodeId[]> propertyValue = getProperty(CertificateGroupType.CERTIFICATE_TYPES);
    return propertyValue.orElse(null);
  }

  public void setCertificateTypes(NodeId[] value) {
    setProperty(CertificateGroupType.CERTIFICATE_TYPES, value);
  }

  public TrustListNode getTrustListNode() {
    Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "TrustList");
    return (TrustListNode) component.orElse(null);
  }
}
