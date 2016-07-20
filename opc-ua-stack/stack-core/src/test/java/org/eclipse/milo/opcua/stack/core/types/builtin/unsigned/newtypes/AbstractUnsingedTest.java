/*
 * Copyright (c) 2016 Jens Reimann
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *  http://www.eclipse.org/org/documents/edl-v10.html.
 */
package org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.newtypes;

import org.testng.Assert;

public abstract class AbstractUnsingedTest {

    protected static <T extends UnsignedNumber & Comparable<T>> void assertCompare(final T a,
                                                                                   final T b,
                                                                                   final int compare) {
        final int result = a.compareTo(b);

        if (compare == 0) {
            Assert.assertTrue(result == 0);
        } else if (compare < 0) {
            Assert.assertTrue(result < 0);
        } else if (compare > 0) {
            Assert.assertTrue(result > 0);
        }
    }

}
