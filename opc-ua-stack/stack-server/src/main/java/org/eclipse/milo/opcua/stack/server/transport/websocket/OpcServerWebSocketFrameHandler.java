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

package org.eclipse.milo.opcua.stack.server.transport.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.eclipse.milo.opcua.stack.server.transport.http.OpcServerHttpChannelInitializer;
import org.eclipse.milo.opcua.stack.server.transport.uasc.UascServerHelloHandler;

import static io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler.HandshakeComplete;
import static org.eclipse.milo.opcua.stack.server.transport.SocketServerManager.SocketServer.ServerLookup;

public class OpcServerWebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    
    private String subprotocol;

    private final ServerLookup serverLookup;

    public OpcServerWebSocketFrameHandler(ServerLookup serverLookup) {
        this.serverLookup = serverLookup;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object event) throws Exception {
        if (event instanceof HandshakeComplete) {
            HandshakeComplete handshake = (HandshakeComplete) event;

            handshake.requestUri();
            handshake.requestHeaders();

            subprotocol = handshake.selectedSubprotocol();

            if (OpcServerHttpChannelInitializer.WS_PROTOCOL_JSON.equalsIgnoreCase(subprotocol)) {
                ctx.channel().pipeline().addLast(new UascServerHelloHandler(serverLookup));
            }
        }

        super.userEventTriggered(ctx, event);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        if (OpcServerHttpChannelInitializer.WS_PROTOCOL_BINARY.equalsIgnoreCase(subprotocol)) {
            // Pass the binary contents to the UA Secure Conversation handlers

            ctx.fireChannelRead(msg.content().retain());
        } else if (OpcServerHttpChannelInitializer.WS_PROTOCOL_JSON.equalsIgnoreCase(subprotocol)) {
            // TODO End of the pipeline; decode and deliver
            String text = ((TextWebSocketFrame) msg).text();

            throw new Exception("TODO");
        } else {
            ctx.close();
        }
    }

}
