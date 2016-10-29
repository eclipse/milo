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

package org.eclipse.milo.opcua.sdk.core;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AccessLevelTest {

    @Test
    public void testGetMask() {
        for (AccessLevel accessLevel : AccessLevel.values()) {
            assertEquals(AccessLevel.getMask(accessLevel), accessLevel.getValue());
        }

        assertEquals(AccessLevel.getMask(AccessLevel.NONE), 0);

        assertEquals(
            AccessLevel.getMask(AccessLevel.READ_ONLY),
            AccessLevel.CurrentRead.getValue());

        assertEquals(
            AccessLevel.getMask(AccessLevel.READ_WRITE),
            AccessLevel.CurrentRead.getValue() | AccessLevel.CurrentWrite.getValue());

        assertEquals(
            AccessLevel.getMask(AccessLevel.HISTORY_READ_ONLY),
            AccessLevel.HistoryRead.getValue());

        assertEquals(
            AccessLevel.getMask(AccessLevel.HISTORY_READ_WRITE),
            AccessLevel.HistoryRead.getValue() | AccessLevel.HistoryWrite.getValue());
    }

    @Test
    public void testFromMask() {
        assertEquals(AccessLevel.fromMask(0), AccessLevel.NONE);

        assertEquals(
            AccessLevel.fromMask(
                AccessLevel.CurrentRead.getValue()),
            AccessLevel.READ_ONLY
        );

        assertEquals(
            AccessLevel.fromMask(
                AccessLevel.CurrentRead.getValue() | AccessLevel.CurrentWrite.getValue()),
            AccessLevel.READ_WRITE
        );

        assertEquals(
            AccessLevel.fromMask(
                AccessLevel.HistoryRead.getValue()),
            AccessLevel.HISTORY_READ_ONLY
        );

        assertEquals(
            AccessLevel.fromMask(
                AccessLevel.HistoryRead.getValue() | AccessLevel.HistoryWrite.getValue()),
            AccessLevel.HISTORY_READ_WRITE
        );
    }

}
