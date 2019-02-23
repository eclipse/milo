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
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaReferenceTypeNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class UaReferenceTypeLoader {

    private final UaNodeContext context;

    public UaReferenceTypeLoader(UaNodeContext context) {
        this.context = context;
    }

    private void buildNode0() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=31"), new QualifiedName(0, "References"), new LocalizedText("en", "References"), new LocalizedText("en", "The abstract base type for all references."), UInteger.valueOf(0L), UInteger.valueOf(0L), true, true, new LocalizedText("en", "References"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=31"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=32"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=31"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=33"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=31"), NodeId.parse("ns=0;i=35"), ExpandedNodeId.parse("svr=0;i=91"), NodeClass.Object, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode1() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=32"), new QualifiedName(0, "NonHierarchicalReferences"), new LocalizedText("en", "NonHierarchicalReferences"), new LocalizedText("en", "The abstract base type for all non-hierarchical references."), UInteger.valueOf(0L), UInteger.valueOf(0L), true, false, new LocalizedText("en", "NonHierarchicalReferences"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=32"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=31"), NodeClass.ReferenceType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=32"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=37"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=32"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=38"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=32"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=39"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=32"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=40"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=32"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=41"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=32"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=3065"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=32"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=51"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=32"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=52"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=32"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=53"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=32"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=54"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=32"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=117"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=32"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=9004"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=32"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=9005"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=32"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=9006"), NodeClass.ReferenceType, true));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode2() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=33"), new QualifiedName(0, "HierarchicalReferences"), new LocalizedText("en", "HierarchicalReferences"), new LocalizedText("en", "The abstract base type for all hierarchical references."), UInteger.valueOf(0L), UInteger.valueOf(0L), true, false, new LocalizedText("en", "HierarchicalReferences"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=33"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=31"), NodeClass.ReferenceType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=33"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=34"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=33"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=35"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=33"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=36"), NodeClass.ReferenceType, true));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode3() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=34"), new QualifiedName(0, "HasChild"), new LocalizedText("en", "HasChild"), new LocalizedText("en", "The abstract base type for all non-looping hierarchical references."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "ChildOf"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=34"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=33"), NodeClass.ReferenceType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=34"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=44"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=34"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=45"), NodeClass.ReferenceType, true));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode4() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=35"), new QualifiedName(0, "Organizes"), new LocalizedText("en", "Organizes"), new LocalizedText("en", "The type for hierarchical references that are used to organize nodes."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "OrganizedBy"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=35"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=33"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode5() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=36"), new QualifiedName(0, "HasEventSource"), new LocalizedText("en", "HasEventSource"), new LocalizedText("en", "The type for non-looping hierarchical references that are used to organize event sources."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "EventSourceOf"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=36"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=33"), NodeClass.ReferenceType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=36"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=48"), NodeClass.ReferenceType, true));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode6() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=37"), new QualifiedName(0, "HasModellingRule"), new LocalizedText("en", "HasModellingRule"), new LocalizedText("en", "The type for references from instance declarations to modelling rule nodes."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "ModellingRuleOf"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=37"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=32"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode7() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=38"), new QualifiedName(0, "HasEncoding"), new LocalizedText("en", "HasEncoding"), new LocalizedText("en", "The type for references from data type nodes to to data type encoding nodes."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "EncodingOf"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=38"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=32"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode8() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=39"), new QualifiedName(0, "HasDescription"), new LocalizedText("en", "HasDescription"), new LocalizedText("en", "The type for references from data type encoding nodes to data type description nodes."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "DescriptionOf"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=39"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=32"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode9() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=40"), new QualifiedName(0, "HasTypeDefinition"), new LocalizedText("en", "HasTypeDefinition"), new LocalizedText("en", "The type for references from a instance node its type defintion node."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "TypeDefinitionOf"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=40"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=32"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode10() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=41"), new QualifiedName(0, "GeneratesEvent"), new LocalizedText("en", "GeneratesEvent"), new LocalizedText("en", "The type for references from a node to an event type that is raised by node."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "GeneratesEvent"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=41"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=32"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode11() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=44"), new QualifiedName(0, "Aggregates"), new LocalizedText("en", "Aggregates"), new LocalizedText("en", "The type for non-looping hierarchical references that are used to aggregate nodes into complex types."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "AggregatedBy"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=44"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=34"), NodeClass.ReferenceType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=44"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=46"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=44"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=47"), NodeClass.ReferenceType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=44"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=56"), NodeClass.ReferenceType, true));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode12() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=45"), new QualifiedName(0, "HasSubtype"), new LocalizedText("en", "HasSubtype"), new LocalizedText("en", "The type for non-looping hierarchical references that are used to define sub types."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "HasSupertype"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=45"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=34"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode13() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=46"), new QualifiedName(0, "HasProperty"), new LocalizedText("en", "HasProperty"), new LocalizedText("en", "The type for non-looping hierarchical reference from a node to its property."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "PropertyOf"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=46"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=44"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode14() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=47"), new QualifiedName(0, "HasComponent"), new LocalizedText("en", "HasComponent"), new LocalizedText("en", "The type for non-looping hierarchical reference from a node to its component."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "ComponentOf"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=47"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=44"), NodeClass.ReferenceType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=47"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=49"), NodeClass.ReferenceType, true));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode15() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=48"), new QualifiedName(0, "HasNotifier"), new LocalizedText("en", "HasNotifier"), new LocalizedText("en", "The type for non-looping hierarchical references that are used to indicate how events propagate from node to node."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "NotifierOf"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=48"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=36"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode16() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=49"), new QualifiedName(0, "HasOrderedComponent"), new LocalizedText("en", "HasOrderedComponent"), new LocalizedText("en", "The type for non-looping hierarchical reference from a node to its component when the order of references matters."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "OrderedComponentOf"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=49"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=47"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode17() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=51"), new QualifiedName(0, "FromState"), new LocalizedText("en", "FromState"), new LocalizedText("en", "The type for a reference to the state before a transition."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "ToTransition"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=51"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=32"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode18() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=52"), new QualifiedName(0, "ToState"), new LocalizedText("en", "ToState"), new LocalizedText("en", "The type for a reference to the state after a transition."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "FromTransition"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=52"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=32"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode19() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=53"), new QualifiedName(0, "HasCause"), new LocalizedText("en", "HasCause"), new LocalizedText("en", "The type for a reference to a method that can cause a transition to occur."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "MayBeCausedBy"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=53"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=32"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode20() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=54"), new QualifiedName(0, "HasEffect"), new LocalizedText("en", "HasEffect"), new LocalizedText("en", "The type for a reference to an event that may be raised when a transition occurs."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "MayBeEffectedBy"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=54"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=32"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode21() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=56"), new QualifiedName(0, "HasHistoricalConfiguration"), new LocalizedText("en", "HasHistoricalConfiguration"), new LocalizedText("en", "The type for a reference to the historical configuration for a data variable."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "HistoricalConfigurationOf"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=56"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=44"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode22() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=117"), new QualifiedName(0, "HasSubStateMachine"), new LocalizedText("en", "HasSubStateMachine"), new LocalizedText("en", "The type for a reference to a substate for a state."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "SubStateMachineOf"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=117"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=32"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode23() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=9004"), new QualifiedName(0, "HasTrueSubState"), new LocalizedText("en", "HasTrueSubState"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "IsTrueSubStateOf"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9004"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=32"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode24() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=9005"), new QualifiedName(0, "HasFalseSubState"), new LocalizedText("en", "HasFalseSubState"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "IsFalseSubStateOf"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9005"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=32"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode25() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=9006"), new QualifiedName(0, "HasCondition"), new LocalizedText("en", "HasCondition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "IsConditionOf"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9006"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=32"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    private void buildNode26() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, NodeId.parse("ns=0;i=3065"), new QualifiedName(0, "AlwaysGeneratesEvent"), new LocalizedText("en", "AlwaysGeneratesEvent"), new LocalizedText("en", "The type for references from a node to an event type that is always raised by node."), UInteger.valueOf(0L), UInteger.valueOf(0L), false, false, new LocalizedText("en", "AlwaysGeneratesEvent"));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3065"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=32"), NodeClass.ReferenceType, false));
        this.context.getNodeManager().addNode(node);
    }

    public void buildNodes() {
        buildNode0();
        buildNode1();
        buildNode2();
        buildNode3();
        buildNode4();
        buildNode5();
        buildNode6();
        buildNode7();
        buildNode8();
        buildNode9();
        buildNode10();
        buildNode11();
        buildNode12();
        buildNode13();
        buildNode14();
        buildNode15();
        buildNode16();
        buildNode17();
        buildNode18();
        buildNode19();
        buildNode20();
        buildNode21();
        buildNode22();
        buildNode23();
        buildNode24();
        buildNode25();
        buildNode26();
    }

}
