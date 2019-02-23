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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.CertificateExpirationAlarmType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class CertificateExpirationAlarmNode extends SystemOffNormalAlarmNode implements CertificateExpirationAlarmType {
    public CertificateExpirationAlarmNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getExpirationDateNode() {
        return getPropertyNode(CertificateExpirationAlarmType.EXPIRATION_DATE);
    }

    public CompletableFuture<DateTime> getExpirationDate() {
        return getProperty(CertificateExpirationAlarmType.EXPIRATION_DATE);
    }

    public CompletableFuture<StatusCode> setExpirationDate(DateTime value) {
        return setProperty(CertificateExpirationAlarmType.EXPIRATION_DATE, value);
    }

    public CompletableFuture<PropertyNode> getExpirationLimitNode() {
        return getPropertyNode(CertificateExpirationAlarmType.EXPIRATION_LIMIT);
    }

    public CompletableFuture<Double> getExpirationLimit() {
        return getProperty(CertificateExpirationAlarmType.EXPIRATION_LIMIT);
    }

    public CompletableFuture<StatusCode> setExpirationLimit(Double value) {
        return setProperty(CertificateExpirationAlarmType.EXPIRATION_LIMIT, value);
    }

    public CompletableFuture<PropertyNode> getCertificateTypeNode() {
        return getPropertyNode(CertificateExpirationAlarmType.CERTIFICATE_TYPE);
    }

    public CompletableFuture<NodeId> getCertificateType() {
        return getProperty(CertificateExpirationAlarmType.CERTIFICATE_TYPE);
    }

    public CompletableFuture<StatusCode> setCertificateType(NodeId value) {
        return setProperty(CertificateExpirationAlarmType.CERTIFICATE_TYPE, value);
    }

    public CompletableFuture<PropertyNode> getCertificateNode() {
        return getPropertyNode(CertificateExpirationAlarmType.CERTIFICATE);
    }

    public CompletableFuture<ByteString> getCertificate() {
        return getProperty(CertificateExpirationAlarmType.CERTIFICATE);
    }

    public CompletableFuture<StatusCode> setCertificate(ByteString value) {
        return setProperty(CertificateExpirationAlarmType.CERTIFICATE, value);
    }
}
