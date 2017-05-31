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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("ReadAtTimeDetails")
public class ReadAtTimeDetails extends HistoryReadDetails {

    public static final NodeId TypeId = Identifiers.ReadAtTimeDetails;
    public static final NodeId BinaryEncodingId = Identifiers.ReadAtTimeDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadAtTimeDetails_Encoding_DefaultXml;

    protected final DateTime[] _reqTimes;
    protected final Boolean _useSimpleBounds;

    public ReadAtTimeDetails() {
        super();
        this._reqTimes = null;
        this._useSimpleBounds = null;
    }

    public ReadAtTimeDetails(DateTime[] _reqTimes, Boolean _useSimpleBounds) {
        super();
        this._reqTimes = _reqTimes;
        this._useSimpleBounds = _useSimpleBounds;
    }

    @Nullable
    public DateTime[] getReqTimes() { return _reqTimes; }

    public Boolean getUseSimpleBounds() { return _useSimpleBounds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ReqTimes", _reqTimes)
            .add("UseSimpleBounds", _useSimpleBounds)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ReadAtTimeDetails> {
        @Override
        public ReadAtTimeDetails decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            DateTime[] _reqTimes = reader.readArray(reader::readDateTime, DateTime.class);
            Boolean _useSimpleBounds = reader.readBoolean();

            return new ReadAtTimeDetails(_reqTimes, _useSimpleBounds);
        }

        @Override
        public void encode(SerializationContext context, ReadAtTimeDetails encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeArray(encodable._reqTimes, writer::writeDateTime);
            writer.writeBoolean(encodable._useSimpleBounds);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ReadAtTimeDetails> {
        @Override
        public ReadAtTimeDetails decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            DateTime[] _reqTimes = reader.readArray("ReqTimes", reader::readDateTime, DateTime.class);
            Boolean _useSimpleBounds = reader.readBoolean("UseSimpleBounds");

            return new ReadAtTimeDetails(_reqTimes, _useSimpleBounds);
        }

        @Override
        public void encode(SerializationContext context, ReadAtTimeDetails encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeArray("ReqTimes", encodable._reqTimes, writer::writeDateTime);
            writer.writeBoolean("UseSimpleBounds", encodable._useSimpleBounds);
        }
    }

}
