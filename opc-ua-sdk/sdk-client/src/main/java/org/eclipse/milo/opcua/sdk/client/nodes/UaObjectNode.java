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

import org.eclipse.milo.opcua.sdk.client.AddressSpace;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.methods.UaMethod;
import org.eclipse.milo.opcua.sdk.core.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.core.nodes.ObjectNodeProperties;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
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
import org.eclipse.milo.opcua.stack.core.types.enumerated.NamingRuleType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;

import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class UaObjectNode extends UaNode implements ObjectNode {

    private UByte eventNotifier;

    public UaObjectNode(
        OpcUaClient client,
        NodeId nodeId,
        NodeClass nodeClass,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        UByte eventNotifier
    ) {

        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask);

        this.eventNotifier = eventNotifier;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The returned attribute is the most recently seen value; it is not read live from the server.
     *
     * @see #readEventNotifier()
     */
    @Override
    public synchronized UByte getEventNotifier() {
        return eventNotifier;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The attribute is only updated locally; it is not written to the server.
     *
     * @see #writeEventNotifier(UByte)
     */
    @Override
    public void setEventNotifier(UByte eventNotifier) {
        this.eventNotifier = eventNotifier;
    }

    /**
     * Read the EventNotifier attribute for this Node from the server and update the local
     * attribute if the operation succeeds.
     *
     * @return the {@link UByte} read from the server.
     * @throws UaException if a service- or operation-level error occurs.s
     */
    public UByte readEventNotifier() throws UaException {
        DataValue value = readAttribute(AttributeId.EventNotifier);

        StatusCode statusCode = value.getStatusCode();

        if (statusCode != null && statusCode.isBad()) {
            throw new UaException(statusCode, "read EventNotifier failed");
        } else {
            UByte eventNotifier = (UByte) value.getValue().getValue();
            setEventNotifier(eventNotifier);
            return eventNotifier;
        }
    }

    /**
     * Write a new EventNotifier attribute for this Node to the server and update the
     * local attribute if the operation succeeds.
     *
     * @param eventNotifier the {@link UByte} to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    public void writeEventNotifier(UByte eventNotifier) throws UaException {
        DataValue value = DataValue.valueOnly(new Variant(eventNotifier));
        StatusCode statusCode = writeAttribute(AttributeId.EventNotifier, value);

        if (statusCode == null || statusCode.isGood()) {
            setEventNotifier(eventNotifier);
        } else {
            throw new UaException(statusCode, "write EventNotifier failed");
        }
    }

    public Variant[] callMethod(String methodName, Variant[] inputs) throws UaException {
        return findMethod(methodName).call(inputs);
    }

    public Variant[] callMethod(QualifiedName methodName, Variant[] inputs) throws UaException {
        return findMethod(methodName).call(inputs);
    }

    public CompletableFuture<Variant[]> callMethodAsync(String methodName, Variant[] inputs) {
        return findMethodAsync(methodName).thenCompose(m -> m.callAsync(inputs));
    }

    public CompletableFuture<Variant[]> callMethodAsync(QualifiedName methodName, Variant[] inputs) {
        return findMethodAsync(methodName).thenCompose(m -> m.callAsync(inputs));
    }

    public UaMethod findMethod(String methodName) throws UaException {
        UShort namespaceIndex = getNodeId().getNamespaceIndex();

        return findMethod(new QualifiedName(namespaceIndex, methodName));
    }

    public UaMethod findMethod(QualifiedName methodName) throws UaException {
        try {
            return findMethodAsync(methodName).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<UaMethod> findMethodAsync(String methodName) {
        UShort namespaceIndex = getNodeId().getNamespaceIndex();

        return findMethodAsync(new QualifiedName(namespaceIndex, methodName));
    }

    public CompletableFuture<UaMethod> findMethodAsync(QualifiedName methodName) {
        // TODO use browse instead of findMemberNodeId so the target can be constrained to method nodes

        CompletableFuture<ExpandedNodeId> memberNodeId =
            findMemberNodeId(methodName, Identifiers.HasComponent.expanded(), false);

        return memberNodeId.thenCompose(xni -> {
            AddressSpace addressSpace = client.getAddressSpace();

            return addressSpace.localizeAsync(xni).thenCompose(
                nodeId ->
                    addressSpace.getNodeAsync(nodeId).thenCompose(node -> {
                        UaMethodNode methodNode = (UaMethodNode) node;

                        CompletableFuture<Argument[]> f1 = methodNode.getInputArguments();
                        CompletableFuture<Argument[]> f2 = methodNode.getOutputArguments();

                        return f1.thenCombine(
                            f2,
                            (in, out) -> new UaMethod(client, this, methodNode, in, out)
                        );
                    })
            );
        });
    }

    public CompletableFuture<? extends UaNode> getComponent(QualifiedName browseName) {
        UInteger nodeClassMask = uint(NodeClass.Object.getValue() | NodeClass.Variable.getValue());
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

            Optional<CompletableFuture<? extends UaNode>> node = references.stream()
                .filter(r -> browseName.equals(r.getBrowseName()))
                .flatMap(r -> {
                    Optional<CompletableFuture<? extends UaNode>> opt = r.getNodeId()
                        .local(client.getNamespaceTable())
                        .map(id -> client.getAddressSpace().getNodeAsync(id));

                    return opt2stream(opt);
                })
                .findFirst();

            return node.orElse(failedUaFuture(StatusCodes.Bad_NotFound));
        });
    }

    public CompletableFuture<? extends ObjectNode> getObjectComponent(String namespaceUri, String name) {
        UShort namespaceIndex = client.getNamespaceTable().getIndex(namespaceUri);

        if (namespaceIndex != null) {
            return getObjectComponent(new QualifiedName(namespaceIndex, name));
        } else {
            return failedUaFuture(StatusCodes.Bad_NotFound);
        }
    }

    public CompletableFuture<? extends UaObjectNode> getObjectComponent(QualifiedName browseName) {
        UInteger nodeClassMask = uint(NodeClass.Object.getValue());
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

            Optional<CompletableFuture<UaObjectNode>> node = references.stream()
                .filter(r -> browseName.equals(r.getBrowseName()))
                .flatMap(r -> {
                    Optional<CompletableFuture<UaObjectNode>> opt = r.getNodeId()
                        .local(client.getNamespaceTable())
                        .map(id -> client.getAddressSpace().getObjectNodeAsync(id));

                    return opt2stream(opt);
                })
                .findFirst();

            return node.orElse(failedUaFuture(StatusCodes.Bad_NotFound));
        });
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

    public CompletableFuture<? extends UaObjectTypeNode> getTypeDefinition() {
        UInteger nodeClassMask = uint(NodeClass.ObjectType.getValue());
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

            Optional<CompletableFuture<UaObjectTypeNode>> node = references.stream()
                .flatMap(r -> {
                    Optional<CompletableFuture<UaObjectTypeNode>> opt = r.getNodeId()
                        .local(client.getNamespaceTable())
                        .map(
                            id ->
                                client.getAddressSpace().getNodeAsync(id)
                                    .thenApply(n -> (UaObjectTypeNode) n)
                        );

                    return opt2stream(opt);
                })
                .findFirst();

            return node.orElse(FutureUtils.failedUaFuture(StatusCodes.Bad_NotFound));
        });
    }

    /**
     * Get the value of the {@link ObjectNodeProperties#NodeVersion} Property, if it exists.
     *
     * @return the value of the NodeVersion Property, if it exists.
     * @see ObjectNodeProperties
     */
    public CompletableFuture<String> getNodeVersion() {
        return getProperty(ObjectNodeProperties.NodeVersion);
    }

    /**
     * Get the value of the {@link ObjectNodeProperties#Icon} Property, if it exists.
     *
     * @return the value of the Icon Property, if it exists.
     * @see ObjectNodeProperties
     */
    public CompletableFuture<ByteString> getIcon() {
        return getProperty(ObjectNodeProperties.Icon);
    }

    /**
     * Get the value of the {@link ObjectNodeProperties#NamingRule} Property, if it exists.
     *
     * @return the value of the NamingRule Property, if it exists.
     * @see ObjectNodeProperties
     */
    public CompletableFuture<NamingRuleType> getNamingRuleAsync() {
        return getProperty(ObjectNodeProperties.NamingRule);
    }

    /**
     * Set the value of the {@link ObjectNodeProperties#NodeVersion} Property, if it exists.
     *
     * @param nodeVersion the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see ObjectNodeProperties
     */
    public CompletableFuture<StatusCode> setNodeVersion(String nodeVersion) {
        return setProperty(ObjectNodeProperties.NodeVersion, nodeVersion);
    }

    /**
     * Set the value of the {@link ObjectNodeProperties#Icon} Property, if it exists.
     *
     * @param icon the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see ObjectNodeProperties
     */
    public CompletableFuture<StatusCode> setIcon(ByteString icon) {
        return setProperty(ObjectNodeProperties.Icon, icon);
    }

    /**
     * Set the value of the {@link ObjectNodeProperties#NamingRule} Property, if it exists.
     *
     * @param namingRule the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see ObjectNodeProperties
     */
    public CompletableFuture<StatusCode> setNamingRuleAsync(NamingRuleType namingRule) {
        return setProperty(ObjectNodeProperties.NamingRule, namingRule);
    }

    protected DataValue getAttributeValue(AttributeId attributeId) {
        switch (attributeId) {
            case EventNotifier:
                return DataValue.valueOnly(new Variant(getEventNotifier()));
            default:
                return super.getAttributeValue(attributeId);
        }
    }

    protected void setAttributeValue(AttributeId attributeId, DataValue value) {
        switch (attributeId) {
            case EventNotifier: {
                setEventNotifier((UByte) value.getValue().getValue());
                break;
            }
            default: {
                super.setAttributeValue(attributeId, value);
            }
        }
    }

}
