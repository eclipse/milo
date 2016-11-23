/*
 * Copyright (c) 2016 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.server;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.eclipse.milo.opcua.sdk.core.ServerTable;
import org.eclipse.milo.opcua.sdk.server.api.AbstractServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig;
import org.eclipse.milo.opcua.sdk.server.namespaces.OpcUaNamespace;
import org.eclipse.milo.opcua.sdk.server.namespaces.VendorNamespace;
import org.eclipse.milo.opcua.sdk.server.services.helpers.BrowseHelper.BrowseContinuationPoint;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.stack.core.BuiltinReferenceType;
import org.eclipse.milo.opcua.stack.core.ReferenceType;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.application.UaStackServer;
import org.eclipse.milo.opcua.stack.core.application.services.AttributeServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.MethodServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.MonitoredItemServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.NodeManagementServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.SessionServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.SubscriptionServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.ViewServiceSet;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.core.util.ManifestUtil;
import org.eclipse.milo.opcua.stack.server.tcp.UaTcpStackServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Sets.newHashSet;

public class OpcUaServer {

    public static final String SDK_VERSION =
        ManifestUtil.read("X-SDK-Version").orElse("dev");

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<ByteString, BrowseContinuationPoint> browseContinuationPoints = Maps.newConcurrentMap();

    private final ServerNodeMap nodeMap = new OpcUaServerNodeMap();

    private final Map<NodeId, ReferenceType> referenceTypes = Maps.newConcurrentMap();

    private final Map<UInteger, Subscription> subscriptions = Maps.newConcurrentMap();

    private final NamespaceManager namespaceManager = new NamespaceManager();
    private final SessionManager sessionManager = new SessionManager(this);
    private final ServerTable serverTable = new ServerTable();

    private final UaStackServer stackServer;
    private final EventBus eventBus;

    private final OpcUaNamespace uaNamespace;
    private final VendorNamespace vendorNamespace;

    private final OpcUaServerConfig config;

    public OpcUaServer(OpcUaServerConfig config) {
        this.config = config;

        stackServer = new UaTcpStackServer(config);

        stackServer.addServiceSet((AttributeServiceSet) sessionManager);
        stackServer.addServiceSet((MethodServiceSet) sessionManager);
        stackServer.addServiceSet((MonitoredItemServiceSet) sessionManager);
        stackServer.addServiceSet((NodeManagementServiceSet) sessionManager);
        stackServer.addServiceSet((SessionServiceSet) sessionManager);
        stackServer.addServiceSet((SubscriptionServiceSet) sessionManager);
        stackServer.addServiceSet((ViewServiceSet) sessionManager);

        namespaceManager.addNamespace(uaNamespace = new OpcUaNamespace(this));

        vendorNamespace = namespaceManager.registerAndAdd(
            config.getApplicationUri(),
            index -> new VendorNamespace(OpcUaServer.this, config.getApplicationUri()));

        serverTable.addUri(stackServer.getApplicationDescription().getApplicationUri());

        for (ReferenceType referenceType : BuiltinReferenceType.values()) {
            referenceTypes.put(referenceType.getNodeId(), referenceType);
        }

        String configuredHostname = config.getHostname();

        for (String bindAddress : config.getBindAddresses()) {
            Set<String> hostnames = Sets.union(
                newHashSet(configuredHostname),
                config.getHostnameResolver().apply(bindAddress)
            );

            for (String hostname : hostnames) {
                for (SecurityPolicy securityPolicy : config.getSecurityPolicies()) {
                    MessageSecurityMode messageSecurity = securityPolicy == SecurityPolicy.None ?
                        MessageSecurityMode.None : MessageSecurityMode.SignAndEncrypt;

                    String endpointUrl = endpointUrl(hostname, config.getBindPort(), config.getServerName());

                    Set<X509Certificate> certificates = config.getCertificateManager().getCertificates();

                    if (certificates.isEmpty() && securityPolicy == SecurityPolicy.None) {
                        logger.info("Binding endpoint {} to {} [{}/{}]",
                            endpointUrl, bindAddress, securityPolicy, messageSecurity);

                        stackServer.addEndpoint(endpointUrl, bindAddress, null, securityPolicy, messageSecurity);
                    } else {
                        for (X509Certificate certificate : certificates) {
                            logger.info("Binding endpoint {} to {} [{}/{}]",
                                endpointUrl, bindAddress, securityPolicy, messageSecurity);

                            stackServer.addEndpoint(
                                endpointUrl, bindAddress, certificate, securityPolicy, messageSecurity);
                        }
                    }
                }
            }
        }

        eventBus = new AsyncEventBus("server", stackServer.getExecutorService());

        logger.info("eclipse milo opc-ua stack version: {}", Stack.VERSION);
        logger.info("eclipse milo opc-ua sdk version: {}", SDK_VERSION);
    }

    public CompletableFuture<OpcUaServer> startup() {
        return stackServer.startup().thenApply(ignored -> OpcUaServer.this);
    }

    public CompletableFuture<OpcUaServer> shutdown() {
        return stackServer.shutdown().thenApply(ignored -> OpcUaServer.this);
    }

    private static String endpointUrl(String hostname, int port, String serverName) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("opc.tcp://%s:%d", hostname, port));
        if (!serverName.isEmpty()) {
            sb.append("/").append(serverName);
        }
        return sb.toString();
    }

    public OpcUaServerConfig getConfig() {
        return config;
    }

    public NamespaceManager getNamespaceManager() {
        return namespaceManager;
    }

    public ServerNodeMap getNodeMap() {
        return nodeMap;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public OpcUaNamespace getUaNamespace() {
        return uaNamespace;
    }

    public ServerTable getServerTable() {
        return serverTable;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public Map<UInteger, Subscription> getSubscriptions() {
        return subscriptions;
    }

    public Optional<KeyPair> getKeyPair(ByteString thumbprint) {
        return stackServer.getCertificateManager().getKeyPair(thumbprint);
    }

    public Optional<X509Certificate> getCertificate(ByteString thumbprint) {
        return stackServer.getCertificateManager().getCertificate(thumbprint);
    }

    public ExecutorService getExecutorService() {
        return stackServer.getExecutorService();
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return Stack.sharedScheduledExecutor();
    }

    public ChannelConfig getChannelConfig() {
        return stackServer.getChannelConfig();
    }

    public EndpointDescription[] getEndpointDescriptions() {
        return stackServer.getEndpointDescriptions();
    }

    public List<UserTokenPolicy> getUserTokenPolicies() {
        return stackServer.getUserTokenPolicies();
    }

    public ApplicationDescription getApplicationDescription() {
        return stackServer.getApplicationDescription();
    }

    public SignedSoftwareCertificate[] getSoftwareCertificates() {
        return stackServer.getSoftwareCertificates();
    }

    public void closeSecureChannel(ServerSecureChannel secureChannel) {
        stackServer.closeSecureChannel(secureChannel);
    }

    public UaStackServer getServer() {
        return stackServer;
    }

    public Map<NodeId, ReferenceType> getReferenceTypes() {
        return referenceTypes;
    }

    public Map<ByteString, BrowseContinuationPoint> getBrowseContinuationPoints() {
        return browseContinuationPoints;
    }

    /**
     * Given a bind address resolve it to one or more hostnames to be used when building endpoints.
     *
     * @param bindAddress the bind address to resolve.
     * @return the hostnames that will be used to represent this bind address in endpoints.
     */
    public static Set<String> getHostnames(String bindAddress) {
        Set<String> hostnames = newHashSet();

        try {
            InetAddress inetAddress = InetAddress.getByName(bindAddress);

            if (inetAddress.isAnyLocalAddress()) {
                try {
                    Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();

                    for (NetworkInterface ni : Collections.list(nis)) {
                        Collections.list(ni.getInetAddresses()).stream()
                            .filter(ia -> ia instanceof Inet4Address)
                            .forEach(ia -> {
                                hostnames.add(ia.getHostName());
                                hostnames.add(ia.getHostAddress());
                                hostnames.add(ia.getCanonicalHostName());
                            });
                    }
                } catch (SocketException e) {
                    LoggerFactory.getLogger(OpcUaServer.class)
                        .warn("Failed to NetworkInterfaces for bind address: {}", bindAddress, e);
                }
            } else {
                hostnames.add(inetAddress.getHostName());
                hostnames.add(inetAddress.getHostAddress());
                hostnames.add(inetAddress.getCanonicalHostName());
            }
        } catch (UnknownHostException e) {
            LoggerFactory.getLogger(OpcUaServer.class)
                .warn("Failed to get InetAddress for bind address: {}", bindAddress, e);
        }

        return hostnames;
    }

    private static class OpcUaServerNodeMap extends AbstractServerNodeMap {}

}
