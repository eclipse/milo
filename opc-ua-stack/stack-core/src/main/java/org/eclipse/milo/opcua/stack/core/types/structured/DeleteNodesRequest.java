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

public class DeleteNodesRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.DeleteNodesRequest;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteNodesRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteNodesRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final DeleteNodesItem[] nodesToDelete;

    public DeleteNodesRequest() {
        this.requestHeader = null;
        this.nodesToDelete = null;
    }

    public DeleteNodesRequest(RequestHeader requestHeader, DeleteNodesItem[] nodesToDelete) {
        this.requestHeader = requestHeader;
        this.nodesToDelete = nodesToDelete;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    @Nullable
    public DeleteNodesItem[] getNodesToDelete() { return nodesToDelete; }

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
            .add("NodesToDelete", nodesToDelete)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<DeleteNodesRequest> {

        @Override
        public Class<DeleteNodesRequest> getType() {
            return DeleteNodesRequest.class;
        }

        @Override
        public DeleteNodesRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            DeleteNodesItem[] nodesToDelete =
                decoder.readBuiltinStructArray(
                    "NodesToDelete",
                    DeleteNodesItem.class
                );

            return new DeleteNodesRequest(requestHeader, nodesToDelete);
        }

        @Override
        public void encode(DeleteNodesRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBuiltinStructArray(
                "NodesToDelete",
                value.nodesToDelete,
                DeleteNodesItem.class
            );
        }
    }

}
