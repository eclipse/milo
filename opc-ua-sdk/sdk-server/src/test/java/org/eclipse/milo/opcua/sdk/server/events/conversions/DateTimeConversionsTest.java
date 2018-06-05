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

package org.eclipse.milo.opcua.sdk.server.events.conversions;

import java.util.Calendar;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class DateTimeConversionsTest {

    @Test
    public void testDateTimeToString() {
        DateTime now = DateTime.now();
        String nowAsString = (String) DateTimeConversions
            .convert(now, BuiltinDataType.String, false);

        System.out.println("now: " + now);
        System.out.println("nowAsString: " + nowAsString);

        assertNotNull(nowAsString);

        DateTime nowAsStringAsDateTime = (DateTime) StringConversions
            .convert(nowAsString, BuiltinDataType.DateTime, false);

        System.out.println("nowAsStringAsDateTime: " + nowAsStringAsDateTime);

        assertNotNull(nowAsStringAsDateTime);

        Calendar c1 = Calendar.getInstance();
        c1.setTime(nowAsStringAsDateTime.getJavaDate());

        Calendar c2 = Calendar.getInstance();
        c2.setTime(now.getJavaDate());

        assertEquals(c1.get(Calendar.YEAR), c2.get(Calendar.YEAR));
        assertEquals(c1.get(Calendar.MONTH), c2.get(Calendar.MONTH));
        assertEquals(c1.get(Calendar.DAY_OF_MONTH), c2.get(Calendar.DAY_OF_MONTH));
        assertEquals(c1.get(Calendar.HOUR_OF_DAY), c2.get(Calendar.HOUR_OF_DAY));
        assertEquals(c1.get(Calendar.MINUTE), c2.get(Calendar.MINUTE));
        assertEquals(c1.get(Calendar.SECOND), c2.get(Calendar.SECOND));
    }

}
