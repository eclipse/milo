/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.subscriptions;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaSubscription.ClientHandleSequence;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ClientHandleSequenceTest {

    @Test
    public void testRollover() {
        ClientHandleSequence sequence = new ClientHandleSequence(h -> false, UInteger.MAX_VALUE - 1);

        assertEquals(sequence.nextClientHandle(), uint(UInteger.MAX_VALUE - 1));
        assertEquals(sequence.nextClientHandle(), UInteger.MAX);
        assertEquals(sequence.nextClientHandle(), uint(0));
        assertEquals(sequence.nextClientHandle(), uint(1));
    }

    @Test
    public void testInUsePredicate() {
        ClientHandleSequence sequence = new ClientHandleSequence(h -> h.longValue() < 10);

        assertEquals(sequence.nextClientHandle(), uint(10));
    }

    // Slow; enable to test manually.
    @Test
    @Disabled
    public void testThrowsIfAllUsed() {
        ClientHandleSequence sequence = new ClientHandleSequence(h -> true);

        assertThrows(IllegalStateException.class, sequence::nextClientHandle);
    }

}