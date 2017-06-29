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

import com.google.common.base.MoreObjects;
import io.netty.buffer.ByteBuf;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class ErrorMessage {

    private final StatusCode error;
    private final String reason;

    public ErrorMessage(long error, String reason) {
        this.error = new StatusCode(error);
        this.reason = reason;
    }

    public StatusCode getError() {
        return error;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {

        return MoreObjects.toStringHelper(this)
            .add("error", error)
            .add("reason", reason)
            .toString();
    }

    public static void encode(ErrorMessage message, ByteBuf buffer) {
        buffer.writeInt((int) message.getError().getValue());
        encodeString(message.getReason(), buffer);
    }

    public static ErrorMessage decode(ByteBuf buffer) {
        return new ErrorMessage(
            buffer.readUnsignedInt(),
            decodeString(buffer));
    }

    private static void encodeString(String s, ByteBuf buffer) {
        new OpcUaBinaryStreamEncoder(buffer).writeString(s);
    }

    private static String decodeString(ByteBuf buffer) {
        return new OpcUaBinaryStreamDecoder(buffer).readString();
    }

}
