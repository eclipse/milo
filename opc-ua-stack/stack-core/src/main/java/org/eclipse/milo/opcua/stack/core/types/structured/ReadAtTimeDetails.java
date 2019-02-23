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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class ReadAtTimeDetails extends HistoryReadDetails {

    public static final NodeId TypeId = Identifiers.ReadAtTimeDetails;
    public static final NodeId BinaryEncodingId = Identifiers.ReadAtTimeDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadAtTimeDetails_Encoding_DefaultXml;

    protected final DateTime[] reqTimes;
    protected final Boolean useSimpleBounds;

    public ReadAtTimeDetails() {
        super();
        this.reqTimes = null;
        this.useSimpleBounds = null;
    }

    public ReadAtTimeDetails(DateTime[] reqTimes, Boolean useSimpleBounds) {
        super();
        this.reqTimes = reqTimes;
        this.useSimpleBounds = useSimpleBounds;
    }

    @Nullable
    public DateTime[] getReqTimes() { return reqTimes; }

    public Boolean getUseSimpleBounds() { return useSimpleBounds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ReqTimes", reqTimes)
            .add("UseSimpleBounds", useSimpleBounds)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ReadAtTimeDetails> {

        @Override
        public Class<ReadAtTimeDetails> getType() {
            return ReadAtTimeDetails.class;
        }

        @Override
        public ReadAtTimeDetails decode(UaDecoder decoder) throws UaSerializationException {
            DateTime[] reqTimes = decoder.readArray("ReqTimes", decoder::readDateTime, DateTime.class);
            Boolean useSimpleBounds = decoder.readBoolean("UseSimpleBounds");

            return new ReadAtTimeDetails(reqTimes, useSimpleBounds);
        }

        @Override
        public void encode(ReadAtTimeDetails value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeArray("ReqTimes", value.reqTimes, encoder::writeDateTime);
            encoder.writeBoolean("UseSimpleBounds", value.useSimpleBounds);
        }
    }

}
