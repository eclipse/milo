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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

@UaDataType("AddNodesItem")
public class AddNodesItem implements UaStructure {

    public static final NodeId TypeId = Identifiers.AddNodesItem;
    public static final NodeId BinaryEncodingId = Identifiers.AddNodesItem_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AddNodesItem_Encoding_DefaultXml;

    protected final ExpandedNodeId _parentNodeId;
    protected final NodeId _referenceTypeId;
    protected final ExpandedNodeId _requestedNewNodeId;
    protected final QualifiedName _browseName;
    protected final NodeClass _nodeClass;
    protected final ExtensionObject _nodeAttributes;
    protected final ExpandedNodeId _typeDefinition;

    public AddNodesItem() {
        this._parentNodeId = null;
        this._referenceTypeId = null;
        this._requestedNewNodeId = null;
        this._browseName = null;
        this._nodeClass = null;
        this._nodeAttributes = null;
        this._typeDefinition = null;
    }

    public AddNodesItem(ExpandedNodeId _parentNodeId, NodeId _referenceTypeId, ExpandedNodeId _requestedNewNodeId, QualifiedName _browseName, NodeClass _nodeClass, ExtensionObject _nodeAttributes, ExpandedNodeId _typeDefinition) {
        this._parentNodeId = _parentNodeId;
        this._referenceTypeId = _referenceTypeId;
        this._requestedNewNodeId = _requestedNewNodeId;
        this._browseName = _browseName;
        this._nodeClass = _nodeClass;
        this._nodeAttributes = _nodeAttributes;
        this._typeDefinition = _typeDefinition;
    }

    public ExpandedNodeId getParentNodeId() { return _parentNodeId; }

    public NodeId getReferenceTypeId() { return _referenceTypeId; }

    public ExpandedNodeId getRequestedNewNodeId() { return _requestedNewNodeId; }

    public QualifiedName getBrowseName() { return _browseName; }

    public NodeClass getNodeClass() { return _nodeClass; }

    public ExtensionObject getNodeAttributes() { return _nodeAttributes; }

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
            .add("ParentNodeId", _parentNodeId)
            .add("ReferenceTypeId", _referenceTypeId)
            .add("RequestedNewNodeId", _requestedNewNodeId)
            .add("BrowseName", _browseName)
            .add("NodeClass", _nodeClass)
            .add("NodeAttributes", _nodeAttributes)
            .add("TypeDefinition", _typeDefinition)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<AddNodesItem> {
        @Override
        public AddNodesItem decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ExpandedNodeId _parentNodeId = reader.readExpandedNodeId();
            NodeId _referenceTypeId = reader.readNodeId();
            ExpandedNodeId _requestedNewNodeId = reader.readExpandedNodeId();
            QualifiedName _browseName = reader.readQualifiedName();
            NodeClass _nodeClass = NodeClass.from(reader.readInt32());
            ExtensionObject _nodeAttributes = reader.readExtensionObject();
            ExpandedNodeId _typeDefinition = reader.readExpandedNodeId();

            return new AddNodesItem(_parentNodeId, _referenceTypeId, _requestedNewNodeId, _browseName, _nodeClass, _nodeAttributes, _typeDefinition);
        }

        @Override
        public void encode(SerializationContext context, AddNodesItem value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeExpandedNodeId(value._parentNodeId);
            writer.writeNodeId(value._referenceTypeId);
            writer.writeExpandedNodeId(value._requestedNewNodeId);
            writer.writeQualifiedName(value._browseName);
            writer.writeInt32(value._nodeClass != null ? value._nodeClass.getValue() : 0);
            writer.writeExtensionObject(value._nodeAttributes);
            writer.writeExpandedNodeId(value._typeDefinition);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<AddNodesItem> {
        @Override
        public AddNodesItem decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ExpandedNodeId _parentNodeId = reader.readExpandedNodeId("ParentNodeId");
            NodeId _referenceTypeId = reader.readNodeId("ReferenceTypeId");
            ExpandedNodeId _requestedNewNodeId = reader.readExpandedNodeId("RequestedNewNodeId");
            QualifiedName _browseName = reader.readQualifiedName("BrowseName");
            NodeClass _nodeClass = NodeClass.from(reader.readInt32("NodeClass"));
            ExtensionObject _nodeAttributes = reader.readExtensionObject("NodeAttributes");
            ExpandedNodeId _typeDefinition = reader.readExpandedNodeId("TypeDefinition");

            return new AddNodesItem(_parentNodeId, _referenceTypeId, _requestedNewNodeId, _browseName, _nodeClass, _nodeAttributes, _typeDefinition);
        }

        @Override
        public void encode(SerializationContext context, AddNodesItem encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeExpandedNodeId("ParentNodeId", encodable._parentNodeId);
            writer.writeNodeId("ReferenceTypeId", encodable._referenceTypeId);
            writer.writeExpandedNodeId("RequestedNewNodeId", encodable._requestedNewNodeId);
            writer.writeQualifiedName("BrowseName", encodable._browseName);
            writer.writeInt32("NodeClass", encodable._nodeClass != null ? encodable._nodeClass.getValue() : 0);
            writer.writeExtensionObject("NodeAttributes", encodable._nodeAttributes);
            writer.writeExpandedNodeId("TypeDefinition", encodable._typeDefinition);
        }
    }

}
