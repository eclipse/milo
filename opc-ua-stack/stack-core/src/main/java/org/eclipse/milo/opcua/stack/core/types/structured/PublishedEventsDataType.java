/*
 * Copyright (c) 2021 the Eclipse Milo Authors
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
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class PublishedEventsDataType extends PublishedDataSetSourceDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15582");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15681");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15954");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16155");

    private final NodeId eventNotifier;

    private final SimpleAttributeOperand[] selectedFields;

    private final ContentFilter filter;

    public PublishedEventsDataType(NodeId eventNotifier, SimpleAttributeOperand[] selectedFields,
                                   ContentFilter filter) {
        this.eventNotifier = eventNotifier;
        this.selectedFields = selectedFields;
        this.filter = filter;
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

    public NodeId getEventNotifier() {
        return eventNotifier;
    }

    public SimpleAttributeOperand[] getSelectedFields() {
        return selectedFields;
    }

    public ContentFilter getFilter() {
        return filter;
    }

    public static final class Codec extends GenericDataTypeCodec<PublishedEventsDataType> {
        @Override
        public Class<PublishedEventsDataType> getType() {
            return PublishedEventsDataType.class;
        }

        @Override
        public PublishedEventsDataType decode(SerializationContext context, UaDecoder decoder) {
            NodeId eventNotifier = decoder.readNodeId("EventNotifier");
            SimpleAttributeOperand[] selectedFields = (SimpleAttributeOperand[]) decoder.readStructArray("SelectedFields", SimpleAttributeOperand.TYPE_ID);
            ContentFilter filter = (ContentFilter) decoder.readStruct("Filter", ContentFilter.TYPE_ID);
            return new PublishedEventsDataType(eventNotifier, selectedFields, filter);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           PublishedEventsDataType value) {
            encoder.writeNodeId("EventNotifier", value.getEventNotifier());
            encoder.writeStructArray("SelectedFields", value.getSelectedFields(), SimpleAttributeOperand.TYPE_ID);
            encoder.writeStruct("Filter", value.getFilter(), ContentFilter.TYPE_ID);
        }
    }
}
