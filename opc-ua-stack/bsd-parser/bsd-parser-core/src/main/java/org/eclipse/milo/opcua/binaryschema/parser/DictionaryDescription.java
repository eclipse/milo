/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.binaryschema.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DictionaryDescription {

    private final List<CodecDescription> enumCodecs =
        Collections.synchronizedList(new ArrayList<>());

    private final List<CodecDescription> structCodecs =
        Collections.synchronizedList(new ArrayList<>());

    private final String namespaceUri;

    public DictionaryDescription(
        String namespaceUri,
        List<CodecDescription> enumCodecs,
        List<CodecDescription> structCodecs) {

        this.namespaceUri = namespaceUri;
        this.enumCodecs.addAll(enumCodecs);
        this.structCodecs.addAll(structCodecs);
    }

    public String getNamespaceUri() {
        return namespaceUri;
    }

    public List<CodecDescription> getEnumCodecs() {
        return enumCodecs;
    }

    public List<CodecDescription> getStructCodecs() {
        return structCodecs;
    }

}
