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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

@UaDataType("ModelChangeStructureDataType")
public class ModelChangeStructureDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.ModelChangeStructureDataType;
    public static final NodeId BinaryEncodingId = Identifiers.ModelChangeStructureDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ModelChangeStructureDataType_Encoding_DefaultXml;

    protected final NodeId _affected;
    protected final NodeId _affectedType;
    protected final UByte _verb;

    public ModelChangeStructureDataType() {
        this._affected = null;
        this._affectedType = null;
        this._verb = null;
    }

    public ModelChangeStructureDataType(NodeId _affected, NodeId _affectedType, UByte _verb) {
        this._affected = _affected;
        this._affectedType = _affectedType;
        this._verb = _verb;
    }

    public NodeId getAffected() { return _affected; }

    public NodeId getAffectedType() { return _affectedType; }

    public UByte getVerb() { return _verb; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Affected", _affected)
            .add("AffectedType", _affectedType)
            .add("Verb", _verb)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ModelChangeStructureDataType> {
        @Override
        public ModelChangeStructureDataType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _affected = reader.readNodeId();
            NodeId _affectedType = reader.readNodeId();
            UByte _verb = reader.readByte();

            return new ModelChangeStructureDataType(_affected, _affectedType, _verb);
        }

        @Override
        public void encode(SerializationContext context, ModelChangeStructureDataType encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(encodable._affected);
            writer.writeNodeId(encodable._affectedType);
            writer.writeByte(encodable._verb);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ModelChangeStructureDataType> {
        @Override
        public ModelChangeStructureDataType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _affected = reader.readNodeId("Affected");
            NodeId _affectedType = reader.readNodeId("AffectedType");
            UByte _verb = reader.readByte("Verb");

            return new ModelChangeStructureDataType(_affected, _affectedType, _verb);
        }

        @Override
        public void encode(SerializationContext context, ModelChangeStructureDataType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("Affected", encodable._affected);
            writer.writeNodeId("AffectedType", encodable._affectedType);
            writer.writeByte("Verb", encodable._verb);
        }
    }

}
