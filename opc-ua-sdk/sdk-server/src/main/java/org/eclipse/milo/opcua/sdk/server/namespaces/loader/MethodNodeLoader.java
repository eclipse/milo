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
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

class MethodNodeLoader {
    private final UaNodeContext context;

    private final NodeManager<UaNode> nodeManager;

    MethodNodeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager) {
        this.context = context;
        this.nodeManager = nodeManager;
    }

    private void loadNode0() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.TrustListType_OpenWithMasks, new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.TrustListType_OpenWithMasks, Identifiers.HasProperty, Identifiers.TrustListType_OpenWithMasks_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListType_OpenWithMasks, Identifiers.HasProperty, Identifiers.TrustListType_OpenWithMasks_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListType_OpenWithMasks, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListType_OpenWithMasks, Identifiers.HasComponent, Identifiers.TrustListType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode1() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.TrustListType_CloseAndUpdate, new QualifiedName(0, "CloseAndUpdate"), new LocalizedText("en", "CloseAndUpdate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.TrustListType_CloseAndUpdate, Identifiers.HasProperty, Identifiers.TrustListType_CloseAndUpdate_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListType_CloseAndUpdate, Identifiers.HasProperty, Identifiers.TrustListType_CloseAndUpdate_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListType_CloseAndUpdate, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListType_CloseAndUpdate, Identifiers.HasComponent, Identifiers.TrustListType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode2() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.TrustListType_AddCertificate, new QualifiedName(0, "AddCertificate"), new LocalizedText("en", "AddCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.TrustListType_AddCertificate, Identifiers.HasProperty, Identifiers.TrustListType_AddCertificate_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListType_AddCertificate, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListType_AddCertificate, Identifiers.HasComponent, Identifiers.TrustListType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode3() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.TrustListType_RemoveCertificate, new QualifiedName(0, "RemoveCertificate"), new LocalizedText("en", "RemoveCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.TrustListType_RemoveCertificate, Identifiers.HasProperty, Identifiers.TrustListType_RemoveCertificate_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListType_RemoveCertificate, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListType_RemoveCertificate, Identifiers.HasComponent, Identifiers.TrustListType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode4() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfigurationType_UpdateCertificate, new QualifiedName(0, "UpdateCertificate"), new LocalizedText("en", "UpdateCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfigurationType_UpdateCertificate, Identifiers.HasProperty, Identifiers.ServerConfigurationType_UpdateCertificate_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_UpdateCertificate, Identifiers.HasProperty, Identifiers.ServerConfigurationType_UpdateCertificate_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_UpdateCertificate, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_UpdateCertificate, Identifiers.HasComponent, Identifiers.ServerConfigurationType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode5() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Open, new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Open, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Open_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Open, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Open_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Open, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode6() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Close, new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Close, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Close_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Close, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode7() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Read, new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Read, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Read_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Read, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Read_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Read, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode8() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Write, new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Write, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Write_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Write, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode9() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_GetPosition, new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_GetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_GetPosition_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_GetPosition, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode10() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_SetPosition, new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_SetPosition, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_SetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_SetPosition, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode11() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_OpenWithMasks, new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_OpenWithMasks_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_OpenWithMasks_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_OpenWithMasks, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode12() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_CloseAndUpdate, new QualifiedName(0, "CloseAndUpdate"), new LocalizedText("en", "CloseAndUpdate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_CloseAndUpdate, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_CloseAndUpdate_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_CloseAndUpdate, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_CloseAndUpdate_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_CloseAndUpdate, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode13() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_AddCertificate, new QualifiedName(0, "AddCertificate"), new LocalizedText("en", "AddCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_AddCertificate, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_AddCertificate_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_AddCertificate, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode14() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_RemoveCertificate, new QualifiedName(0, "RemoveCertificate"), new LocalizedText("en", "RemoveCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_RemoveCertificate, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_RemoveCertificate_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_RemoveCertificate, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode15() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfigurationType_CreateSigningRequest, new QualifiedName(0, "CreateSigningRequest"), new LocalizedText("en", "CreateSigningRequest"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CreateSigningRequest, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CreateSigningRequest_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CreateSigningRequest, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CreateSigningRequest_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CreateSigningRequest, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CreateSigningRequest, Identifiers.HasComponent, Identifiers.ServerConfigurationType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode16() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfigurationType_ApplyChanges, new QualifiedName(0, "ApplyChanges"), new LocalizedText("en", "ApplyChanges"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfigurationType_ApplyChanges, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_ApplyChanges, Identifiers.HasComponent, Identifiers.ServerConfigurationType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode17() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CreateSigningRequest, new QualifiedName(0, "CreateSigningRequest"), new LocalizedText("en", "CreateSigningRequest"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CreateSigningRequest, Identifiers.HasProperty, Identifiers.ServerConfiguration_CreateSigningRequest_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CreateSigningRequest, Identifiers.HasProperty, Identifiers.ServerConfiguration_CreateSigningRequest_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CreateSigningRequest, Identifiers.HasComponent, Identifiers.ServerConfiguration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode18() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_ApplyChanges, new QualifiedName(0, "ApplyChanges"), new LocalizedText("en", "ApplyChanges"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_ApplyChanges, Identifiers.HasComponent, Identifiers.ServerConfiguration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode19() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerType_SetSubscriptionDurable, new QualifiedName(0, "SetSubscriptionDurable"), new LocalizedText("en", "SetSubscriptionDurable"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerType_SetSubscriptionDurable, Identifiers.HasProperty, Identifiers.ServerType_SetSubscriptionDurable_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_SetSubscriptionDurable, Identifiers.HasProperty, Identifiers.ServerType_SetSubscriptionDurable_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_SetSubscriptionDurable, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_SetSubscriptionDurable, Identifiers.HasComponent, Identifiers.ServerType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode20() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.Server_SetSubscriptionDurable, new QualifiedName(0, "SetSubscriptionDurable"), new LocalizedText("en", "SetSubscriptionDurable"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.Server_SetSubscriptionDurable, Identifiers.HasProperty, Identifiers.Server_SetSubscriptionDurable_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_SetSubscriptionDurable, Identifiers.HasProperty, Identifiers.Server_SetSubscriptionDurable_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_SetSubscriptionDurable, Identifiers.HasComponent, Identifiers.Server.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode21() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfigurationType_GetRejectedList, new QualifiedName(0, "GetRejectedList"), new LocalizedText("en", "GetRejectedList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfigurationType_GetRejectedList, Identifiers.HasProperty, Identifiers.ServerConfigurationType_GetRejectedList_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_GetRejectedList, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_GetRejectedList, Identifiers.HasComponent, Identifiers.ServerConfigurationType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode22() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_GetRejectedList, new QualifiedName(0, "GetRejectedList"), new LocalizedText("en", "GetRejectedList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_GetRejectedList, Identifiers.HasProperty, Identifiers.ServerConfiguration_GetRejectedList_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_GetRejectedList, Identifiers.HasComponent, Identifiers.ServerConfiguration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode23() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerType_ResendData, new QualifiedName(0, "ResendData"), new LocalizedText("en", "ResendData"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerType_ResendData, Identifiers.HasProperty, Identifiers.ServerType_ResendData_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ResendData, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ResendData, Identifiers.HasComponent, Identifiers.ServerType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode24() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.Server_ResendData, new QualifiedName(0, "ResendData"), new LocalizedText("en", "ResendData"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.Server_ResendData, Identifiers.HasProperty, Identifiers.Server_ResendData_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ResendData, Identifiers.HasComponent, Identifiers.Server.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode25() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerType_RequestServerStateChange, new QualifiedName(0, "RequestServerStateChange"), new LocalizedText("en", "RequestServerStateChange"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerType_RequestServerStateChange, Identifiers.HasProperty, Identifiers.ServerType_RequestServerStateChange_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_RequestServerStateChange, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_RequestServerStateChange, Identifiers.HasComponent, Identifiers.ServerType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode26() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.Server_RequestServerStateChange, new QualifiedName(0, "RequestServerStateChange"), new LocalizedText("en", "RequestServerStateChange"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.Server_RequestServerStateChange, Identifiers.HasProperty, Identifiers.Server_RequestServerStateChange_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_RequestServerStateChange, Identifiers.HasComponent, Identifiers.Server.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode27() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ConditionType_ConditionRefresh2, new QualifiedName(0, "ConditionRefresh2"), new LocalizedText("en", "ConditionRefresh2"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ConditionType_ConditionRefresh2, Identifiers.HasProperty, Identifiers.ConditionType_ConditionRefresh2_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType_ConditionRefresh2, Identifiers.AlwaysGeneratesEvent, Identifiers.RefreshStartEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType_ConditionRefresh2, Identifiers.AlwaysGeneratesEvent, Identifiers.RefreshEndEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType_ConditionRefresh2, Identifiers.HasComponent, Identifiers.ConditionType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode28() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ConditionType_Enable, new QualifiedName(0, "Enable"), new LocalizedText("en", "Enable"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ConditionType_Enable, Identifiers.AlwaysGeneratesEvent, Identifiers.AuditConditionEnableEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType_Enable, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType_Enable, Identifiers.HasComponent, Identifiers.ConditionType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode29() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ConditionType_Disable, new QualifiedName(0, "Disable"), new LocalizedText("en", "Disable"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ConditionType_Disable, Identifiers.AlwaysGeneratesEvent, Identifiers.AuditConditionEnableEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType_Disable, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType_Disable, Identifiers.HasComponent, Identifiers.ConditionType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode30() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ConditionType_AddComment, new QualifiedName(0, "AddComment"), new LocalizedText("en", "AddComment"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ConditionType_AddComment, Identifiers.HasProperty, Identifiers.ConditionType_AddComment_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType_AddComment, Identifiers.AlwaysGeneratesEvent, Identifiers.AuditConditionCommentEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType_AddComment, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType_AddComment, Identifiers.HasComponent, Identifiers.ConditionType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode31() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.DialogConditionType_Respond, new QualifiedName(0, "Respond"), new LocalizedText("en", "Respond"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.DialogConditionType_Respond, Identifiers.HasProperty, Identifiers.DialogConditionType_Respond_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.DialogConditionType_Respond, Identifiers.AlwaysGeneratesEvent, Identifiers.AuditConditionRespondEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.DialogConditionType_Respond, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.DialogConditionType_Respond, Identifiers.HasComponent, Identifiers.DialogConditionType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode32() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.AcknowledgeableConditionType_Acknowledge, new QualifiedName(0, "Acknowledge"), new LocalizedText("en", "Acknowledge"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.AcknowledgeableConditionType_Acknowledge, Identifiers.HasProperty, Identifiers.AcknowledgeableConditionType_Acknowledge_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.AcknowledgeableConditionType_Acknowledge, Identifiers.AlwaysGeneratesEvent, Identifiers.AuditConditionAcknowledgeEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.AcknowledgeableConditionType_Acknowledge, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.AcknowledgeableConditionType_Acknowledge, Identifiers.HasComponent, Identifiers.AcknowledgeableConditionType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode33() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.AcknowledgeableConditionType_Confirm, new QualifiedName(0, "Confirm"), new LocalizedText("en", "Confirm"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.AcknowledgeableConditionType_Confirm, Identifiers.HasProperty, Identifiers.AcknowledgeableConditionType_Confirm_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.AcknowledgeableConditionType_Confirm, Identifiers.AlwaysGeneratesEvent, Identifiers.AuditConditionConfirmEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.AcknowledgeableConditionType_Confirm, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.AcknowledgeableConditionType_Confirm, Identifiers.HasComponent, Identifiers.AcknowledgeableConditionType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode34() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.AlarmConditionType_ShelvingState_Unshelve, new QualifiedName(0, "Unshelve"), new LocalizedText("en", "Unshelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState_Unshelve, Identifiers.AlwaysGeneratesEvent, Identifiers.AuditConditionShelvingEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState_Unshelve, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState_Unshelve, Identifiers.HasComponent, Identifiers.AlarmConditionType_ShelvingState.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode35() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.AlarmConditionType_ShelvingState_OneShotShelve, new QualifiedName(0, "OneShotShelve"), new LocalizedText("en", "OneShotShelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState_OneShotShelve, Identifiers.AlwaysGeneratesEvent, Identifiers.AuditConditionShelvingEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState_OneShotShelve, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState_OneShotShelve, Identifiers.HasComponent, Identifiers.AlarmConditionType_ShelvingState.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode36() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.AlarmConditionType_ShelvingState_TimedShelve, new QualifiedName(0, "TimedShelve"), new LocalizedText("en", "TimedShelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState_TimedShelve, Identifiers.HasProperty, Identifiers.AlarmConditionType_ShelvingState_TimedShelve_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState_TimedShelve, Identifiers.AlwaysGeneratesEvent, Identifiers.AuditConditionShelvingEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState_TimedShelve, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState_TimedShelve, Identifiers.HasComponent, Identifiers.AlarmConditionType_ShelvingState.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode37() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_CreateDirectory, new QualifiedName(0, "CreateDirectory"), new LocalizedText("en", "CreateDirectory"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_CreateDirectory, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_CreateDirectory_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_CreateDirectory, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_CreateDirectory_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_CreateDirectory, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_CreateDirectory, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode38() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_CreateFile, new QualifiedName(0, "CreateFile"), new LocalizedText("en", "CreateFile"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_CreateFile, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_CreateFile_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_CreateFile, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_CreateFile_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_CreateFile, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_CreateFile, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode39() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_Delete, new QualifiedName(0, "Delete"), new LocalizedText("en", "Delete"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_Delete, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_Delete_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_Delete, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_Delete, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode40() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_MoveOrCopy, new QualifiedName(0, "MoveOrCopy"), new LocalizedText("en", "MoveOrCopy"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_MoveOrCopy, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_MoveOrCopy_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_MoveOrCopy, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_MoveOrCopy_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_MoveOrCopy, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_MoveOrCopy, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode41() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileDirectoryType_FileName_Placeholder_Open, new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_Open, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileName_Placeholder_Open_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_Open, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileName_Placeholder_Open_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_Open, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_Open, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileName_Placeholder.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode42() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileDirectoryType_FileName_Placeholder_Close, new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_Close, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileName_Placeholder_Close_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_Close, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_Close, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileName_Placeholder.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode43() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileDirectoryType_FileName_Placeholder_Read, new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_Read, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileName_Placeholder_Read_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_Read, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileName_Placeholder_Read_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_Read, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_Read, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileName_Placeholder.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode44() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileDirectoryType_FileName_Placeholder_Write, new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_Write, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileName_Placeholder_Write_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_Write, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_Write, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileName_Placeholder.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode45() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileDirectoryType_FileName_Placeholder_GetPosition, new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_GetPosition, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileName_Placeholder_GetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_GetPosition, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileName_Placeholder_GetPosition_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_GetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_GetPosition, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileName_Placeholder.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode46() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileDirectoryType_FileName_Placeholder_SetPosition, new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_SetPosition, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileName_Placeholder_SetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_SetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder_SetPosition, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileName_Placeholder.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode47() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileDirectoryType_CreateDirectory, new QualifiedName(0, "CreateDirectory"), new LocalizedText("en", "CreateDirectory"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileDirectoryType_CreateDirectory, Identifiers.HasProperty, Identifiers.FileDirectoryType_CreateDirectory_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_CreateDirectory, Identifiers.HasProperty, Identifiers.FileDirectoryType_CreateDirectory_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_CreateDirectory, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_CreateDirectory, Identifiers.HasComponent, Identifiers.FileDirectoryType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode48() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileDirectoryType_CreateFile, new QualifiedName(0, "CreateFile"), new LocalizedText("en", "CreateFile"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileDirectoryType_CreateFile, Identifiers.HasProperty, Identifiers.FileDirectoryType_CreateFile_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_CreateFile, Identifiers.HasProperty, Identifiers.FileDirectoryType_CreateFile_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_CreateFile, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_CreateFile, Identifiers.HasComponent, Identifiers.FileDirectoryType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode49() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileDirectoryType_Delete, new QualifiedName(0, "Delete"), new LocalizedText("en", "Delete"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileDirectoryType_Delete, Identifiers.HasProperty, Identifiers.FileDirectoryType_Delete_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_Delete, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_Delete, Identifiers.HasComponent, Identifiers.FileDirectoryType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode50() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileDirectoryType_MoveOrCopy, new QualifiedName(0, "MoveOrCopy"), new LocalizedText("en", "MoveOrCopy"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileDirectoryType_MoveOrCopy, Identifiers.HasProperty, Identifiers.FileDirectoryType_MoveOrCopy_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_MoveOrCopy, Identifiers.HasProperty, Identifiers.FileDirectoryType_MoveOrCopy_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_MoveOrCopy, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_MoveOrCopy, Identifiers.HasComponent, Identifiers.FileDirectoryType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode51() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupType_TrustList_Open, new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_Open, Identifiers.HasProperty, Identifiers.CertificateGroupType_TrustList_Open_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_Open, Identifiers.HasProperty, Identifiers.CertificateGroupType_TrustList_Open_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_Open, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_Open, Identifiers.HasComponent, Identifiers.CertificateGroupType_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode52() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupType_TrustList_Close, new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_Close, Identifiers.HasProperty, Identifiers.CertificateGroupType_TrustList_Close_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_Close, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_Close, Identifiers.HasComponent, Identifiers.CertificateGroupType_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode53() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupType_TrustList_Read, new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_Read, Identifiers.HasProperty, Identifiers.CertificateGroupType_TrustList_Read_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_Read, Identifiers.HasProperty, Identifiers.CertificateGroupType_TrustList_Read_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_Read, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_Read, Identifiers.HasComponent, Identifiers.CertificateGroupType_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode54() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupType_TrustList_Write, new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_Write, Identifiers.HasProperty, Identifiers.CertificateGroupType_TrustList_Write_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_Write, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_Write, Identifiers.HasComponent, Identifiers.CertificateGroupType_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode55() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupType_TrustList_GetPosition, new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.CertificateGroupType_TrustList_GetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.CertificateGroupType_TrustList_GetPosition_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_GetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_GetPosition, Identifiers.HasComponent, Identifiers.CertificateGroupType_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode56() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupType_TrustList_SetPosition, new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_SetPosition, Identifiers.HasProperty, Identifiers.CertificateGroupType_TrustList_SetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_SetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_SetPosition, Identifiers.HasComponent, Identifiers.CertificateGroupType_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode57() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupType_TrustList_OpenWithMasks, new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.CertificateGroupType_TrustList_OpenWithMasks_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.CertificateGroupType_TrustList_OpenWithMasks_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_OpenWithMasks, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList_OpenWithMasks, Identifiers.HasComponent, Identifiers.CertificateGroupType_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode58() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_UpdateCertificate, new QualifiedName(0, "UpdateCertificate"), new LocalizedText("en", "UpdateCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_UpdateCertificate, Identifiers.HasProperty, Identifiers.ServerConfiguration_UpdateCertificate_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_UpdateCertificate, Identifiers.HasProperty, Identifiers.ServerConfiguration_UpdateCertificate_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_UpdateCertificate, Identifiers.HasComponent, Identifiers.ServerConfiguration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode59() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Open, new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Open, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Open_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Open, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Open_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Open, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Open, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode60() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Close, new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Close, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Close_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Close, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Close, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode61() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Read, new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Read, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Read_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Read, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Read_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Read, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Read, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode62() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Write, new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Write, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Write_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Write, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Write, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode63() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_GetPosition, new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_GetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_GetPosition_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_GetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_GetPosition, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode64() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_SetPosition, new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_SetPosition, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_SetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_SetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_SetPosition, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode65() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_OpenWithMasks, new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_OpenWithMasks_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_OpenWithMasks_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_OpenWithMasks, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_OpenWithMasks, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode66() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Open, new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Open, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Open_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Open, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Open_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Open, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Open, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode67() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Close, new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Close, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Close_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Close, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Close, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode68() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Read, new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Read, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Read_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Read, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Read_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Read, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Read, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode69() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Write, new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Write, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Write_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Write, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Write, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode70() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_GetPosition, new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_GetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_GetPosition_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_GetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_GetPosition, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode71() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_SetPosition, new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_SetPosition, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_SetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_SetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_SetPosition, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode72() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_OpenWithMasks, new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_OpenWithMasks_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_OpenWithMasks_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_OpenWithMasks, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_OpenWithMasks, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode73() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Open, new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Open, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Open_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Open, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Open_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Open, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Open, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode74() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Close, new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Close, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Close_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Close, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Close, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode75() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Read, new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Read, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Read_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Read, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Read_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Read, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Read, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode76() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Write, new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Write, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Write_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Write, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Write, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode77() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_GetPosition, new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_GetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_GetPosition_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_GetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_GetPosition, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode78() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_SetPosition, new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_SetPosition, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_SetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_SetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_SetPosition, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode79() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_OpenWithMasks, new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_OpenWithMasks_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_OpenWithMasks_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_OpenWithMasks, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_OpenWithMasks, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode80() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Open, new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Open, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Open_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Open, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Open_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Open, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Open, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode81() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Close, new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Close, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Close_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Close, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Close, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode82() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Read, new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Read, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Read_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Read, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Read_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Read, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Read, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode83() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Write, new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Write, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Write_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Write, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Write, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode84() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_GetPosition, new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_GetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_GetPosition_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_GetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_GetPosition, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode85() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_SetPosition, new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_SetPosition, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_SetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_SetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_SetPosition, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode86() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_OpenWithMasks, new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_OpenWithMasks_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_OpenWithMasks_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_OpenWithMasks, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_OpenWithMasks, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode87() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Open, new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Open, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Open_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Open, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Open_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Open, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Open, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode88() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Close, new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Close, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Close_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Close, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Close, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode89() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Read, new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Read, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Read_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Read, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Read_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Read, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Read, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode90() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Write, new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Write, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Write_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Write, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Write, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode91() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_GetPosition, new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_GetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_GetPosition_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_GetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_GetPosition, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode92() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_SetPosition, new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_SetPosition, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_SetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_SetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_SetPosition, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode93() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_OpenWithMasks, new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_OpenWithMasks_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_OpenWithMasks_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_OpenWithMasks, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_OpenWithMasks, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode94() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Open, new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Open, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Open_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Open, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Open_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Open, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode95() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Close, new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Close, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Close_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Close, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode96() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Read, new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Read, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Read_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Read, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Read_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Read, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode97() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Write, new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Write, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Write_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Write, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode98() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_GetPosition, new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_GetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_GetPosition_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_GetPosition, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode99() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_SetPosition, new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_SetPosition, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_SetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_SetPosition, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode100() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_OpenWithMasks, new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_OpenWithMasks_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_OpenWithMasks_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_OpenWithMasks, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode101() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_CloseAndUpdate, new QualifiedName(0, "CloseAndUpdate"), new LocalizedText("en", "CloseAndUpdate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_CloseAndUpdate, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_CloseAndUpdate_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_CloseAndUpdate, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_CloseAndUpdate_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_CloseAndUpdate, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode102() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_AddCertificate, new QualifiedName(0, "AddCertificate"), new LocalizedText("en", "AddCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_AddCertificate, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_AddCertificate_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_AddCertificate, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode103() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_RemoveCertificate, new QualifiedName(0, "RemoveCertificate"), new LocalizedText("en", "RemoveCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_RemoveCertificate, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_RemoveCertificate_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_RemoveCertificate, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode104() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Open, new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Open, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Open_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Open, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Open_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Open, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode105() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Close, new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Close, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Close_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Close, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode106() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Read, new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Read, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Read_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Read, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Read_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Read, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode107() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Write, new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Write, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Write_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Write, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode108() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_GetPosition, new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_GetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_GetPosition, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_GetPosition_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_GetPosition, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode109() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_SetPosition, new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_SetPosition, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_SetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_SetPosition, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode110() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_OpenWithMasks, new QualifiedName(0, "OpenWithMasks"), new LocalizedText("en", "OpenWithMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_OpenWithMasks_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_OpenWithMasks, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_OpenWithMasks_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_OpenWithMasks, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode111() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_CloseAndUpdate, new QualifiedName(0, "CloseAndUpdate"), new LocalizedText("en", "CloseAndUpdate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_CloseAndUpdate, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_CloseAndUpdate_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_CloseAndUpdate, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_CloseAndUpdate_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_CloseAndUpdate, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode112() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_AddCertificate, new QualifiedName(0, "AddCertificate"), new LocalizedText("en", "AddCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_AddCertificate, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_AddCertificate_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_AddCertificate, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode113() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_RemoveCertificate, new QualifiedName(0, "RemoveCertificate"), new LocalizedText("en", "RemoveCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_RemoveCertificate, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_RemoveCertificate_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_RemoveCertificate, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode114() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ProgramStateMachineType_Start, new QualifiedName(0, "Start"), new LocalizedText("en", "Start"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Start, Identifiers.HasCause, Identifiers.ProgramStateMachineType_ReadyToRunning.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Start, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Start, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode115() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ProgramStateMachineType_Suspend, new QualifiedName(0, "Suspend"), new LocalizedText("en", "Suspend"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Suspend, Identifiers.HasCause, Identifiers.ProgramStateMachineType_RunningToSuspended.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Suspend, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Suspend, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode116() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ProgramStateMachineType_Resume, new QualifiedName(0, "Resume"), new LocalizedText("en", "Resume"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Resume, Identifiers.HasCause, Identifiers.ProgramStateMachineType_SuspendedToRunning.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Resume, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Resume, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode117() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ProgramStateMachineType_Halt, new QualifiedName(0, "Halt"), new LocalizedText("en", "Halt"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Halt, Identifiers.HasCause, Identifiers.ProgramStateMachineType_RunningToHalted.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Halt, Identifiers.HasCause, Identifiers.ProgramStateMachineType_SuspendedToHalted.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Halt, Identifiers.HasCause, Identifiers.ProgramStateMachineType_ReadyToHalted.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Halt, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Halt, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode118() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ProgramStateMachineType_Reset, new QualifiedName(0, "Reset"), new LocalizedText("en", "Reset"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Reset, Identifiers.HasCause, Identifiers.ProgramStateMachineType_HaltedToReady.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Reset, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Reset, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode119() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ShelvedStateMachineType_Unshelve, new QualifiedName(0, "Unshelve"), new LocalizedText("en", "Unshelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_Unshelve, Identifiers.HasCause, Identifiers.ShelvedStateMachineType_TimedShelvedToUnshelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_Unshelve, Identifiers.HasCause, Identifiers.ShelvedStateMachineType_OneShotShelvedToUnshelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_Unshelve, Identifiers.AlwaysGeneratesEvent, Identifiers.AuditConditionShelvingEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_Unshelve, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_Unshelve, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode120() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ShelvedStateMachineType_OneShotShelve, new QualifiedName(0, "OneShotShelve"), new LocalizedText("en", "OneShotShelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelve, Identifiers.HasCause, Identifiers.ShelvedStateMachineType_UnshelvedToOneShotShelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelve, Identifiers.HasCause, Identifiers.ShelvedStateMachineType_TimedShelvedToOneShotShelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelve, Identifiers.AlwaysGeneratesEvent, Identifiers.AuditConditionShelvingEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelve, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelve, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode121() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ShelvedStateMachineType_TimedShelve, new QualifiedName(0, "TimedShelve"), new LocalizedText("en", "TimedShelve"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelve, Identifiers.HasProperty, Identifiers.ShelvedStateMachineType_TimedShelve_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelve, Identifiers.HasCause, Identifiers.ShelvedStateMachineType_UnshelvedToTimedShelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelve, Identifiers.HasCause, Identifiers.ShelvedStateMachineType_OneShotShelvedToTimedShelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelve, Identifiers.AlwaysGeneratesEvent, Identifiers.AuditConditionShelvingEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelve, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelve, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode122() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ServerType_GetMonitoredItems, new QualifiedName(0, "GetMonitoredItems"), new LocalizedText("en", "GetMonitoredItems"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ServerType_GetMonitoredItems, Identifiers.HasProperty, Identifiers.ServerType_GetMonitoredItems_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_GetMonitoredItems, Identifiers.HasProperty, Identifiers.ServerType_GetMonitoredItems_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_GetMonitoredItems, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_GetMonitoredItems, Identifiers.HasComponent, Identifiers.ServerType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode123() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.Server_GetMonitoredItems, new QualifiedName(0, "GetMonitoredItems"), new LocalizedText("en", "GetMonitoredItems"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.Server_GetMonitoredItems, Identifiers.HasProperty, Identifiers.Server_GetMonitoredItems_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_GetMonitoredItems, Identifiers.HasProperty, Identifiers.Server_GetMonitoredItems_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_GetMonitoredItems, Identifiers.HasComponent, Identifiers.Server.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode124() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileType_Open, new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileType_Open, Identifiers.HasProperty, Identifiers.FileType_Open_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType_Open, Identifiers.HasProperty, Identifiers.FileType_Open_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType_Open, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType_Open, Identifiers.HasComponent, Identifiers.FileType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode125() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileType_Close, new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileType_Close, Identifiers.HasProperty, Identifiers.FileType_Close_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType_Close, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType_Close, Identifiers.HasComponent, Identifiers.FileType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode126() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileType_Read, new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileType_Read, Identifiers.HasProperty, Identifiers.FileType_Read_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType_Read, Identifiers.HasProperty, Identifiers.FileType_Read_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType_Read, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType_Read, Identifiers.HasComponent, Identifiers.FileType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode127() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileType_Write, new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileType_Write, Identifiers.HasProperty, Identifiers.FileType_Write_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType_Write, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType_Write, Identifiers.HasComponent, Identifiers.FileType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode128() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileType_GetPosition, new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileType_GetPosition, Identifiers.HasProperty, Identifiers.FileType_GetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType_GetPosition, Identifiers.HasProperty, Identifiers.FileType_GetPosition_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType_GetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType_GetPosition, Identifiers.HasComponent, Identifiers.FileType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode129() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.FileType_SetPosition, new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.FileType_SetPosition, Identifiers.HasProperty, Identifiers.FileType_SetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType_SetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileType_SetPosition, Identifiers.HasComponent, Identifiers.FileType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode130() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.AddressSpaceFileType_ExportNamespace, new QualifiedName(0, "ExportNamespace"), new LocalizedText("en", "ExportNamespace"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.AddressSpaceFileType_ExportNamespace, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.AddressSpaceFileType_ExportNamespace, Identifiers.HasComponent, Identifiers.AddressSpaceFileType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode131() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.NamespaceMetadataType_NamespaceFile_Open, new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_Open, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_NamespaceFile_Open_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_Open, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_NamespaceFile_Open_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_Open, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_Open, Identifiers.HasComponent, Identifiers.NamespaceMetadataType_NamespaceFile.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode132() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.NamespaceMetadataType_NamespaceFile_Close, new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_Close, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_NamespaceFile_Close_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_Close, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_Close, Identifiers.HasComponent, Identifiers.NamespaceMetadataType_NamespaceFile.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode133() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.NamespaceMetadataType_NamespaceFile_Read, new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_Read, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_NamespaceFile_Read_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_Read, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_NamespaceFile_Read_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_Read, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_Read, Identifiers.HasComponent, Identifiers.NamespaceMetadataType_NamespaceFile.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode134() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.NamespaceMetadataType_NamespaceFile_Write, new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_Write, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_NamespaceFile_Write_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_Write, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_Write, Identifiers.HasComponent, Identifiers.NamespaceMetadataType_NamespaceFile.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode135() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.NamespaceMetadataType_NamespaceFile_GetPosition, new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_GetPosition, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_NamespaceFile_GetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_GetPosition, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_NamespaceFile_GetPosition_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_GetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_GetPosition, Identifiers.HasComponent, Identifiers.NamespaceMetadataType_NamespaceFile.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode136() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.NamespaceMetadataType_NamespaceFile_SetPosition, new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_SetPosition, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_NamespaceFile_SetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_SetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile_SetPosition, Identifiers.HasComponent, Identifiers.NamespaceMetadataType_NamespaceFile.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode137() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.NamespacesType_AddressSpaceFile_Open, new QualifiedName(0, "Open"), new LocalizedText("en", "Open"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_Open, Identifiers.HasProperty, Identifiers.NamespacesType_AddressSpaceFile_Open_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_Open, Identifiers.HasProperty, Identifiers.NamespacesType_AddressSpaceFile_Open_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_Open, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_Open, Identifiers.HasComponent, Identifiers.NamespacesType_AddressSpaceFile.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode138() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.NamespacesType_AddressSpaceFile_Close, new QualifiedName(0, "Close"), new LocalizedText("en", "Close"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_Close, Identifiers.HasProperty, Identifiers.NamespacesType_AddressSpaceFile_Close_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_Close, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_Close, Identifiers.HasComponent, Identifiers.NamespacesType_AddressSpaceFile.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode139() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.NamespacesType_AddressSpaceFile_Read, new QualifiedName(0, "Read"), new LocalizedText("en", "Read"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_Read, Identifiers.HasProperty, Identifiers.NamespacesType_AddressSpaceFile_Read_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_Read, Identifiers.HasProperty, Identifiers.NamespacesType_AddressSpaceFile_Read_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_Read, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_Read, Identifiers.HasComponent, Identifiers.NamespacesType_AddressSpaceFile.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode140() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.NamespacesType_AddressSpaceFile_Write, new QualifiedName(0, "Write"), new LocalizedText("en", "Write"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_Write, Identifiers.HasProperty, Identifiers.NamespacesType_AddressSpaceFile_Write_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_Write, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_Write, Identifiers.HasComponent, Identifiers.NamespacesType_AddressSpaceFile.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode141() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.NamespacesType_AddressSpaceFile_GetPosition, new QualifiedName(0, "GetPosition"), new LocalizedText("en", "GetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_GetPosition, Identifiers.HasProperty, Identifiers.NamespacesType_AddressSpaceFile_GetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_GetPosition, Identifiers.HasProperty, Identifiers.NamespacesType_AddressSpaceFile_GetPosition_OutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_GetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_GetPosition, Identifiers.HasComponent, Identifiers.NamespacesType_AddressSpaceFile.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode142() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.NamespacesType_AddressSpaceFile_SetPosition, new QualifiedName(0, "SetPosition"), new LocalizedText("en", "SetPosition"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_SetPosition, Identifiers.HasProperty, Identifiers.NamespacesType_AddressSpaceFile_SetPosition_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_SetPosition, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile_SetPosition, Identifiers.HasComponent, Identifiers.NamespacesType_AddressSpaceFile.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode143() {
        UaMethodNode node = new UaMethodNode(this.context, Identifiers.ConditionType_ConditionRefresh, new QualifiedName(0, "ConditionRefresh"), new LocalizedText("en", "ConditionRefresh"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true, true);
        node.addReference(new Reference(Identifiers.ConditionType_ConditionRefresh, Identifiers.HasProperty, Identifiers.ConditionType_ConditionRefresh_InputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType_ConditionRefresh, Identifiers.AlwaysGeneratesEvent, Identifiers.RefreshStartEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType_ConditionRefresh, Identifiers.AlwaysGeneratesEvent, Identifiers.RefreshEndEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionType_ConditionRefresh, Identifiers.HasComponent, Identifiers.ConditionType.expanded(), false));
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
