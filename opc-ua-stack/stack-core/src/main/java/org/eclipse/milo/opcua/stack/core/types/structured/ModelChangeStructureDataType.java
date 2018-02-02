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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

public class ModelChangeStructureDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.ModelChangeStructureDataType;
    public static final NodeId BinaryEncodingId = Identifiers.ModelChangeStructureDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ModelChangeStructureDataType_Encoding_DefaultXml;

    protected final NodeId affected;
    protected final NodeId affectedType;
    protected final UByte verb;

    public ModelChangeStructureDataType() {
        this.affected = null;
        this.affectedType = null;
        this.verb = null;
    }

    public ModelChangeStructureDataType(NodeId affected, NodeId affectedType, UByte verb) {
        this.affected = affected;
        this.affectedType = affectedType;
        this.verb = verb;
    }

    public NodeId getAffected() { return affected; }

    public NodeId getAffectedType() { return affectedType; }

    public UByte getVerb() { return verb; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Affected", affected)
            .add("AffectedType", affectedType)
            .add("Verb", verb)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ModelChangeStructureDataType> {

        @Override
        public Class<ModelChangeStructureDataType> getType() {
            return ModelChangeStructureDataType.class;
        }

        @Override
        public ModelChangeStructureDataType decode(UaDecoder decoder) throws UaSerializationException {
            NodeId affected = decoder.readNodeId("Affected");
            NodeId affectedType = decoder.readNodeId("AffectedType");
            UByte verb = decoder.readByte("Verb");

            return new ModelChangeStructureDataType(affected, affectedType, verb);
        }

        @Override
        public void encode(ModelChangeStructureDataType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("Affected", value.affected);
            encoder.writeNodeId("AffectedType", value.affectedType);
            encoder.writeByte("Verb", value.verb);
        }
    }

}
