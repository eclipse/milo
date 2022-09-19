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

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;

public class ClientFactory {


    public static AbstractClient create(OpcTransportConfig config) {
        return null;
    }

    public static AbstractClient create(String endpointUrl) {
        return null;
    }

    public static AbstractClient create(
        String endpointUrl,
        Function<List<EndpointDescription>, Optional<EndpointDescription>> selectEndpoint,
        Function<ClientConfigBuilder, ClientConfig> buildConfig
    ) throws UaException {

        String scheme = EndpointUtil.getScheme(endpointUrl);

        String profileUri;

        switch (Objects.requireNonNullElse(scheme, "").toLowerCase()) {
            case "opc.tcp":
                profileUri = Stack.TCP_UASC_UABINARY_TRANSPORT_URI;
                break;

            case "http":
            case "https":
            case "opc.http":
            case "opc.https":
                profileUri = Stack.HTTPS_UABINARY_TRANSPORT_URI;
                break;

            case "opc.ws":
            case "opc.wss":
                profileUri = Stack.WSS_UASC_UABINARY_TRANSPORT_URI;
                break;

            default:
                throw new UaException(
                    StatusCodes.Bad_InternalError,
                    "unsupported protocol: " + scheme
                );
        }

        var configBuilder = new ClientConfigBuilder() {};
        ClientConfig config = buildConfig.apply(configBuilder);

        OpcTransport transport = TransportFactory.getInstance().create(profileUri, config);

        return new AbstractClient(transport) {};
    }


}
