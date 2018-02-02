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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public class RelativePathElement implements UaStructure {

    public static final NodeId TypeId = Identifiers.RelativePathElement;
    public static final NodeId BinaryEncodingId = Identifiers.RelativePathElement_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RelativePathElement_Encoding_DefaultXml;

    protected final NodeId referenceTypeId;
    protected final Boolean isInverse;
    protected final Boolean includeSubtypes;
    protected final QualifiedName targetName;

    public RelativePathElement() {
        this.referenceTypeId = null;
        this.isInverse = null;
        this.includeSubtypes = null;
        this.targetName = null;
    }

    public RelativePathElement(NodeId referenceTypeId, Boolean isInverse, Boolean includeSubtypes, QualifiedName targetName) {
        this.referenceTypeId = referenceTypeId;
        this.isInverse = isInverse;
        this.includeSubtypes = includeSubtypes;
        this.targetName = targetName;
    }

    public NodeId getReferenceTypeId() { return referenceTypeId; }

    public Boolean getIsInverse() { return isInverse; }

    public Boolean getIncludeSubtypes() { return includeSubtypes; }

    public QualifiedName getTargetName() { return targetName; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ReferenceTypeId", referenceTypeId)
            .add("IsInverse", isInverse)
            .add("IncludeSubtypes", includeSubtypes)
            .add("TargetName", targetName)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<RelativePathElement> {

        @Override
        public Class<RelativePathElement> getType() {
            return RelativePathElement.class;
        }

        @Override
        public RelativePathElement decode(UaDecoder decoder) throws UaSerializationException {
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            Boolean isInverse = decoder.readBoolean("IsInverse");
            Boolean includeSubtypes = decoder.readBoolean("IncludeSubtypes");
            QualifiedName targetName = decoder.readQualifiedName("TargetName");

            return new RelativePathElement(referenceTypeId, isInverse, includeSubtypes, targetName);
        }

        @Override
        public void encode(RelativePathElement value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("ReferenceTypeId", value.referenceTypeId);
            encoder.writeBoolean("IsInverse", value.isInverse);
            encoder.writeBoolean("IncludeSubtypes", value.includeSubtypes);
            encoder.writeQualifiedName("TargetName", value.targetName);
        }
    }

}
