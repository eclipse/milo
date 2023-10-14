/*
 * Copyright (c) 2023 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.9/#6.2.9.13.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.9/#6.2.9.13.1</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class DataSetReaderDataType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15623");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15703");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=16007");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16286");

    private final @Nullable String name;

    private final Boolean enabled;

    private final Variant publisherId;

    private final UShort writerGroupId;

    private final UShort dataSetWriterId;

    private final DataSetMetaDataType dataSetMetaData;

    private final DataSetFieldContentMask dataSetFieldContentMask;

    private final Double messageReceiveTimeout;

    private final UInteger keyFrameCount;

    private final @Nullable String headerLayoutUri;

    private final MessageSecurityMode securityMode;

    private final @Nullable String securityGroupId;

    private final EndpointDescription @Nullable [] securityKeyServices;

    private final KeyValuePair @Nullable [] dataSetReaderProperties;

    private final DataSetReaderTransportDataType transportSettings;

    private final DataSetReaderMessageDataType messageSettings;

    private final SubscribedDataSetDataType subscribedDataSet;

    public DataSetReaderDataType(@Nullable String name, Boolean enabled, Variant publisherId,
                                 UShort writerGroupId, UShort dataSetWriterId, DataSetMetaDataType dataSetMetaData,
                                 DataSetFieldContentMask dataSetFieldContentMask, Double messageReceiveTimeout,
                                 UInteger keyFrameCount, @Nullable String headerLayoutUri, MessageSecurityMode securityMode,
                                 @Nullable String securityGroupId, EndpointDescription @Nullable [] securityKeyServices,
                                 KeyValuePair @Nullable [] dataSetReaderProperties,
                                 DataSetReaderTransportDataType transportSettings,
                                 DataSetReaderMessageDataType messageSettings, SubscribedDataSetDataType subscribedDataSet) {
        this.name = name;
        this.enabled = enabled;
        this.publisherId = publisherId;
        this.writerGroupId = writerGroupId;
        this.dataSetWriterId = dataSetWriterId;
        this.dataSetMetaData = dataSetMetaData;
        this.dataSetFieldContentMask = dataSetFieldContentMask;
        this.messageReceiveTimeout = messageReceiveTimeout;
        this.keyFrameCount = keyFrameCount;
        this.headerLayoutUri = headerLayoutUri;
        this.securityMode = securityMode;
        this.securityGroupId = securityGroupId;
        this.securityKeyServices = securityKeyServices;
        this.dataSetReaderProperties = dataSetReaderProperties;
        this.transportSettings = transportSettings;
        this.messageSettings = messageSettings;
        this.subscribedDataSet = subscribedDataSet;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public @Nullable String getName() {
        return name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Variant getPublisherId() {
        return publisherId;
    }

    public UShort getWriterGroupId() {
        return writerGroupId;
    }

    public UShort getDataSetWriterId() {
        return dataSetWriterId;
    }

    public DataSetMetaDataType getDataSetMetaData() {
        return dataSetMetaData;
    }

    public DataSetFieldContentMask getDataSetFieldContentMask() {
        return dataSetFieldContentMask;
    }

    public Double getMessageReceiveTimeout() {
        return messageReceiveTimeout;
    }

    public UInteger getKeyFrameCount() {
        return keyFrameCount;
    }

    public @Nullable String getHeaderLayoutUri() {
        return headerLayoutUri;
    }

    public MessageSecurityMode getSecurityMode() {
        return securityMode;
    }

    public @Nullable String getSecurityGroupId() {
        return securityGroupId;
    }

    public EndpointDescription @Nullable [] getSecurityKeyServices() {
        return securityKeyServices;
    }

    public KeyValuePair @Nullable [] getDataSetReaderProperties() {
        return dataSetReaderProperties;
    }

    public DataSetReaderTransportDataType getTransportSettings() {
        return transportSettings;
    }

    public DataSetReaderMessageDataType getMessageSettings() {
        return messageSettings;
    }

    public SubscribedDataSetDataType getSubscribedDataSet() {
        return subscribedDataSet;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15703),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Name", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Enabled", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("PublisherId", LocalizedText.NULL_VALUE, new NodeId(0, 24), -1, null, UInteger.valueOf(0), false),
                new StructureField("WriterGroupId", LocalizedText.NULL_VALUE, new NodeId(0, 5), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetWriterId", LocalizedText.NULL_VALUE, new NodeId(0, 5), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetMetaData", LocalizedText.NULL_VALUE, new NodeId(0, 14523), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetFieldContentMask", LocalizedText.NULL_VALUE, new NodeId(0, 15583), -1, null, UInteger.valueOf(0), false),
                new StructureField("MessageReceiveTimeout", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("KeyFrameCount", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("HeaderLayoutUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityMode", LocalizedText.NULL_VALUE, new NodeId(0, 302), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityGroupId", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityKeyServices", LocalizedText.NULL_VALUE, new NodeId(0, 312), 1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetReaderProperties", LocalizedText.NULL_VALUE, new NodeId(0, 14533), 1, null, UInteger.valueOf(0), false),
                new StructureField("TransportSettings", LocalizedText.NULL_VALUE, new NodeId(0, 15628), -1, null, UInteger.valueOf(0), false),
                new StructureField("MessageSettings", LocalizedText.NULL_VALUE, new NodeId(0, 15629), -1, null, UInteger.valueOf(0), false),
                new StructureField("SubscribedDataSet", LocalizedText.NULL_VALUE, new NodeId(0, 15630), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DataSetReaderDataType> {
        @Override
        public Class<DataSetReaderDataType> getType() {
            return DataSetReaderDataType.class;
        }

        @Override
        public DataSetReaderDataType decodeType(EncodingContext context, UaDecoder decoder) {
            String name = decoder.decodeString("Name");
            Boolean enabled = decoder.decodeBoolean("Enabled");
            Variant publisherId = decoder.decodeVariant("PublisherId");
            UShort writerGroupId = decoder.decodeUInt16("WriterGroupId");
            UShort dataSetWriterId = decoder.decodeUInt16("DataSetWriterId");
            DataSetMetaDataType dataSetMetaData = (DataSetMetaDataType) decoder.decodeStruct("DataSetMetaData", DataSetMetaDataType.TYPE_ID);
            DataSetFieldContentMask dataSetFieldContentMask = new DataSetFieldContentMask(decoder.decodeUInt32("DataSetFieldContentMask"));
            Double messageReceiveTimeout = decoder.decodeDouble("MessageReceiveTimeout");
            UInteger keyFrameCount = decoder.decodeUInt32("KeyFrameCount");
            String headerLayoutUri = decoder.decodeString("HeaderLayoutUri");
            MessageSecurityMode securityMode = MessageSecurityMode.from(decoder.decodeEnum("SecurityMode"));
            String securityGroupId = decoder.decodeString("SecurityGroupId");
            EndpointDescription[] securityKeyServices = (EndpointDescription[]) decoder.decodeStructArray("SecurityKeyServices", EndpointDescription.TYPE_ID);
            KeyValuePair[] dataSetReaderProperties = (KeyValuePair[]) decoder.decodeStructArray("DataSetReaderProperties", KeyValuePair.TYPE_ID);
            DataSetReaderTransportDataType transportSettings = (DataSetReaderTransportDataType) decoder.decodeStruct("TransportSettings", DataSetReaderTransportDataType.TYPE_ID);
            DataSetReaderMessageDataType messageSettings = (DataSetReaderMessageDataType) decoder.decodeStruct("MessageSettings", DataSetReaderMessageDataType.TYPE_ID);
            SubscribedDataSetDataType subscribedDataSet = (SubscribedDataSetDataType) decoder.decodeStruct("SubscribedDataSet", SubscribedDataSetDataType.TYPE_ID);
            return new DataSetReaderDataType(name, enabled, publisherId, writerGroupId, dataSetWriterId, dataSetMetaData, dataSetFieldContentMask, messageReceiveTimeout, keyFrameCount, headerLayoutUri, securityMode, securityGroupId, securityKeyServices, dataSetReaderProperties, transportSettings, messageSettings, subscribedDataSet);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               DataSetReaderDataType value) {
            encoder.encodeString("Name", value.getName());
            encoder.encodeBoolean("Enabled", value.getEnabled());
            encoder.encodeVariant("PublisherId", value.getPublisherId());
            encoder.encodeUInt16("WriterGroupId", value.getWriterGroupId());
            encoder.encodeUInt16("DataSetWriterId", value.getDataSetWriterId());
            encoder.encodeStruct("DataSetMetaData", value.getDataSetMetaData(), DataSetMetaDataType.TYPE_ID);
            encoder.encodeUInt32("DataSetFieldContentMask", value.getDataSetFieldContentMask().getValue());
            encoder.encodeDouble("MessageReceiveTimeout", value.getMessageReceiveTimeout());
            encoder.encodeUInt32("KeyFrameCount", value.getKeyFrameCount());
            encoder.encodeString("HeaderLayoutUri", value.getHeaderLayoutUri());
            encoder.encodeEnum("SecurityMode", value.getSecurityMode());
            encoder.encodeString("SecurityGroupId", value.getSecurityGroupId());
            encoder.encodeStructArray("SecurityKeyServices", value.getSecurityKeyServices(), EndpointDescription.TYPE_ID);
            encoder.encodeStructArray("DataSetReaderProperties", value.getDataSetReaderProperties(), KeyValuePair.TYPE_ID);
            encoder.encodeStruct("TransportSettings", value.getTransportSettings(), DataSetReaderTransportDataType.TYPE_ID);
            encoder.encodeStruct("MessageSettings", value.getMessageSettings(), DataSetReaderMessageDataType.TYPE_ID);
            encoder.encodeStruct("SubscribedDataSet", value.getSubscribedDataSet(), SubscribedDataSetDataType.TYPE_ID);
        }
    }
}
