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


    public static void encode(AddNodesItem addNodesItem, UaEncoder encoder) {
        encoder.encodeExpandedNodeId("ParentNodeId", addNodesItem._parentNodeId);
        encoder.encodeNodeId("ReferenceTypeId", addNodesItem._referenceTypeId);
        encoder.encodeExpandedNodeId("RequestedNewNodeId", addNodesItem._requestedNewNodeId);
        encoder.encodeQualifiedName("BrowseName", addNodesItem._browseName);
        encoder.encodeEnumeration("NodeClass", addNodesItem._nodeClass);
        encoder.encodeExtensionObject("NodeAttributes", addNodesItem._nodeAttributes);
        encoder.encodeExpandedNodeId("TypeDefinition", addNodesItem._typeDefinition);
    }

    public static AddNodesItem decode(UaDecoder decoder) {
        ExpandedNodeId _parentNodeId = decoder.decodeExpandedNodeId("ParentNodeId");
        NodeId _referenceTypeId = decoder.decodeNodeId("ReferenceTypeId");
        ExpandedNodeId _requestedNewNodeId = decoder.decodeExpandedNodeId("RequestedNewNodeId");
        QualifiedName _browseName = decoder.decodeQualifiedName("BrowseName");
        NodeClass _nodeClass = decoder.decodeEnumeration("NodeClass", NodeClass.class);
        ExtensionObject _nodeAttributes = decoder.decodeExtensionObject("NodeAttributes");
        ExpandedNodeId _typeDefinition = decoder.decodeExpandedNodeId("TypeDefinition");

        return new AddNodesItem(_parentNodeId, _referenceTypeId, _requestedNewNodeId, _browseName, _nodeClass, _nodeAttributes, _typeDefinition);
    }

    static {
        DelegateRegistry.registerEncoder(AddNodesItem::encode, AddNodesItem.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(AddNodesItem::decode, AddNodesItem.class, BinaryEncodingId, XmlEncodingId);
    }

}
