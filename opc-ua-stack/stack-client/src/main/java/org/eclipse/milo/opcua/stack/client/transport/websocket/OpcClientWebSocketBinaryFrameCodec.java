/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.client.transport.websocket;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.client.transport.uasc.ClientSecureChannel;
import org.eclipse.milo.opcua.stack.client.transport.uasc.UascClientAcknowledgeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler.ClientHandshakeStateEvent;

public class OpcClientWebSocketBinaryFrameCodec extends MessageToMessageCodec<WebSocketFrame, ByteBuf> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UaStackClient client;
    private final CompletableFuture<ClientSecureChannel> handshake;

    public OpcClientWebSocketBinaryFrameCodec(
        UaStackClient client,
        CompletableFuture<ClientSecureChannel> handshake) {

        this.client = client;
        this.handshake = handshake;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object event) throws Exception {
        if (event instanceof ClientHandshakeStateEvent) {
            logger.debug("WebSocket handshake event: " + event);

            if (event == ClientHandshakeStateEvent.HANDSHAKE_COMPLETE) {
                ctx.pipeline().addLast(new UascClientAcknowledgeHandler(client, handshake));
            }
        }
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) {
        out.add(new BinaryWebSocketFrame(msg.retain()));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) {
        if (msg instanceof BinaryWebSocketFrame) {
            out.add(msg.content().retain());
        } else if (msg instanceof TextWebSocketFrame) {
            TextWebSocketFrame textFrame = (TextWebSocketFrame) msg;

            logger.error("Received WebSocket frame:\n" + textFrame.text());
            ctx.close();
        }
    }

}
