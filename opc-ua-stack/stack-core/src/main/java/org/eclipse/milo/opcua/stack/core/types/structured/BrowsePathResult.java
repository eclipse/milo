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

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class BrowsePathResult extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=549");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=551");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=550");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15192");

    private final StatusCode statusCode;

    private final BrowsePathTarget[] targets;

    public BrowsePathResult(StatusCode statusCode, BrowsePathTarget[] targets) {
        this.statusCode = statusCode;
        this.targets = targets;
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

    public BrowsePathTarget[] getTargets() {
        return targets;
    }

    public static final class Codec extends GenericDataTypeCodec<BrowsePathResult> {
        @Override
        public Class<BrowsePathResult> getType() {
            return BrowsePathResult.class;
        }

        @Override
        public BrowsePathResult decode(SerializationContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            BrowsePathTarget[] targets = (BrowsePathTarget[]) decoder.readStructArray("Targets", BrowsePathTarget.TYPE_ID);
            return new BrowsePathResult(statusCode, targets);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, BrowsePathResult value) {
            encoder.writeStatusCode("StatusCode", value.getStatusCode());
            encoder.writeStructArray("Targets", value.getTargets(), BrowsePathTarget.TYPE_ID);
        }
    }
}
