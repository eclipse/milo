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
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class UaMethodLoader {

    private final UaNodeContext context;
    private final NodeManager<UaNode> nodeManager;

    public UaMethodLoader(UaNodeContext context, NodeManager<UaNode> nodeManager) {
        this.context = context;
        this.nodeManager = nodeManager;
    }

    private void buildNode0() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12543"), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12543"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12522"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12543"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12544"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12543"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12545"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12543"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12543"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12522"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12543"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12544"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12543"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12545"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode1() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12546"), new QualifiedName(0, "CloseAndUpdate"), new LocalizedText("en", "CloseAndUpdate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12546"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12522"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12546"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12705"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12546"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12547"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12546"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=80"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12546"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12522"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12546"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12705"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12546"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12547"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode2() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12548"), new QualifiedName(0, "AddCertificate"), new LocalizedText("en", "AddCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12548"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12522"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12548"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12549"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12548"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=80"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12548"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12522"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12548"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12549"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode3() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12550"), new QualifiedName(0, "RemoveCertificate"), new LocalizedText("en", "RemoveCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12550"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12522"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12550"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12551"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12550"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=80"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12550"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12522"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12550"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12551"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode4() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12616"), new QualifiedName(0, "UpdateCertificate"), new LocalizedText("en", "UpdateCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12616"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12581"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12617"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12618"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12616"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12616"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12581"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12617"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12618"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode5() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12647"), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12647"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12647"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12648"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12647"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12649"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12647"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12647"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12648"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12647"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12649"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode6() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12650"), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12650"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12650"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12651"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12650"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12650"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12651"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode7() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12652"), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12652"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12652"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12653"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12652"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12654"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12652"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12652"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12653"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12652"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12654"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode8() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12655"), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12655"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12655"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12656"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12655"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12655"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12656"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode9() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12657"), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12657"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12657"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12658"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12657"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12659"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12657"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12657"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12658"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12657"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12659"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode10() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12660"), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12660"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12660"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12661"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12660"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12660"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12661"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode11() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12663"), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12663"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12663"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12664"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12663"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12665"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12663"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12663"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12664"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12663"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12665"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode12() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12666"), new QualifiedName(0, "CloseAndUpdate"), new LocalizedText("en", "CloseAndUpdate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12666"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12666"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14160"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12666"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12667"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12666"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12666"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14160"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12666"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12667"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode13() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12668"), new QualifiedName(0, "AddCertificate"), new LocalizedText("en", "AddCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12668"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12668"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12669"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12668"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12668"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12669"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode14() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12670"), new QualifiedName(0, "RemoveCertificate"), new LocalizedText("en", "RemoveCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12670"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12670"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12671"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12670"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12670"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12671"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode15() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12731"), new QualifiedName(0, "CreateSigningRequest"), new LocalizedText("en", "CreateSigningRequest"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12731"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12581"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12731"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12732"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12731"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12733"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12731"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12731"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12581"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12731"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12732"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12731"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12733"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode16() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12734"), new QualifiedName(0, "ApplyChanges"), new LocalizedText("en", "ApplyChanges"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12734"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12581"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12734"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12734"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12581"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode17() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12737"), new QualifiedName(0, "CreateSigningRequest"), new LocalizedText("en", "CreateSigningRequest"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12737"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12637"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12737"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12738"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12737"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12739"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12737"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12637"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12737"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12738"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12737"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12739"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode18() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12740"), new QualifiedName(0, "ApplyChanges"), new LocalizedText("en", "ApplyChanges"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12740"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12637"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12740"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12637"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode19() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12746"), new QualifiedName(0, "SetSubscriptionDurable"), new LocalizedText("en", "SetSubscriptionDurable"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12746"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2004"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12746"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12747"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12746"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12748"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12746"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=80"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12746"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2004"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12746"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12747"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12746"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12748"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode20() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12749"), new QualifiedName(0, "SetSubscriptionDurable"), new LocalizedText("en", "SetSubscriptionDurable"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12749"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2253"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12749"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12750"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12749"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12751"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12749"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2253"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12749"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12750"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12749"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12751"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode21() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12775"), new QualifiedName(0, "GetRejectedList"), new LocalizedText("en", "GetRejectedList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12775"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12581"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12775"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12776"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12775"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12775"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12581"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12775"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12776"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode22() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12777"), new QualifiedName(0, "GetRejectedList"), new LocalizedText("en", "GetRejectedList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12777"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12637"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12777"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12778"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12777"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12637"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12777"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12778"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode23() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12871"), new QualifiedName(0, "ResendData"), new LocalizedText("en", "ResendData"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12871"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2004"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12871"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12872"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12871"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=80"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12871"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2004"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12871"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12872"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode24() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12873"), new QualifiedName(0, "ResendData"), new LocalizedText("en", "ResendData"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12873"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2253"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12873"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12874"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12873"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2253"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12873"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12874"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode25() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12883"), new QualifiedName(0, "RequestServerStateChange"), new LocalizedText("en", "RequestServerStateChange"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12883"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2004"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12883"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12884"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12883"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=80"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12883"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2004"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12883"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12884"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode26() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12886"), new QualifiedName(0, "RequestServerStateChange"), new LocalizedText("en", "RequestServerStateChange"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12886"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2253"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12886"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12887"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12886"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2253"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12886"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12887"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode27() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=12912"), new QualifiedName(0, "ConditionRefresh2"), new LocalizedText("en", "ConditionRefresh2"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12912"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2782"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12912"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12913"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12912"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=2787"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12912"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=2788"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12912"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2782"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12912"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12913"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode28() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=9027"), new QualifiedName(0, "Enable"), new LocalizedText("en", "Enable"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=9027"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2782"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9027"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=2803"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9027"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9027"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2782"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode29() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=9028"), new QualifiedName(0, "Disable"), new LocalizedText("en", "Disable"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=9028"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2782"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9028"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=2803"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9028"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9028"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2782"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode30() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=9029"), new QualifiedName(0, "AddComment"), new LocalizedText("en", "AddComment"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=9029"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2782"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9029"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9030"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9029"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=2829"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9029"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9029"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2782"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9029"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9030"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode31() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=9069"), new QualifiedName(0, "Respond"), new LocalizedText("en", "Respond"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=9069"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2830"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9069"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9070"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9069"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=8927"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9069"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9069"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2830"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9069"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9070"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode32() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=9111"), new QualifiedName(0, "Acknowledge"), new LocalizedText("en", "Acknowledge"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=9111"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2881"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9111"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9112"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9111"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=8944"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9111"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9111"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2881"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9111"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9112"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode33() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=9113"), new QualifiedName(0, "Confirm"), new LocalizedText("en", "Confirm"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=9113"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2881"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9113"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9114"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9113"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=8961"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9113"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=80"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9113"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2881"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9113"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9114"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode34() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=9211"), new QualifiedName(0, "Unshelve"), new LocalizedText("en", "Unshelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=9211"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9178"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9211"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=11093"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9211"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9211"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9178"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode35() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=9212"), new QualifiedName(0, "OneShotShelve"), new LocalizedText("en", "OneShotShelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=9212"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9178"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9212"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=11093"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9212"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9212"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9178"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode36() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=9213"), new QualifiedName(0, "TimedShelve"), new LocalizedText("en", "TimedShelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=9213"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9178"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9213"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9214"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9213"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=11093"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9213"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9213"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9178"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9213"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9214"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode37() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13355"), new QualifiedName(0, "CreateDirectory"), new LocalizedText("en", "CreateDirectory"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13355"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13354"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13355"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13356"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13355"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13357"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13355"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13355"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13354"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13355"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13356"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13355"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13357"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode38() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13358"), new QualifiedName(0, "CreateFile"), new LocalizedText("en", "CreateFile"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13358"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13354"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13358"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13359"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13358"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13360"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13358"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13358"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13354"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13358"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13359"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13358"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13360"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode39() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13361"), new QualifiedName(0, "Delete"), new LocalizedText("en", "Delete"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13361"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13354"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13361"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13362"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13361"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13361"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13354"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13361"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13362"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode40() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13363"), new QualifiedName(0, "MoveOrCopy"), new LocalizedText("en", "MoveOrCopy"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13363"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13354"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13363"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13364"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13363"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13365"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13363"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13363"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13354"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13363"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13364"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13363"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13365"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode41() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13372"), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13372"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13366"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13372"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13373"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13372"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13374"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13372"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13372"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13366"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13372"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13373"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13372"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13374"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode42() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13375"), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13375"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13366"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13375"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13376"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13375"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13375"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13366"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13375"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13376"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode43() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13377"), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13377"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13366"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13377"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13378"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13377"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13379"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13377"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13377"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13366"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13377"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13378"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13377"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13379"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode44() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13380"), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13380"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13366"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13380"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13381"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13380"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13380"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13366"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13380"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13381"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode45() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13382"), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13382"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13366"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13382"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13383"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13382"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13384"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13382"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13382"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13366"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13382"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13383"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13382"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13384"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode46() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13385"), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13385"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13366"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13385"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13386"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13385"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13385"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13366"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13385"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13386"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode47() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13387"), new QualifiedName(0, "CreateDirectory"), new LocalizedText("en", "CreateDirectory"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13387"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13353"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13387"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13388"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13387"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13389"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13387"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13387"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13353"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13387"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13388"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13387"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13389"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode48() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13390"), new QualifiedName(0, "CreateFile"), new LocalizedText("en", "CreateFile"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13390"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13353"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13390"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13391"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13390"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13392"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13390"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13390"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13353"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13390"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13391"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13390"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13392"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode49() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13393"), new QualifiedName(0, "Delete"), new LocalizedText("en", "Delete"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13393"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13353"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13393"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13394"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13393"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13393"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13353"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13393"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13394"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode50() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13395"), new QualifiedName(0, "MoveOrCopy"), new LocalizedText("en", "MoveOrCopy"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13395"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13353"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13395"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13396"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13395"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13397"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13395"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13395"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13353"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13395"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13396"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13395"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13397"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode51() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13605"), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13605"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13605"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13606"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13605"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13607"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13605"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13605"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13605"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13606"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13605"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13607"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode52() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13608"), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13608"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13608"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13609"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13608"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13608"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13608"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13609"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode53() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13610"), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13610"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13610"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13611"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13610"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13612"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13610"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13610"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13610"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13611"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13610"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13612"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode54() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13613"), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13613"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13613"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13614"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13613"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13613"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13613"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13614"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode55() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13615"), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13615"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13615"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13616"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13615"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13617"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13615"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13615"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13615"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13616"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13615"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13617"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode56() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13618"), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13618"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13618"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13619"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13618"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13618"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13618"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13619"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode57() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13621"), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13621"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13621"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13622"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13621"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13623"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13621"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13621"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13621"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13622"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13621"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13623"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode58() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13737"), new QualifiedName(0, "UpdateCertificate"), new LocalizedText("en", "UpdateCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13737"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12637"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13737"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13738"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13737"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13739"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13737"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12637"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13737"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13738"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13737"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13739"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode59() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13821"), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13821"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13815"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13821"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13822"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13821"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13823"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13821"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13821"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13815"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13821"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13822"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13821"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13823"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode60() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13824"), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13824"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13815"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13824"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13825"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13824"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13824"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13815"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13824"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13825"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode61() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13826"), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13826"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13815"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13826"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13827"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13826"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13828"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13826"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13826"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13815"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13826"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13827"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13826"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13828"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode62() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13829"), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13829"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13815"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13829"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13830"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13829"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13829"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13815"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13829"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13830"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode63() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13831"), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13831"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13815"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13831"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13832"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13831"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13833"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13831"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13831"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13815"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13831"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13832"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13831"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13833"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode64() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13834"), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13834"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13815"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13834"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13835"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13834"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13834"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13815"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13834"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13835"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode65() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13837"), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13837"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13815"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13837"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13838"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13837"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13839"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13837"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13837"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13815"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13837"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13838"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13837"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13839"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode66() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13855"), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13855"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13849"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13855"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13856"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13855"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13857"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13855"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13855"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13849"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13855"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13856"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13855"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13857"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode67() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13858"), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13858"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13849"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13858"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13859"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13858"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13858"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13849"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13858"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13859"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode68() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13860"), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13860"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13849"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13860"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13861"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13860"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13862"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13860"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13860"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13849"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13860"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13861"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13860"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13862"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode69() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13863"), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13863"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13849"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13863"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13864"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13863"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13863"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13849"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13863"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13864"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode70() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13865"), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13865"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13849"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13865"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13866"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13865"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13867"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13865"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13865"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13849"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13865"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13866"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13865"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13867"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode71() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13868"), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13868"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13849"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13868"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13869"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13868"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13868"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13849"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13868"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13869"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode72() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13871"), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13871"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13849"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13871"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13872"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13871"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13873"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13871"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13871"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13849"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13871"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13872"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13871"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13873"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode73() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13889"), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13889"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13883"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13889"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13890"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13889"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13891"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13889"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13889"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13883"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13889"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13890"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13889"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13891"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode74() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13892"), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13892"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13883"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13892"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13893"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13892"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13892"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13883"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13892"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13893"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode75() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13894"), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13894"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13883"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13894"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13895"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13894"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13896"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13894"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13894"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13883"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13894"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13895"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13894"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13896"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode76() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13897"), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13897"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13883"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13897"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13898"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13897"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13897"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13883"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13897"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13898"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode77() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13899"), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13899"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13883"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13899"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13900"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13899"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13901"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13899"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13899"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13883"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13899"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13900"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13899"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13901"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode78() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13902"), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13902"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13883"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13902"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13903"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13902"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13902"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13883"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13902"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13903"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode79() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13905"), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13905"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13883"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13905"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13906"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13905"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13907"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13905"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13905"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13883"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13905"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13906"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13905"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13907"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode80() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13923"), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13923"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13917"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13923"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13924"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13923"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13925"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13923"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13923"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13917"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13923"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13924"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13923"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13925"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode81() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13926"), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13926"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13917"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13926"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13927"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13926"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13926"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13917"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13926"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13927"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode82() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13928"), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13928"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13917"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13928"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13929"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13928"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13930"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13928"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13928"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13917"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13928"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13929"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13928"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13930"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode83() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13931"), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13931"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13917"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13931"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13932"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13931"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13931"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13917"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13931"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13932"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode84() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13933"), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13933"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13917"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13933"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13934"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13933"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13935"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13933"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13933"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13917"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13933"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13934"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13933"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13935"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode85() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13936"), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13936"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13917"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13936"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13937"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13936"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13936"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13917"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13936"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13937"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode86() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13939"), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13939"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13917"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13939"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13940"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13939"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13941"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13939"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13939"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13917"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13939"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13940"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13939"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13941"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode87() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13958"), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13958"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13952"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13958"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13959"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13958"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13960"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13958"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13958"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13952"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13958"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13959"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13958"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13960"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode88() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13961"), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13961"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13952"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13961"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13962"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13961"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13961"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13952"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13961"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13962"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode89() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13963"), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13963"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13952"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13963"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13964"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13963"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13965"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13963"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13963"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13952"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13963"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13964"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13963"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13965"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode90() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13966"), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13966"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13952"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13966"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13967"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13966"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13966"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13952"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13966"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13967"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode91() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13968"), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13968"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13952"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13968"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13969"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13968"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13970"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13968"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13968"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13952"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13968"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13969"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13968"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13970"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode92() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13971"), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13971"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13952"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13971"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13972"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13971"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13971"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13952"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13971"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13972"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode93() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=13974"), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13974"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13952"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13974"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13975"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13974"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13976"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13974"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13974"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13952"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13974"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13975"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13974"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13976"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode94() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14095"), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14095"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14095"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14096"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14095"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14097"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14095"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14095"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14096"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14095"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14097"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode95() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14098"), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14098"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14098"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14099"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14098"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14098"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14099"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode96() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14100"), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14100"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14100"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14101"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14100"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14102"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14100"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14100"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14101"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14100"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14102"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode97() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14103"), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14103"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14103"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14104"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14103"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14103"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14104"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode98() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14105"), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14105"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14105"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14106"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14105"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14107"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14105"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14105"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14106"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14105"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14107"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode99() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14108"), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14108"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14108"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14109"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14108"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14108"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14109"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode100() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14111"), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14111"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14111"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14112"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14111"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14113"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14111"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14111"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14112"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14111"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14113"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode101() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14114"), new QualifiedName(0, "CloseAndUpdate"), new LocalizedText("en", "CloseAndUpdate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14114"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14114"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14115"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14114"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14116"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14114"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14114"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14115"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14114"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14116"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode102() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14117"), new QualifiedName(0, "AddCertificate"), new LocalizedText("en", "AddCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14117"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14117"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14118"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14117"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14117"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14118"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode103() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14119"), new QualifiedName(0, "RemoveCertificate"), new LocalizedText("en", "RemoveCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14119"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14119"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14120"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14119"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14119"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14120"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode104() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14129"), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14129"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14129"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14130"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14129"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14131"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14129"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14129"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14130"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14129"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14131"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode105() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14132"), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14132"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14132"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14133"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14132"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14132"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14133"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode106() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14134"), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14134"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14134"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14135"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14134"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14136"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14134"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14134"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14135"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14134"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14136"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode107() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14137"), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14137"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14137"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14138"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14137"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14137"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14138"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode108() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14139"), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14139"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14139"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14140"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14139"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14141"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14139"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14139"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14140"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14139"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14141"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode109() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14142"), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14142"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14142"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14143"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14142"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14142"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14143"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode110() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14145"), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14145"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14145"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14146"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14145"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14147"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14145"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14145"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14146"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14145"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14147"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode111() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14148"), new QualifiedName(0, "CloseAndUpdate"), new LocalizedText("en", "CloseAndUpdate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14148"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14148"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14149"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14148"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14150"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14148"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14148"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14149"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14148"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14150"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode112() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14151"), new QualifiedName(0, "AddCertificate"), new LocalizedText("en", "AddCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14151"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14151"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14152"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14151"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14151"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14152"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode113() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=14153"), new QualifiedName(0, "RemoveCertificate"), new LocalizedText("en", "RemoveCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=14153"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14153"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14154"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14153"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=14153"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=14154"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode114() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=2426"), new QualifiedName(0, "Start"), new LocalizedText("en", "Start"), new LocalizedText("en", "Causes the Program to transition from the Ready state to the Running state."), UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2426"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2391"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2426"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2410"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2426"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2410"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2426"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2426"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2391"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode115() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=2427"), new QualifiedName(0, "Suspend"), new LocalizedText("en", "Suspend"), new LocalizedText("en", "Causes the Program to transition from the Running state to the Suspended state."), UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2427"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2391"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2427"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2416"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2427"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2416"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2427"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2427"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2391"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode116() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=2428"), new QualifiedName(0, "Resume"), new LocalizedText("en", "Resume"), new LocalizedText("en", "Causes the Program to transition from the Suspended state to the Running state."), UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2428"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2391"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2428"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2418"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2428"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2418"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2428"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2428"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2391"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode117() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=2429"), new QualifiedName(0, "Halt"), new LocalizedText("en", "Halt"), new LocalizedText("en", "Causes the Program to transition from the Ready, Running or Suspended state to the Halted state."), UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2429"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2391"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2429"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2412"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2429"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2420"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2429"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2424"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2429"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2412"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2429"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2420"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2429"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2424"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2429"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2429"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2391"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode118() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=2430"), new QualifiedName(0, "Reset"), new LocalizedText("en", "Reset"), new LocalizedText("en", "Causes the Program to transition from the Halted state to the Ready state."), UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2430"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2391"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2430"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2408"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2430"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2408"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2430"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2430"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2391"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode119() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=2947"), new QualifiedName(0, "Unshelve"), new LocalizedText("en", "Unshelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2947"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2929"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2947"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2940"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2947"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2943"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2947"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2940"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2947"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2943"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2947"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=11093"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2947"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2947"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2929"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode120() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=2948"), new QualifiedName(0, "OneShotShelve"), new LocalizedText("en", "OneShotShelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2948"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2929"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2948"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2936"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2948"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2942"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2948"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2936"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2948"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2942"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2948"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=11093"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2948"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2948"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2929"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode121() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=2949"), new QualifiedName(0, "TimedShelve"), new LocalizedText("en", "TimedShelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2949"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2929"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2949"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2935"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2949"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2945"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2949"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2991"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2949"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2935"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2949"), NodeId.parse("ns=0;i=53"), ExpandedNodeId.parse("svr=0;i=2945"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2949"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=11093"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2949"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2949"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2929"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2949"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2991"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode122() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11489"), new QualifiedName(0, "GetMonitoredItems"), new LocalizedText("en", "GetMonitoredItems"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11489"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2004"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11489"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11490"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11489"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11491"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11489"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=80"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11489"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2004"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11489"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11490"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11489"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11491"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode123() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11492"), new QualifiedName(0, "GetMonitoredItems"), new LocalizedText("en", "GetMonitoredItems"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11492"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2253"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11492"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11493"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11492"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11494"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11492"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2253"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11492"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11493"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11492"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11494"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode124() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11580"), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11580"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11575"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11580"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11581"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11580"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11582"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11580"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11580"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11575"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11580"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11581"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11580"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11582"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode125() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11583"), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11583"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11575"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11583"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11584"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11583"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11583"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11575"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11583"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11584"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode126() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11585"), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11585"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11575"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11585"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11586"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11585"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11587"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11585"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11585"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11575"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11585"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11586"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11585"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11587"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode127() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11588"), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11588"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11575"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11588"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11589"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11588"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11588"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11575"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11588"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11589"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode128() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11590"), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11590"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11575"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11590"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11591"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11590"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11592"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11590"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11590"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11575"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11590"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11591"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11590"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11592"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode129() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11593"), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11593"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11575"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11593"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11594"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11593"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11593"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11575"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11593"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11594"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode130() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11615"), new QualifiedName(0, "ExportNamespace"), new LocalizedText("en", "ExportNamespace"), new LocalizedText("en", "Updates the file by exporting the server namespace."), UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11615"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11595"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11615"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=80"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11615"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11595"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode131() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11629"), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11629"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11624"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11629"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11630"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11629"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11631"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11629"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11629"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11624"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11629"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11630"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11629"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11631"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode132() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11632"), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11632"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11624"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11632"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11633"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11632"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11632"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11624"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11632"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11633"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode133() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11634"), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11634"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11624"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11634"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11635"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11634"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11636"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11634"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11634"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11624"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11634"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11635"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11634"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11636"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode134() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11637"), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11637"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11624"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11637"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11638"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11637"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11637"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11624"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11637"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11638"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode135() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11639"), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11639"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11624"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11639"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11640"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11639"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11641"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11639"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11639"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11624"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11639"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11640"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11639"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11641"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode136() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11642"), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11642"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11624"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11642"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11643"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11642"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11642"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11624"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11642"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11643"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode137() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11680"), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11680"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11675"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11680"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11681"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11680"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11682"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11680"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11680"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11675"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11680"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11681"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11680"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11682"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode138() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11683"), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11683"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11675"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11683"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11684"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11683"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11683"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11675"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11683"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11684"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode139() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11685"), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11685"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11675"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11685"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11686"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11685"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11687"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11685"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11685"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11675"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11685"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11686"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11685"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11687"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode140() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11688"), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11688"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11675"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11688"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11689"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11688"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11688"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11675"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11688"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11689"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode141() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11690"), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11690"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11675"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11690"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11691"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11690"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11692"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11690"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11690"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11675"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11690"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11691"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11690"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11692"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode142() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=11693"), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11693"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11675"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11693"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11694"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11693"), NodeId.parse("ns=0;i=37"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11693"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11675"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11693"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11694"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode143() {
        UaMethodNode node = new UaMethodNode(this.context, NodeId.parse("ns=0;i=3875"), new QualifiedName(0, "ConditionRefresh"), new LocalizedText("en", "ConditionRefresh"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true, true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=3875"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2782"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3875"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3876"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3875"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=2787"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3875"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=2788"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3875"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2782"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3875"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3876"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
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
        buildNode27();
        buildNode28();
        buildNode29();
        buildNode30();
        buildNode31();
        buildNode32();
        buildNode33();
        buildNode34();
        buildNode35();
        buildNode36();
        buildNode37();
        buildNode38();
        buildNode39();
        buildNode40();
        buildNode41();
        buildNode42();
        buildNode43();
        buildNode44();
        buildNode45();
        buildNode46();
        buildNode47();
        buildNode48();
        buildNode49();
        buildNode50();
        buildNode51();
        buildNode52();
        buildNode53();
        buildNode54();
        buildNode55();
        buildNode56();
        buildNode57();
        buildNode58();
        buildNode59();
        buildNode60();
        buildNode61();
        buildNode62();
        buildNode63();
        buildNode64();
        buildNode65();
        buildNode66();
        buildNode67();
        buildNode68();
        buildNode69();
        buildNode70();
        buildNode71();
        buildNode72();
        buildNode73();
        buildNode74();
        buildNode75();
        buildNode76();
        buildNode77();
        buildNode78();
        buildNode79();
        buildNode80();
        buildNode81();
        buildNode82();
        buildNode83();
        buildNode84();
        buildNode85();
        buildNode86();
        buildNode87();
        buildNode88();
        buildNode89();
        buildNode90();
        buildNode91();
        buildNode92();
        buildNode93();
        buildNode94();
        buildNode95();
        buildNode96();
        buildNode97();
        buildNode98();
        buildNode99();
        buildNode100();
        buildNode101();
        buildNode102();
        buildNode103();
        buildNode104();
        buildNode105();
        buildNode106();
        buildNode107();
        buildNode108();
        buildNode109();
        buildNode110();
        buildNode111();
        buildNode112();
        buildNode113();
        buildNode114();
        buildNode115();
        buildNode116();
        buildNode117();
        buildNode118();
        buildNode119();
        buildNode120();
        buildNode121();
        buildNode122();
        buildNode123();
        buildNode124();
        buildNode125();
        buildNode126();
        buildNode127();
        buildNode128();
        buildNode129();
        buildNode130();
        buildNode131();
        buildNode132();
        buildNode133();
        buildNode134();
        buildNode135();
        buildNode136();
        buildNode137();
        buildNode138();
        buildNode139();
        buildNode140();
        buildNode141();
        buildNode142();
        buildNode143();
    }

}
