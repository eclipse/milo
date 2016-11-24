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

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public interface AuditEventType extends BaseEventType {

    Property<DateTime> ACTION_TIME_STAMP = new BasicProperty<>(
        QualifiedName.parse("0:ActionTimeStamp"),
        NodeId.parse("ns=0;i=294"),
        -1,
        DateTime.class
    );

    Property<Boolean> STATUS = new BasicProperty<>(
        QualifiedName.parse("0:Status"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<String> SERVER_ID = new BasicProperty<>(
        QualifiedName.parse("0:ServerId"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<String> CLIENT_AUDIT_ENTRY_ID = new BasicProperty<>(
        QualifiedName.parse("0:ClientAuditEntryId"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<String> CLIENT_USER_ID = new BasicProperty<>(
        QualifiedName.parse("0:ClientUserId"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );


    CompletableFuture<? extends PropertyType> actionTimeStamp();

    CompletableFuture<DateTime> getActionTimeStamp();

    CompletableFuture<StatusCode> setActionTimeStamp(DateTime value);

    CompletableFuture<? extends PropertyType> status();

    CompletableFuture<Boolean> getStatus();

    CompletableFuture<StatusCode> setStatus(Boolean value);

    CompletableFuture<? extends PropertyType> serverId();

    CompletableFuture<String> getServerId();

    CompletableFuture<StatusCode> setServerId(String value);

    CompletableFuture<? extends PropertyType> clientAuditEntryId();

    CompletableFuture<String> getClientAuditEntryId();

    CompletableFuture<StatusCode> setClientAuditEntryId(String value);

    CompletableFuture<? extends PropertyType> clientUserId();

    CompletableFuture<String> getClientUserId();

    CompletableFuture<StatusCode> setClientUserId(String value);


}