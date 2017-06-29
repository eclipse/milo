/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class EndpointConfiguration implements UaStructure {

    public static final NodeId TypeId = Identifiers.EndpointConfiguration;
    public static final NodeId BinaryEncodingId = Identifiers.EndpointConfiguration_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EndpointConfiguration_Encoding_DefaultXml;

    protected final Integer operationTimeout;
    protected final Boolean useBinaryEncoding;
    protected final Integer maxStringLength;
    protected final Integer maxByteStringLength;
    protected final Integer maxArrayLength;
    protected final Integer maxMessageSize;
    protected final Integer maxBufferSize;
    protected final Integer channelLifetime;
    protected final Integer securityTokenLifetime;

    public EndpointConfiguration() {
        this.operationTimeout = null;
        this.useBinaryEncoding = null;
        this.maxStringLength = null;
        this.maxByteStringLength = null;
        this.maxArrayLength = null;
        this.maxMessageSize = null;
        this.maxBufferSize = null;
        this.channelLifetime = null;
        this.securityTokenLifetime = null;
    }

    public EndpointConfiguration(Integer operationTimeout, Boolean useBinaryEncoding, Integer maxStringLength, Integer maxByteStringLength, Integer maxArrayLength, Integer maxMessageSize, Integer maxBufferSize, Integer channelLifetime, Integer securityTokenLifetime) {
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

    public Integer getOperationTimeout() { return operationTimeout; }

    public Boolean getUseBinaryEncoding() { return useBinaryEncoding; }

    public Integer getMaxStringLength() { return maxStringLength; }

    public Integer getMaxByteStringLength() { return maxByteStringLength; }

    public Integer getMaxArrayLength() { return maxArrayLength; }

    public Integer getMaxMessageSize() { return maxMessageSize; }

    public Integer getMaxBufferSize() { return maxBufferSize; }

    public Integer getChannelLifetime() { return channelLifetime; }

    public Integer getSecurityTokenLifetime() { return securityTokenLifetime; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("OperationTimeout", operationTimeout)
            .add("UseBinaryEncoding", useBinaryEncoding)
            .add("MaxStringLength", maxStringLength)
            .add("MaxByteStringLength", maxByteStringLength)
            .add("MaxArrayLength", maxArrayLength)
            .add("MaxMessageSize", maxMessageSize)
            .add("MaxBufferSize", maxBufferSize)
            .add("ChannelLifetime", channelLifetime)
            .add("SecurityTokenLifetime", securityTokenLifetime)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<EndpointConfiguration> {

        @Override
        public Class<EndpointConfiguration> getType() {
            return EndpointConfiguration.class;
        }

        @Override
        public EndpointConfiguration decode(UaDecoder decoder) throws UaSerializationException {
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
        public void encode(EndpointConfiguration value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeInt32("OperationTimeout", value.operationTimeout);
            encoder.writeBoolean("UseBinaryEncoding", value.useBinaryEncoding);
            encoder.writeInt32("MaxStringLength", value.maxStringLength);
            encoder.writeInt32("MaxByteStringLength", value.maxByteStringLength);
            encoder.writeInt32("MaxArrayLength", value.maxArrayLength);
            encoder.writeInt32("MaxMessageSize", value.maxMessageSize);
            encoder.writeInt32("MaxBufferSize", value.maxBufferSize);
            encoder.writeInt32("ChannelLifetime", value.channelLifetime);
            encoder.writeInt32("SecurityTokenLifetime", value.securityTokenLifetime);
        }
    }

}
