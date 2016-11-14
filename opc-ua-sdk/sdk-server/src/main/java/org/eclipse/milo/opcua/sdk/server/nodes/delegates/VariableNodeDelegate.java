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

import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeDelegate;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import static org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeUtil.ATTRIBUTE_ID_INVALID_EXCEPTION;
import static org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeUtil.dv;
import static org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeUtil.extract;


public class VariableNodeDelegate implements AttributeDelegate {

    private final AttributeDelegate parent;

    public VariableNodeDelegate() {
        this(new AttributeDelegateAdapter());
    }

    public VariableNodeDelegate(AttributeDelegate parent) {
        this.parent = parent;
    }

    @Override
    public DataValue getAttribute(
        AttributeContext context,
        UaNode node,
        AttributeId attributeId) throws UaException {

        if (node instanceof VariableNode) {
            VariableNode vNode = (VariableNode) node;

            switch (attributeId) {
                case Value:
                    DataValue value = getValue(context, vNode);

                    return new DataValue(
                        value.getValue(),
                        value.getStatusCode(),
                        value.getSourceTime(),
                        DateTime.now()
                    );

                case DataType:
                    return dv(getDataType(context, vNode));

                case ValueRank:
                    return dv(getValueRank(context, vNode));

                case ArrayDimensions:
                    return getArrayDimensions(context, vNode)
                        .map(AttributeUtil::dv)
                        .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

                case AccessLevel:
                    return dv(getAccessLevel(context, vNode));

                case UserAccessLevel:
                    return dv(getUserAccessLevel(context, vNode));

                case MinimumSamplingInterval:
                    return getMinimumSamplingInterval(context, vNode)
                        .map(AttributeUtil::dv)
                        .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

                case Historizing:
                    return dv(getHistorizing(context, vNode));

                default:
                    return parent.getAttribute(context, node, attributeId);
            }
        } else {
            return parent.getAttribute(context, node, attributeId);
        }
    }

    @Override
    public void setAttribute(
        AttributeContext context,
        UaNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        if (node instanceof VariableNode) {
            VariableNode vNode = (VariableNode) node;

            switch (attributeId) {
                case Value:
                    setValue(context, vNode, value);
                    break;

                case DataType:
                    setDataType(context, vNode, extract(value));
                    break;

                case ValueRank:
                    setValueRank(context, vNode, extract(value));
                    break;

                case ArrayDimensions:
                    setArrayDimensions(context, vNode, extract(value));
                    break;

                case AccessLevel:
                    setAccessLevel(context, vNode, extract(value));
                    break;

                case UserAccessLevel:
                    setUserAccessLevel(context, vNode, extract(value));
                    break;

                case MinimumSamplingInterval:
                    setMinimumSamplingInterval(context, vNode, extract(value));
                    break;

                case Historizing:
                    setHistorizing(context, vNode, extract(value));
                    break;

                default:
                    parent.setAttribute(context, node, attributeId, value);
            }
        } else {
            parent.setAttribute(context, node, attributeId, value);
        }
    }

    protected DataValue getValue(
        AttributeContext context, VariableNode node) throws UaException {

        return node.getValue();
    }

    protected NodeId getDataType(
        AttributeContext context, VariableNode node) throws UaException {

        return node.getDataType();
    }

    protected Integer getValueRank(
        AttributeContext context, VariableNode node) throws UaException {

        return node.getValueRank();
    }

    protected Optional<UInteger[]> getArrayDimensions(
        AttributeContext context, VariableNode node) throws UaException {

        return node.getArrayDimensions();
    }

    protected UByte getAccessLevel(
        AttributeContext context, VariableNode node) throws UaException {

        return node.getAccessLevel();
    }

    protected UByte getUserAccessLevel(
        AttributeContext context, VariableNode node) throws UaException {

        return node.getUserAccessLevel();
    }

    protected Optional<Double> getMinimumSamplingInterval(
        AttributeContext context, VariableNode node) throws UaException {

        return node.getMinimumSamplingInterval();
    }

    protected Boolean getHistorizing(
        AttributeContext context, VariableNode node) throws UaException {

        return node.getHistorizing();
    }

    protected void setValue(
        AttributeContext context, VariableNode node, DataValue value) throws UaException {

        node.setValue(value);
    }

    protected void setDataType(
        AttributeContext context, VariableNode node, NodeId dataType) throws UaException {

        node.setDataType(dataType);
    }

    protected void setValueRank(
        AttributeContext context, VariableNode node, Integer valueRank) throws UaException {

        node.setValueRank(valueRank);
    }

    protected void setArrayDimensions(
        AttributeContext context, VariableNode node, UInteger[] arrayDimensions) throws UaException {

        node.setArrayDimensions(Optional.ofNullable(arrayDimensions));
    }

    protected void setAccessLevel(
        AttributeContext context, VariableNode node, UByte accessLevel) throws UaException {

        node.setAccessLevel(accessLevel);
    }

    protected void setUserAccessLevel(
        AttributeContext context, VariableNode node, UByte userAccessLevel) throws UaException {

        node.setUserAccessLevel(userAccessLevel);
    }

    protected void setMinimumSamplingInterval(
        AttributeContext context, VariableNode node, Double minimumSamplingInterval) throws UaException {

        node.setMinimumSamplingInterval(Optional.ofNullable(minimumSamplingInterval));
    }

    protected void setHistorizing(
        AttributeContext context, VariableNode node, Boolean historizing) throws UaException {

        node.setHistorizing(historizing);
    }

}
