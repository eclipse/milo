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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;

public class UpdateEventDetails extends HistoryUpdateDetails {

    public static final NodeId TypeId = Identifiers.UpdateEventDetails;
    public static final NodeId BinaryEncodingId = Identifiers.UpdateEventDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.UpdateEventDetails_Encoding_DefaultXml;

    protected final PerformUpdateType performInsertReplace;
    protected final EventFilter filter;
    protected final HistoryEventFieldList[] eventData;

    public UpdateEventDetails() {
        super(null);
        this.performInsertReplace = null;
        this.filter = null;
        this.eventData = null;
    }

    public UpdateEventDetails(NodeId nodeId, PerformUpdateType performInsertReplace, EventFilter filter, HistoryEventFieldList[] eventData) {
        super(nodeId);
        this.performInsertReplace = performInsertReplace;
        this.filter = filter;
        this.eventData = eventData;
    }

    public PerformUpdateType getPerformInsertReplace() { return performInsertReplace; }

    public EventFilter getFilter() { return filter; }

    @Nullable
    public HistoryEventFieldList[] getEventData() { return eventData; }

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
            .add("PerformInsertReplace", performInsertReplace)
            .add("Filter", filter)
            .add("EventData", eventData)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<UpdateEventDetails> {

        @Override
        public Class<UpdateEventDetails> getType() {
            return UpdateEventDetails.class;
        }

        @Override
        public UpdateEventDetails decode(UaDecoder decoder) throws UaSerializationException {
            NodeId nodeId = decoder.readNodeId("NodeId");
            PerformUpdateType performInsertReplace = PerformUpdateType.from(decoder.readInt32("PerformInsertReplace"));
            EventFilter filter = (EventFilter) decoder.readBuiltinStruct("Filter", EventFilter.class);
            HistoryEventFieldList[] eventData =
                decoder.readBuiltinStructArray(
                    "EventData",
                    HistoryEventFieldList.class
                );

            return new UpdateEventDetails(nodeId, performInsertReplace, filter, eventData);
        }

        @Override
        public void encode(UpdateEventDetails value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("NodeId", value.nodeId);
            encoder.writeInt32("PerformInsertReplace", value.performInsertReplace != null ? value.performInsertReplace.getValue() : 0);
            encoder.writeBuiltinStruct("Filter", value.filter, EventFilter.class);
            encoder.writeBuiltinStructArray(
                "EventData",
                value.eventData,
                HistoryEventFieldList.class
            );
        }
    }

}
