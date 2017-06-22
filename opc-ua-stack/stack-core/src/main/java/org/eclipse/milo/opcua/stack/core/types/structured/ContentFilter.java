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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("ContentFilter")
public class ContentFilter implements UaStructure {

    public static final NodeId TypeId = Identifiers.ContentFilter;
    public static final NodeId BinaryEncodingId = Identifiers.ContentFilter_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ContentFilter_Encoding_DefaultXml;

    protected final ContentFilterElement[] _elements;

    public ContentFilter() {
        this._elements = null;
    }

    public ContentFilter(ContentFilterElement[] _elements) {
        this._elements = _elements;
    }

    @Nullable
    public ContentFilterElement[] getElements() { return _elements; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Elements", _elements)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ContentFilter> {
        @Override
        public ContentFilter decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ContentFilterElement[] _elements =
                reader.readArray(
                    () -> (ContentFilterElement) context.decode(
                        ContentFilterElement.BinaryEncodingId, reader),
                    ContentFilterElement.class
                );

            return new ContentFilter(_elements);
        }

        @Override
        public void encode(SerializationContext context, ContentFilter value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeArray(
                value._elements,
                e -> context.encode(ContentFilterElement.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ContentFilter> {
        @Override
        public ContentFilter decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ContentFilterElement[] _elements =
                reader.readArray(
                    "Elements",
                    f -> (ContentFilterElement) context.decode(
                        ContentFilterElement.XmlEncodingId, reader),
                    ContentFilterElement.class
                );

            return new ContentFilter(_elements);
        }

        @Override
        public void encode(SerializationContext context, ContentFilter encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeArray(
                "Elements",
                encodable._elements,
                (f, e) -> context.encode(ContentFilterElement.XmlEncodingId, e, writer)
            );
        }
    }

}
