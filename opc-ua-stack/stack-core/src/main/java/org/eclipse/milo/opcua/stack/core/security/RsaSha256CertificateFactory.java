/*
 * Copyright (c) 2023 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.security;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.eclipse.milo.opcua.stack.core.util.SelfSignedCertificateGenerator;

public abstract class RsaSha256CertificateFactory implements CertificateFactory {

    private static final int DEFAULT_KEY_LENGTH = 2048;

    @Override
    public KeyPair createKeyPair(NodeId certificateTypeId) throws NoSuchAlgorithmException {
        if (NodeIds.RsaSha256ApplicationCertificateType.equals(certificateTypeId)) {
            return createRsaSha256KeyPair();
        } else {
            throw new UnsupportedOperationException("certificateTypeId: " + certificateTypeId);
        }
    }

    @Override
    public X509Certificate[] createCertificateChain(NodeId certificateTypeId, KeyPair keyPair) throws Exception {
        if (NodeIds.RsaSha256ApplicationCertificateType.equals(certificateTypeId)) {
            return createRsaSha256CertificateChain(keyPair);
        } else {
            throw new UnsupportedOperationException("certificateTypeId: " + certificateTypeId);
        }
    }

    @Override
    public ByteString createSigningRequest(NodeId certificateTypeId, KeyPair keyPair, X500Name subjectName, String sanUri, List<String> dnsNames, List<String> ipAddresses) throws Exception {
        if (NodeIds.RsaSha256ApplicationCertificateType.equals(certificateTypeId)) {
            return createRsaSha256SigningRequest(keyPair, subjectName, sanUri, dnsNames, ipAddresses);
        } else {
            throw new UnsupportedOperationException("certificateTypeId: " + certificateTypeId);
        }
    }

    protected KeyPair createRsaSha256KeyPair() throws NoSuchAlgorithmException {
        return SelfSignedCertificateGenerator.generateRsaKeyPair(DEFAULT_KEY_LENGTH);
    }

    protected abstract X509Certificate[] createRsaSha256CertificateChain(KeyPair keyPair) throws Exception;

    protected ByteString createRsaSha256SigningRequest(
        KeyPair keyPair,
        X500Name subjectName,
        String sanUri,
        List<String> dnsNames,
        List<String> ipAddresses
    ) throws Exception {

        PKCS10CertificationRequest csr = CertificateUtil.generateCsr(
            keyPair,
            subjectName,
            sanUri,
            dnsNames,
            ipAddresses,
            "SHA256withRSA"
        );

        return ByteString.of(csr.getEncoded());
    }

}
