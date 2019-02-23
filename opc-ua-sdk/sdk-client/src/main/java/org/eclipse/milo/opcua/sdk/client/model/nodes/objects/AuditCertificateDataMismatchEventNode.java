/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditCertificateDataMismatchEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AuditCertificateDataMismatchEventNode extends AuditCertificateEventNode implements AuditCertificateDataMismatchEventType {
    public AuditCertificateDataMismatchEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getInvalidHostnameNode() {
        return getPropertyNode(AuditCertificateDataMismatchEventType.INVALID_HOSTNAME);
    }

    public CompletableFuture<String> getInvalidHostname() {
        return getProperty(AuditCertificateDataMismatchEventType.INVALID_HOSTNAME);
    }

    public CompletableFuture<StatusCode> setInvalidHostname(String value) {
        return setProperty(AuditCertificateDataMismatchEventType.INVALID_HOSTNAME, value);
    }

    public CompletableFuture<PropertyNode> getInvalidUriNode() {
        return getPropertyNode(AuditCertificateDataMismatchEventType.INVALID_URI);
    }

    public CompletableFuture<String> getInvalidUri() {
        return getProperty(AuditCertificateDataMismatchEventType.INVALID_URI);
    }

    public CompletableFuture<StatusCode> setInvalidUri(String value) {
        return setProperty(AuditCertificateDataMismatchEventType.INVALID_URI, value);
    }
}
