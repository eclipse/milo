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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class BrowseResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.BrowseResult;
    public static final NodeId BinaryEncodingId = Identifiers.BrowseResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowseResult_Encoding_DefaultXml;

    protected final StatusCode statusCode;
    protected final ByteString continuationPoint;
    protected final ReferenceDescription[] references;

    public BrowseResult() {
        this.statusCode = null;
        this.continuationPoint = null;
        this.references = null;
    }

    public BrowseResult(StatusCode statusCode, ByteString continuationPoint, ReferenceDescription[] references) {
        this.statusCode = statusCode;
        this.continuationPoint = continuationPoint;
        this.references = references;
    }

    public StatusCode getStatusCode() { return statusCode; }

    public ByteString getContinuationPoint() { return continuationPoint; }

    @Nullable
    public ReferenceDescription[] getReferences() { return references; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StatusCode", statusCode)
            .add("ContinuationPoint", continuationPoint)
            .add("References", references)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<BrowseResult> {

        @Override
        public Class<BrowseResult> getType() {
            return BrowseResult.class;
        }

        @Override
        public BrowseResult decode(UaDecoder decoder) throws UaSerializationException {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            ByteString continuationPoint = decoder.readByteString("ContinuationPoint");
            ReferenceDescription[] references =
                decoder.readBuiltinStructArray(
                    "References",
                    ReferenceDescription.class
                );

            return new BrowseResult(statusCode, continuationPoint, references);
        }

        @Override
        public void encode(BrowseResult value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeStatusCode("StatusCode", value.statusCode);
            encoder.writeByteString("ContinuationPoint", value.continuationPoint);
            encoder.writeBuiltinStructArray(
                "References",
                value.references,
                ReferenceDescription.class
            );
        }
    }

}
