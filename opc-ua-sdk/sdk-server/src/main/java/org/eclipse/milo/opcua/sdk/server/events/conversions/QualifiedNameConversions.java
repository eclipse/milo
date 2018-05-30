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

}
