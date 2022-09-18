/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.core.types;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.util.Tree;
import org.jetbrains.annotations.Nullable;

public class TypeTree<T extends TypeTree.Type> {

    protected final Map<NodeId, Tree<T>> types = new HashMap<>();
    protected final Tree<T> tree;

    public TypeTree(Tree<T> tree) {
        this.tree = tree;

        tree.traverseNodes(
            treeNode ->
                types.put(treeNode.getValue().getNodeId(), treeNode)
        );
    }

    public Tree<T> getRoot() {
        return tree;
    }

    public @Nullable Tree<T> getTreeNode(NodeId nodeId) {
        return types.get(nodeId);
    }

    public @Nullable T getType(NodeId nodeId) {
        Tree<T> node = types.get(nodeId);
        return node != null ? node.getValue() : null;
    }

    public boolean isSubtypeOf(T type, T superType) {
        return isSubtypeOf(type.getNodeId(), superType.getNodeId());
    }

    public boolean isSubtypeOf(T type, NodeId superTypeId) {
        return isSubtypeOf(type.getNodeId(), superTypeId);
    }

    public boolean isSubtypeOf(NodeId typeId, T superType) {
        return isSubtypeOf(typeId, superType.getNodeId());
    }

    public boolean isSubtypeOf(NodeId nodeId, NodeId superNodeId) {
        Tree<T> tree = getTreeNode(nodeId);
        if (tree == null) return false;

        Tree<T> parentTree = tree.getParent();
        if (parentTree == null) return false;

        NodeId parentNodeId = parentTree.getValue().getNodeId();

        return parentNodeId.equals(superNodeId) || isSubtypeOf(parentNodeId, superNodeId);
    }

    public interface Type {

        NodeId getNodeId();

    }

}
