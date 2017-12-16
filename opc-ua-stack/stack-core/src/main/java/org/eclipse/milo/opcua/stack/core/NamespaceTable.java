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

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class NamespaceTable {

    public static final String OPC_UA_NAMESPACE = "http://opcfoundation.org/UA/";

    private final BiMap<UShort, String> uriTable = HashBiMap.create();

    public NamespaceTable() {
        uriTable.put(ushort(0), OPC_UA_NAMESPACE);
    }

    public synchronized UShort addUri(String uri) {
        UShort index = ushort(1);
        while (uriTable.containsKey(index)) {
            index = ushort(index.intValue() + 1);
            if (index.intValue() == 65535) {
                throw new UaRuntimeException(StatusCodes.Bad_InternalError, "uri table full");
            }
        }
        uriTable.put(index, uri);

        return index;
    }

    public synchronized void putUri(String uri, UShort index) {
        uriTable.put(index, uri);
    }

    public synchronized String getUri(UShort index) {
        return uriTable.get(index);
    }

    /**
     * @param uri the namespace URI to look up.
     * @return the index of the namespace URI, or {@code null} if it is not present.
     */
    public synchronized UShort getIndex(String uri) {
        return uriTable.inverse().getOrDefault(uri, null);
    }

    public synchronized void update(Consumer<BiMap<UShort, String>> uriTableConsumer) {
        uriTableConsumer.accept(uriTable);
    }

    public synchronized String[] toArray() {
        List<String> uris = uriTable.entrySet().stream()
            .sorted((e1, e2) -> e1.getKey().intValue() - e2.getKey().intValue())
            .map(Map.Entry::getValue)
            .collect(Collectors.toList());

        return uris.toArray(new String[uris.size()]);
    }

}
