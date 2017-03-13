/*
 * Copyright (c) 2017 Stefan Profanter
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

package org.eclipse.milo.opcua.sdk.server.discovery;


import java.io.File;
import java.util.EnumSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.ImmutableList;
import org.eclipse.milo.opcua.sdk.common.KeyStoreLoader;
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

public class ServerRunner {

    public OpcUaServer serverLds;
    public OpcUaServer serverRegister;

    public ServerRunner(int registerTimeoutSeconds) {
        this.serverLds = createLds(registerTimeoutSeconds);
        this.serverRegister = createRegister();
    }

    private UsernameIdentityValidator getIdentityValidator() {
        return new UsernameIdentityValidator(true, authChallenge -> {
            String username = authChallenge.getUsername();
            String password = authChallenge.getPassword();

            boolean userOk = "user".equals(username) && "password1".equals(password);
            boolean adminOk = "admin".equals(username) && "password2".equals(password);

            return userOk || adminOk;
        });
    }

    private OpcUaServer createLds(int registerTimeoutSeconds) {
        CryptoRestrictions.remove();

        KeyStoreLoader loader = null;
        try {
            loader = new KeyStoreLoader().load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DefaultCertificateManager certificateManager = new DefaultCertificateManager(
            loader.getServerKeyPair(),
            loader.getServerCertificate()
        );

        File securityTempDir = new File(System.getProperty("java.io.tmpdir"), "security");

        LoggerFactory.getLogger(getClass())
            .info("security temp dir: {}", securityTempDir.getAbsolutePath());

        DefaultCertificateValidator certificateValidator = new DefaultCertificateValidator(securityTempDir);

        OpcUaServerConfig serverConfig = OpcUaServerConfig.builder()
            .setApplicationUri("urn:eclipse:milo:examples:server_lds")
            .setApplicationName(LocalizedText.english("Eclipse Milo OPC-UA Example Discovery Server"))
            .setBindAddresses(newArrayList("0.0.0.0"))
            .setBindPort(4840)
            .setBuildInfo(
                new BuildInfo(
                    "urn:eclipse:milo:test::lds_server",
                    "eclipse",
                    "eclipse milo example server LDS",
                    OpcUaServer.SDK_VERSION,
                    "", DateTime.now()))
            .setCertificateManager(certificateManager)
            .setCertificateValidator(certificateValidator)
            .setIdentityValidator(getIdentityValidator())
            .setProductUri("urn:eclipse:milo:example-lds-server")
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
            .setDiscoveryServerEnabled(true)
            .setMulticastEnabled(true)
            .setRegisterTimeoutSeconds(registerTimeoutSeconds)
            .build();

        return new OpcUaServer(serverConfig);
    }

    private OpcUaServer createRegister() {
        CryptoRestrictions.remove();

        KeyStoreLoader loader = null;
        try {
            loader = new KeyStoreLoader().load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DefaultCertificateManager certificateManager = new DefaultCertificateManager(
            loader.getServerKeyPair(),
            loader.getServerCertificate()
        );

        File securityTempDir = new File(System.getProperty("java.io.tmpdir"), "security");

        LoggerFactory.getLogger(getClass())
            .info("security temp dir: {}", securityTempDir.getAbsolutePath());

        DefaultCertificateValidator certificateValidator = new DefaultCertificateValidator(securityTempDir);

        OpcUaServerConfig serverConfig = OpcUaServerConfig.builder()
            .setApplicationUri("urn:eclipse:milo:examples:server_register")
            .setApplicationName(new LocalizedText("de","Anmeldungsserver"))
            .setBindAddresses(newArrayList("0.0.0.0"))
            .setBindPort(12687)
            .setBuildInfo(
                new BuildInfo(
                    "urn:eclipse:milo:test::register_server",
                    "eclipse",
                    "eclipse milo example server Register",
                    OpcUaServer.SDK_VERSION,
                    "", DateTime.now()))
            .setCertificateManager(certificateManager)
            .setCertificateValidator(certificateValidator)
            .setIdentityValidator(getIdentityValidator())
            .setProductUri("urn:eclipse:milo:example-register-server")
            .setServerName("register")
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
            .setDiscoveryServerEnabled(true)
            .setMulticastEnabled(true)
            .build();

        return new OpcUaServer(serverConfig);
    }

    public void startLds() throws ExecutionException, InterruptedException {
        this.serverLds.startup().get();
    }

    public void stopLds() throws ExecutionException, InterruptedException {
        serverLds.shutdown().get();
    }

    public void startRegisterServer() throws ExecutionException, InterruptedException {
        this.serverRegister.startup().get();
    }

    public void stopRegisterServer() throws ExecutionException, InterruptedException {
        this.serverRegister.shutdown().get();
    }
}
