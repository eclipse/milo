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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class DeleteAtTimeDetails extends HistoryUpdateDetails {

    public static final NodeId TypeId = Identifiers.DeleteAtTimeDetails;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteAtTimeDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteAtTimeDetails_Encoding_DefaultXml;

    protected final DateTime[] reqTimes;

    public DeleteAtTimeDetails() {
        super(null);
        this.reqTimes = null;
    }

    public DeleteAtTimeDetails(NodeId nodeId, DateTime[] reqTimes) {
        super(nodeId);
        this.reqTimes = reqTimes;
    }

    @Nullable
    public DateTime[] getReqTimes() { return reqTimes; }

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
            .add("ReqTimes", reqTimes)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<DeleteAtTimeDetails> {

        @Override
        public Class<DeleteAtTimeDetails> getType() {
            return DeleteAtTimeDetails.class;
        }

        @Override
        public DeleteAtTimeDetails decode(UaDecoder decoder) throws UaSerializationException {
            NodeId nodeId = decoder.readNodeId("NodeId");
            DateTime[] reqTimes = decoder.readArray("ReqTimes", decoder::readDateTime, DateTime.class);

            return new DeleteAtTimeDetails(nodeId, reqTimes);
        }

        @Override
        public void encode(DeleteAtTimeDetails value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("NodeId", value.nodeId);
            encoder.writeArray("ReqTimes", value.reqTimes, encoder::writeDateTime);
        }
    }

}
