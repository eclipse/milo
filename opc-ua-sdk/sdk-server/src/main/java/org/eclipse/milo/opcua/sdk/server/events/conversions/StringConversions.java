/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.events.conversions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

final class StringConversions {

    private static final DateFormat ISO_8601_UTC_DATE_FORMAT;

    static {
        ISO_8601_UTC_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        ISO_8601_UTC_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private StringConversions() {}

    static Boolean stringToBoolean(String s) throws ConversionFailedException {
        if (s.equalsIgnoreCase("true") || s.equals("1")) {
            return true;
        } else if (s.equalsIgnoreCase("false") || s.equals("0")) {
            return false;
        } else {
            throw new ConversionFailedException(BuiltinDataType.String, BuiltinDataType.Boolean);
        }
    }

    static UByte stringToByte(String s) throws ConversionFailedException {
        try {
            return UByte.valueOf(s);
        } catch (NumberFormatException e) {
            throw new ConversionFailedException(BuiltinDataType.String, BuiltinDataType.Byte, e);
        }
    }

    static DateTime stringToDateTime(String s) throws ConversionFailedException {
        try {
            Date date = iso8601UtcStringToDate(s);

            return new DateTime(date);
        } catch (ParseException e) {
            throw new ConversionFailedException(BuiltinDataType.String, BuiltinDataType.DateTime, e);
        }
    }

    static Double stringToDouble(String s) throws ConversionFailedException {
        try {
            return Double.valueOf(s);
        } catch (NumberFormatException e) {
            throw new ConversionFailedException(BuiltinDataType.String, BuiltinDataType.Double, e);
        }
    }

    static ExpandedNodeId stringToExpandedNodeId(String s) throws ConversionFailedException {
        try {
            return ExpandedNodeId.parse(s);
        } catch (Throwable t) {
            throw new ConversionFailedException(BuiltinDataType.String, BuiltinDataType.ExpandedNodeId, t);
        }
    }

    static Float stringToFloat(String s) throws ConversionFailedException {
        try {
            return Float.valueOf(s);
        } catch (NumberFormatException e) {
            throw new ConversionFailedException(BuiltinDataType.String, BuiltinDataType.Float, e);
        }
    }

    static UUID stringToGuid(String s) throws ConversionFailedException {
        try {
            return UUID.fromString(s);
        } catch (IllegalArgumentException e) {
            throw new ConversionFailedException(BuiltinDataType.String, BuiltinDataType.Guid, e);
        }
    }

    static Short stringToInt16(String s) throws ConversionFailedException {
        try {
            return Short.valueOf(s);
        } catch (NumberFormatException e) {
            throw new ConversionFailedException(BuiltinDataType.String, BuiltinDataType.Int16, e);
        }
    }

    static Integer stringToInt32(String s) throws ConversionFailedException {
        try {
            return Integer.valueOf(s);
        } catch (NumberFormatException e) {
            throw new ConversionFailedException(BuiltinDataType.String, BuiltinDataType.Int32, e);
        }
    }

    static Long stringToInt64(String s) throws ConversionFailedException {
        try {
            return Long.valueOf(s);
        } catch (NumberFormatException e) {
            throw new ConversionFailedException(BuiltinDataType.String, BuiltinDataType.Int64, e);
        }
    }

    static NodeId stringToNodeId(String s) throws ConversionFailedException {
        try {
            return NodeId.parse(s);
        } catch (Throwable t) {
            throw new ConversionFailedException(BuiltinDataType.String, BuiltinDataType.NodeId, t);
        }
    }

    static Byte stringToSByte(String s) throws ConversionFailedException {
        try {
            return Byte.valueOf(s);
        } catch (NumberFormatException e) {
            throw new ConversionFailedException(BuiltinDataType.String, BuiltinDataType.SByte, e);
        }
    }

    static LocalizedText stringToLocalizedText(String s) {
        return new LocalizedText("", s);
    }

    static QualifiedName stringToQualifiedName(String s) {
        return new QualifiedName(0, s);
    }

    static UShort stringToUInt16(String s) throws ConversionFailedException {
        try {
            return UShort.valueOf(s);
        } catch (NumberFormatException e) {
            throw new ConversionFailedException(BuiltinDataType.String, BuiltinDataType.UInt16, e);
        }
    }

    static UInteger stringToUInt32(String s) throws ConversionFailedException {
        try {
            return UInteger.valueOf(s);
        } catch (NumberFormatException e) {
            throw new ConversionFailedException(BuiltinDataType.String, BuiltinDataType.UInt32, e);
        }
    }

    static ULong stringToUInt64(String s) throws ConversionFailedException {
        try {
            return ULong.valueOf(s);
        } catch (NumberFormatException e) {
            throw new ConversionFailedException(BuiltinDataType.String, BuiltinDataType.UInt64, e);
        }
    }

    private static Date iso8601UtcStringToDate(String s) throws ParseException {
        synchronized (ISO_8601_UTC_DATE_FORMAT) {
            return ISO_8601_UTC_DATE_FORMAT.parse(s);
        }
    }

    static Object convert(
        Object value,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionNotDefinedException, ConversionFailedException {

        if (value instanceof String) {
            String s = (String) value;

            return implicit ?
                implicitConversion(s, targetType) :
                explicitConversion(s, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(
        String s,
        BuiltinDataType targetType
    ) throws ConversionFailedException, ConversionNotDefinedException {

        //@formatter:off
        switch (targetType) {
            case DateTime:          return stringToDateTime(s);
            case ExpandedNodeId:    return stringToExpandedNodeId(s);
            case NodeId:            return stringToNodeId(s);
            case LocalizedText:     return stringToLocalizedText(s);
            case QualifiedName:     return stringToQualifiedName(s);
            default:                return implicitConversion(s, targetType);
        }
        //@formatter:on
    }

    static Object implicitConversion(
        String s,
        BuiltinDataType targetType
    ) throws ConversionNotDefinedException, ConversionFailedException {

        //@formatter:off
        switch (targetType) {
            case Boolean:   return stringToBoolean(s);
            case Byte:      return stringToByte(s);
            case Double:    return stringToDouble(s);
            case Float:     return stringToFloat(s);
            case Guid:      return stringToGuid(s);
            case Int16:     return stringToInt16(s);
            case Int32:     return stringToInt32(s);
            case Int64:     return stringToInt64(s);
            case SByte:     return stringToSByte(s);
            case UInt16:    return stringToUInt16(s);
            case UInt32:    return stringToUInt32(s);
            case UInt64:    return stringToUInt64(s);
            default:        throw new ConversionNotDefinedException(BuiltinDataType.String, targetType);
        }
        //@formatter:on
    }

}
