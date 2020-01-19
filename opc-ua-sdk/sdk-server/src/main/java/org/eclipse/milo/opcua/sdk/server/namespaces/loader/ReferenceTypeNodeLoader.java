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
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

class ReferenceTypeNodeLoader {
    private final UaNodeContext context;

    private final NodeManager<UaNode> nodeManager;

    ReferenceTypeNodeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager) {
        this.context = context;
        this.nodeManager = nodeManager;
    }

    private void loadNode0() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.References, new QualifiedName(0, "References"), new LocalizedText("en", "References"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true, LocalizedText.NULL_VALUE);
        this.nodeManager.addNode(node);
    }

    private void loadNode1() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.NonHierarchicalReferences, new QualifiedName(0, "NonHierarchicalReferences"), new LocalizedText("en", "NonHierarchicalReferences"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, false, new LocalizedText("en", "NonHierarchicalReferences"));
        node.addReference(new Reference(Identifiers.NonHierarchicalReferences, Identifiers.HasSubtype, Identifiers.References.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode2() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HierarchicalReferences, new QualifiedName(0, "HierarchicalReferences"), new LocalizedText("en", "HierarchicalReferences"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, false, new LocalizedText("en", "HierarchicalReferences"));
        node.addReference(new Reference(Identifiers.HierarchicalReferences, Identifiers.HasSubtype, Identifiers.References.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode3() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasChild, new QualifiedName(0, "HasChild"), new LocalizedText("en", "HasChild"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "ChildOf"));
        node.addReference(new Reference(Identifiers.HasChild, Identifiers.HasSubtype, Identifiers.HierarchicalReferences.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode4() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.Organizes, new QualifiedName(0, "Organizes"), new LocalizedText("en", "Organizes"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "OrganizedBy"));
        node.addReference(new Reference(Identifiers.Organizes, Identifiers.HasSubtype, Identifiers.HierarchicalReferences.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode5() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasEventSource, new QualifiedName(0, "HasEventSource"), new LocalizedText("en", "HasEventSource"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "EventSourceOf"));
        node.addReference(new Reference(Identifiers.HasEventSource, Identifiers.HasSubtype, Identifiers.HierarchicalReferences.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode6() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasModellingRule, new QualifiedName(0, "HasModellingRule"), new LocalizedText("en", "HasModellingRule"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "ModellingRuleOf"));
        node.addReference(new Reference(Identifiers.HasModellingRule, Identifiers.HasSubtype, Identifiers.NonHierarchicalReferences.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode7() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasEncoding, new QualifiedName(0, "HasEncoding"), new LocalizedText("en", "HasEncoding"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "EncodingOf"));
        node.addReference(new Reference(Identifiers.HasEncoding, Identifiers.HasSubtype, Identifiers.NonHierarchicalReferences.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode8() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasDescription, new QualifiedName(0, "HasDescription"), new LocalizedText("en", "HasDescription"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "DescriptionOf"));
        node.addReference(new Reference(Identifiers.HasDescription, Identifiers.HasSubtype, Identifiers.NonHierarchicalReferences.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode9() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasTypeDefinition, new QualifiedName(0, "HasTypeDefinition"), new LocalizedText("en", "HasTypeDefinition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "TypeDefinitionOf"));
        node.addReference(new Reference(Identifiers.HasTypeDefinition, Identifiers.HasSubtype, Identifiers.NonHierarchicalReferences.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode10() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.GeneratesEvent, new QualifiedName(0, "GeneratesEvent"), new LocalizedText("en", "GeneratesEvent"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "GeneratesEvent"));
        node.addReference(new Reference(Identifiers.GeneratesEvent, Identifiers.HasSubtype, Identifiers.NonHierarchicalReferences.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode11() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.Aggregates, new QualifiedName(0, "Aggregates"), new LocalizedText("en", "Aggregates"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "AggregatedBy"));
        node.addReference(new Reference(Identifiers.Aggregates, Identifiers.HasSubtype, Identifiers.HasChild.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode12() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasSubtype, new QualifiedName(0, "HasSubtype"), new LocalizedText("en", "HasSubtype"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "SubtypeOf"));
        node.addReference(new Reference(Identifiers.HasSubtype, Identifiers.HasSubtype, Identifiers.HasChild.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode13() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasProperty, new QualifiedName(0, "HasProperty"), new LocalizedText("en", "HasProperty"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "PropertyOf"));
        node.addReference(new Reference(Identifiers.HasProperty, Identifiers.HasSubtype, Identifiers.Aggregates.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode14() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasComponent, new QualifiedName(0, "HasComponent"), new LocalizedText("en", "HasComponent"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "ComponentOf"));
        node.addReference(new Reference(Identifiers.HasComponent, Identifiers.HasSubtype, Identifiers.Aggregates.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode15() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasNotifier, new QualifiedName(0, "HasNotifier"), new LocalizedText("en", "HasNotifier"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "NotifierOf"));
        node.addReference(new Reference(Identifiers.HasNotifier, Identifiers.HasSubtype, Identifiers.HasEventSource.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode16() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasOrderedComponent, new QualifiedName(0, "HasOrderedComponent"), new LocalizedText("en", "HasOrderedComponent"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "OrderedComponentOf"));
        node.addReference(new Reference(Identifiers.HasOrderedComponent, Identifiers.HasSubtype, Identifiers.HasComponent.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode17() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.FromState, new QualifiedName(0, "FromState"), new LocalizedText("en", "FromState"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "ToTransition"));
        node.addReference(new Reference(Identifiers.FromState, Identifiers.HasSubtype, Identifiers.NonHierarchicalReferences.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode18() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.ToState, new QualifiedName(0, "ToState"), new LocalizedText("en", "ToState"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "FromTransition"));
        node.addReference(new Reference(Identifiers.ToState, Identifiers.HasSubtype, Identifiers.NonHierarchicalReferences.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode19() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasCause, new QualifiedName(0, "HasCause"), new LocalizedText("en", "HasCause"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "MayBeCausedBy"));
        node.addReference(new Reference(Identifiers.HasCause, Identifiers.HasSubtype, Identifiers.NonHierarchicalReferences.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode20() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasEffect, new QualifiedName(0, "HasEffect"), new LocalizedText("en", "HasEffect"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "MayBeEffectedBy"));
        node.addReference(new Reference(Identifiers.HasEffect, Identifiers.HasSubtype, Identifiers.NonHierarchicalReferences.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode21() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasHistoricalConfiguration, new QualifiedName(0, "HasHistoricalConfiguration"), new LocalizedText("en", "HasHistoricalConfiguration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "HistoricalConfigurationOf"));
        node.addReference(new Reference(Identifiers.HasHistoricalConfiguration, Identifiers.HasSubtype, Identifiers.Aggregates.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode22() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasSubStateMachine, new QualifiedName(0, "HasSubStateMachine"), new LocalizedText("en", "HasSubStateMachine"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "SubStateMachineOf"));
        node.addReference(new Reference(Identifiers.HasSubStateMachine, Identifiers.HasSubtype, Identifiers.NonHierarchicalReferences.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode23() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasTrueSubState, new QualifiedName(0, "HasTrueSubState"), new LocalizedText("en", "HasTrueSubState"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "IsTrueSubStateOf"));
        node.addReference(new Reference(Identifiers.HasTrueSubState, Identifiers.HasSubtype, Identifiers.NonHierarchicalReferences.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode24() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasFalseSubState, new QualifiedName(0, "HasFalseSubState"), new LocalizedText("en", "HasFalseSubState"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "IsFalseSubStateOf"));
        node.addReference(new Reference(Identifiers.HasFalseSubState, Identifiers.HasSubtype, Identifiers.NonHierarchicalReferences.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode25() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.HasCondition, new QualifiedName(0, "HasCondition"), new LocalizedText("en", "HasCondition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "IsConditionOf"));
        node.addReference(new Reference(Identifiers.HasCondition, Identifiers.HasSubtype, Identifiers.NonHierarchicalReferences.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode26() {
        UaReferenceTypeNode node = new UaReferenceTypeNode(this.context, Identifiers.AlwaysGeneratesEvent, new QualifiedName(0, "AlwaysGeneratesEvent"), new LocalizedText("en", "AlwaysGeneratesEvent"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false, false, new LocalizedText("en", "AlwaysGeneratesEvent"));
        node.addReference(new Reference(Identifiers.AlwaysGeneratesEvent, Identifiers.HasSubtype, Identifiers.GeneratesEvent.expanded(), false));
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
