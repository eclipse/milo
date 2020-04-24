/*
 * Copyright (c) 2019 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class DataTypeTreeSessionInitializer implements SessionFsm.SessionInitializer {

    public static final String SESSION_ATTRIBUTE_KEY = "dataTypeTree";

    @Override
    public CompletableFuture<Unit> initialize(UaStackClient stackClient, OpcUaSession session) {
        return DataTypeTreeBuilder.build(stackClient, session)
            .thenAccept(tree -> session.setAttribute(SESSION_ATTRIBUTE_KEY, tree))
            .thenApply(v -> Unit.VALUE);
    }

}
