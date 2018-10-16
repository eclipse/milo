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

}
