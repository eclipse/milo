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


    public static void encode(SemanticChangeStructureDataType semanticChangeStructureDataType, UaEncoder encoder) {
        encoder.encodeNodeId("Affected", semanticChangeStructureDataType._affected);
        encoder.encodeNodeId("AffectedType", semanticChangeStructureDataType._affectedType);
    }

    public static SemanticChangeStructureDataType decode(UaDecoder decoder) {
        NodeId _affected = decoder.decodeNodeId("Affected");
        NodeId _affectedType = decoder.decodeNodeId("AffectedType");

        return new SemanticChangeStructureDataType(_affected, _affectedType);
    }

    static {
        DelegateRegistry.registerEncoder(SemanticChangeStructureDataType::encode, SemanticChangeStructureDataType.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(SemanticChangeStructureDataType::decode, SemanticChangeStructureDataType.class, BinaryEncodingId, XmlEncodingId);
    }

}
