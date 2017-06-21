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

import java.util.UUID;
import javax.annotation.Nonnull;

import io.netty.buffer.ByteBufUtil;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;

final class ByteStringConversions {

    private ByteStringConversions() {}

    @Nonnull
    static UUID byteStringToGuid(@Nonnull ByteString bs) {
        return UUID.nameUUIDFromBytes(bs.bytesOrEmpty());
    }

    @Nonnull
    static String byteStringToString(@Nonnull ByteString bs) {
        return ByteBufUtil.hexDump(bs.bytesOrEmpty());
    }

}
