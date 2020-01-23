/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model;

import org.eclipse.milo.opcua.sdk.client.ObjectTypeManager;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AcknowledgeableConditionTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AddressSpaceFileTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AggregateConfigurationTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AggregateFunctionTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AlarmConditionTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ApplicationCertificateTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditActivateSessionEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditAddNodesEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditAddReferencesEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCancelEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCertificateDataMismatchEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCertificateEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCertificateExpiredEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCertificateInvalidEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCertificateMismatchEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCertificateRevokedEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCertificateUntrustedEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditChannelEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditConditionAcknowledgeEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditConditionCommentEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditConditionConfirmEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditConditionEnableEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditConditionEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditConditionRespondEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditConditionShelvingEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCreateSessionEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditDeleteNodesEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditDeleteReferencesEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditHistoryAtTimeDeleteEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditHistoryDeleteEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditHistoryEventDeleteEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditHistoryEventUpdateEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditHistoryRawModifyDeleteEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditHistoryUpdateEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditHistoryValueUpdateEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditNodeManagementEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditOpenSecureChannelEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditProgramTransitionEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditSecurityEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditSessionEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditUpdateEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditUpdateMethodEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditUpdateStateEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditUrlMismatchEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditWriteUpdateEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.BaseConditionClassTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.BaseEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.BaseModelChangeEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.BaseObjectTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.CertificateExpirationAlarmTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.CertificateGroupFolderTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.CertificateGroupTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.CertificateTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.CertificateUpdatedAuditEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ConditionTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.DataTypeEncodingTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.DataTypeSystemTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.DeviceFailureEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.DialogConditionTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.DiscreteAlarmTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.EventQueueOverflowEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ExclusiveDeviationAlarmTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ExclusiveLevelAlarmTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ExclusiveLimitAlarmTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ExclusiveLimitStateMachineTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ExclusiveRateOfChangeAlarmTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FileDirectoryTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FileTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FiniteStateMachineTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FolderTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.GeneralModelChangeEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.HistoricalDataConfigurationTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.HistoryServerCapabilitiesTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.HttpsCertificateTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.InitialStateTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.LimitAlarmTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.MaintenanceConditionClassTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ModellingRuleTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.NamespaceMetadataTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.NamespacesTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.NonExclusiveDeviationAlarmTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.NonExclusiveLevelAlarmTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.NonExclusiveLimitAlarmTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.NonExclusiveRateOfChangeAlarmTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.NonTransparentNetworkRedundancyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.NonTransparentRedundancyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.OffNormalAlarmTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.OperationLimitsTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ProcessConditionClassTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ProgramStateMachineTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ProgramTransitionAuditEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ProgramTransitionEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ProgressEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.RefreshEndEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.RefreshRequiredEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.RefreshStartEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.RsaMinApplicationCertificateTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.RsaSha256ApplicationCertificateTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.SemanticChangeEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ServerCapabilitiesTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ServerConfigurationTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ServerDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ServerRedundancyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ServerTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.SessionDiagnosticsObjectTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.SessionsDiagnosticsSummaryTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ShelvedStateMachineTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.StateMachineTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.StateTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.SystemConditionClassTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.SystemEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.SystemOffNormalAlarmTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.SystemStatusChangeEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.TransitionEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.TransitionTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.TransparentRedundancyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.TripAlarmTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.TrustListTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.TrustListUpdatedAuditEventTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.VendorServerInfoTypeNode;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class ObjectTypeInitializer {
    public static void initialize(NamespaceTable namespaceTable,
                                  ObjectTypeManager objectTypeManager) {
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=58")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            BaseObjectTypeNode.class,
            BaseObjectTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=61")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            FolderTypeNode.class,
            FolderTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=75")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            DataTypeSystemTypeNode.class,
            DataTypeSystemTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=76")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            DataTypeEncodingTypeNode.class,
            DataTypeEncodingTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=77")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ModellingRuleTypeNode.class,
            ModellingRuleTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12522")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            TrustListTypeNode.class,
            TrustListTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12555")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            CertificateGroupTypeNode.class,
            CertificateGroupTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12556")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            CertificateTypeNode.class,
            CertificateTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12557")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ApplicationCertificateTypeNode.class,
            ApplicationCertificateTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12558")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            HttpsCertificateTypeNode.class,
            HttpsCertificateTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12559")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            RsaMinApplicationCertificateTypeNode.class,
            RsaMinApplicationCertificateTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12560")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            RsaSha256ApplicationCertificateTypeNode.class,
            RsaSha256ApplicationCertificateTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12561")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            TrustListUpdatedAuditEventTypeNode.class,
            TrustListUpdatedAuditEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12581")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ServerConfigurationTypeNode.class,
            ServerConfigurationTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12620")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            CertificateUpdatedAuditEventTypeNode.class,
            CertificateUpdatedAuditEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=8927")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditConditionRespondEventTypeNode.class,
            AuditConditionRespondEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=8944")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditConditionAcknowledgeEventTypeNode.class,
            AuditConditionAcknowledgeEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=8961")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditConditionConfirmEventTypeNode.class,
            AuditConditionConfirmEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=13225")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            CertificateExpirationAlarmTypeNode.class,
            CertificateExpirationAlarmTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=13353")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            FileDirectoryTypeNode.class,
            FileDirectoryTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=9318")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ExclusiveLimitStateMachineTypeNode.class,
            ExclusiveLimitStateMachineTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=9341")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ExclusiveLimitAlarmTypeNode.class,
            ExclusiveLimitAlarmTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=9482")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ExclusiveLevelAlarmTypeNode.class,
            ExclusiveLevelAlarmTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=9623")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ExclusiveRateOfChangeAlarmTypeNode.class,
            ExclusiveRateOfChangeAlarmTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=13813")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            CertificateGroupFolderTypeNode.class,
            CertificateGroupFolderTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=9764")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ExclusiveDeviationAlarmTypeNode.class,
            ExclusiveDeviationAlarmTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=9906")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NonExclusiveLimitAlarmTypeNode.class,
            NonExclusiveLimitAlarmTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=10060")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NonExclusiveLevelAlarmTypeNode.class,
            NonExclusiveLevelAlarmTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2004")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ServerTypeNode.class,
            ServerTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2013")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ServerCapabilitiesTypeNode.class,
            ServerCapabilitiesTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2020")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ServerDiagnosticsTypeNode.class,
            ServerDiagnosticsTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=10214")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NonExclusiveRateOfChangeAlarmTypeNode.class,
            NonExclusiveRateOfChangeAlarmTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2026")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SessionsDiagnosticsSummaryTypeNode.class,
            SessionsDiagnosticsSummaryTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2029")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SessionDiagnosticsObjectTypeNode.class,
            SessionDiagnosticsObjectTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2033")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            VendorServerInfoTypeNode.class,
            VendorServerInfoTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2034")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ServerRedundancyTypeNode.class,
            ServerRedundancyTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2036")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            TransparentRedundancyTypeNode.class,
            TransparentRedundancyTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2039")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NonTransparentRedundancyTypeNode.class,
            NonTransparentRedundancyTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2041")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            BaseEventTypeNode.class,
            BaseEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2052")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditEventTypeNode.class,
            AuditEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2058")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditSecurityEventTypeNode.class,
            AuditSecurityEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2059")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditChannelEventTypeNode.class,
            AuditChannelEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2060")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditOpenSecureChannelEventTypeNode.class,
            AuditOpenSecureChannelEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2069")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditSessionEventTypeNode.class,
            AuditSessionEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2071")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCreateSessionEventTypeNode.class,
            AuditCreateSessionEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2075")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditActivateSessionEventTypeNode.class,
            AuditActivateSessionEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2078")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCancelEventTypeNode.class,
            AuditCancelEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2080")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCertificateEventTypeNode.class,
            AuditCertificateEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2082")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCertificateDataMismatchEventTypeNode.class,
            AuditCertificateDataMismatchEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2085")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCertificateExpiredEventTypeNode.class,
            AuditCertificateExpiredEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2086")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCertificateInvalidEventTypeNode.class,
            AuditCertificateInvalidEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2087")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCertificateUntrustedEventTypeNode.class,
            AuditCertificateUntrustedEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2088")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCertificateRevokedEventTypeNode.class,
            AuditCertificateRevokedEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2089")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCertificateMismatchEventTypeNode.class,
            AuditCertificateMismatchEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2090")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditNodeManagementEventTypeNode.class,
            AuditNodeManagementEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2091")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditAddNodesEventTypeNode.class,
            AuditAddNodesEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2093")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditDeleteNodesEventTypeNode.class,
            AuditDeleteNodesEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2095")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditAddReferencesEventTypeNode.class,
            AuditAddReferencesEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2097")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditDeleteReferencesEventTypeNode.class,
            AuditDeleteReferencesEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2099")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditUpdateEventTypeNode.class,
            AuditUpdateEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2100")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditWriteUpdateEventTypeNode.class,
            AuditWriteUpdateEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2104")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditHistoryUpdateEventTypeNode.class,
            AuditHistoryUpdateEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2127")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditUpdateMethodEventTypeNode.class,
            AuditUpdateMethodEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2130")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SystemEventTypeNode.class,
            SystemEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2131")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            DeviceFailureEventTypeNode.class,
            DeviceFailureEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2132")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            BaseModelChangeEventTypeNode.class,
            BaseModelChangeEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2133")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            GeneralModelChangeEventTypeNode.class,
            GeneralModelChangeEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=10368")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NonExclusiveDeviationAlarmTypeNode.class,
            NonExclusiveDeviationAlarmTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2299")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            StateMachineTypeNode.class,
            StateMachineTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2307")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            StateTypeNode.class,
            StateTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2309")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            InitialStateTypeNode.class,
            InitialStateTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2310")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            TransitionTypeNode.class,
            TransitionTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2311")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            TransitionEventTypeNode.class,
            TransitionEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2315")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditUpdateStateEventTypeNode.class,
            AuditUpdateStateEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2318")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            HistoricalDataConfigurationTypeNode.class,
            HistoricalDataConfigurationTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2330")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            HistoryServerCapabilitiesTypeNode.class,
            HistoryServerCapabilitiesTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=10523")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            DiscreteAlarmTypeNode.class,
            DiscreteAlarmTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2340")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AggregateFunctionTypeNode.class,
            AggregateFunctionTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2378")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ProgramTransitionEventTypeNode.class,
            ProgramTransitionEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2391")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ProgramStateMachineTypeNode.class,
            ProgramStateMachineTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=10637")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            OffNormalAlarmTypeNode.class,
            OffNormalAlarmTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=10751")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            TripAlarmTypeNode.class,
            TripAlarmTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2738")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SemanticChangeEventTypeNode.class,
            SemanticChangeEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2748")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditUrlMismatchEventTypeNode.class,
            AuditUrlMismatchEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2771")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            FiniteStateMachineTypeNode.class,
            FiniteStateMachineTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2782")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ConditionTypeNode.class,
            ConditionTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2787")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            RefreshStartEventTypeNode.class,
            RefreshStartEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2788")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            RefreshEndEventTypeNode.class,
            RefreshEndEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2789")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            RefreshRequiredEventTypeNode.class,
            RefreshRequiredEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2790")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditConditionEventTypeNode.class,
            AuditConditionEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2803")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditConditionEnableEventTypeNode.class,
            AuditConditionEnableEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2829")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditConditionCommentEventTypeNode.class,
            AuditConditionCommentEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2830")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            DialogConditionTypeNode.class,
            DialogConditionTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2881")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AcknowledgeableConditionTypeNode.class,
            AcknowledgeableConditionTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11093")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditConditionShelvingEventTypeNode.class,
            AuditConditionShelvingEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2915")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AlarmConditionTypeNode.class,
            AlarmConditionTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2929")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ShelvedStateMachineTypeNode.class,
            ShelvedStateMachineTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2955")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            LimitAlarmTypeNode.class,
            LimitAlarmTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11163")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            BaseConditionClassTypeNode.class,
            BaseConditionClassTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11164")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ProcessConditionClassTypeNode.class,
            ProcessConditionClassTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11165")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            MaintenanceConditionClassTypeNode.class,
            MaintenanceConditionClassTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11166")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SystemConditionClassTypeNode.class,
            SystemConditionClassTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11187")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AggregateConfigurationTypeNode.class,
            AggregateConfigurationTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2999")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditHistoryEventUpdateEventTypeNode.class,
            AuditHistoryEventUpdateEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=3006")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditHistoryValueUpdateEventTypeNode.class,
            AuditHistoryValueUpdateEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=3012")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditHistoryDeleteEventTypeNode.class,
            AuditHistoryDeleteEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=3014")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditHistoryRawModifyDeleteEventTypeNode.class,
            AuditHistoryRawModifyDeleteEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=3019")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditHistoryAtTimeDeleteEventTypeNode.class,
            AuditHistoryAtTimeDeleteEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=3022")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditHistoryEventDeleteEventTypeNode.class,
            AuditHistoryEventDeleteEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=3035")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            EventQueueOverflowEventTypeNode.class,
            EventQueueOverflowEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11436")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ProgressEventTypeNode.class,
            ProgressEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11446")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SystemStatusChangeEventTypeNode.class,
            SystemStatusChangeEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11564")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            OperationLimitsTypeNode.class,
            OperationLimitsTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11575")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            FileTypeNode.class,
            FileTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11595")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AddressSpaceFileTypeNode.class,
            AddressSpaceFileTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11616")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NamespaceMetadataTypeNode.class,
            NamespaceMetadataTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11645")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NamespacesTypeNode.class,
            NamespacesTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11753")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SystemOffNormalAlarmTypeNode.class,
            SystemOffNormalAlarmTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11856")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditProgramTransitionEventTypeNode.class,
            AuditProgramTransitionEventTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11945")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NonTransparentNetworkRedundancyTypeNode.class,
            NonTransparentNetworkRedundancyTypeNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=3806")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ProgramTransitionAuditEventTypeNode.class,
            ProgramTransitionAuditEventTypeNode::new
        );
    }
}
