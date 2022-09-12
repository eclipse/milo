package org.eclipse.milo.opcua.stack.core.types;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.structured.*;

public class DataTypeDictionaryInitializer extends AbstractDataTypeDictionaryInitializer {
    @Override
    protected void initializeEnums(NamespaceTable namespaceTable,
                                   DataTypeDictionary<OpcUaBinaryDataTypeCodec> binaryDictionary,
                                   DataTypeDictionary<OpcUaXmlDataTypeCodec> xmlDictionary) throws Exception {
//        binaryDictionary.registerEnumCodec(
//            new NamingRuleType.Codec().asBinaryCodec(),
//            "NamingRuleType",
//            NamingRuleType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new NamingRuleType.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "NamingRuleType"),
//            NamingRuleType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new OpenFileMode.Codec().asBinaryCodec(),
//            "OpenFileMode",
//            OpenFileMode.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new OpenFileMode.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "OpenFileMode"),
//            OpenFileMode.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new IdentityCriteriaType.Codec().asBinaryCodec(),
//            "IdentityCriteriaType",
//            IdentityCriteriaType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new IdentityCriteriaType.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "IdentityCriteriaType"),
//            IdentityCriteriaType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new TrustListMasks.Codec().asBinaryCodec(),
//            "TrustListMasks",
//            TrustListMasks.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new TrustListMasks.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "TrustListMasks"),
//            TrustListMasks.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new PubSubState.Codec().asBinaryCodec(),
//            "PubSubState",
//            PubSubState.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new PubSubState.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "PubSubState"),
//            PubSubState.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new OverrideValueHandling.Codec().asBinaryCodec(),
//            "OverrideValueHandling",
//            OverrideValueHandling.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new OverrideValueHandling.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "OverrideValueHandling"),
//            OverrideValueHandling.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new DataSetOrderingType.Codec().asBinaryCodec(),
//            "DataSetOrderingType",
//            DataSetOrderingType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new DataSetOrderingType.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "DataSetOrderingType"),
//            DataSetOrderingType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new BrokerTransportQualityOfService.Codec().asBinaryCodec(),
//            "BrokerTransportQualityOfService",
//            BrokerTransportQualityOfService.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new BrokerTransportQualityOfService.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "BrokerTransportQualityOfService"),
//            BrokerTransportQualityOfService.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new DiagnosticsLevel.Codec().asBinaryCodec(),
//            "DiagnosticsLevel",
//            DiagnosticsLevel.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new DiagnosticsLevel.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "DiagnosticsLevel"),
//            DiagnosticsLevel.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new PubSubDiagnosticsCounterClassification.Codec().asBinaryCodec(),
//            "PubSubDiagnosticsCounterClassification",
//            PubSubDiagnosticsCounterClassification.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new PubSubDiagnosticsCounterClassification.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "PubSubDiagnosticsCounterClassification"),
//            PubSubDiagnosticsCounterClassification.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new Duplex.Codec().asBinaryCodec(),
//            "Duplex",
//            Duplex.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new Duplex.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "Duplex"),
//            Duplex.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new InterfaceAdminStatus.Codec().asBinaryCodec(),
//            "InterfaceAdminStatus",
//            InterfaceAdminStatus.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new InterfaceAdminStatus.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "InterfaceAdminStatus"),
//            InterfaceAdminStatus.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new InterfaceOperStatus.Codec().asBinaryCodec(),
//            "InterfaceOperStatus",
//            InterfaceOperStatus.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new InterfaceOperStatus.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "InterfaceOperStatus"),
//            InterfaceOperStatus.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new NegotiationStatus.Codec().asBinaryCodec(),
//            "NegotiationStatus",
//            NegotiationStatus.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new NegotiationStatus.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "NegotiationStatus"),
//            NegotiationStatus.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new TsnFailureCode.Codec().asBinaryCodec(),
//            "TsnFailureCode",
//            TsnFailureCode.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new TsnFailureCode.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "TsnFailureCode"),
//            TsnFailureCode.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new TsnStreamState.Codec().asBinaryCodec(),
//            "TsnStreamState",
//            TsnStreamState.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new TsnStreamState.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "TsnStreamState"),
//            TsnStreamState.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new TsnTalkerStatus.Codec().asBinaryCodec(),
//            "TsnTalkerStatus",
//            TsnTalkerStatus.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new TsnTalkerStatus.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "TsnTalkerStatus"),
//            TsnTalkerStatus.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new TsnListenerStatus.Codec().asBinaryCodec(),
//            "TsnListenerStatus",
//            TsnListenerStatus.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new TsnListenerStatus.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "TsnListenerStatus"),
//            TsnListenerStatus.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new IdType.Codec().asBinaryCodec(),
//            "IdType",
//            IdType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new IdType.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "IdType"),
//            IdType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new NodeClass.Codec().asBinaryCodec(),
//            "NodeClass",
//            NodeClass.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new NodeClass.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "NodeClass"),
//            NodeClass.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new StructureType.Codec().asBinaryCodec(),
//            "StructureType",
//            StructureType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new StructureType.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "StructureType"),
//            StructureType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new ApplicationType.Codec().asBinaryCodec(),
//            "ApplicationType",
//            ApplicationType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new ApplicationType.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "ApplicationType"),
//            ApplicationType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new MessageSecurityMode.Codec().asBinaryCodec(),
//            "MessageSecurityMode",
//            MessageSecurityMode.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new MessageSecurityMode.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "MessageSecurityMode"),
//            MessageSecurityMode.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new UserTokenType.Codec().asBinaryCodec(),
//            "UserTokenType",
//            UserTokenType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new UserTokenType.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "UserTokenType"),
//            UserTokenType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new SecurityTokenRequestType.Codec().asBinaryCodec(),
//            "SecurityTokenRequestType",
//            SecurityTokenRequestType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new SecurityTokenRequestType.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "SecurityTokenRequestType"),
//            SecurityTokenRequestType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new NodeAttributesMask.Codec().asBinaryCodec(),
//            "NodeAttributesMask",
//            NodeAttributesMask.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new NodeAttributesMask.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "NodeAttributesMask"),
//            NodeAttributesMask.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new FilterOperator.Codec().asBinaryCodec(),
//            "FilterOperator",
//            FilterOperator.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new FilterOperator.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "FilterOperator"),
//            FilterOperator.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new HistoryUpdateType.Codec().asBinaryCodec(),
//            "HistoryUpdateType",
//            HistoryUpdateType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new HistoryUpdateType.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "HistoryUpdateType"),
//            HistoryUpdateType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new PerformUpdateType.Codec().asBinaryCodec(),
//            "PerformUpdateType",
//            PerformUpdateType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new PerformUpdateType.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "PerformUpdateType"),
//            PerformUpdateType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new RedundancySupport.Codec().asBinaryCodec(),
//            "RedundancySupport",
//            RedundancySupport.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new RedundancySupport.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "RedundancySupport"),
//            RedundancySupport.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new ServerState.Codec().asBinaryCodec(),
//            "ServerState",
//            ServerState.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new ServerState.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "ServerState"),
//            ServerState.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new AxisScaleEnumeration.Codec().asBinaryCodec(),
//            "AxisScaleEnumeration",
//            AxisScaleEnumeration.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new AxisScaleEnumeration.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "AxisScaleEnumeration"),
//            AxisScaleEnumeration.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new ExceptionDeviationFormat.Codec().asBinaryCodec(),
//            "ExceptionDeviationFormat",
//            ExceptionDeviationFormat.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new ExceptionDeviationFormat.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "ExceptionDeviationFormat"),
//            ExceptionDeviationFormat.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new BrowseDirection.Codec().asBinaryCodec(),
//            "BrowseDirection",
//            BrowseDirection.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new BrowseDirection.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "BrowseDirection"),
//            BrowseDirection.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new BrowseResultMask.Codec().asBinaryCodec(),
//            "BrowseResultMask",
//            BrowseResultMask.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new BrowseResultMask.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "BrowseResultMask"),
//            BrowseResultMask.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new TimestampsToReturn.Codec().asBinaryCodec(),
//            "TimestampsToReturn",
//            TimestampsToReturn.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new TimestampsToReturn.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "TimestampsToReturn"),
//            TimestampsToReturn.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new MonitoringMode.Codec().asBinaryCodec(),
//            "MonitoringMode",
//            MonitoringMode.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new MonitoringMode.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "MonitoringMode"),
//            MonitoringMode.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new DataChangeTrigger.Codec().asBinaryCodec(),
//            "DataChangeTrigger",
//            DataChangeTrigger.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new DataChangeTrigger.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "DataChangeTrigger"),
//            DataChangeTrigger.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new DeadbandType.Codec().asBinaryCodec(),
//            "DeadbandType",
//            DeadbandType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new DeadbandType.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "DeadbandType"),
//            DeadbandType.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        binaryDictionary.registerEnumCodec(
//            new ModelChangeStructureVerbMask.Codec().asBinaryCodec(),
//            "ModelChangeStructureVerbMask",
//            ModelChangeStructureVerbMask.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
//        xmlDictionary.registerEnumCodec(
//            new ModelChangeStructureVerbMask.Codec().asXmlCodec(),
//            String.format("//xs:element[@name='%s']", "ModelChangeStructureVerbMask"),
//            ModelChangeStructureVerbMask.TypeInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable)
//        );
    }

    @Override
    protected void initializeStructs(NamespaceTable namespaceTable,
                                     DataTypeDictionary<OpcUaBinaryDataTypeCodec> binaryDictionary,
                                     DataTypeDictionary<OpcUaXmlDataTypeCodec> xmlDictionary) throws Exception {
        binaryDictionary.registerStructCodec(
            new KeyValuePair.Codec().asBinaryCodec(),
            "KeyValuePair",
            KeyValuePair.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            KeyValuePair.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new KeyValuePair.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "KeyValuePair"),
            KeyValuePair.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            KeyValuePair.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new AdditionalParametersType.Codec().asBinaryCodec(),
            "AdditionalParametersType",
            AdditionalParametersType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AdditionalParametersType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new AdditionalParametersType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AdditionalParametersType"),
            AdditionalParametersType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AdditionalParametersType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new EphemeralKeyType.Codec().asBinaryCodec(),
            "EphemeralKeyType",
            EphemeralKeyType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EphemeralKeyType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new EphemeralKeyType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EphemeralKeyType"),
            EphemeralKeyType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EphemeralKeyType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new EndpointType.Codec().asBinaryCodec(),
            "EndpointType",
            EndpointType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EndpointType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new EndpointType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EndpointType"),
            EndpointType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EndpointType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new RationalNumber.Codec().asBinaryCodec(),
            "RationalNumber",
            RationalNumber.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RationalNumber.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new RationalNumber.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RationalNumber"),
            RationalNumber.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RationalNumber.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ThreeDVector.Codec().asBinaryCodec(),
            "3DVector",
            ThreeDVector.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDVector.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ThreeDVector.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "3DVector"),
            ThreeDVector.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDVector.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ThreeDCartesianCoordinates.Codec().asBinaryCodec(),
            "3DCartesianCoordinates",
            ThreeDCartesianCoordinates.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDCartesianCoordinates.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ThreeDCartesianCoordinates.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "3DCartesianCoordinates"),
            ThreeDCartesianCoordinates.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDCartesianCoordinates.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ThreeDOrientation.Codec().asBinaryCodec(),
            "3DOrientation",
            ThreeDOrientation.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDOrientation.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ThreeDOrientation.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "3DOrientation"),
            ThreeDOrientation.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDOrientation.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ThreeDFrame.Codec().asBinaryCodec(),
            "3DFrame",
            ThreeDFrame.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDFrame.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ThreeDFrame.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "3DFrame"),
            ThreeDFrame.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDFrame.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CurrencyUnitType.Codec().asBinaryCodec(),
            "CurrencyUnitType",
            CurrencyUnitType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CurrencyUnitType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CurrencyUnitType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CurrencyUnitType"),
            CurrencyUnitType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CurrencyUnitType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new TrustListDataType.Codec().asBinaryCodec(),
            "TrustListDataType",
            TrustListDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TrustListDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new TrustListDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TrustListDataType"),
            TrustListDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TrustListDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new UABinaryFileDataType.Codec().asBinaryCodec(),
            "UABinaryFileDataType",
            UABinaryFileDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UABinaryFileDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new UABinaryFileDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UABinaryFileDataType"),
            UABinaryFileDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UABinaryFileDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DataSetMetaDataType.Codec().asBinaryCodec(),
            "DataSetMetaDataType",
            DataSetMetaDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataSetMetaDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DataSetMetaDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DataSetMetaDataType"),
            DataSetMetaDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataSetMetaDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new StructureDescription.Codec().asBinaryCodec(),
            "StructureDescription",
            StructureDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StructureDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new StructureDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "StructureDescription"),
            StructureDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StructureDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new EnumDescription.Codec().asBinaryCodec(),
            "EnumDescription",
            EnumDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EnumDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new EnumDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EnumDescription"),
            EnumDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EnumDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SimpleTypeDescription.Codec().asBinaryCodec(),
            "SimpleTypeDescription",
            SimpleTypeDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SimpleTypeDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SimpleTypeDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SimpleTypeDescription"),
            SimpleTypeDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SimpleTypeDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new PortableQualifiedName.Codec().asBinaryCodec(),
            "PortableQualifiedName",
            PortableQualifiedName.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PortableQualifiedName.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new PortableQualifiedName.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PortableQualifiedName"),
            PortableQualifiedName.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PortableQualifiedName.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new PortableNodeId.Codec().asBinaryCodec(),
            "PortableNodeId",
            PortableNodeId.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PortableNodeId.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new PortableNodeId.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PortableNodeId"),
            PortableNodeId.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PortableNodeId.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new UnsignedRationalNumber.Codec().asBinaryCodec(),
            "UnsignedRationalNumber",
            UnsignedRationalNumber.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UnsignedRationalNumber.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new UnsignedRationalNumber.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UnsignedRationalNumber"),
            UnsignedRationalNumber.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UnsignedRationalNumber.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new FieldMetaData.Codec().asBinaryCodec(),
            "FieldMetaData",
            FieldMetaData.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FieldMetaData.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new FieldMetaData.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "FieldMetaData"),
            FieldMetaData.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FieldMetaData.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ConfigurationVersionDataType.Codec().asBinaryCodec(),
            "ConfigurationVersionDataType",
            ConfigurationVersionDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ConfigurationVersionDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ConfigurationVersionDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ConfigurationVersionDataType"),
            ConfigurationVersionDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ConfigurationVersionDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new PublishedDataSetDataType.Codec().asBinaryCodec(),
            "PublishedDataSetDataType",
            PublishedDataSetDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishedDataSetDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new PublishedDataSetDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PublishedDataSetDataType"),
            PublishedDataSetDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishedDataSetDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new PublishedDataItemsDataType.Codec().asBinaryCodec(),
            "PublishedDataItemsDataType",
            PublishedDataItemsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishedDataItemsDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new PublishedDataItemsDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PublishedDataItemsDataType"),
            PublishedDataItemsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishedDataItemsDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new PublishedEventsDataType.Codec().asBinaryCodec(),
            "PublishedEventsDataType",
            PublishedEventsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishedEventsDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new PublishedEventsDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PublishedEventsDataType"),
            PublishedEventsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishedEventsDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new PublishedDataSetCustomSourceDataType.Codec().asBinaryCodec(),
            "PublishedDataSetCustomSourceDataType",
            PublishedDataSetCustomSourceDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishedDataSetCustomSourceDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new PublishedDataSetCustomSourceDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PublishedDataSetCustomSourceDataType"),
            PublishedDataSetCustomSourceDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishedDataSetCustomSourceDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new PublishedVariableDataType.Codec().asBinaryCodec(),
            "PublishedVariableDataType",
            PublishedVariableDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishedVariableDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new PublishedVariableDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PublishedVariableDataType"),
            PublishedVariableDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishedVariableDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DataSetWriterDataType.Codec().asBinaryCodec(),
            "DataSetWriterDataType",
            DataSetWriterDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataSetWriterDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DataSetWriterDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DataSetWriterDataType"),
            DataSetWriterDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataSetWriterDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new BrokerDataSetWriterTransportDataType.Codec().asBinaryCodec(),
            "BrokerDataSetWriterTransportDataType",
            BrokerDataSetWriterTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrokerDataSetWriterTransportDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new BrokerDataSetWriterTransportDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrokerDataSetWriterTransportDataType"),
            BrokerDataSetWriterTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrokerDataSetWriterTransportDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new UadpDataSetWriterMessageDataType.Codec().asBinaryCodec(),
            "UadpDataSetWriterMessageDataType",
            UadpDataSetWriterMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UadpDataSetWriterMessageDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new UadpDataSetWriterMessageDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UadpDataSetWriterMessageDataType"),
            UadpDataSetWriterMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UadpDataSetWriterMessageDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new JsonDataSetWriterMessageDataType.Codec().asBinaryCodec(),
            "JsonDataSetWriterMessageDataType",
            JsonDataSetWriterMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            JsonDataSetWriterMessageDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new JsonDataSetWriterMessageDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "JsonDataSetWriterMessageDataType"),
            JsonDataSetWriterMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            JsonDataSetWriterMessageDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new WriterGroupDataType.Codec().asBinaryCodec(),
            "WriterGroupDataType",
            WriterGroupDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            WriterGroupDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new WriterGroupDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "WriterGroupDataType"),
            WriterGroupDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            WriterGroupDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ReaderGroupDataType.Codec().asBinaryCodec(),
            "ReaderGroupDataType",
            ReaderGroupDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReaderGroupDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ReaderGroupDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReaderGroupDataType"),
            ReaderGroupDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReaderGroupDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DatagramWriterGroupTransportDataType.Codec().asBinaryCodec(),
            "DatagramWriterGroupTransportDataType",
            DatagramWriterGroupTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DatagramWriterGroupTransportDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DatagramWriterGroupTransportDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DatagramWriterGroupTransportDataType"),
            DatagramWriterGroupTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DatagramWriterGroupTransportDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DatagramWriterGroupTransport2DataType.Codec().asBinaryCodec(),
            "DatagramWriterGroupTransport2DataType",
            DatagramWriterGroupTransport2DataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DatagramWriterGroupTransport2DataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DatagramWriterGroupTransport2DataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DatagramWriterGroupTransport2DataType"),
            DatagramWriterGroupTransport2DataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DatagramWriterGroupTransport2DataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new BrokerWriterGroupTransportDataType.Codec().asBinaryCodec(),
            "BrokerWriterGroupTransportDataType",
            BrokerWriterGroupTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrokerWriterGroupTransportDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new BrokerWriterGroupTransportDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrokerWriterGroupTransportDataType"),
            BrokerWriterGroupTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrokerWriterGroupTransportDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new UadpWriterGroupMessageDataType.Codec().asBinaryCodec(),
            "UadpWriterGroupMessageDataType",
            UadpWriterGroupMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UadpWriterGroupMessageDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new UadpWriterGroupMessageDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UadpWriterGroupMessageDataType"),
            UadpWriterGroupMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UadpWriterGroupMessageDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new JsonWriterGroupMessageDataType.Codec().asBinaryCodec(),
            "JsonWriterGroupMessageDataType",
            JsonWriterGroupMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            JsonWriterGroupMessageDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new JsonWriterGroupMessageDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "JsonWriterGroupMessageDataType"),
            JsonWriterGroupMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            JsonWriterGroupMessageDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new PubSubConnectionDataType.Codec().asBinaryCodec(),
            "PubSubConnectionDataType",
            PubSubConnectionDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConnectionDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new PubSubConnectionDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PubSubConnectionDataType"),
            PubSubConnectionDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConnectionDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DatagramConnectionTransportDataType.Codec().asBinaryCodec(),
            "DatagramConnectionTransportDataType",
            DatagramConnectionTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DatagramConnectionTransportDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DatagramConnectionTransportDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DatagramConnectionTransportDataType"),
            DatagramConnectionTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DatagramConnectionTransportDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DatagramConnectionTransport2DataType.Codec().asBinaryCodec(),
            "DatagramConnectionTransport2DataType",
            DatagramConnectionTransport2DataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DatagramConnectionTransport2DataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DatagramConnectionTransport2DataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DatagramConnectionTransport2DataType"),
            DatagramConnectionTransport2DataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DatagramConnectionTransport2DataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new BrokerConnectionTransportDataType.Codec().asBinaryCodec(),
            "BrokerConnectionTransportDataType",
            BrokerConnectionTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrokerConnectionTransportDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new BrokerConnectionTransportDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrokerConnectionTransportDataType"),
            BrokerConnectionTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrokerConnectionTransportDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new NetworkAddressUrlDataType.Codec().asBinaryCodec(),
            "NetworkAddressUrlDataType",
            NetworkAddressUrlDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NetworkAddressUrlDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new NetworkAddressUrlDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "NetworkAddressUrlDataType"),
            NetworkAddressUrlDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NetworkAddressUrlDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DataSetReaderDataType.Codec().asBinaryCodec(),
            "DataSetReaderDataType",
            DataSetReaderDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataSetReaderDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DataSetReaderDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DataSetReaderDataType"),
            DataSetReaderDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataSetReaderDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DatagramDataSetReaderTransportDataType.Codec().asBinaryCodec(),
            "DatagramDataSetReaderTransportDataType",
            DatagramDataSetReaderTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DatagramDataSetReaderTransportDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DatagramDataSetReaderTransportDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DatagramDataSetReaderTransportDataType"),
            DatagramDataSetReaderTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DatagramDataSetReaderTransportDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new BrokerDataSetReaderTransportDataType.Codec().asBinaryCodec(),
            "BrokerDataSetReaderTransportDataType",
            BrokerDataSetReaderTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrokerDataSetReaderTransportDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new BrokerDataSetReaderTransportDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrokerDataSetReaderTransportDataType"),
            BrokerDataSetReaderTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrokerDataSetReaderTransportDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new UadpDataSetReaderMessageDataType.Codec().asBinaryCodec(),
            "UadpDataSetReaderMessageDataType",
            UadpDataSetReaderMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UadpDataSetReaderMessageDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new UadpDataSetReaderMessageDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UadpDataSetReaderMessageDataType"),
            UadpDataSetReaderMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UadpDataSetReaderMessageDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new JsonDataSetReaderMessageDataType.Codec().asBinaryCodec(),
            "JsonDataSetReaderMessageDataType",
            JsonDataSetReaderMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            JsonDataSetReaderMessageDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new JsonDataSetReaderMessageDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "JsonDataSetReaderMessageDataType"),
            JsonDataSetReaderMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            JsonDataSetReaderMessageDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new TargetVariablesDataType.Codec().asBinaryCodec(),
            "TargetVariablesDataType",
            TargetVariablesDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TargetVariablesDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new TargetVariablesDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TargetVariablesDataType"),
            TargetVariablesDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TargetVariablesDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SubscribedDataSetMirrorDataType.Codec().asBinaryCodec(),
            "SubscribedDataSetMirrorDataType",
            SubscribedDataSetMirrorDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SubscribedDataSetMirrorDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SubscribedDataSetMirrorDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SubscribedDataSetMirrorDataType"),
            SubscribedDataSetMirrorDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SubscribedDataSetMirrorDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new StandaloneSubscribedDataSetRefDataType.Codec().asBinaryCodec(),
            "StandaloneSubscribedDataSetRefDataType",
            StandaloneSubscribedDataSetRefDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StandaloneSubscribedDataSetRefDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new StandaloneSubscribedDataSetRefDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "StandaloneSubscribedDataSetRefDataType"),
            StandaloneSubscribedDataSetRefDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StandaloneSubscribedDataSetRefDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new StandaloneSubscribedDataSetDataType.Codec().asBinaryCodec(),
            "StandaloneSubscribedDataSetDataType",
            StandaloneSubscribedDataSetDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StandaloneSubscribedDataSetDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new StandaloneSubscribedDataSetDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "StandaloneSubscribedDataSetDataType"),
            StandaloneSubscribedDataSetDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StandaloneSubscribedDataSetDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new FieldTargetDataType.Codec().asBinaryCodec(),
            "FieldTargetDataType",
            FieldTargetDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FieldTargetDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new FieldTargetDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "FieldTargetDataType"),
            FieldTargetDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FieldTargetDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new PubSubConfigurationDataType.Codec().asBinaryCodec(),
            "PubSubConfigurationDataType",
            PubSubConfigurationDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfigurationDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new PubSubConfigurationDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PubSubConfigurationDataType"),
            PubSubConfigurationDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfigurationDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new PubSubConfiguration2DataType.Codec().asBinaryCodec(),
            "PubSubConfiguration2DataType",
            PubSubConfiguration2DataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfiguration2DataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new PubSubConfiguration2DataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PubSubConfiguration2DataType"),
            PubSubConfiguration2DataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfiguration2DataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SecurityGroupDataType.Codec().asBinaryCodec(),
            "SecurityGroupDataType",
            SecurityGroupDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SecurityGroupDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SecurityGroupDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SecurityGroupDataType"),
            SecurityGroupDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SecurityGroupDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new PubSubKeyPushTargetDataType.Codec().asBinaryCodec(),
            "PubSubKeyPushTargetDataType",
            PubSubKeyPushTargetDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubKeyPushTargetDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new PubSubKeyPushTargetDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PubSubKeyPushTargetDataType"),
            PubSubKeyPushTargetDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubKeyPushTargetDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new TransmitQosPriorityDataType.Codec().asBinaryCodec(),
            "TransmitQosPriorityDataType",
            TransmitQosPriorityDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TransmitQosPriorityDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new TransmitQosPriorityDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TransmitQosPriorityDataType"),
            TransmitQosPriorityDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TransmitQosPriorityDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ReceiveQosPriorityDataType.Codec().asBinaryCodec(),
            "ReceiveQosPriorityDataType",
            ReceiveQosPriorityDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReceiveQosPriorityDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ReceiveQosPriorityDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReceiveQosPriorityDataType"),
            ReceiveQosPriorityDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReceiveQosPriorityDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new PubSubConfigurationRefDataType.Codec().asBinaryCodec(),
            "PubSubConfigurationRefDataType",
            PubSubConfigurationRefDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfigurationRefDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new PubSubConfigurationRefDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PubSubConfigurationRefDataType"),
            PubSubConfigurationRefDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfigurationRefDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new PubSubConfigurationValueDataType.Codec().asBinaryCodec(),
            "PubSubConfigurationValueDataType",
            PubSubConfigurationValueDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfigurationValueDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new PubSubConfigurationValueDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PubSubConfigurationValueDataType"),
            PubSubConfigurationValueDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfigurationValueDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new AliasNameDataType.Codec().asBinaryCodec(),
            "AliasNameDataType",
            AliasNameDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AliasNameDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new AliasNameDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AliasNameDataType"),
            AliasNameDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AliasNameDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new UserManagementDataType.Codec().asBinaryCodec(),
            "UserManagementDataType",
            UserManagementDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UserManagementDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new UserManagementDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UserManagementDataType"),
            UserManagementDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UserManagementDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new PriorityMappingEntryType.Codec().asBinaryCodec(),
            "PriorityMappingEntryType",
            PriorityMappingEntryType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PriorityMappingEntryType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new PriorityMappingEntryType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PriorityMappingEntryType"),
            PriorityMappingEntryType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PriorityMappingEntryType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new RolePermissionType.Codec().asBinaryCodec(),
            "RolePermissionType",
            RolePermissionType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RolePermissionType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new RolePermissionType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RolePermissionType"),
            RolePermissionType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RolePermissionType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new StructureDefinition.Codec().asBinaryCodec(),
            "StructureDefinition",
            StructureDefinition.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StructureDefinition.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new StructureDefinition.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "StructureDefinition"),
            StructureDefinition.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StructureDefinition.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new EnumDefinition.Codec().asBinaryCodec(),
            "EnumDefinition",
            EnumDefinition.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EnumDefinition.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new EnumDefinition.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EnumDefinition"),
            EnumDefinition.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EnumDefinition.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new StructureField.Codec().asBinaryCodec(),
            "StructureField",
            StructureField.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StructureField.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new StructureField.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "StructureField"),
            StructureField.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StructureField.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new Argument.Codec().asBinaryCodec(),
            "Argument",
            Argument.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            Argument.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new Argument.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "Argument"),
            Argument.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            Argument.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new EnumValueType.Codec().asBinaryCodec(),
            "EnumValueType",
            EnumValueType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EnumValueType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new EnumValueType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EnumValueType"),
            EnumValueType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EnumValueType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new EnumField.Codec().asBinaryCodec(),
            "EnumField",
            EnumField.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EnumField.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new EnumField.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EnumField"),
            EnumField.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EnumField.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new TimeZoneDataType.Codec().asBinaryCodec(),
            "TimeZoneDataType",
            TimeZoneDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TimeZoneDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new TimeZoneDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TimeZoneDataType"),
            TimeZoneDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TimeZoneDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ApplicationDescription.Codec().asBinaryCodec(),
            "ApplicationDescription",
            ApplicationDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ApplicationDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ApplicationDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ApplicationDescription"),
            ApplicationDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ApplicationDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ServerOnNetwork.Codec().asBinaryCodec(),
            "ServerOnNetwork",
            ServerOnNetwork.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ServerOnNetwork.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ServerOnNetwork.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ServerOnNetwork"),
            ServerOnNetwork.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ServerOnNetwork.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new UserTokenPolicy.Codec().asBinaryCodec(),
            "UserTokenPolicy",
            UserTokenPolicy.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UserTokenPolicy.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new UserTokenPolicy.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UserTokenPolicy"),
            UserTokenPolicy.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UserTokenPolicy.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new EndpointDescription.Codec().asBinaryCodec(),
            "EndpointDescription",
            EndpointDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EndpointDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new EndpointDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EndpointDescription"),
            EndpointDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EndpointDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new RegisteredServer.Codec().asBinaryCodec(),
            "RegisteredServer",
            RegisteredServer.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisteredServer.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new RegisteredServer.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RegisteredServer"),
            RegisteredServer.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisteredServer.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DiscoveryConfiguration.Codec().asBinaryCodec(),
            "DiscoveryConfiguration",
            DiscoveryConfiguration.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DiscoveryConfiguration.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DiscoveryConfiguration.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DiscoveryConfiguration"),
            DiscoveryConfiguration.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DiscoveryConfiguration.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new MdnsDiscoveryConfiguration.Codec().asBinaryCodec(),
            "MdnsDiscoveryConfiguration",
            MdnsDiscoveryConfiguration.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MdnsDiscoveryConfiguration.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new MdnsDiscoveryConfiguration.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MdnsDiscoveryConfiguration"),
            MdnsDiscoveryConfiguration.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MdnsDiscoveryConfiguration.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SignedSoftwareCertificate.Codec().asBinaryCodec(),
            "SignedSoftwareCertificate",
            SignedSoftwareCertificate.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SignedSoftwareCertificate.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SignedSoftwareCertificate.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SignedSoftwareCertificate"),
            SignedSoftwareCertificate.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SignedSoftwareCertificate.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new AnonymousIdentityToken.Codec().asBinaryCodec(),
            "AnonymousIdentityToken",
            AnonymousIdentityToken.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AnonymousIdentityToken.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new AnonymousIdentityToken.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AnonymousIdentityToken"),
            AnonymousIdentityToken.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AnonymousIdentityToken.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new UserNameIdentityToken.Codec().asBinaryCodec(),
            "UserNameIdentityToken",
            UserNameIdentityToken.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UserNameIdentityToken.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new UserNameIdentityToken.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UserNameIdentityToken"),
            UserNameIdentityToken.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UserNameIdentityToken.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new X509IdentityToken.Codec().asBinaryCodec(),
            "X509IdentityToken",
            X509IdentityToken.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            X509IdentityToken.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new X509IdentityToken.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "X509IdentityToken"),
            X509IdentityToken.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            X509IdentityToken.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new IssuedIdentityToken.Codec().asBinaryCodec(),
            "IssuedIdentityToken",
            IssuedIdentityToken.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            IssuedIdentityToken.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new IssuedIdentityToken.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "IssuedIdentityToken"),
            IssuedIdentityToken.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            IssuedIdentityToken.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new AddNodesItem.Codec().asBinaryCodec(),
            "AddNodesItem",
            AddNodesItem.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesItem.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new AddNodesItem.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AddNodesItem"),
            AddNodesItem.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesItem.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new AddReferencesItem.Codec().asBinaryCodec(),
            "AddReferencesItem",
            AddReferencesItem.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddReferencesItem.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new AddReferencesItem.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AddReferencesItem"),
            AddReferencesItem.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddReferencesItem.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DeleteNodesItem.Codec().asBinaryCodec(),
            "DeleteNodesItem",
            DeleteNodesItem.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteNodesItem.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DeleteNodesItem.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteNodesItem"),
            DeleteNodesItem.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteNodesItem.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DeleteReferencesItem.Codec().asBinaryCodec(),
            "DeleteReferencesItem",
            DeleteReferencesItem.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteReferencesItem.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DeleteReferencesItem.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteReferencesItem"),
            DeleteReferencesItem.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteReferencesItem.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new RelativePathElement.Codec().asBinaryCodec(),
            "RelativePathElement",
            RelativePathElement.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RelativePathElement.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new RelativePathElement.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RelativePathElement"),
            RelativePathElement.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RelativePathElement.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new RelativePath.Codec().asBinaryCodec(),
            "RelativePath",
            RelativePath.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RelativePath.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new RelativePath.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RelativePath"),
            RelativePath.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RelativePath.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new EndpointConfiguration.Codec().asBinaryCodec(),
            "EndpointConfiguration",
            EndpointConfiguration.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EndpointConfiguration.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new EndpointConfiguration.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EndpointConfiguration"),
            EndpointConfiguration.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EndpointConfiguration.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ContentFilterElement.Codec().asBinaryCodec(),
            "ContentFilterElement",
            ContentFilterElement.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilterElement.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ContentFilterElement.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ContentFilterElement"),
            ContentFilterElement.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilterElement.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ContentFilter.Codec().asBinaryCodec(),
            "ContentFilter",
            ContentFilter.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilter.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ContentFilter.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ContentFilter"),
            ContentFilter.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilter.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new FilterOperand.Codec().asBinaryCodec(),
            "FilterOperand",
            FilterOperand.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FilterOperand.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new FilterOperand.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "FilterOperand"),
            FilterOperand.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FilterOperand.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ElementOperand.Codec().asBinaryCodec(),
            "ElementOperand",
            ElementOperand.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ElementOperand.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ElementOperand.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ElementOperand"),
            ElementOperand.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ElementOperand.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new LiteralOperand.Codec().asBinaryCodec(),
            "LiteralOperand",
            LiteralOperand.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            LiteralOperand.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new LiteralOperand.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "LiteralOperand"),
            LiteralOperand.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            LiteralOperand.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new AttributeOperand.Codec().asBinaryCodec(),
            "AttributeOperand",
            AttributeOperand.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AttributeOperand.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new AttributeOperand.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AttributeOperand"),
            AttributeOperand.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AttributeOperand.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SimpleAttributeOperand.Codec().asBinaryCodec(),
            "SimpleAttributeOperand",
            SimpleAttributeOperand.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SimpleAttributeOperand.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SimpleAttributeOperand.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SimpleAttributeOperand"),
            SimpleAttributeOperand.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SimpleAttributeOperand.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new HistoryEvent.Codec().asBinaryCodec(),
            "HistoryEvent",
            HistoryEvent.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryEvent.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new HistoryEvent.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryEvent"),
            HistoryEvent.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryEvent.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new MonitoringFilter.Codec().asBinaryCodec(),
            "MonitoringFilter",
            MonitoringFilter.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoringFilter.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new MonitoringFilter.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoringFilter"),
            MonitoringFilter.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoringFilter.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new EventFilter.Codec().asBinaryCodec(),
            "EventFilter",
            EventFilter.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EventFilter.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new EventFilter.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EventFilter"),
            EventFilter.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EventFilter.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DataChangeFilter.Codec().asBinaryCodec(),
            "DataChangeFilter",
            DataChangeFilter.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataChangeFilter.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DataChangeFilter.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DataChangeFilter"),
            DataChangeFilter.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataChangeFilter.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new AggregateFilter.Codec().asBinaryCodec(),
            "AggregateFilter",
            AggregateFilter.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AggregateFilter.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new AggregateFilter.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AggregateFilter"),
            AggregateFilter.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AggregateFilter.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new AggregateConfiguration.Codec().asBinaryCodec(),
            "AggregateConfiguration",
            AggregateConfiguration.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AggregateConfiguration.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new AggregateConfiguration.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AggregateConfiguration"),
            AggregateConfiguration.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AggregateConfiguration.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new HistoryEventFieldList.Codec().asBinaryCodec(),
            "HistoryEventFieldList",
            HistoryEventFieldList.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryEventFieldList.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new HistoryEventFieldList.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryEventFieldList"),
            HistoryEventFieldList.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryEventFieldList.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new BuildInfo.Codec().asBinaryCodec(),
            "BuildInfo",
            BuildInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BuildInfo.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new BuildInfo.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BuildInfo"),
            BuildInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BuildInfo.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new RedundantServerDataType.Codec().asBinaryCodec(),
            "RedundantServerDataType",
            RedundantServerDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RedundantServerDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new RedundantServerDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RedundantServerDataType"),
            RedundantServerDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RedundantServerDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new EndpointUrlListDataType.Codec().asBinaryCodec(),
            "EndpointUrlListDataType",
            EndpointUrlListDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EndpointUrlListDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new EndpointUrlListDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EndpointUrlListDataType"),
            EndpointUrlListDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EndpointUrlListDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new NetworkGroupDataType.Codec().asBinaryCodec(),
            "NetworkGroupDataType",
            NetworkGroupDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NetworkGroupDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new NetworkGroupDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "NetworkGroupDataType"),
            NetworkGroupDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NetworkGroupDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SamplingIntervalDiagnosticsDataType.Codec().asBinaryCodec(),
            "SamplingIntervalDiagnosticsDataType",
            SamplingIntervalDiagnosticsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SamplingIntervalDiagnosticsDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SamplingIntervalDiagnosticsDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SamplingIntervalDiagnosticsDataType"),
            SamplingIntervalDiagnosticsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SamplingIntervalDiagnosticsDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ServerDiagnosticsSummaryDataType.Codec().asBinaryCodec(),
            "ServerDiagnosticsSummaryDataType",
            ServerDiagnosticsSummaryDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ServerDiagnosticsSummaryDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ServerDiagnosticsSummaryDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ServerDiagnosticsSummaryDataType"),
            ServerDiagnosticsSummaryDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ServerDiagnosticsSummaryDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ServerStatusDataType.Codec().asBinaryCodec(),
            "ServerStatusDataType",
            ServerStatusDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ServerStatusDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ServerStatusDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ServerStatusDataType"),
            ServerStatusDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ServerStatusDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SessionDiagnosticsDataType.Codec().asBinaryCodec(),
            "SessionDiagnosticsDataType",
            SessionDiagnosticsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SessionDiagnosticsDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SessionDiagnosticsDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SessionDiagnosticsDataType"),
            SessionDiagnosticsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SessionDiagnosticsDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SessionSecurityDiagnosticsDataType.Codec().asBinaryCodec(),
            "SessionSecurityDiagnosticsDataType",
            SessionSecurityDiagnosticsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SessionSecurityDiagnosticsDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SessionSecurityDiagnosticsDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SessionSecurityDiagnosticsDataType"),
            SessionSecurityDiagnosticsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SessionSecurityDiagnosticsDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ServiceCounterDataType.Codec().asBinaryCodec(),
            "ServiceCounterDataType",
            ServiceCounterDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ServiceCounterDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ServiceCounterDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ServiceCounterDataType"),
            ServiceCounterDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ServiceCounterDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new StatusResult.Codec().asBinaryCodec(),
            "StatusResult",
            StatusResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StatusResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new StatusResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "StatusResult"),
            StatusResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StatusResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SubscriptionDiagnosticsDataType.Codec().asBinaryCodec(),
            "SubscriptionDiagnosticsDataType",
            SubscriptionDiagnosticsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SubscriptionDiagnosticsDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SubscriptionDiagnosticsDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SubscriptionDiagnosticsDataType"),
            SubscriptionDiagnosticsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SubscriptionDiagnosticsDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ModelChangeStructureDataType.Codec().asBinaryCodec(),
            "ModelChangeStructureDataType",
            ModelChangeStructureDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModelChangeStructureDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ModelChangeStructureDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ModelChangeStructureDataType"),
            ModelChangeStructureDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModelChangeStructureDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SemanticChangeStructureDataType.Codec().asBinaryCodec(),
            "SemanticChangeStructureDataType",
            SemanticChangeStructureDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SemanticChangeStructureDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SemanticChangeStructureDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SemanticChangeStructureDataType"),
            SemanticChangeStructureDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SemanticChangeStructureDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new Range.Codec().asBinaryCodec(),
            "Range",
            Range.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            Range.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new Range.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "Range"),
            Range.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            Range.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new EUInformation.Codec().asBinaryCodec(),
            "EUInformation",
            EUInformation.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EUInformation.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new EUInformation.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EUInformation"),
            EUInformation.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EUInformation.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ComplexNumberType.Codec().asBinaryCodec(),
            "ComplexNumberType",
            ComplexNumberType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ComplexNumberType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ComplexNumberType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ComplexNumberType"),
            ComplexNumberType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ComplexNumberType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DoubleComplexNumberType.Codec().asBinaryCodec(),
            "DoubleComplexNumberType",
            DoubleComplexNumberType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DoubleComplexNumberType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DoubleComplexNumberType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DoubleComplexNumberType"),
            DoubleComplexNumberType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DoubleComplexNumberType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new AxisInformation.Codec().asBinaryCodec(),
            "AxisInformation",
            AxisInformation.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AxisInformation.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new AxisInformation.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AxisInformation"),
            AxisInformation.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AxisInformation.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new XVType.Codec().asBinaryCodec(),
            "XVType",
            XVType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            XVType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new XVType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "XVType"),
            XVType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            XVType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ProgramDiagnosticDataType.Codec().asBinaryCodec(),
            "ProgramDiagnosticDataType",
            ProgramDiagnosticDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ProgramDiagnosticDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ProgramDiagnosticDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ProgramDiagnosticDataType"),
            ProgramDiagnosticDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ProgramDiagnosticDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ProgramDiagnostic2DataType.Codec().asBinaryCodec(),
            "ProgramDiagnostic2DataType",
            ProgramDiagnostic2DataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ProgramDiagnostic2DataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ProgramDiagnostic2DataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ProgramDiagnostic2DataType"),
            ProgramDiagnostic2DataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ProgramDiagnostic2DataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new Annotation.Codec().asBinaryCodec(),
            "Annotation",
            Annotation.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            Annotation.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new Annotation.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "Annotation"),
            Annotation.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            Annotation.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DecimalDataType.Codec().asBinaryCodec(),
            "DecimalDataType",
            DecimalDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DecimalDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DecimalDataType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DecimalDataType"),
            DecimalDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DecimalDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new Node.Codec().asBinaryCodec(),
            "Node",
            Node.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            Node.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new Node.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "Node"),
            Node.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            Node.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new InstanceNode.Codec().asBinaryCodec(),
            "InstanceNode",
            InstanceNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            InstanceNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new InstanceNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "InstanceNode"),
            InstanceNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            InstanceNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ObjectNode.Codec().asBinaryCodec(),
            "ObjectNode",
            ObjectNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ObjectNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ObjectNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ObjectNode"),
            ObjectNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ObjectNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new VariableNode.Codec().asBinaryCodec(),
            "VariableNode",
            VariableNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            VariableNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new VariableNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "VariableNode"),
            VariableNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            VariableNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new MethodNode.Codec().asBinaryCodec(),
            "MethodNode",
            MethodNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MethodNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new MethodNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MethodNode"),
            MethodNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MethodNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ViewNode.Codec().asBinaryCodec(),
            "ViewNode",
            ViewNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ViewNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ViewNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ViewNode"),
            ViewNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ViewNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new TypeNode.Codec().asBinaryCodec(),
            "TypeNode",
            TypeNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TypeNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new TypeNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TypeNode"),
            TypeNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TypeNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ObjectTypeNode.Codec().asBinaryCodec(),
            "ObjectTypeNode",
            ObjectTypeNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ObjectTypeNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ObjectTypeNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ObjectTypeNode"),
            ObjectTypeNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ObjectTypeNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new VariableTypeNode.Codec().asBinaryCodec(),
            "VariableTypeNode",
            VariableTypeNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            VariableTypeNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new VariableTypeNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "VariableTypeNode"),
            VariableTypeNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            VariableTypeNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ReferenceTypeNode.Codec().asBinaryCodec(),
            "ReferenceTypeNode",
            ReferenceTypeNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceTypeNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ReferenceTypeNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReferenceTypeNode"),
            ReferenceTypeNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceTypeNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DataTypeNode.Codec().asBinaryCodec(),
            "DataTypeNode",
            DataTypeNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataTypeNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DataTypeNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DataTypeNode"),
            DataTypeNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataTypeNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ReferenceNode.Codec().asBinaryCodec(),
            "ReferenceNode",
            ReferenceNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ReferenceNode.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReferenceNode"),
            ReferenceNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new RequestHeader.Codec().asBinaryCodec(),
            "RequestHeader",
            RequestHeader.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RequestHeader.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new RequestHeader.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RequestHeader"),
            RequestHeader.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RequestHeader.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ResponseHeader.Codec().asBinaryCodec(),
            "ResponseHeader",
            ResponseHeader.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ResponseHeader.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ResponseHeader.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ResponseHeader"),
            ResponseHeader.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ResponseHeader.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ServiceFault.Codec().asBinaryCodec(),
            "ServiceFault",
            ServiceFault.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ServiceFault.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ServiceFault.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ServiceFault"),
            ServiceFault.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ServiceFault.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SessionlessInvokeRequestType.Codec().asBinaryCodec(),
            "SessionlessInvokeRequestType",
            SessionlessInvokeRequestType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SessionlessInvokeRequestType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SessionlessInvokeRequestType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SessionlessInvokeRequestType"),
            SessionlessInvokeRequestType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SessionlessInvokeRequestType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SessionlessInvokeResponseType.Codec().asBinaryCodec(),
            "SessionlessInvokeResponseType",
            SessionlessInvokeResponseType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SessionlessInvokeResponseType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SessionlessInvokeResponseType.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SessionlessInvokeResponseType"),
            SessionlessInvokeResponseType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SessionlessInvokeResponseType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new FindServersRequest.Codec().asBinaryCodec(),
            "FindServersRequest",
            FindServersRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FindServersRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new FindServersRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "FindServersRequest"),
            FindServersRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FindServersRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new FindServersResponse.Codec().asBinaryCodec(),
            "FindServersResponse",
            FindServersResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FindServersResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new FindServersResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "FindServersResponse"),
            FindServersResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FindServersResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new FindServersOnNetworkRequest.Codec().asBinaryCodec(),
            "FindServersOnNetworkRequest",
            FindServersOnNetworkRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FindServersOnNetworkRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new FindServersOnNetworkRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "FindServersOnNetworkRequest"),
            FindServersOnNetworkRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FindServersOnNetworkRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new FindServersOnNetworkResponse.Codec().asBinaryCodec(),
            "FindServersOnNetworkResponse",
            FindServersOnNetworkResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FindServersOnNetworkResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new FindServersOnNetworkResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "FindServersOnNetworkResponse"),
            FindServersOnNetworkResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FindServersOnNetworkResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new GetEndpointsRequest.Codec().asBinaryCodec(),
            "GetEndpointsRequest",
            GetEndpointsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            GetEndpointsRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new GetEndpointsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "GetEndpointsRequest"),
            GetEndpointsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            GetEndpointsRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new GetEndpointsResponse.Codec().asBinaryCodec(),
            "GetEndpointsResponse",
            GetEndpointsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            GetEndpointsResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new GetEndpointsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "GetEndpointsResponse"),
            GetEndpointsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            GetEndpointsResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new RegisterServerRequest.Codec().asBinaryCodec(),
            "RegisterServerRequest",
            RegisterServerRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServerRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new RegisterServerRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RegisterServerRequest"),
            RegisterServerRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServerRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new RegisterServerResponse.Codec().asBinaryCodec(),
            "RegisterServerResponse",
            RegisterServerResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServerResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new RegisterServerResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RegisterServerResponse"),
            RegisterServerResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServerResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new RegisterServer2Request.Codec().asBinaryCodec(),
            "RegisterServer2Request",
            RegisterServer2Request.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServer2Request.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new RegisterServer2Request.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RegisterServer2Request"),
            RegisterServer2Request.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServer2Request.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new RegisterServer2Response.Codec().asBinaryCodec(),
            "RegisterServer2Response",
            RegisterServer2Response.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServer2Response.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new RegisterServer2Response.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RegisterServer2Response"),
            RegisterServer2Response.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServer2Response.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ChannelSecurityToken.Codec().asBinaryCodec(),
            "ChannelSecurityToken",
            ChannelSecurityToken.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ChannelSecurityToken.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ChannelSecurityToken.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ChannelSecurityToken"),
            ChannelSecurityToken.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ChannelSecurityToken.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new OpenSecureChannelRequest.Codec().asBinaryCodec(),
            "OpenSecureChannelRequest",
            OpenSecureChannelRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            OpenSecureChannelRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new OpenSecureChannelRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "OpenSecureChannelRequest"),
            OpenSecureChannelRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            OpenSecureChannelRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new OpenSecureChannelResponse.Codec().asBinaryCodec(),
            "OpenSecureChannelResponse",
            OpenSecureChannelResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            OpenSecureChannelResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new OpenSecureChannelResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "OpenSecureChannelResponse"),
            OpenSecureChannelResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            OpenSecureChannelResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CloseSecureChannelRequest.Codec().asBinaryCodec(),
            "CloseSecureChannelRequest",
            CloseSecureChannelRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CloseSecureChannelRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CloseSecureChannelRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CloseSecureChannelRequest"),
            CloseSecureChannelRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CloseSecureChannelRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CloseSecureChannelResponse.Codec().asBinaryCodec(),
            "CloseSecureChannelResponse",
            CloseSecureChannelResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CloseSecureChannelResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CloseSecureChannelResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CloseSecureChannelResponse"),
            CloseSecureChannelResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CloseSecureChannelResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SignatureData.Codec().asBinaryCodec(),
            "SignatureData",
            SignatureData.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SignatureData.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SignatureData.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SignatureData"),
            SignatureData.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SignatureData.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CreateSessionRequest.Codec().asBinaryCodec(),
            "CreateSessionRequest",
            CreateSessionRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateSessionRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CreateSessionRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CreateSessionRequest"),
            CreateSessionRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateSessionRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CreateSessionResponse.Codec().asBinaryCodec(),
            "CreateSessionResponse",
            CreateSessionResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateSessionResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CreateSessionResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CreateSessionResponse"),
            CreateSessionResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateSessionResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ActivateSessionRequest.Codec().asBinaryCodec(),
            "ActivateSessionRequest",
            ActivateSessionRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ActivateSessionRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ActivateSessionRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ActivateSessionRequest"),
            ActivateSessionRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ActivateSessionRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ActivateSessionResponse.Codec().asBinaryCodec(),
            "ActivateSessionResponse",
            ActivateSessionResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ActivateSessionResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ActivateSessionResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ActivateSessionResponse"),
            ActivateSessionResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ActivateSessionResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CloseSessionRequest.Codec().asBinaryCodec(),
            "CloseSessionRequest",
            CloseSessionRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CloseSessionRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CloseSessionRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CloseSessionRequest"),
            CloseSessionRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CloseSessionRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CloseSessionResponse.Codec().asBinaryCodec(),
            "CloseSessionResponse",
            CloseSessionResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CloseSessionResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CloseSessionResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CloseSessionResponse"),
            CloseSessionResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CloseSessionResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CancelRequest.Codec().asBinaryCodec(),
            "CancelRequest",
            CancelRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CancelRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CancelRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CancelRequest"),
            CancelRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CancelRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CancelResponse.Codec().asBinaryCodec(),
            "CancelResponse",
            CancelResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CancelResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CancelResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CancelResponse"),
            CancelResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CancelResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new NodeAttributes.Codec().asBinaryCodec(),
            "NodeAttributes",
            NodeAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NodeAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new NodeAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "NodeAttributes"),
            NodeAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NodeAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ObjectAttributes.Codec().asBinaryCodec(),
            "ObjectAttributes",
            ObjectAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ObjectAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ObjectAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ObjectAttributes"),
            ObjectAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ObjectAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new VariableAttributes.Codec().asBinaryCodec(),
            "VariableAttributes",
            VariableAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            VariableAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new VariableAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "VariableAttributes"),
            VariableAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            VariableAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new MethodAttributes.Codec().asBinaryCodec(),
            "MethodAttributes",
            MethodAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MethodAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new MethodAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MethodAttributes"),
            MethodAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MethodAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ObjectTypeAttributes.Codec().asBinaryCodec(),
            "ObjectTypeAttributes",
            ObjectTypeAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ObjectTypeAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ObjectTypeAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ObjectTypeAttributes"),
            ObjectTypeAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ObjectTypeAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new VariableTypeAttributes.Codec().asBinaryCodec(),
            "VariableTypeAttributes",
            VariableTypeAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            VariableTypeAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new VariableTypeAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "VariableTypeAttributes"),
            VariableTypeAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            VariableTypeAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ReferenceTypeAttributes.Codec().asBinaryCodec(),
            "ReferenceTypeAttributes",
            ReferenceTypeAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceTypeAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ReferenceTypeAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReferenceTypeAttributes"),
            ReferenceTypeAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceTypeAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DataTypeAttributes.Codec().asBinaryCodec(),
            "DataTypeAttributes",
            DataTypeAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataTypeAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DataTypeAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DataTypeAttributes"),
            DataTypeAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataTypeAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ViewAttributes.Codec().asBinaryCodec(),
            "ViewAttributes",
            ViewAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ViewAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ViewAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ViewAttributes"),
            ViewAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ViewAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new GenericAttributes.Codec().asBinaryCodec(),
            "GenericAttributes",
            GenericAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            GenericAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new GenericAttributes.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "GenericAttributes"),
            GenericAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            GenericAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new GenericAttributeValue.Codec().asBinaryCodec(),
            "GenericAttributeValue",
            GenericAttributeValue.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            GenericAttributeValue.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new GenericAttributeValue.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "GenericAttributeValue"),
            GenericAttributeValue.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            GenericAttributeValue.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new AddNodesResult.Codec().asBinaryCodec(),
            "AddNodesResult",
            AddNodesResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new AddNodesResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AddNodesResult"),
            AddNodesResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new AddNodesRequest.Codec().asBinaryCodec(),
            "AddNodesRequest",
            AddNodesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new AddNodesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AddNodesRequest"),
            AddNodesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new AddNodesResponse.Codec().asBinaryCodec(),
            "AddNodesResponse",
            AddNodesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new AddNodesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AddNodesResponse"),
            AddNodesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new AddReferencesRequest.Codec().asBinaryCodec(),
            "AddReferencesRequest",
            AddReferencesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddReferencesRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new AddReferencesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AddReferencesRequest"),
            AddReferencesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddReferencesRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new AddReferencesResponse.Codec().asBinaryCodec(),
            "AddReferencesResponse",
            AddReferencesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddReferencesResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new AddReferencesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AddReferencesResponse"),
            AddReferencesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddReferencesResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DeleteNodesRequest.Codec().asBinaryCodec(),
            "DeleteNodesRequest",
            DeleteNodesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteNodesRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DeleteNodesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteNodesRequest"),
            DeleteNodesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteNodesRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DeleteNodesResponse.Codec().asBinaryCodec(),
            "DeleteNodesResponse",
            DeleteNodesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteNodesResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DeleteNodesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteNodesResponse"),
            DeleteNodesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteNodesResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DeleteReferencesRequest.Codec().asBinaryCodec(),
            "DeleteReferencesRequest",
            DeleteReferencesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteReferencesRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DeleteReferencesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteReferencesRequest"),
            DeleteReferencesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteReferencesRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DeleteReferencesResponse.Codec().asBinaryCodec(),
            "DeleteReferencesResponse",
            DeleteReferencesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteReferencesResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DeleteReferencesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteReferencesResponse"),
            DeleteReferencesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteReferencesResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ViewDescription.Codec().asBinaryCodec(),
            "ViewDescription",
            ViewDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ViewDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ViewDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ViewDescription"),
            ViewDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ViewDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new BrowseDescription.Codec().asBinaryCodec(),
            "BrowseDescription",
            BrowseDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new BrowseDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowseDescription"),
            BrowseDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ReferenceDescription.Codec().asBinaryCodec(),
            "ReferenceDescription",
            ReferenceDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ReferenceDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReferenceDescription"),
            ReferenceDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new BrowseResult.Codec().asBinaryCodec(),
            "BrowseResult",
            BrowseResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new BrowseResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowseResult"),
            BrowseResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new BrowseRequest.Codec().asBinaryCodec(),
            "BrowseRequest",
            BrowseRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new BrowseRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowseRequest"),
            BrowseRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new BrowseResponse.Codec().asBinaryCodec(),
            "BrowseResponse",
            BrowseResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new BrowseResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowseResponse"),
            BrowseResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new BrowseNextRequest.Codec().asBinaryCodec(),
            "BrowseNextRequest",
            BrowseNextRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseNextRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new BrowseNextRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowseNextRequest"),
            BrowseNextRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseNextRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new BrowseNextResponse.Codec().asBinaryCodec(),
            "BrowseNextResponse",
            BrowseNextResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseNextResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new BrowseNextResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowseNextResponse"),
            BrowseNextResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseNextResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new BrowsePath.Codec().asBinaryCodec(),
            "BrowsePath",
            BrowsePath.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowsePath.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new BrowsePath.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowsePath"),
            BrowsePath.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowsePath.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new BrowsePathTarget.Codec().asBinaryCodec(),
            "BrowsePathTarget",
            BrowsePathTarget.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowsePathTarget.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new BrowsePathTarget.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowsePathTarget"),
            BrowsePathTarget.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowsePathTarget.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new BrowsePathResult.Codec().asBinaryCodec(),
            "BrowsePathResult",
            BrowsePathResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowsePathResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new BrowsePathResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "BrowsePathResult"),
            BrowsePathResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowsePathResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new TranslateBrowsePathsToNodeIdsRequest.Codec().asBinaryCodec(),
            "TranslateBrowsePathsToNodeIdsRequest",
            TranslateBrowsePathsToNodeIdsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TranslateBrowsePathsToNodeIdsRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new TranslateBrowsePathsToNodeIdsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TranslateBrowsePathsToNodeIdsRequest"),
            TranslateBrowsePathsToNodeIdsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TranslateBrowsePathsToNodeIdsRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new TranslateBrowsePathsToNodeIdsResponse.Codec().asBinaryCodec(),
            "TranslateBrowsePathsToNodeIdsResponse",
            TranslateBrowsePathsToNodeIdsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TranslateBrowsePathsToNodeIdsResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new TranslateBrowsePathsToNodeIdsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TranslateBrowsePathsToNodeIdsResponse"),
            TranslateBrowsePathsToNodeIdsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TranslateBrowsePathsToNodeIdsResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new RegisterNodesRequest.Codec().asBinaryCodec(),
            "RegisterNodesRequest",
            RegisterNodesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterNodesRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new RegisterNodesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RegisterNodesRequest"),
            RegisterNodesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterNodesRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new RegisterNodesResponse.Codec().asBinaryCodec(),
            "RegisterNodesResponse",
            RegisterNodesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterNodesResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new RegisterNodesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RegisterNodesResponse"),
            RegisterNodesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterNodesResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new UnregisterNodesRequest.Codec().asBinaryCodec(),
            "UnregisterNodesRequest",
            UnregisterNodesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UnregisterNodesRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new UnregisterNodesRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UnregisterNodesRequest"),
            UnregisterNodesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UnregisterNodesRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new UnregisterNodesResponse.Codec().asBinaryCodec(),
            "UnregisterNodesResponse",
            UnregisterNodesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UnregisterNodesResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new UnregisterNodesResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UnregisterNodesResponse"),
            UnregisterNodesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UnregisterNodesResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new QueryDataDescription.Codec().asBinaryCodec(),
            "QueryDataDescription",
            QueryDataDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryDataDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new QueryDataDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "QueryDataDescription"),
            QueryDataDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryDataDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new NodeTypeDescription.Codec().asBinaryCodec(),
            "NodeTypeDescription",
            NodeTypeDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NodeTypeDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new NodeTypeDescription.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "NodeTypeDescription"),
            NodeTypeDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NodeTypeDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new QueryDataSet.Codec().asBinaryCodec(),
            "QueryDataSet",
            QueryDataSet.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryDataSet.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new QueryDataSet.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "QueryDataSet"),
            QueryDataSet.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryDataSet.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new NodeReference.Codec().asBinaryCodec(),
            "NodeReference",
            NodeReference.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NodeReference.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new NodeReference.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "NodeReference"),
            NodeReference.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NodeReference.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ContentFilterElementResult.Codec().asBinaryCodec(),
            "ContentFilterElementResult",
            ContentFilterElementResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilterElementResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ContentFilterElementResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ContentFilterElementResult"),
            ContentFilterElementResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilterElementResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ContentFilterResult.Codec().asBinaryCodec(),
            "ContentFilterResult",
            ContentFilterResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilterResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ContentFilterResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ContentFilterResult"),
            ContentFilterResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilterResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ParsingResult.Codec().asBinaryCodec(),
            "ParsingResult",
            ParsingResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ParsingResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ParsingResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ParsingResult"),
            ParsingResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ParsingResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new QueryFirstRequest.Codec().asBinaryCodec(),
            "QueryFirstRequest",
            QueryFirstRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryFirstRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new QueryFirstRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "QueryFirstRequest"),
            QueryFirstRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryFirstRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new QueryFirstResponse.Codec().asBinaryCodec(),
            "QueryFirstResponse",
            QueryFirstResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryFirstResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new QueryFirstResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "QueryFirstResponse"),
            QueryFirstResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryFirstResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new QueryNextRequest.Codec().asBinaryCodec(),
            "QueryNextRequest",
            QueryNextRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryNextRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new QueryNextRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "QueryNextRequest"),
            QueryNextRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryNextRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new QueryNextResponse.Codec().asBinaryCodec(),
            "QueryNextResponse",
            QueryNextResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryNextResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new QueryNextResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "QueryNextResponse"),
            QueryNextResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryNextResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ReadValueId.Codec().asBinaryCodec(),
            "ReadValueId",
            ReadValueId.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadValueId.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ReadValueId.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReadValueId"),
            ReadValueId.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadValueId.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ReadRequest.Codec().asBinaryCodec(),
            "ReadRequest",
            ReadRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ReadRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReadRequest"),
            ReadRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ReadResponse.Codec().asBinaryCodec(),
            "ReadResponse",
            ReadResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ReadResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReadResponse"),
            ReadResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new HistoryReadValueId.Codec().asBinaryCodec(),
            "HistoryReadValueId",
            HistoryReadValueId.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadValueId.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new HistoryReadValueId.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryReadValueId"),
            HistoryReadValueId.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadValueId.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new HistoryReadResult.Codec().asBinaryCodec(),
            "HistoryReadResult",
            HistoryReadResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new HistoryReadResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryReadResult"),
            HistoryReadResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new HistoryReadDetails.Codec().asBinaryCodec(),
            "HistoryReadDetails",
            HistoryReadDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new HistoryReadDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryReadDetails"),
            HistoryReadDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ReadEventDetails.Codec().asBinaryCodec(),
            "ReadEventDetails",
            ReadEventDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadEventDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ReadEventDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReadEventDetails"),
            ReadEventDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadEventDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ReadRawModifiedDetails.Codec().asBinaryCodec(),
            "ReadRawModifiedDetails",
            ReadRawModifiedDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadRawModifiedDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ReadRawModifiedDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReadRawModifiedDetails"),
            ReadRawModifiedDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadRawModifiedDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ReadProcessedDetails.Codec().asBinaryCodec(),
            "ReadProcessedDetails",
            ReadProcessedDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadProcessedDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ReadProcessedDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReadProcessedDetails"),
            ReadProcessedDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadProcessedDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ReadAtTimeDetails.Codec().asBinaryCodec(),
            "ReadAtTimeDetails",
            ReadAtTimeDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadAtTimeDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ReadAtTimeDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReadAtTimeDetails"),
            ReadAtTimeDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadAtTimeDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ReadAnnotationDataDetails.Codec().asBinaryCodec(),
            "ReadAnnotationDataDetails",
            ReadAnnotationDataDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadAnnotationDataDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ReadAnnotationDataDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ReadAnnotationDataDetails"),
            ReadAnnotationDataDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadAnnotationDataDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new HistoryData.Codec().asBinaryCodec(),
            "HistoryData",
            HistoryData.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryData.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new HistoryData.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryData"),
            HistoryData.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryData.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new HistoryModifiedData.Codec().asBinaryCodec(),
            "HistoryModifiedData",
            HistoryModifiedData.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryModifiedData.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new HistoryModifiedData.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryModifiedData"),
            HistoryModifiedData.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryModifiedData.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ModificationInfo.Codec().asBinaryCodec(),
            "ModificationInfo",
            ModificationInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModificationInfo.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ModificationInfo.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ModificationInfo"),
            ModificationInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModificationInfo.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new HistoryReadRequest.Codec().asBinaryCodec(),
            "HistoryReadRequest",
            HistoryReadRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new HistoryReadRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryReadRequest"),
            HistoryReadRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new HistoryReadResponse.Codec().asBinaryCodec(),
            "HistoryReadResponse",
            HistoryReadResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new HistoryReadResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryReadResponse"),
            HistoryReadResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new WriteValue.Codec().asBinaryCodec(),
            "WriteValue",
            WriteValue.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            WriteValue.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new WriteValue.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "WriteValue"),
            WriteValue.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            WriteValue.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new WriteRequest.Codec().asBinaryCodec(),
            "WriteRequest",
            WriteRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            WriteRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new WriteRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "WriteRequest"),
            WriteRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            WriteRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new WriteResponse.Codec().asBinaryCodec(),
            "WriteResponse",
            WriteResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            WriteResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new WriteResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "WriteResponse"),
            WriteResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            WriteResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new HistoryUpdateDetails.Codec().asBinaryCodec(),
            "HistoryUpdateDetails",
            HistoryUpdateDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new HistoryUpdateDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryUpdateDetails"),
            HistoryUpdateDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new UpdateDataDetails.Codec().asBinaryCodec(),
            "UpdateDataDetails",
            UpdateDataDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UpdateDataDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new UpdateDataDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UpdateDataDetails"),
            UpdateDataDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UpdateDataDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new UpdateStructureDataDetails.Codec().asBinaryCodec(),
            "UpdateStructureDataDetails",
            UpdateStructureDataDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UpdateStructureDataDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new UpdateStructureDataDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UpdateStructureDataDetails"),
            UpdateStructureDataDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UpdateStructureDataDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new UpdateEventDetails.Codec().asBinaryCodec(),
            "UpdateEventDetails",
            UpdateEventDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UpdateEventDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new UpdateEventDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "UpdateEventDetails"),
            UpdateEventDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UpdateEventDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DeleteRawModifiedDetails.Codec().asBinaryCodec(),
            "DeleteRawModifiedDetails",
            DeleteRawModifiedDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteRawModifiedDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DeleteRawModifiedDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteRawModifiedDetails"),
            DeleteRawModifiedDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteRawModifiedDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DeleteAtTimeDetails.Codec().asBinaryCodec(),
            "DeleteAtTimeDetails",
            DeleteAtTimeDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteAtTimeDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DeleteAtTimeDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteAtTimeDetails"),
            DeleteAtTimeDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteAtTimeDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DeleteEventDetails.Codec().asBinaryCodec(),
            "DeleteEventDetails",
            DeleteEventDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteEventDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DeleteEventDetails.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteEventDetails"),
            DeleteEventDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteEventDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new HistoryUpdateResult.Codec().asBinaryCodec(),
            "HistoryUpdateResult",
            HistoryUpdateResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new HistoryUpdateResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryUpdateResult"),
            HistoryUpdateResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new HistoryUpdateRequest.Codec().asBinaryCodec(),
            "HistoryUpdateRequest",
            HistoryUpdateRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new HistoryUpdateRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryUpdateRequest"),
            HistoryUpdateRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new HistoryUpdateResponse.Codec().asBinaryCodec(),
            "HistoryUpdateResponse",
            HistoryUpdateResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new HistoryUpdateResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "HistoryUpdateResponse"),
            HistoryUpdateResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CallMethodRequest.Codec().asBinaryCodec(),
            "CallMethodRequest",
            CallMethodRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CallMethodRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CallMethodRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CallMethodRequest"),
            CallMethodRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CallMethodRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CallMethodResult.Codec().asBinaryCodec(),
            "CallMethodResult",
            CallMethodResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CallMethodResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CallMethodResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CallMethodResult"),
            CallMethodResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CallMethodResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CallRequest.Codec().asBinaryCodec(),
            "CallRequest",
            CallRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CallRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CallRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CallRequest"),
            CallRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CallRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CallResponse.Codec().asBinaryCodec(),
            "CallResponse",
            CallResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CallResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CallResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CallResponse"),
            CallResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CallResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new MonitoringFilterResult.Codec().asBinaryCodec(),
            "MonitoringFilterResult",
            MonitoringFilterResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoringFilterResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new MonitoringFilterResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoringFilterResult"),
            MonitoringFilterResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoringFilterResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new EventFilterResult.Codec().asBinaryCodec(),
            "EventFilterResult",
            EventFilterResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EventFilterResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new EventFilterResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EventFilterResult"),
            EventFilterResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EventFilterResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new AggregateFilterResult.Codec().asBinaryCodec(),
            "AggregateFilterResult",
            AggregateFilterResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AggregateFilterResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new AggregateFilterResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "AggregateFilterResult"),
            AggregateFilterResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AggregateFilterResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new MonitoringParameters.Codec().asBinaryCodec(),
            "MonitoringParameters",
            MonitoringParameters.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoringParameters.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new MonitoringParameters.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoringParameters"),
            MonitoringParameters.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoringParameters.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new MonitoredItemCreateRequest.Codec().asBinaryCodec(),
            "MonitoredItemCreateRequest",
            MonitoredItemCreateRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemCreateRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new MonitoredItemCreateRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoredItemCreateRequest"),
            MonitoredItemCreateRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemCreateRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new MonitoredItemCreateResult.Codec().asBinaryCodec(),
            "MonitoredItemCreateResult",
            MonitoredItemCreateResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemCreateResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new MonitoredItemCreateResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoredItemCreateResult"),
            MonitoredItemCreateResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemCreateResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CreateMonitoredItemsRequest.Codec().asBinaryCodec(),
            "CreateMonitoredItemsRequest",
            CreateMonitoredItemsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateMonitoredItemsRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CreateMonitoredItemsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CreateMonitoredItemsRequest"),
            CreateMonitoredItemsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateMonitoredItemsRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CreateMonitoredItemsResponse.Codec().asBinaryCodec(),
            "CreateMonitoredItemsResponse",
            CreateMonitoredItemsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateMonitoredItemsResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CreateMonitoredItemsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CreateMonitoredItemsResponse"),
            CreateMonitoredItemsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateMonitoredItemsResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new MonitoredItemModifyRequest.Codec().asBinaryCodec(),
            "MonitoredItemModifyRequest",
            MonitoredItemModifyRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemModifyRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new MonitoredItemModifyRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoredItemModifyRequest"),
            MonitoredItemModifyRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemModifyRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new MonitoredItemModifyResult.Codec().asBinaryCodec(),
            "MonitoredItemModifyResult",
            MonitoredItemModifyResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemModifyResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new MonitoredItemModifyResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoredItemModifyResult"),
            MonitoredItemModifyResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemModifyResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ModifyMonitoredItemsRequest.Codec().asBinaryCodec(),
            "ModifyMonitoredItemsRequest",
            ModifyMonitoredItemsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModifyMonitoredItemsRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ModifyMonitoredItemsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ModifyMonitoredItemsRequest"),
            ModifyMonitoredItemsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModifyMonitoredItemsRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ModifyMonitoredItemsResponse.Codec().asBinaryCodec(),
            "ModifyMonitoredItemsResponse",
            ModifyMonitoredItemsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModifyMonitoredItemsResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ModifyMonitoredItemsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ModifyMonitoredItemsResponse"),
            ModifyMonitoredItemsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModifyMonitoredItemsResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SetMonitoringModeRequest.Codec().asBinaryCodec(),
            "SetMonitoringModeRequest",
            SetMonitoringModeRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetMonitoringModeRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SetMonitoringModeRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SetMonitoringModeRequest"),
            SetMonitoringModeRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetMonitoringModeRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SetMonitoringModeResponse.Codec().asBinaryCodec(),
            "SetMonitoringModeResponse",
            SetMonitoringModeResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetMonitoringModeResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SetMonitoringModeResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SetMonitoringModeResponse"),
            SetMonitoringModeResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetMonitoringModeResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SetTriggeringRequest.Codec().asBinaryCodec(),
            "SetTriggeringRequest",
            SetTriggeringRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetTriggeringRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SetTriggeringRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SetTriggeringRequest"),
            SetTriggeringRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetTriggeringRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SetTriggeringResponse.Codec().asBinaryCodec(),
            "SetTriggeringResponse",
            SetTriggeringResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetTriggeringResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SetTriggeringResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SetTriggeringResponse"),
            SetTriggeringResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetTriggeringResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DeleteMonitoredItemsRequest.Codec().asBinaryCodec(),
            "DeleteMonitoredItemsRequest",
            DeleteMonitoredItemsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteMonitoredItemsRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DeleteMonitoredItemsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteMonitoredItemsRequest"),
            DeleteMonitoredItemsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteMonitoredItemsRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DeleteMonitoredItemsResponse.Codec().asBinaryCodec(),
            "DeleteMonitoredItemsResponse",
            DeleteMonitoredItemsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteMonitoredItemsResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DeleteMonitoredItemsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteMonitoredItemsResponse"),
            DeleteMonitoredItemsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteMonitoredItemsResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CreateSubscriptionRequest.Codec().asBinaryCodec(),
            "CreateSubscriptionRequest",
            CreateSubscriptionRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateSubscriptionRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CreateSubscriptionRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CreateSubscriptionRequest"),
            CreateSubscriptionRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateSubscriptionRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new CreateSubscriptionResponse.Codec().asBinaryCodec(),
            "CreateSubscriptionResponse",
            CreateSubscriptionResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateSubscriptionResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new CreateSubscriptionResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "CreateSubscriptionResponse"),
            CreateSubscriptionResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateSubscriptionResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ModifySubscriptionRequest.Codec().asBinaryCodec(),
            "ModifySubscriptionRequest",
            ModifySubscriptionRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModifySubscriptionRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ModifySubscriptionRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ModifySubscriptionRequest"),
            ModifySubscriptionRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModifySubscriptionRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new ModifySubscriptionResponse.Codec().asBinaryCodec(),
            "ModifySubscriptionResponse",
            ModifySubscriptionResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModifySubscriptionResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new ModifySubscriptionResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "ModifySubscriptionResponse"),
            ModifySubscriptionResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModifySubscriptionResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SetPublishingModeRequest.Codec().asBinaryCodec(),
            "SetPublishingModeRequest",
            SetPublishingModeRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetPublishingModeRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SetPublishingModeRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SetPublishingModeRequest"),
            SetPublishingModeRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetPublishingModeRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SetPublishingModeResponse.Codec().asBinaryCodec(),
            "SetPublishingModeResponse",
            SetPublishingModeResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetPublishingModeResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SetPublishingModeResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SetPublishingModeResponse"),
            SetPublishingModeResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetPublishingModeResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new NotificationMessage.Codec().asBinaryCodec(),
            "NotificationMessage",
            NotificationMessage.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NotificationMessage.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new NotificationMessage.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "NotificationMessage"),
            NotificationMessage.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NotificationMessage.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new NotificationData.Codec().asBinaryCodec(),
            "NotificationData",
            NotificationData.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NotificationData.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new NotificationData.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "NotificationData"),
            NotificationData.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NotificationData.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DataChangeNotification.Codec().asBinaryCodec(),
            "DataChangeNotification",
            DataChangeNotification.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataChangeNotification.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DataChangeNotification.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DataChangeNotification"),
            DataChangeNotification.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataChangeNotification.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new EventNotificationList.Codec().asBinaryCodec(),
            "EventNotificationList",
            EventNotificationList.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EventNotificationList.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new EventNotificationList.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EventNotificationList"),
            EventNotificationList.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EventNotificationList.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new StatusChangeNotification.Codec().asBinaryCodec(),
            "StatusChangeNotification",
            StatusChangeNotification.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StatusChangeNotification.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new StatusChangeNotification.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "StatusChangeNotification"),
            StatusChangeNotification.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StatusChangeNotification.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new MonitoredItemNotification.Codec().asBinaryCodec(),
            "MonitoredItemNotification",
            MonitoredItemNotification.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemNotification.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new MonitoredItemNotification.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "MonitoredItemNotification"),
            MonitoredItemNotification.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemNotification.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new EventFieldList.Codec().asBinaryCodec(),
            "EventFieldList",
            EventFieldList.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EventFieldList.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new EventFieldList.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "EventFieldList"),
            EventFieldList.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EventFieldList.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new SubscriptionAcknowledgement.Codec().asBinaryCodec(),
            "SubscriptionAcknowledgement",
            SubscriptionAcknowledgement.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SubscriptionAcknowledgement.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new SubscriptionAcknowledgement.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "SubscriptionAcknowledgement"),
            SubscriptionAcknowledgement.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SubscriptionAcknowledgement.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new PublishRequest.Codec().asBinaryCodec(),
            "PublishRequest",
            PublishRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new PublishRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PublishRequest"),
            PublishRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new PublishResponse.Codec().asBinaryCodec(),
            "PublishResponse",
            PublishResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new PublishResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "PublishResponse"),
            PublishResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new RepublishRequest.Codec().asBinaryCodec(),
            "RepublishRequest",
            RepublishRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RepublishRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new RepublishRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RepublishRequest"),
            RepublishRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RepublishRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new RepublishResponse.Codec().asBinaryCodec(),
            "RepublishResponse",
            RepublishResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RepublishResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new RepublishResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "RepublishResponse"),
            RepublishResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RepublishResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new TransferResult.Codec().asBinaryCodec(),
            "TransferResult",
            TransferResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TransferResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new TransferResult.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TransferResult"),
            TransferResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TransferResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new TransferSubscriptionsRequest.Codec().asBinaryCodec(),
            "TransferSubscriptionsRequest",
            TransferSubscriptionsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TransferSubscriptionsRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new TransferSubscriptionsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TransferSubscriptionsRequest"),
            TransferSubscriptionsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TransferSubscriptionsRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new TransferSubscriptionsResponse.Codec().asBinaryCodec(),
            "TransferSubscriptionsResponse",
            TransferSubscriptionsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TransferSubscriptionsResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new TransferSubscriptionsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "TransferSubscriptionsResponse"),
            TransferSubscriptionsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TransferSubscriptionsResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DeleteSubscriptionsRequest.Codec().asBinaryCodec(),
            "DeleteSubscriptionsRequest",
            DeleteSubscriptionsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteSubscriptionsRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DeleteSubscriptionsRequest.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteSubscriptionsRequest"),
            DeleteSubscriptionsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteSubscriptionsRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        binaryDictionary.registerStructCodec(
            new DeleteSubscriptionsResponse.Codec().asBinaryCodec(),
            "DeleteSubscriptionsResponse",
            DeleteSubscriptionsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteSubscriptionsResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        xmlDictionary.registerStructCodec(
            new DeleteSubscriptionsResponse.Codec().asXmlCodec(),
            String.format("//xs:element[@name='%s']", "DeleteSubscriptionsResponse"),
            DeleteSubscriptionsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteSubscriptionsResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
    }
}
