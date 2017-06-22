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

@UaDataType("TranslateBrowsePathsToNodeIdsRequest")
public class TranslateBrowsePathsToNodeIdsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.TranslateBrowsePathsToNodeIdsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.TranslateBrowsePathsToNodeIdsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TranslateBrowsePathsToNodeIdsRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final BrowsePath[] _browsePaths;

    public TranslateBrowsePathsToNodeIdsRequest() {
        this._requestHeader = null;
        this._browsePaths = null;
    }

    public TranslateBrowsePathsToNodeIdsRequest(RequestHeader _requestHeader, BrowsePath[] _browsePaths) {
        this._requestHeader = _requestHeader;
        this._browsePaths = _browsePaths;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    @Nullable
    public BrowsePath[] getBrowsePaths() { return _browsePaths; }

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
            .add("BrowsePaths", _browsePaths)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<TranslateBrowsePathsToNodeIdsRequest> {
        @Override
        public TranslateBrowsePathsToNodeIdsRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            BrowsePath[] _browsePaths =
                reader.readArray(
                    () -> (BrowsePath) context.decode(
                        BrowsePath.BinaryEncodingId, reader),
                    BrowsePath.class
                );

            return new TranslateBrowsePathsToNodeIdsRequest(_requestHeader, _browsePaths);
        }

        @Override
        public void encode(SerializationContext context, TranslateBrowsePathsToNodeIdsRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
            writer.writeArray(
                value._browsePaths,
                e -> context.encode(BrowsePath.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<TranslateBrowsePathsToNodeIdsRequest> {
        @Override
        public TranslateBrowsePathsToNodeIdsRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            BrowsePath[] _browsePaths =
                reader.readArray(
                    "BrowsePaths",
                    f -> (BrowsePath) context.decode(
                        BrowsePath.XmlEncodingId, reader),
                    BrowsePath.class
                );

            return new TranslateBrowsePathsToNodeIdsRequest(_requestHeader, _browsePaths);
        }

        @Override
        public void encode(SerializationContext context, TranslateBrowsePathsToNodeIdsRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeArray(
                "BrowsePaths",
                encodable._browsePaths,
                (f, e) -> context.encode(BrowsePath.XmlEncodingId, e, writer)
            );
        }
    }

}
