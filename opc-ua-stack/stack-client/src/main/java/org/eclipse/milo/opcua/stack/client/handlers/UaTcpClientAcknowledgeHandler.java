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

package org.eclipse.milo.opcua.stack.client.handlers;

import java.nio.ByteOrder;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import com.google.common.primitives.Ints;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.util.AttributeKey;
import io.netty.util.Timeout;
import org.eclipse.milo.opcua.stack.client.UaTcpStackClient;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.channel.ChannelParameters;
import org.eclipse.milo.opcua.stack.core.channel.ClientSecureChannel;
import org.eclipse.milo.opcua.stack.core.channel.SerializationQueue;
import org.eclipse.milo.opcua.stack.core.channel.headers.HeaderDecoder;
import org.eclipse.milo.opcua.stack.core.channel.messages.AcknowledgeMessage;
import org.eclipse.milo.opcua.stack.core.channel.messages.ErrorMessage;
import org.eclipse.milo.opcua.stack.core.channel.messages.HelloMessage;
import org.eclipse.milo.opcua.stack.core.channel.messages.MessageType;
import org.eclipse.milo.opcua.stack.core.channel.messages.TcpMessageDecoder;
import org.eclipse.milo.opcua.stack.core.channel.messages.TcpMessageEncoder;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UaTcpClientAcknowledgeHandler extends ByteToMessageCodec<UaRequestFuture> implements HeaderDecoder {

    public static final AttributeKey<List<UaRequestFuture>> KEY_AWAITING_HANDSHAKE =
        AttributeKey.valueOf("awaiting-handshake");

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<UaRequestFuture> awaitingHandshake = new CopyOnWriteArrayList<>();

    private volatile Timeout helloTimeout;


    private final UaTcpStackClient client;
    private final ClientSecureChannel secureChannel;
    private final CompletableFuture<ClientSecureChannel> handshakeFuture;

    public UaTcpClientAcknowledgeHandler(UaTcpStackClient client,
                                         ClientSecureChannel secureChannel,
                                         CompletableFuture<ClientSecureChannel> handshakeFuture) {

        this.client = client;
        this.secureChannel = secureChannel;
        this.handshakeFuture = handshakeFuture;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        helloTimeout = startHelloTimeout(ctx);

        secureChannel.setChannel(ctx.channel());

        HelloMessage hello = new HelloMessage(
            PROTOCOL_VERSION,
            client.getChannelConfig().getMaxChunkSize(),
            client.getChannelConfig().getMaxChunkSize(),
            client.getChannelConfig().getMaxMessageSize(),
            client.getChannelConfig().getMaxChunkCount(),
            client.getEndpointUrl());

        ByteBuf messageBuffer = TcpMessageEncoder.encode(hello);

        ctx.writeAndFlush(messageBuffer, ctx.voidPromise());

        logger.debug("Sent Hello message on channel={}.", ctx.channel());

        super.channelActive(ctx);
    }

    private Timeout startHelloTimeout(ChannelHandlerContext ctx) {
        long acknowledgeTimeoutMs = client.getConfig().getAcknowledgeTimeout().longValue();

        return client.getConfig().getWheelTimer().newTimeout(
            timeout -> {
                if (!timeout.isCancelled()) {
                    handshakeFuture.completeExceptionally(
                        new UaException(StatusCodes.Bad_Timeout,
                            "timed out waiting for acknowledge"));
                    ctx.close();
                }
            },
            acknowledgeTimeoutMs, TimeUnit.MILLISECONDS
        );
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, UaRequestFuture message, ByteBuf out) throws Exception {
        awaitingHandshake.add(message);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        buffer = buffer.order(ByteOrder.LITTLE_ENDIAN);

        while (buffer.readableBytes() >= HEADER_LENGTH &&
            buffer.readableBytes() >= getMessageLength(buffer)) {

            int messageLength = getMessageLength(buffer);
            MessageType messageType = MessageType.fromMediumInt(buffer.getMedium(buffer.readerIndex()));

            switch (messageType) {
                case Acknowledge:
                    onAcknowledge(ctx, buffer.readSlice(messageLength));
                    break;

                case Error:
                    onError(ctx, buffer.readSlice(messageLength));
                    break;

                default:
                    out.add(buffer.readSlice(messageLength).retain());
            }
        }
    }

    private void onAcknowledge(ChannelHandlerContext ctx, ByteBuf buffer) {
        if (helloTimeout != null && !helloTimeout.cancel()) {
            helloTimeout = null;
            handshakeFuture.completeExceptionally(
                new UaException(StatusCodes.Bad_Timeout,
                    "timed out waiting for acknowledge"));
            ctx.close();
            return;
        }

        logger.debug("Received Acknowledge message on channel={}.", ctx.channel());

        buffer.skipBytes(3 + 1 + 4); // Skip messageType, chunkType, and messageSize

        AcknowledgeMessage acknowledge = AcknowledgeMessage.decode(buffer);

        long remoteProtocolVersion = acknowledge.getProtocolVersion();
        long remoteReceiveBufferSize = acknowledge.getReceiveBufferSize();
        long remoteSendBufferSize = acknowledge.getSendBufferSize();
        long remoteMaxMessageSize = acknowledge.getMaxMessageSize();
        long remoteMaxChunkCount = acknowledge.getMaxChunkCount();

        if (PROTOCOL_VERSION > remoteProtocolVersion) {
            logger.warn("Client protocol version ({}) does not match server protocol version ({}).",
                PROTOCOL_VERSION, remoteProtocolVersion);
        }

        ChannelConfig config = client.getChannelConfig();

        /* Our receive buffer size is determined by the remote send buffer size. */
        long localReceiveBufferSize = Math.min(remoteSendBufferSize, config.getMaxChunkSize());

        /* Our send buffer size is determined by the remote receive buffer size. */
        long localSendBufferSize = Math.min(remoteReceiveBufferSize, config.getMaxChunkSize());

        /* Max message size the remote can send us; not influenced by remote configuration. */
        long localMaxMessageSize = config.getMaxMessageSize();

        /* Max chunk count the remote can send us; not influenced by remote configuration. */
        long localMaxChunkCount = config.getMaxChunkCount();

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

        ctx.channel().attr(KEY_AWAITING_HANDSHAKE).set(awaitingHandshake);

        ctx.executor().execute(() -> {
            int maxArrayLength = client.getChannelConfig().getMaxArrayLength();
            int maxStringLength = client.getChannelConfig().getMaxStringLength();

            SerializationQueue serializationQueue = new SerializationQueue(
                client.getConfig().getExecutor(),
                parameters,
                maxArrayLength,
                maxStringLength
            );

            UaTcpClientMessageHandler handler = new UaTcpClientMessageHandler(
                client,
                secureChannel,
                serializationQueue,
                handshakeFuture
            );

            ctx.pipeline().addFirst(handler);
        });
    }

    private void onError(ChannelHandlerContext ctx, ByteBuf buffer) {
        try {
            ErrorMessage errorMessage = TcpMessageDecoder.decodeError(buffer);
            StatusCode statusCode = errorMessage.getError();

            logger.error(
                "[remote={}] Received error message: {}",
                ctx.channel().remoteAddress(), errorMessage);

            handshakeFuture.completeExceptionally(new UaException(statusCode, errorMessage.getReason()));
        } catch (UaException e) {
            logger.error(
                "[remote={}] An exception occurred while decoding an error message: {}",
                ctx.channel().remoteAddress(), e.getMessage(), e);

            handshakeFuture.completeExceptionally(e);
        } finally {
            ctx.close();
        }
    }

}
