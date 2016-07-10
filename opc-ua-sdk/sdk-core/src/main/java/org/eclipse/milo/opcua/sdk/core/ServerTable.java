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

package org.eclipse.milo.opcua.sdk.core;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;

public class ServerTable {

    private final BiMap<Integer, String> uriTable = HashBiMap.create();

    public synchronized int addUri(String uri) {
        int index = 1;
        while (uriTable.containsKey(index)) {
            index++;
            if (index == 65535) {
                throw new UaRuntimeException(StatusCodes.Bad_InternalError, "uri table full");
            }
        }
        uriTable.put(index, uri);
        return index;
    }

    public synchronized void putUri(String uri, int index) {
        uriTable.put(index, uri);
    }

    public synchronized String getUri(int index) {
        return uriTable.get(index);
    }

    public synchronized int getIndex(String uri) {
        return uriTable.inverse().get(uri);
    }

    public synchronized String[] toArray() {
        List<String> uris = uriTable.entrySet().stream()
            .sorted((e1, e2) -> e1.getKey() - e2.getKey())
            .map(Map.Entry::getValue)
            .collect(Collectors.toList());

        return uris.toArray(new String[uris.size()]);
    }

}
