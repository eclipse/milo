/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import java.util.StringJoiner;

import lombok.EqualsAndHashCode;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.4.5/#5.4.5.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.4.5/#5.4.5.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
public class RegisterServerRequest extends Structure implements UaRequestMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=435");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=437");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=436");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15103");

    private final RequestHeader requestHeader;

    private final RegisteredServer server;

    public RegisterServerRequest(RequestHeader requestHeader, RegisteredServer server) {
        this.requestHeader = requestHeader;
        this.server = server;
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

    public RegisteredServer getServer() {
        return server;
    }

    @Override
    public String toString() {
        var joiner = new StringJoiner(", ", RegisterServerRequest.class.getSimpleName() + "[", "]");
        joiner.add("requestHeader=" + getRequestHeader());
        joiner.add("server=" + getServer());
        return joiner.toString();
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 437),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("Server", LocalizedText.NULL_VALUE, new NodeId(0, 432), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<RegisterServerRequest> {
        @Override
        public Class<RegisterServerRequest> getType() {
            return RegisterServerRequest.class;
        }

        @Override
        public RegisterServerRequest decodeType(EncodingContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.decodeStruct("RequestHeader", RequestHeader.TYPE_ID);
            RegisteredServer server = (RegisteredServer) decoder.decodeStruct("Server", RegisteredServer.TYPE_ID);
            return new RegisterServerRequest(requestHeader, server);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               RegisterServerRequest value) {
            encoder.encodeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.encodeStruct("Server", value.getServer(), RegisteredServer.TYPE_ID);
        }
    }
}
