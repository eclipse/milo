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

import io.netty.buffer.ByteBuf;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.messages.MessageType;

public final class SecureMessageHeader {

    public static final int SECURE_MESSAGE_HEADER_SIZE = 12;

    private final MessageType messageType;
    private final char finalFlag;
    private final long messageSize;
    private final long secureChannelId;

    /**
     * @param messageType     the {@link MessageType} of the following message.
     * @param finalFlag       a one byte ASCII code that indicates whether the MessageChunk is the final chunk in a
     *                        Message. The following values are defined at this time: 'C', an intermediate chunk, 'F',
     *                        the final chunk, and  'A', the final chunk (used when an error occurred and the Message
     *                        is aborted).
     * @param messageSize     the length of the MessageChunk, in bytes. This value includes size of the Message header.
     * @param secureChannelId a unique identifier for the SecureChannel assigned by the Server.
     */
    public SecureMessageHeader(MessageType messageType, char finalFlag, long messageSize, long secureChannelId) {
        this.messageType = messageType;
        this.finalFlag = finalFlag;
        this.messageSize = messageSize;
        this.secureChannelId = secureChannelId;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public char getChunkType() {
        return finalFlag;
    }

    public long getMessageSize() {
        return messageSize;
    }

    public long getSecureChannelId() {
        return secureChannelId;
    }

    public static void encode(SecureMessageHeader header, ByteBuf buffer) throws UaException {
        buffer.writeMedium(MessageType.toMediumInt(header.getMessageType()));
        buffer.writeByte(header.getChunkType());
        buffer.writeInt((int) header.getMessageSize());
        buffer.writeInt((int) header.getSecureChannelId());
    }

    public static SecureMessageHeader decode(ByteBuf buffer) throws UaException {
        return new SecureMessageHeader(
            MessageType.fromMediumInt(buffer.readMedium()),
            (char) buffer.readByte(),
            buffer.readUnsignedInt(),
            buffer.readUnsignedInt()
        );
    }

}
