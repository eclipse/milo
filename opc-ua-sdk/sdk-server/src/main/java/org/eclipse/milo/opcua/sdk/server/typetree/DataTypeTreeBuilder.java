/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.typetree;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.typetree.DataType;
import org.eclipse.milo.opcua.sdk.core.typetree.DataTypeTree;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.nodes.UaDataTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.ReferenceTypes;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.structured.DataTypeDefinition;
import org.eclipse.milo.opcua.stack.core.util.Tree;
import org.jetbrains.annotations.Nullable;

public class DataTypeTreeBuilder {

    public static DataTypeTree build(OpcUaServer server) {
        UaNode rootNode = server.getAddressSpaceManager()
            .getManagedNode(NodeIds.Structure)
            .orElseThrow();

        var tree = new Tree<DataType>(
            null,
            new ServerDataType(null, (UaDataTypeNode) rootNode)
        );

        addChildren(tree, server);

        return new DataTypeTree(tree);
    }

    private static void addChildren(Tree<DataType> tree, OpcUaServer server) {
        NodeId nodeId = tree.getValue().getNodeId();

        // TODO HasSubtype _or_ subtypes of HasSubtype...
        List<Reference> references = server.getAddressSpaceManager().getManagedReferences(
            nodeId,
            r -> r.isForward() && r.getReferenceTypeId().equals(ReferenceTypes.HasSubtype)
        );

        List<UaNode> childNodes = references.stream()
            .flatMap(
                r ->
                    server.getAddressSpaceManager()
                        .getManagedNode(r.getTargetNodeId()).stream()
            )
            .collect(Collectors.toList());

        for (UaNode childNode : childNodes) {
            tree.addChild(
                new ServerDataType(tree, (UaDataTypeNode) childNode)
            );
        }

        for (Tree<DataType> child : tree.getChildren()) {
            addChildren(child, server);
        }
    }

    private static class ServerDataType implements DataType {

        private final Tree<DataType> tree;
        private final UaDataTypeNode node;

        public ServerDataType(Tree<DataType> tree, UaDataTypeNode node) {
            this.tree = tree;
            this.node = node;
        }

        @Override
        public QualifiedName getBrowseName() {
            return node.getBrowseName();
        }

        @Override
        public NodeId getNodeId() {
            return node.getNodeId();
        }

        @Override
        public @Nullable NodeId getBinaryEncodingId() {
            return null; // TODO
        }

        @Override
        public @Nullable NodeId getXmlEncodingId() {
            return null; // TODO
        }

        @Override
        public @Nullable NodeId getJsonEncodingId() {
            return null; // TODO
        }

        @Override
        public DataTypeDefinition getDataTypeDefinition() {
            return node.getDataTypeDefinition();
        }

    }

}
