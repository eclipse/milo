/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.channel.headers;

import java.nio.charset.Charset;
import javax.annotation.Nonnull;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;

public class AsymmetricSecurityHeader {

    private final String securityPolicyUri;
    private final ByteString senderCertificate;
    private final ByteString receiverThumbprint;

    /**
     * @param securityPolicyUri  the URI of the Security Policy used to secure the Message.
     * @param senderCertificate  the DER-encoded X509v3 Certificate assigned to the sending Application
     *                           Instance. This field shall be null if the Message is not signed.
     * @param receiverThumbprint the thumbprint of the X509v3 Certificate assigned to the receiving Application
     *                           Instance. The thumbprint is the SHA1 digest of the DER encoded form of the
     *                           Certificate. This indicates what public key was used to encrypt the MessageChunk. This
     *                           field shall be null if the Message is not encrypted.
     */
    public AsymmetricSecurityHeader(@Nonnull String securityPolicyUri,
                                    @Nonnull ByteString senderCertificate,
                                    @Nonnull ByteString receiverThumbprint) {

        Preconditions.checkNotNull(securityPolicyUri);
        Preconditions.checkArgument(securityPolicyUri.getBytes(Charset.forName("UTF-8")).length <= 255,
            "securityPolicyUri length cannot be greater than 255 bytes");

        Preconditions.checkArgument(receiverThumbprint.bytes() == null || receiverThumbprint.length() == 20,
            "receiverThumbprint length must be either null or exactly 20 bytes");

        this.securityPolicyUri = securityPolicyUri;
        this.senderCertificate = senderCertificate;
        this.receiverThumbprint = receiverThumbprint;
    }

    @Nonnull
    public String getSecurityPolicyUri() {
        return securityPolicyUri;
    }

    @Nonnull
    public ByteString getSenderCertificate() {
        return senderCertificate;
    }

    @Nonnull
    public ByteString getReceiverThumbprint() {
        return receiverThumbprint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AsymmetricSecurityHeader that = (AsymmetricSecurityHeader) o;

        return receiverThumbprint.equals(that.receiverThumbprint) &&
            securityPolicyUri.equals(that.securityPolicyUri) &&
            senderCertificate.equals(that.senderCertificate);
    }

    @Override
    public int hashCode() {
        int result = securityPolicyUri.hashCode();
        result = 31 * result + senderCertificate.hashCode();
        result = 31 * result + receiverThumbprint.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("securityPolicyUri", securityPolicyUri)
            .add("senderCertificate", senderCertificate)
            .add("receiverThumbprint", receiverThumbprint)
            .toString();
    }

    public static void encode(AsymmetricSecurityHeader header, ByteBuf buffer) {
        String securityPolicy = header.getSecurityPolicyUri();
        buffer.writeIntLE(securityPolicy.length());
        buffer.writeBytes(securityPolicy.getBytes(Charset.forName("UTF-8")));

        ByteString senderCertificate = header.getSenderCertificate();
        if (senderCertificate.isNull()) {
            buffer.writeIntLE(-1);
        } else {
            buffer.writeIntLE(senderCertificate.length());
            buffer.writeBytes(senderCertificate.bytes());
        }

        ByteString receiverThumbprint = header.getReceiverThumbprint();
        if (receiverThumbprint.isNull()) {
            buffer.writeIntLE(-1);
        } else {
            buffer.writeIntLE(receiverThumbprint.length());
            buffer.writeBytes(receiverThumbprint.bytes());
        }
    }

    public static AsymmetricSecurityHeader decode(ByteBuf buffer, int maxArrayLength, int maxStringLength) {
        /* SecurityPolicyUri */
        int securityPolicyUriLength = buffer.readIntLE();
        if (securityPolicyUriLength > maxStringLength) {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingLimitsExceeded,
                "max array length exceeded");
        }

        byte[] securityPolicyUriBytes = new byte[securityPolicyUriLength];
        buffer.readBytes(securityPolicyUriBytes);

        String securityPolicyUri = new String(
            securityPolicyUriBytes,
            Charset.forName("UTF-8")
        );

        /* SenderCertificate */
        int senderCertificateLength = buffer.readIntLE();
        if (senderCertificateLength > maxArrayLength) {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingLimitsExceeded,
                "max array length exceeded");
        }

        byte[] senderCertificate = null;
        if (senderCertificateLength >= 0) {
            senderCertificate = new byte[senderCertificateLength];
            buffer.readBytes(senderCertificate);
        }

        /* ReceiverCertificateThumbprint */
        int thumbprintLength = buffer.readIntLE();
        if (thumbprintLength > maxArrayLength) {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingLimitsExceeded,
                "max array length exceeded");
        }

        byte[] receiverCertificateThumbprint = null;
        if (thumbprintLength >= 0) {
            receiverCertificateThumbprint = new byte[thumbprintLength];
            buffer.readBytes(receiverCertificateThumbprint);
        }

        return new AsymmetricSecurityHeader(
            securityPolicyUri,
            new ByteString(senderCertificate),
            new ByteString(receiverCertificateThumbprint)
        );
    }

}
