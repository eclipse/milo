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

public class DeleteRawModifiedDetails extends HistoryUpdateDetails {

    public static final NodeId TypeId = Identifiers.DeleteRawModifiedDetails;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteRawModifiedDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteRawModifiedDetails_Encoding_DefaultXml;

    protected final Boolean isDeleteModified;
    protected final DateTime startTime;
    protected final DateTime endTime;

    public DeleteRawModifiedDetails() {
        super(null);
        this.isDeleteModified = null;
        this.startTime = null;
        this.endTime = null;
    }

    public DeleteRawModifiedDetails(NodeId nodeId, Boolean isDeleteModified, DateTime startTime, DateTime endTime) {
        super(nodeId);
        this.isDeleteModified = isDeleteModified;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Boolean getIsDeleteModified() { return isDeleteModified; }

    public DateTime getStartTime() { return startTime; }

    public DateTime getEndTime() { return endTime; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NodeId", nodeId)
            .add("IsDeleteModified", isDeleteModified)
            .add("StartTime", startTime)
            .add("EndTime", endTime)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<DeleteRawModifiedDetails> {

        @Override
        public Class<DeleteRawModifiedDetails> getType() {
            return DeleteRawModifiedDetails.class;
        }

        @Override
        public DeleteRawModifiedDetails decode(UaDecoder decoder) throws UaSerializationException {
            NodeId nodeId = decoder.readNodeId("NodeId");
            Boolean isDeleteModified = decoder.readBoolean("IsDeleteModified");
            DateTime startTime = decoder.readDateTime("StartTime");
            DateTime endTime = decoder.readDateTime("EndTime");

            return new DeleteRawModifiedDetails(nodeId, isDeleteModified, startTime, endTime);
        }

        @Override
        public void encode(DeleteRawModifiedDetails value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("NodeId", value.nodeId);
            encoder.writeBoolean("IsDeleteModified", value.isDeleteModified);
            encoder.writeDateTime("StartTime", value.startTime);
            encoder.writeDateTime("EndTime", value.endTime);
        }
    }

}
