/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
