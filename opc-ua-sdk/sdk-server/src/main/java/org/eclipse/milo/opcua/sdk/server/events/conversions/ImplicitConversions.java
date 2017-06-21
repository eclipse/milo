/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.server.events.conversions;

import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableTable;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public class ImplicitConversions {

    private static final ImmutableTable<BuiltinDataType, BuiltinDataType, Function> IMPLICIT_CONVERSIONS;

    static {
        IMPLICIT_CONVERSIONS = ImmutableTable.<BuiltinDataType, BuiltinDataType, Function>builder()
            .put(
                BuiltinDataType.Boolean, BuiltinDataType.Byte,
                (Function<Boolean, UByte>) BooleanConversions::booleanToByte)
            .put(
                BuiltinDataType.Boolean, BuiltinDataType.Double,
                (Function<Boolean, Double>) BooleanConversions::booleanToDouble)
            .put(
                BuiltinDataType.Boolean, BuiltinDataType.Float,
                (Function<Boolean, Float>) BooleanConversions::booleanToFloat)
            .put(
                BuiltinDataType.Boolean, BuiltinDataType.Int16,
                (Function<Boolean, Short>) BooleanConversions::booleanToInt16)
            .put(
                BuiltinDataType.Boolean, BuiltinDataType.Int32,
                (Function<Boolean, Integer>) BooleanConversions::booleanToInt32)
            .put(
                BuiltinDataType.Boolean, BuiltinDataType.Int64,
                (Function<Boolean, Long>) BooleanConversions::booleanToInt64)
            .put(
                BuiltinDataType.Boolean, BuiltinDataType.SByte,
                (Function<Boolean, Byte>) BooleanConversions::booleanToSByte)
            .put(
                BuiltinDataType.Boolean, BuiltinDataType.UInt16,
                (Function<Boolean, UShort>) BooleanConversions::booleanToUInt16)
            .put(
                BuiltinDataType.Boolean, BuiltinDataType.UInt32,
                (Function<Boolean, UInteger>) BooleanConversions::booleanToUInt32)
            .put(
                BuiltinDataType.Boolean, BuiltinDataType.UInt64,
                (Function<Boolean, ULong>) BooleanConversions::booleanToUInt64)
            .build();
    }

    @Nullable
    public static Object convert(@Nonnull Object sourceValue, @Nonnull BuiltinDataType targetType) {
        BuiltinDataType sourceType = BuiltinDataType.fromBackingClass(sourceValue.getClass());

        if (sourceType == null) {
            return null;
        }

        Function conversion = IMPLICIT_CONVERSIONS.get(sourceType, targetType);

        if (conversion != null) {
            @SuppressWarnings("unchecked")
            Object targetValue = conversion.apply(sourceValue);

            return targetValue;
        } else {
            return null;
        }
    }

    public static int getPrecedence(@Nonnull BuiltinDataType dataType) {
        //@formatter:off
        switch (dataType) {
            case Double:            return 18;
            case Float:             return 17;
            case Int64:             return 16;
            case UInt64:            return 15;
            case Int32:             return 14;
            case UInt32:            return 13;
            case StatusCode:        return 12;
            case Int16:             return 11;
            case UInt16:            return 10;
            case SByte:             return 9;
            case Byte:              return 8;
            case Boolean:           return 7;
            case Guid:              return 6;
            case String:            return 5;
            case ExpandedNodeId:    return 4;
            case NodeId:            return 3;
            case LocalizedText:     return 2;
            case QualifiedName:     return 1;
            default:                return 0;
        }
        //@formatter:on
    }

}
