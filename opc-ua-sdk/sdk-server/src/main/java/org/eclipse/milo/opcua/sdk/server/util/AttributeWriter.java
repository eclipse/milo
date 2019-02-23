/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.NumericRange;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.WriteMask;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.DataTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaServerNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.util.ArrayUtil;
import org.eclipse.milo.opcua.stack.core.util.TypeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;
import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.extract;
import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.getAccessLevels;
import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.getUserAccessLevels;
import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.getUserWriteMasks;
import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.getWriteMasks;

public class AttributeWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttributeWriter.class);

    public static void writeAttribute(AttributeContext context,
                                      UaServerNode node,
                                      AttributeId attributeId,
                                      DataValue value,
                                      @Nullable String indexRange) throws UaException {

        AttributeContext internalContext = new AttributeContext(context.getServer());

        NodeClass nodeClass = node.getNodeClass();

        if (attributeId == AttributeId.Value && nodeClass == NodeClass.Variable) {
            Set<AccessLevel> accessLevels = getAccessLevels(node, internalContext);
            if (!accessLevels.contains(AccessLevel.CurrentWrite)) {
                throw new UaException(StatusCodes.Bad_NotWritable);
            }

            Set<AccessLevel> userAccessLevels = getUserAccessLevels(node, context);
            if (!userAccessLevels.contains(AccessLevel.CurrentWrite)) {
                throw new UaException(StatusCodes.Bad_UserAccessDenied);
            }
        } else {
            WriteMask writeMask = writeMaskForAttribute(attributeId);

            Set<WriteMask> writeMasks = getWriteMasks(node, internalContext);
            if (!writeMasks.contains(writeMask)) {
                throw new UaException(StatusCodes.Bad_NotWritable);
            }

            Set<WriteMask> userWriteMasks = getUserWriteMasks(node, context);
            if (!userWriteMasks.contains(writeMask)) {
                throw new UaException(StatusCodes.Bad_UserAccessDenied);
            }
        }

        Variant updateVariant = value.getValue();

        if (indexRange != null) {
            NumericRange range = NumericRange.parse(indexRange);

            DataValue current = node.getAttribute(
                internalContext,
                attributeId
            );

            Variant currentVariant = current.getValue();

            Object valueAtRange = NumericRange.writeToValueAtRange(
                currentVariant,
                updateVariant,
                range
            );

            updateVariant = new Variant(valueAtRange);
        }

        DateTime sourceTime = value.getSourceTime();
        DateTime serverTime = value.getServerTime();

        value = new DataValue(
            updateVariant,
            value.getStatusCode(),
            (sourceTime == null || sourceTime.isNull()) ? DateTime.now() : sourceTime,
            (serverTime == null || serverTime.isNull()) ? DateTime.now() : serverTime
        );

        if (attributeId == AttributeId.Value) {
            NodeId dataType = extract(
                node.getAttribute(
                    internalContext,
                    AttributeId.DataType)
            );

            if (dataType != null) {
                value = validateDataType(context.getServer().getNodeManager(), dataType, value);
            }

            Integer valueRank = extract(
                node.getAttribute(
                    internalContext,
                    AttributeId.ValueRank)
            );

            if (valueRank == null) valueRank = 0;

            if (valueRank > 0) {
                UInteger[] arrayDimensions = extract(
                    node.getAttribute(
                        context,
                        AttributeId.ArrayDimensions)
                );

                validateArrayType(valueRank, arrayDimensions, value);
            }

            node.setAttribute(context, attributeId, value);
        } else {
            node.setAttribute(context, attributeId, value);
        }
    }

    private static WriteMask writeMaskForAttribute(AttributeId attributeId) {
        switch (attributeId) {
            case AccessLevel:
                return WriteMask.AccessLevel;
            case ArrayDimensions:
                return WriteMask.ArrayDimensions;
            case BrowseName:
                return WriteMask.BrowseName;
            case ContainsNoLoops:
                return WriteMask.ContainsNoLoops;
            case DataType:
                return WriteMask.DataType;
            case Description:
                return WriteMask.Description;
            case DisplayName:
                return WriteMask.DisplayName;
            case EventNotifier:
                return WriteMask.EventNotifier;
            case Executable:
                return WriteMask.Executable;
            case Historizing:
                return WriteMask.Historizing;
            case InverseName:
                return WriteMask.InverseName;
            case IsAbstract:
                return WriteMask.IsAbstract;
            case MinimumSamplingInterval:
                return WriteMask.MinimumSamplingInterval;
            case NodeClass:
                return WriteMask.NodeClass;
            case NodeId:
                return WriteMask.NodeId;
            case Symmetric:
                return WriteMask.Symmetric;
            case UserAccessLevel:
                return WriteMask.UserAccessLevel;
            case UserExecutable:
                return WriteMask.UserExecutable;
            case UserWriteMask:
                return WriteMask.UserWriteMask;
            case Value:
                return WriteMask.ValueForVariableType;
            case ValueRank:
                return WriteMask.ValueRank;
            case WriteMask:
                return WriteMask.WriteMask;

            default:
                throw new IllegalArgumentException("unknown AttributeId: " + attributeId);
        }
    }

    private static DataValue validateDataType(
        NodeManager<UaNode> nodeManager,
        NodeId dataType,
        DataValue value) throws UaException {

        Variant variant = value.getValue();
        if (variant == null) return value;

        Object o = variant.getValue();
        if (o == null) throw new UaException(StatusCodes.Bad_TypeMismatch);

        Class<?> valueClass = o.getClass().isArray() ?
            ArrayUtil.getType(o) : o.getClass();

        Class<?> expectedClass = getExpectedClass(nodeManager, dataType, valueClass);

        if (expectedClass != null) {
            LOGGER.debug(
                "dataTypeId={}, valueClass={}, expectedClass={}",
                dataType, valueClass.getSimpleName(), expectedClass.getSimpleName());

            if (!expectedClass.isAssignableFrom(valueClass)) {
                // Writing a ByteString to a UByte[] is explicitly allowed by the spec.
                if (o instanceof ByteString && expectedClass == UByte.class) {
                    ByteString byteString = (ByteString) o;

                    return new DataValue(
                        new Variant(byteString.uBytes()),
                        value.getStatusCode(),
                        value.getSourceTime(),
                        value.getServerTime()
                    );
                } else if (expectedClass == Variant.class) {
                    // Allow writing anything to a Variant
                    return value;
                } else {
                    throw new UaException(StatusCodes.Bad_TypeMismatch);
                }
            }
        } else {
            throw new UaException(StatusCodes.Bad_TypeMismatch);
        }

        return value;
    }

    private static void validateArrayType(
        Integer valueRank,
        UInteger[] arrayDimensions,
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

                int[] nodeDimensions = Optional.ofNullable(arrayDimensions).map(uia -> {
                    int[] dims = new int[uia.length];
                    for (int i = 0; i < uia.length; i++) {
                        dims[i] = uia[i].intValue();
                    }
                    return dims;
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

    private static Class<?> getExpectedClass(
        NodeManager<UaNode> nodeManager,
        NodeId dataTypeId,
        Class<?> valueClass) throws UaException {

        if (TypeUtil.isBuiltin(dataTypeId)) {
            return TypeUtil.getBackingClass(dataTypeId);
        } else if (subtypeOf(nodeManager, dataTypeId, Identifiers.Structure)) {
            return ExtensionObject.class;
        } else if (subtypeOf(nodeManager, dataTypeId, Identifiers.Enumeration)) {
            return Integer.class;
        } else {
            NodeId superBuiltInType = findConcreteBuiltInSuperTypeId(nodeManager, dataTypeId);

            if (superBuiltInType != null) {
                // One of dataTypeId's supertypes is a concrete built-in
                // type; expect the same Class<?> as that built-in type.
                return TypeUtil.getBackingClass(superBuiltInType);
            } else {
                int valueDataTypeId = TypeUtil.getBuiltinTypeId(valueClass);

                if (valueDataTypeId > -1) {
                    // The value they sent us maps to a built-in type.
                    // If dataTypeId is a subtype of that built-in type,
                    // the expected class is the class of the value they sent.
                    NodeId builtInTypeId = new NodeId(0, valueDataTypeId);

                    if (dataTypeId.equals(builtInTypeId) ||
                        subtypeOf(nodeManager, builtInTypeId, dataTypeId)) {

                        return valueClass;
                    } else {
                        throw new UaException(StatusCodes.Bad_TypeMismatch);
                    }
                } else {
                    throw new UaException(StatusCodes.Bad_TypeMismatch);
                }
            }
        }
    }

    /**
     * @return {@code true} if {@code dataTypeId} is a subtype of {@code potentialSuperTypeId}.
     */
    private static boolean subtypeOf(NodeManager<UaNode> nodeManager, NodeId dataTypeId, NodeId potentialSuperTypeId) {
        UaNode dataTypeNode = nodeManager.get(dataTypeId);

        if (dataTypeNode != null) {
            NodeId superTypeId = getSuperTypeId(nodeManager, dataTypeId);

            if (superTypeId != null) {
                return superTypeId.equals(potentialSuperTypeId) ||
                    subtypeOf(nodeManager, superTypeId, potentialSuperTypeId);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Find the first concrete built-in supertype for {@code dataTypeId}, if one exists.
     *
     * @return the first concrete built-in supertype for {@code dataTypeId}, if one exists.
     */
    @Nullable
    private static NodeId findConcreteBuiltInSuperTypeId(NodeManager<UaNode> nodeManager, NodeId dataTypeId) {
        if (TypeUtil.isBuiltin(dataTypeId) && isConcrete(nodeManager, dataTypeId)) {
            return dataTypeId;
        } else {
            NodeId superTypeId = getSuperTypeId(nodeManager, dataTypeId);

            if (superTypeId != null) {
                return findConcreteBuiltInSuperTypeId(nodeManager, superTypeId);
            } else {
                return null;
            }
        }
    }

    @Nullable
    private static NodeId getSuperTypeId(NodeManager<UaNode> nodeManager, NodeId dataTypeId) {
        UaNode dataTypeNode = nodeManager.get(dataTypeId);

        if (dataTypeNode != null) {
            return dataTypeNode.getReferences()
                .stream()
                .filter(Reference.SUBTYPE_OF)
                .flatMap(r -> opt2stream(r.getTargetNodeId().local()))
                .findFirst()
                .orElse(null);
        } else {
            return null;
        }
    }

    private static boolean isAbstract(NodeManager<UaNode> nodeManager, NodeId dataTypeId) {
        UaNode node = nodeManager.get(dataTypeId);

        if (node instanceof DataTypeNode) {
            return ((DataTypeNode) node).getIsAbstract();
        } else {
            return false;
        }
    }

    private static boolean isConcrete(NodeManager<UaNode> nodeManager, NodeId dataTypeId) {
        return !isAbstract(nodeManager, dataTypeId);
    }

}
