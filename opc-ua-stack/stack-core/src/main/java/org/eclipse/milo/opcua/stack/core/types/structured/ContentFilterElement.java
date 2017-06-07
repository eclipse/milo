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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.FilterOperator;

@UaDataType("ContentFilterElement")
public class ContentFilterElement implements UaStructure {

    public static final NodeId TypeId = Identifiers.ContentFilterElement;
    public static final NodeId BinaryEncodingId = Identifiers.ContentFilterElement_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ContentFilterElement_Encoding_DefaultXml;

    protected final FilterOperator _filterOperator;
    protected final ExtensionObject[] _filterOperands;

    public ContentFilterElement() {
        this._filterOperator = null;
        this._filterOperands = null;
    }

    public ContentFilterElement(FilterOperator _filterOperator, ExtensionObject[] _filterOperands) {
        this._filterOperator = _filterOperator;
        this._filterOperands = _filterOperands;
    }

    public FilterOperator getFilterOperator() { return _filterOperator; }

    @Nullable
    public ExtensionObject[] getFilterOperands() { return _filterOperands; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("FilterOperator", _filterOperator)
            .add("FilterOperands", _filterOperands)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ContentFilterElement> {
        @Override
        public ContentFilterElement decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            FilterOperator _filterOperator = FilterOperator.from(reader.readInt32());
            ExtensionObject[] _filterOperands = reader.readArray(reader::readExtensionObject, ExtensionObject.class);

            return new ContentFilterElement(_filterOperator, _filterOperands);
        }

        @Override
        public void encode(SerializationContext context, ContentFilterElement value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeInt32(value._filterOperator != null ? value._filterOperator.getValue() : 0);
            writer.writeArray(value._filterOperands, writer::writeExtensionObject);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ContentFilterElement> {
        @Override
        public ContentFilterElement decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            FilterOperator _filterOperator = FilterOperator.from(reader.readInt32("FilterOperator"));
            ExtensionObject[] _filterOperands = reader.readArray("FilterOperands", reader::readExtensionObject, ExtensionObject.class);

            return new ContentFilterElement(_filterOperator, _filterOperands);
        }

        @Override
        public void encode(SerializationContext context, ContentFilterElement encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeInt32("FilterOperator", encodable._filterOperator != null ? encodable._filterOperator.getValue() : 0);
            writer.writeArray("FilterOperands", encodable._filterOperands, writer::writeExtensionObject);
        }
    }

}
