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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class BrowseRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=525");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=527");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=526");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15184");

    private final RequestHeader requestHeader;

    private final ViewDescription view;

    private final UInteger requestedMaxReferencesPerNode;

    private final BrowseDescription[] nodesToBrowse;

    public BrowseRequest(RequestHeader requestHeader, ViewDescription view,
                         UInteger requestedMaxReferencesPerNode, BrowseDescription[] nodesToBrowse) {
        this.requestHeader = requestHeader;
        this.view = view;
        this.requestedMaxReferencesPerNode = requestedMaxReferencesPerNode;
        this.nodesToBrowse = nodesToBrowse;
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

    public ViewDescription getView() {
        return view;
    }

    public UInteger getRequestedMaxReferencesPerNode() {
        return requestedMaxReferencesPerNode;
    }

    public BrowseDescription[] getNodesToBrowse() {
        return nodesToBrowse;
    }

    public static final class Codec extends GenericDataTypeCodec<BrowseRequest> {
        @Override
        public Class<BrowseRequest> getType() {
            return BrowseRequest.class;
        }

        @Override
        public BrowseRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            ViewDescription view = (ViewDescription) decoder.readStruct("View", ViewDescription.TYPE_ID);
            UInteger requestedMaxReferencesPerNode = decoder.readUInt32("RequestedMaxReferencesPerNode");
            BrowseDescription[] nodesToBrowse = (BrowseDescription[]) decoder.readStructArray("NodesToBrowse", BrowseDescription.TYPE_ID);
            return new BrowseRequest(requestHeader, view, requestedMaxReferencesPerNode, nodesToBrowse);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, BrowseRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeStruct("View", value.getView(), ViewDescription.TYPE_ID);
            encoder.writeUInt32("RequestedMaxReferencesPerNode", value.getRequestedMaxReferencesPerNode());
            encoder.writeStructArray("NodesToBrowse", value.getNodesToBrowse(), BrowseDescription.TYPE_ID);
        }
    }
}
