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

import java.security.KeyPair;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.eclipse.milo.opcua.stack.client.security.ClientCertificateValidator;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.ServerTable;
import org.eclipse.milo.opcua.stack.core.channel.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingManager;
import org.eclipse.milo.opcua.stack.core.encoding.OpcUaEncodingManager;
import org.eclipse.milo.opcua.stack.core.security.CertificateManager;
import org.eclipse.milo.opcua.stack.core.security.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.transport.client.ClientApplication;
import org.eclipse.milo.opcua.stack.transport.server.OpcServerTransport;
import org.eclipse.milo.opcua.stack.transport.server.ServerApplication;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;
import org.eclipse.milo.opcua.stack.transport.server.tcp.OpcTcpServerTransport;
import org.eclipse.milo.opcua.stack.transport.server.tcp.OpcTcpServerTransportConfig;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;


class OpcTcpTransportTest extends SecurityFixture {

    static {
        // Required for SecurityPolicy.Aes256_Sha256_RsaPss
        Security.addProvider(new BouncyCastleProvider());
    }

    private static Stream<Arguments> provideSecurityParameters() {
        return Stream.of(
            Arguments.of(SecurityPolicy.None, MessageSecurityMode.None),
            Arguments.of(SecurityPolicy.Basic256Sha256, MessageSecurityMode.Sign),
            Arguments.of(SecurityPolicy.Basic256Sha256, MessageSecurityMode.SignAndEncrypt),
            Arguments.of(SecurityPolicy.Aes128_Sha256_RsaOaep, MessageSecurityMode.Sign),
            Arguments.of(SecurityPolicy.Aes128_Sha256_RsaOaep, MessageSecurityMode.SignAndEncrypt),
            Arguments.of(SecurityPolicy.Aes256_Sha256_RsaPss, MessageSecurityMode.Sign),
            Arguments.of(SecurityPolicy.Aes256_Sha256_RsaPss, MessageSecurityMode.SignAndEncrypt)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSecurityParameters")
    void connectThenDisconnect(SecurityPolicy securityPolicy, MessageSecurityMode messageSecurityMode) throws Exception {
        OpcServerTransport serverTransport = bindServerTransport(
            securityPolicy,
            messageSecurityMode
        );

        var application = new ClientApplication() {
            @Override
            public EndpointDescription getEndpoint() {
                return newEndpointDescription(securityPolicy, messageSecurityMode);
            }

            @Override
            public Optional<KeyPair> getKeyPair() {
                return Optional.of(clientKeyPair);
            }

            @Override
            public Optional<X509Certificate> getCertificate() {
                return Optional.of(clientCertificate);
            }

            @Override
            public Optional<X509Certificate[]> getCertificateChain() {
                return Optional.of(new X509Certificate[]{clientCertificate});
            }

            @Override
            public CertificateValidator getCertificateValidator() {
                return new ClientCertificateValidator.InsecureValidator();
            }

            @Override
            public EncodingContext getEncodingContext() {
                return new DefaultEncodingContext();
            }
        };

        OpcTcpClientTransportConfig config = OpcTcpClientTransportConfig.newBuilder().build();

        var transport = new OpcTcpClientTransport(config);

        System.out.println("connecting...");
        transport.connect(application).get();
        System.out.println("connected");

        System.out.println("disconnecting...");
        transport.disconnect().get();
        System.out.println("disconnected");

        System.out.println("unbinding server transport...");
        serverTransport.unbind();
        System.out.println("server transport unbound");
    }

    private OpcServerTransport bindServerTransport(
        SecurityPolicy securityPolicy,
        MessageSecurityMode messageSecurityMode
    ) throws Exception {

        var application = new ServerApplication() {

            private final AtomicLong secureChannelId = new AtomicLong(0L);
            private final AtomicLong secureChannelTokenId = new AtomicLong(0L);

            @Override
            public List<EndpointDescription> getEndpointDescriptions() {
                return List.of(newEndpointDescription(securityPolicy, messageSecurityMode));
            }

            @Override
            public EncodingContext getEncodingContext() {
                return new DefaultEncodingContext();
            }

            @Override
            public CertificateManager getCertificateManager() {
                return serverCertificateManager;
            }

            @Override
            public CertificateValidator getCertificateValidator() {
                return serverCertificateValidator;
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
        return transport;
    }

    private EndpointDescription newEndpointDescription(
        SecurityPolicy securityPolicy,
        MessageSecurityMode messageSecurityMode
    ) {

        return new EndpointDescription(
            "opc.tcp://localhost:12685",
            new ApplicationDescription(
                "uri:server",
                "productUri",
                LocalizedText.NULL_VALUE,
                ApplicationType.Server,
                null, null,
                new String[]{"opc.tcp://localhost:12685"}
            ),
            ByteString.of(serverCertificateBytes),
            messageSecurityMode,
            securityPolicy.getUri(),
            new UserTokenPolicy[]{
                new UserTokenPolicy(
                    "anonymous",
                    UserTokenType.Anonymous,
                    null, null, null
                )
            },
            TransportProfile.TCP_UASC_UABINARY.getUri(),
            ubyte(0)
        );
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