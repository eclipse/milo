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

import java.util.LinkedHashMap;

import org.jetbrains.annotations.Nullable;

public class DynamicUnion extends DynamicStruct {

    public DynamicUnion(LinkedHashMap<String, Object> members) {
        super(members);
    }

    public @Nullable String getFieldName() {
        return null;
    }

    public @Nullable Object getFieldValue() {
        return null;
    }

    public boolean isNull() {
        return true;
    }

    static DynamicUnion create(String fieldName, Object fieldValue) {
        LinkedHashMap<String, Object> members = new LinkedHashMap<>();
        members.put(fieldName, fieldValue);
        return new DynamicUnion(members);
    }

}
