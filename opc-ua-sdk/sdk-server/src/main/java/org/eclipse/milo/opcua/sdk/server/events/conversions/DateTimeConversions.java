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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.annotation.Nonnull;

import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;

final class DateTimeConversions {

    private DateTimeConversions() {}

    @Nonnull
    static String dateTimeToString(@Nonnull DateTime dt) {
        return dateToIso8601UtcString(dt.getJavaDate());
    }

    private static final DateFormat ISO_8601_UTC_DATE_FORMAT;

    static {
        ISO_8601_UTC_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        ISO_8601_UTC_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private static String dateToIso8601UtcString(Date date) {
        synchronized (ISO_8601_UTC_DATE_FORMAT) {
            return ISO_8601_UTC_DATE_FORMAT.format(date);
        }
    }

}
