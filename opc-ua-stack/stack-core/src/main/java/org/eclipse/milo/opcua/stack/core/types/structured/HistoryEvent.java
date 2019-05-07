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

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class HistoryEvent extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=659");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=660");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=661");

    private final HistoryEventFieldList[] events;

    public HistoryEvent(HistoryEventFieldList[] events) {
        this.events = events;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    public HistoryEventFieldList[] getEvents() {
        return events;
    }

    public static final class Codec extends GenericDataTypeCodec<HistoryEvent> {
        @Override
        public Class<HistoryEvent> getType() {
            return HistoryEvent.class;
        }

        @Override
        public HistoryEvent decode(SerializationContext context, UaDecoder decoder) {
            HistoryEventFieldList[] events = (HistoryEventFieldList[]) decoder.readStructArray("Events", HistoryEventFieldList.TYPE_ID);
            return new HistoryEvent(events);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, HistoryEvent value) {
            encoder.writeStructArray("Events", value.getEvents(), HistoryEventFieldList.TYPE_ID);
        }
    }
}
