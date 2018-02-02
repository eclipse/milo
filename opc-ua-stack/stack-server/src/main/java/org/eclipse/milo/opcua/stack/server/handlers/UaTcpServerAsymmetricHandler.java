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
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.application.CertificateManager;
import org.eclipse.milo.opcua.stack.core.application.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.channel.ChannelSecurity;
import org.eclipse.milo.opcua.stack.core.channel.ChunkDecoder;
import org.eclipse.milo.opcua.stack.core.channel.ChunkEncoder;
import org.eclipse.milo.opcua.stack.core.channel.ExceptionHandler;
import org.eclipse.milo.opcua.stack.core.channel.MessageAbortedException;
import org.eclipse.milo.opcua.stack.core.channel.SerializationQueue;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.channel.headers.AsymmetricSecurityHeader;
import org.eclipse.milo.opcua.stack.core.channel.headers.HeaderDecoder;
import org.eclipse.milo.opcua.stack.core.channel.messages.ErrorMessage;
import org.eclipse.milo.opcua.stack.core.channel.messages.MessageType;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.SecurityTokenRequestType;
import org.eclipse.milo.opcua.stack.core.types.structured.ChannelSecurityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.OpenSecureChannelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.OpenSecureChannelResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.util.BufferUtil;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.eclipse.milo.opcua.stack.server.tcp.UaTcpStackServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.NonceUtil.generateNonce;
import static org.eclipse.milo.opcua.stack.core.util.NonceUtil.getNonceLength;

public class UaTcpServerAsymmetricHandler extends ByteToMessageDecoder implements HeaderDecoder {

    private static final long SecureChannelLifetimeMin = 60000L * 60;
    private static final long SecureChannelLifetimeMax = 60000L * 60 * 24;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ServerSecureChannel secureChannel;
    private volatile boolean symmetricHandlerAdded = false;

    private List<ByteBuf> chunkBuffers = new ArrayList<>();

    private final AtomicReference<AsymmetricSecurityHeader> headerRef = new AtomicReference<>();

    private final int maxChunkCount;
    private final int maxChunkSize;

    private final UaTcpStackServer server;
    private final SerializationQueue serializationQueue;

    public UaTcpServerAsymmetricHandler(UaTcpStackServer server, SerializationQueue serializationQueue) {
        this.server = server;
        this.serializationQueue = serializationQueue;

        maxChunkCount = serializationQueue.getParameters().getLocalMaxChunkCount();
        maxChunkSize = serializationQueue.getParameters().getLocalReceiveBufferSize();
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        buffer = buffer.order(ByteOrder.LITTLE_ENDIAN);

        while (buffer.readableBytes() >= HEADER_LENGTH &&
            buffer.readableBytes() >= getMessageLength(buffer)) {

            int messageLength = getMessageLength(buffer);
            MessageType messageType = MessageType.fromMediumInt(buffer.getMedium(buffer.readerIndex()));

            switch (messageType) {
                case OpenSecureChannel:
                    onOpenSecureChannel(ctx, buffer.readSlice(messageLength));
                    break;

                case CloseSecureChannel:
                    logger.debug("Received CloseSecureChannelRequest");
                    if (secureChannel != null) {
                        server.closeSecureChannel(secureChannel);
                    }
                    buffer.skipBytes(messageLength);
                    break;

                default:
                    throw new UaException(StatusCodes.Bad_TcpMessageTypeInvalid,
                        "unexpected MessageType: " + messageType);
            }
        }
    }

    private void onOpenSecureChannel(ChannelHandlerContext ctx, ByteBuf buffer) throws UaException {
        buffer.skipBytes(3); // Skip messageType

        char chunkType = (char) buffer.readByte();

        if (chunkType == 'A') {
            chunkBuffers.forEach(ByteBuf::release);
            chunkBuffers.clear();
            headerRef.set(null);
        } else {
            buffer.skipBytes(4); // Skip messageSize

            long secureChannelId = buffer.readUnsignedInt();
            AsymmetricSecurityHeader securityHeader = AsymmetricSecurityHeader.decode(buffer);

            if (secureChannelId == 0) {
                // Okay, this is the first OpenSecureChannelRequest... carry on.
                String endpointUrl = ctx.channel().attr(UaTcpServerHelloHandler.ENDPOINT_URL_KEY).get();
                String securityPolicyUri = securityHeader.getSecurityPolicyUri();

                EndpointDescription endpointDescription = Arrays.stream(server.getEndpointDescriptions())
                    .filter(e -> {
                        String s1 = EndpointUtil.getPath(endpointUrl);
                        String s2 = EndpointUtil.getPath(e.getEndpointUrl());
                        boolean uriMatch = s1.equals(s2);
                        boolean policyMatch = e.getSecurityPolicyUri().equals(securityPolicyUri);
                        return uriMatch && policyMatch;
                    }).findFirst().orElse(null);

                if (endpointDescription == null && !server.getConfig().isStrictEndpointUrlsEnabled()) {
                    endpointDescription = Arrays.stream(server.getEndpointDescriptions())
                        .filter(e -> e.getSecurityPolicyUri().equals(securityPolicyUri))
                        .findFirst().orElse(null);
                }

                if (endpointDescription == null) {
                    throw new UaException(StatusCodes.Bad_SecurityChecksFailed, "SecurityPolicy URI did not match");
                }

                secureChannel = server.openSecureChannel();
                secureChannel.setEndpointDescription(endpointDescription);
            } else {
                secureChannel = server.getSecureChannel(secureChannelId);

                if (secureChannel == null) {
                    throw new UaException(StatusCodes.Bad_TcpSecureChannelUnknown,
                        "unknown secure channel id: " + secureChannelId);
                }

                if (!secureChannel.getRemoteCertificateChainBytes().equals(securityHeader.getSenderCertificate())) {
                    throw new UaException(StatusCodes.Bad_SecurityChecksFailed,
                        "certificate requesting renewal did not match existing certificate.");
                }

                Channel boundChannel = secureChannel.attr(UaTcpStackServer.BoundChannelKey).get();
                if (boundChannel != null && boundChannel != ctx.channel()) {
                    throw new UaException(StatusCodes.Bad_SecurityChecksFailed,
                        "received a renewal request from channel other than the bound channel.");
                }
            }

            if (!headerRef.compareAndSet(null, securityHeader)) {
                if (!securityHeader.equals(headerRef.get())) {
                    throw new UaException(StatusCodes.Bad_SecurityChecksFailed,
                        "subsequent AsymmetricSecurityHeader did not match");
                }
            }

            SecurityPolicy securityPolicy = SecurityPolicy.fromUri(securityHeader.getSecurityPolicyUri());
            secureChannel.setSecurityPolicy(securityPolicy);

            if (securityPolicy != SecurityPolicy.None) {
                secureChannel.setRemoteCertificate(securityHeader.getSenderCertificate().bytesOrEmpty());

                CertificateValidator certificateValidator = server.getCertificateValidator();

                certificateValidator.validate(secureChannel.getRemoteCertificate());

                certificateValidator.verifyTrustChain(secureChannel.getRemoteCertificateChain());

                CertificateManager certificateManager = server.getCertificateManager();

                Optional<X509Certificate[]> localCertificateChain = certificateManager
                    .getCertificateChain(securityHeader.getReceiverThumbprint());

                Optional<KeyPair> keyPair = certificateManager
                    .getKeyPair(securityHeader.getReceiverThumbprint());

                if (localCertificateChain.isPresent() && keyPair.isPresent()) {
                    X509Certificate[] chain = localCertificateChain.get();

                    secureChannel.setLocalCertificate(chain[0]);
                    secureChannel.setLocalCertificateChain(chain);
                    secureChannel.setKeyPair(keyPair.get());
                } else {
                    throw new UaException(StatusCodes.Bad_SecurityChecksFailed,
                        "no certificate for provided thumbprint");
                }
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
                headerRef.set(null);

                serializationQueue.decode((binaryDecoder, chunkDecoder) ->
                    chunkDecoder.decodeAsymmetric(secureChannel, buffersToDecode, new ChunkDecoder.Callback() {
                        @Override
                        public void onDecodingError(UaException ex) {
                            logger.error(
                                "Error decoding asymmetric message: {}",
                                ex.getMessage(), ex);

                            ctx.close();
                        }

                        @Override
                        public void onMessageAborted(MessageAbortedException ex) {
                            logger.warn(
                                "Asymmetric message aborted. error={} reason={}",
                                ex.getStatusCode(), ex.getMessage());
                        }

                        @Override
                        public void onMessageDecoded(ByteBuf message, long requestId) {
                            try {
                                OpenSecureChannelRequest request = (OpenSecureChannelRequest) binaryDecoder
                                    .setBuffer(message)
                                    .readMessage(null);

                                logger.debug(
                                    "Received OpenSecureChannelRequest ({}, id={}).",
                                    request.getRequestType(), secureChannelId);

                                installSecurityToken(ctx, request, requestId);
                            } catch (UaException e) {
                                logger.error("Error installing security token: {}", e.getStatusCode(), e);
                                ctx.close();
                            } catch (Throwable t) {
                                logger.error("Error decoding OpenSecureChannelRequest", t);
                                ctx.close();
                            } finally {
                                message.release();
                                buffersToDecode.clear();
                            }
                        }
                    })
                );
            }
        }
    }

    private void installSecurityToken(ChannelHandlerContext ctx,
                                      OpenSecureChannelRequest request,
                                      long requestId) throws UaException {

        SecurityTokenRequestType requestType = request.getRequestType();

        if (requestType == SecurityTokenRequestType.Issue) {
            secureChannel.setMessageSecurityMode(request.getSecurityMode());
        } else if (requestType == SecurityTokenRequestType.Renew &&
            secureChannel.getMessageSecurityMode() != request.getSecurityMode()) {

            throw new UaException(StatusCodes.Bad_SecurityChecksFailed,
                "secure channel renewal requested a different MessageSecurityMode.");
        }

        long channelLifetime = request.getRequestedLifetime().longValue();
        channelLifetime = Math.min(SecureChannelLifetimeMax, channelLifetime);
        channelLifetime = Math.max(SecureChannelLifetimeMin, channelLifetime);

        ChannelSecurityToken newToken = new ChannelSecurityToken(
            uint(secureChannel.getChannelId()),
            uint(server.nextTokenId()),
            DateTime.now(),
            uint(channelLifetime)
        );

        ChannelSecurity.SecuritySecrets newKeys = null;

        if (secureChannel.isSymmetricSigningEnabled()) {
            // Validate the remote nonce; it must be non-null and the correct length for the security algorithm.
            ByteString remoteNonce = request.getClientNonce();
            if (remoteNonce == null || remoteNonce.isNull()) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed, "remote nonce must be non-null");
            }
            if (remoteNonce.length() < getNonceLength(secureChannel.getSecurityPolicy())) {
                String message = String.format(
                    "remote nonce length must be at least %d bytes",
                    getNonceLength(secureChannel.getSecurityPolicy()));

                throw new UaException(StatusCodes.Bad_SecurityChecksFailed, message);
            }

            ByteString localNonce = generateNonce(secureChannel.getSecurityPolicy());

            secureChannel.setLocalNonce(localNonce);
            secureChannel.setRemoteNonce(remoteNonce);

            newKeys = ChannelSecurity.generateKeyPair(
                secureChannel,
                secureChannel.getRemoteNonce(),
                secureChannel.getLocalNonce()
            );
        }

        ChannelSecurity oldSecrets = secureChannel.getChannelSecurity();
        ChannelSecurity.SecuritySecrets oldKeys = oldSecrets != null ? oldSecrets.getCurrentKeys() : null;
        ChannelSecurityToken oldToken = oldSecrets != null ? oldSecrets.getCurrentToken() : null;

        ChannelSecurity newSecrets = new ChannelSecurity(
            newKeys,
            newToken,
            oldKeys,
            oldToken
        );

        secureChannel.setChannelSecurity(newSecrets);

        ResponseHeader responseHeader = new ResponseHeader(
            DateTime.now(),
            request.getRequestHeader().getRequestHandle(),
            StatusCode.GOOD,
            null, null, null
        );

        OpenSecureChannelResponse response = new OpenSecureChannelResponse(
            responseHeader,
            uint(PROTOCOL_VERSION),
            newToken,
            secureChannel.getLocalNonce()
        );

        sendOpenSecureChannelResponse(ctx, requestId, response);
    }

    private void sendOpenSecureChannelResponse(
        ChannelHandlerContext ctx,
        long requestId,
        OpenSecureChannelResponse response) {

        serializationQueue.encode((writer, chunkEncoder) -> {
            ByteBuf messageBuffer = BufferUtil.buffer();

            try {
                writer.setBuffer(messageBuffer);
                writer.writeMessage(null, response);

                checkMessageSize(messageBuffer);

                chunkEncoder.encodeAsymmetric(
                    secureChannel,
                    requestId,
                    messageBuffer,
                    MessageType.OpenSecureChannel,
                    new ChunkEncoder.Callback() {
                        @Override
                        public void onEncodingError(UaException ex) {
                            logger.error("Error encoding OpenSecureChannelResponse: {}", ex.getMessage(), ex);
                            ctx.fireExceptionCaught(ex);
                        }

                        @Override
                        public void onMessageEncoded(List<ByteBuf> messageChunks, long requestId) {
                            if (!symmetricHandlerAdded) {
                                UaTcpServerSymmetricHandler symmetricHandler =
                                    new UaTcpServerSymmetricHandler(
                                        server, serializationQueue, secureChannel);

                                ctx.pipeline().addFirst(symmetricHandler);
                                symmetricHandlerAdded = true;
                            }

                            CompositeByteBuf chunkComposite = BufferUtil.compositeBuffer();

                            for (ByteBuf chunk : messageChunks) {
                                chunkComposite.addComponent(chunk);
                                chunkComposite.writerIndex(chunkComposite.writerIndex() + chunk.readableBytes());
                            }

                            ctx.writeAndFlush(chunkComposite, ctx.voidPromise());

                            long lifetime = response.getSecurityToken().getRevisedLifetime().longValue();
                            server.secureChannelIssuedOrRenewed(secureChannel, lifetime);

                            logger.debug("Sent OpenSecureChannelResponse.");
                        }
                    }
                );
            } catch (UaSerializationException e) {
                ctx.fireExceptionCaught(e);
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
