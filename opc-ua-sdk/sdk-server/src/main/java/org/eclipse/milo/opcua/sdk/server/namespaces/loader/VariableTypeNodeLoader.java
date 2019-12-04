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
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

class VariableTypeNodeLoader {
    private final UaNodeContext context;

    private final NodeManager<UaNode> nodeManager;

    VariableTypeNodeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager) {
        this.context = context;
        this.nodeManager = nodeManager;
    }

    private void loadNode0() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.BaseVariableType, new QualifiedName(0, "BaseVariableType"), new LocalizedText("en", "BaseVariableType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.BaseDataType, -2, new UInteger[]{}, true);
        this.nodeManager.addNode(node);
    }

    private void loadNode1() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.BaseDataVariableType, new QualifiedName(0, "BaseDataVariableType"), new LocalizedText("en", "BaseDataVariableType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.BaseDataType, -2, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.BaseDataVariableType, Identifiers.HasSubtype, Identifiers.BaseVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode2() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.PropertyType, new QualifiedName(0, "PropertyType"), new LocalizedText("en", "PropertyType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.BaseDataType, -2, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.PropertyType, Identifiers.HasSubtype, Identifiers.BaseVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode3() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.DataTypeDescriptionType, new QualifiedName(0, "DataTypeDescriptionType"), new LocalizedText("en", "DataTypeDescriptionType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.String, -1, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.DataTypeDescriptionType, Identifiers.HasProperty, Identifiers.DataTypeDescriptionType_DataTypeVersion.expanded(), true));
        node.addReference(new Reference(Identifiers.DataTypeDescriptionType, Identifiers.HasProperty, Identifiers.DataTypeDescriptionType_DictionaryFragment.expanded(), true));
        node.addReference(new Reference(Identifiers.DataTypeDescriptionType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode4() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.DataTypeDictionaryType, new QualifiedName(0, "DataTypeDictionaryType"), new LocalizedText("en", "DataTypeDictionaryType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.ByteString, -1, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.DataTypeDictionaryType, Identifiers.HasProperty, Identifiers.DataTypeDictionaryType_DataTypeVersion.expanded(), true));
        node.addReference(new Reference(Identifiers.DataTypeDictionaryType, Identifiers.HasProperty, Identifiers.DataTypeDictionaryType_NamespaceUri.expanded(), true));
        node.addReference(new Reference(Identifiers.DataTypeDictionaryType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode5() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.TwoStateVariableType, new QualifiedName(0, "TwoStateVariableType"), new LocalizedText("en", "TwoStateVariableType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.LocalizedText, -1, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.TwoStateVariableType, Identifiers.HasProperty, Identifiers.TwoStateVariableType_Id.expanded(), true));
        node.addReference(new Reference(Identifiers.TwoStateVariableType, Identifiers.HasProperty, Identifiers.TwoStateVariableType_TransitionTime.expanded(), true));
        node.addReference(new Reference(Identifiers.TwoStateVariableType, Identifiers.HasProperty, Identifiers.TwoStateVariableType_EffectiveTransitionTime.expanded(), true));
        node.addReference(new Reference(Identifiers.TwoStateVariableType, Identifiers.HasProperty, Identifiers.TwoStateVariableType_TrueState.expanded(), true));
        node.addReference(new Reference(Identifiers.TwoStateVariableType, Identifiers.HasProperty, Identifiers.TwoStateVariableType_FalseState.expanded(), true));
        node.addReference(new Reference(Identifiers.TwoStateVariableType, Identifiers.HasSubtype, Identifiers.StateVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode6() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.ConditionVariableType, new QualifiedName(0, "ConditionVariableType"), new LocalizedText("en", "ConditionVariableType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.BaseDataType, -2, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.ConditionVariableType, Identifiers.HasProperty, Identifiers.ConditionVariableType_SourceTimestamp.expanded(), true));
        node.addReference(new Reference(Identifiers.ConditionVariableType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode7() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.ServerVendorCapabilityType, new QualifiedName(0, "ServerVendorCapabilityType"), new LocalizedText("en", "ServerVendorCapabilityType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.BaseDataType, -1, new UInteger[]{}, true);
        node.addReference(new Reference(Identifiers.ServerVendorCapabilityType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode8() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.ServerStatusType, new QualifiedName(0, "ServerStatusType"), new LocalizedText("en", "ServerStatusType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.ServerStatusDataType, -1, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.ServerStatusType, Identifiers.HasComponent, Identifiers.ServerStatusType_StartTime.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerStatusType, Identifiers.HasComponent, Identifiers.ServerStatusType_CurrentTime.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerStatusType, Identifiers.HasComponent, Identifiers.ServerStatusType_State.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerStatusType, Identifiers.HasComponent, Identifiers.ServerStatusType_BuildInfo.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerStatusType, Identifiers.HasComponent, Identifiers.ServerStatusType_SecondsTillShutdown.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerStatusType, Identifiers.HasComponent, Identifiers.ServerStatusType_ShutdownReason.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerStatusType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode9() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.ServerDiagnosticsSummaryType, new QualifiedName(0, "ServerDiagnosticsSummaryType"), new LocalizedText("en", "ServerDiagnosticsSummaryType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.ServerDiagnosticsSummaryDataType, -1, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryType, Identifiers.HasComponent, Identifiers.ServerDiagnosticsSummaryType_ServerViewCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryType, Identifiers.HasComponent, Identifiers.ServerDiagnosticsSummaryType_CurrentSessionCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryType, Identifiers.HasComponent, Identifiers.ServerDiagnosticsSummaryType_CumulatedSessionCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryType, Identifiers.HasComponent, Identifiers.ServerDiagnosticsSummaryType_SecurityRejectedSessionCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryType, Identifiers.HasComponent, Identifiers.ServerDiagnosticsSummaryType_RejectedSessionCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryType, Identifiers.HasComponent, Identifiers.ServerDiagnosticsSummaryType_SessionTimeoutCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryType, Identifiers.HasComponent, Identifiers.ServerDiagnosticsSummaryType_SessionAbortCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryType, Identifiers.HasComponent, Identifiers.ServerDiagnosticsSummaryType_PublishingIntervalCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryType, Identifiers.HasComponent, Identifiers.ServerDiagnosticsSummaryType_CurrentSubscriptionCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryType, Identifiers.HasComponent, Identifiers.ServerDiagnosticsSummaryType_CumulatedSubscriptionCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryType, Identifiers.HasComponent, Identifiers.ServerDiagnosticsSummaryType_SecurityRejectedRequestsCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryType, Identifiers.HasComponent, Identifiers.ServerDiagnosticsSummaryType_RejectedRequestsCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode10() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.SamplingIntervalDiagnosticsArrayType, new QualifiedName(0, "SamplingIntervalDiagnosticsArrayType"), new LocalizedText("en", "SamplingIntervalDiagnosticsArrayType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.SamplingIntervalDiagnosticsDataType, 1, new UInteger[]{UInteger.valueOf(0)}, false);
        node.addReference(new Reference(Identifiers.SamplingIntervalDiagnosticsArrayType, Identifiers.HasComponent, Identifiers.SamplingIntervalDiagnosticsArrayType_SamplingIntervalDiagnostics.expanded(), true));
        node.addReference(new Reference(Identifiers.SamplingIntervalDiagnosticsArrayType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode11() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.SamplingIntervalDiagnosticsType, new QualifiedName(0, "SamplingIntervalDiagnosticsType"), new LocalizedText("en", "SamplingIntervalDiagnosticsType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.SamplingIntervalDiagnosticsDataType, -1, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.SamplingIntervalDiagnosticsType, Identifiers.HasComponent, Identifiers.SamplingIntervalDiagnosticsType_SamplingInterval.expanded(), true));
        node.addReference(new Reference(Identifiers.SamplingIntervalDiagnosticsType, Identifiers.HasComponent, Identifiers.SamplingIntervalDiagnosticsType_SampledMonitoredItemsCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SamplingIntervalDiagnosticsType, Identifiers.HasComponent, Identifiers.SamplingIntervalDiagnosticsType_MaxSampledMonitoredItemsCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SamplingIntervalDiagnosticsType, Identifiers.HasComponent, Identifiers.SamplingIntervalDiagnosticsType_DisabledMonitoredItemsSamplingCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SamplingIntervalDiagnosticsType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode12() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.SubscriptionDiagnosticsArrayType, new QualifiedName(0, "SubscriptionDiagnosticsArrayType"), new LocalizedText("en", "SubscriptionDiagnosticsArrayType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.SubscriptionDiagnosticsDataType, 1, new UInteger[]{UInteger.valueOf(0)}, false);
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsArrayType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsArrayType_SubscriptionDiagnostics.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsArrayType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode13() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.SubscriptionDiagnosticsType, new QualifiedName(0, "SubscriptionDiagnosticsType"), new LocalizedText("en", "SubscriptionDiagnosticsType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.SubscriptionDiagnosticsDataType, -1, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_SessionId.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_SubscriptionId.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_Priority.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_PublishingInterval.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_MaxKeepAliveCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_MaxLifetimeCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_MaxNotificationsPerPublish.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_PublishingEnabled.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_ModifyCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_EnableCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_DisableCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_RepublishRequestCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_RepublishMessageRequestCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_RepublishMessageCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_TransferRequestCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_TransferredToAltClientCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_TransferredToSameClientCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_PublishRequestCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_DataChangeNotificationsCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_EventNotificationsCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_NotificationsCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_LatePublishRequestCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_CurrentKeepAliveCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_CurrentLifetimeCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_UnacknowledgedMessageCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_DiscardedMessageCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_MonitoredItemCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_DisabledMonitoredItemCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_MonitoringQueueOverflowCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_NextSequenceNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasComponent, Identifiers.SubscriptionDiagnosticsType_EventQueueOverFlowCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode14() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.SessionDiagnosticsArrayType, new QualifiedName(0, "SessionDiagnosticsArrayType"), new LocalizedText("en", "SessionDiagnosticsArrayType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.SessionDiagnosticsDataType, 1, new UInteger[]{UInteger.valueOf(0)}, false);
        node.addReference(new Reference(Identifiers.SessionDiagnosticsArrayType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsArrayType_SessionDiagnostics.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsArrayType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode15() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.SessionDiagnosticsVariableType, new QualifiedName(0, "SessionDiagnosticsVariableType"), new LocalizedText("en", "SessionDiagnosticsVariableType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.SessionDiagnosticsDataType, -1, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_SessionId.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_SessionName.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_ClientDescription.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_ServerUri.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_EndpointUrl.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_LocaleIds.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_ActualSessionTimeout.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_MaxResponseMessageSize.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_ClientConnectionTime.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_ClientLastContactTime.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_CurrentSubscriptionsCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_CurrentMonitoredItemsCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_CurrentPublishRequestsInQueue.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_TotalRequestCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_UnauthorizedRequestCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_ReadCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_HistoryReadCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_WriteCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_HistoryUpdateCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_CallCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_CreateMonitoredItemsCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_ModifyMonitoredItemsCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_SetMonitoringModeCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_SetTriggeringCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_DeleteMonitoredItemsCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_CreateSubscriptionCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_ModifySubscriptionCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_SetPublishingModeCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_PublishCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_RepublishCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_TransferSubscriptionsCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_DeleteSubscriptionsCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_AddNodesCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_AddReferencesCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_DeleteNodesCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_DeleteReferencesCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_BrowseCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_BrowseNextCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_TranslateBrowsePathsToNodeIdsCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_QueryFirstCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_QueryNextCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_RegisterNodesCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasComponent, Identifiers.SessionDiagnosticsVariableType_UnregisterNodesCount.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsVariableType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode16() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.SessionSecurityDiagnosticsArrayType, new QualifiedName(0, "SessionSecurityDiagnosticsArrayType"), new LocalizedText("en", "SessionSecurityDiagnosticsArrayType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.SessionSecurityDiagnosticsDataType, 1, new UInteger[]{UInteger.valueOf(0)}, false);
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsArrayType, Identifiers.HasComponent, Identifiers.SessionSecurityDiagnosticsArrayType_SessionSecurityDiagnostics.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsArrayType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode17() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.SessionSecurityDiagnosticsType, new QualifiedName(0, "SessionSecurityDiagnosticsType"), new LocalizedText("en", "SessionSecurityDiagnosticsType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.SessionSecurityDiagnosticsDataType, -1, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsType, Identifiers.HasComponent, Identifiers.SessionSecurityDiagnosticsType_SessionId.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsType, Identifiers.HasComponent, Identifiers.SessionSecurityDiagnosticsType_ClientUserIdOfSession.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsType, Identifiers.HasComponent, Identifiers.SessionSecurityDiagnosticsType_ClientUserIdHistory.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsType, Identifiers.HasComponent, Identifiers.SessionSecurityDiagnosticsType_AuthenticationMechanism.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsType, Identifiers.HasComponent, Identifiers.SessionSecurityDiagnosticsType_Encoding.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsType, Identifiers.HasComponent, Identifiers.SessionSecurityDiagnosticsType_TransportProtocol.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsType, Identifiers.HasComponent, Identifiers.SessionSecurityDiagnosticsType_SecurityMode.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsType, Identifiers.HasComponent, Identifiers.SessionSecurityDiagnosticsType_SecurityPolicyUri.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsType, Identifiers.HasComponent, Identifiers.SessionSecurityDiagnosticsType_ClientCertificate.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode18() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.DataItemType, new QualifiedName(0, "DataItemType"), new LocalizedText("en", "DataItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.BaseDataType, -2, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.DataItemType, Identifiers.HasProperty, Identifiers.DataItemType_Definition.expanded(), true));
        node.addReference(new Reference(Identifiers.DataItemType, Identifiers.HasProperty, Identifiers.DataItemType_ValuePrecision.expanded(), true));
        node.addReference(new Reference(Identifiers.DataItemType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode19() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.AnalogItemType, new QualifiedName(0, "AnalogItemType"), new LocalizedText("en", "AnalogItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.Number, -2, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.AnalogItemType, Identifiers.HasProperty, Identifiers.AnalogItemType_InstrumentRange.expanded(), true));
        node.addReference(new Reference(Identifiers.AnalogItemType, Identifiers.HasProperty, Identifiers.AnalogItemType_EURange.expanded(), true));
        node.addReference(new Reference(Identifiers.AnalogItemType, Identifiers.HasProperty, Identifiers.AnalogItemType_EngineeringUnits.expanded(), true));
        node.addReference(new Reference(Identifiers.AnalogItemType, Identifiers.HasSubtype, Identifiers.DataItemType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode20() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.DiscreteItemType, new QualifiedName(0, "DiscreteItemType"), new LocalizedText("en", "DiscreteItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.BaseDataType, -2, new UInteger[]{}, true);
        node.addReference(new Reference(Identifiers.DiscreteItemType, Identifiers.HasSubtype, Identifiers.DataItemType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode21() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.TwoStateDiscreteType, new QualifiedName(0, "TwoStateDiscreteType"), new LocalizedText("en", "TwoStateDiscreteType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.Boolean, -2, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.TwoStateDiscreteType, Identifiers.HasProperty, Identifiers.TwoStateDiscreteType_FalseState.expanded(), true));
        node.addReference(new Reference(Identifiers.TwoStateDiscreteType, Identifiers.HasProperty, Identifiers.TwoStateDiscreteType_TrueState.expanded(), true));
        node.addReference(new Reference(Identifiers.TwoStateDiscreteType, Identifiers.HasSubtype, Identifiers.DiscreteItemType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode22() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.MultiStateDiscreteType, new QualifiedName(0, "MultiStateDiscreteType"), new LocalizedText("en", "MultiStateDiscreteType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.UInteger, -2, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.MultiStateDiscreteType, Identifiers.HasProperty, Identifiers.MultiStateDiscreteType_EnumStrings.expanded(), true));
        node.addReference(new Reference(Identifiers.MultiStateDiscreteType, Identifiers.HasSubtype, Identifiers.DiscreteItemType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode23() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.ProgramDiagnosticType, new QualifiedName(0, "ProgramDiagnosticType"), new LocalizedText("en", "ProgramDiagnosticType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.ProgramDiagnosticDataType, -1, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.ProgramDiagnosticType, Identifiers.HasProperty, Identifiers.ProgramDiagnosticType_CreateSessionId.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramDiagnosticType, Identifiers.HasProperty, Identifiers.ProgramDiagnosticType_CreateClientName.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramDiagnosticType, Identifiers.HasProperty, Identifiers.ProgramDiagnosticType_InvocationCreationTime.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramDiagnosticType, Identifiers.HasProperty, Identifiers.ProgramDiagnosticType_LastTransitionTime.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramDiagnosticType, Identifiers.HasProperty, Identifiers.ProgramDiagnosticType_LastMethodCall.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramDiagnosticType, Identifiers.HasProperty, Identifiers.ProgramDiagnosticType_LastMethodSessionId.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramDiagnosticType, Identifiers.HasProperty, Identifiers.ProgramDiagnosticType_LastMethodInputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramDiagnosticType, Identifiers.HasProperty, Identifiers.ProgramDiagnosticType_LastMethodOutputArguments.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramDiagnosticType, Identifiers.HasProperty, Identifiers.ProgramDiagnosticType_LastMethodCallTime.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramDiagnosticType, Identifiers.HasProperty, Identifiers.ProgramDiagnosticType_LastMethodReturnStatus.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramDiagnosticType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode24() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.StateVariableType, new QualifiedName(0, "StateVariableType"), new LocalizedText("en", "StateVariableType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.LocalizedText, -1, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.StateVariableType, Identifiers.HasProperty, Identifiers.StateVariableType_Id.expanded(), true));
        node.addReference(new Reference(Identifiers.StateVariableType, Identifiers.HasProperty, Identifiers.StateVariableType_Name.expanded(), true));
        node.addReference(new Reference(Identifiers.StateVariableType, Identifiers.HasProperty, Identifiers.StateVariableType_Number.expanded(), true));
        node.addReference(new Reference(Identifiers.StateVariableType, Identifiers.HasProperty, Identifiers.StateVariableType_EffectiveDisplayName.expanded(), true));
        node.addReference(new Reference(Identifiers.StateVariableType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode25() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.FiniteStateVariableType, new QualifiedName(0, "FiniteStateVariableType"), new LocalizedText("en", "FiniteStateVariableType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.LocalizedText, -1, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.FiniteStateVariableType, Identifiers.HasProperty, Identifiers.FiniteStateVariableType_Id.expanded(), true));
        node.addReference(new Reference(Identifiers.FiniteStateVariableType, Identifiers.HasSubtype, Identifiers.StateVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode26() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.TransitionVariableType, new QualifiedName(0, "TransitionVariableType"), new LocalizedText("en", "TransitionVariableType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.LocalizedText, -1, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.TransitionVariableType, Identifiers.HasProperty, Identifiers.TransitionVariableType_Id.expanded(), true));
        node.addReference(new Reference(Identifiers.TransitionVariableType, Identifiers.HasProperty, Identifiers.TransitionVariableType_Name.expanded(), true));
        node.addReference(new Reference(Identifiers.TransitionVariableType, Identifiers.HasProperty, Identifiers.TransitionVariableType_Number.expanded(), true));
        node.addReference(new Reference(Identifiers.TransitionVariableType, Identifiers.HasProperty, Identifiers.TransitionVariableType_TransitionTime.expanded(), true));
        node.addReference(new Reference(Identifiers.TransitionVariableType, Identifiers.HasProperty, Identifiers.TransitionVariableType_EffectiveTransitionTime.expanded(), true));
        node.addReference(new Reference(Identifiers.TransitionVariableType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode27() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.FiniteTransitionVariableType, new QualifiedName(0, "FiniteTransitionVariableType"), new LocalizedText("en", "FiniteTransitionVariableType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.LocalizedText, -1, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.FiniteTransitionVariableType, Identifiers.HasProperty, Identifiers.FiniteTransitionVariableType_Id.expanded(), true));
        node.addReference(new Reference(Identifiers.FiniteTransitionVariableType, Identifiers.HasSubtype, Identifiers.TransitionVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode28() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.MultiStateValueDiscreteType, new QualifiedName(0, "MultiStateValueDiscreteType"), new LocalizedText("en", "MultiStateValueDiscreteType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.Number, -2, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.MultiStateValueDiscreteType, Identifiers.HasProperty, Identifiers.MultiStateValueDiscreteType_EnumValues.expanded(), true));
        node.addReference(new Reference(Identifiers.MultiStateValueDiscreteType, Identifiers.HasProperty, Identifiers.MultiStateValueDiscreteType_ValueAsText.expanded(), true));
        node.addReference(new Reference(Identifiers.MultiStateValueDiscreteType, Identifiers.HasSubtype, Identifiers.DiscreteItemType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode29() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.BuildInfoType, new QualifiedName(0, "BuildInfoType"), new LocalizedText("en", "BuildInfoType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.BuildInfo, -1, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.BuildInfoType, Identifiers.HasComponent, Identifiers.BuildInfoType_ProductUri.expanded(), true));
        node.addReference(new Reference(Identifiers.BuildInfoType, Identifiers.HasComponent, Identifiers.BuildInfoType_ManufacturerName.expanded(), true));
        node.addReference(new Reference(Identifiers.BuildInfoType, Identifiers.HasComponent, Identifiers.BuildInfoType_ProductName.expanded(), true));
        node.addReference(new Reference(Identifiers.BuildInfoType, Identifiers.HasComponent, Identifiers.BuildInfoType_SoftwareVersion.expanded(), true));
        node.addReference(new Reference(Identifiers.BuildInfoType, Identifiers.HasComponent, Identifiers.BuildInfoType_BuildNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.BuildInfoType, Identifiers.HasComponent, Identifiers.BuildInfoType_BuildDate.expanded(), true));
        node.addReference(new Reference(Identifiers.BuildInfoType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode30() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.OptionSetType, new QualifiedName(0, "OptionSetType"), new LocalizedText("en", "OptionSetType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.BaseDataType, -1, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.OptionSetType, Identifiers.HasProperty, Identifiers.OptionSetType_OptionSetValues.expanded(), true));
        node.addReference(new Reference(Identifiers.OptionSetType, Identifiers.HasProperty, Identifiers.OptionSetType_BitMask.expanded(), true));
        node.addReference(new Reference(Identifiers.OptionSetType, Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode31() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.ArrayItemType, new QualifiedName(0, "ArrayItemType"), new LocalizedText("en", "ArrayItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.BaseDataType, 0, new UInteger[]{}, true);
        node.addReference(new Reference(Identifiers.ArrayItemType, Identifiers.HasProperty, Identifiers.ArrayItemType_InstrumentRange.expanded(), true));
        node.addReference(new Reference(Identifiers.ArrayItemType, Identifiers.HasProperty, Identifiers.ArrayItemType_EURange.expanded(), true));
        node.addReference(new Reference(Identifiers.ArrayItemType, Identifiers.HasProperty, Identifiers.ArrayItemType_EngineeringUnits.expanded(), true));
        node.addReference(new Reference(Identifiers.ArrayItemType, Identifiers.HasProperty, Identifiers.ArrayItemType_Title.expanded(), true));
        node.addReference(new Reference(Identifiers.ArrayItemType, Identifiers.HasProperty, Identifiers.ArrayItemType_AxisScaleType.expanded(), true));
        node.addReference(new Reference(Identifiers.ArrayItemType, Identifiers.HasSubtype, Identifiers.DataItemType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode32() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.YArrayItemType, new QualifiedName(0, "YArrayItemType"), new LocalizedText("en", "YArrayItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.BaseDataType, 1, new UInteger[]{UInteger.valueOf(0)}, false);
        node.addReference(new Reference(Identifiers.YArrayItemType, Identifiers.HasProperty, Identifiers.YArrayItemType_XAxisDefinition.expanded(), true));
        node.addReference(new Reference(Identifiers.YArrayItemType, Identifiers.HasSubtype, Identifiers.ArrayItemType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode33() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.XYArrayItemType, new QualifiedName(0, "XYArrayItemType"), new LocalizedText("en", "XYArrayItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.XVType, 1, new UInteger[]{UInteger.valueOf(0)}, false);
        node.addReference(new Reference(Identifiers.XYArrayItemType, Identifiers.HasProperty, Identifiers.XYArrayItemType_XAxisDefinition.expanded(), true));
        node.addReference(new Reference(Identifiers.XYArrayItemType, Identifiers.HasSubtype, Identifiers.ArrayItemType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode34() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.ImageItemType, new QualifiedName(0, "ImageItemType"), new LocalizedText("en", "ImageItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.BaseDataType, 2, new UInteger[]{UInteger.valueOf(0), UInteger.valueOf(0)}, false);
        node.addReference(new Reference(Identifiers.ImageItemType, Identifiers.HasProperty, Identifiers.ImageItemType_XAxisDefinition.expanded(), true));
        node.addReference(new Reference(Identifiers.ImageItemType, Identifiers.HasProperty, Identifiers.ImageItemType_YAxisDefinition.expanded(), true));
        node.addReference(new Reference(Identifiers.ImageItemType, Identifiers.HasSubtype, Identifiers.ArrayItemType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode35() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.CubeItemType, new QualifiedName(0, "CubeItemType"), new LocalizedText("en", "CubeItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.BaseDataType, 3, new UInteger[]{UInteger.valueOf(0), UInteger.valueOf(0), UInteger.valueOf(0)}, false);
        node.addReference(new Reference(Identifiers.CubeItemType, Identifiers.HasProperty, Identifiers.CubeItemType_XAxisDefinition.expanded(), true));
        node.addReference(new Reference(Identifiers.CubeItemType, Identifiers.HasProperty, Identifiers.CubeItemType_YAxisDefinition.expanded(), true));
        node.addReference(new Reference(Identifiers.CubeItemType, Identifiers.HasProperty, Identifiers.CubeItemType_ZAxisDefinition.expanded(), true));
        node.addReference(new Reference(Identifiers.CubeItemType, Identifiers.HasSubtype, Identifiers.ArrayItemType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode36() {
        UaVariableTypeNode node = new UaVariableTypeNode(this.context, Identifiers.NDimensionArrayItemType, new QualifiedName(0, "NDimensionArrayItemType"), new LocalizedText("en", "NDimensionArrayItemType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), new DataValue(Variant.NULL_VALUE), Identifiers.BaseDataType, 0, new UInteger[]{}, false);
        node.addReference(new Reference(Identifiers.NDimensionArrayItemType, Identifiers.HasProperty, Identifiers.NDimensionArrayItemType_AxisDefinition.expanded(), true));
        node.addReference(new Reference(Identifiers.NDimensionArrayItemType, Identifiers.HasSubtype, Identifiers.ArrayItemType.expanded(), false));
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
