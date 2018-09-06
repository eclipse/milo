/*
 * Copyright (c) 2018 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.server.nodes.factories;

import java.util.LinkedHashMap;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

class NodeTable {

    final LinkedHashMap<BrowsePath, NodeId> nodes = new LinkedHashMap<>();

    void addNode(BrowsePath browsePath, NodeId nodeId) {
        nodes.put(browsePath, nodeId);
    }

    static NodeTable merge(NodeTable table1, NodeTable table2) {
        NodeTable mergedNodeTable = new NodeTable();

        // Put all the second table's nodes first
        mergedNodeTable.nodes.putAll(table2.nodes);

        // Allow the first table's nodes to overwrite
        mergedNodeTable.nodes.putAll(table1.nodes);

        return mergedNodeTable;
    }

}
