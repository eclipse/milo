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

public class AddReferencesRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.AddReferencesRequest;
    public static final NodeId BinaryEncodingId = Identifiers.AddReferencesRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AddReferencesRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final AddReferencesItem[] referencesToAdd;

    public AddReferencesRequest() {
        this.requestHeader = null;
        this.referencesToAdd = null;
    }

    public AddReferencesRequest(RequestHeader requestHeader, AddReferencesItem[] referencesToAdd) {
        this.requestHeader = requestHeader;
        this.referencesToAdd = referencesToAdd;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    @Nullable
    public AddReferencesItem[] getReferencesToAdd() { return referencesToAdd; }

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
            .add("ReferencesToAdd", referencesToAdd)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<AddReferencesRequest> {

        @Override
        public Class<AddReferencesRequest> getType() {
            return AddReferencesRequest.class;
        }

        @Override
        public AddReferencesRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            AddReferencesItem[] referencesToAdd =
                decoder.readBuiltinStructArray(
                    "ReferencesToAdd",
                    AddReferencesItem.class
                );

            return new AddReferencesRequest(requestHeader, referencesToAdd);
        }

        @Override
        public void encode(AddReferencesRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBuiltinStructArray(
                "ReferencesToAdd",
                value.referencesToAdd,
                AddReferencesItem.class
            );
        }
    }

}
