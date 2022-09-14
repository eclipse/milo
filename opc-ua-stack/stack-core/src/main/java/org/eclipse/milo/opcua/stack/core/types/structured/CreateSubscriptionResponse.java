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
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.13.2/#5.13.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.13.2/#5.13.2.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class CreateSubscriptionResponse extends Structure implements UaResponseMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=788");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=790");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=789");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15338");

    private final ResponseHeader responseHeader;

    private final UInteger subscriptionId;

    private final Double revisedPublishingInterval;

    private final UInteger revisedLifetimeCount;

    private final UInteger revisedMaxKeepAliveCount;

    public CreateSubscriptionResponse(ResponseHeader responseHeader, UInteger subscriptionId,
                                      Double revisedPublishingInterval, UInteger revisedLifetimeCount,
                                      UInteger revisedMaxKeepAliveCount) {
        this.responseHeader = responseHeader;
        this.subscriptionId = subscriptionId;
        this.revisedPublishingInterval = revisedPublishingInterval;
        this.revisedLifetimeCount = revisedLifetimeCount;
        this.revisedMaxKeepAliveCount = revisedMaxKeepAliveCount;
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

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public UInteger getSubscriptionId() {
        return subscriptionId;
    }

    public Double getRevisedPublishingInterval() {
        return revisedPublishingInterval;
    }

    public UInteger getRevisedLifetimeCount() {
        return revisedLifetimeCount;
    }

    public UInteger getRevisedMaxKeepAliveCount() {
        return revisedMaxKeepAliveCount;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 790),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ResponseHeader", LocalizedText.NULL_VALUE, new NodeId(0, 392), -1, null, UInteger.valueOf(0), false),
                new StructureField("SubscriptionId", LocalizedText.NULL_VALUE, new NodeId(0, 288), -1, null, UInteger.valueOf(0), false),
                new StructureField("RevisedPublishingInterval", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("RevisedLifetimeCount", LocalizedText.NULL_VALUE, new NodeId(0, 289), -1, null, UInteger.valueOf(0), false),
                new StructureField("RevisedMaxKeepAliveCount", LocalizedText.NULL_VALUE, new NodeId(0, 289), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<CreateSubscriptionResponse> {
        @Override
        public Class<CreateSubscriptionResponse> getType() {
            return CreateSubscriptionResponse.class;
        }

        @Override
        public CreateSubscriptionResponse decodeType(SerializationContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.decodeStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            UInteger subscriptionId = decoder.decodeUInt32("SubscriptionId");
            Double revisedPublishingInterval = decoder.decodeDouble("RevisedPublishingInterval");
            UInteger revisedLifetimeCount = decoder.decodeUInt32("RevisedLifetimeCount");
            UInteger revisedMaxKeepAliveCount = decoder.decodeUInt32("RevisedMaxKeepAliveCount");
            return new CreateSubscriptionResponse(responseHeader, subscriptionId, revisedPublishingInterval, revisedLifetimeCount, revisedMaxKeepAliveCount);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               CreateSubscriptionResponse value) {
            encoder.encodeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.encodeUInt32("SubscriptionId", value.getSubscriptionId());
            encoder.encodeDouble("RevisedPublishingInterval", value.getRevisedPublishingInterval());
            encoder.encodeUInt32("RevisedLifetimeCount", value.getRevisedLifetimeCount());
            encoder.encodeUInt32("RevisedMaxKeepAliveCount", value.getRevisedMaxKeepAliveCount());
        }
    }
}
