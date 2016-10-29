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

package org.eclipse.milo.opcua.stack.core.channel;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import io.netty.buffer.ByteBuf;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.headers.AsymmetricSecurityHeader;
import org.eclipse.milo.opcua.stack.core.channel.headers.SecureMessageHeader;
import org.eclipse.milo.opcua.stack.core.channel.headers.SequenceHeader;
import org.eclipse.milo.opcua.stack.core.channel.headers.SymmetricSecurityHeader;
import org.eclipse.milo.opcua.stack.core.channel.messages.MessageType;
import org.eclipse.milo.opcua.stack.core.security.SecurityAlgorithm;
import org.eclipse.milo.opcua.stack.core.util.BufferUtil;
import org.eclipse.milo.opcua.stack.core.util.LongSequence;
import org.eclipse.milo.opcua.stack.core.util.SignatureUtil;

public class ChunkEncoder {

    private final Delegate asymmetricDelegate = new AsymmetricDelegate();
    private final Delegate symmetricDelegate = new SymmetricDelegate();

    // Wrap after UInt32.MAX - 1024
    private final LongSequence sequenceNumber = new LongSequence(1L, 4294966271L);

    private volatile long lastRequestId = 1L;

    private final ChannelParameters parameters;

    public ChunkEncoder(ChannelParameters parameters) {
        this.parameters = parameters;
    }

    public List<ByteBuf> encodeAsymmetric(
        SecureChannel channel,
        MessageType messageType,
        ByteBuf messageBuffer,
        long requestId) throws UaException {

        return encode(asymmetricDelegate, channel, messageType, messageBuffer, requestId);
    }

    public List<ByteBuf> encodeSymmetric(
        SecureChannel channel,
        MessageType messageType,
        ByteBuf messageBuffer,
        long requestId) throws UaException {

        return encode(symmetricDelegate, channel, messageType, messageBuffer, requestId);
    }

    private List<ByteBuf> encode(
        Delegate delegate,
        SecureChannel channel,
        MessageType messageType,
        ByteBuf messageBuffer,
        long requestId) throws UaException {

        List<ByteBuf> chunks = new ArrayList<>();

        boolean encrypted = delegate.isEncryptionEnabled(channel);

        int securityHeaderSize = delegate.getSecurityHeaderSize(channel);
        int cipherTextBlockSize = delegate.getCipherTextBlockSize(channel);
        int plainTextBlockSize = delegate.getPlainTextBlockSize(channel);
        int signatureSize = delegate.getSignatureSize(channel);

        int maxChunkSize = parameters.getLocalSendBufferSize();
        int headerSizes = SecureMessageHeader.SECURE_MESSAGE_HEADER_SIZE + securityHeaderSize;
        int paddingOverhead = encrypted ? (cipherTextBlockSize > 256 ? 2 : 1) : 0;

        int maxBlockCount = (maxChunkSize - headerSizes - signatureSize - paddingOverhead) / cipherTextBlockSize;
        int maxBodySize = (plainTextBlockSize * maxBlockCount - SequenceHeader.SEQUENCE_HEADER_SIZE);

        while (messageBuffer.readableBytes() > 0) {
            int bodySize = Math.min(messageBuffer.readableBytes(), maxBodySize);

            int paddingSize = encrypted ?
                plainTextBlockSize -
                    (SequenceHeader.SEQUENCE_HEADER_SIZE + bodySize + signatureSize + paddingOverhead) %
                        plainTextBlockSize : 0;

            int plainTextContentSize = SequenceHeader.SEQUENCE_HEADER_SIZE +
                bodySize + signatureSize + paddingSize + paddingOverhead;

            assert (plainTextContentSize % plainTextBlockSize == 0);

            int chunkSize = SecureMessageHeader.SECURE_MESSAGE_HEADER_SIZE + securityHeaderSize +
                (plainTextContentSize / plainTextBlockSize) * cipherTextBlockSize;

            ByteBuf chunkBuffer = BufferUtil.buffer(chunkSize);

            /* Message Header */
            SecureMessageHeader messageHeader = new SecureMessageHeader(
                messageType,
                messageBuffer.readableBytes() > bodySize ? 'C' : 'F',
                chunkSize,
                channel.getChannelId()
            );

            SecureMessageHeader.encode(messageHeader, chunkBuffer);

            /* Security Header */
            delegate.encodeSecurityHeader(channel, chunkBuffer);

            /* Sequence Header */
            SequenceHeader sequenceHeader = new SequenceHeader(
                sequenceNumber.getAndIncrement(),
                requestId
            );

            SequenceHeader.encode(sequenceHeader, chunkBuffer);

            /* Message Body */
            chunkBuffer.writeBytes(messageBuffer, bodySize);

            /* Padding and Signature */
            if (encrypted) {
                writePadding(cipherTextBlockSize, paddingSize, chunkBuffer);
            }

            if (delegate.isSigningEnabled(channel)) {
                ByteBuffer chunkNioBuffer = chunkBuffer.nioBuffer(0, chunkBuffer.writerIndex());

                byte[] signature = delegate.signChunk(channel, chunkNioBuffer);

                chunkBuffer.writeBytes(signature);
            }

            /* Encryption */
            if (encrypted) {
                chunkBuffer.readerIndex(SecureMessageHeader.SECURE_MESSAGE_HEADER_SIZE + securityHeaderSize);

                assert (chunkBuffer.readableBytes() % plainTextBlockSize == 0);

                try {
                    int blockCount = chunkBuffer.readableBytes() / plainTextBlockSize;

                    ByteBuffer chunkNioBuffer = chunkBuffer.nioBuffer(
                        chunkBuffer.readerIndex(), blockCount * cipherTextBlockSize);

                    ByteBuf copyBuffer = chunkBuffer.copy();
                    ByteBuffer plainTextNioBuffer = copyBuffer.nioBuffer();

                    Cipher cipher = delegate.getAndInitializeCipher(channel);

                    if (delegate instanceof AsymmetricDelegate) {
                        for (int blockNumber = 0; blockNumber < blockCount; blockNumber++) {
                            int position = blockNumber * plainTextBlockSize;
                            int limit = (blockNumber + 1) * plainTextBlockSize;
                            plainTextNioBuffer.position(position).limit(limit);

                            int bytesWritten = cipher.doFinal(plainTextNioBuffer, chunkNioBuffer);

                            assert (bytesWritten == cipherTextBlockSize);
                        }
                    } else {
                        cipher.doFinal(plainTextNioBuffer, chunkNioBuffer);
                    }

                    copyBuffer.release();
                } catch (GeneralSecurityException e) {
                    throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
                }
            }

            chunkBuffer.readerIndex(0).writerIndex(chunkSize);

            chunks.add(chunkBuffer);
        }

        lastRequestId = requestId;

        return chunks;
    }

    public long getLastRequestId() {
        return lastRequestId;
    }

    private void writePadding(int cipherTextBlockSize, int paddingSize, ByteBuf buffer) {
        if (cipherTextBlockSize > 256) {
            buffer.writeShort(paddingSize);
        } else {
            buffer.writeByte(paddingSize);
        }

        for (int i = 0; i < paddingSize; i++) {
            buffer.writeByte(paddingSize);
        }

        if (cipherTextBlockSize > 256) {
            // Replace the last byte with the MSB of the 2-byte padding length
            int paddingLengthMsb = paddingSize >> 8;
            buffer.writerIndex(buffer.writerIndex() - 1);
            buffer.writeByte(paddingLengthMsb);
        }
    }

    private static interface Delegate {
        byte[] signChunk(SecureChannel channel, ByteBuffer chunkNioBuffer) throws UaException;

        void encodeSecurityHeader(SecureChannel channel, ByteBuf buffer) throws UaException;

        Cipher getAndInitializeCipher(SecureChannel channel) throws UaException;

        int getSecurityHeaderSize(SecureChannel channel) throws UaException;

        int getCipherTextBlockSize(SecureChannel channel);

        int getPlainTextBlockSize(SecureChannel channel);

        int getSignatureSize(SecureChannel channel);

        boolean isEncryptionEnabled(SecureChannel channel);

        boolean isSigningEnabled(SecureChannel channel);

    }

    private static class AsymmetricDelegate implements Delegate {

        @Override
        public byte[] signChunk(SecureChannel channel, ByteBuffer chunkNioBuffer) throws UaException {
            return SignatureUtil.sign(
                channel.getSecurityPolicy().getAsymmetricSignatureAlgorithm(),
                channel.getKeyPair().getPrivate(),
                chunkNioBuffer
            );
        }

        @Override
        public Cipher getAndInitializeCipher(SecureChannel channel) throws UaException {
            Certificate remoteCertificate = channel.getRemoteCertificate();

            assert (remoteCertificate != null);

            try {
                String transformation = channel.getSecurityPolicy()
                    .getAsymmetricEncryptionAlgorithm().getTransformation();
                Cipher cipher = Cipher.getInstance(transformation);
                cipher.init(Cipher.ENCRYPT_MODE, remoteCertificate.getPublicKey());
                return cipher;
            } catch (GeneralSecurityException e) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
            }
        }

        @Override
        public void encodeSecurityHeader(SecureChannel channel, ByteBuf buffer) throws UaException {
            AsymmetricSecurityHeader header = new AsymmetricSecurityHeader(
                channel.getSecurityPolicy().getSecurityPolicyUri(),
                channel.getLocalCertificateBytes(),
                channel.getRemoteCertificateThumbprint()
            );

            AsymmetricSecurityHeader.encode(header, buffer);
        }

        @Override
        public int getSecurityHeaderSize(SecureChannel channel) throws UaException {
            String securityPolicyUri = channel.getSecurityPolicy().getSecurityPolicyUri();
            byte[] localCertificateBytes = channel.getLocalCertificateBytes().bytes();
            byte[] remoteCertificateThumbprint = channel.getRemoteCertificateThumbprint().bytes();

            return 12 + securityPolicyUri.length() +
                (localCertificateBytes != null ? localCertificateBytes.length : 0) +
                (remoteCertificateThumbprint != null ? remoteCertificateThumbprint.length : 0);
        }

        @Override
        public int getCipherTextBlockSize(SecureChannel channel) {
            return channel.getRemoteAsymmetricCipherTextBlockSize();
        }

        @Override
        public int getPlainTextBlockSize(SecureChannel channel) {
            return channel.getRemoteAsymmetricPlainTextBlockSize();
        }

        @Override
        public int getSignatureSize(SecureChannel channel) {
            return channel.getLocalAsymmetricSignatureSize();
        }

        @Override
        public boolean isEncryptionEnabled(SecureChannel channel) {
            return channel.isAsymmetricEncryptionEnabled();
        }

        @Override
        public boolean isSigningEnabled(SecureChannel channel) {
            return channel.isAsymmetricSigningEnabled();
        }

    }

    private static class SymmetricDelegate implements Delegate {

        private volatile ChannelSecurity.SecuritySecrets securitySecrets;

        @Override
        public void encodeSecurityHeader(SecureChannel channel, ByteBuf buffer) {
            ChannelSecurity channelSecurity = channel.getChannelSecurity();
            long tokenId = channelSecurity != null ? channelSecurity.getCurrentToken().getTokenId().longValue() : 0L;

            SymmetricSecurityHeader.encode(new SymmetricSecurityHeader(tokenId), buffer);

            securitySecrets = channelSecurity != null ? channelSecurity.getCurrentKeys() : null;
        }

        @Override
        public byte[] signChunk(SecureChannel channel, ByteBuffer chunkNioBuffer) throws UaException {
            SecurityAlgorithm signatureAlgorithm = channel.getSecurityPolicy().getSymmetricSignatureAlgorithm();
            byte[] signatureKey = channel.getEncryptionKeys(securitySecrets).getSignatureKey();

            return SignatureUtil.hmac(
                signatureAlgorithm,
                signatureKey,
                chunkNioBuffer
            );
        }

        @Override
        public Cipher getAndInitializeCipher(SecureChannel channel) throws UaException {
            try {
                String transformation = channel.getSecurityPolicy()
                    .getSymmetricEncryptionAlgorithm().getTransformation();
                ChannelSecurity.SecretKeys secretKeys = channel.getEncryptionKeys(securitySecrets);

                SecretKeySpec keySpec = new SecretKeySpec(secretKeys.getEncryptionKey(), "AES");
                IvParameterSpec ivSpec = new IvParameterSpec(secretKeys.getInitializationVector());

                Cipher cipher = Cipher.getInstance(transformation);
                cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

                assert (cipher.getBlockSize() == channel.getSymmetricCipherTextBlockSize());

                return cipher;
            } catch (GeneralSecurityException e) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
            }
        }

        @Override
        public int getSecurityHeaderSize(SecureChannel channel) {
            return SymmetricSecurityHeader.SYMMETRIC_SECURITY_HEADER_SIZE;
        }

        @Override
        public int getCipherTextBlockSize(SecureChannel channel) {
            return channel.getSymmetricCipherTextBlockSize();
        }

        @Override
        public int getPlainTextBlockSize(SecureChannel channel) {
            return channel.getSymmetricPlainTextBlockSize();
        }

        @Override
        public int getSignatureSize(SecureChannel channel) {
            return channel.getSymmetricSignatureSize();
        }

        @Override
        public boolean isEncryptionEnabled(SecureChannel channel) {
            return channel.isSymmetricEncryptionEnabled();
        }

        @Override
        public boolean isSigningEnabled(SecureChannel channel) {
            return channel.isSymmetricSigningEnabled();
        }

    }

}
