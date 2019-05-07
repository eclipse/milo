/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class BrowsePath extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=543");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=545");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=544");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15190");

    private final NodeId startingNode;

    private final RelativePath relativePath;

    public BrowsePath(NodeId startingNode, RelativePath relativePath) {
        this.startingNode = startingNode;
        this.relativePath = relativePath;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public NodeId getStartingNode() {
        return startingNode;
    }

    public RelativePath getRelativePath() {
        return relativePath;
    }

    public static final class Codec extends GenericDataTypeCodec<BrowsePath> {
        @Override
        public Class<BrowsePath> getType() {
            return BrowsePath.class;
        }

        @Override
        public BrowsePath decode(SerializationContext context, UaDecoder decoder) {
            NodeId startingNode = decoder.readNodeId("StartingNode");
            RelativePath relativePath = (RelativePath) decoder.readStruct("RelativePath", RelativePath.TYPE_ID);
            return new BrowsePath(startingNode, relativePath);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, BrowsePath value) {
            encoder.writeNodeId("StartingNode", value.getStartingNode());
            encoder.writeStruct("RelativePath", value.getRelativePath(), RelativePath.TYPE_ID);
        }
    }
}
