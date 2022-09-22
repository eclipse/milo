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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.ServerTable;
import org.eclipse.milo.opcua.stack.core.channel.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingManager;
import org.eclipse.milo.opcua.stack.core.encoding.OpcUaEncodingManager;
import org.eclipse.milo.opcua.stack.core.security.CertificateManager;
import org.eclipse.milo.opcua.stack.core.security.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.security.DefaultCertificateManager;
import org.eclipse.milo.opcua.stack.core.security.DefaultTrustListManager;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
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
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.server.security.DefaultServerCertificateValidator;
import org.eclipse.milo.opcua.stack.transport.client.ClientApplication;
import org.eclipse.milo.opcua.stack.transport.server.ServerApplication;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;
import org.eclipse.milo.opcua.stack.transport.server.tcp.OpcTcpServerTransport;
import org.eclipse.milo.opcua.stack.transport.server.tcp.OpcTcpServerTransportConfig;
import org.junit.jupiter.api.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;


class OpcTcpTransportTest {

    static final UserTokenPolicy USER_TOKEN_POLICY_ANONYMOUS = new UserTokenPolicy(
        "anonymous",
        UserTokenType.Anonymous,
        null,
        null,
        null
    );

    static final EndpointDescription ENDPOINT_DESCRIPTION = new EndpointDescription(
        "opc.tcp://localhost:12685",
        new ApplicationDescription(
            "uri:server",
            "productUri",
            LocalizedText.NULL_VALUE,
            ApplicationType.Server,
            null, null,
            new String[]{"opc.tcp://localhost:12685"}
        ),
        ByteString.NULL_VALUE,
        MessageSecurityMode.None,
        SecurityPolicy.None.getUri(),
        new UserTokenPolicy[]{USER_TOKEN_POLICY_ANONYMOUS},
        TransportProfile.TCP_UASC_UABINARY.getUri(),
        ubyte(0)
    );


    @Test()
    void connect() throws Exception {
        setupServer();

        var application = new ClientApplication() {
            @Override
            public EncodingContext getEncodingContext() {
                return new DefaultEncodingContext();
            }
        };

        OpcTcpTransportConfig config = OpcTcpTransportConfig.newBuilder()
            .setEndpoint(ENDPOINT_DESCRIPTION)
            .build();

        var transport = new OpcTcpTransport(config);

        System.out.println("connecting...");
        transport.connect(application).get();
        System.out.println("connected");

        System.out.println("disconnecting...");
        transport.disconnect().get();
        System.out.println("disconnected");
    }

    @Test
    void readServerTime() throws Exception {
        setupServer();

        var application = new ClientApplication() {
            @Override
            public EncodingContext getEncodingContext() {
                return new DefaultEncodingContext();
            }
        };

        OpcTcpTransportConfig config = OpcTcpTransportConfig.newBuilder()
            .setEndpoint(ENDPOINT_DESCRIPTION)
            .build();

        var transport = new OpcTcpTransport(config);

        transport.connect(application).get();

        NodeId authToken = createSession(transport);
        activateSession(transport, authToken);
        DataValue value = readCurrentTime(transport, authToken);

        System.out.println(value.getValue());
    }

    private void setupServer() throws Exception {
        var application = new ServerApplication() {

            private final AtomicLong secureChannelId = new AtomicLong(0L);
            private final AtomicLong secureChannelTokenId = new AtomicLong(0L);

            @Override
            public List<EndpointDescription> getEndpointDescriptions() {
                return List.of(ENDPOINT_DESCRIPTION);
            }

            @Override
            public EncodingContext getEncodingContext() {
                return new DefaultEncodingContext();
            }

            @Override
            public CertificateManager getCertificateManager() {
                return new DefaultCertificateManager();
            }

            @Override
            public CertificateValidator getCertificateValidator() {
                try {
                    File pkiDir = Files.createTempDirectory("pki").toFile();
                    DefaultTrustListManager trustListManager = new DefaultTrustListManager(pkiDir);
                    return new DefaultServerCertificateValidator(trustListManager);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Long getNextSecureChannelId() {
                return secureChannelId.getAndIncrement();
            }

            @Override
            public Long getNextSecureChannelTokenId() {
                return secureChannelTokenId.getAndIncrement();
            }

            @Override
            public CompletableFuture<UaResponseMessageType> handleServiceRequest(
                ServiceRequestContext context,
                UaRequestMessageType requestMessage
            ) {

                System.out.println("request: " + requestMessage);

                return CompletableFuture.failedFuture(new RuntimeException("not implemented"));
            }

        };


        OpcTcpServerTransportConfig config = OpcTcpServerTransportConfig.newBuilder().build();

        var transport = new OpcTcpServerTransport(config);
        transport.bind(application);
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

    private static class DefaultEncodingContext implements EncodingContext {

        private final NamespaceTable namespaceTable = new NamespaceTable();
        private final ServerTable serverTable = new ServerTable();

        @Override
        public DataTypeManager getDataTypeManager() {
            return OpcUaDataTypeManager.getInstance();
        }

        @Override
        public EncodingManager getEncodingManager() {
            return OpcUaEncodingManager.getInstance();
        }

        @Override
        public EncodingLimits getEncodingLimits() {
            return EncodingLimits.DEFAULT;
        }

        @Override
        public NamespaceTable getNamespaceTable() {
            return namespaceTable;
        }

        @Override
        public ServerTable getServerTable() {
            return serverTable;
        }

    }

}