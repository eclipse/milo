package org.eclipse.milo.opcua.stack.core.security;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.jetbrains.annotations.Nullable;

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

    interface CertificateGroup {


        NodeId getCertificateGroupId();

        List<NodeId> getSupportedCertificateTypeIds();

        TrustListManager getTrustListManager();

        List<CertificateRecord> getCertificateRecords();

        Optional<KeyPair> getKeyPair(NodeId certificateTypeId);

        Optional<X509Certificate[]> getCertificate(NodeId certificateTypeId);

        void updateCertificate(
            NodeId certificateTypeId,
            KeyPair keyPair,
            X509Certificate[] certificateChain
        ) throws Exception;

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

        class CertificateRecord {
            public final NodeId certificateGroupId;
            public final NodeId certificateTypeId;
            public final X509Certificate[] certificateChain;

            public CertificateRecord(
                NodeId certificateGroupId,
                NodeId certificateTypeId,
                X509Certificate[] certificateChain
            ) {

                this.certificateGroupId = certificateGroupId;
                this.certificateTypeId = certificateTypeId;
                this.certificateChain = certificateChain;
            }
        }

    }

    interface CertificateFactory {

        KeyPair createKeyPair(NodeId certificateTypeId);

        X509Certificate createCertificate(NodeId certificateTypeId, KeyPair keyPair);

    }

    class DefaultApplicationGroup implements CertificateGroup {

        private final KeyManager keyManager;
        private final TrustListManager trustListManager;
        private final CertificateFactory certificateFactory;

        public DefaultApplicationGroup(
            KeyManager keyManager,
            TrustListManager trustListManager,
            CertificateFactory certificateFactory
        ) {

            this.keyManager = keyManager;
            this.trustListManager = trustListManager;
            this.certificateFactory = certificateFactory;
        }

        public void initialize() throws Exception {
            for (NodeId certificateTypeId : getSupportedCertificateTypeIds()) {
                String alias = getAlias(certificateTypeId);

                if (!keyManager.contains(alias)) {
                    KeyPair keyPair = createKeyPair(certificateTypeId);
                    X509Certificate certificate = createCertificate(certificateTypeId, keyPair);

                    keyManager.set(
                        alias,
                        getPassword(certificateTypeId),
                        new KeyManager.KeyRecord(keyPair.getPrivate(), new X509Certificate[]{certificate})
                    );
                }
            }
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
            return trustListManager;
        }

        @Override
        public List<CertificateRecord> getCertificateRecords() {
            var certificateRecords = new ArrayList<CertificateRecord>();

            for (NodeId certificateTypeId : getSupportedCertificateTypeIds()) {
                String alias = getAlias(certificateTypeId);
                String password = getPassword(certificateTypeId);

                if (alias != null && password != null) {
                    try {
                        KeyManager.KeyRecord keyRecord = keyManager.get(alias, password);

                        if (keyRecord != null) {
                            certificateRecords.add(new CertificateRecord(
                                getCertificateGroupId(),
                                certificateTypeId,
                                keyRecord.certificateChain
                            ));
                        }
                    } catch (Exception e) {
                        return List.of();
                    }
                }
            }

            return certificateRecords;
        }

        @Override
        public Optional<KeyPair> getKeyPair(NodeId certificateTypeId) {
            if (certificateTypeId.equals(NodeIds.RsaSha256ApplicationCertificateType)) {
                String alias = getAlias(certificateTypeId);
                String password = getPassword(certificateTypeId);

                if (alias == null || password == null) {
                    return Optional.empty();
                }

                try {
                    KeyManager.KeyRecord keyRecord = keyManager.get(alias, password);

                    if (keyRecord != null) {
                        KeyPair keyPair = new KeyPair(
                            keyRecord.certificateChain[0].getPublicKey(),
                            keyRecord.privateKey
                        );
                        return Optional.of(keyPair);
                    } else {
                        return Optional.empty();
                    }
                } catch (Exception e) {
                    return Optional.empty();
                }
            } else {
                return Optional.empty();
            }
        }

        @Override
        public Optional<X509Certificate[]> getCertificate(NodeId certificateTypeId) {
            return Optional.empty();
        }

        @Override
        public void updateCertificate(
            NodeId certificateTypeId,
            KeyPair keyPair,
            X509Certificate[] certificateChain
        ) throws Exception {

            if (certificateTypeId.equals(NodeIds.RsaSha256ApplicationCertificateType)) {
                String alias = getAlias(certificateTypeId);
                String password = getPassword(certificateTypeId);

                if (alias == null || password == null) {
                    return;
                }

                keyManager.set(alias, password, new KeyManager.KeyRecord(keyPair.getPrivate(), certificateChain));
            } else {
                throw new UaException(StatusCodes.Bad_InvalidArgument, "certificateTypeId");
            }
        }

        @Override
        public void addRejectedCertificate(X509Certificate certificate) {
            // TODO
        }

        @Override
        public boolean removeRejectedCertificate(X509Certificate certificate) {
            return false; // TODO
        }

        @Override
        public List<X509Certificate> getRejectedCertificates() {
            return null; // TODO
        }

        protected @Nullable String getAlias(NodeId certificateTypeId) {
            if (certificateTypeId.equals(NodeIds.RsaSha256ApplicationCertificateType)) {
                return "server-rsa-sha256";
            } else {
                return null;
            }
        }

        protected @Nullable String getPassword(NodeId certificateTypeId) {
            if (certificateTypeId.equals(NodeIds.RsaSha256ApplicationCertificateType)) {
                return "password";
            } else {
                return null;
            }
        }

        protected KeyPair createKeyPair(NodeId certificateTypeId) {
            return certificateFactory.createKeyPair(certificateTypeId);
        }

        protected X509Certificate createCertificate(NodeId certificateTypeId, KeyPair keyPair) {
            return certificateFactory.createCertificate(certificateTypeId, keyPair);
        }

    }

}
