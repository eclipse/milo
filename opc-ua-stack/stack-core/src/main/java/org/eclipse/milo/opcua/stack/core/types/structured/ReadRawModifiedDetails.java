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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ReadRawModifiedDetails extends HistoryReadDetails {

    public static final NodeId TypeId = Identifiers.ReadRawModifiedDetails;
    public static final NodeId BinaryEncodingId = Identifiers.ReadRawModifiedDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadRawModifiedDetails_Encoding_DefaultXml;

    protected final Boolean isReadModified;
    protected final DateTime startTime;
    protected final DateTime endTime;
    protected final UInteger numValuesPerNode;
    protected final Boolean returnBounds;

    public ReadRawModifiedDetails() {
        super();
        this.isReadModified = null;
        this.startTime = null;
        this.endTime = null;
        this.numValuesPerNode = null;
        this.returnBounds = null;
    }

    public ReadRawModifiedDetails(Boolean isReadModified, DateTime startTime, DateTime endTime, UInteger numValuesPerNode, Boolean returnBounds) {
        super();
        this.isReadModified = isReadModified;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numValuesPerNode = numValuesPerNode;
        this.returnBounds = returnBounds;
    }

    public Boolean getIsReadModified() { return isReadModified; }

    public DateTime getStartTime() { return startTime; }

    public DateTime getEndTime() { return endTime; }

    public UInteger getNumValuesPerNode() { return numValuesPerNode; }

    public Boolean getReturnBounds() { return returnBounds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("IsReadModified", isReadModified)
            .add("StartTime", startTime)
            .add("EndTime", endTime)
            .add("NumValuesPerNode", numValuesPerNode)
            .add("ReturnBounds", returnBounds)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ReadRawModifiedDetails> {

        @Override
        public Class<ReadRawModifiedDetails> getType() {
            return ReadRawModifiedDetails.class;
        }

        @Override
        public ReadRawModifiedDetails decode(UaDecoder decoder) throws UaSerializationException {
            Boolean isReadModified = decoder.readBoolean("IsReadModified");
            DateTime startTime = decoder.readDateTime("StartTime");
            DateTime endTime = decoder.readDateTime("EndTime");
            UInteger numValuesPerNode = decoder.readUInt32("NumValuesPerNode");
            Boolean returnBounds = decoder.readBoolean("ReturnBounds");

            return new ReadRawModifiedDetails(isReadModified, startTime, endTime, numValuesPerNode, returnBounds);
        }

        @Override
        public void encode(ReadRawModifiedDetails value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBoolean("IsReadModified", value.isReadModified);
            encoder.writeDateTime("StartTime", value.startTime);
            encoder.writeDateTime("EndTime", value.endTime);
            encoder.writeUInt32("NumValuesPerNode", value.numValuesPerNode);
            encoder.writeBoolean("ReturnBounds", value.returnBounds);
        }
    }

}
