/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.core.types;

import org.eclipse.milo.opcua.sdk.core.DataTypeTree;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.jetbrains.annotations.Nullable;

public class DynamicEnum {

    private final DataTypeTree.DataType dataType;
    private final String name;
    private final int value;
    private final LocalizedText displayName;
    private final LocalizedText description;

    public DynamicEnum(DataTypeTree.DataType dataType, int value) {
        this(dataType, null, value, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE);
    }

    public DynamicEnum(DataTypeTree.DataType dataType, String name, int value, LocalizedText displayName, LocalizedText description) {
        this.dataType = dataType;
        this.name = name;
        this.value = value;
        this.displayName = displayName;
        this.description = description;
    }

    public DataTypeTree.DataType getDataType() {
        return dataType;
    }

    public @Nullable String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public LocalizedText getDisplayName() {
        return displayName;
    }

    public LocalizedText getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "DynamicEnum{" +
            "name='" + name + '\'' +
            ", value=" + value +
            '}';
    }

}
