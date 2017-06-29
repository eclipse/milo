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
import java.net.URI;
import java.nio.ByteOrder;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.application.CertificateManager;
import org.eclipse.milo.opcua.stack.core.application.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.channel.ChannelSecurity;
import org.eclipse.milo.opcua.stack.core.channel.ExceptionHandler;
import org.eclipse.milo.opcua.stack.core.channel.SerializationQueue;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.channel.headers.AsymmetricSecurityHeader;
import org.eclipse.milo.opcua.stack.core.channel.headers.HeaderDecoder;
import org.eclipse.milo.opcua.stack.core.channel.messages.ErrorMessage;
import org.eclipse.milo.opcua.stack.core.channel.messages.MessageType;
import org.eclipse.milo.opcua.stack.core.security.SecurityAlgorithm;
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
                        String s1 = pathOrUrl(endpointUrl);
                        String s2 = pathOrUrl(e.getEndpointUrl());
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

                if (!secureChannel.getRemoteCertificateBytes().equals(securityHeader.getSenderCertificate())) {
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

            if (!securityHeader.getSenderCertificate().isNull() && securityPolicy != SecurityPolicy.None) {
                secureChannel.setRemoteCertificate(securityHeader.getSenderCertificate().bytes());

                try {
                    CertificateValidator certificateValidator = server.getCertificateValidator();

                    certificateValidator.validate(secureChannel.getRemoteCertificate());

                    certificateValidator.verifyTrustChain(
                        secureChannel.getRemoteCertificate(),
                        secureChannel.getRemoteCertificateChain());
                } catch (UaException e) {
                    try {
                        UaException cause = new UaException(e.getStatusCode(), "security checks failed");
                        ErrorMessage errorMessage = ExceptionHandler.sendErrorMessage(ctx, cause);

                        logger.debug("[remote={}] {}.",
                            ctx.channel().remoteAddress(), errorMessage.getReason(), cause);
                    } catch (Exception inner) {
                        logger.error("Error sending ErrorMessage: {}", inner.getMessage(), inner);
                    }
                }
            }

            if (!securityHeader.getReceiverThumbprint().isNull()) {
                CertificateManager certificateManager = server.getCertificateManager();

                Optional<X509Certificate> localCertificate = certificateManager
                    .getCertificate(securityHeader.getReceiverThumbprint());

                Optional<KeyPair> keyPair = certificateManager
                    .getKeyPair(securityHeader.getReceiverThumbprint());

                if (localCertificate.isPresent() && keyPair.isPresent()) {
                    secureChannel.setLocalCertificate(localCertificate.get());
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

            if (chunkBuffers.size() > maxChunkCount) {
                throw new UaException(StatusCodes.Bad_TcpMessageTooLarge,
                    String.format("max chunk count exceeded (%s)", maxChunkCount));
            }

            if (chunkType == 'F') {
                final List<ByteBuf> buffersToDecode = chunkBuffers;

                chunkBuffers = new ArrayList<>(maxChunkCount);
                headerRef.set(null);

                serializationQueue.decode((reader, chunkDecoder) -> {
                    ByteBuf messageBuffer = null;

                    try {
                        messageBuffer = chunkDecoder.decodeAsymmetric(secureChannel, buffersToDecode);

                        reader.setBuffer(messageBuffer);

                        OpenSecureChannelRequest request = (OpenSecureChannelRequest) reader.readMessage(null);

                        logger.debug("Received OpenSecureChannelRequest ({}, id={}).",
                            request.getRequestType(), secureChannelId);

                        long requestId = chunkDecoder.getLastRequestId();
                        installSecurityToken(ctx, request, requestId);
                    } catch (UaException e) {
                        logger.error("Error decoding asymmetric message: {}", e.getMessage(), e);
                        ctx.close();
                    } finally {
                        if (messageBuffer != null) {
                            messageBuffer.release();
                        }
                        buffersToDecode.clear();
                    }
                });
            }
        }
    }

    private String pathOrUrl(String endpointUrl) {
        try {
            URI uri = new URI(endpointUrl).parseServerAuthority();
            return uri.getPath();
        } catch (Throwable e) {
            logger.warn("Endpoint URL '{}' is not a valid URI: {}", e.getMessage(), e);
            return endpointUrl;
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
            SecurityAlgorithm algorithm = secureChannel.getSecurityPolicy().getSymmetricEncryptionAlgorithm();

            // Validate the remote nonce; it must be non-null and the correct length for the security algorithm.
            ByteString remoteNonce = request.getClientNonce();
            if (remoteNonce == null || remoteNonce.isNull()) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed, "remote nonce must be non-null");
            }
            if (remoteNonce.length() < getNonceLength(algorithm)) {
                String message = String.format(
                    "remote nonce length must be at least %d bytes",
                    getNonceLength(algorithm));

                throw new UaException(StatusCodes.Bad_SecurityChecksFailed, message);
            }


            ByteString localNonce = generateNonce(getNonceLength(algorithm));

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

                List<ByteBuf> chunks = chunkEncoder.encodeAsymmetric(
                    secureChannel,
                    MessageType.OpenSecureChannel,
                    messageBuffer,
                    requestId
                );

                if (!symmetricHandlerAdded) {
                    ctx.pipeline().addFirst(new UaTcpServerSymmetricHandler(server, serializationQueue, secureChannel));
                    symmetricHandlerAdded = true;
                }

                chunks.forEach(c -> ctx.write(c, ctx.voidPromise()));
                ctx.flush();

                long lifetime = response.getSecurityToken().getRevisedLifetime().longValue();
                server.secureChannelIssuedOrRenewed(secureChannel, lifetime);

                logger.debug("Sent OpenSecureChannelResponse.");
            } catch (UaException e) {
                logger.error("Error encoding OpenSecureChannelResponse: {}", e.getMessage(), e);
                ctx.close();
            } finally {
                messageBuffer.release();
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        chunkBuffers.forEach(ByteBuf::release);
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
