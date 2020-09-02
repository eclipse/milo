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

import org.eclipse.milo.opcua.sdk.core.nodes.ReferenceTypeNode;
import org.eclipse.milo.opcua.sdk.core.nodes.ReferenceTypeNodeProperties;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class UaReferenceTypeNode extends UaNode implements ReferenceTypeNode {

    private Boolean isAbstract;
    private Boolean symmetric;
    private LocalizedText inverseName;

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

    @Override
    public synchronized Object getAttribute(AttributeId attributeId) {
        switch (attributeId) {
            case IsAbstract:
                return isAbstract;

            case Symmetric:
                return symmetric;

            case InverseName:
                return inverseName;

            default:
                return super.getAttribute(attributeId);
        }
    }

    @Override
    public synchronized void setAttribute(AttributeId attributeId, Object value) {
        switch (attributeId) {
            case IsAbstract:
                isAbstract = (Boolean) value;
                break;

            case Symmetric:
                symmetric = (Boolean) value;
                break;

            case InverseName:
                inverseName = (LocalizedText) value;
                break;

            default:
                super.setAttribute(attributeId, value);
                return; // prevent firing an attribute change
        }

        fireAttributeChanged(attributeId, value);
    }

    /**
     * Get the value of the NodeVersion Property, if it exists.
     *
     * @return the value of the NodeVersion Property, if it exists.
     * @see ReferenceTypeNodeProperties#NodeVersion
     */
    @Nullable
    public String getNodeVersion() {
        return getProperty(ReferenceTypeNodeProperties.NodeVersion).orElse(null);
    }

    /**
     * Set the value of the NodeVersion Property.
     * <p>
     * A PropertyNode will be created if it does not already exist.
     *
     * @param nodeVersion the value to set.
     * @see ReferenceTypeNodeProperties#NodeVersion
     */
    public void setNodeVersion(String nodeVersion) {
        setProperty(ReferenceTypeNodeProperties.NodeVersion, nodeVersion);
    }

}
