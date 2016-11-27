/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("EndpointConfiguration")
public class EndpointConfiguration implements UaStructure {

    public static final NodeId TypeId = Identifiers.EndpointConfiguration;
    public static final NodeId BinaryEncodingId = Identifiers.EndpointConfiguration_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EndpointConfiguration_Encoding_DefaultXml;

    protected final Integer _operationTimeout;
    protected final Boolean _useBinaryEncoding;
    protected final Integer _maxStringLength;
    protected final Integer _maxByteStringLength;
    protected final Integer _maxArrayLength;
    protected final Integer _maxMessageSize;
    protected final Integer _maxBufferSize;
    protected final Integer _channelLifetime;
    protected final Integer _securityTokenLifetime;

    public EndpointConfiguration() {
        this._operationTimeout = null;
        this._useBinaryEncoding = null;
        this._maxStringLength = null;
        this._maxByteStringLength = null;
        this._maxArrayLength = null;
        this._maxMessageSize = null;
        this._maxBufferSize = null;
        this._channelLifetime = null;
        this._securityTokenLifetime = null;
    }

    public EndpointConfiguration(Integer _operationTimeout, Boolean _useBinaryEncoding, Integer _maxStringLength, Integer _maxByteStringLength, Integer _maxArrayLength, Integer _maxMessageSize, Integer _maxBufferSize, Integer _channelLifetime, Integer _securityTokenLifetime) {
        this._operationTimeout = _operationTimeout;
        this._useBinaryEncoding = _useBinaryEncoding;
        this._maxStringLength = _maxStringLength;
        this._maxByteStringLength = _maxByteStringLength;
        this._maxArrayLength = _maxArrayLength;
        this._maxMessageSize = _maxMessageSize;
        this._maxBufferSize = _maxBufferSize;
        this._channelLifetime = _channelLifetime;
        this._securityTokenLifetime = _securityTokenLifetime;
    }

    public Integer getOperationTimeout() { return _operationTimeout; }

    public Boolean getUseBinaryEncoding() { return _useBinaryEncoding; }

    public Integer getMaxStringLength() { return _maxStringLength; }

    public Integer getMaxByteStringLength() { return _maxByteStringLength; }

    public Integer getMaxArrayLength() { return _maxArrayLength; }

    public Integer getMaxMessageSize() { return _maxMessageSize; }

    public Integer getMaxBufferSize() { return _maxBufferSize; }

    public Integer getChannelLifetime() { return _channelLifetime; }

    public Integer getSecurityTokenLifetime() { return _securityTokenLifetime; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("OperationTimeout", _operationTimeout)
            .add("UseBinaryEncoding", _useBinaryEncoding)
            .add("MaxStringLength", _maxStringLength)
            .add("MaxByteStringLength", _maxByteStringLength)
            .add("MaxArrayLength", _maxArrayLength)
            .add("MaxMessageSize", _maxMessageSize)
            .add("MaxBufferSize", _maxBufferSize)
            .add("ChannelLifetime", _channelLifetime)
            .add("SecurityTokenLifetime", _securityTokenLifetime)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<EndpointConfiguration> {
        @Override
        public EndpointConfiguration decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            Integer _operationTimeout = reader.readInt32();
            Boolean _useBinaryEncoding = reader.readBoolean();
            Integer _maxStringLength = reader.readInt32();
            Integer _maxByteStringLength = reader.readInt32();
            Integer _maxArrayLength = reader.readInt32();
            Integer _maxMessageSize = reader.readInt32();
            Integer _maxBufferSize = reader.readInt32();
            Integer _channelLifetime = reader.readInt32();
            Integer _securityTokenLifetime = reader.readInt32();

            return new EndpointConfiguration(_operationTimeout, _useBinaryEncoding, _maxStringLength, _maxByteStringLength, _maxArrayLength, _maxMessageSize, _maxBufferSize, _channelLifetime, _securityTokenLifetime);
        }

        @Override
        public void encode(SerializationContext context, EndpointConfiguration encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeInt32(encodable._operationTimeout);
            writer.writeBoolean(encodable._useBinaryEncoding);
            writer.writeInt32(encodable._maxStringLength);
            writer.writeInt32(encodable._maxByteStringLength);
            writer.writeInt32(encodable._maxArrayLength);
            writer.writeInt32(encodable._maxMessageSize);
            writer.writeInt32(encodable._maxBufferSize);
            writer.writeInt32(encodable._channelLifetime);
            writer.writeInt32(encodable._securityTokenLifetime);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<EndpointConfiguration> {
        @Override
        public EndpointConfiguration decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            Integer _operationTimeout = reader.readInt32("OperationTimeout");
            Boolean _useBinaryEncoding = reader.readBoolean("UseBinaryEncoding");
            Integer _maxStringLength = reader.readInt32("MaxStringLength");
            Integer _maxByteStringLength = reader.readInt32("MaxByteStringLength");
            Integer _maxArrayLength = reader.readInt32("MaxArrayLength");
            Integer _maxMessageSize = reader.readInt32("MaxMessageSize");
            Integer _maxBufferSize = reader.readInt32("MaxBufferSize");
            Integer _channelLifetime = reader.readInt32("ChannelLifetime");
            Integer _securityTokenLifetime = reader.readInt32("SecurityTokenLifetime");

            return new EndpointConfiguration(_operationTimeout, _useBinaryEncoding, _maxStringLength, _maxByteStringLength, _maxArrayLength, _maxMessageSize, _maxBufferSize, _channelLifetime, _securityTokenLifetime);
        }

        @Override
        public void encode(SerializationContext context, EndpointConfiguration encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeInt32("OperationTimeout", encodable._operationTimeout);
            writer.writeBoolean("UseBinaryEncoding", encodable._useBinaryEncoding);
            writer.writeInt32("MaxStringLength", encodable._maxStringLength);
            writer.writeInt32("MaxByteStringLength", encodable._maxByteStringLength);
            writer.writeInt32("MaxArrayLength", encodable._maxArrayLength);
            writer.writeInt32("MaxMessageSize", encodable._maxMessageSize);
            writer.writeInt32("MaxBufferSize", encodable._maxBufferSize);
            writer.writeInt32("ChannelLifetime", encodable._channelLifetime);
            writer.writeInt32("SecurityTokenLifetime", encodable._securityTokenLifetime);
        }
    }

}
