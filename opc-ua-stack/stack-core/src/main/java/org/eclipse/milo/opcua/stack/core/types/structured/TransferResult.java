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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class TransferResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.TransferResult;
    public static final NodeId BinaryEncodingId = Identifiers.TransferResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TransferResult_Encoding_DefaultXml;

    protected final StatusCode statusCode;
    protected final UInteger[] availableSequenceNumbers;

    public TransferResult() {
        this.statusCode = null;
        this.availableSequenceNumbers = null;
    }

    public TransferResult(StatusCode statusCode, UInteger[] availableSequenceNumbers) {
        this.statusCode = statusCode;
        this.availableSequenceNumbers = availableSequenceNumbers;
    }

    public StatusCode getStatusCode() { return statusCode; }

    @Nullable
    public UInteger[] getAvailableSequenceNumbers() { return availableSequenceNumbers; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StatusCode", statusCode)
            .add("AvailableSequenceNumbers", availableSequenceNumbers)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<TransferResult> {

        @Override
        public Class<TransferResult> getType() {
            return TransferResult.class;
        }

        @Override
        public TransferResult decode(UaDecoder decoder) throws UaSerializationException {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            UInteger[] availableSequenceNumbers = decoder.readArray("AvailableSequenceNumbers", decoder::readUInt32, UInteger.class);

            return new TransferResult(statusCode, availableSequenceNumbers);
        }

        @Override
        public void encode(TransferResult value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeStatusCode("StatusCode", value.statusCode);
            encoder.writeArray("AvailableSequenceNumbers", value.availableSequenceNumbers, encoder::writeUInt32);
        }
    }

}
