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

package org.eclipse.milo.opcua.stack.core.channel.messages;

import io.netty.buffer.ByteBuf;
import org.eclipse.milo.opcua.stack.core.UaException;

public class TcpMessageDecoder {

    public static HelloMessage decodeHello(ByteBuf buffer) throws UaException {
        MessageType messageType = MessageType.fromMediumInt(buffer.readMedium());
        char chunkType = (char) buffer.readByte();
        buffer.skipBytes(4); // length

        assert (messageType == MessageType.Hello && chunkType == 'F');

        return HelloMessage.decode(buffer);
    }

    public static AcknowledgeMessage decodeAcknowledge(ByteBuf buffer) throws UaException {
        MessageType messageType = MessageType.fromMediumInt(buffer.readMedium());
        char chunkType = (char) buffer.readByte();
        buffer.skipBytes(4); // length

        assert (messageType == MessageType.Acknowledge && chunkType == 'F');

        return AcknowledgeMessage.decode(buffer);
    }

    public static ErrorMessage decodeError(ByteBuf buffer) throws UaException {
        MessageType messageType = MessageType.fromMediumInt(buffer.readMedium());
        char chunkType = (char) buffer.readByte();
        buffer.skipBytes(4); // length

        assert (messageType == MessageType.Error && chunkType == 'F');

        return ErrorMessage.decode(buffer);
    }

}
