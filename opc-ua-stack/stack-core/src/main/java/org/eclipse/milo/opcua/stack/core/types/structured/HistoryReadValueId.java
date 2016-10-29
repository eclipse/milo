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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

@UaDataType("HistoryReadValueId")
public class HistoryReadValueId implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryReadValueId;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryReadValueId_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryReadValueId_Encoding_DefaultXml;

    protected final NodeId _nodeId;
    protected final String _indexRange;
    protected final QualifiedName _dataEncoding;
    protected final ByteString _continuationPoint;

    public HistoryReadValueId() {
        this._nodeId = null;
        this._indexRange = null;
        this._dataEncoding = null;
        this._continuationPoint = null;
    }

    public HistoryReadValueId(NodeId _nodeId, String _indexRange, QualifiedName _dataEncoding, ByteString _continuationPoint) {
        this._nodeId = _nodeId;
        this._indexRange = _indexRange;
        this._dataEncoding = _dataEncoding;
        this._continuationPoint = _continuationPoint;
    }

    public NodeId getNodeId() { return _nodeId; }

    public String getIndexRange() { return _indexRange; }

    public QualifiedName getDataEncoding() { return _dataEncoding; }

    public ByteString getContinuationPoint() { return _continuationPoint; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(HistoryReadValueId historyReadValueId, UaEncoder encoder) {
        encoder.encodeNodeId("NodeId", historyReadValueId._nodeId);
        encoder.encodeString("IndexRange", historyReadValueId._indexRange);
        encoder.encodeQualifiedName("DataEncoding", historyReadValueId._dataEncoding);
        encoder.encodeByteString("ContinuationPoint", historyReadValueId._continuationPoint);
    }

    public static HistoryReadValueId decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");
        String _indexRange = decoder.decodeString("IndexRange");
        QualifiedName _dataEncoding = decoder.decodeQualifiedName("DataEncoding");
        ByteString _continuationPoint = decoder.decodeByteString("ContinuationPoint");

        return new HistoryReadValueId(_nodeId, _indexRange, _dataEncoding, _continuationPoint);
    }

    static {
        DelegateRegistry.registerEncoder(HistoryReadValueId::encode, HistoryReadValueId.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(HistoryReadValueId::decode, HistoryReadValueId.class, BinaryEncodingId, XmlEncodingId);
    }

}
