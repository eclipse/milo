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
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class BaseAnalogTypeNode extends DataItemTypeNode implements BaseAnalogType {
    public BaseAnalogTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                              QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                              UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                              RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                              DataValue value, NodeId dataType, Integer valueRank, UInteger[] arrayDimensions,
                              UByte accessLevel, UByte userAccessLevel, Double minimumSamplingInterval, Boolean historizing,
                              AccessLevelExType accessLevelEx) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing, accessLevelEx);
    }

    @Override
    public Range getInstrumentRange() throws UaException {
        PropertyTypeNode node = getInstrumentRangeNode();
        return cast(node.getValue().getValue().getValue(), Range.class);
    }

    @Override
    public void setInstrumentRange(Range value) throws UaException {
        PropertyTypeNode node = getInstrumentRangeNode();
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticEncodingContext(), value);
        node.setValue(new Variant(encoded));
    }

    @Override
    public Range readInstrumentRange() throws UaException {
        try {
            return readInstrumentRangeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeInstrumentRange(Range value) throws UaException {
        try {
            writeInstrumentRangeAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Range> readInstrumentRangeAsync() {
        return getInstrumentRangeNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> cast(v.getValue().getValue(), Range.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeInstrumentRangeAsync(Range instrumentRange) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticEncodingContext(), instrumentRange);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getInstrumentRangeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getInstrumentRangeNode() throws UaException {
        try {
            return getInstrumentRangeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getInstrumentRangeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "InstrumentRange",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Range getEuRange() throws UaException {
        PropertyTypeNode node = getEuRangeNode();
        return cast(node.getValue().getValue().getValue(), Range.class);
    }

    @Override
    public void setEuRange(Range value) throws UaException {
        PropertyTypeNode node = getEuRangeNode();
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticEncodingContext(), value);
        node.setValue(new Variant(encoded));
    }

    @Override
    public Range readEuRange() throws UaException {
        try {
            return readEuRangeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEuRange(Range value) throws UaException {
        try {
            writeEuRangeAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Range> readEuRangeAsync() {
        return getEuRangeNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> cast(v.getValue().getValue(), Range.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeEuRangeAsync(Range euRange) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticEncodingContext(), euRange);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getEuRangeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getEuRangeNode() throws UaException {
        try {
            return getEuRangeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getEuRangeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "EURange",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public EUInformation getEngineeringUnits() throws UaException {
        PropertyTypeNode node = getEngineeringUnitsNode();
        return cast(node.getValue().getValue().getValue(), EUInformation.class);
    }

    @Override
    public void setEngineeringUnits(EUInformation value) throws UaException {
        PropertyTypeNode node = getEngineeringUnitsNode();
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticEncodingContext(), value);
        node.setValue(new Variant(encoded));
    }

    @Override
    public EUInformation readEngineeringUnits() throws UaException {
        try {
            return readEngineeringUnitsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEngineeringUnits(EUInformation value) throws UaException {
        try {
            writeEngineeringUnitsAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends EUInformation> readEngineeringUnitsAsync() {
        return getEngineeringUnitsNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> cast(v.getValue().getValue(), EUInformation.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeEngineeringUnitsAsync(EUInformation engineeringUnits) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticEncodingContext(), engineeringUnits);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getEngineeringUnitsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getEngineeringUnitsNode() throws UaException {
        try {
            return getEngineeringUnitsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getEngineeringUnitsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "EngineeringUnits",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
