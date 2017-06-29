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

public class EventFilter extends MonitoringFilter {

    public static final NodeId TypeId = Identifiers.EventFilter;
    public static final NodeId BinaryEncodingId = Identifiers.EventFilter_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EventFilter_Encoding_DefaultXml;

    protected final SimpleAttributeOperand[] selectClauses;
    protected final ContentFilter whereClause;

    public EventFilter() {
        super();
        this.selectClauses = null;
        this.whereClause = null;
    }

    public EventFilter(SimpleAttributeOperand[] selectClauses, ContentFilter whereClause) {
        super();
        this.selectClauses = selectClauses;
        this.whereClause = whereClause;
    }

    @Nullable
    public SimpleAttributeOperand[] getSelectClauses() { return selectClauses; }

    public ContentFilter getWhereClause() { return whereClause; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SelectClauses", selectClauses)
            .add("WhereClause", whereClause)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<EventFilter> {

        @Override
        public Class<EventFilter> getType() {
            return EventFilter.class;
        }

        @Override
        public EventFilter decode(UaDecoder decoder) throws UaSerializationException {
            SimpleAttributeOperand[] selectClauses =
                decoder.readBuiltinStructArray(
                    "SelectClauses",
                    SimpleAttributeOperand.class
                );
            ContentFilter whereClause = (ContentFilter) decoder.readBuiltinStruct("WhereClause", ContentFilter.class);

            return new EventFilter(selectClauses, whereClause);
        }

        @Override
        public void encode(EventFilter value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStructArray(
                "SelectClauses",
                value.selectClauses,
                SimpleAttributeOperand.class
            );
            encoder.writeBuiltinStruct("WhereClause", value.whereClause, ContentFilter.class);
        }
    }

}
