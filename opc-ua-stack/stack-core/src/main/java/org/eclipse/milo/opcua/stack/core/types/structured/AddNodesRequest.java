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

public class AddNodesRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.AddNodesRequest;
    public static final NodeId BinaryEncodingId = Identifiers.AddNodesRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AddNodesRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final AddNodesItem[] nodesToAdd;

    public AddNodesRequest() {
        this.requestHeader = null;
        this.nodesToAdd = null;
    }

    public AddNodesRequest(RequestHeader requestHeader, AddNodesItem[] nodesToAdd) {
        this.requestHeader = requestHeader;
        this.nodesToAdd = nodesToAdd;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    @Nullable
    public AddNodesItem[] getNodesToAdd() { return nodesToAdd; }

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
            .add("NodesToAdd", nodesToAdd)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<AddNodesRequest> {

        @Override
        public Class<AddNodesRequest> getType() {
            return AddNodesRequest.class;
        }

        @Override
        public AddNodesRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            AddNodesItem[] nodesToAdd =
                decoder.readBuiltinStructArray(
                    "NodesToAdd",
                    AddNodesItem.class
                );

            return new AddNodesRequest(requestHeader, nodesToAdd);
        }

        @Override
        public void encode(AddNodesRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBuiltinStructArray(
                "NodesToAdd",
                value.nodesToAdd,
                AddNodesItem.class
            );
        }
    }

}
