/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.stack.core.types.builtin;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DateTimeTest {

    @Test
    public void getJavaInstant() {
        ZonedDateTime inputZdt = LocalDateTime
            .of(2020, Month.JANUARY, 10, 20, 30, 40, 123_456_789)
            .atZone(ZoneId.systemDefault());

        Instant endInstant = inputZdt.toInstant();

        DateTime dateTime = new DateTime(endInstant);
        Instant outputInstant = dateTime.getJavaInstant();

        ZonedDateTime outputZdt = ZonedDateTime.ofInstant(outputInstant, ZoneId.systemDefault());

        assertEquals(outputZdt.getYear(), inputZdt.getYear());
        assertEquals(outputZdt.getMonth(), inputZdt.getMonth());
        assertEquals(outputZdt.getDayOfMonth(), inputZdt.getDayOfMonth());
        assertEquals(outputZdt.getHour(), inputZdt.getHour());
        assertEquals(outputZdt.getMinute(), inputZdt.getMinute());
        assertEquals(outputZdt.getSecond(), inputZdt.getSecond());
        assertEquals(outputZdt.getNano(), 123_456_700);
    }

}
