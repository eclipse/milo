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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("BrowseNextRequest")
public class BrowseNextRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.BrowseNextRequest;
    public static final NodeId BinaryEncodingId = Identifiers.BrowseNextRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowseNextRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final Boolean _releaseContinuationPoints;
    protected final ByteString[] _continuationPoints;

    public BrowseNextRequest() {
        this._requestHeader = null;
        this._releaseContinuationPoints = null;
        this._continuationPoints = null;
    }

    public BrowseNextRequest(RequestHeader _requestHeader, Boolean _releaseContinuationPoints, ByteString[] _continuationPoints) {
        this._requestHeader = _requestHeader;
        this._releaseContinuationPoints = _releaseContinuationPoints;
        this._continuationPoints = _continuationPoints;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public Boolean getReleaseContinuationPoints() { return _releaseContinuationPoints; }

    @Nullable
    public ByteString[] getContinuationPoints() { return _continuationPoints; }

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
            .add("ReleaseContinuationPoints", _releaseContinuationPoints)
            .add("ContinuationPoints", _continuationPoints)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<BrowseNextRequest> {
        @Override
        public BrowseNextRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            Boolean _releaseContinuationPoints = reader.readBoolean();
            ByteString[] _continuationPoints = reader.readArray(reader::readByteString, ByteString.class);

            return new BrowseNextRequest(_requestHeader, _releaseContinuationPoints, _continuationPoints);
        }

        @Override
        public void encode(SerializationContext context, BrowseNextRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            writer.writeBoolean(encodable._releaseContinuationPoints);
            writer.writeArray(encodable._continuationPoints, writer::writeByteString);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<BrowseNextRequest> {
        @Override
        public BrowseNextRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            Boolean _releaseContinuationPoints = reader.readBoolean("ReleaseContinuationPoints");
            ByteString[] _continuationPoints = reader.readArray("ContinuationPoints", reader::readByteString, ByteString.class);

            return new BrowseNextRequest(_requestHeader, _releaseContinuationPoints, _continuationPoints);
        }

        @Override
        public void encode(SerializationContext context, BrowseNextRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            writer.writeBoolean("ReleaseContinuationPoints", encodable._releaseContinuationPoints);
            writer.writeArray("ContinuationPoints", encodable._continuationPoints, writer::writeByteString);
        }
    }

}
