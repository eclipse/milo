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
import java.util.stream.Collectors;

import org.eclipse.milo.opcua.stack.core.types.structured.Structure;

public class DynamicStruct extends Structure {

    private final LinkedHashMap<String, Object> members;

    public DynamicStruct(LinkedHashMap<String, Object> members) {
        this.members = members;
    }

    public LinkedHashMap<String, Object> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DynamicStruct{");
        sb.append("members={");
        sb.append(joinMembers(members));
        sb.append('}');
        return sb.toString();
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
