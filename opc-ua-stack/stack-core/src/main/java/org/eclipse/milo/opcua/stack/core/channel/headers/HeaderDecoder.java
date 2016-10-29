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

import com.google.common.primitives.Ints;
import io.netty.buffer.ByteBuf;
import org.eclipse.milo.opcua.stack.core.channel.messages.TcpMessageEncoder;

public interface HeaderDecoder {

    long PROTOCOL_VERSION = 0L;

    int HEADER_LENGTH = 8;
    int HEADER_LENGTH_INDEX = 4;

    /**
     * Get the message length from a {@link ByteBuf} containing a {@link TcpMessageEncoder}. The reader index will not
     * be advanced.
     *
     * @param buffer {@link ByteBuf} to extract from.
     * @return The message length, which includes the size of the header.
     */
    default int getMessageLength(ByteBuf buffer) {
        return Ints.checkedCast(buffer.getUnsignedInt(buffer.readerIndex() + HEADER_LENGTH_INDEX));
    }

}
