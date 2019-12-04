/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.namespaces.loader;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaReferenceTypeNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

class ReferenceTypeNodeLoader {
    private final UaNodeContext context;

    private final NodeManager<UaNode> nodeManager;

    ReferenceTypeNodeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager) {
        this.context = context;
        this.nodeManager = nodeManager;
    }

    private void loadNode0() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 31), new QualifiedName(0, "References"), new LocalizedText("en", "References"), new LocalizedText("en", "The abstract base type for all references."), UInteger.valueOf(0), UInteger.valueOf(0), true, true, LocalizedText.NULL_VALUE);
        this.nodeManager.addNode(node);
    }

    private void loadNode1() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 32), new QualifiedName(0, "NonHierarchicalReferences"), new LocalizedText("en", "NonHierarchicalReferences"), new LocalizedText("en", "The abstract base type for all non-hierarchical references."), UInteger.valueOf(0), UInteger.valueOf(0), true, false, new LocalizedText("en", "NonHierarchicalReferences"));
        node.addReference(new Reference(new NodeId(0, 32), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(31), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode2() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 33), new QualifiedName(0, "HierarchicalReferences"), new LocalizedText("en", "HierarchicalReferences"), new LocalizedText("en", "The abstract base type for all hierarchical references."), UInteger.valueOf(0), UInteger.valueOf(0), true, false, new LocalizedText("en", "HierarchicalReferences"));
        node.addReference(new Reference(new NodeId(0, 33), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(31), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode3() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 34), new QualifiedName(0, "HasChild"), new LocalizedText("en", "HasChild"), new LocalizedText("en", "The abstract base type for all non-looping hierarchical references."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "ChildOf"));
        node.addReference(new Reference(new NodeId(0, 34), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(33), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode4() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 35), new QualifiedName(0, "Organizes"), new LocalizedText("en", "Organizes"), new LocalizedText("en", "The type for hierarchical references that are used to organize nodes."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "OrganizedBy"));
        node.addReference(new Reference(new NodeId(0, 35), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(33), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode5() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 36), new QualifiedName(0, "HasEventSource"), new LocalizedText("en", "HasEventSource"), new LocalizedText("en", "The type for non-looping hierarchical references that are used to organize event sources."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "EventSourceOf"));
        node.addReference(new Reference(new NodeId(0, 36), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(33), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode6() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 37), new QualifiedName(0, "HasModellingRule"), new LocalizedText("en", "HasModellingRule"), new LocalizedText("en", "The type for references from instance declarations to modelling rule nodes."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "ModellingRuleOf"));
        node.addReference(new Reference(new NodeId(0, 37), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(32), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode7() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 38), new QualifiedName(0, "HasEncoding"), new LocalizedText("en", "HasEncoding"), new LocalizedText("en", "The type for references from data type nodes to to data type encoding nodes."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "EncodingOf"));
        node.addReference(new Reference(new NodeId(0, 38), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(32), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode8() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 39), new QualifiedName(0, "HasDescription"), new LocalizedText("en", "HasDescription"), new LocalizedText("en", "The type for references from data type encoding nodes to data type description nodes."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "DescriptionOf"));
        node.addReference(new Reference(new NodeId(0, 39), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(32), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode9() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 40), new QualifiedName(0, "HasTypeDefinition"), new LocalizedText("en", "HasTypeDefinition"), new LocalizedText("en", "The type for references from a instance node its type defintion node."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "TypeDefinitionOf"));
        node.addReference(new Reference(new NodeId(0, 40), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(32), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode10() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 41), new QualifiedName(0, "GeneratesEvent"), new LocalizedText("en", "GeneratesEvent"), new LocalizedText("en", "The type for references from a node to an event type that is raised by node."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "GeneratesEvent"));
        node.addReference(new Reference(new NodeId(0, 41), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(32), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode11() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 44), new QualifiedName(0, "Aggregates"), new LocalizedText("en", "Aggregates"), new LocalizedText("en", "The type for non-looping hierarchical references that are used to aggregate nodes into complex types."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "AggregatedBy"));
        node.addReference(new Reference(new NodeId(0, 44), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(34), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode12() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 45), new QualifiedName(0, "HasSubtype"), new LocalizedText("en", "HasSubtype"), new LocalizedText("en", "The type for non-looping hierarchical references that are used to define sub types."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "SubtypeOf"));
        node.addReference(new Reference(new NodeId(0, 45), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(34), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode13() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 46), new QualifiedName(0, "HasProperty"), new LocalizedText("en", "HasProperty"), new LocalizedText("en", "The type for non-looping hierarchical reference from a node to its property."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "PropertyOf"));
        node.addReference(new Reference(new NodeId(0, 46), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(44), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode14() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 47), new QualifiedName(0, "HasComponent"), new LocalizedText("en", "HasComponent"), new LocalizedText("en", "The type for non-looping hierarchical reference from a node to its component."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "ComponentOf"));
        node.addReference(new Reference(new NodeId(0, 47), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(44), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode15() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 48), new QualifiedName(0, "HasNotifier"), new LocalizedText("en", "HasNotifier"), new LocalizedText("en", "The type for non-looping hierarchical references that are used to indicate how events propagate from node to node."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "NotifierOf"));
        node.addReference(new Reference(new NodeId(0, 48), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(36), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode16() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 49), new QualifiedName(0, "HasOrderedComponent"), new LocalizedText("en", "HasOrderedComponent"), new LocalizedText("en", "The type for non-looping hierarchical reference from a node to its component when the order of references matters."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "OrderedComponentOf"));
        node.addReference(new Reference(new NodeId(0, 49), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(47), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode17() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 51), new QualifiedName(0, "FromState"), new LocalizedText("en", "FromState"), new LocalizedText("en", "The type for a reference to the state before a transition."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "ToTransition"));
        node.addReference(new Reference(new NodeId(0, 51), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(32), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode18() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 52), new QualifiedName(0, "ToState"), new LocalizedText("en", "ToState"), new LocalizedText("en", "The type for a reference to the state after a transition."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "FromTransition"));
        node.addReference(new Reference(new NodeId(0, 52), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(32), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode19() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 53), new QualifiedName(0, "HasCause"), new LocalizedText("en", "HasCause"), new LocalizedText("en", "The type for a reference to a method that can cause a transition to occur."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "MayBeCausedBy"));
        node.addReference(new Reference(new NodeId(0, 53), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(32), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode20() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 54), new QualifiedName(0, "HasEffect"), new LocalizedText("en", "HasEffect"), new LocalizedText("en", "The type for a reference to an event that may be raised when a transition occurs."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "MayBeEffectedBy"));
        node.addReference(new Reference(new NodeId(0, 54), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(32), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode21() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 56), new QualifiedName(0, "HasHistoricalConfiguration"), new LocalizedText("en", "HasHistoricalConfiguration"), new LocalizedText("en", "The type for a reference to the historical configuration for a data variable."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "HistoricalConfigurationOf"));
        node.addReference(new Reference(new NodeId(0, 56), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(44), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode22() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 117), new QualifiedName(0, "HasSubStateMachine"), new LocalizedText("en", "HasSubStateMachine"), new LocalizedText("en", "The type for a reference to a substate for a state."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "SubStateMachineOf"));
        node.addReference(new Reference(new NodeId(0, 117), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(32), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode23() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 9004), new QualifiedName(0, "HasTrueSubState"), new LocalizedText("en", "HasTrueSubState"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "IsTrueSubStateOf"));
        node.addReference(new Reference(new NodeId(0, 9004), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(32), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode24() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 9005), new QualifiedName(0, "HasFalseSubState"), new LocalizedText("en", "HasFalseSubState"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "IsFalseSubStateOf"));
        node.addReference(new Reference(new NodeId(0, 9005), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(32), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode25() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 9006), new QualifiedName(0, "HasCondition"), new LocalizedText("en", "HasCondition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "IsConditionOf"));
        node.addReference(new Reference(new NodeId(0, 9006), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(32), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode26() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, new NodeId(0, 3065), new QualifiedName(0, "AlwaysGeneratesEvent"), new LocalizedText("en", "AlwaysGeneratesEvent"), new LocalizedText("en", "The type for references from a node to an event type that is always raised by node."), UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "AlwaysGeneratesEvent"));
        node.addReference(new Reference(new NodeId(0, 3065), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(41), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    public void loadAllNodes() {
        loadNode0();
        loadNode1();
        loadNode2();
        loadNode3();
        loadNode4();
        loadNode5();
        loadNode6();
        loadNode7();
        loadNode8();
        loadNode9();
        loadNode10();
        loadNode11();
        loadNode12();
        loadNode13();
        loadNode14();
        loadNode15();
        loadNode16();
        loadNode17();
        loadNode18();
        loadNode19();
        loadNode20();
        loadNode21();
        loadNode22();
        loadNode23();
        loadNode24();
        loadNode25();
        loadNode26();
    }
}
