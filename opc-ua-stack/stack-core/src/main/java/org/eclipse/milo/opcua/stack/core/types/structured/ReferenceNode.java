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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class ReferenceNode implements UaStructure {

    public static final NodeId TypeId = Identifiers.ReferenceNode;
    public static final NodeId BinaryEncodingId = Identifiers.ReferenceNode_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReferenceNode_Encoding_DefaultXml;

    protected final NodeId referenceTypeId;
    protected final Boolean isInverse;
    protected final ExpandedNodeId targetId;

    public ReferenceNode() {
        this.referenceTypeId = null;
        this.isInverse = null;
        this.targetId = null;
    }

    public ReferenceNode(NodeId referenceTypeId, Boolean isInverse, ExpandedNodeId targetId) {
        this.referenceTypeId = referenceTypeId;
        this.isInverse = isInverse;
        this.targetId = targetId;
    }

    public NodeId getReferenceTypeId() { return referenceTypeId; }

    public Boolean getIsInverse() { return isInverse; }

    public ExpandedNodeId getTargetId() { return targetId; }

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
            .add("TargetId", targetId)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ReferenceNode> {

        @Override
        public Class<ReferenceNode> getType() {
            return ReferenceNode.class;
        }

        @Override
        public ReferenceNode decode(UaDecoder decoder) throws UaSerializationException {
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            Boolean isInverse = decoder.readBoolean("IsInverse");
            ExpandedNodeId targetId = decoder.readExpandedNodeId("TargetId");

            return new ReferenceNode(referenceTypeId, isInverse, targetId);
        }

        @Override
        public void encode(ReferenceNode value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("ReferenceTypeId", value.referenceTypeId);
            encoder.writeBoolean("IsInverse", value.isInverse);
            encoder.writeExpandedNodeId("TargetId", value.targetId);
        }
    }

}
