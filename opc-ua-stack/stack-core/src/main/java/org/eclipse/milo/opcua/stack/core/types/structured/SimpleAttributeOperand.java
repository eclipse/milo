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
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("SimpleAttributeOperand")
public class SimpleAttributeOperand extends FilterOperand {

    public static final NodeId TypeId = Identifiers.SimpleAttributeOperand;
    public static final NodeId BinaryEncodingId = Identifiers.SimpleAttributeOperand_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SimpleAttributeOperand_Encoding_DefaultXml;

    protected final NodeId _typeDefinitionId;
    protected final QualifiedName[] _browsePath;
    protected final UInteger _attributeId;
    protected final String _indexRange;

    public SimpleAttributeOperand() {
        super();
        this._typeDefinitionId = null;
        this._browsePath = null;
        this._attributeId = null;
        this._indexRange = null;
    }

    public SimpleAttributeOperand(NodeId _typeDefinitionId, QualifiedName[] _browsePath, UInteger _attributeId, String _indexRange) {
        super();
        this._typeDefinitionId = _typeDefinitionId;
        this._browsePath = _browsePath;
        this._attributeId = _attributeId;
        this._indexRange = _indexRange;
    }

    public NodeId getTypeDefinitionId() { return _typeDefinitionId; }

    public QualifiedName[] getBrowsePath() { return _browsePath; }

    public UInteger getAttributeId() { return _attributeId; }

    public String getIndexRange() { return _indexRange; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(SimpleAttributeOperand simpleAttributeOperand, UaEncoder encoder) {
        encoder.encodeNodeId("TypeDefinitionId", simpleAttributeOperand._typeDefinitionId);
        encoder.encodeArray("BrowsePath", simpleAttributeOperand._browsePath, encoder::encodeQualifiedName);
        encoder.encodeUInt32("AttributeId", simpleAttributeOperand._attributeId);
        encoder.encodeString("IndexRange", simpleAttributeOperand._indexRange);
    }

    public static SimpleAttributeOperand decode(UaDecoder decoder) {
        NodeId _typeDefinitionId = decoder.decodeNodeId("TypeDefinitionId");
        QualifiedName[] _browsePath = decoder.decodeArray("BrowsePath", decoder::decodeQualifiedName, QualifiedName.class);
        UInteger _attributeId = decoder.decodeUInt32("AttributeId");
        String _indexRange = decoder.decodeString("IndexRange");

        return new SimpleAttributeOperand(_typeDefinitionId, _browsePath, _attributeId, _indexRange);
    }

    static {
        DelegateRegistry.registerEncoder(SimpleAttributeOperand::encode, SimpleAttributeOperand.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(SimpleAttributeOperand::decode, SimpleAttributeOperand.class, BinaryEncodingId, XmlEncodingId);
    }

}
