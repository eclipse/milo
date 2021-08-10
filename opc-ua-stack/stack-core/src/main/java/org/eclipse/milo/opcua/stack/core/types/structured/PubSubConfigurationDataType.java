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

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class PubSubConfigurationDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15530");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21154");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21178");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21202");

    private final PublishedDataSetDataType[] publishedDataSets;

    private final PubSubConnectionDataType[] connections;

    private final Boolean enabled;

    public PubSubConfigurationDataType(PublishedDataSetDataType[] publishedDataSets,
                                       PubSubConnectionDataType[] connections, Boolean enabled) {
        this.publishedDataSets = publishedDataSets;
        this.connections = connections;
        this.enabled = enabled;
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

    public PublishedDataSetDataType[] getPublishedDataSets() {
        return publishedDataSets;
    }

    public PubSubConnectionDataType[] getConnections() {
        return connections;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public static final class Codec extends GenericDataTypeCodec<PubSubConfigurationDataType> {
        @Override
        public Class<PubSubConfigurationDataType> getType() {
            return PubSubConfigurationDataType.class;
        }

        @Override
        public PubSubConfigurationDataType decode(SerializationContext context, UaDecoder decoder) {
            PublishedDataSetDataType[] publishedDataSets = (PublishedDataSetDataType[]) decoder.readStructArray("PublishedDataSets", PublishedDataSetDataType.TYPE_ID);
            PubSubConnectionDataType[] connections = (PubSubConnectionDataType[]) decoder.readStructArray("Connections", PubSubConnectionDataType.TYPE_ID);
            Boolean enabled = decoder.readBoolean("Enabled");
            return new PubSubConfigurationDataType(publishedDataSets, connections, enabled);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           PubSubConfigurationDataType value) {
            encoder.writeStructArray("PublishedDataSets", value.getPublishedDataSets(), PublishedDataSetDataType.TYPE_ID);
            encoder.writeStructArray("Connections", value.getConnections(), PubSubConnectionDataType.TYPE_ID);
            encoder.writeBoolean("Enabled", value.getEnabled());
        }
    }
}
