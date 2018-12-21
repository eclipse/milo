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

package org.eclipse.milo.opcua.stack.core.types.builtin;

import java.time.Instant;
import java.util.Date;

import com.google.common.base.MoreObjects;

public final class DateTime {

    public static final DateTime MIN_VALUE = new DateTime(0L);

    /**
     * The delta in 100 nanosecond intervals between Java epoch (January 1, 1970) and UTC epoch (Jan 1, 1601).
     */
    private static final long EPOCH_DELTA = 116444736000000000L;

    private final long utcTime;

    public DateTime() {
        this(javaToUtc(System.currentTimeMillis()));
    }

    public DateTime(long utcTime) {
        this.utcTime = utcTime;
    }

    public DateTime(Date date) {
        this(javaToUtc(date.getTime()));
    }

    public DateTime(Instant instant) {
        this(javaToUtc(instant.toEpochMilli()) + (instant.getNano() % 1_000_000) / 100);
    }

    /**
     * @return this time as 100 nanosecond intervals since UTC epoch.
     */
    public long getUtcTime() {
        return utcTime;
    }

    /**
     * @return this time as milliseconds since Java epoch.
     */
    public long getJavaTime() {
        return utcToJava(utcTime);
    }

    /**
     * @return this time as a {@link Date}.
     */
    public Date getJavaDate() {
        return new Date(utcToJava(utcTime));
    }

    /**
     * @return this time as an {@link Instant}.
     */
    public Instant getJavaInstant() {
        return Instant.ofEpochSecond(utcToJava(utcTime) / 1_000, (utcTime % 10_000_000) * 100);
    }

    public boolean isNull() {
        return utcTime == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateTime dateTime = (DateTime) o;

        return utcTime == dateTime.utcTime;
    }

    @Override
    public int hashCode() {
        return (int) (utcTime ^ (utcTime >>> 32));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("utcTime", utcTime)
            .add("javaDate", getJavaDate())
            .toString();
    }

    /**
     * @return a {@link DateTime} initialized to now.
     */
    public static DateTime now() {
        return new DateTime();
    }

    /**
     * @return a {@link DateTime} initialized to now, considering nanoseconds
     */
    public static DateTime nowNanos() {
        return new DateTime(Instant.now());
    }

    private static long javaToUtc(long javaTime) {
        return (javaTime * 10_000) + EPOCH_DELTA;
    }

    private static long utcToJava(long utcTime) {
        return (utcTime - EPOCH_DELTA) / 10_000;
    }

}
