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

import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
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
        VariableTypeNode node,
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
        VariableTypeNode node,
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
        return node.getValue();
    }

    default NodeId getDataType(AttributeContext context, VariableTypeNode node) throws UaException {
        return node.getDataType();
    }

    default Integer getValueRank(AttributeContext context, VariableTypeNode node) throws UaException {
        return node.getValueRank();
    }

    default UInteger[] getArrayDimensions(
        AttributeContext context, VariableTypeNode node) throws UaException {

        return node.getArrayDimensions();
    }

    default Boolean getIsAbstract(AttributeContext context, VariableTypeNode node) throws UaException {
        return node.getIsAbstract();
    }

    default void setValue(AttributeContext context, VariableTypeNode node, DataValue value) throws UaException {
        node.setValue(value);
    }

    default void setDataType(AttributeContext context, VariableTypeNode node, NodeId dataType) throws UaException {
        node.setDataType(dataType);
    }

    default void setValueRank(AttributeContext context, VariableTypeNode node, Integer valueRank) throws UaException {
        node.setValueRank(valueRank);
    }

    default void setArrayDimensions(
        AttributeContext context, VariableTypeNode node, UInteger[] arrayDimensions) throws UaException {

        node.setArrayDimensions(arrayDimensions);
    }

    default void setIsAbstract(AttributeContext context, VariableTypeNode node, Boolean isAbstract) throws UaException {
        node.setIsAbstract(isAbstract);
    }

}
