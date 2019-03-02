/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import org.eclipse.milo.opcua.sdk.server.ObjectTypeManager;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class ObjectTypeManagerInitializer {
    public static void initialize(NamespaceTable namespaceTable,
                                  ObjectTypeManager objectTypeManager) {
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=58")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            BaseObjectNode.class,
            BaseObjectNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=61")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            FolderNode.class,
            FolderNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=75")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            DataTypeSystemNode.class,
            DataTypeSystemNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=76")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            DataTypeEncodingNode.class,
            DataTypeEncodingNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=77")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ModellingRuleNode.class,
            ModellingRuleNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12522")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            TrustListNode.class,
            TrustListNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12555")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            CertificateGroupNode.class,
            CertificateGroupNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12556")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            CertificateNode.class,
            CertificateNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12557")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ApplicationCertificateNode.class,
            ApplicationCertificateNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12558")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            HttpsCertificateNode.class,
            HttpsCertificateNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12559")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            RsaMinApplicationCertificateNode.class,
            RsaMinApplicationCertificateNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12560")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            RsaSha256ApplicationCertificateNode.class,
            RsaSha256ApplicationCertificateNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12561")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            TrustListUpdatedAuditEventNode.class,
            TrustListUpdatedAuditEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12581")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ServerConfigurationNode.class,
            ServerConfigurationNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=12620")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            CertificateUpdatedAuditEventNode.class,
            CertificateUpdatedAuditEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=8927")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditConditionRespondEventNode.class,
            AuditConditionRespondEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=8944")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditConditionAcknowledgeEventNode.class,
            AuditConditionAcknowledgeEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=8961")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditConditionConfirmEventNode.class,
            AuditConditionConfirmEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=13225")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            CertificateExpirationAlarmNode.class,
            CertificateExpirationAlarmNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=13353")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            FileDirectoryNode.class,
            FileDirectoryNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=9318")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ExclusiveLimitStateMachineNode.class,
            ExclusiveLimitStateMachineNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=9341")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ExclusiveLimitAlarmNode.class,
            ExclusiveLimitAlarmNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=9482")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ExclusiveLevelAlarmNode.class,
            ExclusiveLevelAlarmNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=9623")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ExclusiveRateOfChangeAlarmNode.class,
            ExclusiveRateOfChangeAlarmNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=13813")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            CertificateGroupFolderNode.class,
            CertificateGroupFolderNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=9764")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ExclusiveDeviationAlarmNode.class,
            ExclusiveDeviationAlarmNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=9906")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NonExclusiveLimitAlarmNode.class,
            NonExclusiveLimitAlarmNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=10060")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NonExclusiveLevelAlarmNode.class,
            NonExclusiveLevelAlarmNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2004")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ServerNode.class,
            ServerNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2013")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ServerCapabilitiesNode.class,
            ServerCapabilitiesNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2020")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ServerDiagnosticsNode.class,
            ServerDiagnosticsNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=10214")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NonExclusiveRateOfChangeAlarmNode.class,
            NonExclusiveRateOfChangeAlarmNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2026")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SessionsDiagnosticsSummaryNode.class,
            SessionsDiagnosticsSummaryNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2029")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SessionDiagnosticsObjectNode.class,
            SessionDiagnosticsObjectNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2033")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            VendorServerInfoNode.class,
            VendorServerInfoNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2034")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ServerRedundancyNode.class,
            ServerRedundancyNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2036")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            TransparentRedundancyNode.class,
            TransparentRedundancyNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2039")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NonTransparentRedundancyNode.class,
            NonTransparentRedundancyNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2041")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            BaseEventNode.class,
            BaseEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2052")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditEventNode.class,
            AuditEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2058")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditSecurityEventNode.class,
            AuditSecurityEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2059")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditChannelEventNode.class,
            AuditChannelEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2060")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditOpenSecureChannelEventNode.class,
            AuditOpenSecureChannelEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2069")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditSessionEventNode.class,
            AuditSessionEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2071")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCreateSessionEventNode.class,
            AuditCreateSessionEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2075")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditActivateSessionEventNode.class,
            AuditActivateSessionEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2078")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCancelEventNode.class,
            AuditCancelEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2080")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCertificateEventNode.class,
            AuditCertificateEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2082")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCertificateDataMismatchEventNode.class,
            AuditCertificateDataMismatchEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2085")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCertificateExpiredEventNode.class,
            AuditCertificateExpiredEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2086")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCertificateInvalidEventNode.class,
            AuditCertificateInvalidEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2087")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCertificateUntrustedEventNode.class,
            AuditCertificateUntrustedEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2088")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCertificateRevokedEventNode.class,
            AuditCertificateRevokedEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2089")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditCertificateMismatchEventNode.class,
            AuditCertificateMismatchEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2090")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditNodeManagementEventNode.class,
            AuditNodeManagementEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2091")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditAddNodesEventNode.class,
            AuditAddNodesEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2093")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditDeleteNodesEventNode.class,
            AuditDeleteNodesEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2095")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditAddReferencesEventNode.class,
            AuditAddReferencesEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2097")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditDeleteReferencesEventNode.class,
            AuditDeleteReferencesEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2099")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditUpdateEventNode.class,
            AuditUpdateEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2100")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditWriteUpdateEventNode.class,
            AuditWriteUpdateEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2104")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditHistoryUpdateEventNode.class,
            AuditHistoryUpdateEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2127")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditUpdateMethodEventNode.class,
            AuditUpdateMethodEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2130")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SystemEventNode.class,
            SystemEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2131")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            DeviceFailureEventNode.class,
            DeviceFailureEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2132")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            BaseModelChangeEventNode.class,
            BaseModelChangeEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2133")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            GeneralModelChangeEventNode.class,
            GeneralModelChangeEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=10368")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NonExclusiveDeviationAlarmNode.class,
            NonExclusiveDeviationAlarmNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2299")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            StateMachineNode.class,
            StateMachineNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2307")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            StateNode.class,
            StateNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2309")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            InitialStateNode.class,
            InitialStateNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2310")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            TransitionNode.class,
            TransitionNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2311")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            TransitionEventNode.class,
            TransitionEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2315")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditUpdateStateEventNode.class,
            AuditUpdateStateEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2318")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            HistoricalDataConfigurationNode.class,
            HistoricalDataConfigurationNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2330")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            HistoryServerCapabilitiesNode.class,
            HistoryServerCapabilitiesNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=10523")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            DiscreteAlarmNode.class,
            DiscreteAlarmNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2340")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AggregateFunctionNode.class,
            AggregateFunctionNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2378")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ProgramTransitionEventNode.class,
            ProgramTransitionEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2391")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ProgramStateMachineNode.class,
            ProgramStateMachineNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=10637")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            OffNormalAlarmNode.class,
            OffNormalAlarmNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=10751")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            TripAlarmNode.class,
            TripAlarmNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2738")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SemanticChangeEventNode.class,
            SemanticChangeEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2748")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditUrlMismatchEventNode.class,
            AuditUrlMismatchEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2771")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            FiniteStateMachineNode.class,
            FiniteStateMachineNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2782")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ConditionNode.class,
            ConditionNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2787")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            RefreshStartEventNode.class,
            RefreshStartEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2788")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            RefreshEndEventNode.class,
            RefreshEndEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2789")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            RefreshRequiredEventNode.class,
            RefreshRequiredEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2790")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditConditionEventNode.class,
            AuditConditionEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2803")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditConditionEnableEventNode.class,
            AuditConditionEnableEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2829")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditConditionCommentEventNode.class,
            AuditConditionCommentEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2830")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            DialogConditionNode.class,
            DialogConditionNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2881")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AcknowledgeableConditionNode.class,
            AcknowledgeableConditionNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11093")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditConditionShelvingEventNode.class,
            AuditConditionShelvingEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2915")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AlarmConditionNode.class,
            AlarmConditionNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2929")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ShelvedStateMachineNode.class,
            ShelvedStateMachineNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2955")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            LimitAlarmNode.class,
            LimitAlarmNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11163")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            BaseConditionClassNode.class,
            BaseConditionClassNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11164")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ProcessConditionClassNode.class,
            ProcessConditionClassNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11165")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            MaintenanceConditionClassNode.class,
            MaintenanceConditionClassNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11166")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SystemConditionClassNode.class,
            SystemConditionClassNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11187")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AggregateConfigurationNode.class,
            AggregateConfigurationNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=2999")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditHistoryEventUpdateEventNode.class,
            AuditHistoryEventUpdateEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=3006")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditHistoryValueUpdateEventNode.class,
            AuditHistoryValueUpdateEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=3012")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditHistoryDeleteEventNode.class,
            AuditHistoryDeleteEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=3014")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditHistoryRawModifyDeleteEventNode.class,
            AuditHistoryRawModifyDeleteEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=3019")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditHistoryAtTimeDeleteEventNode.class,
            AuditHistoryAtTimeDeleteEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=3022")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditHistoryEventDeleteEventNode.class,
            AuditHistoryEventDeleteEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=3035")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            EventQueueOverflowEventNode.class,
            EventQueueOverflowEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11436")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ProgressEventNode.class,
            ProgressEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11446")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SystemStatusChangeEventNode.class,
            SystemStatusChangeEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11564")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            OperationLimitsNode.class,
            OperationLimitsNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11575")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            FileNode.class,
            FileNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11595")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AddressSpaceFileNode.class,
            AddressSpaceFileNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11616")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NamespaceMetadataNode.class,
            NamespaceMetadataNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11645")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NamespacesNode.class,
            NamespacesNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11753")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SystemOffNormalAlarmNode.class,
            SystemOffNormalAlarmNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11856")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AuditProgramTransitionEventNode.class,
            AuditProgramTransitionEventNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=11945")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NonTransparentNetworkRedundancyNode.class,
            NonTransparentNetworkRedundancyNode::new
        );
        objectTypeManager.registerObjectType(
            NodeId.parse("ns=0;i=3806")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ProgramTransitionAuditEventNode.class,
            ProgramTransitionAuditEventNode::new
        );
    }
}
