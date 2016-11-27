/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaDataTypeManager;
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

@UaDataType("DeleteNodesRequest")
public class DeleteNodesRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.DeleteNodesRequest;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteNodesRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteNodesRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final DeleteNodesItem[] _nodesToDelete;

    public DeleteNodesRequest() {
        this._requestHeader = null;
        this._nodesToDelete = null;
    }

    public DeleteNodesRequest(RequestHeader _requestHeader, DeleteNodesItem[] _nodesToDelete) {
        this._requestHeader = _requestHeader;
        this._nodesToDelete = _nodesToDelete;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    @Nullable
    public DeleteNodesItem[] getNodesToDelete() { return _nodesToDelete; }

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
            .add("NodesToDelete", _nodesToDelete)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<DeleteNodesRequest> {
        @Override
        public DeleteNodesRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            DeleteNodesItem[] _nodesToDelete =
                reader.readArray(
                    () -> (DeleteNodesItem) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "DeleteNodesItem", reader),
                    DeleteNodesItem.class
                );

            return new DeleteNodesRequest(_requestHeader, _nodesToDelete);
        }

        @Override
        public void encode(SerializationContext context, DeleteNodesRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            writer.writeArray(
                encodable._nodesToDelete,
                e -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "DeleteNodesItem", e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<DeleteNodesRequest> {
        @Override
        public DeleteNodesRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            DeleteNodesItem[] _nodesToDelete =
                reader.readArray(
                    "NodesToDelete",
                    f -> (DeleteNodesItem) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "DeleteNodesItem", reader),
                    DeleteNodesItem.class
                );

            return new DeleteNodesRequest(_requestHeader, _nodesToDelete);
        }

        @Override
        public void encode(SerializationContext context, DeleteNodesRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            writer.writeArray(
                "NodesToDelete",
                encodable._nodesToDelete,
                (f, e) -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "DeleteNodesItem", e, writer)
            );
        }
    }

}
