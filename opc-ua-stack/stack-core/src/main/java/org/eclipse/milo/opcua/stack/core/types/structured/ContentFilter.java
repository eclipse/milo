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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
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

    public ContentFilterElement[] getElements() { return _elements; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ContentFilter contentFilter, UaEncoder encoder) {
        encoder.encodeArray("Elements", contentFilter._elements, encoder::encodeSerializable);
    }

    public static ContentFilter decode(UaDecoder decoder) {
        ContentFilterElement[] _elements = decoder.decodeArray("Elements", decoder::decodeSerializable, ContentFilterElement.class);

        return new ContentFilter(_elements);
    }

    static {
        DelegateRegistry.registerEncoder(ContentFilter::encode, ContentFilter.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ContentFilter::decode, ContentFilter.class, BinaryEncodingId, XmlEncodingId);
    }

}
