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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@UaDataType("BrowseResult")
public class BrowseResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.BrowseResult;
    public static final NodeId BinaryEncodingId = Identifiers.BrowseResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowseResult_Encoding_DefaultXml;

    protected final StatusCode _statusCode;
    protected final ByteString _continuationPoint;
    protected final ReferenceDescription[] _references;

    public BrowseResult() {
        this._statusCode = null;
        this._continuationPoint = null;
        this._references = null;
    }

    public BrowseResult(StatusCode _statusCode, ByteString _continuationPoint, ReferenceDescription[] _references) {
        this._statusCode = _statusCode;
        this._continuationPoint = _continuationPoint;
        this._references = _references;
    }

    public StatusCode getStatusCode() { return _statusCode; }

    public ByteString getContinuationPoint() { return _continuationPoint; }

    @Nullable
    public ReferenceDescription[] getReferences() { return _references; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StatusCode", _statusCode)
            .add("ContinuationPoint", _continuationPoint)
            .add("References", _references)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<BrowseResult> {
        @Override
        public BrowseResult decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode();
            ByteString _continuationPoint = reader.readByteString();
            ReferenceDescription[] _references =
                reader.readArray(
                    () -> (ReferenceDescription) context.decode(
                        ReferenceDescription.BinaryEncodingId, reader),
                    ReferenceDescription.class
                );

            return new BrowseResult(_statusCode, _continuationPoint, _references);
        }

        @Override
        public void encode(SerializationContext context, BrowseResult encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode(encodable._statusCode);
            writer.writeByteString(encodable._continuationPoint);
            writer.writeArray(
                encodable._references,
                e -> context.encode(ReferenceDescription.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<BrowseResult> {
        @Override
        public BrowseResult decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode("StatusCode");
            ByteString _continuationPoint = reader.readByteString("ContinuationPoint");
            ReferenceDescription[] _references =
                reader.readArray(
                    "References",
                    f -> (ReferenceDescription) context.decode(
                        ReferenceDescription.XmlEncodingId, reader),
                    ReferenceDescription.class
                );

            return new BrowseResult(_statusCode, _continuationPoint, _references);
        }

        @Override
        public void encode(SerializationContext context, BrowseResult encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode("StatusCode", encodable._statusCode);
            writer.writeByteString("ContinuationPoint", encodable._continuationPoint);
            writer.writeArray(
                "References",
                encodable._references,
                (f, e) -> context.encode(ReferenceDescription.XmlEncodingId, e, writer)
            );
        }
    }

}
