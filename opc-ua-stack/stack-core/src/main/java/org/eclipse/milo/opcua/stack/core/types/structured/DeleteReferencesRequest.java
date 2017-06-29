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

public class DeleteReferencesRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.DeleteReferencesRequest;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteReferencesRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteReferencesRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final DeleteReferencesItem[] referencesToDelete;

    public DeleteReferencesRequest() {
        this.requestHeader = null;
        this.referencesToDelete = null;
    }

    public DeleteReferencesRequest(RequestHeader requestHeader, DeleteReferencesItem[] referencesToDelete) {
        this.requestHeader = requestHeader;
        this.referencesToDelete = referencesToDelete;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    @Nullable
    public DeleteReferencesItem[] getReferencesToDelete() { return referencesToDelete; }

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
            .add("ReferencesToDelete", referencesToDelete)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<DeleteReferencesRequest> {

        @Override
        public Class<DeleteReferencesRequest> getType() {
            return DeleteReferencesRequest.class;
        }

        @Override
        public DeleteReferencesRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            DeleteReferencesItem[] referencesToDelete =
                decoder.readBuiltinStructArray(
                    "ReferencesToDelete",
                    DeleteReferencesItem.class
                );

            return new DeleteReferencesRequest(requestHeader, referencesToDelete);
        }

        @Override
        public void encode(DeleteReferencesRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBuiltinStructArray(
                "ReferencesToDelete",
                value.referencesToDelete,
                DeleteReferencesItem.class
            );
        }
    }

}
