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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;

public class BrowseDescription implements UaStructure {

    public static final NodeId TypeId = Identifiers.BrowseDescription;
    public static final NodeId BinaryEncodingId = Identifiers.BrowseDescription_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowseDescription_Encoding_DefaultXml;

    protected final NodeId nodeId;
    protected final BrowseDirection browseDirection;
    protected final NodeId referenceTypeId;
    protected final Boolean includeSubtypes;
    protected final UInteger nodeClassMask;
    protected final UInteger resultMask;

    public BrowseDescription() {
        this.nodeId = null;
        this.browseDirection = null;
        this.referenceTypeId = null;
        this.includeSubtypes = null;
        this.nodeClassMask = null;
        this.resultMask = null;
    }

    public BrowseDescription(NodeId nodeId, BrowseDirection browseDirection, NodeId referenceTypeId, Boolean includeSubtypes, UInteger nodeClassMask, UInteger resultMask) {
        this.nodeId = nodeId;
        this.browseDirection = browseDirection;
        this.referenceTypeId = referenceTypeId;
        this.includeSubtypes = includeSubtypes;
        this.nodeClassMask = nodeClassMask;
        this.resultMask = resultMask;
    }

    public NodeId getNodeId() { return nodeId; }

    public BrowseDirection getBrowseDirection() { return browseDirection; }

    public NodeId getReferenceTypeId() { return referenceTypeId; }

    public Boolean getIncludeSubtypes() { return includeSubtypes; }

    public UInteger getNodeClassMask() { return nodeClassMask; }

    public UInteger getResultMask() { return resultMask; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NodeId", nodeId)
            .add("BrowseDirection", browseDirection)
            .add("ReferenceTypeId", referenceTypeId)
            .add("IncludeSubtypes", includeSubtypes)
            .add("NodeClassMask", nodeClassMask)
            .add("ResultMask", resultMask)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<BrowseDescription> {

        @Override
        public Class<BrowseDescription> getType() {
            return BrowseDescription.class;
        }

        @Override
        public BrowseDescription decode(UaDecoder decoder) throws UaSerializationException {
            NodeId nodeId = decoder.readNodeId("NodeId");
            BrowseDirection browseDirection = BrowseDirection.from(decoder.readInt32("BrowseDirection"));
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            Boolean includeSubtypes = decoder.readBoolean("IncludeSubtypes");
            UInteger nodeClassMask = decoder.readUInt32("NodeClassMask");
            UInteger resultMask = decoder.readUInt32("ResultMask");

            return new BrowseDescription(nodeId, browseDirection, referenceTypeId, includeSubtypes, nodeClassMask, resultMask);
        }

        @Override
        public void encode(BrowseDescription value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("NodeId", value.nodeId);
            encoder.writeInt32("BrowseDirection", value.browseDirection != null ? value.browseDirection.getValue() : 0);
            encoder.writeNodeId("ReferenceTypeId", value.referenceTypeId);
            encoder.writeBoolean("IncludeSubtypes", value.includeSubtypes);
            encoder.writeUInt32("NodeClassMask", value.nodeClassMask);
            encoder.writeUInt32("ResultMask", value.resultMask);
        }
    }

}
