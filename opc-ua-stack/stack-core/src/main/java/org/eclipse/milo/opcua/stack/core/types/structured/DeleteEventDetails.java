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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class DeleteEventDetails extends HistoryUpdateDetails {

    public static final NodeId TypeId = Identifiers.DeleteEventDetails;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteEventDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteEventDetails_Encoding_DefaultXml;

    protected final ByteString[] eventIds;

    public DeleteEventDetails() {
        super(null);
        this.eventIds = null;
    }

    public DeleteEventDetails(NodeId nodeId, ByteString[] eventIds) {
        super(nodeId);
        this.eventIds = eventIds;
    }

    @Nullable
    public ByteString[] getEventIds() { return eventIds; }

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
            .add("EventIds", eventIds)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<DeleteEventDetails> {

        @Override
        public Class<DeleteEventDetails> getType() {
            return DeleteEventDetails.class;
        }

        @Override
        public DeleteEventDetails decode(UaDecoder decoder) throws UaSerializationException {
            NodeId nodeId = decoder.readNodeId("NodeId");
            ByteString[] eventIds = decoder.readArray("EventIds", decoder::readByteString, ByteString.class);

            return new DeleteEventDetails(nodeId, eventIds);
        }

        @Override
        public void encode(DeleteEventDetails value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("NodeId", value.nodeId);
            encoder.writeArray("EventIds", value.eventIds, encoder::writeByteString);
        }
    }

}
