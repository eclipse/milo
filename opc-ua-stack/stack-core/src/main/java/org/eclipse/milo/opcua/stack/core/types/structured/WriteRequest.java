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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class WriteRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.WriteRequest;
    public static final NodeId BinaryEncodingId = Identifiers.WriteRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.WriteRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final WriteValue[] nodesToWrite;

    public WriteRequest() {
        this.requestHeader = null;
        this.nodesToWrite = null;
    }

    public WriteRequest(RequestHeader requestHeader, WriteValue[] nodesToWrite) {
        this.requestHeader = requestHeader;
        this.nodesToWrite = nodesToWrite;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    @Nullable
    public WriteValue[] getNodesToWrite() { return nodesToWrite; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RequestHeader", requestHeader)
            .add("NodesToWrite", nodesToWrite)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<WriteRequest> {

        @Override
        public Class<WriteRequest> getType() {
            return WriteRequest.class;
        }

        @Override
        public WriteRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            WriteValue[] nodesToWrite =
                decoder.readBuiltinStructArray(
                    "NodesToWrite",
                    WriteValue.class
                );

            return new WriteRequest(requestHeader, nodesToWrite);
        }

        @Override
        public void encode(WriteRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBuiltinStructArray(
                "NodesToWrite",
                value.nodesToWrite,
                WriteValue.class
            );
        }
    }

}
