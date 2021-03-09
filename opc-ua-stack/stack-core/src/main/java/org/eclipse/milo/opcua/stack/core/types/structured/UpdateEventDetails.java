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
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class UpdateEventDetails extends HistoryUpdateDetails implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=683");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=685");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=684");

    private final PerformUpdateType performInsertReplace;

    private final EventFilter filter;

    private final HistoryEventFieldList[] eventData;

    public UpdateEventDetails(NodeId nodeId, PerformUpdateType performInsertReplace,
                              EventFilter filter, HistoryEventFieldList[] eventData) {
        super(nodeId);
        this.performInsertReplace = performInsertReplace;
        this.filter = filter;
        this.eventData = eventData;
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

    public PerformUpdateType getPerformInsertReplace() {
        return performInsertReplace;
    }

    public EventFilter getFilter() {
        return filter;
    }

    public HistoryEventFieldList[] getEventData() {
        return eventData;
    }

    public static final class Codec extends GenericDataTypeCodec<UpdateEventDetails> {
        @Override
        public Class<UpdateEventDetails> getType() {
            return UpdateEventDetails.class;
        }

        @Override
        public UpdateEventDetails decode(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            PerformUpdateType performInsertReplace = decoder.readEnum("PerformInsertReplace", PerformUpdateType.class);
            EventFilter filter = (EventFilter) decoder.readStruct("Filter", EventFilter.TYPE_ID);
            HistoryEventFieldList[] eventData = (HistoryEventFieldList[]) decoder.readStructArray("EventData", HistoryEventFieldList.TYPE_ID);
            return new UpdateEventDetails(nodeId, performInsertReplace, filter, eventData);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, UpdateEventDetails value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeEnum("PerformInsertReplace", value.getPerformInsertReplace());
            encoder.writeStruct("Filter", value.getFilter(), EventFilter.TYPE_ID);
            encoder.writeStructArray("EventData", value.getEventData(), HistoryEventFieldList.TYPE_ID);
        }
    }
}
