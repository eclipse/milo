/*
 * Copyright (c) 2019 the Eclipse Milo Authors
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
public class EndpointConfiguration extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=331");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=332");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=333");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15199");

    private final Integer operationTimeout;

    private final Boolean useBinaryEncoding;

    private final Integer maxStringLength;

    private final Integer maxByteStringLength;

    private final Integer maxArrayLength;

    private final Integer maxMessageSize;

    private final Integer maxBufferSize;

    private final Integer channelLifetime;

    private final Integer securityTokenLifetime;

    public EndpointConfiguration(Integer operationTimeout, Boolean useBinaryEncoding,
                                 Integer maxStringLength, Integer maxByteStringLength, Integer maxArrayLength,
                                 Integer maxMessageSize, Integer maxBufferSize, Integer channelLifetime,
                                 Integer securityTokenLifetime) {
        this.operationTimeout = operationTimeout;
        this.useBinaryEncoding = useBinaryEncoding;
        this.maxStringLength = maxStringLength;
        this.maxByteStringLength = maxByteStringLength;
        this.maxArrayLength = maxArrayLength;
        this.maxMessageSize = maxMessageSize;
        this.maxBufferSize = maxBufferSize;
        this.channelLifetime = channelLifetime;
        this.securityTokenLifetime = securityTokenLifetime;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    public Integer getOperationTimeout() {
        return operationTimeout;
    }

    public Boolean getUseBinaryEncoding() {
        return useBinaryEncoding;
    }

    public Integer getMaxStringLength() {
        return maxStringLength;
    }

    public Integer getMaxByteStringLength() {
        return maxByteStringLength;
    }

    public Integer getMaxArrayLength() {
        return maxArrayLength;
    }

    public Integer getMaxMessageSize() {
        return maxMessageSize;
    }

    public Integer getMaxBufferSize() {
        return maxBufferSize;
    }

    public Integer getChannelLifetime() {
        return channelLifetime;
    }

    public Integer getSecurityTokenLifetime() {
        return securityTokenLifetime;
    }

    public static final class Codec extends GenericDataTypeCodec<EndpointConfiguration> {
        @Override
        public Class<EndpointConfiguration> getType() {
            return EndpointConfiguration.class;
        }

        @Override
        public EndpointConfiguration decode(SerializationContext context, UaDecoder decoder) {
            Integer operationTimeout = decoder.readInt32("OperationTimeout");
            Boolean useBinaryEncoding = decoder.readBoolean("UseBinaryEncoding");
            Integer maxStringLength = decoder.readInt32("MaxStringLength");
            Integer maxByteStringLength = decoder.readInt32("MaxByteStringLength");
            Integer maxArrayLength = decoder.readInt32("MaxArrayLength");
            Integer maxMessageSize = decoder.readInt32("MaxMessageSize");
            Integer maxBufferSize = decoder.readInt32("MaxBufferSize");
            Integer channelLifetime = decoder.readInt32("ChannelLifetime");
            Integer securityTokenLifetime = decoder.readInt32("SecurityTokenLifetime");
            return new EndpointConfiguration(operationTimeout, useBinaryEncoding, maxStringLength, maxByteStringLength, maxArrayLength, maxMessageSize, maxBufferSize, channelLifetime, securityTokenLifetime);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           EndpointConfiguration value) {
            encoder.writeInt32("OperationTimeout", value.getOperationTimeout());
            encoder.writeBoolean("UseBinaryEncoding", value.getUseBinaryEncoding());
            encoder.writeInt32("MaxStringLength", value.getMaxStringLength());
            encoder.writeInt32("MaxByteStringLength", value.getMaxByteStringLength());
            encoder.writeInt32("MaxArrayLength", value.getMaxArrayLength());
            encoder.writeInt32("MaxMessageSize", value.getMaxMessageSize());
            encoder.writeInt32("MaxBufferSize", value.getMaxBufferSize());
            encoder.writeInt32("ChannelLifetime", value.getChannelLifetime());
            encoder.writeInt32("SecurityTokenLifetime", value.getSecurityTokenLifetime());
        }
    }
}
