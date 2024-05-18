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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

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

        assertEquals(inputZdt.getYear(), outputZdt.getYear());
        assertEquals(inputZdt.getMonth(), outputZdt.getMonth());
        assertEquals(inputZdt.getDayOfMonth(), outputZdt.getDayOfMonth());
        assertEquals(inputZdt.getHour(), outputZdt.getHour());
        assertEquals(inputZdt.getMinute(), outputZdt.getMinute());
        assertEquals(inputZdt.getSecond(), outputZdt.getSecond());
        assertEquals(123_456_700, outputZdt.getNano());
    }

}
