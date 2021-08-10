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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ReaderGroupDataType extends PubSubGroupDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15520");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21153");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21177");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21201");

    private final ExtensionObject transportSettings;

    private final ExtensionObject messageSettings;

    private final DataSetReaderDataType[] dataSetReaders;

    public ReaderGroupDataType(String name, Boolean enabled, MessageSecurityMode securityMode,
                               String securityGroupId, EndpointDescription[] securityKeyServices,
                               UInteger maxNetworkMessageSize, KeyValuePair[] groupProperties,
                               ExtensionObject transportSettings, ExtensionObject messageSettings,
                               DataSetReaderDataType[] dataSetReaders) {
        super(name, enabled, securityMode, securityGroupId, securityKeyServices, maxNetworkMessageSize, groupProperties);
        this.transportSettings = transportSettings;
        this.messageSettings = messageSettings;
        this.dataSetReaders = dataSetReaders;
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

    public ExtensionObject getTransportSettings() {
        return transportSettings;
    }

    public ExtensionObject getMessageSettings() {
        return messageSettings;
    }

    public DataSetReaderDataType[] getDataSetReaders() {
        return dataSetReaders;
    }

    public static final class Codec extends GenericDataTypeCodec<ReaderGroupDataType> {
        @Override
        public Class<ReaderGroupDataType> getType() {
            return ReaderGroupDataType.class;
        }

        @Override
        public ReaderGroupDataType decode(SerializationContext context, UaDecoder decoder) {
            String name = decoder.readString("Name");
            Boolean enabled = decoder.readBoolean("Enabled");
            MessageSecurityMode securityMode = decoder.readEnum("SecurityMode", MessageSecurityMode.class);
            String securityGroupId = decoder.readString("SecurityGroupId");
            EndpointDescription[] securityKeyServices = (EndpointDescription[]) decoder.readStructArray("SecurityKeyServices", EndpointDescription.TYPE_ID);
            UInteger maxNetworkMessageSize = decoder.readUInt32("MaxNetworkMessageSize");
            KeyValuePair[] groupProperties = (KeyValuePair[]) decoder.readStructArray("GroupProperties", KeyValuePair.TYPE_ID);
            ExtensionObject transportSettings = decoder.readExtensionObject("TransportSettings");
            ExtensionObject messageSettings = decoder.readExtensionObject("MessageSettings");
            DataSetReaderDataType[] dataSetReaders = (DataSetReaderDataType[]) decoder.readStructArray("DataSetReaders", DataSetReaderDataType.TYPE_ID);
            return new ReaderGroupDataType(name, enabled, securityMode, securityGroupId, securityKeyServices, maxNetworkMessageSize, groupProperties, transportSettings, messageSettings, dataSetReaders);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ReaderGroupDataType value) {
            encoder.writeString("Name", value.getName());
            encoder.writeBoolean("Enabled", value.getEnabled());
            encoder.writeEnum("SecurityMode", value.getSecurityMode());
            encoder.writeString("SecurityGroupId", value.getSecurityGroupId());
            encoder.writeStructArray("SecurityKeyServices", value.getSecurityKeyServices(), EndpointDescription.TYPE_ID);
            encoder.writeUInt32("MaxNetworkMessageSize", value.getMaxNetworkMessageSize());
            encoder.writeStructArray("GroupProperties", value.getGroupProperties(), KeyValuePair.TYPE_ID);
            encoder.writeExtensionObject("TransportSettings", value.getTransportSettings());
            encoder.writeExtensionObject("MessageSettings", value.getMessageSettings());
            encoder.writeStructArray("DataSetReaders", value.getDataSetReaders(), DataSetReaderDataType.TYPE_ID);
        }
    }
}
