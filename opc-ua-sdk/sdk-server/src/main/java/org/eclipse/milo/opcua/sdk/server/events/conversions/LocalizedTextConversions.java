/*
 * Copyright (c) 2018 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

final class LocalizedTextConversions {

    private LocalizedTextConversions() {}

    @Nullable
    static String localizedTextToString(@Nonnull LocalizedText text) {
        return text.getText();
    }

    @Nullable
    static Object convert(@Nonnull Object o, BuiltinDataType targetType, boolean implicit) {
        if (o instanceof LocalizedText) {
            LocalizedText text = (LocalizedText) o;

            return implicit ?
                implicitConversion(text, targetType) :
                explicitConversion(text, targetType);
        } else {
            return null;
        }
    }

    @Nullable
    static Object explicitConversion(@Nonnull LocalizedText text, BuiltinDataType targetType) {
        return implicitConversion(text, targetType);
    }

    @Nullable
    static Object implicitConversion(@Nonnull LocalizedText text, BuiltinDataType targetType) {
        //@formatter:off
        switch(targetType) {
            case String:    return localizedTextToString(text);
            default:        return null;
        }
        //@formatter:on
    }

}
