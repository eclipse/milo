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
