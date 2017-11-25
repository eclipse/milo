/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface AuditUpdateStateEventType extends AuditUpdateMethodEventType {
    QualifiedProperty<Object> OLD_STATE_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldStateId",
        NodeId.parse("ns=0;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    QualifiedProperty<Object> NEW_STATE_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NewStateId",
        NodeId.parse("ns=0;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    CompletableFuture<? extends PropertyType> getOldStateIdNode();

    CompletableFuture<?> getOldStateId();

    CompletableFuture<StatusCode> setOldStateId(Object value);

    CompletableFuture<? extends PropertyType> getNewStateIdNode();

    CompletableFuture<?> getNewStateId();

    CompletableFuture<StatusCode> setNewStateId(Object value);
}
