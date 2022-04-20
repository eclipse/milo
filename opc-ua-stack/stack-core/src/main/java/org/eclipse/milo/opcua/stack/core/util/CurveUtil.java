/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.stack.core.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.NamedParameterSpec;
import java.security.spec.XECPublicKeySpec;
import java.util.Optional;
import javax.crypto.KeyAgreement;

import com.google.common.primitives.Bytes;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.generators.HKDFBytesGenerator;
import org.bouncycastle.crypto.params.HKDFParameters;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCEdDSAPublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCXDHPublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;


public class CurveUtil {

    public static EcdhExchange newEcdhExchange(SecurityPolicy securityPolicy, String label) throws UaException {
        switch (securityPolicy) {
            case ECC_curve25519:
                return new EcdhExchange(generateKeyPair("X25519", 256), label);
            case ECC_nistP256:
                return new EcdhExchange(generateKeyPair("EC", 256), label);
            default:
                throw new UaException(
                    StatusCodes.Bad_InternalError,
                    "unexpected SecurityPolicy: " + securityPolicy);
        }
    }

    static KeyPair generateKeyPair(String algorithm, int keySize) throws UaException {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm);
            generator.initialize(keySize, new SecureRandom());
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new UaException(StatusCodes.Bad_InternalError, e);
        }
    }

    public static Optional<ECCurve> getNamedCurve(SecurityPolicy securityPolicy) {
        switch (securityPolicy) {
            case ECC_curve25519: {
                X9ECParameters parameters = CustomNamedCurves.getByName("Curve25519");
                return Optional.ofNullable(parameters != null ? parameters.getCurve() : null);
            }
            case ECC_nistP256: {
                X9ECParameters parameters = NISTNamedCurves.getByName("P-256");
                return Optional.ofNullable(parameters != null ? parameters.getCurve() : null);
            }
            default:
                return Optional.empty();
        }
    }

    public static class EcdhExchange {

        private static int calcs = 0;

        private KeyPair keyPair;

        private byte[] localNonce;
        private byte[] remoteNonce;

        private byte[] secretX;
        private byte[] secretY;

        private final String label;

        public EcdhExchange(KeyPair keyPair, String label) throws UaRuntimeException {
            this.keyPair = keyPair;
            this.label = label;

            byte[] xbs = null, ybs = null;

            PublicKey publicKey = keyPair.getPublic();
            if (publicKey instanceof ECPublicKey) {
                BigInteger x = ((ECPublicKey) publicKey).getW().getAffineX();
                BigInteger y = ((ECPublicKey) publicKey).getW().getAffineY();
                xbs = BigIntegers.asUnsignedByteArray(32, x);
                ybs = BigIntegers.asUnsignedByteArray(32, y);

                localNonce = Arrays.concatenate(xbs, ybs);
            } else if (publicKey instanceof BCEdDSAPublicKey) {
                localNonce = ((BCEdDSAPublicKey) publicKey).getPointEncoding();
//                xbs = new byte[16];
//                ybs = new byte[16];
//                byte[] pointEncoding = ((BCEdDSAPublicKey) publicKey).getPointEncoding();
//                assert pointEncoding.length == 32;
//                System.arraycopy(pointEncoding, 0, xbs, 0, 16);
//                System.arraycopy(pointEncoding, 16, ybs, 0, 16);
//
//                localNonce = Arrays.concatenate(xbs, ybs);
            } else if (publicKey instanceof BCXDHPublicKey) {
                localNonce = ((BCXDHPublicKey) publicKey).getUEncoding();
            }

            System.out.println("My PublicKey: " + ByteBufUtil.hexDump(publicKey.getEncoded()));
        }

        public synchronized byte[] getLocalNonce() {
            return localNonce;
        }

        public synchronized void setRemoteNonce(byte[] remoteNonce) {
            this.remoteNonce = remoteNonce;
        }

        public synchronized void calculateSecret(SecurityPolicy securityPolicy) throws UaException {
            BigInteger x = null, y = null;

            if (securityPolicy == SecurityPolicy.ECC_curve25519) {
                x = BigIntegers.fromUnsignedByteArray(remoteNonce, 0, 16);
                y = BigIntegers.fromUnsignedByteArray(remoteNonce, 16, 16);
            } else if (securityPolicy == SecurityPolicy.ECC_nistP256) {
                x = BigIntegers.fromUnsignedByteArray(remoteNonce, 0, 32);
                y = BigIntegers.fromUnsignedByteArray(remoteNonce, 32, 32);
            }

            assert x != null && y != null;

            System.out.println("x: " + x);
            System.out.println("y: " + y);

            ECCurve curve = getNamedCurve(securityPolicy).orElseThrow(
                () ->
                    new UaException(StatusCodes.Bad_InternalError,
                        "no curve defined: " + securityPolicy)
            );

            if (securityPolicy == SecurityPolicy.ECC_nistP256) {
                if (calcs++ % 2 == 0) {
                    System.out.println("Using KeyAgreement");
                    try {
                        KeyFactory keyFactory = KeyFactory.getInstance("EC");
                        PublicKey publicKey = keyFactory.generatePublic(
                            new ECPublicKeySpec(
                                new java.security.spec.ECPoint(x, y),
                                EC5Util.convertToSpec(NISTNamedCurves.getByName("P-256"))
                            )
                        );

                        System.out.println("Their PublicKey: " + ByteBufUtil.hexDump(publicKey.getEncoded()));

                        KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH");
                        keyAgreement.init(keyPair.getPrivate());
                        keyAgreement.doPhase(publicKey, true);
                        byte[] secret = keyAgreement.generateSecret();
                        secretX = new byte[secret.length / 2];
                        secretY = new byte[secret.length / 2];
                        System.arraycopy(secret, 0, secretX, 0, secret.length / 2);
                        System.arraycopy(secret, secret.length / 2, secretY, 0, secret.length / 2);

                        System.out.println("secretX: " + ByteBufUtil.hexDump(secretX));
                        System.out.println("secretY: " + ByteBufUtil.hexDump(secretY));
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("Using math");
                    ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();

                    ECPoint secretPoint = curve.createPoint(x, y)
                        .multiply(privateKey.getS())
                        .normalize();

                    //secretX = secretPoint.getAffineXCoord().getEncoded();
                    //secretY = secretPoint.getAffineYCoord().getEncoded();

                    byte[] secret = secretPoint.getAffineXCoord().getEncoded();
                    secretX = new byte[secret.length / 2];
                    secretY = new byte[secret.length / 2];
                    System.arraycopy(secret, 0, secretX, 0, secret.length / 2);
                    System.arraycopy(secret, secret.length / 2, secretY, 0, secret.length / 2);

                    System.out.println("secretX: " + ByteBufUtil.hexDump(secretX));
                    System.out.println("secretY: " + ByteBufUtil.hexDump(secretY));
                }
            } else if (securityPolicy == SecurityPolicy.ECC_curve25519) {
                try {
                    KeyAgreement keyAgreement = KeyAgreement.getInstance("X25519");
                    keyAgreement.init(keyPair.getPrivate());
                    KeyFactory kf = KeyFactory.getInstance("X25519");

                    // TODO does remoteNonce need to be reversed?
                    BigInteger u = new BigInteger(1, Arrays.reverse(remoteNonce));

//                    ECPublicKeySpec spec = new ECPublicKeySpec(
//                        curve.createPoint(
//                            BigIntegers.fromUnsignedByteArray(remoteNonce, 0, 16),
//                            BigIntegers.fromUnsignedByteArray(remoteNonce, 16, 16)),
//                        EC5Util.convertSpec(EC5Util.convertToSpec(CustomNamedCurves.getByName("Curve25519")))
//                    );

                    XECPublicKeySpec spec = new XECPublicKeySpec(new NamedParameterSpec("X25519"), u);
                    PublicKey pubKey = kf.generatePublic(spec);
                    keyAgreement.doPhase(pubKey, true);
                    byte[] secret = keyAgreement.generateSecret();

                    secretX = new byte[16];
                    secretY = new byte[16];
                    System.arraycopy(secret, 0, secretX, 0, 16);
                    System.arraycopy(secret, 16, secretY, 0, 16);

                    System.out.println("secretX: " + ByteBufUtil.hexDump(secretX));
                    System.out.println("secretY: " + ByteBufUtil.hexDump(secretY));

                    {
                        // keys
                        HKDFBytesGenerator hkdf = new HKDFBytesGenerator(new SHA256Digest());
                        ByteBuf saltBuf = Unpooled.buffer();
                        int L = 64 + 32 + 12; // signature length + key length + IV length
                        saltBuf.writeShortLE(L);
                        saltBuf.writeBytes(localNonce).writeBytes(remoteNonce);
                        saltBuf.writeBytes(label.getBytes(StandardCharsets.UTF_8));
                        byte[] ikm = Bytes.concat(secretX, secretY);
                        byte[] salt = ByteBufUtil.getBytes(saltBuf);
                        System.out.println("ikm: " + ByteBufUtil.hexDump(ikm));
                        System.out.println("salt/info: " + ByteBufUtil.hexDump(salt));
                        hkdf.init(new HKDFParameters(ikm, salt, salt));
                        byte[] keyData = new byte[L];
                        hkdf.generateBytes(keyData, 0, keyData.length);
                        System.out.println("keyData: " + ByteBufUtil.hexDump(keyData));
                        byte[] signingKey = new byte[64];
                        byte[] encryptionKey = new byte[32];
                        byte[] iv = new byte[12];
                        System.arraycopy(keyData, 0, signingKey, 0, 64);
                        System.arraycopy(keyData, 64, encryptionKey, 0, 32);
                        System.arraycopy(keyData, 96, iv, 0, 12);
                        System.out.println("my signingKey: " + ByteBufUtil.hexDump(signingKey));
                        System.out.println("my encryptionKey: " + ByteBufUtil.hexDump(encryptionKey));
                        System.out.println("my iv: " + ByteBufUtil.hexDump(iv));
                    }
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public synchronized byte[] getSecretX() {
            return secretX;
        }

        public synchronized byte[] getSecretY() {
            return secretY;
        }

        public synchronized void dispose() {
            keyPair = null;
            java.util.Arrays.fill(localNonce, (byte) 0);
            java.util.Arrays.fill(remoteNonce, (byte) 0);
            java.util.Arrays.fill(secretX, (byte) 0);
            java.util.Arrays.fill(secretY, (byte) 0);
        }

    }

}
