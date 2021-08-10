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
public class SessionlessInvokeRequestType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15901");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15903");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15902");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15091");

    private final UInteger urisVersion;

    private final String[] namespaceUris;

    private final String[] serverUris;

    private final String[] localeIds;

    private final UInteger serviceId;

    public SessionlessInvokeRequestType(UInteger urisVersion, String[] namespaceUris,
                                        String[] serverUris, String[] localeIds, UInteger serviceId) {
        this.urisVersion = urisVersion;
        this.namespaceUris = namespaceUris;
        this.serverUris = serverUris;
        this.localeIds = localeIds;
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

    public UInteger getUrisVersion() {
        return urisVersion;
    }

    public String[] getNamespaceUris() {
        return namespaceUris;
    }

    public String[] getServerUris() {
        return serverUris;
    }

    public String[] getLocaleIds() {
        return localeIds;
    }

    public UInteger getServiceId() {
        return serviceId;
    }

    public static final class Codec extends GenericDataTypeCodec<SessionlessInvokeRequestType> {
        @Override
        public Class<SessionlessInvokeRequestType> getType() {
            return SessionlessInvokeRequestType.class;
        }

        @Override
        public SessionlessInvokeRequestType decode(SerializationContext context, UaDecoder decoder) {
            UInteger urisVersion = decoder.readUInt32("UrisVersion");
            String[] namespaceUris = decoder.readStringArray("NamespaceUris");
            String[] serverUris = decoder.readStringArray("ServerUris");
            String[] localeIds = decoder.readStringArray("LocaleIds");
            UInteger serviceId = decoder.readUInt32("ServiceId");
            return new SessionlessInvokeRequestType(urisVersion, namespaceUris, serverUris, localeIds, serviceId);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           SessionlessInvokeRequestType value) {
            encoder.writeUInt32("UrisVersion", value.getUrisVersion());
            encoder.writeStringArray("NamespaceUris", value.getNamespaceUris());
            encoder.writeStringArray("ServerUris", value.getServerUris());
            encoder.writeStringArray("LocaleIds", value.getLocaleIds());
            encoder.writeUInt32("ServiceId", value.getServiceId());
        }
    }
}
