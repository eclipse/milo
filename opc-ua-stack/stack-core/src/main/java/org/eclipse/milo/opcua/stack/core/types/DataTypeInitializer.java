package org.eclipse.milo.opcua.stack.core.types;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.structured.*;

public class DataTypeInitializer {
    public void initialize(NamespaceTable namespaceTable, DataTypeManager dataTypeManager) {
        try {
            registerStructCodecs(namespaceTable, dataTypeManager);
        } catch (Exception e) {
            throw new RuntimeException("DataType initialization failed", e);
        }
    }

    private void registerStructCodecs(NamespaceTable namespaceTable, DataTypeManager dataTypeManager)
        throws Exception {
        dataTypeManager.registerCodec(
            new KeyValuePair.Codec(),
            KeyValuePair.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            KeyValuePair.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            KeyValuePair.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            KeyValuePair.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new AdditionalParametersType.Codec(),
            AdditionalParametersType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AdditionalParametersType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AdditionalParametersType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AdditionalParametersType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new EphemeralKeyType.Codec(),
            EphemeralKeyType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EphemeralKeyType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EphemeralKeyType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EphemeralKeyType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new EndpointType.Codec(),
            EndpointType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EndpointType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EndpointType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EndpointType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new RationalNumber.Codec(),
            RationalNumber.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RationalNumber.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RationalNumber.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RationalNumber.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ThreeDVector.Codec(),
            ThreeDVector.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDVector.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDVector.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDVector.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ThreeDCartesianCoordinates.Codec(),
            ThreeDCartesianCoordinates.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDCartesianCoordinates.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDCartesianCoordinates.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDCartesianCoordinates.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ThreeDOrientation.Codec(),
            ThreeDOrientation.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDOrientation.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDOrientation.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDOrientation.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ThreeDFrame.Codec(),
            ThreeDFrame.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDFrame.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDFrame.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ThreeDFrame.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CurrencyUnitType.Codec(),
            CurrencyUnitType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CurrencyUnitType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CurrencyUnitType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CurrencyUnitType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new TrustListDataType.Codec(),
            TrustListDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TrustListDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TrustListDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TrustListDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new UABinaryFileDataType.Codec(),
            UABinaryFileDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UABinaryFileDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UABinaryFileDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UABinaryFileDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DataSetMetaDataType.Codec(),
            DataSetMetaDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataSetMetaDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DataSetMetaDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DataSetMetaDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new StructureDescription.Codec(),
            StructureDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StructureDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructureDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructureDescription.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new EnumDescription.Codec(),
            EnumDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EnumDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EnumDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EnumDescription.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SimpleTypeDescription.Codec(),
            SimpleTypeDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SimpleTypeDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SimpleTypeDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SimpleTypeDescription.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new PortableQualifiedName.Codec(),
            PortableQualifiedName.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PortableQualifiedName.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PortableQualifiedName.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PortableQualifiedName.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new PortableNodeId.Codec(),
            PortableNodeId.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PortableNodeId.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PortableNodeId.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PortableNodeId.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new UnsignedRationalNumber.Codec(),
            UnsignedRationalNumber.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UnsignedRationalNumber.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UnsignedRationalNumber.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UnsignedRationalNumber.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new FieldMetaData.Codec(),
            FieldMetaData.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FieldMetaData.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            FieldMetaData.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            FieldMetaData.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ConfigurationVersionDataType.Codec(),
            ConfigurationVersionDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ConfigurationVersionDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ConfigurationVersionDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ConfigurationVersionDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new PublishedDataSetDataType.Codec(),
            PublishedDataSetDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishedDataSetDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PublishedDataSetDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PublishedDataSetDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new PublishedDataItemsDataType.Codec(),
            PublishedDataItemsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishedDataItemsDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PublishedDataItemsDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PublishedDataItemsDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new PublishedEventsDataType.Codec(),
            PublishedEventsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishedEventsDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PublishedEventsDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PublishedEventsDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new PublishedDataSetCustomSourceDataType.Codec(),
            PublishedDataSetCustomSourceDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishedDataSetCustomSourceDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PublishedDataSetCustomSourceDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PublishedDataSetCustomSourceDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new PublishedVariableDataType.Codec(),
            PublishedVariableDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishedVariableDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PublishedVariableDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PublishedVariableDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DataSetWriterDataType.Codec(),
            DataSetWriterDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataSetWriterDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DataSetWriterDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DataSetWriterDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new BrokerDataSetWriterTransportDataType.Codec(),
            BrokerDataSetWriterTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrokerDataSetWriterTransportDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrokerDataSetWriterTransportDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrokerDataSetWriterTransportDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new UadpDataSetWriterMessageDataType.Codec(),
            UadpDataSetWriterMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UadpDataSetWriterMessageDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UadpDataSetWriterMessageDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UadpDataSetWriterMessageDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new JsonDataSetWriterMessageDataType.Codec(),
            JsonDataSetWriterMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            JsonDataSetWriterMessageDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            JsonDataSetWriterMessageDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            JsonDataSetWriterMessageDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new WriterGroupDataType.Codec(),
            WriterGroupDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            WriterGroupDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            WriterGroupDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            WriterGroupDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ReaderGroupDataType.Codec(),
            ReaderGroupDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReaderGroupDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReaderGroupDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReaderGroupDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DatagramWriterGroupTransportDataType.Codec(),
            DatagramWriterGroupTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DatagramWriterGroupTransportDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DatagramWriterGroupTransportDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DatagramWriterGroupTransportDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DatagramWriterGroupTransport2DataType.Codec(),
            DatagramWriterGroupTransport2DataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DatagramWriterGroupTransport2DataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DatagramWriterGroupTransport2DataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DatagramWriterGroupTransport2DataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new BrokerWriterGroupTransportDataType.Codec(),
            BrokerWriterGroupTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrokerWriterGroupTransportDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrokerWriterGroupTransportDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrokerWriterGroupTransportDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new UadpWriterGroupMessageDataType.Codec(),
            UadpWriterGroupMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UadpWriterGroupMessageDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UadpWriterGroupMessageDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UadpWriterGroupMessageDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new JsonWriterGroupMessageDataType.Codec(),
            JsonWriterGroupMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            JsonWriterGroupMessageDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            JsonWriterGroupMessageDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            JsonWriterGroupMessageDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new PubSubConnectionDataType.Codec(),
            PubSubConnectionDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConnectionDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConnectionDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConnectionDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DatagramConnectionTransportDataType.Codec(),
            DatagramConnectionTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DatagramConnectionTransportDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DatagramConnectionTransportDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DatagramConnectionTransportDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DatagramConnectionTransport2DataType.Codec(),
            DatagramConnectionTransport2DataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DatagramConnectionTransport2DataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DatagramConnectionTransport2DataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DatagramConnectionTransport2DataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new BrokerConnectionTransportDataType.Codec(),
            BrokerConnectionTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrokerConnectionTransportDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrokerConnectionTransportDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrokerConnectionTransportDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new NetworkAddressUrlDataType.Codec(),
            NetworkAddressUrlDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NetworkAddressUrlDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            NetworkAddressUrlDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            NetworkAddressUrlDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DataSetReaderDataType.Codec(),
            DataSetReaderDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataSetReaderDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DataSetReaderDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DataSetReaderDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DatagramDataSetReaderTransportDataType.Codec(),
            DatagramDataSetReaderTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DatagramDataSetReaderTransportDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DatagramDataSetReaderTransportDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DatagramDataSetReaderTransportDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new BrokerDataSetReaderTransportDataType.Codec(),
            BrokerDataSetReaderTransportDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrokerDataSetReaderTransportDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrokerDataSetReaderTransportDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrokerDataSetReaderTransportDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new UadpDataSetReaderMessageDataType.Codec(),
            UadpDataSetReaderMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UadpDataSetReaderMessageDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UadpDataSetReaderMessageDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UadpDataSetReaderMessageDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new JsonDataSetReaderMessageDataType.Codec(),
            JsonDataSetReaderMessageDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            JsonDataSetReaderMessageDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            JsonDataSetReaderMessageDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            JsonDataSetReaderMessageDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new TargetVariablesDataType.Codec(),
            TargetVariablesDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TargetVariablesDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TargetVariablesDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TargetVariablesDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SubscribedDataSetMirrorDataType.Codec(),
            SubscribedDataSetMirrorDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SubscribedDataSetMirrorDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SubscribedDataSetMirrorDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SubscribedDataSetMirrorDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new StandaloneSubscribedDataSetRefDataType.Codec(),
            StandaloneSubscribedDataSetRefDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StandaloneSubscribedDataSetRefDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StandaloneSubscribedDataSetRefDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StandaloneSubscribedDataSetRefDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new StandaloneSubscribedDataSetDataType.Codec(),
            StandaloneSubscribedDataSetDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StandaloneSubscribedDataSetDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StandaloneSubscribedDataSetDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StandaloneSubscribedDataSetDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new FieldTargetDataType.Codec(),
            FieldTargetDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FieldTargetDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            FieldTargetDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            FieldTargetDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new PubSubConfigurationDataType.Codec(),
            PubSubConfigurationDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfigurationDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfigurationDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfigurationDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new PubSubConfiguration2DataType.Codec(),
            PubSubConfiguration2DataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfiguration2DataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfiguration2DataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfiguration2DataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SecurityGroupDataType.Codec(),
            SecurityGroupDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SecurityGroupDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SecurityGroupDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SecurityGroupDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new PubSubKeyPushTargetDataType.Codec(),
            PubSubKeyPushTargetDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubKeyPushTargetDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PubSubKeyPushTargetDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PubSubKeyPushTargetDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new TransmitQosPriorityDataType.Codec(),
            TransmitQosPriorityDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TransmitQosPriorityDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TransmitQosPriorityDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TransmitQosPriorityDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ReceiveQosPriorityDataType.Codec(),
            ReceiveQosPriorityDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReceiveQosPriorityDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReceiveQosPriorityDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReceiveQosPriorityDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new PubSubConfigurationRefDataType.Codec(),
            PubSubConfigurationRefDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfigurationRefDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfigurationRefDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfigurationRefDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new PubSubConfigurationValueDataType.Codec(),
            PubSubConfigurationValueDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfigurationValueDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfigurationValueDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PubSubConfigurationValueDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new AliasNameDataType.Codec(),
            AliasNameDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AliasNameDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AliasNameDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AliasNameDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new UserManagementDataType.Codec(),
            UserManagementDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UserManagementDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UserManagementDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UserManagementDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new PriorityMappingEntryType.Codec(),
            PriorityMappingEntryType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PriorityMappingEntryType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PriorityMappingEntryType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PriorityMappingEntryType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new RolePermissionType.Codec(),
            RolePermissionType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RolePermissionType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RolePermissionType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RolePermissionType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new StructureDefinition.Codec(),
            StructureDefinition.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StructureDefinition.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructureDefinition.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructureDefinition.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new EnumDefinition.Codec(),
            EnumDefinition.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EnumDefinition.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EnumDefinition.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EnumDefinition.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new StructureField.Codec(),
            StructureField.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StructureField.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructureField.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructureField.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new Argument.Codec(),
            Argument.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            Argument.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            Argument.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            Argument.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new EnumValueType.Codec(),
            EnumValueType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EnumValueType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EnumValueType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EnumValueType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new EnumField.Codec(),
            EnumField.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EnumField.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EnumField.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EnumField.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new TimeZoneDataType.Codec(),
            TimeZoneDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TimeZoneDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TimeZoneDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TimeZoneDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ApplicationDescription.Codec(),
            ApplicationDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ApplicationDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ApplicationDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ApplicationDescription.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ServerOnNetwork.Codec(),
            ServerOnNetwork.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ServerOnNetwork.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ServerOnNetwork.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ServerOnNetwork.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new UserTokenPolicy.Codec(),
            UserTokenPolicy.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UserTokenPolicy.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UserTokenPolicy.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UserTokenPolicy.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new EndpointDescription.Codec(),
            EndpointDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EndpointDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EndpointDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EndpointDescription.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new RegisteredServer.Codec(),
            RegisteredServer.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisteredServer.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RegisteredServer.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RegisteredServer.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DiscoveryConfiguration.Codec(),
            DiscoveryConfiguration.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DiscoveryConfiguration.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DiscoveryConfiguration.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DiscoveryConfiguration.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new MdnsDiscoveryConfiguration.Codec(),
            MdnsDiscoveryConfiguration.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MdnsDiscoveryConfiguration.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MdnsDiscoveryConfiguration.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MdnsDiscoveryConfiguration.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SignedSoftwareCertificate.Codec(),
            SignedSoftwareCertificate.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SignedSoftwareCertificate.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SignedSoftwareCertificate.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SignedSoftwareCertificate.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new AnonymousIdentityToken.Codec(),
            AnonymousIdentityToken.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AnonymousIdentityToken.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AnonymousIdentityToken.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AnonymousIdentityToken.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new UserNameIdentityToken.Codec(),
            UserNameIdentityToken.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UserNameIdentityToken.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UserNameIdentityToken.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UserNameIdentityToken.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new X509IdentityToken.Codec(),
            X509IdentityToken.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            X509IdentityToken.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            X509IdentityToken.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            X509IdentityToken.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new IssuedIdentityToken.Codec(),
            IssuedIdentityToken.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            IssuedIdentityToken.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            IssuedIdentityToken.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            IssuedIdentityToken.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new AddNodesItem.Codec(),
            AddNodesItem.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesItem.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesItem.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesItem.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new AddReferencesItem.Codec(),
            AddReferencesItem.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddReferencesItem.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AddReferencesItem.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AddReferencesItem.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DeleteNodesItem.Codec(),
            DeleteNodesItem.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteNodesItem.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteNodesItem.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteNodesItem.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DeleteReferencesItem.Codec(),
            DeleteReferencesItem.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteReferencesItem.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteReferencesItem.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteReferencesItem.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new RelativePathElement.Codec(),
            RelativePathElement.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RelativePathElement.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RelativePathElement.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RelativePathElement.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new RelativePath.Codec(),
            RelativePath.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RelativePath.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RelativePath.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RelativePath.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new EndpointConfiguration.Codec(),
            EndpointConfiguration.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EndpointConfiguration.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EndpointConfiguration.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EndpointConfiguration.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ContentFilterElement.Codec(),
            ContentFilterElement.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilterElement.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilterElement.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilterElement.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ContentFilter.Codec(),
            ContentFilter.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilter.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilter.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilter.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new FilterOperand.Codec(),
            FilterOperand.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FilterOperand.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            FilterOperand.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            FilterOperand.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ElementOperand.Codec(),
            ElementOperand.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ElementOperand.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ElementOperand.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ElementOperand.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new LiteralOperand.Codec(),
            LiteralOperand.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            LiteralOperand.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            LiteralOperand.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            LiteralOperand.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new AttributeOperand.Codec(),
            AttributeOperand.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AttributeOperand.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AttributeOperand.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AttributeOperand.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SimpleAttributeOperand.Codec(),
            SimpleAttributeOperand.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SimpleAttributeOperand.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SimpleAttributeOperand.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SimpleAttributeOperand.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new HistoryEvent.Codec(),
            HistoryEvent.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryEvent.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryEvent.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryEvent.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new MonitoringFilter.Codec(),
            MonitoringFilter.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoringFilter.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MonitoringFilter.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MonitoringFilter.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new EventFilter.Codec(),
            EventFilter.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EventFilter.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EventFilter.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EventFilter.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DataChangeFilter.Codec(),
            DataChangeFilter.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataChangeFilter.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DataChangeFilter.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DataChangeFilter.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new AggregateFilter.Codec(),
            AggregateFilter.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AggregateFilter.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AggregateFilter.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AggregateFilter.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new AggregateConfiguration.Codec(),
            AggregateConfiguration.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AggregateConfiguration.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AggregateConfiguration.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AggregateConfiguration.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new HistoryEventFieldList.Codec(),
            HistoryEventFieldList.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryEventFieldList.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryEventFieldList.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryEventFieldList.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new BuildInfo.Codec(),
            BuildInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BuildInfo.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BuildInfo.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BuildInfo.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new RedundantServerDataType.Codec(),
            RedundantServerDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RedundantServerDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RedundantServerDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RedundantServerDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new EndpointUrlListDataType.Codec(),
            EndpointUrlListDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EndpointUrlListDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EndpointUrlListDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EndpointUrlListDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new NetworkGroupDataType.Codec(),
            NetworkGroupDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NetworkGroupDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            NetworkGroupDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            NetworkGroupDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SamplingIntervalDiagnosticsDataType.Codec(),
            SamplingIntervalDiagnosticsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SamplingIntervalDiagnosticsDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SamplingIntervalDiagnosticsDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SamplingIntervalDiagnosticsDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ServerDiagnosticsSummaryDataType.Codec(),
            ServerDiagnosticsSummaryDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ServerDiagnosticsSummaryDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ServerDiagnosticsSummaryDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ServerDiagnosticsSummaryDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ServerStatusDataType.Codec(),
            ServerStatusDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ServerStatusDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ServerStatusDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ServerStatusDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SessionDiagnosticsDataType.Codec(),
            SessionDiagnosticsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SessionDiagnosticsDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SessionDiagnosticsDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SessionDiagnosticsDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SessionSecurityDiagnosticsDataType.Codec(),
            SessionSecurityDiagnosticsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SessionSecurityDiagnosticsDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SessionSecurityDiagnosticsDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SessionSecurityDiagnosticsDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ServiceCounterDataType.Codec(),
            ServiceCounterDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ServiceCounterDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ServiceCounterDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ServiceCounterDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new StatusResult.Codec(),
            StatusResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StatusResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StatusResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StatusResult.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SubscriptionDiagnosticsDataType.Codec(),
            SubscriptionDiagnosticsDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SubscriptionDiagnosticsDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SubscriptionDiagnosticsDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SubscriptionDiagnosticsDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ModelChangeStructureDataType.Codec(),
            ModelChangeStructureDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModelChangeStructureDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ModelChangeStructureDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ModelChangeStructureDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SemanticChangeStructureDataType.Codec(),
            SemanticChangeStructureDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SemanticChangeStructureDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SemanticChangeStructureDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SemanticChangeStructureDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new Range.Codec(),
            Range.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            Range.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            Range.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            Range.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new EUInformation.Codec(),
            EUInformation.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EUInformation.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EUInformation.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EUInformation.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ComplexNumberType.Codec(),
            ComplexNumberType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ComplexNumberType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ComplexNumberType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ComplexNumberType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DoubleComplexNumberType.Codec(),
            DoubleComplexNumberType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DoubleComplexNumberType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DoubleComplexNumberType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DoubleComplexNumberType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new AxisInformation.Codec(),
            AxisInformation.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AxisInformation.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AxisInformation.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AxisInformation.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new XVType.Codec(),
            XVType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            XVType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            XVType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            XVType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ProgramDiagnosticDataType.Codec(),
            ProgramDiagnosticDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ProgramDiagnosticDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ProgramDiagnosticDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ProgramDiagnosticDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ProgramDiagnostic2DataType.Codec(),
            ProgramDiagnostic2DataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ProgramDiagnostic2DataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ProgramDiagnostic2DataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ProgramDiagnostic2DataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new Annotation.Codec(),
            Annotation.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            Annotation.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            Annotation.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            Annotation.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DecimalDataType.Codec(),
            DecimalDataType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DecimalDataType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DecimalDataType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DecimalDataType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new Node.Codec(),
            Node.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            Node.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            Node.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            Node.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new InstanceNode.Codec(),
            InstanceNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            InstanceNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            InstanceNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            InstanceNode.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ObjectNode.Codec(),
            ObjectNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ObjectNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ObjectNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ObjectNode.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new VariableNode.Codec(),
            VariableNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            VariableNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            VariableNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            VariableNode.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new MethodNode.Codec(),
            MethodNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MethodNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MethodNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MethodNode.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ViewNode.Codec(),
            ViewNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ViewNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ViewNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ViewNode.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new TypeNode.Codec(),
            TypeNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TypeNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TypeNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TypeNode.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ObjectTypeNode.Codec(),
            ObjectTypeNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ObjectTypeNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ObjectTypeNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ObjectTypeNode.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new VariableTypeNode.Codec(),
            VariableTypeNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            VariableTypeNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            VariableTypeNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            VariableTypeNode.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ReferenceTypeNode.Codec(),
            ReferenceTypeNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceTypeNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceTypeNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceTypeNode.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DataTypeNode.Codec(),
            DataTypeNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataTypeNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DataTypeNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DataTypeNode.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ReferenceNode.Codec(),
            ReferenceNode.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceNode.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceNode.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceNode.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new RequestHeader.Codec(),
            RequestHeader.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RequestHeader.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RequestHeader.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RequestHeader.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ResponseHeader.Codec(),
            ResponseHeader.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ResponseHeader.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ResponseHeader.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ResponseHeader.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ServiceFault.Codec(),
            ServiceFault.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ServiceFault.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ServiceFault.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ServiceFault.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SessionlessInvokeRequestType.Codec(),
            SessionlessInvokeRequestType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SessionlessInvokeRequestType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SessionlessInvokeRequestType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SessionlessInvokeRequestType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SessionlessInvokeResponseType.Codec(),
            SessionlessInvokeResponseType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SessionlessInvokeResponseType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SessionlessInvokeResponseType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SessionlessInvokeResponseType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new FindServersRequest.Codec(),
            FindServersRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FindServersRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            FindServersRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            FindServersRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new FindServersResponse.Codec(),
            FindServersResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FindServersResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            FindServersResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            FindServersResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new FindServersOnNetworkRequest.Codec(),
            FindServersOnNetworkRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FindServersOnNetworkRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            FindServersOnNetworkRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            FindServersOnNetworkRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new FindServersOnNetworkResponse.Codec(),
            FindServersOnNetworkResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            FindServersOnNetworkResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            FindServersOnNetworkResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            FindServersOnNetworkResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new GetEndpointsRequest.Codec(),
            GetEndpointsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            GetEndpointsRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            GetEndpointsRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            GetEndpointsRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new GetEndpointsResponse.Codec(),
            GetEndpointsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            GetEndpointsResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            GetEndpointsResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            GetEndpointsResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new RegisterServerRequest.Codec(),
            RegisterServerRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServerRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServerRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServerRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new RegisterServerResponse.Codec(),
            RegisterServerResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServerResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServerResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServerResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new RegisterServer2Request.Codec(),
            RegisterServer2Request.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServer2Request.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServer2Request.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServer2Request.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new RegisterServer2Response.Codec(),
            RegisterServer2Response.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServer2Response.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServer2Response.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RegisterServer2Response.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ChannelSecurityToken.Codec(),
            ChannelSecurityToken.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ChannelSecurityToken.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ChannelSecurityToken.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ChannelSecurityToken.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new OpenSecureChannelRequest.Codec(),
            OpenSecureChannelRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            OpenSecureChannelRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            OpenSecureChannelRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            OpenSecureChannelRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new OpenSecureChannelResponse.Codec(),
            OpenSecureChannelResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            OpenSecureChannelResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            OpenSecureChannelResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            OpenSecureChannelResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CloseSecureChannelRequest.Codec(),
            CloseSecureChannelRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CloseSecureChannelRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CloseSecureChannelRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CloseSecureChannelRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CloseSecureChannelResponse.Codec(),
            CloseSecureChannelResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CloseSecureChannelResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CloseSecureChannelResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CloseSecureChannelResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SignatureData.Codec(),
            SignatureData.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SignatureData.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SignatureData.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SignatureData.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CreateSessionRequest.Codec(),
            CreateSessionRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateSessionRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CreateSessionRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CreateSessionRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CreateSessionResponse.Codec(),
            CreateSessionResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateSessionResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CreateSessionResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CreateSessionResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ActivateSessionRequest.Codec(),
            ActivateSessionRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ActivateSessionRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ActivateSessionRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ActivateSessionRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ActivateSessionResponse.Codec(),
            ActivateSessionResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ActivateSessionResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ActivateSessionResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ActivateSessionResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CloseSessionRequest.Codec(),
            CloseSessionRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CloseSessionRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CloseSessionRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CloseSessionRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CloseSessionResponse.Codec(),
            CloseSessionResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CloseSessionResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CloseSessionResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CloseSessionResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CancelRequest.Codec(),
            CancelRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CancelRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CancelRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CancelRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CancelResponse.Codec(),
            CancelResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CancelResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CancelResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CancelResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new NodeAttributes.Codec(),
            NodeAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NodeAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            NodeAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            NodeAttributes.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ObjectAttributes.Codec(),
            ObjectAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ObjectAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ObjectAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ObjectAttributes.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new VariableAttributes.Codec(),
            VariableAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            VariableAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            VariableAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            VariableAttributes.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new MethodAttributes.Codec(),
            MethodAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MethodAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MethodAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MethodAttributes.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ObjectTypeAttributes.Codec(),
            ObjectTypeAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ObjectTypeAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ObjectTypeAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ObjectTypeAttributes.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new VariableTypeAttributes.Codec(),
            VariableTypeAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            VariableTypeAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            VariableTypeAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            VariableTypeAttributes.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ReferenceTypeAttributes.Codec(),
            ReferenceTypeAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceTypeAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceTypeAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceTypeAttributes.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DataTypeAttributes.Codec(),
            DataTypeAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataTypeAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DataTypeAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DataTypeAttributes.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ViewAttributes.Codec(),
            ViewAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ViewAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ViewAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ViewAttributes.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new GenericAttributes.Codec(),
            GenericAttributes.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            GenericAttributes.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            GenericAttributes.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            GenericAttributes.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new GenericAttributeValue.Codec(),
            GenericAttributeValue.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            GenericAttributeValue.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            GenericAttributeValue.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            GenericAttributeValue.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new AddNodesResult.Codec(),
            AddNodesResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesResult.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new AddNodesRequest.Codec(),
            AddNodesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new AddNodesResponse.Codec(),
            AddNodesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AddNodesResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new AddReferencesRequest.Codec(),
            AddReferencesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddReferencesRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AddReferencesRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AddReferencesRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new AddReferencesResponse.Codec(),
            AddReferencesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AddReferencesResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AddReferencesResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AddReferencesResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DeleteNodesRequest.Codec(),
            DeleteNodesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteNodesRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteNodesRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteNodesRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DeleteNodesResponse.Codec(),
            DeleteNodesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteNodesResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteNodesResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteNodesResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DeleteReferencesRequest.Codec(),
            DeleteReferencesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteReferencesRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteReferencesRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteReferencesRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DeleteReferencesResponse.Codec(),
            DeleteReferencesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteReferencesResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteReferencesResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteReferencesResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ViewDescription.Codec(),
            ViewDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ViewDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ViewDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ViewDescription.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new BrowseDescription.Codec(),
            BrowseDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowseDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowseDescription.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ReferenceDescription.Codec(),
            ReferenceDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReferenceDescription.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new BrowseResult.Codec(),
            BrowseResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowseResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowseResult.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new BrowseRequest.Codec(),
            BrowseRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowseRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowseRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new BrowseResponse.Codec(),
            BrowseResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowseResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowseResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new BrowseNextRequest.Codec(),
            BrowseNextRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseNextRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowseNextRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowseNextRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new BrowseNextResponse.Codec(),
            BrowseNextResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowseNextResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowseNextResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowseNextResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new BrowsePath.Codec(),
            BrowsePath.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowsePath.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowsePath.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowsePath.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new BrowsePathTarget.Codec(),
            BrowsePathTarget.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowsePathTarget.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowsePathTarget.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowsePathTarget.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new BrowsePathResult.Codec(),
            BrowsePathResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            BrowsePathResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowsePathResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            BrowsePathResult.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new TranslateBrowsePathsToNodeIdsRequest.Codec(),
            TranslateBrowsePathsToNodeIdsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TranslateBrowsePathsToNodeIdsRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TranslateBrowsePathsToNodeIdsRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TranslateBrowsePathsToNodeIdsRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new TranslateBrowsePathsToNodeIdsResponse.Codec(),
            TranslateBrowsePathsToNodeIdsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TranslateBrowsePathsToNodeIdsResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TranslateBrowsePathsToNodeIdsResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TranslateBrowsePathsToNodeIdsResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new RegisterNodesRequest.Codec(),
            RegisterNodesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterNodesRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RegisterNodesRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RegisterNodesRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new RegisterNodesResponse.Codec(),
            RegisterNodesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RegisterNodesResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RegisterNodesResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RegisterNodesResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new UnregisterNodesRequest.Codec(),
            UnregisterNodesRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UnregisterNodesRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UnregisterNodesRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UnregisterNodesRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new UnregisterNodesResponse.Codec(),
            UnregisterNodesResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UnregisterNodesResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UnregisterNodesResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UnregisterNodesResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new QueryDataDescription.Codec(),
            QueryDataDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryDataDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            QueryDataDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            QueryDataDescription.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new NodeTypeDescription.Codec(),
            NodeTypeDescription.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NodeTypeDescription.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            NodeTypeDescription.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            NodeTypeDescription.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new QueryDataSet.Codec(),
            QueryDataSet.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryDataSet.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            QueryDataSet.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            QueryDataSet.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new NodeReference.Codec(),
            NodeReference.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NodeReference.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            NodeReference.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            NodeReference.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ContentFilterElementResult.Codec(),
            ContentFilterElementResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilterElementResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilterElementResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilterElementResult.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ContentFilterResult.Codec(),
            ContentFilterResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilterResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilterResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ContentFilterResult.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ParsingResult.Codec(),
            ParsingResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ParsingResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ParsingResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ParsingResult.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new QueryFirstRequest.Codec(),
            QueryFirstRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryFirstRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            QueryFirstRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            QueryFirstRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new QueryFirstResponse.Codec(),
            QueryFirstResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryFirstResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            QueryFirstResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            QueryFirstResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new QueryNextRequest.Codec(),
            QueryNextRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryNextRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            QueryNextRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            QueryNextRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new QueryNextResponse.Codec(),
            QueryNextResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            QueryNextResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            QueryNextResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            QueryNextResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ReadValueId.Codec(),
            ReadValueId.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadValueId.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReadValueId.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReadValueId.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ReadRequest.Codec(),
            ReadRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReadRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReadRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ReadResponse.Codec(),
            ReadResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReadResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReadResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new HistoryReadValueId.Codec(),
            HistoryReadValueId.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadValueId.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadValueId.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadValueId.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new HistoryReadResult.Codec(),
            HistoryReadResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadResult.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new HistoryReadDetails.Codec(),
            HistoryReadDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadDetails.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ReadEventDetails.Codec(),
            ReadEventDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadEventDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReadEventDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReadEventDetails.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ReadRawModifiedDetails.Codec(),
            ReadRawModifiedDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadRawModifiedDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReadRawModifiedDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReadRawModifiedDetails.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ReadProcessedDetails.Codec(),
            ReadProcessedDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadProcessedDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReadProcessedDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReadProcessedDetails.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ReadAtTimeDetails.Codec(),
            ReadAtTimeDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadAtTimeDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReadAtTimeDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReadAtTimeDetails.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ReadAnnotationDataDetails.Codec(),
            ReadAnnotationDataDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ReadAnnotationDataDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReadAnnotationDataDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ReadAnnotationDataDetails.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new HistoryData.Codec(),
            HistoryData.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryData.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryData.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryData.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new HistoryModifiedData.Codec(),
            HistoryModifiedData.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryModifiedData.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryModifiedData.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryModifiedData.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ModificationInfo.Codec(),
            ModificationInfo.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModificationInfo.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ModificationInfo.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ModificationInfo.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new HistoryReadRequest.Codec(),
            HistoryReadRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new HistoryReadResponse.Codec(),
            HistoryReadResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryReadResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new WriteValue.Codec(),
            WriteValue.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            WriteValue.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            WriteValue.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            WriteValue.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new WriteRequest.Codec(),
            WriteRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            WriteRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            WriteRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            WriteRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new WriteResponse.Codec(),
            WriteResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            WriteResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            WriteResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            WriteResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new HistoryUpdateDetails.Codec(),
            HistoryUpdateDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateDetails.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new UpdateDataDetails.Codec(),
            UpdateDataDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UpdateDataDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UpdateDataDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UpdateDataDetails.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new UpdateStructureDataDetails.Codec(),
            UpdateStructureDataDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UpdateStructureDataDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UpdateStructureDataDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UpdateStructureDataDetails.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new UpdateEventDetails.Codec(),
            UpdateEventDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            UpdateEventDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UpdateEventDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UpdateEventDetails.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DeleteRawModifiedDetails.Codec(),
            DeleteRawModifiedDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteRawModifiedDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteRawModifiedDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteRawModifiedDetails.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DeleteAtTimeDetails.Codec(),
            DeleteAtTimeDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteAtTimeDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteAtTimeDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteAtTimeDetails.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DeleteEventDetails.Codec(),
            DeleteEventDetails.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteEventDetails.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteEventDetails.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteEventDetails.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new HistoryUpdateResult.Codec(),
            HistoryUpdateResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateResult.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new HistoryUpdateRequest.Codec(),
            HistoryUpdateRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new HistoryUpdateResponse.Codec(),
            HistoryUpdateResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            HistoryUpdateResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CallMethodRequest.Codec(),
            CallMethodRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CallMethodRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CallMethodRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CallMethodRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CallMethodResult.Codec(),
            CallMethodResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CallMethodResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CallMethodResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CallMethodResult.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CallRequest.Codec(),
            CallRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CallRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CallRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CallRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CallResponse.Codec(),
            CallResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CallResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CallResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CallResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new MonitoringFilterResult.Codec(),
            MonitoringFilterResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoringFilterResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MonitoringFilterResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MonitoringFilterResult.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new EventFilterResult.Codec(),
            EventFilterResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EventFilterResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EventFilterResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EventFilterResult.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new AggregateFilterResult.Codec(),
            AggregateFilterResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            AggregateFilterResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AggregateFilterResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            AggregateFilterResult.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new MonitoringParameters.Codec(),
            MonitoringParameters.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoringParameters.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MonitoringParameters.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MonitoringParameters.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new MonitoredItemCreateRequest.Codec(),
            MonitoredItemCreateRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemCreateRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemCreateRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemCreateRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new MonitoredItemCreateResult.Codec(),
            MonitoredItemCreateResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemCreateResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemCreateResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemCreateResult.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CreateMonitoredItemsRequest.Codec(),
            CreateMonitoredItemsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateMonitoredItemsRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CreateMonitoredItemsRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CreateMonitoredItemsRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CreateMonitoredItemsResponse.Codec(),
            CreateMonitoredItemsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateMonitoredItemsResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CreateMonitoredItemsResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CreateMonitoredItemsResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new MonitoredItemModifyRequest.Codec(),
            MonitoredItemModifyRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemModifyRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemModifyRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemModifyRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new MonitoredItemModifyResult.Codec(),
            MonitoredItemModifyResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemModifyResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemModifyResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemModifyResult.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ModifyMonitoredItemsRequest.Codec(),
            ModifyMonitoredItemsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModifyMonitoredItemsRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ModifyMonitoredItemsRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ModifyMonitoredItemsRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ModifyMonitoredItemsResponse.Codec(),
            ModifyMonitoredItemsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModifyMonitoredItemsResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ModifyMonitoredItemsResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ModifyMonitoredItemsResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SetMonitoringModeRequest.Codec(),
            SetMonitoringModeRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetMonitoringModeRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SetMonitoringModeRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SetMonitoringModeRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SetMonitoringModeResponse.Codec(),
            SetMonitoringModeResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetMonitoringModeResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SetMonitoringModeResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SetMonitoringModeResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SetTriggeringRequest.Codec(),
            SetTriggeringRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetTriggeringRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SetTriggeringRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SetTriggeringRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SetTriggeringResponse.Codec(),
            SetTriggeringResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetTriggeringResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SetTriggeringResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SetTriggeringResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DeleteMonitoredItemsRequest.Codec(),
            DeleteMonitoredItemsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteMonitoredItemsRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteMonitoredItemsRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteMonitoredItemsRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DeleteMonitoredItemsResponse.Codec(),
            DeleteMonitoredItemsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteMonitoredItemsResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteMonitoredItemsResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteMonitoredItemsResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CreateSubscriptionRequest.Codec(),
            CreateSubscriptionRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateSubscriptionRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CreateSubscriptionRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CreateSubscriptionRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new CreateSubscriptionResponse.Codec(),
            CreateSubscriptionResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            CreateSubscriptionResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CreateSubscriptionResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            CreateSubscriptionResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ModifySubscriptionRequest.Codec(),
            ModifySubscriptionRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModifySubscriptionRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ModifySubscriptionRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ModifySubscriptionRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new ModifySubscriptionResponse.Codec(),
            ModifySubscriptionResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            ModifySubscriptionResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ModifySubscriptionResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ModifySubscriptionResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SetPublishingModeRequest.Codec(),
            SetPublishingModeRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetPublishingModeRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SetPublishingModeRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SetPublishingModeRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SetPublishingModeResponse.Codec(),
            SetPublishingModeResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SetPublishingModeResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SetPublishingModeResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SetPublishingModeResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new NotificationMessage.Codec(),
            NotificationMessage.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NotificationMessage.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            NotificationMessage.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            NotificationMessage.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new NotificationData.Codec(),
            NotificationData.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            NotificationData.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            NotificationData.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            NotificationData.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DataChangeNotification.Codec(),
            DataChangeNotification.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DataChangeNotification.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DataChangeNotification.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DataChangeNotification.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new EventNotificationList.Codec(),
            EventNotificationList.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EventNotificationList.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EventNotificationList.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EventNotificationList.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new StatusChangeNotification.Codec(),
            StatusChangeNotification.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            StatusChangeNotification.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StatusChangeNotification.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StatusChangeNotification.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new MonitoredItemNotification.Codec(),
            MonitoredItemNotification.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemNotification.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemNotification.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            MonitoredItemNotification.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new EventFieldList.Codec(),
            EventFieldList.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            EventFieldList.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EventFieldList.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            EventFieldList.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new SubscriptionAcknowledgement.Codec(),
            SubscriptionAcknowledgement.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            SubscriptionAcknowledgement.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SubscriptionAcknowledgement.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            SubscriptionAcknowledgement.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new PublishRequest.Codec(),
            PublishRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PublishRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PublishRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new PublishResponse.Codec(),
            PublishResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            PublishResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PublishResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            PublishResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new RepublishRequest.Codec(),
            RepublishRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RepublishRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RepublishRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RepublishRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new RepublishResponse.Codec(),
            RepublishResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            RepublishResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RepublishResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            RepublishResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new TransferResult.Codec(),
            TransferResult.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TransferResult.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TransferResult.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TransferResult.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new TransferSubscriptionsRequest.Codec(),
            TransferSubscriptionsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TransferSubscriptionsRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TransferSubscriptionsRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TransferSubscriptionsRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new TransferSubscriptionsResponse.Codec(),
            TransferSubscriptionsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            TransferSubscriptionsResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TransferSubscriptionsResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            TransferSubscriptionsResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DeleteSubscriptionsRequest.Codec(),
            DeleteSubscriptionsRequest.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteSubscriptionsRequest.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteSubscriptionsRequest.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteSubscriptionsRequest.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerCodec(
            new DeleteSubscriptionsResponse.Codec(),
            DeleteSubscriptionsResponse.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            DeleteSubscriptionsResponse.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteSubscriptionsResponse.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            DeleteSubscriptionsResponse.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
    }
}
