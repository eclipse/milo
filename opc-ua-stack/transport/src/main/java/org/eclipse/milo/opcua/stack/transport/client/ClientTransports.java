/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.transport.client.tcp.OpcTcpTransport;
import org.eclipse.milo.opcua.stack.transport.client.tcp.OpcTcpTransportConfig;

public class ClientTransports {

    private static final ClientTransports INSTANCE;

    static {
        INSTANCE = new ClientTransports();
        INSTANCE.register(Stack.TCP_UASC_UABINARY_TRANSPORT_URI, new OpcTcpTransportFactory());
    }

    public static ClientTransports getInstance() {
        return INSTANCE;
    }

    private final Map<String, TransportFactory> factories = new ConcurrentHashMap<>();

    private ClientTransports() {}

    public OpcTransport createTransport(String profileUri, OpcTransportConfig config) throws UaException {
        TransportFactory factory = factories.get(profileUri);

        if (factory != null) {
            return factory.create(config);
        } else {
            throw new UaException(StatusCodes.Bad_NotSupported, "transport: " + profileUri);
        }
    }

    public void register(String profileUri, TransportFactory factory) {
        factories.put(profileUri, factory);
    }

    public interface TransportFactory {

        OpcTransport create(OpcTransportConfig config);

    }

    private static class OpcTcpTransportFactory implements TransportFactory {

        @Override
        public OpcTransport create(OpcTransportConfig clientConfig) {
            // TODO unsafe cast... can we do something better?
            return new OpcTcpTransport((OpcTcpTransportConfig) clientConfig);
        }

    }

}
