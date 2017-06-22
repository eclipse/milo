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
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

@UaDataType("AddReferencesItem")
public class AddReferencesItem implements UaStructure {

    public static final NodeId TypeId = Identifiers.AddReferencesItem;
    public static final NodeId BinaryEncodingId = Identifiers.AddReferencesItem_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AddReferencesItem_Encoding_DefaultXml;

    protected final NodeId _sourceNodeId;
    protected final NodeId _referenceTypeId;
    protected final Boolean _isForward;
    protected final String _targetServerUri;
    protected final ExpandedNodeId _targetNodeId;
    protected final NodeClass _targetNodeClass;

    public AddReferencesItem() {
        this._sourceNodeId = null;
        this._referenceTypeId = null;
        this._isForward = null;
        this._targetServerUri = null;
        this._targetNodeId = null;
        this._targetNodeClass = null;
    }

    public AddReferencesItem(NodeId _sourceNodeId, NodeId _referenceTypeId, Boolean _isForward, String _targetServerUri, ExpandedNodeId _targetNodeId, NodeClass _targetNodeClass) {
        this._sourceNodeId = _sourceNodeId;
        this._referenceTypeId = _referenceTypeId;
        this._isForward = _isForward;
        this._targetServerUri = _targetServerUri;
        this._targetNodeId = _targetNodeId;
        this._targetNodeClass = _targetNodeClass;
    }

    public NodeId getSourceNodeId() { return _sourceNodeId; }

    public NodeId getReferenceTypeId() { return _referenceTypeId; }

    public Boolean getIsForward() { return _isForward; }

    public String getTargetServerUri() { return _targetServerUri; }

    public ExpandedNodeId getTargetNodeId() { return _targetNodeId; }

    public NodeClass getTargetNodeClass() { return _targetNodeClass; }

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
            .add("TargetServerUri", _targetServerUri)
            .add("TargetNodeId", _targetNodeId)
            .add("TargetNodeClass", _targetNodeClass)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<AddReferencesItem> {
        @Override
        public AddReferencesItem decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _sourceNodeId = reader.readNodeId();
            NodeId _referenceTypeId = reader.readNodeId();
            Boolean _isForward = reader.readBoolean();
            String _targetServerUri = reader.readString();
            ExpandedNodeId _targetNodeId = reader.readExpandedNodeId();
            NodeClass _targetNodeClass = NodeClass.from(reader.readInt32());

            return new AddReferencesItem(_sourceNodeId, _referenceTypeId, _isForward, _targetServerUri, _targetNodeId, _targetNodeClass);
        }

        @Override
        public void encode(SerializationContext context, AddReferencesItem value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(value._sourceNodeId);
            writer.writeNodeId(value._referenceTypeId);
            writer.writeBoolean(value._isForward);
            writer.writeString(value._targetServerUri);
            writer.writeExpandedNodeId(value._targetNodeId);
            writer.writeInt32(value._targetNodeClass != null ? value._targetNodeClass.getValue() : 0);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<AddReferencesItem> {
        @Override
        public AddReferencesItem decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _sourceNodeId = reader.readNodeId("SourceNodeId");
            NodeId _referenceTypeId = reader.readNodeId("ReferenceTypeId");
            Boolean _isForward = reader.readBoolean("IsForward");
            String _targetServerUri = reader.readString("TargetServerUri");
            ExpandedNodeId _targetNodeId = reader.readExpandedNodeId("TargetNodeId");
            NodeClass _targetNodeClass = NodeClass.from(reader.readInt32("TargetNodeClass"));

            return new AddReferencesItem(_sourceNodeId, _referenceTypeId, _isForward, _targetServerUri, _targetNodeId, _targetNodeClass);
        }

        @Override
        public void encode(SerializationContext context, AddReferencesItem encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("SourceNodeId", encodable._sourceNodeId);
            writer.writeNodeId("ReferenceTypeId", encodable._referenceTypeId);
            writer.writeBoolean("IsForward", encodable._isForward);
            writer.writeString("TargetServerUri", encodable._targetServerUri);
            writer.writeExpandedNodeId("TargetNodeId", encodable._targetNodeId);
            writer.writeInt32("TargetNodeClass", encodable._targetNodeClass != null ? encodable._targetNodeClass.getValue() : 0);
        }
    }

}
