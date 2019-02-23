/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes;

import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ReferenceTypeNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;

public class UaReferenceTypeNode extends UaNode implements ReferenceTypeNode {

    private volatile Boolean isAbstract;
    private volatile Boolean symmetric;
    private volatile LocalizedText inverseName;

    public UaReferenceTypeNode(
        UaNodeContext context,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        Boolean isAbstract,
        Boolean symmetric,
        LocalizedText inverseName) {

        super(context, nodeId, NodeClass.ReferenceType,
            browseName, displayName, description, writeMask, userWriteMask);

        this.isAbstract = isAbstract;
        this.symmetric = symmetric;
        this.inverseName = inverseName;
    }


    @Override
    public Boolean getIsAbstract() {
        return isAbstract;
    }

    @Override
    public Boolean getSymmetric() {
        return symmetric;
    }

    @Override
    public LocalizedText getInverseName() {
        return inverseName;
    }

    @Override
    public synchronized void setIsAbstract(Boolean isAbstract) {
        this.isAbstract = isAbstract;

        fireAttributeChanged(AttributeId.IsAbstract, isAbstract);
    }

    @Override
    public synchronized void setSymmetric(Boolean symmetric) {
        this.symmetric = symmetric;

        fireAttributeChanged(AttributeId.Symmetric, symmetric);
    }

    @Override
    public synchronized void setInverseName(LocalizedText inverseName) {
        this.inverseName = inverseName;

        fireAttributeChanged(AttributeId.InverseName, inverseName);
    }

    @Nullable
    public String getNodeVersion() {
        return getProperty(NodeVersion).orElse(null);
    }

    public void setNodeVersion(String nodeVersion) {
        setProperty(NodeVersion, nodeVersion);
    }

    public static final QualifiedProperty<String> NodeVersion = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "NodeVersion",
        Identifiers.String,
        ValueRanks.Scalar,
        String.class
    );

}
