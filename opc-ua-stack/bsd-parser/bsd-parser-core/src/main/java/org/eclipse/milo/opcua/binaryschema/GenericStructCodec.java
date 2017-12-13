/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.binaryschema;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.milo.opcua.stack.core.util.ArrayUtil;
import org.opcfoundation.opcua.binaryschema.StructuredType;

public class GenericStructCodec extends AbstractCodec<Struct, Struct.Member> {

    public GenericStructCodec(StructuredType structuredType) {
        super(structuredType);
    }

    @Override
    public Class<Struct> getType() {
        return Struct.class;
    }

    @Override
    protected Struct createStructure(String name, LinkedHashMap<String, Struct.Member> members) {
        return new Struct(name, members);
    }

    @Override
    protected Map<String, Struct.Member> getMembers(Struct value) {
        return value.getMembers();
    }

    @Override
    protected Struct.Member opcUaToMemberTypeScalar(String name, Object value, String typeName) {
        return new Struct.Member(name, value);
    }

    @Override
    protected Struct.Member opcUaToMemberTypeArray(String name, Object values, String typeName) {
        return new Struct.Member(name, values);
    }

    @Override
    protected Object memberTypeToOpcUaScalar(Struct.Member member, String typeName) {
        return member.getValue();
    }

    @Override
    protected Object memberTypeToOpcUaArray(Struct.Member member, String typeName) {
        Object value = member.getValue();

        if (value == null) {
            return null;
        } else if (value instanceof List) {
            List<Object> list = (List<Object>) value;

            return list.toArray();
        } else if (value.getClass().isArray()) {
            List<Object> values = new ArrayList<>();

            Object flattened = ArrayUtil.flatten(value);
            int length = Array.getLength(flattened);
            for (int i = 0; i < length; i++) {
                values.add(Array.get(flattened, i));
            }

            return values.toArray();
        } else {
            return value;
        }
    }

}
