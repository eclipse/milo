/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.core;

import java.util.Comparator;
import java.util.Map;

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
        return uriTable.entrySet().stream()
            .sorted(Comparator.comparingInt(Map.Entry::getKey))
            .map(Map.Entry::getValue)
            .toArray(String[]::new);
    }

}
