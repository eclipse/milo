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

package org.eclipse.milo.opcua.stack.core.channel.headers;

import com.google.common.base.MoreObjects;
import io.netty.buffer.ByteBuf;
import org.eclipse.milo.opcua.stack.core.util.annotations.UInt32Primitive;

public class SymmetricSecurityHeader {

    public static final int SYMMETRIC_SECURITY_HEADER_SIZE = 4;

    @UInt32Primitive
    private final long tokenId;

    /**
     * @param tokenId A unique identifier for the SecureChannel SecurityToken used to secure the Message.
     */
    public SymmetricSecurityHeader(long tokenId) {
        this.tokenId = tokenId;
    }

    public long getTokenId() {
        return tokenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SymmetricSecurityHeader that = (SymmetricSecurityHeader) o;

        return tokenId == that.tokenId;
    }

    @Override
    public int hashCode() {
        return (int) (tokenId ^ (tokenId >>> 32));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("tokenId", tokenId)
            .toString();
    }

    public static ByteBuf encode(SymmetricSecurityHeader header, ByteBuf buffer) {
        buffer.writeInt((int) header.getTokenId());

        return buffer;
    }

    public static SymmetricSecurityHeader decode(ByteBuf buffer) {
        return new SymmetricSecurityHeader(buffer.readUnsignedInt());
    }

}
