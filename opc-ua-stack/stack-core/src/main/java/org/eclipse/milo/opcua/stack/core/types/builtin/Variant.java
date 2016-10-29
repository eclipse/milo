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

package org.eclipse.milo.opcua.stack.core.types.builtin;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.util.ArrayUtil;
import org.eclipse.milo.opcua.stack.core.util.TypeUtil;

import static com.google.common.base.Preconditions.checkArgument;

public final class Variant {

    public static final Variant NULL_VALUE = new Variant(null);

    private final Object value;

    /**
     * Create a new Variant with a given value.
     *
     * @param value the value this Variant holds.
     */
    public Variant(@Nullable Object value) {
        if (value != null) {
            boolean clazzIsArray = value.getClass().isArray();

            Class<?> componentClazz = clazzIsArray ?
                ArrayUtil.getType(value) : value.getClass();

            checkArgument(clazzIsArray || !Variant.class.equals(componentClazz), "Variant cannot contain Variant");
            checkArgument(!DataValue.class.equals(componentClazz), "Variant cannot contain DataValue");
            checkArgument(!DiagnosticInfo.class.equals(componentClazz), "Variant cannot contain DiagnosticInfo");
        }

        this.value = value;
    }

    public Optional<NodeId> getDataType() {
        if (value == null) return Optional.empty();

        if (value instanceof UaStructure) {
            return Optional.of(((UaStructure) value).getTypeId());
        } else if (value instanceof UaEnumeration) {
            return Optional.of(Identifiers.Int32);
        } else {
            Class<?> clazz = value.getClass().isArray() ?
                ArrayUtil.getType(value) : value.getClass();

            int typeId = TypeUtil.getBuiltinTypeId(clazz);

            return typeId == -1 ?
                Optional.empty() : Optional.of(new NodeId(0, typeId));
        }
    }

    public Object getValue() {
        return value;
    }

    public boolean isNull() {
        return value == null;
    }

    public boolean isNotNull() {
        return !isNull();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Variant variant = (Variant) o;

        return Objects.deepEquals(value, variant.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueHash());
    }

    private int valueHash() {
        if (value instanceof Object[]) {
            return Arrays.deepHashCode((Object[]) value);
        } else if (value instanceof boolean[]) {
            return Arrays.hashCode((boolean[]) value);
        } else if (value instanceof byte[]) {
            return Arrays.hashCode((byte[]) value);
        } else if (value instanceof short[]) {
            return Arrays.hashCode((short[]) value);
        } else if (value instanceof int[]) {
            return Arrays.hashCode((int[]) value);
        } else if (value instanceof long[]) {
            return Arrays.hashCode((long[]) value);
        } else if (value instanceof float[]) {
            return Arrays.hashCode((float[]) value);
        } else if (value instanceof double[]) {
            return Arrays.hashCode((double[]) value);
        } else {
            return Objects.hashCode(value);
        }
    }

    @Override
    public String toString() {
        ToStringHelper helper = MoreObjects.toStringHelper(this);

        helper.add("value", value);

        return helper.toString();
    }

}
