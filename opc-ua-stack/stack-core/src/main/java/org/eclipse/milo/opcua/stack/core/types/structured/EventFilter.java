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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
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

    public SimpleAttributeOperand[] getSelectClauses() { return _selectClauses; }

    public ContentFilter getWhereClause() { return _whereClause; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(EventFilter eventFilter, UaEncoder encoder) {
        encoder.encodeArray("SelectClauses", eventFilter._selectClauses, encoder::encodeSerializable);
        encoder.encodeSerializable("WhereClause", eventFilter._whereClause != null ? eventFilter._whereClause : new ContentFilter());
    }

    public static EventFilter decode(UaDecoder decoder) {
        SimpleAttributeOperand[] _selectClauses = decoder.decodeArray("SelectClauses", decoder::decodeSerializable, SimpleAttributeOperand.class);
        ContentFilter _whereClause = decoder.decodeSerializable("WhereClause", ContentFilter.class);

        return new EventFilter(_selectClauses, _whereClause);
    }

    static {
        DelegateRegistry.registerEncoder(EventFilter::encode, EventFilter.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(EventFilter::decode, EventFilter.class, BinaryEncodingId, XmlEncodingId);
    }

}
