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

//        @Override
//        public String toString() {
//            StringBuilder sb = new StringBuilder();
//            sb.append("--- NodeTable ---").append("\n");
//
//            rows.sort(Comparator.comparing(row -> row.browsePath.browseName.toParseableString()));
//
//            rows.forEach(row -> {
//                sb.append(String.format("%-25s", row.browsePath));
//                sb.append(row.nodeId.toParseableString());
//                sb.append("\n");
//            });
//
//            return sb.toString();
//        }

}
