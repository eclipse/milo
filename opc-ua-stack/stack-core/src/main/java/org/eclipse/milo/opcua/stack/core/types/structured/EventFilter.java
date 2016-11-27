/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("EventFilter")
public class EventFilter extends MonitoringFilter {

    public static final NodeId TypeId = Identifiers.EventFilter;
    public static final NodeId BinaryEncodingId = Identifiers.EventFilter_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EventFilter_Encoding_DefaultXml;

    protected final SimpleAttributeOperand[] _selectClauses;
    protected final ContentFilter _whereClause;

    public EventFilter() {
        super();
        this._selectClauses = null;
        this._whereClause = null;
    }

    public EventFilter(SimpleAttributeOperand[] _selectClauses, ContentFilter _whereClause) {
        super();
        this._selectClauses = _selectClauses;
        this._whereClause = _whereClause;
    }

    @Nullable
    public SimpleAttributeOperand[] getSelectClauses() { return _selectClauses; }

    public ContentFilter getWhereClause() { return _whereClause; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SelectClauses", _selectClauses)
            .add("WhereClause", _whereClause)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<EventFilter> {
        @Override
        public EventFilter decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            SimpleAttributeOperand[] _selectClauses =
                reader.readArray(
                    () -> (SimpleAttributeOperand) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "SimpleAttributeOperand", reader),
                    SimpleAttributeOperand.class
                );
            ContentFilter _whereClause = (ContentFilter) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ContentFilter", reader);

            return new EventFilter(_selectClauses, _whereClause);
        }

        @Override
        public void encode(SerializationContext context, EventFilter encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeArray(
                encodable._selectClauses,
                e -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "SimpleAttributeOperand", e, writer)
            );
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ContentFilter", encodable._whereClause, writer);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<EventFilter> {
        @Override
        public EventFilter decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            SimpleAttributeOperand[] _selectClauses =
                reader.readArray(
                    "SelectClauses",
                    f -> (SimpleAttributeOperand) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "SimpleAttributeOperand", reader),
                    SimpleAttributeOperand.class
                );
            ContentFilter _whereClause = (ContentFilter) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ContentFilter", reader);

            return new EventFilter(_selectClauses, _whereClause);
        }

        @Override
        public void encode(SerializationContext context, EventFilter encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeArray(
                "SelectClauses",
                encodable._selectClauses,
                (f, e) -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "SimpleAttributeOperand", e, writer)
            );
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ContentFilter", encodable._whereClause, writer);
        }
    }

}
