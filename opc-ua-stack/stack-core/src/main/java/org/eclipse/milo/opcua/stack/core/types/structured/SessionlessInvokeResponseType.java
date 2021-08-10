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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class SessionlessInvokeResponseType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=20999");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21001");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21000");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15092");

    private final String[] namespaceUris;

    private final String[] serverUris;

    private final UInteger serviceId;

    public SessionlessInvokeResponseType(String[] namespaceUris, String[] serverUris,
                                         UInteger serviceId) {
        this.namespaceUris = namespaceUris;
        this.serverUris = serverUris;
        this.serviceId = serviceId;
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

    public String[] getNamespaceUris() {
        return namespaceUris;
    }

    public String[] getServerUris() {
        return serverUris;
    }

    public UInteger getServiceId() {
        return serviceId;
    }

    public static final class Codec extends GenericDataTypeCodec<SessionlessInvokeResponseType> {
        @Override
        public Class<SessionlessInvokeResponseType> getType() {
            return SessionlessInvokeResponseType.class;
        }

        @Override
        public SessionlessInvokeResponseType decode(SerializationContext context, UaDecoder decoder) {
            String[] namespaceUris = decoder.readStringArray("NamespaceUris");
            String[] serverUris = decoder.readStringArray("ServerUris");
            UInteger serviceId = decoder.readUInt32("ServiceId");
            return new SessionlessInvokeResponseType(namespaceUris, serverUris, serviceId);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           SessionlessInvokeResponseType value) {
            encoder.writeStringArray("NamespaceUris", value.getNamespaceUris());
            encoder.writeStringArray("ServerUris", value.getServerUris());
            encoder.writeUInt32("ServiceId", value.getServiceId());
        }
    }
}
