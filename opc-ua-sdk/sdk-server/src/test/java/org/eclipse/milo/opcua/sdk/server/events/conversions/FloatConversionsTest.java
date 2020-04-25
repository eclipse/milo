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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

import static org.eclipse.milo.opcua.sdk.server.events.conversions.AbstractConversionTest.ConversionType.EXPLICIT;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.AbstractConversionTest.ConversionType.IMPLICIT;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.AbstractConversionTest.ConversionType.NONE;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class FloatConversionsTest extends AbstractConversionTest<Float> {

    @Override
    protected Class<Float> getSourceClass() {
        return Float.class;
    }

    @Override
    public Conversion[] getConversions(BuiltinDataType targetType) {
        switch (targetType) {
            case Boolean: {
                return new Conversion[]{
                    c(0.0f, Boolean.FALSE),
                    c(1.0f, Boolean.TRUE)
                };
            }

            case Byte: {
                return new Conversion[]{
                    c(0.0f, ubyte(0)),
                    c(UByte.MIN.floatValue(), UByte.MIN),
                    c(UByte.MAX.floatValue(), UByte.MAX),
                    f(UByte.MIN.floatValue() - 1, targetType, ConversionUnderflowException.class),
                    f(UByte.MAX.floatValue() + 1, targetType, ConversionOverflowException.class)
                };
            }

            case Double: {
                return new Conversion[]{
                    c(0.0f, 0.0d),
                    c(Float.MIN_VALUE, (double) Float.MIN_VALUE),
                    c(Float.MAX_VALUE, (double) Float.MAX_VALUE)
                };
            }

            case Int16: {
                return new Conversion[]{
                    c(0.0f, (short) 0),
                    c(0.4f, (short) 0),
                    c(0.5f, (short) 1),
                    c((float) Short.MIN_VALUE, Short.MIN_VALUE),
                    c((float) Short.MAX_VALUE, Short.MAX_VALUE),
                    f((float) (Short.MIN_VALUE - 1), targetType, ConversionUnderflowException.class),
                    f((float) (Short.MAX_VALUE + 1), targetType, ConversionOverflowException.class)
                };
            }

            case Int32: {
                return new Conversion[]{
                    c(0.0f, 0),
                    c(0.4f, 0),
                    c(0.5f, 1),
                    c((float) Integer.MIN_VALUE, Integer.MIN_VALUE),
                    c((float) Integer.MAX_VALUE, Integer.MAX_VALUE),
                };
            }

            case Int64: {
                return new Conversion[]{
                    c(0.0f, 0L),
                    c(0.4f, 0L),
                    c(0.5f, 1L)
                };
            }

            case SByte: {
                return new Conversion[]{
                    c(0.0f, (byte) 0),
                    c(0.4f, (byte) 0),
                    c(0.5f, (byte) 1),
                    c((float) Byte.MIN_VALUE, Byte.MIN_VALUE),
                    c((float) Byte.MAX_VALUE, Byte.MAX_VALUE),
                    f((float) (Byte.MIN_VALUE - 1), targetType, ConversionUnderflowException.class),
                    f((float) (Byte.MAX_VALUE + 1), targetType, ConversionOverflowException.class)
                };
            }

            case String: {
                return new Conversion[]{
                    c(0.0f, "0.0"),
                    c(0.4f, "0.4"),
                    c(0.5f, "0.5"),
                    c(1.0f, "1.0")
                };
            }

            case UInt16: {
                return new Conversion[]{
                    c(0.0f, ushort(0)),
                    c(0.4f, ushort(0)),
                    c(0.5f, ushort(1)),
                    c(UShort.MIN.floatValue(), UShort.MIN),
                    c(UShort.MAX.floatValue(), UShort.MAX),
                    f(UShort.MIN.floatValue() - 1, targetType, ConversionUnderflowException.class),
                    f(UShort.MAX.floatValue() + 1, targetType, ConversionOverflowException.class)
                };
            }

            case UInt32: {
                return new Conversion[]{
                    c(0.0f, uint(0)),
                    c(0.4f, uint(0)),
                    c(0.5f, uint(1)),
                    f(-1.0f, targetType, ConversionUnderflowException.class)
                };
            }

            case UInt64: {
                return new Conversion[]{
                    c(0.0f, ulong(0)),
                    c(0.4f, ulong(0)),
                    c(0.5f, ulong(1)),
                    f(-1.0f, targetType, ConversionUnderflowException.class)
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
            case Boolean:       return EXPLICIT;
            case Byte:          return EXPLICIT;
            case Double:        return IMPLICIT;
            case Int16:         return EXPLICIT;
            case Int32:         return EXPLICIT;
            case Int64:         return EXPLICIT;
            case SByte:         return EXPLICIT;
            case String:        return EXPLICIT;
            case UInt16:        return EXPLICIT;
            case UInt32:        return EXPLICIT;
            case UInt64:        return EXPLICIT;
            default:            return NONE;
        }
        //@formatter:on
    }

    @Override
    protected Object convert(
        Object fromValue,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionException {

        return FloatConversions.convert(fromValue, targetType, implicit);
    }

}
