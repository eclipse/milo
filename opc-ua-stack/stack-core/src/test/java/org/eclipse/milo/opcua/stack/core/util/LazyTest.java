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

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class LazyTest {

    @Test
    public void lazyRetainsNonNullValue() {
        Lazy<Object> lazy = new Lazy<>();

        Object instance = new Object();

        Object o1 = lazy.getOrCompute(() -> instance);
        assertEquals(o1, instance);

        Object o2 = lazy.getOrCompute(() -> instance);
        assertEquals(o2, instance);
    }

    @Test
    public void lazyRetainsNullValue() {
        Lazy<Object> lazy = new Lazy<>();

        Object o1 = lazy.getOrCompute(() -> null);

        assertNull(o1);
    }

    @Test
    public void lazyOnlyComputesOnce() {
        Lazy<Object> lazy = new Lazy<>();

        final Object instance = new Object();

        Supplier<Object> supplier = new Supplier<Object>() {
            final AtomicInteger count = new AtomicInteger(0);

            @Override
            public Object get() {
                if (count.incrementAndGet() != 1) {
                    throw new IllegalStateException();
                } else {
                    return instance;
                }
            }
        };

        Object o1 = lazy.getOrCompute(supplier);
        assertEquals(o1, instance);

        Object o2 = lazy.getOrCompute(supplier);
        assertEquals(o2, instance);
    }

    @Test
    public void lazyIsResettable() {
        Lazy<Object> lazy = new Lazy<>();

        final Object instance1 = new Object();
        final Object instance2 = new Object();

        Supplier<Object> supplier = new Supplier<Object>() {
            final AtomicInteger count = new AtomicInteger(0);

            @Override
            public Object get() {
                switch (count.incrementAndGet()) {
                    case 1:
                        return instance1;
                    case 2:
                    default:
                        return instance2;
                }
            }
        };

        Object o1 = lazy.getOrCompute(supplier);
        assertEquals(o1, instance1);

        lazy.reset();

        Object o2 = lazy.getOrCompute(supplier);
        assertEquals(o2, instance2);
    }

}
