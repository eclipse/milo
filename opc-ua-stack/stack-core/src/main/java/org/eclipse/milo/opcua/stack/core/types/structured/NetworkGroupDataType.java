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
public class NetworkGroupDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11944");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11950");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11958");

    private final String serverUri;

    private final EndpointUrlListDataType[] networkPaths;

    public NetworkGroupDataType(String serverUri, EndpointUrlListDataType[] networkPaths) {
        this.serverUri = serverUri;
        this.networkPaths = networkPaths;
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

    public String getServerUri() {
        return serverUri;
    }

    public EndpointUrlListDataType[] getNetworkPaths() {
        return networkPaths;
    }

    public static final class Codec extends GenericDataTypeCodec<NetworkGroupDataType> {
        @Override
        public Class<NetworkGroupDataType> getType() {
            return NetworkGroupDataType.class;
        }

        @Override
        public NetworkGroupDataType decode(SerializationContext context, UaDecoder decoder) {
            String serverUri = decoder.readString("ServerUri");
            EndpointUrlListDataType[] networkPaths = (EndpointUrlListDataType[]) decoder.readStructArray("NetworkPaths", EndpointUrlListDataType.TYPE_ID);
            return new NetworkGroupDataType(serverUri, networkPaths);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           NetworkGroupDataType value) {
            encoder.writeString("ServerUri", value.getServerUri());
            encoder.writeStructArray("NetworkPaths", value.getNetworkPaths(), EndpointUrlListDataType.TYPE_ID);
        }
    }
}
