/*
 * Copyright (c) 2016 Kevin Herron
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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("TranslateBrowsePathsToNodeIdsRequest")
public class TranslateBrowsePathsToNodeIdsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.TranslateBrowsePathsToNodeIdsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.TranslateBrowsePathsToNodeIdsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TranslateBrowsePathsToNodeIdsRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final BrowsePath[] _browsePaths;

    public TranslateBrowsePathsToNodeIdsRequest() {
        this._requestHeader = null;
        this._browsePaths = null;
    }

    public TranslateBrowsePathsToNodeIdsRequest(RequestHeader _requestHeader, BrowsePath[] _browsePaths) {
        this._requestHeader = _requestHeader;
        this._browsePaths = _browsePaths;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public BrowsePath[] getBrowsePaths() { return _browsePaths; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(TranslateBrowsePathsToNodeIdsRequest translateBrowsePathsToNodeIdsRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", translateBrowsePathsToNodeIdsRequest._requestHeader != null ? translateBrowsePathsToNodeIdsRequest._requestHeader : new RequestHeader());
        encoder.encodeArray("BrowsePaths", translateBrowsePathsToNodeIdsRequest._browsePaths, encoder::encodeSerializable);
    }

    public static TranslateBrowsePathsToNodeIdsRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        BrowsePath[] _browsePaths = decoder.decodeArray("BrowsePaths", decoder::decodeSerializable, BrowsePath.class);

        return new TranslateBrowsePathsToNodeIdsRequest(_requestHeader, _browsePaths);
    }

    static {
        DelegateRegistry.registerEncoder(TranslateBrowsePathsToNodeIdsRequest::encode, TranslateBrowsePathsToNodeIdsRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(TranslateBrowsePathsToNodeIdsRequest::decode, TranslateBrowsePathsToNodeIdsRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
