/*
 * Copyright (c) 2023 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part11/6.8.6/#6.8.6.1">https://reference.opcfoundation.org/v104/Core/docs/Part11/6.8.6/#6.8.6.1</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class DeleteAtTimeDetails extends HistoryUpdateDetails implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=689");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=691");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=690");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15284");

    private final DateTime @Nullable [] reqTimes;

    public DeleteAtTimeDetails(NodeId nodeId, DateTime @Nullable [] reqTimes) {
        super(nodeId);
        this.reqTimes = reqTimes;
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

    public DateTime @Nullable [] getReqTimes() {
        return reqTimes;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 691),
            new NodeId(0, 677),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("NodeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("ReqTimes", LocalizedText.NULL_VALUE, new NodeId(0, 294), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DeleteAtTimeDetails> {
        @Override
        public Class<DeleteAtTimeDetails> getType() {
            return DeleteAtTimeDetails.class;
        }

        @Override
        public DeleteAtTimeDetails decodeType(EncodingContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.decodeNodeId("NodeId");
            DateTime[] reqTimes = decoder.decodeDateTimeArray("ReqTimes");
            return new DeleteAtTimeDetails(nodeId, reqTimes);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder, DeleteAtTimeDetails value) {
            encoder.encodeNodeId("NodeId", value.getNodeId());
            encoder.encodeDateTimeArray("ReqTimes", value.getReqTimes());
        }
    }
}
