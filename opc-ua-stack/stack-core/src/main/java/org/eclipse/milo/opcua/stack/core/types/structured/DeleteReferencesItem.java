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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("DeleteReferencesItem")
public class DeleteReferencesItem implements UaStructure {

    public static final NodeId TypeId = Identifiers.DeleteReferencesItem;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteReferencesItem_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteReferencesItem_Encoding_DefaultXml;

    protected final NodeId _sourceNodeId;
    protected final NodeId _referenceTypeId;
    protected final Boolean _isForward;
    protected final ExpandedNodeId _targetNodeId;
    protected final Boolean _deleteBidirectional;

    public DeleteReferencesItem() {
        this._sourceNodeId = null;
        this._referenceTypeId = null;
        this._isForward = null;
        this._targetNodeId = null;
        this._deleteBidirectional = null;
    }

    public DeleteReferencesItem(NodeId _sourceNodeId, NodeId _referenceTypeId, Boolean _isForward, ExpandedNodeId _targetNodeId, Boolean _deleteBidirectional) {
        this._sourceNodeId = _sourceNodeId;
        this._referenceTypeId = _referenceTypeId;
        this._isForward = _isForward;
        this._targetNodeId = _targetNodeId;
        this._deleteBidirectional = _deleteBidirectional;
    }

    public NodeId getSourceNodeId() { return _sourceNodeId; }

    public NodeId getReferenceTypeId() { return _referenceTypeId; }

    public Boolean getIsForward() { return _isForward; }

    public ExpandedNodeId getTargetNodeId() { return _targetNodeId; }

    public Boolean getDeleteBidirectional() { return _deleteBidirectional; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SourceNodeId", _sourceNodeId)
            .add("ReferenceTypeId", _referenceTypeId)
            .add("IsForward", _isForward)
            .add("TargetNodeId", _targetNodeId)
            .add("DeleteBidirectional", _deleteBidirectional)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<DeleteReferencesItem> {
        @Override
        public DeleteReferencesItem decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _sourceNodeId = reader.readNodeId();
            NodeId _referenceTypeId = reader.readNodeId();
            Boolean _isForward = reader.readBoolean();
            ExpandedNodeId _targetNodeId = reader.readExpandedNodeId();
            Boolean _deleteBidirectional = reader.readBoolean();

            return new DeleteReferencesItem(_sourceNodeId, _referenceTypeId, _isForward, _targetNodeId, _deleteBidirectional);
        }

        @Override
        public void encode(SerializationContext context, DeleteReferencesItem encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(encodable._sourceNodeId);
            writer.writeNodeId(encodable._referenceTypeId);
            writer.writeBoolean(encodable._isForward);
            writer.writeExpandedNodeId(encodable._targetNodeId);
            writer.writeBoolean(encodable._deleteBidirectional);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<DeleteReferencesItem> {
        @Override
        public DeleteReferencesItem decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _sourceNodeId = reader.readNodeId("SourceNodeId");
            NodeId _referenceTypeId = reader.readNodeId("ReferenceTypeId");
            Boolean _isForward = reader.readBoolean("IsForward");
            ExpandedNodeId _targetNodeId = reader.readExpandedNodeId("TargetNodeId");
            Boolean _deleteBidirectional = reader.readBoolean("DeleteBidirectional");

            return new DeleteReferencesItem(_sourceNodeId, _referenceTypeId, _isForward, _targetNodeId, _deleteBidirectional);
        }

        @Override
        public void encode(SerializationContext context, DeleteReferencesItem encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("SourceNodeId", encodable._sourceNodeId);
            writer.writeNodeId("ReferenceTypeId", encodable._referenceTypeId);
            writer.writeBoolean("IsForward", encodable._isForward);
            writer.writeExpandedNodeId("TargetNodeId", encodable._targetNodeId);
            writer.writeBoolean("DeleteBidirectional", encodable._deleteBidirectional);
        }
    }

}
