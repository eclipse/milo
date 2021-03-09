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
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class EventFilter extends MonitoringFilter implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=725");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=726");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=727");

    private final SimpleAttributeOperand[] selectClauses;

    private final ContentFilter whereClause;

    public EventFilter(SimpleAttributeOperand[] selectClauses, ContentFilter whereClause) {
        this.selectClauses = selectClauses;
        this.whereClause = whereClause;
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

    public SimpleAttributeOperand[] getSelectClauses() {
        return selectClauses;
    }

    public ContentFilter getWhereClause() {
        return whereClause;
    }

    public static final class Codec extends GenericDataTypeCodec<EventFilter> {
        @Override
        public Class<EventFilter> getType() {
            return EventFilter.class;
        }

        @Override
        public EventFilter decode(SerializationContext context, UaDecoder decoder) {
            SimpleAttributeOperand[] selectClauses = (SimpleAttributeOperand[]) decoder.readStructArray("SelectClauses", SimpleAttributeOperand.TYPE_ID);
            ContentFilter whereClause = (ContentFilter) decoder.readStruct("WhereClause", ContentFilter.TYPE_ID);
            return new EventFilter(selectClauses, whereClause);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, EventFilter value) {
            encoder.writeStructArray("SelectClauses", value.getSelectClauses(), SimpleAttributeOperand.TYPE_ID);
            encoder.writeStruct("WhereClause", value.getWhereClause(), ContentFilter.TYPE_ID);
        }
    }
}
