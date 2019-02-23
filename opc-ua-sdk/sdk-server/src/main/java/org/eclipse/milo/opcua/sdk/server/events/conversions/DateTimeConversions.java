/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.events.conversions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;

final class DateTimeConversions {

    private DateTimeConversions() {}

    @Nonnull
    static String dateTimeToString(@Nonnull DateTime dt) {
        return dateToIso8601UtcString(dt.getJavaDate());
    }

    private static final DateFormat ISO_8601_UTC_DATE_FORMAT;

    static {
        ISO_8601_UTC_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        ISO_8601_UTC_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private static String dateToIso8601UtcString(Date date) {
        synchronized (ISO_8601_UTC_DATE_FORMAT) {
            return ISO_8601_UTC_DATE_FORMAT.format(date);
        }
    }

    @Nullable
    static Object convert(@Nonnull Object o, BuiltinDataType targetType, boolean implicit) {
        if (o instanceof DateTime) {
            DateTime d = (DateTime) o;

            return implicit ?
                implicitConversion(d, targetType) :
                explicitConversion(d, targetType);
        } else {
            return null;
        }
    }

    @Nullable
    static Object explicitConversion(@Nonnull DateTime d, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case String:    return dateTimeToString(d);
            default:        return implicitConversion(d, targetType);
        }
        //@formatter:on
    }

    @Nullable
    static Object implicitConversion(@Nonnull DateTime d, BuiltinDataType targetType) {
        // no implicit conversions exist
        return null;
    }


}
