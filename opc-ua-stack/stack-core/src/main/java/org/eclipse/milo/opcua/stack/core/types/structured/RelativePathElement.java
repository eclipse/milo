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
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

@UaDataType("RelativePathElement")
public class RelativePathElement implements UaStructure {

    public static final NodeId TypeId = Identifiers.RelativePathElement;
    public static final NodeId BinaryEncodingId = Identifiers.RelativePathElement_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RelativePathElement_Encoding_DefaultXml;

    protected final NodeId _referenceTypeId;
    protected final Boolean _isInverse;
    protected final Boolean _includeSubtypes;
    protected final QualifiedName _targetName;

    public RelativePathElement() {
        this._referenceTypeId = null;
        this._isInverse = null;
        this._includeSubtypes = null;
        this._targetName = null;
    }

    public RelativePathElement(NodeId _referenceTypeId, Boolean _isInverse, Boolean _includeSubtypes, QualifiedName _targetName) {
        this._referenceTypeId = _referenceTypeId;
        this._isInverse = _isInverse;
        this._includeSubtypes = _includeSubtypes;
        this._targetName = _targetName;
    }

    public NodeId getReferenceTypeId() { return _referenceTypeId; }

    public Boolean getIsInverse() { return _isInverse; }

    public Boolean getIncludeSubtypes() { return _includeSubtypes; }

    public QualifiedName getTargetName() { return _targetName; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(RelativePathElement relativePathElement, UaEncoder encoder) {
        encoder.encodeNodeId("ReferenceTypeId", relativePathElement._referenceTypeId);
        encoder.encodeBoolean("IsInverse", relativePathElement._isInverse);
        encoder.encodeBoolean("IncludeSubtypes", relativePathElement._includeSubtypes);
        encoder.encodeQualifiedName("TargetName", relativePathElement._targetName);
    }

    public static RelativePathElement decode(UaDecoder decoder) {
        NodeId _referenceTypeId = decoder.decodeNodeId("ReferenceTypeId");
        Boolean _isInverse = decoder.decodeBoolean("IsInverse");
        Boolean _includeSubtypes = decoder.decodeBoolean("IncludeSubtypes");
        QualifiedName _targetName = decoder.decodeQualifiedName("TargetName");

        return new RelativePathElement(_referenceTypeId, _isInverse, _includeSubtypes, _targetName);
    }

    static {
        DelegateRegistry.registerEncoder(RelativePathElement::encode, RelativePathElement.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(RelativePathElement::decode, RelativePathElement.class, BinaryEncodingId, XmlEncodingId);
    }

}
