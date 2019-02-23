/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes.delegates;

import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.server.api.nodes.DataTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ReferenceTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ViewNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public abstract class DelegatingAttributeDelegate implements AttributeDelegate {

    private final AttributeDelegate delegate;

    /**
     * Create a {@link DelegatingAttributeDelegate} whose delegate is {@link AttributeDelegate#DEFAULT}.
     */
    public DelegatingAttributeDelegate() {
        this(null);
    }

    /**
     * Create a {@link DelegatingAttributeDelegate} that delegates calls to {@code delegate}.
     *
     * @param delegate the {@link AttributeDelegate} to delegate calls to.
     */
    public DelegatingAttributeDelegate(@Nullable AttributeDelegate delegate) {
        this.delegate = delegate != null ? delegate : AttributeDelegate.DEFAULT;
    }

    // region Node delegates

    @Override
    public NodeId getNodeId(AttributeContext context, Node node) throws UaException {
        return delegate.getNodeId(context, node);
    }

    @Override
    public NodeClass getNodeClass(AttributeContext context, Node node) throws UaException {
        return delegate.getNodeClass(context, node);
    }

    @Override
    public QualifiedName getBrowseName(AttributeContext context, Node node) throws UaException {
        return delegate.getBrowseName(context, node);
    }

    @Override
    public LocalizedText getDisplayName(AttributeContext context, Node node) throws UaException {
        return delegate.getDisplayName(context, node);
    }

    @Override
    public LocalizedText getDescription(AttributeContext context, Node node) throws UaException {
        return delegate.getDescription(context, node);
    }

    @Override
    public UInteger getWriteMask(AttributeContext context, Node node) throws UaException {
        return delegate.getWriteMask(context, node);
    }

    @Override
    public UInteger getUserWriteMask(AttributeContext context, Node node) throws UaException {
        return delegate.getUserWriteMask(context, node);
    }

    @Override
    public void setNodeId(AttributeContext context, Node node, NodeId nodeId) throws UaException {
        delegate.setNodeId(context, node, nodeId);
    }

    @Override
    public void setNodeClass(AttributeContext context, Node node, NodeClass nodeClass) throws UaException {
        delegate.setNodeClass(context, node, nodeClass);
    }

    @Override
    public void setBrowseName(AttributeContext context, Node node, QualifiedName browseName) throws UaException {
        delegate.setBrowseName(context, node, browseName);
    }

    @Override
    public void setDisplayName(AttributeContext context, Node node, LocalizedText displayName) throws UaException {
        delegate.setDisplayName(context, node, displayName);
    }

    @Override
    public void setDescription(AttributeContext context, Node node, LocalizedText description) throws UaException {
        delegate.setDescription(context, node, description);
    }

    @Override
    public void setWriteMask(AttributeContext context, Node node, UInteger writeMask) throws UaException {
        delegate.setWriteMask(context, node, writeMask);
    }

    @Override
    public void setUserWriteMask(AttributeContext context, Node node, UInteger userWriteMask) throws UaException {
        delegate.setUserWriteMask(context, node, userWriteMask);
    }

    // endregion

    // region DataTypeNode delegates

    @Override
    public Boolean getIsAbstract(AttributeContext context, DataTypeNode node) throws UaException {
        return delegate.getIsAbstract(context, node);
    }

    @Override
    public void setIsAbstract(AttributeContext context, DataTypeNode node, Boolean isAbstract) throws UaException {
        delegate.setIsAbstract(context, node, isAbstract);
    }

    // endregion

    // region MethodNode delegates

    @Override
    public Boolean isExecutable(AttributeContext context, MethodNode node) throws UaException {
        return delegate.isExecutable(context, node);
    }

    @Override
    public Boolean isUserExecutable(AttributeContext context, MethodNode node) throws UaException {
        return delegate.isUserExecutable(context, node);
    }

    @Override
    public void setExecutable(AttributeContext context, MethodNode node, Boolean executable) throws UaException {
        delegate.setExecutable(context, node, executable);
    }

    @Override
    public void setUserExecutable(
        AttributeContext context, MethodNode node, Boolean userExecutable) throws UaException {

        delegate.setUserExecutable(context, node, userExecutable);
    }

    // endregion

    // region ObjectNode delegates

    @Override
    public UByte getEventNotifier(AttributeContext context, ObjectNode node) throws UaException {
        return delegate.getEventNotifier(context, node);
    }

    @Override
    public void setEventNotifier(AttributeContext context, ObjectNode node, UByte eventNotifier) throws UaException {
        delegate.setEventNotifier(context, node, eventNotifier);
    }

    // endregion

    // region ObjectTypeNode delegates

    @Override
    public Boolean getIsAbstract(AttributeContext context, ObjectTypeNode node) throws UaException {
        return delegate.getIsAbstract(context, node);
    }

    @Override
    public void setIsAbstract(AttributeContext context, ObjectTypeNode node, Boolean isAbstract) throws UaException {
        delegate.setIsAbstract(context, node, isAbstract);
    }

    // endregion

    // region ReferenceTypeNode delegates

    @Override
    public Boolean getIsAbstract(AttributeContext context, ReferenceTypeNode node) throws UaException {
        return delegate.getIsAbstract(context, node);
    }

    @Override
    public Boolean getSymmetric(AttributeContext context, ReferenceTypeNode node) throws UaException {
        return delegate.getSymmetric(context, node);
    }

    @Override
    public LocalizedText getInverseName(AttributeContext context, ReferenceTypeNode node) throws UaException {
        return delegate.getInverseName(context, node);
    }

    @Override
    public void setIsAbstract(AttributeContext context, ReferenceTypeNode node, Boolean isAbstract) throws UaException {
        delegate.setIsAbstract(context, node, isAbstract);
    }

    @Override
    public void setSymmetric(AttributeContext context, ReferenceTypeNode node, Boolean symmetric) throws UaException {
        delegate.setSymmetric(context, node, symmetric);
    }

    @Override
    public void setInverseName(
        AttributeContext context, ReferenceTypeNode node, LocalizedText inverseName) throws UaException {

        delegate.setInverseName(context, node, inverseName);
    }

    // endregion

    // region VariableNode delegates

    @Override
    public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
        return delegate.getValue(context, node);
    }

    @Override
    public NodeId getDataType(AttributeContext context, VariableNode node) throws UaException {
        return delegate.getDataType(context, node);
    }

    @Override
    public Integer getValueRank(AttributeContext context, VariableNode node) throws UaException {
        return delegate.getValueRank(context, node);
    }

    @Override
    public UInteger[] getArrayDimensions(AttributeContext context, VariableNode node) throws UaException {
        return delegate.getArrayDimensions(context, node);
    }

    @Override
    public UByte getAccessLevel(AttributeContext context, VariableNode node) throws UaException {
        return delegate.getAccessLevel(context, node);
    }

    @Override
    public UByte getUserAccessLevel(AttributeContext context, VariableNode node) throws UaException {
        return delegate.getUserAccessLevel(context, node);
    }

    @Override
    public Double getMinimumSamplingInterval(AttributeContext context, VariableNode node) throws UaException {
        return delegate.getMinimumSamplingInterval(context, node);
    }

    @Override
    public Boolean getHistorizing(AttributeContext context, VariableNode node) throws UaException {
        return delegate.getHistorizing(context, node);
    }

    @Override
    public void setValue(AttributeContext context, VariableNode node, DataValue value) throws UaException {
        delegate.setValue(context, node, value);
    }

    @Override
    public void setDataType(AttributeContext context, VariableNode node, NodeId dataType) throws UaException {
        delegate.setDataType(context, node, dataType);
    }

    @Override
    public void setValueRank(AttributeContext context, VariableNode node, Integer valueRank) throws UaException {
        delegate.setValueRank(context, node, valueRank);
    }

    @Override
    public void setArrayDimensions(
        AttributeContext context, VariableNode node, UInteger[] arrayDimensions) throws UaException {

        delegate.setArrayDimensions(context, node, arrayDimensions);
    }

    @Override
    public void setAccessLevel(AttributeContext context, VariableNode node, UByte accessLevel) throws UaException {
        delegate.setAccessLevel(context, node, accessLevel);
    }

    @Override
    public void setUserAccessLevel(
        AttributeContext context, VariableNode node, UByte userAccessLevel) throws UaException {

        delegate.setUserAccessLevel(context, node, userAccessLevel);
    }

    @Override
    public void setMinimumSamplingInterval(
        AttributeContext context, VariableNode node, Double minimumSamplingInterval) throws UaException {

        delegate.setMinimumSamplingInterval(context, node, minimumSamplingInterval);
    }

    @Override
    public void setHistorizing(AttributeContext context, VariableNode node, Boolean historizing) throws UaException {
        delegate.setHistorizing(context, node, historizing);
    }

    // endregion

    // region VariableTypeNode delegates

    @Override
    public DataValue getValue(AttributeContext context, VariableTypeNode node) throws UaException {
        return delegate.getValue(context, node);
    }

    @Override
    public NodeId getDataType(AttributeContext context, VariableTypeNode node) throws UaException {
        return delegate.getDataType(context, node);
    }

    @Override
    public Integer getValueRank(AttributeContext context, VariableTypeNode node) throws UaException {
        return delegate.getValueRank(context, node);
    }

    @Override
    public UInteger[] getArrayDimensions(AttributeContext context, VariableTypeNode node) throws UaException {
        return delegate.getArrayDimensions(context, node);
    }

    @Override
    public Boolean getIsAbstract(AttributeContext context, VariableTypeNode node) throws UaException {
        return delegate.getIsAbstract(context, node);
    }

    @Override
    public void setValue(AttributeContext context, VariableTypeNode node, DataValue value) throws UaException {
        delegate.setValue(context, node, value);
    }

    @Override
    public void setDataType(AttributeContext context, VariableTypeNode node, NodeId dataType) throws UaException {
        delegate.setDataType(context, node, dataType);
    }

    @Override
    public void setValueRank(AttributeContext context, VariableTypeNode node, Integer valueRank) throws UaException {
        delegate.setValueRank(context, node, valueRank);
    }

    @Override
    public void setArrayDimensions(
        AttributeContext context, VariableTypeNode node, UInteger[] arrayDimensions) throws UaException {

        delegate.setArrayDimensions(context, node, arrayDimensions);
    }

    @Override
    public void setIsAbstract(AttributeContext context, VariableTypeNode node, Boolean isAbstract) throws UaException {
        delegate.setIsAbstract(context, node, isAbstract);
    }

    // endregion

    // region ViewNode delegates

    @Override
    public Boolean getContainsNoLoops(AttributeContext context, ViewNode node) throws UaException {
        return delegate.getContainsNoLoops(context, node);
    }

    @Override
    public UByte getEventNotifier(AttributeContext context, ViewNode node) throws UaException {
        return delegate.getEventNotifier(context, node);
    }

    @Override
    public void setContainsNoLoops(
        AttributeContext context, ViewNode node, Boolean containsNoLoops) throws UaException {

        delegate.setContainsNoLoops(context, node, containsNoLoops);
    }

    @Override
    public void setEventNotifier(AttributeContext context, ViewNode node, UByte eventNotifier) throws UaException {
        delegate.setEventNotifier(context, node, eventNotifier);
    }

    // endregion

}
