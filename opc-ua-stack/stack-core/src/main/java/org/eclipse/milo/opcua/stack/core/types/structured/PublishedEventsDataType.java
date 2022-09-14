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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.3/#6.2.3.8.4">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.3/#6.2.3.8.4</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class PublishedEventsDataType extends PublishedDataSetSourceDataType implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15582");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15681");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15954");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16155");

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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15681),
            new NodeId(0, 15580),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("EventNotifier", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("SelectedFields", LocalizedText.NULL_VALUE, new NodeId(0, 601), 1, null, UInteger.valueOf(0), false),
                new StructureField("Filter", LocalizedText.NULL_VALUE, new NodeId(0, 586), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<PublishedEventsDataType> {
        @Override
        public Class<PublishedEventsDataType> getType() {
            return PublishedEventsDataType.class;
        }

        @Override
        public PublishedEventsDataType decodeType(SerializationContext context, UaDecoder decoder) {
            NodeId eventNotifier = decoder.readNodeId("EventNotifier");
            SimpleAttributeOperand[] selectedFields = (SimpleAttributeOperand[]) decoder.readStructArray("SelectedFields", SimpleAttributeOperand.TYPE_ID);
            ContentFilter filter = (ContentFilter) decoder.readStruct("Filter", ContentFilter.TYPE_ID);
            return new PublishedEventsDataType(eventNotifier, selectedFields, filter);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               PublishedEventsDataType value) {
            encoder.writeNodeId("EventNotifier", value.getEventNotifier());
            encoder.writeStructArray("SelectedFields", value.getSelectedFields(), SimpleAttributeOperand.TYPE_ID);
            encoder.writeStruct("Filter", value.getFilter(), ContentFilter.TYPE_ID);
        }
    }
}
