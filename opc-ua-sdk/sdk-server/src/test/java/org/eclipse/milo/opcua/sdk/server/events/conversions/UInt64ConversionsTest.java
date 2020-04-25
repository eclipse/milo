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

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class UInt64ConversionsTest extends AbstractConversionTest<ULong> {

    @Override
    protected Class<ULong> getSourceClass() {
        return ULong.class;
    }

    @Override
    public Conversion[] getConversions(BuiltinDataType targetType) {
        switch (targetType) {
            case Boolean: {
                return new Conversion[]{
                    c(ulong(0), Boolean.FALSE),
                    c(ulong(1), Boolean.TRUE)
                };
            }

            case Byte: {
                return new Conversion[]{
                    c(ulong(0), ubyte(0)),
                    c(ulong(UByte.MAX_VALUE), UByte.MAX),
                    f(ulong(UByte.MAX_VALUE + 1), targetType, ConversionOverflowException.class)
                };
            }

            case Double: {
                ULong dMax = ulong((long) Double.MAX_VALUE);
                return new Conversion[]{
                    c(ulong(0), (0d)),
                    c(dMax, dMax.doubleValue())
                };
            }

            case Float: {
                ULong fMax = ulong((long) Float.MAX_VALUE);
                return new Conversion[]{
                    c(ulong(0), 0f),
                    c(fMax, fMax.floatValue())
                };
            }

            case Int16: {
                return new Conversion[]{
                    c(ulong(0), (short) 0),
                    c(ulong(Short.MAX_VALUE), Short.MAX_VALUE),
                    f(ulong(Short.MAX_VALUE + 1), targetType, ConversionOverflowException.class)
                };
            }

            case Int32: {
                return new Conversion[]{
                    c(ulong(0), 0),
                    c(ulong(Integer.MAX_VALUE), Integer.MAX_VALUE),
                    f(ulong(Integer.MAX_VALUE + 1L), targetType, ConversionOverflowException.class)
                };
            }

            case Int64: {
                return new Conversion[]{
                    c(ulong(0), 0L),
                    c(ulong(Long.MAX_VALUE), Long.MAX_VALUE),
                    f(ULong.MAX, targetType, ConversionOverflowException.class)
                };
            }

            case SByte: {
                return new Conversion[]{
                    c(ulong(0), (byte) 0),
                    c(ulong(Byte.MAX_VALUE), Byte.MAX_VALUE),
                    f(ulong(Byte.MAX_VALUE + 1), targetType, ConversionOverflowException.class)
                };
            }

            case StatusCode: {
                return new Conversion[]{
                    c(ulong(0), new StatusCode(0x00000000)),
                    c(ulong(0xABCD), new StatusCode(0x0000ABCD)),
                    c(ulong(0xFFFFFFFFL), new StatusCode(0xFFFFFFFF)),
                    f(ULong.MAX, targetType, ConversionOverflowException.class)
                };
            }

            case String: {
                return new Conversion[]{
                    c(ULong.MIN, ULong.MIN.toString()),
                    c(ULong.MAX, ULong.MAX.toString())
                };
            }

            case UInt16: {
                return new Conversion[]{
                    c(ulong(0), ushort(0)),
                    c(ulong(UShort.MAX_VALUE), UShort.MAX),
                    f(ulong(UShort.MAX_VALUE + 1), targetType, ConversionOverflowException.class)
                };
            }

            case UInt32: {
                return new Conversion[]{
                    c(ulong(0), uint(0)),
                    c(ulong(UInteger.MAX_VALUE), UInteger.MAX),
                    f(ulong(UInteger.MAX_VALUE + 1), targetType, ConversionOverflowException.class)
                };
            }

            default:
                return new ConversionSuccess[0];
        }
    }

    @Override
    public ConversionType getConversionType(BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Boolean:       return ConversionType.EXPLICIT;
            case Byte:          return ConversionType.EXPLICIT;
            case Double:        return ConversionType.IMPLICIT;
            case Float:         return ConversionType.IMPLICIT;
            case Int16:         return ConversionType.EXPLICIT;
            case Int32:         return ConversionType.EXPLICIT;
            case Int64:         return ConversionType.IMPLICIT;
            case SByte:         return ConversionType.EXPLICIT;
            case StatusCode:    return ConversionType.EXPLICIT;
            case String:        return ConversionType.EXPLICIT;
            case UInt16:        return ConversionType.EXPLICIT;
            case UInt32:        return ConversionType.EXPLICIT;
            default:            return ConversionType.NONE;
        }
        //@formatter:on
    }

    @Override
    protected Object convert(
        Object fromValue,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionNotDefinedException, ConversionOverflowException {
        
        return UInt64Conversions.convert(fromValue, targetType, implicit);
    }

}
