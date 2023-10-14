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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part11/6.5.4">https://reference.opcfoundation.org/v104/Core/docs/Part11/6.5.4</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class HistoryEvent extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=659");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=661");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=660");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15273");

    private final HistoryEventFieldList @Nullable [] events;

    public HistoryEvent(HistoryEventFieldList @Nullable [] events) {
        this.events = events;
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

    public HistoryEventFieldList @Nullable [] getEvents() {
        return events;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 661),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Events", LocalizedText.NULL_VALUE, new NodeId(0, 920), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<HistoryEvent> {
        @Override
        public Class<HistoryEvent> getType() {
            return HistoryEvent.class;
        }

        @Override
        public HistoryEvent decodeType(EncodingContext context, UaDecoder decoder) {
            HistoryEventFieldList[] events = (HistoryEventFieldList[]) decoder.decodeStructArray("Events", HistoryEventFieldList.TYPE_ID);
            return new HistoryEvent(events);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder, HistoryEvent value) {
            encoder.encodeStructArray("Events", value.getEvents(), HistoryEventFieldList.TYPE_ID);
        }
    }
}
