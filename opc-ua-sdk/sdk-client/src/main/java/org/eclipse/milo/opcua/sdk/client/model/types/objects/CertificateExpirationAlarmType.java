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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface CertificateExpirationAlarmType extends SystemOffNormalAlarmType {
    QualifiedProperty<DateTime> EXPIRATION_DATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ExpirationDate",
        NodeId.parse("ns=0;i=13"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<Double> EXPIRATION_LIMIT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ExpirationLimit",
        NodeId.parse("ns=0;i=290"),
        ValueRanks.Scalar,
        Double.class
    );

    QualifiedProperty<NodeId> CERTIFICATE_TYPE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CertificateType",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<ByteString> CERTIFICATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Certificate",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.Scalar,
        ByteString.class
    );

    CompletableFuture<? extends PropertyType> getExpirationDateNode();

    CompletableFuture<DateTime> getExpirationDate();

    CompletableFuture<StatusCode> setExpirationDate(DateTime value);

    CompletableFuture<? extends PropertyType> getExpirationLimitNode();

    CompletableFuture<Double> getExpirationLimit();

    CompletableFuture<StatusCode> setExpirationLimit(Double value);

    CompletableFuture<? extends PropertyType> getCertificateTypeNode();

    CompletableFuture<NodeId> getCertificateType();

    CompletableFuture<StatusCode> setCertificateType(NodeId value);

    CompletableFuture<? extends PropertyType> getCertificateNode();

    CompletableFuture<ByteString> getCertificate();

    CompletableFuture<StatusCode> setCertificate(ByteString value);
}
