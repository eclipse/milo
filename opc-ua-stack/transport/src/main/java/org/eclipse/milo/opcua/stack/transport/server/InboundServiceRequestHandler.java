///*
// * Copyright (c) 2022 the Eclipse Milo Authors
// *
// * This program and the accompanying materials are made
// * available under the terms of the Eclipse Public License 2.0
// * which is available at https://www.eclipse.org/legal/epl-2.0/
// *
// * SPDX-License-Identifier: EPL-2.0
// */
//
//package org.eclipse.milo.opcua.stack.transport.server;
//
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//
//public abstract class InboundServiceRequestHandler
//    extends SimpleChannelInboundHandler<ServiceRequest> implements ServiceRequestHandler {
//
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, ServiceRequest serviceRequest) throws Exception {
//        handleRequest(serviceRequest);
//    }
//
//    public static class DelegatingServiceRequestHandler extends InboundServiceRequestHandler {
//
//        private final ServiceRequestHandler delegate;
//
//        public DelegatingServiceRequestHandler(ServiceRequestHandler delegate) {
//            this.delegate = delegate;
//        }
//
//        @Override
//        public void handleRequest(ServiceRequest serviceRequest) {
//            delegate.handleRequest(serviceRequest);
//        }
//
//    }
//
//}
