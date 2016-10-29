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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("AttributeOperand")
public class AttributeOperand extends FilterOperand {

    public static final NodeId TypeId = Identifiers.AttributeOperand;
    public static final NodeId BinaryEncodingId = Identifiers.AttributeOperand_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AttributeOperand_Encoding_DefaultXml;

    protected final NodeId _nodeId;
    protected final String _alias;
    protected final RelativePath _browsePath;
    protected final UInteger _attributeId;
    protected final String _indexRange;

    public AttributeOperand() {
        super();
        this._nodeId = null;
        this._alias = null;
        this._browsePath = null;
        this._attributeId = null;
        this._indexRange = null;
    }

    public AttributeOperand(NodeId _nodeId, String _alias, RelativePath _browsePath, UInteger _attributeId, String _indexRange) {
        super();
        this._nodeId = _nodeId;
        this._alias = _alias;
        this._browsePath = _browsePath;
        this._attributeId = _attributeId;
        this._indexRange = _indexRange;
    }

    public NodeId getNodeId() { return _nodeId; }

    public String getAlias() { return _alias; }

    public RelativePath getBrowsePath() { return _browsePath; }

    public UInteger getAttributeId() { return _attributeId; }

    public String getIndexRange() { return _indexRange; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(AttributeOperand attributeOperand, UaEncoder encoder) {
        encoder.encodeNodeId("NodeId", attributeOperand._nodeId);
        encoder.encodeString("Alias", attributeOperand._alias);
        encoder.encodeSerializable("BrowsePath", attributeOperand._browsePath != null ? attributeOperand._browsePath : new RelativePath());
        encoder.encodeUInt32("AttributeId", attributeOperand._attributeId);
        encoder.encodeString("IndexRange", attributeOperand._indexRange);
    }

    public static AttributeOperand decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");
        String _alias = decoder.decodeString("Alias");
        RelativePath _browsePath = decoder.decodeSerializable("BrowsePath", RelativePath.class);
        UInteger _attributeId = decoder.decodeUInt32("AttributeId");
        String _indexRange = decoder.decodeString("IndexRange");

        return new AttributeOperand(_nodeId, _alias, _browsePath, _attributeId, _indexRange);
    }

    static {
        DelegateRegistry.registerEncoder(AttributeOperand::encode, AttributeOperand.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(AttributeOperand::decode, AttributeOperand.class, BinaryEncodingId, XmlEncodingId);
    }

}
