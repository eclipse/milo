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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

@UaDataType("ReferenceDescription")
public class ReferenceDescription implements UaStructure {

    public static final NodeId TypeId = Identifiers.ReferenceDescription;
    public static final NodeId BinaryEncodingId = Identifiers.ReferenceDescription_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReferenceDescription_Encoding_DefaultXml;

    protected final NodeId _referenceTypeId;
    protected final Boolean _isForward;
    protected final ExpandedNodeId _nodeId;
    protected final QualifiedName _browseName;
    protected final LocalizedText _displayName;
    protected final NodeClass _nodeClass;
    protected final ExpandedNodeId _typeDefinition;

    public ReferenceDescription() {
        this._referenceTypeId = null;
        this._isForward = null;
        this._nodeId = null;
        this._browseName = null;
        this._displayName = null;
        this._nodeClass = null;
        this._typeDefinition = null;
    }

    public ReferenceDescription(NodeId _referenceTypeId, Boolean _isForward, ExpandedNodeId _nodeId, QualifiedName _browseName, LocalizedText _displayName, NodeClass _nodeClass, ExpandedNodeId _typeDefinition) {
        this._referenceTypeId = _referenceTypeId;
        this._isForward = _isForward;
        this._nodeId = _nodeId;
        this._browseName = _browseName;
        this._displayName = _displayName;
        this._nodeClass = _nodeClass;
        this._typeDefinition = _typeDefinition;
    }

    public NodeId getReferenceTypeId() { return _referenceTypeId; }

    public Boolean getIsForward() { return _isForward; }

    public ExpandedNodeId getNodeId() { return _nodeId; }

    public QualifiedName getBrowseName() { return _browseName; }

    public LocalizedText getDisplayName() { return _displayName; }

    public NodeClass getNodeClass() { return _nodeClass; }

    public ExpandedNodeId getTypeDefinition() { return _typeDefinition; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ReferenceTypeId", _referenceTypeId)
            .add("IsForward", _isForward)
            .add("NodeId", _nodeId)
            .add("BrowseName", _browseName)
            .add("DisplayName", _displayName)
            .add("NodeClass", _nodeClass)
            .add("TypeDefinition", _typeDefinition)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ReferenceDescription> {
        @Override
        public ReferenceDescription decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _referenceTypeId = reader.readNodeId();
            Boolean _isForward = reader.readBoolean();
            ExpandedNodeId _nodeId = reader.readExpandedNodeId();
            QualifiedName _browseName = reader.readQualifiedName();
            LocalizedText _displayName = reader.readLocalizedText();
            NodeClass _nodeClass = NodeClass.from(reader.readInt32());
            ExpandedNodeId _typeDefinition = reader.readExpandedNodeId();

            return new ReferenceDescription(_referenceTypeId, _isForward, _nodeId, _browseName, _displayName, _nodeClass, _typeDefinition);
        }

        @Override
        public void encode(SerializationContext context, ReferenceDescription encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(encodable._referenceTypeId);
            writer.writeBoolean(encodable._isForward);
            writer.writeExpandedNodeId(encodable._nodeId);
            writer.writeQualifiedName(encodable._browseName);
            writer.writeLocalizedText(encodable._displayName);
            writer.writeInt32(encodable._nodeClass != null ? encodable._nodeClass.getValue() : 0);
            writer.writeExpandedNodeId(encodable._typeDefinition);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ReferenceDescription> {
        @Override
        public ReferenceDescription decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _referenceTypeId = reader.readNodeId("ReferenceTypeId");
            Boolean _isForward = reader.readBoolean("IsForward");
            ExpandedNodeId _nodeId = reader.readExpandedNodeId("NodeId");
            QualifiedName _browseName = reader.readQualifiedName("BrowseName");
            LocalizedText _displayName = reader.readLocalizedText("DisplayName");
            NodeClass _nodeClass = NodeClass.from(reader.readInt32("NodeClass"));
            ExpandedNodeId _typeDefinition = reader.readExpandedNodeId("TypeDefinition");

            return new ReferenceDescription(_referenceTypeId, _isForward, _nodeId, _browseName, _displayName, _nodeClass, _typeDefinition);
        }

        @Override
        public void encode(SerializationContext context, ReferenceDescription encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("ReferenceTypeId", encodable._referenceTypeId);
            writer.writeBoolean("IsForward", encodable._isForward);
            writer.writeExpandedNodeId("NodeId", encodable._nodeId);
            writer.writeQualifiedName("BrowseName", encodable._browseName);
            writer.writeLocalizedText("DisplayName", encodable._displayName);
            writer.writeInt32("NodeClass", encodable._nodeClass != null ? encodable._nodeClass.getValue() : 0);
            writer.writeExpandedNodeId("TypeDefinition", encodable._typeDefinition);
        }
    }

}
