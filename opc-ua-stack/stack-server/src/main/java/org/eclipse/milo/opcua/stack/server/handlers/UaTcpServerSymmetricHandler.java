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
import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.util.ReferenceCountUtil;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.UaExceptionStatus;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceResponse;
import org.eclipse.milo.opcua.stack.core.channel.ChannelSecurity;
import org.eclipse.milo.opcua.stack.core.channel.ChunkDecoder;
import org.eclipse.milo.opcua.stack.core.channel.ChunkEncoder;
import org.eclipse.milo.opcua.stack.core.channel.ExceptionHandler;
import org.eclipse.milo.opcua.stack.core.channel.MessageAbortedException;
import org.eclipse.milo.opcua.stack.core.channel.SerializationQueue;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.channel.headers.HeaderDecoder;
import org.eclipse.milo.opcua.stack.core.channel.messages.ErrorMessage;
import org.eclipse.milo.opcua.stack.core.channel.messages.MessageType;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.core.util.BufferUtil;
import org.eclipse.milo.opcua.stack.server.tcp.UaTcpStackServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class UaTcpServerSymmetricHandler extends ByteToMessageCodec<ServiceResponse> implements HeaderDecoder {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private List<ByteBuf> chunkBuffers;

    private final int maxChunkCount;
    private final int maxChunkSize;

    private final UaTcpStackServer server;
    private final SerializationQueue serializationQueue;
    private final ServerSecureChannel secureChannel;

    public UaTcpServerSymmetricHandler(UaTcpStackServer server,
                                       SerializationQueue serializationQueue,
                                       ServerSecureChannel secureChannel) {

        this.server = server;
        this.serializationQueue = serializationQueue;
        this.secureChannel = secureChannel;

        maxChunkCount = serializationQueue.getParameters().getLocalMaxChunkCount();
        maxChunkSize = serializationQueue.getParameters().getLocalReceiveBufferSize();

        chunkBuffers = new ArrayList<>(maxChunkCount);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        if (secureChannel != null) {
            secureChannel.attr(UaTcpStackServer.BoundChannelKey).set(ctx.channel());
        }

        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (secureChannel != null) {
            secureChannel.attr(UaTcpStackServer.BoundChannelKey).remove();
        }

        super.channelInactive(ctx);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ServiceResponse serviceResponse, ByteBuf out) throws Exception {
        serializationQueue.encode((binaryEncoder, chunkEncoder) -> {
            ByteBuf messageBuffer = BufferUtil.buffer();

            try {
                binaryEncoder.setBuffer(messageBuffer);
                binaryEncoder.writeMessage(null, serviceResponse.getResponse());

                checkMessageSize(messageBuffer);

                chunkEncoder.encodeSymmetric(
                    secureChannel,
                    serviceResponse.getRequestId(),
                    messageBuffer,
                    MessageType.SecureMessage,
                    new ChunkEncoder.Callback() {
                        @Override
                        public void onEncodingError(UaException ex) {
                            logger.error("Error encoding {}: {}",
                                serviceResponse, ex.getMessage(), ex);

                            sendServiceFault(ex.getStatusCode(), ctx, serviceResponse);
                        }

                        @Override
                        public void onMessageEncoded(List<ByteBuf> messageChunks, long requestId) {
                            CompositeByteBuf chunkComposite = BufferUtil.compositeBuffer();

                            for (ByteBuf chunk : messageChunks) {
                                chunkComposite.addComponent(chunk);
                                chunkComposite.writerIndex(chunkComposite.writerIndex() + chunk.readableBytes());
                            }

                            ctx.writeAndFlush(chunkComposite, ctx.voidPromise());
                        }
                    }
                );
            } catch (UaSerializationException ex) {
                logger.error("Error encoding response: {}", ex.getStatusCode(), ex);

                sendServiceFault(ex.getStatusCode(), ctx, serviceResponse);
            } finally {
                messageBuffer.release();
            }
        });
    }

    private void sendServiceFault(
        StatusCode statusCode,
        ChannelHandlerContext ctx,
        ServiceResponse serviceResponse) {

        if (serviceResponse.isServiceFault()) {
            // If we already failed to send a ServiceFault don't try again
            ctx.fireExceptionCaught(new UaException(statusCode));
        } else {
            UInteger requestHandle = serviceResponse.getRequest().getRequestHeader().getRequestHandle();

            ServiceFault serviceFault = new ServiceFault(
                new ResponseHeader(
                    DateTime.now(),
                    requestHandle,
                    statusCode,
                    null, null, null
                )
            );

            ServiceResponse newServiceResponse = new ServiceResponse(
                serviceResponse.getRequest(),
                serviceResponse.getRequestId(),
                serviceFault
            );

            ctx.writeAndFlush(newServiceResponse);
        }
    }

    private void checkMessageSize(ByteBuf messageBuffer) throws UaSerializationException {
        int messageSize = messageBuffer.readableBytes();
        int remoteMaxMessageSize = serializationQueue.getParameters().getRemoteMaxMessageSize();

        if (remoteMaxMessageSize > 0 && messageSize > remoteMaxMessageSize) {
            throw new UaSerializationException(
                StatusCodes.Bad_ResponseTooLarge,
                "response exceeds remote max message size: " +
                    messageSize + " > " + remoteMaxMessageSize);
        }
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        buffer = buffer.order(ByteOrder.LITTLE_ENDIAN);

        while (buffer.readableBytes() >= HEADER_LENGTH &&
            buffer.readableBytes() >= getMessageLength(buffer)) {

            int messageLength = getMessageLength(buffer);
            MessageType messageType = MessageType.fromMediumInt(buffer.getMedium(buffer.readerIndex()));

            switch (messageType) {
                case SecureMessage:
                    onSecureMessage(ctx, buffer.readSlice(messageLength), out);
                    break;

                default:
                    out.add(buffer.readSlice(messageLength).retain());
            }
        }
    }

    private void onSecureMessage(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws UaException {
        buffer.skipBytes(3); // Skip messageType

        char chunkType = (char) buffer.readByte();

        if (chunkType == 'A') {
            chunkBuffers.forEach(ByteBuf::release);
            chunkBuffers.clear();
        } else {
            buffer.skipBytes(4); // Skip messageSize

            long secureChannelId = buffer.readUnsignedInt();
            if (secureChannelId != secureChannel.getChannelId()) {
                throw new UaException(StatusCodes.Bad_SecureChannelIdInvalid,
                    "invalid secure channel id: " + secureChannelId);
            }

            int chunkSize = buffer.readerIndex(0).readableBytes();
            if (chunkSize > maxChunkSize) {
                throw new UaException(StatusCodes.Bad_TcpMessageTooLarge,
                    String.format("max chunk size exceeded (%s)", maxChunkSize));
            }

            chunkBuffers.add(buffer.retain());

            if (maxChunkCount > 0 && chunkBuffers.size() > maxChunkCount) {
                throw new UaException(StatusCodes.Bad_TcpMessageTooLarge,
                    String.format("max chunk count exceeded (%s)", maxChunkCount));
            }

            if (chunkType == 'F') {
                final List<ByteBuf> buffersToDecode = chunkBuffers;
                chunkBuffers = new ArrayList<>();

                serializationQueue.decode((binaryDecoder, chunkDecoder) -> {
                    try {
                        validateChunkHeaders(buffersToDecode);
                    } catch (UaException e) {
                        logger.error("Error validating chunk headers: {}", e.getMessage(), e);
                        buffersToDecode.forEach(ReferenceCountUtil::safeRelease);
                        ctx.fireExceptionCaught(e);
                        return;
                    }

                    chunkDecoder.decodeSymmetric(secureChannel, buffersToDecode, new ChunkDecoder.Callback() {
                        @Override
                        public void onDecodingError(UaException ex) {
                            logger.error(
                                "Error decoding symmetric message: {}",
                                ex.getMessage(), ex);

                            ctx.close();
                        }

                        @Override
                        public void onMessageAborted(MessageAbortedException ex) {
                            logger.warn(
                                "Received message abort chunk; error={}, reason={}",
                                ex.getStatusCode(), ex.getMessage());
                        }

                        @Override
                        public void onMessageDecoded(ByteBuf message, long requestId) {
                            server.getExecutorService().execute(() -> {
                                try {
                                    UaRequestMessage request = (UaRequestMessage) binaryDecoder
                                        .setBuffer(message)
                                        .readMessage(null);

                                    server.receiveRequest(new ServiceRequest<>(
                                        request,
                                        requestId,
                                        server,
                                        secureChannel
                                    ));
                                } catch (Throwable t) {
                                    logger.error("Error decoding UaRequestMessage", t);

                                    StatusCode statusCode = UaExceptionStatus.extract(t)
                                        .map(UaExceptionStatus::getStatusCode)
                                        .orElse(StatusCode.BAD);

                                    ServiceFault serviceFault = new ServiceFault(
                                        new ResponseHeader(
                                            DateTime.now(),
                                            uint(0),
                                            statusCode,
                                            null, null, null
                                        )
                                    );

                                    ctx.writeAndFlush(new ServiceResponse(null, requestId, serviceFault));
                                } finally {
                                    message.release();
                                    buffersToDecode.clear();
                                }
                            });
                        }
                    });
                });
            }
        }
    }

    private void validateChunkHeaders(List<ByteBuf> chunkBuffers) throws UaException {
        ChannelSecurity channelSecurity = secureChannel.getChannelSecurity();
        long currentTokenId = channelSecurity.getCurrentToken().getTokenId().longValue();
        long previousTokenId = channelSecurity.getPreviousToken()
            .map(t -> t.getTokenId().longValue())
            .orElse(-1L);

        for (ByteBuf chunkBuffer : chunkBuffers) {
            // tokenId starts after messageType + chunkType + messageSize + secureChannelId
            long tokenId = chunkBuffer.getUnsignedInt(3 + 1 + 4 + 4);

            if (tokenId != currentTokenId && tokenId != previousTokenId) {
                String message = String.format(
                    "received unknown secure channel token: " +
                        "tokenId=%s currentTokenId=%s previousTokenId=%s",
                    tokenId, currentTokenId, previousTokenId
                );

                throw new UaException(StatusCodes.Bad_SecureChannelTokenUnknown, message);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        chunkBuffers.forEach(ReferenceCountUtil::safeRelease);
        chunkBuffers.clear();

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
