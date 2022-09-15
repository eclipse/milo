/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.session.SessionFsm;
import org.eclipse.milo.opcua.sdk.core.types.DataTypeTree;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.util.Unit;

/**
 * Builds a {@link DataTypeTree} and stores it on an {@link OpcUaSession} as an attribute under
 * the key {@link DataTypeTreeSessionInitializer#SESSION_ATTRIBUTE_KEY}.
 */
public class DataTypeTreeSessionInitializer implements SessionFsm.SessionInitializer {

    /**
     * The attribute key that the {@link DataTypeTree} will be stored under in the {@link OpcUaSession}.
     *
     * @see OpcUaSession#getAttribute(String)
     * @see OpcUaSession#setAttribute(String, Object)
     */
    public static final String SESSION_ATTRIBUTE_KEY = "dataTypeTree";

    @Override
    public CompletableFuture<Unit> initialize(UaStackClient stackClient, OpcUaSession session) {
        return DataTypeTreeBuilder.buildAsync(stackClient, session)
            .thenAccept(tree -> session.setAttribute(SESSION_ATTRIBUTE_KEY, tree))
            .thenApply(v -> Unit.VALUE);
    }

}
