/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.spec.NamedParameterSpec;
import java.security.spec.XECPublicKeySpec;
import javax.crypto.KeyAgreement;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.HKDFBytesGenerator;
import org.bouncycastle.crypto.params.HKDFParameters;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCXDHPublicKey;
import org.bouncycastle.util.Arrays;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.ChannelSecurity;

public abstract class EccKeyExchange {

    private static final byte[] LABEL_CLIENT =
        "opcua-client".getBytes(StandardCharsets.UTF_8);

    private static final byte[] LABEL_SERVER =
        "opcua-server".getBytes(StandardCharsets.UTF_8);

    protected final KeyPair keyPair;
    protected byte[] clientNonce;
    protected byte[] serverNonce;

    public EccKeyExchange(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public byte[] getClientNonce() {
        return clientNonce;
    }

    public byte[] getServerNonce() {
        return serverNonce;
    }

    public ChannelSecurity.SecretKeys generateClientKeys() {
        return generateKeys(calculateClientSalt());
    }

    public ChannelSecurity.SecretKeys generateServerKeys() {
        return generateKeys(calculateServerSalt());
    }

    private ChannelSecurity.SecretKeys generateKeys(byte[] salt) {
        HKDFBytesGenerator hkdf = new HKDFBytesGenerator(new SHA256Digest());
        byte[] ikm = calculateSecret();
        hkdf.init(new HKDFParameters(ikm, salt, salt));

        byte[] signatureKey = new byte[64];
        byte[] encryptionKey = new byte[32];
        byte[] initializationVector = new byte[12];
        hkdf.generateBytes(signatureKey, 0, 64);
        hkdf.generateBytes(encryptionKey, 0, 32);
        hkdf.generateBytes(initializationVector, 0, 12);

        return new ChannelSecurity.SecretKeys(signatureKey, encryptionKey, initializationVector);
    }

    protected abstract byte[] calculateSecret();

    protected static byte[] calculateSecret(KeyPair keyPair, byte[] publicKeyBytes) {
        try {
            KeyAgreement keyAgreement = KeyAgreement.getInstance("X25519");
            keyAgreement.init(keyPair.getPrivate());
            KeyFactory kf = KeyFactory.getInstance("X25519");

            // TODO should publicKeyBytes be reversed?
            BigInteger u = new BigInteger(1, Arrays.reverse(publicKeyBytes));

            XECPublicKeySpec spec = new XECPublicKeySpec(new NamedParameterSpec("X25519"), u);
            PublicKey pubKey = kf.generatePublic(spec);
            keyAgreement.doPhase(pubKey, true);

            return keyAgreement.generateSecret();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    protected byte[] calculateClientSalt() {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeShortLE(64 + 32 + 12);
        buffer.writeBytes(LABEL_CLIENT);
        buffer.writeBytes(clientNonce);
        buffer.writeBytes(serverNonce);
        return ByteBufUtil.getBytes(buffer);
    }

    protected byte[] calculateServerSalt() {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeShortLE(64 + 32 + 12);
        buffer.writeBytes(LABEL_SERVER);
        buffer.writeBytes(serverNonce);
        buffer.writeBytes(clientNonce);
        return ByteBufUtil.getBytes(buffer);
    }

    public static class ClientKeyExchange extends EccKeyExchange {

        public ClientKeyExchange() throws UaException {
            super(CurveUtil.generateKeyPair("X25519", 256));

            BCXDHPublicKey publicKey = (BCXDHPublicKey) keyPair.getPublic();
            this.clientNonce = publicKey.getUEncoding();
        }

        public void setServerNonce(byte[] serverNonce) {
            super.serverNonce = serverNonce;
        }

        @Override
        protected byte[] calculateSecret() {
            return calculateSecret(keyPair, serverNonce);
        }

    }

    public static class ServerKeyExchange extends EccKeyExchange {

        public ServerKeyExchange() throws UaException {
            super(CurveUtil.generateKeyPair("X25519", 256));

            BCXDHPublicKey publicKey = (BCXDHPublicKey) keyPair.getPublic();
            this.serverNonce = publicKey.getUEncoding();
        }

        public void setClientNonce(byte[] clientNonce) {
            super.clientNonce = clientNonce;
        }

        @Override
        protected byte[] calculateSecret() {
            return calculateSecret(keyPair, clientNonce);
        }

    }

}
