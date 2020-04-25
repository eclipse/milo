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

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;

final class GuidConversions {

    private GuidConversions() {}

    static ByteString guidToByteString(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());

        return ByteString.of(bb.array());
    }

    static String guidToString(UUID uuid) {
        return uuid.toString();
    }

    static Object convert(
        Object value,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionNotDefinedException {

        if (value instanceof UUID) {
            UUID uuid = (UUID) value;

            return implicit ?
                implicitConversion(uuid, targetType) :
                explicitConversion(uuid, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(UUID uuid, BuiltinDataType targetType) throws ConversionNotDefinedException {
        //@formatter:off
        switch (targetType) {
            case ByteString:    return guidToByteString(uuid);
            case String:        return guidToString(uuid);
            default:            return implicitConversion(uuid, targetType);
        }
        //@formatter:on
    }

    static Object implicitConversion(UUID uuid, BuiltinDataType targetType) throws ConversionNotDefinedException {
        // no implicit conversions exist
        throw new ConversionNotDefinedException(BuiltinDataType.Guid, targetType);
    }

}
