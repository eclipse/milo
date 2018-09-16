/*
 * Copyright (c) 2018 Kevin Herron
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

package org.eclipse.milo.opcua.stack.server.transport;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import org.eclipse.milo.opcua.stack.core.UaExceptionStatus;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceResponse;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.server.UaStackServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcServerHttpCodec extends MessageToMessageCodec<HttpRequest, ServiceResponse> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UaStackServer stackServer;

    public OpcServerHttpCodec(UaStackServer stackServer) {
        this.stackServer = stackServer;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ServiceResponse msg, List<Object> out) throws Exception {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, HttpRequest httpRequest, List<Object> out) throws Exception {
        if (httpRequest instanceof FullHttpRequest) {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) httpRequest;

            SecurityPolicy securityPolicy = SecurityPolicy.None;

            String securityPolicyUri = fullHttpRequest.headers().get("OPCUA-SecurityPolicy");
            if (securityPolicyUri != null) {
                securityPolicy = SecurityPolicy.fromUri(securityPolicyUri);
            }

            ByteBuf content = fullHttpRequest.content();
            OpcUaBinaryStreamDecoder decoder = new OpcUaBinaryStreamDecoder(content);

            try {
                UaRequestMessage requestMessage = (UaRequestMessage) decoder.readMessage(null);

                // TODO nothing about this is correct
                ServiceRequest<UaRequestMessage, UaResponseMessage> serviceRequest = new ServiceRequest<UaRequestMessage, UaResponseMessage>(
                    requestMessage,
                    0,
                    null,
                    new ServerSecureChannel()
                );

                stackServer.onServiceRequest(serviceRequest);
            } catch (Throwable t) {
                logger.error("Error decoding UaRequestMessage", t);

                StatusCode statusCode = UaExceptionStatus.extract(t)
                    .map(UaExceptionStatus::getStatusCode)
                    .orElse(StatusCode.BAD);

                ServiceFault serviceFault = new ServiceFault(
                    new ResponseHeader(
                        DateTime.now(),
                        uint(0),
                        statusCode,
                        null, null, null
                    )
                );

                // TODO ctx.writeAndFlush(new ServiceResponse(null, requestId, serviceFault));
            }
        }
    }

}
