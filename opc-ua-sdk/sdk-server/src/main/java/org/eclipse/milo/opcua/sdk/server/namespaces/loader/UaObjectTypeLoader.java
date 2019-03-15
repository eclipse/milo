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
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectTypeNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class UaObjectTypeLoader {

    private final UaNodeContext context;
    private final NodeManager<UaNode> nodeManager;

    public UaObjectTypeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager) {
        this.context = context;
        this.nodeManager = nodeManager;
    }

    private void buildNode0() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=58"), new QualifiedName(0, "BaseObjectType"), new LocalizedText("en", "BaseObjectType"), new LocalizedText("en", "The base type for all object nodes."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=3062"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=3063"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=61"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=75"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=76"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=77"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=35"), ExpandedNodeId.parse("svr=0;i=88"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2004"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2013"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2020"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2026"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2029"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2033"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2034"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11564"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11575"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11616"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11645"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2041"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2340"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2299"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2307"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2310"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11163"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=3850"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2318"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2330"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=12555"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=12556"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=12581"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=58"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11187"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode1() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=61"), new QualifiedName(0, "FolderType"), new LocalizedText("en", "FolderType"), new LocalizedText("en", "The type for objects that organize other nodes."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=84"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=85"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=86"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=87"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=88"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=89"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=90"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=91"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=3093"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=3094"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2019"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2754"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=13353"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=3048"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2996"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2997"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11201"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11876"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11172"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=61"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=13813"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode2() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=75"), new QualifiedName(0, "DataTypeSystemType"), new LocalizedText("en", "DataTypeSystemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=75"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=75"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=92"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=75"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=93"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode3() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=76"), new QualifiedName(0, "DataTypeEncodingType"), new LocalizedText("en", "DataTypeEncodingType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12676"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=297"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=7616"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12757"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12758"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=8913"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=309"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12195"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=305"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=313"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=433"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12892"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12893"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=345"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=317"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=320"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=323"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=326"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12505"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=939"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=377"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=380"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=383"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=386"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=538"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=541"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=332"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=336"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=342"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=584"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=587"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=590"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=593"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=596"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=599"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=602"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=660"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=720"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=726"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=949"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=921"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=339"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=854"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11949"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11950"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=857"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=860"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=863"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=866"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=869"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=872"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=300"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=875"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=878"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=898"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=885"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=888"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12173"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12174"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12081"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12082"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=895"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=892"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12680"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=298"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=8251"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12765"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12766"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=8917"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=310"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12207"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=306"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=314"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=434"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12900"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12901"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=346"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=318"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=321"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=324"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=327"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12509"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=940"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=378"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=381"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=384"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=387"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=539"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=542"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=333"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=337"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=343"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=585"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=588"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=591"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=594"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=597"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=600"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=603"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=661"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=721"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=727"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=950"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=922"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=340"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=855"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11957"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11958"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=858"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=861"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=864"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=867"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=870"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=873"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=301"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=876"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=879"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=899"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=886"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=889"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12181"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12182"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12090"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=896"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=76"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=893"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode4() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=77"), new QualifiedName(0, "ModellingRuleType"), new LocalizedText("en", "ModellingRuleType"), new LocalizedText("en", "The type for an object that describes how an instance declaration is used when a type is instantiated."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=77"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=111"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=77"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=77"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=111"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=77"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=78"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=77"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=80"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=77"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=83"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=77"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=79"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=77"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11508"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=77"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11510"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode5() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=12522"), new QualifiedName(0, "TrustListType"), new LocalizedText("en", "TrustListType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12542"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12543"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12546"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12548"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12550"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11575"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12542"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12543"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12546"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12548"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12550"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=13815"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=13849"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=13883"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=13917"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=13952"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12642"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=14089"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12522"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=14123"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode6() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=12555"), new QualifiedName(0, "CertificateGroupType"), new LocalizedText("en", "CertificateGroupType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12555"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12555"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13631"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12555"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12555"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13599"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12555"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13631"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12555"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=13814"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12555"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=13848"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12555"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=13882"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12555"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=13916"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12555"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=13951"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12555"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=14156"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12555"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=14088"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12555"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=14122"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode7() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=12556"), new QualifiedName(0, "CertificateType"), new LocalizedText("en", "CertificateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12556"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12556"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=12557"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12556"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=12558"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode8() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=12557"), new QualifiedName(0, "ApplicationCertificateType"), new LocalizedText("en", "ApplicationCertificateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12557"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=12556"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12557"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=12559"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12557"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=12560"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode9() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=12558"), new QualifiedName(0, "HttpsCertificateType"), new LocalizedText("en", "HttpsCertificateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12558"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=12556"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode10() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=12559"), new QualifiedName(0, "RsaMinApplicationCertificateType"), new LocalizedText("en", "RsaMinApplicationCertificateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12559"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=12557"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode11() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=12560"), new QualifiedName(0, "RsaSha256ApplicationCertificateType"), new LocalizedText("en", "RsaSha256ApplicationCertificateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12560"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=12557"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode12() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=12561"), new QualifiedName(0, "TrustListUpdatedAuditEventType"), new LocalizedText("en", "TrustListUpdatedAuditEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12561"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2127"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode13() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=12581"), new QualifiedName(0, "ServerConfigurationType"), new LocalizedText("en", "ServerConfigurationType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13950"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12708"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12583"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12584"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12585"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12616"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12734"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12731"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12775"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13950"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12708"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12583"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12584"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12585"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12616"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12734"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12731"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12775"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12581"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12637"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode14() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=12620"), new QualifiedName(0, "CertificateUpdatedAuditEventType"), new LocalizedText("en", "CertificateUpdatedAuditEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=12620"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13735"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12620"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13736"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12620"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2127"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12620"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13735"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=12620"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13736"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode15() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=8927"), new QualifiedName(0, "AuditConditionRespondEventType"), new LocalizedText("en", "AuditConditionRespondEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=8927"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=9069"), NodeClass.Method, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=8927"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11852"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=8927"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2790"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=8927"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11852"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode16() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=8944"), new QualifiedName(0, "AuditConditionAcknowledgeEventType"), new LocalizedText("en", "AuditConditionAcknowledgeEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=8944"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=9111"), NodeClass.Method, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=8944"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=8945"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=8944"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11853"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=8944"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2790"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=8944"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=8945"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=8944"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11853"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode17() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=8961"), new QualifiedName(0, "AuditConditionConfirmEventType"), new LocalizedText("en", "AuditConditionConfirmEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=8961"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=9113"), NodeClass.Method, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=8961"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=8962"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=8961"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11854"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=8961"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2790"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=8961"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=8962"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=8961"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11854"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode18() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=13225"), new QualifiedName(0, "CertificateExpirationAlarmType"), new LocalizedText("en", "CertificateExpirationAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13225"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13325"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13225"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13326"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13225"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13327"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13225"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11753"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13225"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13325"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13225"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13326"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13225"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13327"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode19() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=13353"), new QualifiedName(0, "FileDirectoryType"), new LocalizedText("en", "FileDirectoryType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13353"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13354"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13353"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13366"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13353"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13387"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13353"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13390"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13353"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13393"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13353"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13395"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13353"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=61"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13353"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=13354"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13353"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13354"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13353"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13366"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13353"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13387"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13353"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13390"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13353"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13393"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13353"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13395"), NodeClass.Method, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode20() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=9318"), new QualifiedName(0, "ExclusiveLimitStateMachineType"), new LocalizedText("en", "ExclusiveLimitStateMachineType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9329"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9331"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9333"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9335"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9337"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9338"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9339"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9340"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2771"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9329"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9331"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9333"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9335"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9337"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9338"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9339"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9340"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9318"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=9455"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode21() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=9341"), new QualifiedName(0, "ExclusiveLimitAlarmType"), new LocalizedText("en", "ExclusiveLimitAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=9341"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9398"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9341"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9455"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9341"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2955"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9341"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9398"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9341"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9455"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9341"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=9482"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9341"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=9764"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9341"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=9623"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode22() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=9482"), new QualifiedName(0, "ExclusiveLevelAlarmType"), new LocalizedText("en", "ExclusiveLevelAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=9482"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=9341"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode23() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=9623"), new QualifiedName(0, "ExclusiveRateOfChangeAlarmType"), new LocalizedText("en", "ExclusiveRateOfChangeAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=9623"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=9341"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode24() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=13813"), new QualifiedName(0, "CertificateGroupFolderType"), new LocalizedText("en", "CertificateGroupFolderType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=13813"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13814"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13813"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13848"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13813"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13882"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13813"), NodeId.parse("ns=0;i=35"), ExpandedNodeId.parse("svr=0;i=13916"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13813"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=61"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13813"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13814"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13813"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13848"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13813"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=13882"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13813"), NodeId.parse("ns=0;i=35"), ExpandedNodeId.parse("svr=0;i=13916"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13813"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=13950"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=13813"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=14053"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode25() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=9764"), new QualifiedName(0, "ExclusiveDeviationAlarmType"), new LocalizedText("en", "ExclusiveDeviationAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=9764"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9905"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9764"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=9341"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9764"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9905"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode26() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=9906"), new QualifiedName(0, "NonExclusiveLimitAlarmType"), new LocalizedText("en", "NonExclusiveLimitAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=9906"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9963"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9906"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=10020"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9906"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=10029"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9906"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=10038"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9906"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=10047"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9906"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2955"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9906"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9963"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9906"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=10020"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9906"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=10029"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9906"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=10038"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9906"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=10047"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9906"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=10060"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9906"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=10368"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=9906"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=10214"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode27() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=10060"), new QualifiedName(0, "NonExclusiveLevelAlarmType"), new LocalizedText("en", "NonExclusiveLevelAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=10060"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=9906"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode28() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2004"), new QualifiedName(0, "ServerType"), new LocalizedText("en", "ServerType"), new LocalizedText("en", "Specifies the current status and capabilities of the server."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2005"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2006"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2007"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2008"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2742"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12882"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2009"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2010"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2011"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2012"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11527"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11489"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12871"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12746"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12883"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2005"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2006"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2007"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2008"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2742"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12882"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2009"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2010"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2011"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2012"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11527"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11489"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12871"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12746"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12883"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2004"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2253"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode29() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2013"), new QualifiedName(0, "ServerCapabilitiesType"), new LocalizedText("en", "ServerCapabilitiesType"), new LocalizedText("en", "Describes the capabilities supported by the server."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2009"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2014"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2016"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2017"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2732"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2733"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2734"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3049"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11549"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11550"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12910"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11551"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2019"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2754"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11562"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2014"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2016"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2017"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2732"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2733"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2734"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3049"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11549"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11550"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12910"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11551"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2019"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2754"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11562"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2013"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2268"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode30() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2020"), new QualifiedName(0, "ServerDiagnosticsType"), new LocalizedText("en", "ServerDiagnosticsType"), new LocalizedText("en", "The diagnostics information for a server."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2020"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2010"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2020"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2021"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2020"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2022"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2020"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2023"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2020"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2744"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2020"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2025"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2020"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2020"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2021"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2020"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2022"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2020"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2023"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2020"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2744"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2020"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2025"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2020"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2274"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode31() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=10214"), new QualifiedName(0, "NonExclusiveRateOfChangeAlarmType"), new LocalizedText("en", "NonExclusiveRateOfChangeAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=10214"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=9906"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode32() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2026"), new QualifiedName(0, "SessionsDiagnosticsSummaryType"), new LocalizedText("en", "SessionsDiagnosticsSummaryType"), new LocalizedText("en", "Provides a summary of session level diagnostics."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2026"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=3111"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2026"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2744"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2026"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2027"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2026"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2028"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2026"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12097"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2026"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2026"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2027"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2026"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2028"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2026"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12097"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2026"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=3706"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode33() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2029"), new QualifiedName(0, "SessionDiagnosticsObjectType"), new LocalizedText("en", "SessionDiagnosticsObjectType"), new LocalizedText("en", "A container for session level diagnostics information."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2029"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=12097"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2029"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2030"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2029"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2031"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2029"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2032"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2029"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2029"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2030"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2029"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2031"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2029"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2032"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode34() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2033"), new QualifiedName(0, "VendorServerInfoType"), new LocalizedText("en", "VendorServerInfoType"), new LocalizedText("en", "A base type for vendor specific server information."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2033"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2011"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2033"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2033"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2295"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode35() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2034"), new QualifiedName(0, "ServerRedundancyType"), new LocalizedText("en", "ServerRedundancyType"), new LocalizedText("en", "A base type for an object that describe how a server supports redundancy."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2034"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2012"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2034"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2035"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2034"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2034"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2035"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2034"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2036"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2034"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2039"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2034"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2296"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode36() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2036"), new QualifiedName(0, "TransparentRedundancyType"), new LocalizedText("en", "TransparentRedundancyType"), new LocalizedText("en", "Identifies the capabilties of server that supports transparent redundancy."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2036"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2037"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2036"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2038"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2036"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2034"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2036"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2037"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2036"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2038"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode37() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2039"), new QualifiedName(0, "NonTransparentRedundancyType"), new LocalizedText("en", "NonTransparentRedundancyType"), new LocalizedText("en", "Identifies the capabilties of server that supports non-transparent redundancy."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2039"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2040"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2039"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2034"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2039"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2040"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2039"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11945"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode38() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2041"), new QualifiedName(0, "BaseEventType"), new LocalizedText("en", "BaseEventType"), new LocalizedText("en", "The base type for all events."), UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2042"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2043"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2044"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2045"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2046"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2047"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3190"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2050"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2051"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2042"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2043"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2044"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2045"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2046"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2047"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3190"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2050"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2051"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2052"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2130"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2132"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=3035"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11436"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=35"), ExpandedNodeId.parse("svr=0;i=3048"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2311"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2041"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2782"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode39() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2052"), new QualifiedName(0, "AuditEventType"), new LocalizedText("en", "AuditEventType"), new LocalizedText("en", "A base type for events used to track client initiated changes to the server state."), UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2052"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2053"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2052"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2054"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2052"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2055"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2052"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2056"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2052"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2057"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2052"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2041"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2052"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2053"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2052"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2054"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2052"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2055"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2052"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2056"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2052"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2057"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2052"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2058"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2052"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2090"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2052"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2099"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2052"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2127"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode40() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2058"), new QualifiedName(0, "AuditSecurityEventType"), new LocalizedText("en", "AuditSecurityEventType"), new LocalizedText("en", "A base type for events used to track security related changes."), UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2058"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2052"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2058"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2059"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2058"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2069"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2058"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2080"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode41() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2059"), new QualifiedName(0, "AuditChannelEventType"), new LocalizedText("en", "AuditChannelEventType"), new LocalizedText("en", "A base type for events used to track related changes to a secure channel."), UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2059"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2745"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2059"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2058"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2059"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2745"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2059"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2060"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode42() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2060"), new QualifiedName(0, "AuditOpenSecureChannelEventType"), new LocalizedText("en", "AuditOpenSecureChannelEventType"), new LocalizedText("en", "An event that is raised when a secure channel is opened."), UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2060"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2061"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2060"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2746"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2060"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2062"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2060"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2063"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2060"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2065"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2060"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2066"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2060"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2059"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2060"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2061"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2060"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2746"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2060"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2062"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2060"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2063"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2060"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2065"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2060"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2066"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode43() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2069"), new QualifiedName(0, "AuditSessionEventType"), new LocalizedText("en", "AuditSessionEventType"), new LocalizedText("en", "A base type for events used to track related changes to a session."), UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2069"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2070"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2069"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2058"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2069"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2070"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2069"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2071"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2069"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2075"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2069"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2078"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode44() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2071"), new QualifiedName(0, "AuditCreateSessionEventType"), new LocalizedText("en", "AuditCreateSessionEventType"), new LocalizedText("en", "An event that is raised when a session is created."), UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2071"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2072"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2071"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2073"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2071"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2747"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2071"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2074"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2071"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2069"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2071"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2072"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2071"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2073"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2071"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2747"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2071"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2074"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2071"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2748"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode45() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2075"), new QualifiedName(0, "AuditActivateSessionEventType"), new LocalizedText("en", "AuditActivateSessionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2075"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2076"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2075"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2077"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2075"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11485"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2075"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2069"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2075"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2076"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2075"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2077"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2075"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11485"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode46() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2078"), new QualifiedName(0, "AuditCancelEventType"), new LocalizedText("en", "AuditCancelEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2078"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2079"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2078"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2069"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2078"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2079"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode47() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2080"), new QualifiedName(0, "AuditCertificateEventType"), new LocalizedText("en", "AuditCertificateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2080"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2081"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2080"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2058"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2080"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2081"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2080"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2082"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2080"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2085"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2080"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2086"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2080"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2087"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2080"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2088"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2080"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2089"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode48() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2082"), new QualifiedName(0, "AuditCertificateDataMismatchEventType"), new LocalizedText("en", "AuditCertificateDataMismatchEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2082"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2083"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2082"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2084"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2082"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2080"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2082"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2083"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2082"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2084"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode49() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2085"), new QualifiedName(0, "AuditCertificateExpiredEventType"), new LocalizedText("en", "AuditCertificateExpiredEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2085"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2080"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode50() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2086"), new QualifiedName(0, "AuditCertificateInvalidEventType"), new LocalizedText("en", "AuditCertificateInvalidEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2086"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2080"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode51() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2087"), new QualifiedName(0, "AuditCertificateUntrustedEventType"), new LocalizedText("en", "AuditCertificateUntrustedEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2087"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2080"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode52() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2088"), new QualifiedName(0, "AuditCertificateRevokedEventType"), new LocalizedText("en", "AuditCertificateRevokedEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2088"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2080"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode53() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2089"), new QualifiedName(0, "AuditCertificateMismatchEventType"), new LocalizedText("en", "AuditCertificateMismatchEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2089"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2080"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode54() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2090"), new QualifiedName(0, "AuditNodeManagementEventType"), new LocalizedText("en", "AuditNodeManagementEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2090"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2052"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2090"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2091"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2090"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2093"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2090"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2095"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2090"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2097"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode55() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2091"), new QualifiedName(0, "AuditAddNodesEventType"), new LocalizedText("en", "AuditAddNodesEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2091"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2092"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2091"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2090"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2091"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2092"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode56() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2093"), new QualifiedName(0, "AuditDeleteNodesEventType"), new LocalizedText("en", "AuditDeleteNodesEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2093"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2094"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2093"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2090"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2093"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2094"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode57() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2095"), new QualifiedName(0, "AuditAddReferencesEventType"), new LocalizedText("en", "AuditAddReferencesEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2095"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2096"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2095"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2090"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2095"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2096"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode58() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2097"), new QualifiedName(0, "AuditDeleteReferencesEventType"), new LocalizedText("en", "AuditDeleteReferencesEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2097"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2098"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2097"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2090"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2097"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2098"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode59() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2099"), new QualifiedName(0, "AuditUpdateEventType"), new LocalizedText("en", "AuditUpdateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2099"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2052"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2099"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2100"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2099"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2104"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode60() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2100"), new QualifiedName(0, "AuditWriteUpdateEventType"), new LocalizedText("en", "AuditWriteUpdateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2100"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2750"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2100"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2101"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2100"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2102"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2100"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2103"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2100"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2099"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2100"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2750"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2100"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2101"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2100"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2102"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2100"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2103"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode61() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2104"), new QualifiedName(0, "AuditHistoryUpdateEventType"), new LocalizedText("en", "AuditHistoryUpdateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2104"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2751"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2104"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2099"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2104"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2751"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2104"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2999"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2104"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=3006"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2104"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=3012"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode62() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2127"), new QualifiedName(0, "AuditUpdateMethodEventType"), new LocalizedText("en", "AuditUpdateMethodEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2127"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2128"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2127"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2129"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2127"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2052"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2127"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2128"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2127"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2129"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2127"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2315"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2127"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2790"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2127"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=12561"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2127"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=12620"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode63() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2130"), new QualifiedName(0, "SystemEventType"), new LocalizedText("en", "SystemEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2130"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2041"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2130"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2131"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2130"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11446"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2130"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2787"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2130"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2788"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2130"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2789"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode64() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2131"), new QualifiedName(0, "DeviceFailureEventType"), new LocalizedText("en", "DeviceFailureEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2131"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2130"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode65() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2132"), new QualifiedName(0, "BaseModelChangeEventType"), new LocalizedText("en", "BaseModelChangeEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2132"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2041"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2132"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2133"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2132"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2738"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode66() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2133"), new QualifiedName(0, "GeneralModelChangeEventType"), new LocalizedText("en", "GeneralModelChangeEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2133"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2134"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2133"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2132"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2133"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2134"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode67() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=10368"), new QualifiedName(0, "NonExclusiveDeviationAlarmType"), new LocalizedText("en", "NonExclusiveDeviationAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=10368"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=10522"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=10368"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=9906"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=10368"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=10522"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode68() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2299"), new QualifiedName(0, "StateMachineType"), new LocalizedText("en", "StateMachineType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2299"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2769"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2299"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2770"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2299"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2299"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2769"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2299"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2770"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2299"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2771"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode69() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2307"), new QualifiedName(0, "StateType"), new LocalizedText("en", "StateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2307"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2308"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2307"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2307"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2308"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2307"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2309"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2307"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2930"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2307"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2932"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2307"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2933"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2307"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=9329"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2307"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=9331"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2307"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=9333"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2307"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=9335"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2307"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2400"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2307"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2402"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2307"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2404"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2307"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2406"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode70() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2309"), new QualifiedName(0, "InitialStateType"), new LocalizedText("en", "InitialStateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2309"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2307"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode71() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2310"), new QualifiedName(0, "TransitionType"), new LocalizedText("en", "TransitionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2312"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2312"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2935"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2936"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2940"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2942"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2943"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2945"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=9337"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=9338"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=9339"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=9340"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2408"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2410"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2412"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2414"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2416"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2418"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2420"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2422"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2310"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2424"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode72() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2311"), new QualifiedName(0, "TransitionEventType"), new LocalizedText("en", "TransitionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2311"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2774"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2311"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2775"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2311"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2776"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2311"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2041"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2311"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2774"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2311"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2775"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2311"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2776"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2311"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2378"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode73() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2315"), new QualifiedName(0, "AuditUpdateStateEventType"), new LocalizedText("en", "AuditUpdateStateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2315"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2777"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2315"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2778"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2315"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2127"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2315"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2777"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2315"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2778"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2315"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11856"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2315"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=3806"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode74() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2318"), new QualifiedName(0, "HistoricalDataConfigurationType"), new LocalizedText("en", "HistoricalDataConfigurationType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=3059"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11876"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2323"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2324"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2325"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2326"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2327"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2328"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11499"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11500"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=3059"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11876"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2323"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2324"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2325"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2326"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2327"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2328"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11499"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11500"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2318"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11202"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode75() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2330"), new QualifiedName(0, "HistoryServerCapabilitiesType"), new LocalizedText("en", "HistoryServerCapabilitiesType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11192"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2331"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2332"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11268"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11269"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2334"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2335"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2336"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2337"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2338"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11278"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11279"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11280"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11501"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11270"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11172"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2331"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2332"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11268"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11269"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2334"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2335"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2336"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2337"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2338"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11278"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11279"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11280"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11501"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11270"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2330"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11172"), NodeClass.Object, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode76() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=10523"), new QualifiedName(0, "DiscreteAlarmType"), new LocalizedText("en", "DiscreteAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=10523"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2915"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=10523"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=10637"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode77() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2340"), new QualifiedName(0, "AggregateFunctionType"), new LocalizedText("en", "AggregateFunctionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2341"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2342"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2343"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11285"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2344"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11304"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2346"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2347"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2348"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2349"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2350"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11286"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11287"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11305"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11306"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11288"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2351"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2352"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11307"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11308"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2355"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2357"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2358"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2359"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11505"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11506"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11507"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2360"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2361"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2362"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2363"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=2364"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11292"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11426"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11427"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11428"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2340"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11429"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode78() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2378"), new QualifiedName(0, "ProgramTransitionEventType"), new LocalizedText("en", "ProgramTransitionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2378"), NodeId.parse("ns=0;i=54"), ExpandedNodeId.parse("svr=0;i=2408"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2378"), NodeId.parse("ns=0;i=54"), ExpandedNodeId.parse("svr=0;i=2410"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2378"), NodeId.parse("ns=0;i=54"), ExpandedNodeId.parse("svr=0;i=2412"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2378"), NodeId.parse("ns=0;i=54"), ExpandedNodeId.parse("svr=0;i=2414"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2378"), NodeId.parse("ns=0;i=54"), ExpandedNodeId.parse("svr=0;i=2416"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2378"), NodeId.parse("ns=0;i=54"), ExpandedNodeId.parse("svr=0;i=2418"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2378"), NodeId.parse("ns=0;i=54"), ExpandedNodeId.parse("svr=0;i=2420"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2378"), NodeId.parse("ns=0;i=54"), ExpandedNodeId.parse("svr=0;i=2422"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2378"), NodeId.parse("ns=0;i=54"), ExpandedNodeId.parse("svr=0;i=2424"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2378"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2379"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2378"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2311"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2378"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2379"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode79() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2391"), new QualifiedName(0, "ProgramStateMachineType"), new LocalizedText("en", "ProgramStateMachineType"), new LocalizedText("en", "A state machine for a program."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=3830"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=3835"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2392"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2393"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2394"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2395"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2396"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2397"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2398"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2399"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=3850"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2400"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2402"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2404"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2406"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2408"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2410"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2412"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2414"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2416"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2418"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2420"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2422"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2424"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2426"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2427"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2428"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2429"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2430"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2771"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=3830"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=3835"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2392"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2393"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2394"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2395"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2396"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2397"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2398"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2399"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=3850"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2400"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2402"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2404"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2406"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2408"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2410"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2412"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2414"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2416"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2418"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2420"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2422"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2424"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2426"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2427"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2428"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2429"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2391"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2430"), NodeClass.Method, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode80() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=10637"), new QualifiedName(0, "OffNormalAlarmType"), new LocalizedText("en", "OffNormalAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=10637"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11158"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=10637"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=10523"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=10637"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11158"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=10637"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11753"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=10637"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=10751"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode81() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=10751"), new QualifiedName(0, "TripAlarmType"), new LocalizedText("en", "TripAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=10751"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=10637"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode82() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2738"), new QualifiedName(0, "SemanticChangeEventType"), new LocalizedText("en", "SemanticChangeEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2738"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2739"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2738"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2132"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2738"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2739"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode83() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2748"), new QualifiedName(0, "AuditUrlMismatchEventType"), new LocalizedText("en", "AuditUrlMismatchEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2748"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2749"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2748"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2071"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2748"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2749"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode84() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2771"), new QualifiedName(0, "FiniteStateMachineType"), new LocalizedText("en", "FiniteStateMachineType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2771"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2772"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2771"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2773"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2771"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2299"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2771"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2772"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2771"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2773"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2771"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2929"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2771"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=9318"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2771"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2391"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode85() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2782"), new QualifiedName(0, "ConditionType"), new LocalizedText("en", "ConditionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11112"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11113"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9009"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9010"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3874"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9011"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9020"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9022"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9024"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9026"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9028"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9027"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9029"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=3875"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12912"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2041"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11112"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11113"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9009"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9010"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3874"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9011"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9020"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9022"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9024"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9026"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9028"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9027"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9029"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=3875"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=12912"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2830"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2782"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2881"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode86() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2787"), new QualifiedName(0, "RefreshStartEventType"), new LocalizedText("en", "RefreshStartEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2787"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=3875"), NodeClass.Method, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2787"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=12912"), NodeClass.Method, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2787"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2130"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode87() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2788"), new QualifiedName(0, "RefreshEndEventType"), new LocalizedText("en", "RefreshEndEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2788"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=3875"), NodeClass.Method, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2788"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=12912"), NodeClass.Method, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2788"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2130"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode88() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2789"), new QualifiedName(0, "RefreshRequiredEventType"), new LocalizedText("en", "RefreshRequiredEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2789"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2130"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode89() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2790"), new QualifiedName(0, "AuditConditionEventType"), new LocalizedText("en", "AuditConditionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2790"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2127"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2790"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2803"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2790"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2829"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2790"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=8927"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2790"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=8944"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2790"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=8961"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2790"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11093"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode90() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2803"), new QualifiedName(0, "AuditConditionEnableEventType"), new LocalizedText("en", "AuditConditionEnableEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2803"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=9028"), NodeClass.Method, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2803"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=9027"), NodeClass.Method, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2803"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2790"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode91() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2829"), new QualifiedName(0, "AuditConditionCommentEventType"), new LocalizedText("en", "AuditConditionCommentEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2829"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=9029"), NodeClass.Method, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2829"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=4170"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2829"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11851"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2829"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2790"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2829"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=4170"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2829"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11851"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode92() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2830"), new QualifiedName(0, "DialogConditionType"), new LocalizedText("en", "DialogConditionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9035"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9055"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2831"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9064"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9065"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9066"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9067"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9068"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9069"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2782"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9035"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9055"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=2831"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9064"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9065"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9066"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9067"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9068"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2830"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9069"), NodeClass.Method, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode93() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2881"), new QualifiedName(0, "AcknowledgeableConditionType"), new LocalizedText("en", "AcknowledgeableConditionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2881"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9073"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2881"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9093"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2881"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9102"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2881"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9111"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2881"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9113"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2881"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2782"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2881"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9073"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2881"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9093"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2881"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9102"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2881"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9111"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2881"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9113"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2881"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2915"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode94() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=11093"), new QualifiedName(0, "AuditConditionShelvingEventType"), new LocalizedText("en", "AuditConditionShelvingEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11093"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=9211"), NodeClass.Method, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11093"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=9212"), NodeClass.Method, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11093"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=9213"), NodeClass.Method, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11093"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=2947"), NodeClass.Method, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11093"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=2948"), NodeClass.Method, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11093"), NodeId.parse("ns=0;i=3065"), ExpandedNodeId.parse("svr=0;i=2949"), NodeClass.Method, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11093"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11855"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11093"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2790"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11093"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11855"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode95() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2915"), new QualifiedName(0, "AlarmConditionType"), new LocalizedText("en", "AlarmConditionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9118"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9160"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11120"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9169"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9178"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9215"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9216"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2881"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9118"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9160"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11120"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9169"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=9178"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9215"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9216"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=54"), ExpandedNodeId.parse("svr=0;i=2935"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=54"), ExpandedNodeId.parse("svr=0;i=2936"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=54"), ExpandedNodeId.parse("svr=0;i=2940"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=54"), ExpandedNodeId.parse("svr=0;i=2942"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=54"), ExpandedNodeId.parse("svr=0;i=2943"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=54"), ExpandedNodeId.parse("svr=0;i=2945"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2955"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2915"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=10523"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode96() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2929"), new QualifiedName(0, "ShelvedStateMachineType"), new LocalizedText("en", "ShelvedStateMachineType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=9178"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9115"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2930"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2932"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2933"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2935"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2936"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2940"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2942"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2943"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2945"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2947"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2948"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2949"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2771"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=9115"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2930"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2932"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2933"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2935"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2936"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2940"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2942"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2943"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2945"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2947"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2948"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2929"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=2949"), NodeClass.Method, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode97() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2955"), new QualifiedName(0, "LimitAlarmType"), new LocalizedText("en", "LimitAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2955"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11124"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2955"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11125"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2955"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11126"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2955"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11127"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2955"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2915"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2955"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11124"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2955"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11125"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2955"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11126"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2955"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11127"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2955"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=9341"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2955"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=9906"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode98() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=11163"), new QualifiedName(0, "BaseConditionClassType"), new LocalizedText("en", "BaseConditionClassType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11163"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11163"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11164"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11163"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11165"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11163"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11166"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode99() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=11164"), new QualifiedName(0, "ProcessConditionClassType"), new LocalizedText("en", "ProcessConditionClassType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11164"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11163"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode100() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=11165"), new QualifiedName(0, "MaintenanceConditionClassType"), new LocalizedText("en", "MaintenanceConditionClassType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11165"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11163"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode101() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=11166"), new QualifiedName(0, "SystemConditionClassType"), new LocalizedText("en", "SystemConditionClassType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11166"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11163"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode102() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=11187"), new QualifiedName(0, "AggregateConfigurationType"), new LocalizedText("en", "AggregateConfigurationType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11187"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=3059"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11187"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11203"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11187"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11188"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11187"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11189"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11187"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11190"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11187"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11191"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11187"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11187"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11188"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11187"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11189"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11187"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11190"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11187"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11191"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode103() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=2999"), new QualifiedName(0, "AuditHistoryEventUpdateEventType"), new LocalizedText("en", "AuditHistoryEventUpdateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=2999"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3025"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2999"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3028"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2999"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3003"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2999"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3029"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2999"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3030"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2999"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2104"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2999"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3025"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2999"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3028"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2999"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3003"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2999"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3029"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=2999"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3030"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode104() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=3006"), new QualifiedName(0, "AuditHistoryValueUpdateEventType"), new LocalizedText("en", "AuditHistoryValueUpdateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=3006"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3026"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3006"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3031"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3006"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3032"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3006"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3033"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3006"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2104"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3006"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3026"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3006"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3031"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3006"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3032"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3006"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3033"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode105() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=3012"), new QualifiedName(0, "AuditHistoryDeleteEventType"), new LocalizedText("en", "AuditHistoryDeleteEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=3012"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3027"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3012"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2104"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3012"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3027"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3012"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=3014"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3012"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=3019"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3012"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=3022"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode106() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=3014"), new QualifiedName(0, "AuditHistoryRawModifyDeleteEventType"), new LocalizedText("en", "AuditHistoryRawModifyDeleteEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=3014"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3015"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3014"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3016"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3014"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3017"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3014"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3034"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3014"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=3012"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3014"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3015"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3014"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3016"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3014"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3017"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3014"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3034"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode107() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=3019"), new QualifiedName(0, "AuditHistoryAtTimeDeleteEventType"), new LocalizedText("en", "AuditHistoryAtTimeDeleteEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=3019"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3020"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3019"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3021"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3019"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=3012"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3019"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3020"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3019"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3021"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode108() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=3022"), new QualifiedName(0, "AuditHistoryEventDeleteEventType"), new LocalizedText("en", "AuditHistoryEventDeleteEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=3022"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3023"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3022"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3024"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3022"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=3012"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3022"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3023"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3022"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=3024"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode109() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=3035"), new QualifiedName(0, "EventQueueOverflowEventType"), new LocalizedText("en", "EventQueueOverflowEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=3035"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2041"), NodeClass.ObjectType, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode110() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=11436"), new QualifiedName(0, "ProgressEventType"), new LocalizedText("en", "ProgressEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11436"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12502"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11436"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12503"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11436"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2041"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11436"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12502"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11436"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12503"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode111() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=11446"), new QualifiedName(0, "SystemStatusChangeEventType"), new LocalizedText("en", "SystemStatusChangeEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), true);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11446"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11696"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11446"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2130"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11446"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11696"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode112() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=11564"), new QualifiedName(0, "OperationLimitsType"), new LocalizedText("en", "OperationLimitsType"), new LocalizedText("en", "Identifies the operation limits imposed by the server."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11551"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11565"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12161"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12162"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11567"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12163"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12164"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11569"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11570"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11571"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11572"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11573"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11574"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11565"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12161"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12162"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11567"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12163"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12164"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11569"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11570"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11571"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11572"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11573"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11574"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11564"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11704"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode113() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=11575"), new QualifiedName(0, "FileType"), new LocalizedText("en", "FileType"), new LocalizedText("en", "An object that represents a file that can be accessed via the server."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11576"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12686"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12687"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11579"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13341"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11580"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11583"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11585"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11588"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11590"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11593"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11576"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12686"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=12687"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11579"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=13341"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11580"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11583"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11585"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11588"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11590"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11593"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=13366"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11595"), NodeClass.ObjectType, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11575"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=12522"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode114() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=11595"), new QualifiedName(0, "AddressSpaceFileType"), new LocalizedText("en", "AddressSpaceFileType"), new LocalizedText("en", "A file used to store a namespace exported from the server."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11595"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11615"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11595"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=11575"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11595"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11615"), NodeClass.Method, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11595"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11624"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11595"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11675"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode115() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=11616"), new QualifiedName(0, "NamespaceMetadataType"), new LocalizedText("en", "NamespaceMetadataType"), new LocalizedText("en", "Provides the metadata for a namespace used by the server."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11617"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11618"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11619"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11620"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11621"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11622"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11623"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11624"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11617"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11618"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11619"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11620"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11621"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11622"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11623"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11624"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11616"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11646"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode116() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=11645"), new QualifiedName(0, "NamespacesType"), new LocalizedText("en", "NamespacesType"), new LocalizedText("en", "A container for the namespace metadata provided by the server."), UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11645"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11527"), NodeClass.Object, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11645"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11646"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11645"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11675"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11645"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=58"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11645"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11646"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11645"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=11675"), NodeClass.Object, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11645"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=11715"), NodeClass.Object, false));
        this.nodeManager.addNode(node);
    }

    private void buildNode117() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=11753"), new QualifiedName(0, "SystemOffNormalAlarmType"), new LocalizedText("en", "SystemOffNormalAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11753"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=10637"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11753"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=13225"), NodeClass.ObjectType, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode118() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=11856"), new QualifiedName(0, "AuditProgramTransitionEventType"), new LocalizedText("en", "AuditProgramTransitionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11856"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11875"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11856"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2315"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11856"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11875"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode119() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=11945"), new QualifiedName(0, "NonTransparentNetworkRedundancyType"), new LocalizedText("en", "NonTransparentNetworkRedundancyType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=11945"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11948"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11945"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2039"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=11945"), NodeId.parse("ns=0;i=46"), ExpandedNodeId.parse("svr=0;i=11948"), NodeClass.Variable, true));
        this.nodeManager.addNode(node);
    }

    private void buildNode120() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, NodeId.parse("ns=0;i=3806"), new QualifiedName(0, "ProgramTransitionAuditEventType"), new LocalizedText("en", "ProgramTransitionAuditEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
        node.addReference(new Reference(NodeId.parse("ns=0;i=3806"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=3825"), NodeClass.Variable, true));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3806"), NodeId.parse("ns=0;i=45"), ExpandedNodeId.parse("svr=0;i=2315"), NodeClass.ObjectType, false));
        node.addReference(new Reference(NodeId.parse("ns=0;i=3806"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=3825"), NodeClass.Variable, true));
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
    }

}
