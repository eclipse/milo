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
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.CompositeByteBuf;
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

public class ChunkDecoder {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Delegate asymmetricDelegate = new AsymmetricDelegate();
    private final Delegate symmetricDelegate = new SymmetricDelegate();

    private volatile long lastSequenceNumber = -1L;
    private volatile long lastRequestId;

    private final ChannelParameters parameters;

    public ChunkDecoder(ChannelParameters parameters) {
        this.parameters = parameters;
    }

    public ByteBuf decodeAsymmetric(SecureChannel channel, List<ByteBuf> chunkBuffers) throws UaException {
        return decode(asymmetricDelegate, channel, chunkBuffers);
    }

    public ByteBuf decodeSymmetric(SecureChannel channel, List<ByteBuf> chunkBuffers) throws UaException {
        return decode(symmetricDelegate, channel, chunkBuffers);
    }

    private ByteBuf decode(Delegate delegate, SecureChannel channel, List<ByteBuf> chunkBuffers) throws UaException {
        CompositeByteBuf composite = BufferUtil.compositeBuffer();

        int signatureSize = delegate.getSignatureSize(channel);
        int cipherTextBlockSize = delegate.getCipherTextBlockSize(channel);

        boolean encrypted = delegate.isEncryptionEnabled(channel);
        boolean signed = delegate.isSigningEnabled(channel);

        for (ByteBuf chunkBuffer : chunkBuffers) {
            final char chunkType = (char) chunkBuffer.getByte(3);

            chunkBuffer.skipBytes(SecureMessageHeader.SECURE_MESSAGE_HEADER_SIZE);

            delegate.readSecurityHeader(channel, chunkBuffer);

            if (encrypted) {
                decryptChunk(delegate, channel, chunkBuffer);
            }

            int encryptedStart = chunkBuffer.readerIndex();
            chunkBuffer.readerIndex(0);

            if (signed) {
                delegate.verifyChunk(channel, chunkBuffer);
            }

            final int paddingSize = encrypted ? getPaddingSize(cipherTextBlockSize, signatureSize, chunkBuffer) : 0;
            final int bodyEnd = chunkBuffer.readableBytes() - signatureSize - paddingSize;

            chunkBuffer.readerIndex(encryptedStart);

            SequenceHeader sequenceHeader = SequenceHeader.decode(chunkBuffer);
            long sequenceNumber = sequenceHeader.getSequenceNumber();
            lastRequestId = sequenceHeader.getRequestId();

            if (lastSequenceNumber == -1) {
                lastSequenceNumber = sequenceNumber;
            } else {
                if (lastSequenceNumber + 1 != sequenceNumber) {
                    String message = String.format("expected sequence number %s but received %s",
                        lastSequenceNumber + 1, sequenceNumber);

                    logger.error(message);
                    logger.error(ByteBufUtil.hexDump(chunkBuffer, 0, chunkBuffer.writerIndex()));

                    throw new UaException(StatusCodes.Bad_SecurityChecksFailed, message);
                }

                lastSequenceNumber = sequenceNumber;
            }

            ByteBuf bodyBuffer = chunkBuffer.readSlice(bodyEnd - chunkBuffer.readerIndex());

            if (chunkType == 'A') {
                ErrorMessage errorMessage = ErrorMessage.decode(bodyBuffer);

                throw new MessageAbortedException(errorMessage.getError(), errorMessage.getReason());
            }

            composite.addComponent(bodyBuffer);
            composite.writerIndex(composite.writerIndex() + bodyBuffer.readableBytes());
        }

        return composite.order(ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * @return the most recently decoded request id.
     */
    public long getLastRequestId() {
        return lastRequestId;
    }

    private void decryptChunk(Delegate delegate, SecureChannel channel, ByteBuf chunkBuffer) throws UaException {
        int cipherTextBlockSize = delegate.getCipherTextBlockSize(channel);
        int blockCount = chunkBuffer.readableBytes() / cipherTextBlockSize;

        int plainTextBufferSize = cipherTextBlockSize * blockCount;

        ByteBuf plainTextBuffer = BufferUtil.buffer(plainTextBufferSize);

        ByteBuffer plainTextNioBuffer = plainTextBuffer
            .writerIndex(plainTextBufferSize)
            .nioBuffer();

        ByteBuffer chunkNioBuffer = chunkBuffer.nioBuffer();

        try {
            Cipher cipher = delegate.getCipher(channel);

            assert (chunkBuffer.readableBytes() % cipherTextBlockSize == 0);

            if (delegate instanceof AsymmetricDelegate) {
                for (int blockNumber = 0; blockNumber < blockCount; blockNumber++) {
                    chunkNioBuffer.limit(chunkNioBuffer.position() + cipherTextBlockSize);

                    cipher.doFinal(chunkNioBuffer, plainTextNioBuffer);
                }
            } else {
                cipher.doFinal(chunkNioBuffer, plainTextNioBuffer);
            }
        } catch (GeneralSecurityException e) {
            throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
        }

        /* Write plainTextBuffer back into the chunk buffer we decrypted from. */
        plainTextNioBuffer.flip(); // limit = pos, pos = 0

        chunkBuffer.writerIndex(chunkBuffer.readerIndex());
        chunkBuffer.writeBytes(plainTextNioBuffer);

        plainTextBuffer.release();
    }

    private int getPaddingSize(int cipherTextBlockSize, int signatureSize, ByteBuf buffer) {
        int lastPaddingByteOffset = buffer.readableBytes() - signatureSize - 1;

        return cipherTextBlockSize <= 256 ?
            buffer.getUnsignedByte(lastPaddingByteOffset) + 1 :
            buffer.getUnsignedShort(lastPaddingByteOffset - 1) + 2;
    }

    private static interface Delegate {
        void readSecurityHeader(SecureChannel channel, ByteBuf chunkBuffer) throws UaException;

        Cipher getCipher(SecureChannel channel) throws UaException;

        int getCipherTextBlockSize(SecureChannel channel);

        int getSignatureSize(SecureChannel channel);

        void verifyChunk(SecureChannel channel, ByteBuf chunkBuffer) throws UaException;

        boolean isEncryptionEnabled(SecureChannel channel);

        boolean isSigningEnabled(SecureChannel channel);

    }

    private static class AsymmetricDelegate implements Delegate {

        @Override
        public void readSecurityHeader(SecureChannel channel, ByteBuf chunkBuffer) {
            AsymmetricSecurityHeader.decode(chunkBuffer);
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
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
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
            chunkNioBuffer.position(0).limit(chunkBuffer.writerIndex() - signatureSize);

            try {
                Signature signature = Signature.getInstance(transformation);

                signature.initVerify(channel.getRemoteCertificate().getPublicKey());
                signature.update(chunkNioBuffer);

                byte[] signatureBytes = new byte[signatureSize];
                chunkNioBuffer.limit(chunkNioBuffer.position() + signatureSize);
                chunkNioBuffer.get(signatureBytes);

                if (!signature.verify(signatureBytes)) {
                    throw new UaException(StatusCodes.Bad_SecurityChecksFailed, "could not verify signature");
                }
            } catch (NoSuchAlgorithmException | SignatureException e) {
                throw new UaException(StatusCodes.Bad_InternalError, e);
            } catch (InvalidKeyException e) {
                throw new UaException(StatusCodes.Bad_CertificateInvalid, e);
            }
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

    private static class SymmetricDelegate implements Delegate {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private volatile ChannelSecurity.SecuritySecrets securitySecrets;

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
                    securitySecrets = channelSecurity.getCurrentKeys();
                } else {
                    long previousTokenId = channelSecurity.getPreviousToken()
                        .map(t -> t.getTokenId().longValue())
                        .orElse(-1L);

                    logger.debug("Attempting to use SecuritySecrets from previousTokenId={}", previousTokenId);

                    if (receivedTokenId != previousTokenId) {
                        logger.warn(
                            "receivedTokenId={} did not match previousTokenId={}",
                            receivedTokenId, previousTokenId);

                        throw new UaException(StatusCodes.Bad_SecureChannelTokenUnknown,
                            "unknown secure channel token: " + receivedTokenId);
                    }

                    if (channel.isSymmetricEncryptionEnabled() && channelSecurity.getPreviousKeys().isPresent()) {
                        securitySecrets = channelSecurity.getPreviousKeys().get();
                    }
                }
            }
        }

        @Override
        public Cipher getCipher(SecureChannel channel) throws UaException {
            try {
                String transformation = channel.getSecurityPolicy()
                    .getSymmetricEncryptionAlgorithm().getTransformation();

                ChannelSecurity.SecretKeys decryptionKeys = channel.getDecryptionKeys(securitySecrets);

                SecretKeySpec keySpec = new SecretKeySpec(decryptionKeys.getEncryptionKey(), "AES");
                IvParameterSpec ivSpec = new IvParameterSpec(decryptionKeys.getInitializationVector());

                Cipher cipher = Cipher.getInstance(transformation);
                cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

                return cipher;
            } catch (GeneralSecurityException e) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
            }
        }

        @Override
        public int getCipherTextBlockSize(SecureChannel channel) {
            return channel.getSymmetricCipherTextBlockSize();
        }

        @Override
        public int getSignatureSize(SecureChannel channel) {
            return channel.getSymmetricSignatureSize();
        }

        @Override
        public void verifyChunk(SecureChannel channel, ByteBuf chunkBuffer) throws UaException {
            SecurityAlgorithm securityAlgorithm = channel.getSecurityPolicy().getSymmetricSignatureAlgorithm();
            byte[] secretKey = channel.getDecryptionKeys(securitySecrets).getSignatureKey();
            int signatureSize = channel.getSymmetricSignatureSize();

            ByteBuffer chunkNioBuffer = chunkBuffer.nioBuffer(0, chunkBuffer.writerIndex());
            chunkNioBuffer.position(0).limit(chunkBuffer.writerIndex() - signatureSize);

            byte[] signature = SignatureUtil.hmac(securityAlgorithm, secretKey, chunkNioBuffer);

            byte[] signatureBytes = new byte[signatureSize];
            chunkNioBuffer.limit(chunkNioBuffer.position() + signatureSize);
            chunkNioBuffer.get(signatureBytes);

            if (!Arrays.equals(signature, signatureBytes)) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed, "could not verify signature");
            }
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
