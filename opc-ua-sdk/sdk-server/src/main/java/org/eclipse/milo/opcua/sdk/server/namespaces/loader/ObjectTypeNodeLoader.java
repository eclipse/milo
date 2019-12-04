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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

class ObjectTypeNodeLoader {
    private final UaNodeContext context;

    private final NodeManager<UaNode> nodeManager;

    ObjectTypeNodeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager) {
        this.context = context;
        this.nodeManager = nodeManager;
    }

    private void loadNode0() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 58), new QualifiedName(0, "BaseObjectType"), new LocalizedText("en", "BaseObjectType"), new LocalizedText("en", "The base type for all object nodes."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        this.nodeManager.addNode(node);
    }

    private void loadNode1() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 61), new QualifiedName(0, "FolderType"), new LocalizedText("en", "FolderType"), new LocalizedText("en", "The type for objects that organize other nodes."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 61), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode2() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 75), new QualifiedName(0, "DataTypeSystemType"), new LocalizedText("en", "DataTypeSystemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 75), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode3() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 76), new QualifiedName(0, "DataTypeEncodingType"), new LocalizedText("en", "DataTypeEncodingType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 76), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode4() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 77), new QualifiedName(0, "ModellingRuleType"), new LocalizedText("en", "ModellingRuleType"), new LocalizedText("en", "The type for an object that describes how an instance declaration is used when a type is instantiated."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 77), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(111), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 77), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode5() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 12522), new QualifiedName(0, "TrustListType"), new LocalizedText("en", "TrustListType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12522), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12542), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12522), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12543), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12522), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12546), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12522), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12548), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12522), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12550), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12522), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11575), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode6() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 12555), new QualifiedName(0, "CertificateGroupType"), new LocalizedText("en", "CertificateGroupType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12555), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13599), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12555), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13631), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12555), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode7() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 12556), new QualifiedName(0, "CertificateType"), new LocalizedText("en", "CertificateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 12556), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode8() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 12557), new QualifiedName(0, "ApplicationCertificateType"), new LocalizedText("en", "ApplicationCertificateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 12557), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12556), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode9() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 12558), new QualifiedName(0, "HttpsCertificateType"), new LocalizedText("en", "HttpsCertificateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12558), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12556), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode10() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 12559), new QualifiedName(0, "RsaMinApplicationCertificateType"), new LocalizedText("en", "RsaMinApplicationCertificateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12559), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12557), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode11() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 12560), new QualifiedName(0, "RsaSha256ApplicationCertificateType"), new LocalizedText("en", "RsaSha256ApplicationCertificateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12560), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12557), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode12() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 12561), new QualifiedName(0, "TrustListUpdatedAuditEventType"), new LocalizedText("en", "TrustListUpdatedAuditEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 12561), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2127), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode13() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 12581), new QualifiedName(0, "ServerConfigurationType"), new LocalizedText("en", "ServerConfigurationType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12581), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13950), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12581), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12708), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12581), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12583), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12581), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12584), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12581), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12585), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12581), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12616), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12581), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12734), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12581), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12731), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12581), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12775), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12581), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode14() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 12620), new QualifiedName(0, "CertificateUpdatedAuditEventType"), new LocalizedText("en", "CertificateUpdatedAuditEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 12620), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13735), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12620), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13736), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12620), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2127), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode15() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 8927), new QualifiedName(0, "AuditConditionRespondEventType"), new LocalizedText("en", "AuditConditionRespondEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 8927), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11852), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 8927), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2790), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode16() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 8944), new QualifiedName(0, "AuditConditionAcknowledgeEventType"), new LocalizedText("en", "AuditConditionAcknowledgeEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 8944), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8945), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 8944), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11853), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 8944), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2790), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode17() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 8961), new QualifiedName(0, "AuditConditionConfirmEventType"), new LocalizedText("en", "AuditConditionConfirmEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 8961), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(8962), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 8961), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11854), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 8961), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2790), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode18() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 13225), new QualifiedName(0, "CertificateExpirationAlarmType"), new LocalizedText("en", "CertificateExpirationAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 13225), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13325), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13225), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(14900), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13225), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13326), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13225), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13327), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13225), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11753), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode19() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 13353), new QualifiedName(0, "FileDirectoryType"), new LocalizedText("en", "FileDirectoryType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 13353), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13354), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13353), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13366), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13353), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13387), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13353), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13390), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13353), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13393), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13353), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13395), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13353), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode20() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 9318), new QualifiedName(0, "ExclusiveLimitStateMachineType"), new LocalizedText("en", "ExclusiveLimitStateMachineType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 9318), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9329), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9318), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9331), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9318), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9333), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9318), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9335), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9318), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9337), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9318), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9338), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9318), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9339), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9318), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9340), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9318), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2771), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode21() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 9341), new QualifiedName(0, "ExclusiveLimitAlarmType"), new LocalizedText("en", "ExclusiveLimitAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 9341), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9398), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9341), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9455), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9341), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2955), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode22() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 9482), new QualifiedName(0, "ExclusiveLevelAlarmType"), new LocalizedText("en", "ExclusiveLevelAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 9482), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9341), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode23() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 9623), new QualifiedName(0, "ExclusiveRateOfChangeAlarmType"), new LocalizedText("en", "ExclusiveRateOfChangeAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 9623), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9341), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode24() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 13813), new QualifiedName(0, "CertificateGroupFolderType"), new LocalizedText("en", "CertificateGroupFolderType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 13813), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13814), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13813), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13848), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13813), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13882), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13813), new NodeId(0, 35), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13916), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 13813), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode25() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 9764), new QualifiedName(0, "ExclusiveDeviationAlarmType"), new LocalizedText("en", "ExclusiveDeviationAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 9764), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9905), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9764), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9341), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode26() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 9906), new QualifiedName(0, "NonExclusiveLimitAlarmType"), new LocalizedText("en", "NonExclusiveLimitAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 9906), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9963), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9906), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(10020), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9906), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(10029), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9906), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(10038), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9906), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(10047), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 9906), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2955), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode27() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 10060), new QualifiedName(0, "NonExclusiveLevelAlarmType"), new LocalizedText("en", "NonExclusiveLevelAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 10060), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9906), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode28() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2004), new QualifiedName(0, "ServerType"), new LocalizedText("en", "ServerType"), new LocalizedText("en", "Specifies the current status and capabilities of the server."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2004), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2005), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2004), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2006), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2004), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2007), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2004), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2008), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2004), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2742), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2004), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12882), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2004), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2009), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2004), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2010), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2004), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2011), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2004), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2012), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2004), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11527), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2004), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11489), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2004), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12871), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2004), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12746), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2004), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12883), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2004), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode29() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2013), new QualifiedName(0, "ServerCapabilitiesType"), new LocalizedText("en", "ServerCapabilitiesType"), new LocalizedText("en", "Describes the capabilities supported by the server."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2013), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2014), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2013), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2016), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2013), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2017), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2013), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2732), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2013), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2733), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2013), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2734), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2013), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3049), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2013), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11549), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2013), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11550), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2013), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12910), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2013), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11551), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2013), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2019), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2013), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2754), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2013), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11562), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2013), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode30() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2020), new QualifiedName(0, "ServerDiagnosticsType"), new LocalizedText("en", "ServerDiagnosticsType"), new LocalizedText("en", "The diagnostics information for a server."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2020), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2021), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2020), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2022), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2020), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2023), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2020), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2744), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2020), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2025), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2020), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode31() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 10214), new QualifiedName(0, "NonExclusiveRateOfChangeAlarmType"), new LocalizedText("en", "NonExclusiveRateOfChangeAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 10214), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9906), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode32() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2026), new QualifiedName(0, "SessionsDiagnosticsSummaryType"), new LocalizedText("en", "SessionsDiagnosticsSummaryType"), new LocalizedText("en", "Provides a summary of session level diagnostics."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2026), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2027), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2026), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2028), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2026), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12097), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2026), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode33() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2029), new QualifiedName(0, "SessionDiagnosticsObjectType"), new LocalizedText("en", "SessionDiagnosticsObjectType"), new LocalizedText("en", "A container for session level diagnostics information."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2029), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2030), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2029), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2031), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2029), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2032), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2029), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode34() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2033), new QualifiedName(0, "VendorServerInfoType"), new LocalizedText("en", "VendorServerInfoType"), new LocalizedText("en", "A base type for vendor specific server information."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2033), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode35() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2034), new QualifiedName(0, "ServerRedundancyType"), new LocalizedText("en", "ServerRedundancyType"), new LocalizedText("en", "A base type for an object that describe how a server supports redundancy."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2034), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2035), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2034), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode36() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2036), new QualifiedName(0, "TransparentRedundancyType"), new LocalizedText("en", "TransparentRedundancyType"), new LocalizedText("en", "Identifies the capabilties of server that supports transparent redundancy."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2036), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2037), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2036), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2038), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2036), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2034), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode37() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2039), new QualifiedName(0, "NonTransparentRedundancyType"), new LocalizedText("en", "NonTransparentRedundancyType"), new LocalizedText("en", "Identifies the capabilties of server that supports non-transparent redundancy."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2039), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2040), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2039), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2034), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode38() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2041), new QualifiedName(0, "BaseEventType"), new LocalizedText("en", "BaseEventType"), new LocalizedText("en", "The base type for all events."), UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2041), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2042), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2041), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2043), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2041), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2044), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2041), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2045), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2041), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2046), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2041), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2047), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2041), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3190), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2041), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2050), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2041), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2051), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2041), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode39() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2052), new QualifiedName(0, "AuditEventType"), new LocalizedText("en", "AuditEventType"), new LocalizedText("en", "A base type for events used to track client initiated changes to the server state."), UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2052), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2053), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2052), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2054), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2052), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2055), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2052), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2056), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2052), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2057), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2052), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2041), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode40() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2058), new QualifiedName(0, "AuditSecurityEventType"), new LocalizedText("en", "AuditSecurityEventType"), new LocalizedText("en", "A base type for events used to track security related changes."), UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2058), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2052), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode41() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2059), new QualifiedName(0, "AuditChannelEventType"), new LocalizedText("en", "AuditChannelEventType"), new LocalizedText("en", "A base type for events used to track related changes to a secure channel."), UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2059), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2745), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2059), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2058), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode42() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2060), new QualifiedName(0, "AuditOpenSecureChannelEventType"), new LocalizedText("en", "AuditOpenSecureChannelEventType"), new LocalizedText("en", "An event that is raised when a secure channel is opened."), UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2060), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2061), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2060), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2746), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2060), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2062), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2060), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2063), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2060), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2065), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2060), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2066), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2060), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2059), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode43() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2069), new QualifiedName(0, "AuditSessionEventType"), new LocalizedText("en", "AuditSessionEventType"), new LocalizedText("en", "A base type for events used to track related changes to a session."), UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2069), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2070), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2069), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2058), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode44() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2071), new QualifiedName(0, "AuditCreateSessionEventType"), new LocalizedText("en", "AuditCreateSessionEventType"), new LocalizedText("en", "An event that is raised when a session is created."), UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2071), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2072), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2071), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2073), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2071), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2747), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2071), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2074), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2071), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2069), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode45() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2075), new QualifiedName(0, "AuditActivateSessionEventType"), new LocalizedText("en", "AuditActivateSessionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2075), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2076), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2075), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2077), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2075), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11485), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2075), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2069), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode46() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2078), new QualifiedName(0, "AuditCancelEventType"), new LocalizedText("en", "AuditCancelEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2078), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2079), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2078), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2069), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode47() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2080), new QualifiedName(0, "AuditCertificateEventType"), new LocalizedText("en", "AuditCertificateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2080), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2081), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2080), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2058), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode48() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2082), new QualifiedName(0, "AuditCertificateDataMismatchEventType"), new LocalizedText("en", "AuditCertificateDataMismatchEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2082), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2083), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2082), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2084), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2082), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2080), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode49() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2085), new QualifiedName(0, "AuditCertificateExpiredEventType"), new LocalizedText("en", "AuditCertificateExpiredEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2085), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2080), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode50() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2086), new QualifiedName(0, "AuditCertificateInvalidEventType"), new LocalizedText("en", "AuditCertificateInvalidEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2086), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2080), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode51() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2087), new QualifiedName(0, "AuditCertificateUntrustedEventType"), new LocalizedText("en", "AuditCertificateUntrustedEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2087), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2080), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode52() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2088), new QualifiedName(0, "AuditCertificateRevokedEventType"), new LocalizedText("en", "AuditCertificateRevokedEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2088), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2080), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode53() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2089), new QualifiedName(0, "AuditCertificateMismatchEventType"), new LocalizedText("en", "AuditCertificateMismatchEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2089), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2080), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode54() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2090), new QualifiedName(0, "AuditNodeManagementEventType"), new LocalizedText("en", "AuditNodeManagementEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2090), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2052), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode55() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2091), new QualifiedName(0, "AuditAddNodesEventType"), new LocalizedText("en", "AuditAddNodesEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2091), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2092), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2091), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2090), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode56() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2093), new QualifiedName(0, "AuditDeleteNodesEventType"), new LocalizedText("en", "AuditDeleteNodesEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2093), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2094), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2093), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2090), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode57() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2095), new QualifiedName(0, "AuditAddReferencesEventType"), new LocalizedText("en", "AuditAddReferencesEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2095), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2096), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2095), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2090), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode58() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2097), new QualifiedName(0, "AuditDeleteReferencesEventType"), new LocalizedText("en", "AuditDeleteReferencesEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2097), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2098), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2097), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2090), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode59() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2099), new QualifiedName(0, "AuditUpdateEventType"), new LocalizedText("en", "AuditUpdateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2099), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2052), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode60() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2100), new QualifiedName(0, "AuditWriteUpdateEventType"), new LocalizedText("en", "AuditWriteUpdateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2100), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2750), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2100), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2101), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2100), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2102), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2100), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2103), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2100), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2099), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode61() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2104), new QualifiedName(0, "AuditHistoryUpdateEventType"), new LocalizedText("en", "AuditHistoryUpdateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2104), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2751), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2104), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2099), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode62() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2127), new QualifiedName(0, "AuditUpdateMethodEventType"), new LocalizedText("en", "AuditUpdateMethodEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2127), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2128), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2127), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2129), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2127), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2052), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode63() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2130), new QualifiedName(0, "SystemEventType"), new LocalizedText("en", "SystemEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2130), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2041), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode64() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2131), new QualifiedName(0, "DeviceFailureEventType"), new LocalizedText("en", "DeviceFailureEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2131), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2130), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode65() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2132), new QualifiedName(0, "BaseModelChangeEventType"), new LocalizedText("en", "BaseModelChangeEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2132), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2041), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode66() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2133), new QualifiedName(0, "GeneralModelChangeEventType"), new LocalizedText("en", "GeneralModelChangeEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2133), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2134), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2133), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2132), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode67() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 10368), new QualifiedName(0, "NonExclusiveDeviationAlarmType"), new LocalizedText("en", "NonExclusiveDeviationAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 10368), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(10522), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 10368), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9906), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode68() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2299), new QualifiedName(0, "StateMachineType"), new LocalizedText("en", "StateMachineType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2299), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2769), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2299), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2770), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2299), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode69() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2307), new QualifiedName(0, "StateType"), new LocalizedText("en", "StateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2307), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2308), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2307), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode70() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2309), new QualifiedName(0, "InitialStateType"), new LocalizedText("en", "InitialStateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2309), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2307), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode71() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2310), new QualifiedName(0, "TransitionType"), new LocalizedText("en", "TransitionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2310), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2312), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2310), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode72() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2311), new QualifiedName(0, "TransitionEventType"), new LocalizedText("en", "TransitionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2311), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2774), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2311), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2775), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2311), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2776), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2311), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2041), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode73() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2315), new QualifiedName(0, "AuditUpdateStateEventType"), new LocalizedText("en", "AuditUpdateStateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2315), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2777), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2315), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2778), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2315), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2127), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode74() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2318), new QualifiedName(0, "HistoricalDataConfigurationType"), new LocalizedText("en", "HistoricalDataConfigurationType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2318), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3059), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2318), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11876), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2318), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2323), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2318), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2324), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2318), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2325), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2318), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2326), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2318), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2327), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2318), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2328), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2318), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11499), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2318), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11500), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2318), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode75() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2330), new QualifiedName(0, "HistoryServerCapabilitiesType"), new LocalizedText("en", "HistoryServerCapabilitiesType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2330), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2331), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2330), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2332), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2330), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11268), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2330), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11269), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2330), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2334), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2330), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2335), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2330), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2336), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2330), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2337), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2330), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2338), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2330), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11278), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2330), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11279), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2330), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11280), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2330), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11501), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2330), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11270), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2330), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11172), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2330), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode76() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 10523), new QualifiedName(0, "DiscreteAlarmType"), new LocalizedText("en", "DiscreteAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 10523), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2915), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode77() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2340), new QualifiedName(0, "AggregateFunctionType"), new LocalizedText("en", "AggregateFunctionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2340), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode78() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2378), new QualifiedName(0, "ProgramTransitionEventType"), new LocalizedText("en", "ProgramTransitionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2378), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2379), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2378), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2311), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode79() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2391), new QualifiedName(0, "ProgramStateMachineType"), new LocalizedText("en", "ProgramStateMachineType"), new LocalizedText("en", "A state machine for a program."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3830), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3835), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2392), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2393), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2394), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2395), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2396), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2397), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2398), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2399), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3850), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2400), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2402), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2404), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2406), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2408), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2410), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2412), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2414), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2416), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2418), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2420), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2422), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2424), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2426), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2427), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2428), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2429), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2430), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2391), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2771), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode80() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 10637), new QualifiedName(0, "OffNormalAlarmType"), new LocalizedText("en", "OffNormalAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 10637), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11158), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 10637), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(10523), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode81() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 10751), new QualifiedName(0, "TripAlarmType"), new LocalizedText("en", "TripAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 10751), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(10637), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode82() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2738), new QualifiedName(0, "SemanticChangeEventType"), new LocalizedText("en", "SemanticChangeEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2738), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2739), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2738), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2132), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode83() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2748), new QualifiedName(0, "AuditUrlMismatchEventType"), new LocalizedText("en", "AuditUrlMismatchEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2748), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2749), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2748), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2071), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode84() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2771), new QualifiedName(0, "FiniteStateMachineType"), new LocalizedText("en", "FiniteStateMachineType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2771), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2772), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2771), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2773), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2771), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2299), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode85() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2782), new QualifiedName(0, "ConditionType"), new LocalizedText("en", "ConditionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2782), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11112), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2782), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11113), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2782), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9009), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2782), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9010), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2782), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3874), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2782), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9011), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2782), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9020), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2782), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9022), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2782), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9024), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2782), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9026), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2782), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9028), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2782), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9027), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2782), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9029), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2782), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3875), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2782), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12912), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2782), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2041), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode86() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2787), new QualifiedName(0, "RefreshStartEventType"), new LocalizedText("en", "RefreshStartEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2787), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2130), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode87() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2788), new QualifiedName(0, "RefreshEndEventType"), new LocalizedText("en", "RefreshEndEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2788), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2130), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode88() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2789), new QualifiedName(0, "RefreshRequiredEventType"), new LocalizedText("en", "RefreshRequiredEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2789), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2130), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode89() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2790), new QualifiedName(0, "AuditConditionEventType"), new LocalizedText("en", "AuditConditionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2790), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2127), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode90() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2803), new QualifiedName(0, "AuditConditionEnableEventType"), new LocalizedText("en", "AuditConditionEnableEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2803), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2790), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode91() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2829), new QualifiedName(0, "AuditConditionCommentEventType"), new LocalizedText("en", "AuditConditionCommentEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2829), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(4170), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2829), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11851), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2829), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2790), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode92() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2830), new QualifiedName(0, "DialogConditionType"), new LocalizedText("en", "DialogConditionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2830), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9035), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2830), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9055), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2830), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2831), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2830), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9064), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2830), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9065), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2830), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9066), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2830), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9067), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2830), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9068), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2830), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9069), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2830), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2782), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode93() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2881), new QualifiedName(0, "AcknowledgeableConditionType"), new LocalizedText("en", "AcknowledgeableConditionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2881), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9073), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2881), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9093), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2881), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9102), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2881), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9111), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2881), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9113), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2881), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2782), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode94() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 11093), new QualifiedName(0, "AuditConditionShelvingEventType"), new LocalizedText("en", "AuditConditionShelvingEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11093), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11855), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11093), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2790), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode95() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2915), new QualifiedName(0, "AlarmConditionType"), new LocalizedText("en", "AlarmConditionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2915), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9118), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2915), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9160), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2915), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11120), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2915), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9169), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2915), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9178), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2915), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9215), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2915), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9216), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2915), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2881), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode96() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2929), new QualifiedName(0, "ShelvedStateMachineType"), new LocalizedText("en", "ShelvedStateMachineType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2929), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9115), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2929), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2930), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2929), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2932), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2929), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2933), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2929), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2935), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2929), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2936), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2929), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2940), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2929), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2942), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2929), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2943), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2929), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2945), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2929), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2947), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2929), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2948), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2929), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2949), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2929), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2771), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode97() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2955), new QualifiedName(0, "LimitAlarmType"), new LocalizedText("en", "LimitAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2955), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11124), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2955), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11125), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2955), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11126), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2955), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11127), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2955), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2915), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode98() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 11163), new QualifiedName(0, "BaseConditionClassType"), new LocalizedText("en", "BaseConditionClassType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11163), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode99() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 11164), new QualifiedName(0, "ProcessConditionClassType"), new LocalizedText("en", "ProcessConditionClassType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11164), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11163), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode100() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 11165), new QualifiedName(0, "MaintenanceConditionClassType"), new LocalizedText("en", "MaintenanceConditionClassType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11165), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11163), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode101() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 11166), new QualifiedName(0, "SystemConditionClassType"), new LocalizedText("en", "SystemConditionClassType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11166), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11163), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode102() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 11187), new QualifiedName(0, "AggregateConfigurationType"), new LocalizedText("en", "AggregateConfigurationType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11187), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11188), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11187), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11189), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11187), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11190), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11187), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11191), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11187), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode103() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 2999), new QualifiedName(0, "AuditHistoryEventUpdateEventType"), new LocalizedText("en", "AuditHistoryEventUpdateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 2999), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3025), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2999), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3028), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2999), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3003), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2999), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3029), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2999), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3030), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 2999), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2104), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode104() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 3006), new QualifiedName(0, "AuditHistoryValueUpdateEventType"), new LocalizedText("en", "AuditHistoryValueUpdateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 3006), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3026), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3006), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3031), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3006), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3032), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3006), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3033), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3006), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2104), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode105() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 3012), new QualifiedName(0, "AuditHistoryDeleteEventType"), new LocalizedText("en", "AuditHistoryDeleteEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 3012), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3027), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3012), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2104), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode106() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 3014), new QualifiedName(0, "AuditHistoryRawModifyDeleteEventType"), new LocalizedText("en", "AuditHistoryRawModifyDeleteEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 3014), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3015), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3014), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3016), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3014), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3017), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3014), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3034), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3014), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3012), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode107() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 3019), new QualifiedName(0, "AuditHistoryAtTimeDeleteEventType"), new LocalizedText("en", "AuditHistoryAtTimeDeleteEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 3019), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3020), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3019), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3021), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3019), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3012), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode108() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 3022), new QualifiedName(0, "AuditHistoryEventDeleteEventType"), new LocalizedText("en", "AuditHistoryEventDeleteEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 3022), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3023), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3022), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3024), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3022), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3012), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode109() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 3035), new QualifiedName(0, "EventQueueOverflowEventType"), new LocalizedText("en", "EventQueueOverflowEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 3035), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2041), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode110() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 11436), new QualifiedName(0, "ProgressEventType"), new LocalizedText("en", "ProgressEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 11436), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12502), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11436), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12503), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11436), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2041), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode111() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 11446), new QualifiedName(0, "SystemStatusChangeEventType"), new LocalizedText("en", "SystemStatusChangeEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 11446), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11696), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11446), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2130), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode112() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 11564), new QualifiedName(0, "OperationLimitsType"), new LocalizedText("en", "OperationLimitsType"), new LocalizedText("en", "Identifies the operation limits imposed by the server."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11564), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11565), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11564), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12161), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11564), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12162), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11564), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11567), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11564), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12163), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11564), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12164), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11564), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11569), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11564), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11570), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11564), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11571), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11564), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11572), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11564), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11573), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11564), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11574), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11564), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(61), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode113() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 11575), new QualifiedName(0, "FileType"), new LocalizedText("en", "FileType"), new LocalizedText("en", "An object that represents a file that can be accessed via the server."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11575), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11576), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11575), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12686), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11575), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12687), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11575), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11579), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11575), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13341), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11575), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11580), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11575), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11583), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11575), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11585), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11575), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11588), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11575), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11590), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11575), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11593), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11575), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode114() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 11595), new QualifiedName(0, "AddressSpaceFileType"), new LocalizedText("en", "AddressSpaceFileType"), new LocalizedText("en", "A file used to store a namespace exported from the server."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11595), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11615), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11595), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11575), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode115() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 11616), new QualifiedName(0, "NamespaceMetadataType"), new LocalizedText("en", "NamespaceMetadataType"), new LocalizedText("en", "Provides the metadata for a namespace used by the server."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11616), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11617), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11616), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11618), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11616), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11619), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11616), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11620), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11616), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11621), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11616), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11622), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11616), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11623), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11616), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11624), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11616), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode116() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 11645), new QualifiedName(0, "NamespacesType"), new LocalizedText("en", "NamespacesType"), new LocalizedText("en", "A container for the namespace metadata provided by the server."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11645), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11646), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11645), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11675), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11645), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(58), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode117() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 11753), new QualifiedName(0, "SystemOffNormalAlarmType"), new LocalizedText("en", "SystemOffNormalAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11753), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(10637), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode118() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 11856), new QualifiedName(0, "AuditProgramTransitionEventType"), new LocalizedText("en", "AuditProgramTransitionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11856), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11875), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11856), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2315), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode119() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 11945), new QualifiedName(0, "NonTransparentNetworkRedundancyType"), new LocalizedText("en", "NonTransparentNetworkRedundancyType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11945), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11948), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11945), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2039), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode120() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, new NodeId(0, 3806), new QualifiedName(0, "ProgramTransitionAuditEventType"), new LocalizedText("en", "ProgramTransitionAuditEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 3806), new NodeId(0, 47), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(3825), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 3806), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(2315), UInteger.valueOf(0)), false));
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
    }
}
