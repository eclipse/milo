/*
 * Copyright (c) 2016 Kevin Herron
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

package org.eclipse.milo.opcua.stack.core.serialization;

import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.AttributeWriteMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.AxisScaleEnumeration;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ComplianceLevel;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DataChangeTrigger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DeadbandType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.EnumeratedTestType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ExceptionDeviationFormat;
import org.eclipse.milo.opcua.stack.core.types.enumerated.FilterOperator;
import org.eclipse.milo.opcua.stack.core.types.enumerated.HistoryUpdateType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ModelChangeStructureVerbMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NamingRuleType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeAttributesMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeIdType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.OpenFileMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.RedundancySupport;
import org.eclipse.milo.opcua.stack.core.types.enumerated.SecurityTokenRequestType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TrustListMasks;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesResult;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.AggregateConfiguration;
import org.eclipse.milo.opcua.stack.core.types.structured.AggregateFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.AggregateFilterResult;
import org.eclipse.milo.opcua.stack.core.types.structured.Annotation;
import org.eclipse.milo.opcua.stack.core.types.structured.AnonymousIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.types.structured.ArrayTestType;
import org.eclipse.milo.opcua.stack.core.types.structured.AttributeOperand;
import org.eclipse.milo.opcua.stack.core.types.structured.AxisInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowsePath;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowsePathResult;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowsePathTarget;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;
import org.eclipse.milo.opcua.stack.core.types.structured.CallRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ChannelSecurityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSecureChannelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSecureChannelResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ComplexNumberType;
import org.eclipse.milo.opcua.stack.core.types.structured.CompositeTestType;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilterElement;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilterElementResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilterResult;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DataChangeFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.DataChangeNotification;
import org.eclipse.milo.opcua.stack.core.types.structured.DataTypeAttributes;
import org.eclipse.milo.opcua.stack.core.types.structured.DataTypeNode;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteAtTimeDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteEventDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteRawModifiedDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DiscoveryConfiguration;
import org.eclipse.milo.opcua.stack.core.types.structured.DoubleComplexNumberType;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.ElementOperand;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointConfiguration;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointUrlListDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumValueType;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFieldList;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilterResult;
import org.eclipse.milo.opcua.stack.core.types.structured.EventNotificationList;
import org.eclipse.milo.opcua.stack.core.types.structured.FilterOperand;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersOnNetworkRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersOnNetworkResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryData;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryEvent;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryEventFieldList;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryModifiedData;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadResult;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateResult;
import org.eclipse.milo.opcua.stack.core.types.structured.InstanceNode;
import org.eclipse.milo.opcua.stack.core.types.structured.IssuedIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.KerberosIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.LiteralOperand;
import org.eclipse.milo.opcua.stack.core.types.structured.MdnsDiscoveryConfiguration;
import org.eclipse.milo.opcua.stack.core.types.structured.MethodAttributes;
import org.eclipse.milo.opcua.stack.core.types.structured.MethodNode;
import org.eclipse.milo.opcua.stack.core.types.structured.ModelChangeStructureDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ModificationInfo;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateResult;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyResult;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemNotification;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringFilterResult;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.NetworkGroupDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.Node;
import org.eclipse.milo.opcua.stack.core.types.structured.NodeAttributes;
import org.eclipse.milo.opcua.stack.core.types.structured.NodeReference;
import org.eclipse.milo.opcua.stack.core.types.structured.NodeTypeDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.NotificationData;
import org.eclipse.milo.opcua.stack.core.types.structured.NotificationMessage;
import org.eclipse.milo.opcua.stack.core.types.structured.ObjectAttributes;
import org.eclipse.milo.opcua.stack.core.types.structured.ObjectNode;
import org.eclipse.milo.opcua.stack.core.types.structured.ObjectTypeAttributes;
import org.eclipse.milo.opcua.stack.core.types.structured.ObjectTypeNode;
import org.eclipse.milo.opcua.stack.core.types.structured.OpenSecureChannelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.OpenSecureChannelResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.OptionSet;
import org.eclipse.milo.opcua.stack.core.types.structured.ParsingResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ProgramDiagnosticDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryDataDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryDataSet;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryFirstRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryFirstResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryNextRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryNextResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadAtTimeDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadEventDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadProcessedDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRawModifiedDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RedundantServerDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceNode;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceTypeAttributes;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceTypeNode;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServer2Request;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServer2Response;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServerRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServerResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisteredServer;
import org.eclipse.milo.opcua.stack.core.types.structured.RelativePath;
import org.eclipse.milo.opcua.stack.core.types.structured.RelativePathElement;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.SamplingIntervalDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ScalarTestType;
import org.eclipse.milo.opcua.stack.core.types.structured.SemanticChangeStructureDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerDiagnosticsSummaryDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerOnNetwork;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceCounterDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.SimpleAttributeOperand;
import org.eclipse.milo.opcua.stack.core.types.structured.SoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.StatusChangeNotification;
import org.eclipse.milo.opcua.stack.core.types.structured.StatusResult;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionAcknowledgement;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SupportedProfile;
import org.eclipse.milo.opcua.stack.core.types.structured.TestStackExRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TestStackExResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.TestStackRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TestStackResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.TimeZoneDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferResult;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.TrustListDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.TypeNode;
import org.eclipse.milo.opcua.stack.core.types.structured.Union;
import org.eclipse.milo.opcua.stack.core.types.structured.UnregisterNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.UnregisterNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.UpdateDataDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.UpdateEventDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.UpdateStructureDataDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserNameIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.core.types.structured.VariableAttributes;
import org.eclipse.milo.opcua.stack.core.types.structured.VariableNode;
import org.eclipse.milo.opcua.stack.core.types.structured.VariableTypeAttributes;
import org.eclipse.milo.opcua.stack.core.types.structured.VariableTypeNode;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewAttributes;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewNode;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.eclipse.milo.opcua.stack.core.types.structured.X509IdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.XVType;


/**
 * Registers the encoders and decoders for all the built-in enumerations and structures with {@link DelegateRegistry}.
 *
 * This class is semi-auto-generated; if the UA spec version changes and adds or removes structures these methods will
 * need to be updated.
 */
class DelegateRegistryInitializer {

    static void initialize() {
        initializeEnumerated();
        initializeStructured();
    }

    private static void initializeEnumerated() {
        DelegateRegistry.register(
            ApplicationType::encode,
            ApplicationType::decode,
            ApplicationType.class
        );
        DelegateRegistry.register(
            AttributeWriteMask::encode,
            AttributeWriteMask::decode,
            AttributeWriteMask.class
        );
        DelegateRegistry.register(
            AxisScaleEnumeration::encode,
            AxisScaleEnumeration::decode,
            AxisScaleEnumeration.class
        );
        DelegateRegistry.register(
            BrowseDirection::encode,
            BrowseDirection::decode,
            BrowseDirection.class
        );
        DelegateRegistry.register(
            BrowseResultMask::encode,
            BrowseResultMask::decode,
            BrowseResultMask.class
        );
        DelegateRegistry.register(
            ComplianceLevel::encode,
            ComplianceLevel::decode,
            ComplianceLevel.class
        );
        DelegateRegistry.register(
            DataChangeTrigger::encode,
            DataChangeTrigger::decode,
            DataChangeTrigger.class
        );
        DelegateRegistry.register(
            DeadbandType::encode,
            DeadbandType::decode,
            DeadbandType.class
        );
        DelegateRegistry.register(
            EnumeratedTestType::encode,
            EnumeratedTestType::decode,
            EnumeratedTestType.class
        );
        DelegateRegistry.register(
            ExceptionDeviationFormat::encode,
            ExceptionDeviationFormat::decode,
            ExceptionDeviationFormat.class
        );
        DelegateRegistry.register(
            FilterOperator::encode,
            FilterOperator::decode,
            FilterOperator.class
        );
        DelegateRegistry.register(
            HistoryUpdateType::encode,
            HistoryUpdateType::decode,
            HistoryUpdateType.class
        );
        DelegateRegistry.register(
            IdType::encode,
            IdType::decode,
            IdType.class
        );
        DelegateRegistry.register(
            MessageSecurityMode::encode,
            MessageSecurityMode::decode,
            MessageSecurityMode.class
        );
        DelegateRegistry.register(
            ModelChangeStructureVerbMask::encode,
            ModelChangeStructureVerbMask::decode,
            ModelChangeStructureVerbMask.class
        );
        DelegateRegistry.register(
            MonitoringMode::encode,
            MonitoringMode::decode,
            MonitoringMode.class
        );
        DelegateRegistry.register(
            NamingRuleType::encode,
            NamingRuleType::decode,
            NamingRuleType.class
        );
        DelegateRegistry.register(
            NodeAttributesMask::encode,
            NodeAttributesMask::decode,
            NodeAttributesMask.class
        );
        DelegateRegistry.register(
            NodeClass::encode,
            NodeClass::decode,
            NodeClass.class
        );
        DelegateRegistry.register(
            NodeIdType::encode,
            NodeIdType::decode,
            NodeIdType.class
        );
        DelegateRegistry.register(
            OpenFileMode::encode,
            OpenFileMode::decode,
            OpenFileMode.class
        );
        DelegateRegistry.register(
            PerformUpdateType::encode,
            PerformUpdateType::decode,
            PerformUpdateType.class
        );
        DelegateRegistry.register(
            RedundancySupport::encode,
            RedundancySupport::decode,
            RedundancySupport.class
        );
        DelegateRegistry.register(
            SecurityTokenRequestType::encode,
            SecurityTokenRequestType::decode,
            SecurityTokenRequestType.class
        );
        DelegateRegistry.register(
            ServerState::encode,
            ServerState::decode,
            ServerState.class
        );
        DelegateRegistry.register(
            TimestampsToReturn::encode,
            TimestampsToReturn::decode,
            TimestampsToReturn.class
        );
        DelegateRegistry.register(
            TrustListMasks::encode,
            TrustListMasks::decode,
            TrustListMasks.class
        );
        DelegateRegistry.register(
            UserTokenType::encode,
            UserTokenType::decode,
            UserTokenType.class
        );
    }

    private static void initializeStructured() {
        DelegateRegistry.register(
            ActivateSessionRequest::encode,
            ActivateSessionRequest::decode,
            ActivateSessionRequest.class,
            ActivateSessionRequest.BinaryEncodingId,
            ActivateSessionRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            ActivateSessionResponse::encode,
            ActivateSessionResponse::decode,
            ActivateSessionResponse.class,
            ActivateSessionResponse.BinaryEncodingId,
            ActivateSessionResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            AddNodesItem::encode,
            AddNodesItem::decode,
            AddNodesItem.class,
            AddNodesItem.BinaryEncodingId,
            AddNodesItem.XmlEncodingId
        );
        DelegateRegistry.register(
            AddNodesRequest::encode,
            AddNodesRequest::decode,
            AddNodesRequest.class,
            AddNodesRequest.BinaryEncodingId,
            AddNodesRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            AddNodesResponse::encode,
            AddNodesResponse::decode,
            AddNodesResponse.class,
            AddNodesResponse.BinaryEncodingId,
            AddNodesResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            AddNodesResult::encode,
            AddNodesResult::decode,
            AddNodesResult.class,
            AddNodesResult.BinaryEncodingId,
            AddNodesResult.XmlEncodingId
        );
        DelegateRegistry.register(
            AddReferencesItem::encode,
            AddReferencesItem::decode,
            AddReferencesItem.class,
            AddReferencesItem.BinaryEncodingId,
            AddReferencesItem.XmlEncodingId
        );
        DelegateRegistry.register(
            AddReferencesRequest::encode,
            AddReferencesRequest::decode,
            AddReferencesRequest.class,
            AddReferencesRequest.BinaryEncodingId,
            AddReferencesRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            AddReferencesResponse::encode,
            AddReferencesResponse::decode,
            AddReferencesResponse.class,
            AddReferencesResponse.BinaryEncodingId,
            AddReferencesResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            AggregateConfiguration::encode,
            AggregateConfiguration::decode,
            AggregateConfiguration.class,
            AggregateConfiguration.BinaryEncodingId,
            AggregateConfiguration.XmlEncodingId
        );
        DelegateRegistry.register(
            AggregateFilter::encode,
            AggregateFilter::decode,
            AggregateFilter.class,
            AggregateFilter.BinaryEncodingId,
            AggregateFilter.XmlEncodingId
        );
        DelegateRegistry.register(
            AggregateFilterResult::encode,
            AggregateFilterResult::decode,
            AggregateFilterResult.class,
            AggregateFilterResult.BinaryEncodingId,
            AggregateFilterResult.XmlEncodingId
        );
        DelegateRegistry.register(
            Annotation::encode,
            Annotation::decode,
            Annotation.class,
            Annotation.BinaryEncodingId,
            Annotation.XmlEncodingId
        );
        DelegateRegistry.register(
            AnonymousIdentityToken::encode,
            AnonymousIdentityToken::decode,
            AnonymousIdentityToken.class,
            AnonymousIdentityToken.BinaryEncodingId,
            AnonymousIdentityToken.XmlEncodingId
        );
        DelegateRegistry.register(
            ApplicationDescription::encode,
            ApplicationDescription::decode,
            ApplicationDescription.class,
            ApplicationDescription.BinaryEncodingId,
            ApplicationDescription.XmlEncodingId
        );
        DelegateRegistry.register(
            Argument::encode,
            Argument::decode,
            Argument.class,
            Argument.BinaryEncodingId,
            Argument.XmlEncodingId
        );
        DelegateRegistry.register(
            ArrayTestType::encode,
            ArrayTestType::decode,
            ArrayTestType.class,
            ArrayTestType.BinaryEncodingId,
            ArrayTestType.XmlEncodingId
        );
        DelegateRegistry.register(
            AttributeOperand::encode,
            AttributeOperand::decode,
            AttributeOperand.class,
            AttributeOperand.BinaryEncodingId,
            AttributeOperand.XmlEncodingId
        );
        DelegateRegistry.register(
            AxisInformation::encode,
            AxisInformation::decode,
            AxisInformation.class,
            AxisInformation.BinaryEncodingId,
            AxisInformation.XmlEncodingId
        );
        DelegateRegistry.register(
            BrowseDescription::encode,
            BrowseDescription::decode,
            BrowseDescription.class,
            BrowseDescription.BinaryEncodingId,
            BrowseDescription.XmlEncodingId
        );
        DelegateRegistry.register(
            BrowseNextRequest::encode,
            BrowseNextRequest::decode,
            BrowseNextRequest.class,
            BrowseNextRequest.BinaryEncodingId,
            BrowseNextRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            BrowseNextResponse::encode,
            BrowseNextResponse::decode,
            BrowseNextResponse.class,
            BrowseNextResponse.BinaryEncodingId,
            BrowseNextResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            BrowsePath::encode,
            BrowsePath::decode,
            BrowsePath.class,
            BrowsePath.BinaryEncodingId,
            BrowsePath.XmlEncodingId
        );
        DelegateRegistry.register(
            BrowsePathResult::encode,
            BrowsePathResult::decode,
            BrowsePathResult.class,
            BrowsePathResult.BinaryEncodingId,
            BrowsePathResult.XmlEncodingId
        );
        DelegateRegistry.register(
            BrowsePathTarget::encode,
            BrowsePathTarget::decode,
            BrowsePathTarget.class,
            BrowsePathTarget.BinaryEncodingId,
            BrowsePathTarget.XmlEncodingId
        );
        DelegateRegistry.register(
            BrowseRequest::encode,
            BrowseRequest::decode,
            BrowseRequest.class,
            BrowseRequest.BinaryEncodingId,
            BrowseRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            BrowseResponse::encode,
            BrowseResponse::decode,
            BrowseResponse.class,
            BrowseResponse.BinaryEncodingId,
            BrowseResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            BrowseResult::encode,
            BrowseResult::decode,
            BrowseResult.class,
            BrowseResult.BinaryEncodingId,
            BrowseResult.XmlEncodingId
        );
        DelegateRegistry.register(
            BuildInfo::encode,
            BuildInfo::decode,
            BuildInfo.class,
            BuildInfo.BinaryEncodingId,
            BuildInfo.XmlEncodingId
        );
        DelegateRegistry.register(
            CallMethodRequest::encode,
            CallMethodRequest::decode,
            CallMethodRequest.class,
            CallMethodRequest.BinaryEncodingId,
            CallMethodRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            CallMethodResult::encode,
            CallMethodResult::decode,
            CallMethodResult.class,
            CallMethodResult.BinaryEncodingId,
            CallMethodResult.XmlEncodingId
        );
        DelegateRegistry.register(
            CallRequest::encode,
            CallRequest::decode,
            CallRequest.class,
            CallRequest.BinaryEncodingId,
            CallRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            CallResponse::encode,
            CallResponse::decode,
            CallResponse.class,
            CallResponse.BinaryEncodingId,
            CallResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            CancelRequest::encode,
            CancelRequest::decode,
            CancelRequest.class,
            CancelRequest.BinaryEncodingId,
            CancelRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            CancelResponse::encode,
            CancelResponse::decode,
            CancelResponse.class,
            CancelResponse.BinaryEncodingId,
            CancelResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            ChannelSecurityToken::encode,
            ChannelSecurityToken::decode,
            ChannelSecurityToken.class,
            ChannelSecurityToken.BinaryEncodingId,
            ChannelSecurityToken.XmlEncodingId
        );
        DelegateRegistry.register(
            CloseSecureChannelRequest::encode,
            CloseSecureChannelRequest::decode,
            CloseSecureChannelRequest.class,
            CloseSecureChannelRequest.BinaryEncodingId,
            CloseSecureChannelRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            CloseSecureChannelResponse::encode,
            CloseSecureChannelResponse::decode,
            CloseSecureChannelResponse.class,
            CloseSecureChannelResponse.BinaryEncodingId,
            CloseSecureChannelResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            CloseSessionRequest::encode,
            CloseSessionRequest::decode,
            CloseSessionRequest.class,
            CloseSessionRequest.BinaryEncodingId,
            CloseSessionRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            CloseSessionResponse::encode,
            CloseSessionResponse::decode,
            CloseSessionResponse.class,
            CloseSessionResponse.BinaryEncodingId,
            CloseSessionResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            ComplexNumberType::encode,
            ComplexNumberType::decode,
            ComplexNumberType.class,
            ComplexNumberType.BinaryEncodingId,
            ComplexNumberType.XmlEncodingId
        );
        DelegateRegistry.register(
            CompositeTestType::encode,
            CompositeTestType::decode,
            CompositeTestType.class,
            CompositeTestType.BinaryEncodingId,
            CompositeTestType.XmlEncodingId
        );
        DelegateRegistry.register(
            ContentFilter::encode,
            ContentFilter::decode,
            ContentFilter.class,
            ContentFilter.BinaryEncodingId,
            ContentFilter.XmlEncodingId
        );
        DelegateRegistry.register(
            ContentFilterElement::encode,
            ContentFilterElement::decode,
            ContentFilterElement.class,
            ContentFilterElement.BinaryEncodingId,
            ContentFilterElement.XmlEncodingId
        );
        DelegateRegistry.register(
            ContentFilterElementResult::encode,
            ContentFilterElementResult::decode,
            ContentFilterElementResult.class,
            ContentFilterElementResult.BinaryEncodingId,
            ContentFilterElementResult.XmlEncodingId
        );
        DelegateRegistry.register(
            ContentFilterResult::encode,
            ContentFilterResult::decode,
            ContentFilterResult.class,
            ContentFilterResult.BinaryEncodingId,
            ContentFilterResult.XmlEncodingId
        );
        DelegateRegistry.register(
            CreateMonitoredItemsRequest::encode,
            CreateMonitoredItemsRequest::decode,
            CreateMonitoredItemsRequest.class,
            CreateMonitoredItemsRequest.BinaryEncodingId,
            CreateMonitoredItemsRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            CreateMonitoredItemsResponse::encode,
            CreateMonitoredItemsResponse::decode,
            CreateMonitoredItemsResponse.class,
            CreateMonitoredItemsResponse.BinaryEncodingId,
            CreateMonitoredItemsResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            CreateSessionRequest::encode,
            CreateSessionRequest::decode,
            CreateSessionRequest.class,
            CreateSessionRequest.BinaryEncodingId,
            CreateSessionRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            CreateSessionResponse::encode,
            CreateSessionResponse::decode,
            CreateSessionResponse.class,
            CreateSessionResponse.BinaryEncodingId,
            CreateSessionResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            CreateSubscriptionRequest::encode,
            CreateSubscriptionRequest::decode,
            CreateSubscriptionRequest.class,
            CreateSubscriptionRequest.BinaryEncodingId,
            CreateSubscriptionRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            CreateSubscriptionResponse::encode,
            CreateSubscriptionResponse::decode,
            CreateSubscriptionResponse.class,
            CreateSubscriptionResponse.BinaryEncodingId,
            CreateSubscriptionResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            DataChangeFilter::encode,
            DataChangeFilter::decode,
            DataChangeFilter.class,
            DataChangeFilter.BinaryEncodingId,
            DataChangeFilter.XmlEncodingId
        );
        DelegateRegistry.register(
            DataChangeNotification::encode,
            DataChangeNotification::decode,
            DataChangeNotification.class,
            DataChangeNotification.BinaryEncodingId,
            DataChangeNotification.XmlEncodingId
        );
        DelegateRegistry.register(
            DataTypeAttributes::encode,
            DataTypeAttributes::decode,
            DataTypeAttributes.class,
            DataTypeAttributes.BinaryEncodingId,
            DataTypeAttributes.XmlEncodingId
        );
        DelegateRegistry.register(
            DataTypeNode::encode,
            DataTypeNode::decode,
            DataTypeNode.class,
            DataTypeNode.BinaryEncodingId,
            DataTypeNode.XmlEncodingId
        );
        DelegateRegistry.register(
            DeleteAtTimeDetails::encode,
            DeleteAtTimeDetails::decode,
            DeleteAtTimeDetails.class,
            DeleteAtTimeDetails.BinaryEncodingId,
            DeleteAtTimeDetails.XmlEncodingId
        );
        DelegateRegistry.register(
            DeleteEventDetails::encode,
            DeleteEventDetails::decode,
            DeleteEventDetails.class,
            DeleteEventDetails.BinaryEncodingId,
            DeleteEventDetails.XmlEncodingId
        );
        DelegateRegistry.register(
            DeleteMonitoredItemsRequest::encode,
            DeleteMonitoredItemsRequest::decode,
            DeleteMonitoredItemsRequest.class,
            DeleteMonitoredItemsRequest.BinaryEncodingId,
            DeleteMonitoredItemsRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            DeleteMonitoredItemsResponse::encode,
            DeleteMonitoredItemsResponse::decode,
            DeleteMonitoredItemsResponse.class,
            DeleteMonitoredItemsResponse.BinaryEncodingId,
            DeleteMonitoredItemsResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            DeleteNodesItem::encode,
            DeleteNodesItem::decode,
            DeleteNodesItem.class,
            DeleteNodesItem.BinaryEncodingId,
            DeleteNodesItem.XmlEncodingId
        );
        DelegateRegistry.register(
            DeleteNodesRequest::encode,
            DeleteNodesRequest::decode,
            DeleteNodesRequest.class,
            DeleteNodesRequest.BinaryEncodingId,
            DeleteNodesRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            DeleteNodesResponse::encode,
            DeleteNodesResponse::decode,
            DeleteNodesResponse.class,
            DeleteNodesResponse.BinaryEncodingId,
            DeleteNodesResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            DeleteRawModifiedDetails::encode,
            DeleteRawModifiedDetails::decode,
            DeleteRawModifiedDetails.class,
            DeleteRawModifiedDetails.BinaryEncodingId,
            DeleteRawModifiedDetails.XmlEncodingId
        );
        DelegateRegistry.register(
            DeleteReferencesItem::encode,
            DeleteReferencesItem::decode,
            DeleteReferencesItem.class,
            DeleteReferencesItem.BinaryEncodingId,
            DeleteReferencesItem.XmlEncodingId
        );
        DelegateRegistry.register(
            DeleteReferencesRequest::encode,
            DeleteReferencesRequest::decode,
            DeleteReferencesRequest.class,
            DeleteReferencesRequest.BinaryEncodingId,
            DeleteReferencesRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            DeleteReferencesResponse::encode,
            DeleteReferencesResponse::decode,
            DeleteReferencesResponse.class,
            DeleteReferencesResponse.BinaryEncodingId,
            DeleteReferencesResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            DeleteSubscriptionsRequest::encode,
            DeleteSubscriptionsRequest::decode,
            DeleteSubscriptionsRequest.class,
            DeleteSubscriptionsRequest.BinaryEncodingId,
            DeleteSubscriptionsRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            DeleteSubscriptionsResponse::encode,
            DeleteSubscriptionsResponse::decode,
            DeleteSubscriptionsResponse.class,
            DeleteSubscriptionsResponse.BinaryEncodingId,
            DeleteSubscriptionsResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            DiscoveryConfiguration::encode,
            DiscoveryConfiguration::decode,
            DiscoveryConfiguration.class,
            DiscoveryConfiguration.BinaryEncodingId,
            DiscoveryConfiguration.XmlEncodingId
        );
        DelegateRegistry.register(
            DoubleComplexNumberType::encode,
            DoubleComplexNumberType::decode,
            DoubleComplexNumberType.class,
            DoubleComplexNumberType.BinaryEncodingId,
            DoubleComplexNumberType.XmlEncodingId
        );
        DelegateRegistry.register(
            ElementOperand::encode,
            ElementOperand::decode,
            ElementOperand.class,
            ElementOperand.BinaryEncodingId,
            ElementOperand.XmlEncodingId
        );
        DelegateRegistry.register(
            EndpointConfiguration::encode,
            EndpointConfiguration::decode,
            EndpointConfiguration.class,
            EndpointConfiguration.BinaryEncodingId,
            EndpointConfiguration.XmlEncodingId
        );
        DelegateRegistry.register(
            EndpointDescription::encode,
            EndpointDescription::decode,
            EndpointDescription.class,
            EndpointDescription.BinaryEncodingId,
            EndpointDescription.XmlEncodingId
        );
        DelegateRegistry.register(
            EndpointUrlListDataType::encode,
            EndpointUrlListDataType::decode,
            EndpointUrlListDataType.class,
            EndpointUrlListDataType.BinaryEncodingId,
            EndpointUrlListDataType.XmlEncodingId
        );
        DelegateRegistry.register(
            EnumValueType::encode,
            EnumValueType::decode,
            EnumValueType.class,
            EnumValueType.BinaryEncodingId,
            EnumValueType.XmlEncodingId
        );
        DelegateRegistry.register(
            EUInformation::encode,
            EUInformation::decode,
            EUInformation.class,
            EUInformation.BinaryEncodingId,
            EUInformation.XmlEncodingId
        );
        DelegateRegistry.register(
            EventFieldList::encode,
            EventFieldList::decode,
            EventFieldList.class,
            EventFieldList.BinaryEncodingId,
            EventFieldList.XmlEncodingId
        );
        DelegateRegistry.register(
            EventFilter::encode,
            EventFilter::decode,
            EventFilter.class,
            EventFilter.BinaryEncodingId,
            EventFilter.XmlEncodingId
        );
        DelegateRegistry.register(
            EventFilterResult::encode,
            EventFilterResult::decode,
            EventFilterResult.class,
            EventFilterResult.BinaryEncodingId,
            EventFilterResult.XmlEncodingId
        );
        DelegateRegistry.register(
            EventNotificationList::encode,
            EventNotificationList::decode,
            EventNotificationList.class,
            EventNotificationList.BinaryEncodingId,
            EventNotificationList.XmlEncodingId
        );
        DelegateRegistry.register(
            FilterOperand::encode,
            FilterOperand::decode,
            FilterOperand.class,
            FilterOperand.BinaryEncodingId,
            FilterOperand.XmlEncodingId
        );
        DelegateRegistry.register(
            FindServersOnNetworkRequest::encode,
            FindServersOnNetworkRequest::decode,
            FindServersOnNetworkRequest.class,
            FindServersOnNetworkRequest.BinaryEncodingId,
            FindServersOnNetworkRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            FindServersOnNetworkResponse::encode,
            FindServersOnNetworkResponse::decode,
            FindServersOnNetworkResponse.class,
            FindServersOnNetworkResponse.BinaryEncodingId,
            FindServersOnNetworkResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            FindServersRequest::encode,
            FindServersRequest::decode,
            FindServersRequest.class,
            FindServersRequest.BinaryEncodingId,
            FindServersRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            FindServersResponse::encode,
            FindServersResponse::decode,
            FindServersResponse.class,
            FindServersResponse.BinaryEncodingId,
            FindServersResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            GetEndpointsRequest::encode,
            GetEndpointsRequest::decode,
            GetEndpointsRequest.class,
            GetEndpointsRequest.BinaryEncodingId,
            GetEndpointsRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            GetEndpointsResponse::encode,
            GetEndpointsResponse::decode,
            GetEndpointsResponse.class,
            GetEndpointsResponse.BinaryEncodingId,
            GetEndpointsResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            HistoryData::encode,
            HistoryData::decode,
            HistoryData.class,
            HistoryData.BinaryEncodingId,
            HistoryData.XmlEncodingId
        );
        DelegateRegistry.register(
            HistoryEvent::encode,
            HistoryEvent::decode,
            HistoryEvent.class,
            HistoryEvent.BinaryEncodingId,
            HistoryEvent.XmlEncodingId
        );
        DelegateRegistry.register(
            HistoryEventFieldList::encode,
            HistoryEventFieldList::decode,
            HistoryEventFieldList.class,
            HistoryEventFieldList.BinaryEncodingId,
            HistoryEventFieldList.XmlEncodingId
        );
        DelegateRegistry.register(
            HistoryModifiedData::encode,
            HistoryModifiedData::decode,
            HistoryModifiedData.class,
            HistoryModifiedData.BinaryEncodingId,
            HistoryModifiedData.XmlEncodingId
        );
        DelegateRegistry.register(
            HistoryReadDetails::encode,
            HistoryReadDetails::decode,
            HistoryReadDetails.class,
            HistoryReadDetails.BinaryEncodingId,
            HistoryReadDetails.XmlEncodingId
        );
        DelegateRegistry.register(
            HistoryReadRequest::encode,
            HistoryReadRequest::decode,
            HistoryReadRequest.class,
            HistoryReadRequest.BinaryEncodingId,
            HistoryReadRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            HistoryReadResponse::encode,
            HistoryReadResponse::decode,
            HistoryReadResponse.class,
            HistoryReadResponse.BinaryEncodingId,
            HistoryReadResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            HistoryReadResult::encode,
            HistoryReadResult::decode,
            HistoryReadResult.class,
            HistoryReadResult.BinaryEncodingId,
            HistoryReadResult.XmlEncodingId
        );
        DelegateRegistry.register(
            HistoryReadValueId::encode,
            HistoryReadValueId::decode,
            HistoryReadValueId.class,
            HistoryReadValueId.BinaryEncodingId,
            HistoryReadValueId.XmlEncodingId
        );
        DelegateRegistry.register(
            HistoryUpdateDetails::encode,
            HistoryUpdateDetails::decode,
            HistoryUpdateDetails.class,
            HistoryUpdateDetails.BinaryEncodingId,
            HistoryUpdateDetails.XmlEncodingId
        );
        DelegateRegistry.register(
            HistoryUpdateRequest::encode,
            HistoryUpdateRequest::decode,
            HistoryUpdateRequest.class,
            HistoryUpdateRequest.BinaryEncodingId,
            HistoryUpdateRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            HistoryUpdateResponse::encode,
            HistoryUpdateResponse::decode,
            HistoryUpdateResponse.class,
            HistoryUpdateResponse.BinaryEncodingId,
            HistoryUpdateResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            HistoryUpdateResult::encode,
            HistoryUpdateResult::decode,
            HistoryUpdateResult.class,
            HistoryUpdateResult.BinaryEncodingId,
            HistoryUpdateResult.XmlEncodingId
        );
        DelegateRegistry.register(
            InstanceNode::encode,
            InstanceNode::decode,
            InstanceNode.class,
            InstanceNode.BinaryEncodingId,
            InstanceNode.XmlEncodingId
        );
        DelegateRegistry.register(
            IssuedIdentityToken::encode,
            IssuedIdentityToken::decode,
            IssuedIdentityToken.class,
            IssuedIdentityToken.BinaryEncodingId,
            IssuedIdentityToken.XmlEncodingId
        );
        DelegateRegistry.register(
            KerberosIdentityToken::encode,
            KerberosIdentityToken::decode,
            KerberosIdentityToken.class,
            KerberosIdentityToken.BinaryEncodingId,
            KerberosIdentityToken.XmlEncodingId
        );
        DelegateRegistry.register(
            LiteralOperand::encode,
            LiteralOperand::decode,
            LiteralOperand.class,
            LiteralOperand.BinaryEncodingId,
            LiteralOperand.XmlEncodingId
        );
        DelegateRegistry.register(
            MdnsDiscoveryConfiguration::encode,
            MdnsDiscoveryConfiguration::decode,
            MdnsDiscoveryConfiguration.class,
            MdnsDiscoveryConfiguration.BinaryEncodingId,
            MdnsDiscoveryConfiguration.XmlEncodingId
        );
        DelegateRegistry.register(
            MethodAttributes::encode,
            MethodAttributes::decode,
            MethodAttributes.class,
            MethodAttributes.BinaryEncodingId,
            MethodAttributes.XmlEncodingId
        );
        DelegateRegistry.register(
            MethodNode::encode,
            MethodNode::decode,
            MethodNode.class,
            MethodNode.BinaryEncodingId,
            MethodNode.XmlEncodingId
        );
        DelegateRegistry.register(
            ModelChangeStructureDataType::encode,
            ModelChangeStructureDataType::decode,
            ModelChangeStructureDataType.class,
            ModelChangeStructureDataType.BinaryEncodingId,
            ModelChangeStructureDataType.XmlEncodingId
        );
        DelegateRegistry.register(
            ModificationInfo::encode,
            ModificationInfo::decode,
            ModificationInfo.class,
            ModificationInfo.BinaryEncodingId,
            ModificationInfo.XmlEncodingId
        );
        DelegateRegistry.register(
            ModifyMonitoredItemsRequest::encode,
            ModifyMonitoredItemsRequest::decode,
            ModifyMonitoredItemsRequest.class,
            ModifyMonitoredItemsRequest.BinaryEncodingId,
            ModifyMonitoredItemsRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            ModifyMonitoredItemsResponse::encode,
            ModifyMonitoredItemsResponse::decode,
            ModifyMonitoredItemsResponse.class,
            ModifyMonitoredItemsResponse.BinaryEncodingId,
            ModifyMonitoredItemsResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            ModifySubscriptionRequest::encode,
            ModifySubscriptionRequest::decode,
            ModifySubscriptionRequest.class,
            ModifySubscriptionRequest.BinaryEncodingId,
            ModifySubscriptionRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            ModifySubscriptionResponse::encode,
            ModifySubscriptionResponse::decode,
            ModifySubscriptionResponse.class,
            ModifySubscriptionResponse.BinaryEncodingId,
            ModifySubscriptionResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            MonitoredItemCreateRequest::encode,
            MonitoredItemCreateRequest::decode,
            MonitoredItemCreateRequest.class,
            MonitoredItemCreateRequest.BinaryEncodingId,
            MonitoredItemCreateRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            MonitoredItemCreateResult::encode,
            MonitoredItemCreateResult::decode,
            MonitoredItemCreateResult.class,
            MonitoredItemCreateResult.BinaryEncodingId,
            MonitoredItemCreateResult.XmlEncodingId
        );
        DelegateRegistry.register(
            MonitoredItemModifyRequest::encode,
            MonitoredItemModifyRequest::decode,
            MonitoredItemModifyRequest.class,
            MonitoredItemModifyRequest.BinaryEncodingId,
            MonitoredItemModifyRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            MonitoredItemModifyResult::encode,
            MonitoredItemModifyResult::decode,
            MonitoredItemModifyResult.class,
            MonitoredItemModifyResult.BinaryEncodingId,
            MonitoredItemModifyResult.XmlEncodingId
        );
        DelegateRegistry.register(
            MonitoredItemNotification::encode,
            MonitoredItemNotification::decode,
            MonitoredItemNotification.class,
            MonitoredItemNotification.BinaryEncodingId,
            MonitoredItemNotification.XmlEncodingId
        );
        DelegateRegistry.register(
            MonitoringFilter::encode,
            MonitoringFilter::decode,
            MonitoringFilter.class,
            MonitoringFilter.BinaryEncodingId,
            MonitoringFilter.XmlEncodingId
        );
        DelegateRegistry.register(
            MonitoringFilterResult::encode,
            MonitoringFilterResult::decode,
            MonitoringFilterResult.class,
            MonitoringFilterResult.BinaryEncodingId,
            MonitoringFilterResult.XmlEncodingId
        );
        DelegateRegistry.register(
            MonitoringParameters::encode,
            MonitoringParameters::decode,
            MonitoringParameters.class,
            MonitoringParameters.BinaryEncodingId,
            MonitoringParameters.XmlEncodingId
        );
        DelegateRegistry.register(
            NetworkGroupDataType::encode,
            NetworkGroupDataType::decode,
            NetworkGroupDataType.class,
            NetworkGroupDataType.BinaryEncodingId,
            NetworkGroupDataType.XmlEncodingId
        );
        DelegateRegistry.register(
            Node::encode,
            Node::decode,
            Node.class,
            Node.BinaryEncodingId,
            Node.XmlEncodingId
        );
        DelegateRegistry.register(
            NodeAttributes::encode,
            NodeAttributes::decode,
            NodeAttributes.class,
            NodeAttributes.BinaryEncodingId,
            NodeAttributes.XmlEncodingId
        );
        DelegateRegistry.register(
            NodeReference::encode,
            NodeReference::decode,
            NodeReference.class,
            NodeReference.BinaryEncodingId,
            NodeReference.XmlEncodingId
        );
        DelegateRegistry.register(
            NodeTypeDescription::encode,
            NodeTypeDescription::decode,
            NodeTypeDescription.class,
            NodeTypeDescription.BinaryEncodingId,
            NodeTypeDescription.XmlEncodingId
        );
        DelegateRegistry.register(
            NotificationData::encode,
            NotificationData::decode,
            NotificationData.class,
            NotificationData.BinaryEncodingId,
            NotificationData.XmlEncodingId
        );
        DelegateRegistry.register(
            NotificationMessage::encode,
            NotificationMessage::decode,
            NotificationMessage.class,
            NotificationMessage.BinaryEncodingId,
            NotificationMessage.XmlEncodingId
        );
        DelegateRegistry.register(
            ObjectAttributes::encode,
            ObjectAttributes::decode,
            ObjectAttributes.class,
            ObjectAttributes.BinaryEncodingId,
            ObjectAttributes.XmlEncodingId
        );
        DelegateRegistry.register(
            ObjectNode::encode,
            ObjectNode::decode,
            ObjectNode.class,
            ObjectNode.BinaryEncodingId,
            ObjectNode.XmlEncodingId
        );
        DelegateRegistry.register(
            ObjectTypeAttributes::encode,
            ObjectTypeAttributes::decode,
            ObjectTypeAttributes.class,
            ObjectTypeAttributes.BinaryEncodingId,
            ObjectTypeAttributes.XmlEncodingId
        );
        DelegateRegistry.register(
            ObjectTypeNode::encode,
            ObjectTypeNode::decode,
            ObjectTypeNode.class,
            ObjectTypeNode.BinaryEncodingId,
            ObjectTypeNode.XmlEncodingId
        );
        DelegateRegistry.register(
            OpenSecureChannelRequest::encode,
            OpenSecureChannelRequest::decode,
            OpenSecureChannelRequest.class,
            OpenSecureChannelRequest.BinaryEncodingId,
            OpenSecureChannelRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            OpenSecureChannelResponse::encode,
            OpenSecureChannelResponse::decode,
            OpenSecureChannelResponse.class,
            OpenSecureChannelResponse.BinaryEncodingId,
            OpenSecureChannelResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            OptionSet::encode,
            OptionSet::decode,
            OptionSet.class,
            OptionSet.BinaryEncodingId,
            OptionSet.XmlEncodingId
        );
        DelegateRegistry.register(
            ParsingResult::encode,
            ParsingResult::decode,
            ParsingResult.class,
            ParsingResult.BinaryEncodingId,
            ParsingResult.XmlEncodingId
        );
        DelegateRegistry.register(
            ProgramDiagnosticDataType::encode,
            ProgramDiagnosticDataType::decode,
            ProgramDiagnosticDataType.class,
            ProgramDiagnosticDataType.BinaryEncodingId,
            ProgramDiagnosticDataType.XmlEncodingId
        );
        DelegateRegistry.register(
            PublishRequest::encode,
            PublishRequest::decode,
            PublishRequest.class,
            PublishRequest.BinaryEncodingId,
            PublishRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            PublishResponse::encode,
            PublishResponse::decode,
            PublishResponse.class,
            PublishResponse.BinaryEncodingId,
            PublishResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            QueryDataDescription::encode,
            QueryDataDescription::decode,
            QueryDataDescription.class,
            QueryDataDescription.BinaryEncodingId,
            QueryDataDescription.XmlEncodingId
        );
        DelegateRegistry.register(
            QueryDataSet::encode,
            QueryDataSet::decode,
            QueryDataSet.class,
            QueryDataSet.BinaryEncodingId,
            QueryDataSet.XmlEncodingId
        );
        DelegateRegistry.register(
            QueryFirstRequest::encode,
            QueryFirstRequest::decode,
            QueryFirstRequest.class,
            QueryFirstRequest.BinaryEncodingId,
            QueryFirstRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            QueryFirstResponse::encode,
            QueryFirstResponse::decode,
            QueryFirstResponse.class,
            QueryFirstResponse.BinaryEncodingId,
            QueryFirstResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            QueryNextRequest::encode,
            QueryNextRequest::decode,
            QueryNextRequest.class,
            QueryNextRequest.BinaryEncodingId,
            QueryNextRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            QueryNextResponse::encode,
            QueryNextResponse::decode,
            QueryNextResponse.class,
            QueryNextResponse.BinaryEncodingId,
            QueryNextResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            Range::encode,
            Range::decode,
            Range.class,
            Range.BinaryEncodingId,
            Range.XmlEncodingId
        );
        DelegateRegistry.register(
            ReadAtTimeDetails::encode,
            ReadAtTimeDetails::decode,
            ReadAtTimeDetails.class,
            ReadAtTimeDetails.BinaryEncodingId,
            ReadAtTimeDetails.XmlEncodingId
        );
        DelegateRegistry.register(
            ReadEventDetails::encode,
            ReadEventDetails::decode,
            ReadEventDetails.class,
            ReadEventDetails.BinaryEncodingId,
            ReadEventDetails.XmlEncodingId
        );
        DelegateRegistry.register(
            ReadProcessedDetails::encode,
            ReadProcessedDetails::decode,
            ReadProcessedDetails.class,
            ReadProcessedDetails.BinaryEncodingId,
            ReadProcessedDetails.XmlEncodingId
        );
        DelegateRegistry.register(
            ReadRawModifiedDetails::encode,
            ReadRawModifiedDetails::decode,
            ReadRawModifiedDetails.class,
            ReadRawModifiedDetails.BinaryEncodingId,
            ReadRawModifiedDetails.XmlEncodingId
        );
        DelegateRegistry.register(
            ReadRequest::encode,
            ReadRequest::decode,
            ReadRequest.class,
            ReadRequest.BinaryEncodingId,
            ReadRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            ReadResponse::encode,
            ReadResponse::decode,
            ReadResponse.class,
            ReadResponse.BinaryEncodingId,
            ReadResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            ReadValueId::encode,
            ReadValueId::decode,
            ReadValueId.class,
            ReadValueId.BinaryEncodingId,
            ReadValueId.XmlEncodingId
        );
        DelegateRegistry.register(
            RedundantServerDataType::encode,
            RedundantServerDataType::decode,
            RedundantServerDataType.class,
            RedundantServerDataType.BinaryEncodingId,
            RedundantServerDataType.XmlEncodingId
        );
        DelegateRegistry.register(
            ReferenceDescription::encode,
            ReferenceDescription::decode,
            ReferenceDescription.class,
            ReferenceDescription.BinaryEncodingId,
            ReferenceDescription.XmlEncodingId
        );
        DelegateRegistry.register(
            ReferenceNode::encode,
            ReferenceNode::decode,
            ReferenceNode.class,
            ReferenceNode.BinaryEncodingId,
            ReferenceNode.XmlEncodingId
        );
        DelegateRegistry.register(
            ReferenceTypeAttributes::encode,
            ReferenceTypeAttributes::decode,
            ReferenceTypeAttributes.class,
            ReferenceTypeAttributes.BinaryEncodingId,
            ReferenceTypeAttributes.XmlEncodingId
        );
        DelegateRegistry.register(
            ReferenceTypeNode::encode,
            ReferenceTypeNode::decode,
            ReferenceTypeNode.class,
            ReferenceTypeNode.BinaryEncodingId,
            ReferenceTypeNode.XmlEncodingId
        );
        DelegateRegistry.register(
            RegisteredServer::encode,
            RegisteredServer::decode,
            RegisteredServer.class,
            RegisteredServer.BinaryEncodingId,
            RegisteredServer.XmlEncodingId
        );
        DelegateRegistry.register(
            RegisterNodesRequest::encode,
            RegisterNodesRequest::decode,
            RegisterNodesRequest.class,
            RegisterNodesRequest.BinaryEncodingId,
            RegisterNodesRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            RegisterNodesResponse::encode,
            RegisterNodesResponse::decode,
            RegisterNodesResponse.class,
            RegisterNodesResponse.BinaryEncodingId,
            RegisterNodesResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            RegisterServer2Request::encode,
            RegisterServer2Request::decode,
            RegisterServer2Request.class,
            RegisterServer2Request.BinaryEncodingId,
            RegisterServer2Request.XmlEncodingId
        );
        DelegateRegistry.register(
            RegisterServer2Response::encode,
            RegisterServer2Response::decode,
            RegisterServer2Response.class,
            RegisterServer2Response.BinaryEncodingId,
            RegisterServer2Response.XmlEncodingId
        );
        DelegateRegistry.register(
            RegisterServerRequest::encode,
            RegisterServerRequest::decode,
            RegisterServerRequest.class,
            RegisterServerRequest.BinaryEncodingId,
            RegisterServerRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            RegisterServerResponse::encode,
            RegisterServerResponse::decode,
            RegisterServerResponse.class,
            RegisterServerResponse.BinaryEncodingId,
            RegisterServerResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            RelativePath::encode,
            RelativePath::decode,
            RelativePath.class,
            RelativePath.BinaryEncodingId,
            RelativePath.XmlEncodingId
        );
        DelegateRegistry.register(
            RelativePathElement::encode,
            RelativePathElement::decode,
            RelativePathElement.class,
            RelativePathElement.BinaryEncodingId,
            RelativePathElement.XmlEncodingId
        );
        DelegateRegistry.register(
            RepublishRequest::encode,
            RepublishRequest::decode,
            RepublishRequest.class,
            RepublishRequest.BinaryEncodingId,
            RepublishRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            RepublishResponse::encode,
            RepublishResponse::decode,
            RepublishResponse.class,
            RepublishResponse.BinaryEncodingId,
            RepublishResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            RequestHeader::encode,
            RequestHeader::decode,
            RequestHeader.class,
            RequestHeader.BinaryEncodingId,
            RequestHeader.XmlEncodingId
        );
        DelegateRegistry.register(
            ResponseHeader::encode,
            ResponseHeader::decode,
            ResponseHeader.class,
            ResponseHeader.BinaryEncodingId,
            ResponseHeader.XmlEncodingId
        );
        DelegateRegistry.register(
            SamplingIntervalDiagnosticsDataType::encode,
            SamplingIntervalDiagnosticsDataType::decode,
            SamplingIntervalDiagnosticsDataType.class,
            SamplingIntervalDiagnosticsDataType.BinaryEncodingId,
            SamplingIntervalDiagnosticsDataType.XmlEncodingId
        );
        DelegateRegistry.register(
            ScalarTestType::encode,
            ScalarTestType::decode,
            ScalarTestType.class,
            ScalarTestType.BinaryEncodingId,
            ScalarTestType.XmlEncodingId
        );
        DelegateRegistry.register(
            SemanticChangeStructureDataType::encode,
            SemanticChangeStructureDataType::decode,
            SemanticChangeStructureDataType.class,
            SemanticChangeStructureDataType.BinaryEncodingId,
            SemanticChangeStructureDataType.XmlEncodingId
        );
        DelegateRegistry.register(
            ServerDiagnosticsSummaryDataType::encode,
            ServerDiagnosticsSummaryDataType::decode,
            ServerDiagnosticsSummaryDataType.class,
            ServerDiagnosticsSummaryDataType.BinaryEncodingId,
            ServerDiagnosticsSummaryDataType.XmlEncodingId
        );
        DelegateRegistry.register(
            ServerOnNetwork::encode,
            ServerOnNetwork::decode,
            ServerOnNetwork.class,
            ServerOnNetwork.BinaryEncodingId,
            ServerOnNetwork.XmlEncodingId
        );
        DelegateRegistry.register(
            ServerStatusDataType::encode,
            ServerStatusDataType::decode,
            ServerStatusDataType.class,
            ServerStatusDataType.BinaryEncodingId,
            ServerStatusDataType.XmlEncodingId
        );
        DelegateRegistry.register(
            ServiceCounterDataType::encode,
            ServiceCounterDataType::decode,
            ServiceCounterDataType.class,
            ServiceCounterDataType.BinaryEncodingId,
            ServiceCounterDataType.XmlEncodingId
        );
        DelegateRegistry.register(
            ServiceFault::encode,
            ServiceFault::decode,
            ServiceFault.class,
            ServiceFault.BinaryEncodingId,
            ServiceFault.XmlEncodingId
        );
        DelegateRegistry.register(
            SessionDiagnosticsDataType::encode,
            SessionDiagnosticsDataType::decode,
            SessionDiagnosticsDataType.class,
            SessionDiagnosticsDataType.BinaryEncodingId,
            SessionDiagnosticsDataType.XmlEncodingId
        );
        DelegateRegistry.register(
            SessionSecurityDiagnosticsDataType::encode,
            SessionSecurityDiagnosticsDataType::decode,
            SessionSecurityDiagnosticsDataType.class,
            SessionSecurityDiagnosticsDataType.BinaryEncodingId,
            SessionSecurityDiagnosticsDataType.XmlEncodingId
        );
        DelegateRegistry.register(
            SetMonitoringModeRequest::encode,
            SetMonitoringModeRequest::decode,
            SetMonitoringModeRequest.class,
            SetMonitoringModeRequest.BinaryEncodingId,
            SetMonitoringModeRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            SetMonitoringModeResponse::encode,
            SetMonitoringModeResponse::decode,
            SetMonitoringModeResponse.class,
            SetMonitoringModeResponse.BinaryEncodingId,
            SetMonitoringModeResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            SetPublishingModeRequest::encode,
            SetPublishingModeRequest::decode,
            SetPublishingModeRequest.class,
            SetPublishingModeRequest.BinaryEncodingId,
            SetPublishingModeRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            SetPublishingModeResponse::encode,
            SetPublishingModeResponse::decode,
            SetPublishingModeResponse.class,
            SetPublishingModeResponse.BinaryEncodingId,
            SetPublishingModeResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            SetTriggeringRequest::encode,
            SetTriggeringRequest::decode,
            SetTriggeringRequest.class,
            SetTriggeringRequest.BinaryEncodingId,
            SetTriggeringRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            SetTriggeringResponse::encode,
            SetTriggeringResponse::decode,
            SetTriggeringResponse.class,
            SetTriggeringResponse.BinaryEncodingId,
            SetTriggeringResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            SignatureData::encode,
            SignatureData::decode,
            SignatureData.class,
            SignatureData.BinaryEncodingId,
            SignatureData.XmlEncodingId
        );
        DelegateRegistry.register(
            SignedSoftwareCertificate::encode,
            SignedSoftwareCertificate::decode,
            SignedSoftwareCertificate.class,
            SignedSoftwareCertificate.BinaryEncodingId,
            SignedSoftwareCertificate.XmlEncodingId
        );
        DelegateRegistry.register(
            SimpleAttributeOperand::encode,
            SimpleAttributeOperand::decode,
            SimpleAttributeOperand.class,
            SimpleAttributeOperand.BinaryEncodingId,
            SimpleAttributeOperand.XmlEncodingId
        );
        DelegateRegistry.register(
            SoftwareCertificate::encode,
            SoftwareCertificate::decode,
            SoftwareCertificate.class,
            SoftwareCertificate.BinaryEncodingId,
            SoftwareCertificate.XmlEncodingId
        );
        DelegateRegistry.register(
            StatusChangeNotification::encode,
            StatusChangeNotification::decode,
            StatusChangeNotification.class,
            StatusChangeNotification.BinaryEncodingId,
            StatusChangeNotification.XmlEncodingId
        );
        DelegateRegistry.register(
            StatusResult::encode,
            StatusResult::decode,
            StatusResult.class,
            StatusResult.BinaryEncodingId,
            StatusResult.XmlEncodingId
        );
        DelegateRegistry.register(
            SubscriptionAcknowledgement::encode,
            SubscriptionAcknowledgement::decode,
            SubscriptionAcknowledgement.class,
            SubscriptionAcknowledgement.BinaryEncodingId,
            SubscriptionAcknowledgement.XmlEncodingId
        );
        DelegateRegistry.register(
            SubscriptionDiagnosticsDataType::encode,
            SubscriptionDiagnosticsDataType::decode,
            SubscriptionDiagnosticsDataType.class,
            SubscriptionDiagnosticsDataType.BinaryEncodingId,
            SubscriptionDiagnosticsDataType.XmlEncodingId
        );
        DelegateRegistry.register(
            SupportedProfile::encode,
            SupportedProfile::decode,
            SupportedProfile.class,
            SupportedProfile.BinaryEncodingId,
            SupportedProfile.XmlEncodingId
        );
        DelegateRegistry.register(
            TestStackExRequest::encode,
            TestStackExRequest::decode,
            TestStackExRequest.class,
            TestStackExRequest.BinaryEncodingId,
            TestStackExRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            TestStackExResponse::encode,
            TestStackExResponse::decode,
            TestStackExResponse.class,
            TestStackExResponse.BinaryEncodingId,
            TestStackExResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            TestStackRequest::encode,
            TestStackRequest::decode,
            TestStackRequest.class,
            TestStackRequest.BinaryEncodingId,
            TestStackRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            TestStackResponse::encode,
            TestStackResponse::decode,
            TestStackResponse.class,
            TestStackResponse.BinaryEncodingId,
            TestStackResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            TimeZoneDataType::encode,
            TimeZoneDataType::decode,
            TimeZoneDataType.class,
            TimeZoneDataType.BinaryEncodingId,
            TimeZoneDataType.XmlEncodingId
        );
        DelegateRegistry.register(
            TransferResult::encode,
            TransferResult::decode,
            TransferResult.class,
            TransferResult.BinaryEncodingId,
            TransferResult.XmlEncodingId
        );
        DelegateRegistry.register(
            TransferSubscriptionsRequest::encode,
            TransferSubscriptionsRequest::decode,
            TransferSubscriptionsRequest.class,
            TransferSubscriptionsRequest.BinaryEncodingId,
            TransferSubscriptionsRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            TransferSubscriptionsResponse::encode,
            TransferSubscriptionsResponse::decode,
            TransferSubscriptionsResponse.class,
            TransferSubscriptionsResponse.BinaryEncodingId,
            TransferSubscriptionsResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            TranslateBrowsePathsToNodeIdsRequest::encode,
            TranslateBrowsePathsToNodeIdsRequest::decode,
            TranslateBrowsePathsToNodeIdsRequest.class,
            TranslateBrowsePathsToNodeIdsRequest.BinaryEncodingId,
            TranslateBrowsePathsToNodeIdsRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            TranslateBrowsePathsToNodeIdsResponse::encode,
            TranslateBrowsePathsToNodeIdsResponse::decode,
            TranslateBrowsePathsToNodeIdsResponse.class,
            TranslateBrowsePathsToNodeIdsResponse.BinaryEncodingId,
            TranslateBrowsePathsToNodeIdsResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            TrustListDataType::encode,
            TrustListDataType::decode,
            TrustListDataType.class,
            TrustListDataType.BinaryEncodingId,
            TrustListDataType.XmlEncodingId
        );
        DelegateRegistry.register(
            TypeNode::encode,
            TypeNode::decode,
            TypeNode.class,
            TypeNode.BinaryEncodingId,
            TypeNode.XmlEncodingId
        );
        DelegateRegistry.register(
            Union::encode,
            Union::decode,
            Union.class,
            Union.BinaryEncodingId,
            Union.XmlEncodingId
        );
        DelegateRegistry.register(
            UnregisterNodesRequest::encode,
            UnregisterNodesRequest::decode,
            UnregisterNodesRequest.class,
            UnregisterNodesRequest.BinaryEncodingId,
            UnregisterNodesRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            UnregisterNodesResponse::encode,
            UnregisterNodesResponse::decode,
            UnregisterNodesResponse.class,
            UnregisterNodesResponse.BinaryEncodingId,
            UnregisterNodesResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            UpdateDataDetails::encode,
            UpdateDataDetails::decode,
            UpdateDataDetails.class,
            UpdateDataDetails.BinaryEncodingId,
            UpdateDataDetails.XmlEncodingId
        );
        DelegateRegistry.register(
            UpdateEventDetails::encode,
            UpdateEventDetails::decode,
            UpdateEventDetails.class,
            UpdateEventDetails.BinaryEncodingId,
            UpdateEventDetails.XmlEncodingId
        );
        DelegateRegistry.register(
            UpdateStructureDataDetails::encode,
            UpdateStructureDataDetails::decode,
            UpdateStructureDataDetails.class,
            UpdateStructureDataDetails.BinaryEncodingId,
            UpdateStructureDataDetails.XmlEncodingId
        );
        DelegateRegistry.register(
            UserIdentityToken::encode,
            UserIdentityToken::decode,
            UserIdentityToken.class,
            UserIdentityToken.BinaryEncodingId,
            UserIdentityToken.XmlEncodingId
        );
        DelegateRegistry.register(
            UserNameIdentityToken::encode,
            UserNameIdentityToken::decode,
            UserNameIdentityToken.class,
            UserNameIdentityToken.BinaryEncodingId,
            UserNameIdentityToken.XmlEncodingId
        );
        DelegateRegistry.register(
            UserTokenPolicy::encode,
            UserTokenPolicy::decode,
            UserTokenPolicy.class,
            UserTokenPolicy.BinaryEncodingId,
            UserTokenPolicy.XmlEncodingId
        );
        DelegateRegistry.register(
            VariableAttributes::encode,
            VariableAttributes::decode,
            VariableAttributes.class,
            VariableAttributes.BinaryEncodingId,
            VariableAttributes.XmlEncodingId
        );
        DelegateRegistry.register(
            VariableNode::encode,
            VariableNode::decode,
            VariableNode.class,
            VariableNode.BinaryEncodingId,
            VariableNode.XmlEncodingId
        );
        DelegateRegistry.register(
            VariableTypeAttributes::encode,
            VariableTypeAttributes::decode,
            VariableTypeAttributes.class,
            VariableTypeAttributes.BinaryEncodingId,
            VariableTypeAttributes.XmlEncodingId
        );
        DelegateRegistry.register(
            VariableTypeNode::encode,
            VariableTypeNode::decode,
            VariableTypeNode.class,
            VariableTypeNode.BinaryEncodingId,
            VariableTypeNode.XmlEncodingId
        );
        DelegateRegistry.register(
            ViewAttributes::encode,
            ViewAttributes::decode,
            ViewAttributes.class,
            ViewAttributes.BinaryEncodingId,
            ViewAttributes.XmlEncodingId
        );
        DelegateRegistry.register(
            ViewDescription::encode,
            ViewDescription::decode,
            ViewDescription.class,
            ViewDescription.BinaryEncodingId,
            ViewDescription.XmlEncodingId
        );
        DelegateRegistry.register(
            ViewNode::encode,
            ViewNode::decode,
            ViewNode.class,
            ViewNode.BinaryEncodingId,
            ViewNode.XmlEncodingId
        );
        DelegateRegistry.register(
            WriteRequest::encode,
            WriteRequest::decode,
            WriteRequest.class,
            WriteRequest.BinaryEncodingId,
            WriteRequest.XmlEncodingId
        );
        DelegateRegistry.register(
            WriteResponse::encode,
            WriteResponse::decode,
            WriteResponse.class,
            WriteResponse.BinaryEncodingId,
            WriteResponse.XmlEncodingId
        );
        DelegateRegistry.register(
            WriteValue::encode,
            WriteValue::decode,
            WriteValue.class,
            WriteValue.BinaryEncodingId,
            WriteValue.XmlEncodingId
        );
        DelegateRegistry.register(
            X509IdentityToken::encode,
            X509IdentityToken::decode,
            X509IdentityToken.class,
            X509IdentityToken.BinaryEncodingId,
            X509IdentityToken.XmlEncodingId
        );
        DelegateRegistry.register(
            XVType::encode,
            XVType::decode,
            XVType.class,
            XVType.BinaryEncodingId,
            XVType.XmlEncodingId
        );
    }

}

