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

import java.util.EnumSet;
import java.util.function.Supplier;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.core.NumericRange;
import org.eclipse.milo.opcua.sdk.core.WriteMask;
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
import org.eclipse.milo.opcua.sdk.server.nodes.ServerNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class AttributeReader {

    private static final Supplier<UaException> ATTRIBUTE_ID_INVALID_EXCEPTION =
        () -> new UaException(StatusCodes.Bad_AttributeIdInvalid);

    public static DataValue readAttribute(AttributeContext context,
                                          ServerNode node,
                                          AttributeId attributeId,
                                          @Nullable TimestampsToReturn timestamps,
                                          @Nullable String indexRange) {
        try {
            DataValue value = readAttribute(context, node, attributeId);

            if (indexRange != null) {
                NumericRange range = NumericRange.parse(indexRange);

                Object valueAtRange = NumericRange.readFromValueAtRange(value.getValue(), range);

                value = new DataValue(
                    new Variant(valueAtRange),
                    value.getStatusCode(),
                    value.getSourceTime(),
                    value.getServerTime()
                );
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

    private static DataValue readAttribute(
        AttributeContext context,
        ServerNode node,
        AttributeId attributeId) throws UaException {

        switch (node.getNodeClass()) {
            case DataType:
                return readDataTypeAttribute((ServerNode & DataTypeNode) node, attributeId);

            case Method:
                return readMethodAttribute((ServerNode & MethodNode) node, attributeId);

            case Object:
                return readObjectAttribute((ServerNode & ObjectNode) node, attributeId);

            case ObjectType:
                return readObjectTypeAttribute((ServerNode & ObjectTypeNode) node, attributeId);

            case ReferenceType:
                return readReferenceTypeAttribute((ServerNode & ReferenceTypeNode) node, attributeId);

            case Variable:
                return readVariableAttribute(context, (ServerNode & VariableNode) node, attributeId);

            case VariableType:
                return readVariableTypeAttribute((ServerNode & VariableTypeNode) node, attributeId);

            case View:
                return readViewAttribute((ServerNode & ViewNode) node, attributeId);

            default:
                throw new UaException(StatusCodes.Bad_NodeClassInvalid);
        }

    }


    private static DataValue readNodeAttribute(Node node, AttributeId attributeId) throws UaException {
        UInteger writeMask = node.getWriteMask().orElse(uint(0));
        EnumSet<WriteMask> writeMasks = WriteMask.fromMask(writeMask);

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

    private static <T extends ServerNode & VariableNode>
    DataValue readVariableAttribute(AttributeContext context, T node, AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case Value:
                return node.getAttribute(context, AttributeId.Value);

            case DataType:
                return node.getAttribute(context, AttributeId.DataType);

            case ValueRank:
                return node.getAttribute(context, AttributeId.ValueRank);

            case ArrayDimensions:
                return node.getAttribute(context, AttributeId.ArrayDimensions);

            case AccessLevel:
                return node.getAttribute(context, AttributeId.AccessLevel);

            case UserAccessLevel:
                return node.getAttribute(context, AttributeId.UserAccessLevel);

            case MinimumSamplingInterval:
                return node.getAttribute(context, AttributeId.MinimumSamplingInterval);

            case Historizing:
                return node.getAttribute(context, AttributeId.Historizing);

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

    private static DataValue readViewAttribute(ServerNode node, AttributeId attributeId) {
        return null; // TODO
    }

    /**
     * DataValue for a non-value attribute; no source timestamp included.
     */
    private static DataValue dv(Object o) {
        return new DataValue(new Variant(o), StatusCode.GOOD, null, DateTime.now());
    }

}
