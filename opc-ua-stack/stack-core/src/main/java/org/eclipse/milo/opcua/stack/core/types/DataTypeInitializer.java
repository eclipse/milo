/*
 * Copyright (c) 2021 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrokerTransportQualityOfService;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DataChangeTrigger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DataSetOrderingType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DeadbandType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DiagnosticsLevel;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ExceptionDeviationFormat;
import org.eclipse.milo.opcua.stack.core.types.enumerated.FilterOperator;
import org.eclipse.milo.opcua.stack.core.types.enumerated.HistoryUpdateType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdentityCriteriaType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ModelChangeStructureVerbMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NamingRuleType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeAttributesMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.OpenFileMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.OverrideValueHandling;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PubSubDiagnosticsCounterClassification;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PubSubState;
import org.eclipse.milo.opcua.stack.core.types.enumerated.RedundancySupport;
import org.eclipse.milo.opcua.stack.core.types.enumerated.SecurityTokenRequestType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.AdditionalParametersType;
import org.eclipse.milo.opcua.stack.core.types.structured.AggregateConfiguration;
import org.eclipse.milo.opcua.stack.core.types.structured.AggregateFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.AggregateFilterResult;
import org.eclipse.milo.opcua.stack.core.types.structured.AliasNameDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.Annotation;
import org.eclipse.milo.opcua.stack.core.types.structured.AnonymousIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.types.structured.AttributeOperand;
import org.eclipse.milo.opcua.stack.core.types.structured.AxisInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.BrokerConnectionTransportDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.BrokerDataSetReaderTransportDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.BrokerDataSetWriterTransportDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.BrokerWriterGroupTransportDataType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.ConfigurationVersionDataType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.CurrencyUnitType;
import org.eclipse.milo.opcua.stack.core.types.structured.DataChangeFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.DataChangeNotification;
import org.eclipse.milo.opcua.stack.core.types.structured.DataSetMetaDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.DataSetReaderDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.DataSetWriterDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.DataTypeAttributes;
import org.eclipse.milo.opcua.stack.core.types.structured.DataTypeNode;
import org.eclipse.milo.opcua.stack.core.types.structured.DatagramConnectionTransportDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.DatagramWriterGroupTransportDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.DecimalDataType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointType;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointUrlListDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumField;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumValueType;
import org.eclipse.milo.opcua.stack.core.types.structured.EphemeralKeyType;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFieldList;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilterResult;
import org.eclipse.milo.opcua.stack.core.types.structured.EventNotificationList;
import org.eclipse.milo.opcua.stack.core.types.structured.FieldMetaData;
import org.eclipse.milo.opcua.stack.core.types.structured.FieldTargetDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.FilterOperand;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersOnNetworkRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersOnNetworkResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.GenericAttributeValue;
import org.eclipse.milo.opcua.stack.core.types.structured.GenericAttributes;
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
import org.eclipse.milo.opcua.stack.core.types.structured.IdentityMappingRuleType;
import org.eclipse.milo.opcua.stack.core.types.structured.InstanceNode;
import org.eclipse.milo.opcua.stack.core.types.structured.IssuedIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.JsonDataSetReaderMessageDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.JsonDataSetWriterMessageDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.JsonWriterGroupMessageDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.KeyValuePair;
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
import org.eclipse.milo.opcua.stack.core.types.structured.NetworkAddressUrlDataType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.ProgramDiagnostic2DataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ProgramDiagnosticDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.PubSubConfigurationDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.PubSubConnectionDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishedDataItemsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishedDataSetDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishedEventsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishedVariableDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryDataDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryDataSet;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryFirstRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryFirstResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryNextRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryNextResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;
import org.eclipse.milo.opcua.stack.core.types.structured.RationalNumber;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadAnnotationDataDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadAtTimeDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadEventDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadProcessedDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRawModifiedDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ReaderGroupDataType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;
import org.eclipse.milo.opcua.stack.core.types.structured.SamplingIntervalDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SemanticChangeStructureDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerDiagnosticsSummaryDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerOnNetwork;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceCounterDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionlessInvokeRequestType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionlessInvokeResponseType;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.SimpleAttributeOperand;
import org.eclipse.milo.opcua.stack.core.types.structured.SimpleTypeDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.StatusChangeNotification;
import org.eclipse.milo.opcua.stack.core.types.structured.StatusResult;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureField;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscribedDataSetMirrorDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionAcknowledgement;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.TargetVariablesDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ThreeDCartesianCoordinates;
import org.eclipse.milo.opcua.stack.core.types.structured.ThreeDFrame;
import org.eclipse.milo.opcua.stack.core.types.structured.ThreeDOrientation;
import org.eclipse.milo.opcua.stack.core.types.structured.ThreeDVector;
import org.eclipse.milo.opcua.stack.core.types.structured.TimeZoneDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferResult;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.TrustListDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.TypeNode;
import org.eclipse.milo.opcua.stack.core.types.structured.UABinaryFileDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.UadpDataSetReaderMessageDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.UadpDataSetWriterMessageDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.UadpWriterGroupMessageDataType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.WriterGroupDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.X509IdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.XVType;

class DataTypeInitializer {
    private static final String NAMESPACE_URI = "http://opcfoundation.org/UA/";

    private static final String BINARY_DICTIONARY_URI = "http://opcfoundation.org/UA/";

    private static final String XML_DICTIONARY_URI = "http://opcfoundation.org/UA/2008/02/Types.xsd";

    @SuppressWarnings("unchecked")
    public static void initialize(NamespaceTable namespaceTable, DataTypeManager dataTypeManager) {
        DataTypeDictionary<OpcUaBinaryDataTypeCodec<?>> binaryDictionary =
            (DataTypeDictionary<OpcUaBinaryDataTypeCodec<?>>)
                dataTypeManager.getDataTypeDictionary(BINARY_DICTIONARY_URI);
        if (binaryDictionary == null) {
            binaryDictionary = new OpcUaBinaryDataTypeDictionary(BINARY_DICTIONARY_URI);
        }
        DataTypeDictionary<OpcUaXmlDataTypeCodec<?>> xmlDictionary =
            (DataTypeDictionary<OpcUaXmlDataTypeCodec<?>>)
                dataTypeManager.getDataTypeDictionary(XML_DICTIONARY_URI);
        if (xmlDictionary == null) {
            xmlDictionary = new OpcUaXmlDataTypeDictionary(XML_DICTIONARY_URI);
        }
        initialize(namespaceTable, dataTypeManager, binaryDictionary, xmlDictionary);
    }

    public static void initialize(NamespaceTable namespaceTable, DataTypeManager dataTypeManager,
                                  DataTypeDictionary<OpcUaBinaryDataTypeCodec<?>> binaryDictionary,
                                  DataTypeDictionary<OpcUaXmlDataTypeCodec<?>> xmlDictionary) {
        initializeEnums(namespaceTable, binaryDictionary, xmlDictionary);
        initializeStructs(namespaceTable, binaryDictionary, xmlDictionary);
        dataTypeManager.registerTypeDictionary(binaryDictionary);
        dataTypeManager.registerTypeDictionary(xmlDictionary);
    }

    private static void initializeEnums(NamespaceTable namespaceTable,
                                        DataTypeDictionary<OpcUaBinaryDataTypeCodec<?>> binaryDictionary,
                                        DataTypeDictionary<OpcUaXmlDataTypeCodec<?>> xmlDictionary) {
        binaryDictionary.registerEnumCodec(
            new StructureType.Codec().asBinaryCodec(),
            "StructureType",
            StructureType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new StructureType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "StructureType"),
            StructureType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new NamingRuleType.Codec().asBinaryCodec(),
            "NamingRuleType",
            NamingRuleType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new NamingRuleType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "NamingRuleType"),
            NamingRuleType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new IdType.Codec().asBinaryCodec(),
            "IdType",
            IdType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new IdType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "IdType"),
            IdType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new NodeClass.Codec().asBinaryCodec(),
            "NodeClass",
            NodeClass.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new NodeClass.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "NodeClass"),
            NodeClass.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new MessageSecurityMode.Codec().asBinaryCodec(),
            "MessageSecurityMode",
            MessageSecurityMode.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new MessageSecurityMode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "MessageSecurityMode"),
            MessageSecurityMode.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new UserTokenType.Codec().asBinaryCodec(),
            "UserTokenType",
            UserTokenType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new UserTokenType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "UserTokenType"),
            UserTokenType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new ApplicationType.Codec().asBinaryCodec(),
            "ApplicationType",
            ApplicationType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new ApplicationType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ApplicationType"),
            ApplicationType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new SecurityTokenRequestType.Codec().asBinaryCodec(),
            "SecurityTokenRequestType",
            SecurityTokenRequestType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new SecurityTokenRequestType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SecurityTokenRequestType"),
            SecurityTokenRequestType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new NodeAttributesMask.Codec().asBinaryCodec(),
            "NodeAttributesMask",
            NodeAttributesMask.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new NodeAttributesMask.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "NodeAttributesMask"),
            NodeAttributesMask.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new BrowseDirection.Codec().asBinaryCodec(),
            "BrowseDirection",
            BrowseDirection.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new BrowseDirection.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BrowseDirection"),
            BrowseDirection.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new BrowseResultMask.Codec().asBinaryCodec(),
            "BrowseResultMask",
            BrowseResultMask.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new BrowseResultMask.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BrowseResultMask"),
            BrowseResultMask.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new FilterOperator.Codec().asBinaryCodec(),
            "FilterOperator",
            FilterOperator.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new FilterOperator.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "FilterOperator"),
            FilterOperator.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new TimestampsToReturn.Codec().asBinaryCodec(),
            "TimestampsToReturn",
            TimestampsToReturn.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new TimestampsToReturn.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "TimestampsToReturn"),
            TimestampsToReturn.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new MonitoringMode.Codec().asBinaryCodec(),
            "MonitoringMode",
            MonitoringMode.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new MonitoringMode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "MonitoringMode"),
            MonitoringMode.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new DataChangeTrigger.Codec().asBinaryCodec(),
            "DataChangeTrigger",
            DataChangeTrigger.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new DataChangeTrigger.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DataChangeTrigger"),
            DataChangeTrigger.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new DeadbandType.Codec().asBinaryCodec(),
            "DeadbandType",
            DeadbandType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new DeadbandType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DeadbandType"),
            DeadbandType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new RedundancySupport.Codec().asBinaryCodec(),
            "RedundancySupport",
            RedundancySupport.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new RedundancySupport.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "RedundancySupport"),
            RedundancySupport.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new ServerState.Codec().asBinaryCodec(),
            "ServerState",
            ServerState.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new ServerState.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ServerState"),
            ServerState.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new ExceptionDeviationFormat.Codec().asBinaryCodec(),
            "ExceptionDeviationFormat",
            ExceptionDeviationFormat.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new ExceptionDeviationFormat.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ExceptionDeviationFormat"),
            ExceptionDeviationFormat.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new HistoryUpdateType.Codec().asBinaryCodec(),
            "HistoryUpdateType",
            HistoryUpdateType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new HistoryUpdateType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "HistoryUpdateType"),
            HistoryUpdateType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new PerformUpdateType.Codec().asBinaryCodec(),
            "PerformUpdateType",
            PerformUpdateType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new PerformUpdateType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "PerformUpdateType"),
            PerformUpdateType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new DiagnosticsLevel.Codec().asBinaryCodec(),
            "DiagnosticsLevel",
            DiagnosticsLevel.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new DiagnosticsLevel.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DiagnosticsLevel"),
            DiagnosticsLevel.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new PubSubDiagnosticsCounterClassification.Codec().asBinaryCodec(),
            "PubSubDiagnosticsCounterClassification",
            PubSubDiagnosticsCounterClassification.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new PubSubDiagnosticsCounterClassification.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "PubSubDiagnosticsCounterClassification"),
            PubSubDiagnosticsCounterClassification.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new OpenFileMode.Codec().asBinaryCodec(),
            "OpenFileMode",
            OpenFileMode.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new OpenFileMode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "OpenFileMode"),
            OpenFileMode.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new ModelChangeStructureVerbMask.Codec().asBinaryCodec(),
            "ModelChangeStructureVerbMask",
            ModelChangeStructureVerbMask.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new ModelChangeStructureVerbMask.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ModelChangeStructureVerbMask"),
            ModelChangeStructureVerbMask.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new AxisScaleEnumeration.Codec().asBinaryCodec(),
            "AxisScaleEnumeration",
            AxisScaleEnumeration.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new AxisScaleEnumeration.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "AxisScaleEnumeration"),
            AxisScaleEnumeration.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new DataSetOrderingType.Codec().asBinaryCodec(),
            "DataSetOrderingType",
            DataSetOrderingType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new DataSetOrderingType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DataSetOrderingType"),
            DataSetOrderingType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new TrustListMasks.Codec().asBinaryCodec(),
            "TrustListMasks",
            TrustListMasks.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new TrustListMasks.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "TrustListMasks"),
            TrustListMasks.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new PubSubState.Codec().asBinaryCodec(),
            "PubSubState",
            PubSubState.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new PubSubState.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "PubSubState"),
            PubSubState.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new BrokerTransportQualityOfService.Codec().asBinaryCodec(),
            "BrokerTransportQualityOfService",
            BrokerTransportQualityOfService.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new BrokerTransportQualityOfService.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BrokerTransportQualityOfService"),
            BrokerTransportQualityOfService.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new IdentityCriteriaType.Codec().asBinaryCodec(),
            "IdentityCriteriaType",
            IdentityCriteriaType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new IdentityCriteriaType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "IdentityCriteriaType"),
            IdentityCriteriaType.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerEnumCodec(
            new OverrideValueHandling.Codec().asBinaryCodec(),
            "OverrideValueHandling",
            OverrideValueHandling.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerEnumCodec(
            new OverrideValueHandling.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "OverrideValueHandling"),
            OverrideValueHandling.getTypeId().toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
    }

    private static void initializeStructs(NamespaceTable namespaceTable,
                                          DataTypeDictionary<OpcUaBinaryDataTypeCodec<?>> binaryDictionary,
                                          DataTypeDictionary<OpcUaXmlDataTypeCodec<?>> xmlDictionary) {
        binaryDictionary.registerStructCodec(
            new RolePermissionType.Codec().asBinaryCodec(),
            "RolePermissionType",
            RolePermissionType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RolePermissionType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RolePermissionType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "RolePermissionType"),
            RolePermissionType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RolePermissionType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new StructureDefinition.Codec().asBinaryCodec(),
            "StructureDefinition",
            StructureDefinition.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            StructureDefinition.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new StructureDefinition.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "StructureDefinition"),
            StructureDefinition.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            StructureDefinition.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EnumDefinition.Codec().asBinaryCodec(),
            "EnumDefinition",
            EnumDefinition.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EnumDefinition.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EnumDefinition.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "EnumDefinition"),
            EnumDefinition.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EnumDefinition.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new StructureField.Codec().asBinaryCodec(),
            "StructureField",
            StructureField.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            StructureField.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new StructureField.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "StructureField"),
            StructureField.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            StructureField.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EnumField.Codec().asBinaryCodec(),
            "EnumField",
            EnumField.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EnumField.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EnumField.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "EnumField"),
            EnumField.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EnumField.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new Node.Codec().asBinaryCodec(),
            "Node",
            Node.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            Node.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new Node.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "Node"),
            Node.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            Node.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ObjectNode.Codec().asBinaryCodec(),
            "ObjectNode",
            ObjectNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ObjectNode.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ObjectNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ObjectNode"),
            ObjectNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ObjectNode.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ObjectTypeNode.Codec().asBinaryCodec(),
            "ObjectTypeNode",
            ObjectTypeNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ObjectTypeNode.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ObjectTypeNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ObjectTypeNode"),
            ObjectTypeNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ObjectTypeNode.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new VariableNode.Codec().asBinaryCodec(),
            "VariableNode",
            VariableNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            VariableNode.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new VariableNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "VariableNode"),
            VariableNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            VariableNode.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new VariableTypeNode.Codec().asBinaryCodec(),
            "VariableTypeNode",
            VariableTypeNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            VariableTypeNode.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new VariableTypeNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "VariableTypeNode"),
            VariableTypeNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            VariableTypeNode.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReferenceTypeNode.Codec().asBinaryCodec(),
            "ReferenceTypeNode",
            ReferenceTypeNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReferenceTypeNode.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReferenceTypeNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ReferenceTypeNode"),
            ReferenceTypeNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReferenceTypeNode.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MethodNode.Codec().asBinaryCodec(),
            "MethodNode",
            MethodNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MethodNode.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MethodNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "MethodNode"),
            MethodNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MethodNode.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ViewNode.Codec().asBinaryCodec(),
            "ViewNode",
            ViewNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ViewNode.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ViewNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ViewNode"),
            ViewNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ViewNode.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DataTypeNode.Codec().asBinaryCodec(),
            "DataTypeNode",
            DataTypeNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataTypeNode.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DataTypeNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DataTypeNode"),
            DataTypeNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataTypeNode.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReferenceNode.Codec().asBinaryCodec(),
            "ReferenceNode",
            ReferenceNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReferenceNode.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReferenceNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ReferenceNode"),
            ReferenceNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReferenceNode.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new Argument.Codec().asBinaryCodec(),
            "Argument",
            Argument.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            Argument.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new Argument.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "Argument"),
            Argument.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            Argument.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new StatusResult.Codec().asBinaryCodec(),
            "StatusResult",
            StatusResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            StatusResult.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new StatusResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "StatusResult"),
            StatusResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            StatusResult.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UserTokenPolicy.Codec().asBinaryCodec(),
            "UserTokenPolicy",
            UserTokenPolicy.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UserTokenPolicy.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UserTokenPolicy.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "UserTokenPolicy"),
            UserTokenPolicy.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UserTokenPolicy.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ApplicationDescription.Codec().asBinaryCodec(),
            "ApplicationDescription",
            ApplicationDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ApplicationDescription.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ApplicationDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ApplicationDescription"),
            ApplicationDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ApplicationDescription.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EndpointDescription.Codec().asBinaryCodec(),
            "EndpointDescription",
            EndpointDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EndpointDescription.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EndpointDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "EndpointDescription"),
            EndpointDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EndpointDescription.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AnonymousIdentityToken.Codec().asBinaryCodec(),
            "AnonymousIdentityToken",
            AnonymousIdentityToken.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AnonymousIdentityToken.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AnonymousIdentityToken.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "AnonymousIdentityToken"),
            AnonymousIdentityToken.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AnonymousIdentityToken.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UserNameIdentityToken.Codec().asBinaryCodec(),
            "UserNameIdentityToken",
            UserNameIdentityToken.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UserNameIdentityToken.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UserNameIdentityToken.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "UserNameIdentityToken"),
            UserNameIdentityToken.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UserNameIdentityToken.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new X509IdentityToken.Codec().asBinaryCodec(),
            "X509IdentityToken",
            X509IdentityToken.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            X509IdentityToken.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new X509IdentityToken.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "X509IdentityToken"),
            X509IdentityToken.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            X509IdentityToken.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EndpointConfiguration.Codec().asBinaryCodec(),
            "EndpointConfiguration",
            EndpointConfiguration.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EndpointConfiguration.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EndpointConfiguration.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "EndpointConfiguration"),
            EndpointConfiguration.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EndpointConfiguration.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BuildInfo.Codec().asBinaryCodec(),
            "BuildInfo",
            BuildInfo.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BuildInfo.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BuildInfo.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BuildInfo"),
            BuildInfo.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BuildInfo.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SignedSoftwareCertificate.Codec().asBinaryCodec(),
            "SignedSoftwareCertificate",
            SignedSoftwareCertificate.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SignedSoftwareCertificate.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SignedSoftwareCertificate.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SignedSoftwareCertificate"),
            SignedSoftwareCertificate.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SignedSoftwareCertificate.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new NodeAttributes.Codec().asBinaryCodec(),
            "NodeAttributes",
            NodeAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NodeAttributes.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new NodeAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "NodeAttributes"),
            NodeAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NodeAttributes.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ObjectAttributes.Codec().asBinaryCodec(),
            "ObjectAttributes",
            ObjectAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ObjectAttributes.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ObjectAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ObjectAttributes"),
            ObjectAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ObjectAttributes.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new VariableAttributes.Codec().asBinaryCodec(),
            "VariableAttributes",
            VariableAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            VariableAttributes.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new VariableAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "VariableAttributes"),
            VariableAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            VariableAttributes.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MethodAttributes.Codec().asBinaryCodec(),
            "MethodAttributes",
            MethodAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MethodAttributes.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MethodAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "MethodAttributes"),
            MethodAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MethodAttributes.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ObjectTypeAttributes.Codec().asBinaryCodec(),
            "ObjectTypeAttributes",
            ObjectTypeAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ObjectTypeAttributes.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ObjectTypeAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ObjectTypeAttributes"),
            ObjectTypeAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ObjectTypeAttributes.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new VariableTypeAttributes.Codec().asBinaryCodec(),
            "VariableTypeAttributes",
            VariableTypeAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            VariableTypeAttributes.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new VariableTypeAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "VariableTypeAttributes"),
            VariableTypeAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            VariableTypeAttributes.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReferenceTypeAttributes.Codec().asBinaryCodec(),
            "ReferenceTypeAttributes",
            ReferenceTypeAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReferenceTypeAttributes.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReferenceTypeAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ReferenceTypeAttributes"),
            ReferenceTypeAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReferenceTypeAttributes.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DataTypeAttributes.Codec().asBinaryCodec(),
            "DataTypeAttributes",
            DataTypeAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataTypeAttributes.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DataTypeAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DataTypeAttributes"),
            DataTypeAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataTypeAttributes.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ViewAttributes.Codec().asBinaryCodec(),
            "ViewAttributes",
            ViewAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ViewAttributes.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ViewAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ViewAttributes"),
            ViewAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ViewAttributes.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AddNodesItem.Codec().asBinaryCodec(),
            "AddNodesItem",
            AddNodesItem.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddNodesItem.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AddNodesItem.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "AddNodesItem"),
            AddNodesItem.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddNodesItem.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AddReferencesItem.Codec().asBinaryCodec(),
            "AddReferencesItem",
            AddReferencesItem.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddReferencesItem.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AddReferencesItem.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "AddReferencesItem"),
            AddReferencesItem.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddReferencesItem.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteNodesItem.Codec().asBinaryCodec(),
            "DeleteNodesItem",
            DeleteNodesItem.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteNodesItem.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteNodesItem.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DeleteNodesItem"),
            DeleteNodesItem.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteNodesItem.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteReferencesItem.Codec().asBinaryCodec(),
            "DeleteReferencesItem",
            DeleteReferencesItem.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteReferencesItem.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteReferencesItem.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DeleteReferencesItem"),
            DeleteReferencesItem.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteReferencesItem.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RequestHeader.Codec().asBinaryCodec(),
            "RequestHeader",
            RequestHeader.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RequestHeader.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RequestHeader.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "RequestHeader"),
            RequestHeader.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RequestHeader.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ResponseHeader.Codec().asBinaryCodec(),
            "ResponseHeader",
            ResponseHeader.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ResponseHeader.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ResponseHeader.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ResponseHeader"),
            ResponseHeader.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ResponseHeader.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ServiceFault.Codec().asBinaryCodec(),
            "ServiceFault",
            ServiceFault.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServiceFault.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ServiceFault.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ServiceFault"),
            ServiceFault.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServiceFault.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new FindServersRequest.Codec().asBinaryCodec(),
            "FindServersRequest",
            FindServersRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FindServersRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new FindServersRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "FindServersRequest"),
            FindServersRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FindServersRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new FindServersResponse.Codec().asBinaryCodec(),
            "FindServersResponse",
            FindServersResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FindServersResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new FindServersResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "FindServersResponse"),
            FindServersResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FindServersResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new GetEndpointsRequest.Codec().asBinaryCodec(),
            "GetEndpointsRequest",
            GetEndpointsRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            GetEndpointsRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new GetEndpointsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "GetEndpointsRequest"),
            GetEndpointsRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            GetEndpointsRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new GetEndpointsResponse.Codec().asBinaryCodec(),
            "GetEndpointsResponse",
            GetEndpointsResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            GetEndpointsResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new GetEndpointsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "GetEndpointsResponse"),
            GetEndpointsResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            GetEndpointsResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RegisteredServer.Codec().asBinaryCodec(),
            "RegisteredServer",
            RegisteredServer.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisteredServer.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RegisteredServer.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "RegisteredServer"),
            RegisteredServer.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisteredServer.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RegisterServerRequest.Codec().asBinaryCodec(),
            "RegisterServerRequest",
            RegisterServerRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterServerRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RegisterServerRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "RegisterServerRequest"),
            RegisterServerRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterServerRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RegisterServerResponse.Codec().asBinaryCodec(),
            "RegisterServerResponse",
            RegisterServerResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterServerResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RegisterServerResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "RegisterServerResponse"),
            RegisterServerResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterServerResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ChannelSecurityToken.Codec().asBinaryCodec(),
            "ChannelSecurityToken",
            ChannelSecurityToken.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ChannelSecurityToken.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ChannelSecurityToken.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ChannelSecurityToken"),
            ChannelSecurityToken.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ChannelSecurityToken.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new OpenSecureChannelRequest.Codec().asBinaryCodec(),
            "OpenSecureChannelRequest",
            OpenSecureChannelRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            OpenSecureChannelRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new OpenSecureChannelRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "OpenSecureChannelRequest"),
            OpenSecureChannelRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            OpenSecureChannelRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new OpenSecureChannelResponse.Codec().asBinaryCodec(),
            "OpenSecureChannelResponse",
            OpenSecureChannelResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            OpenSecureChannelResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new OpenSecureChannelResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "OpenSecureChannelResponse"),
            OpenSecureChannelResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            OpenSecureChannelResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CloseSecureChannelRequest.Codec().asBinaryCodec(),
            "CloseSecureChannelRequest",
            CloseSecureChannelRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CloseSecureChannelRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CloseSecureChannelRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CloseSecureChannelRequest"),
            CloseSecureChannelRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CloseSecureChannelRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CloseSecureChannelResponse.Codec().asBinaryCodec(),
            "CloseSecureChannelResponse",
            CloseSecureChannelResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CloseSecureChannelResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CloseSecureChannelResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CloseSecureChannelResponse"),
            CloseSecureChannelResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CloseSecureChannelResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SignatureData.Codec().asBinaryCodec(),
            "SignatureData",
            SignatureData.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SignatureData.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SignatureData.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SignatureData"),
            SignatureData.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SignatureData.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CreateSessionRequest.Codec().asBinaryCodec(),
            "CreateSessionRequest",
            CreateSessionRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateSessionRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CreateSessionRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CreateSessionRequest"),
            CreateSessionRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateSessionRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CreateSessionResponse.Codec().asBinaryCodec(),
            "CreateSessionResponse",
            CreateSessionResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateSessionResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CreateSessionResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CreateSessionResponse"),
            CreateSessionResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateSessionResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ActivateSessionRequest.Codec().asBinaryCodec(),
            "ActivateSessionRequest",
            ActivateSessionRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ActivateSessionRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ActivateSessionRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ActivateSessionRequest"),
            ActivateSessionRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ActivateSessionRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ActivateSessionResponse.Codec().asBinaryCodec(),
            "ActivateSessionResponse",
            ActivateSessionResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ActivateSessionResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ActivateSessionResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ActivateSessionResponse"),
            ActivateSessionResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ActivateSessionResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CloseSessionRequest.Codec().asBinaryCodec(),
            "CloseSessionRequest",
            CloseSessionRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CloseSessionRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CloseSessionRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CloseSessionRequest"),
            CloseSessionRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CloseSessionRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CloseSessionResponse.Codec().asBinaryCodec(),
            "CloseSessionResponse",
            CloseSessionResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CloseSessionResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CloseSessionResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CloseSessionResponse"),
            CloseSessionResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CloseSessionResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CancelRequest.Codec().asBinaryCodec(),
            "CancelRequest",
            CancelRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CancelRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CancelRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CancelRequest"),
            CancelRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CancelRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CancelResponse.Codec().asBinaryCodec(),
            "CancelResponse",
            CancelResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CancelResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CancelResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CancelResponse"),
            CancelResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CancelResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AddNodesResult.Codec().asBinaryCodec(),
            "AddNodesResult",
            AddNodesResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddNodesResult.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AddNodesResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "AddNodesResult"),
            AddNodesResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddNodesResult.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AddNodesRequest.Codec().asBinaryCodec(),
            "AddNodesRequest",
            AddNodesRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddNodesRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AddNodesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "AddNodesRequest"),
            AddNodesRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddNodesRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AddNodesResponse.Codec().asBinaryCodec(),
            "AddNodesResponse",
            AddNodesResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddNodesResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AddNodesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "AddNodesResponse"),
            AddNodesResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddNodesResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AddReferencesRequest.Codec().asBinaryCodec(),
            "AddReferencesRequest",
            AddReferencesRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddReferencesRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AddReferencesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "AddReferencesRequest"),
            AddReferencesRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddReferencesRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AddReferencesResponse.Codec().asBinaryCodec(),
            "AddReferencesResponse",
            AddReferencesResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddReferencesResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AddReferencesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "AddReferencesResponse"),
            AddReferencesResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AddReferencesResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteNodesRequest.Codec().asBinaryCodec(),
            "DeleteNodesRequest",
            DeleteNodesRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteNodesRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteNodesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DeleteNodesRequest"),
            DeleteNodesRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteNodesRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteNodesResponse.Codec().asBinaryCodec(),
            "DeleteNodesResponse",
            DeleteNodesResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteNodesResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteNodesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DeleteNodesResponse"),
            DeleteNodesResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteNodesResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteReferencesRequest.Codec().asBinaryCodec(),
            "DeleteReferencesRequest",
            DeleteReferencesRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteReferencesRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteReferencesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DeleteReferencesRequest"),
            DeleteReferencesRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteReferencesRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteReferencesResponse.Codec().asBinaryCodec(),
            "DeleteReferencesResponse",
            DeleteReferencesResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteReferencesResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteReferencesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DeleteReferencesResponse"),
            DeleteReferencesResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteReferencesResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ViewDescription.Codec().asBinaryCodec(),
            "ViewDescription",
            ViewDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ViewDescription.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ViewDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ViewDescription"),
            ViewDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ViewDescription.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowseDescription.Codec().asBinaryCodec(),
            "BrowseDescription",
            BrowseDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseDescription.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowseDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BrowseDescription"),
            BrowseDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseDescription.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReferenceDescription.Codec().asBinaryCodec(),
            "ReferenceDescription",
            ReferenceDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReferenceDescription.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReferenceDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ReferenceDescription"),
            ReferenceDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReferenceDescription.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowseResult.Codec().asBinaryCodec(),
            "BrowseResult",
            BrowseResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseResult.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowseResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BrowseResult"),
            BrowseResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseResult.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowseRequest.Codec().asBinaryCodec(),
            "BrowseRequest",
            BrowseRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowseRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BrowseRequest"),
            BrowseRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowseResponse.Codec().asBinaryCodec(),
            "BrowseResponse",
            BrowseResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowseResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BrowseResponse"),
            BrowseResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowseNextRequest.Codec().asBinaryCodec(),
            "BrowseNextRequest",
            BrowseNextRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseNextRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowseNextRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BrowseNextRequest"),
            BrowseNextRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseNextRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowseNextResponse.Codec().asBinaryCodec(),
            "BrowseNextResponse",
            BrowseNextResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseNextResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowseNextResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BrowseNextResponse"),
            BrowseNextResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowseNextResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RelativePathElement.Codec().asBinaryCodec(),
            "RelativePathElement",
            RelativePathElement.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RelativePathElement.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RelativePathElement.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "RelativePathElement"),
            RelativePathElement.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RelativePathElement.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RelativePath.Codec().asBinaryCodec(),
            "RelativePath",
            RelativePath.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RelativePath.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RelativePath.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "RelativePath"),
            RelativePath.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RelativePath.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowsePath.Codec().asBinaryCodec(),
            "BrowsePath",
            BrowsePath.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowsePath.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowsePath.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BrowsePath"),
            BrowsePath.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowsePath.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowsePathTarget.Codec().asBinaryCodec(),
            "BrowsePathTarget",
            BrowsePathTarget.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowsePathTarget.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowsePathTarget.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BrowsePathTarget"),
            BrowsePathTarget.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowsePathTarget.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrowsePathResult.Codec().asBinaryCodec(),
            "BrowsePathResult",
            BrowsePathResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowsePathResult.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrowsePathResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BrowsePathResult"),
            BrowsePathResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrowsePathResult.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new TranslateBrowsePathsToNodeIdsRequest.Codec().asBinaryCodec(),
            "TranslateBrowsePathsToNodeIdsRequest",
            TranslateBrowsePathsToNodeIdsRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TranslateBrowsePathsToNodeIdsRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TranslateBrowsePathsToNodeIdsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "TranslateBrowsePathsToNodeIdsRequest"),
            TranslateBrowsePathsToNodeIdsRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TranslateBrowsePathsToNodeIdsRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new TranslateBrowsePathsToNodeIdsResponse.Codec().asBinaryCodec(),
            "TranslateBrowsePathsToNodeIdsResponse",
            TranslateBrowsePathsToNodeIdsResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TranslateBrowsePathsToNodeIdsResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TranslateBrowsePathsToNodeIdsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "TranslateBrowsePathsToNodeIdsResponse"),
            TranslateBrowsePathsToNodeIdsResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TranslateBrowsePathsToNodeIdsResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RegisterNodesRequest.Codec().asBinaryCodec(),
            "RegisterNodesRequest",
            RegisterNodesRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterNodesRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RegisterNodesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "RegisterNodesRequest"),
            RegisterNodesRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterNodesRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RegisterNodesResponse.Codec().asBinaryCodec(),
            "RegisterNodesResponse",
            RegisterNodesResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterNodesResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RegisterNodesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "RegisterNodesResponse"),
            RegisterNodesResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterNodesResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UnregisterNodesRequest.Codec().asBinaryCodec(),
            "UnregisterNodesRequest",
            UnregisterNodesRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UnregisterNodesRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UnregisterNodesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "UnregisterNodesRequest"),
            UnregisterNodesRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UnregisterNodesRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UnregisterNodesResponse.Codec().asBinaryCodec(),
            "UnregisterNodesResponse",
            UnregisterNodesResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UnregisterNodesResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UnregisterNodesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "UnregisterNodesResponse"),
            UnregisterNodesResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UnregisterNodesResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new QueryDataDescription.Codec().asBinaryCodec(),
            "QueryDataDescription",
            QueryDataDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryDataDescription.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new QueryDataDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "QueryDataDescription"),
            QueryDataDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryDataDescription.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new NodeTypeDescription.Codec().asBinaryCodec(),
            "NodeTypeDescription",
            NodeTypeDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NodeTypeDescription.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new NodeTypeDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "NodeTypeDescription"),
            NodeTypeDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NodeTypeDescription.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new QueryDataSet.Codec().asBinaryCodec(),
            "QueryDataSet",
            QueryDataSet.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryDataSet.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new QueryDataSet.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "QueryDataSet"),
            QueryDataSet.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryDataSet.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new NodeReference.Codec().asBinaryCodec(),
            "NodeReference",
            NodeReference.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NodeReference.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new NodeReference.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "NodeReference"),
            NodeReference.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NodeReference.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ContentFilterElement.Codec().asBinaryCodec(),
            "ContentFilterElement",
            ContentFilterElement.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ContentFilterElement.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ContentFilterElement.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ContentFilterElement"),
            ContentFilterElement.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ContentFilterElement.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ContentFilter.Codec().asBinaryCodec(),
            "ContentFilter",
            ContentFilter.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ContentFilter.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ContentFilter.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ContentFilter"),
            ContentFilter.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ContentFilter.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new FilterOperand.Codec().asBinaryCodec(),
            "FilterOperand",
            FilterOperand.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FilterOperand.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new FilterOperand.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "FilterOperand"),
            FilterOperand.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FilterOperand.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ElementOperand.Codec().asBinaryCodec(),
            "ElementOperand",
            ElementOperand.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ElementOperand.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ElementOperand.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ElementOperand"),
            ElementOperand.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ElementOperand.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new LiteralOperand.Codec().asBinaryCodec(),
            "LiteralOperand",
            LiteralOperand.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            LiteralOperand.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new LiteralOperand.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "LiteralOperand"),
            LiteralOperand.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            LiteralOperand.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AttributeOperand.Codec().asBinaryCodec(),
            "AttributeOperand",
            AttributeOperand.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AttributeOperand.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AttributeOperand.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "AttributeOperand"),
            AttributeOperand.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AttributeOperand.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SimpleAttributeOperand.Codec().asBinaryCodec(),
            "SimpleAttributeOperand",
            SimpleAttributeOperand.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SimpleAttributeOperand.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SimpleAttributeOperand.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SimpleAttributeOperand"),
            SimpleAttributeOperand.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SimpleAttributeOperand.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ContentFilterElementResult.Codec().asBinaryCodec(),
            "ContentFilterElementResult",
            ContentFilterElementResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ContentFilterElementResult.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ContentFilterElementResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ContentFilterElementResult"),
            ContentFilterElementResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ContentFilterElementResult.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ContentFilterResult.Codec().asBinaryCodec(),
            "ContentFilterResult",
            ContentFilterResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ContentFilterResult.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ContentFilterResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ContentFilterResult"),
            ContentFilterResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ContentFilterResult.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ParsingResult.Codec().asBinaryCodec(),
            "ParsingResult",
            ParsingResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ParsingResult.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ParsingResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ParsingResult"),
            ParsingResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ParsingResult.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new QueryFirstRequest.Codec().asBinaryCodec(),
            "QueryFirstRequest",
            QueryFirstRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryFirstRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new QueryFirstRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "QueryFirstRequest"),
            QueryFirstRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryFirstRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new QueryFirstResponse.Codec().asBinaryCodec(),
            "QueryFirstResponse",
            QueryFirstResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryFirstResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new QueryFirstResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "QueryFirstResponse"),
            QueryFirstResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryFirstResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new QueryNextRequest.Codec().asBinaryCodec(),
            "QueryNextRequest",
            QueryNextRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryNextRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new QueryNextRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "QueryNextRequest"),
            QueryNextRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryNextRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new QueryNextResponse.Codec().asBinaryCodec(),
            "QueryNextResponse",
            QueryNextResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryNextResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new QueryNextResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "QueryNextResponse"),
            QueryNextResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            QueryNextResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReadValueId.Codec().asBinaryCodec(),
            "ReadValueId",
            ReadValueId.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadValueId.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReadValueId.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ReadValueId"),
            ReadValueId.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadValueId.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReadRequest.Codec().asBinaryCodec(),
            "ReadRequest",
            ReadRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReadRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ReadRequest"),
            ReadRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReadResponse.Codec().asBinaryCodec(),
            "ReadResponse",
            ReadResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReadResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ReadResponse"),
            ReadResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryReadValueId.Codec().asBinaryCodec(),
            "HistoryReadValueId",
            HistoryReadValueId.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadValueId.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryReadValueId.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "HistoryReadValueId"),
            HistoryReadValueId.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadValueId.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryReadResult.Codec().asBinaryCodec(),
            "HistoryReadResult",
            HistoryReadResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadResult.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryReadResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "HistoryReadResult"),
            HistoryReadResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadResult.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryReadDetails.Codec().asBinaryCodec(),
            "HistoryReadDetails",
            HistoryReadDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadDetails.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryReadDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "HistoryReadDetails"),
            HistoryReadDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadDetails.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReadEventDetails.Codec().asBinaryCodec(),
            "ReadEventDetails",
            ReadEventDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadEventDetails.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReadEventDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ReadEventDetails"),
            ReadEventDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadEventDetails.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReadRawModifiedDetails.Codec().asBinaryCodec(),
            "ReadRawModifiedDetails",
            ReadRawModifiedDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadRawModifiedDetails.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReadRawModifiedDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ReadRawModifiedDetails"),
            ReadRawModifiedDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadRawModifiedDetails.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReadProcessedDetails.Codec().asBinaryCodec(),
            "ReadProcessedDetails",
            ReadProcessedDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadProcessedDetails.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReadProcessedDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ReadProcessedDetails"),
            ReadProcessedDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadProcessedDetails.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReadAtTimeDetails.Codec().asBinaryCodec(),
            "ReadAtTimeDetails",
            ReadAtTimeDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadAtTimeDetails.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReadAtTimeDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ReadAtTimeDetails"),
            ReadAtTimeDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadAtTimeDetails.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryData.Codec().asBinaryCodec(),
            "HistoryData",
            HistoryData.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryData.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryData.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "HistoryData"),
            HistoryData.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryData.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryEvent.Codec().asBinaryCodec(),
            "HistoryEvent",
            HistoryEvent.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryEvent.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryEvent.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "HistoryEvent"),
            HistoryEvent.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryEvent.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryReadRequest.Codec().asBinaryCodec(),
            "HistoryReadRequest",
            HistoryReadRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryReadRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "HistoryReadRequest"),
            HistoryReadRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryReadResponse.Codec().asBinaryCodec(),
            "HistoryReadResponse",
            HistoryReadResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryReadResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "HistoryReadResponse"),
            HistoryReadResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryReadResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new WriteValue.Codec().asBinaryCodec(),
            "WriteValue",
            WriteValue.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            WriteValue.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new WriteValue.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "WriteValue"),
            WriteValue.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            WriteValue.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new WriteRequest.Codec().asBinaryCodec(),
            "WriteRequest",
            WriteRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            WriteRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new WriteRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "WriteRequest"),
            WriteRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            WriteRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new WriteResponse.Codec().asBinaryCodec(),
            "WriteResponse",
            WriteResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            WriteResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new WriteResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "WriteResponse"),
            WriteResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            WriteResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryUpdateDetails.Codec().asBinaryCodec(),
            "HistoryUpdateDetails",
            HistoryUpdateDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryUpdateDetails.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryUpdateDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "HistoryUpdateDetails"),
            HistoryUpdateDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryUpdateDetails.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UpdateDataDetails.Codec().asBinaryCodec(),
            "UpdateDataDetails",
            UpdateDataDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UpdateDataDetails.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UpdateDataDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "UpdateDataDetails"),
            UpdateDataDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UpdateDataDetails.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UpdateEventDetails.Codec().asBinaryCodec(),
            "UpdateEventDetails",
            UpdateEventDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UpdateEventDetails.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UpdateEventDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "UpdateEventDetails"),
            UpdateEventDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UpdateEventDetails.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteRawModifiedDetails.Codec().asBinaryCodec(),
            "DeleteRawModifiedDetails",
            DeleteRawModifiedDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteRawModifiedDetails.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteRawModifiedDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DeleteRawModifiedDetails"),
            DeleteRawModifiedDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteRawModifiedDetails.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteAtTimeDetails.Codec().asBinaryCodec(),
            "DeleteAtTimeDetails",
            DeleteAtTimeDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteAtTimeDetails.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteAtTimeDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DeleteAtTimeDetails"),
            DeleteAtTimeDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteAtTimeDetails.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteEventDetails.Codec().asBinaryCodec(),
            "DeleteEventDetails",
            DeleteEventDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteEventDetails.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteEventDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DeleteEventDetails"),
            DeleteEventDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteEventDetails.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryUpdateResult.Codec().asBinaryCodec(),
            "HistoryUpdateResult",
            HistoryUpdateResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryUpdateResult.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryUpdateResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "HistoryUpdateResult"),
            HistoryUpdateResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryUpdateResult.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryUpdateRequest.Codec().asBinaryCodec(),
            "HistoryUpdateRequest",
            HistoryUpdateRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryUpdateRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryUpdateRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "HistoryUpdateRequest"),
            HistoryUpdateRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryUpdateRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryUpdateResponse.Codec().asBinaryCodec(),
            "HistoryUpdateResponse",
            HistoryUpdateResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryUpdateResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryUpdateResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "HistoryUpdateResponse"),
            HistoryUpdateResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryUpdateResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CallMethodRequest.Codec().asBinaryCodec(),
            "CallMethodRequest",
            CallMethodRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CallMethodRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CallMethodRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CallMethodRequest"),
            CallMethodRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CallMethodRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CallMethodResult.Codec().asBinaryCodec(),
            "CallMethodResult",
            CallMethodResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CallMethodResult.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CallMethodResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CallMethodResult"),
            CallMethodResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CallMethodResult.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CallRequest.Codec().asBinaryCodec(),
            "CallRequest",
            CallRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CallRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CallRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CallRequest"),
            CallRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CallRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CallResponse.Codec().asBinaryCodec(),
            "CallResponse",
            CallResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CallResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CallResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CallResponse"),
            CallResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CallResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MonitoringFilter.Codec().asBinaryCodec(),
            "MonitoringFilter",
            MonitoringFilter.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoringFilter.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MonitoringFilter.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "MonitoringFilter"),
            MonitoringFilter.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoringFilter.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new TimeZoneDataType.Codec().asBinaryCodec(),
            "TimeZoneDataType",
            TimeZoneDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TimeZoneDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TimeZoneDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "TimeZoneDataType"),
            TimeZoneDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TimeZoneDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DataChangeFilter.Codec().asBinaryCodec(),
            "DataChangeFilter",
            DataChangeFilter.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataChangeFilter.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DataChangeFilter.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DataChangeFilter"),
            DataChangeFilter.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataChangeFilter.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EventFilter.Codec().asBinaryCodec(),
            "EventFilter",
            EventFilter.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EventFilter.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EventFilter.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "EventFilter"),
            EventFilter.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EventFilter.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AggregateFilter.Codec().asBinaryCodec(),
            "AggregateFilter",
            AggregateFilter.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AggregateFilter.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AggregateFilter.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "AggregateFilter"),
            AggregateFilter.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AggregateFilter.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MonitoringFilterResult.Codec().asBinaryCodec(),
            "MonitoringFilterResult",
            MonitoringFilterResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoringFilterResult.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MonitoringFilterResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "MonitoringFilterResult"),
            MonitoringFilterResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoringFilterResult.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EventFilterResult.Codec().asBinaryCodec(),
            "EventFilterResult",
            EventFilterResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EventFilterResult.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EventFilterResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "EventFilterResult"),
            EventFilterResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EventFilterResult.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AggregateFilterResult.Codec().asBinaryCodec(),
            "AggregateFilterResult",
            AggregateFilterResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AggregateFilterResult.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AggregateFilterResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "AggregateFilterResult"),
            AggregateFilterResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AggregateFilterResult.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MonitoringParameters.Codec().asBinaryCodec(),
            "MonitoringParameters",
            MonitoringParameters.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoringParameters.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MonitoringParameters.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "MonitoringParameters"),
            MonitoringParameters.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoringParameters.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MonitoredItemCreateRequest.Codec().asBinaryCodec(),
            "MonitoredItemCreateRequest",
            MonitoredItemCreateRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemCreateRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MonitoredItemCreateRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "MonitoredItemCreateRequest"),
            MonitoredItemCreateRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemCreateRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MonitoredItemCreateResult.Codec().asBinaryCodec(),
            "MonitoredItemCreateResult",
            MonitoredItemCreateResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemCreateResult.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MonitoredItemCreateResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "MonitoredItemCreateResult"),
            MonitoredItemCreateResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemCreateResult.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CreateMonitoredItemsRequest.Codec().asBinaryCodec(),
            "CreateMonitoredItemsRequest",
            CreateMonitoredItemsRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateMonitoredItemsRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CreateMonitoredItemsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CreateMonitoredItemsRequest"),
            CreateMonitoredItemsRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateMonitoredItemsRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CreateMonitoredItemsResponse.Codec().asBinaryCodec(),
            "CreateMonitoredItemsResponse",
            CreateMonitoredItemsResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateMonitoredItemsResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CreateMonitoredItemsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CreateMonitoredItemsResponse"),
            CreateMonitoredItemsResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateMonitoredItemsResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MonitoredItemModifyRequest.Codec().asBinaryCodec(),
            "MonitoredItemModifyRequest",
            MonitoredItemModifyRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemModifyRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MonitoredItemModifyRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "MonitoredItemModifyRequest"),
            MonitoredItemModifyRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemModifyRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MonitoredItemModifyResult.Codec().asBinaryCodec(),
            "MonitoredItemModifyResult",
            MonitoredItemModifyResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemModifyResult.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MonitoredItemModifyResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "MonitoredItemModifyResult"),
            MonitoredItemModifyResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemModifyResult.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ModifyMonitoredItemsRequest.Codec().asBinaryCodec(),
            "ModifyMonitoredItemsRequest",
            ModifyMonitoredItemsRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModifyMonitoredItemsRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ModifyMonitoredItemsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ModifyMonitoredItemsRequest"),
            ModifyMonitoredItemsRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModifyMonitoredItemsRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ModifyMonitoredItemsResponse.Codec().asBinaryCodec(),
            "ModifyMonitoredItemsResponse",
            ModifyMonitoredItemsResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModifyMonitoredItemsResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ModifyMonitoredItemsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ModifyMonitoredItemsResponse"),
            ModifyMonitoredItemsResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModifyMonitoredItemsResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SetMonitoringModeRequest.Codec().asBinaryCodec(),
            "SetMonitoringModeRequest",
            SetMonitoringModeRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetMonitoringModeRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SetMonitoringModeRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SetMonitoringModeRequest"),
            SetMonitoringModeRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetMonitoringModeRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SetMonitoringModeResponse.Codec().asBinaryCodec(),
            "SetMonitoringModeResponse",
            SetMonitoringModeResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetMonitoringModeResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SetMonitoringModeResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SetMonitoringModeResponse"),
            SetMonitoringModeResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetMonitoringModeResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SetTriggeringRequest.Codec().asBinaryCodec(),
            "SetTriggeringRequest",
            SetTriggeringRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetTriggeringRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SetTriggeringRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SetTriggeringRequest"),
            SetTriggeringRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetTriggeringRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SetTriggeringResponse.Codec().asBinaryCodec(),
            "SetTriggeringResponse",
            SetTriggeringResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetTriggeringResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SetTriggeringResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SetTriggeringResponse"),
            SetTriggeringResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetTriggeringResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteMonitoredItemsRequest.Codec().asBinaryCodec(),
            "DeleteMonitoredItemsRequest",
            DeleteMonitoredItemsRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteMonitoredItemsRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteMonitoredItemsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DeleteMonitoredItemsRequest"),
            DeleteMonitoredItemsRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteMonitoredItemsRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteMonitoredItemsResponse.Codec().asBinaryCodec(),
            "DeleteMonitoredItemsResponse",
            DeleteMonitoredItemsResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteMonitoredItemsResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteMonitoredItemsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DeleteMonitoredItemsResponse"),
            DeleteMonitoredItemsResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteMonitoredItemsResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CreateSubscriptionRequest.Codec().asBinaryCodec(),
            "CreateSubscriptionRequest",
            CreateSubscriptionRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateSubscriptionRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CreateSubscriptionRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CreateSubscriptionRequest"),
            CreateSubscriptionRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateSubscriptionRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CreateSubscriptionResponse.Codec().asBinaryCodec(),
            "CreateSubscriptionResponse",
            CreateSubscriptionResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateSubscriptionResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CreateSubscriptionResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CreateSubscriptionResponse"),
            CreateSubscriptionResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CreateSubscriptionResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ModifySubscriptionRequest.Codec().asBinaryCodec(),
            "ModifySubscriptionRequest",
            ModifySubscriptionRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModifySubscriptionRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ModifySubscriptionRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ModifySubscriptionRequest"),
            ModifySubscriptionRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModifySubscriptionRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ModifySubscriptionResponse.Codec().asBinaryCodec(),
            "ModifySubscriptionResponse",
            ModifySubscriptionResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModifySubscriptionResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ModifySubscriptionResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ModifySubscriptionResponse"),
            ModifySubscriptionResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModifySubscriptionResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SetPublishingModeRequest.Codec().asBinaryCodec(),
            "SetPublishingModeRequest",
            SetPublishingModeRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetPublishingModeRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SetPublishingModeRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SetPublishingModeRequest"),
            SetPublishingModeRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetPublishingModeRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SetPublishingModeResponse.Codec().asBinaryCodec(),
            "SetPublishingModeResponse",
            SetPublishingModeResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetPublishingModeResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SetPublishingModeResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SetPublishingModeResponse"),
            SetPublishingModeResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SetPublishingModeResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new NotificationMessage.Codec().asBinaryCodec(),
            "NotificationMessage",
            NotificationMessage.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NotificationMessage.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new NotificationMessage.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "NotificationMessage"),
            NotificationMessage.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NotificationMessage.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MonitoredItemNotification.Codec().asBinaryCodec(),
            "MonitoredItemNotification",
            MonitoredItemNotification.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemNotification.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MonitoredItemNotification.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "MonitoredItemNotification"),
            MonitoredItemNotification.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MonitoredItemNotification.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DataChangeNotification.Codec().asBinaryCodec(),
            "DataChangeNotification",
            DataChangeNotification.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataChangeNotification.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DataChangeNotification.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DataChangeNotification"),
            DataChangeNotification.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataChangeNotification.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new StatusChangeNotification.Codec().asBinaryCodec(),
            "StatusChangeNotification",
            StatusChangeNotification.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            StatusChangeNotification.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new StatusChangeNotification.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "StatusChangeNotification"),
            StatusChangeNotification.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            StatusChangeNotification.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SubscriptionAcknowledgement.Codec().asBinaryCodec(),
            "SubscriptionAcknowledgement",
            SubscriptionAcknowledgement.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SubscriptionAcknowledgement.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SubscriptionAcknowledgement.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SubscriptionAcknowledgement"),
            SubscriptionAcknowledgement.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SubscriptionAcknowledgement.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new PublishRequest.Codec().asBinaryCodec(),
            "PublishRequest",
            PublishRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PublishRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new PublishRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "PublishRequest"),
            PublishRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PublishRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new PublishResponse.Codec().asBinaryCodec(),
            "PublishResponse",
            PublishResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PublishResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new PublishResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "PublishResponse"),
            PublishResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PublishResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RepublishRequest.Codec().asBinaryCodec(),
            "RepublishRequest",
            RepublishRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RepublishRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RepublishRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "RepublishRequest"),
            RepublishRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RepublishRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RepublishResponse.Codec().asBinaryCodec(),
            "RepublishResponse",
            RepublishResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RepublishResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RepublishResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "RepublishResponse"),
            RepublishResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RepublishResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new TransferResult.Codec().asBinaryCodec(),
            "TransferResult",
            TransferResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TransferResult.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TransferResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "TransferResult"),
            TransferResult.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TransferResult.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new TransferSubscriptionsRequest.Codec().asBinaryCodec(),
            "TransferSubscriptionsRequest",
            TransferSubscriptionsRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TransferSubscriptionsRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TransferSubscriptionsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "TransferSubscriptionsRequest"),
            TransferSubscriptionsRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TransferSubscriptionsRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new TransferSubscriptionsResponse.Codec().asBinaryCodec(),
            "TransferSubscriptionsResponse",
            TransferSubscriptionsResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TransferSubscriptionsResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TransferSubscriptionsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "TransferSubscriptionsResponse"),
            TransferSubscriptionsResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TransferSubscriptionsResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteSubscriptionsRequest.Codec().asBinaryCodec(),
            "DeleteSubscriptionsRequest",
            DeleteSubscriptionsRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteSubscriptionsRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteSubscriptionsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DeleteSubscriptionsRequest"),
            DeleteSubscriptionsRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteSubscriptionsRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DeleteSubscriptionsResponse.Codec().asBinaryCodec(),
            "DeleteSubscriptionsResponse",
            DeleteSubscriptionsResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteSubscriptionsResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DeleteSubscriptionsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DeleteSubscriptionsResponse"),
            DeleteSubscriptionsResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DeleteSubscriptionsResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RedundantServerDataType.Codec().asBinaryCodec(),
            "RedundantServerDataType",
            RedundantServerDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RedundantServerDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RedundantServerDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "RedundantServerDataType"),
            RedundantServerDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RedundantServerDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SamplingIntervalDiagnosticsDataType.Codec().asBinaryCodec(),
            "SamplingIntervalDiagnosticsDataType",
            SamplingIntervalDiagnosticsDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SamplingIntervalDiagnosticsDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SamplingIntervalDiagnosticsDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SamplingIntervalDiagnosticsDataType"),
            SamplingIntervalDiagnosticsDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SamplingIntervalDiagnosticsDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ServerDiagnosticsSummaryDataType.Codec().asBinaryCodec(),
            "ServerDiagnosticsSummaryDataType",
            ServerDiagnosticsSummaryDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServerDiagnosticsSummaryDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ServerDiagnosticsSummaryDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ServerDiagnosticsSummaryDataType"),
            ServerDiagnosticsSummaryDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServerDiagnosticsSummaryDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ServerStatusDataType.Codec().asBinaryCodec(),
            "ServerStatusDataType",
            ServerStatusDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServerStatusDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ServerStatusDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ServerStatusDataType"),
            ServerStatusDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServerStatusDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SessionDiagnosticsDataType.Codec().asBinaryCodec(),
            "SessionDiagnosticsDataType",
            SessionDiagnosticsDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SessionDiagnosticsDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SessionDiagnosticsDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SessionDiagnosticsDataType"),
            SessionDiagnosticsDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SessionDiagnosticsDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SessionSecurityDiagnosticsDataType.Codec().asBinaryCodec(),
            "SessionSecurityDiagnosticsDataType",
            SessionSecurityDiagnosticsDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SessionSecurityDiagnosticsDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SessionSecurityDiagnosticsDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SessionSecurityDiagnosticsDataType"),
            SessionSecurityDiagnosticsDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SessionSecurityDiagnosticsDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ServiceCounterDataType.Codec().asBinaryCodec(),
            "ServiceCounterDataType",
            ServiceCounterDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServiceCounterDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ServiceCounterDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ServiceCounterDataType"),
            ServiceCounterDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServiceCounterDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SubscriptionDiagnosticsDataType.Codec().asBinaryCodec(),
            "SubscriptionDiagnosticsDataType",
            SubscriptionDiagnosticsDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SubscriptionDiagnosticsDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SubscriptionDiagnosticsDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SubscriptionDiagnosticsDataType"),
            SubscriptionDiagnosticsDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SubscriptionDiagnosticsDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ModelChangeStructureDataType.Codec().asBinaryCodec(),
            "ModelChangeStructureDataType",
            ModelChangeStructureDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModelChangeStructureDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ModelChangeStructureDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ModelChangeStructureDataType"),
            ModelChangeStructureDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModelChangeStructureDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new Range.Codec().asBinaryCodec(),
            "Range",
            Range.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            Range.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new Range.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "Range"),
            Range.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            Range.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EUInformation.Codec().asBinaryCodec(),
            "EUInformation",
            EUInformation.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EUInformation.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EUInformation.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "EUInformation"),
            EUInformation.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EUInformation.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new Annotation.Codec().asBinaryCodec(),
            "Annotation",
            Annotation.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            Annotation.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new Annotation.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "Annotation"),
            Annotation.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            Annotation.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ProgramDiagnosticDataType.Codec().asBinaryCodec(),
            "ProgramDiagnosticDataType",
            ProgramDiagnosticDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ProgramDiagnosticDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ProgramDiagnosticDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ProgramDiagnosticDataType"),
            ProgramDiagnosticDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ProgramDiagnosticDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SemanticChangeStructureDataType.Codec().asBinaryCodec(),
            "SemanticChangeStructureDataType",
            SemanticChangeStructureDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SemanticChangeStructureDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SemanticChangeStructureDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SemanticChangeStructureDataType"),
            SemanticChangeStructureDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SemanticChangeStructureDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EventNotificationList.Codec().asBinaryCodec(),
            "EventNotificationList",
            EventNotificationList.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EventNotificationList.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EventNotificationList.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "EventNotificationList"),
            EventNotificationList.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EventNotificationList.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EventFieldList.Codec().asBinaryCodec(),
            "EventFieldList",
            EventFieldList.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EventFieldList.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EventFieldList.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "EventFieldList"),
            EventFieldList.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EventFieldList.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryEventFieldList.Codec().asBinaryCodec(),
            "HistoryEventFieldList",
            HistoryEventFieldList.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryEventFieldList.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryEventFieldList.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "HistoryEventFieldList"),
            HistoryEventFieldList.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryEventFieldList.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new IssuedIdentityToken.Codec().asBinaryCodec(),
            "IssuedIdentityToken",
            IssuedIdentityToken.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            IssuedIdentityToken.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new IssuedIdentityToken.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "IssuedIdentityToken"),
            IssuedIdentityToken.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            IssuedIdentityToken.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new NotificationData.Codec().asBinaryCodec(),
            "NotificationData",
            NotificationData.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NotificationData.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new NotificationData.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "NotificationData"),
            NotificationData.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NotificationData.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AggregateConfiguration.Codec().asBinaryCodec(),
            "AggregateConfiguration",
            AggregateConfiguration.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AggregateConfiguration.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AggregateConfiguration.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "AggregateConfiguration"),
            AggregateConfiguration.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AggregateConfiguration.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DatagramConnectionTransportDataType.Codec().asBinaryCodec(),
            "DatagramConnectionTransportDataType",
            DatagramConnectionTransportDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DatagramConnectionTransportDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DatagramConnectionTransportDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DatagramConnectionTransportDataType"),
            DatagramConnectionTransportDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DatagramConnectionTransportDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EphemeralKeyType.Codec().asBinaryCodec(),
            "EphemeralKeyType",
            EphemeralKeyType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EphemeralKeyType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EphemeralKeyType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "EphemeralKeyType"),
            EphemeralKeyType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EphemeralKeyType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new GenericAttributeValue.Codec().asBinaryCodec(),
            "GenericAttributeValue",
            GenericAttributeValue.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            GenericAttributeValue.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new GenericAttributeValue.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "GenericAttributeValue"),
            GenericAttributeValue.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            GenericAttributeValue.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new GenericAttributes.Codec().asBinaryCodec(),
            "GenericAttributes",
            GenericAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            GenericAttributes.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new GenericAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "GenericAttributes"),
            GenericAttributes.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            GenericAttributes.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DecimalDataType.Codec().asBinaryCodec(),
            "DecimalDataType",
            DecimalDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DecimalDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DecimalDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DecimalDataType"),
            DecimalDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DecimalDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RationalNumber.Codec().asBinaryCodec(),
            "RationalNumber",
            RationalNumber.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RationalNumber.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RationalNumber.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "RationalNumber"),
            RationalNumber.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RationalNumber.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ThreeDVector.Codec().asBinaryCodec(),
            "ThreeDVector",
            ThreeDVector.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ThreeDVector.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ThreeDVector.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ThreeDVector"),
            ThreeDVector.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ThreeDVector.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ThreeDCartesianCoordinates.Codec().asBinaryCodec(),
            "ThreeDCartesianCoordinates",
            ThreeDCartesianCoordinates.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ThreeDCartesianCoordinates.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ThreeDCartesianCoordinates.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ThreeDCartesianCoordinates"),
            ThreeDCartesianCoordinates.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ThreeDCartesianCoordinates.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ThreeDOrientation.Codec().asBinaryCodec(),
            "ThreeDOrientation",
            ThreeDOrientation.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ThreeDOrientation.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ThreeDOrientation.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ThreeDOrientation"),
            ThreeDOrientation.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ThreeDOrientation.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ThreeDFrame.Codec().asBinaryCodec(),
            "ThreeDFrame",
            ThreeDFrame.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ThreeDFrame.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ThreeDFrame.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ThreeDFrame"),
            ThreeDFrame.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ThreeDFrame.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ModificationInfo.Codec().asBinaryCodec(),
            "ModificationInfo",
            ModificationInfo.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModificationInfo.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ModificationInfo.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ModificationInfo"),
            ModificationInfo.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ModificationInfo.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new HistoryModifiedData.Codec().asBinaryCodec(),
            "HistoryModifiedData",
            HistoryModifiedData.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryModifiedData.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new HistoryModifiedData.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "HistoryModifiedData"),
            HistoryModifiedData.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            HistoryModifiedData.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UpdateStructureDataDetails.Codec().asBinaryCodec(),
            "UpdateStructureDataDetails",
            UpdateStructureDataDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UpdateStructureDataDetails.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UpdateStructureDataDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "UpdateStructureDataDetails"),
            UpdateStructureDataDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UpdateStructureDataDetails.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new InstanceNode.Codec().asBinaryCodec(),
            "InstanceNode",
            InstanceNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            InstanceNode.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new InstanceNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "InstanceNode"),
            InstanceNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            InstanceNode.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new TypeNode.Codec().asBinaryCodec(),
            "TypeNode",
            TypeNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TypeNode.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TypeNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "TypeNode"),
            TypeNode.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TypeNode.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EndpointUrlListDataType.Codec().asBinaryCodec(),
            "EndpointUrlListDataType",
            EndpointUrlListDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EndpointUrlListDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EndpointUrlListDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "EndpointUrlListDataType"),
            EndpointUrlListDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EndpointUrlListDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new NetworkGroupDataType.Codec().asBinaryCodec(),
            "NetworkGroupDataType",
            NetworkGroupDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NetworkGroupDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new NetworkGroupDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "NetworkGroupDataType"),
            NetworkGroupDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NetworkGroupDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AxisInformation.Codec().asBinaryCodec(),
            "AxisInformation",
            AxisInformation.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AxisInformation.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AxisInformation.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "AxisInformation"),
            AxisInformation.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AxisInformation.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new XVType.Codec().asBinaryCodec(),
            "XVType",
            XVType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            XVType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new XVType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "XVType"),
            XVType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            XVType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ComplexNumberType.Codec().asBinaryCodec(),
            "ComplexNumberType",
            ComplexNumberType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ComplexNumberType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ComplexNumberType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ComplexNumberType"),
            ComplexNumberType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ComplexNumberType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DoubleComplexNumberType.Codec().asBinaryCodec(),
            "DoubleComplexNumberType",
            DoubleComplexNumberType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DoubleComplexNumberType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DoubleComplexNumberType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DoubleComplexNumberType"),
            DoubleComplexNumberType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DoubleComplexNumberType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ServerOnNetwork.Codec().asBinaryCodec(),
            "ServerOnNetwork",
            ServerOnNetwork.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServerOnNetwork.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ServerOnNetwork.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ServerOnNetwork"),
            ServerOnNetwork.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ServerOnNetwork.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new FindServersOnNetworkRequest.Codec().asBinaryCodec(),
            "FindServersOnNetworkRequest",
            FindServersOnNetworkRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FindServersOnNetworkRequest.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new FindServersOnNetworkRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "FindServersOnNetworkRequest"),
            FindServersOnNetworkRequest.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FindServersOnNetworkRequest.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new FindServersOnNetworkResponse.Codec().asBinaryCodec(),
            "FindServersOnNetworkResponse",
            FindServersOnNetworkResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FindServersOnNetworkResponse.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new FindServersOnNetworkResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "FindServersOnNetworkResponse"),
            FindServersOnNetworkResponse.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FindServersOnNetworkResponse.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RegisterServer2Request.Codec().asBinaryCodec(),
            "RegisterServer2Request",
            RegisterServer2Request.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterServer2Request.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RegisterServer2Request.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "RegisterServer2Request"),
            RegisterServer2Request.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterServer2Request.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new RegisterServer2Response.Codec().asBinaryCodec(),
            "RegisterServer2Response",
            RegisterServer2Response.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterServer2Response.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new RegisterServer2Response.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "RegisterServer2Response"),
            RegisterServer2Response.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            RegisterServer2Response.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new TrustListDataType.Codec().asBinaryCodec(),
            "TrustListDataType",
            TrustListDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TrustListDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TrustListDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "TrustListDataType"),
            TrustListDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TrustListDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new OptionSet.Codec().asBinaryCodec(),
            "OptionSet",
            OptionSet.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            OptionSet.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new OptionSet.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "OptionSet"),
            OptionSet.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            OptionSet.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SessionlessInvokeResponseType.Codec().asBinaryCodec(),
            "SessionlessInvokeResponseType",
            SessionlessInvokeResponseType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SessionlessInvokeResponseType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SessionlessInvokeResponseType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SessionlessInvokeResponseType"),
            SessionlessInvokeResponseType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SessionlessInvokeResponseType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DiscoveryConfiguration.Codec().asBinaryCodec(),
            "DiscoveryConfiguration",
            DiscoveryConfiguration.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DiscoveryConfiguration.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DiscoveryConfiguration.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DiscoveryConfiguration"),
            DiscoveryConfiguration.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DiscoveryConfiguration.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new MdnsDiscoveryConfiguration.Codec().asBinaryCodec(),
            "MdnsDiscoveryConfiguration",
            MdnsDiscoveryConfiguration.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MdnsDiscoveryConfiguration.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new MdnsDiscoveryConfiguration.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "MdnsDiscoveryConfiguration"),
            MdnsDiscoveryConfiguration.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            MdnsDiscoveryConfiguration.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new PublishedVariableDataType.Codec().asBinaryCodec(),
            "PublishedVariableDataType",
            PublishedVariableDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PublishedVariableDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new PublishedVariableDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "PublishedVariableDataType"),
            PublishedVariableDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PublishedVariableDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DataSetMetaDataType.Codec().asBinaryCodec(),
            "DataSetMetaDataType",
            DataSetMetaDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataSetMetaDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DataSetMetaDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DataSetMetaDataType"),
            DataSetMetaDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataSetMetaDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new FieldMetaData.Codec().asBinaryCodec(),
            "FieldMetaData",
            FieldMetaData.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FieldMetaData.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new FieldMetaData.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "FieldMetaData"),
            FieldMetaData.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FieldMetaData.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new KeyValuePair.Codec().asBinaryCodec(),
            "KeyValuePair",
            KeyValuePair.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            KeyValuePair.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new KeyValuePair.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "KeyValuePair"),
            KeyValuePair.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            KeyValuePair.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ConfigurationVersionDataType.Codec().asBinaryCodec(),
            "ConfigurationVersionDataType",
            ConfigurationVersionDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ConfigurationVersionDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ConfigurationVersionDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ConfigurationVersionDataType"),
            ConfigurationVersionDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ConfigurationVersionDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new FieldTargetDataType.Codec().asBinaryCodec(),
            "FieldTargetDataType",
            FieldTargetDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FieldTargetDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new FieldTargetDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "FieldTargetDataType"),
            FieldTargetDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            FieldTargetDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SimpleTypeDescription.Codec().asBinaryCodec(),
            "SimpleTypeDescription",
            SimpleTypeDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SimpleTypeDescription.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SimpleTypeDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SimpleTypeDescription"),
            SimpleTypeDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SimpleTypeDescription.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UABinaryFileDataType.Codec().asBinaryCodec(),
            "UABinaryFileDataType",
            UABinaryFileDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UABinaryFileDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UABinaryFileDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "UABinaryFileDataType"),
            UABinaryFileDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UABinaryFileDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrokerConnectionTransportDataType.Codec().asBinaryCodec(),
            "BrokerConnectionTransportDataType",
            BrokerConnectionTransportDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrokerConnectionTransportDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrokerConnectionTransportDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BrokerConnectionTransportDataType"),
            BrokerConnectionTransportDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrokerConnectionTransportDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AliasNameDataType.Codec().asBinaryCodec(),
            "AliasNameDataType",
            AliasNameDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AliasNameDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AliasNameDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "AliasNameDataType"),
            AliasNameDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AliasNameDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReadAnnotationDataDetails.Codec().asBinaryCodec(),
            "ReadAnnotationDataDetails",
            ReadAnnotationDataDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadAnnotationDataDetails.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReadAnnotationDataDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ReadAnnotationDataDetails"),
            ReadAnnotationDataDetails.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReadAnnotationDataDetails.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new CurrencyUnitType.Codec().asBinaryCodec(),
            "CurrencyUnitType",
            CurrencyUnitType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CurrencyUnitType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new CurrencyUnitType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "CurrencyUnitType"),
            CurrencyUnitType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            CurrencyUnitType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ProgramDiagnostic2DataType.Codec().asBinaryCodec(),
            "ProgramDiagnostic2DataType",
            ProgramDiagnostic2DataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ProgramDiagnostic2DataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ProgramDiagnostic2DataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ProgramDiagnostic2DataType"),
            ProgramDiagnostic2DataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ProgramDiagnostic2DataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new WriterGroupDataType.Codec().asBinaryCodec(),
            "WriterGroupDataType",
            WriterGroupDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            WriterGroupDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new WriterGroupDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "WriterGroupDataType"),
            WriterGroupDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            WriterGroupDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new StructureDescription.Codec().asBinaryCodec(),
            "StructureDescription",
            StructureDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            StructureDescription.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new StructureDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "StructureDescription"),
            StructureDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            StructureDescription.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EnumDescription.Codec().asBinaryCodec(),
            "EnumDescription",
            EnumDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EnumDescription.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EnumDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "EnumDescription"),
            EnumDescription.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EnumDescription.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new NetworkAddressUrlDataType.Codec().asBinaryCodec(),
            "NetworkAddressUrlDataType",
            NetworkAddressUrlDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NetworkAddressUrlDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new NetworkAddressUrlDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "NetworkAddressUrlDataType"),
            NetworkAddressUrlDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            NetworkAddressUrlDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new ReaderGroupDataType.Codec().asBinaryCodec(),
            "ReaderGroupDataType",
            ReaderGroupDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReaderGroupDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new ReaderGroupDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "ReaderGroupDataType"),
            ReaderGroupDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            ReaderGroupDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EndpointType.Codec().asBinaryCodec(),
            "EndpointType",
            EndpointType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EndpointType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EndpointType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "EndpointType"),
            EndpointType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EndpointType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new PubSubConfigurationDataType.Codec().asBinaryCodec(),
            "PubSubConfigurationDataType",
            PubSubConfigurationDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PubSubConfigurationDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new PubSubConfigurationDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "PubSubConfigurationDataType"),
            PubSubConfigurationDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PubSubConfigurationDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DatagramWriterGroupTransportDataType.Codec().asBinaryCodec(),
            "DatagramWriterGroupTransportDataType",
            DatagramWriterGroupTransportDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DatagramWriterGroupTransportDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DatagramWriterGroupTransportDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DatagramWriterGroupTransportDataType"),
            DatagramWriterGroupTransportDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DatagramWriterGroupTransportDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new PublishedDataSetDataType.Codec().asBinaryCodec(),
            "PublishedDataSetDataType",
            PublishedDataSetDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PublishedDataSetDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new PublishedDataSetDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "PublishedDataSetDataType"),
            PublishedDataSetDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PublishedDataSetDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new PublishedDataItemsDataType.Codec().asBinaryCodec(),
            "PublishedDataItemsDataType",
            PublishedDataItemsDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PublishedDataItemsDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new PublishedDataItemsDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "PublishedDataItemsDataType"),
            PublishedDataItemsDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PublishedDataItemsDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new PublishedEventsDataType.Codec().asBinaryCodec(),
            "PublishedEventsDataType",
            PublishedEventsDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PublishedEventsDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new PublishedEventsDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "PublishedEventsDataType"),
            PublishedEventsDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PublishedEventsDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DataSetWriterDataType.Codec().asBinaryCodec(),
            "DataSetWriterDataType",
            DataSetWriterDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataSetWriterDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DataSetWriterDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DataSetWriterDataType"),
            DataSetWriterDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataSetWriterDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new PubSubConnectionDataType.Codec().asBinaryCodec(),
            "PubSubConnectionDataType",
            PubSubConnectionDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PubSubConnectionDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new PubSubConnectionDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "PubSubConnectionDataType"),
            PubSubConnectionDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            PubSubConnectionDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new DataSetReaderDataType.Codec().asBinaryCodec(),
            "DataSetReaderDataType",
            DataSetReaderDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataSetReaderDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new DataSetReaderDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "DataSetReaderDataType"),
            DataSetReaderDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            DataSetReaderDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new TargetVariablesDataType.Codec().asBinaryCodec(),
            "TargetVariablesDataType",
            TargetVariablesDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TargetVariablesDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new TargetVariablesDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "TargetVariablesDataType"),
            TargetVariablesDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            TargetVariablesDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new IdentityMappingRuleType.Codec().asBinaryCodec(),
            "IdentityMappingRuleType",
            IdentityMappingRuleType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            IdentityMappingRuleType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new IdentityMappingRuleType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "IdentityMappingRuleType"),
            IdentityMappingRuleType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            IdentityMappingRuleType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SubscribedDataSetMirrorDataType.Codec().asBinaryCodec(),
            "SubscribedDataSetMirrorDataType",
            SubscribedDataSetMirrorDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SubscribedDataSetMirrorDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SubscribedDataSetMirrorDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SubscribedDataSetMirrorDataType"),
            SubscribedDataSetMirrorDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SubscribedDataSetMirrorDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UadpWriterGroupMessageDataType.Codec().asBinaryCodec(),
            "UadpWriterGroupMessageDataType",
            UadpWriterGroupMessageDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UadpWriterGroupMessageDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UadpWriterGroupMessageDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "UadpWriterGroupMessageDataType"),
            UadpWriterGroupMessageDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UadpWriterGroupMessageDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UadpDataSetWriterMessageDataType.Codec().asBinaryCodec(),
            "UadpDataSetWriterMessageDataType",
            UadpDataSetWriterMessageDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UadpDataSetWriterMessageDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UadpDataSetWriterMessageDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "UadpDataSetWriterMessageDataType"),
            UadpDataSetWriterMessageDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UadpDataSetWriterMessageDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new UadpDataSetReaderMessageDataType.Codec().asBinaryCodec(),
            "UadpDataSetReaderMessageDataType",
            UadpDataSetReaderMessageDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UadpDataSetReaderMessageDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new UadpDataSetReaderMessageDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "UadpDataSetReaderMessageDataType"),
            UadpDataSetReaderMessageDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            UadpDataSetReaderMessageDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new JsonWriterGroupMessageDataType.Codec().asBinaryCodec(),
            "JsonWriterGroupMessageDataType",
            JsonWriterGroupMessageDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            JsonWriterGroupMessageDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new JsonWriterGroupMessageDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "JsonWriterGroupMessageDataType"),
            JsonWriterGroupMessageDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            JsonWriterGroupMessageDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new JsonDataSetWriterMessageDataType.Codec().asBinaryCodec(),
            "JsonDataSetWriterMessageDataType",
            JsonDataSetWriterMessageDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            JsonDataSetWriterMessageDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new JsonDataSetWriterMessageDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "JsonDataSetWriterMessageDataType"),
            JsonDataSetWriterMessageDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            JsonDataSetWriterMessageDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new JsonDataSetReaderMessageDataType.Codec().asBinaryCodec(),
            "JsonDataSetReaderMessageDataType",
            JsonDataSetReaderMessageDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            JsonDataSetReaderMessageDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new JsonDataSetReaderMessageDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "JsonDataSetReaderMessageDataType"),
            JsonDataSetReaderMessageDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            JsonDataSetReaderMessageDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrokerWriterGroupTransportDataType.Codec().asBinaryCodec(),
            "BrokerWriterGroupTransportDataType",
            BrokerWriterGroupTransportDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrokerWriterGroupTransportDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrokerWriterGroupTransportDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BrokerWriterGroupTransportDataType"),
            BrokerWriterGroupTransportDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrokerWriterGroupTransportDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrokerDataSetWriterTransportDataType.Codec().asBinaryCodec(),
            "BrokerDataSetWriterTransportDataType",
            BrokerDataSetWriterTransportDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrokerDataSetWriterTransportDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrokerDataSetWriterTransportDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BrokerDataSetWriterTransportDataType"),
            BrokerDataSetWriterTransportDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrokerDataSetWriterTransportDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new BrokerDataSetReaderTransportDataType.Codec().asBinaryCodec(),
            "BrokerDataSetReaderTransportDataType",
            BrokerDataSetReaderTransportDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrokerDataSetReaderTransportDataType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new BrokerDataSetReaderTransportDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "BrokerDataSetReaderTransportDataType"),
            BrokerDataSetReaderTransportDataType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            BrokerDataSetReaderTransportDataType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new EnumValueType.Codec().asBinaryCodec(),
            "EnumValueType",
            EnumValueType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EnumValueType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new EnumValueType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "EnumValueType"),
            EnumValueType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            EnumValueType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new SessionlessInvokeRequestType.Codec().asBinaryCodec(),
            "SessionlessInvokeRequestType",
            SessionlessInvokeRequestType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SessionlessInvokeRequestType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new SessionlessInvokeRequestType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "SessionlessInvokeRequestType"),
            SessionlessInvokeRequestType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            SessionlessInvokeRequestType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        binaryDictionary.registerStructCodec(
            new AdditionalParametersType.Codec().asBinaryCodec(),
            "AdditionalParametersType",
            AdditionalParametersType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AdditionalParametersType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
        xmlDictionary.registerStructCodec(
            new AdditionalParametersType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%ss']", "AdditionalParametersType"),
            AdditionalParametersType.TYPE_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI)),
            AdditionalParametersType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow(
                () -> new IllegalStateException("namespace not registered: " + NAMESPACE_URI))
        );
    }
}
