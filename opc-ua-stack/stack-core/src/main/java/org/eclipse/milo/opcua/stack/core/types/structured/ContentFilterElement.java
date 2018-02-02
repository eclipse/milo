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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.FilterOperator;

public class ContentFilterElement implements UaStructure {

    public static final NodeId TypeId = Identifiers.ContentFilterElement;
    public static final NodeId BinaryEncodingId = Identifiers.ContentFilterElement_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ContentFilterElement_Encoding_DefaultXml;

    protected final FilterOperator filterOperator;
    protected final ExtensionObject[] filterOperands;

    public ContentFilterElement() {
        this.filterOperator = null;
        this.filterOperands = null;
    }

    public ContentFilterElement(FilterOperator filterOperator, ExtensionObject[] filterOperands) {
        this.filterOperator = filterOperator;
        this.filterOperands = filterOperands;
    }

    public FilterOperator getFilterOperator() { return filterOperator; }

    @Nullable
    public ExtensionObject[] getFilterOperands() { return filterOperands; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("FilterOperator", filterOperator)
            .add("FilterOperands", filterOperands)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ContentFilterElement> {

        @Override
        public Class<ContentFilterElement> getType() {
            return ContentFilterElement.class;
        }

        @Override
        public ContentFilterElement decode(UaDecoder decoder) throws UaSerializationException {
            FilterOperator filterOperator = FilterOperator.from(decoder.readInt32("FilterOperator"));
            ExtensionObject[] filterOperands = decoder.readArray("FilterOperands", decoder::readExtensionObject, ExtensionObject.class);

            return new ContentFilterElement(filterOperator, filterOperands);
        }

        @Override
        public void encode(ContentFilterElement value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeInt32("FilterOperator", value.filterOperator != null ? value.filterOperator.getValue() : 0);
            encoder.writeArray("FilterOperands", value.filterOperands, encoder::writeExtensionObject);
        }
    }

}
