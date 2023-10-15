/*
 * Copyright (c) 2023 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.19">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.19</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class NetworkGroupDataType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=11944");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=11958");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=11950");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15364");

    private final @Nullable String serverUri;

    private final EndpointUrlListDataType @Nullable [] networkPaths;

    public NetworkGroupDataType(@Nullable String serverUri,
                                EndpointUrlListDataType @Nullable [] networkPaths) {
        this.serverUri = serverUri;
        this.networkPaths = networkPaths;
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

    public @Nullable String getServerUri() {
        return serverUri;
    }

    public EndpointUrlListDataType @Nullable [] getNetworkPaths() {
        return networkPaths;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 11958),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ServerUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("NetworkPaths", LocalizedText.NULL_VALUE, new NodeId(0, 11943), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<NetworkGroupDataType> {
        @Override
        public Class<NetworkGroupDataType> getType() {
            return NetworkGroupDataType.class;
        }

        @Override
        public NetworkGroupDataType decodeType(EncodingContext context, UaDecoder decoder) {
            String serverUri = decoder.decodeString("ServerUri");
            EndpointUrlListDataType[] networkPaths = (EndpointUrlListDataType[]) decoder.decodeStructArray("NetworkPaths", EndpointUrlListDataType.TYPE_ID);
            return new NetworkGroupDataType(serverUri, networkPaths);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder, NetworkGroupDataType value) {
            encoder.encodeString("ServerUri", value.getServerUri());
            encoder.encodeStructArray("NetworkPaths", value.getNetworkPaths(), EndpointUrlListDataType.TYPE_ID);
        }
    }
}
