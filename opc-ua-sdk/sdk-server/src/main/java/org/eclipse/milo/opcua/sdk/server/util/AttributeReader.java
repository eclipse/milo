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

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.function.Supplier;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.core.NumericRange;
import org.eclipse.milo.opcua.sdk.core.nodes.DataTypeNode;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.core.nodes.Node;
import org.eclipse.milo.opcua.sdk.core.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.core.nodes.ObjectTypeNode;
import org.eclipse.milo.opcua.sdk.core.nodes.ReferenceTypeNode;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

public class AttributeReader {

    private static final Supplier<UaException> ATTRIBUTE_ID_INVALID_EXCEPTION =
        () -> new UaException(StatusCodes.Bad_AttributeIdInvalid);

    public static DataValue readAttribute(Node node,
                                          AttributeId attributeId,
                                          @Nullable TimestampsToReturn timestamps,
                                          @Nullable String indexRange) {
        try {
            DataValue value = readAttribute(node, attributeId);

            if (indexRange != null) {
                NumericRange range = NumericRange.parse(indexRange);

                Object valueAtRange = NumericRange.readFromValueAtRange(value.getValue(), range);

                value = new DataValue(
                    new Variant(valueAtRange),
                    value.getStatusCode(),
                    value.getSourceTime(),
                    value.getServerTime());
            }

            if (timestamps != null) {
                value = (attributeId == AttributeId.Value) ?
                    DataValue.derivedValue(value, timestamps) :
                    DataValue.derivedNonValue(value, timestamps);
            }

            return value;
        } catch (UaException e) {
            return new DataValue(e.getStatusCode());
        }
    }

    private static DataValue readAttribute(Node node, AttributeId attributeId) throws UaException {
        switch (node.getNodeClass()) {
            case DataType:
                return readDataTypeAttribute((DataTypeNode) node, attributeId);

            case Method:
                return readMethodAttribute((MethodNode) node, attributeId);

            case Object:
                return readObjectAttribute((ObjectNode) node, attributeId);

            case ObjectType:
                return readObjectTypeAttribute((ObjectTypeNode) node, attributeId);

            case ReferenceType:
                return readReferenceTypeAttribute((ReferenceTypeNode) node, attributeId);

            case Variable:
                return readVariableAttribute((VariableNode) node, attributeId);

            case VariableType:
                return readVariableTypeAttribute((VariableTypeNode) node, attributeId);

            default:
                throw new UaException(StatusCodes.Bad_NodeClassInvalid);
        }

    }

    private static DataValue readNodeAttribute(Node node, AttributeId attributeId) throws UaException {
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
                return node.getDescription().map(AttributeReader::dv)
                    .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

            case WriteMask:
                return node.getWriteMask().map(AttributeReader::dv)
                    .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

            case UserWriteMask:
                return node.getUserWriteMask().map(AttributeReader::dv)
                    .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

            default:
                throw ATTRIBUTE_ID_INVALID_EXCEPTION.get();
        }
    }

    private static DataValue readDataTypeAttribute(DataTypeNode node, AttributeId attributeId) throws UaException {
        switch (attributeId) {
            case IsAbstract:
                return dv(node.getIsAbstract());

            default:
                return readNodeAttribute(node, attributeId);
        }
    }

    private static DataValue readMethodAttribute(MethodNode node, AttributeId attributeId) throws UaException {
        switch (attributeId) {
            case Executable:
                return dv(node.isExecutable());

            case UserExecutable:
                return dv(node.isUserExecutable());

            default:
                return readNodeAttribute(node, attributeId);
        }
    }

    private static DataValue readObjectAttribute(ObjectNode node, AttributeId attributeId) throws UaException {
        switch (attributeId) {
            case EventNotifier:
                return dv(node.getEventNotifier());

            default:
                return readNodeAttribute(node, attributeId);
        }
    }

    private static DataValue readObjectTypeAttribute(ObjectTypeNode node, AttributeId attributeId) throws UaException {
        switch (attributeId) {
            case IsAbstract:
                return dv(node.getIsAbstract());

            default:
                return readNodeAttribute(node, attributeId);
        }
    }

    private static DataValue readReferenceTypeAttribute(ReferenceTypeNode node,
                                                        AttributeId attributeId) throws UaException {
        switch (attributeId) {
            case IsAbstract:
                return dv(node.getIsAbstract());

            case Symmetric:
                return dv(node.getSymmetric());

            case InverseName:
                return node.getInverseName().map(AttributeReader::dv)
                    .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

            default:
                return readNodeAttribute(node, attributeId);
        }
    }

    private static DataValue readVariableAttribute(VariableNode node, AttributeId attributeId) throws UaException {
        switch (attributeId) {
            case Value:
                return new DataValue(
                    node.getValue().getValue(),
                    node.getValue().getStatusCode(),
                    node.getValue().getSourceTime(),
                    DateTime.now());

            case DataType:
                return dv(node.getDataType());

            case ValueRank:
                return dv(node.getValueRank());

            case ArrayDimensions:
                return node.getArrayDimensions().map(AttributeReader::dv)
                    .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

            case AccessLevel:
                return dv(node.getAccessLevel());

            case UserAccessLevel:
                return dv(node.getUserAccessLevel());

            case MinimumSamplingInterval:
                return node.getMinimumSamplingInterval().map(AttributeReader::dv)
                    .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

            case Historizing:
                return dv(node.getHistorizing());

            default:
                return readNodeAttribute(node, attributeId);
        }
    }

    private static DataValue readVariableTypeAttribute(VariableTypeNode node,
                                                       AttributeId attributeId) throws UaException {
        switch (attributeId) {
            case Value:
                return node.getValue().orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

            case DataType:
                return dv(node.getDataType());

            case ValueRank:
                return dv(node.getValueRank());

            case ArrayDimensions:
                return node.getArrayDimensions().map(AttributeReader::dv)
                    .orElseThrow(ATTRIBUTE_ID_INVALID_EXCEPTION);

            case IsAbstract:
                return dv(node.getIsAbstract());

            default:
                return readNodeAttribute(node, attributeId);
        }
    }

    /**
     * DataValue for a non-value attribute; no source timestamp included.
     */
    private static DataValue dv(Object o) {
        return new DataValue(new Variant(o), StatusCode.GOOD, null, DateTime.now());
    }

}
