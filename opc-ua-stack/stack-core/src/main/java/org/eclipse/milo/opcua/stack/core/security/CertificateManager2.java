package org.eclipse.milo.opcua.stack.core.security;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

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
     * Remove the certificate identified by {@code thumbprint} from the Rejected Certificates list.
     *
     * @param thumbprint the certificate thumbprint.
     * @return {@code true} if a certificate with a matching thumbprint was found.
     */
    boolean removeRejectedCertificate(ByteString thumbprint);

    interface CertificateGroup {

        NodeId getCertificateGroupId();

        List<NodeId> getSupportedCertificateTypeIds();

        TrustListManager getTrustListManager();

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
