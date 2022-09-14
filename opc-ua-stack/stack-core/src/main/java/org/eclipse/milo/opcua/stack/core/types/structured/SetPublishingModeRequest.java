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
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.13.4/#5.13.4.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.13.4/#5.13.4.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class SetPublishingModeRequest extends Structure implements UaRequestMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=797");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=799");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=798");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15341");

    private final RequestHeader requestHeader;

    private final Boolean publishingEnabled;

    private final UInteger[] subscriptionIds;

    public SetPublishingModeRequest(RequestHeader requestHeader, Boolean publishingEnabled,
                                    UInteger[] subscriptionIds) {
        this.requestHeader = requestHeader;
        this.publishingEnabled = publishingEnabled;
        this.subscriptionIds = subscriptionIds;
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

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public Boolean getPublishingEnabled() {
        return publishingEnabled;
    }

    public UInteger[] getSubscriptionIds() {
        return subscriptionIds;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 799),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("PublishingEnabled", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("SubscriptionIds", LocalizedText.NULL_VALUE, new NodeId(0, 288), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<SetPublishingModeRequest> {
        @Override
        public Class<SetPublishingModeRequest> getType() {
            return SetPublishingModeRequest.class;
        }

        @Override
        public SetPublishingModeRequest decodeType(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.decodeStruct("RequestHeader", RequestHeader.TYPE_ID);
            Boolean publishingEnabled = decoder.decodeBoolean("PublishingEnabled");
            UInteger[] subscriptionIds = decoder.decodeUInt32Array("SubscriptionIds");
            return new SetPublishingModeRequest(requestHeader, publishingEnabled, subscriptionIds);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               SetPublishingModeRequest value) {
            encoder.encodeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.encodeBoolean("PublishingEnabled", value.getPublishingEnabled());
            encoder.encodeUInt32Array("SubscriptionIds", value.getSubscriptionIds());
        }
    }
}
