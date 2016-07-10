/*
 * Copyright (c) 2016 Kevin Herron
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

import java.util.concurrent.atomic.AtomicLong;

public class LongSequence {

    private final AtomicLong atomic;

    private final long low;
    private final long high;

    public LongSequence(long low, long high) {
        this.low = low;
        this.high = high;

        atomic = new AtomicLong(low);
    }

    /**
     * @return the current value in the sequence, followed by an increment.
     */
    public Long getAndIncrement() {
        while (true) {
            Long current = atomic.get();
            Long next = (current >= high ? low : current + 1);
            if (atomic.compareAndSet(current, next)) {
                return current;
            }
        }
    }

    /**
     * @return the current value in the sequence, without incrementing.
     */
    public Long get() {
        return atomic.get();
    }

}
