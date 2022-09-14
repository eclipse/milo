/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.api;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.AddressSpace;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.services.AttributeServices;
import org.eclipse.milo.opcua.sdk.client.api.services.MethodServices;
import org.eclipse.milo.opcua.sdk.client.api.services.MonitoredItemServices;
import org.eclipse.milo.opcua.sdk.client.api.services.NodeManagementServices;
import org.eclipse.milo.opcua.sdk.client.api.services.SubscriptionServices;
import org.eclipse.milo.opcua.sdk.client.api.services.ViewServices;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscriptionManager;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaSubscriptionManager;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;

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
     * @return the {@link OpcUaSubscriptionManager} for this client.
     */
    UaSubscriptionManager getSubscriptionManager();

    /**
     * Send a {@link UaRequestMessageType}.
     *
     * @param request the request to send.
     * @return a {@link CompletableFuture} holding the response.
     */
    <T extends UaResponseMessageType> CompletableFuture<T> sendRequest(UaRequestMessageType request);

}
