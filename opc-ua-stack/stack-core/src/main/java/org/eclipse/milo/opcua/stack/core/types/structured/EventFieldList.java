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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class EventFieldList extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=917");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=919");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=918");

    private final UInteger clientHandle;

    private final Variant[] eventFields;

    public EventFieldList(UInteger clientHandle, Variant[] eventFields) {
        this.clientHandle = clientHandle;
        this.eventFields = eventFields;
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

    public UInteger getClientHandle() {
        return clientHandle;
    }

    public Variant[] getEventFields() {
        return eventFields;
    }

    public static final class Codec extends GenericDataTypeCodec<EventFieldList> {
        @Override
        public Class<EventFieldList> getType() {
            return EventFieldList.class;
        }

        @Override
        public EventFieldList decode(SerializationContext context, UaDecoder decoder) {
            UInteger clientHandle = decoder.readUInt32("ClientHandle");
            Variant[] eventFields = decoder.readVariantArray("EventFields");
            return new EventFieldList(clientHandle, eventFields);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, EventFieldList value) {
            encoder.writeUInt32("ClientHandle", value.getClientHandle());
            encoder.writeVariantArray("EventFields", value.getEventFields());
        }
    }
}
