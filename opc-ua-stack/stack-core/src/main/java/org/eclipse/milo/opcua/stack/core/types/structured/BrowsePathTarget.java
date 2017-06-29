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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class BrowsePathTarget implements UaStructure {

    public static final NodeId TypeId = Identifiers.BrowsePathTarget;
    public static final NodeId BinaryEncodingId = Identifiers.BrowsePathTarget_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowsePathTarget_Encoding_DefaultXml;

    protected final ExpandedNodeId targetId;
    protected final UInteger remainingPathIndex;

    public BrowsePathTarget() {
        this.targetId = null;
        this.remainingPathIndex = null;
    }

    public BrowsePathTarget(ExpandedNodeId targetId, UInteger remainingPathIndex) {
        this.targetId = targetId;
        this.remainingPathIndex = remainingPathIndex;
    }

    public ExpandedNodeId getTargetId() { return targetId; }

    public UInteger getRemainingPathIndex() { return remainingPathIndex; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("TargetId", targetId)
            .add("RemainingPathIndex", remainingPathIndex)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<BrowsePathTarget> {

        @Override
        public Class<BrowsePathTarget> getType() {
            return BrowsePathTarget.class;
        }

        @Override
        public BrowsePathTarget decode(UaDecoder decoder) throws UaSerializationException {
            ExpandedNodeId targetId = decoder.readExpandedNodeId("TargetId");
            UInteger remainingPathIndex = decoder.readUInt32("RemainingPathIndex");

            return new BrowsePathTarget(targetId, remainingPathIndex);
        }

        @Override
        public void encode(BrowsePathTarget value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeExpandedNodeId("TargetId", value.targetId);
            encoder.writeUInt32("RemainingPathIndex", value.remainingPathIndex);
        }
    }

}
