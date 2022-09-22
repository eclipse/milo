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
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.transport.server.ServerApplication;

public class UascServiceRequestHandler extends SimpleChannelInboundHandler<UascServiceRequest> {

    private final ServerApplication application;

    public UascServiceRequestHandler(ServerApplication application) {
        this.application = application;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UascServiceRequest serviceRequest) {
        CompletableFuture<UaResponseMessageType> future = application.handleServiceRequest(
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
            } else {
                StatusCode serviceResult = UaException.extractStatusCode(ex)
                    .orElse(new StatusCode(StatusCodes.Bad_UnexpectedError));

                var header = new ResponseHeader(
                    DateTime.now(),
                    serviceRequest.getRequestMessage().getRequestHeader().getRequestHandle(),
                    serviceResult,
                    DiagnosticInfo.NULL_VALUE,
                    null,
                    null
                );

                var serviceResponse = new UascServiceResponse(
                    new ServiceFault(header),
                    serviceRequest.getRequestId()
                );

                ctx.pipeline().writeAndFlush(serviceResponse);
            }
        }));
    }

}
