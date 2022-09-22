/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.server.security.DefaultServerCertificateValidator;
import org.eclipse.milo.opcua.stack.transport.server.tcp.OpcTcpServerTransport;
import org.eclipse.milo.opcua.stack.transport.server.tcp.OpcTcpServerTransportConfig;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;

public class Test {

    static final UserTokenPolicy USER_TOKEN_POLICY_ANONYMOUS = new UserTokenPolicy(
        "anonymous",
        UserTokenType.Anonymous,
        null,
        null,
        null
    );

    public static void main(String[] args) throws Exception {
        var endpoint = new EndpointDescription(
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

        var application = new ServerApplication() {

            private final AtomicLong secureChannelId = new AtomicLong(0L);
            private final AtomicLong secureChannelTokenId = new AtomicLong(0L);

            @Override
            public List<EndpointDescription> getEndpointDescriptions() {
                return List.of(endpoint);
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
            public CompletableFuture<UaResponseMessageType> handleServiceRequest(ServiceRequestContext context, UaRequestMessageType requestMessage) {
                System.out.println("request: " + requestMessage);

                return CompletableFuture.failedFuture(new RuntimeException("not implemented"));
            }

        };


        OpcTcpServerTransportConfig config = OpcTcpServerTransportConfig.newBuilder().build();

        var transport = new OpcTcpServerTransport(config);
        transport.bind(application);

        Thread.sleep(Integer.MAX_VALUE);
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
