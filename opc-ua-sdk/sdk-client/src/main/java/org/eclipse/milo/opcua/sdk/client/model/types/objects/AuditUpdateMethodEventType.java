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

public interface AuditUpdateMethodEventType extends AuditEventType {
    QualifiedProperty<NodeId> METHOD_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MethodId",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<Object[]> INPUT_ARGUMENTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InputArguments",
        NodeId.parse("ns=0;i=24"),
        ValueRanks.OneDimension,
        Object[].class
    );

    CompletableFuture<? extends PropertyType> getMethodIdNode();

    CompletableFuture<NodeId> getMethodId();

    CompletableFuture<StatusCode> setMethodId(NodeId value);

    CompletableFuture<? extends PropertyType> getInputArgumentsNode();

    CompletableFuture<Object[]> getInputArguments();

    CompletableFuture<StatusCode> setInputArguments(Object[] value);
}
