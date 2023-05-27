/*
 * Copyright (c) 2023 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.subscriptions2;

import java.util.function.Consumer;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.NotificationMessage;

public class PublishingManager {

    private final OpcUaClient client;

    public PublishingManager(OpcUaClient client) {
        this.client = client;
    }

    void addSubscription(
        UInteger subscriptionId,
        Consumer<NotificationMessage> notificationMessageConsumer
    ) {}

    void removeSubscription(UInteger subscriptionId) {}

}
