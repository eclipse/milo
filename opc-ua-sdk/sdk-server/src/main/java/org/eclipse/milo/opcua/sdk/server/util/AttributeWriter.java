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
import org.eclipse.milo.opcua.sdk.core.nodes.DataTypeNode;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.core.nodes.Node;
import org.eclipse.milo.opcua.sdk.core.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.core.nodes.ObjectTypeNode;
import org.eclipse.milo.opcua.sdk.core.nodes.ReferenceTypeNode;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.NamespaceManager;
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
                                  Node node,
                                  AttributeId attributeId,
                                  DataValue value) throws UaException {

        switch (node.getNodeClass()) {
            case DataType:
                writeDataTypeAttribute(ns, (DataTypeNode) node, attributeId, value);
                break;

            case Method:
                writeMethodAttribute(ns, (MethodNode) node, attributeId, value);
                break;

            case Object:
                writeObjectAttribute(ns, (ObjectNode) node, attributeId, value);
                break;

            case ObjectType:
                writeObjectTypeAttribute(ns, (ObjectTypeNode) node, attributeId, value);
                break;

            case ReferenceType:
                writeReferenceTypeAttribute(ns, (ReferenceTypeNode) node, attributeId, value);
                break;

            case Variable:
                writeVariableAttribute(ns, (VariableNode) node, attributeId, value);
                break;

            case VariableType:
                writeVariableTypeAttribute(ns, (VariableTypeNode) node, attributeId, value);
                break;

            default:
                throw new UaException(StatusCodes.Bad_NodeClassInvalid);
        }
    }

    private static void writeNodeAttribute(NamespaceManager ns,
                                           Node node,
                                           AttributeId attributeId,
                                           DataValue value) throws UaException {

        UInteger writeMask = node.getWriteMask().orElse(uint(0));
        EnumSet<WriteMask> writeMasks = WriteMask.fromMask(writeMask);

        switch (attributeId) {
            case NodeId:
                if (writeMasks.contains(WriteMask.NodeId)) {
                    node.setNodeId(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case NodeClass:
                if (writeMasks.contains(WriteMask.NodeClass)) {
                    node.setNodeClass(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case BrowseName:
                if (writeMasks.contains(WriteMask.BrowseName)) {
                    node.setBrowseName(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case DisplayName:
                if (writeMasks.contains(WriteMask.DisplayName)) {
                    node.setDisplayName(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case Description:
                if (writeMasks.contains(WriteMask.Description)) {
                    node.setDescription(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case WriteMask:
                if (writeMasks.contains(WriteMask.WriteMask)) {
                    node.setWriteMask(Optional.ofNullable(extract(value)));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case UserWriteMask:
                if (writeMasks.contains(WriteMask.UserWriteMask)) {
                    node.setUserWriteMask(Optional.ofNullable(extract(value)));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            default:
                throw new UaException(StatusCodes.Bad_AttributeIdInvalid);
        }
    }

    private static void writeDataTypeAttribute(NamespaceManager ns,
                                               DataTypeNode node,
                                               AttributeId attributeId,
                                               DataValue value) throws UaException {

        UInteger writeMask = node.getWriteMask().orElse(uint(0));
        EnumSet<WriteMask> writeMasks = WriteMask.fromMask(writeMask);

        switch (attributeId) {
            case IsAbstract:
                if (writeMasks.contains(WriteMask.IsAbstract)) {
                    node.setIsAbstract(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            default:
                writeNodeAttribute(ns, node, attributeId, value);
        }
    }

    private static void writeMethodAttribute(NamespaceManager ns,
                                             MethodNode node,
                                             AttributeId attributeId,
                                             DataValue value) throws UaException {

        UInteger writeMask = node.getWriteMask().orElse(uint(0));
        EnumSet<WriteMask> writeMasks = WriteMask.fromMask(writeMask);

        switch (attributeId) {
            case Executable:
                if (writeMasks.contains(WriteMask.Executable)) {
                    node.setExecutable(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case UserExecutable:
                if (writeMasks.contains(WriteMask.UserExecutable)) {
                    node.setUserExecutable(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            default:
                writeNodeAttribute(ns, node, attributeId, value);
        }
    }

    private static void writeObjectAttribute(NamespaceManager ns,
                                             ObjectNode node,
                                             AttributeId attributeId,
                                             DataValue value) throws UaException {

        UInteger writeMask = node.getWriteMask().orElse(uint(0));
        EnumSet<WriteMask> writeMasks = WriteMask.fromMask(writeMask);

        switch (attributeId) {
            case EventNotifier:
                if (writeMasks.contains(WriteMask.EventNotifier)) {
                    node.setWriteMask(Optional.ofNullable(extract(value)));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            default:
                writeNodeAttribute(ns, node, attributeId, value);
        }
    }

    private static void writeObjectTypeAttribute(NamespaceManager ns,
                                                 ObjectTypeNode node,
                                                 AttributeId attributeId,
                                                 DataValue value) throws UaException {

        UInteger writeMask = node.getWriteMask().orElse(uint(0));
        EnumSet<WriteMask> writeMasks = WriteMask.fromMask(writeMask);

        switch (attributeId) {
            case IsAbstract:
                if (writeMasks.contains(WriteMask.IsAbstract)) {
                    node.setIsAbstract(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            default:
                writeNodeAttribute(ns, node, attributeId, value);
        }
    }

    private static void writeReferenceTypeAttribute(NamespaceManager ns,
                                                    ReferenceTypeNode node,
                                                    AttributeId attributeId,
                                                    DataValue value) throws UaException {

        UInteger writeMask = node.getWriteMask().orElse(uint(0));
        EnumSet<WriteMask> writeMasks = WriteMask.fromMask(writeMask);

        switch (attributeId) {
            case IsAbstract:
                if (writeMasks.contains(WriteMask.IsAbstract)) {
                    node.setIsAbstract(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case Symmetric:
                if (writeMasks.contains(WriteMask.Symmetric)) {
                    node.setSymmetric(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case InverseName:
                if (writeMasks.contains(WriteMask.InverseName)) {
                    node.setInverseName(Optional.ofNullable(extract(value)));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            default:
                writeNodeAttribute(ns, node, attributeId, value);
        }
    }

    private static void writeVariableAttribute(NamespaceManager ns,
                                               VariableNode node,
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

                    node.setValue(value);
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case DataType:
                if (writeMasks.contains(WriteMask.DataType)) {
                    node.setDataType(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case ValueRank:
                if (writeMasks.contains(WriteMask.ValueRank)) {
                    node.setValueRank(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case ArrayDimensions:
                if (writeMasks.contains(WriteMask.ArrayDimensions)) {
                    node.setArrayDimensions(Optional.of(extract(value)));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case AccessLevel:
                if (writeMasks.contains(WriteMask.AccessLevel)) {
                    node.setAccessLevel(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case UserAccessLevel:
                if (writeMasks.contains(WriteMask.UserAccessLevel)) {
                    node.setUserAccessLevel(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case MinimumSamplingInterval:
                if (writeMasks.contains(WriteMask.MinimumSamplingInterval)) {
                    node.setMinimumSamplingInterval(Optional.of(extract(value)));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case Historizing:
                if (writeMasks.contains(WriteMask.Historizing)) {
                    node.setHistorizing(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            default:
                writeNodeAttribute(ns, node, attributeId, value);
        }
    }

    private static void writeVariableTypeAttribute(NamespaceManager ns,
                                                   VariableTypeNode node,
                                                   AttributeId attributeId,
                                                   DataValue value) throws UaException {

        UInteger writeMask = node.getWriteMask().orElse(uint(0));
        EnumSet<WriteMask> writeMasks = WriteMask.fromMask(writeMask);

        switch (attributeId) {
            case Value:
                if (writeMasks.contains(WriteMask.ValueForVariableType)) {
                    value = validateDataType(ns, node.getDataType().expanded(), value);
                    validateArrayType(node.getValueRank(), node.getArrayDimensions(), value);

                    node.setValue(Optional.ofNullable(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case DataType:
                if (writeMasks.contains(WriteMask.DataType)) {
                    node.setDataType(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case ValueRank:
                if (writeMasks.contains(WriteMask.ValueRank)) {
                    node.setValueRank(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            case IsAbstract:
                if (writeMasks.contains(WriteMask.IsAbstract)) {
                    node.setIsAbstract(extract(value));
                } else {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }
                break;

            default:
                writeNodeAttribute(ns, node, attributeId, value);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T extract(DataValue value) throws UaException {
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
