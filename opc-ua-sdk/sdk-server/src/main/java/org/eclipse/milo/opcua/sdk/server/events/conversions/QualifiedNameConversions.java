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

import javax.annotation.Nullable;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

final class QualifiedNameConversions {

    private QualifiedNameConversions() {}

    @Nullable
    static String qualifiedNameToString(QualifiedName name) {
        return name.getName();
    }

    static LocalizedText qualifiedNameToLocalizedText(QualifiedName name) {
        return new LocalizedText("", name.getName());
    }

    @Nullable
    static Object convert(
        Object value,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionNotDefinedException {

        if (value instanceof QualifiedName) {
            QualifiedName name = (QualifiedName) value;

            return implicit ?
                implicitConversion(name, targetType) :
                explicitConversion(name, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    @Nullable
    static Object explicitConversion(
        QualifiedName name,
        BuiltinDataType targetType
    ) throws ConversionNotDefinedException {

        return implicitConversion(name, targetType);
    }

    @Nullable
    static Object implicitConversion(
        QualifiedName name,
        BuiltinDataType targetType
    ) throws ConversionNotDefinedException {

        //@formatter:off
        switch (targetType) {
            case String:        return qualifiedNameToString(name);
            case LocalizedText: return qualifiedNameToLocalizedText(name);
            default:            throw new ConversionNotDefinedException(BuiltinDataType.QualifiedName, targetType);
        }
        //@formatter:on
    }

}
