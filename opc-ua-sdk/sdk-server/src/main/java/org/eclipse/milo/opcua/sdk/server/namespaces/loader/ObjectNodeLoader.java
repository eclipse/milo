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
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.AddressSpaceFileTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.AggregateConfigurationTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.AggregateFunctionTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.CertificateGroupFolderTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.CertificateGroupTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.DataTypeEncodingTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.DataTypeSystemTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ExclusiveLimitStateMachineTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.FileDirectoryTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.FileTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.FolderTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.HistoricalDataConfigurationTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.HistoryServerCapabilitiesTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ModellingRuleTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.NamespaceMetadataTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.NamespacesTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.OperationLimitsTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerCapabilitiesTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerConfigurationTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerRedundancyTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.SessionDiagnosticsObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.SessionsDiagnosticsSummaryTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ShelvedStateMachineTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.StateTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.TransitionTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.TrustListTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.VendorServerInfoTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

class ObjectNodeLoader {
    private final UaNodeContext context;

    private final NodeManager<UaNode> nodeManager;

    ObjectNodeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager) {
        this.context = context;
        this.nodeManager = nodeManager;
    }

    private void loadNode0() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 8251), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 8251), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7594), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 8251), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7656), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 8251), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode1() {
        ModellingRuleTypeNode node = new ModellingRuleTypeNode(this.context, new NodeId(0, 78), new QualifiedName(0, "Mandatory"), new LocalizedText("en", "Mandatory"), new LocalizedText("en", "Specifies that an instance with the attributes and references of the instance declaration must appear when a type is instantiated."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 78), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(112), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 78), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(77), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode2() {
        ModellingRuleTypeNode node = new ModellingRuleTypeNode(this.context, new NodeId(0, 79), new QualifiedName(0, "MandatoryShared"), new LocalizedText("en", "MandatoryShared"), new LocalizedText("en", "Specifies that a reference to a shared instance must appear in when a type is instantiated."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 79), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(116), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 79), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(77), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode3() {
        ModellingRuleTypeNode node = new ModellingRuleTypeNode(this.context, new NodeId(0, 80), new QualifiedName(0, "Optional"), new LocalizedText("en", "Optional"), new LocalizedText("en", "Specifies that an instance with the attributes and references of the instance declaration may appear when a type is instantiated."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 80), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(113), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 80), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(77), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode4() {
        ModellingRuleTypeNode node = new ModellingRuleTypeNode(this.context, new NodeId(0, 83), new QualifiedName(0, "ExposesItsArray"), new LocalizedText("en", "ExposesItsArray"), new LocalizedText("en", "Specifies that an instance appears for each element of the containing array variable."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 83), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(114), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 83), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(77), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode5() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 84), new QualifiedName(0, "Root"), new LocalizedText("en", "Root"), new LocalizedText("en", "The root of the server address space."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 84), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode6() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 85), new QualifiedName(0, "Objects"), new LocalizedText("en", "Objects"), new LocalizedText("en", "The browse entry point when looking for objects in the server address space."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 85), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(84), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 85), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode7() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 86), new QualifiedName(0, "Types"), new LocalizedText("en", "Types"), new LocalizedText("en", "The browse entry point when looking for types in the server address space."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 86), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(84), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 86), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode8() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 87), new QualifiedName(0, "Views"), new LocalizedText("en", "Views"), new LocalizedText("en", "The browse entry point when looking for views in the server address space."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 87), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(84), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 87), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode9() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 88), new QualifiedName(0, "ObjectTypes"), new LocalizedText("en", "ObjectTypes"), new LocalizedText("en", "The browse entry point when looking for object types in the server address space."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 88), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(86), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 88), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 88), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode10() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 89), new QualifiedName(0, "VariableTypes"), new LocalizedText("en", "VariableTypes"), new LocalizedText("en", "The browse entry point when looking for variable types in the server address space."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 89), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(86), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 89), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(62), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 89), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode11() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 90), new QualifiedName(0, "DataTypes"), new LocalizedText("en", "DataTypes"), new LocalizedText("en", "The browse entry point when looking for data types in the server address space."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 90), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(86), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 90), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 90), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode12() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 91), new QualifiedName(0, "ReferenceTypes"), new LocalizedText("en", "ReferenceTypes"), new LocalizedText("en", "The browse entry point when looking for reference types in the server address space."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 91), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(86), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 91), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(31), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 91), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode13() {
        DataTypeSystemTypeNode node = new DataTypeSystemTypeNode(this.context, new NodeId(0, 92), new QualifiedName(0, "XML Schema"), new LocalizedText("en", "XML Schema"), new LocalizedText("en", "A type system which uses XML schema to describe the encoding of data types."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 92), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(90), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 92), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(75), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode14() {
        DataTypeSystemTypeNode node = new DataTypeSystemTypeNode(this.context, new NodeId(0, 93), new QualifiedName(0, "OPC Binary"), new LocalizedText("en", "OPC Binary"), new LocalizedText("en", "A type system which uses OPC binary schema to describe the encoding of data types."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 93), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(90), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 93), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(75), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode15() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 297), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 297), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(296), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 297), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8285), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 297), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode16() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 298), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 298), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(296), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 298), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7650), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 298), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode17() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 300), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 300), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(299), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 300), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8294), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 300), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode18() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 301), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 301), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(299), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 301), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7659), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 301), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode19() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 305), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 305), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(304), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 305), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8297), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 305), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode20() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 306), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 306), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(304), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 306), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7662), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 306), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode21() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 309), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 309), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(308), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 309), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8300), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 309), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode22() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 310), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 310), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(308), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 310), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7665), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 310), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode23() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 313), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 313), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(312), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 313), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8303), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 313), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode24() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 314), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 314), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(312), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 314), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7668), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 314), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode25() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 317), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 317), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(316), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 317), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8306), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 317), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode26() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 318), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 318), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(316), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 318), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7671), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 318), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode27() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 320), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 320), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(319), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 320), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8309), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 320), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode28() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 321), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 321), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(319), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 321), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7674), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 321), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode29() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 323), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 323), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(322), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 323), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8312), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 323), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode30() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 324), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 324), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(322), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 324), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7677), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 324), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode31() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 326), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 326), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(325), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 326), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8315), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 326), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode32() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 327), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 327), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(325), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 327), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7680), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 327), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode33() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 332), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 332), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(331), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 332), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8321), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 332), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode34() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 333), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 333), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(331), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 333), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7686), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 333), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode35() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 339), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 339), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(338), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 339), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8327), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 339), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode36() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 340), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 340), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(338), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 340), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7692), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 340), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode37() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 345), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 345), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(344), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 345), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8333), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 345), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode38() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 346), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 346), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(344), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 346), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7698), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 346), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode39() {
        ServerConfigurationTypeNode node = new ServerConfigurationTypeNode(this.context, new NodeId(0, 12637), new QualifiedName(0, "ServerConfiguration"), new LocalizedText("en", "ServerConfiguration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12637), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14053), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12637), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12710), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12637), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12639), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12637), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12640), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12637), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12641), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12637), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13737), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12637), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12740), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12637), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12737), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12637), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12777), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12637), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2253), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12637), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12581), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode40() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, new NodeId(0, 12642), new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12643), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14157), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14158), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12646), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12647), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12650), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12652), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12655), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12657), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12660), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12662), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12663), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12666), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12668), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12670), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12522), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12642), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14156), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode41() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 377), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 377), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(376), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 377), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8363), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 377), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode42() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 378), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 378), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(376), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 378), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7728), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 378), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode43() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 380), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 380), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(379), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 380), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8366), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 380), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode44() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 381), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 381), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(379), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 381), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7731), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 381), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode45() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 383), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 383), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(382), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 383), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8369), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 383), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode46() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 384), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 384), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(382), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 384), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7734), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 384), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode47() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 386), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 386), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(385), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 386), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8372), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 386), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode48() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 387), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 387), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(385), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 387), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7737), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 387), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode49() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12676), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12676), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12554), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12676), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12677), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12676), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode50() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12680), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12680), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12554), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12680), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12681), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12680), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode51() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 433), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 433), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(432), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 433), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8417), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 433), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode52() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 434), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 434), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(432), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 434), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7782), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 434), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode53() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12757), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12757), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12755), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12757), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12759), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12757), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode54() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12758), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12758), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12756), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12758), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12762), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12758), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode55() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12765), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12765), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12755), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12765), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12767), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12765), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode56() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12766), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12766), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12756), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12766), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12770), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12766), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode57() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 538), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 538), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(537), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 538), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12712), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 538), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode58() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 539), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 539), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(537), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 539), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12718), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 539), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode59() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 541), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 541), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(540), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 541), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12715), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 541), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode60() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 542), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 542), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(540), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 542), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12721), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 542), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode61() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 584), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 584), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(583), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 584), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8564), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 584), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode62() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 585), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 585), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(583), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 585), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7929), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 585), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode63() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 587), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 587), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(586), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 587), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8567), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 587), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode64() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 588), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 588), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(586), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 588), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7932), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 588), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode65() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 590), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 590), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(589), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 590), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8570), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 590), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode66() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 591), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 591), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(589), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 591), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7935), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 591), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode67() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 593), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 593), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(592), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 593), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8573), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 593), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode68() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 594), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 594), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(592), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 594), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7938), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 594), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode69() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 596), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 596), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(595), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 596), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8576), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 596), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode70() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 597), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 597), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(595), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 597), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7941), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 597), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode71() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 599), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 599), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(598), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 599), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8579), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 599), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode72() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 600), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 600), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(598), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 600), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7944), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 600), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode73() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 602), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 602), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(601), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 602), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8582), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 602), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode74() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 603), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 603), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(601), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 603), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7947), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 603), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode75() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12892), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12892), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12890), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12892), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12894), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12892), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode76() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12893), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12893), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12891), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12893), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12897), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12893), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode77() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12900), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12900), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12890), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12900), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12902), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12900), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode78() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12901), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12901), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12891), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12901), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12905), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12901), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode79() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 660), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 660), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(659), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 660), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8639), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 660), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode80() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 661), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 661), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(659), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 661), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8004), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 661), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode81() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 720), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 720), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(719), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 720), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8702), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 720), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode82() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 8913), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 8913), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8912), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 8913), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8918), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 8913), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode83() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 721), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 721), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(719), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 721), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8067), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 721), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode84() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 8917), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 8917), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8912), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 8917), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8914), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 8917), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode85() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 726), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 726), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(725), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 726), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8708), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 726), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode86() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 727), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 727), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(725), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 727), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8073), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 727), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode87() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 854), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 854), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(853), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 854), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8843), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 854), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode88() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 855), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 855), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(853), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 855), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8208), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 855), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode89() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 857), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 857), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(856), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 857), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8846), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 857), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode90() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 858), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 858), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(856), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 858), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8211), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 858), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode91() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 860), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 860), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(859), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 860), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8849), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 860), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode92() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 861), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 861), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(859), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 861), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8214), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 861), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode93() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 863), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 863), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(862), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 863), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8852), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 863), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode94() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 864), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 864), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(862), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 864), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8217), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 864), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode95() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 866), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 866), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(865), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 866), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8855), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 866), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode96() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 867), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 867), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(865), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 867), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8220), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 867), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode97() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 869), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 869), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(868), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 869), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8858), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 869), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode98() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 870), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 870), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(868), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 870), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8223), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 870), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode99() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 872), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 872), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(871), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 872), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8861), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 872), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode100() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 873), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 873), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(871), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 873), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8226), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 873), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode101() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 875), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 875), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(874), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 875), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8864), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 875), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode102() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 876), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 876), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(874), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 876), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8229), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 876), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode103() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 878), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 878), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(877), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 878), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8867), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 878), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode104() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 879), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 879), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(877), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 879), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8232), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 879), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode105() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 885), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 885), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(884), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 885), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8873), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 885), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode106() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 886), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 886), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(884), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 886), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8238), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 886), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode107() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 888), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 888), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(887), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 888), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8876), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 888), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode108() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 889), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 889), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(887), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 889), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8241), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 889), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode109() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 892), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 892), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(891), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 892), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8879), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 892), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode110() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 893), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 893), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(891), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 893), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8244), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 893), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode111() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 895), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 895), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(894), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 895), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8882), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 895), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode112() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 896), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 896), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(894), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 896), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8247), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 896), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode113() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 898), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 898), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(897), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 898), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8870), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 898), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode114() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 899), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 899), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(897), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 899), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8235), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 899), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode115() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 921), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 921), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(920), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 921), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8807), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 921), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode116() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 922), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 922), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(920), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 922), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8172), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 922), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode117() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 939), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 939), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(938), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 939), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8318), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 939), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode118() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 940), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 940), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(938), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 940), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7683), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 940), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode119() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 949), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 949), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(948), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 949), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8711), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 949), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode120() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 950), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 950), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(948), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 950), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8076), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 950), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode121() {
        ShelvedStateMachineTypeNode node = new ShelvedStateMachineTypeNode(this.context, new NodeId(0, 9178), new QualifiedName(0, "ShelvingState"), new LocalizedText("en", "ShelvingState"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 9178), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9179), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9178), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9184), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9178), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9189), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9178), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9211), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9178), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9212), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9178), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9213), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9178), new NodeId(0, 9004), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9118), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 9178), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2929), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9178), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9178), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2915), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode122() {
        FileDirectoryTypeNode node = new FileDirectoryTypeNode(this.context, new NodeId(0, 13354), new QualifiedName(0, "<FileDirectoryName>"), new LocalizedText("en", "<FileDirectoryName>"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 13354), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13355), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13354), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13358), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13354), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13361), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13354), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13363), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13354), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13353), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13354), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11508), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13354), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13353), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode123() {
        FileTypeNode node = new FileTypeNode(this.context, new NodeId(0, 13366), new QualifiedName(0, "<FileName>"), new LocalizedText("en", "<FileName>"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 13366), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13367), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13366), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13368), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13366), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13369), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13366), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13370), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13366), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13372), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13366), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13375), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13366), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13377), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13366), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13380), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13366), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13382), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13366), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13385), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13366), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11575), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13366), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11508), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13366), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13353), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode124() {
        StateTypeNode node = new StateTypeNode(this.context, new NodeId(0, 9329), new QualifiedName(0, "HighHigh"), new LocalizedText("en", "HighHigh"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 9329), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9330), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9329), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9339), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 9329), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9340), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 9329), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2307), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9329), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9318), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode125() {
        StateTypeNode node = new StateTypeNode(this.context, new NodeId(0, 9331), new QualifiedName(0, "High"), new LocalizedText("en", "High"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 9331), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9332), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9331), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9339), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 9331), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9340), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 9331), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2307), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9331), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9318), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode126() {
        StateTypeNode node = new StateTypeNode(this.context, new NodeId(0, 9333), new QualifiedName(0, "Low"), new LocalizedText("en", "Low"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 9333), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9334), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9333), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9337), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 9333), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9338), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 9333), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2307), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9333), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9318), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode127() {
        StateTypeNode node = new StateTypeNode(this.context, new NodeId(0, 9335), new QualifiedName(0, "LowLow"), new LocalizedText("en", "LowLow"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 9335), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9336), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9335), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9337), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 9335), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9338), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 9335), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2307), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9335), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9318), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode128() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 9337), new QualifiedName(0, "LowLowToLow"), new LocalizedText("en", "LowLowToLow"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 9337), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11340), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9337), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9335), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9337), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9333), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9337), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9337), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9318), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode129() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 9338), new QualifiedName(0, "LowToLowLow"), new LocalizedText("en", "LowToLowLow"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 9338), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11341), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9338), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9333), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9338), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9335), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9338), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9338), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9318), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode130() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 9339), new QualifiedName(0, "HighHighToHigh"), new LocalizedText("en", "HighHighToHigh"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 9339), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11342), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9339), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9329), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9339), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9331), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9339), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9339), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9318), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode131() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 9340), new QualifiedName(0, "HighToHighHigh"), new LocalizedText("en", "HighToHighHigh"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 9340), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11343), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9340), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9331), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9340), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9329), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9340), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9340), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9318), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode132() {
        ExclusiveLimitStateMachineTypeNode node = new ExclusiveLimitStateMachineTypeNode(this.context, new NodeId(0, 9455), new QualifiedName(0, "LimitState"), new LocalizedText("en", "LimitState"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 9455), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9456), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9455), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9461), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9455), new NodeId(0, 9004), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9398), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 9455), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9318), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9455), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9455), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9341), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode133() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, new NodeId(0, 13599), new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 13599), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13600), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13599), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13601), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13599), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13602), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13599), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13603), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13599), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13605), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13599), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13608), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13599), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13610), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13599), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13613), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13599), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13615), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13599), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13618), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13599), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13620), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13599), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13621), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13599), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12522), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13599), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13599), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12555), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode134() {
        CertificateGroupTypeNode node = new CertificateGroupTypeNode(this.context, new NodeId(0, 13814), new QualifiedName(0, "DefaultApplicationGroup"), new LocalizedText("en", "DefaultApplicationGroup"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 13814), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13815), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13814), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13847), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13814), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12555), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13814), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13814), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13813), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode135() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, new NodeId(0, 13815), new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 13815), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13816), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13815), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13817), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13815), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13818), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13815), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13819), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13815), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13821), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13815), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13824), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13815), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13826), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13815), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13829), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13815), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13831), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13815), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13834), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13815), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13836), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13815), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13837), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13815), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12522), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13815), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13815), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13814), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode136() {
        CertificateGroupTypeNode node = new CertificateGroupTypeNode(this.context, new NodeId(0, 13848), new QualifiedName(0, "DefaultHttpsGroup"), new LocalizedText("en", "DefaultHttpsGroup"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 13848), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13849), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13848), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13881), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13848), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12555), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13848), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13848), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13813), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode137() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, new NodeId(0, 13849), new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 13849), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13850), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13849), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13851), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13849), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13852), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13849), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13853), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13849), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13855), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13849), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13858), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13849), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13860), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13849), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13863), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13849), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13865), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13849), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13868), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13849), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13870), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13849), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13871), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13849), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12522), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13849), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13849), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13848), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode138() {
        CertificateGroupTypeNode node = new CertificateGroupTypeNode(this.context, new NodeId(0, 13882), new QualifiedName(0, "DefaultUserTokenGroup"), new LocalizedText("en", "DefaultUserTokenGroup"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 13882), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13883), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13882), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13915), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13882), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12555), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13882), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13882), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13813), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode139() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, new NodeId(0, 13883), new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 13883), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13884), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13883), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13885), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13883), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13886), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13883), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13887), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13883), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13889), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13883), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13892), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13883), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13894), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13883), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13897), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13883), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13899), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13883), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13902), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13883), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13904), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13883), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13905), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13883), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12522), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13883), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13883), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13882), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode140() {
        CertificateGroupTypeNode node = new CertificateGroupTypeNode(this.context, new NodeId(0, 13916), new QualifiedName(0, "<AdditionalGroup>"), new LocalizedText("en", "<AdditionalGroup>"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 13916), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13917), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13916), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13949), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13916), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12555), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13916), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11508), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13916), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13813), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode141() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, new NodeId(0, 13917), new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 13917), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13918), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13917), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13919), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13917), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13920), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13917), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13921), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13917), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13923), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13917), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13926), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13917), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13928), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13917), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13931), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13917), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13933), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13917), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13936), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13917), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13938), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13917), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13939), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13917), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12522), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13917), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13917), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13916), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode142() {
        CertificateGroupFolderTypeNode node = new CertificateGroupFolderTypeNode(this.context, new NodeId(0, 13950), new QualifiedName(0, "CertificateGroups"), new LocalizedText("en", "CertificateGroups"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 13950), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13951), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13950), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13813), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13950), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13950), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12581), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode143() {
        CertificateGroupTypeNode node = new CertificateGroupTypeNode(this.context, new NodeId(0, 13951), new QualifiedName(0, "DefaultApplicationGroup"), new LocalizedText("en", "DefaultApplicationGroup"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 13951), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13952), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13951), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13984), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13951), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12555), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13951), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13951), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13950), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode144() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, new NodeId(0, 13952), new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 13952), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13953), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13952), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13954), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13952), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13955), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13952), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13956), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13952), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13958), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13952), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13961), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13952), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13963), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13952), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13966), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13952), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13968), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13952), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13971), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13952), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13973), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13952), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13974), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13952), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12522), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13952), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13952), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13951), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode145() {
        CertificateGroupFolderTypeNode node = new CertificateGroupFolderTypeNode(this.context, new NodeId(0, 14053), new QualifiedName(0, "CertificateGroups"), new LocalizedText("en", "CertificateGroups"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 14053), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14156), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14053), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14088), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14053), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14122), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14053), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13813), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14053), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12637), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode146() {
        CertificateGroupTypeNode node = new CertificateGroupTypeNode(this.context, new NodeId(0, 14088), new QualifiedName(0, "DefaultHttpsGroup"), new LocalizedText("en", "DefaultHttpsGroup"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 14088), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14089), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14088), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14121), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14088), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12555), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14088), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14053), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode147() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, new NodeId(0, 14089), new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14090), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14091), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14092), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14093), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14095), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14098), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14100), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14103), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14105), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14108), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14110), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14111), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14114), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14117), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14119), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12522), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14089), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14088), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode148() {
        CertificateGroupTypeNode node = new CertificateGroupTypeNode(this.context, new NodeId(0, 14122), new QualifiedName(0, "DefaultUserTokenGroup"), new LocalizedText("en", "DefaultUserTokenGroup"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 14122), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14123), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14122), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14155), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14122), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12555), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14122), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14053), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode149() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, new NodeId(0, 14123), new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14124), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14125), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14126), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14127), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14129), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14132), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14134), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14137), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14139), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14142), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14144), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14145), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14148), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14151), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14153), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12522), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14123), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14122), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode150() {
        CertificateGroupTypeNode node = new CertificateGroupTypeNode(this.context, new NodeId(0, 14156), new QualifiedName(0, "DefaultApplicationGroup"), new LocalizedText("en", "DefaultApplicationGroup"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 14156), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12642), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14156), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14161), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14156), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12555), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 14156), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14053), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode151() {
        ServerCapabilitiesTypeNode node = new ServerCapabilitiesTypeNode(this.context, new NodeId(0, 2009), new QualifiedName(0, "ServerCapabilities"), new LocalizedText("en", "ServerCapabilities"), new LocalizedText("en", "Describes capabilities supported by the server."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2009), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3086), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2009), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3087), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2009), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3088), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2009), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3089), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2009), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3090), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2009), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3091), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2009), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3092), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2009), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3093), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2009), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3094), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2009), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2013), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2009), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2009), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2004), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode152() {
        ServerDiagnosticsTypeNode node = new ServerDiagnosticsTypeNode(this.context, new NodeId(0, 2010), new QualifiedName(0, "ServerDiagnostics"), new LocalizedText("en", "ServerDiagnostics"), new LocalizedText("en", "Reports diagnostics about the server."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2010), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3095), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2010), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3110), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2010), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3111), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2010), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3114), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2010), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2020), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2010), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2010), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2004), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode153() {
        VendorServerInfoTypeNode node = new VendorServerInfoTypeNode(this.context, new NodeId(0, 2011), new QualifiedName(0, "VendorServerInfo"), new LocalizedText("en", "VendorServerInfo"), new LocalizedText("en", "Server information provided by the vendor."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2011), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2033), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2011), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2011), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2004), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode154() {
        ServerRedundancyTypeNode node = new ServerRedundancyTypeNode(this.context, new NodeId(0, 2012), new QualifiedName(0, "ServerRedundancy"), new LocalizedText("en", "ServerRedundancy"), new LocalizedText("en", "Describes the redundancy capabilities of the server."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2012), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3115), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2012), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2034), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2012), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2012), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2004), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode155() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 2019), new QualifiedName(0, "ModellingRules"), new LocalizedText("en", "ModellingRules"), new LocalizedText("en", "A folder for the modelling rules supported by the server."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2019), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2019), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2019), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2013), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode156() {
        ServerTypeNode node = new ServerTypeNode(this.context, new NodeId(0, 2253), new QualifiedName(0, "Server"), new LocalizedText("en", "Server"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(1));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2254), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2255), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2256), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2267), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2994), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12885), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2268), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2274), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2295), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2296), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11715), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11492), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12873), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12749), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12886), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(85), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2253), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2004), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode157() {
        ServerCapabilitiesTypeNode node = new ServerCapabilitiesTypeNode(this.context, new NodeId(0, 2268), new QualifiedName(0, "ServerCapabilities"), new LocalizedText("en", "ServerCapabilities"), new LocalizedText("en", "Describes capabilities supported by the server."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2268), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2269), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2268), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2271), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2268), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2272), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2268), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2735), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2268), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2736), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2268), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2737), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2268), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3704), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2268), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11702), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2268), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11703), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2268), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12911), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2268), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11704), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2268), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2996), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2268), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2997), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2268), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2013), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2268), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2253), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode158() {
        ServerDiagnosticsTypeNode node = new ServerDiagnosticsTypeNode(this.context, new NodeId(0, 2274), new QualifiedName(0, "ServerDiagnostics"), new LocalizedText("en", "ServerDiagnostics"), new LocalizedText("en", "Reports diagnostics about the server."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2274), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2275), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2274), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2289), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2274), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2290), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2274), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3706), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2274), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2294), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2274), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2020), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2274), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2253), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode159() {
        VendorServerInfoTypeNode node = new VendorServerInfoTypeNode(this.context, new NodeId(0, 2295), new QualifiedName(0, "VendorServerInfo"), new LocalizedText("en", "VendorServerInfo"), new LocalizedText("en", "Server information provided by the vendor."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2295), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2033), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2295), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2253), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode160() {
        ServerRedundancyTypeNode node = new ServerRedundancyTypeNode(this.context, new NodeId(0, 2296), new QualifiedName(0, "ServerRedundancy"), new LocalizedText("en", "ServerRedundancy"), new LocalizedText("en", "Describes the redundancy capabilities of the server."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2296), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3709), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2296), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11312), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2296), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11313), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2296), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11314), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2296), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14415), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2296), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2034), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2296), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2253), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode161() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2341), new QualifiedName(0, "Interpolative"), new LocalizedText("en", "Interpolative"), new LocalizedText("en", "At the beginning of each interval, retrieve the calculated value from the data points on either side of the requested timestamp."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2341), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode162() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2342), new QualifiedName(0, "Average"), new LocalizedText("en", "Average"), new LocalizedText("en", "Retrieve the average value of the data over the interval."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2342), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode163() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2343), new QualifiedName(0, "TimeAverage"), new LocalizedText("en", "TimeAverage"), new LocalizedText("en", "Retrieve the time weighted average data over the interval using Interpolated Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2343), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode164() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2344), new QualifiedName(0, "Total"), new LocalizedText("en", "Total"), new LocalizedText("en", "Retrieve the total (time integral) of the data over the interval using Interpolated Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2344), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode165() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2346), new QualifiedName(0, "Minimum"), new LocalizedText("en", "Minimum"), new LocalizedText("en", "Retrieve the minimum raw value in the interval with the timestamp of the start of the interval."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2346), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode166() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2347), new QualifiedName(0, "Maximum"), new LocalizedText("en", "Maximum"), new LocalizedText("en", "Retrieve the maximum raw value in the interval with the timestamp of the start of the interval."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2347), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode167() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2348), new QualifiedName(0, "MinimumActualTime"), new LocalizedText("en", "MinimumActualTime"), new LocalizedText("en", "Retrieve the minimum value in the interval and the Timestamp of the minimum value."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2348), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode168() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2349), new QualifiedName(0, "MaximumActualTime"), new LocalizedText("en", "MaximumActualTime"), new LocalizedText("en", "Retrieve the maximum value in the interval and the Timestamp of the maximum value."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2349), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode169() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2350), new QualifiedName(0, "Range"), new LocalizedText("en", "Range"), new LocalizedText("en", "Retrieve the difference between the minimum and maximum Value over the interval."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2350), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode170() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2351), new QualifiedName(0, "AnnotationCount"), new LocalizedText("en", "AnnotationCount"), new LocalizedText("en", "Retrieve the number of Annotations in the interval."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2351), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode171() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2352), new QualifiedName(0, "Count"), new LocalizedText("en", "Count"), new LocalizedText("en", "Retrieve the number of raw values over the interval."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2352), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode172() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2355), new QualifiedName(0, "NumberOfTransitions"), new LocalizedText("en", "NumberOfTransitions"), new LocalizedText("en", "Retrieve the number of changes between zero and non-zero that a Boolean or Numeric value experienced in the interval."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2355), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode173() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2357), new QualifiedName(0, "Start"), new LocalizedText("en", "Start"), new LocalizedText("en", "Retrieve the value at the beginning of the interval using Interpolated Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2357), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode174() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2358), new QualifiedName(0, "End"), new LocalizedText("en", "End"), new LocalizedText("en", "Retrieve the value at the end of the interval using Interpolated Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2358), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode175() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2359), new QualifiedName(0, "Delta"), new LocalizedText("en", "Delta"), new LocalizedText("en", "Retrieve the difference between the Start and End value in the interval."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2359), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode176() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2360), new QualifiedName(0, "DurationGood"), new LocalizedText("en", "DurationGood"), new LocalizedText("en", "Retrieve the total duration of time in the interval during which the data is good."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2360), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode177() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2361), new QualifiedName(0, "DurationBad"), new LocalizedText("en", "DurationBad"), new LocalizedText("en", "Retrieve the total duration of time in the interval during which the data is bad."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2361), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode178() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2362), new QualifiedName(0, "PercentGood"), new LocalizedText("en", "PercentGood"), new LocalizedText("en", "Retrieve the percent of data (0 to 100) in the interval which has a good StatusCode."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2362), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode179() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2363), new QualifiedName(0, "PercentBad"), new LocalizedText("en", "PercentBad"), new LocalizedText("en", "Retrieve the percent of data (0 to 100) in the interval which has a bad StatusCode."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2363), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode180() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 2364), new QualifiedName(0, "WorstQuality"), new LocalizedText("en", "WorstQuality"), new LocalizedText("en", "Retrieve the worst StatusCode of data in the interval."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2364), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode181() {
        StateTypeNode node = new StateTypeNode(this.context, new NodeId(0, 2400), new QualifiedName(0, "Ready"), new LocalizedText("en", "Ready"), new LocalizedText("en", "The Program is properly initialized and may be started."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2400), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2401), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2400), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2408), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2400), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2410), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2400), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2414), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2400), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2422), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2400), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2424), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2400), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2307), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2400), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode182() {
        StateTypeNode node = new StateTypeNode(this.context, new NodeId(0, 2402), new QualifiedName(0, "Running"), new LocalizedText("en", "Running"), new LocalizedText("en", "The Program is executing making progress towards completion."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2402), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2403), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2402), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2410), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2402), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2412), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2402), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2414), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2402), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2416), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2402), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2418), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2402), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2307), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2402), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode183() {
        StateTypeNode node = new StateTypeNode(this.context, new NodeId(0, 2404), new QualifiedName(0, "Suspended"), new LocalizedText("en", "Suspended"), new LocalizedText("en", "The Program has been stopped prior to reaching a terminal state but may be resumed."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2404), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2405), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2404), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2416), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2404), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2418), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2404), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2420), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2404), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2422), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2404), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2307), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2404), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode184() {
        StateTypeNode node = new StateTypeNode(this.context, new NodeId(0, 2406), new QualifiedName(0, "Halted"), new LocalizedText("en", "Halted"), new LocalizedText("en", "The Program is in a terminal or failed state, and it cannot be started or resumed without being reset."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2406), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2407), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2406), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2408), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2406), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2412), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2406), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2420), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2406), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2424), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2406), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2307), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2406), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode185() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 2408), new QualifiedName(0, "HaltedToReady"), new LocalizedText("en", "HaltedToReady"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2408), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2409), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2408), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2406), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2408), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2400), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2408), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2430), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2408), new NodeId(0, 54), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2378), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2408), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2408), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode186() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 2410), new QualifiedName(0, "ReadyToRunning"), new LocalizedText("en", "ReadyToRunning"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2410), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2411), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2410), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2400), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2410), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2402), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2410), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2426), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2410), new NodeId(0, 54), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2378), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2410), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2410), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode187() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 2412), new QualifiedName(0, "RunningToHalted"), new LocalizedText("en", "RunningToHalted"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2412), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2413), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2412), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2402), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2412), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2406), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2412), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2429), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2412), new NodeId(0, 54), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2378), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2412), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2412), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode188() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 2414), new QualifiedName(0, "RunningToReady"), new LocalizedText("en", "RunningToReady"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2414), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2415), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2414), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2402), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2414), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2400), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2414), new NodeId(0, 54), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2378), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2414), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2414), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode189() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 2416), new QualifiedName(0, "RunningToSuspended"), new LocalizedText("en", "RunningToSuspended"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2416), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2417), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2416), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2402), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2416), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2404), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2416), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2427), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2416), new NodeId(0, 54), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2378), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2416), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2416), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode190() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 2418), new QualifiedName(0, "SuspendedToRunning"), new LocalizedText("en", "SuspendedToRunning"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2418), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2419), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2418), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2404), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2418), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2402), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2418), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2428), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2418), new NodeId(0, 54), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2378), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2418), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2418), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode191() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 2420), new QualifiedName(0, "SuspendedToHalted"), new LocalizedText("en", "SuspendedToHalted"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2420), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2421), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2420), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2404), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2420), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2406), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2420), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2429), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2420), new NodeId(0, 54), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2378), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2420), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2420), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode192() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 2422), new QualifiedName(0, "SuspendedToReady"), new LocalizedText("en", "SuspendedToReady"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2422), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2423), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2422), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2404), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2422), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2400), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2422), new NodeId(0, 54), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2378), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2422), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2422), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode193() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 2424), new QualifiedName(0, "ReadyToHalted"), new LocalizedText("en", "ReadyToHalted"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2424), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2425), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2424), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2400), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2424), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2406), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2424), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2429), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2424), new NodeId(0, 54), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2378), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2424), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2424), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode194() {
        SessionsDiagnosticsSummaryTypeNode node = new SessionsDiagnosticsSummaryTypeNode(this.context, new NodeId(0, 2744), new QualifiedName(0, "SessionsDiagnosticsSummary"), new LocalizedText("en", "SessionsDiagnosticsSummary"), new LocalizedText("en", "A summary of session level diagnostics."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2744), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3129), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2744), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3130), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2744), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2026), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2744), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2744), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2020), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode195() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 2754), new QualifiedName(0, "AggregateFunctions"), new LocalizedText("en", "AggregateFunctions"), new LocalizedText("en", "A folder for the real time aggregates supported by the server."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2754), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2754), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2754), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2013), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode196() {
        NamespaceMetadataTypeNode node = new NamespaceMetadataTypeNode(this.context, new NodeId(0, 15182), new QualifiedName(0, "http://opcfoundation.org/UA/"), new LocalizedText("en", "http://opcfoundation.org/UA/"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 15182), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(15183), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 15182), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(15184), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 15182), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(15185), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 15182), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(15186), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 15182), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(15187), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 15182), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(15188), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 15182), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(15189), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 15182), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11616), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 15182), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11715), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode197() {
        StateTypeNode node = new StateTypeNode(this.context, new NodeId(0, 2930), new QualifiedName(0, "Unshelved"), new LocalizedText("en", "Unshelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2930), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(6098), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2930), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2935), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2930), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2936), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2930), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2940), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2930), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2943), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2930), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2307), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2930), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2929), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode198() {
        StateTypeNode node = new StateTypeNode(this.context, new NodeId(0, 2932), new QualifiedName(0, "TimedShelved"), new LocalizedText("en", "TimedShelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2932), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(6100), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2932), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2935), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2932), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2940), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2932), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2942), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2932), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2945), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2932), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2307), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2932), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2929), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode199() {
        StateTypeNode node = new StateTypeNode(this.context, new NodeId(0, 2933), new QualifiedName(0, "OneShotShelved"), new LocalizedText("en", "OneShotShelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2933), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(6101), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2933), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2936), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2933), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2942), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2933), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2943), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2933), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2945), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 2933), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2307), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2933), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2929), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode200() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 2935), new QualifiedName(0, "UnshelvedToTimedShelved"), new LocalizedText("en", "UnshelvedToTimedShelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2935), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11322), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2935), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2930), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2935), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2932), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2935), new NodeId(0, 54), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2915), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2935), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2949), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2935), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2935), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2929), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode201() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 2936), new QualifiedName(0, "UnshelvedToOneShotShelved"), new LocalizedText("en", "UnshelvedToOneShotShelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2936), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11323), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2936), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2930), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2936), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2933), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2936), new NodeId(0, 54), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2915), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2936), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2948), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2936), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2936), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2929), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode202() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 2940), new QualifiedName(0, "TimedShelvedToUnshelved"), new LocalizedText("en", "TimedShelvedToUnshelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2940), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11324), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2940), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2932), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2940), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2930), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2940), new NodeId(0, 54), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2915), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2940), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2947), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2940), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2940), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2929), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode203() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 2942), new QualifiedName(0, "TimedShelvedToOneShotShelved"), new LocalizedText("en", "TimedShelvedToOneShotShelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2942), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11325), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2942), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2932), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2942), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2933), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2942), new NodeId(0, 54), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2915), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2942), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2948), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2942), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2942), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2929), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode204() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 2943), new QualifiedName(0, "OneShotShelvedToUnshelved"), new LocalizedText("en", "OneShotShelvedToUnshelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2943), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11326), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2943), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2933), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2943), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2930), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2943), new NodeId(0, 54), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2915), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2943), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2947), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2943), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2943), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2929), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode205() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, new NodeId(0, 2945), new QualifiedName(0, "OneShotShelvedToTimedShelved"), new LocalizedText("en", "OneShotShelvedToTimedShelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2945), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11327), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2945), new NodeId(0, 51), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2933), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2945), new NodeId(0, 52), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2932), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2945), new NodeId(0, 54), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2915), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2945), new NodeId(0, 53), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2949), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2945), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2310), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2945), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2929), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode206() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 11172), new QualifiedName(0, "AggregateFunctions"), new LocalizedText("en", "AggregateFunctions"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11172), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11172), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11172), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2330), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode207() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 2996), new QualifiedName(0, "ModellingRules"), new LocalizedText("en", "ModellingRules"), new LocalizedText("en", "A folder for the modelling rules supported by the server."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2996), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2996), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2268), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode208() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 2997), new QualifiedName(0, "AggregateFunctions"), new LocalizedText("en", "AggregateFunctions"), new LocalizedText("en", "A folder for the real time aggregates supported by the server."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 2997), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2997), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2268), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode209() {
        HistoryServerCapabilitiesTypeNode node = new HistoryServerCapabilitiesTypeNode(this.context, new NodeId(0, 11192), new QualifiedName(0, "HistoryServerCapabilities"), new LocalizedText("en", "HistoryServerCapabilities"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11193), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11242), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11273), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11274), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11196), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11197), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11198), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11199), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11200), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11281), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11282), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11283), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11502), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11275), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11201), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2268), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 11192), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2330), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode210() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 11201), new QualifiedName(0, "AggregateFunctions"), new LocalizedText("en", "AggregateFunctions"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11201), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11201), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11192), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode211() {
        HistoricalDataConfigurationTypeNode node = new HistoricalDataConfigurationTypeNode(this.context, new NodeId(0, 11202), new QualifiedName(0, "HA Configuration"), new LocalizedText("en", "HA Configuration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11202), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11203), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11202), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11208), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11202), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2318), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode212() {
        AggregateConfigurationTypeNode node = new AggregateConfigurationTypeNode(this.context, new NodeId(0, 11203), new QualifiedName(0, "AggregateConfiguration"), new LocalizedText("en", "AggregateConfiguration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11203), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11204), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11203), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11205), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11203), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11206), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11203), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11207), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11203), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11187), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11203), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11202), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode213() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 3048), new QualifiedName(0, "EventTypes"), new LocalizedText("en", "EventTypes"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 3048), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(86), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 3048), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2041), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3048), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode214() {
        AggregateConfigurationTypeNode node = new AggregateConfigurationTypeNode(this.context, new NodeId(0, 3059), new QualifiedName(0, "AggregateConfiguration"), new LocalizedText("en", "AggregateConfiguration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 3059), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11168), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3059), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11169), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3059), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11170), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3059), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11171), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3059), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11187), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3059), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3059), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2318), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode215() {
        BaseObjectTypeNode node = new BaseObjectTypeNode(this.context, new NodeId(0, 3062), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), new LocalizedText("en", "The default binary encoding for a data type."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 3062), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode216() {
        BaseObjectTypeNode node = new BaseObjectTypeNode(this.context, new NodeId(0, 3063), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), new LocalizedText("en", "The default XML encoding for a data type."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 3063), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode217() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 3093), new QualifiedName(0, "ModellingRules"), new LocalizedText("en", "ModellingRules"), new LocalizedText("en", "A folder for the modelling rules supported by the server."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 3093), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3093), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3093), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2009), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode218() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11285), new QualifiedName(0, "TimeAverage2"), new LocalizedText("en", "TimeAverage2"), new LocalizedText("en", "Retrieve the time weighted average data over the interval using Simple Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11285), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode219() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 3094), new QualifiedName(0, "AggregateFunctions"), new LocalizedText("en", "AggregateFunctions"), new LocalizedText("en", "A folder for the real time aggregates supported by the server."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 3094), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3094), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3094), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2009), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode220() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11286), new QualifiedName(0, "Minimum2"), new LocalizedText("en", "Minimum2"), new LocalizedText("en", "Retrieve the minimum value in the interval including the Simple Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11286), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode221() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11287), new QualifiedName(0, "Maximum2"), new LocalizedText("en", "Maximum2"), new LocalizedText("en", "Retrieve the maximum value in the interval including the Simple Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11287), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode222() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11288), new QualifiedName(0, "Range2"), new LocalizedText("en", "Range2"), new LocalizedText("en", "Retrieve the difference between the Minimum2 and Maximum2 value over the interval."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11288), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode223() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11292), new QualifiedName(0, "WorstQuality2"), new LocalizedText("en", "WorstQuality2"), new LocalizedText("en", "Retrieve the worst StatusCode of data in the interval including the Simple Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11292), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode224() {
        SessionsDiagnosticsSummaryTypeNode node = new SessionsDiagnosticsSummaryTypeNode(this.context, new NodeId(0, 3111), new QualifiedName(0, "SessionsDiagnosticsSummary"), new LocalizedText("en", "SessionsDiagnosticsSummary"), new LocalizedText("en", "A summary of session level diagnostics."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 3111), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3112), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3111), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3113), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3111), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2026), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3111), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(78), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3111), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2010), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode225() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11304), new QualifiedName(0, "Total2"), new LocalizedText("en", "Total2"), new LocalizedText("en", "Retrieve the total (time integral) of the data over the interval using Simple Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11304), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode226() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11305), new QualifiedName(0, "MinimumActualTime2"), new LocalizedText("en", "MinimumActualTime2"), new LocalizedText("en", "Retrieve the minimum value with the actual timestamp including the Simple Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11305), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode227() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11306), new QualifiedName(0, "MaximumActualTime2"), new LocalizedText("en", "MaximumActualTime2"), new LocalizedText("en", "Retrieve the maximum value with the actual timestamp including the Simple Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11306), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode228() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11307), new QualifiedName(0, "DurationInStateZero"), new LocalizedText("en", "DurationInStateZero"), new LocalizedText("en", "Retrieve the time a Boolean or numeric was in a zero state using Simple Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11307), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode229() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11308), new QualifiedName(0, "DurationInStateNonZero"), new LocalizedText("en", "DurationInStateNonZero"), new LocalizedText("en", "Retrieve the time a Boolean or numeric was in a non-zero state using Simple Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11308), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode230() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11426), new QualifiedName(0, "StandardDeviationSample"), new LocalizedText("en", "StandardDeviationSample"), new LocalizedText("en", "Retrieve the standard deviation for the interval for a sample of the population (n-1)."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11426), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode231() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11427), new QualifiedName(0, "StandardDeviationPopulation"), new LocalizedText("en", "StandardDeviationPopulation"), new LocalizedText("en", "Retrieve the standard deviation for the interval for a complete population (n) which includes Simple Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11427), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode232() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11428), new QualifiedName(0, "VarianceSample"), new LocalizedText("en", "VarianceSample"), new LocalizedText("en", "Retrieve the variance for the interval as calculated by the StandardDeviationSample."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11428), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode233() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11429), new QualifiedName(0, "VariancePopulation"), new LocalizedText("en", "VariancePopulation"), new LocalizedText("en", "Retrieve the variance for the interval as calculated by the StandardDeviationPopulation which includes Simple Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11429), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode234() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11505), new QualifiedName(0, "StartBound"), new LocalizedText("en", "StartBound"), new LocalizedText("en", "Retrieve the value at the beginning of the interval using Simple Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11505), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode235() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11506), new QualifiedName(0, "EndBound"), new LocalizedText("en", "EndBound"), new LocalizedText("en", "Retrieve the value at the end of the interval using Simple Bounding Values."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11506), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode236() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, new NodeId(0, 11507), new QualifiedName(0, "DeltaBounds"), new LocalizedText("en", "DeltaBounds"), new LocalizedText("en", "Retrieve the difference between the StartBound and EndBound value in the interval."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11507), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2340), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode237() {
        ModellingRuleTypeNode node = new ModellingRuleTypeNode(this.context, new NodeId(0, 11508), new QualifiedName(0, "OptionalPlaceholder"), new LocalizedText("en", "OptionalPlaceholder"), new LocalizedText("en", "Specifies that zero or more instances with the attributes and references of the instance declaration may appear when a type is instantiated."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11508), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11509), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11508), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(77), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode238() {
        ModellingRuleTypeNode node = new ModellingRuleTypeNode(this.context, new NodeId(0, 11510), new QualifiedName(0, "MandatoryPlaceholder"), new LocalizedText("en", "MandatoryPlaceholder"), new LocalizedText("en", "Specifies that one or more instances with the attributes and references of the instance declaration must appear when a type is instantiated."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11510), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11511), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11510), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(77), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode239() {
        NamespacesTypeNode node = new NamespacesTypeNode(this.context, new NodeId(0, 11527), new QualifiedName(0, "Namespaces"), new LocalizedText("en", "Namespaces"), new LocalizedText("en", "Describes the namespaces supported by the server."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11527), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11645), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11527), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11527), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2004), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode240() {
        OperationLimitsTypeNode node = new OperationLimitsTypeNode(this.context, new NodeId(0, 11551), new QualifiedName(0, "OperationLimits"), new LocalizedText("en", "OperationLimits"), new LocalizedText("en", "Defines the limits supported by the server for different operations."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11551), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11564), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11551), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11551), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2013), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode241() {
        AddressSpaceFileTypeNode node = new AddressSpaceFileTypeNode(this.context, new NodeId(0, 11624), new QualifiedName(0, "NamespaceFile"), new LocalizedText("en", "NamespaceFile"), new LocalizedText("en", "A file containing the nodes of the namespace."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11624), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11625), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11624), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12690), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11624), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12691), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11624), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11628), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11624), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11629), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11624), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11632), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11624), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11634), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11624), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11637), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11624), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11639), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11624), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11642), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11624), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11595), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11624), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11624), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11616), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode242() {
        NamespaceMetadataTypeNode node = new NamespaceMetadataTypeNode(this.context, new NodeId(0, 11646), new QualifiedName(0, "<NamespaceIdentifier>"), new LocalizedText("en", "<NamespaceIdentifier>"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11646), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11647), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11646), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11648), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11646), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11649), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11646), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11650), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11646), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11651), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11646), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11652), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11646), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11653), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11646), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11616), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11646), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11508), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11646), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11645), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode243() {
        AddressSpaceFileTypeNode node = new AddressSpaceFileTypeNode(this.context, new NodeId(0, 11675), new QualifiedName(0, "AddressSpaceFile"), new LocalizedText("en", "AddressSpaceFile"), new LocalizedText("en", "A file containing the nodes of the namespace."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11675), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11676), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11675), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12694), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11675), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12695), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11675), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11679), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11675), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11680), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11675), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11683), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11675), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11685), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11675), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11688), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11675), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11690), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11675), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11693), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11675), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11595), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11675), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11675), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11645), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode244() {
        OperationLimitsTypeNode node = new OperationLimitsTypeNode(this.context, new NodeId(0, 11704), new QualifiedName(0, "OperationLimits"), new LocalizedText("en", "OperationLimits"), new LocalizedText("en", "Defines the limits supported by the server for different operations."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11704), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11705), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11704), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12165), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11704), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12166), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11704), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11707), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11704), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12167), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11704), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12168), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11704), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11709), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11704), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11710), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11704), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11711), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11704), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11712), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11704), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11713), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11704), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11714), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11704), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11564), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11704), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2268), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode245() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 7616), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 7616), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7594), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 7616), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8291), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 7616), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode246() {
        NamespacesTypeNode node = new NamespacesTypeNode(this.context, new NodeId(0, 11715), new QualifiedName(0, "Namespaces"), new LocalizedText("en", "Namespaces"), new LocalizedText("en", "Describes the namespaces supported by the server."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11715), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(15182), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11715), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11645), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11715), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2253), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode247() {
        FolderTypeNode node = new FolderTypeNode(this.context, new NodeId(0, 11876), new QualifiedName(0, "AggregateFunctions"), new LocalizedText("en", "AggregateFunctions"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11876), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11876), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11876), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2318), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode248() {
        SessionsDiagnosticsSummaryTypeNode node = new SessionsDiagnosticsSummaryTypeNode(this.context, new NodeId(0, 3706), new QualifiedName(0, "SessionsDiagnosticsSummary"), new LocalizedText("en", "SessionsDiagnosticsSummary"), new LocalizedText("en", "A summary of session level diagnostics."), UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 3706), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3707), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3706), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3708), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3706), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2026), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3706), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2274), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode249() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 11949), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11949), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11943), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 11949), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11951), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11949), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode250() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 11950), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11950), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11944), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 11950), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11954), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11950), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode251() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 11957), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11957), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11943), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 11957), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11959), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11957), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode252() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 11958), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 11958), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11944), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 11958), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11962), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11958), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode253() {
        BaseObjectTypeNode node = new BaseObjectTypeNode(this.context, new NodeId(0, 3850), new QualifiedName(0, "FinalResultData"), new LocalizedText("en", "FinalResultData"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 3850), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3850), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(80), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3850), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2391), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode254() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12081), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12081), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12079), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12081), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12083), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12081), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode255() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12082), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12082), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12080), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12082), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12086), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12082), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode256() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12089), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12089), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12079), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12089), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12091), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12089), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode257() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12090), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12090), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12080), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12090), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12094), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12090), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode258() {
        SessionDiagnosticsObjectTypeNode node = new SessionDiagnosticsObjectTypeNode(this.context, new NodeId(0, 12097), new QualifiedName(0, "<ClientName>"), new LocalizedText("en", "<ClientName>"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12097), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12098), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12097), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12142), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12097), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12152), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12097), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2029), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12097), new NodeId(0, 37), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11508), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12097), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2026), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode259() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12173), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12173), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12171), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12173), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12175), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12173), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode260() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12174), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12174), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12172), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12174), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12178), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12174), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode261() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12181), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12181), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12171), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12181), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12183), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12181), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode262() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12182), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12182), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12172), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12182), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12186), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12182), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode263() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12195), new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12195), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12189), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12195), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12201), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12195), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode264() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, new NodeId(0, 12207), new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(new NodeId(0, 12207), new NodeId(0, 38), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12189), UInteger.valueOf(0)), false));
        node.addReference(new Reference(new NodeId(0, 12207), new NodeId(0, 39), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12213), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12207), new NodeId(0, 40), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(76), UInteger.valueOf(0)), true));
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
        loadNode144();
        loadNode145();
        loadNode146();
        loadNode147();
        loadNode148();
        loadNode149();
        loadNode150();
        loadNode151();
        loadNode152();
        loadNode153();
        loadNode154();
        loadNode155();
        loadNode156();
        loadNode157();
        loadNode158();
        loadNode159();
        loadNode160();
        loadNode161();
        loadNode162();
        loadNode163();
        loadNode164();
        loadNode165();
        loadNode166();
        loadNode167();
        loadNode168();
        loadNode169();
        loadNode170();
        loadNode171();
        loadNode172();
        loadNode173();
        loadNode174();
        loadNode175();
        loadNode176();
        loadNode177();
        loadNode178();
        loadNode179();
        loadNode180();
        loadNode181();
        loadNode182();
        loadNode183();
        loadNode184();
        loadNode185();
        loadNode186();
        loadNode187();
        loadNode188();
        loadNode189();
        loadNode190();
        loadNode191();
        loadNode192();
        loadNode193();
        loadNode194();
        loadNode195();
        loadNode196();
        loadNode197();
        loadNode198();
        loadNode199();
        loadNode200();
        loadNode201();
        loadNode202();
        loadNode203();
        loadNode204();
        loadNode205();
        loadNode206();
        loadNode207();
        loadNode208();
        loadNode209();
        loadNode210();
        loadNode211();
        loadNode212();
        loadNode213();
        loadNode214();
        loadNode215();
        loadNode216();
        loadNode217();
        loadNode218();
        loadNode219();
        loadNode220();
        loadNode221();
        loadNode222();
        loadNode223();
        loadNode224();
        loadNode225();
        loadNode226();
        loadNode227();
        loadNode228();
        loadNode229();
        loadNode230();
        loadNode231();
        loadNode232();
        loadNode233();
        loadNode234();
        loadNode235();
        loadNode236();
        loadNode237();
        loadNode238();
        loadNode239();
        loadNode240();
        loadNode241();
        loadNode242();
        loadNode243();
        loadNode244();
        loadNode245();
        loadNode246();
        loadNode247();
        loadNode248();
        loadNode249();
        loadNode250();
        loadNode251();
        loadNode252();
        loadNode253();
        loadNode254();
        loadNode255();
        loadNode256();
        loadNode257();
        loadNode258();
        loadNode259();
        loadNode260();
        loadNode261();
        loadNode262();
        loadNode263();
        loadNode264();
    }
}
