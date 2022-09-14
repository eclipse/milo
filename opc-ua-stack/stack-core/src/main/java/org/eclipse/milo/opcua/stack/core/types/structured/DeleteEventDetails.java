/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part11/6.8.7/#6.8.7.1">https://reference.opcfoundation.org/v104/Core/docs/Part11/6.8.7/#6.8.7.1</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class DeleteEventDetails extends HistoryUpdateDetails implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=692");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=694");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=693");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15285");

    private final ByteString[] eventIds;

    public DeleteEventDetails(NodeId nodeId, ByteString[] eventIds) {
        super(nodeId);
        this.eventIds = eventIds;
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

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public ByteString[] getEventIds() {
        return eventIds;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 694),
            new NodeId(0, 677),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("NodeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("EventIds", LocalizedText.NULL_VALUE, new NodeId(0, 15), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DeleteEventDetails> {
        @Override
        public Class<DeleteEventDetails> getType() {
            return DeleteEventDetails.class;
        }

        @Override
        public DeleteEventDetails decodeType(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.decodeNodeId("NodeId");
            ByteString[] eventIds = decoder.decodeByteStringArray("EventIds");
            return new DeleteEventDetails(nodeId, eventIds);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               DeleteEventDetails value) {
            encoder.encodeNodeId("NodeId", value.getNodeId());
            encoder.encodeByteStringArray("EventIds", value.getEventIds());
        }
    }
}
