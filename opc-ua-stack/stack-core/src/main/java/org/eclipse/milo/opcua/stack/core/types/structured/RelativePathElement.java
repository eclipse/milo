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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ReferenceTypeId", _referenceTypeId)
            .add("IsInverse", _isInverse)
            .add("IncludeSubtypes", _includeSubtypes)
            .add("TargetName", _targetName)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<RelativePathElement> {
        @Override
        public RelativePathElement decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _referenceTypeId = reader.readNodeId();
            Boolean _isInverse = reader.readBoolean();
            Boolean _includeSubtypes = reader.readBoolean();
            QualifiedName _targetName = reader.readQualifiedName();

            return new RelativePathElement(_referenceTypeId, _isInverse, _includeSubtypes, _targetName);
        }

        @Override
        public void encode(SerializationContext context, RelativePathElement encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(encodable._referenceTypeId);
            writer.writeBoolean(encodable._isInverse);
            writer.writeBoolean(encodable._includeSubtypes);
            writer.writeQualifiedName(encodable._targetName);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<RelativePathElement> {
        @Override
        public RelativePathElement decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _referenceTypeId = reader.readNodeId("ReferenceTypeId");
            Boolean _isInverse = reader.readBoolean("IsInverse");
            Boolean _includeSubtypes = reader.readBoolean("IncludeSubtypes");
            QualifiedName _targetName = reader.readQualifiedName("TargetName");

            return new RelativePathElement(_referenceTypeId, _isInverse, _includeSubtypes, _targetName);
        }

        @Override
        public void encode(SerializationContext context, RelativePathElement encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("ReferenceTypeId", encodable._referenceTypeId);
            writer.writeBoolean("IsInverse", encodable._isInverse);
            writer.writeBoolean("IncludeSubtypes", encodable._includeSubtypes);
            writer.writeQualifiedName("TargetName", encodable._targetName);
        }
    }

}
