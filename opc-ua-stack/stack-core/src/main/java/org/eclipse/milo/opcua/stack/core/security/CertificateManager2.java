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
     * Get the {@link X509Certificate} identified by {@code certificateGroupId} and
     * {@code certificateTypeId}.
     *
     * @param certificateGroupId the {@link NodeId} of the CertificateGroup the certificate
     *     belongs to.
     * @param certificateTypeId the {@link NodeId} of the CertificateType identifying the type of
     *     certificate.
     * @return the {@link X509Certificate} identified by {@code certificateGroupId} and
     *     {@code certificateTypeId}.
     */
    Optional<X509Certificate> getCertificate(NodeId certificateGroupId, NodeId certificateTypeId);

    /**
     * Get the {@link X509Certificate} identified by {@code thumbprint} as well as any
     * certificates in its chain.
     *
     * @param thumbprint the thumbprint of the certificate.
     * @return the {@link X509Certificate} identified by {@code thumbprint} as well as any
     *     certificates in its chain.
     */
    Optional<X509Certificate[]> getCertificateChain(ByteString thumbprint);

    /**
     * Get the {@link X509Certificate} identified by {@code certificateGroupId} and
     * {@code certificateTypeId} as well as any certificates in its chain.
     *
     * @param certificateGroupId the {@link NodeId} of the CertificateGroup the certificate
     *     belongs to.
     * @param certificateTypeId the {@link NodeId} of the CertificateType identifying the type of
     *     certificate.
     * @return the {@link X509Certificate} identified by {@code certificateGroupId} and
     *     {@code certificateTypeId} as well as any certificates in its chain.
     */
    Optional<X509Certificate[]> getCertificateChain(NodeId certificateGroupId, NodeId certificateTypeId);

    Optional<CertificateGroup> getCertificateGroup(NodeId certificateGroupId);

    interface CertificateGroup {

        NodeId getCertificateGroupId();

        List<NodeId> getSupportedCertificateTypeIds();

        Optional<KeyPair> getKeyPair(NodeId certificateTypeId);

        Optional<X509Certificate> getCertificate(NodeId certificateTypeId);

        Optional<X509Certificate[]> getCertificateChain(NodeId certificateTypeId);

        TrustListManager getTrustListManager();

        void addCertificate(NodeId certificateTypeId, X509Certificate certificate, KeyPair keyPair);

        void addCertificate(NodeId certificateTypeId, X509Certificate[] certificateChain, KeyPair keyPair);

        void removeCertificate(NodeId certificateTypeId);

    }

    class DefaultApplicationCertificateGroup implements CertificateGroup {

        static class Entry {
            KeyPair keyPair;
            X509Certificate certificate;
            X509Certificate[] certificateChain;

            Entry(KeyPair keyPair, X509Certificate certificate, X509Certificate[] certificateChain) {
                this.keyPair = keyPair;
                this.certificate = certificate;
                this.certificateChain = certificateChain;
            }
        }

        private final Map<NodeId, Entry> certificates =
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
        public Optional<KeyPair> getKeyPair(NodeId certificateTypeId) {
            Entry entry = certificates.get(certificateTypeId);

            if (entry == null) {
                return Optional.empty();
            } else {
                return Optional.of(entry.keyPair);
            }
        }

        @Override
        public Optional<X509Certificate> getCertificate(NodeId certificateTypeId) {
            Entry entry = certificates.get(certificateTypeId);

            if (entry == null) {
                return Optional.empty();
            } else {
                return Optional.of(entry.certificate);
            }
        }

        @Override
        public Optional<X509Certificate[]> getCertificateChain(NodeId certificateTypeId) {
            Entry entry = certificates.get(certificateTypeId);

            if (entry == null) {
                return Optional.empty();
            } else {
                return Optional.of(entry.certificateChain);
            }
        }

        @Override
        public TrustListManager getTrustListManager() {
            return null; // TODO
        }

        @Override
        public void addCertificate(NodeId certificateTypeId, X509Certificate certificate, KeyPair keyPair) {
            var entry = new Entry(keyPair, certificate, new X509Certificate[]{certificate});
            certificates.put(certificateTypeId, entry);
        }

        @Override
        public void addCertificate(NodeId certificateTypeId, X509Certificate[] certificateChain, KeyPair keyPair) {
            var entry = new Entry(keyPair, certificateChain[0], certificateChain);
            certificates.put(certificateTypeId, entry);
        }

        @Override
        public void removeCertificate(NodeId certificateTypeId) {
            certificates.remove(certificateTypeId);
        }

    }

}
