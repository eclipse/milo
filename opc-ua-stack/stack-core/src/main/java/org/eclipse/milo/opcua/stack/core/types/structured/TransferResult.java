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
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class TransferResult extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=836");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=838");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=837");

    private final StatusCode statusCode;

    private final UInteger[] availableSequenceNumbers;

    public TransferResult(StatusCode statusCode, UInteger[] availableSequenceNumbers) {
        this.statusCode = statusCode;
        this.availableSequenceNumbers = availableSequenceNumbers;
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

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public UInteger[] getAvailableSequenceNumbers() {
        return availableSequenceNumbers;
    }

    public static final class Codec extends GenericDataTypeCodec<TransferResult> {
        @Override
        public Class<TransferResult> getType() {
            return TransferResult.class;
        }

        @Override
        public TransferResult decode(SerializationContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            UInteger[] availableSequenceNumbers = decoder.readUInt32Array("AvailableSequenceNumbers");
            return new TransferResult(statusCode, availableSequenceNumbers);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, TransferResult value) {
            encoder.writeStatusCode("StatusCode", value.getStatusCode());
            encoder.writeUInt32Array("AvailableSequenceNumbers", value.getAvailableSequenceNumbers());
        }
    }
}
