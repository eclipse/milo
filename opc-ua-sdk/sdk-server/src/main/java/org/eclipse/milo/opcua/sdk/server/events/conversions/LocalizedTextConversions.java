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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

final class LocalizedTextConversions {

    private LocalizedTextConversions() {}

    static String localizedTextToString(LocalizedText text) {
        return text.getText();
    }

    static Object convert(
        Object value,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionNotDefinedException {

        if (value instanceof LocalizedText) {
            LocalizedText text = (LocalizedText) value;

            return implicit ?
                implicitConversion(text, targetType) :
                explicitConversion(text, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(
        LocalizedText text,
        BuiltinDataType targetType
    ) throws ConversionNotDefinedException {

        return implicitConversion(text, targetType);
    }

    static Object implicitConversion(
        LocalizedText text,
        BuiltinDataType targetType
    ) throws ConversionNotDefinedException {

        //@formatter:off
        switch (targetType) {
            case String:    return localizedTextToString(text);
            default:        throw new ConversionNotDefinedException(BuiltinDataType.LocalizedText, targetType);
        }
        //@formatter:on
    }

}
