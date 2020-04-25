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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;
import static org.testng.Assert.fail;

abstract class AbstractConversionTest<S> {

//    @Test
//    public void testNullConversion() throws ConversionException {
//        for (BuiltinDataType targetType : BuiltinDataType.values()) {
//            assertNull(convert(null, targetType, true));
//            assertNull(convert(null, targetType, false));
//        }
//    }

    @Test
    public void testAllConversions() throws ConversionException {
        for (BuiltinDataType targetType : BuiltinDataType.values()) {
            Conversion[] conversions = getConversions(targetType);
            ConversionType conversionType = getConversionType(targetType);
            BuiltinDataType sourceType = BuiltinDataType.fromBackingClass(getSourceClass());

            if (conversionType != ConversionType.NONE) {
                if (conversions == null || conversions.length == 0) {
                    fail("expected conversions for " + targetType);
                }

                System.out.println(String.format("%s -> %s [%s]", sourceType, targetType, conversionType));

                for (Conversion conversion : conversions) {
                    assertEquals(targetType, conversion.targetType);

                    S fromValue = getSourceClass().cast(conversion.fromValue);

                    if (conversion instanceof ConversionSuccess) {
                        Object expectedValue = ((ConversionSuccess) conversion).expectedValue;

                        Object convertedValue = convert(fromValue, targetType, conversionType == ConversionType.IMPLICIT);

                        System.out.println(String.format("\tfromValue=%s targetValue=%s", fromValue, expectedValue));

                        assertEquals(convertedValue, expectedValue);
                    } else if (conversion instanceof ConversionFailure) {
                        assertThrows(
                            ((ConversionFailure) conversion).expectedFailure,
                            () -> convert(fromValue, targetType, conversionType == ConversionType.IMPLICIT)
                        );
                    }
                }
            } else {
                if (conversions.length != 0) {
                    fail(String.format(
                        "conversions defined for %s -> %s " +
                            "but no ConversionType is defined", sourceType, targetType));
                }
            }
        }
    }

    @Test
    public void testExplicitConversionsCalledImplicitlyAreNull() {
        for (BuiltinDataType targetType : BuiltinDataType.values()) {
            Conversion[] conversions = getConversions(targetType);

            for (Conversion conversion : conversions) {
                ConversionType conversionType = getConversionType(conversion.targetType);

                if (conversionType == ConversionType.EXPLICIT) {
                    S fromValue = getSourceClass().cast(conversion.fromValue);

                    assertThrows(
                        ConversionNotDefinedException.class,
                        () ->
                            convert(fromValue, targetType, true)
                    );
                }
            }
        }
    }

    @Test
    public void testImplicitConversionsCalledExplicitly() throws ConversionException {
        for (BuiltinDataType targetType : BuiltinDataType.values()) {
            Conversion[] conversions = getConversions(targetType);

            for (Conversion conversion : conversions) {
                ConversionType conversionType = getConversionType(conversion.targetType);

                if (conversionType == ConversionType.IMPLICIT) {
                    S fromValue = getSourceClass().cast(conversion.fromValue);

                    if (conversion instanceof ConversionSuccess) {
                        Object expectedValue = ((ConversionSuccess) conversion).expectedValue;
                        Object convertedValue = convert(fromValue, targetType, false);

                        System.out.println(String.format(
                            "[%s] fromValue=%s targetType=%s targetValue=%s",
                            conversionType, fromValue, targetType, expectedValue));

                        assertEquals(convertedValue, expectedValue);
                    } else if (conversion instanceof ConversionFailure) {
                        assertThrows(
                            ((ConversionFailure) conversion).expectedFailure,
                            () -> convert(fromValue, targetType, true)
                        );
                    }
                }
            }
        }
    }

    protected ConversionSuccess c(@Nonnull S fromValue, @Nonnull Object targetValue) {
        return new ConversionSuccess(
            fromValue,
            BuiltinDataType.fromBackingClass(targetValue.getClass()),
            targetValue
        );
    }

    protected ConversionSuccess c(
        @Nonnull S fromValue,
        @Nullable Object targetValue,
        @Nonnull BuiltinDataType targetType
    ) {

        return new ConversionSuccess(fromValue, targetType, targetValue);
    }

    protected ConversionFailure f(
        @Nonnull S fromValue,
        @Nonnull BuiltinDataType targetType,
        Class<? extends ConversionException> expectedFailure
    ) {

        return new ConversionFailure(
            fromValue,
            targetType,
            expectedFailure
        );
    }

    protected abstract Class<S> getSourceClass();

    public abstract Conversion[] getConversions(BuiltinDataType targetType);

    public abstract ConversionType getConversionType(BuiltinDataType targetType);

    /*
    {
        //@formatter:off
        switch (targetType) {
            case Boolean:   return ConversionType.IMPLICIT;
            case Byte:      return ConversionType.IMPLICIT;
            case Float:     return ConversionType.IMPLICIT;
            case Double:    return ConversionType.IMPLICIT;
            case Int16:     return ConversionType.IMPLICIT;
            case Int32:     return ConversionType.IMPLICIT;
            case Int64:     return ConversionType.IMPLICIT;
            case SByte:     return ConversionType.IMPLICIT;
            case String:    return ConversionType.EXPLICIT;
            case UInt16:    return ConversionType.IMPLICIT;
            case UInt32:    return ConversionType.IMPLICIT;
            case UInt64:    return ConversionType.IMPLICIT;
            default:        return ConversionType.NONE;
        }
        //@formatter:on
    }
    */

    // protected abstract Conversion[] getExplicitConversions();

    // protected abstract Conversion[] getImplicitConversions();

    protected abstract Object convert(
        Object fromValue,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionException;

    static abstract class Conversion {
        Object fromValue;
        BuiltinDataType targetType;

        public Conversion(Object fromValue, BuiltinDataType targetType) {
            this.fromValue = fromValue;
            this.targetType = targetType;
        }
    }

    static class ConversionSuccess extends Conversion {
        Object expectedValue;

        public ConversionSuccess(Object fromValue, BuiltinDataType targetType, Object expectedValue) {
            super(fromValue, targetType);

            this.expectedValue = expectedValue;
        }
    }

    static class ConversionFailure extends Conversion {
        Class<? extends ConversionException> expectedFailure;

        public ConversionFailure(
            Object fromValue,
            BuiltinDataType targetType,
            Class<? extends ConversionException> expectedFailure
        ) {

            super(fromValue, targetType);

            this.expectedFailure = expectedFailure;
        }
    }

    enum ConversionType {
        EXPLICIT,
        IMPLICIT,
        NONE
    }

}
