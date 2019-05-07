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
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class MdnsDiscoveryConfiguration extends DiscoveryConfiguration implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12891");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12893");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12901");

    private final String mdnsServerName;

    private final String[] serverCapabilities;

    public MdnsDiscoveryConfiguration(String mdnsServerName, String[] serverCapabilities) {
        this.mdnsServerName = mdnsServerName;
        this.serverCapabilities = serverCapabilities;
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

    public String getMdnsServerName() {
        return mdnsServerName;
    }

    public String[] getServerCapabilities() {
        return serverCapabilities;
    }

    public static final class Codec extends GenericDataTypeCodec<MdnsDiscoveryConfiguration> {
        @Override
        public Class<MdnsDiscoveryConfiguration> getType() {
            return MdnsDiscoveryConfiguration.class;
        }

        @Override
        public MdnsDiscoveryConfiguration decode(SerializationContext context, UaDecoder decoder) {
            String mdnsServerName = decoder.readString("MdnsServerName");
            String[] serverCapabilities = decoder.readStringArray("ServerCapabilities");
            return new MdnsDiscoveryConfiguration(mdnsServerName, serverCapabilities);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           MdnsDiscoveryConfiguration value) {
            encoder.writeString("MdnsServerName", value.getMdnsServerName());
            encoder.writeStringArray("ServerCapabilities", value.getServerCapabilities());
        }
    }
}
