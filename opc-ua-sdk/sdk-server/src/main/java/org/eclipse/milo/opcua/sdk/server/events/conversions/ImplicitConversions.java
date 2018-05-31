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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;

public class ImplicitConversions {

    @Nullable
    public static Object convert(@Nonnull Object sourceValue, @Nonnull BuiltinDataType targetType) {
        BuiltinDataType sourceType = BuiltinDataType.fromBackingClass(sourceValue.getClass());

        if (sourceType == null) {
            return null;
        }

        if (!sourceType.getBackingClass().isAssignableFrom(sourceValue.getClass())) {
            return null;
        }

        return convert(sourceValue, sourceType, targetType);
    }

    private static Object convert(
        @Nonnull Object sourceValue,
        BuiltinDataType sourceType,
        BuiltinDataType targetType) {

        switch (sourceType) {
            case Boolean:
                return BooleanConversions
                    .convert(sourceValue, targetType, true);
            case Byte:
                return ByteConversions
                    .convert(sourceValue, targetType, true);

            default:
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
