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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class PubSubConnectionDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15617");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15694");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15992");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16281");

    private final String name;

    private final Boolean enabled;

    private final Variant publisherId;

    private final String transportProfileUri;

    private final ExtensionObject address;

    private final KeyValuePair[] connectionProperties;

    private final ExtensionObject transportSettings;

    private final WriterGroupDataType[] writerGroups;

    private final ReaderGroupDataType[] readerGroups;

    public PubSubConnectionDataType(String name, Boolean enabled, Variant publisherId,
                                    String transportProfileUri, ExtensionObject address, KeyValuePair[] connectionProperties,
                                    ExtensionObject transportSettings, WriterGroupDataType[] writerGroups,
                                    ReaderGroupDataType[] readerGroups) {
        this.name = name;
        this.enabled = enabled;
        this.publisherId = publisherId;
        this.transportProfileUri = transportProfileUri;
        this.address = address;
        this.connectionProperties = connectionProperties;
        this.transportSettings = transportSettings;
        this.writerGroups = writerGroups;
        this.readerGroups = readerGroups;
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

    public String getName() {
        return name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Variant getPublisherId() {
        return publisherId;
    }

    public String getTransportProfileUri() {
        return transportProfileUri;
    }

    public ExtensionObject getAddress() {
        return address;
    }

    public KeyValuePair[] getConnectionProperties() {
        return connectionProperties;
    }

    public ExtensionObject getTransportSettings() {
        return transportSettings;
    }

    public WriterGroupDataType[] getWriterGroups() {
        return writerGroups;
    }

    public ReaderGroupDataType[] getReaderGroups() {
        return readerGroups;
    }

    public static final class Codec extends GenericDataTypeCodec<PubSubConnectionDataType> {
        @Override
        public Class<PubSubConnectionDataType> getType() {
            return PubSubConnectionDataType.class;
        }

        @Override
        public PubSubConnectionDataType decode(SerializationContext context, UaDecoder decoder) {
            String name = decoder.readString("Name");
            Boolean enabled = decoder.readBoolean("Enabled");
            Variant publisherId = decoder.readVariant("PublisherId");
            String transportProfileUri = decoder.readString("TransportProfileUri");
            ExtensionObject address = decoder.readExtensionObject("Address");
            KeyValuePair[] connectionProperties = (KeyValuePair[]) decoder.readStructArray("ConnectionProperties", KeyValuePair.TYPE_ID);
            ExtensionObject transportSettings = decoder.readExtensionObject("TransportSettings");
            WriterGroupDataType[] writerGroups = (WriterGroupDataType[]) decoder.readStructArray("WriterGroups", WriterGroupDataType.TYPE_ID);
            ReaderGroupDataType[] readerGroups = (ReaderGroupDataType[]) decoder.readStructArray("ReaderGroups", ReaderGroupDataType.TYPE_ID);
            return new PubSubConnectionDataType(name, enabled, publisherId, transportProfileUri, address, connectionProperties, transportSettings, writerGroups, readerGroups);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           PubSubConnectionDataType value) {
            encoder.writeString("Name", value.getName());
            encoder.writeBoolean("Enabled", value.getEnabled());
            encoder.writeVariant("PublisherId", value.getPublisherId());
            encoder.writeString("TransportProfileUri", value.getTransportProfileUri());
            encoder.writeExtensionObject("Address", value.getAddress());
            encoder.writeStructArray("ConnectionProperties", value.getConnectionProperties(), KeyValuePair.TYPE_ID);
            encoder.writeExtensionObject("TransportSettings", value.getTransportSettings());
            encoder.writeStructArray("WriterGroups", value.getWriterGroups(), WriterGroupDataType.TYPE_ID);
            encoder.writeStructArray("ReaderGroups", value.getReaderGroups(), ReaderGroupDataType.TYPE_ID);
        }
    }
}
