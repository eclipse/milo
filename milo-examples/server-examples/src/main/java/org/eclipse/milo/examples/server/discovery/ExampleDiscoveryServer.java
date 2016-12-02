/*
 * Copyright (c) 2016 Kevin Herron, Stefan Profanter
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


package org.eclipse.milo.examples.server.discovery;

import java.io.File;
import java.util.EnumSet;
import java.util.concurrent.CompletableFuture;

import com.google.common.collect.ImmutableList;
import org.eclipse.milo.examples.server.KeyStoreLoader;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig;
import org.eclipse.milo.opcua.sdk.server.identity.UsernameIdentityValidator;
import org.eclipse.milo.opcua.stack.core.application.DefaultCertificateManager;
import org.eclipse.milo.opcua.stack.core.application.DefaultCertificateValidator;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;
import org.eclipse.milo.opcua.stack.core.util.CryptoRestrictions;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig.USER_TOKEN_POLICY_ANONYMOUS;
import static org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig.USER_TOKEN_POLICY_USERNAME;

public class ExampleDiscoveryServer {
    public static void main(String[] args) throws Exception {
        ExampleDiscoveryServer server = new ExampleDiscoveryServer();

        server.startup().get();

        final CompletableFuture<Void> future = new CompletableFuture<>();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> server.shutdown().thenRun(() -> future.complete(null))));

        future.get();
    }

    private final OpcUaServer server;

    private ExampleDiscoveryServer() throws Exception {
        CryptoRestrictions.remove();

        KeyStoreLoader loader = new KeyStoreLoader().load();

        DefaultCertificateManager certificateManager = new DefaultCertificateManager(
                loader.getServerKeyPair(),
                loader.getServerCertificate()
        );

        File securityTempDir = new File(System.getProperty("java.io.tmpdir"), "security");

        LoggerFactory.getLogger(getClass())
                .info("security temp dir: {}", securityTempDir.getAbsolutePath());

        DefaultCertificateValidator certificateValidator = new DefaultCertificateValidator(securityTempDir);

        UsernameIdentityValidator identityValidator = new UsernameIdentityValidator(true, authChallenge -> {
            String username = authChallenge.getUsername();
            String password = authChallenge.getPassword();

            boolean userOk = "user".equals(username) && "password1".equals(password);
            boolean adminOk = "admin".equals(username) && "password2".equals(password);

            return userOk || adminOk;
        });

        OpcUaServerConfig serverConfig = OpcUaServerConfig.builder()
            .setApplicationUri("urn:eclipse:milo:examples:server")
            .setApplicationName(LocalizedText.english("Eclipse Milo OPC-UA Example Discovery Server"))
            .setBindAddresses(newArrayList("0.0.0.0"))
            .setBindPort(4840)
            .setBuildInfo(
                    new BuildInfo(
                            "urn:eclipse:milo:example-server",
                            "eclipse",
                            "eclipse milo example server",
                            OpcUaServer.SDK_VERSION,
                            "", DateTime.now()))
            .setCertificateManager(certificateManager)
            .setCertificateValidator(certificateValidator)
            .setIdentityValidator(identityValidator)
            .setProductUri("urn:eclipse:milo:example-discovery-server")
            .setServerName("discovery")
            .setSecurityPolicies(
                    EnumSet.of(
                            SecurityPolicy.None,
                            SecurityPolicy.Basic128Rsa15,
                            SecurityPolicy.Basic256,
                            SecurityPolicy.Basic256Sha256))
            .setUserTokenPolicies(
                    ImmutableList.of(
                            USER_TOKEN_POLICY_ANONYMOUS,
                            USER_TOKEN_POLICY_USERNAME))
            .setIsDiscoveryServer(true)
            .setEnableMulticast(true)
            .build();

        server = new OpcUaServer(serverConfig);

    }

    public OpcUaServer getServer() {
        return server;
    }

    private CompletableFuture<OpcUaServer> startup() {
        return server.startup();
    }

    private CompletableFuture<OpcUaServer> shutdown() {
        return server.shutdown();
    }
}
