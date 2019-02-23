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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class CloseSecureChannelResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.CloseSecureChannelResponse;
    public static final NodeId BinaryEncodingId = Identifiers.CloseSecureChannelResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CloseSecureChannelResponse_Encoding_DefaultXml;

    protected final ResponseHeader responseHeader;

    public CloseSecureChannelResponse() {
        this.responseHeader = null;
    }

    public CloseSecureChannelResponse(ResponseHeader responseHeader) {
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

    public static class Codec extends BuiltinDataTypeCodec<CloseSecureChannelResponse> {

        @Override
        public Class<CloseSecureChannelResponse> getType() {
            return CloseSecureChannelResponse.class;
        }

        @Override
        public CloseSecureChannelResponse decode(UaDecoder decoder) throws UaSerializationException {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readBuiltinStruct("ResponseHeader", ResponseHeader.class);

            return new CloseSecureChannelResponse(responseHeader);
        }

        @Override
        public void encode(CloseSecureChannelResponse value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ResponseHeader", value.responseHeader, ResponseHeader.class);
        }
    }

}
