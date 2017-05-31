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

@UaDataType("AddNodesRequest")
public class AddNodesRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.AddNodesRequest;
    public static final NodeId BinaryEncodingId = Identifiers.AddNodesRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AddNodesRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final AddNodesItem[] _nodesToAdd;

    public AddNodesRequest() {
        this._requestHeader = null;
        this._nodesToAdd = null;
    }

    public AddNodesRequest(RequestHeader _requestHeader, AddNodesItem[] _nodesToAdd) {
        this._requestHeader = _requestHeader;
        this._nodesToAdd = _nodesToAdd;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    @Nullable
    public AddNodesItem[] getNodesToAdd() { return _nodesToAdd; }

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
            .add("NodesToAdd", _nodesToAdd)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<AddNodesRequest> {
        @Override
        public AddNodesRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            AddNodesItem[] _nodesToAdd =
                reader.readArray(
                    () -> (AddNodesItem) context.decode(
                        AddNodesItem.BinaryEncodingId, reader),
                    AddNodesItem.class
                );

            return new AddNodesRequest(_requestHeader, _nodesToAdd);
        }

        @Override
        public void encode(SerializationContext context, AddNodesRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, encodable._requestHeader, writer);
            writer.writeArray(
                encodable._nodesToAdd,
                e -> context.encode(AddNodesItem.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<AddNodesRequest> {
        @Override
        public AddNodesRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            AddNodesItem[] _nodesToAdd =
                reader.readArray(
                    "NodesToAdd",
                    f -> (AddNodesItem) context.decode(
                        AddNodesItem.XmlEncodingId, reader),
                    AddNodesItem.class
                );

            return new AddNodesRequest(_requestHeader, _nodesToAdd);
        }

        @Override
        public void encode(SerializationContext context, AddNodesRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeArray(
                "NodesToAdd",
                encodable._nodesToAdd,
                (f, e) -> context.encode(AddNodesItem.XmlEncodingId, e, writer)
            );
        }
    }

}
