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

import javax.annotation.Nullable;

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
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("TransferResult")
public class TransferResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.TransferResult;
    public static final NodeId BinaryEncodingId = Identifiers.TransferResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TransferResult_Encoding_DefaultXml;

    protected final StatusCode _statusCode;
    protected final UInteger[] _availableSequenceNumbers;

    public TransferResult() {
        this._statusCode = null;
        this._availableSequenceNumbers = null;
    }

    public TransferResult(StatusCode _statusCode, UInteger[] _availableSequenceNumbers) {
        this._statusCode = _statusCode;
        this._availableSequenceNumbers = _availableSequenceNumbers;
    }

    public StatusCode getStatusCode() { return _statusCode; }

    @Nullable
    public UInteger[] getAvailableSequenceNumbers() { return _availableSequenceNumbers; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StatusCode", _statusCode)
            .add("AvailableSequenceNumbers", _availableSequenceNumbers)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<TransferResult> {
        @Override
        public TransferResult decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode();
            UInteger[] _availableSequenceNumbers = reader.readArray(reader::readUInt32, UInteger.class);

            return new TransferResult(_statusCode, _availableSequenceNumbers);
        }

        @Override
        public void encode(SerializationContext context, TransferResult value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode(value._statusCode);
            writer.writeArray(value._availableSequenceNumbers, writer::writeUInt32);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<TransferResult> {
        @Override
        public TransferResult decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode("StatusCode");
            UInteger[] _availableSequenceNumbers = reader.readArray("AvailableSequenceNumbers", reader::readUInt32, UInteger.class);

            return new TransferResult(_statusCode, _availableSequenceNumbers);
        }

        @Override
        public void encode(SerializationContext context, TransferResult encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode("StatusCode", encodable._statusCode);
            writer.writeArray("AvailableSequenceNumbers", encodable._availableSequenceNumbers, writer::writeUInt32);
        }
    }

}
