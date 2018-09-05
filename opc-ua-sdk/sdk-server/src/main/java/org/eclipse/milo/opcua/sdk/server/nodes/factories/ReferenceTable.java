package org.eclipse.milo.opcua.sdk.server.nodes.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.jooq.lambda.tuple.Tuple3;

class ReferenceTable {

    private final List<Tuple3<BrowsePath, NodeId, Target>> references = new ArrayList<>();

    void addReference(
        BrowsePath sourcePath,
        NodeId referenceTypeId,
        ExpandedNodeId targetNodeId) {

        references.add(new Tuple3<>(sourcePath, referenceTypeId, new Target(targetNodeId)));
    }

    void addReference(
        BrowsePath sourcePath,
        NodeId referenceTypeId,
        BrowsePath targetPath) {

        references.add(new Tuple3<>(sourcePath, referenceTypeId, new Target(targetPath)));
    }

    List<Tuple3<BrowsePath, NodeId, Target>> getReferences(BrowsePath sourcePath) {
        return references.stream().filter(t -> t.v1.equals(sourcePath)).collect(Collectors.toList());
    }

    static ReferenceTable merge(ReferenceTable table1, ReferenceTable table2) {
        ReferenceTable mergedTable = new ReferenceTable();

        mergedTable.references.addAll(table1.references);

        table2.references.forEach(row -> {
            BrowsePath browsePath = row.v1;
            NodeId referenceTypeId = row.v2;

            if (Identifiers.HasTypeDefinition.equals(referenceTypeId)) {
                // Don't merge a HasTypeDefinition reference if there's already one present
                // for a given BrowsePath.
                // This logic may need to be extended to include other Reference types for
                // which there should only be one.

                boolean noTypeDefinition = mergedTable.references.stream()
                    .noneMatch(r ->
                        r.v1.equals(browsePath) && r.v2.equals(Identifiers.HasTypeDefinition));

                if (noTypeDefinition) {
                    mergedTable.references.add(row);
                }
            } else if (!mergedTable.references.contains(row)) {
                mergedTable.references.add(row);
            }
        });

        return mergedTable;
    }

    static class Target {
        final ExpandedNodeId targetNodeId;
        final BrowsePath targetPath;

        Target(ExpandedNodeId targetNodeId) {
            this.targetNodeId = targetNodeId;
            this.targetPath = null;
        }

        Target(BrowsePath targetPath) {
            this.targetPath = targetPath;
            this.targetNodeId = null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Target target = (Target) o;
            return Objects.equals(targetNodeId, target.targetNodeId) &&
                Objects.equals(targetPath, target.targetPath);
        }

        @Override
        public int hashCode() {
            return Objects.hash(targetNodeId, targetPath);
        }
    }

//        @Override
//        public String toString() {
//            StringBuilder sb = new StringBuilder();
//            sb.append("--- ReferenceTable ---").append("\n");
//
//            rows.sort(Comparator.comparing(row -> row.sourcePath.browseName.toParseableString()));
//
//            rows.forEach(row -> {
//                sb.append(String.format("%-25s", row.sourcePath));
//                sb.append(String.format("%-20s", row.referenceTypeId.toParseableString()));
//
//                if (row.targetNodeId != null) {
//                    sb.append(String.format("%s", row.targetNodeId.toParseableString()));
//                } else {
//                    sb.append(String.format("%s", row.targetPath));
//                }
//
//                sb.append("\n");
//            });
//
//            return sb.toString();
//        }

}
