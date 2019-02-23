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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class FindServersResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.FindServersResponse;
    public static final NodeId BinaryEncodingId = Identifiers.FindServersResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.FindServersResponse_Encoding_DefaultXml;

    protected final ResponseHeader responseHeader;
    protected final ApplicationDescription[] servers;

    public FindServersResponse() {
        this.responseHeader = null;
        this.servers = null;
    }

    public FindServersResponse(ResponseHeader responseHeader, ApplicationDescription[] servers) {
        this.responseHeader = responseHeader;
        this.servers = servers;
    }

    public ResponseHeader getResponseHeader() { return responseHeader; }

    @Nullable
    public ApplicationDescription[] getServers() { return servers; }

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
            .add("Servers", servers)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<FindServersResponse> {

        @Override
        public Class<FindServersResponse> getType() {
            return FindServersResponse.class;
        }

        @Override
        public FindServersResponse decode(UaDecoder decoder) throws UaSerializationException {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readBuiltinStruct("ResponseHeader", ResponseHeader.class);
            ApplicationDescription[] servers =
                decoder.readBuiltinStructArray(
                    "Servers",
                    ApplicationDescription.class
                );

            return new FindServersResponse(responseHeader, servers);
        }

        @Override
        public void encode(FindServersResponse value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ResponseHeader", value.responseHeader, ResponseHeader.class);
            encoder.writeBuiltinStructArray(
                "Servers",
                value.servers,
                ApplicationDescription.class
            );
        }
    }

}
