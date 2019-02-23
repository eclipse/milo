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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ServerStatusType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;

public interface ServerType extends BaseObjectType {
    QualifiedProperty<String[]> SERVER_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServerArray",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<String[]> NAMESPACE_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NamespaceArray",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<UByte> SERVICE_LEVEL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServiceLevel",
        NodeId.parse("ns=0;i=3"),
        ValueRanks.Scalar,
        UByte.class
    );

    QualifiedProperty<Boolean> AUDITING = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Auditing",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<DateTime> ESTIMATED_RETURN_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EstimatedReturnTime",
        NodeId.parse("ns=0;i=13"),
        ValueRanks.Scalar,
        DateTime.class
    );

    CompletableFuture<? extends PropertyType> getServerArrayNode();

    CompletableFuture<String[]> getServerArray();

    CompletableFuture<StatusCode> setServerArray(String[] value);

    CompletableFuture<? extends PropertyType> getNamespaceArrayNode();

    CompletableFuture<String[]> getNamespaceArray();

    CompletableFuture<StatusCode> setNamespaceArray(String[] value);

    CompletableFuture<? extends PropertyType> getServiceLevelNode();

    CompletableFuture<UByte> getServiceLevel();

    CompletableFuture<StatusCode> setServiceLevel(UByte value);

    CompletableFuture<? extends PropertyType> getAuditingNode();

    CompletableFuture<Boolean> getAuditing();

    CompletableFuture<StatusCode> setAuditing(Boolean value);

    CompletableFuture<? extends PropertyType> getEstimatedReturnTimeNode();

    CompletableFuture<DateTime> getEstimatedReturnTime();

    CompletableFuture<StatusCode> setEstimatedReturnTime(DateTime value);

    CompletableFuture<? extends ServerStatusType> getServerStatusNode();

    CompletableFuture<ServerStatusDataType> getServerStatus();

    CompletableFuture<StatusCode> setServerStatus(ServerStatusDataType value);

    CompletableFuture<? extends ServerCapabilitiesType> getServerCapabilitiesNode();

    CompletableFuture<? extends ServerDiagnosticsType> getServerDiagnosticsNode();

    CompletableFuture<? extends VendorServerInfoType> getVendorServerInfoNode();

    CompletableFuture<? extends ServerRedundancyType> getServerRedundancyNode();

    CompletableFuture<? extends NamespacesType> getNamespacesNode();
}
