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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface ServerConfigurationType extends BaseObjectType {
    QualifiedProperty<String[]> SERVER_CAPABILITIES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServerCapabilities",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<String[]> SUPPORTED_PRIVATE_KEY_FORMATS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SupportedPrivateKeyFormats",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<UInteger> MAX_TRUST_LIST_SIZE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxTrustListSize",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<Boolean> MULTICAST_DNS_ENABLED = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MulticastDnsEnabled",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    CompletableFuture<? extends PropertyType> getServerCapabilitiesNode();

    CompletableFuture<String[]> getServerCapabilities();

    CompletableFuture<StatusCode> setServerCapabilities(String[] value);

    CompletableFuture<? extends PropertyType> getSupportedPrivateKeyFormatsNode();

    CompletableFuture<String[]> getSupportedPrivateKeyFormats();

    CompletableFuture<StatusCode> setSupportedPrivateKeyFormats(String[] value);

    CompletableFuture<? extends PropertyType> getMaxTrustListSizeNode();

    CompletableFuture<UInteger> getMaxTrustListSize();

    CompletableFuture<StatusCode> setMaxTrustListSize(UInteger value);

    CompletableFuture<? extends PropertyType> getMulticastDnsEnabledNode();

    CompletableFuture<Boolean> getMulticastDnsEnabled();

    CompletableFuture<StatusCode> setMulticastDnsEnabled(Boolean value);

    CompletableFuture<? extends CertificateGroupFolderType> getCertificateGroupsNode();
}
