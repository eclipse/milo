/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class BrowseRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.BrowseRequest;
    public static final NodeId BinaryEncodingId = Identifiers.BrowseRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowseRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final ViewDescription view;
    protected final UInteger requestedMaxReferencesPerNode;
    protected final BrowseDescription[] nodesToBrowse;

    public BrowseRequest() {
        this.requestHeader = null;
        this.view = null;
        this.requestedMaxReferencesPerNode = null;
        this.nodesToBrowse = null;
    }

    public BrowseRequest(RequestHeader requestHeader, ViewDescription view, UInteger requestedMaxReferencesPerNode, BrowseDescription[] nodesToBrowse) {
        this.requestHeader = requestHeader;
        this.view = view;
        this.requestedMaxReferencesPerNode = requestedMaxReferencesPerNode;
        this.nodesToBrowse = nodesToBrowse;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public ViewDescription getView() { return view; }

    public UInteger getRequestedMaxReferencesPerNode() { return requestedMaxReferencesPerNode; }

    @Nullable
    public BrowseDescription[] getNodesToBrowse() { return nodesToBrowse; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RequestHeader", requestHeader)
            .add("View", view)
            .add("RequestedMaxReferencesPerNode", requestedMaxReferencesPerNode)
            .add("NodesToBrowse", nodesToBrowse)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<BrowseRequest> {

        @Override
        public Class<BrowseRequest> getType() {
            return BrowseRequest.class;
        }

        @Override
        public BrowseRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            ViewDescription view = (ViewDescription) decoder.readBuiltinStruct("View", ViewDescription.class);
            UInteger requestedMaxReferencesPerNode = decoder.readUInt32("RequestedMaxReferencesPerNode");
            BrowseDescription[] nodesToBrowse =
                decoder.readBuiltinStructArray(
                    "NodesToBrowse",
                    BrowseDescription.class
                );

            return new BrowseRequest(requestHeader, view, requestedMaxReferencesPerNode, nodesToBrowse);
        }

        @Override
        public void encode(BrowseRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBuiltinStruct("View", value.view, ViewDescription.class);
            encoder.writeUInt32("RequestedMaxReferencesPerNode", value.requestedMaxReferencesPerNode);
            encoder.writeBuiltinStructArray(
                "NodesToBrowse",
                value.nodesToBrowse,
                BrowseDescription.class
            );
        }
    }

}
