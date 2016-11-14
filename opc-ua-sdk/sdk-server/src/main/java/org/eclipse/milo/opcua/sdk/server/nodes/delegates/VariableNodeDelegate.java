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
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class VariableNodeDelegate extends AttributeDelegateAdapter {

    @Override
    protected DataValue getVariableAttribute(
        AttributeContext context,
        VariableNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case Value:
                DataValue value = getValue(context, node);

                return new DataValue(
                    value.getValue(),
                    value.getStatusCode(),
                    value.getSourceTime(),
                    DateTime.now()
                );

            case DataType:
                return dv(getDataType(context, node));

            case ValueRank:
                return dv(getValueRank(context, node));

            case ArrayDimensions:
                return getArrayDimensions(context, node)
                    .map(AttributeDelegateAdapter::dv)
                    .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

            case AccessLevel:
                return dv(getAccessLevel(context, node));

            case UserAccessLevel:
                return dv(getUserAccessLevel(context, node));

            case MinimumSamplingInterval:
                return getMinimumSamplingInterval(context, node)
                    .map(AttributeDelegateAdapter::dv)
                    .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

            case Historizing:
                return dv(getHistorizing(context, node));


            default:
                return super.getVariableAttribute(context, node, attributeId);
        }
    }

    protected DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
        return node.getValue();
    }

    protected NodeId getDataType(AttributeContext context, VariableNode node) throws UaException {
        return node.getDataType();
    }

    protected Integer getValueRank(AttributeContext context, VariableNode node) throws UaException {
        return node.getValueRank();
    }

    protected Optional<UInteger[]> getArrayDimensions(AttributeContext context, VariableNode node) throws UaException {
        return node.getArrayDimensions();
    }

    protected UByte getAccessLevel(AttributeContext context, VariableNode node) throws UaException {
        return node.getAccessLevel();
    }

    protected UByte getUserAccessLevel(AttributeContext context, VariableNode node) throws UaException {
        return node.getUserAccessLevel();
    }

    protected Optional<Double> getMinimumSamplingInterval(AttributeContext context, VariableNode node) throws UaException {
        return node.getMinimumSamplingInterval();
    }

    protected Boolean getHistorizing(AttributeContext context, VariableNode node) throws UaException {
        return node.getHistorizing();
    }

    @Override
    protected void setVariableAttribute(
        AttributeContext context,
        VariableNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case Value:
                setValue(context, node, value);
                break;

            case DataType:
                setDataType(context, node, extract(value));
                break;

            case ValueRank:
                setValueRank(context, node, extract(value));
                break;

            case ArrayDimensions:
                setArrayDimensions(context, node, extract(value));
                break;

            case AccessLevel:
                setAccessLevel(context, node, extract(value));
                break;

            case UserAccessLevel:
                setUserAccessLevel(context, node, extract(value));
                break;

            case MinimumSamplingInterval:
                setMinimumSamplingInterval(context, node, extract(value));
                break;

            case Historizing:
                setHistorizing(context, node, extract(value));
                break;

            default:
                super.setVariableAttribute(context, node, attributeId, value);
        }
    }

    protected void setValue(AttributeContext context, VariableNode node, DataValue value) throws UaException {
        node.setValue(value);
    }

    protected void setDataType(AttributeContext context, VariableNode node, NodeId dataType) throws UaException {
        node.setDataType(dataType);
    }

    protected void setValueRank(AttributeContext context, VariableNode node, Integer valueRank) throws UaException {
        node.setValueRank(valueRank);
    }

    protected void setArrayDimensions(AttributeContext context, VariableNode node, UInteger[] arrayDimensions) throws UaException {
        node.setArrayDimensions(Optional.ofNullable(arrayDimensions));
    }

    protected void setAccessLevel(AttributeContext context, VariableNode node, UByte accessLevel) throws UaException {
        node.setAccessLevel(accessLevel);
    }

    protected void setUserAccessLevel(AttributeContext context, VariableNode node, UByte userAccessLevel) throws UaException {
        node.setUserAccessLevel(userAccessLevel);
    }

    protected void setMinimumSamplingInterval(AttributeContext context, VariableNode node, Double minimumSamplingInterval) throws UaException {
        node.setMinimumSamplingInterval(Optional.ofNullable(minimumSamplingInterval));
    }

    protected void setHistorizing(AttributeContext context, VariableNode node, Boolean historizing) throws UaException {
        node.setHistorizing(historizing);
    }

}
