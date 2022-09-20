/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.client.tcp;

import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;


class OpcTcpTransportTest {

    private final EndpointDescription endpoint = new EndpointDescription(
        "opc.tcp://localhost:12685/milo",
        null,
        null,
        MessageSecurityMode.None,
        SecurityPolicy.None.getUri(),
        null, null, null
    );


    @Disabled("run manually")
    @Test()
    void connect() throws Exception {
        OpcTcpTransportConfig config = OpcTcpTransportConfig.newBuilder()
            .setEndpoint(endpoint)
            .build();

        var transport = new OpcTcpTransport(config);

        transport.connect().get();
        transport.disconnect().get();
    }

    @Disabled("run manually")
    @Test
    void readServerTime() throws Exception {
        OpcTcpTransportConfig config = OpcTcpTransportConfig.newBuilder()
            .setEndpoint(endpoint)
            .build();

        var transport = new OpcTcpTransport(config);

        transport.connect().get();

        NodeId authToken = createSession(transport);
        activateSession(transport, authToken);
        DataValue value = readCurrentTime(transport, authToken);

        System.out.println(value.getValue());
    }

    private static NodeId createSession(OpcTcpTransport transport) throws Exception {
        var header = new RequestHeader(
            NodeId.NULL_VALUE,
            DateTime.nowMillis(),
            uint(0),
            uint(0),
            null,
            uint(5_000),
            null
        );

        var request = new CreateSessionRequest(
            header,
            ApplicationDescription.builder()
                .applicationName(LocalizedText.NULL_VALUE)
                .applicationUri("")
                .applicationType(ApplicationType.Client)
                .productUri("")
                .applicationUri("")
                .build(),
            null,
            "opc.tcp://localhost:12685/milo",
            "sessionName",
            ByteString.NULL_VALUE,
            ByteString.NULL_VALUE,
            60_000d,
            UInteger.MAX
        );

        CreateSessionResponse response = (CreateSessionResponse) transport.sendRequestMessage(request).get();

        return response.getAuthenticationToken();
    }

    private static void activateSession(OpcTcpTransport transport, NodeId authToken) throws Exception {
        var header = new RequestHeader(
            authToken,
            DateTime.nowMillis(),
            uint(0),
            uint(0),
            null,
            uint(5_000),
            null
        );

        var request = new ActivateSessionRequest(
            header,
            new SignatureData(null, ByteString.NULL_VALUE),
            null,
            null,
            null,
            new SignatureData(null, ByteString.NULL_VALUE)
        );

        ActivateSessionResponse response = (ActivateSessionResponse) transport.sendRequestMessage(request).get();
        assert response.getResponseHeader().getServiceResult().isGood();
    }

    private static DataValue readCurrentTime(OpcTcpTransport transport, NodeId authToken) throws Exception {
        var header = new RequestHeader(
            authToken,
            DateTime.nowMillis(),
            uint(0),
            uint(0),
            null,
            uint(5_000),
            null
        );

        var readRequest = new ReadRequest(
            header,
            0d,
            TimestampsToReturn.Both,
            new ReadValueId[]{
                new ReadValueId(NodeIds.Server_ServerStatus_CurrentTime, AttributeId.Value.uid(), null, QualifiedName.NULL_VALUE)
            }
        );

        ReadResponse response = (ReadResponse) transport.sendRequestMessage(readRequest).get();
        return response.getResults()[0];
    }

}