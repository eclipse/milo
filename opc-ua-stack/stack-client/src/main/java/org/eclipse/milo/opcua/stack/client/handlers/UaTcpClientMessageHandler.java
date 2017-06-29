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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.util.AttributeKey;
import io.netty.util.Timeout;
import org.eclipse.milo.opcua.stack.client.UaTcpStackClient;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.UaServiceFaultException;
import org.eclipse.milo.opcua.stack.core.channel.ChannelSecurity;
import org.eclipse.milo.opcua.stack.core.channel.ClientSecureChannel;
import org.eclipse.milo.opcua.stack.core.channel.MessageAbortedException;
import org.eclipse.milo.opcua.stack.core.channel.SerializationQueue;
import org.eclipse.milo.opcua.stack.core.channel.headers.AsymmetricSecurityHeader;
import org.eclipse.milo.opcua.stack.core.channel.headers.HeaderDecoder;
import org.eclipse.milo.opcua.stack.core.channel.messages.ErrorMessage;
import org.eclipse.milo.opcua.stack.core.channel.messages.MessageType;
import org.eclipse.milo.opcua.stack.core.channel.messages.TcpMessageDecoder;
import org.eclipse.milo.opcua.stack.core.security.SecurityAlgorithm;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.SecurityTokenRequestType;
import org.eclipse.milo.opcua.stack.core.types.structured.ChannelSecurityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSecureChannelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.OpenSecureChannelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.OpenSecureChannelResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.core.util.BufferUtil;
import org.eclipse.milo.opcua.stack.core.util.LongSequence;
import org.eclipse.milo.opcua.stack.core.util.NonceUtil;
import org.jooq.lambda.tuple.Tuple2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class UaTcpClientMessageHandler extends ByteToMessageCodec<UaRequestFuture> implements HeaderDecoder {

    public static final AttributeKey<Map<Long, UaRequestFuture>> KEY_PENDING_REQUEST_FUTURES =
        AttributeKey.valueOf("pending-request-futures");

    public static final int SECURE_CHANNEL_TIMEOUT_SECONDS = 10;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private List<ByteBuf> chunkBuffers = new LinkedList<>();

    private final AtomicReference<AsymmetricSecurityHeader> headerRef = new AtomicReference<>();

    private ScheduledFuture renewFuture;
    private Timeout secureChannelTimeout;

    private final Map<Long, UaRequestFuture> pending;
    private final LongSequence requestIdSequence;

    private final UaTcpStackClient client;
    private final ClientSecureChannel secureChannel;
    private final SerializationQueue serializationQueue;
    private final CompletableFuture<ClientSecureChannel> handshakeFuture;

    public UaTcpClientMessageHandler(
        UaTcpStackClient client,
        ClientSecureChannel secureChannel,
        SerializationQueue serializationQueue,
        CompletableFuture<ClientSecureChannel> handshakeFuture) {

        this.client = client;
        this.secureChannel = secureChannel;
        this.serializationQueue = serializationQueue;
        this.handshakeFuture = handshakeFuture;

        secureChannel
            .attr(KEY_PENDING_REQUEST_FUTURES)
            .setIfAbsent(Maps.newConcurrentMap());

        pending = secureChannel.attr(KEY_PENDING_REQUEST_FUTURES).get();

        secureChannel
            .attr(ClientSecureChannel.KEY_REQUEST_ID_SEQUENCE)
            .setIfAbsent(new LongSequence(1L, UInteger.MAX_VALUE));

        requestIdSequence = secureChannel.attr(ClientSecureChannel.KEY_REQUEST_ID_SEQUENCE).get();

        handshakeFuture.thenAccept(sc -> {
            Channel channel = sc.getChannel();

            channel.eventLoop().execute(() -> {
                List<UaRequestFuture> awaitingHandshake = channel.attr(
                    UaTcpClientAcknowledgeHandler.KEY_AWAITING_HANDSHAKE).get();

                if (awaitingHandshake != null) {
                    channel.attr(UaTcpClientAcknowledgeHandler.KEY_AWAITING_HANDSHAKE).remove();

                    logger.debug(
                        "{} message(s) queued before handshake completed; sending now.",
                        awaitingHandshake.size());

                    awaitingHandshake.forEach(channel::writeAndFlush);
                }
            });
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        SecurityTokenRequestType requestType = secureChannel.getChannelId() == 0 ?
            SecurityTokenRequestType.Issue : SecurityTokenRequestType.Renew;

        secureChannelTimeout = client.getConfig().getWheelTimer().newTimeout(
            timeout -> {
                if (!timeout.isCancelled()) {
                    handshakeFuture.completeExceptionally(
                        new UaException(
                            StatusCodes.Bad_Timeout,
                            "timed out waiting for secure channel"));
                    ctx.close();
                }
            },
            SECURE_CHANNEL_TIMEOUT_SECONDS, TimeUnit.SECONDS
        );

        logger.debug("OpenSecureChannel timeout scheduled for +5s");

        sendOpenSecureChannelRequest(ctx, requestType);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (renewFuture != null) renewFuture.cancel(false);

        handshakeFuture.completeExceptionally(
            new UaException(StatusCodes.Bad_ConnectionClosed, "connection closed"));

        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error(
            "[remote={}] Exception caught: {}",
            ctx.channel().remoteAddress(), cause.getMessage(), cause);

        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof CloseSecureChannelRequest) {
            sendCloseSecureChannelRequest(ctx, (CloseSecureChannelRequest) evt);
        }
    }

    private void sendOpenSecureChannelRequest(ChannelHandlerContext ctx, SecurityTokenRequestType requestType) {
        SecurityAlgorithm algorithm = secureChannel.getSecurityPolicy().getSymmetricEncryptionAlgorithm();
        int nonceLength = NonceUtil.getNonceLength(algorithm);

        ByteString clientNonce = secureChannel.isSymmetricSigningEnabled() ?
            NonceUtil.generateNonce(nonceLength) :
            ByteString.NULL_VALUE;

        secureChannel.setLocalNonce(clientNonce);

        OpenSecureChannelRequest request = new OpenSecureChannelRequest(
            new RequestHeader(null, DateTime.now(), uint(0), uint(0), null, uint(0), null),
            uint(PROTOCOL_VERSION),
            requestType,
            secureChannel.getMessageSecurityMode(),
            secureChannel.getLocalNonce(),
            client.getChannelLifetime());

        encodeMessage(request, MessageType.OpenSecureChannel).whenComplete((t2, ex) -> {
            if (ex != null) {
                ctx.close();
                return;
            }

            List<ByteBuf> chunks = t2.v2();

            ctx.executor().execute(() -> {
                chunks.forEach(c -> ctx.write(c, ctx.voidPromise()));
                ctx.flush();
            });

            ChannelSecurity channelSecurity = secureChannel.getChannelSecurity();

            long currentTokenId = channelSecurity != null ?
                channelSecurity.getCurrentToken().getTokenId().longValue() : -1L;

            long previousTokenId = channelSecurity != null ?
                channelSecurity.getPreviousToken().map(token -> token.getTokenId().longValue()).orElse(-1L) : -1L;

            logger.debug(
                "Sent OpenSecureChannelRequest ({}, id={}, currentToken={}, previousToken={}).",
                request.getRequestType(), secureChannel.getChannelId(), currentTokenId, previousTokenId);
        });
    }

    private void sendCloseSecureChannelRequest(ChannelHandlerContext ctx, CloseSecureChannelRequest request) {
        encodeMessage(request, MessageType.CloseSecureChannel).whenComplete((t2, ex) -> {
            if (ex != null) {
                ctx.close();
                return;
            }

            List<ByteBuf> chunks = t2.v2();

            ctx.executor().execute(() -> {
                chunks.forEach(c -> ctx.write(c, ctx.voidPromise()));
                ctx.flush();
                ctx.close();
            });

            secureChannel.setChannelId(0);
        });
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, UaRequestFuture request, ByteBuf buffer) throws Exception {
        encodeMessage(request.getRequest(), MessageType.SecureMessage).whenComplete((t2, ex) -> {
            if (ex != null) {
                ctx.close();
                return;
            }

            long requestId = t2.v1();
            List<ByteBuf> chunks = t2.v2();

            pending.put(requestId, request);

            // No matter how we complete, make sure the entry in pending is removed.
            // This covers the case where the request fails due to a timeout in the
            // upper layers as well as normal completion.
            request.getFuture().whenComplete((r, x) -> pending.remove(requestId));

            ctx.executor().execute(() -> {
                chunks.forEach(c -> ctx.write(c, ctx.voidPromise()));
                ctx.flush();
            });
        });
    }

    private CompletableFuture<Tuple2<Long, List<ByteBuf>>> encodeMessage(
        UaRequestMessage request,
        MessageType messageType) {

        CompletableFuture<Tuple2<Long, List<ByteBuf>>> future = new CompletableFuture<>();

        serializationQueue.encode((writer, chunkEncoder) -> {
            ByteBuf messageBuffer = null;

            try {
                messageBuffer = BufferUtil.buffer();
                writer.setBuffer(messageBuffer);

                writer.writeMessage(null, request);

                List<ByteBuf> chunks;

                if (messageType == MessageType.OpenSecureChannel) {
                    chunks = chunkEncoder.encodeAsymmetric(
                        secureChannel,
                        messageType,
                        messageBuffer,
                        requestIdSequence.getAndIncrement()
                    );
                } else {
                    chunks = chunkEncoder.encodeSymmetric(
                        secureChannel,
                        messageType,
                        messageBuffer,
                        requestIdSequence.getAndIncrement()
                    );
                }

                future.complete(new Tuple2<>(chunkEncoder.getLastRequestId(), chunks));
            } catch (UaException ex) {
                logger.error("Error encoding {}: {}", request, ex.getMessage(), ex);

                future.completeExceptionally(ex);
            } finally {
                if (messageBuffer != null) {
                    messageBuffer.release();
                }
            }
        });

        return future;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        if (buffer.readableBytes() >= HEADER_LENGTH) {
            buffer = buffer.order(ByteOrder.LITTLE_ENDIAN);

            if (buffer.readableBytes() >= getMessageLength(buffer)) {
                decodeMessage(ctx, buffer);
            }
        }
    }

    private void decodeMessage(ChannelHandlerContext ctx, ByteBuf buffer) throws UaException {
        int messageLength = getMessageLength(buffer);
        MessageType messageType = MessageType.fromMediumInt(buffer.getMedium(buffer.readerIndex()));

        switch (messageType) {
            case OpenSecureChannel:
                onOpenSecureChannel(ctx, buffer.readSlice(messageLength));
                break;

            case SecureMessage:
                onSecureMessage(ctx, buffer.readSlice(messageLength));
                break;

            case Error:
                onError(ctx, buffer.readSlice(messageLength));
                break;

            default:
                throw new UaException(
                    StatusCodes.Bad_TcpMessageTypeInvalid,
                    "unexpected MessageType: " + messageType);
        }
    }

    private boolean accumulateChunk(ByteBuf buffer) throws UaException {
        int maxChunkCount = serializationQueue.getParameters().getLocalMaxChunkCount();
        int maxChunkSize = serializationQueue.getParameters().getLocalReceiveBufferSize();

        int chunkSize = buffer.readerIndex(0).readableBytes();

        if (chunkSize > maxChunkSize) {
            throw new UaException(StatusCodes.Bad_TcpMessageTooLarge,
                String.format("max chunk size exceeded (%s)", maxChunkSize));
        }

        chunkBuffers.add(buffer.retain());

        if (chunkBuffers.size() > maxChunkCount) {
            throw new UaException(StatusCodes.Bad_TcpMessageTooLarge,
                String.format("max chunk count exceeded (%s)", maxChunkCount));
        }

        char chunkType = (char) buffer.getByte(3);

        return (chunkType == 'A' || chunkType == 'F');
    }

    private void onOpenSecureChannel(ChannelHandlerContext ctx, ByteBuf buffer) throws UaException {
        if (secureChannelTimeout != null) {
            if (secureChannelTimeout.cancel()) {
                logger.debug("OpenSecureChannel timeout canceled");

                secureChannelTimeout = null;
            } else {
                logger.warn("timed out waiting for secure channel");

                handshakeFuture.completeExceptionally(
                    new UaException(StatusCodes.Bad_Timeout,
                        "timed out waiting for secure channel"));
                ctx.close();
                return;
            }
        }

        buffer.skipBytes(3 + 1 + 4 + 4); // skip messageType, chunkType, messageSize, secureChannelId

        AsymmetricSecurityHeader securityHeader = AsymmetricSecurityHeader.decode(buffer);
        if (!headerRef.compareAndSet(null, securityHeader)) {
            if (!securityHeader.equals(headerRef.get())) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed,
                    "subsequent AsymmetricSecurityHeader did not match");
            }
        }

        if (accumulateChunk(buffer)) {
            final List<ByteBuf> buffersToDecode = ImmutableList.copyOf(chunkBuffers);
            chunkBuffers = new LinkedList<>();

            serializationQueue.decode((reader, chunkDecoder) -> {
                ByteBuf decodedBuffer = null;

                try {
                    decodedBuffer = chunkDecoder.decodeAsymmetric(secureChannel, buffersToDecode);

                    reader.setBuffer(decodedBuffer);

                    UaResponseMessage response = (UaResponseMessage) reader.readMessage(null);

                    StatusCode serviceResult = response.getResponseHeader().getServiceResult();

                    if (serviceResult.isGood()) {
                        OpenSecureChannelResponse oscr = (OpenSecureChannelResponse) response;

                        secureChannel.setChannelId(oscr.getSecurityToken().getChannelId().longValue());
                        logger.debug("Received OpenSecureChannelResponse.");

                        installSecurityToken(ctx, oscr);

                        handshakeFuture.complete(secureChannel);
                    } else {
                        ServiceFault serviceFault = (response instanceof ServiceFault) ?
                            (ServiceFault) response :
                            new ServiceFault(response.getResponseHeader());

                        throw new UaServiceFaultException(serviceFault);
                    }
                } catch (MessageAbortedException e) {
                    logger.error(
                        "Received message abort chunk; error={}, reason={}",
                        e.getStatusCode(), e.getMessage());

                    ctx.close();
                } catch (Throwable t) {
                    logger.error("Error decoding OpenSecureChannelResponse: {}", t.getMessage(), t);
                    ctx.close();
                } finally {
                    if (decodedBuffer != null) {
                        decodedBuffer.release();
                    }
                }
            });
        }
    }

    private void installSecurityToken(ChannelHandlerContext ctx, OpenSecureChannelResponse response) {
        ChannelSecurity.SecuritySecrets newKeys = null;
        if (response.getServerProtocolVersion().longValue() < PROTOCOL_VERSION) {
            throw new UaRuntimeException(StatusCodes.Bad_ProtocolVersionUnsupported,
                "server protocol version unsupported: " + response.getServerProtocolVersion());
        }

        ChannelSecurityToken newToken = response.getSecurityToken();

        if (secureChannel.isSymmetricSigningEnabled()) {
            secureChannel.setRemoteNonce(response.getServerNonce());

            newKeys = ChannelSecurity.generateKeyPair(
                secureChannel,
                secureChannel.getLocalNonce(),
                secureChannel.getRemoteNonce()
            );
        }

        ChannelSecurity oldSecrets = secureChannel.getChannelSecurity();
        ChannelSecurity.SecuritySecrets oldKeys = oldSecrets != null ? oldSecrets.getCurrentKeys() : null;
        ChannelSecurityToken oldToken = oldSecrets != null ? oldSecrets.getCurrentToken() : null;

        secureChannel.setChannelSecurity(new ChannelSecurity(newKeys, newToken, oldKeys, oldToken));

        DateTime createdAt = response.getSecurityToken().getCreatedAt();
        long revisedLifetime = response.getSecurityToken().getRevisedLifetime().longValue();

        if (revisedLifetime > 0) {
            long renewAt = (long) (revisedLifetime * 0.75);
            renewFuture = ctx.executor().schedule(
                () -> sendOpenSecureChannelRequest(ctx, SecurityTokenRequestType.Renew),
                renewAt, TimeUnit.MILLISECONDS);
        } else {
            logger.warn("Server revised secure channel lifetime to 0; renewal will not occur.");
        }

        ctx.executor().execute(() -> {
            // SecureChannel is ready; remove the acknowledge handler.
            if (ctx.pipeline().get(UaTcpClientAcknowledgeHandler.class) != null) {
                ctx.pipeline().remove(UaTcpClientAcknowledgeHandler.class);
            }
        });

        ChannelSecurity channelSecurity = secureChannel.getChannelSecurity();

        long currentTokenId = channelSecurity.getCurrentToken().getTokenId().longValue();

        long previousTokenId = channelSecurity.getPreviousToken()
            .map(t -> t.getTokenId().longValue()).orElse(-1L);

        logger.debug(
            "SecureChannel id={}, currentTokenId={}, previousTokenId={}, lifetime={}ms, createdAt={}",
            secureChannel.getChannelId(), currentTokenId, previousTokenId, revisedLifetime, createdAt);
    }

    private void onSecureMessage(ChannelHandlerContext ctx, ByteBuf buffer) throws UaException {
        buffer.skipBytes(3 + 1 + 4); // skip messageType, chunkType, messageSize

        long secureChannelId = buffer.readUnsignedInt();
        if (secureChannelId != secureChannel.getChannelId()) {
            throw new UaException(StatusCodes.Bad_SecureChannelIdInvalid,
                "invalid secure channel id: " + secureChannelId);
        }

        if (accumulateChunk(buffer)) {
            final List<ByteBuf> buffersToDecode = ImmutableList.copyOf(chunkBuffers);
            chunkBuffers = new LinkedList<>();

            serializationQueue.decode((reader, chunkDecoder) -> {
                ByteBuf decodedBuffer = null;

                try {
                    decodedBuffer = chunkDecoder.decodeSymmetric(secureChannel, buffersToDecode);

                    reader.setBuffer(decodedBuffer);

                    UaResponseMessage response = (UaResponseMessage) reader.readMessage(null);

                    UaRequestFuture request = pending.remove(chunkDecoder.getLastRequestId());

                    if (request != null) {
                        request.getFuture().complete(response);
                    } else {
                        logger.warn("No UaRequestFuture for requestId={}", chunkDecoder.getLastRequestId());
                    }
                } catch (MessageAbortedException e) {
                    logger.debug(
                        "Received message abort chunk; error={}, reason={}",
                        e.getStatusCode(), e.getMessage());

                    UaRequestFuture request = pending.remove(chunkDecoder.getLastRequestId());

                    if (request != null) {
                        client.getExecutorService().execute(
                            () -> request.getFuture().completeExceptionally(e));
                    } else {
                        logger.warn("No UaRequestFuture for requestId={}", chunkDecoder.getLastRequestId());
                    }
                } catch (Throwable t) {
                    logger.error("Error decoding symmetric message: {}", t.getMessage(), t);
                    serializationQueue.pause();
                    ctx.close();
                } finally {
                    if (decodedBuffer != null) {
                        decodedBuffer.release();
                    }
                }
            });
        }
    }

    private void onError(ChannelHandlerContext ctx, ByteBuf buffer) {
        try {
            ErrorMessage errorMessage = TcpMessageDecoder.decodeError(buffer);
            StatusCode statusCode = errorMessage.getError();
            long errorCode = statusCode.getValue();

            boolean secureChannelError =
                errorCode == StatusCodes.Bad_SecurityChecksFailed ||
                    errorCode == StatusCodes.Bad_TcpSecureChannelUnknown ||
                    errorCode == StatusCodes.Bad_SecureChannelIdInvalid;

            if (secureChannelError) {
                secureChannel.setChannelId(0);
            }

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
