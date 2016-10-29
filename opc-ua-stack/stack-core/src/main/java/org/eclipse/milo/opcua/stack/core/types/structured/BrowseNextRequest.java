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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("BrowseNextRequest")
public class BrowseNextRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.BrowseNextRequest;
    public static final NodeId BinaryEncodingId = Identifiers.BrowseNextRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowseNextRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final Boolean _releaseContinuationPoints;
    protected final ByteString[] _continuationPoints;

    public BrowseNextRequest() {
        this._requestHeader = null;
        this._releaseContinuationPoints = null;
        this._continuationPoints = null;
    }

    public BrowseNextRequest(RequestHeader _requestHeader, Boolean _releaseContinuationPoints, ByteString[] _continuationPoints) {
        this._requestHeader = _requestHeader;
        this._releaseContinuationPoints = _releaseContinuationPoints;
        this._continuationPoints = _continuationPoints;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public Boolean getReleaseContinuationPoints() { return _releaseContinuationPoints; }

    public ByteString[] getContinuationPoints() { return _continuationPoints; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(BrowseNextRequest browseNextRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", browseNextRequest._requestHeader != null ? browseNextRequest._requestHeader : new RequestHeader());
        encoder.encodeBoolean("ReleaseContinuationPoints", browseNextRequest._releaseContinuationPoints);
        encoder.encodeArray("ContinuationPoints", browseNextRequest._continuationPoints, encoder::encodeByteString);
    }

    public static BrowseNextRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        Boolean _releaseContinuationPoints = decoder.decodeBoolean("ReleaseContinuationPoints");
        ByteString[] _continuationPoints = decoder.decodeArray("ContinuationPoints", decoder::decodeByteString, ByteString.class);

        return new BrowseNextRequest(_requestHeader, _releaseContinuationPoints, _continuationPoints);
    }

    static {
        DelegateRegistry.registerEncoder(BrowseNextRequest::encode, BrowseNextRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(BrowseNextRequest::decode, BrowseNextRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
