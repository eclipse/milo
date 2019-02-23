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

import java.util.List;

public class DictionaryDescription {

    private final String namespaceUri;
    private final List<CodecDescription> enumCodecs;
    private final List<CodecDescription> structCodecs;

    public DictionaryDescription(
        String namespaceUri,
        List<CodecDescription> enumCodecs,
        List<CodecDescription> structCodecs) {

        this.namespaceUri = namespaceUri;
        this.enumCodecs = enumCodecs;
        this.structCodecs = structCodecs;
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
