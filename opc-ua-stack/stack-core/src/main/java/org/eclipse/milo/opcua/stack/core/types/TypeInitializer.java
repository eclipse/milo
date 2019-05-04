/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.AxisScaleEnumeration;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DataChangeTrigger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DeadbandType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.StatusChangeNotification;
import org.eclipse.milo.opcua.stack.core.types.structured.StatusResult;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionAcknowledgement;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.TimeZoneDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferResult;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.TrustListDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.TypeNode;
import org.eclipse.milo.opcua.stack.core.types.structured.UnregisterNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.UnregisterNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.UpdateDataDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.UpdateEventDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.UpdateStructureDataDetails;
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

class TypeInitializer {
    private static final String NAMESPACE_URI = "http://opcfoundation.org/UA/";

    public static final void initialize(NamespaceTable namespaceTable,
                                        DataTypeDictionary<OpcUaBinaryDataTypeCodec<?>> binaryDictionary,
                                        DataTypeDictionary<OpcUaXmlDataTypeCodec<?>> xmlDictionary) {
        initializeStructs(namespaceTable, binaryDictionary, xmlDictionary);
        initializeEnums(namespaceTable, binaryDictionary, xmlDictionary);
    }

    private static final void initializeStructs(NamespaceTable namespaceTable,
                                                DataTypeDictionary<OpcUaBinaryDataTypeCodec<?>> binaryDictionary,
                                                DataTypeDictionary<OpcUaXmlDataTypeCodec<?>> xmlDictionary) {
        binaryDictionary.registerStructCodec(
            new TrustListDataType.Codec().asBinaryCodec(),
            "TrustListDataType",
            TrustListDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TrustListDataType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TrustListDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TrustListDataType"),
            TrustListDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TrustListDataType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new Argument.Codec().asBinaryCodec(),
            "Argument",
            Argument.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            Argument.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new Argument.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "Argument"),
            Argument.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            Argument.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new StatusResult.Codec().asBinaryCodec(),
            "StatusResult",
            StatusResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            StatusResult.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new StatusResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "StatusResult"),
            StatusResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            StatusResult.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UserTokenPolicy.Codec().asBinaryCodec(),
            "UserTokenPolicy",
            UserTokenPolicy.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UserTokenPolicy.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UserTokenPolicy.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UserTokenPolicy"),
            UserTokenPolicy.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UserTokenPolicy.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ApplicationDescription.Codec().asBinaryCodec(),
            "ApplicationDescription",
            ApplicationDescription.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ApplicationDescription.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ApplicationDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ApplicationDescription"),
            ApplicationDescription.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ApplicationDescription.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EndpointDescription.Codec().asBinaryCodec(),
            "EndpointDescription",
            EndpointDescription.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EndpointDescription.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EndpointDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EndpointDescription"),
            EndpointDescription.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EndpointDescription.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AnonymousIdentityToken.Codec().asBinaryCodec(),
            "AnonymousIdentityToken",
            AnonymousIdentityToken.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AnonymousIdentityToken.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AnonymousIdentityToken.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AnonymousIdentityToken"),
            AnonymousIdentityToken.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AnonymousIdentityToken.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UserNameIdentityToken.Codec().asBinaryCodec(),
            "UserNameIdentityToken",
            UserNameIdentityToken.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UserNameIdentityToken.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UserNameIdentityToken.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UserNameIdentityToken"),
            UserNameIdentityToken.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UserNameIdentityToken.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new X509IdentityToken.Codec().asBinaryCodec(),
            "X509IdentityToken",
            X509IdentityToken.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            X509IdentityToken.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new X509IdentityToken.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "X509IdentityToken"),
            X509IdentityToken.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            X509IdentityToken.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EndpointConfiguration.Codec().asBinaryCodec(),
            "EndpointConfiguration",
            EndpointConfiguration.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EndpointConfiguration.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EndpointConfiguration.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EndpointConfiguration"),
            EndpointConfiguration.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EndpointConfiguration.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BuildInfo.Codec().asBinaryCodec(),
            "BuildInfo",
            BuildInfo.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BuildInfo.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BuildInfo.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BuildInfo"),
            BuildInfo.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BuildInfo.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SignedSoftwareCertificate.Codec().asBinaryCodec(),
            "SignedSoftwareCertificate",
            SignedSoftwareCertificate.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SignedSoftwareCertificate.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SignedSoftwareCertificate.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SignedSoftwareCertificate"),
            SignedSoftwareCertificate.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SignedSoftwareCertificate.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AddNodesItem.Codec().asBinaryCodec(),
            "AddNodesItem",
            AddNodesItem.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddNodesItem.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AddNodesItem.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AddNodesItem"),
            AddNodesItem.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddNodesItem.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AddReferencesItem.Codec().asBinaryCodec(),
            "AddReferencesItem",
            AddReferencesItem.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddReferencesItem.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AddReferencesItem.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AddReferencesItem"),
            AddReferencesItem.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddReferencesItem.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteNodesItem.Codec().asBinaryCodec(),
            "DeleteNodesItem",
            DeleteNodesItem.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteNodesItem.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteNodesItem.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteNodesItem"),
            DeleteNodesItem.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteNodesItem.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteReferencesItem.Codec().asBinaryCodec(),
            "DeleteReferencesItem",
            DeleteReferencesItem.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteReferencesItem.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteReferencesItem.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteReferencesItem"),
            DeleteReferencesItem.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteReferencesItem.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RegisteredServer.Codec().asBinaryCodec(),
            "RegisteredServer",
            RegisteredServer.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisteredServer.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RegisteredServer.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RegisteredServer"),
            RegisteredServer.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisteredServer.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new OptionSet.Codec().asBinaryCodec(),
            "OptionSet",
            OptionSet.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            OptionSet.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new OptionSet.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "OptionSet"),
            OptionSet.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            OptionSet.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RelativePathElement.Codec().asBinaryCodec(),
            "RelativePathElement",
            RelativePathElement.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RelativePathElement.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RelativePathElement.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RelativePathElement"),
            RelativePathElement.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RelativePathElement.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RelativePath.Codec().asBinaryCodec(),
            "RelativePath",
            RelativePath.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RelativePath.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RelativePath.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RelativePath"),
            RelativePath.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RelativePath.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ContentFilterElement.Codec().asBinaryCodec(),
            "ContentFilterElement",
            ContentFilterElement.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ContentFilterElement.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ContentFilterElement.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ContentFilterElement"),
            ContentFilterElement.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ContentFilterElement.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ContentFilter.Codec().asBinaryCodec(),
            "ContentFilter",
            ContentFilter.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ContentFilter.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ContentFilter.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ContentFilter"),
            ContentFilter.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ContentFilter.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new FilterOperand.Codec().asBinaryCodec(),
            "FilterOperand",
            FilterOperand.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FilterOperand.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new FilterOperand.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "FilterOperand"),
            FilterOperand.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FilterOperand.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ElementOperand.Codec().asBinaryCodec(),
            "ElementOperand",
            ElementOperand.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ElementOperand.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ElementOperand.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ElementOperand"),
            ElementOperand.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ElementOperand.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new LiteralOperand.Codec().asBinaryCodec(),
            "LiteralOperand",
            LiteralOperand.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            LiteralOperand.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new LiteralOperand.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "LiteralOperand"),
            LiteralOperand.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            LiteralOperand.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AttributeOperand.Codec().asBinaryCodec(),
            "AttributeOperand",
            AttributeOperand.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AttributeOperand.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AttributeOperand.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AttributeOperand"),
            AttributeOperand.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AttributeOperand.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SimpleAttributeOperand.Codec().asBinaryCodec(),
            "SimpleAttributeOperand",
            SimpleAttributeOperand.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SimpleAttributeOperand.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SimpleAttributeOperand.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SimpleAttributeOperand"),
            SimpleAttributeOperand.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SimpleAttributeOperand.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DiscoveryConfiguration.Codec().asBinaryCodec(),
            "DiscoveryConfiguration",
            DiscoveryConfiguration.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DiscoveryConfiguration.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DiscoveryConfiguration.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DiscoveryConfiguration"),
            DiscoveryConfiguration.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DiscoveryConfiguration.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MdnsDiscoveryConfiguration.Codec().asBinaryCodec(),
            "MdnsDiscoveryConfiguration",
            MdnsDiscoveryConfiguration.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MdnsDiscoveryConfiguration.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MdnsDiscoveryConfiguration.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MdnsDiscoveryConfiguration"),
            MdnsDiscoveryConfiguration.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MdnsDiscoveryConfiguration.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryEvent.Codec().asBinaryCodec(),
            "HistoryEvent",
            HistoryEvent.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryEvent.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryEvent.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryEvent"),
            HistoryEvent.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryEvent.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MonitoringFilter.Codec().asBinaryCodec(),
            "MonitoringFilter",
            MonitoringFilter.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoringFilter.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MonitoringFilter.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoringFilter"),
            MonitoringFilter.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoringFilter.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new TimeZoneDataType.Codec().asBinaryCodec(),
            "TimeZoneDataType",
            TimeZoneDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TimeZoneDataType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TimeZoneDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TimeZoneDataType"),
            TimeZoneDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TimeZoneDataType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EventFilter.Codec().asBinaryCodec(),
            "EventFilter",
            EventFilter.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EventFilter.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EventFilter.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EventFilter"),
            EventFilter.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EventFilter.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RedundantServerDataType.Codec().asBinaryCodec(),
            "RedundantServerDataType",
            RedundantServerDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RedundantServerDataType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RedundantServerDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RedundantServerDataType"),
            RedundantServerDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RedundantServerDataType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SamplingIntervalDiagnosticsDataType.Codec().asBinaryCodec(),
            "SamplingIntervalDiagnosticsDataType",
            SamplingIntervalDiagnosticsDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SamplingIntervalDiagnosticsDataType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SamplingIntervalDiagnosticsDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SamplingIntervalDiagnosticsDataType"),
            SamplingIntervalDiagnosticsDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SamplingIntervalDiagnosticsDataType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ServerDiagnosticsSummaryDataType.Codec().asBinaryCodec(),
            "ServerDiagnosticsSummaryDataType",
            ServerDiagnosticsSummaryDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServerDiagnosticsSummaryDataType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ServerDiagnosticsSummaryDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ServerDiagnosticsSummaryDataType"),
            ServerDiagnosticsSummaryDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServerDiagnosticsSummaryDataType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ServerStatusDataType.Codec().asBinaryCodec(),
            "ServerStatusDataType",
            ServerStatusDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServerStatusDataType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ServerStatusDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ServerStatusDataType"),
            ServerStatusDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServerStatusDataType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SessionDiagnosticsDataType.Codec().asBinaryCodec(),
            "SessionDiagnosticsDataType",
            SessionDiagnosticsDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SessionDiagnosticsDataType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SessionDiagnosticsDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SessionDiagnosticsDataType"),
            SessionDiagnosticsDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SessionDiagnosticsDataType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SessionSecurityDiagnosticsDataType.Codec().asBinaryCodec(),
            "SessionSecurityDiagnosticsDataType",
            SessionSecurityDiagnosticsDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SessionSecurityDiagnosticsDataType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SessionSecurityDiagnosticsDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SessionSecurityDiagnosticsDataType"),
            SessionSecurityDiagnosticsDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SessionSecurityDiagnosticsDataType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ServiceCounterDataType.Codec().asBinaryCodec(),
            "ServiceCounterDataType",
            ServiceCounterDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServiceCounterDataType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ServiceCounterDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ServiceCounterDataType"),
            ServiceCounterDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServiceCounterDataType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SubscriptionDiagnosticsDataType.Codec().asBinaryCodec(),
            "SubscriptionDiagnosticsDataType",
            SubscriptionDiagnosticsDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SubscriptionDiagnosticsDataType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SubscriptionDiagnosticsDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SubscriptionDiagnosticsDataType"),
            SubscriptionDiagnosticsDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SubscriptionDiagnosticsDataType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ModelChangeStructureDataType.Codec().asBinaryCodec(),
            "ModelChangeStructureDataType",
            ModelChangeStructureDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModelChangeStructureDataType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ModelChangeStructureDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ModelChangeStructureDataType"),
            ModelChangeStructureDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModelChangeStructureDataType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new Range.Codec().asBinaryCodec(),
            "Range",
            Range.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            Range.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new Range.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "Range"),
            Range.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            Range.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EUInformation.Codec().asBinaryCodec(),
            "EUInformation",
            EUInformation.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EUInformation.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EUInformation.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EUInformation"),
            EUInformation.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EUInformation.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new Annotation.Codec().asBinaryCodec(),
            "Annotation",
            Annotation.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            Annotation.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new Annotation.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "Annotation"),
            Annotation.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            Annotation.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ProgramDiagnosticDataType.Codec().asBinaryCodec(),
            "ProgramDiagnosticDataType",
            ProgramDiagnosticDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ProgramDiagnosticDataType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ProgramDiagnosticDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ProgramDiagnosticDataType"),
            ProgramDiagnosticDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ProgramDiagnosticDataType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SemanticChangeStructureDataType.Codec().asBinaryCodec(),
            "SemanticChangeStructureDataType",
            SemanticChangeStructureDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SemanticChangeStructureDataType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SemanticChangeStructureDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SemanticChangeStructureDataType"),
            SemanticChangeStructureDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SemanticChangeStructureDataType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryEventFieldList.Codec().asBinaryCodec(),
            "HistoryEventFieldList",
            HistoryEventFieldList.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryEventFieldList.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryEventFieldList.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryEventFieldList"),
            HistoryEventFieldList.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryEventFieldList.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new IssuedIdentityToken.Codec().asBinaryCodec(),
            "IssuedIdentityToken",
            IssuedIdentityToken.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            IssuedIdentityToken.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new IssuedIdentityToken.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "IssuedIdentityToken"),
            IssuedIdentityToken.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            IssuedIdentityToken.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AggregateConfiguration.Codec().asBinaryCodec(),
            "AggregateConfiguration",
            AggregateConfiguration.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AggregateConfiguration.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AggregateConfiguration.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AggregateConfiguration"),
            AggregateConfiguration.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AggregateConfiguration.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EnumValueType.Codec().asBinaryCodec(),
            "EnumValueType",
            EnumValueType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EnumValueType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EnumValueType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EnumValueType"),
            EnumValueType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EnumValueType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EndpointUrlListDataType.Codec().asBinaryCodec(),
            "EndpointUrlListDataType",
            EndpointUrlListDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EndpointUrlListDataType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EndpointUrlListDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EndpointUrlListDataType"),
            EndpointUrlListDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EndpointUrlListDataType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new NetworkGroupDataType.Codec().asBinaryCodec(),
            "NetworkGroupDataType",
            NetworkGroupDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NetworkGroupDataType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new NetworkGroupDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "NetworkGroupDataType"),
            NetworkGroupDataType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NetworkGroupDataType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AxisInformation.Codec().asBinaryCodec(),
            "AxisInformation",
            AxisInformation.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AxisInformation.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AxisInformation.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AxisInformation"),
            AxisInformation.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AxisInformation.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new XVType.Codec().asBinaryCodec(),
            "XVType",
            XVType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            XVType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new XVType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "XVType"),
            XVType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            XVType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ComplexNumberType.Codec().asBinaryCodec(),
            "ComplexNumberType",
            ComplexNumberType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ComplexNumberType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ComplexNumberType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ComplexNumberType"),
            ComplexNumberType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ComplexNumberType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DoubleComplexNumberType.Codec().asBinaryCodec(),
            "DoubleComplexNumberType",
            DoubleComplexNumberType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DoubleComplexNumberType.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DoubleComplexNumberType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DoubleComplexNumberType"),
            DoubleComplexNumberType.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DoubleComplexNumberType.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ServerOnNetwork.Codec().asBinaryCodec(),
            "ServerOnNetwork",
            ServerOnNetwork.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServerOnNetwork.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ServerOnNetwork.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ServerOnNetwork"),
            ServerOnNetwork.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServerOnNetwork.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new Node.Codec().asBinaryCodec(),
            "Node",
            Node.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            Node.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new Node.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "Node"),
            Node.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            Node.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ObjectNode.Codec().asBinaryCodec(),
            "ObjectNode",
            ObjectNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ObjectNode.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ObjectNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ObjectNode"),
            ObjectNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ObjectNode.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ObjectTypeNode.Codec().asBinaryCodec(),
            "ObjectTypeNode",
            ObjectTypeNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ObjectTypeNode.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ObjectTypeNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ObjectTypeNode"),
            ObjectTypeNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ObjectTypeNode.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new VariableNode.Codec().asBinaryCodec(),
            "VariableNode",
            VariableNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            VariableNode.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new VariableNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "VariableNode"),
            VariableNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            VariableNode.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new VariableTypeNode.Codec().asBinaryCodec(),
            "VariableTypeNode",
            VariableTypeNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            VariableTypeNode.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new VariableTypeNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "VariableTypeNode"),
            VariableTypeNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            VariableTypeNode.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReferenceTypeNode.Codec().asBinaryCodec(),
            "ReferenceTypeNode",
            ReferenceTypeNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReferenceTypeNode.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReferenceTypeNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReferenceTypeNode"),
            ReferenceTypeNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReferenceTypeNode.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MethodNode.Codec().asBinaryCodec(),
            "MethodNode",
            MethodNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MethodNode.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MethodNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MethodNode"),
            MethodNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MethodNode.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ViewNode.Codec().asBinaryCodec(),
            "ViewNode",
            ViewNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ViewNode.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ViewNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ViewNode"),
            ViewNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ViewNode.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DataTypeNode.Codec().asBinaryCodec(),
            "DataTypeNode",
            DataTypeNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataTypeNode.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DataTypeNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DataTypeNode"),
            DataTypeNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataTypeNode.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReferenceNode.Codec().asBinaryCodec(),
            "ReferenceNode",
            ReferenceNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReferenceNode.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReferenceNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReferenceNode"),
            ReferenceNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReferenceNode.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new NodeAttributes.Codec().asBinaryCodec(),
            "NodeAttributes",
            NodeAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NodeAttributes.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new NodeAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "NodeAttributes"),
            NodeAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NodeAttributes.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ObjectAttributes.Codec().asBinaryCodec(),
            "ObjectAttributes",
            ObjectAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ObjectAttributes.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ObjectAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ObjectAttributes"),
            ObjectAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ObjectAttributes.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new VariableAttributes.Codec().asBinaryCodec(),
            "VariableAttributes",
            VariableAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            VariableAttributes.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new VariableAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "VariableAttributes"),
            VariableAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            VariableAttributes.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MethodAttributes.Codec().asBinaryCodec(),
            "MethodAttributes",
            MethodAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MethodAttributes.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MethodAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MethodAttributes"),
            MethodAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MethodAttributes.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ObjectTypeAttributes.Codec().asBinaryCodec(),
            "ObjectTypeAttributes",
            ObjectTypeAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ObjectTypeAttributes.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ObjectTypeAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ObjectTypeAttributes"),
            ObjectTypeAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ObjectTypeAttributes.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new VariableTypeAttributes.Codec().asBinaryCodec(),
            "VariableTypeAttributes",
            VariableTypeAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            VariableTypeAttributes.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new VariableTypeAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "VariableTypeAttributes"),
            VariableTypeAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            VariableTypeAttributes.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReferenceTypeAttributes.Codec().asBinaryCodec(),
            "ReferenceTypeAttributes",
            ReferenceTypeAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReferenceTypeAttributes.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReferenceTypeAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReferenceTypeAttributes"),
            ReferenceTypeAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReferenceTypeAttributes.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DataTypeAttributes.Codec().asBinaryCodec(),
            "DataTypeAttributes",
            DataTypeAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataTypeAttributes.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DataTypeAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DataTypeAttributes"),
            DataTypeAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataTypeAttributes.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ViewAttributes.Codec().asBinaryCodec(),
            "ViewAttributes",
            ViewAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ViewAttributes.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ViewAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ViewAttributes"),
            ViewAttributes.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ViewAttributes.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RequestHeader.Codec().asBinaryCodec(),
            "RequestHeader",
            RequestHeader.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RequestHeader.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RequestHeader.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RequestHeader"),
            RequestHeader.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RequestHeader.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ResponseHeader.Codec().asBinaryCodec(),
            "ResponseHeader",
            ResponseHeader.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ResponseHeader.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ResponseHeader.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ResponseHeader"),
            ResponseHeader.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ResponseHeader.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ServiceFault.Codec().asBinaryCodec(),
            "ServiceFault",
            ServiceFault.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServiceFault.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ServiceFault.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ServiceFault"),
            ServiceFault.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServiceFault.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new FindServersRequest.Codec().asBinaryCodec(),
            "FindServersRequest",
            FindServersRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FindServersRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new FindServersRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "FindServersRequest"),
            FindServersRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FindServersRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new FindServersResponse.Codec().asBinaryCodec(),
            "FindServersResponse",
            FindServersResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FindServersResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new FindServersResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "FindServersResponse"),
            FindServersResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FindServersResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new GetEndpointsRequest.Codec().asBinaryCodec(),
            "GetEndpointsRequest",
            GetEndpointsRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            GetEndpointsRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new GetEndpointsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "GetEndpointsRequest"),
            GetEndpointsRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            GetEndpointsRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new GetEndpointsResponse.Codec().asBinaryCodec(),
            "GetEndpointsResponse",
            GetEndpointsResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            GetEndpointsResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new GetEndpointsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "GetEndpointsResponse"),
            GetEndpointsResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            GetEndpointsResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RegisterServerRequest.Codec().asBinaryCodec(),
            "RegisterServerRequest",
            RegisterServerRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterServerRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RegisterServerRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RegisterServerRequest"),
            RegisterServerRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterServerRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RegisterServerResponse.Codec().asBinaryCodec(),
            "RegisterServerResponse",
            RegisterServerResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterServerResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RegisterServerResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RegisterServerResponse"),
            RegisterServerResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterServerResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ChannelSecurityToken.Codec().asBinaryCodec(),
            "ChannelSecurityToken",
            ChannelSecurityToken.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ChannelSecurityToken.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ChannelSecurityToken.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ChannelSecurityToken"),
            ChannelSecurityToken.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ChannelSecurityToken.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new OpenSecureChannelRequest.Codec().asBinaryCodec(),
            "OpenSecureChannelRequest",
            OpenSecureChannelRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            OpenSecureChannelRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new OpenSecureChannelRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "OpenSecureChannelRequest"),
            OpenSecureChannelRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            OpenSecureChannelRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new OpenSecureChannelResponse.Codec().asBinaryCodec(),
            "OpenSecureChannelResponse",
            OpenSecureChannelResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            OpenSecureChannelResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new OpenSecureChannelResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "OpenSecureChannelResponse"),
            OpenSecureChannelResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            OpenSecureChannelResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CloseSecureChannelRequest.Codec().asBinaryCodec(),
            "CloseSecureChannelRequest",
            CloseSecureChannelRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CloseSecureChannelRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CloseSecureChannelRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CloseSecureChannelRequest"),
            CloseSecureChannelRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CloseSecureChannelRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CloseSecureChannelResponse.Codec().asBinaryCodec(),
            "CloseSecureChannelResponse",
            CloseSecureChannelResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CloseSecureChannelResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CloseSecureChannelResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CloseSecureChannelResponse"),
            CloseSecureChannelResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CloseSecureChannelResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SignatureData.Codec().asBinaryCodec(),
            "SignatureData",
            SignatureData.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SignatureData.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SignatureData.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SignatureData"),
            SignatureData.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SignatureData.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CreateSessionRequest.Codec().asBinaryCodec(),
            "CreateSessionRequest",
            CreateSessionRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateSessionRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CreateSessionRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CreateSessionRequest"),
            CreateSessionRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateSessionRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CreateSessionResponse.Codec().asBinaryCodec(),
            "CreateSessionResponse",
            CreateSessionResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateSessionResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CreateSessionResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CreateSessionResponse"),
            CreateSessionResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateSessionResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ActivateSessionRequest.Codec().asBinaryCodec(),
            "ActivateSessionRequest",
            ActivateSessionRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ActivateSessionRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ActivateSessionRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ActivateSessionRequest"),
            ActivateSessionRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ActivateSessionRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ActivateSessionResponse.Codec().asBinaryCodec(),
            "ActivateSessionResponse",
            ActivateSessionResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ActivateSessionResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ActivateSessionResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ActivateSessionResponse"),
            ActivateSessionResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ActivateSessionResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CloseSessionRequest.Codec().asBinaryCodec(),
            "CloseSessionRequest",
            CloseSessionRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CloseSessionRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CloseSessionRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CloseSessionRequest"),
            CloseSessionRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CloseSessionRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CloseSessionResponse.Codec().asBinaryCodec(),
            "CloseSessionResponse",
            CloseSessionResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CloseSessionResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CloseSessionResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CloseSessionResponse"),
            CloseSessionResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CloseSessionResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CancelRequest.Codec().asBinaryCodec(),
            "CancelRequest",
            CancelRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CancelRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CancelRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CancelRequest"),
            CancelRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CancelRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CancelResponse.Codec().asBinaryCodec(),
            "CancelResponse",
            CancelResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CancelResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CancelResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CancelResponse"),
            CancelResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CancelResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AddNodesResult.Codec().asBinaryCodec(),
            "AddNodesResult",
            AddNodesResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddNodesResult.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AddNodesResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AddNodesResult"),
            AddNodesResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddNodesResult.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AddNodesRequest.Codec().asBinaryCodec(),
            "AddNodesRequest",
            AddNodesRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddNodesRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AddNodesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AddNodesRequest"),
            AddNodesRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddNodesRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AddNodesResponse.Codec().asBinaryCodec(),
            "AddNodesResponse",
            AddNodesResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddNodesResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AddNodesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AddNodesResponse"),
            AddNodesResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddNodesResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AddReferencesRequest.Codec().asBinaryCodec(),
            "AddReferencesRequest",
            AddReferencesRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddReferencesRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AddReferencesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AddReferencesRequest"),
            AddReferencesRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddReferencesRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AddReferencesResponse.Codec().asBinaryCodec(),
            "AddReferencesResponse",
            AddReferencesResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddReferencesResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AddReferencesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AddReferencesResponse"),
            AddReferencesResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddReferencesResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteNodesRequest.Codec().asBinaryCodec(),
            "DeleteNodesRequest",
            DeleteNodesRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteNodesRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteNodesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteNodesRequest"),
            DeleteNodesRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteNodesRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteNodesResponse.Codec().asBinaryCodec(),
            "DeleteNodesResponse",
            DeleteNodesResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteNodesResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteNodesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteNodesResponse"),
            DeleteNodesResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteNodesResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteReferencesRequest.Codec().asBinaryCodec(),
            "DeleteReferencesRequest",
            DeleteReferencesRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteReferencesRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteReferencesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteReferencesRequest"),
            DeleteReferencesRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteReferencesRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteReferencesResponse.Codec().asBinaryCodec(),
            "DeleteReferencesResponse",
            DeleteReferencesResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteReferencesResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteReferencesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteReferencesResponse"),
            DeleteReferencesResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteReferencesResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ViewDescription.Codec().asBinaryCodec(),
            "ViewDescription",
            ViewDescription.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ViewDescription.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ViewDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ViewDescription"),
            ViewDescription.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ViewDescription.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowseDescription.Codec().asBinaryCodec(),
            "BrowseDescription",
            BrowseDescription.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseDescription.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowseDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowseDescription"),
            BrowseDescription.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseDescription.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReferenceDescription.Codec().asBinaryCodec(),
            "ReferenceDescription",
            ReferenceDescription.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReferenceDescription.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReferenceDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReferenceDescription"),
            ReferenceDescription.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReferenceDescription.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowseResult.Codec().asBinaryCodec(),
            "BrowseResult",
            BrowseResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseResult.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowseResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowseResult"),
            BrowseResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseResult.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowseRequest.Codec().asBinaryCodec(),
            "BrowseRequest",
            BrowseRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowseRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowseRequest"),
            BrowseRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowseResponse.Codec().asBinaryCodec(),
            "BrowseResponse",
            BrowseResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowseResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowseResponse"),
            BrowseResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowseNextRequest.Codec().asBinaryCodec(),
            "BrowseNextRequest",
            BrowseNextRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseNextRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowseNextRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowseNextRequest"),
            BrowseNextRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseNextRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowseNextResponse.Codec().asBinaryCodec(),
            "BrowseNextResponse",
            BrowseNextResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseNextResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowseNextResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowseNextResponse"),
            BrowseNextResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseNextResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowsePath.Codec().asBinaryCodec(),
            "BrowsePath",
            BrowsePath.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowsePath.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowsePath.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowsePath"),
            BrowsePath.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowsePath.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowsePathTarget.Codec().asBinaryCodec(),
            "BrowsePathTarget",
            BrowsePathTarget.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowsePathTarget.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowsePathTarget.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowsePathTarget"),
            BrowsePathTarget.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowsePathTarget.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowsePathResult.Codec().asBinaryCodec(),
            "BrowsePathResult",
            BrowsePathResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowsePathResult.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowsePathResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowsePathResult"),
            BrowsePathResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowsePathResult.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new TranslateBrowsePathsToNodeIdsRequest.Codec().asBinaryCodec(),
            "TranslateBrowsePathsToNodeIdsRequest",
            TranslateBrowsePathsToNodeIdsRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TranslateBrowsePathsToNodeIdsRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TranslateBrowsePathsToNodeIdsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TranslateBrowsePathsToNodeIdsRequest"),
            TranslateBrowsePathsToNodeIdsRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TranslateBrowsePathsToNodeIdsRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new TranslateBrowsePathsToNodeIdsResponse.Codec().asBinaryCodec(),
            "TranslateBrowsePathsToNodeIdsResponse",
            TranslateBrowsePathsToNodeIdsResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TranslateBrowsePathsToNodeIdsResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TranslateBrowsePathsToNodeIdsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TranslateBrowsePathsToNodeIdsResponse"),
            TranslateBrowsePathsToNodeIdsResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TranslateBrowsePathsToNodeIdsResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RegisterNodesRequest.Codec().asBinaryCodec(),
            "RegisterNodesRequest",
            RegisterNodesRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterNodesRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RegisterNodesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RegisterNodesRequest"),
            RegisterNodesRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterNodesRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RegisterNodesResponse.Codec().asBinaryCodec(),
            "RegisterNodesResponse",
            RegisterNodesResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterNodesResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RegisterNodesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RegisterNodesResponse"),
            RegisterNodesResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterNodesResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UnregisterNodesRequest.Codec().asBinaryCodec(),
            "UnregisterNodesRequest",
            UnregisterNodesRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UnregisterNodesRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UnregisterNodesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UnregisterNodesRequest"),
            UnregisterNodesRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UnregisterNodesRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UnregisterNodesResponse.Codec().asBinaryCodec(),
            "UnregisterNodesResponse",
            UnregisterNodesResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UnregisterNodesResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UnregisterNodesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UnregisterNodesResponse"),
            UnregisterNodesResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UnregisterNodesResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new QueryDataDescription.Codec().asBinaryCodec(),
            "QueryDataDescription",
            QueryDataDescription.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryDataDescription.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new QueryDataDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "QueryDataDescription"),
            QueryDataDescription.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryDataDescription.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new NodeTypeDescription.Codec().asBinaryCodec(),
            "NodeTypeDescription",
            NodeTypeDescription.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NodeTypeDescription.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new NodeTypeDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "NodeTypeDescription"),
            NodeTypeDescription.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NodeTypeDescription.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new QueryDataSet.Codec().asBinaryCodec(),
            "QueryDataSet",
            QueryDataSet.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryDataSet.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new QueryDataSet.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "QueryDataSet"),
            QueryDataSet.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryDataSet.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new NodeReference.Codec().asBinaryCodec(),
            "NodeReference",
            NodeReference.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NodeReference.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new NodeReference.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "NodeReference"),
            NodeReference.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NodeReference.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ContentFilterElementResult.Codec().asBinaryCodec(),
            "ContentFilterElementResult",
            ContentFilterElementResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ContentFilterElementResult.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ContentFilterElementResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ContentFilterElementResult"),
            ContentFilterElementResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ContentFilterElementResult.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ContentFilterResult.Codec().asBinaryCodec(),
            "ContentFilterResult",
            ContentFilterResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ContentFilterResult.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ContentFilterResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ContentFilterResult"),
            ContentFilterResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ContentFilterResult.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ParsingResult.Codec().asBinaryCodec(),
            "ParsingResult",
            ParsingResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ParsingResult.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ParsingResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ParsingResult"),
            ParsingResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ParsingResult.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new QueryFirstRequest.Codec().asBinaryCodec(),
            "QueryFirstRequest",
            QueryFirstRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryFirstRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new QueryFirstRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "QueryFirstRequest"),
            QueryFirstRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryFirstRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new QueryFirstResponse.Codec().asBinaryCodec(),
            "QueryFirstResponse",
            QueryFirstResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryFirstResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new QueryFirstResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "QueryFirstResponse"),
            QueryFirstResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryFirstResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new QueryNextRequest.Codec().asBinaryCodec(),
            "QueryNextRequest",
            QueryNextRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryNextRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new QueryNextRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "QueryNextRequest"),
            QueryNextRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryNextRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new QueryNextResponse.Codec().asBinaryCodec(),
            "QueryNextResponse",
            QueryNextResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryNextResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new QueryNextResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "QueryNextResponse"),
            QueryNextResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryNextResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReadValueId.Codec().asBinaryCodec(),
            "ReadValueId",
            ReadValueId.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadValueId.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReadValueId.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReadValueId"),
            ReadValueId.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadValueId.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReadRequest.Codec().asBinaryCodec(),
            "ReadRequest",
            ReadRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReadRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReadRequest"),
            ReadRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReadResponse.Codec().asBinaryCodec(),
            "ReadResponse",
            ReadResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReadResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReadResponse"),
            ReadResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryReadValueId.Codec().asBinaryCodec(),
            "HistoryReadValueId",
            HistoryReadValueId.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadValueId.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryReadValueId.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryReadValueId"),
            HistoryReadValueId.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadValueId.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryReadResult.Codec().asBinaryCodec(),
            "HistoryReadResult",
            HistoryReadResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadResult.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryReadResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryReadResult"),
            HistoryReadResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadResult.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryReadDetails.Codec().asBinaryCodec(),
            "HistoryReadDetails",
            HistoryReadDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadDetails.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryReadDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryReadDetails"),
            HistoryReadDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadDetails.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReadEventDetails.Codec().asBinaryCodec(),
            "ReadEventDetails",
            ReadEventDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadEventDetails.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReadEventDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReadEventDetails"),
            ReadEventDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadEventDetails.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReadRawModifiedDetails.Codec().asBinaryCodec(),
            "ReadRawModifiedDetails",
            ReadRawModifiedDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadRawModifiedDetails.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReadRawModifiedDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReadRawModifiedDetails"),
            ReadRawModifiedDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadRawModifiedDetails.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReadProcessedDetails.Codec().asBinaryCodec(),
            "ReadProcessedDetails",
            ReadProcessedDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadProcessedDetails.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReadProcessedDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReadProcessedDetails"),
            ReadProcessedDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadProcessedDetails.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReadAtTimeDetails.Codec().asBinaryCodec(),
            "ReadAtTimeDetails",
            ReadAtTimeDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadAtTimeDetails.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReadAtTimeDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReadAtTimeDetails"),
            ReadAtTimeDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadAtTimeDetails.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryData.Codec().asBinaryCodec(),
            "HistoryData",
            HistoryData.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryData.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryData.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryData"),
            HistoryData.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryData.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryReadRequest.Codec().asBinaryCodec(),
            "HistoryReadRequest",
            HistoryReadRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryReadRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryReadRequest"),
            HistoryReadRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryReadResponse.Codec().asBinaryCodec(),
            "HistoryReadResponse",
            HistoryReadResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryReadResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryReadResponse"),
            HistoryReadResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new WriteValue.Codec().asBinaryCodec(),
            "WriteValue",
            WriteValue.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            WriteValue.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new WriteValue.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "WriteValue"),
            WriteValue.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            WriteValue.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new WriteRequest.Codec().asBinaryCodec(),
            "WriteRequest",
            WriteRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            WriteRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new WriteRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "WriteRequest"),
            WriteRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            WriteRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new WriteResponse.Codec().asBinaryCodec(),
            "WriteResponse",
            WriteResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            WriteResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new WriteResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "WriteResponse"),
            WriteResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            WriteResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryUpdateDetails.Codec().asBinaryCodec(),
            "HistoryUpdateDetails",
            HistoryUpdateDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryUpdateDetails.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryUpdateDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryUpdateDetails"),
            HistoryUpdateDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryUpdateDetails.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UpdateDataDetails.Codec().asBinaryCodec(),
            "UpdateDataDetails",
            UpdateDataDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UpdateDataDetails.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UpdateDataDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UpdateDataDetails"),
            UpdateDataDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UpdateDataDetails.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UpdateEventDetails.Codec().asBinaryCodec(),
            "UpdateEventDetails",
            UpdateEventDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UpdateEventDetails.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UpdateEventDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UpdateEventDetails"),
            UpdateEventDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UpdateEventDetails.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteRawModifiedDetails.Codec().asBinaryCodec(),
            "DeleteRawModifiedDetails",
            DeleteRawModifiedDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteRawModifiedDetails.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteRawModifiedDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteRawModifiedDetails"),
            DeleteRawModifiedDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteRawModifiedDetails.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteAtTimeDetails.Codec().asBinaryCodec(),
            "DeleteAtTimeDetails",
            DeleteAtTimeDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteAtTimeDetails.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteAtTimeDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteAtTimeDetails"),
            DeleteAtTimeDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteAtTimeDetails.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteEventDetails.Codec().asBinaryCodec(),
            "DeleteEventDetails",
            DeleteEventDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteEventDetails.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteEventDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteEventDetails"),
            DeleteEventDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteEventDetails.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryUpdateResult.Codec().asBinaryCodec(),
            "HistoryUpdateResult",
            HistoryUpdateResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryUpdateResult.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryUpdateResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryUpdateResult"),
            HistoryUpdateResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryUpdateResult.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryUpdateRequest.Codec().asBinaryCodec(),
            "HistoryUpdateRequest",
            HistoryUpdateRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryUpdateRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryUpdateRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryUpdateRequest"),
            HistoryUpdateRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryUpdateRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryUpdateResponse.Codec().asBinaryCodec(),
            "HistoryUpdateResponse",
            HistoryUpdateResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryUpdateResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryUpdateResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryUpdateResponse"),
            HistoryUpdateResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryUpdateResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CallMethodRequest.Codec().asBinaryCodec(),
            "CallMethodRequest",
            CallMethodRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CallMethodRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CallMethodRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CallMethodRequest"),
            CallMethodRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CallMethodRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CallMethodResult.Codec().asBinaryCodec(),
            "CallMethodResult",
            CallMethodResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CallMethodResult.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CallMethodResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CallMethodResult"),
            CallMethodResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CallMethodResult.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CallRequest.Codec().asBinaryCodec(),
            "CallRequest",
            CallRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CallRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CallRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CallRequest"),
            CallRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CallRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CallResponse.Codec().asBinaryCodec(),
            "CallResponse",
            CallResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CallResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CallResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CallResponse"),
            CallResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CallResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DataChangeFilter.Codec().asBinaryCodec(),
            "DataChangeFilter",
            DataChangeFilter.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataChangeFilter.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DataChangeFilter.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DataChangeFilter"),
            DataChangeFilter.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataChangeFilter.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AggregateFilter.Codec().asBinaryCodec(),
            "AggregateFilter",
            AggregateFilter.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AggregateFilter.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AggregateFilter.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AggregateFilter"),
            AggregateFilter.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AggregateFilter.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MonitoringFilterResult.Codec().asBinaryCodec(),
            "MonitoringFilterResult",
            MonitoringFilterResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoringFilterResult.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MonitoringFilterResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoringFilterResult"),
            MonitoringFilterResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoringFilterResult.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EventFilterResult.Codec().asBinaryCodec(),
            "EventFilterResult",
            EventFilterResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EventFilterResult.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EventFilterResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EventFilterResult"),
            EventFilterResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EventFilterResult.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AggregateFilterResult.Codec().asBinaryCodec(),
            "AggregateFilterResult",
            AggregateFilterResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AggregateFilterResult.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AggregateFilterResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AggregateFilterResult"),
            AggregateFilterResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AggregateFilterResult.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MonitoringParameters.Codec().asBinaryCodec(),
            "MonitoringParameters",
            MonitoringParameters.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoringParameters.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MonitoringParameters.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoringParameters"),
            MonitoringParameters.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoringParameters.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MonitoredItemCreateRequest.Codec().asBinaryCodec(),
            "MonitoredItemCreateRequest",
            MonitoredItemCreateRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemCreateRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MonitoredItemCreateRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoredItemCreateRequest"),
            MonitoredItemCreateRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemCreateRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MonitoredItemCreateResult.Codec().asBinaryCodec(),
            "MonitoredItemCreateResult",
            MonitoredItemCreateResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemCreateResult.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MonitoredItemCreateResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoredItemCreateResult"),
            MonitoredItemCreateResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemCreateResult.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CreateMonitoredItemsRequest.Codec().asBinaryCodec(),
            "CreateMonitoredItemsRequest",
            CreateMonitoredItemsRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateMonitoredItemsRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CreateMonitoredItemsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CreateMonitoredItemsRequest"),
            CreateMonitoredItemsRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateMonitoredItemsRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CreateMonitoredItemsResponse.Codec().asBinaryCodec(),
            "CreateMonitoredItemsResponse",
            CreateMonitoredItemsResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateMonitoredItemsResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CreateMonitoredItemsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CreateMonitoredItemsResponse"),
            CreateMonitoredItemsResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateMonitoredItemsResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MonitoredItemModifyRequest.Codec().asBinaryCodec(),
            "MonitoredItemModifyRequest",
            MonitoredItemModifyRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemModifyRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MonitoredItemModifyRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoredItemModifyRequest"),
            MonitoredItemModifyRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemModifyRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MonitoredItemModifyResult.Codec().asBinaryCodec(),
            "MonitoredItemModifyResult",
            MonitoredItemModifyResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemModifyResult.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MonitoredItemModifyResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoredItemModifyResult"),
            MonitoredItemModifyResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemModifyResult.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ModifyMonitoredItemsRequest.Codec().asBinaryCodec(),
            "ModifyMonitoredItemsRequest",
            ModifyMonitoredItemsRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModifyMonitoredItemsRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ModifyMonitoredItemsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ModifyMonitoredItemsRequest"),
            ModifyMonitoredItemsRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModifyMonitoredItemsRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ModifyMonitoredItemsResponse.Codec().asBinaryCodec(),
            "ModifyMonitoredItemsResponse",
            ModifyMonitoredItemsResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModifyMonitoredItemsResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ModifyMonitoredItemsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ModifyMonitoredItemsResponse"),
            ModifyMonitoredItemsResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModifyMonitoredItemsResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SetMonitoringModeRequest.Codec().asBinaryCodec(),
            "SetMonitoringModeRequest",
            SetMonitoringModeRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetMonitoringModeRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SetMonitoringModeRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SetMonitoringModeRequest"),
            SetMonitoringModeRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetMonitoringModeRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SetMonitoringModeResponse.Codec().asBinaryCodec(),
            "SetMonitoringModeResponse",
            SetMonitoringModeResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetMonitoringModeResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SetMonitoringModeResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SetMonitoringModeResponse"),
            SetMonitoringModeResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetMonitoringModeResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SetTriggeringRequest.Codec().asBinaryCodec(),
            "SetTriggeringRequest",
            SetTriggeringRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetTriggeringRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SetTriggeringRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SetTriggeringRequest"),
            SetTriggeringRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetTriggeringRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SetTriggeringResponse.Codec().asBinaryCodec(),
            "SetTriggeringResponse",
            SetTriggeringResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetTriggeringResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SetTriggeringResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SetTriggeringResponse"),
            SetTriggeringResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetTriggeringResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteMonitoredItemsRequest.Codec().asBinaryCodec(),
            "DeleteMonitoredItemsRequest",
            DeleteMonitoredItemsRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteMonitoredItemsRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteMonitoredItemsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteMonitoredItemsRequest"),
            DeleteMonitoredItemsRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteMonitoredItemsRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteMonitoredItemsResponse.Codec().asBinaryCodec(),
            "DeleteMonitoredItemsResponse",
            DeleteMonitoredItemsResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteMonitoredItemsResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteMonitoredItemsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteMonitoredItemsResponse"),
            DeleteMonitoredItemsResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteMonitoredItemsResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CreateSubscriptionRequest.Codec().asBinaryCodec(),
            "CreateSubscriptionRequest",
            CreateSubscriptionRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateSubscriptionRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CreateSubscriptionRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CreateSubscriptionRequest"),
            CreateSubscriptionRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateSubscriptionRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CreateSubscriptionResponse.Codec().asBinaryCodec(),
            "CreateSubscriptionResponse",
            CreateSubscriptionResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateSubscriptionResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CreateSubscriptionResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CreateSubscriptionResponse"),
            CreateSubscriptionResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateSubscriptionResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ModifySubscriptionRequest.Codec().asBinaryCodec(),
            "ModifySubscriptionRequest",
            ModifySubscriptionRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModifySubscriptionRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ModifySubscriptionRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ModifySubscriptionRequest"),
            ModifySubscriptionRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModifySubscriptionRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ModifySubscriptionResponse.Codec().asBinaryCodec(),
            "ModifySubscriptionResponse",
            ModifySubscriptionResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModifySubscriptionResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ModifySubscriptionResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ModifySubscriptionResponse"),
            ModifySubscriptionResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModifySubscriptionResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SetPublishingModeRequest.Codec().asBinaryCodec(),
            "SetPublishingModeRequest",
            SetPublishingModeRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetPublishingModeRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SetPublishingModeRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SetPublishingModeRequest"),
            SetPublishingModeRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetPublishingModeRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SetPublishingModeResponse.Codec().asBinaryCodec(),
            "SetPublishingModeResponse",
            SetPublishingModeResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetPublishingModeResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SetPublishingModeResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SetPublishingModeResponse"),
            SetPublishingModeResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetPublishingModeResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new NotificationMessage.Codec().asBinaryCodec(),
            "NotificationMessage",
            NotificationMessage.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NotificationMessage.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new NotificationMessage.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "NotificationMessage"),
            NotificationMessage.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NotificationMessage.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MonitoredItemNotification.Codec().asBinaryCodec(),
            "MonitoredItemNotification",
            MonitoredItemNotification.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemNotification.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MonitoredItemNotification.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoredItemNotification"),
            MonitoredItemNotification.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemNotification.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DataChangeNotification.Codec().asBinaryCodec(),
            "DataChangeNotification",
            DataChangeNotification.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataChangeNotification.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DataChangeNotification.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DataChangeNotification"),
            DataChangeNotification.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataChangeNotification.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new StatusChangeNotification.Codec().asBinaryCodec(),
            "StatusChangeNotification",
            StatusChangeNotification.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            StatusChangeNotification.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new StatusChangeNotification.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "StatusChangeNotification"),
            StatusChangeNotification.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            StatusChangeNotification.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SubscriptionAcknowledgement.Codec().asBinaryCodec(),
            "SubscriptionAcknowledgement",
            SubscriptionAcknowledgement.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SubscriptionAcknowledgement.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SubscriptionAcknowledgement.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SubscriptionAcknowledgement"),
            SubscriptionAcknowledgement.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SubscriptionAcknowledgement.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new PublishRequest.Codec().asBinaryCodec(),
            "PublishRequest",
            PublishRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PublishRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new PublishRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PublishRequest"),
            PublishRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PublishRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new PublishResponse.Codec().asBinaryCodec(),
            "PublishResponse",
            PublishResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PublishResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new PublishResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PublishResponse"),
            PublishResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PublishResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RepublishRequest.Codec().asBinaryCodec(),
            "RepublishRequest",
            RepublishRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RepublishRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RepublishRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RepublishRequest"),
            RepublishRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RepublishRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RepublishResponse.Codec().asBinaryCodec(),
            "RepublishResponse",
            RepublishResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RepublishResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RepublishResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RepublishResponse"),
            RepublishResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RepublishResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new TransferResult.Codec().asBinaryCodec(),
            "TransferResult",
            TransferResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TransferResult.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TransferResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TransferResult"),
            TransferResult.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TransferResult.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new TransferSubscriptionsRequest.Codec().asBinaryCodec(),
            "TransferSubscriptionsRequest",
            TransferSubscriptionsRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TransferSubscriptionsRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TransferSubscriptionsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TransferSubscriptionsRequest"),
            TransferSubscriptionsRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TransferSubscriptionsRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new TransferSubscriptionsResponse.Codec().asBinaryCodec(),
            "TransferSubscriptionsResponse",
            TransferSubscriptionsResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TransferSubscriptionsResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TransferSubscriptionsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TransferSubscriptionsResponse"),
            TransferSubscriptionsResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TransferSubscriptionsResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteSubscriptionsRequest.Codec().asBinaryCodec(),
            "DeleteSubscriptionsRequest",
            DeleteSubscriptionsRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteSubscriptionsRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteSubscriptionsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteSubscriptionsRequest"),
            DeleteSubscriptionsRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteSubscriptionsRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteSubscriptionsResponse.Codec().asBinaryCodec(),
            "DeleteSubscriptionsResponse",
            DeleteSubscriptionsResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteSubscriptionsResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteSubscriptionsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteSubscriptionsResponse"),
            DeleteSubscriptionsResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteSubscriptionsResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EventNotificationList.Codec().asBinaryCodec(),
            "EventNotificationList",
            EventNotificationList.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EventNotificationList.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EventNotificationList.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EventNotificationList"),
            EventNotificationList.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EventNotificationList.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EventFieldList.Codec().asBinaryCodec(),
            "EventFieldList",
            EventFieldList.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EventFieldList.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EventFieldList.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EventFieldList"),
            EventFieldList.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EventFieldList.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new NotificationData.Codec().asBinaryCodec(),
            "NotificationData",
            NotificationData.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NotificationData.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new NotificationData.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "NotificationData"),
            NotificationData.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NotificationData.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ModificationInfo.Codec().asBinaryCodec(),
            "ModificationInfo",
            ModificationInfo.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModificationInfo.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ModificationInfo.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ModificationInfo"),
            ModificationInfo.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModificationInfo.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryModifiedData.Codec().asBinaryCodec(),
            "HistoryModifiedData",
            HistoryModifiedData.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryModifiedData.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryModifiedData.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryModifiedData"),
            HistoryModifiedData.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryModifiedData.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UpdateStructureDataDetails.Codec().asBinaryCodec(),
            "UpdateStructureDataDetails",
            UpdateStructureDataDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UpdateStructureDataDetails.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UpdateStructureDataDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UpdateStructureDataDetails"),
            UpdateStructureDataDetails.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UpdateStructureDataDetails.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new InstanceNode.Codec().asBinaryCodec(),
            "InstanceNode",
            InstanceNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            InstanceNode.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new InstanceNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "InstanceNode"),
            InstanceNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            InstanceNode.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new TypeNode.Codec().asBinaryCodec(),
            "TypeNode",
            TypeNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TypeNode.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TypeNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TypeNode"),
            TypeNode.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TypeNode.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new FindServersOnNetworkRequest.Codec().asBinaryCodec(),
            "FindServersOnNetworkRequest",
            FindServersOnNetworkRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FindServersOnNetworkRequest.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new FindServersOnNetworkRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "FindServersOnNetworkRequest"),
            FindServersOnNetworkRequest.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FindServersOnNetworkRequest.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new FindServersOnNetworkResponse.Codec().asBinaryCodec(),
            "FindServersOnNetworkResponse",
            FindServersOnNetworkResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FindServersOnNetworkResponse.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new FindServersOnNetworkResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "FindServersOnNetworkResponse"),
            FindServersOnNetworkResponse.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FindServersOnNetworkResponse.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RegisterServer2Request.Codec().asBinaryCodec(),
            "RegisterServer2Request",
            RegisterServer2Request.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterServer2Request.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RegisterServer2Request.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RegisterServer2Request"),
            RegisterServer2Request.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterServer2Request.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RegisterServer2Response.Codec().asBinaryCodec(),
            "RegisterServer2Response",
            RegisterServer2Response.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterServer2Response.BINARY_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RegisterServer2Response.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RegisterServer2Response"),
            RegisterServer2Response.TYPE_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterServer2Response.XML_ENCODING_ID.local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
    }

    private static final void initializeEnums(NamespaceTable namespaceTable,
                                              DataTypeDictionary<OpcUaBinaryDataTypeCodec<?>> binaryDictionary,
                                              DataTypeDictionary<OpcUaXmlDataTypeCodec<?>> xmlDictionary) {
        binaryDictionary.registerEnumCodec(
            new NamingRuleType.Codec().asBinaryCodec(),
            "NamingRuleType",
            NamingRuleType.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new NamingRuleType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "NamingRuleType"),
            NamingRuleType.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new IdType.Codec().asBinaryCodec(),
            "IdType",
            IdType.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new IdType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "IdType"),
            IdType.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new NodeClass.Codec().asBinaryCodec(),
            "NodeClass",
            NodeClass.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new NodeClass.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "NodeClass"),
            NodeClass.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new TrustListMasks.Codec().asBinaryCodec(),
            "TrustListMasks",
            TrustListMasks.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new TrustListMasks.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TrustListMasks"),
            TrustListMasks.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new MessageSecurityMode.Codec().asBinaryCodec(),
            "MessageSecurityMode",
            MessageSecurityMode.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new MessageSecurityMode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MessageSecurityMode"),
            MessageSecurityMode.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new UserTokenType.Codec().asBinaryCodec(),
            "UserTokenType",
            UserTokenType.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new UserTokenType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UserTokenType"),
            UserTokenType.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new ApplicationType.Codec().asBinaryCodec(),
            "ApplicationType",
            ApplicationType.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new ApplicationType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ApplicationType"),
            ApplicationType.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new SecurityTokenRequestType.Codec().asBinaryCodec(),
            "SecurityTokenRequestType",
            SecurityTokenRequestType.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new SecurityTokenRequestType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SecurityTokenRequestType"),
            SecurityTokenRequestType.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new NodeAttributesMask.Codec().asBinaryCodec(),
            "NodeAttributesMask",
            NodeAttributesMask.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new NodeAttributesMask.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "NodeAttributesMask"),
            NodeAttributesMask.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new FilterOperator.Codec().asBinaryCodec(),
            "FilterOperator",
            FilterOperator.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new FilterOperator.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "FilterOperator"),
            FilterOperator.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new RedundancySupport.Codec().asBinaryCodec(),
            "RedundancySupport",
            RedundancySupport.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new RedundancySupport.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RedundancySupport"),
            RedundancySupport.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new ServerState.Codec().asBinaryCodec(),
            "ServerState",
            ServerState.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new ServerState.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ServerState"),
            ServerState.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new ExceptionDeviationFormat.Codec().asBinaryCodec(),
            "ExceptionDeviationFormat",
            ExceptionDeviationFormat.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new ExceptionDeviationFormat.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ExceptionDeviationFormat"),
            ExceptionDeviationFormat.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new HistoryUpdateType.Codec().asBinaryCodec(),
            "HistoryUpdateType",
            HistoryUpdateType.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new HistoryUpdateType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryUpdateType"),
            HistoryUpdateType.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new PerformUpdateType.Codec().asBinaryCodec(),
            "PerformUpdateType",
            PerformUpdateType.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new PerformUpdateType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PerformUpdateType"),
            PerformUpdateType.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new OpenFileMode.Codec().asBinaryCodec(),
            "OpenFileMode",
            OpenFileMode.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new OpenFileMode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "OpenFileMode"),
            OpenFileMode.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new AxisScaleEnumeration.Codec().asBinaryCodec(),
            "AxisScaleEnumeration",
            AxisScaleEnumeration.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new AxisScaleEnumeration.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AxisScaleEnumeration"),
            AxisScaleEnumeration.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new BrowseDirection.Codec().asBinaryCodec(),
            "BrowseDirection",
            BrowseDirection.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new BrowseDirection.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowseDirection"),
            BrowseDirection.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new BrowseResultMask.Codec().asBinaryCodec(),
            "BrowseResultMask",
            BrowseResultMask.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new BrowseResultMask.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowseResultMask"),
            BrowseResultMask.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new TimestampsToReturn.Codec().asBinaryCodec(),
            "TimestampsToReturn",
            TimestampsToReturn.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new TimestampsToReturn.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TimestampsToReturn"),
            TimestampsToReturn.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new MonitoringMode.Codec().asBinaryCodec(),
            "MonitoringMode",
            MonitoringMode.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new MonitoringMode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoringMode"),
            MonitoringMode.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new DataChangeTrigger.Codec().asBinaryCodec(),
            "DataChangeTrigger",
            DataChangeTrigger.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new DataChangeTrigger.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DataChangeTrigger"),
            DataChangeTrigger.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new DeadbandType.Codec().asBinaryCodec(),
            "DeadbandType",
            DeadbandType.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new DeadbandType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeadbandType"),
            DeadbandType.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new ModelChangeStructureVerbMask.Codec().asBinaryCodec(),
            "ModelChangeStructureVerbMask",
            ModelChangeStructureVerbMask.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new ModelChangeStructureVerbMask.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ModelChangeStructureVerbMask"),
            ModelChangeStructureVerbMask.getTypeId().local(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
    }
}
