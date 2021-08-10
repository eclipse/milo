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
public class BrokerWriterGroupTransportDataType extends WriterGroupTransportDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15667");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15727");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16021");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16524");

    private final String queueName;

    private final String resourceUri;

    private final String authenticationProfileUri;

    private final BrokerTransportQualityOfService requestedDeliveryGuarantee;

    public BrokerWriterGroupTransportDataType(String queueName, String resourceUri,
                                              String authenticationProfileUri, BrokerTransportQualityOfService requestedDeliveryGuarantee) {
        this.queueName = queueName;
        this.resourceUri = resourceUri;
        this.authenticationProfileUri = authenticationProfileUri;
        this.requestedDeliveryGuarantee = requestedDeliveryGuarantee;
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

    public static final class Codec extends GenericDataTypeCodec<BrokerWriterGroupTransportDataType> {
        @Override
        public Class<BrokerWriterGroupTransportDataType> getType() {
            return BrokerWriterGroupTransportDataType.class;
        }

        @Override
        public BrokerWriterGroupTransportDataType decode(SerializationContext context,
                                                         UaDecoder decoder) {
            String queueName = decoder.readString("QueueName");
            String resourceUri = decoder.readString("ResourceUri");
            String authenticationProfileUri = decoder.readString("AuthenticationProfileUri");
            BrokerTransportQualityOfService requestedDeliveryGuarantee = decoder.readEnum("RequestedDeliveryGuarantee", BrokerTransportQualityOfService.class);
            return new BrokerWriterGroupTransportDataType(queueName, resourceUri, authenticationProfileUri, requestedDeliveryGuarantee);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           BrokerWriterGroupTransportDataType value) {
            encoder.writeString("QueueName", value.getQueueName());
            encoder.writeString("ResourceUri", value.getResourceUri());
            encoder.writeString("AuthenticationProfileUri", value.getAuthenticationProfileUri());
            encoder.writeEnum("RequestedDeliveryGuarantee", value.getRequestedDeliveryGuarantee());
        }
    }
}
