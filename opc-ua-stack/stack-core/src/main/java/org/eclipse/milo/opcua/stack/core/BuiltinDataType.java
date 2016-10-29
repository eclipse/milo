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

package org.eclipse.milo.opcua.stack.core;

import java.util.UUID;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
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

public enum BuiltinDataType {

    Boolean(1, Boolean.class),
    SByte(2, Byte.class),
    Byte(3, UByte.class),
    Int16(4, Short.class),
    UInt16(5, UShort.class),
    Int32(6, Integer.class),
    UInt32(7, UInteger.class),
    Int64(8, Long.class),
    UInt64(9, ULong.class),
    Float(10, Float.class),
    Double(11, Double.class),
    String(12, String.class),
    DateTime(13, DateTime.class),
    Guid(14, UUID.class),
    ByteString(15, ByteString.class),
    XmlElement(16, XmlElement.class),
    NodeId(17, NodeId.class),
    ExpandedNodeId(18, org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId.class),
    StatusCode(19, StatusCode.class),
    QualifiedName(20, QualifiedName.class),
    LocalizedText(21, org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText.class),
    ExtensionObject(22, ExtensionObject.class),
    DataValue(23, org.eclipse.milo.opcua.stack.core.types.builtin.DataValue.class),
    Variant(24, Variant.class),
    DiagnosticInfo(25, org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo.class);

    private final int typeId;
    private final Class<?> backingClass;

    BuiltinDataType(int typeId, Class<?> backingClass) {
        this.typeId = typeId;
        this.backingClass = backingClass;
    }

    public int getTypeId() {
        return typeId;
    }

    public Class<?> getBackingClass() {
        return backingClass;
    }

    private static final BiMap<Integer, Class<?>> BackingClassesById;
    private static final BiMap<NodeId, Class<?>> BackingClassesByNodeId;

    static {
        ImmutableBiMap.Builder<Integer, Class<?>> builder = ImmutableBiMap.<Integer, Class<?>>builder();
        ImmutableBiMap.Builder<NodeId, Class<?>> builder2 = ImmutableBiMap.<NodeId, Class<?>>builder();

        for (BuiltinDataType dataType : values()) {
            builder.put(dataType.getTypeId(), dataType.getBackingClass());
            builder2.put(new NodeId(0, dataType.getTypeId()), dataType.getBackingClass());
        }

        BackingClassesById = builder.build();
        BackingClassesByNodeId = builder2.build();
    }

    /**
     * @param backingClass the backing {@link Class} of the builtin type.
     * @return the id of the builtin type backed by {@code backingClass}.
     */
    public static int getBuiltinTypeId(Class<?> backingClass) {
        if (backingClass.isPrimitive()) {
            if (backingClass == boolean.class) {
                backingClass = Boolean.class;
            } else if (backingClass == byte.class) {
                backingClass = Byte.class;
            } else if (backingClass == short.class) {
                backingClass = Short.class;
            } else if (backingClass == int.class) {
                backingClass = Integer.class;
            } else if (backingClass == long.class) {
                backingClass = Long.class;
            } else if (backingClass == float.class) {
                backingClass = Float.class;
            } else if (backingClass == double.class) {
                backingClass = Double.class;
            }
        }

        return BackingClassesById.inverse().get(backingClass);
    }

    /**
     * @param typeId the id of the builtin type.
     * @return the {@link Class} backing the builtin type.
     */
    public static Class<?> getBackingClass(int typeId) {
        return BackingClassesById.get(typeId);
    }

    public static Class<?> getBackingClass(NodeId typeId) {
        return BackingClassesByNodeId.get(typeId);
    }

    public static Class<?> getBackingClass(ExpandedNodeId typeId) {
        if (typeId.getNamespaceIndex().intValue() == 0 && typeId.getType() == IdType.Numeric) {
            Number id = (Number) typeId.getIdentifier();
            return BackingClassesById.get(id.intValue());
        }

        return null;
    }

    public static boolean isBuiltin(int typeId) {
        return BackingClassesById.containsKey(typeId);
    }

    public static boolean isBuiltin(NodeId typeId) {
        return BackingClassesByNodeId.containsKey(typeId);
    }

    public static boolean isBuiltin(ExpandedNodeId typeId) {
        return typeId.local().map(BackingClassesByNodeId::containsKey).orElse(false);
    }

}
