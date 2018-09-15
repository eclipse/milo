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

package org.eclipse.milo.opcua.sdk.client.session;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.testng.Assert.assertNotNull;

public class SessionFsmTest {

    @Test
    public void testCloseSessionWhileInactive() throws Exception {
        OpcUaClientConfig clientConfig = OpcUaClientConfig.builder()
            .setEndpoint(new EndpointDescription(
                "opc.tcp://localhost:12685",
                null,
                null,
                MessageSecurityMode.None,
                SecurityPolicy.None.getSecurityPolicyUri(),
                null,
                TransportProfile.TCP_UASC_UABINARY.getUri(),
                null
            ))
            .setApplicationName(LocalizedText.english("Eclipse Milo Test Client"))
            .setApplicationUri("urn:eclipse:milo:examples:client")
            .setRequestTimeout(uint(60000))
            .build();

        OpcUaClient client = OpcUaClient.create(clientConfig);

        SessionFsm sessionFsm = new SessionFsm(client);

        assertNotNull(sessionFsm.closeSession().get());
    }

}
