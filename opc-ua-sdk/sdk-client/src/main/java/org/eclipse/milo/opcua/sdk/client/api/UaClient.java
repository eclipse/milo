/*
 * Copyright (c) 2016 Kevin Herron and others
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

package org.eclipse.milo.opcua.sdk.client.api;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.services.AttributeServices;
import org.eclipse.milo.opcua.sdk.client.api.services.MethodServices;
import org.eclipse.milo.opcua.sdk.client.api.services.MonitoredItemServices;
import org.eclipse.milo.opcua.sdk.client.api.services.NodeManagementServices;
import org.eclipse.milo.opcua.sdk.client.api.services.SubscriptionServices;
import org.eclipse.milo.opcua.sdk.client.api.services.ViewServices;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscriptionManager;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaSubscriptionManager;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;

public interface UaClient extends AttributeServices,
    MethodServices, MonitoredItemServices, NodeManagementServices, SubscriptionServices, ViewServices {

    /**
     * @return the {@link OpcUaClientConfig} for this client.
     */
    OpcUaClientConfig getConfig();

    /**
     * Connect to the configured endpoint.
     *
     * @return a {@link CompletableFuture} holding this client instance.
     */
    CompletableFuture<? extends UaClient> connect();

    /**
     * Disconnect from the configured endpoint.
     *
     * @return a {@link CompletableFuture} holding this client instance.
     */
    CompletableFuture<? extends UaClient> disconnect();

    /**
     * @return a {@link CompletableFuture} holding the {@link UaSession}.
     */
    CompletableFuture<? extends UaSession> getSession();

    /**
     * @return the {@link AddressSpace}.
     */
    AddressSpace getAddressSpace();

    /**
     * @return the {@link NodeCache}.
     */
    NodeCache getNodeCache();

    /**
     * @return the {@link OpcUaSubscriptionManager} for this client.
     */
    UaSubscriptionManager getSubscriptionManager();

    /**
     * Send a {@link UaRequestMessage}.
     *
     * @param request the request to send.
     * @return a {@link CompletableFuture} holding the response.
     */
    <T extends UaResponseMessage> CompletableFuture<T> sendRequest(UaRequestMessage request);

}
