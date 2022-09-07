/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types;

import java.util.Arrays;
import java.util.LinkedHashMap;

import org.jetbrains.annotations.Nullable;

public class DynamicUnion extends DynamicStruct {

    public DynamicUnion() {
        this(new LinkedHashMap<>());
    }

    public DynamicUnion(LinkedHashMap<String, Object> members) {
        super(members);
    }

    public @Nullable String getFieldName() {
        return getMembers().keySet().stream().findFirst().orElse(null);
    }

    public @Nullable Object getFieldValue() {
        return getMembers().values().stream().findFirst().orElse(null);
    }

    public boolean isNull() {
        return getMembers().isEmpty();
    }

    @Override
    public String toString() {
        return "DynamicUnion{" + joinMembers(getMembers()) + "}";
    }

    private static String joinMembers(LinkedHashMap<String, Object> members) {
        return members.entrySet().stream()
            .findFirst()
            .map(e -> {
                String k = e.getKey();
                Object v = e.getValue();
                if (v instanceof Object[]) {
                    return String.format("%s=%s", k, Arrays.toString((Object[]) v));
                } else {
                    return String.format("%s=%s", k, v);
                }
            })
            .orElse("null");
    }

    static DynamicUnion ofNull() {
        return new DynamicUnion();
    }

    static DynamicUnion of(String fieldName, Object fieldValue) {
        LinkedHashMap<String, Object> members = new LinkedHashMap<>();
        members.put(fieldName, fieldValue);
        return new DynamicUnion(members);
    }

}
