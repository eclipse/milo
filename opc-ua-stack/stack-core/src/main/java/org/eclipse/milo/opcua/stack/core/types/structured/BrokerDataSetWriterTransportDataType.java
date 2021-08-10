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
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrokerTransportQualityOfService;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class BrokerDataSetWriterTransportDataType extends DataSetWriterTransportDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15669");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15729");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16022");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16525");

    private final String queueName;

    private final String resourceUri;

    private final String authenticationProfileUri;

    private final BrokerTransportQualityOfService requestedDeliveryGuarantee;

    private final String metaDataQueueName;

    private final Double metaDataUpdateTime;

    public BrokerDataSetWriterTransportDataType(String queueName, String resourceUri,
                                                String authenticationProfileUri, BrokerTransportQualityOfService requestedDeliveryGuarantee,
                                                String metaDataQueueName, Double metaDataUpdateTime) {
        this.queueName = queueName;
        this.resourceUri = resourceUri;
        this.authenticationProfileUri = authenticationProfileUri;
        this.requestedDeliveryGuarantee = requestedDeliveryGuarantee;
        this.metaDataQueueName = metaDataQueueName;
        this.metaDataUpdateTime = metaDataUpdateTime;
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

    public String getQueueName() {
        return queueName;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public String getAuthenticationProfileUri() {
        return authenticationProfileUri;
    }

    public BrokerTransportQualityOfService getRequestedDeliveryGuarantee() {
        return requestedDeliveryGuarantee;
    }

    public String getMetaDataQueueName() {
        return metaDataQueueName;
    }

    public Double getMetaDataUpdateTime() {
        return metaDataUpdateTime;
    }

    public static final class Codec extends GenericDataTypeCodec<BrokerDataSetWriterTransportDataType> {
        @Override
        public Class<BrokerDataSetWriterTransportDataType> getType() {
            return BrokerDataSetWriterTransportDataType.class;
        }

        @Override
        public BrokerDataSetWriterTransportDataType decode(SerializationContext context,
                                                           UaDecoder decoder) {
            String queueName = decoder.readString("QueueName");
            String resourceUri = decoder.readString("ResourceUri");
            String authenticationProfileUri = decoder.readString("AuthenticationProfileUri");
            BrokerTransportQualityOfService requestedDeliveryGuarantee = decoder.readEnum("RequestedDeliveryGuarantee", BrokerTransportQualityOfService.class);
            String metaDataQueueName = decoder.readString("MetaDataQueueName");
            Double metaDataUpdateTime = decoder.readDouble("MetaDataUpdateTime");
            return new BrokerDataSetWriterTransportDataType(queueName, resourceUri, authenticationProfileUri, requestedDeliveryGuarantee, metaDataQueueName, metaDataUpdateTime);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           BrokerDataSetWriterTransportDataType value) {
            encoder.writeString("QueueName", value.getQueueName());
            encoder.writeString("ResourceUri", value.getResourceUri());
            encoder.writeString("AuthenticationProfileUri", value.getAuthenticationProfileUri());
            encoder.writeEnum("RequestedDeliveryGuarantee", value.getRequestedDeliveryGuarantee());
            encoder.writeString("MetaDataQueueName", value.getMetaDataQueueName());
            encoder.writeDouble("MetaDataUpdateTime", value.getMetaDataUpdateTime());
        }
    }
}
