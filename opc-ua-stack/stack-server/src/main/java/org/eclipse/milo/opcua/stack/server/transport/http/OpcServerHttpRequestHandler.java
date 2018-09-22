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
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.util.BufferUtil;
import org.eclipse.milo.opcua.stack.server.UaStackServer;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpcServerHttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final String UABINARY_CONTENT_TYPE =
        HttpHeaderValues.APPLICATION_OCTET_STREAM.toString();

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

            ServiceRequest serviceRequest = new ServiceRequest(
                request,
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

            stackServer.onServiceRequest(httpRequest.uri(), serviceRequest);
        } catch (Throwable t) {
            logger.error("Error decoding UaRequestMessage", t);

            sendServiceFault(ctx, null, t);
        }
    }

    private void sendServiceResponse(
        ChannelHandlerContext ctx,
        UaRequestMessage request,
        UaResponseMessage response) {

        ByteBuf contentBuffer = BufferUtil.pooledBuffer();

        // TODO switch on transport profile for binary vs xml encoding
        OpcUaBinaryStreamEncoder binaryEncoder = new OpcUaBinaryStreamEncoder();
        binaryEncoder.setBuffer(contentBuffer);
        binaryEncoder.writeMessage(null, response);

        FullHttpResponse httpResponse = new DefaultFullHttpResponse(
            HttpVersion.HTTP_1_1,
            HttpResponseStatus.OK,
            contentBuffer
        );

        httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, UABINARY_CONTENT_TYPE);
        httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, contentBuffer.readableBytes());

        ctx.writeAndFlush(httpResponse);
    }

    private void sendServiceFault(
        ChannelHandlerContext ctx,
        UInteger requestHandle,
        Throwable fault) {

        // TODO
    }

}
