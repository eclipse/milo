/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.server.transport.uasc;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.primitives.Ints;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.AttributeKey;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.ChannelParameters;
import org.eclipse.milo.opcua.stack.core.channel.ExceptionHandler;
import org.eclipse.milo.opcua.stack.core.channel.MessageLimits;
import org.eclipse.milo.opcua.stack.core.channel.SerializationQueue;
import org.eclipse.milo.opcua.stack.core.channel.headers.HeaderDecoder;
import org.eclipse.milo.opcua.stack.core.channel.messages.AcknowledgeMessage;
import org.eclipse.milo.opcua.stack.core.channel.messages.ErrorMessage;
import org.eclipse.milo.opcua.stack.core.channel.messages.HelloMessage;
import org.eclipse.milo.opcua.stack.core.channel.messages.MessageType;
import org.eclipse.milo.opcua.stack.core.channel.messages.TcpMessageDecoder;
import org.eclipse.milo.opcua.stack.core.channel.messages.TcpMessageEncoder;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.eclipse.milo.opcua.stack.server.UaStackServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UascServerHelloHandler extends ByteToMessageDecoder implements HeaderDecoder {

    static final AttributeKey<String> ENDPOINT_URL_KEY = AttributeKey.valueOf("endpoint-url");

    /**
     * Cumulative count of all connection rejections for the lifetime of the server.
     */
    @SuppressWarnings("WeakerAccess")
    public static final AtomicLong CUMULATIVE_DEADLINES_MISSED = new AtomicLong(0L);

    private static final int MAX_HELLO_MESSAGE_SIZE = 8 + 20 + 4 + 4096;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private volatile boolean receivedHello = false;

    private final UaStackServer stackServer;
    private final TransportProfile transportProfile;

    public UascServerHelloHandler(UaStackServer stackServer, TransportProfile transportProfile) {
        if (transportProfile != TransportProfile.TCP_UASC_UABINARY &&
            transportProfile != TransportProfile.WSS_UASC_UABINARY) {

            throw new IllegalArgumentException("transportProfile: " + transportProfile);
        }

        this.stackServer = stackServer;
        this.transportProfile = transportProfile;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int helloDeadlineMs = Stack.ConnectionLimits.HELLO_DEADLINE_MS;

        logger.debug("Scheduling Hello deadline for +" + helloDeadlineMs + "ms");

        ctx.executor().schedule(
            () -> {
                if (!receivedHello) {
                    long cumulativeDeadlinesMissed =
                        CUMULATIVE_DEADLINES_MISSED.incrementAndGet();

                    logger.debug("No Hello received after " +
                        helloDeadlineMs + "ms; closing channel. " +
                        "cumulativeDeadlinesMissed=" + cumulativeDeadlinesMissed);

                    ctx.close();
                }
            },
            helloDeadlineMs, TimeUnit.MILLISECONDS
        );

        super.channelActive(ctx);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        while (buffer.readableBytes() >= HEADER_LENGTH) {
            int messageLength = getMessageLength(buffer, MAX_HELLO_MESSAGE_SIZE);

            if (buffer.readableBytes() < messageLength) {
                break;
            }

            MessageType messageType = MessageType.fromMediumInt(buffer.getMediumLE(buffer.readerIndex()));

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

        receivedHello = true;

        final HelloMessage hello = TcpMessageDecoder.decodeHello(buffer);

        String endpointUrl = hello.getEndpointUrl();

        boolean endpointMatch = stackServer.getEndpointDescriptions()
            .stream()
            .anyMatch(endpoint ->
                Objects.equals(
                    EndpointUtil.getPath(endpointUrl),
                    EndpointUtil.getPath(endpoint.getEndpointUrl()))
            );

        if (!endpointMatch) {
            throw new UaException(
                StatusCodes.Bad_TcpEndpointUrlInvalid,
                "unrecognized endpoint url: " + endpointUrl);
        }

        ctx.channel().attr(ENDPOINT_URL_KEY).set(endpointUrl);

        long remoteProtocolVersion = hello.getProtocolVersion();
        long remoteReceiveBufferSize = hello.getReceiveBufferSize();
        long remoteSendBufferSize = hello.getSendBufferSize();
        long remoteMaxMessageSize = hello.getMaxMessageSize();
        long remoteMaxChunkCount = hello.getMaxChunkCount();

        if (remoteProtocolVersion < PROTOCOL_VERSION) {
            throw new UaException(StatusCodes.Bad_ProtocolVersionUnsupported,
                "unsupported protocol version: " + remoteProtocolVersion);
        }

        MessageLimits config = stackServer.getConfig().getMessageLimits();

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

        SerializationQueue serializationQueue = new SerializationQueue(
            stackServer.getConfig().getExecutor(),
            parameters,
            stackServer.getSerializationContext()
        );

        ctx.pipeline().addLast(new UascServerAsymmetricHandler(stackServer, transportProfile, serializationQueue));
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
