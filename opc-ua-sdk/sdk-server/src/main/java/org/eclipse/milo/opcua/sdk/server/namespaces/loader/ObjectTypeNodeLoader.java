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
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
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
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.BaseObjectType, new QualifiedName(0, "BaseObjectType"), new LocalizedText("en", "BaseObjectType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        this.nodeManager.addNode(node);
    }

    private void loadNode1() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.FolderType, new QualifiedName(0, "FolderType"), new LocalizedText("en", "FolderType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.FolderType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode2() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.DataTypeSystemType, new QualifiedName(0, "DataTypeSystemType"), new LocalizedText("en", "DataTypeSystemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.DataTypeSystemType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode3() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.DataTypeEncodingType, new QualifiedName(0, "DataTypeEncodingType"), new LocalizedText("en", "DataTypeEncodingType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.DataTypeEncodingType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode4() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ModellingRuleType, new QualifiedName(0, "ModellingRuleType"), new LocalizedText("en", "ModellingRuleType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ModellingRuleType, Identifiers.HasProperty, Identifiers.ModellingRuleType_NamingRule.expanded(), true));
        node.addReference(new Reference(Identifiers.ModellingRuleType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode5() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.TrustListType, new QualifiedName(0, "TrustListType"), new LocalizedText("en", "TrustListType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.TrustListType, Identifiers.HasProperty, Identifiers.TrustListType_LastUpdateTime.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListType, Identifiers.HasComponent, Identifiers.TrustListType_OpenWithMasks.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListType, Identifiers.HasComponent, Identifiers.TrustListType_CloseAndUpdate.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListType, Identifiers.HasComponent, Identifiers.TrustListType_AddCertificate.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListType, Identifiers.HasComponent, Identifiers.TrustListType_RemoveCertificate.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListType, Identifiers.HasSubtype, Identifiers.FileType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode6() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.CertificateGroupType, new QualifiedName(0, "CertificateGroupType"), new LocalizedText("en", "CertificateGroupType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.CertificateGroupType, Identifiers.HasComponent, Identifiers.CertificateGroupType_TrustList.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType, Identifiers.HasProperty, Identifiers.CertificateGroupType_CertificateTypes.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode7() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.CertificateType, new QualifiedName(0, "CertificateType"), new LocalizedText("en", "CertificateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.CertificateType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode8() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ApplicationCertificateType, new QualifiedName(0, "ApplicationCertificateType"), new LocalizedText("en", "ApplicationCertificateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.ApplicationCertificateType, Identifiers.HasSubtype, Identifiers.CertificateType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode9() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.HttpsCertificateType, new QualifiedName(0, "HttpsCertificateType"), new LocalizedText("en", "HttpsCertificateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.HttpsCertificateType, Identifiers.HasSubtype, Identifiers.CertificateType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode10() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.RsaMinApplicationCertificateType, new QualifiedName(0, "RsaMinApplicationCertificateType"), new LocalizedText("en", "RsaMinApplicationCertificateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.RsaMinApplicationCertificateType, Identifiers.HasSubtype, Identifiers.ApplicationCertificateType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode11() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.RsaSha256ApplicationCertificateType, new QualifiedName(0, "RsaSha256ApplicationCertificateType"), new LocalizedText("en", "RsaSha256ApplicationCertificateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.RsaSha256ApplicationCertificateType, Identifiers.HasSubtype, Identifiers.ApplicationCertificateType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode12() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.TrustListUpdatedAuditEventType, new QualifiedName(0, "TrustListUpdatedAuditEventType"), new LocalizedText("en", "TrustListUpdatedAuditEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.TrustListUpdatedAuditEventType, Identifiers.HasSubtype, Identifiers.AuditUpdateMethodEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode13() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ServerConfigurationType, new QualifiedName(0, "ServerConfigurationType"), new LocalizedText("en", "ServerConfigurationType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ServerConfigurationType, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType, Identifiers.HasProperty, Identifiers.ServerConfigurationType_ServerCapabilities.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType, Identifiers.HasProperty, Identifiers.ServerConfigurationType_SupportedPrivateKeyFormats.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType, Identifiers.HasProperty, Identifiers.ServerConfigurationType_MaxTrustListSize.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType, Identifiers.HasProperty, Identifiers.ServerConfigurationType_MulticastDnsEnabled.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType, Identifiers.HasComponent, Identifiers.ServerConfigurationType_UpdateCertificate.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType, Identifiers.HasComponent, Identifiers.ServerConfigurationType_ApplyChanges.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CreateSigningRequest.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType, Identifiers.HasComponent, Identifiers.ServerConfigurationType_GetRejectedList.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode14() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.CertificateUpdatedAuditEventType, new QualifiedName(0, "CertificateUpdatedAuditEventType"), new LocalizedText("en", "CertificateUpdatedAuditEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.CertificateUpdatedAuditEventType, Identifiers.HasProperty, Identifiers.CertificateUpdatedAuditEventType_CertificateGroup.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateUpdatedAuditEventType, Identifiers.HasProperty, Identifiers.CertificateUpdatedAuditEventType_CertificateType.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateUpdatedAuditEventType, Identifiers.HasSubtype, Identifiers.AuditUpdateMethodEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode15() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditConditionRespondEventType, new QualifiedName(0, "AuditConditionRespondEventType"), new LocalizedText("en", "AuditConditionRespondEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AuditConditionRespondEventType, Identifiers.HasProperty, Identifiers.AuditConditionRespondEventType_SelectedResponse.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditConditionRespondEventType, Identifiers.HasSubtype, Identifiers.AuditConditionEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode16() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditConditionAcknowledgeEventType, new QualifiedName(0, "AuditConditionAcknowledgeEventType"), new LocalizedText("en", "AuditConditionAcknowledgeEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AuditConditionAcknowledgeEventType, Identifiers.HasProperty, new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(15002), UInteger.valueOf(0)), true));
        node.addReference(new Reference(Identifiers.AuditConditionAcknowledgeEventType, Identifiers.HasProperty, Identifiers.AuditConditionAcknowledgeEventType_Comment.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditConditionAcknowledgeEventType, Identifiers.HasSubtype, Identifiers.AuditConditionEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode17() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditConditionConfirmEventType, new QualifiedName(0, "AuditConditionConfirmEventType"), new LocalizedText("en", "AuditConditionConfirmEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AuditConditionConfirmEventType, Identifiers.HasProperty, new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(15003), UInteger.valueOf(0)), true));
        node.addReference(new Reference(Identifiers.AuditConditionConfirmEventType, Identifiers.HasProperty, Identifiers.AuditConditionConfirmEventType_Comment.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditConditionConfirmEventType, Identifiers.HasSubtype, Identifiers.AuditConditionEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode18() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.CertificateExpirationAlarmType, new QualifiedName(0, "CertificateExpirationAlarmType"), new LocalizedText("en", "CertificateExpirationAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.CertificateExpirationAlarmType, Identifiers.HasProperty, Identifiers.CertificateExpirationAlarmType_ExpirationDate.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateExpirationAlarmType, Identifiers.HasProperty, Identifiers.CertificateExpirationAlarmType_ExpirationLimit.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateExpirationAlarmType, Identifiers.HasProperty, Identifiers.CertificateExpirationAlarmType_CertificateType.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateExpirationAlarmType, Identifiers.HasProperty, Identifiers.CertificateExpirationAlarmType_Certificate.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateExpirationAlarmType, Identifiers.HasSubtype, Identifiers.SystemOffNormalAlarmType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode19() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.FileDirectoryType, new QualifiedName(0, "FileDirectoryType"), new LocalizedText("en", "FileDirectoryType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.FileDirectoryType, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileName_Placeholder.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType, Identifiers.HasComponent, Identifiers.FileDirectoryType_CreateDirectory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType, Identifiers.HasComponent, Identifiers.FileDirectoryType_CreateFile.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType, Identifiers.HasComponent, Identifiers.FileDirectoryType_Delete.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType, Identifiers.HasComponent, Identifiers.FileDirectoryType_MoveOrCopy.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType, Identifiers.HasSubtype, Identifiers.FolderType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode20() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ExclusiveLimitStateMachineType, new QualifiedName(0, "ExclusiveLimitStateMachineType"), new LocalizedText("en", "ExclusiveLimitStateMachineType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType, Identifiers.HasComponent, Identifiers.ExclusiveLimitStateMachineType_HighHigh.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType, Identifiers.HasComponent, Identifiers.ExclusiveLimitStateMachineType_High.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType, Identifiers.HasComponent, Identifiers.ExclusiveLimitStateMachineType_Low.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType, Identifiers.HasComponent, Identifiers.ExclusiveLimitStateMachineType_LowLow.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType, Identifiers.HasComponent, Identifiers.ExclusiveLimitStateMachineType_LowLowToLow.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType, Identifiers.HasComponent, Identifiers.ExclusiveLimitStateMachineType_LowToLowLow.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType, Identifiers.HasComponent, Identifiers.ExclusiveLimitStateMachineType_HighHighToHigh.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType, Identifiers.HasComponent, Identifiers.ExclusiveLimitStateMachineType_HighToHighHigh.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType, Identifiers.HasSubtype, Identifiers.FiniteStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode21() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ExclusiveLimitAlarmType, new QualifiedName(0, "ExclusiveLimitAlarmType"), new LocalizedText("en", "ExclusiveLimitAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ExclusiveLimitAlarmType, Identifiers.HasComponent, Identifiers.ExclusiveLimitAlarmType_ActiveState.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitAlarmType, Identifiers.HasComponent, Identifiers.ExclusiveLimitAlarmType_LimitState.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitAlarmType, Identifiers.HasSubtype, Identifiers.LimitAlarmType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode22() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ExclusiveLevelAlarmType, new QualifiedName(0, "ExclusiveLevelAlarmType"), new LocalizedText("en", "ExclusiveLevelAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ExclusiveLevelAlarmType, Identifiers.HasSubtype, Identifiers.ExclusiveLimitAlarmType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode23() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ExclusiveRateOfChangeAlarmType, new QualifiedName(0, "ExclusiveRateOfChangeAlarmType"), new LocalizedText("en", "ExclusiveRateOfChangeAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ExclusiveRateOfChangeAlarmType, Identifiers.HasSubtype, Identifiers.ExclusiveLimitAlarmType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode24() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.CertificateGroupFolderType, new QualifiedName(0, "CertificateGroupFolderType"), new LocalizedText("en", "CertificateGroupFolderType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType, Identifiers.Organizes, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType, Identifiers.HasSubtype, Identifiers.FolderType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode25() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ExclusiveDeviationAlarmType, new QualifiedName(0, "ExclusiveDeviationAlarmType"), new LocalizedText("en", "ExclusiveDeviationAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ExclusiveDeviationAlarmType, Identifiers.HasProperty, Identifiers.ExclusiveDeviationAlarmType_SetpointNode.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveDeviationAlarmType, Identifiers.HasSubtype, Identifiers.ExclusiveLimitAlarmType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode26() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.NonExclusiveLimitAlarmType, new QualifiedName(0, "NonExclusiveLimitAlarmType"), new LocalizedText("en", "NonExclusiveLimitAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.NonExclusiveLimitAlarmType, Identifiers.HasComponent, Identifiers.NonExclusiveLimitAlarmType_ActiveState.expanded(), true));
        node.addReference(new Reference(Identifiers.NonExclusiveLimitAlarmType, Identifiers.HasComponent, Identifiers.NonExclusiveLimitAlarmType_HighHighState.expanded(), true));
        node.addReference(new Reference(Identifiers.NonExclusiveLimitAlarmType, Identifiers.HasComponent, Identifiers.NonExclusiveLimitAlarmType_HighState.expanded(), true));
        node.addReference(new Reference(Identifiers.NonExclusiveLimitAlarmType, Identifiers.HasComponent, Identifiers.NonExclusiveLimitAlarmType_LowState.expanded(), true));
        node.addReference(new Reference(Identifiers.NonExclusiveLimitAlarmType, Identifiers.HasComponent, Identifiers.NonExclusiveLimitAlarmType_LowLowState.expanded(), true));
        node.addReference(new Reference(Identifiers.NonExclusiveLimitAlarmType, Identifiers.HasSubtype, Identifiers.LimitAlarmType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode27() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.NonExclusiveLevelAlarmType, new QualifiedName(0, "NonExclusiveLevelAlarmType"), new LocalizedText("en", "NonExclusiveLevelAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.NonExclusiveLevelAlarmType, Identifiers.HasSubtype, Identifiers.NonExclusiveLimitAlarmType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode28() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ServerType, new QualifiedName(0, "ServerType"), new LocalizedText("en", "ServerType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ServerType, Identifiers.HasProperty, Identifiers.ServerType_ServerArray.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType, Identifiers.HasProperty, Identifiers.ServerType_NamespaceArray.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType, Identifiers.HasComponent, Identifiers.ServerType_ServerStatus.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType, Identifiers.HasProperty, Identifiers.ServerType_ServiceLevel.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType, Identifiers.HasProperty, Identifiers.ServerType_Auditing.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType, Identifiers.HasProperty, Identifiers.ServerType_EstimatedReturnTime.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType, Identifiers.HasComponent, Identifiers.ServerType_ServerCapabilities.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType, Identifiers.HasComponent, Identifiers.ServerType_ServerDiagnostics.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType, Identifiers.HasComponent, Identifiers.ServerType_VendorServerInfo.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType, Identifiers.HasComponent, Identifiers.ServerType_ServerRedundancy.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType, Identifiers.HasComponent, Identifiers.ServerType_Namespaces.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType, Identifiers.HasComponent, Identifiers.ServerType_GetMonitoredItems.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType, Identifiers.HasComponent, Identifiers.ServerType_ResendData.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType, Identifiers.HasComponent, Identifiers.ServerType_SetSubscriptionDurable.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType, Identifiers.HasComponent, Identifiers.ServerType_RequestServerStateChange.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode29() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ServerCapabilitiesType, new QualifiedName(0, "ServerCapabilitiesType"), new LocalizedText("en", "ServerCapabilitiesType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType, Identifiers.HasProperty, Identifiers.ServerCapabilitiesType_ServerProfileArray.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType, Identifiers.HasProperty, Identifiers.ServerCapabilitiesType_LocaleIdArray.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType, Identifiers.HasProperty, Identifiers.ServerCapabilitiesType_MinSupportedSampleRate.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType, Identifiers.HasProperty, Identifiers.ServerCapabilitiesType_MaxBrowseContinuationPoints.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType, Identifiers.HasProperty, Identifiers.ServerCapabilitiesType_MaxQueryContinuationPoints.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType, Identifiers.HasProperty, Identifiers.ServerCapabilitiesType_MaxHistoryContinuationPoints.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType, Identifiers.HasProperty, Identifiers.ServerCapabilitiesType_SoftwareCertificates.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType, Identifiers.HasProperty, Identifiers.ServerCapabilitiesType_MaxArrayLength.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType, Identifiers.HasProperty, Identifiers.ServerCapabilitiesType_MaxStringLength.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType, Identifiers.HasProperty, Identifiers.ServerCapabilitiesType_MaxByteStringLength.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType, Identifiers.HasComponent, Identifiers.ServerCapabilitiesType_OperationLimits.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType, Identifiers.HasComponent, Identifiers.ServerCapabilitiesType_ModellingRules.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType, Identifiers.HasComponent, Identifiers.ServerCapabilitiesType_AggregateFunctions.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType, Identifiers.HasComponent, Identifiers.ServerCapabilitiesType_VendorCapability_Placeholder.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode30() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ServerDiagnosticsType, new QualifiedName(0, "ServerDiagnosticsType"), new LocalizedText("en", "ServerDiagnosticsType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ServerDiagnosticsType, Identifiers.HasComponent, Identifiers.ServerDiagnosticsType_ServerDiagnosticsSummary.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsType, Identifiers.HasComponent, Identifiers.ServerDiagnosticsType_SamplingIntervalDiagnosticsArray.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsType, Identifiers.HasComponent, Identifiers.ServerDiagnosticsType_SubscriptionDiagnosticsArray.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsType, Identifiers.HasComponent, Identifiers.ServerDiagnosticsType_SessionsDiagnosticsSummary.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsType, Identifiers.HasProperty, Identifiers.ServerDiagnosticsType_EnabledFlag.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode31() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.NonExclusiveRateOfChangeAlarmType, new QualifiedName(0, "NonExclusiveRateOfChangeAlarmType"), new LocalizedText("en", "NonExclusiveRateOfChangeAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.NonExclusiveRateOfChangeAlarmType, Identifiers.HasSubtype, Identifiers.NonExclusiveLimitAlarmType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode32() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.SessionsDiagnosticsSummaryType, new QualifiedName(0, "SessionsDiagnosticsSummaryType"), new LocalizedText("en", "SessionsDiagnosticsSummaryType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.SessionsDiagnosticsSummaryType, Identifiers.HasComponent, Identifiers.SessionsDiagnosticsSummaryType_SessionDiagnosticsArray.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionsDiagnosticsSummaryType, Identifiers.HasComponent, Identifiers.SessionsDiagnosticsSummaryType_SessionSecurityDiagnosticsArray.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionsDiagnosticsSummaryType, Identifiers.HasComponent, Identifiers.SessionsDiagnosticsSummaryType_ClientName_Placeholder.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionsDiagnosticsSummaryType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode33() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.SessionDiagnosticsObjectType, new QualifiedName(0, "SessionDiagnosticsObjectType"), new LocalizedText("en", "SessionDiagnosticsObjectType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.SessionDiagnosticsObjectType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsObjectType_SessionDiagnostics.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsObjectType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsObjectType_SessionSecurityDiagnostics.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsObjectType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsObjectType_SubscriptionDiagnosticsArray.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsObjectType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode34() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.VendorServerInfoType, new QualifiedName(0, "VendorServerInfoType"), new LocalizedText("en", "VendorServerInfoType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.VendorServerInfoType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode35() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ServerRedundancyType, new QualifiedName(0, "ServerRedundancyType"), new LocalizedText("en", "ServerRedundancyType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ServerRedundancyType, Identifiers.HasProperty, Identifiers.ServerRedundancyType_RedundancySupport.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerRedundancyType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode36() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.TransparentRedundancyType, new QualifiedName(0, "TransparentRedundancyType"), new LocalizedText("en", "TransparentRedundancyType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.TransparentRedundancyType, Identifiers.HasProperty, Identifiers.TransparentRedundancyType_CurrentServerId.expanded(), true));
        node.addReference(new Reference(Identifiers.TransparentRedundancyType, Identifiers.HasProperty, Identifiers.TransparentRedundancyType_RedundantServerArray.expanded(), true));
        node.addReference(new Reference(Identifiers.TransparentRedundancyType, Identifiers.HasSubtype, Identifiers.ServerRedundancyType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode37() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.NonTransparentRedundancyType, new QualifiedName(0, "NonTransparentRedundancyType"), new LocalizedText("en", "NonTransparentRedundancyType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.NonTransparentRedundancyType, Identifiers.HasProperty, Identifiers.NonTransparentRedundancyType_ServerUriArray.expanded(), true));
        node.addReference(new Reference(Identifiers.NonTransparentRedundancyType, Identifiers.HasSubtype, Identifiers.ServerRedundancyType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode38() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.BaseEventType, new QualifiedName(0, "BaseEventType"), new LocalizedText("en", "BaseEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.BaseEventType, Identifiers.HasProperty, Identifiers.BaseEventType_EventId.expanded(), true));
        node.addReference(new Reference(Identifiers.BaseEventType, Identifiers.HasProperty, Identifiers.BaseEventType_EventType.expanded(), true));
        node.addReference(new Reference(Identifiers.BaseEventType, Identifiers.HasProperty, Identifiers.BaseEventType_SourceNode.expanded(), true));
        node.addReference(new Reference(Identifiers.BaseEventType, Identifiers.HasProperty, Identifiers.BaseEventType_SourceName.expanded(), true));
        node.addReference(new Reference(Identifiers.BaseEventType, Identifiers.HasProperty, Identifiers.BaseEventType_Time.expanded(), true));
        node.addReference(new Reference(Identifiers.BaseEventType, Identifiers.HasProperty, Identifiers.BaseEventType_ReceiveTime.expanded(), true));
        node.addReference(new Reference(Identifiers.BaseEventType, Identifiers.HasProperty, Identifiers.BaseEventType_LocalTime.expanded(), true));
        node.addReference(new Reference(Identifiers.BaseEventType, Identifiers.HasProperty, Identifiers.BaseEventType_Message.expanded(), true));
        node.addReference(new Reference(Identifiers.BaseEventType, Identifiers.HasProperty, Identifiers.BaseEventType_Severity.expanded(), true));
        node.addReference(new Reference(Identifiers.BaseEventType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode39() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditEventType, new QualifiedName(0, "AuditEventType"), new LocalizedText("en", "AuditEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditEventType, Identifiers.HasProperty, Identifiers.AuditEventType_ActionTimeStamp.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditEventType, Identifiers.HasProperty, Identifiers.AuditEventType_Status.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditEventType, Identifiers.HasProperty, Identifiers.AuditEventType_ServerId.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditEventType, Identifiers.HasProperty, Identifiers.AuditEventType_ClientAuditEntryId.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditEventType, Identifiers.HasProperty, Identifiers.AuditEventType_ClientUserId.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditEventType, Identifiers.HasSubtype, Identifiers.BaseEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode40() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditSecurityEventType, new QualifiedName(0, "AuditSecurityEventType"), new LocalizedText("en", "AuditSecurityEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditSecurityEventType, Identifiers.HasSubtype, Identifiers.AuditEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode41() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditChannelEventType, new QualifiedName(0, "AuditChannelEventType"), new LocalizedText("en", "AuditChannelEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditChannelEventType, Identifiers.HasProperty, Identifiers.AuditChannelEventType_SecureChannelId.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditChannelEventType, Identifiers.HasSubtype, Identifiers.AuditSecurityEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode42() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditOpenSecureChannelEventType, new QualifiedName(0, "AuditOpenSecureChannelEventType"), new LocalizedText("en", "AuditOpenSecureChannelEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditOpenSecureChannelEventType, Identifiers.HasProperty, Identifiers.AuditOpenSecureChannelEventType_ClientCertificate.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditOpenSecureChannelEventType, Identifiers.HasProperty, Identifiers.AuditOpenSecureChannelEventType_ClientCertificateThumbprint.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditOpenSecureChannelEventType, Identifiers.HasProperty, Identifiers.AuditOpenSecureChannelEventType_RequestType.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditOpenSecureChannelEventType, Identifiers.HasProperty, Identifiers.AuditOpenSecureChannelEventType_SecurityPolicyUri.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditOpenSecureChannelEventType, Identifiers.HasProperty, Identifiers.AuditOpenSecureChannelEventType_SecurityMode.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditOpenSecureChannelEventType, Identifiers.HasProperty, Identifiers.AuditOpenSecureChannelEventType_RequestedLifetime.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditOpenSecureChannelEventType, Identifiers.HasSubtype, Identifiers.AuditChannelEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode43() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditSessionEventType, new QualifiedName(0, "AuditSessionEventType"), new LocalizedText("en", "AuditSessionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditSessionEventType, Identifiers.HasProperty, Identifiers.AuditSessionEventType_SessionId.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditSessionEventType, Identifiers.HasSubtype, Identifiers.AuditSecurityEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode44() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditCreateSessionEventType, new QualifiedName(0, "AuditCreateSessionEventType"), new LocalizedText("en", "AuditCreateSessionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditCreateSessionEventType, Identifiers.HasProperty, Identifiers.AuditCreateSessionEventType_SecureChannelId.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditCreateSessionEventType, Identifiers.HasProperty, Identifiers.AuditCreateSessionEventType_ClientCertificate.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditCreateSessionEventType, Identifiers.HasProperty, Identifiers.AuditCreateSessionEventType_ClientCertificateThumbprint.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditCreateSessionEventType, Identifiers.HasProperty, Identifiers.AuditCreateSessionEventType_RevisedSessionTimeout.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditCreateSessionEventType, Identifiers.HasSubtype, Identifiers.AuditSessionEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode45() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditActivateSessionEventType, new QualifiedName(0, "AuditActivateSessionEventType"), new LocalizedText("en", "AuditActivateSessionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditActivateSessionEventType, Identifiers.HasProperty, Identifiers.AuditActivateSessionEventType_ClientSoftwareCertificates.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditActivateSessionEventType, Identifiers.HasProperty, Identifiers.AuditActivateSessionEventType_UserIdentityToken.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditActivateSessionEventType, Identifiers.HasProperty, Identifiers.AuditActivateSessionEventType_SecureChannelId.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditActivateSessionEventType, Identifiers.HasSubtype, Identifiers.AuditSessionEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode46() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditCancelEventType, new QualifiedName(0, "AuditCancelEventType"), new LocalizedText("en", "AuditCancelEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditCancelEventType, Identifiers.HasProperty, Identifiers.AuditCancelEventType_RequestHandle.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditCancelEventType, Identifiers.HasSubtype, Identifiers.AuditSessionEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode47() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditCertificateEventType, new QualifiedName(0, "AuditCertificateEventType"), new LocalizedText("en", "AuditCertificateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditCertificateEventType, Identifiers.HasProperty, Identifiers.AuditCertificateEventType_Certificate.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditCertificateEventType, Identifiers.HasSubtype, Identifiers.AuditSecurityEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode48() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditCertificateDataMismatchEventType, new QualifiedName(0, "AuditCertificateDataMismatchEventType"), new LocalizedText("en", "AuditCertificateDataMismatchEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditCertificateDataMismatchEventType, Identifiers.HasProperty, Identifiers.AuditCertificateDataMismatchEventType_InvalidHostname.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditCertificateDataMismatchEventType, Identifiers.HasProperty, Identifiers.AuditCertificateDataMismatchEventType_InvalidUri.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditCertificateDataMismatchEventType, Identifiers.HasSubtype, Identifiers.AuditCertificateEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode49() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditCertificateExpiredEventType, new QualifiedName(0, "AuditCertificateExpiredEventType"), new LocalizedText("en", "AuditCertificateExpiredEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditCertificateExpiredEventType, Identifiers.HasSubtype, Identifiers.AuditCertificateEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode50() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditCertificateInvalidEventType, new QualifiedName(0, "AuditCertificateInvalidEventType"), new LocalizedText("en", "AuditCertificateInvalidEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditCertificateInvalidEventType, Identifiers.HasSubtype, Identifiers.AuditCertificateEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode51() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditCertificateUntrustedEventType, new QualifiedName(0, "AuditCertificateUntrustedEventType"), new LocalizedText("en", "AuditCertificateUntrustedEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditCertificateUntrustedEventType, Identifiers.HasSubtype, Identifiers.AuditCertificateEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode52() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditCertificateRevokedEventType, new QualifiedName(0, "AuditCertificateRevokedEventType"), new LocalizedText("en", "AuditCertificateRevokedEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditCertificateRevokedEventType, Identifiers.HasSubtype, Identifiers.AuditCertificateEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode53() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditCertificateMismatchEventType, new QualifiedName(0, "AuditCertificateMismatchEventType"), new LocalizedText("en", "AuditCertificateMismatchEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditCertificateMismatchEventType, Identifiers.HasSubtype, Identifiers.AuditCertificateEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode54() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditNodeManagementEventType, new QualifiedName(0, "AuditNodeManagementEventType"), new LocalizedText("en", "AuditNodeManagementEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditNodeManagementEventType, Identifiers.HasSubtype, Identifiers.AuditEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode55() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditAddNodesEventType, new QualifiedName(0, "AuditAddNodesEventType"), new LocalizedText("en", "AuditAddNodesEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditAddNodesEventType, Identifiers.HasProperty, Identifiers.AuditAddNodesEventType_NodesToAdd.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditAddNodesEventType, Identifiers.HasSubtype, Identifiers.AuditNodeManagementEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode56() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditDeleteNodesEventType, new QualifiedName(0, "AuditDeleteNodesEventType"), new LocalizedText("en", "AuditDeleteNodesEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditDeleteNodesEventType, Identifiers.HasProperty, Identifiers.AuditDeleteNodesEventType_NodesToDelete.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditDeleteNodesEventType, Identifiers.HasSubtype, Identifiers.AuditNodeManagementEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode57() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditAddReferencesEventType, new QualifiedName(0, "AuditAddReferencesEventType"), new LocalizedText("en", "AuditAddReferencesEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditAddReferencesEventType, Identifiers.HasProperty, Identifiers.AuditAddReferencesEventType_ReferencesToAdd.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditAddReferencesEventType, Identifiers.HasSubtype, Identifiers.AuditNodeManagementEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode58() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditDeleteReferencesEventType, new QualifiedName(0, "AuditDeleteReferencesEventType"), new LocalizedText("en", "AuditDeleteReferencesEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditDeleteReferencesEventType, Identifiers.HasProperty, Identifiers.AuditDeleteReferencesEventType_ReferencesToDelete.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditDeleteReferencesEventType, Identifiers.HasSubtype, Identifiers.AuditNodeManagementEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode59() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditUpdateEventType, new QualifiedName(0, "AuditUpdateEventType"), new LocalizedText("en", "AuditUpdateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditUpdateEventType, Identifiers.HasSubtype, Identifiers.AuditEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode60() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditWriteUpdateEventType, new QualifiedName(0, "AuditWriteUpdateEventType"), new LocalizedText("en", "AuditWriteUpdateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditWriteUpdateEventType, Identifiers.HasProperty, Identifiers.AuditWriteUpdateEventType_AttributeId.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditWriteUpdateEventType, Identifiers.HasProperty, Identifiers.AuditWriteUpdateEventType_IndexRange.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditWriteUpdateEventType, Identifiers.HasProperty, Identifiers.AuditWriteUpdateEventType_OldValue.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditWriteUpdateEventType, Identifiers.HasProperty, Identifiers.AuditWriteUpdateEventType_NewValue.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditWriteUpdateEventType, Identifiers.HasSubtype, Identifiers.AuditUpdateEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode61() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditHistoryUpdateEventType, new QualifiedName(0, "AuditHistoryUpdateEventType"), new LocalizedText("en", "AuditHistoryUpdateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditHistoryUpdateEventType, Identifiers.HasProperty, Identifiers.AuditHistoryUpdateEventType_ParameterDataTypeId.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryUpdateEventType, Identifiers.HasSubtype, Identifiers.AuditUpdateEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode62() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditUpdateMethodEventType, new QualifiedName(0, "AuditUpdateMethodEventType"), new LocalizedText("en", "AuditUpdateMethodEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditUpdateMethodEventType, Identifiers.HasProperty, Identifiers.AuditUpdateMethodEventType_MethodId.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditUpdateMethodEventType, Identifiers.HasProperty, Identifiers.AuditUpdateMethodEventType_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditUpdateMethodEventType, Identifiers.HasSubtype, Identifiers.AuditEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode63() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.SystemEventType, new QualifiedName(0, "SystemEventType"), new LocalizedText("en", "SystemEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.SystemEventType, Identifiers.HasSubtype, Identifiers.BaseEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode64() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.DeviceFailureEventType, new QualifiedName(0, "DeviceFailureEventType"), new LocalizedText("en", "DeviceFailureEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.DeviceFailureEventType, Identifiers.HasSubtype, Identifiers.SystemEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode65() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.BaseModelChangeEventType, new QualifiedName(0, "BaseModelChangeEventType"), new LocalizedText("en", "BaseModelChangeEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.BaseModelChangeEventType, Identifiers.HasSubtype, Identifiers.BaseEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode66() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.GeneralModelChangeEventType, new QualifiedName(0, "GeneralModelChangeEventType"), new LocalizedText("en", "GeneralModelChangeEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.GeneralModelChangeEventType, Identifiers.HasProperty, Identifiers.GeneralModelChangeEventType_Changes.expanded(), true));
        node.addReference(new Reference(Identifiers.GeneralModelChangeEventType, Identifiers.HasSubtype, Identifiers.BaseModelChangeEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode67() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.NonExclusiveDeviationAlarmType, new QualifiedName(0, "NonExclusiveDeviationAlarmType"), new LocalizedText("en", "NonExclusiveDeviationAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.NonExclusiveDeviationAlarmType, Identifiers.HasProperty, Identifiers.NonExclusiveDeviationAlarmType_SetpointNode.expanded(), true));
        node.addReference(new Reference(Identifiers.NonExclusiveDeviationAlarmType, Identifiers.HasSubtype, Identifiers.NonExclusiveLimitAlarmType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode68() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.StateMachineType, new QualifiedName(0, "StateMachineType"), new LocalizedText("en", "StateMachineType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.StateMachineType, Identifiers.HasComponent, Identifiers.StateMachineType_CurrentState.expanded(), true));
        node.addReference(new Reference(Identifiers.StateMachineType, Identifiers.HasComponent, Identifiers.StateMachineType_LastTransition.expanded(), true));
        node.addReference(new Reference(Identifiers.StateMachineType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode69() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.StateType, new QualifiedName(0, "StateType"), new LocalizedText("en", "StateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.StateType, Identifiers.HasProperty, Identifiers.StateType_StateNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.StateType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode70() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.InitialStateType, new QualifiedName(0, "InitialStateType"), new LocalizedText("en", "InitialStateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.InitialStateType, Identifiers.HasSubtype, Identifiers.StateType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode71() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.TransitionType, new QualifiedName(0, "TransitionType"), new LocalizedText("en", "TransitionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.TransitionType, Identifiers.HasProperty, Identifiers.TransitionType_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.TransitionType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode72() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.TransitionEventType, new QualifiedName(0, "TransitionEventType"), new LocalizedText("en", "TransitionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.TransitionEventType, Identifiers.HasComponent, Identifiers.TransitionEventType_Transition.expanded(), true));
        node.addReference(new Reference(Identifiers.TransitionEventType, Identifiers.HasComponent, Identifiers.TransitionEventType_FromState.expanded(), true));
        node.addReference(new Reference(Identifiers.TransitionEventType, Identifiers.HasComponent, Identifiers.TransitionEventType_ToState.expanded(), true));
        node.addReference(new Reference(Identifiers.TransitionEventType, Identifiers.HasSubtype, Identifiers.BaseEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode73() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditUpdateStateEventType, new QualifiedName(0, "AuditUpdateStateEventType"), new LocalizedText("en", "AuditUpdateStateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditUpdateStateEventType, Identifiers.HasProperty, Identifiers.AuditUpdateStateEventType_OldStateId.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditUpdateStateEventType, Identifiers.HasProperty, Identifiers.AuditUpdateStateEventType_NewStateId.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditUpdateStateEventType, Identifiers.HasSubtype, Identifiers.AuditUpdateMethodEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode74() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.HistoricalDataConfigurationType, new QualifiedName(0, "HistoricalDataConfigurationType"), new LocalizedText("en", "HistoricalDataConfigurationType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType, Identifiers.HasComponent, Identifiers.HistoricalDataConfigurationType_AggregateConfiguration.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType, Identifiers.HasComponent, Identifiers.HistoricalDataConfigurationType_AggregateFunctions.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType, Identifiers.HasProperty, Identifiers.HistoricalDataConfigurationType_Stepped.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType, Identifiers.HasProperty, Identifiers.HistoricalDataConfigurationType_Definition.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType, Identifiers.HasProperty, Identifiers.HistoricalDataConfigurationType_MaxTimeInterval.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType, Identifiers.HasProperty, Identifiers.HistoricalDataConfigurationType_MinTimeInterval.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType, Identifiers.HasProperty, Identifiers.HistoricalDataConfigurationType_ExceptionDeviation.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType, Identifiers.HasProperty, Identifiers.HistoricalDataConfigurationType_ExceptionDeviationFormat.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType, Identifiers.HasProperty, Identifiers.HistoricalDataConfigurationType_StartOfArchive.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType, Identifiers.HasProperty, Identifiers.HistoricalDataConfigurationType_StartOfOnlineArchive.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode75() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.HistoryServerCapabilitiesType, new QualifiedName(0, "HistoryServerCapabilitiesType"), new LocalizedText("en", "HistoryServerCapabilitiesType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType, Identifiers.HasProperty, Identifiers.HistoryServerCapabilitiesType_AccessHistoryDataCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType, Identifiers.HasProperty, Identifiers.HistoryServerCapabilitiesType_AccessHistoryEventsCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType, Identifiers.HasProperty, Identifiers.HistoryServerCapabilitiesType_MaxReturnDataValues.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType, Identifiers.HasProperty, Identifiers.HistoryServerCapabilitiesType_MaxReturnEventValues.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType, Identifiers.HasProperty, Identifiers.HistoryServerCapabilitiesType_InsertDataCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType, Identifiers.HasProperty, Identifiers.HistoryServerCapabilitiesType_ReplaceDataCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType, Identifiers.HasProperty, Identifiers.HistoryServerCapabilitiesType_UpdateDataCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType, Identifiers.HasProperty, Identifiers.HistoryServerCapabilitiesType_DeleteRawCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType, Identifiers.HasProperty, Identifiers.HistoryServerCapabilitiesType_DeleteAtTimeCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType, Identifiers.HasProperty, Identifiers.HistoryServerCapabilitiesType_InsertEventCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType, Identifiers.HasProperty, Identifiers.HistoryServerCapabilitiesType_ReplaceEventCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType, Identifiers.HasProperty, Identifiers.HistoryServerCapabilitiesType_UpdateEventCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType, Identifiers.HasProperty, Identifiers.HistoryServerCapabilitiesType_DeleteEventCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType, Identifiers.HasProperty, Identifiers.HistoryServerCapabilitiesType_InsertAnnotationCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType, Identifiers.HasComponent, Identifiers.HistoryServerCapabilitiesType_AggregateFunctions.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode76() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.DiscreteAlarmType, new QualifiedName(0, "DiscreteAlarmType"), new LocalizedText("en", "DiscreteAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.DiscreteAlarmType, Identifiers.HasSubtype, Identifiers.AlarmConditionType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode77() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AggregateFunctionType, new QualifiedName(0, "AggregateFunctionType"), new LocalizedText("en", "AggregateFunctionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AggregateFunctionType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode78() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ProgramTransitionEventType, new QualifiedName(0, "ProgramTransitionEventType"), new LocalizedText("en", "ProgramTransitionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ProgramTransitionEventType, Identifiers.HasProperty, Identifiers.ProgramTransitionEventType_IntermediateResult.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramTransitionEventType, Identifiers.HasSubtype, Identifiers.TransitionEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode79() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ProgramStateMachineType, new QualifiedName(0, "ProgramStateMachineType"), new LocalizedText("en", "ProgramStateMachineType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_CurrentState.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_LastTransition.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_Creatable.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_Deletable.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_AutoDelete.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_RecycleCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_InstanceCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_MaxInstanceCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_MaxRecycleCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_ProgramDiagnostics.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_FinalResultData.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_Ready.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_Running.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_Suspended.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_Halted.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_HaltedToReady.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_ReadyToRunning.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_RunningToHalted.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_RunningToReady.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_RunningToSuspended.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_SuspendedToRunning.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_SuspendedToHalted.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_SuspendedToReady.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_ReadyToHalted.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_Start.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_Suspend.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_Resume.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_Halt.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasComponent, Identifiers.ProgramStateMachineType_Reset.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType, Identifiers.HasSubtype, Identifiers.FiniteStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode80() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.OffNormalAlarmType, new QualifiedName(0, "OffNormalAlarmType"), new LocalizedText("en", "OffNormalAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.OffNormalAlarmType, Identifiers.HasProperty, Identifiers.OffNormalAlarmType_NormalState.expanded(), true));
        node.addReference(new Reference(Identifiers.OffNormalAlarmType, Identifiers.HasSubtype, Identifiers.DiscreteAlarmType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode81() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.TripAlarmType, new QualifiedName(0, "TripAlarmType"), new LocalizedText("en", "TripAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.TripAlarmType, Identifiers.HasSubtype, Identifiers.OffNormalAlarmType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode82() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.SemanticChangeEventType, new QualifiedName(0, "SemanticChangeEventType"), new LocalizedText("en", "SemanticChangeEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.SemanticChangeEventType, Identifiers.HasProperty, Identifiers.SemanticChangeEventType_Changes.expanded(), true));
        node.addReference(new Reference(Identifiers.SemanticChangeEventType, Identifiers.HasSubtype, Identifiers.BaseModelChangeEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode83() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditUrlMismatchEventType, new QualifiedName(0, "AuditUrlMismatchEventType"), new LocalizedText("en", "AuditUrlMismatchEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditUrlMismatchEventType, Identifiers.HasProperty, Identifiers.AuditUrlMismatchEventType_EndpointUrl.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditUrlMismatchEventType, Identifiers.HasSubtype, Identifiers.AuditCreateSessionEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode84() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.FiniteStateMachineType, new QualifiedName(0, "FiniteStateMachineType"), new LocalizedText("en", "FiniteStateMachineType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.FiniteStateMachineType, Identifiers.HasComponent, Identifiers.FiniteStateMachineType_CurrentState.expanded(), true));
        node.addReference(new Reference(Identifiers.FiniteStateMachineType, Identifiers.HasComponent, Identifiers.FiniteStateMachineType_LastTransition.expanded(), true));
        node.addReference(new Reference(Identifiers.FiniteStateMachineType, Identifiers.HasSubtype, Identifiers.StateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode85() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ConditionType, new QualifiedName(0, "ConditionType"), new LocalizedText("en", "ConditionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.ConditionType, Identifiers.HasProperty, Identifiers.ConditionType_ConditionClassId.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType, Identifiers.HasProperty, Identifiers.ConditionType_ConditionClassName.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType, Identifiers.HasProperty, Identifiers.ConditionType_ConditionName.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType, Identifiers.HasProperty, Identifiers.ConditionType_BranchId.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType, Identifiers.HasProperty, Identifiers.ConditionType_Retain.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType, Identifiers.HasComponent, Identifiers.ConditionType_EnabledState.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType, Identifiers.HasComponent, Identifiers.ConditionType_Quality.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType, Identifiers.HasComponent, Identifiers.ConditionType_LastSeverity.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType, Identifiers.HasComponent, Identifiers.ConditionType_Comment.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType, Identifiers.HasProperty, Identifiers.ConditionType_ClientUserId.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType, Identifiers.HasComponent, Identifiers.ConditionType_Disable.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType, Identifiers.HasComponent, Identifiers.ConditionType_Enable.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType, Identifiers.HasComponent, Identifiers.ConditionType_AddComment.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType, Identifiers.HasComponent, Identifiers.ConditionType_ConditionRefresh.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType, Identifiers.HasComponent, Identifiers.ConditionType_ConditionRefresh2.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType, Identifiers.HasSubtype, Identifiers.BaseEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode86() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.RefreshStartEventType, new QualifiedName(0, "RefreshStartEventType"), new LocalizedText("en", "RefreshStartEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.RefreshStartEventType, Identifiers.HasSubtype, Identifiers.SystemEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode87() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.RefreshEndEventType, new QualifiedName(0, "RefreshEndEventType"), new LocalizedText("en", "RefreshEndEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.RefreshEndEventType, Identifiers.HasSubtype, Identifiers.SystemEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode88() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.RefreshRequiredEventType, new QualifiedName(0, "RefreshRequiredEventType"), new LocalizedText("en", "RefreshRequiredEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.RefreshRequiredEventType, Identifiers.HasSubtype, Identifiers.SystemEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode89() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditConditionEventType, new QualifiedName(0, "AuditConditionEventType"), new LocalizedText("en", "AuditConditionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AuditConditionEventType, Identifiers.HasSubtype, Identifiers.AuditUpdateMethodEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode90() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditConditionEnableEventType, new QualifiedName(0, "AuditConditionEnableEventType"), new LocalizedText("en", "AuditConditionEnableEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AuditConditionEnableEventType, Identifiers.HasSubtype, Identifiers.AuditConditionEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode91() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditConditionCommentEventType, new QualifiedName(0, "AuditConditionCommentEventType"), new LocalizedText("en", "AuditConditionCommentEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AuditConditionCommentEventType, Identifiers.HasProperty, new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(15001), UInteger.valueOf(0)), true));
        node.addReference(new Reference(Identifiers.AuditConditionCommentEventType, Identifiers.HasProperty, Identifiers.AuditConditionCommentEventType_Comment.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditConditionCommentEventType, Identifiers.HasSubtype, Identifiers.AuditConditionEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode92() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.DialogConditionType, new QualifiedName(0, "DialogConditionType"), new LocalizedText("en", "DialogConditionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.DialogConditionType, Identifiers.HasComponent, Identifiers.DialogConditionType_EnabledState.expanded(), true));
        node.addReference(new Reference(Identifiers.DialogConditionType, Identifiers.HasComponent, Identifiers.DialogConditionType_DialogState.expanded(), true));
        node.addReference(new Reference(Identifiers.DialogConditionType, Identifiers.HasProperty, Identifiers.DialogConditionType_Prompt.expanded(), true));
        node.addReference(new Reference(Identifiers.DialogConditionType, Identifiers.HasProperty, Identifiers.DialogConditionType_ResponseOptionSet.expanded(), true));
        node.addReference(new Reference(Identifiers.DialogConditionType, Identifiers.HasProperty, Identifiers.DialogConditionType_DefaultResponse.expanded(), true));
        node.addReference(new Reference(Identifiers.DialogConditionType, Identifiers.HasProperty, Identifiers.DialogConditionType_OkResponse.expanded(), true));
        node.addReference(new Reference(Identifiers.DialogConditionType, Identifiers.HasProperty, Identifiers.DialogConditionType_CancelResponse.expanded(), true));
        node.addReference(new Reference(Identifiers.DialogConditionType, Identifiers.HasProperty, Identifiers.DialogConditionType_LastResponse.expanded(), true));
        node.addReference(new Reference(Identifiers.DialogConditionType, Identifiers.HasComponent, Identifiers.DialogConditionType_Respond.expanded(), true));
        node.addReference(new Reference(Identifiers.DialogConditionType, Identifiers.HasSubtype, Identifiers.ConditionType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode93() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AcknowledgeableConditionType, new QualifiedName(0, "AcknowledgeableConditionType"), new LocalizedText("en", "AcknowledgeableConditionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AcknowledgeableConditionType, Identifiers.HasComponent, Identifiers.AcknowledgeableConditionType_EnabledState.expanded(), true));
        node.addReference(new Reference(Identifiers.AcknowledgeableConditionType, Identifiers.HasComponent, Identifiers.AcknowledgeableConditionType_AckedState.expanded(), true));
        node.addReference(new Reference(Identifiers.AcknowledgeableConditionType, Identifiers.HasComponent, Identifiers.AcknowledgeableConditionType_ConfirmedState.expanded(), true));
        node.addReference(new Reference(Identifiers.AcknowledgeableConditionType, Identifiers.HasComponent, Identifiers.AcknowledgeableConditionType_Acknowledge.expanded(), true));
        node.addReference(new Reference(Identifiers.AcknowledgeableConditionType, Identifiers.HasComponent, Identifiers.AcknowledgeableConditionType_Confirm.expanded(), true));
        node.addReference(new Reference(Identifiers.AcknowledgeableConditionType, Identifiers.HasSubtype, Identifiers.ConditionType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode94() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditConditionShelvingEventType, new QualifiedName(0, "AuditConditionShelvingEventType"), new LocalizedText("en", "AuditConditionShelvingEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AuditConditionShelvingEventType, Identifiers.HasProperty, Identifiers.AuditConditionShelvingEventType_ShelvingTime.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditConditionShelvingEventType, Identifiers.HasSubtype, Identifiers.AuditConditionEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode95() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AlarmConditionType, new QualifiedName(0, "AlarmConditionType"), new LocalizedText("en", "AlarmConditionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AlarmConditionType, Identifiers.HasComponent, Identifiers.AlarmConditionType_EnabledState.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType, Identifiers.HasComponent, Identifiers.AlarmConditionType_ActiveState.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType, Identifiers.HasProperty, Identifiers.AlarmConditionType_InputNode.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType, Identifiers.HasComponent, Identifiers.AlarmConditionType_SuppressedState.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType, Identifiers.HasComponent, Identifiers.AlarmConditionType_ShelvingState.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType, Identifiers.HasProperty, Identifiers.AlarmConditionType_SuppressedOrShelved.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType, Identifiers.HasProperty, Identifiers.AlarmConditionType_MaxTimeShelved.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType, Identifiers.HasSubtype, Identifiers.AcknowledgeableConditionType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode96() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ShelvedStateMachineType, new QualifiedName(0, "ShelvedStateMachineType"), new LocalizedText("en", "ShelvedStateMachineType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType, Identifiers.HasProperty, Identifiers.ShelvedStateMachineType_UnshelveTime.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType_Unshelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType_TimedShelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType_OneShotShelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType_UnshelvedToTimedShelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType_UnshelvedToOneShotShelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType_TimedShelvedToUnshelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType_TimedShelvedToOneShotShelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType_OneShotShelvedToUnshelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType_OneShotShelvedToTimedShelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType_Unshelve.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType_OneShotShelve.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType_TimedShelve.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType, Identifiers.HasSubtype, Identifiers.FiniteStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode97() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.LimitAlarmType, new QualifiedName(0, "LimitAlarmType"), new LocalizedText("en", "LimitAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.LimitAlarmType, Identifiers.HasProperty, Identifiers.LimitAlarmType_HighHighLimit.expanded(), true));
        node.addReference(new Reference(Identifiers.LimitAlarmType, Identifiers.HasProperty, Identifiers.LimitAlarmType_HighLimit.expanded(), true));
        node.addReference(new Reference(Identifiers.LimitAlarmType, Identifiers.HasProperty, Identifiers.LimitAlarmType_LowLimit.expanded(), true));
        node.addReference(new Reference(Identifiers.LimitAlarmType, Identifiers.HasProperty, Identifiers.LimitAlarmType_LowLowLimit.expanded(), true));
        node.addReference(new Reference(Identifiers.LimitAlarmType, Identifiers.HasSubtype, Identifiers.AlarmConditionType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode98() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.BaseConditionClassType, new QualifiedName(0, "BaseConditionClassType"), new LocalizedText("en", "BaseConditionClassType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.BaseConditionClassType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode99() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ProcessConditionClassType, new QualifiedName(0, "ProcessConditionClassType"), new LocalizedText("en", "ProcessConditionClassType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ProcessConditionClassType, Identifiers.HasSubtype, Identifiers.BaseConditionClassType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode100() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.MaintenanceConditionClassType, new QualifiedName(0, "MaintenanceConditionClassType"), new LocalizedText("en", "MaintenanceConditionClassType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.MaintenanceConditionClassType, Identifiers.HasSubtype, Identifiers.BaseConditionClassType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode101() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.SystemConditionClassType, new QualifiedName(0, "SystemConditionClassType"), new LocalizedText("en", "SystemConditionClassType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.SystemConditionClassType, Identifiers.HasSubtype, Identifiers.BaseConditionClassType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode102() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AggregateConfigurationType, new QualifiedName(0, "AggregateConfigurationType"), new LocalizedText("en", "AggregateConfigurationType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AggregateConfigurationType, Identifiers.HasProperty, Identifiers.AggregateConfigurationType_TreatUncertainAsBad.expanded(), true));
        node.addReference(new Reference(Identifiers.AggregateConfigurationType, Identifiers.HasProperty, Identifiers.AggregateConfigurationType_PercentDataBad.expanded(), true));
        node.addReference(new Reference(Identifiers.AggregateConfigurationType, Identifiers.HasProperty, Identifiers.AggregateConfigurationType_PercentDataGood.expanded(), true));
        node.addReference(new Reference(Identifiers.AggregateConfigurationType, Identifiers.HasProperty, Identifiers.AggregateConfigurationType_UseSlopedExtrapolation.expanded(), true));
        node.addReference(new Reference(Identifiers.AggregateConfigurationType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode103() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditHistoryEventUpdateEventType, new QualifiedName(0, "AuditHistoryEventUpdateEventType"), new LocalizedText("en", "AuditHistoryEventUpdateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditHistoryEventUpdateEventType, Identifiers.HasProperty, Identifiers.AuditHistoryEventUpdateEventType_UpdatedNode.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryEventUpdateEventType, Identifiers.HasProperty, Identifiers.AuditHistoryEventUpdateEventType_PerformInsertReplace.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryEventUpdateEventType, Identifiers.HasProperty, Identifiers.AuditHistoryEventUpdateEventType_Filter.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryEventUpdateEventType, Identifiers.HasProperty, Identifiers.AuditHistoryEventUpdateEventType_NewValues.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryEventUpdateEventType, Identifiers.HasProperty, Identifiers.AuditHistoryEventUpdateEventType_OldValues.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryEventUpdateEventType, Identifiers.HasSubtype, Identifiers.AuditHistoryUpdateEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode104() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditHistoryValueUpdateEventType, new QualifiedName(0, "AuditHistoryValueUpdateEventType"), new LocalizedText("en", "AuditHistoryValueUpdateEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditHistoryValueUpdateEventType, Identifiers.HasProperty, Identifiers.AuditHistoryValueUpdateEventType_UpdatedNode.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryValueUpdateEventType, Identifiers.HasProperty, Identifiers.AuditHistoryValueUpdateEventType_PerformInsertReplace.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryValueUpdateEventType, Identifiers.HasProperty, Identifiers.AuditHistoryValueUpdateEventType_NewValues.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryValueUpdateEventType, Identifiers.HasProperty, Identifiers.AuditHistoryValueUpdateEventType_OldValues.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryValueUpdateEventType, Identifiers.HasSubtype, Identifiers.AuditHistoryUpdateEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode105() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditHistoryDeleteEventType, new QualifiedName(0, "AuditHistoryDeleteEventType"), new LocalizedText("en", "AuditHistoryDeleteEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditHistoryDeleteEventType, Identifiers.HasProperty, Identifiers.AuditHistoryDeleteEventType_UpdatedNode.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryDeleteEventType, Identifiers.HasSubtype, Identifiers.AuditHistoryUpdateEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode106() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditHistoryRawModifyDeleteEventType, new QualifiedName(0, "AuditHistoryRawModifyDeleteEventType"), new LocalizedText("en", "AuditHistoryRawModifyDeleteEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditHistoryRawModifyDeleteEventType, Identifiers.HasProperty, Identifiers.AuditHistoryRawModifyDeleteEventType_IsDeleteModified.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryRawModifyDeleteEventType, Identifiers.HasProperty, Identifiers.AuditHistoryRawModifyDeleteEventType_StartTime.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryRawModifyDeleteEventType, Identifiers.HasProperty, Identifiers.AuditHistoryRawModifyDeleteEventType_EndTime.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryRawModifyDeleteEventType, Identifiers.HasProperty, Identifiers.AuditHistoryRawModifyDeleteEventType_OldValues.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryRawModifyDeleteEventType, Identifiers.HasSubtype, Identifiers.AuditHistoryDeleteEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode107() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditHistoryAtTimeDeleteEventType, new QualifiedName(0, "AuditHistoryAtTimeDeleteEventType"), new LocalizedText("en", "AuditHistoryAtTimeDeleteEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditHistoryAtTimeDeleteEventType, Identifiers.HasProperty, Identifiers.AuditHistoryAtTimeDeleteEventType_ReqTimes.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryAtTimeDeleteEventType, Identifiers.HasProperty, Identifiers.AuditHistoryAtTimeDeleteEventType_OldValues.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryAtTimeDeleteEventType, Identifiers.HasSubtype, Identifiers.AuditHistoryDeleteEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode108() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditHistoryEventDeleteEventType, new QualifiedName(0, "AuditHistoryEventDeleteEventType"), new LocalizedText("en", "AuditHistoryEventDeleteEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.AuditHistoryEventDeleteEventType, Identifiers.HasProperty, Identifiers.AuditHistoryEventDeleteEventType_EventIds.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryEventDeleteEventType, Identifiers.HasProperty, Identifiers.AuditHistoryEventDeleteEventType_OldValues.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditHistoryEventDeleteEventType, Identifiers.HasSubtype, Identifiers.AuditHistoryDeleteEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode109() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.EventQueueOverflowEventType, new QualifiedName(0, "EventQueueOverflowEventType"), new LocalizedText("en", "EventQueueOverflowEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.EventQueueOverflowEventType, Identifiers.HasSubtype, Identifiers.BaseEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode110() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ProgressEventType, new QualifiedName(0, "ProgressEventType"), new LocalizedText("en", "ProgressEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.ProgressEventType, Identifiers.HasProperty, Identifiers.ProgressEventType_Context.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgressEventType, Identifiers.HasProperty, Identifiers.ProgressEventType_Progress.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgressEventType, Identifiers.HasSubtype, Identifiers.BaseEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode111() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.SystemStatusChangeEventType, new QualifiedName(0, "SystemStatusChangeEventType"), new LocalizedText("en", "SystemStatusChangeEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.SystemStatusChangeEventType, Identifiers.HasProperty, Identifiers.SystemStatusChangeEventType_SystemState.expanded(), true));
        node.addReference(new Reference(Identifiers.SystemStatusChangeEventType, Identifiers.HasSubtype, Identifiers.SystemEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode112() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.OperationLimitsType, new QualifiedName(0, "OperationLimitsType"), new LocalizedText("en", "OperationLimitsType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.OperationLimitsType, Identifiers.HasProperty, Identifiers.OperationLimitsType_MaxNodesPerRead.expanded(), true));
        node.addReference(new Reference(Identifiers.OperationLimitsType, Identifiers.HasProperty, Identifiers.OperationLimitsType_MaxNodesPerHistoryReadData.expanded(), true));
        node.addReference(new Reference(Identifiers.OperationLimitsType, Identifiers.HasProperty, Identifiers.OperationLimitsType_MaxNodesPerHistoryReadEvents.expanded(), true));
        node.addReference(new Reference(Identifiers.OperationLimitsType, Identifiers.HasProperty, Identifiers.OperationLimitsType_MaxNodesPerWrite.expanded(), true));
        node.addReference(new Reference(Identifiers.OperationLimitsType, Identifiers.HasProperty, Identifiers.OperationLimitsType_MaxNodesPerHistoryUpdateData.expanded(), true));
        node.addReference(new Reference(Identifiers.OperationLimitsType, Identifiers.HasProperty, Identifiers.OperationLimitsType_MaxNodesPerHistoryUpdateEvents.expanded(), true));
        node.addReference(new Reference(Identifiers.OperationLimitsType, Identifiers.HasProperty, Identifiers.OperationLimitsType_MaxNodesPerMethodCall.expanded(), true));
        node.addReference(new Reference(Identifiers.OperationLimitsType, Identifiers.HasProperty, Identifiers.OperationLimitsType_MaxNodesPerBrowse.expanded(), true));
        node.addReference(new Reference(Identifiers.OperationLimitsType, Identifiers.HasProperty, Identifiers.OperationLimitsType_MaxNodesPerRegisterNodes.expanded(), true));
        node.addReference(new Reference(Identifiers.OperationLimitsType, Identifiers.HasProperty, Identifiers.OperationLimitsType_MaxNodesPerTranslateBrowsePathsToNodeIds.expanded(), true));
        node.addReference(new Reference(Identifiers.OperationLimitsType, Identifiers.HasProperty, Identifiers.OperationLimitsType_MaxNodesPerNodeManagement.expanded(), true));
        node.addReference(new Reference(Identifiers.OperationLimitsType, Identifiers.HasProperty, Identifiers.OperationLimitsType_MaxMonitoredItemsPerCall.expanded(), true));
        node.addReference(new Reference(Identifiers.OperationLimitsType, Identifiers.HasSubtype, Identifiers.FolderType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode113() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.FileType, new QualifiedName(0, "FileType"), new LocalizedText("en", "FileType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.FileType, Identifiers.HasProperty, Identifiers.FileType_Size.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType, Identifiers.HasProperty, Identifiers.FileType_Writable.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType, Identifiers.HasProperty, Identifiers.FileType_UserWritable.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType, Identifiers.HasProperty, Identifiers.FileType_OpenCount.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType, Identifiers.HasProperty, Identifiers.FileType_MimeType.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType, Identifiers.HasComponent, Identifiers.FileType_Open.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType, Identifiers.HasComponent, Identifiers.FileType_Close.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType, Identifiers.HasComponent, Identifiers.FileType_Read.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType, Identifiers.HasComponent, Identifiers.FileType_Write.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType, Identifiers.HasComponent, Identifiers.FileType_GetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType, Identifiers.HasComponent, Identifiers.FileType_SetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode114() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AddressSpaceFileType, new QualifiedName(0, "AddressSpaceFileType"), new LocalizedText("en", "AddressSpaceFileType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AddressSpaceFileType, Identifiers.HasComponent, Identifiers.AddressSpaceFileType_ExportNamespace.expanded(), true));
        node.addReference(new Reference(Identifiers.AddressSpaceFileType, Identifiers.HasSubtype, Identifiers.FileType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode115() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.NamespaceMetadataType, new QualifiedName(0, "NamespaceMetadataType"), new LocalizedText("en", "NamespaceMetadataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.NamespaceMetadataType, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_NamespaceUri.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_NamespaceVersion.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_NamespacePublicationDate.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_IsNamespaceSubset.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_StaticNodeIdTypes.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_StaticNumericNodeIdRange.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_StaticStringNodeIdPattern.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType, Identifiers.HasComponent, Identifiers.NamespaceMetadataType_NamespaceFile.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode116() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.NamespacesType, new QualifiedName(0, "NamespacesType"), new LocalizedText("en", "NamespacesType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.NamespacesType, Identifiers.HasComponent, Identifiers.NamespacesType_NamespaceIdentifier_Placeholder.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType, Identifiers.HasComponent, Identifiers.NamespacesType_AddressSpaceFile.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType, Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode117() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.SystemOffNormalAlarmType, new QualifiedName(0, "SystemOffNormalAlarmType"), new LocalizedText("en", "SystemOffNormalAlarmType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.SystemOffNormalAlarmType, Identifiers.HasSubtype, Identifiers.OffNormalAlarmType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode118() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.AuditProgramTransitionEventType, new QualifiedName(0, "AuditProgramTransitionEventType"), new LocalizedText("en", "AuditProgramTransitionEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AuditProgramTransitionEventType, Identifiers.HasProperty, Identifiers.AuditProgramTransitionEventType_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.AuditProgramTransitionEventType, Identifiers.HasSubtype, Identifiers.AuditUpdateStateEventType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode119() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.NonTransparentNetworkRedundancyType, new QualifiedName(0, "NonTransparentNetworkRedundancyType"), new LocalizedText("en", "NonTransparentNetworkRedundancyType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.NonTransparentNetworkRedundancyType, Identifiers.HasProperty, Identifiers.NonTransparentNetworkRedundancyType_ServerNetworkGroups.expanded(), true));
        node.addReference(new Reference(Identifiers.NonTransparentNetworkRedundancyType, Identifiers.HasSubtype, Identifiers.NonTransparentRedundancyType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode120() {
        UaObjectTypeNode node = new UaObjectTypeNode(this.context, Identifiers.ProgramTransitionAuditEventType, new QualifiedName(0, "ProgramTransitionAuditEventType"), new LocalizedText("en", "ProgramTransitionAuditEventType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ProgramTransitionAuditEventType, Identifiers.HasComponent, Identifiers.ProgramTransitionAuditEventType_Transition.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramTransitionAuditEventType, Identifiers.HasSubtype, Identifiers.AuditUpdateStateEventType.expanded(), false));
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
