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

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.channel.ChannelSecurity;
import org.eclipse.milo.opcua.stack.core.channel.ChunkDecoder;
import org.eclipse.milo.opcua.stack.core.channel.ChunkEncoder;
import org.eclipse.milo.opcua.stack.core.channel.MessageAbortedException;
import org.eclipse.milo.opcua.stack.core.channel.SerializationQueue;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.channel.headers.HeaderDecoder;
import org.eclipse.milo.opcua.stack.core.channel.messages.MessageType;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.core.util.BufferUtil;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.eclipse.milo.opcua.stack.server.UaStackServer;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class UascServerSymmetricHandler extends ByteToMessageDecoder implements HeaderDecoder {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private List<ByteBuf> chunkBuffers;

    private final int maxChunkCount;
    private final int maxChunkSize;

    private final UaStackServer stackServer;
    private final SerializationQueue serializationQueue;
    private final ServerSecureChannel secureChannel;

    UascServerSymmetricHandler(
        UaStackServer stackServer,
        SerializationQueue serializationQueue,
        ServerSecureChannel secureChannel) {

        this.stackServer = stackServer;
        this.serializationQueue = serializationQueue;
        this.secureChannel = secureChannel;

        maxChunkCount = serializationQueue.getParameters().getLocalMaxChunkCount();
        maxChunkSize = serializationQueue.getParameters().getLocalReceiveBufferSize();

        chunkBuffers = new ArrayList<>(maxChunkCount);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        while (buffer.readableBytes() >= HEADER_LENGTH) {
            int messageLength = getMessageLength(buffer, maxChunkSize);

            if (buffer.readableBytes() < messageLength) {
                break;
            }

            MessageType messageType = MessageType.fromMediumInt(buffer.getMediumLE(buffer.readerIndex()));

            switch (messageType) {
                case SecureMessage:
                    onSecureMessage(ctx, buffer.readSlice(messageLength));
                    break;

                default:
                    out.add(buffer.readSlice(messageLength).retain());
            }
        }
    }

    private void onSecureMessage(ChannelHandlerContext ctx, ByteBuf buffer) throws UaException {
        buffer.skipBytes(3); // Skip messageType

        char chunkType = (char) buffer.readByte();

        if (chunkType == 'A') {
            chunkBuffers.forEach(ByteBuf::release);
            chunkBuffers.clear();
        } else {
            buffer.skipBytes(4); // Skip messageSize

            long secureChannelId = buffer.readUnsignedIntLE();
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
                            stackServer.getConfig().getExecutor().execute(() -> {
                                try {
                                    String endpointUrl = ctx
                                        .channel()
                                        .attr(UascServerHelloHandler.ENDPOINT_URL_KEY)
                                        .get();

                                    EndpointDescription endpoint = ctx
                                        .channel()
                                        .attr(UascServerAsymmetricHandler.ENDPOINT_KEY)
                                        .get();

                                    String path = EndpointUtil.getPath(endpointUrl);

                                    UaRequestMessage request = (UaRequestMessage) binaryDecoder
                                        .setBuffer(message)
                                        .readMessage(null);

                                    ServiceRequest serviceRequest = new ServiceRequest(
                                        stackServer,
                                        request,
                                        endpoint,
                                        secureChannel.getChannelId(),
                                        secureChannel.getRemoteCertificateBytes()
                                    );

                                    serviceRequest.getFuture().whenComplete((response, fault) -> {
                                        if (response != null) {
                                            sendServiceResponse(ctx, requestId, request, response);
                                        } else {
                                            UInteger requestHandle = request.getRequestHeader().getRequestHandle();

                                            sendServiceFault(ctx, requestId, requestHandle, fault);
                                        }
                                    });

                                    stackServer.onServiceRequest(path, serviceRequest);
                                } catch (Throwable t) {
                                    logger.error("Error decoding UaRequestMessage", t);

                                    sendServiceFault(ctx, requestId, uint(0), t);
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

    private void sendServiceResponse(
        ChannelHandlerContext ctx,
        long requestId,
        UaRequestMessage request,
        UaResponseMessage response) {

        serializationQueue.encode((binaryEncoder, chunkEncoder) -> {
            ByteBuf messageBuffer = BufferUtil.pooledBuffer();

            try {
                binaryEncoder.setBuffer(messageBuffer);
                binaryEncoder.writeMessage(null, response);

                checkMessageSize(messageBuffer);

                chunkEncoder.encodeSymmetric(
                    secureChannel,
                    requestId,
                    messageBuffer,
                    MessageType.SecureMessage,
                    new ChunkEncoder.Callback() {
                        @Override
                        public void onEncodingError(UaException ex) {
                            logger.error("Error encoding {}: {}",
                                response, ex.getMessage(), ex);

                            UInteger requestHandle = request.getRequestHeader().getRequestHandle();

                            sendServiceFault(ctx, requestId, requestHandle, ex);
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

                UInteger requestHandle = request.getRequestHeader().getRequestHandle();

                sendServiceFault(ctx, requestId, requestHandle, ex);
            } finally {
                messageBuffer.release();
            }
        });
    }

    private void sendServiceFault(
        ChannelHandlerContext ctx,
        long requestId,
        UInteger requestHandle,
        Throwable fault) {

        StatusCode statusCode = UaException.extract(fault)
            .map(UaException::getStatusCode)
            .orElse(StatusCode.BAD);

        ServiceFault serviceFault = new ServiceFault(
            new ResponseHeader(
                DateTime.now(),
                requestHandle,
                statusCode,
                null,
                null,
                null
            )
        );

        serializationQueue.encode((binaryEncoder, chunkEncoder) -> {
            ByteBuf messageBuffer = BufferUtil.pooledBuffer();

            try {
                binaryEncoder.setBuffer(messageBuffer);
                binaryEncoder.writeMessage(null, serviceFault);

                checkMessageSize(messageBuffer);

                chunkEncoder.encodeSymmetric(
                    secureChannel,
                    requestId,
                    messageBuffer,
                    MessageType.SecureMessage,
                    new ChunkEncoder.Callback() {
                        @Override
                        public void onEncodingError(UaException ex) {
                            logger.error("Error encoding {}: {}",
                                serviceFault, ex.getMessage(), ex);
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
                logger.error("Error encoding ServiceFault: {}", ex.getStatusCode(), ex);
            } finally {
                messageBuffer.release();
            }
        });
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

    private void validateChunkHeaders(List<ByteBuf> chunkBuffers) throws UaException {
        ChannelSecurity channelSecurity = secureChannel.getChannelSecurity();
        long currentTokenId = channelSecurity.getCurrentToken().getTokenId().longValue();
        long previousTokenId = channelSecurity.getPreviousToken()
            .map(t -> t.getTokenId().longValue())
            .orElse(-1L);

        for (ByteBuf chunkBuffer : chunkBuffers) {
            // tokenId starts after messageType + chunkType + messageSize + secureChannelId
            long tokenId = chunkBuffer.getUnsignedIntLE(3 + 1 + 4 + 4);

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

}
