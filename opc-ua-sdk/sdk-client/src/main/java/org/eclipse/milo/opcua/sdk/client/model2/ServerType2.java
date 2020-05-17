/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model2;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.client.model.types.objects.BaseObjectType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NamespacesType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerCapabilitiesType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerDiagnosticsType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerRedundancyType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.VendorServerInfoType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ServerStatusType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface ServerType2 extends BaseObjectType {
    QualifiedProperty<String[]> SERVER_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServerArray",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<String[]> NAMESPACE_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NamespaceArray",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<UByte> SERVICE_LEVEL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServiceLevel",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=3"),
        ValueRanks.Scalar,
        UByte.class
    );

    QualifiedProperty<Boolean> AUDITING = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Auditing",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<DateTime> ESTIMATED_RETURN_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EstimatedReturnTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=13"),
        ValueRanks.Scalar,
        DateTime.class
    );

    //region Properties

    //region ServerArray

    /**
     * Get the local value of the ServerArray Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ServerArray Node.
     * @throws UaException if an error occurs creating or getting the ServerArray Node.
     */
    String[] getServerArray() throws UaException;

    /**
     * Set the local value of the ServerArray Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param value the local value to set for the ServerArray Node.
     * @throws UaException if an error occurs creating or getting the ServerArray Node.
     */
    void setServerArray(String[] value) throws UaException;

    /**
     * Read the value of the ServerArray Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String[] readServerArray() throws UaException;

    /**
     * Write a new value for the ServerArray Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param value the {@link String[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeServerArray(String[] value) throws UaException;

    /**
     * An asynchronous implementation of {@link #readServerArray()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<String[]> readServerArrayAsync();

    /**
     * An asynchronous implementation of {@link #writeServerArray(String[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeServerArrayAsync(String[] value);

    /**
     * Get the ServerArray {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ServerArray {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getServerArrayNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getServerArrayNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<PropertyType> getServerArrayNodeAsync();

    //endregion

    //region NamespaceArray

    String[] getNamespaceArray();

    void setNamespaceArray(String[] value);

    PropertyType getNamespaceArrayNode();

    //endregion

    UByte getServiceLevel();

    void setServiceLevel(UByte value);

    PropertyType getServiceLevelNode();

    Boolean getAuditing();

    void setAuditing(Boolean value);

    PropertyType getAuditingNode();

    @Nullable
    DateTime getEstimatedReturnTime();

    void setEstimatedReturnTime(DateTime value);

    @Nullable
    PropertyType getEstimatedReturnTimeNode();

    //endregion

    //region Object Components

    /**
     * Get the ServerCapabilitiesType Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ServerCapabilitiesType Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    ServerCapabilitiesType getServerCapabilitiesNode() throws UaException;

    ServerDiagnosticsType getServerDiagnosticsNode();

    VendorServerInfoType getVendorServerInfoNode();

    ServerRedundancyType getServerRedundancyNode();

    NamespacesType getNamespacesNode();

    //endregion

    //region Variable Components

    //region ServerStatus

    ServerStatusDataType getServerStatus();

    void setServerStatus(ServerStatusDataType value);

    ServerStatusDataType readServerStatus() throws UaException;

    void writeServerStatus(ServerStatusDataType value) throws UaException;

    CompletableFuture<ServerStatusDataType> readServerStatusAsync();

    CompletableFuture<StatusCode> writeServerStatusAsync(ServerStatusDataType value);

    ServerStatusType getServerStatusNode();

    //endregion ServerStatus

    //endregion Variable Components

    //region Methods

    // TODO methods

    //endregion

}
