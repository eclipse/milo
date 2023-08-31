package org.eclipse.milo.opcua.stack.core.security;

import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.*;

public interface CertificateManager2 {

    /**
     * Get the {@link KeyPair} belonging to the certificate identified by {@code thumbprint}.
     * <p>
     * {@code thumbprint} is a SHA1 hash of the encoded certificate.
     *
     * @param thumbprint the thumbprint of the certificate.
     * @return the {@link KeyPair} belonging to the certificate identified by {@code thumbprint}.
     */
    Optional<KeyPair> getKeyPair(ByteString thumbprint);

    /**
     * Get the {@link X509Certificate} identified by {@code thumbprint}.
     * <p>
     * {@code thumbprint} is a SHA1 hash of the encoded certificate.
     *
     * @param thumbprint the thumbprint of the certificate.
     * @return the {@link X509Certificate} identified by {@code thumbprint}.
     */
    Optional<X509Certificate> getCertificate(ByteString thumbprint);

    /**
     * Get the {@link X509Certificate} identified by {@code thumbprint} as well as any
     * certificates in its chain.
     *
     * @param thumbprint the thumbprint of the certificate.
     * @return the {@link X509Certificate} identified by {@code thumbprint} as well as any
     *     certificates in its chain.
     */
    Optional<X509Certificate[]> getCertificateChain(ByteString thumbprint);

    Optional<CertificateGroup> getCertificateGroup(NodeId certificateGroupId);

    /**
     * Get the list of Rejected Certificates.
     *
     * @return the list of Rejected {@link X509Certificate}s.
     */
    List<X509Certificate> getRejectedCertificates();

    /**
     * Add {@code certificate} to the Rejected Certificates list.
     *
     * @param certificate the {@link X509Certificate} to add to the Rejected Certificates list.
     */
    void addRejectedCertificate(X509Certificate certificate);

    /**
     * Remove {@code certificate} from the Rejected Certificates list.
     *
     * @param certificate the {@link X509Certificate} to remove from the Rejected Certificates list.
     * @return {@code true} if the certificate was removed.
     */
    boolean removeRejectedCertificate(X509Certificate certificate);

    interface CertificateGroup {

        NodeId getCertificateGroupId();

        List<NodeId> getSupportedCertificateTypeIds();

        TrustListManager getTrustListManager();

        List<Certificate> getCertificates();

        Optional<Certificate> getCertificate(NodeId certificateTypeId);

        Optional<Certificate> removeCertificate(NodeId certificateTypeId);

        void setCertificate(NodeId certificateTypeId, Certificate certificate);


        class Certificate {
            public KeyPair keyPair;
            public X509Certificate certificate;
            public X509Certificate[] certificateChain;

            public Certificate(KeyPair keyPair, X509Certificate certificate, X509Certificate[] certificateChain) {
                this.keyPair = keyPair;
                this.certificate = certificate;
                this.certificateChain = certificateChain;
            }
        }

    }

    class DefaultApplicationCertificateGroup implements CertificateGroup {

        private final Map<NodeId, Certificate> certificates =
            Collections.synchronizedMap(new HashMap<>());

        private final DefaultTrustListManager trustListManager;

        public DefaultApplicationCertificateGroup(File trustListDir) throws IOException {
            trustListManager = new DefaultTrustListManager(trustListDir);
        }

        public DefaultApplicationCertificateGroup(
            File trustListDir,
            KeyPair keyPair,
            X509Certificate[] certificateChain
        ) throws IOException {

            setCertificate(
                NodeIds.RsaSha256ApplicationCertificateType,
                new Certificate(keyPair, certificateChain[0], certificateChain)
            );

            trustListManager = new DefaultTrustListManager(trustListDir);
        }

        @Override
        public NodeId getCertificateGroupId() {
            return NodeIds.ServerConfiguration_CertificateGroups_DefaultApplicationGroup;
        }

        @Override
        public List<NodeId> getSupportedCertificateTypeIds() {
            return List.of(NodeIds.RsaSha256ApplicationCertificateType);
        }

        @Override
        public TrustListManager getTrustListManager() {
            return null; // TODO
        }

        @Override
        public List<Certificate> getCertificates() {
            return List.copyOf(certificates.values());
        }

        @Override
        public Optional<Certificate> getCertificate(NodeId certificateTypeId) {
            return Optional.ofNullable(certificates.get(certificateTypeId));
        }

        @Override
        public Optional<Certificate> removeCertificate(NodeId certificateTypeId) {
            return Optional.ofNullable(certificates.remove(certificateTypeId));
        }

        @Override
        public void setCertificate(NodeId certificateTypeId, Certificate certificate) {
            if (getSupportedCertificateTypeIds().contains(certificateTypeId)) {
                certificates.put(certificateTypeId, certificate);
            } else {
                throw new IllegalArgumentException("unsupported certificate type: " + certificateTypeId);
            }
        }

    }

}
