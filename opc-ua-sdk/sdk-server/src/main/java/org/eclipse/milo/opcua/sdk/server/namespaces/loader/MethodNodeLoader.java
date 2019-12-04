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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

class MethodNodeLoader {
    private final UaNodeContext context;

    private final NodeManager<UaNode> nodeManager;

    MethodNodeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager) {
        this.context = context;
        this.nodeManager = nodeManager;
    }

    private void loadNode0() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12543), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12543), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12544), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12543), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12545), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12543), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12543), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12522), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode1() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12546), new QualifiedName(0, "CloseAndUpdate"), new LocalizedText("en", "CloseAndUpdate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12546), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12705), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12546), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12547), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12546), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12546), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12522), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode2() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12548), new QualifiedName(0, "AddCertificate"), new LocalizedText("en", "AddCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12548), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12549), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12548), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12548), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12522), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode3() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12550), new QualifiedName(0, "RemoveCertificate"), new LocalizedText("en", "RemoveCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12550), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12551), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12550), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12550), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12522), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode4() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12616), new QualifiedName(0, "UpdateCertificate"), new LocalizedText("en", "UpdateCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12616), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12617), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12616), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12618), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12616), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12616), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12581), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode5() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12647), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12647), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12648), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12647), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12649), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12647), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12642), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode6() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12650), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12650), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12651), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12650), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12642), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode7() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12652), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12652), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12653), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12652), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12654), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12652), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12642), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode8() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12655), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12655), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12656), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12655), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12642), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode9() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12657), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12657), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12658), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12657), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12659), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12657), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12642), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode10() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12660), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12660), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12661), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12660), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12642), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode11() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12663), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12663), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12664), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12663), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12665), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12663), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12642), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode12() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12666), new QualifiedName(0, "CloseAndUpdate"), new LocalizedText("en", "CloseAndUpdate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12666), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14160), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12666), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12667), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12666), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12642), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode13() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12668), new QualifiedName(0, "AddCertificate"), new LocalizedText("en", "AddCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12668), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12669), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12668), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12642), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode14() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12670), new QualifiedName(0, "RemoveCertificate"), new LocalizedText("en", "RemoveCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12670), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12671), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12670), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12642), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode15() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12731), new QualifiedName(0, "CreateSigningRequest"), new LocalizedText("en", "CreateSigningRequest"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12731), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12732), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12731), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12733), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12731), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12731), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12581), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode16() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12734), new QualifiedName(0, "ApplyChanges"), new LocalizedText("en", "ApplyChanges"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12734), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12734), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12581), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode17() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12737), new QualifiedName(0, "CreateSigningRequest"), new LocalizedText("en", "CreateSigningRequest"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12737), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12738), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12737), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12739), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12737), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12637), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode18() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12740), new QualifiedName(0, "ApplyChanges"), new LocalizedText("en", "ApplyChanges"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12740), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12637), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode19() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12746), new QualifiedName(0, "SetSubscriptionDurable"), new LocalizedText("en", "SetSubscriptionDurable"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12746), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12747), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12746), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12748), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12746), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12746), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2004), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode20() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12749), new QualifiedName(0, "SetSubscriptionDurable"), new LocalizedText("en", "SetSubscriptionDurable"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12749), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12750), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12749), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12751), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12749), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2253), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode21() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12775), new QualifiedName(0, "GetRejectedList"), new LocalizedText("en", "GetRejectedList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12775), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12776), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12775), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12775), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12581), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode22() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12777), new QualifiedName(0, "GetRejectedList"), new LocalizedText("en", "GetRejectedList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12777), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12778), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12777), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12637), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode23() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12871), new QualifiedName(0, "ResendData"), new LocalizedText("en", "ResendData"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12871), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12872), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12871), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12871), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2004), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode24() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12873), new QualifiedName(0, "ResendData"), new LocalizedText("en", "ResendData"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12873), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12874), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12873), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2253), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode25() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12883), new QualifiedName(0, "RequestServerStateChange"), new LocalizedText("en", "RequestServerStateChange"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12883), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12884), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12883), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12883), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2004), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode26() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12886), new QualifiedName(0, "RequestServerStateChange"), new LocalizedText("en", "RequestServerStateChange"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12886), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12887), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12886), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2253), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode27() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 12912), new QualifiedName(0, "ConditionRefresh2"), new LocalizedText("en", "ConditionRefresh2"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 12912), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12913), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12912), new NodeId(0, 3065), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2787), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12912), new NodeId(0, 3065), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2788), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12912), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2782), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode28() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 9027), new QualifiedName(0, "Enable"), new LocalizedText("en", "Enable"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 9027), new NodeId(0, 3065), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2803), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9027), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9027), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2782), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode29() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 9028), new QualifiedName(0, "Disable"), new LocalizedText("en", "Disable"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 9028), new NodeId(0, 3065), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2803), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9028), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9028), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2782), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode30() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 9029), new QualifiedName(0, "AddComment"), new LocalizedText("en", "AddComment"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 9029), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9030), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9029), new NodeId(0, 3065), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2829), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9029), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9029), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2782), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode31() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 9069), new QualifiedName(0, "Respond"), new LocalizedText("en", "Respond"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 9069), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9070), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9069), new NodeId(0, 3065), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8927), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9069), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9069), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2830), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode32() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 9111), new QualifiedName(0, "Acknowledge"), new LocalizedText("en", "Acknowledge"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 9111), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9112), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9111), new NodeId(0, 3065), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8944), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9111), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9111), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2881), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode33() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 9113), new QualifiedName(0, "Confirm"), new LocalizedText("en", "Confirm"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 9113), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9114), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9113), new NodeId(0, 3065), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8961), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9113), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9113), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2881), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode34() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 9211), new QualifiedName(0, "Unshelve"), new LocalizedText("en", "Unshelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 9211), new NodeId(0, 3065), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11093), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9211), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9211), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9178), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode35() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 9212), new QualifiedName(0, "OneShotShelve"), new LocalizedText("en", "OneShotShelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 9212), new NodeId(0, 3065), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11093), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9212), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9212), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9178), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode36() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 9213), new QualifiedName(0, "TimedShelve"), new LocalizedText("en", "TimedShelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 9213), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9214), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9213), new NodeId(0, 3065), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11093), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9213), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9213), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9178), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode37() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13355), new QualifiedName(0, "CreateDirectory"), new LocalizedText("en", "CreateDirectory"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13355), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13356), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13355), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13357), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13355), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13355), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13354), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode38() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13358), new QualifiedName(0, "CreateFile"), new LocalizedText("en", "CreateFile"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13358), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13359), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13358), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13360), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13358), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13358), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13354), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode39() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13361), new QualifiedName(0, "Delete"), new LocalizedText("en", "Delete"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13361), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13362), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13361), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13361), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13354), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode40() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13363), new QualifiedName(0, "MoveOrCopy"), new LocalizedText("en", "MoveOrCopy"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13363), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13364), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13363), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13365), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13363), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13363), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13354), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode41() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13372), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13372), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13373), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13372), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13374), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13372), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13372), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13366), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode42() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13375), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13375), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13376), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13375), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13375), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13366), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode43() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13377), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13377), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13378), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13377), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13379), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13377), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13377), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13366), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode44() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13380), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13380), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13381), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13380), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13380), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13366), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode45() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13382), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13382), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13383), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13382), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13384), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13382), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13382), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13366), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode46() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13385), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13385), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13386), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13385), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13385), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13366), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode47() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13387), new QualifiedName(0, "CreateDirectory"), new LocalizedText("en", "CreateDirectory"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13387), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13388), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13387), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13389), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13387), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13387), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13353), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode48() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13390), new QualifiedName(0, "CreateFile"), new LocalizedText("en", "CreateFile"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13390), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13391), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13390), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13392), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13390), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13390), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13353), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode49() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13393), new QualifiedName(0, "Delete"), new LocalizedText("en", "Delete"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13393), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13394), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13393), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13393), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13353), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode50() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13395), new QualifiedName(0, "MoveOrCopy"), new LocalizedText("en", "MoveOrCopy"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13395), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13396), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13395), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13397), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13395), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13395), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13353), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode51() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13605), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13605), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13606), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13605), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13607), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13605), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13605), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13599), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode52() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13608), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13608), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13609), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13608), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13608), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13599), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode53() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13610), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13610), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13611), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13610), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13612), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13610), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13610), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13599), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode54() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13613), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13613), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13614), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13613), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13613), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13599), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode55() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13615), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13615), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13616), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13615), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13617), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13615), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13615), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13599), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode56() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13618), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13618), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13619), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13618), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13618), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13599), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode57() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13621), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13621), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13622), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13621), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13623), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13621), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13621), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13599), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode58() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13737), new QualifiedName(0, "UpdateCertificate"), new LocalizedText("en", "UpdateCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13737), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13738), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13737), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13739), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13737), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12637), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode59() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13821), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13821), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13822), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13821), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13823), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13821), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13821), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13815), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode60() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13824), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13824), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13825), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13824), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13824), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13815), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode61() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13826), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13826), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13827), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13826), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13828), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13826), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13826), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13815), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode62() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13829), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13829), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13830), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13829), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13829), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13815), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode63() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13831), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13831), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13832), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13831), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13833), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13831), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13831), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13815), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode64() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13834), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13834), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13835), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13834), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13834), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13815), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode65() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13837), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13837), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13838), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13837), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13839), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13837), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13837), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13815), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode66() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13855), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13855), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13856), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13855), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13857), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13855), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13855), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13849), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode67() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13858), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13858), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13859), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13858), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13858), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13849), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode68() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13860), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13860), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13861), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13860), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13862), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13860), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13860), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13849), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode69() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13863), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13863), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13864), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13863), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13863), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13849), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode70() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13865), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13865), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13866), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13865), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13867), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13865), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13865), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13849), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode71() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13868), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13868), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13869), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13868), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13868), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13849), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode72() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13871), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13871), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13872), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13871), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13873), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13871), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13871), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13849), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode73() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13889), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13889), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13890), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13889), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13891), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13889), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13889), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13883), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode74() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13892), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13892), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13893), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13892), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13892), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13883), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode75() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13894), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13894), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13895), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13894), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13896), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13894), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13894), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13883), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode76() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13897), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13897), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13898), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13897), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13897), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13883), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode77() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13899), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13899), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13900), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13899), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13901), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13899), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13899), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13883), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode78() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13902), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13902), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13903), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13902), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13902), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13883), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode79() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13905), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13905), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13906), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13905), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13907), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13905), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13905), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13883), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode80() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13923), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13923), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13924), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13923), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13925), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13923), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13923), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13917), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode81() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13926), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13926), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13927), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13926), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13926), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13917), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode82() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13928), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13928), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13929), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13928), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13930), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13928), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13928), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13917), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode83() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13931), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13931), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13932), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13931), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13931), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13917), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode84() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13933), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13933), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13934), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13933), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13935), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13933), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13933), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13917), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode85() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13936), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13936), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13937), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13936), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13936), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13917), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode86() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13939), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13939), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13940), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13939), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13941), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13939), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13939), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13917), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode87() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13958), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13958), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13959), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13958), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13960), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13958), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13958), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13952), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode88() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13961), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13961), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13962), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13961), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13961), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13952), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode89() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13963), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13963), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13964), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13963), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13965), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13963), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13963), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13952), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode90() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13966), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13966), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13967), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13966), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13966), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13952), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode91() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13968), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13968), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13969), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13968), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13970), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13968), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13968), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13952), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode92() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13971), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13971), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13972), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13971), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13971), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13952), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode93() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 13974), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 13974), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13975), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13974), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13976), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13974), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13974), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13952), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode94() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14095), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14095), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14096), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14095), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14097), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14095), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14089), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode95() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14098), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14098), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14099), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14098), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14089), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode96() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14100), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14100), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14101), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14100), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14102), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14100), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14089), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode97() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14103), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14103), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14104), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14103), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14089), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode98() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14105), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14105), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14106), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14105), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14107), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14105), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14089), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode99() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14108), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14108), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14109), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14108), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14089), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode100() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14111), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14111), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14112), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14111), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14113), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14111), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14089), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode101() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14114), new QualifiedName(0, "CloseAndUpdate"), new LocalizedText("en", "CloseAndUpdate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14114), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14115), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14114), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14116), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14114), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14089), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode102() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14117), new QualifiedName(0, "AddCertificate"), new LocalizedText("en", "AddCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14117), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14118), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14117), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14089), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode103() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14119), new QualifiedName(0, "RemoveCertificate"), new LocalizedText("en", "RemoveCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14119), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14120), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14119), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14089), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode104() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14129), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14129), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14130), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14129), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14131), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14129), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14123), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode105() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14132), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14132), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14133), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14132), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14123), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode106() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14134), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14134), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14135), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14134), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14136), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14134), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14123), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode107() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14137), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14137), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14138), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14137), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14123), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode108() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14139), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14139), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14140), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14139), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14141), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14139), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14123), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode109() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14142), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14142), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14143), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14142), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14123), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode110() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14145), new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14145), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14146), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14145), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14147), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14145), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14123), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode111() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14148), new QualifiedName(0, "CloseAndUpdate"), new LocalizedText("en", "CloseAndUpdate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14148), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14149), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14148), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14150), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14148), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14123), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode112() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14151), new QualifiedName(0, "AddCertificate"), new LocalizedText("en", "AddCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14151), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14152), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14151), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14123), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode113() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 14153), new QualifiedName(0, "RemoveCertificate"), new LocalizedText("en", "RemoveCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 14153), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14154), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14153), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14123), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode114() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 2426), new QualifiedName(0, "Start"), new LocalizedText("en", "Start"), new LocalizedText("en", "Causes the Program to transition from the Ready state to the Running state."), UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 2426), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2410), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2426), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2426), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode115() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 2427), new QualifiedName(0, "Suspend"), new LocalizedText("en", "Suspend"), new LocalizedText("en", "Causes the Program to transition from the Running state to the Suspended state."), UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 2427), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2416), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2427), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2427), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode116() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 2428), new QualifiedName(0, "Resume"), new LocalizedText("en", "Resume"), new LocalizedText("en", "Causes the Program to transition from the Suspended state to the Running state."), UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 2428), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2418), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2428), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2428), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode117() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 2429), new QualifiedName(0, "Halt"), new LocalizedText("en", "Halt"), new LocalizedText("en", "Causes the Program to transition from the Ready, Running or Suspended state to the Halted state."), UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 2429), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2412), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2429), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2420), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2429), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2424), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2429), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2429), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode118() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 2430), new QualifiedName(0, "Reset"), new LocalizedText("en", "Reset"), new LocalizedText("en", "Causes the Program to transition from the Halted state to the Ready state."), UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 2430), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2408), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2430), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2430), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode119() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 2947), new QualifiedName(0, "Unshelve"), new LocalizedText("en", "Unshelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 2947), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2940), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2947), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2943), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2947), new NodeId(0, 3065), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11093), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2947), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2947), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2929), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode120() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 2948), new QualifiedName(0, "OneShotShelve"), new LocalizedText("en", "OneShotShelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 2948), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2936), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2948), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2942), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2948), new NodeId(0, 3065), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11093), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2948), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2948), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2929), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode121() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 2949), new QualifiedName(0, "TimedShelve"), new LocalizedText("en", "TimedShelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 2949), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2991), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2949), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2935), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2949), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2945), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2949), new NodeId(0, 3065), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11093), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2949), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2949), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2929), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode122() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11489), new QualifiedName(0, "GetMonitoredItems"), new LocalizedText("en", "GetMonitoredItems"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11489), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11490), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11489), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11491), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11489), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11489), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2004), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode123() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11492), new QualifiedName(0, "GetMonitoredItems"), new LocalizedText("en", "GetMonitoredItems"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11492), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11493), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11492), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11494), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11492), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2253), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode124() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11580), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11580), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11581), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11580), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11582), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11580), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11580), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11575), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode125() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11583), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11583), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11584), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11583), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11583), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11575), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode126() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11585), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11585), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11586), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11585), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11587), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11585), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11585), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11575), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode127() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11588), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11588), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11589), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11588), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11588), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11575), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode128() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11590), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11590), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11591), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11590), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11592), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11590), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11590), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11575), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode129() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11593), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11593), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11594), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11593), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11593), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11575), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode130() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11615), new QualifiedName(0, "ExportNamespace"), new LocalizedText("en", "ExportNamespace"), new LocalizedText("en", "Updates the file by exporting the server namespace."), UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11615), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11615), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11595), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode131() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11629), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11629), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11630), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11629), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11631), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11629), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11629), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11624), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode132() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11632), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11632), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11633), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11632), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11632), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11624), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode133() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11634), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11634), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11635), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11634), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11636), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11634), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11634), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11624), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode134() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11637), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11637), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11638), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11637), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11637), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11624), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode135() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11639), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11639), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11640), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11639), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11641), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11639), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11639), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11624), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode136() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11642), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11642), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11643), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11642), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11642), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11624), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode137() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11680), new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11680), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11681), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11680), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11682), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11680), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11680), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11675), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode138() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11683), new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11683), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11684), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11683), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11683), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11675), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode139() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11685), new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11685), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11686), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11685), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11687), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11685), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11685), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11675), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode140() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11688), new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11688), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11689), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11688), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11688), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11675), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode141() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11690), new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11690), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11691), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11690), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11692), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11690), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11690), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11675), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode142() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 11693), new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 11693), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11694), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11693), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11693), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11675), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode143() {
        UaMethodNode node = new UaMethodNode(this.context, new NodeId(0, 3875), new QualifiedName(0, "ConditionRefresh"), new LocalizedText("en", "ConditionRefresh"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(new NodeId(0, 3875), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3876), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3875), new NodeId(0, 3065), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2787), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3875), new NodeId(0, 3065), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2788), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3875), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2782), UInteger.valueOf(0)), false));
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
        loadNode27();
        loadNode28();
        loadNode29();
        loadNode30();
        loadNode31();
        loadNode32();
        loadNode33();
        loadNode34();
        loadNode35();
        loadNode36();
        loadNode37();
        loadNode38();
        loadNode39();
        loadNode40();
        loadNode41();
        loadNode42();
        loadNode43();
        loadNode44();
        loadNode45();
        loadNode46();
        loadNode47();
        loadNode48();
        loadNode49();
        loadNode50();
        loadNode51();
        loadNode52();
        loadNode53();
        loadNode54();
        loadNode55();
        loadNode56();
        loadNode57();
        loadNode58();
        loadNode59();
        loadNode60();
        loadNode61();
        loadNode62();
        loadNode63();
        loadNode64();
        loadNode65();
        loadNode66();
        loadNode67();
        loadNode68();
        loadNode69();
        loadNode70();
        loadNode71();
        loadNode72();
        loadNode73();
        loadNode74();
        loadNode75();
        loadNode76();
        loadNode77();
        loadNode78();
        loadNode79();
        loadNode80();
        loadNode81();
        loadNode82();
        loadNode83();
        loadNode84();
        loadNode85();
        loadNode86();
        loadNode87();
        loadNode88();
        loadNode89();
        loadNode90();
        loadNode91();
        loadNode92();
        loadNode93();
        loadNode94();
        loadNode95();
        loadNode96();
        loadNode97();
        loadNode98();
        loadNode99();
        loadNode100();
        loadNode101();
        loadNode102();
        loadNode103();
        loadNode104();
        loadNode105();
        loadNode106();
        loadNode107();
        loadNode108();
        loadNode109();
        loadNode110();
        loadNode111();
        loadNode112();
        loadNode113();
        loadNode114();
        loadNode115();
        loadNode116();
        loadNode117();
        loadNode118();
        loadNode119();
        loadNode120();
        loadNode121();
        loadNode122();
        loadNode123();
        loadNode124();
        loadNode125();
        loadNode126();
        loadNode127();
        loadNode128();
        loadNode129();
        loadNode130();
        loadNode131();
        loadNode132();
        loadNode133();
        loadNode134();
        loadNode135();
        loadNode136();
        loadNode137();
        loadNode138();
        loadNode139();
        loadNode140();
        loadNode141();
        loadNode142();
        loadNode143();
    }
}
