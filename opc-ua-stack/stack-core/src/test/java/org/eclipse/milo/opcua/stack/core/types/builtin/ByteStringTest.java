/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.builtin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class ByteStringTest {

    @Test
    public void testByteStringEquals() {
        ByteString bs1 = ByteString.of(new byte[]{1, 2, 3, 4});
        ByteString bs2 = ByteString.of(new byte[]{1, 2, 3, 4});
        ByteString bs3 = ByteString.of(new byte[]{1, 2, 3, 4, 5});

        assertEquals(bs2, bs1);

        assertNotEquals(bs1, bs3);
        assertNotEquals(bs2, bs3);
    }

}
