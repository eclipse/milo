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

package org.eclipse.milo.opcua.stack.server.transport.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import org.eclipse.milo.opcua.stack.core.UaExceptionStatus;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.server.UaStackServer;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcServerHttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UaStackServer stackServer;

    public OpcServerHttpRequestHandler(UaStackServer stackServer) {
        this.stackServer = stackServer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest httpRequest) throws Exception {
        SecurityPolicy securityPolicy = SecurityPolicy.None;

        String securityPolicyUri = httpRequest.headers().get("OPCUA-SecurityPolicy");
        if (securityPolicyUri != null) {
            securityPolicy = SecurityPolicy.fromUri(securityPolicyUri);
        }

        ServerSecureChannel secureChannel = new ServerSecureChannel();
        secureChannel.setChannelId(0L); // TODO shared id per endpoint URL / path?
        secureChannel.setSecurityPolicy(securityPolicy);

        if (securityPolicy == SecurityPolicy.None) {
            secureChannel.setMessageSecurityMode(MessageSecurityMode.None);
        } else {
            secureChannel.setMessageSecurityMode(MessageSecurityMode.Sign);
        }

        ByteBuf content = httpRequest.content();
        OpcUaBinaryStreamDecoder decoder = new OpcUaBinaryStreamDecoder(content);

        try {
            UaRequestMessage request = (UaRequestMessage) decoder.readMessage(null);
            UInteger requestHandle = request.getRequestHeader().getRequestHandle();

            // TODO nothing about this is correct
            ServiceRequest<UaRequestMessage, UaResponseMessage> serviceRequest = new ServiceRequest<UaRequestMessage, UaResponseMessage>(
                request,
                0,
                stackServer,
                secureChannel
            );

            serviceRequest.getFuture().whenComplete((response, fault) -> {
                if (response != null) {
                    sendServiceResponse(ctx, request, response);
                } else {
                    sendServiceFault(ctx, requestHandle, fault);
                }
            });

            stackServer.onServiceRequest(serviceRequest);
        } catch (Throwable t) {
            logger.error("Error decoding UaRequestMessage", t);

            sendServiceFault(ctx, null, t);

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

    private void sendServiceResponse(
        ChannelHandlerContext ctx,
        UaRequestMessage request,
        UaResponseMessage response) {

        // TODO

    }

    private void sendServiceFault(
        ChannelHandlerContext ctx,
        UInteger requestHandle,
        Throwable fault) {

        // TODO

    }

}
