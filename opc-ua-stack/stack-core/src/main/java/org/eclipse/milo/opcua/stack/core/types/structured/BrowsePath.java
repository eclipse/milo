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

public class BrowsePath implements UaStructure {

    public static final NodeId TypeId = Identifiers.BrowsePath;
    public static final NodeId BinaryEncodingId = Identifiers.BrowsePath_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowsePath_Encoding_DefaultXml;

    protected final NodeId startingNode;
    protected final RelativePath relativePath;

    public BrowsePath() {
        this.startingNode = null;
        this.relativePath = null;
    }

    public BrowsePath(NodeId startingNode, RelativePath relativePath) {
        this.startingNode = startingNode;
        this.relativePath = relativePath;
    }

    public NodeId getStartingNode() { return startingNode; }

    public RelativePath getRelativePath() { return relativePath; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StartingNode", startingNode)
            .add("RelativePath", relativePath)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<BrowsePath> {

        @Override
        public Class<BrowsePath> getType() {
            return BrowsePath.class;
        }

        @Override
        public BrowsePath decode(UaDecoder decoder) throws UaSerializationException {
            NodeId startingNode = decoder.readNodeId("StartingNode");
            RelativePath relativePath = (RelativePath) decoder.readBuiltinStruct("RelativePath", RelativePath.class);

            return new BrowsePath(startingNode, relativePath);
        }

        @Override
        public void encode(BrowsePath value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("StartingNode", value.startingNode);
            encoder.writeBuiltinStruct("RelativePath", value.relativePath, RelativePath.class);
        }
    }

}
