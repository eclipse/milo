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

import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
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
        UaVariableNode node,
        AttributeId attributeId
    ) throws UaException {

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
        UaVariableNode node,
        AttributeId attributeId,
        DataValue value
    ) throws UaException {

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

    default DataValue getValue(AttributeContext context, UaVariableNode node) throws UaException {
        return (DataValue) node.getFilterChain().getAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.Value
        );
    }

    default NodeId getDataType(AttributeContext context, UaVariableNode node) throws UaException {
        return (NodeId) node.getFilterChain().getAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.DataType
        );
    }

    default Integer getValueRank(AttributeContext context, UaVariableNode node) throws UaException {
        return (Integer) node.getFilterChain().getAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.ValueRank
        );
    }

    default UInteger[] getArrayDimensions(AttributeContext context, UaVariableNode node) throws UaException {
        return (UInteger[]) node.getFilterChain().getAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.ArrayDimensions
        );
    }

    default UByte getAccessLevel(AttributeContext context, UaVariableNode node) throws UaException {
        return (UByte) node.getFilterChain().getAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.AccessLevel
        );
    }

    default UByte getUserAccessLevel(AttributeContext context, UaVariableNode node) throws UaException {
        return (UByte) node.getFilterChain().getAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.UserAccessLevel
        );
    }

    default Double getMinimumSamplingInterval(AttributeContext context, UaVariableNode node) throws UaException {
        return (Double) node.getFilterChain().getAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.MinimumSamplingInterval
        );
    }

    default Boolean getHistorizing(AttributeContext context, UaVariableNode node) throws UaException {
        return (Boolean) node.getFilterChain().getAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.Historizing
        );
    }

    default void setValue(AttributeContext context, UaVariableNode node, DataValue value) throws UaException {
        node.getFilterChain().setAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.Value,
            value
        );
    }

    default void setDataType(AttributeContext context, UaVariableNode node, NodeId dataType) throws UaException {
        node.getFilterChain().setAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.DataType,
            dataType
        );
    }

    default void setValueRank(AttributeContext context, UaVariableNode node, Integer valueRank) throws UaException {
        node.getFilterChain().setAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.ValueRank,
            valueRank
        );
    }

    default void setArrayDimensions(
        AttributeContext context,
        UaVariableNode node,
        UInteger[] arrayDimensions
    ) throws UaException {

        node.getFilterChain().setAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.ArrayDimensions,
            arrayDimensions
        );
    }

    default void setAccessLevel(AttributeContext context, UaVariableNode node, UByte accessLevel) throws UaException {
        node.getFilterChain().setAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.AccessLevel,
            accessLevel
        );
    }

    default void setUserAccessLevel(
        AttributeContext context,
        UaVariableNode node,
        UByte userAccessLevel
    ) throws UaException {

        node.getFilterChain().setAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.UserAccessLevel,
            userAccessLevel
        );
    }

    default void setMinimumSamplingInterval(
        AttributeContext context,
        UaVariableNode node,
        Double minimumSamplingInterval
    ) throws UaException {

        node.getFilterChain().setAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.MinimumSamplingInterval,
            minimumSamplingInterval
        );
    }

    default void setHistorizing(AttributeContext context, UaVariableNode node, Boolean historizing) throws UaException {
        node.getFilterChain().setAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.Historizing,
            historizing
        );
    }

}
