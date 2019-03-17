/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;
import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.ManagedNamespace;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.nodes.UaFolderNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.util.SubscriptionModel;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class TestNamespace extends ManagedNamespace {

    public static final String NAMESPACE_URI = "urn:eclipse:milo:opcua:test-namespace";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UaNodeManager nodeManager = new UaNodeManager();
    private final UaFolderNode testFolder;
    private final SubscriptionModel subscriptionModel;
    private final UaNodeContext nodeContext;

    private final OpcUaServer server;
    private final UShort namespaceIndex;

    public TestNamespace(OpcUaServer server, UShort namespaceIndex) {
        super(server, namespaceIndex);

        this.server = server;
        this.namespaceIndex = namespaceIndex;

        nodeContext = new UaNodeContext() {
            @Override
            public OpcUaServer getServer() {
                return server;
            }

            @Override
            public NodeManager<UaNode> getNodeManager() {
                return nodeManager;
            }
        };

        NodeId testFolderNodeId = new NodeId(namespaceIndex, "Test");

        testFolder = new UaFolderNode(
            nodeContext,
            testFolderNodeId,
            new QualifiedName(namespaceIndex, "Test"),
            LocalizedText.english("Test")
        );

        nodeManager.addNode(testFolder);

        nodeManager.addReference(new Reference(
            testFolderNodeId,
            Identifiers.Organizes,
            Identifiers.ObjectsFolder.expanded(),
            false
        ));

        subscriptionModel = new SubscriptionModel(server, this);

        addStaticScalarNodes();
    }

    @Override
    public String getNamespaceUri() {
        return NAMESPACE_URI;
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

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(nodeContext)
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
                true
            ));

            logger.debug("Added reference: {} -> {}", folder.getNodeId(), node.getNodeId());

            nodeManager.addNode(node);
        }
    }

    private UaObjectNode addFoldersToRoot(UaNode root, String path) {
        if (path.startsWith("/")) path = path.substring(1);
        String[] elements = path.split("/");

        LinkedList<UaObjectNode> folderNodes = processPathElements(
            Lists.newArrayList(elements),
            Lists.newArrayList(),
            Lists.newLinkedList()
        );

        UaObjectNode firstNode = folderNodes.getFirst();

        if (!nodeManager.containsNode(firstNode)) {
            nodeManager.addNode(firstNode);

            nodeManager.addReference(new Reference(
                root.getNodeId(),
                Identifiers.Organizes,
                firstNode.getNodeId().expanded(),
                true
            ));

            logger.debug("Added reference: {} -> {}", root.getNodeId(), firstNode.getNodeId());
        }

        PeekingIterator<UaObjectNode> iterator = Iterators.peekingIterator(folderNodes.iterator());

        while (iterator.hasNext()) {
            UaObjectNode node = iterator.next();

            if (!nodeManager.containsNode(node)) {
                nodeManager.addNode(node);
            }

            if (iterator.hasNext()) {
                UaObjectNode next = iterator.peek();

                if (!nodeManager.containsNode(next)) {
                    nodeManager.addNode(next);

                    nodeManager.addReference(new Reference(
                        node.getNodeId(),
                        Identifiers.Organizes,
                        next.getNodeId().expanded(),
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

            UaObjectNode node = UaObjectNode.builder(nodeContext)
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

            UaObjectNode node = UaObjectNode.builder(nodeContext)
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
