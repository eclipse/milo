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

}
