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
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class QualifiedNameConversions {

    private QualifiedNameConversions() {}

    @Nullable
    static String qualifiedNameToString(@NotNull QualifiedName name) {
        return name.getName();
    }

    @NotNull
    static LocalizedText qualifiedNameToLocalizedText(@NotNull QualifiedName name) {
        return new LocalizedText("", name.getName());
    }

    @Nullable
    static Object convert(@NotNull Object o, BuiltinDataType targetType, boolean implicit) {
        if (o instanceof QualifiedName) {
            QualifiedName name = (QualifiedName) o;

            return implicit ?
                implicitConversion(name, targetType) :
                explicitConversion(name, targetType);
        } else {
            return null;
        }
    }

    @Nullable
    static Object explicitConversion(@NotNull QualifiedName name, BuiltinDataType targetType) {
        return implicitConversion(name, targetType);
    }

    @Nullable
    static Object implicitConversion(@NotNull QualifiedName name, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case String:        return qualifiedNameToString(name);
            case LocalizedText: return qualifiedNameToLocalizedText(name);
            default:            return null;
        }
        //@formatter:on
    }

}
