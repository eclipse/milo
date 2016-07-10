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


    public static void encode(ModelChangeStructureDataType modelChangeStructureDataType, UaEncoder encoder) {
        encoder.encodeNodeId("Affected", modelChangeStructureDataType._affected);
        encoder.encodeNodeId("AffectedType", modelChangeStructureDataType._affectedType);
        encoder.encodeByte("Verb", modelChangeStructureDataType._verb);
    }

    public static ModelChangeStructureDataType decode(UaDecoder decoder) {
        NodeId _affected = decoder.decodeNodeId("Affected");
        NodeId _affectedType = decoder.decodeNodeId("AffectedType");
        UByte _verb = decoder.decodeByte("Verb");

        return new ModelChangeStructureDataType(_affected, _affectedType, _verb);
    }

    static {
        DelegateRegistry.registerEncoder(ModelChangeStructureDataType::encode, ModelChangeStructureDataType.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ModelChangeStructureDataType::decode, ModelChangeStructureDataType.class, BinaryEncodingId, XmlEncodingId);
    }

}
