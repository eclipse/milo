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

package org.eclipse.milo.opcua.sdk.client;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;
import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.AccessContext;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.MethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.api.Namespace;
import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.ServerNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaFolderNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.util.SubscriptionModel;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class TestNamespace implements Namespace {

    public static final String NAMESPACE_URI = "urn:eclipse:milo:opcua:test-namespace";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ServerNodeMap nodeManager;
    private final UaFolderNode testFolder;
    private final SubscriptionModel subscriptionModel;

    private final OpcUaServer server;
    private final UShort namespaceIndex;

    public TestNamespace(OpcUaServer server, UShort namespaceIndex) {
        this.server = server;
        this.namespaceIndex = namespaceIndex;

        nodeManager = server.getNodeMap();

        NodeId testFolderNodeId = new NodeId(namespaceIndex, "Test");

        testFolder = new UaFolderNode(
            nodeManager,
            testFolderNodeId,
            new QualifiedName(namespaceIndex, "Test"),
            LocalizedText.english("Test")
        );

        nodeManager.put(testFolderNodeId, testFolder);

        try {
            server.getUaNamespace().addReference(
                Identifiers.ObjectsFolder,
                Identifiers.Organizes,
                true,
                testFolderNodeId.expanded(),
                NodeClass.Object
            );
        } catch (UaException e) {
            logger.error("Error adding reference to Connections folder.", e);
        }

        subscriptionModel = new SubscriptionModel(server, this);

        addStaticScalarNodes();
    }

    @Override
    public UShort getNamespaceIndex() {
        return namespaceIndex;
    }

    @Override
    public String getNamespaceUri() {
        return NAMESPACE_URI;
    }

    @Override
    public CompletableFuture<List<Reference>> browse(AccessContext context, NodeId nodeId) {
        ServerNode node = nodeManager.get(nodeId);

        if (node != null) {
            return CompletableFuture.completedFuture(node.getReferences());
        } else {
            CompletableFuture<List<Reference>> f = new CompletableFuture<>();
            f.completeExceptionally(new UaException(StatusCodes.Bad_NodeIdUnknown));
            return f;
        }
    }

    @Override
    public void read(ReadContext context, Double maxAge, TimestampsToReturn timestamps, List<ReadValueId> readValueIds) {
        List<DataValue> results = Lists.newArrayListWithCapacity(readValueIds.size());

        for (ReadValueId id : readValueIds) {
            ServerNode node = nodeManager.get(id.getNodeId());

            if (node != null) {
                DataValue value = node.readAttribute(
                    new AttributeContext(context),
                    id.getAttributeId(),
                    timestamps,
                    id.getIndexRange(),
                    id.getDataEncoding()
                );

                if (logger.isTraceEnabled()) {
                    Variant variant = value.getValue();
                    Object o = variant != null ? variant.getValue() : null;
                    logger.trace("Read value={} from attributeId={} of {}",
                        o, id.getAttributeId(), id.getNodeId());
                }

                results.add(value);
            } else {
                results.add(new DataValue(new StatusCode(StatusCodes.Bad_NodeIdUnknown)));
            }
        }

        context.complete(results);
    }

    @Override
    public void write(WriteContext context, List<WriteValue> writeValues) {
        List<StatusCode> results = Lists.newArrayListWithCapacity(writeValues.size());

        for (WriteValue writeValue : writeValues) {
            try {
                ServerNode node = nodeManager.getNode(writeValue.getNodeId())
                    .orElseThrow(() -> new UaException(StatusCodes.Bad_NodeIdUnknown));

                node.writeAttribute(
                    new AttributeContext(context),
                    writeValue.getAttributeId(),
                    writeValue.getValue(),
                    writeValue.getIndexRange()
                );

                if (logger.isTraceEnabled()) {
                    Variant variant = writeValue.getValue().getValue();
                    Object o = variant != null ? variant.getValue() : null;
                    logger.trace("Wrote value={} to attributeId={} of {}",
                        o, writeValue.getAttributeId(), writeValue.getNodeId());
                }

                results.add(StatusCode.GOOD);
            } catch (UaException e) {
                results.add(e.getStatusCode());
            }
        }

        context.complete(results);
    }

    @Override
    public void onDataItemsCreated(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsCreated(dataItems);
    }

    @Override
    public void onDataItemsModified(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsModified(dataItems);
    }

    @Override
    public void onDataItemsDeleted(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsDeleted(dataItems);
    }

    @Override
    public void onMonitoringModeChanged(List<MonitoredItem> monitoredItems) {
        subscriptionModel.onMonitoringModeChanged(monitoredItems);
    }

    @Override
    public Optional<MethodInvocationHandler> getInvocationHandler(NodeId methodId) {
        ServerNode node = nodeManager.get(methodId);

        if (node instanceof UaMethodNode) {
            return ((UaMethodNode) node).getInvocationHandler();
        } else {
            return Optional.empty();
        }
    }

    private static final Object[][] STATIC_SCALAR_NODES = new Object[][]{
        {"Bool", Identifiers.Boolean, new Variant(false)},
        {"Byte", Identifiers.Byte, new Variant(ubyte(0x00))},
        {"ByteString", Identifiers.ByteString, new Variant(new ByteString(new byte[]{0x01, 0x02, 0x03, 0x04}))},
        {"DateTime", Identifiers.DateTime, new Variant(DateTime.now())},
        {"Double", Identifiers.Double, new Variant(3.14d)},
        {"Duration", Identifiers.Duration, new Variant(1.0)},
        {"Float", Identifiers.Float, new Variant(3.14f)},
        {"Guid", Identifiers.Guid, new Variant(UUID.randomUUID())},
        {"Int16", Identifiers.Int16, new Variant((short) 16)},
        {"Int32", Identifiers.Int32, new Variant(32)},
        {"Int64", Identifiers.Int64, new Variant(64L)},
        {"LocalizedText", Identifiers.LocalizedText, new Variant(LocalizedText.english("localized text"))},
        {"NodeId", Identifiers.NodeId, new Variant(new NodeId(1234, "abcd"))},
        {"QualifiedName", Identifiers.QualifiedName, new Variant(new QualifiedName(1234, "defg"))},
        {"SByte", Identifiers.SByte, new Variant((byte) 0x00)},
        {"String", Identifiers.String, new Variant("string value")},
        {"UtcTime", Identifiers.UtcTime, new Variant(DateTime.now())},
        {"UInt16", Identifiers.UInt16, new Variant(ushort(16))},
        {"UInt32", Identifiers.UInt32, new Variant(uint(32))},
        {"UInt64", Identifiers.UInt64, new Variant(ulong(64L))},
        {"XmlElement", Identifiers.XmlElement, new Variant(new XmlElement("<a>hello</a>"))},
    };

    private void addStaticScalarNodes() {
        UaObjectNode folder = addFoldersToRoot(testFolder, "/Static/AllProfiles/Scalar");

        for (Object[] os : STATIC_SCALAR_NODES) {
            String name = (String) os[0];
            NodeId typeId = (NodeId) os[1];
            Variant variant = (Variant) os[2];

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(nodeManager)
                .setNodeId(new NodeId(namespaceIndex, "/Static/AllProfiles/Scalar/" + name))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, name))
                .setDisplayName(LocalizedText.english(name))
                .setDataType(typeId)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

            node.setValue(new DataValue(variant));

            folder.addReference(new Reference(
                folder.getNodeId(),
                Identifiers.Organizes,
                node.getNodeId().expanded(),
                node.getNodeClass(),
                true
            ));

            logger.debug("Added reference: {} -> {}", folder.getNodeId(), node.getNodeId());

            nodeManager.put(node.getNodeId(), node);
        }
    }

    private UaObjectNode addFoldersToRoot(UaNode root, String path) {
        if (path.startsWith("/")) path = path.substring(1, path.length());
        String[] elements = path.split("/");

        LinkedList<UaObjectNode> folderNodes = processPathElements(
            Lists.newArrayList(elements),
            Lists.newArrayList(),
            Lists.newLinkedList()
        );

        UaObjectNode firstNode = folderNodes.getFirst();

        if (!nodeManager.containsKey(firstNode.getNodeId())) {
            nodeManager.put(firstNode.getNodeId(), firstNode);

            nodeManager.get(root.getNodeId()).addReference(new Reference(
                root.getNodeId(),
                Identifiers.Organizes,
                firstNode.getNodeId().expanded(),
                firstNode.getNodeClass(),
                true
            ));

            logger.debug("Added reference: {} -> {}", root.getNodeId(), firstNode.getNodeId());
        }

        PeekingIterator<UaObjectNode> iterator = Iterators.peekingIterator(folderNodes.iterator());

        while (iterator.hasNext()) {
            UaObjectNode node = iterator.next();

            nodeManager.putIfAbsent(node.getNodeId(), node);

            if (iterator.hasNext()) {
                UaObjectNode next = iterator.peek();

                if (!nodeManager.containsKey(next.getNodeId())) {
                    nodeManager.put(next.getNodeId(), next);

                    nodeManager.get(node.getNodeId()).addReference(new Reference(
                        node.getNodeId(),
                        Identifiers.Organizes,
                        next.getNodeId().expanded(),
                        next.getNodeClass(),
                        true
                    ));

                    logger.debug("Added reference: {} -> {}", node.getNodeId(), next.getNodeId());
                }
            }
        }

        return folderNodes.getLast();
    }

    private LinkedList<UaObjectNode> processPathElements(List<String> elements, List<String> path, LinkedList<UaObjectNode> nodes) {
        if (elements.size() == 1) {
            String name = elements.get(0);
            String prefix = String.join("/", path) + "/";
            if (!prefix.startsWith("/")) prefix = "/" + prefix;

            UaObjectNode node = UaObjectNode.builder(nodeManager)
                .setNodeId(new NodeId(namespaceIndex, prefix + name))
                .setBrowseName(new QualifiedName(namespaceIndex, name))
                .setDisplayName(LocalizedText.english(name))
                .setTypeDefinition(Identifiers.FolderType)
                .build();

            nodes.add(node);

            return nodes;
        } else {
            String name = elements.get(0);
            String prefix = String.join("/", path) + "/";
            if (!prefix.startsWith("/")) prefix = "/" + prefix;

            UaObjectNode node = UaObjectNode.builder(nodeManager)
                .setNodeId(new NodeId(namespaceIndex, prefix + name))
                .setBrowseName(new QualifiedName(namespaceIndex, name))
                .setDisplayName(LocalizedText.english(name))
                .setTypeDefinition(Identifiers.FolderType)
                .build();

            nodes.add(node);
            path.add(name);

            return processPathElements(elements.subList(1, elements.size()), path, nodes);
        }
    }

}
