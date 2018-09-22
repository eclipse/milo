/*
 * Copyright (c) 2018 Kevin Herron
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

package org.eclipse.milo.opcua.stack.server.transport.uasc;

import java.io.IOException;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.Timeout;
import org.eclipse.milo.opcua.stack.core.Stack;
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
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.SecurityTokenRequestType;
import org.eclipse.milo.opcua.stack.core.types.structured.ChannelSecurityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.OpenSecureChannelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.OpenSecureChannelResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.util.BufferUtil;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.eclipse.milo.opcua.stack.server.UaStackServer;
import org.eclipse.milo.opcua.stack.server.tcp.LegacyUaTcpStackServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.NonceUtil.generateNonce;
import static org.eclipse.milo.opcua.stack.core.util.NonceUtil.getNonceLength;

public class UascServerAsymmetricHandler extends ByteToMessageDecoder implements HeaderDecoder {

    private static final long SecureChannelLifetimeMin = 60000L * 60;
    private static final long SecureChannelLifetimeMax = 60000L * 60 * 24;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ServerSecureChannel secureChannel;
    private Timeout secureChannelTimeout;

    private boolean symmetricHandlerAdded = false;

    private List<ByteBuf> chunkBuffers = new ArrayList<>();

    private final AtomicReference<AsymmetricSecurityHeader> headerRef = new AtomicReference<>();

    private final int maxArrayLength;
    private final int maxStringLength;
    private final int maxChunkCount;
    private final int maxChunkSize;

    private final UaStackServer stackServer;
    private final SerializationQueue serializationQueue;

    public UascServerAsymmetricHandler(UaStackServer stackServer, SerializationQueue serializationQueue) {
        this.stackServer = stackServer;
        this.serializationQueue = serializationQueue;

        maxArrayLength = stackServer.getConfig().getEncodingLimits().getMaxArrayLength();
        maxStringLength = stackServer.getConfig().getEncodingLimits().getMaxStringLength();
        maxChunkCount = serializationQueue.getParameters().getLocalMaxChunkCount();
        maxChunkSize = serializationQueue.getParameters().getLocalReceiveBufferSize();
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
                case OpenSecureChannel:
                    onOpenSecureChannel(ctx, buffer.readSlice(messageLength));
                    break;

                case CloseSecureChannel:
                    logger.debug("Received CloseSecureChannelRequest");

                    buffer.skipBytes(messageLength);

                    if (secureChannelTimeout != null) {
                        secureChannelTimeout.cancel();
                        secureChannelTimeout = null;
                    }

                    ctx.close();
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

            long secureChannelId = buffer.readUnsignedIntLE();

            AsymmetricSecurityHeader securityHeader = AsymmetricSecurityHeader.decode(
                buffer,
                maxArrayLength,
                maxStringLength
            );

            if (secureChannelId == 0) {
                // Okay, this is the first OpenSecureChannelRequest... carry on.
                String endpointUrl = ctx.channel().attr(UascServerHelloHandler.ENDPOINT_URL_KEY).get();
                String securityPolicyUri = securityHeader.getSecurityPolicyUri();

                boolean endpointWithSecurityPolicyExists = stackServer.getEndpointDescriptions()
                    .stream()
                    .anyMatch(e -> {
                        String s1 = EndpointUtil.getPath(endpointUrl);
                        String s2 = EndpointUtil.getPath(e.getEndpointUrl());
                        boolean uriMatch = s1.equals(s2);
                        boolean policyMatch = e.getSecurityPolicyUri().equals(securityPolicyUri);
                        return uriMatch && policyMatch;
                    });

                if (!endpointWithSecurityPolicyExists) {
                    throw new UaException(
                        StatusCodes.Bad_SecurityChecksFailed,
                        String.format("No endpoint at %s with SecurityPolicy URI=%s", endpointUrl, securityPolicyUri));
                }

                secureChannel = new ServerSecureChannel();
                secureChannel.setChannelId(stackServer.getNextChannelId());
            } else {
                if (secureChannel == null) {
                    throw new UaException(StatusCodes.Bad_TcpSecureChannelUnknown,
                        "unknown secure channel id: " + secureChannelId);
                }

                if (secureChannelId != secureChannel.getChannelId()) {
                    throw new UaException(StatusCodes.Bad_TcpSecureChannelUnknown,
                        "unknown secure channel id: " + secureChannelId);
                }

                if (!secureChannel.getRemoteCertificateChainBytes().equals(securityHeader.getSenderCertificate())) {
                    throw new UaException(StatusCodes.Bad_SecurityChecksFailed,
                        "certificate requesting renewal did not match existing certificate.");
                }

                Channel boundChannel = secureChannel.attr(LegacyUaTcpStackServer.BoundChannelKey).get();
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

                CertificateValidator certificateValidator = stackServer.getConfig().getCertificateValidator();

                certificateValidator.validate(secureChannel.getRemoteCertificate());
                certificateValidator.verifyTrustChain(secureChannel.getRemoteCertificateChain());

                CertificateManager certificateManager = stackServer.getConfig().getCertificateManager();

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
            MessageSecurityMode securityMode = request.getSecurityMode();

            String endpointUrl = ctx.channel().attr(UascServerHelloHandler.ENDPOINT_URL_KEY).get();

            boolean endpointWithSecurityModeExists = stackServer.getEndpointDescriptions()
                .stream()
                .anyMatch(e -> {
                    String s1 = EndpointUtil.getPath(endpointUrl);
                    String s2 = EndpointUtil.getPath(e.getEndpointUrl());
                    boolean uriMatch = s1.equals(s2);
                    boolean securityModeMatch = e.getSecurityMode() == securityMode;
                    return uriMatch && securityModeMatch;
                });

            if (!endpointWithSecurityModeExists) {
                throw new UaException(
                    StatusCodes.Bad_SecurityChecksFailed,
                    String.format("No endpoint at %s with MessageSecurityMode %s", endpointUrl, securityMode));
            }

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
            uint(stackServer.getNextTokenId()),
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

        /*
         * Cancel the previous timeout, if it exists, and start a new one.
         */
        if (secureChannelTimeout == null || secureChannelTimeout.cancel()) {
            final long lifetime = channelLifetime;
            secureChannelTimeout = Stack.sharedWheelTimer().newTimeout(t ->
                {
                    logger.debug(
                        "SecureChannel renewal timed out after {}ms. id={}, channel={}",
                        lifetime, secureChannel.getChannelId(), ctx.channel());

                    ctx.close();
                },
                channelLifetime, TimeUnit.MILLISECONDS
            );
        }
    }

    private void sendOpenSecureChannelResponse(
        ChannelHandlerContext ctx,
        long requestId,
        OpenSecureChannelResponse response) {

        serializationQueue.encode((writer, chunkEncoder) -> {
            ByteBuf messageBuffer = BufferUtil.pooledBuffer();

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
                                UascServerSymmetricHandler symmetricHandler =
                                    new UascServerSymmetricHandler(
                                        stackServer,
                                        serializationQueue,
                                        secureChannel
                                    );

                                ctx.pipeline().addBefore(ctx.name(), null, symmetricHandler);

                                symmetricHandlerAdded = true;
                            }

                            CompositeByteBuf chunkComposite = BufferUtil.compositeBuffer();

                            for (ByteBuf chunk : messageChunks) {
                                chunkComposite.addComponent(chunk);
                                chunkComposite.writerIndex(chunkComposite.writerIndex() + chunk.readableBytes());
                            }

                            ctx.writeAndFlush(chunkComposite, ctx.voidPromise());

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
