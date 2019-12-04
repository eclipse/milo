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
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableTypeNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

class VariableTypeNodeLoader {
    private final UaNodeContext context;

    private final NodeManager<UaNode> nodeManager;

    VariableTypeNodeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager) {
        this.context = context;
        this.nodeManager = nodeManager;
    }

    private void loadNode0() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 62), new QualifiedName(0, "BaseVariableType"), new LocalizedText("en", "BaseVariableType"), new LocalizedText("en", "The abstract base type for all variable nodes."), UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 24), -2, new UInteger[]{}, true);
        this.nodeManager.addNode(node);
    }

    private void loadNode1() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 63), new QualifiedName(0, "BaseDataVariableType"), new LocalizedText("en", "BaseDataVariableType"), new LocalizedText("en", "The type for variable that represents a process value."), UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 24), -2, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 63), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(62), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode2() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 68), new QualifiedName(0, "PropertyType"), new LocalizedText("en", "PropertyType"), new LocalizedText("en", "The type for variable that represents a property of another node."), UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 24), -2, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 68), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(62), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode3() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 69), new QualifiedName(0, "DataTypeDescriptionType"), new LocalizedText("en", "DataTypeDescriptionType"), new LocalizedText("en", "The type for variable that represents the description of a data type encoding."), UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 12), -1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 69), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(104), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 69), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(105), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 69), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode4() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 72), new QualifiedName(0, "DataTypeDictionaryType"), new LocalizedText("en", "DataTypeDictionaryType"), new LocalizedText("en", "The type for variable that represents the collection of data type decriptions."), UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 15), -1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 72), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(106), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 72), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(107), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 72), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode5() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 8995), new QualifiedName(0, "TwoStateVariableType"), new LocalizedText("en", "TwoStateVariableType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 21), -1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 8995), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8996), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 8995), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9000), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 8995), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9001), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 8995), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11110), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 8995), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11111), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 8995), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2755), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode6() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 9002), new QualifiedName(0, "ConditionVariableType"), new LocalizedText("en", "ConditionVariableType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 24), -2, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 9002), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9003), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9002), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode7() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2137), new QualifiedName(0, "ServerVendorCapabilityType"), new LocalizedText("en", "ServerVendorCapabilityType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 24), -1, new UInteger[]{}, true);
        node.addReference(new Reference(new NodeId(0, 2137), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode8() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2138), new QualifiedName(0, "ServerStatusType"), new LocalizedText("en", "ServerStatusType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 862), -1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2138), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2139), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2138), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2140), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2138), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2141), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2138), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2142), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2138), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2752), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2138), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2753), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2138), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode9() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2150), new QualifiedName(0, "ServerDiagnosticsSummaryType"), new LocalizedText("en", "ServerDiagnosticsSummaryType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 859), -1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2150), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2151), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2150), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2152), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2150), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2153), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2150), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2154), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2150), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2155), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2150), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2156), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2150), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2157), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2150), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2159), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2150), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2160), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2150), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2161), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2150), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2162), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2150), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2163), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2150), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode10() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2164), new QualifiedName(0, "SamplingIntervalDiagnosticsArrayType"), new LocalizedText("en", "SamplingIntervalDiagnosticsArrayType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 856), 1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2164), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12779), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2164), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode11() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2165), new QualifiedName(0, "SamplingIntervalDiagnosticsType"), new LocalizedText("en", "SamplingIntervalDiagnosticsType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 856), -1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2165), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2166), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2165), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11697), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2165), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11698), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2165), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11699), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2165), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode12() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2171), new QualifiedName(0, "SubscriptionDiagnosticsArrayType"), new LocalizedText("en", "SubscriptionDiagnosticsArrayType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 874), 1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2171), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12784), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2171), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode13() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2172), new QualifiedName(0, "SubscriptionDiagnosticsType"), new LocalizedText("en", "SubscriptionDiagnosticsType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 874), -1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2173), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2174), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2175), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2176), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2177), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8888), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2179), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2180), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2181), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2182), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2183), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2184), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2185), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2186), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2187), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2188), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2189), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2190), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2191), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2998), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2193), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8889), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8890), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8891), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8892), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8893), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8894), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8895), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8896), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8897), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8902), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2172), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode14() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2196), new QualifiedName(0, "SessionDiagnosticsArrayType"), new LocalizedText("en", "SessionDiagnosticsArrayType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 865), 1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2196), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12816), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2196), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode15() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2197), new QualifiedName(0, "SessionDiagnosticsVariableType"), new LocalizedText("en", "SessionDiagnosticsVariableType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 865), -1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2198), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2199), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2200), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2201), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2202), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2203), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2204), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3050), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2205), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2206), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2207), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2208), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2209), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8900), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11892), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2217), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2218), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2219), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2220), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2221), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2222), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2223), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2224), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2225), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2226), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2227), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2228), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2229), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2230), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2231), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2232), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2233), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2234), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2235), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2236), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2237), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2238), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2239), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2240), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2241), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2242), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2730), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2731), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2197), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode16() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2243), new QualifiedName(0, "SessionSecurityDiagnosticsArrayType"), new LocalizedText("en", "SessionSecurityDiagnosticsArrayType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 868), 1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2243), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12860), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2243), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode17() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2244), new QualifiedName(0, "SessionSecurityDiagnosticsType"), new LocalizedText("en", "SessionSecurityDiagnosticsType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 868), -1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2244), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2245), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2244), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2246), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2244), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2247), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2244), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2248), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2244), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2249), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2244), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2250), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2244), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2251), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2244), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2252), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2244), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3058), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2244), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode18() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2365), new QualifiedName(0, "DataItemType"), new LocalizedText("en", "DataItemType"), new LocalizedText("en", "A variable that contains live automation data."), UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 24), -2, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2365), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2366), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2365), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2367), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2365), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode19() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2368), new QualifiedName(0, "AnalogItemType"), new LocalizedText("en", "AnalogItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 26), -2, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2368), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2370), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2368), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2369), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2368), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2371), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2368), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2365), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode20() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2372), new QualifiedName(0, "DiscreteItemType"), new LocalizedText("en", "DiscreteItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 24), -2, new UInteger[]{}, true);
        node.addReference(new Reference(new NodeId(0, 2372), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2365), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode21() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2373), new QualifiedName(0, "TwoStateDiscreteType"), new LocalizedText("en", "TwoStateDiscreteType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 1), -2, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2373), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2374), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2373), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2375), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2373), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2372), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode22() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2376), new QualifiedName(0, "MultiStateDiscreteType"), new LocalizedText("en", "MultiStateDiscreteType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 28), -2, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2376), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2377), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2376), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2372), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode23() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2380), new QualifiedName(0, "ProgramDiagnosticType"), new LocalizedText("en", "ProgramDiagnosticType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 894), -1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2380), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2381), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2380), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2382), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2380), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2383), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2380), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2384), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2380), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2385), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2380), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2386), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2380), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2387), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2380), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2388), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2380), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2389), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2380), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2390), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2380), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode24() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2755), new QualifiedName(0, "StateVariableType"), new LocalizedText("en", "StateVariableType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 21), -1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2755), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2756), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2755), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2757), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2755), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2758), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2755), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2759), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2755), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode25() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2760), new QualifiedName(0, "FiniteStateVariableType"), new LocalizedText("en", "FiniteStateVariableType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 21), -1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2760), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2761), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2760), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2755), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode26() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2762), new QualifiedName(0, "TransitionVariableType"), new LocalizedText("en", "TransitionVariableType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 21), -1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2762), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2763), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2762), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2764), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2762), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2765), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2762), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2766), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2762), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11456), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2762), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode27() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 2767), new QualifiedName(0, "FiniteTransitionVariableType"), new LocalizedText("en", "FiniteTransitionVariableType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 21), -1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 2767), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2768), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2767), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2762), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode28() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 11238), new QualifiedName(0, "MultiStateValueDiscreteType"), new LocalizedText("en", "MultiStateValueDiscreteType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 26), -2, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 11238), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11241), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11238), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11461), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11238), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2372), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode29() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 3051), new QualifiedName(0, "BuildInfoType"), new LocalizedText("en", "BuildInfoType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 338), -1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 3051), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3052), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3051), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3053), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3051), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3054), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3051), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3055), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3051), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3056), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3051), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3057), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3051), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode30() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 11487), new QualifiedName(0, "OptionSetType"), new LocalizedText("en", "OptionSetType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 24), -1, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 11487), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11488), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11487), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11701), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11487), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(63), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode31() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 12021), new QualifiedName(0, "ArrayItemType"), new LocalizedText("en", "ArrayItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 24), 0, new UInteger[]{}, true);
        node.addReference(new Reference(new NodeId(0, 12021), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12024), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12021), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12025), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12021), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12026), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12021), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12027), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12021), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12028), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12021), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2365), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode32() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 12029), new QualifiedName(0, "YArrayItemType"), new LocalizedText("en", "YArrayItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 24), 1, new UInteger[]{UInteger.valueOf(0)}, false);
        node.addReference(new Reference(new NodeId(0, 12029), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12037), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12029), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12021), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode33() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 12038), new QualifiedName(0, "XYArrayItemType"), new LocalizedText("en", "XYArrayItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 12080), 1, new UInteger[]{UInteger.valueOf(0)}, false);
        node.addReference(new Reference(new NodeId(0, 12038), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12046), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12038), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12021), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode34() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 12047), new QualifiedName(0, "ImageItemType"), new LocalizedText("en", "ImageItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 24), 2, new UInteger[]{UInteger.valueOf(0), UInteger.valueOf(0)}, false);
        node.addReference(new Reference(new NodeId(0, 12047), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12055), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12047), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12056), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12047), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12021), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode35() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 12057), new QualifiedName(0, "CubeItemType"), new LocalizedText("en", "CubeItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 24), 3, new UInteger[]{UInteger.valueOf(0), UInteger.valueOf(0), UInteger.valueOf(0)}, false);
        node.addReference(new Reference(new NodeId(0, 12057), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12065), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12057), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12066), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12057), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12067), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12057), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12021), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode36() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, new NodeId(0, 12068), new QualifiedName(0, "NDimensionArrayItemType"), new LocalizedText("en", "NDimensionArrayItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), new NodeId(0, 24), 0, new UInteger[]{}, false);
        node.addReference(new Reference(new NodeId(0, 12068), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12076), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12068), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12021), UInteger.valueOf(0)), false));
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
    }
}
