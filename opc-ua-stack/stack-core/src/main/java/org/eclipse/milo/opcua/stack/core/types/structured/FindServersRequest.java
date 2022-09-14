/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.serialization.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.4.2/#5.4.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.4.2/#5.4.2.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class FindServersRequest extends Structure implements UaRequestMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=420");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=422");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=421");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15093");

    private final RequestHeader requestHeader;

    private final String endpointUrl;

    private final String[] localeIds;

    private final String[] serverUris;

    public FindServersRequest(RequestHeader requestHeader, String endpointUrl, String[] localeIds,
                              String[] serverUris) {
        this.requestHeader = requestHeader;
        this.endpointUrl = endpointUrl;
        this.localeIds = localeIds;
        this.serverUris = serverUris;
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

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public String[] getLocaleIds() {
        return localeIds;
    }

    public String[] getServerUris() {
        return serverUris;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 422),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("EndpointUrl", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("LocaleIds", LocalizedText.NULL_VALUE, new NodeId(0, 295), 1, null, UInteger.valueOf(0), false),
                new StructureField("ServerUris", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<FindServersRequest> {
        @Override
        public Class<FindServersRequest> getType() {
            return FindServersRequest.class;
        }

        @Override
        public FindServersRequest decodeType(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.decodeStruct("RequestHeader", RequestHeader.TYPE_ID);
            String endpointUrl = decoder.decodeString("EndpointUrl");
            String[] localeIds = decoder.decodeStringArray("LocaleIds");
            String[] serverUris = decoder.decodeStringArray("ServerUris");
            return new FindServersRequest(requestHeader, endpointUrl, localeIds, serverUris);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               FindServersRequest value) {
            encoder.encodeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.encodeString("EndpointUrl", value.getEndpointUrl());
            encoder.encodeStringArray("LocaleIds", value.getLocaleIds());
            encoder.encodeStringArray("ServerUris", value.getServerUris());
        }
    }
}
