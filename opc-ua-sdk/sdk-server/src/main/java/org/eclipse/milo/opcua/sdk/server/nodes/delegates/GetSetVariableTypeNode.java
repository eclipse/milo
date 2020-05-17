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

import org.eclipse.milo.opcua.sdk.core.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.util.AttributeUtil;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.dv;

public interface GetSetVariableTypeNode extends GetSetBase {

    default DataValue getVariableTypeAttribute(
        AttributeContext context,
        UaVariableTypeNode node,
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

            case IsAbstract:
                return dv(getIsAbstract(context, node));

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    default void setVariableTypeAttribute(
        AttributeContext context,
        UaVariableTypeNode node,
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
            case IsAbstract:
                setIsAbstract(context, node, AttributeUtil.extract(value));
                break;

            default:
                setBaseAttribute(context, node, attributeId, value);
        }
    }

    default DataValue getValue(AttributeContext context, VariableTypeNode node) throws UaException {
        return (DataValue) ((UaNode) node).getFilterChain().getAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.Value
        );
    }

    default NodeId getDataType(AttributeContext context, VariableTypeNode node) throws UaException {
        return (NodeId) ((UaNode) node).getFilterChain().getAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.DataType
        );
    }

    default Integer getValueRank(AttributeContext context, VariableTypeNode node) throws UaException {
        return (Integer) ((UaNode) node).getFilterChain().getAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.ValueRank
        );
    }

    default UInteger[] getArrayDimensions(AttributeContext context, VariableTypeNode node) throws UaException {
        return (UInteger[]) ((UaNode) node).getFilterChain().getAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.ArrayDimensions
        );
    }

    default Boolean getIsAbstract(AttributeContext context, VariableTypeNode node) throws UaException {
        return (Boolean) ((UaNode) node).getFilterChain().getAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.IsAbstract
        );
    }

    default void setValue(AttributeContext context, VariableTypeNode node, DataValue value) throws UaException {
        ((UaNode) node).getFilterChain().setAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.Value,
            value
        );
    }

    default void setDataType(AttributeContext context, VariableTypeNode node, NodeId dataType) throws UaException {
        ((UaNode) node).getFilterChain().setAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.DataType,
            dataType
        );
    }

    default void setValueRank(AttributeContext context, VariableTypeNode node, Integer valueRank) throws UaException {
        ((UaNode) node).getFilterChain().setAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.ValueRank,
            valueRank
        );
    }

    default void setArrayDimensions(
        AttributeContext context,
        VariableTypeNode node,
        UInteger[] arrayDimensions
    ) throws UaException {

        ((UaNode) node).getFilterChain().setAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.ArrayDimensions,
            arrayDimensions
        );
    }

    default void setIsAbstract(
        AttributeContext context,
        VariableTypeNode node,
        Boolean isAbstract
    ) throws UaException {

        ((UaNode) node).getFilterChain().setAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.IsAbstract,
            isAbstract
        );
    }

}
