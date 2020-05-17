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

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NamespacesType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerCapabilitiesType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerDiagnosticsType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerRedundancyType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.VendorServerInfoType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ServerStatusType;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class ServerTypeNode extends BaseObjectTypeNode implements ServerType {

    public ServerTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass, QualifiedName browseName, LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public String[] getServerArray() {
        return new String[0];
    }

    @Override
    public void setServerArray(String[] value) {

    }

    @Override
    public String[] readServerArray() throws UaException {
        return new String[0];
    }

    @Override
    public void writeServerArray(String[] value) throws UaException {

    }

    @Override
    public CompletableFuture<String[]> readServerArrayAsync() {
        return null;
    }

    @Override
    public CompletableFuture<Unit> writeServerArrayAsync(String[] value) {
        return null;
    }

    @Override
    public PropertyType getServerArrayNode() {
        return null;
    }

    @Override
    public String[] getNamespaceArray() {
        return new String[0];
    }

    @Override
    public void setNamespaceArray(String[] value) {

    }

    @Override
    public PropertyType getNamespaceArrayNode() {
        return null;
    }

    @Override
    public UByte getServiceLevel() {
        return null;
    }

    @Override
    public void setServiceLevel(UByte value) {

    }

    @Override
    public PropertyType getServiceLevelNode() {
        return null;
    }

    @Override
    public Boolean getAuditing() {
        return null;
    }

    @Override
    public void setAuditing(Boolean value) {

    }

    @Override
    public PropertyType getAuditingNode() {
        return null;
    }

    @Nullable
    @Override
    public DateTime getEstimatedReturnTime() {
        return null;
    }

    @Override
    public void setEstimatedReturnTime(DateTime value) {

    }

    @Nullable
    @Override
    public PropertyType getEstimatedReturnTimeNode() {
        return null;
    }

    @Override
    public ServerCapabilitiesType getServerCapabilitiesNode() {
        return null;
    }

    @Override
    public ServerDiagnosticsType getServerDiagnosticsNode() {
        return null;
    }

    @Override
    public VendorServerInfoType getVendorServerInfoNode() {
        return null;
    }

    @Override
    public ServerRedundancyType getServerRedundancyNode() {
        return null;
    }

    @Override
    public NamespacesType getNamespacesNode() {
        return null;
    }

    @Override
    public ServerStatusDataType getServerStatus() {
        return null;
    }

    @Override
    public void setServerStatus(ServerStatusDataType value) {

    }

    @Override
    public ServerStatusDataType readServerStatus() throws UaException {
        return null;
    }

    @Override
    public void writeServerStatus(ServerStatusDataType value) throws UaException {

    }

    @Override
    public CompletableFuture<ServerStatusDataType> readServerStatusAsync() {
        return null;
    }

    @Override
    public CompletableFuture<StatusCode> writeServerStatusAsync(ServerStatusDataType value) {
        return null;
    }

    @Override
    public ServerStatusType getServerStatusNode() {
        return null;
    }
}
