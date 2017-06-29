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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class RegisterServerResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.RegisterServerResponse;
    public static final NodeId BinaryEncodingId = Identifiers.RegisterServerResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RegisterServerResponse_Encoding_DefaultXml;

    protected final ResponseHeader responseHeader;

    public RegisterServerResponse() {
        this.responseHeader = null;
    }

    public RegisterServerResponse(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public ResponseHeader getResponseHeader() { return responseHeader; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", responseHeader)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<RegisterServerResponse> {

        @Override
        public Class<RegisterServerResponse> getType() {
            return RegisterServerResponse.class;
        }

        @Override
        public RegisterServerResponse decode(UaDecoder decoder) throws UaSerializationException {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readBuiltinStruct("ResponseHeader", ResponseHeader.class);

            return new RegisterServerResponse(responseHeader);
        }

        @Override
        public void encode(RegisterServerResponse value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ResponseHeader", value.responseHeader, ResponseHeader.class);
        }
    }

}
