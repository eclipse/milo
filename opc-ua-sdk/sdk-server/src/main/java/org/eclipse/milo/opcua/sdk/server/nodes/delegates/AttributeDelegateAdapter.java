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
import java.util.function.Supplier;

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
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

public class AttributeDelegateAdapter implements AttributeDelegate {

    private static final Supplier<UaException> ATTRIBUTE_ID_INVALID_EXCEPTION =
        () -> new UaException(StatusCodes.Bad_AttributeIdInvalid);

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

    protected DataValue getBaseAttribute(AttributeContext context, Node node, AttributeId attributeId) throws UaException {
        switch (attributeId) {
            case NodeId:
                return dv(node.getNodeId());

            case NodeClass:
                return dv(node.getNodeClass());

            case BrowseName:
                return dv(node.getBrowseName());

            case DisplayName:
                return dv(node.getDisplayName());

            case Description:
                return node.getDescription()
                    .map(AttributeDelegateAdapter::dv)
                    .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

            case WriteMask:
                return node.getWriteMask()
                    .map(AttributeDelegateAdapter::dv)
                    .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

            case UserWriteMask:
                return node.getUserWriteMask()
                    .map(AttributeDelegateAdapter::dv)
                    .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

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
                return dv(node.getIsAbstract());

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
                return dv(node.isExecutable());

            case UserExecutable:
                return dv(node.isUserExecutable());

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
                return dv(node.getEventNotifier());

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
                return dv(node.getIsAbstract());

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
                return dv(node.getIsAbstract());

            case Symmetric:
                return dv(node.getSymmetric());

            case InverseName:
                return node.getInverseName()
                    .map(AttributeDelegateAdapter::dv)
                    .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

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
                return dv(node.getDataType());

            case ValueRank:
                return dv(node.getValueRank());

            case ArrayDimensions:
                return node.getArrayDimensions()
                    .map(AttributeDelegateAdapter::dv)
                    .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

            case AccessLevel:
                return dv(node.getAccessLevel());

            case UserAccessLevel:
                return dv(node.getUserAccessLevel());

            case MinimumSamplingInterval:
                return node.getMinimumSamplingInterval()
                    .map(AttributeDelegateAdapter::dv)
                    .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

            case Historizing:
                return dv(node.getHistorizing());

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
                return node.getValue().orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

            case DataType:
                return dv(node.getDataType());

            case ValueRank:
                return dv(node.getValueRank());

            case ArrayDimensions:
                return node.getArrayDimensions()
                    .map(AttributeDelegateAdapter::dv)
                    .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

            case IsAbstract:
                return dv(node.getIsAbstract());

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
                return dv(node.getContainsNoLoops());

            case EventNotifier:
                return dv(node.getEventNotifier());

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    protected void setBaseAttribute(Node node, AttributeId attributeId, DataValue value) throws UaException {
        switch (attributeId) {
            case NodeId:
                node.setNodeId(extract(value));
                break;
            case NodeClass:
                node.setNodeClass(extract(value));
                break;
            case BrowseName:
                node.setBrowseName(extract(value));
                break;
            case DisplayName:
                node.setDisplayName(extract(value));
                break;
            case Description:
                node.setDescription(extract(value));
                break;
            case WriteMask:
                node.setWriteMask(extract(value));
                break;
            case UserWriteMask:
                node.setUserWriteMask(extract(value));
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
                node.setIsAbstract(extract(value));
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
                node.setExecutable(extract(value));
                break;
            case UserExecutable:
                node.setUserExecutable(extract(value));
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
                node.setEventNotifier(extract(value));
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
                node.setIsAbstract(extract(value));
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
                node.setIsAbstract(extract(value));
                break;
            case Symmetric:
                node.setSymmetric(extract(value));
                break;
            case InverseName:
                node.setInverseName(extract(value));
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
                node.setDataType(extract(value));
                break;
            case ValueRank:
                node.setValueRank(extract(value));
                break;
            case ArrayDimensions:
                node.setArrayDimensions(Optional.ofNullable(extract(value)));
                break;
            case AccessLevel:
                node.setAccessLevel(extract(value));
                break;
            case UserAccessLevel:
                node.setUserAccessLevel(extract(value));
                break;
            case MinimumSamplingInterval:
                node.setMinimumSamplingInterval(extract(value));
                break;
            case Historizing:
                node.setHistorizing(extract(value));
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
                node.setDataType(extract(value));
                break;
            case ValueRank:
                node.setValueRank(extract(value));
                break;
            case ArrayDimensions:
                node.setArrayDimensions(extract(value));
                break;
            case IsAbstract:
                node.setIsAbstract(extract(value));
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
                node.setContainsNoLoops(extract(value));
                break;
            case EventNotifier:
                node.setEventNotifier(extract(value));
                break;

            default:
                setBaseAttribute(node, attributeId, value);
        }
    }

    /**
     * DataValue for a non-value attribute; no source timestamp included.
     */
    protected static DataValue dv(Object o) {
        return new DataValue(new Variant(o), StatusCode.GOOD, null, DateTime.now());
    }

    @SuppressWarnings("unchecked")
    protected static <T> T extract(DataValue value) throws UaException {
        Variant variant = value.getValue();
        if (variant == null) return null;

        Object o = variant.getValue();
        if (o == null) return null;

        try {
            return (T) o;
        } catch (ClassCastException e) {
            throw new UaException(StatusCodes.Bad_TypeMismatch);
        }
    }

}