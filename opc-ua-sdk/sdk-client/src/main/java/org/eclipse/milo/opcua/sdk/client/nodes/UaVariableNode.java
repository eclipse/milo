/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.nodes;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableNodeProperties;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.TimeZoneDataType;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;

import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class UaVariableNode extends UaNode implements VariableNode {

    private DataValue value;
    private NodeId dataType;
    private Integer valueRank;
    private UInteger[] arrayDimensions;
    private UByte accessLevel;
    private UByte userAccessLevel;
    private Double minimumSamplingInterval;
    private Boolean historizing;

    public UaVariableNode(
        OpcUaClient client,
        NodeId nodeId,
        NodeClass nodeClass,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        DataValue value,
        NodeId dataType,
        Integer valueRank,
        UInteger[] arrayDimensions,
        UByte accessLevel,
        UByte userAccessLevel,
        Double minimumSamplingInterval,
        Boolean historizing
    ) {

        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask);

        this.value = value;
        this.dataType = dataType;
        this.valueRank = valueRank;
        this.arrayDimensions = arrayDimensions;
        this.accessLevel = accessLevel;
        this.userAccessLevel = userAccessLevel;
        this.minimumSamplingInterval = minimumSamplingInterval;
        this.historizing = historizing;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The returned attribute is the most recently seen value; it is not read live from the server.
     *
     * @see #readValue()
     */
    @Override
    public synchronized DataValue getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The returned attribute is the most recently seen value; it is not read live from the server.
     *
     * @see #readDataType()
     */
    @Override
    public synchronized NodeId getDataType() {
        return dataType;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The returned attribute is the most recently seen value; it is not read live from the server.
     *
     * @see #readValueRank()
     */
    @Override
    public synchronized Integer getValueRank() {
        return valueRank;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The returned attribute is the most recently seen value; it is not read live from the server.
     *
     * @see #readArrayDimensions()
     */
    @Override
    public synchronized UInteger[] getArrayDimensions() {
        return arrayDimensions;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The returned attribute is the most recently seen value; it is not read live from the server.
     *
     * @see #readAccessLevel()
     */
    @Override
    public synchronized UByte getAccessLevel() {
        return accessLevel;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The returned attribute is the most recently seen value; it is not read live from the server.
     *
     * @see #readUserAccessLevel()
     */
    @Override
    public synchronized UByte getUserAccessLevel() {
        return userAccessLevel;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The returned attribute is the most recently seen value; it is not read live from the server.
     *
     * @see #readMinimumSamplingInterval()
     */
    @Override
    public synchronized Double getMinimumSamplingInterval() {
        return minimumSamplingInterval;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The returned attribute is the most recently seen value; it is not read live from the server.
     *
     * @see #readHistorizing()
     */
    @Override
    public synchronized Boolean getHistorizing() {
        return historizing;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The attribute is only updated locally; it is not written to the server.
     *
     * @see #writeValue(DataValue)
     */
    @Override
    public synchronized void setValue(DataValue value) {
        this.value = value;
    }

    /**
     * Set the local Value attribute to a {@link DataValue} containing {@code variant} with no
     * StatusCode or timestamp values.
     *
     * @param variant the {@link Variant} to set.
     * @see #setValue(DataValue)
     */
    public synchronized void setValue(Variant variant) {
        setValue(DataValue.valueOnly(variant));
    }

    /**
     * {@inheritDoc}
     * <p>
     * The attribute is only updated locally; it is not written to the server.
     *
     * @see #writeDataType(NodeId)
     */
    @Override
    public synchronized void setDataType(NodeId dataType) {
        this.dataType = dataType;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The attribute is only updated locally; it is not written to the server.
     *
     * @see #writeValueRank(Integer)
     */
    @Override
    public synchronized void setValueRank(Integer valueRank) {
        this.valueRank = valueRank;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The attribute is only updated locally; it is not written to the server.
     *
     * @see #writeArrayDimensions(UInteger[])
     */
    @Override
    public synchronized void setArrayDimensions(UInteger[] arrayDimensions) {
        this.arrayDimensions = arrayDimensions;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The attribute is only updated locally; it is not written to the server.
     *
     * @see #writeAccessLevel(UByte)
     */
    @Override
    public synchronized void setAccessLevel(UByte accessLevel) {
        this.accessLevel = accessLevel;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The attribute is only updated locally; it is not written to the server.
     *
     * @see #writeUserAccessLevel(UByte)
     */
    @Override
    public synchronized void setUserAccessLevel(UByte userAccessLevel) {
        this.userAccessLevel = userAccessLevel;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The attribute is only updated locally; it is not written to the server.
     *
     * @see #writeMinimumSamplingInterval(Double)
     */
    @Override
    public synchronized void setMinimumSamplingInterval(Double minimumSamplingInterval) {
        this.minimumSamplingInterval = minimumSamplingInterval;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The attribute is only updated locally; it is not written to the server.
     *
     * @see #writeHistorizing(Boolean)
     */
    @Override
    public synchronized void setHistorizing(Boolean historizing) {
        this.historizing = historizing;
    }

    /**
     * Read the Value attribute for this Node from the server and update the local attribute.
     * <p>
     * Unlike reads for other attributes this call does not fail if the resulting {@link DataValue}
     * is not good quality and the local attribute value is updated without regard for status.
     *
     * @return the {@link DataValue} read from the server.
     * @throws UaException if a service-level error occurs.
     */
    public DataValue readValue() throws UaException {
        DataValue value = readAttribute(AttributeId.Value);
        setValue(value);
        return value;
    }

    /**
     * Read the DataType attribute for this Node from the server and update the local attribute if
     * the operation succeeds.
     *
     * @return the {@link NodeId} read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    public NodeId readDataType() throws UaException {
        DataValue value = readAttribute(AttributeId.DataType);

        StatusCode statusCode = value.getStatusCode();

        if (statusCode != null && statusCode.isBad()) {
            throw new UaException(statusCode, "read DataType failed");
        } else {
            NodeId dataType = (NodeId) value.getValue().getValue();
            setDataType(dataType);
            return dataType;
        }
    }

    /**
     * Read the ValueRank attribute for this Node from the server and update the local attribute if
     * the operation succeeds.
     *
     * @return the {@link Integer} read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    public Integer readValueRank() throws UaException {
        DataValue value = readAttribute(AttributeId.ValueRank);

        StatusCode statusCode = value.getStatusCode();

        if (statusCode != null && statusCode.isBad()) {
            throw new UaException(statusCode, "read ValueRank failed");
        } else {
            Integer valueRank = (Integer) value.getValue().getValue();
            setValueRank(valueRank);
            return valueRank;
        }
    }

    /**
     * Read the ArrayDimensions attribute for this Node from the server and update the local
     * attribute if the operation succeeds.
     * <p>
     * ArrayDimensions is an optional attribute. If the operation fails with
     * {@link StatusCodes#Bad_AttributeIdInvalid} a {@code null} value is returned instead.
     * <p>
     * Note that {@code null} is an expected value for the ArrayDimensions attribute in some cases
     * and this call does not distinguish between the attribute existing with a null value and the
     * attribute not existing.
     *
     * @return the {@link UInteger} array read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    @Nullable
    public UInteger[] readArrayDimensions() throws UaException {
        DataValue value = readAttribute(AttributeId.ArrayDimensions);
        StatusCode statusCode = value.getStatusCode();

        if (statusCode != null &&
            statusCode.isBad() &&
            statusCode.getValue() != StatusCodes.Bad_AttributeIdInvalid) {

            throw new UaException(statusCode, "read ArrayDimensions failed");
        } else {
            UInteger[] arrayDimensions = (UInteger[]) value.getValue().getValue();
            setArrayDimensions(arrayDimensions);
            return arrayDimensions;
        }
    }

    /**
     * Read the AccessLevel attribute for this Node from the server and update the local attribute
     * if the operation succeeds.
     *
     * @return the {@link UByte} read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     * @see org.eclipse.milo.opcua.sdk.core.AccessLevel#fromValue(UByte)
     */
    public UByte readAccessLevel() throws UaException {
        DataValue value = readAttribute(AttributeId.AccessLevel);

        StatusCode statusCode = value.getStatusCode();

        if (statusCode != null && statusCode.isBad()) {
            throw new UaException(statusCode, "read AccessLevel failed");
        } else {
            UByte accessLevel = (UByte) value.getValue().getValue();
            setAccessLevel(accessLevel);
            return accessLevel;
        }
    }

    /**
     * Read the UserAccessLevel attribute for this Node from the server and update the local
     * attribute if the operation succeeds.
     *
     * @return the {@link UByte} read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     * @see org.eclipse.milo.opcua.sdk.core.AccessLevel#fromValue(UByte)
     */
    public UByte readUserAccessLevel() throws UaException {
        DataValue value = readAttribute(AttributeId.UserAccessLevel);

        StatusCode statusCode = value.getStatusCode();

        if (statusCode != null && statusCode.isBad()) {
            throw new UaException(statusCode, "read UserAccessLevel failed");
        } else {
            UByte userAccessLevel = (UByte) value.getValue().getValue();
            setUserAccessLevel(userAccessLevel);
            return userAccessLevel;
        }
    }

    /**
     * Read the MinimumSamplingInterval attribute for this Node from the server and update the
     * local attribute if the operation succeeds.
     * <p>
     * MinimumSamplingInterval is an optional attribute. If the operation fails with
     * {@link StatusCodes#Bad_AttributeIdInvalid} a {@code null} value is returned instead.
     *
     * @return the {@link Double} read from the server, or {@code null} if the attribute does not
     * exist.
     * @throws UaException if a service- or operation-level error occurs.
     */
    @Nullable
    public Double readMinimumSamplingInterval() throws UaException {
        DataValue value = readAttribute(AttributeId.MinimumSamplingInterval);

        StatusCode statusCode = value.getStatusCode();

        if (statusCode != null &&
            statusCode.isBad() &&
            statusCode.getValue() != StatusCodes.Bad_AttributeIdInvalid) {

            throw new UaException(statusCode, "read MinimumSamplingInterval failed");
        } else {
            Double minimumSamplingInterval = (Double) value.getValue().getValue();
            setMinimumSamplingInterval(minimumSamplingInterval);
            return minimumSamplingInterval;
        }
    }

    /**
     * Read the Historizing attribute for this Node from the server and update the local attribute
     * if the operation succeeds.
     *
     * @return the {@link Boolean} read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    public Boolean readHistorizing() throws UaException {
        DataValue value = readAttribute(AttributeId.Historizing);

        StatusCode statusCode = value.getStatusCode();

        if (statusCode != null && statusCode.isBad()) {
            throw new UaException(statusCode, "read Historizing failed");
        } else {
            Boolean historizing = (Boolean) value.getValue().getValue();
            setHistorizing(historizing);
            return historizing;
        }
    }

    /**
     * Write a new Value attribute for this Node to the server and update the local attribute if
     * the operation succeeds.
     *
     * @param value the {@link DataValue} to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    public void writeValue(DataValue value) throws UaException {
        StatusCode statusCode = writeAttribute(AttributeId.Value, value);

        if (statusCode != null && statusCode.isBad()) {
            throw new UaException(statusCode, "write Value failed");
        } else {
            setValue(value);
        }
    }

    /**
     * Write a new Value attribute for this Node to the server and update the local attribute if
     * the operation succeeds.
     * <p>
     * This overload creates a {@link DataValue} with no StatusCode or timestamp values.
     *
     * @param variant the {@link Variant} to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    public void writeValue(Variant variant) throws UaException {
        writeValue(DataValue.valueOnly(variant));
    }

    /**
     * Write a new DataType attribute for this Node to the server and update the local attribute if
     * the operation succeeds.
     *
     * @param dataType the {@link NodeId} to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    public void writeDataType(NodeId dataType) throws UaException {
        DataValue value = DataValue.valueOnly(new Variant(dataType));
        StatusCode statusCode = writeAttribute(AttributeId.DataType, value);

        if (statusCode != null && statusCode.isBad()) {
            throw new UaException(statusCode, "write DataType failed");
        } else {
            setDataType(dataType);
        }
    }

    /**
     * Write a new ValueRank attribute for this Node to the server and update the local attribute
     * if the operation succeeds.
     *
     * @param valueRank the {@link Integer} to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    public void writeValueRank(Integer valueRank) throws UaException {
        DataValue value = DataValue.valueOnly(new Variant(valueRank));
        StatusCode statusCode = writeAttribute(AttributeId.ValueRank, value);

        if (statusCode != null && statusCode.isBad()) {
            throw new UaException(statusCode, "write ValueRank failed");
        } else {
            setValueRank(valueRank);
        }
    }

    /**
     * Write a new ArrayDimensions attribute for this Node to the server and update the local
     * attribute if the operation succeeds.
     *
     * @param arrayDimensions the {@link UInteger} array to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    public void writeArrayDimensions(UInteger[] arrayDimensions) throws UaException {
        DataValue value = DataValue.valueOnly(new Variant(arrayDimensions));
        StatusCode statusCode = writeAttribute(AttributeId.ArrayDimensions, value);

        if (statusCode != null && statusCode.isBad()) {
            throw new UaException(statusCode, "write ArrayDimensions failed");
        } else {
            setArrayDimensions(arrayDimensions);
        }
    }

    /**
     * Write a new AccessLevel attribute for this Node to the server and update the local attribute
     * if the operation succeeds.
     *
     * @param accessLevel the {@link UByte} to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    public void writeAccessLevel(UByte accessLevel) throws UaException {
        DataValue value = DataValue.valueOnly(new Variant(accessLevel));
        StatusCode statusCode = writeAttribute(AttributeId.AccessLevel, value);

        if (statusCode != null && statusCode.isBad()) {
            throw new UaException(statusCode, "write AccessLevel failed");
        } else {
            setAccessLevel(accessLevel);
        }
    }

    /**
     * Write a new UserAccessLevel attribute for this Node to the server and update the local
     * attribute if the operation succeeds.
     *
     * @param userAccessLevel the {@link UByte} to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    public void writeUserAccessLevel(UByte userAccessLevel) throws UaException {
        DataValue value = DataValue.valueOnly(new Variant(userAccessLevel));
        StatusCode statusCode = writeAttribute(AttributeId.UserAccessLevel, value);

        if (statusCode != null && statusCode.isBad()) {
            throw new UaException(statusCode, "write UserAccessLevel failed");
        } else {
            setUserAccessLevel(userAccessLevel);
        }
    }

    /**
     * Write a new MinimumSamplingInterval attribute for this Node to the server and update the
     * local attribute if the operation succeeds.
     *
     * @param minimumSamplingInterval the {@link Double} to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    public void writeMinimumSamplingInterval(Double minimumSamplingInterval) throws UaException {
        DataValue value = DataValue.valueOnly(new Variant(minimumSamplingInterval));
        StatusCode statusCode = writeAttribute(AttributeId.MinimumSamplingInterval, value);

        if (statusCode != null && statusCode.isBad()) {
            throw new UaException(statusCode, "write MinimumSamplingInterval failed");
        } else {
            setMinimumSamplingInterval(minimumSamplingInterval);
        }
    }

    /**
     * Write a new Historizing attribute for this Node to the server and update the local attribute
     * if the operation succeeds.
     *
     * @param historizing the {@link Boolean} to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    public void writeHistorizing(Boolean historizing) throws UaException {
        DataValue value = DataValue.valueOnly(new Variant(historizing));
        StatusCode statusCode = writeAttribute(AttributeId.Historizing, value);

        if (statusCode != null && statusCode.isBad()) {
            throw new UaException(statusCode, "write Historizing failed");
        } else {
            setHistorizing(historizing);
        }
    }

    public CompletableFuture<? extends UaVariableNode> getVariableComponent(String namespaceUri, String name) {
        UShort namespaceIndex = client.getNamespaceTable().getIndex(namespaceUri);

        if (namespaceIndex != null) {
            return getVariableComponent(new QualifiedName(namespaceIndex, name));
        } else {
            return failedUaFuture(StatusCodes.Bad_NotFound);
        }
    }

    public CompletableFuture<? extends UaVariableNode> getVariableComponent(QualifiedName browseName) {
        UInteger nodeClassMask = uint(NodeClass.Variable.getValue());
        UInteger resultMask = uint(BrowseResultMask.All.getValue());

        CompletableFuture<BrowseResult> future = client.browse(
            new BrowseDescription(
                getNodeId(),
                BrowseDirection.Forward,
                Identifiers.HasComponent,
                false,
                nodeClassMask,
                resultMask
            )
        );

        return future.thenCompose(result -> {
            List<ReferenceDescription> references = l(result.getReferences());

            Optional<CompletableFuture<UaVariableNode>> node = references.stream()
                .filter(r -> browseName.equals(r.getBrowseName()))
                .flatMap(r -> {
                    Optional<CompletableFuture<UaVariableNode>> opt = r.getNodeId()
                        .local(client.getNamespaceTable())
                        .map(id -> client.getAddressSpace().getVariableNodeAsync(id));

                    return opt2stream(opt);
                })
                .findFirst();

            return node.orElse(failedUaFuture(StatusCodes.Bad_NotFound));
        });
    }

    public UaVariableTypeNode getTypeDefinitionNode() throws UaException {
        try {
            return getTypeDefinitionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends UaVariableTypeNode> getTypeDefinitionNodeAsync() {
        UInteger nodeClassMask = uint(NodeClass.VariableType.getValue());
        UInteger resultMask = uint(BrowseResultMask.All.getValue());

        CompletableFuture<BrowseResult> future = client.browse(
            new BrowseDescription(
                getNodeId(),
                BrowseDirection.Forward,
                Identifiers.HasTypeDefinition,
                false,
                nodeClassMask,
                resultMask
            )
        );

        return future.thenCompose(result -> {
            List<ReferenceDescription> references = l(result.getReferences());

            Optional<CompletableFuture<UaVariableTypeNode>> node = references.stream()
                .flatMap(r -> {
                    Optional<CompletableFuture<UaVariableTypeNode>> opt = r.getNodeId()
                        .local(client.getNamespaceTable())
                        .map(
                            id ->
                                client.getAddressSpace().getNodeAsync(id)
                                    .thenApply(n -> (UaVariableTypeNode) n)
                        );

                    return opt2stream(opt);
                })
                .findFirst();

            return node.orElse(FutureUtils.failedUaFuture(StatusCodes.Bad_NotFound));
        });
    }

    /**
     * Get the value of the {@link VariableNodeProperties#NodeVersion} Property, if it exists.
     *
     * @return the value of the NodeVersion Property, if it exists.
     * @see VariableNodeProperties
     */
    public CompletableFuture<String> getNodeVersion() {
        return getProperty(VariableNodeProperties.NodeVersion);
    }

    /**
     * Get the value of the {@link VariableNodeProperties#LocalTime} Property, if it exists.
     *
     * @return the value of the LocalTime Property, if it exists.
     * @see VariableNodeProperties
     */
    public CompletableFuture<TimeZoneDataType> getLocalTime() {
        return getProperty(VariableNodeProperties.LocalTime);
    }

    /**
     * Get the value of the {@link VariableNodeProperties#DataTypeVersion} Property, if it exists.
     *
     * @return the value of the DataTypeVersion Property, if it exists.
     * @see VariableNodeProperties
     */
    public CompletableFuture<String> getDataTypeVersionAsync() {
        return getProperty(VariableNodeProperties.DataTypeVersion);
    }

    /**
     * Get the value of the {@link VariableNodeProperties#DictionaryFragment} Property, if it exists.
     *
     * @return the value of the DictionaryFragment Property, if it exists.
     * @see VariableNodeProperties
     */
    public CompletableFuture<ByteString> getDictionaryFragmentAsync() {
        return getProperty(VariableNodeProperties.DictionaryFragment);
    }

    /**
     * Get the value of the AllowNulls Property, if it exists.
     *
     * @return the value of the AllowNulls Property, if it exists.
     * @see VariableNodeProperties#AllowNulls
     */
    public CompletableFuture<Boolean> getAllowNulls() {
        return getProperty(VariableNodeProperties.AllowNulls);
    }

    /**
     * Get the value of the {@link VariableNodeProperties#ValueAsText} Property, if it exists.
     *
     * @return the value of the ValueAsText Property, if it exists.
     * @see VariableNodeProperties
     */
    public CompletableFuture<LocalizedText> getValueAsTextAsync() {
        return getProperty(VariableNodeProperties.ValueAsText);
    }

    /**
     * Get the value of the {@link VariableNodeProperties#MaxStringLength} Property, if it exists.
     *
     * @return the value of the MaxStringLength Property, if it exists.
     * @see VariableNodeProperties
     */
    public CompletableFuture<UInteger> getMaxStringLength() {
        return getProperty(VariableNodeProperties.MaxStringLength);
    }

    /**
     * Get the value of the {@link VariableNodeProperties#MaxArrayLength} Property, if it exists.
     *
     * @return the value of the MaxArrayLength Property, if it exists.
     * @see VariableNodeProperties
     */
    public CompletableFuture<UInteger> getMaxArrayLength() {
        return getProperty(VariableNodeProperties.MaxArrayLength);
    }

    /**
     * Get the value of the {@link VariableNodeProperties#EngineeringUnits} Property, if it exists.
     *
     * @return the value of the EngineeringUnits Property, if it exists.
     * @see VariableNodeProperties
     */
    public CompletableFuture<EUInformation> getEngineeringUnitsAsync() {
        return getProperty(VariableNodeProperties.EngineeringUnits);
    }

    /**
     * Set the value of the {@link VariableNodeProperties#NodeVersion} Property, if it exists.
     *
     * @param nodeVersion the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see VariableNodeProperties
     */
    public CompletableFuture<StatusCode> setNodeVersion(String nodeVersion) {
        return setProperty(VariableNodeProperties.NodeVersion, nodeVersion);
    }

    /**
     * Set the value of the {@link VariableNodeProperties#LocalTime} Property, if it exists.
     *
     * @param localTime the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see VariableNodeProperties
     */
    public CompletableFuture<StatusCode> setLocalTime(TimeZoneDataType localTime) {
        return setProperty(VariableNodeProperties.LocalTime, localTime);
    }

    /**
     * Set the value of the {@link VariableNodeProperties#DataTypeVersion} Property, if it exists.
     *
     * @param dataTypeVersion the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see VariableNodeProperties
     */
    public CompletableFuture<StatusCode> setDataTypeVersionAsync(String dataTypeVersion) {
        return setProperty(VariableNodeProperties.DataTypeVersion, dataTypeVersion);
    }

    /**
     * Set the value of the {@link VariableNodeProperties#DictionaryFragment} Property, if it exists.
     *
     * @param dictionaryFragment the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see VariableNodeProperties
     */
    public CompletableFuture<StatusCode> setDictionaryFragmentAsync(ByteString dictionaryFragment) {
        return setProperty(VariableNodeProperties.DictionaryFragment, dictionaryFragment);
    }

    /**
     * Set the value of the {@link VariableNodeProperties#AllowNulls} Property, if it exists.
     *
     * @param allowNulls the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see VariableNodeProperties
     */
    public CompletableFuture<StatusCode> setAllowNulls(Boolean allowNulls) {
        return setProperty(VariableNodeProperties.AllowNulls, allowNulls);
    }

    /**
     * Set the value of the {@link VariableNodeProperties#ValueAsText} Property, if it exists.
     *
     * @param valueAsText the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see VariableNodeProperties
     */
    public CompletableFuture<StatusCode> setValueAsTextAsync(LocalizedText valueAsText) {
        return setProperty(VariableNodeProperties.ValueAsText, valueAsText);
    }

    /**
     * Set the value of the {@link VariableNodeProperties#MaxStringLength} Property, if it exists.
     *
     * @param maxStringLength the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see VariableNodeProperties
     */
    public CompletableFuture<StatusCode> setMaxStringLength(UInteger maxStringLength) {
        return setProperty(VariableNodeProperties.MaxStringLength, maxStringLength);
    }

    /**
     * Set the value of the {@link VariableNodeProperties#MaxArrayLength} Property, if it exists.
     *
     * @param maxArrayLength the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see VariableNodeProperties
     */
    public CompletableFuture<StatusCode> setMaxArrayLength(UInteger maxArrayLength) {
        return setProperty(VariableNodeProperties.MaxArrayLength, maxArrayLength);
    }

    /**
     * Set the value of the {@link VariableNodeProperties#EngineeringUnits} Property, if it exists.
     *
     * @param engineeringUnits the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see VariableNodeProperties
     */
    public CompletableFuture<StatusCode> setEngineeringUnitsAsync(EUInformation engineeringUnits) {
        return setProperty(VariableNodeProperties.EngineeringUnits, engineeringUnits);
    }

}

