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

import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.util.AttributeUtil;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.dv;


public interface GetSetVariableNode extends GetSetBase {

    default DataValue getVariableAttribute(
        AttributeContext context,
        VariableNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case Value:
                return getValue(context, node);

            case DataType:
                return dv(getDataType(context, node));

            case ValueRank:
                return dv(getValueRank(context, node));

            case ArrayDimensions:
                return dv(getArrayDimensions(context, node));

            case AccessLevel:
                return dv(getAccessLevel(context, node));

            case UserAccessLevel:
                return dv(getUserAccessLevel(context, node));

            case MinimumSamplingInterval:
                return dv(getMinimumSamplingInterval(context, node));

            case Historizing:
                return dv(getHistorizing(context, node));

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    default void setVariableAttribute(
        AttributeContext context,
        VariableNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case Value:
                setValue(context, node, value);
                break;
            case DataType:
                setDataType(context, node, AttributeUtil.extract(value));
                break;
            case ValueRank:
                setValueRank(context, node, AttributeUtil.extract(value));
                break;
            case ArrayDimensions:
                setArrayDimensions(context, node, AttributeUtil.extract(value));
                break;
            case AccessLevel:
                setAccessLevel(context, node, AttributeUtil.extract(value));
                break;
            case UserAccessLevel:
                setUserAccessLevel(context, node, AttributeUtil.extract(value));
                break;
            case MinimumSamplingInterval:
                setMinimumSamplingInterval(context, node, AttributeUtil.extract(value));
                break;
            case Historizing:
                setHistorizing(context, node, AttributeUtil.extract(value));
                break;

            default:
                setBaseAttribute(context, node, attributeId, value);
        }
    }

    default DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
        return node.getValue();
    }

    default NodeId getDataType(AttributeContext context, VariableNode node) throws UaException {
        return node.getDataType();
    }

    default Integer getValueRank(AttributeContext context, VariableNode node) throws UaException {
        return node.getValueRank();
    }

    default UInteger[] getArrayDimensions(AttributeContext context, VariableNode node) throws UaException {
        return node.getArrayDimensions();
    }

    default UByte getAccessLevel(AttributeContext context, VariableNode node) throws UaException {
        return node.getAccessLevel();
    }

    default UByte getUserAccessLevel(AttributeContext context, VariableNode node) throws UaException {
        return node.getUserAccessLevel();
    }

    default Double getMinimumSamplingInterval(
        AttributeContext context, VariableNode node) throws UaException {

        return node.getMinimumSamplingInterval();
    }

    default Boolean getHistorizing(
        AttributeContext context, VariableNode node) throws UaException {

        return node.getHistorizing();
    }

    default void setValue(AttributeContext context, VariableNode node, DataValue value) throws UaException {
        node.setValue(value);
    }

    default void setDataType(AttributeContext context, VariableNode node, NodeId dataType) throws UaException {
        node.setDataType(dataType);
    }

    default void setValueRank(AttributeContext context, VariableNode node, Integer valueRank) throws UaException {
        node.setValueRank(valueRank);
    }

    default void setArrayDimensions(
        AttributeContext context, VariableNode node, UInteger[] arrayDimensions) throws UaException {

        node.setArrayDimensions(arrayDimensions);
    }

    default void setAccessLevel(AttributeContext context, VariableNode node, UByte accessLevel) throws UaException {
        node.setAccessLevel(accessLevel);
    }

    default void setUserAccessLevel(
        AttributeContext context, VariableNode node, UByte userAccessLevel) throws UaException {

        node.setUserAccessLevel(userAccessLevel);
    }

    default void setMinimumSamplingInterval(
        AttributeContext context, VariableNode node, Double minimumSamplingInterval) throws UaException {

        node.setMinimumSamplingInterval(minimumSamplingInterval);
    }

    default void setHistorizing(
        AttributeContext context, VariableNode node, Boolean historizing) throws UaException {

        node.setHistorizing(historizing);
    }

}
