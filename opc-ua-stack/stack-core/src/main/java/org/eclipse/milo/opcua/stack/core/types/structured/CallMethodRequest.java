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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

@UaDataType("CallMethodRequest")
public class CallMethodRequest implements UaStructure {

    public static final NodeId TypeId = Identifiers.CallMethodRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CallMethodRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CallMethodRequest_Encoding_DefaultXml;

    protected final NodeId _objectId;
    protected final NodeId _methodId;
    protected final Variant[] _inputArguments;

    public CallMethodRequest() {
        this._objectId = null;
        this._methodId = null;
        this._inputArguments = null;
    }

    public CallMethodRequest(NodeId _objectId, NodeId _methodId, Variant[] _inputArguments) {
        this._objectId = _objectId;
        this._methodId = _methodId;
        this._inputArguments = _inputArguments;
    }

    public NodeId getObjectId() { return _objectId; }

    public NodeId getMethodId() { return _methodId; }

    public Variant[] getInputArguments() { return _inputArguments; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(CallMethodRequest callMethodRequest, UaEncoder encoder) {
        encoder.encodeNodeId("ObjectId", callMethodRequest._objectId);
        encoder.encodeNodeId("MethodId", callMethodRequest._methodId);
        encoder.encodeArray("InputArguments", callMethodRequest._inputArguments, encoder::encodeVariant);
    }

    public static CallMethodRequest decode(UaDecoder decoder) {
        NodeId _objectId = decoder.decodeNodeId("ObjectId");
        NodeId _methodId = decoder.decodeNodeId("MethodId");
        Variant[] _inputArguments = decoder.decodeArray("InputArguments", decoder::decodeVariant, Variant.class);

        return new CallMethodRequest(_objectId, _methodId, _inputArguments);
    }

    static {
        DelegateRegistry.registerEncoder(CallMethodRequest::encode, CallMethodRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(CallMethodRequest::decode, CallMethodRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
