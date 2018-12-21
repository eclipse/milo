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

import static org.testng.Assert.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

import org.testng.annotations.Test;

public class DateTimeTest {

    @Test
    public void getJavaInstant() {
    	LocalDateTime end = LocalDateTime.of(2020, Month.JANUARY, 10, 20, 30, 40, 123_456_789);
    	Instant endInstant = end.toInstant(ZoneId.systemDefault().getRules().getOffset(Instant.now()));
        DateTime dateTime = new DateTime(endInstant);
        Instant outputInstant = dateTime.getJavaInstant();
        LocalDateTime outputLDT = LocalDateTime.ofInstant(outputInstant, ZoneId.systemDefault());
        assertEquals(outputLDT.getYear(), 2020);
        assertEquals(outputLDT.getMonth(), Month.JANUARY);
        assertEquals(outputLDT.getDayOfMonth(), 10);
        assertEquals(outputLDT.getHour(), 20);
        assertEquals(outputLDT.getMinute(), 30);
        assertEquals(outputLDT.getSecond(), 40);
        assertEquals(outputLDT.getNano(), 123_456_700);
    }

}