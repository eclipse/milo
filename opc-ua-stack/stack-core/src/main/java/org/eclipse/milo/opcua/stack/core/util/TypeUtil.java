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

package org.eclipse.milo.opcua.stack.core.util;

import java.util.Map;
import java.util.UUID;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;

public class TypeUtil {

    private static final BiMap<Class<?>, Integer> PRIMITIVE_BUILTIN_TYPES =
        ImmutableBiMap.<Class<?>, Integer>builder()
            .put(boolean.class, 1)
            .put(byte.class, 2)
            .put(short.class, 4)
            .put(int.class, 6)
            .put(long.class, 8)
            .put(float.class, 10)
            .put(double.class, 11)
            .build();

    private static final BiMap<Integer, Class<?>> BUILTIN_TYPES =
        ImmutableBiMap.<Integer, Class<?>>builder()
            .put(1, Boolean.class)      // Boolean
            .put(2, Byte.class)         // SByte
            .put(3, UByte.class)        // UByte
            .put(4, Short.class)        // Int16
            .put(5, UShort.class)       // UInt16
            .put(6, Integer.class)      // Int32
            .put(7, UInteger.class)     // UInt32
            .put(8, Long.class)         // Int64
            .put(9, ULong.class)        // UInt64
            .put(10, Float.class)
            .put(11, Double.class)
            .put(12, String.class)
            .put(13, DateTime.class)
            .put(14, UUID.class)
            .put(15, ByteString.class)
            .put(16, XmlElement.class)
            .put(17, NodeId.class)
            .put(18, ExpandedNodeId.class)
            .put(19, StatusCode.class)
            .put(20, QualifiedName.class)
            .put(21, LocalizedText.class)
            .put(22, ExtensionObject.class)
            .put(23, DataValue.class)
            .put(24, Variant.class)
            .put(25, DiagnosticInfo.class)
            .build();

    private static final Map<Integer, Class<?>> SIMPLE_TYPES =
        ImmutableMap.<Integer, Class<?>>builder()
            .put(id(Identifiers.LocaleId), String.class)
            .put(id(Identifiers.Duration), Double.class)
            .put(id(Identifiers.ImageBMP), ByteString.class)
            .put(id(Identifiers.ImageGIF), ByteString.class)
            .put(id(Identifiers.ImageJPG), ByteString.class)
            .put(id(Identifiers.ImagePNG), ByteString.class)
            .put(id(Identifiers.Integer), Number.class)
            .put(id(Identifiers.Number), Number.class)
            .put(id(Identifiers.UtcTime), DateTime.class)
            .build();

    private static int id(NodeId nodeId) {
        return ((UInteger) nodeId.getIdentifier()).intValue();
    }

    /**
     * @param backingType the backing {@link Class} of the builtin type.
     * @return the id of the builtin type backed by {@code backingType}, or -1 if backingType is not builtin.
     */
    public static int getBuiltinTypeId(Class<?> backingType) {
        if (backingType.isPrimitive()) {
            return PRIMITIVE_BUILTIN_TYPES.getOrDefault(backingType, -1);
        } else {
            if (ExtensionObject.class.isAssignableFrom(backingType)) {
                return BUILTIN_TYPES.inverse().getOrDefault(ExtensionObject.class, -1);
            } else {
                return BUILTIN_TYPES.inverse().getOrDefault(backingType, -1);
            }
        }
    }

    /**
     * @param typeId the id of the builtin type.
     * @return the {@link Class} backing the builtin type.
     */
    public static Class<?> getBackingClass(int typeId) {
        return BUILTIN_TYPES.get(typeId);
    }

    public static Class<?> getBackingClass(ExpandedNodeId typeId) {
        if (typeId.getNamespaceIndex().intValue() == 0 && typeId.getType() == IdType.Numeric) {
            int id = ((Number) typeId.getIdentifier()).intValue();
            Class<?> c = getBackingClass(id);

            return c != null ? c : SIMPLE_TYPES.get(id);
        }

        return null;
    }

}
