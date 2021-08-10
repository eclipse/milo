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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class DataSetWriterDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15597");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15682");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15955");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16156");

    private final String name;

    private final Boolean enabled;

    private final UShort dataSetWriterId;

    private final DataSetFieldContentMask dataSetFieldContentMask;

    private final UInteger keyFrameCount;

    private final String dataSetName;

    private final KeyValuePair[] dataSetWriterProperties;

    private final ExtensionObject transportSettings;

    private final ExtensionObject messageSettings;

    public DataSetWriterDataType(String name, Boolean enabled, UShort dataSetWriterId,
                                 DataSetFieldContentMask dataSetFieldContentMask, UInteger keyFrameCount, String dataSetName,
                                 KeyValuePair[] dataSetWriterProperties, ExtensionObject transportSettings,
                                 ExtensionObject messageSettings) {
        this.name = name;
        this.enabled = enabled;
        this.dataSetWriterId = dataSetWriterId;
        this.dataSetFieldContentMask = dataSetFieldContentMask;
        this.keyFrameCount = keyFrameCount;
        this.dataSetName = dataSetName;
        this.dataSetWriterProperties = dataSetWriterProperties;
        this.transportSettings = transportSettings;
        this.messageSettings = messageSettings;
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

    public UShort getDataSetWriterId() {
        return dataSetWriterId;
    }

    public DataSetFieldContentMask getDataSetFieldContentMask() {
        return dataSetFieldContentMask;
    }

    public UInteger getKeyFrameCount() {
        return keyFrameCount;
    }

    public String getDataSetName() {
        return dataSetName;
    }

    public KeyValuePair[] getDataSetWriterProperties() {
        return dataSetWriterProperties;
    }

    public ExtensionObject getTransportSettings() {
        return transportSettings;
    }

    public ExtensionObject getMessageSettings() {
        return messageSettings;
    }

    public static final class Codec extends GenericDataTypeCodec<DataSetWriterDataType> {
        @Override
        public Class<DataSetWriterDataType> getType() {
            return DataSetWriterDataType.class;
        }

        @Override
        public DataSetWriterDataType decode(SerializationContext context, UaDecoder decoder) {
            String name = decoder.readString("Name");
            Boolean enabled = decoder.readBoolean("Enabled");
            UShort dataSetWriterId = decoder.readUInt16("DataSetWriterId");
            DataSetFieldContentMask dataSetFieldContentMask = new DataSetFieldContentMask(decoder.readUInt32("DataSetFieldContentMask"));
            UInteger keyFrameCount = decoder.readUInt32("KeyFrameCount");
            String dataSetName = decoder.readString("DataSetName");
            KeyValuePair[] dataSetWriterProperties = (KeyValuePair[]) decoder.readStructArray("DataSetWriterProperties", KeyValuePair.TYPE_ID);
            ExtensionObject transportSettings = decoder.readExtensionObject("TransportSettings");
            ExtensionObject messageSettings = decoder.readExtensionObject("MessageSettings");
            return new DataSetWriterDataType(name, enabled, dataSetWriterId, dataSetFieldContentMask, keyFrameCount, dataSetName, dataSetWriterProperties, transportSettings, messageSettings);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           DataSetWriterDataType value) {
            encoder.writeString("Name", value.getName());
            encoder.writeBoolean("Enabled", value.getEnabled());
            encoder.writeUInt16("DataSetWriterId", value.getDataSetWriterId());
            encoder.writeUInt32("DataSetFieldContentMask", value.getDataSetFieldContentMask().getValue());
            encoder.writeUInt32("KeyFrameCount", value.getKeyFrameCount());
            encoder.writeString("DataSetName", value.getDataSetName());
            encoder.writeStructArray("DataSetWriterProperties", value.getDataSetWriterProperties(), KeyValuePair.TYPE_ID);
            encoder.writeExtensionObject("TransportSettings", value.getTransportSettings());
            encoder.writeExtensionObject("MessageSettings", value.getMessageSettings());
        }
    }
}
