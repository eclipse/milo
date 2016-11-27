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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("ElementOperand")
public class ElementOperand extends FilterOperand {

    public static final NodeId TypeId = Identifiers.ElementOperand;
    public static final NodeId BinaryEncodingId = Identifiers.ElementOperand_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ElementOperand_Encoding_DefaultXml;

    protected final UInteger _index;

    public ElementOperand() {
        super();
        this._index = null;
    }

    public ElementOperand(UInteger _index) {
        super();
        this._index = _index;
    }

    public UInteger getIndex() { return _index; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Index", _index)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ElementOperand> {
        @Override
        public ElementOperand decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            UInteger _index = reader.readUInt32();

            return new ElementOperand(_index);
        }

        @Override
        public void encode(SerializationContext context, ElementOperand encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32(encodable._index);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ElementOperand> {
        @Override
        public ElementOperand decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            UInteger _index = reader.readUInt32("Index");

            return new ElementOperand(_index);
        }

        @Override
        public void encode(SerializationContext context, ElementOperand encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32("Index", encodable._index);
        }
    }

}
