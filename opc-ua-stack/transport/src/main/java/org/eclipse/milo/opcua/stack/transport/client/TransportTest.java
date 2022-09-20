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

import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
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
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.transport.client.tcp.OpcTcpTransport;
import org.eclipse.milo.opcua.stack.transport.client.tcp.OpcTcpTransportConfig;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class TransportTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var endpoint = new EndpointDescription(
            "opc.tcp://localhost:12685/milo",
            null,
            null,
            MessageSecurityMode.None,
            SecurityPolicy.None.getUri(),
            null, null, null
        );

        OpcTcpTransportConfig config = OpcTcpTransportConfig.newBuilder()
            .setEndpoint(endpoint)
            .build();

        var transport = new OpcTcpTransport(config);

        transport.connect().get();

        NodeId authToken = createSession(transport);
        activateSession(transport, authToken);
        read(transport, authToken);

        Thread.sleep(Integer.MAX_VALUE);
    }

    private static NodeId createSession(OpcTcpTransport transport) throws ExecutionException, InterruptedException {
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

    private static void activateSession(OpcTcpTransport transport, NodeId authToken) throws ExecutionException, InterruptedException {
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

    private static void read(OpcTcpTransport transport, NodeId authToken) throws ExecutionException, InterruptedException {
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

        UaResponseMessageType response = transport.sendRequestMessage(readRequest).get();
        System.out.println(response);
    }

}
