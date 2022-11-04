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
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrokerTransportQualityOfService;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.4.2/#6.4.2.3.5">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.4.2/#6.4.2.3.5</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class BrokerWriterGroupTransportDataType extends WriterGroupTransportDataType implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15667");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15727");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=16021");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16524");

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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15727),
            new NodeId(0, 15611),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("QueueName", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("ResourceUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("AuthenticationProfileUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("RequestedDeliveryGuarantee", LocalizedText.NULL_VALUE, new NodeId(0, 15008), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<BrokerWriterGroupTransportDataType> {
        @Override
        public Class<BrokerWriterGroupTransportDataType> getType() {
            return BrokerWriterGroupTransportDataType.class;
        }

        @Override
        public BrokerWriterGroupTransportDataType decodeType(EncodingContext context,
                                                             UaDecoder decoder) {
            String queueName = decoder.decodeString("QueueName");
            String resourceUri = decoder.decodeString("ResourceUri");
            String authenticationProfileUri = decoder.decodeString("AuthenticationProfileUri");
            BrokerTransportQualityOfService requestedDeliveryGuarantee = BrokerTransportQualityOfService.from(decoder.decodeEnum("RequestedDeliveryGuarantee"));
            return new BrokerWriterGroupTransportDataType(queueName, resourceUri, authenticationProfileUri, requestedDeliveryGuarantee);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               BrokerWriterGroupTransportDataType value) {
            encoder.encodeString("QueueName", value.getQueueName());
            encoder.encodeString("ResourceUri", value.getResourceUri());
            encoder.encodeString("AuthenticationProfileUri", value.getAuthenticationProfileUri());
            encoder.encodeEnum("RequestedDeliveryGuarantee", value.getRequestedDeliveryGuarantee());
        }
    }
}
