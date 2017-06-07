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

@UaDataType("DeleteReferencesRequest")
public class DeleteReferencesRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.DeleteReferencesRequest;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteReferencesRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteReferencesRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final DeleteReferencesItem[] _referencesToDelete;

    public DeleteReferencesRequest() {
        this._requestHeader = null;
        this._referencesToDelete = null;
    }

    public DeleteReferencesRequest(RequestHeader _requestHeader, DeleteReferencesItem[] _referencesToDelete) {
        this._requestHeader = _requestHeader;
        this._referencesToDelete = _referencesToDelete;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    @Nullable
    public DeleteReferencesItem[] getReferencesToDelete() { return _referencesToDelete; }

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
            .add("ReferencesToDelete", _referencesToDelete)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<DeleteReferencesRequest> {
        @Override
        public DeleteReferencesRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            DeleteReferencesItem[] _referencesToDelete =
                reader.readArray(
                    () -> (DeleteReferencesItem) context.decode(
                        DeleteReferencesItem.BinaryEncodingId, reader),
                    DeleteReferencesItem.class
                );

            return new DeleteReferencesRequest(_requestHeader, _referencesToDelete);
        }

        @Override
        public void encode(SerializationContext context, DeleteReferencesRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
            writer.writeArray(
                value._referencesToDelete,
                e -> context.encode(DeleteReferencesItem.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<DeleteReferencesRequest> {
        @Override
        public DeleteReferencesRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            DeleteReferencesItem[] _referencesToDelete =
                reader.readArray(
                    "ReferencesToDelete",
                    f -> (DeleteReferencesItem) context.decode(
                        DeleteReferencesItem.XmlEncodingId, reader),
                    DeleteReferencesItem.class
                );

            return new DeleteReferencesRequest(_requestHeader, _referencesToDelete);
        }

        @Override
        public void encode(SerializationContext context, DeleteReferencesRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeArray(
                "ReferencesToDelete",
                encodable._referencesToDelete,
                (f, e) -> context.encode(DeleteReferencesItem.XmlEncodingId, e, writer)
            );
        }
    }

}
