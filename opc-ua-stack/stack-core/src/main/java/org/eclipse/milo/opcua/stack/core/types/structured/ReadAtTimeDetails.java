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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ReadAtTimeDetails extends HistoryReadDetails implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=653");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=655");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=654");

    private final DateTime[] reqTimes;

    private final Boolean useSimpleBounds;

    public ReadAtTimeDetails(DateTime[] reqTimes, Boolean useSimpleBounds) {
        this.reqTimes = reqTimes;
        this.useSimpleBounds = useSimpleBounds;
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

    public DateTime[] getReqTimes() {
        return reqTimes;
    }

    public Boolean getUseSimpleBounds() {
        return useSimpleBounds;
    }

    public static final class Codec extends GenericDataTypeCodec<ReadAtTimeDetails> {
        @Override
        public Class<ReadAtTimeDetails> getType() {
            return ReadAtTimeDetails.class;
        }

        @Override
        public ReadAtTimeDetails decode(SerializationContext context, UaDecoder decoder) {
            DateTime[] reqTimes = decoder.readDateTimeArray("ReqTimes");
            Boolean useSimpleBounds = decoder.readBoolean("UseSimpleBounds");
            return new ReadAtTimeDetails(reqTimes, useSimpleBounds);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ReadAtTimeDetails value) {
            encoder.writeDateTimeArray("ReqTimes", value.getReqTimes());
            encoder.writeBoolean("UseSimpleBounds", value.getUseSimpleBounds());
        }
    }
}
