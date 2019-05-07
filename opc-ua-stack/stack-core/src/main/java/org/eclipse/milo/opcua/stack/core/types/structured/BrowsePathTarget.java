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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class BrowsePathTarget extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=546");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=548");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=547");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15191");

    private final ExpandedNodeId targetId;

    private final UInteger remainingPathIndex;

    public BrowsePathTarget(ExpandedNodeId targetId, UInteger remainingPathIndex) {
        this.targetId = targetId;
        this.remainingPathIndex = remainingPathIndex;
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

    public ExpandedNodeId getTargetId() {
        return targetId;
    }

    public UInteger getRemainingPathIndex() {
        return remainingPathIndex;
    }

    public static final class Codec extends GenericDataTypeCodec<BrowsePathTarget> {
        @Override
        public Class<BrowsePathTarget> getType() {
            return BrowsePathTarget.class;
        }

        @Override
        public BrowsePathTarget decode(SerializationContext context, UaDecoder decoder) {
            ExpandedNodeId targetId = decoder.readExpandedNodeId("TargetId");
            UInteger remainingPathIndex = decoder.readUInt32("RemainingPathIndex");
            return new BrowsePathTarget(targetId, remainingPathIndex);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, BrowsePathTarget value) {
            encoder.writeExpandedNodeId("TargetId", value.getTargetId());
            encoder.writeUInt32("RemainingPathIndex", value.getRemainingPathIndex());
        }
    }
}
