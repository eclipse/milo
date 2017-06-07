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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("AddReferencesRequest")
public class AddReferencesRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.AddReferencesRequest;
    public static final NodeId BinaryEncodingId = Identifiers.AddReferencesRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AddReferencesRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final AddReferencesItem[] _referencesToAdd;

    public AddReferencesRequest() {
        this._requestHeader = null;
        this._referencesToAdd = null;
    }

    public AddReferencesRequest(RequestHeader _requestHeader, AddReferencesItem[] _referencesToAdd) {
        this._requestHeader = _requestHeader;
        this._referencesToAdd = _referencesToAdd;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    @Nullable
    public AddReferencesItem[] getReferencesToAdd() { return _referencesToAdd; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RequestHeader", _requestHeader)
            .add("ReferencesToAdd", _referencesToAdd)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<AddReferencesRequest> {
        @Override
        public AddReferencesRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            AddReferencesItem[] _referencesToAdd =
                reader.readArray(
                    () -> (AddReferencesItem) context.decode(
                        AddReferencesItem.BinaryEncodingId, reader),
                    AddReferencesItem.class
                );

            return new AddReferencesRequest(_requestHeader, _referencesToAdd);
        }

        @Override
        public void encode(SerializationContext context, AddReferencesRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
            writer.writeArray(
                value._referencesToAdd,
                e -> context.encode(AddReferencesItem.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<AddReferencesRequest> {
        @Override
        public AddReferencesRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            AddReferencesItem[] _referencesToAdd =
                reader.readArray(
                    "ReferencesToAdd",
                    f -> (AddReferencesItem) context.decode(
                        AddReferencesItem.XmlEncodingId, reader),
                    AddReferencesItem.class
                );

            return new AddReferencesRequest(_requestHeader, _referencesToAdd);
        }

        @Override
        public void encode(SerializationContext context, AddReferencesRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeArray(
                "ReferencesToAdd",
                encodable._referencesToAdd,
                (f, e) -> context.encode(AddReferencesItem.XmlEncodingId, e, writer)
            );
        }
    }

}
