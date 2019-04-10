/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.channel;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.util.ReferenceCountUtil;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.headers.AsymmetricSecurityHeader;
import org.eclipse.milo.opcua.stack.core.channel.headers.SecureMessageHeader;
import org.eclipse.milo.opcua.stack.core.channel.headers.SequenceHeader;
import org.eclipse.milo.opcua.stack.core.channel.headers.SymmetricSecurityHeader;
import org.eclipse.milo.opcua.stack.core.channel.messages.ErrorMessage;
import org.eclipse.milo.opcua.stack.core.security.SecurityAlgorithm;
import org.eclipse.milo.opcua.stack.core.util.BufferUtil;
import org.eclipse.milo.opcua.stack.core.util.SignatureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ChunkDecoder {

    private final AsymmetricDecoder asymmetricDecoder = new AsymmetricDecoder();
    private final SymmetricDecoder symmetricDecoder = new SymmetricDecoder();

    private volatile long lastSequenceNumber = -1L;

    private final ChannelParameters parameters;
    private final int maxArrayLength;
    private final int maxStringLength;

    public ChunkDecoder(ChannelParameters parameters, int maxArrayLength, int maxStringLength) {
        this.parameters = parameters;
        this.maxArrayLength = maxArrayLength;
        this.maxStringLength = maxStringLength;
    }

    public void decodeAsymmetric(
        SecureChannel channel,
        List<ByteBuf> chunkBuffers,
        ChunkDecoder.Callback callback) {

        decode(asymmetricDecoder, channel, chunkBuffers, callback);
    }

    public void decodeSymmetric(
        SecureChannel channel,
        List<ByteBuf> chunkBuffers,
        ChunkDecoder.Callback callback) {

        decode(symmetricDecoder, channel, chunkBuffers, callback);
    }

    private static void decode(
        AbstractDecoder decoder,
        SecureChannel channel,
        List<ByteBuf> chunkBuffers,
        Callback callback) {

        CompositeByteBuf composite = BufferUtil.compositeBuffer();

        try {
            decoder.decode(channel, composite, chunkBuffers, callback);
        } catch (MessageAbortedException e) {
            callback.onMessageAborted(e);

            safeReleaseBuffers(composite, chunkBuffers);
        } catch (UaException e) {
            callback.onDecodingError(e);

            safeReleaseBuffers(composite, chunkBuffers);
        }
    }

    private static void safeReleaseBuffers(CompositeByteBuf composite, List<ByteBuf> chunkBuffers) {
        if (composite.refCnt() > 0) {
            ReferenceCountUtil.safeRelease(composite);
        }
        chunkBuffers.forEach(b -> {
            if (b.refCnt() > 0) {
                ReferenceCountUtil.safeRelease(b);
            }
        });
    }

    public interface Callback {

        void onDecodingError(UaException ex);

        void onMessageAborted(MessageAbortedException ex);

        void onMessageDecoded(ByteBuf message, long requestId);

    }

    private abstract class AbstractDecoder {

        protected final Logger logger = LoggerFactory.getLogger(getClass());

        void decode(
            SecureChannel channel,
            CompositeByteBuf composite,
            List<ByteBuf> chunkBuffers,
            Callback callback) throws UaException {

            int signatureSize = getSignatureSize(channel);
            int cipherTextBlockSize = getCipherTextBlockSize(channel);

            boolean encrypted = isEncryptionEnabled(channel);
            boolean signed = isSigningEnabled(channel);

            long requestId = -1L;

            for (ByteBuf chunkBuffer : chunkBuffers) {
                final char chunkType = (char) chunkBuffer.getByte(3);

                chunkBuffer.skipBytes(SecureMessageHeader.SECURE_MESSAGE_HEADER_SIZE);

                readSecurityHeader(channel, chunkBuffer);

                if (encrypted) {
                    decryptChunk(channel, chunkBuffer);
                }

                int encryptedStart = chunkBuffer.readerIndex();
                chunkBuffer.readerIndex(0);

                if (signed) {
                    verifyChunk(channel, chunkBuffer);
                }

                final int paddingSize = encrypted ? getPaddingSize(cipherTextBlockSize, signatureSize, chunkBuffer) : 0;
                final int bodyEnd = chunkBuffer.readableBytes() - signatureSize - paddingSize;

                chunkBuffer.readerIndex(encryptedStart);

                SequenceHeader sequenceHeader = SequenceHeader.decode(chunkBuffer);
                long sequenceNumber = sequenceHeader.getSequenceNumber();
                requestId = sequenceHeader.getRequestId();

                if (lastSequenceNumber == -1) {
                    lastSequenceNumber = sequenceNumber;
                } else {
                    if (lastSequenceNumber + 1 != sequenceNumber) {
                        String message = String.format(
                            "expected sequence number %s but received %s",
                            lastSequenceNumber + 1, sequenceNumber);

                        throw new UaException(StatusCodes.Bad_SequenceNumberInvalid, message);
                    }

                    lastSequenceNumber = sequenceNumber;
                }

                ByteBuf bodyBuffer = chunkBuffer.readSlice(bodyEnd - chunkBuffer.readerIndex());

                if (chunkType == 'A') {
                    ErrorMessage errorMessage = ErrorMessage.decode(bodyBuffer);

                    throw new MessageAbortedException(errorMessage.getError(), errorMessage.getReason(), requestId);
                }

                composite.addComponent(bodyBuffer);
                composite.writerIndex(composite.writerIndex() + bodyBuffer.readableBytes());
            }

            if (parameters.getLocalMaxMessageSize() > 0 &&
                composite.readableBytes() > parameters.getLocalMaxMessageSize()) {

                String errorMessage = String.format(
                    "message size exceeds configured limit: %s > %s",
                    composite.readableBytes(), parameters.getLocalMaxMessageSize());

                throw new UaException(StatusCodes.Bad_TcpMessageTooLarge, errorMessage);
            }

            callback.onMessageDecoded(composite, requestId);
        }

        private void decryptChunk(SecureChannel channel, ByteBuf chunkBuffer) throws UaException {
            int cipherTextBlockSize = getCipherTextBlockSize(channel);
            int blockCount = chunkBuffer.readableBytes() / cipherTextBlockSize;

            int plainTextBufferSize = cipherTextBlockSize * blockCount;

            ByteBuf plainTextBuffer = BufferUtil.pooledBuffer(plainTextBufferSize);

            ByteBuffer plainTextNioBuffer = plainTextBuffer
                .writerIndex(plainTextBufferSize)
                .nioBuffer();

            ByteBuffer chunkNioBuffer = chunkBuffer.nioBuffer();

            try {
                Cipher cipher = getCipher(channel);

                assert (chunkBuffer.readableBytes() % cipherTextBlockSize == 0);

                if (isAsymmetric()) {
                    for (int blockNumber = 0; blockNumber < blockCount; blockNumber++) {
                        ((Buffer) chunkNioBuffer).limit(chunkNioBuffer.position() + cipherTextBlockSize);

                        cipher.doFinal(chunkNioBuffer, plainTextNioBuffer);
                    }
                } else {
                    cipher.doFinal(chunkNioBuffer, plainTextNioBuffer);
                }

                /* Write plainTextBuffer back into the chunk buffer we decrypted from. */
                ((Buffer) plainTextNioBuffer).flip(); // limit = pos, pos = 0

                chunkBuffer.writerIndex(chunkBuffer.readerIndex());
                chunkBuffer.writeBytes(plainTextNioBuffer);
            } catch (GeneralSecurityException e) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
            } finally {
                plainTextBuffer.release();
            }
        }

        private int getPaddingSize(int cipherTextBlockSize, int signatureSize, ByteBuf buffer) {
            int lastPaddingByteOffset = buffer.readableBytes() - signatureSize - 1;

            return cipherTextBlockSize <= 256 ?
                buffer.getUnsignedByte(lastPaddingByteOffset) + 1 :
                buffer.getUnsignedShortLE(lastPaddingByteOffset - 1) + 2;
        }

        protected abstract void readSecurityHeader(SecureChannel channel, ByteBuf chunkBuffer) throws UaException;

        protected abstract Cipher getCipher(SecureChannel channel) throws UaException;

        protected abstract int getCipherTextBlockSize(SecureChannel channel);

        protected abstract int getSignatureSize(SecureChannel channel);

        protected abstract void verifyChunk(SecureChannel channel, ByteBuf chunkBuffer) throws UaException;

        protected abstract boolean isAsymmetric();

        protected abstract boolean isEncryptionEnabled(SecureChannel channel);

        protected abstract boolean isSigningEnabled(SecureChannel channel);

    }

    private final class AsymmetricDecoder extends AbstractDecoder {

        @Override
        public void readSecurityHeader(SecureChannel channel, ByteBuf chunkBuffer) {
            AsymmetricSecurityHeader.decode(chunkBuffer, maxArrayLength, maxStringLength);
        }

        @Override
        public Cipher getCipher(SecureChannel channel) throws UaException {
            try {
                String transformation = channel.getSecurityPolicy()
                    .getAsymmetricEncryptionAlgorithm().getTransformation();
                Cipher cipher = Cipher.getInstance(transformation);
                cipher.init(Cipher.DECRYPT_MODE, channel.getKeyPair().getPrivate());
                return cipher;
            } catch (GeneralSecurityException e) {
                throw new UaException(StatusCodes.Bad_InternalError, e);
            }
        }

        @Override
        public int getCipherTextBlockSize(SecureChannel channel) {
            return channel.getLocalAsymmetricCipherTextBlockSize();
        }

        @Override
        public int getSignatureSize(SecureChannel channel) {
            return channel.getRemoteAsymmetricSignatureSize();
        }

        @Override
        public void verifyChunk(SecureChannel channel, ByteBuf chunkBuffer) throws UaException {
            String transformation = channel.getSecurityPolicy().getAsymmetricSignatureAlgorithm().getTransformation();
            int signatureSize = channel.getRemoteAsymmetricSignatureSize();

            ByteBuffer chunkNioBuffer = chunkBuffer.nioBuffer(0, chunkBuffer.writerIndex());
            ((Buffer) chunkNioBuffer).position(0);
            ((Buffer) chunkNioBuffer).limit(chunkBuffer.writerIndex() - signatureSize);

            try {
                Signature signature = Signature.getInstance(transformation);

                signature.initVerify(channel.getRemoteCertificate().getPublicKey());
                signature.update(chunkNioBuffer);

                byte[] signatureBytes = new byte[signatureSize];
                ((Buffer) chunkNioBuffer).limit(chunkNioBuffer.position() + signatureSize);
                chunkNioBuffer.get(signatureBytes);

                if (!signature.verify(signatureBytes)) {
                    throw new UaException(StatusCodes.Bad_SecurityChecksFailed, "could not verify signature");
                }
            } catch (NoSuchAlgorithmException e) {
                throw new UaException(StatusCodes.Bad_InternalError, e);
            } catch (SignatureException e) {
                throw new UaException(StatusCodes.Bad_ApplicationSignatureInvalid, e);
            } catch (InvalidKeyException e) {
                throw new UaException(StatusCodes.Bad_CertificateInvalid, e);
            }
        }

        @Override
        protected boolean isAsymmetric() {
            return true;
        }

        @Override
        public boolean isEncryptionEnabled(SecureChannel channel) {
            return channel.isAsymmetricEncryptionEnabled();
        }

        @Override
        public boolean isSigningEnabled(SecureChannel channel) {
            return channel.isAsymmetricEncryptionEnabled();
        }

    }

    private final class SymmetricDecoder extends AbstractDecoder {

        private volatile ChannelSecurity.SecurityKeys securityKeys;
        private volatile Cipher cipher = null;
        private volatile long cipherId = -1;

        @Override
        public void readSecurityHeader(SecureChannel channel, ByteBuf chunkBuffer) throws UaException {
            long receivedTokenId = SymmetricSecurityHeader.decode(chunkBuffer).getTokenId();

            ChannelSecurity channelSecurity = channel.getChannelSecurity();

            if (channelSecurity == null) {
                if (receivedTokenId != 0L) {
                    throw new UaException(StatusCodes.Bad_SecureChannelTokenUnknown,
                        "unknown secure channel token: " + receivedTokenId);
                }
            } else {
                long currentTokenId = channelSecurity.getCurrentToken().getTokenId().longValue();

                if (receivedTokenId == currentTokenId) {
                    securityKeys = channelSecurity.getCurrentKeys();
                } else {
                    long previousTokenId = channelSecurity.getPreviousToken()
                        .map(t -> t.getTokenId().longValue())
                        .orElse(-1L);

                    logger.debug("Attempting to use SecurityKeys from previousTokenId={}", previousTokenId);

                    if (receivedTokenId != previousTokenId) {
                        logger.warn(
                            "receivedTokenId={} did not match previousTokenId={}",
                            receivedTokenId, previousTokenId);

                        throw new UaException(StatusCodes.Bad_SecureChannelTokenUnknown,
                            "unknown secure channel token: " + receivedTokenId);
                    }

                    if (channel.isSymmetricEncryptionEnabled() && channelSecurity.getPreviousKeys().isPresent()) {
                        securityKeys = channelSecurity.getPreviousKeys().get();
                    }
                }

                if (cipherId != receivedTokenId && channel.isSymmetricEncryptionEnabled()) {
                    cipher = initCipher(channel);
                    cipherId = receivedTokenId;
                }
            }
        }

        @Override
        public Cipher getCipher(SecureChannel channel) {
            assert cipher != null;
            return cipher;
        }

        @Override
        public int getCipherTextBlockSize(SecureChannel channel) {
            return channel.getSymmetricBlockSize();
        }

        @Override
        public int getSignatureSize(SecureChannel channel) {
            return channel.getSymmetricSignatureSize();
        }

        @Override
        public void verifyChunk(SecureChannel channel, ByteBuf chunkBuffer) throws UaException {
            SecurityAlgorithm securityAlgorithm = channel.getSecurityPolicy().getSymmetricSignatureAlgorithm();
            byte[] secretKey = channel.getDecryptionKeys(securityKeys).getSignatureKey();
            int signatureSize = channel.getSymmetricSignatureSize();

            ByteBuffer chunkNioBuffer = chunkBuffer.nioBuffer(0, chunkBuffer.writerIndex());
            ((Buffer) chunkNioBuffer).position(0);
            ((Buffer) chunkNioBuffer).limit(chunkBuffer.writerIndex() - signatureSize);

            byte[] signature = SignatureUtil.hmac(securityAlgorithm, secretKey, chunkNioBuffer);

            byte[] signatureBytes = new byte[signatureSize];
            ((Buffer) chunkNioBuffer).limit(chunkNioBuffer.position() + signatureSize);
            chunkNioBuffer.get(signatureBytes);

            if (!MessageDigest.isEqual(signature, signatureBytes)) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed, "could not verify signature");
            }
        }

        @Override
        protected boolean isAsymmetric() {
            return false;
        }

        @Override
        public boolean isEncryptionEnabled(SecureChannel channel) {
            return channel.isSymmetricEncryptionEnabled();
        }

        @Override
        public boolean isSigningEnabled(SecureChannel channel) {
            return channel.isSymmetricSigningEnabled();
        }

        private Cipher initCipher(SecureChannel channel) throws UaException {
            try {
                String transformation = channel.getSecurityPolicy()
                    .getSymmetricEncryptionAlgorithm().getTransformation();

                ChannelSecurity.SecretKeys decryptionKeys = channel.getDecryptionKeys(securityKeys);

                SecretKeySpec keySpec = new SecretKeySpec(decryptionKeys.getEncryptionKey(), "AES");
                IvParameterSpec ivSpec = new IvParameterSpec(decryptionKeys.getInitializationVector());

                Cipher cipher = Cipher.getInstance(transformation);
                cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

                return cipher;
            } catch (GeneralSecurityException e) {
                throw new UaException(StatusCodes.Bad_InternalError, e);
            }
        }

    }

}
