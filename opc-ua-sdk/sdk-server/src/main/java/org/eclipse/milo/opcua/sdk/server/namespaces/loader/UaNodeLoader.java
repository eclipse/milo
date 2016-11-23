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

package org.eclipse.milo.opcua.sdk.server.namespaces.loader;

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;

public class UaNodeLoader {

    private final ServerNodeMap nodeMap;

    public UaNodeLoader(ServerNodeMap nodeMap) {
        this.nodeMap = nodeMap;
    }

    public void loadNodes() throws Exception {
        new UaDataTypeLoader(nodeMap).buildNodes();
        new UaMethodLoader(nodeMap).buildNodes();
        new UaObjectLoader(nodeMap).buildNodes();
        new UaObjectTypeLoader(nodeMap).buildNodes();
        new UaReferenceTypeLoader(nodeMap).buildNodes();
        new UaVariableLoader(nodeMap).buildNodes();
        new UaVariableTypeLoader(nodeMap).buildNodes();
        new UaViewLoader(nodeMap).buildNodes();
    }

}
