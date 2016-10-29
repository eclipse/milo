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


    public static void encode(ReferenceDescription referenceDescription, UaEncoder encoder) {
        encoder.encodeNodeId("ReferenceTypeId", referenceDescription._referenceTypeId);
        encoder.encodeBoolean("IsForward", referenceDescription._isForward);
        encoder.encodeExpandedNodeId("NodeId", referenceDescription._nodeId);
        encoder.encodeQualifiedName("BrowseName", referenceDescription._browseName);
        encoder.encodeLocalizedText("DisplayName", referenceDescription._displayName);
        encoder.encodeEnumeration("NodeClass", referenceDescription._nodeClass);
        encoder.encodeExpandedNodeId("TypeDefinition", referenceDescription._typeDefinition);
    }

    public static ReferenceDescription decode(UaDecoder decoder) {
        NodeId _referenceTypeId = decoder.decodeNodeId("ReferenceTypeId");
        Boolean _isForward = decoder.decodeBoolean("IsForward");
        ExpandedNodeId _nodeId = decoder.decodeExpandedNodeId("NodeId");
        QualifiedName _browseName = decoder.decodeQualifiedName("BrowseName");
        LocalizedText _displayName = decoder.decodeLocalizedText("DisplayName");
        NodeClass _nodeClass = decoder.decodeEnumeration("NodeClass", NodeClass.class);
        ExpandedNodeId _typeDefinition = decoder.decodeExpandedNodeId("TypeDefinition");

        return new ReferenceDescription(_referenceTypeId, _isForward, _nodeId, _browseName, _displayName, _nodeClass, _typeDefinition);
    }

    static {
        DelegateRegistry.registerEncoder(ReferenceDescription::encode, ReferenceDescription.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ReferenceDescription::decode, ReferenceDescription.class, BinaryEncodingId, XmlEncodingId);
    }

}
