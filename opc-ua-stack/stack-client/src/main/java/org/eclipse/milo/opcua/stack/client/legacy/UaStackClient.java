///*
// * Copyright (c) 2018 Kevin Herron
// *
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the Eclipse Public License v1.0
// * and Eclipse Distribution License v1.0 which accompany this distribution.
// *
// * The Eclipse Public License is available at
// *   http://www.eclipse.org/legal/epl-v10.html
// * and the Eclipse Distribution License is available at
// *   http://www.eclipse.org/org/documents/edl-v10.html.
// */
//
//package org.eclipse.milo.opcua.stack.client.legacy;
//
//import java.util.concurrent.CompletableFuture;
//
//import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
//import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
//import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
//import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
//import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
//import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
//
//public interface UaStackClient {
//
//    UaStackClientConfig getConfig();
//
//    CompletableFuture<UaStackClient> connect();
//
//    CompletableFuture<UaStackClient> disconnect();
//
//    /**
//     * Build a new {@link RequestHeader} using a null authentication token and the specified {@code requestTimeout}.
//     *
//     * @param authToken      the session authentication token to use.
//     * @param requestTimeout the request timeout to use.
//     * @return a new {@link RequestHeader} with a null authentication token and a custom request timeout.
//     */
//    RequestHeader newRequestHeader(NodeId authToken, UInteger requestTimeout);
//
//    /**
//     * Send a {@link UaRequestMessage}, returning a {@link CompletableFuture} representing the result of the operation.
//     *
//     * @param request the {@link UaRequestMessage} to send.
//     * @return a {@link CompletableFuture} representing the result of the operation.
//     */
//    CompletableFuture<UaResponseMessage> sendRequest(UaRequestMessage request);
//
//}
