/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.client.model;

import org.eclipse.milo.opcua.sdk.client.TypeRegistry;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AcknowledgeableConditionNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AddressSpaceFileNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AggregateConfigurationNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AggregateFunctionNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AlarmConditionNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ApplicationCertificateNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditActivateSessionEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditAddNodesEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditAddReferencesEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCancelEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCertificateDataMismatchEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCertificateEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCertificateExpiredEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCertificateInvalidEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCertificateMismatchEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCertificateRevokedEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCertificateUntrustedEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditChannelEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditConditionAcknowledgeEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditConditionCommentEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditConditionConfirmEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditConditionEnableEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditConditionEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditConditionRespondEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditConditionShelvingEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditCreateSessionEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditDeleteNodesEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditDeleteReferencesEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditHistoryAtTimeDeleteEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditHistoryDeleteEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditHistoryEventDeleteEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditHistoryEventUpdateEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditHistoryRawModifyDeleteEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditHistoryUpdateEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditHistoryValueUpdateEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditNodeManagementEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditOpenSecureChannelEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditProgramTransitionEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditSecurityEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditSessionEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditUpdateEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditUpdateMethodEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditUpdateStateEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditUrlMismatchEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.AuditWriteUpdateEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.BaseConditionClassNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.BaseModelChangeEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.BaseObjectNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.CertificateExpirationAlarmNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.CertificateGroupFolderNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.CertificateGroupNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.CertificateNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.CertificateUpdatedAuditEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ConditionNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.DataTypeEncodingNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.DataTypeSystemNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.DeviceFailureEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.DialogConditionNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.DiscreteAlarmNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.EventQueueOverflowEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ExclusiveDeviationAlarmNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ExclusiveLevelAlarmNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ExclusiveLimitAlarmNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ExclusiveLimitStateMachineNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ExclusiveRateOfChangeAlarmNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FileDirectoryNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FileNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FiniteStateMachineNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FolderNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.GeneralModelChangeEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.HistoricalDataConfigurationNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.HistoryServerCapabilitiesNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.HttpsCertificateNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.InitialStateNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.LimitAlarmNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.MaintenanceConditionClassNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ModellingRuleNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.NamespaceMetadataNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.NamespacesNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.NonExclusiveDeviationAlarmNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.NonExclusiveLevelAlarmNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.NonExclusiveLimitAlarmNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.NonExclusiveRateOfChangeAlarmNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.NonTransparentNetworkRedundancyNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.NonTransparentRedundancyNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.OffNormalAlarmNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.OperationLimitsNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ProcessConditionClassNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ProgramStateMachineNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ProgramTransitionAuditEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ProgramTransitionEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ProgressEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.RefreshEndEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.RefreshRequiredEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.RefreshStartEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.RsaMinApplicationCertificateNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.RsaSha256ApplicationCertificateNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.SemanticChangeEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ServerCapabilitiesNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ServerConfigurationNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ServerDiagnosticsNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ServerNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ServerRedundancyNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.SessionDiagnosticsObjectNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.SessionsDiagnosticsSummaryNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ShelvedStateMachineNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.StateMachineNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.StateNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.SystemConditionClassNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.SystemEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.SystemOffNormalAlarmNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.SystemStatusChangeEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.TransitionEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.TransitionNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.TransparentRedundancyNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.TripAlarmNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.TrustListNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.TrustListUpdatedAuditEventNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.VendorServerInfoNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.AnalogItemNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ArrayItemNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.BaseDataVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.BaseVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.BuildInfoNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ConditionVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.CubeItemNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.DataItemNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.DataTypeDescriptionNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.DataTypeDictionaryNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.DiscreteItemNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.FiniteStateVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.FiniteTransitionVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ImageItemNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.MultiStateDiscreteNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.MultiStateValueDiscreteNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.NDimensionArrayItemNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.OptionSetNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ProgramDiagnosticNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SamplingIntervalDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SamplingIntervalDiagnosticsNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ServerDiagnosticsSummaryNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ServerStatusNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ServerVendorCapabilityNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionDiagnosticsVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionSecurityDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionSecurityDiagnosticsNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.StateVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SubscriptionDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SubscriptionDiagnosticsNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TransitionVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TwoStateDiscreteNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TwoStateVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.XYArrayItemNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.YArrayItemNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AcknowledgeableConditionType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AddressSpaceFileType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AggregateConfigurationType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AggregateFunctionType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AlarmConditionType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ApplicationCertificateType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditActivateSessionEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditAddNodesEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditAddReferencesEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditCancelEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditCertificateDataMismatchEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditCertificateEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditCertificateExpiredEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditCertificateInvalidEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditCertificateMismatchEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditCertificateRevokedEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditCertificateUntrustedEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditChannelEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditConditionAcknowledgeEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditConditionCommentEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditConditionConfirmEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditConditionEnableEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditConditionEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditConditionRespondEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditConditionShelvingEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditCreateSessionEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditDeleteNodesEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditDeleteReferencesEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryAtTimeDeleteEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryDeleteEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryEventDeleteEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryEventUpdateEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryRawModifyDeleteEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryUpdateEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryValueUpdateEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditNodeManagementEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditOpenSecureChannelEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditProgramTransitionEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditSecurityEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditSessionEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditUpdateEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditUpdateMethodEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditUpdateStateEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditUrlMismatchEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditWriteUpdateEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.BaseConditionClassType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.BaseEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.BaseModelChangeEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.BaseObjectType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.CertificateExpirationAlarmType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.CertificateGroupFolderType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.CertificateGroupType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.CertificateType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.CertificateUpdatedAuditEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ConditionType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.DataTypeEncodingType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.DataTypeSystemType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.DeviceFailureEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.DialogConditionType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.DiscreteAlarmType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.EventQueueOverflowEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ExclusiveDeviationAlarmType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ExclusiveLevelAlarmType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ExclusiveLimitAlarmType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ExclusiveLimitStateMachineType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ExclusiveRateOfChangeAlarmType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.FileDirectoryType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.FileType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.FiniteStateMachineType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.FolderType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.GeneralModelChangeEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.HistoricalDataConfigurationType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.HistoryServerCapabilitiesType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.HttpsCertificateType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.InitialStateType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.LimitAlarmType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.MaintenanceConditionClassType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ModellingRuleType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NamespaceMetadataType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NamespacesType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NonExclusiveDeviationAlarmType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NonExclusiveLevelAlarmType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NonExclusiveLimitAlarmType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NonExclusiveRateOfChangeAlarmType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NonTransparentNetworkRedundancyType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NonTransparentRedundancyType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.OffNormalAlarmType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.OperationLimitsType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ProcessConditionClassType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ProgramStateMachineType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ProgramTransitionAuditEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ProgramTransitionEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ProgressEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.RefreshEndEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.RefreshRequiredEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.RefreshStartEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.RsaMinApplicationCertificateType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.RsaSha256ApplicationCertificateType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SemanticChangeEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerCapabilitiesType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerConfigurationType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerDiagnosticsType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerRedundancyType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SessionDiagnosticsObjectType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SessionsDiagnosticsSummaryType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ShelvedStateMachineType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.StateMachineType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.StateType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SystemConditionClassType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SystemEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SystemOffNormalAlarmType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SystemStatusChangeEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.TransitionEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.TransitionType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.TransparentRedundancyType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.TripAlarmType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.TrustListType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.TrustListUpdatedAuditEventType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.VendorServerInfoType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.AnalogItemType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ArrayItemType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.BaseDataVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.BaseVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.BuildInfoType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ConditionVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.CubeItemType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.DataItemType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.DataTypeDescriptionType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.DataTypeDictionaryType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.DiscreteItemType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.FiniteStateVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.FiniteTransitionVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ImageItemType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.MultiStateDiscreteType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.MultiStateValueDiscreteType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.NDimensionArrayItemType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.OptionSetType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ProgramDiagnosticType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SamplingIntervalDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SamplingIntervalDiagnosticsType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ServerDiagnosticsSummaryType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ServerStatusType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ServerVendorCapabilityType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionDiagnosticsVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionSecurityDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionSecurityDiagnosticsType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.StateVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SubscriptionDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SubscriptionDiagnosticsType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.TransitionVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.TwoStateDiscreteType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.XYArrayItemType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.YArrayItemType;
import org.eclipse.milo.opcua.stack.core.Identifiers;

public class TypeRegistryInitializer {

    public static void initialize(TypeRegistry typeRegistry) {
        registerObjects(typeRegistry);
        registerVariables(typeRegistry);
    }

    private static void registerObjects(TypeRegistry typeRegistry) {
        typeRegistry.registerType(
            Identifiers.BaseObjectType,
            BaseObjectType.class,
            BaseObjectNode.class,
            BaseObjectNode::new
        );

        typeRegistry.registerType(
            Identifiers.FolderType,
            FolderType.class,
            FolderNode.class,
            FolderNode::new
        );

        typeRegistry.registerType(
            Identifiers.DataTypeSystemType,
            DataTypeSystemType.class,
            DataTypeSystemNode.class,
            DataTypeSystemNode::new
        );

        typeRegistry.registerType(
            Identifiers.DataTypeEncodingType,
            DataTypeEncodingType.class,
            DataTypeEncodingNode.class,
            DataTypeEncodingNode::new
        );

        typeRegistry.registerType(
            Identifiers.ModellingRuleType,
            ModellingRuleType.class,
            ModellingRuleNode.class,
            ModellingRuleNode::new
        );

        typeRegistry.registerType(
            Identifiers.TrustListType,
            TrustListType.class,
            TrustListNode.class,
            TrustListNode::new
        );

        typeRegistry.registerType(
            Identifiers.CertificateGroupType,
            CertificateGroupType.class,
            CertificateGroupNode.class,
            CertificateGroupNode::new
        );

        typeRegistry.registerType(
            Identifiers.CertificateType,
            CertificateType.class,
            CertificateNode.class,
            CertificateNode::new
        );

        typeRegistry.registerType(
            Identifiers.ApplicationCertificateType,
            ApplicationCertificateType.class,
            ApplicationCertificateNode.class,
            ApplicationCertificateNode::new
        );

        typeRegistry.registerType(
            Identifiers.HttpsCertificateType,
            HttpsCertificateType.class,
            HttpsCertificateNode.class,
            HttpsCertificateNode::new
        );

        typeRegistry.registerType(
            Identifiers.RsaMinApplicationCertificateType,
            RsaMinApplicationCertificateType.class,
            RsaMinApplicationCertificateNode.class,
            RsaMinApplicationCertificateNode::new
        );

        typeRegistry.registerType(
            Identifiers.RsaSha256ApplicationCertificateType,
            RsaSha256ApplicationCertificateType.class,
            RsaSha256ApplicationCertificateNode.class,
            RsaSha256ApplicationCertificateNode::new
        );

        typeRegistry.registerType(
            Identifiers.TrustListUpdatedAuditEventType,
            TrustListUpdatedAuditEventType.class,
            TrustListUpdatedAuditEventNode.class,
            TrustListUpdatedAuditEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.ServerConfigurationType,
            ServerConfigurationType.class,
            ServerConfigurationNode.class,
            ServerConfigurationNode::new
        );

        typeRegistry.registerType(
            Identifiers.CertificateUpdatedAuditEventType,
            CertificateUpdatedAuditEventType.class,
            CertificateUpdatedAuditEventNode.class,
            CertificateUpdatedAuditEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditConditionRespondEventType,
            AuditConditionRespondEventType.class,
            AuditConditionRespondEventNode.class,
            AuditConditionRespondEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditConditionAcknowledgeEventType,
            AuditConditionAcknowledgeEventType.class,
            AuditConditionAcknowledgeEventNode.class,
            AuditConditionAcknowledgeEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditConditionConfirmEventType,
            AuditConditionConfirmEventType.class,
            AuditConditionConfirmEventNode.class,
            AuditConditionConfirmEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.CertificateExpirationAlarmType,
            CertificateExpirationAlarmType.class,
            CertificateExpirationAlarmNode.class,
            CertificateExpirationAlarmNode::new
        );

        typeRegistry.registerType(
            Identifiers.FileDirectoryType,
            FileDirectoryType.class,
            FileDirectoryNode.class,
            FileDirectoryNode::new
        );

        typeRegistry.registerType(
            Identifiers.ExclusiveLimitStateMachineType,
            ExclusiveLimitStateMachineType.class,
            ExclusiveLimitStateMachineNode.class,
            ExclusiveLimitStateMachineNode::new
        );

        typeRegistry.registerType(
            Identifiers.ExclusiveLimitAlarmType,
            ExclusiveLimitAlarmType.class,
            ExclusiveLimitAlarmNode.class,
            ExclusiveLimitAlarmNode::new
        );

        typeRegistry.registerType(
            Identifiers.ExclusiveLevelAlarmType,
            ExclusiveLevelAlarmType.class,
            ExclusiveLevelAlarmNode.class,
            ExclusiveLevelAlarmNode::new
        );

        typeRegistry.registerType(
            Identifiers.ExclusiveRateOfChangeAlarmType,
            ExclusiveRateOfChangeAlarmType.class,
            ExclusiveRateOfChangeAlarmNode.class,
            ExclusiveRateOfChangeAlarmNode::new
        );

        typeRegistry.registerType(
            Identifiers.CertificateGroupFolderType,
            CertificateGroupFolderType.class,
            CertificateGroupFolderNode.class,
            CertificateGroupFolderNode::new
        );

        typeRegistry.registerType(
            Identifiers.ExclusiveDeviationAlarmType,
            ExclusiveDeviationAlarmType.class,
            ExclusiveDeviationAlarmNode.class,
            ExclusiveDeviationAlarmNode::new
        );

        typeRegistry.registerType(
            Identifiers.NonExclusiveLimitAlarmType,
            NonExclusiveLimitAlarmType.class,
            NonExclusiveLimitAlarmNode.class,
            NonExclusiveLimitAlarmNode::new
        );

        typeRegistry.registerType(
            Identifiers.NonExclusiveLevelAlarmType,
            NonExclusiveLevelAlarmType.class,
            NonExclusiveLevelAlarmNode.class,
            NonExclusiveLevelAlarmNode::new
        );

        typeRegistry.registerType(
            Identifiers.ServerType,
            ServerType.class,
            ServerNode.class,
            ServerNode::new
        );

        typeRegistry.registerType(
            Identifiers.ServerCapabilitiesType,
            ServerCapabilitiesType.class,
            ServerCapabilitiesNode.class,
            ServerCapabilitiesNode::new
        );

        typeRegistry.registerType(
            Identifiers.ServerDiagnosticsType,
            ServerDiagnosticsType.class,
            ServerDiagnosticsNode.class,
            ServerDiagnosticsNode::new
        );

        typeRegistry.registerType(
            Identifiers.NonExclusiveRateOfChangeAlarmType,
            NonExclusiveRateOfChangeAlarmType.class,
            NonExclusiveRateOfChangeAlarmNode.class,
            NonExclusiveRateOfChangeAlarmNode::new
        );

        typeRegistry.registerType(
            Identifiers.SessionsDiagnosticsSummaryType,
            SessionsDiagnosticsSummaryType.class,
            SessionsDiagnosticsSummaryNode.class,
            SessionsDiagnosticsSummaryNode::new
        );

        typeRegistry.registerType(
            Identifiers.SessionDiagnosticsObjectType,
            SessionDiagnosticsObjectType.class,
            SessionDiagnosticsObjectNode.class,
            SessionDiagnosticsObjectNode::new
        );

        typeRegistry.registerType(
            Identifiers.VendorServerInfoType,
            VendorServerInfoType.class,
            VendorServerInfoNode.class,
            VendorServerInfoNode::new
        );

        typeRegistry.registerType(
            Identifiers.ServerRedundancyType,
            ServerRedundancyType.class,
            ServerRedundancyNode.class,
            ServerRedundancyNode::new
        );

        typeRegistry.registerType(
            Identifiers.TransparentRedundancyType,
            TransparentRedundancyType.class,
            TransparentRedundancyNode.class,
            TransparentRedundancyNode::new
        );

        typeRegistry.registerType(
            Identifiers.NonTransparentRedundancyType,
            NonTransparentRedundancyType.class,
            NonTransparentRedundancyNode.class,
            NonTransparentRedundancyNode::new
        );

        typeRegistry.registerType(
            Identifiers.BaseEventType,
            BaseEventType.class,
            BaseEventNode.class,
            BaseEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditEventType,
            AuditEventType.class,
            AuditEventNode.class,
            AuditEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditSecurityEventType,
            AuditSecurityEventType.class,
            AuditSecurityEventNode.class,
            AuditSecurityEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditChannelEventType,
            AuditChannelEventType.class,
            AuditChannelEventNode.class,
            AuditChannelEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditOpenSecureChannelEventType,
            AuditOpenSecureChannelEventType.class,
            AuditOpenSecureChannelEventNode.class,
            AuditOpenSecureChannelEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditSessionEventType,
            AuditSessionEventType.class,
            AuditSessionEventNode.class,
            AuditSessionEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditCreateSessionEventType,
            AuditCreateSessionEventType.class,
            AuditCreateSessionEventNode.class,
            AuditCreateSessionEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditActivateSessionEventType,
            AuditActivateSessionEventType.class,
            AuditActivateSessionEventNode.class,
            AuditActivateSessionEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditCancelEventType,
            AuditCancelEventType.class,
            AuditCancelEventNode.class,
            AuditCancelEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditCertificateEventType,
            AuditCertificateEventType.class,
            AuditCertificateEventNode.class,
            AuditCertificateEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditCertificateDataMismatchEventType,
            AuditCertificateDataMismatchEventType.class,
            AuditCertificateDataMismatchEventNode.class,
            AuditCertificateDataMismatchEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditCertificateExpiredEventType,
            AuditCertificateExpiredEventType.class,
            AuditCertificateExpiredEventNode.class,
            AuditCertificateExpiredEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditCertificateInvalidEventType,
            AuditCertificateInvalidEventType.class,
            AuditCertificateInvalidEventNode.class,
            AuditCertificateInvalidEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditCertificateUntrustedEventType,
            AuditCertificateUntrustedEventType.class,
            AuditCertificateUntrustedEventNode.class,
            AuditCertificateUntrustedEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditCertificateRevokedEventType,
            AuditCertificateRevokedEventType.class,
            AuditCertificateRevokedEventNode.class,
            AuditCertificateRevokedEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditCertificateMismatchEventType,
            AuditCertificateMismatchEventType.class,
            AuditCertificateMismatchEventNode.class,
            AuditCertificateMismatchEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditNodeManagementEventType,
            AuditNodeManagementEventType.class,
            AuditNodeManagementEventNode.class,
            AuditNodeManagementEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditAddNodesEventType,
            AuditAddNodesEventType.class,
            AuditAddNodesEventNode.class,
            AuditAddNodesEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditDeleteNodesEventType,
            AuditDeleteNodesEventType.class,
            AuditDeleteNodesEventNode.class,
            AuditDeleteNodesEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditAddReferencesEventType,
            AuditAddReferencesEventType.class,
            AuditAddReferencesEventNode.class,
            AuditAddReferencesEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditDeleteReferencesEventType,
            AuditDeleteReferencesEventType.class,
            AuditDeleteReferencesEventNode.class,
            AuditDeleteReferencesEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditUpdateEventType,
            AuditUpdateEventType.class,
            AuditUpdateEventNode.class,
            AuditUpdateEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditWriteUpdateEventType,
            AuditWriteUpdateEventType.class,
            AuditWriteUpdateEventNode.class,
            AuditWriteUpdateEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditHistoryUpdateEventType,
            AuditHistoryUpdateEventType.class,
            AuditHistoryUpdateEventNode.class,
            AuditHistoryUpdateEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditUpdateMethodEventType,
            AuditUpdateMethodEventType.class,
            AuditUpdateMethodEventNode.class,
            AuditUpdateMethodEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.SystemEventType,
            SystemEventType.class,
            SystemEventNode.class,
            SystemEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.DeviceFailureEventType,
            DeviceFailureEventType.class,
            DeviceFailureEventNode.class,
            DeviceFailureEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.BaseModelChangeEventType,
            BaseModelChangeEventType.class,
            BaseModelChangeEventNode.class,
            BaseModelChangeEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.GeneralModelChangeEventType,
            GeneralModelChangeEventType.class,
            GeneralModelChangeEventNode.class,
            GeneralModelChangeEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.NonExclusiveDeviationAlarmType,
            NonExclusiveDeviationAlarmType.class,
            NonExclusiveDeviationAlarmNode.class,
            NonExclusiveDeviationAlarmNode::new
        );

        typeRegistry.registerType(
            Identifiers.StateMachineType,
            StateMachineType.class,
            StateMachineNode.class,
            StateMachineNode::new
        );

        typeRegistry.registerType(
            Identifiers.StateType,
            StateType.class,
            StateNode.class,
            StateNode::new
        );

        typeRegistry.registerType(
            Identifiers.InitialStateType,
            InitialStateType.class,
            InitialStateNode.class,
            InitialStateNode::new
        );

        typeRegistry.registerType(
            Identifiers.TransitionType,
            TransitionType.class,
            TransitionNode.class,
            TransitionNode::new
        );

        typeRegistry.registerType(
            Identifiers.TransitionEventType,
            TransitionEventType.class,
            TransitionEventNode.class,
            TransitionEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditUpdateStateEventType,
            AuditUpdateStateEventType.class,
            AuditUpdateStateEventNode.class,
            AuditUpdateStateEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.HistoricalDataConfigurationType,
            HistoricalDataConfigurationType.class,
            HistoricalDataConfigurationNode.class,
            HistoricalDataConfigurationNode::new
        );

        typeRegistry.registerType(
            Identifiers.HistoryServerCapabilitiesType,
            HistoryServerCapabilitiesType.class,
            HistoryServerCapabilitiesNode.class,
            HistoryServerCapabilitiesNode::new
        );

        typeRegistry.registerType(
            Identifiers.DiscreteAlarmType,
            DiscreteAlarmType.class,
            DiscreteAlarmNode.class,
            DiscreteAlarmNode::new
        );

        typeRegistry.registerType(
            Identifiers.AggregateFunctionType,
            AggregateFunctionType.class,
            AggregateFunctionNode.class,
            AggregateFunctionNode::new
        );

        typeRegistry.registerType(
            Identifiers.ProgramTransitionEventType,
            ProgramTransitionEventType.class,
            ProgramTransitionEventNode.class,
            ProgramTransitionEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.ProgramStateMachineType,
            ProgramStateMachineType.class,
            ProgramStateMachineNode.class,
            ProgramStateMachineNode::new
        );

        typeRegistry.registerType(
            Identifiers.OffNormalAlarmType,
            OffNormalAlarmType.class,
            OffNormalAlarmNode.class,
            OffNormalAlarmNode::new
        );

        typeRegistry.registerType(
            Identifiers.TripAlarmType,
            TripAlarmType.class,
            TripAlarmNode.class,
            TripAlarmNode::new
        );

        typeRegistry.registerType(
            Identifiers.SemanticChangeEventType,
            SemanticChangeEventType.class,
            SemanticChangeEventNode.class,
            SemanticChangeEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditUrlMismatchEventType,
            AuditUrlMismatchEventType.class,
            AuditUrlMismatchEventNode.class,
            AuditUrlMismatchEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.FiniteStateMachineType,
            FiniteStateMachineType.class,
            FiniteStateMachineNode.class,
            FiniteStateMachineNode::new
        );

        typeRegistry.registerType(
            Identifiers.ConditionType,
            ConditionType.class,
            ConditionNode.class,
            ConditionNode::new
        );

        typeRegistry.registerType(
            Identifiers.RefreshStartEventType,
            RefreshStartEventType.class,
            RefreshStartEventNode.class,
            RefreshStartEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.RefreshEndEventType,
            RefreshEndEventType.class,
            RefreshEndEventNode.class,
            RefreshEndEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.RefreshRequiredEventType,
            RefreshRequiredEventType.class,
            RefreshRequiredEventNode.class,
            RefreshRequiredEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditConditionEventType,
            AuditConditionEventType.class,
            AuditConditionEventNode.class,
            AuditConditionEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditConditionEnableEventType,
            AuditConditionEnableEventType.class,
            AuditConditionEnableEventNode.class,
            AuditConditionEnableEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditConditionCommentEventType,
            AuditConditionCommentEventType.class,
            AuditConditionCommentEventNode.class,
            AuditConditionCommentEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.DialogConditionType,
            DialogConditionType.class,
            DialogConditionNode.class,
            DialogConditionNode::new
        );

        typeRegistry.registerType(
            Identifiers.AcknowledgeableConditionType,
            AcknowledgeableConditionType.class,
            AcknowledgeableConditionNode.class,
            AcknowledgeableConditionNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditConditionShelvingEventType,
            AuditConditionShelvingEventType.class,
            AuditConditionShelvingEventNode.class,
            AuditConditionShelvingEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AlarmConditionType,
            AlarmConditionType.class,
            AlarmConditionNode.class,
            AlarmConditionNode::new
        );

        typeRegistry.registerType(
            Identifiers.ShelvedStateMachineType,
            ShelvedStateMachineType.class,
            ShelvedStateMachineNode.class,
            ShelvedStateMachineNode::new
        );

        typeRegistry.registerType(
            Identifiers.LimitAlarmType,
            LimitAlarmType.class,
            LimitAlarmNode.class,
            LimitAlarmNode::new
        );

        typeRegistry.registerType(
            Identifiers.BaseConditionClassType,
            BaseConditionClassType.class,
            BaseConditionClassNode.class,
            BaseConditionClassNode::new
        );

        typeRegistry.registerType(
            Identifiers.ProcessConditionClassType,
            ProcessConditionClassType.class,
            ProcessConditionClassNode.class,
            ProcessConditionClassNode::new
        );

        typeRegistry.registerType(
            Identifiers.MaintenanceConditionClassType,
            MaintenanceConditionClassType.class,
            MaintenanceConditionClassNode.class,
            MaintenanceConditionClassNode::new
        );

        typeRegistry.registerType(
            Identifiers.SystemConditionClassType,
            SystemConditionClassType.class,
            SystemConditionClassNode.class,
            SystemConditionClassNode::new
        );

        typeRegistry.registerType(
            Identifiers.AggregateConfigurationType,
            AggregateConfigurationType.class,
            AggregateConfigurationNode.class,
            AggregateConfigurationNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditHistoryEventUpdateEventType,
            AuditHistoryEventUpdateEventType.class,
            AuditHistoryEventUpdateEventNode.class,
            AuditHistoryEventUpdateEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditHistoryValueUpdateEventType,
            AuditHistoryValueUpdateEventType.class,
            AuditHistoryValueUpdateEventNode.class,
            AuditHistoryValueUpdateEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditHistoryDeleteEventType,
            AuditHistoryDeleteEventType.class,
            AuditHistoryDeleteEventNode.class,
            AuditHistoryDeleteEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditHistoryRawModifyDeleteEventType,
            AuditHistoryRawModifyDeleteEventType.class,
            AuditHistoryRawModifyDeleteEventNode.class,
            AuditHistoryRawModifyDeleteEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditHistoryAtTimeDeleteEventType,
            AuditHistoryAtTimeDeleteEventType.class,
            AuditHistoryAtTimeDeleteEventNode.class,
            AuditHistoryAtTimeDeleteEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditHistoryEventDeleteEventType,
            AuditHistoryEventDeleteEventType.class,
            AuditHistoryEventDeleteEventNode.class,
            AuditHistoryEventDeleteEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.EventQueueOverflowEventType,
            EventQueueOverflowEventType.class,
            EventQueueOverflowEventNode.class,
            EventQueueOverflowEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.ProgressEventType,
            ProgressEventType.class,
            ProgressEventNode.class,
            ProgressEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.SystemStatusChangeEventType,
            SystemStatusChangeEventType.class,
            SystemStatusChangeEventNode.class,
            SystemStatusChangeEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.OperationLimitsType,
            OperationLimitsType.class,
            OperationLimitsNode.class,
            OperationLimitsNode::new
        );

        typeRegistry.registerType(
            Identifiers.FileType,
            FileType.class,
            FileNode.class,
            FileNode::new
        );

        typeRegistry.registerType(
            Identifiers.AddressSpaceFileType,
            AddressSpaceFileType.class,
            AddressSpaceFileNode.class,
            AddressSpaceFileNode::new
        );

        typeRegistry.registerType(
            Identifiers.NamespaceMetadataType,
            NamespaceMetadataType.class,
            NamespaceMetadataNode.class,
            NamespaceMetadataNode::new
        );

        typeRegistry.registerType(
            Identifiers.NamespacesType,
            NamespacesType.class,
            NamespacesNode.class,
            NamespacesNode::new
        );

        typeRegistry.registerType(
            Identifiers.SystemOffNormalAlarmType,
            SystemOffNormalAlarmType.class,
            SystemOffNormalAlarmNode.class,
            SystemOffNormalAlarmNode::new
        );

        typeRegistry.registerType(
            Identifiers.AuditProgramTransitionEventType,
            AuditProgramTransitionEventType.class,
            AuditProgramTransitionEventNode.class,
            AuditProgramTransitionEventNode::new
        );

        typeRegistry.registerType(
            Identifiers.NonTransparentNetworkRedundancyType,
            NonTransparentNetworkRedundancyType.class,
            NonTransparentNetworkRedundancyNode.class,
            NonTransparentNetworkRedundancyNode::new
        );

        typeRegistry.registerType(
            Identifiers.ProgramTransitionAuditEventType,
            ProgramTransitionAuditEventType.class,
            ProgramTransitionAuditEventNode.class,
            ProgramTransitionAuditEventNode::new
        );
    }

    private static void registerVariables(TypeRegistry typeRegistry) {
        typeRegistry.registerType(
            Identifiers.BaseVariableType,
            BaseVariableType.class,
            BaseVariableNode.class,
            BaseVariableNode::new
        );

        typeRegistry.registerType(
            Identifiers.BaseDataVariableType,
            BaseDataVariableType.class,
            BaseDataVariableNode.class,
            BaseDataVariableNode::new
        );

        typeRegistry.registerType(
            Identifiers.PropertyType,
            PropertyType.class,
            PropertyNode.class,
            PropertyNode::new
        );

        typeRegistry.registerType(
            Identifiers.DataTypeDescriptionType,
            DataTypeDescriptionType.class,
            DataTypeDescriptionNode.class,
            DataTypeDescriptionNode::new
        );

        typeRegistry.registerType(
            Identifiers.DataTypeDictionaryType,
            DataTypeDictionaryType.class,
            DataTypeDictionaryNode.class,
            DataTypeDictionaryNode::new
        );

        typeRegistry.registerType(
            Identifiers.TwoStateVariableType,
            TwoStateVariableType.class,
            TwoStateVariableNode.class,
            TwoStateVariableNode::new
        );

        typeRegistry.registerType(
            Identifiers.ConditionVariableType,
            ConditionVariableType.class,
            ConditionVariableNode.class,
            ConditionVariableNode::new
        );

        typeRegistry.registerType(
            Identifiers.ServerVendorCapabilityType,
            ServerVendorCapabilityType.class,
            ServerVendorCapabilityNode.class,
            ServerVendorCapabilityNode::new
        );

        typeRegistry.registerType(
            Identifiers.ServerStatusType,
            ServerStatusType.class,
            ServerStatusNode.class,
            ServerStatusNode::new
        );

        typeRegistry.registerType(
            Identifiers.ServerDiagnosticsSummaryType,
            ServerDiagnosticsSummaryType.class,
            ServerDiagnosticsSummaryNode.class,
            ServerDiagnosticsSummaryNode::new
        );

        typeRegistry.registerType(
            Identifiers.SamplingIntervalDiagnosticsArrayType,
            SamplingIntervalDiagnosticsArrayType.class,
            SamplingIntervalDiagnosticsArrayNode.class,
            SamplingIntervalDiagnosticsArrayNode::new
        );

        typeRegistry.registerType(
            Identifiers.SamplingIntervalDiagnosticsType,
            SamplingIntervalDiagnosticsType.class,
            SamplingIntervalDiagnosticsNode.class,
            SamplingIntervalDiagnosticsNode::new
        );

        typeRegistry.registerType(
            Identifiers.SubscriptionDiagnosticsArrayType,
            SubscriptionDiagnosticsArrayType.class,
            SubscriptionDiagnosticsArrayNode.class,
            SubscriptionDiagnosticsArrayNode::new
        );

        typeRegistry.registerType(
            Identifiers.SubscriptionDiagnosticsType,
            SubscriptionDiagnosticsType.class,
            SubscriptionDiagnosticsNode.class,
            SubscriptionDiagnosticsNode::new
        );

        typeRegistry.registerType(
            Identifiers.SessionDiagnosticsArrayType,
            SessionDiagnosticsArrayType.class,
            SessionDiagnosticsArrayNode.class,
            SessionDiagnosticsArrayNode::new
        );

        typeRegistry.registerType(
            Identifiers.SessionDiagnosticsVariableType,
            SessionDiagnosticsVariableType.class,
            SessionDiagnosticsVariableNode.class,
            SessionDiagnosticsVariableNode::new
        );

        typeRegistry.registerType(
            Identifiers.SessionSecurityDiagnosticsArrayType,
            SessionSecurityDiagnosticsArrayType.class,
            SessionSecurityDiagnosticsArrayNode.class,
            SessionSecurityDiagnosticsArrayNode::new
        );

        typeRegistry.registerType(
            Identifiers.SessionSecurityDiagnosticsType,
            SessionSecurityDiagnosticsType.class,
            SessionSecurityDiagnosticsNode.class,
            SessionSecurityDiagnosticsNode::new
        );

        typeRegistry.registerType(
            Identifiers.DataItemType,
            DataItemType.class,
            DataItemNode.class,
            DataItemNode::new
        );

        typeRegistry.registerType(
            Identifiers.AnalogItemType,
            AnalogItemType.class,
            AnalogItemNode.class,
            AnalogItemNode::new
        );

        typeRegistry.registerType(
            Identifiers.DiscreteItemType,
            DiscreteItemType.class,
            DiscreteItemNode.class,
            DiscreteItemNode::new
        );

        typeRegistry.registerType(
            Identifiers.TwoStateDiscreteType,
            TwoStateDiscreteType.class,
            TwoStateDiscreteNode.class,
            TwoStateDiscreteNode::new
        );

        typeRegistry.registerType(
            Identifiers.MultiStateDiscreteType,
            MultiStateDiscreteType.class,
            MultiStateDiscreteNode.class,
            MultiStateDiscreteNode::new
        );

        typeRegistry.registerType(
            Identifiers.ProgramDiagnosticType,
            ProgramDiagnosticType.class,
            ProgramDiagnosticNode.class,
            ProgramDiagnosticNode::new
        );

        typeRegistry.registerType(
            Identifiers.StateVariableType,
            StateVariableType.class,
            StateVariableNode.class,
            StateVariableNode::new
        );

        typeRegistry.registerType(
            Identifiers.FiniteStateVariableType,
            FiniteStateVariableType.class,
            FiniteStateVariableNode.class,
            FiniteStateVariableNode::new
        );

        typeRegistry.registerType(
            Identifiers.TransitionVariableType,
            TransitionVariableType.class,
            TransitionVariableNode.class,
            TransitionVariableNode::new
        );

        typeRegistry.registerType(
            Identifiers.FiniteTransitionVariableType,
            FiniteTransitionVariableType.class,
            FiniteTransitionVariableNode.class,
            FiniteTransitionVariableNode::new
        );

        typeRegistry.registerType(
            Identifiers.MultiStateValueDiscreteType,
            MultiStateValueDiscreteType.class,
            MultiStateValueDiscreteNode.class,
            MultiStateValueDiscreteNode::new
        );

        typeRegistry.registerType(
            Identifiers.BuildInfoType,
            BuildInfoType.class,
            BuildInfoNode.class,
            BuildInfoNode::new
        );

        typeRegistry.registerType(
            Identifiers.OptionSetType,
            OptionSetType.class,
            OptionSetNode.class,
            OptionSetNode::new
        );

        typeRegistry.registerType(
            Identifiers.ArrayItemType,
            ArrayItemType.class,
            ArrayItemNode.class,
            ArrayItemNode::new
        );

        typeRegistry.registerType(
            Identifiers.YArrayItemType,
            YArrayItemType.class,
            YArrayItemNode.class,
            YArrayItemNode::new
        );

        typeRegistry.registerType(
            Identifiers.XYArrayItemType,
            XYArrayItemType.class,
            XYArrayItemNode.class,
            XYArrayItemNode::new
        );

        typeRegistry.registerType(
            Identifiers.ImageItemType,
            ImageItemType.class,
            ImageItemNode.class,
            ImageItemNode::new
        );

        typeRegistry.registerType(
            Identifiers.CubeItemType,
            CubeItemType.class,
            CubeItemNode.class,
            CubeItemNode::new
        );

        typeRegistry.registerType(
            Identifiers.NDimensionArrayItemType,
            NDimensionArrayItemType.class,
            NDimensionArrayItemNode.class,
            NDimensionArrayItemNode::new
        );
    }

}
