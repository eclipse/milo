/*
 * Copyright (c) 2016 Kevin Herron
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

package org.eclipse.milo.opcua.stack.server.handlers;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.google.common.primitives.Ints;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.AttributeKey;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.channel.ChannelParameters;
import org.eclipse.milo.opcua.stack.core.channel.ExceptionHandler;
import org.eclipse.milo.opcua.stack.core.channel.SerializationQueue;
import org.eclipse.milo.opcua.stack.core.channel.headers.HeaderDecoder;
import org.eclipse.milo.opcua.stack.core.channel.messages.AcknowledgeMessage;
import org.eclipse.milo.opcua.stack.core.channel.messages.ErrorMessage;
import org.eclipse.milo.opcua.stack.core.channel.messages.HelloMessage;
import org.eclipse.milo.opcua.stack.core.channel.messages.MessageType;
import org.eclipse.milo.opcua.stack.core.channel.messages.TcpMessageDecoder;
import org.eclipse.milo.opcua.stack.core.channel.messages.TcpMessageEncoder;
import org.eclipse.milo.opcua.stack.server.tcp.UaTcpStackServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UaTcpServerHelloHandler extends ByteToMessageDecoder implements HeaderDecoder {

    public static final AttributeKey<String> ENDPOINT_URL_KEY = AttributeKey.valueOf("endpoint-url");

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Function<String, Optional<UaTcpStackServer>> serverLookup;

    public UaTcpServerHelloHandler(Function<String, Optional<UaTcpStackServer>> serverLookup) {
        this.serverLookup = serverLookup;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        buffer = buffer.order(ByteOrder.LITTLE_ENDIAN);

        while (buffer.readableBytes() >= HEADER_LENGTH &&
            buffer.readableBytes() >= getMessageLength(buffer)) {

            int messageLength = getMessageLength(buffer);
            MessageType messageType = MessageType.fromMediumInt(buffer.getMedium(buffer.readerIndex()));

            switch (messageType) {
                case Hello:
                    onHello(ctx, buffer.readSlice(messageLength));
                    break;

                default:
                    throw new UaException(StatusCodes.Bad_TcpMessageTypeInvalid,
                        "unexpected MessageType: " + messageType);
            }
        }
    }

    private void onHello(ChannelHandlerContext ctx, ByteBuf buffer) throws UaException {
        logger.debug("[remote={}] Received Hello message.", ctx.channel().remoteAddress());

        final HelloMessage hello = TcpMessageDecoder.decodeHello(buffer);

        UaTcpStackServer server = serverLookup.apply(hello.getEndpointUrl()).orElseThrow(
            () -> new UaException(
                StatusCodes.Bad_TcpEndpointUrlInvalid,
                "unrecognized endpoint url: " + hello.getEndpointUrl())
        );

        ctx.channel().attr(ENDPOINT_URL_KEY).set(hello.getEndpointUrl());

        long remoteProtocolVersion = hello.getProtocolVersion();
        long remoteReceiveBufferSize = hello.getReceiveBufferSize();
        long remoteSendBufferSize = hello.getSendBufferSize();
        long remoteMaxMessageSize = hello.getMaxMessageSize();
        long remoteMaxChunkCount = hello.getMaxChunkCount();

        if (remoteProtocolVersion < PROTOCOL_VERSION) {
            throw new UaException(StatusCodes.Bad_ProtocolVersionUnsupported,
                "unsupported protocol version: " + remoteProtocolVersion);
        }

        ChannelConfig config = server.getChannelConfig();

        /* Our receive buffer size is determined by the remote send buffer size. */
        long localReceiveBufferSize = Math.min(remoteSendBufferSize, config.getMaxChunkSize());

        /* Our send buffer size is determined by the remote receive buffer size. */
        long localSendBufferSize = Math.min(remoteReceiveBufferSize, config.getMaxChunkSize());

        /* Max chunk count the remote can send us; not influenced by remote configuration. */
        long localMaxChunkCount = config.getMaxChunkCount();

        /* Max message size the remote can send us. Determined by our max chunk count and receive buffer size. */
        long localMaxMessageSize = Math.min(localReceiveBufferSize * localMaxChunkCount, config.getMaxMessageSize());

        ChannelParameters parameters = new ChannelParameters(
            Ints.saturatedCast(localMaxMessageSize),
            Ints.saturatedCast(localReceiveBufferSize),
            Ints.saturatedCast(localSendBufferSize),
            Ints.saturatedCast(localMaxChunkCount),
            Ints.saturatedCast(remoteMaxMessageSize),
            Ints.saturatedCast(remoteReceiveBufferSize),
            Ints.saturatedCast(remoteSendBufferSize),
            Ints.saturatedCast(remoteMaxChunkCount)
        );

        int maxArrayLength = config.getMaxArrayLength();
        int maxStringLength = config.getMaxStringLength();

        SerializationQueue serializationQueue = new SerializationQueue(
            server.getConfig().getExecutor(),
            parameters,
            maxArrayLength,
            maxStringLength
        );

        ctx.pipeline().addLast(new UaTcpServerAsymmetricHandler(server, serializationQueue));
        ctx.pipeline().remove(this);

        logger.debug("[remote={}] Removed HelloHandler, added AsymmetricHandler.", ctx.channel().remoteAddress());

        AcknowledgeMessage acknowledge = new AcknowledgeMessage(
            PROTOCOL_VERSION,
            localReceiveBufferSize,
            localSendBufferSize,
            localMaxMessageSize,
            localMaxChunkCount
        );

        ByteBuf messageBuffer = TcpMessageEncoder.encode(acknowledge);

        // Using ctx.executor() is necessary to ensure this handler is removed
        // before the message can be written and another response arrives.
        ctx.executor().execute(() -> ctx.writeAndFlush(messageBuffer));

        logger.debug("[remote={}] Sent Acknowledge message.", ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof IOException) {
            ctx.close();
            logger.debug("[remote={}] IOException caught; channel closed");
        } else {
            ErrorMessage errorMessage = ExceptionHandler.sendErrorMessage(ctx, cause);

            if (cause instanceof UaException) {
                logger.debug("[remote={}] UaException caught; sent {}",
                    ctx.channel().remoteAddress(), errorMessage, cause);
            } else {
                logger.error("[remote={}] Exception caught; sent {}",
                    ctx.channel().remoteAddress(), errorMessage, cause);
            }
        }
    }

}
