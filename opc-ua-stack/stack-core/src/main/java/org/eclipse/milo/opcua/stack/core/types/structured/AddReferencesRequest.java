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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class AddReferencesRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=492");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=494");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=493");

    private final RequestHeader requestHeader;

    private final AddReferencesItem[] referencesToAdd;

    public AddReferencesRequest(RequestHeader requestHeader, AddReferencesItem[] referencesToAdd) {
        this.requestHeader = requestHeader;
        this.referencesToAdd = referencesToAdd;
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

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public AddReferencesItem[] getReferencesToAdd() {
        return referencesToAdd;
    }

    public static final class Codec extends GenericDataTypeCodec<AddReferencesRequest> {
        @Override
        public Class<AddReferencesRequest> getType() {
            return AddReferencesRequest.class;
        }

        @Override
        public AddReferencesRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            AddReferencesItem[] referencesToAdd = (AddReferencesItem[]) decoder.readStructArray("ReferencesToAdd", AddReferencesItem.TYPE_ID);
            return new AddReferencesRequest(requestHeader, referencesToAdd);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           AddReferencesRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeStructArray("ReferencesToAdd", value.getReferencesToAdd(), AddReferencesItem.TYPE_ID);
        }
    }
}
