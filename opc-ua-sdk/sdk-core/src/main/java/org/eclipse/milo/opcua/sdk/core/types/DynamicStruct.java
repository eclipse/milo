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

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import org.eclipse.milo.opcua.sdk.core.DataTypeTree;

public class DynamicStruct {

    private final DataTypeTree.DataType dataType;
    private final LinkedHashMap<String, Object> members;

    public DynamicStruct(DataTypeTree.DataType dataType, LinkedHashMap<String, Object> members) {
        this.dataType = dataType;
        this.members = members;
    }

    public DataTypeTree.DataType getDataType() {
        return dataType;
    }

    public LinkedHashMap<String, Object> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "DynamicStruct{" + "members={" + joinMembers(members) + "}}";
    }

    private static String joinMembers(LinkedHashMap<String, Object> members) {
        return members.entrySet().stream()
            .map(e -> {
                String k = e.getKey();
                Object v = e.getValue();
                if (v instanceof Object[]) {
                    return String.format("%s=%s", k, Arrays.toString((Object[]) v));
                } else {
                    return String.format("%s=%s", k, v);
                }
            })
            .collect(Collectors.joining(", "));
    }

}
