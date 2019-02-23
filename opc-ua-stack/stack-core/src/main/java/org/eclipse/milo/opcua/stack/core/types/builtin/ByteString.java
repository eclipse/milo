/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.builtin;

import java.io.Serializable;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;

public final class ByteString implements Serializable {

    private static final long serialVersionUID = 451472015617419665L;

    public static final ByteString NULL_VALUE = new ByteString(null);

    private final byte[] bytes;

    public ByteString(@Nullable byte[] bytes) {
        this.bytes = bytes;
    }

    public int length() {
        return bytes != null ? bytes.length : 0;
    }

    public boolean isNull() {
        return bytes == null;
    }

    public boolean isNullOrEmpty() {
        return bytes == null || bytes.length == 0;
    }

    public boolean isNotNull() {
        return bytes != null;
    }

    @Nullable
    public byte[] bytes() {
        return bytes;
    }

    @Nonnull
    public byte[] bytesOrElse(@Nonnull byte[] other) {
        return bytes != null ? bytes : other;
    }

    @Nonnull
    public byte[] bytesOrEmpty() {
        return bytesOrElse(new byte[0]);
    }

    @Nullable
    public UByte[] uBytes() {
        if (bytes == null) return null;

        UByte[] bs = new UByte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            bs[i] = Unsigned.ubyte(bytes[i]);
        }
        return bs;
    }

    @Nonnull
    public UByte[] uBytesOrElse(@Nonnull UByte[] other) {
        UByte[] ubs = uBytes();

        return ubs != null ? ubs : other;
    }

    @Nonnull
    public UByte[] uBytesOrEmpty() {
        return uBytesOrElse(new UByte[0]);
    }

    public byte byteAt(int index) {
        if (bytes == null) throw new IndexOutOfBoundsException("index=" + index);

        return bytes[index];
    }

    public UByte uByteAt(int index) {
        return Unsigned.ubyte(byteAt(index));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ByteString that = (ByteString) o;

        return Arrays.equals(bytes, that.bytes);
    }

    @Override
    public int hashCode() {
        return bytes != null ? Arrays.hashCode(bytes) : 0;
    }

    public static ByteString of(byte[] bs) {
        return new ByteString(bs);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("bytes", Arrays.toString(bytes))
            .toString();
    }

}
