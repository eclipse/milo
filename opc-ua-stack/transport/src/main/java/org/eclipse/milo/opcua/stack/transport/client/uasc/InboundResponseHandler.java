/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.client.uasc;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;

public abstract class InboundResponseHandler
    extends SimpleChannelInboundHandler<UascMessage.Response> implements ServiceResponseHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UascMessage.Response response) throws Exception {
        if (response.isSuccess()) {
            handleResponse(response.getRequestId(), response.getResponseMessage());
        } else {
            handleReceiveFailure(response.getRequestId(), response.getException());
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof UascMessage.Response) {
            UascMessage.Response response = (UascMessage.Response) evt;
            assert response.isFailure();

            handleSendFailure(response.getRequestId(), response.getException());
        }

        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // TODO handleChannelInactive? subclass needs to cancel pending requests
        handleChannelInactive();

        super.channelInactive(ctx);
    }

    public static class DelegatingResponseHandler extends InboundResponseHandler {

        private final ServiceResponseHandler delegate;

        public DelegatingResponseHandler(ServiceResponseHandler delegate) {
            this.delegate = delegate;
        }

        @Override
        public void handleResponse(long requestId, UaResponseMessageType responseMessage) {
            delegate.handleResponse(requestId, responseMessage);
        }

        @Override
        public void handleSendFailure(long requestId, UaException exception) {
            delegate.handleSendFailure(requestId, exception);
        }

        @Override
        public void handleReceiveFailure(long requestId, UaException exception) {
            delegate.handleReceiveFailure(requestId, exception);
        }

        @Override
        public void handleChannelInactive() {
            delegate.handleChannelInactive();
        }

    }

}
