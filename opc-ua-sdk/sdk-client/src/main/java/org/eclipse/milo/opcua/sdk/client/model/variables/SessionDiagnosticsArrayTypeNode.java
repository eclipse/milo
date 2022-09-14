/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessLevelExType;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;

public class SessionDiagnosticsArrayTypeNode extends BaseDataVariableTypeNode implements SessionDiagnosticsArrayType {
    public SessionDiagnosticsArrayTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                           QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                           UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                           RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                           DataValue value, NodeId dataType, Integer valueRank, UInteger[] arrayDimensions,
                                           UByte accessLevel, UByte userAccessLevel, Double minimumSamplingInterval, Boolean historizing,
                                           AccessLevelExType accessLevelEx) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing, accessLevelEx);
    }

    @Override
    public SessionDiagnosticsDataType getSessionDiagnostics() throws UaException {
        SessionDiagnosticsVariableTypeNode node = getSessionDiagnosticsNode();
        return cast(node.getValue().getValue().getValue(), SessionDiagnosticsDataType.class);
    }

    @Override
    public void setSessionDiagnostics(SessionDiagnosticsDataType value) throws UaException {
        SessionDiagnosticsVariableTypeNode node = getSessionDiagnosticsNode();
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticEncodingContext(), value);
        node.setValue(new Variant(encoded));
    }

    @Override
    public SessionDiagnosticsDataType readSessionDiagnostics() throws UaException {
        try {
            return readSessionDiagnosticsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSessionDiagnostics(SessionDiagnosticsDataType value) throws UaException {
        try {
            writeSessionDiagnosticsAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SessionDiagnosticsDataType> readSessionDiagnosticsAsync() {
        return getSessionDiagnosticsNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> cast(v.getValue().getValue(), SessionDiagnosticsDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeSessionDiagnosticsAsync(
        SessionDiagnosticsDataType sessionDiagnostics) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticEncodingContext(), sessionDiagnostics);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getSessionDiagnosticsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public SessionDiagnosticsVariableTypeNode getSessionDiagnosticsNode() throws UaException {
        try {
            return getSessionDiagnosticsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends SessionDiagnosticsVariableTypeNode> getSessionDiagnosticsNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "SessionDiagnostics",
            ExpandedNodeId.parse("ns=0;i=47"),
            false
        );
        return future.thenApply(node -> (SessionDiagnosticsVariableTypeNode) node);
    }
}
