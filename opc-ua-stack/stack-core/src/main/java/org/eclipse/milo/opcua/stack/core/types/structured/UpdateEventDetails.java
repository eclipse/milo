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
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;

@UaDataType("UpdateEventDetails")
public class UpdateEventDetails extends HistoryUpdateDetails {

    public static final NodeId TypeId = Identifiers.UpdateEventDetails;
    public static final NodeId BinaryEncodingId = Identifiers.UpdateEventDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.UpdateEventDetails_Encoding_DefaultXml;

    protected final PerformUpdateType _performInsertReplace;
    protected final EventFilter _filter;
    protected final HistoryEventFieldList[] _eventData;

    public UpdateEventDetails() {
        super(null);
        this._performInsertReplace = null;
        this._filter = null;
        this._eventData = null;
    }

    public UpdateEventDetails(NodeId _nodeId, PerformUpdateType _performInsertReplace, EventFilter _filter, HistoryEventFieldList[] _eventData) {
        super(_nodeId);
        this._performInsertReplace = _performInsertReplace;
        this._filter = _filter;
        this._eventData = _eventData;
    }

    public PerformUpdateType getPerformInsertReplace() { return _performInsertReplace; }

    public EventFilter getFilter() { return _filter; }

    @Nullable
    public HistoryEventFieldList[] getEventData() { return _eventData; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NodeId", _nodeId)
            .add("PerformInsertReplace", _performInsertReplace)
            .add("Filter", _filter)
            .add("EventData", _eventData)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<UpdateEventDetails> {
        @Override
        public UpdateEventDetails decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId();
            PerformUpdateType _performInsertReplace = PerformUpdateType.from(reader.readInt32());
            EventFilter _filter = (EventFilter) context.decode(EventFilter.BinaryEncodingId, reader);
            HistoryEventFieldList[] _eventData =
                reader.readArray(
                    () -> (HistoryEventFieldList) context.decode(
                        HistoryEventFieldList.BinaryEncodingId, reader),
                    HistoryEventFieldList.class
                );

            return new UpdateEventDetails(_nodeId, _performInsertReplace, _filter, _eventData);
        }

        @Override
        public void encode(SerializationContext context, UpdateEventDetails value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(value._nodeId);
            writer.writeInt32(value._performInsertReplace != null ? value._performInsertReplace.getValue() : 0);
            context.encode(EventFilter.BinaryEncodingId, value._filter, writer);
            writer.writeArray(
                value._eventData,
                e -> context.encode(HistoryEventFieldList.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<UpdateEventDetails> {
        @Override
        public UpdateEventDetails decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId("NodeId");
            PerformUpdateType _performInsertReplace = PerformUpdateType.from(reader.readInt32("PerformInsertReplace"));
            EventFilter _filter = (EventFilter) context.decode(EventFilter.XmlEncodingId, reader);
            HistoryEventFieldList[] _eventData =
                reader.readArray(
                    "EventData",
                    f -> (HistoryEventFieldList) context.decode(
                        HistoryEventFieldList.XmlEncodingId, reader),
                    HistoryEventFieldList.class
                );

            return new UpdateEventDetails(_nodeId, _performInsertReplace, _filter, _eventData);
        }

        @Override
        public void encode(SerializationContext context, UpdateEventDetails encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("NodeId", encodable._nodeId);
            writer.writeInt32("PerformInsertReplace", encodable._performInsertReplace != null ? encodable._performInsertReplace.getValue() : 0);
            context.encode(EventFilter.XmlEncodingId, encodable._filter, writer);
            writer.writeArray(
                "EventData",
                encodable._eventData,
                (f, e) -> context.encode(HistoryEventFieldList.XmlEncodingId, e, writer)
            );
        }
    }

}
