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

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class EndpointUrlListDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11943");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11949");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11957");

    private final String[] endpointUrlList;

    public EndpointUrlListDataType(String[] endpointUrlList) {
        this.endpointUrlList = endpointUrlList;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    public String[] getEndpointUrlList() {
        return endpointUrlList;
    }

    public static final class Codec extends GenericDataTypeCodec<EndpointUrlListDataType> {
        @Override
        public Class<EndpointUrlListDataType> getType() {
            return EndpointUrlListDataType.class;
        }

        @Override
        public EndpointUrlListDataType decode(SerializationContext context, UaDecoder decoder) {
            String[] endpointUrlList = decoder.readStringArray("EndpointUrlList");
            return new EndpointUrlListDataType(endpointUrlList);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           EndpointUrlListDataType value) {
            encoder.writeStringArray("EndpointUrlList", value.getEndpointUrlList());
        }
    }
}
