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

package org.eclipse.milo.opcua.stack.core.types;

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
 * Registers the encoders and decoders for all the built-in enumerations and structures with
 * {@link BuiltinDataTypeDictionary}.
 * <p>
 * This class is semi-auto-generated; if the UA spec version changes and adds or removes structures these methods will
 * need to be updated.
 */
class BuiltinDataTypeDictionaryInitializer {

    static void initialize() {
        initializeStructured();
    }

    private static void initializeStructured() {
        BuiltinDataTypeDictionary.register(
            "ActivateSessionRequest",
            ActivateSessionRequest.class,
            new ActivateSessionRequest.Codec(),
            ActivateSessionRequest.BinaryEncodingId,
            ActivateSessionRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ActivateSessionResponse",
            ActivateSessionResponse.class,
            new ActivateSessionResponse.Codec(),
            ActivateSessionResponse.BinaryEncodingId,
            ActivateSessionResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "AddNodesItem",
            AddNodesItem.class,
            new AddNodesItem.Codec(),
            AddNodesItem.BinaryEncodingId,
            AddNodesItem.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "AddNodesRequest",
            AddNodesRequest.class,
            new AddNodesRequest.Codec(),
            AddNodesRequest.BinaryEncodingId,
            AddNodesRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "AddNodesResponse",
            AddNodesResponse.class,
            new AddNodesResponse.Codec(),
            AddNodesResponse.BinaryEncodingId,
            AddNodesResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "AddNodesResult",
            AddNodesResult.class,
            new AddNodesResult.Codec(),
            AddNodesResult.BinaryEncodingId,
            AddNodesResult.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "AddReferencesItem",
            AddReferencesItem.class,
            new AddReferencesItem.Codec(),
            AddReferencesItem.BinaryEncodingId,
            AddReferencesItem.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "AddReferencesRequest",
            AddReferencesRequest.class,
            new AddReferencesRequest.Codec(),
            AddReferencesRequest.BinaryEncodingId,
            AddReferencesRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "AddReferencesResponse",
            AddReferencesResponse.class,
            new AddReferencesResponse.Codec(),
            AddReferencesResponse.BinaryEncodingId,
            AddReferencesResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "AggregateConfiguration",
            AggregateConfiguration.class,
            new AggregateConfiguration.Codec(),
            AggregateConfiguration.BinaryEncodingId,
            AggregateConfiguration.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "AggregateFilter",
            AggregateFilter.class,
            new AggregateFilter.Codec(),
            AggregateFilter.BinaryEncodingId,
            AggregateFilter.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "AggregateFilterResult",
            AggregateFilterResult.class,
            new AggregateFilterResult.Codec(),
            AggregateFilterResult.BinaryEncodingId,
            AggregateFilterResult.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "Annotation",
            Annotation.class,
            new Annotation.Codec(),
            Annotation.BinaryEncodingId,
            Annotation.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "AnonymousIdentityToken",
            AnonymousIdentityToken.class,
            new AnonymousIdentityToken.Codec(),
            AnonymousIdentityToken.BinaryEncodingId,
            AnonymousIdentityToken.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ApplicationDescription",
            ApplicationDescription.class,
            new ApplicationDescription.Codec(),
            ApplicationDescription.BinaryEncodingId,
            ApplicationDescription.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "Argument",
            Argument.class,
            new Argument.Codec(),
            Argument.BinaryEncodingId,
            Argument.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "AttributeOperand",
            AttributeOperand.class,
            new AttributeOperand.Codec(),
            AttributeOperand.BinaryEncodingId,
            AttributeOperand.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "AxisInformation",
            AxisInformation.class,
            new AxisInformation.Codec(),
            AxisInformation.BinaryEncodingId,
            AxisInformation.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "BrowseDescription",
            BrowseDescription.class,
            new BrowseDescription.Codec(),
            BrowseDescription.BinaryEncodingId,
            BrowseDescription.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "BrowseNextRequest",
            BrowseNextRequest.class,
            new BrowseNextRequest.Codec(),
            BrowseNextRequest.BinaryEncodingId,
            BrowseNextRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "BrowseNextResponse",
            BrowseNextResponse.class,
            new BrowseNextResponse.Codec(),
            BrowseNextResponse.BinaryEncodingId,
            BrowseNextResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "BrowsePath",
            BrowsePath.class,
            new BrowsePath.Codec(),
            BrowsePath.BinaryEncodingId,
            BrowsePath.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "BrowsePathResult",
            BrowsePathResult.class,
            new BrowsePathResult.Codec(),
            BrowsePathResult.BinaryEncodingId,
            BrowsePathResult.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "BrowsePathTarget",
            BrowsePathTarget.class,
            new BrowsePathTarget.Codec(),
            BrowsePathTarget.BinaryEncodingId,
            BrowsePathTarget.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "BrowseRequest",
            BrowseRequest.class,
            new BrowseRequest.Codec(),
            BrowseRequest.BinaryEncodingId,
            BrowseRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "BrowseResponse",
            BrowseResponse.class,
            new BrowseResponse.Codec(),
            BrowseResponse.BinaryEncodingId,
            BrowseResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "BrowseResult",
            BrowseResult.class,
            new BrowseResult.Codec(),
            BrowseResult.BinaryEncodingId,
            BrowseResult.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "BuildInfo",
            BuildInfo.class,
            new BuildInfo.Codec(),
            BuildInfo.BinaryEncodingId,
            BuildInfo.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "CallMethodRequest",
            CallMethodRequest.class,
            new CallMethodRequest.Codec(),
            CallMethodRequest.BinaryEncodingId,
            CallMethodRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "CallMethodResult",
            CallMethodResult.class,
            new CallMethodResult.Codec(),
            CallMethodResult.BinaryEncodingId,
            CallMethodResult.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "CallRequest",
            CallRequest.class,
            new CallRequest.Codec(),
            CallRequest.BinaryEncodingId,
            CallRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "CallResponse",
            CallResponse.class,
            new CallResponse.Codec(),
            CallResponse.BinaryEncodingId,
            CallResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "CancelRequest",
            CancelRequest.class,
            new CancelRequest.Codec(),
            CancelRequest.BinaryEncodingId,
            CancelRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "CancelResponse",
            CancelResponse.class,
            new CancelResponse.Codec(),
            CancelResponse.BinaryEncodingId,
            CancelResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ChannelSecurityToken",
            ChannelSecurityToken.class,
            new ChannelSecurityToken.Codec(),
            ChannelSecurityToken.BinaryEncodingId,
            ChannelSecurityToken.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "CloseSecureChannelRequest",
            CloseSecureChannelRequest.class,
            new CloseSecureChannelRequest.Codec(),
            CloseSecureChannelRequest.BinaryEncodingId,
            CloseSecureChannelRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "CloseSecureChannelResponse",
            CloseSecureChannelResponse.class,
            new CloseSecureChannelResponse.Codec(),
            CloseSecureChannelResponse.BinaryEncodingId,
            CloseSecureChannelResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "CloseSessionRequest",
            CloseSessionRequest.class,
            new CloseSessionRequest.Codec(),
            CloseSessionRequest.BinaryEncodingId,
            CloseSessionRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "CloseSessionResponse",
            CloseSessionResponse.class,
            new CloseSessionResponse.Codec(),
            CloseSessionResponse.BinaryEncodingId,
            CloseSessionResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ComplexNumberType",
            ComplexNumberType.class,
            new ComplexNumberType.Codec(),
            ComplexNumberType.BinaryEncodingId,
            ComplexNumberType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ContentFilter",
            ContentFilter.class,
            new ContentFilter.Codec(),
            ContentFilter.BinaryEncodingId,
            ContentFilter.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ContentFilterElement",
            ContentFilterElement.class,
            new ContentFilterElement.Codec(),
            ContentFilterElement.BinaryEncodingId,
            ContentFilterElement.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ContentFilterElementResult",
            ContentFilterElementResult.class,
            new ContentFilterElementResult.Codec(),
            ContentFilterElementResult.BinaryEncodingId,
            ContentFilterElementResult.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ContentFilterResult",
            ContentFilterResult.class,
            new ContentFilterResult.Codec(),
            ContentFilterResult.BinaryEncodingId,
            ContentFilterResult.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "CreateMonitoredItemsRequest",
            CreateMonitoredItemsRequest.class,
            new CreateMonitoredItemsRequest.Codec(),
            CreateMonitoredItemsRequest.BinaryEncodingId,
            CreateMonitoredItemsRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "CreateMonitoredItemsResponse",
            CreateMonitoredItemsResponse.class,
            new CreateMonitoredItemsResponse.Codec(),
            CreateMonitoredItemsResponse.BinaryEncodingId,
            CreateMonitoredItemsResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "CreateSessionRequest",
            CreateSessionRequest.class,
            new CreateSessionRequest.Codec(),
            CreateSessionRequest.BinaryEncodingId,
            CreateSessionRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "CreateSessionResponse",
            CreateSessionResponse.class,
            new CreateSessionResponse.Codec(),
            CreateSessionResponse.BinaryEncodingId,
            CreateSessionResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "CreateSubscriptionRequest",
            CreateSubscriptionRequest.class,
            new CreateSubscriptionRequest.Codec(),
            CreateSubscriptionRequest.BinaryEncodingId,
            CreateSubscriptionRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "CreateSubscriptionResponse",
            CreateSubscriptionResponse.class,
            new CreateSubscriptionResponse.Codec(),
            CreateSubscriptionResponse.BinaryEncodingId,
            CreateSubscriptionResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DataChangeFilter",
            DataChangeFilter.class,
            new DataChangeFilter.Codec(),
            DataChangeFilter.BinaryEncodingId,
            DataChangeFilter.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DataChangeNotification",
            DataChangeNotification.class,
            new DataChangeNotification.Codec(),
            DataChangeNotification.BinaryEncodingId,
            DataChangeNotification.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DataTypeAttributes",
            DataTypeAttributes.class,
            new DataTypeAttributes.Codec(),
            DataTypeAttributes.BinaryEncodingId,
            DataTypeAttributes.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DataTypeNode",
            DataTypeNode.class,
            new DataTypeNode.Codec(),
            DataTypeNode.BinaryEncodingId,
            DataTypeNode.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DeleteAtTimeDetails",
            DeleteAtTimeDetails.class,
            new DeleteAtTimeDetails.Codec(),
            DeleteAtTimeDetails.BinaryEncodingId,
            DeleteAtTimeDetails.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DeleteEventDetails",
            DeleteEventDetails.class,
            new DeleteEventDetails.Codec(),
            DeleteEventDetails.BinaryEncodingId,
            DeleteEventDetails.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DeleteMonitoredItemsRequest",
            DeleteMonitoredItemsRequest.class,
            new DeleteMonitoredItemsRequest.Codec(),
            DeleteMonitoredItemsRequest.BinaryEncodingId,
            DeleteMonitoredItemsRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DeleteMonitoredItemsResponse",
            DeleteMonitoredItemsResponse.class,
            new DeleteMonitoredItemsResponse.Codec(),
            DeleteMonitoredItemsResponse.BinaryEncodingId,
            DeleteMonitoredItemsResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DeleteNodesItem",
            DeleteNodesItem.class,
            new DeleteNodesItem.Codec(),
            DeleteNodesItem.BinaryEncodingId,
            DeleteNodesItem.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DeleteNodesRequest",
            DeleteNodesRequest.class,
            new DeleteNodesRequest.Codec(),
            DeleteNodesRequest.BinaryEncodingId,
            DeleteNodesRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DeleteNodesResponse",
            DeleteNodesResponse.class,
            new DeleteNodesResponse.Codec(),
            DeleteNodesResponse.BinaryEncodingId,
            DeleteNodesResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DeleteRawModifiedDetails",
            DeleteRawModifiedDetails.class,
            new DeleteRawModifiedDetails.Codec(),
            DeleteRawModifiedDetails.BinaryEncodingId,
            DeleteRawModifiedDetails.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DeleteReferencesItem",
            DeleteReferencesItem.class,
            new DeleteReferencesItem.Codec(),
            DeleteReferencesItem.BinaryEncodingId,
            DeleteReferencesItem.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DeleteReferencesRequest",
            DeleteReferencesRequest.class,
            new DeleteReferencesRequest.Codec(),
            DeleteReferencesRequest.BinaryEncodingId,
            DeleteReferencesRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DeleteReferencesResponse",
            DeleteReferencesResponse.class,
            new DeleteReferencesResponse.Codec(),
            DeleteReferencesResponse.BinaryEncodingId,
            DeleteReferencesResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DeleteSubscriptionsRequest",
            DeleteSubscriptionsRequest.class,
            new DeleteSubscriptionsRequest.Codec(),
            DeleteSubscriptionsRequest.BinaryEncodingId,
            DeleteSubscriptionsRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DeleteSubscriptionsResponse",
            DeleteSubscriptionsResponse.class,
            new DeleteSubscriptionsResponse.Codec(),
            DeleteSubscriptionsResponse.BinaryEncodingId,
            DeleteSubscriptionsResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DiscoveryConfiguration",
            DiscoveryConfiguration.class,
            new DiscoveryConfiguration.Codec(),
            DiscoveryConfiguration.BinaryEncodingId,
            DiscoveryConfiguration.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "DoubleComplexNumberType",
            DoubleComplexNumberType.class,
            new DoubleComplexNumberType.Codec(),
            DoubleComplexNumberType.BinaryEncodingId,
            DoubleComplexNumberType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "EUInformation",
            EUInformation.class,
            new EUInformation.Codec(),
            EUInformation.BinaryEncodingId,
            EUInformation.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ElementOperand",
            ElementOperand.class,
            new ElementOperand.Codec(),
            ElementOperand.BinaryEncodingId,
            ElementOperand.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "EndpointConfiguration",
            EndpointConfiguration.class,
            new EndpointConfiguration.Codec(),
            EndpointConfiguration.BinaryEncodingId,
            EndpointConfiguration.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "EndpointDescription",
            EndpointDescription.class,
            new EndpointDescription.Codec(),
            EndpointDescription.BinaryEncodingId,
            EndpointDescription.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "EndpointUrlListDataType",
            EndpointUrlListDataType.class,
            new EndpointUrlListDataType.Codec(),
            EndpointUrlListDataType.BinaryEncodingId,
            EndpointUrlListDataType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "EnumValueType",
            EnumValueType.class,
            new EnumValueType.Codec(),
            EnumValueType.BinaryEncodingId,
            EnumValueType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "EventFieldList",
            EventFieldList.class,
            new EventFieldList.Codec(),
            EventFieldList.BinaryEncodingId,
            EventFieldList.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "EventFilter",
            EventFilter.class,
            new EventFilter.Codec(),
            EventFilter.BinaryEncodingId,
            EventFilter.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "EventFilterResult",
            EventFilterResult.class,
            new EventFilterResult.Codec(),
            EventFilterResult.BinaryEncodingId,
            EventFilterResult.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "EventNotificationList",
            EventNotificationList.class,
            new EventNotificationList.Codec(),
            EventNotificationList.BinaryEncodingId,
            EventNotificationList.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "FilterOperand",
            FilterOperand.class,
            new FilterOperand.Codec(),
            FilterOperand.BinaryEncodingId,
            FilterOperand.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "FindServersOnNetworkRequest",
            FindServersOnNetworkRequest.class,
            new FindServersOnNetworkRequest.Codec(),
            FindServersOnNetworkRequest.BinaryEncodingId,
            FindServersOnNetworkRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "FindServersOnNetworkResponse",
            FindServersOnNetworkResponse.class,
            new FindServersOnNetworkResponse.Codec(),
            FindServersOnNetworkResponse.BinaryEncodingId,
            FindServersOnNetworkResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "FindServersRequest",
            FindServersRequest.class,
            new FindServersRequest.Codec(),
            FindServersRequest.BinaryEncodingId,
            FindServersRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "FindServersResponse",
            FindServersResponse.class,
            new FindServersResponse.Codec(),
            FindServersResponse.BinaryEncodingId,
            FindServersResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "GetEndpointsRequest",
            GetEndpointsRequest.class,
            new GetEndpointsRequest.Codec(),
            GetEndpointsRequest.BinaryEncodingId,
            GetEndpointsRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "GetEndpointsResponse",
            GetEndpointsResponse.class,
            new GetEndpointsResponse.Codec(),
            GetEndpointsResponse.BinaryEncodingId,
            GetEndpointsResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "HistoryData",
            HistoryData.class,
            new HistoryData.Codec(),
            HistoryData.BinaryEncodingId,
            HistoryData.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "HistoryEvent",
            HistoryEvent.class,
            new HistoryEvent.Codec(),
            HistoryEvent.BinaryEncodingId,
            HistoryEvent.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "HistoryEventFieldList",
            HistoryEventFieldList.class,
            new HistoryEventFieldList.Codec(),
            HistoryEventFieldList.BinaryEncodingId,
            HistoryEventFieldList.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "HistoryModifiedData",
            HistoryModifiedData.class,
            new HistoryModifiedData.Codec(),
            HistoryModifiedData.BinaryEncodingId,
            HistoryModifiedData.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "HistoryReadDetails",
            HistoryReadDetails.class,
            new HistoryReadDetails.Codec(),
            HistoryReadDetails.BinaryEncodingId,
            HistoryReadDetails.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "HistoryReadRequest",
            HistoryReadRequest.class,
            new HistoryReadRequest.Codec(),
            HistoryReadRequest.BinaryEncodingId,
            HistoryReadRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "HistoryReadResponse",
            HistoryReadResponse.class,
            new HistoryReadResponse.Codec(),
            HistoryReadResponse.BinaryEncodingId,
            HistoryReadResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "HistoryReadResult",
            HistoryReadResult.class,
            new HistoryReadResult.Codec(),
            HistoryReadResult.BinaryEncodingId,
            HistoryReadResult.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "HistoryReadValueId",
            HistoryReadValueId.class,
            new HistoryReadValueId.Codec(),
            HistoryReadValueId.BinaryEncodingId,
            HistoryReadValueId.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "HistoryUpdateDetails",
            HistoryUpdateDetails.class,
            new HistoryUpdateDetails.Codec(),
            HistoryUpdateDetails.BinaryEncodingId,
            HistoryUpdateDetails.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "HistoryUpdateRequest",
            HistoryUpdateRequest.class,
            new HistoryUpdateRequest.Codec(),
            HistoryUpdateRequest.BinaryEncodingId,
            HistoryUpdateRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "HistoryUpdateResponse",
            HistoryUpdateResponse.class,
            new HistoryUpdateResponse.Codec(),
            HistoryUpdateResponse.BinaryEncodingId,
            HistoryUpdateResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "HistoryUpdateResult",
            HistoryUpdateResult.class,
            new HistoryUpdateResult.Codec(),
            HistoryUpdateResult.BinaryEncodingId,
            HistoryUpdateResult.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "InstanceNode",
            InstanceNode.class,
            new InstanceNode.Codec(),
            InstanceNode.BinaryEncodingId,
            InstanceNode.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "IssuedIdentityToken",
            IssuedIdentityToken.class,
            new IssuedIdentityToken.Codec(),
            IssuedIdentityToken.BinaryEncodingId,
            IssuedIdentityToken.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "KerberosIdentityToken",
            KerberosIdentityToken.class,
            new KerberosIdentityToken.Codec(),
            KerberosIdentityToken.BinaryEncodingId,
            KerberosIdentityToken.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "LiteralOperand",
            LiteralOperand.class,
            new LiteralOperand.Codec(),
            LiteralOperand.BinaryEncodingId,
            LiteralOperand.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "MdnsDiscoveryConfiguration",
            MdnsDiscoveryConfiguration.class,
            new MdnsDiscoveryConfiguration.Codec(),
            MdnsDiscoveryConfiguration.BinaryEncodingId,
            MdnsDiscoveryConfiguration.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "MethodAttributes",
            MethodAttributes.class,
            new MethodAttributes.Codec(),
            MethodAttributes.BinaryEncodingId,
            MethodAttributes.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "MethodNode",
            MethodNode.class,
            new MethodNode.Codec(),
            MethodNode.BinaryEncodingId,
            MethodNode.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ModelChangeStructureDataType",
            ModelChangeStructureDataType.class,
            new ModelChangeStructureDataType.Codec(),
            ModelChangeStructureDataType.BinaryEncodingId,
            ModelChangeStructureDataType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ModificationInfo",
            ModificationInfo.class,
            new ModificationInfo.Codec(),
            ModificationInfo.BinaryEncodingId,
            ModificationInfo.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ModifyMonitoredItemsRequest",
            ModifyMonitoredItemsRequest.class,
            new ModifyMonitoredItemsRequest.Codec(),
            ModifyMonitoredItemsRequest.BinaryEncodingId,
            ModifyMonitoredItemsRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ModifyMonitoredItemsResponse",
            ModifyMonitoredItemsResponse.class,
            new ModifyMonitoredItemsResponse.Codec(),
            ModifyMonitoredItemsResponse.BinaryEncodingId,
            ModifyMonitoredItemsResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ModifySubscriptionRequest",
            ModifySubscriptionRequest.class,
            new ModifySubscriptionRequest.Codec(),
            ModifySubscriptionRequest.BinaryEncodingId,
            ModifySubscriptionRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ModifySubscriptionResponse",
            ModifySubscriptionResponse.class,
            new ModifySubscriptionResponse.Codec(),
            ModifySubscriptionResponse.BinaryEncodingId,
            ModifySubscriptionResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "MonitoredItemCreateRequest",
            MonitoredItemCreateRequest.class,
            new MonitoredItemCreateRequest.Codec(),
            MonitoredItemCreateRequest.BinaryEncodingId,
            MonitoredItemCreateRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "MonitoredItemCreateResult",
            MonitoredItemCreateResult.class,
            new MonitoredItemCreateResult.Codec(),
            MonitoredItemCreateResult.BinaryEncodingId,
            MonitoredItemCreateResult.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "MonitoredItemModifyRequest",
            MonitoredItemModifyRequest.class,
            new MonitoredItemModifyRequest.Codec(),
            MonitoredItemModifyRequest.BinaryEncodingId,
            MonitoredItemModifyRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "MonitoredItemModifyResult",
            MonitoredItemModifyResult.class,
            new MonitoredItemModifyResult.Codec(),
            MonitoredItemModifyResult.BinaryEncodingId,
            MonitoredItemModifyResult.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "MonitoredItemNotification",
            MonitoredItemNotification.class,
            new MonitoredItemNotification.Codec(),
            MonitoredItemNotification.BinaryEncodingId,
            MonitoredItemNotification.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "MonitoringFilter",
            MonitoringFilter.class,
            new MonitoringFilter.Codec(),
            MonitoringFilter.BinaryEncodingId,
            MonitoringFilter.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "MonitoringFilterResult",
            MonitoringFilterResult.class,
            new MonitoringFilterResult.Codec(),
            MonitoringFilterResult.BinaryEncodingId,
            MonitoringFilterResult.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "MonitoringParameters",
            MonitoringParameters.class,
            new MonitoringParameters.Codec(),
            MonitoringParameters.BinaryEncodingId,
            MonitoringParameters.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "NetworkGroupDataType",
            NetworkGroupDataType.class,
            new NetworkGroupDataType.Codec(),
            NetworkGroupDataType.BinaryEncodingId,
            NetworkGroupDataType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "Node",
            Node.class,
            new Node.Codec(),
            Node.BinaryEncodingId,
            Node.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "NodeAttributes",
            NodeAttributes.class,
            new NodeAttributes.Codec(),
            NodeAttributes.BinaryEncodingId,
            NodeAttributes.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "NodeReference",
            NodeReference.class,
            new NodeReference.Codec(),
            NodeReference.BinaryEncodingId,
            NodeReference.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "NodeTypeDescription",
            NodeTypeDescription.class,
            new NodeTypeDescription.Codec(),
            NodeTypeDescription.BinaryEncodingId,
            NodeTypeDescription.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "NotificationData",
            NotificationData.class,
            new NotificationData.Codec(),
            NotificationData.BinaryEncodingId,
            NotificationData.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "NotificationMessage",
            NotificationMessage.class,
            new NotificationMessage.Codec(),
            NotificationMessage.BinaryEncodingId,
            NotificationMessage.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ObjectAttributes",
            ObjectAttributes.class,
            new ObjectAttributes.Codec(),
            ObjectAttributes.BinaryEncodingId,
            ObjectAttributes.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ObjectNode",
            ObjectNode.class,
            new ObjectNode.Codec(),
            ObjectNode.BinaryEncodingId,
            ObjectNode.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ObjectTypeAttributes",
            ObjectTypeAttributes.class,
            new ObjectTypeAttributes.Codec(),
            ObjectTypeAttributes.BinaryEncodingId,
            ObjectTypeAttributes.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ObjectTypeNode",
            ObjectTypeNode.class,
            new ObjectTypeNode.Codec(),
            ObjectTypeNode.BinaryEncodingId,
            ObjectTypeNode.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "OpenSecureChannelRequest",
            OpenSecureChannelRequest.class,
            new OpenSecureChannelRequest.Codec(),
            OpenSecureChannelRequest.BinaryEncodingId,
            OpenSecureChannelRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "OpenSecureChannelResponse",
            OpenSecureChannelResponse.class,
            new OpenSecureChannelResponse.Codec(),
            OpenSecureChannelResponse.BinaryEncodingId,
            OpenSecureChannelResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "OptionSet",
            OptionSet.class,
            new OptionSet.Codec(),
            OptionSet.BinaryEncodingId,
            OptionSet.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ParsingResult",
            ParsingResult.class,
            new ParsingResult.Codec(),
            ParsingResult.BinaryEncodingId,
            ParsingResult.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ProgramDiagnosticDataType",
            ProgramDiagnosticDataType.class,
            new ProgramDiagnosticDataType.Codec(),
            ProgramDiagnosticDataType.BinaryEncodingId,
            ProgramDiagnosticDataType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "PublishRequest",
            PublishRequest.class,
            new PublishRequest.Codec(),
            PublishRequest.BinaryEncodingId,
            PublishRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "PublishResponse",
            PublishResponse.class,
            new PublishResponse.Codec(),
            PublishResponse.BinaryEncodingId,
            PublishResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "QueryDataDescription",
            QueryDataDescription.class,
            new QueryDataDescription.Codec(),
            QueryDataDescription.BinaryEncodingId,
            QueryDataDescription.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "QueryDataSet",
            QueryDataSet.class,
            new QueryDataSet.Codec(),
            QueryDataSet.BinaryEncodingId,
            QueryDataSet.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "QueryFirstRequest",
            QueryFirstRequest.class,
            new QueryFirstRequest.Codec(),
            QueryFirstRequest.BinaryEncodingId,
            QueryFirstRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "QueryFirstResponse",
            QueryFirstResponse.class,
            new QueryFirstResponse.Codec(),
            QueryFirstResponse.BinaryEncodingId,
            QueryFirstResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "QueryNextRequest",
            QueryNextRequest.class,
            new QueryNextRequest.Codec(),
            QueryNextRequest.BinaryEncodingId,
            QueryNextRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "QueryNextResponse",
            QueryNextResponse.class,
            new QueryNextResponse.Codec(),
            QueryNextResponse.BinaryEncodingId,
            QueryNextResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "Range",
            Range.class,
            new Range.Codec(),
            Range.BinaryEncodingId,
            Range.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ReadAtTimeDetails",
            ReadAtTimeDetails.class,
            new ReadAtTimeDetails.Codec(),
            ReadAtTimeDetails.BinaryEncodingId,
            ReadAtTimeDetails.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ReadEventDetails",
            ReadEventDetails.class,
            new ReadEventDetails.Codec(),
            ReadEventDetails.BinaryEncodingId,
            ReadEventDetails.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ReadProcessedDetails",
            ReadProcessedDetails.class,
            new ReadProcessedDetails.Codec(),
            ReadProcessedDetails.BinaryEncodingId,
            ReadProcessedDetails.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ReadRawModifiedDetails",
            ReadRawModifiedDetails.class,
            new ReadRawModifiedDetails.Codec(),
            ReadRawModifiedDetails.BinaryEncodingId,
            ReadRawModifiedDetails.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ReadRequest",
            ReadRequest.class,
            new ReadRequest.Codec(),
            ReadRequest.BinaryEncodingId,
            ReadRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ReadResponse",
            ReadResponse.class,
            new ReadResponse.Codec(),
            ReadResponse.BinaryEncodingId,
            ReadResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ReadValueId",
            ReadValueId.class,
            new ReadValueId.Codec(),
            ReadValueId.BinaryEncodingId,
            ReadValueId.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "RedundantServerDataType",
            RedundantServerDataType.class,
            new RedundantServerDataType.Codec(),
            RedundantServerDataType.BinaryEncodingId,
            RedundantServerDataType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ReferenceDescription",
            ReferenceDescription.class,
            new ReferenceDescription.Codec(),
            ReferenceDescription.BinaryEncodingId,
            ReferenceDescription.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ReferenceNode",
            ReferenceNode.class,
            new ReferenceNode.Codec(),
            ReferenceNode.BinaryEncodingId,
            ReferenceNode.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ReferenceTypeAttributes",
            ReferenceTypeAttributes.class,
            new ReferenceTypeAttributes.Codec(),
            ReferenceTypeAttributes.BinaryEncodingId,
            ReferenceTypeAttributes.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ReferenceTypeNode",
            ReferenceTypeNode.class,
            new ReferenceTypeNode.Codec(),
            ReferenceTypeNode.BinaryEncodingId,
            ReferenceTypeNode.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "RegisterNodesRequest",
            RegisterNodesRequest.class,
            new RegisterNodesRequest.Codec(),
            RegisterNodesRequest.BinaryEncodingId,
            RegisterNodesRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "RegisterNodesResponse",
            RegisterNodesResponse.class,
            new RegisterNodesResponse.Codec(),
            RegisterNodesResponse.BinaryEncodingId,
            RegisterNodesResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "RegisterServer2Request",
            RegisterServer2Request.class,
            new RegisterServer2Request.Codec(),
            RegisterServer2Request.BinaryEncodingId,
            RegisterServer2Request.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "RegisterServer2Response",
            RegisterServer2Response.class,
            new RegisterServer2Response.Codec(),
            RegisterServer2Response.BinaryEncodingId,
            RegisterServer2Response.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "RegisterServerRequest",
            RegisterServerRequest.class,
            new RegisterServerRequest.Codec(),
            RegisterServerRequest.BinaryEncodingId,
            RegisterServerRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "RegisterServerResponse",
            RegisterServerResponse.class,
            new RegisterServerResponse.Codec(),
            RegisterServerResponse.BinaryEncodingId,
            RegisterServerResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "RegisteredServer",
            RegisteredServer.class,
            new RegisteredServer.Codec(),
            RegisteredServer.BinaryEncodingId,
            RegisteredServer.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "RelativePath",
            RelativePath.class,
            new RelativePath.Codec(),
            RelativePath.BinaryEncodingId,
            RelativePath.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "RelativePathElement",
            RelativePathElement.class,
            new RelativePathElement.Codec(),
            RelativePathElement.BinaryEncodingId,
            RelativePathElement.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "RepublishRequest",
            RepublishRequest.class,
            new RepublishRequest.Codec(),
            RepublishRequest.BinaryEncodingId,
            RepublishRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "RepublishResponse",
            RepublishResponse.class,
            new RepublishResponse.Codec(),
            RepublishResponse.BinaryEncodingId,
            RepublishResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "RequestHeader",
            RequestHeader.class,
            new RequestHeader.Codec(),
            RequestHeader.BinaryEncodingId,
            RequestHeader.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ResponseHeader",
            ResponseHeader.class,
            new ResponseHeader.Codec(),
            ResponseHeader.BinaryEncodingId,
            ResponseHeader.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SamplingIntervalDiagnosticsDataType",
            SamplingIntervalDiagnosticsDataType.class,
            new SamplingIntervalDiagnosticsDataType.Codec(),
            SamplingIntervalDiagnosticsDataType.BinaryEncodingId,
            SamplingIntervalDiagnosticsDataType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SemanticChangeStructureDataType",
            SemanticChangeStructureDataType.class,
            new SemanticChangeStructureDataType.Codec(),
            SemanticChangeStructureDataType.BinaryEncodingId,
            SemanticChangeStructureDataType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ServerDiagnosticsSummaryDataType",
            ServerDiagnosticsSummaryDataType.class,
            new ServerDiagnosticsSummaryDataType.Codec(),
            ServerDiagnosticsSummaryDataType.BinaryEncodingId,
            ServerDiagnosticsSummaryDataType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ServerOnNetwork",
            ServerOnNetwork.class,
            new ServerOnNetwork.Codec(),
            ServerOnNetwork.BinaryEncodingId,
            ServerOnNetwork.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ServerStatusDataType",
            ServerStatusDataType.class,
            new ServerStatusDataType.Codec(),
            ServerStatusDataType.BinaryEncodingId,
            ServerStatusDataType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ServiceCounterDataType",
            ServiceCounterDataType.class,
            new ServiceCounterDataType.Codec(),
            ServiceCounterDataType.BinaryEncodingId,
            ServiceCounterDataType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ServiceFault",
            ServiceFault.class,
            new ServiceFault.Codec(),
            ServiceFault.BinaryEncodingId,
            ServiceFault.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SessionDiagnosticsDataType",
            SessionDiagnosticsDataType.class,
            new SessionDiagnosticsDataType.Codec(),
            SessionDiagnosticsDataType.BinaryEncodingId,
            SessionDiagnosticsDataType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SessionSecurityDiagnosticsDataType",
            SessionSecurityDiagnosticsDataType.class,
            new SessionSecurityDiagnosticsDataType.Codec(),
            SessionSecurityDiagnosticsDataType.BinaryEncodingId,
            SessionSecurityDiagnosticsDataType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SetMonitoringModeRequest",
            SetMonitoringModeRequest.class,
            new SetMonitoringModeRequest.Codec(),
            SetMonitoringModeRequest.BinaryEncodingId,
            SetMonitoringModeRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SetMonitoringModeResponse",
            SetMonitoringModeResponse.class,
            new SetMonitoringModeResponse.Codec(),
            SetMonitoringModeResponse.BinaryEncodingId,
            SetMonitoringModeResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SetPublishingModeRequest",
            SetPublishingModeRequest.class,
            new SetPublishingModeRequest.Codec(),
            SetPublishingModeRequest.BinaryEncodingId,
            SetPublishingModeRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SetPublishingModeResponse",
            SetPublishingModeResponse.class,
            new SetPublishingModeResponse.Codec(),
            SetPublishingModeResponse.BinaryEncodingId,
            SetPublishingModeResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SetTriggeringRequest",
            SetTriggeringRequest.class,
            new SetTriggeringRequest.Codec(),
            SetTriggeringRequest.BinaryEncodingId,
            SetTriggeringRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SetTriggeringResponse",
            SetTriggeringResponse.class,
            new SetTriggeringResponse.Codec(),
            SetTriggeringResponse.BinaryEncodingId,
            SetTriggeringResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SignatureData",
            SignatureData.class,
            new SignatureData.Codec(),
            SignatureData.BinaryEncodingId,
            SignatureData.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SignedSoftwareCertificate",
            SignedSoftwareCertificate.class,
            new SignedSoftwareCertificate.Codec(),
            SignedSoftwareCertificate.BinaryEncodingId,
            SignedSoftwareCertificate.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SimpleAttributeOperand",
            SimpleAttributeOperand.class,
            new SimpleAttributeOperand.Codec(),
            SimpleAttributeOperand.BinaryEncodingId,
            SimpleAttributeOperand.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SoftwareCertificate",
            SoftwareCertificate.class,
            new SoftwareCertificate.Codec(),
            SoftwareCertificate.BinaryEncodingId,
            SoftwareCertificate.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "StatusChangeNotification",
            StatusChangeNotification.class,
            new StatusChangeNotification.Codec(),
            StatusChangeNotification.BinaryEncodingId,
            StatusChangeNotification.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "StatusResult",
            StatusResult.class,
            new StatusResult.Codec(),
            StatusResult.BinaryEncodingId,
            StatusResult.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SubscriptionAcknowledgement",
            SubscriptionAcknowledgement.class,
            new SubscriptionAcknowledgement.Codec(),
            SubscriptionAcknowledgement.BinaryEncodingId,
            SubscriptionAcknowledgement.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SubscriptionDiagnosticsDataType",
            SubscriptionDiagnosticsDataType.class,
            new SubscriptionDiagnosticsDataType.Codec(),
            SubscriptionDiagnosticsDataType.BinaryEncodingId,
            SubscriptionDiagnosticsDataType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "SupportedProfile",
            SupportedProfile.class,
            new SupportedProfile.Codec(),
            SupportedProfile.BinaryEncodingId,
            SupportedProfile.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "TimeZoneDataType",
            TimeZoneDataType.class,
            new TimeZoneDataType.Codec(),
            TimeZoneDataType.BinaryEncodingId,
            TimeZoneDataType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "TransferResult",
            TransferResult.class,
            new TransferResult.Codec(),
            TransferResult.BinaryEncodingId,
            TransferResult.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "TransferSubscriptionsRequest",
            TransferSubscriptionsRequest.class,
            new TransferSubscriptionsRequest.Codec(),
            TransferSubscriptionsRequest.BinaryEncodingId,
            TransferSubscriptionsRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "TransferSubscriptionsResponse",
            TransferSubscriptionsResponse.class,
            new TransferSubscriptionsResponse.Codec(),
            TransferSubscriptionsResponse.BinaryEncodingId,
            TransferSubscriptionsResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "TranslateBrowsePathsToNodeIdsRequest",
            TranslateBrowsePathsToNodeIdsRequest.class,
            new TranslateBrowsePathsToNodeIdsRequest.Codec(),
            TranslateBrowsePathsToNodeIdsRequest.BinaryEncodingId,
            TranslateBrowsePathsToNodeIdsRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "TranslateBrowsePathsToNodeIdsResponse",
            TranslateBrowsePathsToNodeIdsResponse.class,
            new TranslateBrowsePathsToNodeIdsResponse.Codec(),
            TranslateBrowsePathsToNodeIdsResponse.BinaryEncodingId,
            TranslateBrowsePathsToNodeIdsResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "TrustListDataType",
            TrustListDataType.class,
            new TrustListDataType.Codec(),
            TrustListDataType.BinaryEncodingId,
            TrustListDataType.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "TypeNode",
            TypeNode.class,
            new TypeNode.Codec(),
            TypeNode.BinaryEncodingId,
            TypeNode.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "Union",
            Union.class,
            new Union.Codec(),
            Union.BinaryEncodingId,
            Union.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "UnregisterNodesRequest",
            UnregisterNodesRequest.class,
            new UnregisterNodesRequest.Codec(),
            UnregisterNodesRequest.BinaryEncodingId,
            UnregisterNodesRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "UnregisterNodesResponse",
            UnregisterNodesResponse.class,
            new UnregisterNodesResponse.Codec(),
            UnregisterNodesResponse.BinaryEncodingId,
            UnregisterNodesResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "UpdateDataDetails",
            UpdateDataDetails.class,
            new UpdateDataDetails.Codec(),
            UpdateDataDetails.BinaryEncodingId,
            UpdateDataDetails.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "UpdateEventDetails",
            UpdateEventDetails.class,
            new UpdateEventDetails.Codec(),
            UpdateEventDetails.BinaryEncodingId,
            UpdateEventDetails.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "UpdateStructureDataDetails",
            UpdateStructureDataDetails.class,
            new UpdateStructureDataDetails.Codec(),
            UpdateStructureDataDetails.BinaryEncodingId,
            UpdateStructureDataDetails.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "UserIdentityToken",
            UserIdentityToken.class,
            new UserIdentityToken.Codec(),
            UserIdentityToken.BinaryEncodingId,
            UserIdentityToken.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "UserNameIdentityToken",
            UserNameIdentityToken.class,
            new UserNameIdentityToken.Codec(),
            UserNameIdentityToken.BinaryEncodingId,
            UserNameIdentityToken.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "UserTokenPolicy",
            UserTokenPolicy.class,
            new UserTokenPolicy.Codec(),
            UserTokenPolicy.BinaryEncodingId,
            UserTokenPolicy.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "VariableAttributes",
            VariableAttributes.class,
            new VariableAttributes.Codec(),
            VariableAttributes.BinaryEncodingId,
            VariableAttributes.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "VariableNode",
            VariableNode.class,
            new VariableNode.Codec(),
            VariableNode.BinaryEncodingId,
            VariableNode.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "VariableTypeAttributes",
            VariableTypeAttributes.class,
            new VariableTypeAttributes.Codec(),
            VariableTypeAttributes.BinaryEncodingId,
            VariableTypeAttributes.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "VariableTypeNode",
            VariableTypeNode.class,
            new VariableTypeNode.Codec(),
            VariableTypeNode.BinaryEncodingId,
            VariableTypeNode.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ViewAttributes",
            ViewAttributes.class,
            new ViewAttributes.Codec(),
            ViewAttributes.BinaryEncodingId,
            ViewAttributes.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ViewDescription",
            ViewDescription.class,
            new ViewDescription.Codec(),
            ViewDescription.BinaryEncodingId,
            ViewDescription.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "ViewNode",
            ViewNode.class,
            new ViewNode.Codec(),
            ViewNode.BinaryEncodingId,
            ViewNode.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "WriteRequest",
            WriteRequest.class,
            new WriteRequest.Codec(),
            WriteRequest.BinaryEncodingId,
            WriteRequest.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "WriteResponse",
            WriteResponse.class,
            new WriteResponse.Codec(),
            WriteResponse.BinaryEncodingId,
            WriteResponse.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "WriteValue",
            WriteValue.class,
            new WriteValue.Codec(),
            WriteValue.BinaryEncodingId,
            WriteValue.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "X509IdentityToken",
            X509IdentityToken.class,
            new X509IdentityToken.Codec(),
            X509IdentityToken.BinaryEncodingId,
            X509IdentityToken.XmlEncodingId
        );

        BuiltinDataTypeDictionary.register(
            "XVType",
            XVType.class,
            new XVType.Codec(),
            XVType.BinaryEncodingId,
            XVType.XmlEncodingId
        );
    }

}

