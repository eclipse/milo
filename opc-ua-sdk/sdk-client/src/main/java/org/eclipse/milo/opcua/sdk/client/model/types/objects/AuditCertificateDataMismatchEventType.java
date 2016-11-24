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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public interface AuditCertificateDataMismatchEventType extends AuditCertificateEventType {

    Property<String> INVALID_HOSTNAME = new BasicProperty<>(
        QualifiedName.parse("0:InvalidHostname"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<String> INVALID_URI = new BasicProperty<>(
        QualifiedName.parse("0:InvalidUri"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );


    CompletableFuture<? extends PropertyType> invalidHostname();

    CompletableFuture<String> getInvalidHostname();

    CompletableFuture<StatusCode> setInvalidHostname(String value);

    CompletableFuture<? extends PropertyType> invalidUri();

    CompletableFuture<String> getInvalidUri();

    CompletableFuture<StatusCode> setInvalidUri(String value);


}