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

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.TrustListType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class TrustListNode extends FileNode implements TrustListType {
    public TrustListNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                         LocalizedText displayName, LocalizedText description, UInteger writeMask,
                         UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public TrustListNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                         LocalizedText displayName, LocalizedText description, UInteger writeMask,
                         UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getLastUpdateTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TrustListType.LAST_UPDATE_TIME);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public DateTime getLastUpdateTime() {
        Optional<DateTime> propertyValue = getProperty(TrustListType.LAST_UPDATE_TIME);
        return propertyValue.orElse(null);
    }

    @Override
    public void setLastUpdateTime(DateTime value) {
        setProperty(TrustListType.LAST_UPDATE_TIME, value);
    }

    @Override
    public UaMethodNode getOpenWithMasksMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "OpenWithMasks", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getCloseAndUpdateMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "CloseAndUpdate", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getRemoveCertificateMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "RemoveCertificate", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getAddCertificateMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "AddCertificate", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }
}
