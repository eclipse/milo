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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("CallRequest")
public class CallRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CallRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CallRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CallRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final CallMethodRequest[] _methodsToCall;

    public CallRequest() {
        this._requestHeader = null;
        this._methodsToCall = null;
    }

    public CallRequest(RequestHeader _requestHeader, CallMethodRequest[] _methodsToCall) {
        this._requestHeader = _requestHeader;
        this._methodsToCall = _methodsToCall;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    @Nullable
    public CallMethodRequest[] getMethodsToCall() { return _methodsToCall; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RequestHeader", _requestHeader)
            .add("MethodsToCall", _methodsToCall)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<CallRequest> {
        @Override
        public CallRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            CallMethodRequest[] _methodsToCall =
                reader.readArray(
                    () -> (CallMethodRequest) context.decode(
                        CallMethodRequest.BinaryEncodingId, reader),
                    CallMethodRequest.class
                );

            return new CallRequest(_requestHeader, _methodsToCall);
        }

        @Override
        public void encode(SerializationContext context, CallRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
            writer.writeArray(
                value._methodsToCall,
                e -> context.encode(CallMethodRequest.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<CallRequest> {
        @Override
        public CallRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            CallMethodRequest[] _methodsToCall =
                reader.readArray(
                    "MethodsToCall",
                    f -> (CallMethodRequest) context.decode(
                        CallMethodRequest.XmlEncodingId, reader),
                    CallMethodRequest.class
                );

            return new CallRequest(_requestHeader, _methodsToCall);
        }

        @Override
        public void encode(SerializationContext context, CallRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeArray(
                "MethodsToCall",
                encodable._methodsToCall,
                (f, e) -> context.encode(CallMethodRequest.XmlEncodingId, e, writer)
            );
        }
    }

}
