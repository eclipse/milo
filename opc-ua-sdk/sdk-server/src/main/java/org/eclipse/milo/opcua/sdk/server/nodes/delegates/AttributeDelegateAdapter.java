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
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeDelegate;
import org.eclipse.milo.opcua.sdk.server.nodes.ServerNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;

public class AttributeDelegateAdapter implements AttributeDelegate {

    @Override
    public DataValue getAttribute(
        AttributeContext context,
        UaNode node,
        AttributeId attributeId) throws UaException {

        switch (node.getNodeClass()) {
            case DataType:
                return getDataTypeAttribute(context, (ServerNode & DataTypeNode) node, attributeId);

            case Method:
                return getMethodAttribute(context, (ServerNode & MethodNode) node, attributeId);

            case Object:
                return getObjectAttribute(context, (ServerNode & ObjectNode) node, attributeId);

            case ObjectType:
                return getObjectTypeAttribute(context, (ServerNode & ObjectTypeNode) node, attributeId);

            case ReferenceType:
                return getReferenceTypeAttribute(context, (ServerNode & ReferenceTypeNode) node, attributeId);

            case Variable:
                return getVariableAttribute(context, (ServerNode & VariableNode) node, attributeId);

            case VariableType:
                return getVariableTypeAttribute(context, (ServerNode & VariableTypeNode) node, attributeId);

            case View:
                return getViewAttribute(context, (ServerNode & ViewNode) node, attributeId);

            default:
                throw new UaException(StatusCodes.Bad_NodeClassInvalid);
        }
    }

    @Override
    public void setAttribute(
        AttributeContext context,
        UaNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (node.getNodeClass()) {
            case DataType:
                setDataTypeAttribute(context, (DataTypeNode) node, attributeId, value);
                break;
            case Method:
                setMethodAttribute(context, (MethodNode) node, attributeId, value);
                break;
            case Object:
                setObjectAttribute(context, (ObjectNode) node, attributeId, value);
                break;
            case ObjectType:
                setObjectTypeAttribute((ObjectTypeNode) node, attributeId, value);
                break;
            case ReferenceType:
                setReferenceTypeAttribute((ReferenceTypeNode) node, attributeId, value);
                break;
            case Variable:
                setVariableAttribute(context, (VariableNode) node, attributeId, value);
                break;
            case VariableType:
                setVariableTypeAttribute((VariableTypeNode) node, attributeId, value);
                break;
            case View:
                setViewAttribute((ViewNode) node, attributeId, value);
                break;

            default:
                throw new UaException(StatusCodes.Bad_NodeClassInvalid);
        }
    }

    protected DataValue getBaseAttribute(
        AttributeContext context,
        Node node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case NodeId:
                return AttributeUtil.dv(node.getNodeId());

            case NodeClass:
                return AttributeUtil.dv(node.getNodeClass());

            case BrowseName:
                return AttributeUtil.dv(node.getBrowseName());

            case DisplayName:
                return AttributeUtil.dv(node.getDisplayName());

            case Description:
                return node.getDescription()
                    .map(AttributeUtil::dv)
                    .orElseThrow(AttributeUtil.ATTRIBUTE_ID_INVALID_EXCEPTION);

            case WriteMask:
                return node.getWriteMask()
                    .map(AttributeUtil::dv)
                    .orElseThrow(AttributeUtil.ATTRIBUTE_ID_INVALID_EXCEPTION);

            case UserWriteMask:
                return node.getUserWriteMask()
                    .map(AttributeUtil::dv)
                    .orElseThrow(AttributeUtil.ATTRIBUTE_ID_INVALID_EXCEPTION);

            default:
                throw new UaException(StatusCodes.Bad_AttributeIdInvalid);
        }
    }

    protected DataValue getDataTypeAttribute(
        AttributeContext context,
        DataTypeNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case IsAbstract:
                return AttributeUtil.dv(node.getIsAbstract());

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    protected DataValue getMethodAttribute(
        AttributeContext context,
        MethodNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case Executable:
                return AttributeUtil.dv(node.isExecutable());

            case UserExecutable:
                return AttributeUtil.dv(node.isUserExecutable());

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    protected DataValue getObjectAttribute(
        AttributeContext context,
        ObjectNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case EventNotifier:
                return AttributeUtil.dv(node.getEventNotifier());

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    protected DataValue getObjectTypeAttribute(
        AttributeContext context,
        ObjectTypeNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case IsAbstract:
                return AttributeUtil.dv(node.getIsAbstract());

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    protected DataValue getReferenceTypeAttribute(
        AttributeContext context,
        ReferenceTypeNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case IsAbstract:
                return AttributeUtil.dv(node.getIsAbstract());

            case Symmetric:
                return AttributeUtil.dv(node.getSymmetric());

            case InverseName:
                return node.getInverseName()
                    .map(AttributeUtil::dv)
                    .orElseThrow(AttributeUtil.ATTRIBUTE_ID_INVALID_EXCEPTION);

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    protected DataValue getVariableAttribute(
        AttributeContext context,
        VariableNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case Value:
                DataValue value = node.getValue();

                return new DataValue(
                    value.getValue(),
                    value.getStatusCode(),
                    value.getSourceTime(),
                    DateTime.now()
                );

            case DataType:
                return AttributeUtil.dv(node.getDataType());

            case ValueRank:
                return AttributeUtil.dv(node.getValueRank());

            case ArrayDimensions:
                return node.getArrayDimensions()
                    .map(AttributeUtil::dv)
                    .orElseThrow(AttributeUtil.ATTRIBUTE_ID_INVALID_EXCEPTION);

            case AccessLevel:
                return AttributeUtil.dv(node.getAccessLevel());

            case UserAccessLevel:
                return AttributeUtil.dv(node.getUserAccessLevel());

            case MinimumSamplingInterval:
                return node.getMinimumSamplingInterval()
                    .map(AttributeUtil::dv)
                    .orElseThrow(AttributeUtil.ATTRIBUTE_ID_INVALID_EXCEPTION);

            case Historizing:
                return AttributeUtil.dv(node.getHistorizing());

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    protected DataValue getVariableTypeAttribute(
        AttributeContext context,
        VariableTypeNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case Value:
                return node.getValue().orElseThrow(AttributeUtil.ATTRIBUTE_ID_INVALID_EXCEPTION);

            case DataType:
                return AttributeUtil.dv(node.getDataType());

            case ValueRank:
                return AttributeUtil.dv(node.getValueRank());

            case ArrayDimensions:
                return node.getArrayDimensions()
                    .map(AttributeUtil::dv)
                    .orElseThrow(AttributeUtil.ATTRIBUTE_ID_INVALID_EXCEPTION);

            case IsAbstract:
                return AttributeUtil.dv(node.getIsAbstract());

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    protected DataValue getViewAttribute(
        AttributeContext context,
        ViewNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case ContainsNoLoops:
                return AttributeUtil.dv(node.getContainsNoLoops());

            case EventNotifier:
                return AttributeUtil.dv(node.getEventNotifier());

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    protected void setBaseAttribute(Node node, AttributeId attributeId, DataValue value) throws UaException {
        switch (attributeId) {
            case NodeId:
                node.setNodeId(AttributeUtil.extract(value));
                break;
            case NodeClass:
                node.setNodeClass(AttributeUtil.extract(value));
                break;
            case BrowseName:
                node.setBrowseName(AttributeUtil.extract(value));
                break;
            case DisplayName:
                node.setDisplayName(AttributeUtil.extract(value));
                break;
            case Description:
                node.setDescription(AttributeUtil.extract(value));
                break;
            case WriteMask:
                node.setWriteMask(AttributeUtil.extract(value));
                break;
            case UserWriteMask:
                node.setUserWriteMask(AttributeUtil.extract(value));
                break;

            default:
                throw new UaException(StatusCodes.Bad_AttributeIdInvalid);
        }
    }

    protected void setDataTypeAttribute(
        AttributeContext context, DataTypeNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case IsAbstract:
                node.setIsAbstract(AttributeUtil.extract(value));
                break;

            default:
                setBaseAttribute(node, attributeId, value);
        }
    }

    protected void setMethodAttribute(
        AttributeContext context, MethodNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case Executable:
                node.setExecutable(AttributeUtil.extract(value));
                break;
            case UserExecutable:
                node.setUserExecutable(AttributeUtil.extract(value));
                break;

            default:
                setBaseAttribute(node, attributeId, value);
        }
    }

    protected void setObjectAttribute(
        AttributeContext context, ObjectNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case EventNotifier:
                node.setEventNotifier(AttributeUtil.extract(value));
                break;

            default:
                setBaseAttribute(node, attributeId, value);
        }
    }

    protected void setObjectTypeAttribute(
        ObjectTypeNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case IsAbstract:
                node.setIsAbstract(AttributeUtil.extract(value));
                break;

            default:
                setBaseAttribute(node, attributeId, value);
        }
    }

    protected void setReferenceTypeAttribute(
        ReferenceTypeNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case IsAbstract:
                node.setIsAbstract(AttributeUtil.extract(value));
                break;
            case Symmetric:
                node.setSymmetric(AttributeUtil.extract(value));
                break;
            case InverseName:
                node.setInverseName(AttributeUtil.extract(value));
                break;

            default:
                setBaseAttribute(node, attributeId, value);
        }
    }

    protected void setVariableAttribute(
        AttributeContext context,
        VariableNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case Value:
                node.setValue(value);
                break;
            case DataType:
                node.setDataType(AttributeUtil.extract(value));
                break;
            case ValueRank:
                node.setValueRank(AttributeUtil.extract(value));
                break;
            case ArrayDimensions:
                node.setArrayDimensions(Optional.ofNullable(AttributeUtil.extract(value)));
                break;
            case AccessLevel:
                node.setAccessLevel(AttributeUtil.extract(value));
                break;
            case UserAccessLevel:
                node.setUserAccessLevel(AttributeUtil.extract(value));
                break;
            case MinimumSamplingInterval:
                node.setMinimumSamplingInterval(AttributeUtil.extract(value));
                break;
            case Historizing:
                node.setHistorizing(AttributeUtil.extract(value));
                break;

            default:
                setBaseAttribute(node, attributeId, value);
        }
    }

    protected void setVariableTypeAttribute(
        VariableTypeNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case Value:
                node.setValue(Optional.of(value));
                break;
            case DataType:
                node.setDataType(AttributeUtil.extract(value));
                break;
            case ValueRank:
                node.setValueRank(AttributeUtil.extract(value));
                break;
            case ArrayDimensions:
                node.setArrayDimensions(AttributeUtil.extract(value));
                break;
            case IsAbstract:
                node.setIsAbstract(AttributeUtil.extract(value));
                break;

            default:
                setBaseAttribute(node, attributeId, value);
        }
    }

    protected void setViewAttribute(
        ViewNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case ContainsNoLoops:
                node.setContainsNoLoops(AttributeUtil.extract(value));
                break;
            case EventNotifier:
                node.setEventNotifier(AttributeUtil.extract(value));
                break;

            default:
                setBaseAttribute(node, attributeId, value);
        }
    }

}