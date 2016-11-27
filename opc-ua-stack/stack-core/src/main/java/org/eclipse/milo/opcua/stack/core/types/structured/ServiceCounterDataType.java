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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("ServiceCounterDataType")
public class ServiceCounterDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.ServiceCounterDataType;
    public static final NodeId BinaryEncodingId = Identifiers.ServiceCounterDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ServiceCounterDataType_Encoding_DefaultXml;

    protected final UInteger _totalCount;
    protected final UInteger _errorCount;

    public ServiceCounterDataType() {
        this._totalCount = null;
        this._errorCount = null;
    }

    public ServiceCounterDataType(UInteger _totalCount, UInteger _errorCount) {
        this._totalCount = _totalCount;
        this._errorCount = _errorCount;
    }

    public UInteger getTotalCount() { return _totalCount; }

    public UInteger getErrorCount() { return _errorCount; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("TotalCount", _totalCount)
            .add("ErrorCount", _errorCount)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ServiceCounterDataType> {
        @Override
        public ServiceCounterDataType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            UInteger _totalCount = reader.readUInt32();
            UInteger _errorCount = reader.readUInt32();

            return new ServiceCounterDataType(_totalCount, _errorCount);
        }

        @Override
        public void encode(SerializationContext context, ServiceCounterDataType encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32(encodable._totalCount);
            writer.writeUInt32(encodable._errorCount);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ServiceCounterDataType> {
        @Override
        public ServiceCounterDataType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            UInteger _totalCount = reader.readUInt32("TotalCount");
            UInteger _errorCount = reader.readUInt32("ErrorCount");

            return new ServiceCounterDataType(_totalCount, _errorCount);
        }

        @Override
        public void encode(SerializationContext context, ServiceCounterDataType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32("TotalCount", encodable._totalCount);
            writer.writeUInt32("ErrorCount", encodable._errorCount);
        }
    }

}
