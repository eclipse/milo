/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes.factories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.AddressSpaceManager;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class InstanceDeclarationHierarchy {

    private final NodeId typeId;
    private final NodeTable nodeTable;
    private final ReferenceTable referenceTable;

    private InstanceDeclarationHierarchy(
        NodeId typeId,
        NodeTable nodeTable,
        ReferenceTable referenceTable) {

        this.typeId = typeId;
        this.nodeTable = nodeTable;
        this.referenceTable = referenceTable;
    }

    NodeTable getNodeTable() {
        return nodeTable;
    }

    ReferenceTable getReferenceTable() {
        return referenceTable;
    }

    private InstanceDeclarationHierarchy merge(InstanceDeclarationHierarchy parent) {
        NodeTable mergedNodeTable =
            NodeTable.merge(this.nodeTable, parent.nodeTable);

        ReferenceTable mergedReferenceTable =
            ReferenceTable.merge(this.referenceTable, parent.referenceTable);

        return new InstanceDeclarationHierarchy(typeId, mergedNodeTable, mergedReferenceTable);
    }

    public static InstanceDeclarationHierarchy create(
        AddressSpaceManager addressSpaceManager,
        NamespaceTable namespaceTable,
        NodeId typeDefinitionId,
        boolean includeOptionalNodes) {

        Builder builder = new Builder(addressSpaceManager, namespaceTable, includeOptionalNodes);

        return builder.build(typeDefinitionId, includeOptionalNodes);
    }

    static class Builder {

        private final NodeTable nodeTable = new NodeTable();
        private final ReferenceTable referenceTable = new ReferenceTable();

        private final AddressSpaceManager addressSpaceManager;
        private final NamespaceTable namespaceTable;
        private final boolean includeOptionalNodes;

        Builder(
            AddressSpaceManager addressSpaceManager,
            NamespaceTable namespaceTable,
            boolean includeOptionalNodes
        ) {

            this.addressSpaceManager = addressSpaceManager;
            this.namespaceTable = namespaceTable;
            this.includeOptionalNodes = includeOptionalNodes;
        }

        public InstanceDeclarationHierarchy build(NodeId typeDefinitionId, boolean includeOptionalNodes) {
            Optional<InstanceDeclarationHierarchy> parentIdh = addressSpaceManager
                .getManagedReferences(typeDefinitionId)
                .stream()
                .filter(r -> r.isInverse() && Identifiers.HasSubtype.equals(r.getReferenceTypeId()))
                .findFirst()
                .flatMap(r -> r.getTargetNodeId().local(namespaceTable))
                .map(parentTypeId -> InstanceDeclarationHierarchy
                    .create(addressSpaceManager, namespaceTable, parentTypeId, includeOptionalNodes));

            final InstanceDeclarationHierarchy idh = buildHierarchyForType(typeDefinitionId);

            return parentIdh.map(idh::merge).orElse(idh);
        }

        private InstanceDeclarationHierarchy buildHierarchyForType(NodeId typeDefinitionId) {
            BrowsePath browsePath = BrowsePath.ROOT;

            nodeTable.addNode(browsePath, typeDefinitionId);
            referenceTable.addReference(browsePath, Identifiers.HasTypeDefinition, typeDefinitionId.expanded());

            addModeledNodes(typeDefinitionId, browsePath);

            return new InstanceDeclarationHierarchy(typeDefinitionId, nodeTable, referenceTable);
        }

        private void addModeledNodes(NodeId nodeId, BrowsePath parentPath) {
            List<Reference> forwardReferences = addressSpaceManager.getManagedReferences(nodeId)
                .stream()
                .filter(Reference::isForward)
                .collect(Collectors.toList());

            forwardReferences.forEach(reference ->
                addressSpaceManager.getManagedNode(reference.getTargetNodeId()).ifPresent(node -> {
                    // All InstanceDeclarations of the InstanceDeclarationHierarchy and all Nodes referenced with a
                    // non-hierarchical Reference from such an InstanceDeclaration are added to the table.
                    // Hierarchical References to Nodes without a ModellingRule are not considered.

                    BrowsePath browsePath = new BrowsePath(parentPath, node.getBrowseName());

                    if (isInstanceDeclaration(node, includeOptionalNodes)) {
                        nodeTable.addNode(browsePath, node.getNodeId());
                        referenceTable.addReference(parentPath, reference.getReferenceTypeId(), browsePath);

                        node.getReferences().stream()
                            .filter(r -> r.subtypeOf(Identifiers.NonHierarchicalReferences))
                            .forEach(r -> referenceTable
                                .addReference(browsePath, r.getReferenceTypeId(), r.getTargetNodeId()));

                        addModeledNodes(node.getNodeId(), browsePath);
                    }
                })
            );
        }

        private static boolean isInstanceDeclaration(UaNode node, boolean includeOptionalNodes) {
            NodeClass nodeClass = node.getNodeClass();

            boolean methodOrObjectOrVariable =
                nodeClass == NodeClass.Method ||
                    nodeClass == NodeClass.Object ||
                    nodeClass == NodeClass.Variable;

            return methodOrObjectOrVariable && hasModellingRule(node, includeOptionalNodes);
        }

        private static boolean hasModellingRule(UaNode node, boolean includeOptionalNodes) {
            return node.getReferences().stream()
                .anyMatch(r -> {
                    boolean rule =
                        Identifiers.ModellingRule_Mandatory.expanded().equals(r.getTargetNodeId()) ||
                            (includeOptionalNodes &&
                                Identifiers.ModellingRule_Optional.expanded().equals(r.getTargetNodeId()));

                    return Identifiers.HasModellingRule.equals(r.getReferenceTypeId()) && rule;
                });
        }

    }


}
