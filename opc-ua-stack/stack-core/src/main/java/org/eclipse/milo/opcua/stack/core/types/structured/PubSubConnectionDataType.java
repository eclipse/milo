/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.serialization.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.7/#6.2.7.5.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.7/#6.2.7.5.1</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class PubSubConnectionDataType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15617");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15694");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15992");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16281");

    private final String name;

    private final Boolean enabled;

    private final Variant publisherId;

    private final String transportProfileUri;

    private final NetworkAddressDataType address;

    private final KeyValuePair[] connectionProperties;

    private final ConnectionTransportDataType transportSettings;

    private final WriterGroupDataType[] writerGroups;

    private final ReaderGroupDataType[] readerGroups;

    public PubSubConnectionDataType(String name, Boolean enabled, Variant publisherId,
                                    String transportProfileUri, NetworkAddressDataType address,
                                    KeyValuePair[] connectionProperties, ConnectionTransportDataType transportSettings,
                                    WriterGroupDataType[] writerGroups, ReaderGroupDataType[] readerGroups) {
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

    public NetworkAddressDataType getAddress() {
        return address;
    }

    public KeyValuePair[] getConnectionProperties() {
        return connectionProperties;
    }

    public ConnectionTransportDataType getTransportSettings() {
        return transportSettings;
    }

    public WriterGroupDataType[] getWriterGroups() {
        return writerGroups;
    }

    public ReaderGroupDataType[] getReaderGroups() {
        return readerGroups;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15694),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Name", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Enabled", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("PublisherId", LocalizedText.NULL_VALUE, new NodeId(0, 24), -1, null, UInteger.valueOf(0), false),
                new StructureField("TransportProfileUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Address", LocalizedText.NULL_VALUE, new NodeId(0, 15502), -1, null, UInteger.valueOf(0), false),
                new StructureField("ConnectionProperties", LocalizedText.NULL_VALUE, new NodeId(0, 14533), 1, null, UInteger.valueOf(0), false),
                new StructureField("TransportSettings", LocalizedText.NULL_VALUE, new NodeId(0, 15618), -1, null, UInteger.valueOf(0), false),
                new StructureField("WriterGroups", LocalizedText.NULL_VALUE, new NodeId(0, 15480), 1, null, UInteger.valueOf(0), false),
                new StructureField("ReaderGroups", LocalizedText.NULL_VALUE, new NodeId(0, 15520), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<PubSubConnectionDataType> {
        @Override
        public Class<PubSubConnectionDataType> getType() {
            return PubSubConnectionDataType.class;
        }

        @Override
        public PubSubConnectionDataType decodeType(SerializationContext context, UaDecoder decoder) {
            String name = decoder.decodeString("Name");
            Boolean enabled = decoder.decodeBoolean("Enabled");
            Variant publisherId = decoder.decodeVariant("PublisherId");
            String transportProfileUri = decoder.decodeString("TransportProfileUri");
            NetworkAddressDataType address = (NetworkAddressDataType) decoder.decodeStruct("Address", NetworkAddressDataType.TYPE_ID);
            KeyValuePair[] connectionProperties = (KeyValuePair[]) decoder.decodeStructArray("ConnectionProperties", KeyValuePair.TYPE_ID);
            ConnectionTransportDataType transportSettings = (ConnectionTransportDataType) decoder.decodeStruct("TransportSettings", ConnectionTransportDataType.TYPE_ID);
            WriterGroupDataType[] writerGroups = (WriterGroupDataType[]) decoder.decodeStructArray("WriterGroups", WriterGroupDataType.TYPE_ID);
            ReaderGroupDataType[] readerGroups = (ReaderGroupDataType[]) decoder.decodeStructArray("ReaderGroups", ReaderGroupDataType.TYPE_ID);
            return new PubSubConnectionDataType(name, enabled, publisherId, transportProfileUri, address, connectionProperties, transportSettings, writerGroups, readerGroups);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               PubSubConnectionDataType value) {
            encoder.encodeString("Name", value.getName());
            encoder.encodeBoolean("Enabled", value.getEnabled());
            encoder.encodeVariant("PublisherId", value.getPublisherId());
            encoder.encodeString("TransportProfileUri", value.getTransportProfileUri());
            encoder.encodeStruct("Address", value.getAddress(), NetworkAddressDataType.TYPE_ID);
            encoder.encodeStructArray("ConnectionProperties", value.getConnectionProperties(), KeyValuePair.TYPE_ID);
            encoder.encodeStruct("TransportSettings", value.getTransportSettings(), ConnectionTransportDataType.TYPE_ID);
            encoder.encodeStructArray("WriterGroups", value.getWriterGroups(), WriterGroupDataType.TYPE_ID);
            encoder.encodeStructArray("ReaderGroups", value.getReaderGroups(), ReaderGroupDataType.TYPE_ID);
        }
    }
}
