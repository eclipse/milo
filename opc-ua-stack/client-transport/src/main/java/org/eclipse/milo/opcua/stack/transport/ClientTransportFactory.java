/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.transport.tcp.OpcTcpTransport;

public class ClientTransportFactory {

    private static final ClientTransportFactory INSTANCE;

    static {
        INSTANCE = new ClientTransportFactory();
        INSTANCE.register(Stack.TCP_UASC_UABINARY_TRANSPORT_URI, new OpcTcpTransportFactory());
    }

    public static ClientTransportFactory getInstance() {
        return INSTANCE;
    }

    private final Map<String, Function<OpcTransportConfig, OpcTransport>> functions = new ConcurrentHashMap<>();

    public OpcTransport create(String profileUri, OpcTransportConfig config) throws UaException {
        Function<OpcTransportConfig, OpcTransport> f = functions.get(profileUri);

        if (f != null) {
            return f.apply(config);
        } else {
            throw new UaException(StatusCodes.Bad_NotSupported, "transport: " + profileUri);
        }
    }

    public void register(String profileUri, Function<OpcTransportConfig, OpcTransport> createTransport) {
        functions.put(profileUri, createTransport);
    }

    private static class OpcTcpTransportFactory implements Function<OpcTransportConfig, OpcTransport> {
        @Override
        public OpcTransport apply(OpcTransportConfig clientConfig) {
            return new OpcTcpTransport(clientConfig);
        }
    }

}
