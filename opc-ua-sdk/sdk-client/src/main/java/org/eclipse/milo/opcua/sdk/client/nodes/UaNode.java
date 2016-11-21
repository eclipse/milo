/*
 * Copyright (c) 2016 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.client.nodes;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.NodeCache;
import org.eclipse.milo.opcua.sdk.client.api.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.DecoderDelegate;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.binary.BinaryDecoder;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;

import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;
import static org.eclipse.milo.opcua.stack.core.types.builtin.DataValue.valueOnly;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public abstract class UaNode implements Node {

    protected final NodeCache nodeCache;

    protected final OpcUaClient client;
    protected final NodeId nodeId;

    public UaNode(OpcUaClient client, NodeId nodeId) {
        this.client = client;
        this.nodeId = nodeId;

        nodeCache = client.getNodeCache();
    }

    protected CompletableFuture<PropertyNode> getPropertyNode(QualifiedName browseName) {
        UInteger nodeClassMask = uint(NodeClass.Variable.getValue());
        UInteger resultMask = uint(BrowseResultMask.BrowseName.getValue());

        CompletableFuture<BrowseResult> future = client.browse(
            new BrowseDescription(
                nodeId,
                BrowseDirection.Forward,
                Identifiers.HasProperty,
                false,
                nodeClassMask,
                resultMask
            )
        );

        return future.thenCompose(result -> {
            List<ReferenceDescription> references = l(result.getReferences());

            Optional<PropertyNode> node = references.stream()
                .filter(r -> browseName.equals(r.getBrowseName()))
                .flatMap(r -> {
                    Optional<PropertyNode> opt = r.getNodeId().local()
                        .map(id -> new PropertyNode(client, id));

                    return opt2stream(opt);
                })
                .findFirst();

            return node
                .map(CompletableFuture::completedFuture)
                .orElse(failedUaFuture(StatusCodes.Bad_NotFound));
        });
    }

    protected <T> CompletableFuture<T> getProperty(Property<T> property) {
        return getPropertyNode(property.getBrowseName())
            .thenCompose(VariableNode::getValue)
            .thenApply(value -> property.getJavaType().cast(value));
    }

    protected CompletableFuture<DataValue> readProperty(Property<?> property) {
        return getPropertyNode(property.getBrowseName())
            .thenCompose(VariableNode::readValue);
    }

    protected <T> CompletableFuture<StatusCode> setProperty(Property<T> property, T value) {
        return getPropertyNode(property.getBrowseName())
            .thenCompose(node -> node.setValue(value));
    }

    protected CompletableFuture<StatusCode> writeProperty(Property<?> property, DataValue value) {
        return getPropertyNode(property.getBrowseName())
            .thenCompose(node -> node.writeValue(value));
    }

    protected CompletableFuture<DataValue> readAttribute(AttributeId attributeId) {
        Optional<DataValue> opt =
            AttributeId.BASE_NODE_ATTRIBUTES.contains(attributeId) ?
                nodeCache.getAttribute(nodeId, attributeId) : Optional.empty();

        return opt.map(CompletableFuture::completedFuture).orElseGet(() -> {
            ReadValueId readValueId = new ReadValueId(
                nodeId, attributeId.uid(), null, QualifiedName.NULL_VALUE);

            CompletableFuture<ReadResponse> future =
                client.read(0.0, TimestampsToReturn.Both, newArrayList(readValueId));

            return future.thenApply(response -> {
                DataValue value = l(response.getResults()).get(0);

                if (attributeId != AttributeId.Value) {
                    nodeCache.putAttribute(nodeId, attributeId, value);
                }

                return value;
            });
        });
    }

    protected CompletableFuture<StatusCode> writeAttribute(AttributeId attributeId, DataValue value) {
        WriteValue writeValue = new WriteValue(
            nodeId, attributeId.uid(), null, value);

        return client.write(newArrayList(writeValue)).thenApply(response -> {
            StatusCode statusCode = l(response.getResults()).get(0);

            if (statusCode.isGood()) {
                nodeCache.invalidate(nodeId, attributeId);
            }

            return statusCode;
        });
    }

    @Override
    public CompletableFuture<NodeId> getNodeId() {
        return readNodeId().thenApply(v -> (NodeId) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<NodeClass> getNodeClass() {
        return readNodeClass().thenApply(v -> NodeClass.from((Integer) v.getValue().getValue()));
    }

    @Override
    public CompletableFuture<QualifiedName> getBrowseName() {
        return readBrowseName().thenApply(v -> (QualifiedName) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<LocalizedText> getDisplayName() {
        return readDisplayName().thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<LocalizedText> getDescription() {
        return readDescription().thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<UInteger> getWriteMask() {
        return readWriteMask().thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<UInteger> getUserWriteMask() {
        return readUserWriteMask().thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> setNodeId(NodeId nodeId) {
        return writeNodeId(valueOnly(new Variant(nodeId)));
    }

    @Override
    public CompletableFuture<StatusCode> setNodeClass(NodeClass nodeClass) {
        return writeNodeClass(valueOnly(new Variant(nodeClass)));
    }

    @Override
    public CompletableFuture<StatusCode> setBrowseName(QualifiedName browseName) {
        return writeBrowseName(valueOnly(new Variant(browseName)));
    }

    @Override
    public CompletableFuture<StatusCode> setDisplayName(LocalizedText displayName) {
        return writeDisplayName(valueOnly(new Variant(displayName)));
    }

    @Override
    public CompletableFuture<StatusCode> setDescription(LocalizedText description) {
        return writeDescription(valueOnly(new Variant(description)));
    }

    @Override
    public CompletableFuture<StatusCode> setWriteMask(UInteger writeMask) {
        return writeWriteMask(valueOnly(new Variant(writeMask)));
    }

    @Override
    public CompletableFuture<StatusCode> setUserWriteMask(UInteger userWriteMask) {
        return writeUserWriteMask(valueOnly(new Variant(userWriteMask)));
    }

    @Override
    public CompletableFuture<DataValue> readNodeId() {
        return readAttribute(AttributeId.NodeId);
    }

    @Override
    public CompletableFuture<DataValue> readNodeClass() {
        return readAttribute(AttributeId.NodeClass);
    }

    @Override
    public CompletableFuture<DataValue> readBrowseName() {
        return readAttribute(AttributeId.BrowseName);
    }

    @Override
    public CompletableFuture<DataValue> readDisplayName() {
        return readAttribute(AttributeId.DisplayName);
    }

    @Override
    public CompletableFuture<DataValue> readDescription() {
        return readAttribute(AttributeId.Description);
    }

    @Override
    public CompletableFuture<DataValue> readWriteMask() {
        return readAttribute(AttributeId.WriteMask);
    }

    @Override
    public CompletableFuture<DataValue> readUserWriteMask() {
        return readAttribute(AttributeId.UserWriteMask);
    }

    @Override
    public CompletableFuture<StatusCode> writeNodeId(DataValue value) {
        return writeAttribute(AttributeId.NodeId, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeNodeClass(DataValue value) {
        return writeAttribute(AttributeId.NodeClass, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeBrowseName(DataValue value) {
        return writeAttribute(AttributeId.BrowseName, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeDisplayName(DataValue value) {
        return writeAttribute(AttributeId.DisplayName, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeDescription(DataValue value) {
        return writeAttribute(AttributeId.Description, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeWriteMask(DataValue value) {
        return writeAttribute(AttributeId.WriteMask, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeUserWriteMask(DataValue value) {
        return writeAttribute(AttributeId.UserWriteMask, value);
    }

    /**
     * An implementation of cast with special handling for {@link UaEnumeration} and
     * {@link UaStructure} destination types.
     * <p>
     * If the destination type is a {@link UaEnumeration} and the from object is an Integer, an attempt is made to
     * convert the Integer into the corresponding UaEnumeration type.
     * <p>
     * If the destination type is a {@link UaStructure} and the from object is an {@link ExtensionObject}, an attempt
     * is made to decode the {@link ExtensionObject} into an object cast to the type of {@code clazz}.
     *
     * @param o     the Object to cast from.
     * @param clazz the type to cast {@code o} to.
     * @return the object after casting, or null if {@code o} is null.
     */
    protected static <T> T cast(Object o, Class<T> clazz) {
        if (UaEnumeration.class.isAssignableFrom(clazz) && o instanceof Integer) {
            return DelegateRegistry.getInstance().getDecoder(clazz).decode(
                new EnumDecoder((Integer) o)
            );
        } else if (UaStructure.class.isAssignableFrom(clazz) && o instanceof ExtensionObject) {
            Object decoded = ((ExtensionObject) o).decode();
            return clazz.cast(decoded);
        } else {
            return clazz.cast(o);
        }
    }

    private static class EnumDecoder extends BinaryDecoder {

        private final int value;

        EnumDecoder(int value) {
            this.value = value;
        }

        @Override
        public Integer decodeInt32(String field) throws UaSerializationException {
            return value;
        }

        @Override
        public <T extends UaEnumeration> T decodeEnumeration(
            String field, Class<T> clazz) throws UaSerializationException {

            DecoderDelegate<T> delegate = DelegateRegistry.getInstance().getDecoder(clazz);

            return delegate.decode(this);
        }

    }

}
