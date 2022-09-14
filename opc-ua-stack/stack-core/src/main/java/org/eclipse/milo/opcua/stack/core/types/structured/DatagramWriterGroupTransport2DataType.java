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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.4.1/#6.4.1.3.9">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.4.1/#6.4.1.3.9</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class DatagramWriterGroupTransport2DataType extends DatagramWriterGroupTransportDataType implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=23613");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=23865");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=23933");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=24001");

    private final NetworkAddressDataType address;

    private final String qosCategory;

    private final TransmitQosDataType[] datagramQos;

    private final UInteger discoveryAnnounceRate;

    private final String topic;

    public DatagramWriterGroupTransport2DataType(UByte messageRepeatCount, Double messageRepeatDelay,
                                                 NetworkAddressDataType address, String qosCategory, TransmitQosDataType[] datagramQos,
                                                 UInteger discoveryAnnounceRate, String topic) {
        super(messageRepeatCount, messageRepeatDelay);
        this.address = address;
        this.qosCategory = qosCategory;
        this.datagramQos = datagramQos;
        this.discoveryAnnounceRate = discoveryAnnounceRate;
        this.topic = topic;
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

    public NetworkAddressDataType getAddress() {
        return address;
    }

    public String getQosCategory() {
        return qosCategory;
    }

    public TransmitQosDataType[] getDatagramQos() {
        return datagramQos;
    }

    public UInteger getDiscoveryAnnounceRate() {
        return discoveryAnnounceRate;
    }

    public String getTopic() {
        return topic;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 23865),
            new NodeId(0, 15532),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("MessageRepeatCount", LocalizedText.NULL_VALUE, new NodeId(0, 3), -1, null, UInteger.valueOf(0), false),
                new StructureField("MessageRepeatDelay", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("Address", LocalizedText.NULL_VALUE, new NodeId(0, 15502), -1, null, UInteger.valueOf(0), false),
                new StructureField("QosCategory", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("DatagramQos", LocalizedText.NULL_VALUE, new NodeId(0, 23604), 1, null, UInteger.valueOf(0), false),
                new StructureField("DiscoveryAnnounceRate", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("Topic", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DatagramWriterGroupTransport2DataType> {
        @Override
        public Class<DatagramWriterGroupTransport2DataType> getType() {
            return DatagramWriterGroupTransport2DataType.class;
        }

        @Override
        public DatagramWriterGroupTransport2DataType decodeType(SerializationContext context,
                                                                UaDecoder decoder) {
            UByte messageRepeatCount = decoder.readByte("MessageRepeatCount");
            Double messageRepeatDelay = decoder.readDouble("MessageRepeatDelay");
            NetworkAddressDataType address = (NetworkAddressDataType) decoder.readStruct("Address", NetworkAddressDataType.TYPE_ID);
            String qosCategory = decoder.readString("QosCategory");
            TransmitQosDataType[] datagramQos = (TransmitQosDataType[]) decoder.readStructArray("DatagramQos", TransmitQosDataType.TYPE_ID);
            UInteger discoveryAnnounceRate = decoder.readUInt32("DiscoveryAnnounceRate");
            String topic = decoder.readString("Topic");
            return new DatagramWriterGroupTransport2DataType(messageRepeatCount, messageRepeatDelay, address, qosCategory, datagramQos, discoveryAnnounceRate, topic);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               DatagramWriterGroupTransport2DataType value) {
            encoder.writeByte("MessageRepeatCount", value.getMessageRepeatCount());
            encoder.writeDouble("MessageRepeatDelay", value.getMessageRepeatDelay());
            encoder.writeStruct("Address", value.getAddress(), NetworkAddressDataType.TYPE_ID);
            encoder.writeString("QosCategory", value.getQosCategory());
            encoder.writeStructArray("DatagramQos", value.getDatagramQos(), TransmitQosDataType.TYPE_ID);
            encoder.writeUInt32("DiscoveryAnnounceRate", value.getDiscoveryAnnounceRate());
            encoder.writeString("Topic", value.getTopic());
        }
    }
}
