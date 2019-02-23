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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class CallRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CallRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CallRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CallRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final CallMethodRequest[] methodsToCall;

    public CallRequest() {
        this.requestHeader = null;
        this.methodsToCall = null;
    }

    public CallRequest(RequestHeader requestHeader, CallMethodRequest[] methodsToCall) {
        this.requestHeader = requestHeader;
        this.methodsToCall = methodsToCall;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    @Nullable
    public CallMethodRequest[] getMethodsToCall() { return methodsToCall; }

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
            .add("MethodsToCall", methodsToCall)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<CallRequest> {

        @Override
        public Class<CallRequest> getType() {
            return CallRequest.class;
        }

        @Override
        public CallRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            CallMethodRequest[] methodsToCall =
                decoder.readBuiltinStructArray(
                    "MethodsToCall",
                    CallMethodRequest.class
                );

            return new CallRequest(requestHeader, methodsToCall);
        }

        @Override
        public void encode(CallRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBuiltinStructArray(
                "MethodsToCall",
                value.methodsToCall,
                CallMethodRequest.class
            );
        }
    }

}
