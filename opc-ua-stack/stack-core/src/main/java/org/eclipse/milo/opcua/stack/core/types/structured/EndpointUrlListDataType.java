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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("EndpointUrlListDataType")
public class EndpointUrlListDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.EndpointUrlListDataType;
    public static final NodeId BinaryEncodingId = Identifiers.EndpointUrlListDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EndpointUrlListDataType_Encoding_DefaultXml;

    protected final String[] _endpointUrlList;

    public EndpointUrlListDataType() {
        this._endpointUrlList = null;
    }

    public EndpointUrlListDataType(String[] _endpointUrlList) {
        this._endpointUrlList = _endpointUrlList;
    }

    public String[] getEndpointUrlList() { return _endpointUrlList; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(EndpointUrlListDataType endpointUrlListDataType, UaEncoder encoder) {
        encoder.encodeArray("EndpointUrlList", endpointUrlListDataType._endpointUrlList, encoder::encodeString);
    }

    public static EndpointUrlListDataType decode(UaDecoder decoder) {
        String[] _endpointUrlList = decoder.decodeArray("EndpointUrlList", decoder::decodeString, String.class);

        return new EndpointUrlListDataType(_endpointUrlList);
    }

    static {
        DelegateRegistry.registerEncoder(EndpointUrlListDataType::encode, EndpointUrlListDataType.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(EndpointUrlListDataType::decode, EndpointUrlListDataType.class, BinaryEncodingId, XmlEncodingId);
    }

}
