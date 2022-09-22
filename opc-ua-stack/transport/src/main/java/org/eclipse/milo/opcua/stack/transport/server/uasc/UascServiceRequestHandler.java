/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.server.uasc;

import java.util.concurrent.CompletableFuture;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;

public class UascServiceRequestHandler extends SimpleChannelInboundHandler<UascServiceRequest> {

    private final UascServerConfig config;

    public UascServiceRequestHandler(UascServerConfig config) {
        this.config = config;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UascServiceRequest serviceRequest) {
        CompletableFuture<UaResponseMessageType> future = config.getServiceInterface().handleServiceRequest(
            serviceRequest,
            serviceRequest.getRequestMessage()
        );

        future.whenComplete(((response, ex) -> {
            if (response != null) {
                var serviceResponse = new UascServiceResponse(
                    response,
                    serviceRequest.getRequestId()
                );

                ctx.pipeline().writeAndFlush(serviceResponse);
            }
        }));
    }

}
