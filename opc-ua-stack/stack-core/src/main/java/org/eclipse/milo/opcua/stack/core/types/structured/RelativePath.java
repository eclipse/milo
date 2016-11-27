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

@UaDataType("RelativePath")
public class RelativePath implements UaStructure {

    public static final NodeId TypeId = Identifiers.RelativePath;
    public static final NodeId BinaryEncodingId = Identifiers.RelativePath_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RelativePath_Encoding_DefaultXml;

    protected final RelativePathElement[] _elements;

    public RelativePath() {
        this._elements = null;
    }

    public RelativePath(RelativePathElement[] _elements) {
        this._elements = _elements;
    }

    @Nullable
    public RelativePathElement[] getElements() { return _elements; }

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

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<RelativePath> {
        @Override
        public RelativePath decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RelativePathElement[] _elements =
                reader.readArray(
                    () -> (RelativePathElement) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RelativePathElement", reader),
                    RelativePathElement.class
                );

            return new RelativePath(_elements);
        }

        @Override
        public void encode(SerializationContext context, RelativePath encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeArray(
                encodable._elements,
                e -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RelativePathElement", e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<RelativePath> {
        @Override
        public RelativePath decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RelativePathElement[] _elements =
                reader.readArray(
                    "Elements",
                    f -> (RelativePathElement) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RelativePathElement", reader),
                    RelativePathElement.class
                );

            return new RelativePath(_elements);
        }

        @Override
        public void encode(SerializationContext context, RelativePath encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeArray(
                "Elements",
                encodable._elements,
                (f, e) -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RelativePathElement", e, writer)
            );
        }
    }

}
