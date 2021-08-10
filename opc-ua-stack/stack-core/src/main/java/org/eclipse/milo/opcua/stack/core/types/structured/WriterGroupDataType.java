/*
 * Copyright (c) 2021 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class WriterGroupDataType extends PubSubGroupDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15480");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21150");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21174");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21198");

    private final UShort writerGroupId;

    private final Double publishingInterval;

    private final Double keepAliveTime;

    private final UByte priority;

    private final String[] localeIds;

    private final String headerLayoutUri;

    private final ExtensionObject transportSettings;

    private final ExtensionObject messageSettings;

    private final DataSetWriterDataType[] dataSetWriters;

    public WriterGroupDataType(String name, Boolean enabled, MessageSecurityMode securityMode,
                               String securityGroupId, EndpointDescription[] securityKeyServices,
                               UInteger maxNetworkMessageSize, KeyValuePair[] groupProperties, UShort writerGroupId,
                               Double publishingInterval, Double keepAliveTime, UByte priority, String[] localeIds,
                               String headerLayoutUri, ExtensionObject transportSettings, ExtensionObject messageSettings,
                               DataSetWriterDataType[] dataSetWriters) {
        super(name, enabled, securityMode, securityGroupId, securityKeyServices, maxNetworkMessageSize, groupProperties);
        this.writerGroupId = writerGroupId;
        this.publishingInterval = publishingInterval;
        this.keepAliveTime = keepAliveTime;
        this.priority = priority;
        this.localeIds = localeIds;
        this.headerLayoutUri = headerLayoutUri;
        this.transportSettings = transportSettings;
        this.messageSettings = messageSettings;
        this.dataSetWriters = dataSetWriters;
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

    public UShort getWriterGroupId() {
        return writerGroupId;
    }

    public Double getPublishingInterval() {
        return publishingInterval;
    }

    public Double getKeepAliveTime() {
        return keepAliveTime;
    }

    public UByte getPriority() {
        return priority;
    }

    public String[] getLocaleIds() {
        return localeIds;
    }

    public String getHeaderLayoutUri() {
        return headerLayoutUri;
    }

    public ExtensionObject getTransportSettings() {
        return transportSettings;
    }

    public ExtensionObject getMessageSettings() {
        return messageSettings;
    }

    public DataSetWriterDataType[] getDataSetWriters() {
        return dataSetWriters;
    }

    public static final class Codec extends GenericDataTypeCodec<WriterGroupDataType> {
        @Override
        public Class<WriterGroupDataType> getType() {
            return WriterGroupDataType.class;
        }

        @Override
        public WriterGroupDataType decode(SerializationContext context, UaDecoder decoder) {
            String name = decoder.readString("Name");
            Boolean enabled = decoder.readBoolean("Enabled");
            MessageSecurityMode securityMode = decoder.readEnum("SecurityMode", MessageSecurityMode.class);
            String securityGroupId = decoder.readString("SecurityGroupId");
            EndpointDescription[] securityKeyServices = (EndpointDescription[]) decoder.readStructArray("SecurityKeyServices", EndpointDescription.TYPE_ID);
            UInteger maxNetworkMessageSize = decoder.readUInt32("MaxNetworkMessageSize");
            KeyValuePair[] groupProperties = (KeyValuePair[]) decoder.readStructArray("GroupProperties", KeyValuePair.TYPE_ID);
            UShort writerGroupId = decoder.readUInt16("WriterGroupId");
            Double publishingInterval = decoder.readDouble("PublishingInterval");
            Double keepAliveTime = decoder.readDouble("KeepAliveTime");
            UByte priority = decoder.readByte("Priority");
            String[] localeIds = decoder.readStringArray("LocaleIds");
            String headerLayoutUri = decoder.readString("HeaderLayoutUri");
            ExtensionObject transportSettings = decoder.readExtensionObject("TransportSettings");
            ExtensionObject messageSettings = decoder.readExtensionObject("MessageSettings");
            DataSetWriterDataType[] dataSetWriters = (DataSetWriterDataType[]) decoder.readStructArray("DataSetWriters", DataSetWriterDataType.TYPE_ID);
            return new WriterGroupDataType(name, enabled, securityMode, securityGroupId, securityKeyServices, maxNetworkMessageSize, groupProperties, writerGroupId, publishingInterval, keepAliveTime, priority, localeIds, headerLayoutUri, transportSettings, messageSettings, dataSetWriters);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, WriterGroupDataType value) {
            encoder.writeString("Name", value.getName());
            encoder.writeBoolean("Enabled", value.getEnabled());
            encoder.writeEnum("SecurityMode", value.getSecurityMode());
            encoder.writeString("SecurityGroupId", value.getSecurityGroupId());
            encoder.writeStructArray("SecurityKeyServices", value.getSecurityKeyServices(), EndpointDescription.TYPE_ID);
            encoder.writeUInt32("MaxNetworkMessageSize", value.getMaxNetworkMessageSize());
            encoder.writeStructArray("GroupProperties", value.getGroupProperties(), KeyValuePair.TYPE_ID);
            encoder.writeUInt16("WriterGroupId", value.getWriterGroupId());
            encoder.writeDouble("PublishingInterval", value.getPublishingInterval());
            encoder.writeDouble("KeepAliveTime", value.getKeepAliveTime());
            encoder.writeByte("Priority", value.getPriority());
            encoder.writeStringArray("LocaleIds", value.getLocaleIds());
            encoder.writeString("HeaderLayoutUri", value.getHeaderLayoutUri());
            encoder.writeExtensionObject("TransportSettings", value.getTransportSettings());
            encoder.writeExtensionObject("MessageSettings", value.getMessageSettings());
            encoder.writeStructArray("DataSetWriters", value.getDataSetWriters(), DataSetWriterDataType.TYPE_ID);
        }
    }
}
