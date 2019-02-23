/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
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
