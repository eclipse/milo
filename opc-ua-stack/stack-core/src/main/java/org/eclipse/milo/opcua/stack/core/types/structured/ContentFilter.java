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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class ContentFilter implements UaStructure {

    public static final NodeId TypeId = Identifiers.ContentFilter;
    public static final NodeId BinaryEncodingId = Identifiers.ContentFilter_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ContentFilter_Encoding_DefaultXml;

    protected final ContentFilterElement[] elements;

    public ContentFilter() {
        this.elements = null;
    }

    public ContentFilter(ContentFilterElement[] elements) {
        this.elements = elements;
    }

    @Nullable
    public ContentFilterElement[] getElements() { return elements; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Elements", elements)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ContentFilter> {

        @Override
        public Class<ContentFilter> getType() {
            return ContentFilter.class;
        }

        @Override
        public ContentFilter decode(UaDecoder decoder) throws UaSerializationException {
            ContentFilterElement[] elements =
                decoder.readBuiltinStructArray(
                    "Elements",
                    ContentFilterElement.class
                );

            return new ContentFilter(elements);
        }

        @Override
        public void encode(ContentFilter value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStructArray(
                "Elements",
                value.elements,
                ContentFilterElement.class
            );
        }
    }

}
