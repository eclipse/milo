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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface AuditWriteUpdateEventType extends AuditUpdateEventType {
    QualifiedProperty<UInteger> ATTRIBUTE_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AttributeId",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<String> INDEX_RANGE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "IndexRange",
        NodeId.parse("ns=0;i=291"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<Object> OLD_VALUE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldValue",
        NodeId.parse("ns=0;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    QualifiedProperty<Object> NEW_VALUE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NewValue",
        NodeId.parse("ns=0;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    CompletableFuture<? extends PropertyType> getAttributeIdNode();

    CompletableFuture<UInteger> getAttributeId();

    CompletableFuture<StatusCode> setAttributeId(UInteger value);

    CompletableFuture<? extends PropertyType> getIndexRangeNode();

    CompletableFuture<String> getIndexRange();

    CompletableFuture<StatusCode> setIndexRange(String value);

    CompletableFuture<? extends PropertyType> getOldValueNode();

    CompletableFuture<?> getOldValue();

    CompletableFuture<StatusCode> setOldValue(Object value);

    CompletableFuture<? extends PropertyType> getNewValueNode();

    CompletableFuture<?> getNewValue();

    CompletableFuture<StatusCode> setNewValue(Object value);
}
