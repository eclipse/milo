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

@UaDataType("SemanticChangeStructureDataType")
public class SemanticChangeStructureDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.SemanticChangeStructureDataType;
    public static final NodeId BinaryEncodingId = Identifiers.SemanticChangeStructureDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SemanticChangeStructureDataType_Encoding_DefaultXml;

    protected final NodeId _affected;
    protected final NodeId _affectedType;

    public SemanticChangeStructureDataType() {
        this._affected = null;
        this._affectedType = null;
    }

    public SemanticChangeStructureDataType(NodeId _affected, NodeId _affectedType) {
        this._affected = _affected;
        this._affectedType = _affectedType;
    }

    public NodeId getAffected() { return _affected; }

    public NodeId getAffectedType() { return _affectedType; }

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
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<SemanticChangeStructureDataType> {
        @Override
        public SemanticChangeStructureDataType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _affected = reader.readNodeId();
            NodeId _affectedType = reader.readNodeId();

            return new SemanticChangeStructureDataType(_affected, _affectedType);
        }

        @Override
        public void encode(SerializationContext context, SemanticChangeStructureDataType encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(encodable._affected);
            writer.writeNodeId(encodable._affectedType);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<SemanticChangeStructureDataType> {
        @Override
        public SemanticChangeStructureDataType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _affected = reader.readNodeId("Affected");
            NodeId _affectedType = reader.readNodeId("AffectedType");

            return new SemanticChangeStructureDataType(_affected, _affectedType);
        }

        @Override
        public void encode(SerializationContext context, SemanticChangeStructureDataType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("Affected", encodable._affected);
            writer.writeNodeId("AffectedType", encodable._affectedType);
        }
    }

}
