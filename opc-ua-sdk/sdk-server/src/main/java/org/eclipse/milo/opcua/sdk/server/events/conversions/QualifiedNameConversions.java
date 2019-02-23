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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

final class QualifiedNameConversions {

    private QualifiedNameConversions() {}

    @Nullable
    static String qualifiedNameToString(@Nonnull QualifiedName name) {
        return name.getName();
    }

    @Nonnull
    static LocalizedText qualifiedNameToLocalizedText(@Nonnull QualifiedName name) {
        return new LocalizedText("", name.getName());
    }

    @Nullable
    static Object convert(@Nonnull Object o, BuiltinDataType targetType, boolean implicit) {
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
    static Object explicitConversion(@Nonnull QualifiedName name, BuiltinDataType targetType) {
        return implicitConversion(name, targetType);
    }

    @Nullable
    static Object implicitConversion(@Nonnull QualifiedName name, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case String:        return qualifiedNameToString(name);
            case LocalizedText: return qualifiedNameToLocalizedText(name);
            default:            return null;
        }
        //@formatter:on
    }

}
