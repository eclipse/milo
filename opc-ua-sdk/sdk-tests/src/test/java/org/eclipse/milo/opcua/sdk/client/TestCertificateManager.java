/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.eclipse.milo.opcua.stack.core.security.CertificateManager;
import org.eclipse.milo.opcua.stack.core.security.MemoryKeyManager;
import org.eclipse.milo.opcua.stack.core.security.MemoryTrustListManager;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class TestCertificateManager implements CertificateManager {

    private final KeyPair keyPair;
    private final X509Certificate certificate;

    private final DefaultApplicationGroup certificateGroup;

    public TestCertificateManager(KeyPair keyPair, X509Certificate certificate) throws Exception {
        this.keyPair = keyPair;
        this.certificate = certificate;

        certificateGroup = DefaultApplicationGroup.createAndInitialize(
            new MemoryKeyManager(),
            new MemoryTrustListManager(),
            new CertificateFactory() {
                @Override
                public KeyPair createKeyPair(NodeId certificateTypeId) {
                    return keyPair;
                }

                @Override
                public X509Certificate[] createCertificateChain(NodeId certificateTypeId, KeyPair keyPair) {
                    return new X509Certificate[]{certificate};
                }
            }
        );
    }

    @Override
    public Optional<KeyPair> getKeyPair(ByteString thumbprint) {
        return Optional.of(keyPair);
    }

    @Override
    public Optional<X509Certificate> getCertificate(ByteString thumbprint) {
        return Optional.of(certificate);
    }

    @Override
    public Optional<X509Certificate[]> getCertificateChain(ByteString thumbprint) {
        return getCertificate(thumbprint).map(c -> new X509Certificate[]{c});
    }

    @Override
    public Optional<CertificateGroup> getCertificateGroup(ByteString thumbprint) {
        return Optional.of(certificateGroup);
    }

    @Override
    public Optional<CertificateGroup> getCertificateGroup(NodeId certificateGroupId) {
        return Optional.of(certificateGroup);
    }

    @Override
    public List<CertificateGroup> getCertificateGroups() {
        return List.of(certificateGroup);
    }

    @Override
    public List<X509Certificate> getRejectedCertificates() {
        return Collections.emptyList();
    }

    @Override
    public void addRejectedCertificate(X509Certificate certificate) {

    }

    @Override
    public boolean removeRejectedCertificate(X509Certificate certificate) {
        return false;
    }

}
