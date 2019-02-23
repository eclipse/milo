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

public interface AuditCertificateDataMismatchEventType extends AuditCertificateEventType {
    QualifiedProperty<String> INVALID_HOSTNAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InvalidHostname",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<String> INVALID_URI = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InvalidUri",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    CompletableFuture<? extends PropertyType> getInvalidHostnameNode();

    CompletableFuture<String> getInvalidHostname();

    CompletableFuture<StatusCode> setInvalidHostname(String value);

    CompletableFuture<? extends PropertyType> getInvalidUriNode();

    CompletableFuture<String> getInvalidUri();

    CompletableFuture<StatusCode> setInvalidUri(String value);
}
