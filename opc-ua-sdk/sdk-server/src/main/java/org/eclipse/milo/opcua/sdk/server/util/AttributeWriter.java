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

import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.NumericRange;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.WriteMask;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.ServerNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.util.ArrayUtil;
import org.eclipse.milo.opcua.stack.core.util.TypeUtil;

import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.extract;
import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.getAccessLevels;
import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.getUserAccessLevels;
import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.getUserWriteMasks;
import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.getWriteMasks;

public class AttributeWriter {

    public static void writeAttribute(AttributeContext context,
                                      ServerNode node,
                                      AttributeId attributeId,
                                      DataValue value,
                                      @Nullable String indexRange) throws UaException {

        AttributeContext internalContext = new AttributeContext(context.getServer());

        if (attributeId == AttributeId.Value) {
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
                value = validateDataType(dataType.expanded(), value);
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

    static DataValue validateDataType(ExpandedNodeId dataType, DataValue value) throws UaException {
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

    static void validateArrayType(
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

}
