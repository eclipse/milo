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

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public final class StatusCode {

    private static final long SEVERITY_MASK = 0xC0000000L;
    private static final long SEVERITY_GOOD = 0x00000000L;
    private static final long SEVERITY_UNCERTAIN = 0x40000000L;
    private static final long SEVERITY_BAD = 0x80000000L;

    public static final StatusCode GOOD = new StatusCode(SEVERITY_GOOD);
    public static final StatusCode BAD = new StatusCode(SEVERITY_BAD);
    public static final StatusCode UNCERTAIN = new StatusCode(SEVERITY_UNCERTAIN);

    private final long value;

    public StatusCode(long value) {
        this.value = value;
    }

    public StatusCode(int value) {
        this.value = value;
    }

    public StatusCode(UInteger value) {
        this.value = value.longValue();
    }

    public long getValue() {
        return value;
    }

    public boolean isGood() {
        return (value & SEVERITY_MASK) == SEVERITY_GOOD;
    }

    public boolean isBad() {
        return (value & SEVERITY_MASK) == SEVERITY_BAD;
    }

    public boolean isUncertain() {
        return (value & SEVERITY_MASK) == SEVERITY_UNCERTAIN;
    }

    /**
     * Set the DataValue InfoType and Overflow InfoBits.
     *
     * @return a new {@link StatusCode} DataValue and Overflow bits set.
     */
    public StatusCode withOverflow() {
        return new StatusCode(value | 0x480);
    }

    /**
     * Clear the DataValue InfoType and Overflow InfoBits.
     *
     * @return a new {@link StatusCode} with DataValue and Overflow bits cleared.
     */
    public StatusCode withoutOverflow() {
        return new StatusCode(value & ~0x480);
    }

    /**
     * @return {@code true} if DataValue and Overflow bits are set.
     */
    public boolean isOverflowSet() {
        return (value & 0x480) == 0x480;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusCode that = (StatusCode) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return (int) (value ^ (value >>> 32));
    }

    @Override
    public String toString() {
        ToStringHelper helper = MoreObjects.toStringHelper(this);

        StatusCodes.lookup(value).ifPresent(
            nameAndDesc -> helper.add("name", nameAndDesc[0]));

        helper.add("value", String.format("0x%08X", value));
        helper.add("quality", quality(this));

        return helper.toString();
    }

    private static String quality(StatusCode statusCode) {
        if (statusCode.isGood()) {
            return "good";
        } else if (statusCode.isBad()) {
            return "bad";
        } else if (statusCode.isUncertain()) {
            return "uncertain";
        } else {
            return "unknown";
        }
    }

}
