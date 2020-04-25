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

import java.nio.ByteBuffer;
import java.util.UUID;

import io.netty.buffer.ByteBufUtil;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;

final class ByteStringConversions {

    private ByteStringConversions() {}

    static UUID byteStringToGuid(ByteString bs) throws ConversionFailedException {
        if (bs.length() != 16) {
            throw new ConversionFailedException(BuiltinDataType.ByteString, BuiltinDataType.Guid);
        } else {
            ByteBuffer byteBuffer = ByteBuffer.wrap(bs.bytesOrEmpty());
            long high = byteBuffer.getLong();
            long low = byteBuffer.getLong();
            return new UUID(high, low);
        }
    }

    static String byteStringToString(ByteString bs) {
        return ByteBufUtil.hexDump(bs.bytesOrEmpty());
    }

    static Object convert(
        Object value,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionFailedException, ConversionNotDefinedException {

        if (value instanceof ByteString) {
            ByteString bs = (ByteString) value;

            return implicit ?
                implicitConversion(bs, targetType) :
                explicitConversion(bs, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(
        ByteString bs,
        BuiltinDataType targetType
    ) throws ConversionFailedException, ConversionNotDefinedException {

        //@formatter:off
        switch (targetType) {
            case Guid:      return byteStringToGuid(bs);
            case String:    return byteStringToString(bs);
            default:        return implicitConversion(bs, targetType);
        }
        //@formatter:on
    }

    static Object implicitConversion(ByteString bs, BuiltinDataType targetType) throws ConversionNotDefinedException {
        // no implicit conversions exist
        throw new ConversionNotDefinedException(BuiltinDataType.ByteString, targetType);
    }

}
