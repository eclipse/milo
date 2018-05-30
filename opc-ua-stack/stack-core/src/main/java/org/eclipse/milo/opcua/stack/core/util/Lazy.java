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

package org.eclipse.milo.opcua.stack.core.util;

import java.util.function.Supplier;
import javax.annotation.Nonnull;

/**
 * Thread-safe holder for a lazily-computed value.
 *
 * @param <T> the type of value computed and held.
 */
public final class Lazy<T> {

    private static final Object LAZY_NULL = new Object();

    private volatile Object value;

    @SuppressWarnings("unchecked")
    public T getOrCompute(@Nonnull Supplier<T> supplier) {
        final Object v1 = value;

        if (v1 == LAZY_NULL) {
            return null;
        } else {
            if (v1 == null) {
                Object v2 = maybeCompute(supplier);
                return v2 == LAZY_NULL ? null : (T) v2;
            } else {
                return (T) v1;
            }
        }
    }

    private synchronized Object maybeCompute(Supplier<T> supplier) {
        if (value == null) {
            T supplied = supplier.get();
            value = supplied != null ? supplied : LAZY_NULL;
        }

        return value;
    }

}
