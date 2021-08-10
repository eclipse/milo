/*
 * Copyright (c) 2021 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class DatagramConnectionTransportDataType extends ConnectionTransportDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17467");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17468");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17472");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17476");

    private final ExtensionObject discoveryAddress;

    public DatagramConnectionTransportDataType(ExtensionObject discoveryAddress) {
        this.discoveryAddress = discoveryAddress;
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

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public ExtensionObject getDiscoveryAddress() {
        return discoveryAddress;
    }

    public static final class Codec extends GenericDataTypeCodec<DatagramConnectionTransportDataType> {
        @Override
        public Class<DatagramConnectionTransportDataType> getType() {
            return DatagramConnectionTransportDataType.class;
        }

        @Override
        public DatagramConnectionTransportDataType decode(SerializationContext context,
                                                          UaDecoder decoder) {
            ExtensionObject discoveryAddress = decoder.readExtensionObject("DiscoveryAddress");
            return new DatagramConnectionTransportDataType(discoveryAddress);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           DatagramConnectionTransportDataType value) {
            encoder.writeExtensionObject("DiscoveryAddress", value.getDiscoveryAddress());
        }
    }
}
