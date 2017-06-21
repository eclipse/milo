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

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;

public class UaNodeLoader {

    private final OpcUaServer server;

    public UaNodeLoader(OpcUaServer server) {
        this.server = server;
    }

    public void loadNodes() throws Exception {
        new UaDataTypeLoader(server).buildNodes();
        new UaMethodLoader(server).buildNodes();
        new UaObjectLoader(server).buildNodes();
        new UaObjectTypeLoader(server).buildNodes();
        new UaReferenceTypeLoader(server).buildNodes();
        new UaVariableLoader(server).buildNodes();
        new UaVariableTypeLoader(server).buildNodes();
        new UaViewLoader(server).buildNodes();
    }

}
