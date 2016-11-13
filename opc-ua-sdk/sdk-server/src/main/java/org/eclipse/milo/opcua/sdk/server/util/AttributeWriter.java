/*
 * Copyright (c) 2016 Kevin Herron and others
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
import java.util.Optional;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.NumericRange;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.WriteMask;
import org.eclipse.milo.opcua.sdk.server.NamespaceManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.DataTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ReferenceTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.ServerNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.util.ArrayUtil;
import org.eclipse.milo.opcua.stack.core.util.TypeUtil;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class AttributeWriter {

    public static void writeAttribute(NamespaceManager ns,
                                      ServerNode node,
                                      AttributeId attributeId,
                                      DataValue value,
                                      @Nullable String indexRange) throws UaException {

        Variant updateVariant = value.getValue();

        if (indexRange != null) {
            NumericRange range = NumericRange.parse(indexRange);

            DataValue current = node.readAttribute(attributeId);
            Variant currentVariant = current.getValue();

            Object valueAtRange = NumericRange.writeToValueAtRange(
                currentVariant,
                updateVariant,
                range);

            updateVariant = new Variant(valueAtRange);
        }

        DateTime sourceTime = value.getSourceTime();
        DateTime serverTime = value.getServerTime();

        value = new DataValue(
            updateVariant,
            value.getStatusCode(),
            (sourceTime == null || sourceTime.isNull()) ? DateTime.now() : sourceTime,
            (serverTime == null || serverTime.isNull()) ? DateTime.now() : serverTime);

        writeNode(ns, node, attributeId, value);
    }

    private static void writeNode(NamespaceManager ns,
                                  ServerNode node,
                                  AttributeId attributeId,
                                  DataValue value) throws UaException {

        switch (node.getNodeClass()) {
            case DataType:
                writeDataTypeAttribute(ns, (ServerNode & DataTypeNode) node, attributeId, value);
                break;

            case Method:
                writeMethodAttribute(ns, (ServerNode & MethodNode) node, attributeId, value);
                break;

            case Object:
                writeObjectAttribute(ns, (ServerNode & ObjectNode) node, attributeId, value);
                break;

            case ObjectType:
                writeObjectTypeAttribute(ns, (ServerNode & ObjectTypeNode) node, attributeId, value);
                break;

            case ReferenceType:
                writeReferenceTypeAttribute(ns, (ServerNode & ReferenceTypeNode) node, attributeId, value);
                break;

            case Variable:
                writeVariableAttribute(ns, (ServerNode & VariableNode) node, attributeId, value);
                break;

            case VariableType:
                writeVariableTypeAttribute(ns, (ServerNode & VariableTypeNode) node, attributeId, value);
                break;

            default:
                throw new UaException(StatusCodes.Bad_NodeClassInvalid);
        }
    }

    private static void writeNodeAttribute(NamespaceManager ns,
                                           ServerNode node,
                                           AttributeId attributeId,
                                           DataValue value) throws UaException {

        UInteger writeMask = node.getWriteMask().orElse(uint(0));
        EnumSet<WriteMask> writeMasks = WriteMask.fromMask(writeMask);

        switch (attributeId) {
            case NodeId:
                if (writeMasks.contains(WriteMask.NodeId)) {
                    node.setAttribute(AttributeId.NodeId, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case NodeClass:
                if (writeMasks.contains(WriteMask.NodeClass)) {
                    node.setAttribute(AttributeId.NodeClass, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case BrowseName:
                if (writeMasks.contains(WriteMask.BrowseName)) {
                    node.setAttribute(AttributeId.BrowseName, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case DisplayName:
                if (writeMasks.contains(WriteMask.DisplayName)) {
                    node.setAttribute(AttributeId.DisplayName, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case Description:
                if (writeMasks.contains(WriteMask.Description)) {
                    node.setAttribute(AttributeId.Description, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case WriteMask:
                if (writeMasks.contains(WriteMask.WriteMask)) {
                    node.setAttribute(AttributeId.WriteMask, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case UserWriteMask:
                if (writeMasks.contains(WriteMask.UserWriteMask)) {
                    node.setAttribute(AttributeId.UserWriteMask, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            default:
                throw new UaException(StatusCodes.Bad_AttributeIdInvalid);
        }
    }

    private static <T extends ServerNode & DataTypeNode>
    void writeDataTypeAttribute(NamespaceManager ns,
                                T node,
                                AttributeId attributeId,
                                DataValue value) throws UaException {

        UInteger writeMask = node.getWriteMask().orElse(uint(0));
        EnumSet<WriteMask> writeMasks = WriteMask.fromMask(writeMask);

        switch (attributeId) {
            case IsAbstract:
                if (writeMasks.contains(WriteMask.IsAbstract)) {
                    node.setAttribute(AttributeId.IsAbstract, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            default:
                writeNodeAttribute(ns, node, attributeId, value);
        }
    }

    private static <T extends ServerNode & MethodNode>
    void writeMethodAttribute(NamespaceManager ns,
                              T node,
                              AttributeId attributeId,
                              DataValue value) throws UaException {

        UInteger writeMask = node.getWriteMask().orElse(uint(0));
        EnumSet<WriteMask> writeMasks = WriteMask.fromMask(writeMask);

        switch (attributeId) {
            case Executable:
                if (writeMasks.contains(WriteMask.Executable)) {
                    node.setAttribute(AttributeId.Executable, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case UserExecutable:
                if (writeMasks.contains(WriteMask.UserExecutable)) {
                    node.setAttribute(AttributeId.UserExecutable, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            default:
                writeNodeAttribute(ns, node, attributeId, value);
        }
    }

    private static <T extends ServerNode & ObjectNode>
    void writeObjectAttribute(NamespaceManager ns,
                              T node,
                              AttributeId attributeId,
                              DataValue value) throws UaException {

        UInteger writeMask = node.getWriteMask().orElse(uint(0));
        EnumSet<WriteMask> writeMasks = WriteMask.fromMask(writeMask);

        switch (attributeId) {
            case EventNotifier:
                if (writeMasks.contains(WriteMask.EventNotifier)) {
                    node.setAttribute(AttributeId.EventNotifier, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            default:
                writeNodeAttribute(ns, node, attributeId, value);
        }
    }

    private static <T extends ServerNode & ObjectTypeNode>
    void writeObjectTypeAttribute(NamespaceManager ns,
                                  T node,
                                  AttributeId attributeId,
                                  DataValue value) throws UaException {

        UInteger writeMask = node.getWriteMask().orElse(uint(0));
        EnumSet<WriteMask> writeMasks = WriteMask.fromMask(writeMask);

        switch (attributeId) {
            case IsAbstract:
                if (writeMasks.contains(WriteMask.IsAbstract)) {
                    node.setAttribute(AttributeId.IsAbstract, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            default:
                writeNodeAttribute(ns, node, attributeId, value);
        }
    }

    private static <T extends ServerNode & ReferenceTypeNode>
    void writeReferenceTypeAttribute(NamespaceManager ns,
                                     T node,
                                     AttributeId attributeId,
                                     DataValue value) throws UaException {

        UInteger writeMask = node.getWriteMask().orElse(uint(0));
        EnumSet<WriteMask> writeMasks = WriteMask.fromMask(writeMask);

        switch (attributeId) {
            case IsAbstract:
                if (writeMasks.contains(WriteMask.IsAbstract)) {
                    node.setAttribute(AttributeId.IsAbstract, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case Symmetric:
                if (writeMasks.contains(WriteMask.Symmetric)) {
                    node.setAttribute(AttributeId.Symmetric, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case InverseName:
                if (writeMasks.contains(WriteMask.InverseName)) {
                    node.setAttribute(AttributeId.InverseName, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            default:
                writeNodeAttribute(ns, node, attributeId, value);
        }
    }

    private static <T extends ServerNode & VariableNode>
    void writeVariableAttribute(NamespaceManager ns,
                                T node,
                                AttributeId attributeId,
                                DataValue value) throws UaException {

        EnumSet<AccessLevel> accessLevels = AccessLevel.fromMask(node.getAccessLevel());

        UInteger writeMask = node.getWriteMask().orElse(uint(0));
        EnumSet<WriteMask> writeMasks = WriteMask.fromMask(writeMask);

        switch (attributeId) {
            case Value:
                if (accessLevels.contains(AccessLevel.CurrentWrite)) {
                    value = validateDataType(ns, node.getDataType().expanded(), value);
                    validateArrayType(node.getValueRank(), node.getArrayDimensions(), value);

                    node.setAttribute(AttributeId.Value, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case DataType:
                if (writeMasks.contains(WriteMask.DataType)) {
                    node.setAttribute(AttributeId.DataType, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case ValueRank:
                if (writeMasks.contains(WriteMask.ValueRank)) {
                    node.setAttribute(AttributeId.ValueRank, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case ArrayDimensions:
                if (writeMasks.contains(WriteMask.ArrayDimensions)) {
                    node.setAttribute(AttributeId.ArrayDimensions, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case AccessLevel:
                if (writeMasks.contains(WriteMask.AccessLevel)) {
                    node.setAttribute(AttributeId.AccessLevel, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case UserAccessLevel:
                if (writeMasks.contains(WriteMask.UserAccessLevel)) {
                    node.setAttribute(AttributeId.UserAccessLevel, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case MinimumSamplingInterval:
                if (writeMasks.contains(WriteMask.MinimumSamplingInterval)) {
                    node.setAttribute(AttributeId.MinimumSamplingInterval, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case Historizing:
                if (writeMasks.contains(WriteMask.Historizing)) {
                    node.setAttribute(AttributeId.Historizing, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            default:
                writeNodeAttribute(ns, node, attributeId, value);
        }
    }

    private static <T extends ServerNode & VariableTypeNode>
    void writeVariableTypeAttribute(NamespaceManager ns,
                                    T node,
                                    AttributeId attributeId,
                                    DataValue value) throws UaException {

        UInteger writeMask = node.getWriteMask().orElse(uint(0));
        EnumSet<WriteMask> writeMasks = WriteMask.fromMask(writeMask);

        switch (attributeId) {
            case Value:
                if (writeMasks.contains(WriteMask.ValueForVariableType)) {
                    value = validateDataType(ns, node.getDataType().expanded(), value);
                    validateArrayType(node.getValueRank(), node.getArrayDimensions(), value);

                    node.setAttribute(AttributeId.Value, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case DataType:
                if (writeMasks.contains(WriteMask.DataType)) {
                    node.setAttribute(AttributeId.DataType, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case ValueRank:
                if (writeMasks.contains(WriteMask.ValueRank)) {
                    node.setAttribute(AttributeId.ValueRank, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case IsAbstract:
                if (writeMasks.contains(WriteMask.IsAbstract)) {
                    node.setAttribute(AttributeId.IsAbstract, value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            default:
                writeNodeAttribute(ns, node, attributeId, value);
        }
    }

    private static DataValue validateDataType(NamespaceManager ns,
                                              ExpandedNodeId dataType,
                                              DataValue value) throws UaException {

        Variant variant = value.getValue();
        if (variant == null) return value;

        Object o = variant.getValue();
        if (o == null) throw new UaException(StatusCodes.Bad_TypeMismatch);

        Class<?> expected = TypeUtil.getBackingClass(dataType);

        Class<?> actual = o.getClass().isArray() ?
            o.getClass().getComponentType() : o.getClass();

        if (expected == null) {
            throw new UaException(StatusCodes.Bad_TypeMismatch);
        } else {
            if (!expected.isAssignableFrom(actual)) {
                /*
                 * Writing a ByteString to a UByte[] is explicitly allowed by the spec.
                 */
                if (o instanceof ByteString && expected == UByte.class) {
                    ByteString byteString = (ByteString) o;

                    return new DataValue(
                        new Variant(byteString.uBytes()),
                        value.getStatusCode(),
                        value.getSourceTime(),
                        value.getServerTime());
                } else if (expected == Variant.class) {
                    // allow to write anything to a Variant
                    return value;
                } else {
                    throw new UaException(StatusCodes.Bad_TypeMismatch);
                }
            }
        }

        return value;
    }

    private static void validateArrayType(int valueRank,
                                          Optional<UInteger[]> arrayDimensionsOpt,
                                          DataValue value) throws UaException {

        Variant variant = value.getValue();
        if (variant == null) return;

        Object o = variant.getValue();
        if (o == null) return;

        boolean valueIsArray = o.getClass().isArray();

        switch (valueRank) {
            case ValueRanks.ScalarOrOneDimension:
                if (valueIsArray) {
                    int[] valueDimensions = ArrayUtil.getDimensions(o);

                    if (valueDimensions.length > 1) {
                        throw new UaException(StatusCodes.Bad_TypeMismatch);
                    }
                }
                break;

            case ValueRanks.Any:
                break;

            case ValueRanks.Scalar:
                if (valueIsArray) {
                    throw new UaException(StatusCodes.Bad_TypeMismatch);
                }
                break;

            case ValueRanks.OneOrMoreDimensions:
                if (!valueIsArray) {
                    throw new UaException(StatusCodes.Bad_TypeMismatch);
                }
                break;

            case ValueRanks.OneDimension:
            default:
                if (!valueIsArray) {
                    throw new UaException(StatusCodes.Bad_TypeMismatch);
                }

                int[] valueDimensions = ArrayUtil.getDimensions(o);

                if (valueDimensions.length != valueRank) {
                    throw new UaException(StatusCodes.Bad_TypeMismatch);
                }

                int[] nodeDimensions = arrayDimensionsOpt.map(uia -> {
                    int[] arrayDimensions = new int[uia.length];
                    for (int i = 0; i < uia.length; i++) {
                        arrayDimensions[i] = uia[i].intValue();
                    }
                    return arrayDimensions;
                }).orElse(new int[0]);

                if (nodeDimensions.length > 0) {
                    if (nodeDimensions.length != valueDimensions.length) {
                        throw new UaException(StatusCodes.Bad_TypeMismatch);
                    }

                    for (int i = 0; i < nodeDimensions.length; i++) {
                        if (nodeDimensions[i] > 0 && valueDimensions[i] > nodeDimensions[i]) {
                            throw new UaException(StatusCodes.Bad_TypeMismatch);
                        }
                    }
                }
                break;
        }
    }

}
