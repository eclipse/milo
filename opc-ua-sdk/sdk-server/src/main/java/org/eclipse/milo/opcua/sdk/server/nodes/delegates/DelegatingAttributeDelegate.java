/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server.nodes.delegates;

import java.util.Optional;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public abstract class DelegatingAttributeDelegate implements AttributeDelegate {

    private final AttributeDelegate delegate;

    public DelegatingAttributeDelegate() {
        this(null);
    }

    public DelegatingAttributeDelegate(@Nullable AttributeDelegate parent) {
        this.delegate = parent != null ? parent : AttributeDelegate.DEFAULT;
    }

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
    public Optional<LocalizedText> getDescription(AttributeContext context, Node node) throws UaException {
        return delegate.getDescription(context, node);
    }

    @Override
    public Optional<UInteger> getWriteMask(AttributeContext context, Node node) throws UaException {
        return delegate.getWriteMask(context, node);
    }

    @Override
    public Optional<UInteger> getUserWriteMask(AttributeContext context, Node node) throws UaException {
        return delegate.getUserWriteMask(context, node);
    }

    @Override
    public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
        return delegate.getValue(context, node);
    }

    @Override
    public Optional<UInteger[]> getArrayDimensions(AttributeContext context, VariableNode node) throws UaException {
        return delegate.getArrayDimensions(context, node);
    }

    @Override
    public Optional<UInteger[]> getArrayDimensions(AttributeContext context, VariableTypeNode node) throws UaException {
        return delegate.getArrayDimensions(context, node);
    }

}
