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
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Optional;

import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface CertificateManager {

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

    /**
     * Get the {@link CertificateGroup} containing the {@link X509Certificate} identified by
     * {@code thumbprint}.
     *
     * @param thumbprint the thumbprint of the certificate.
     * @return the {@link CertificateGroup} containing the {@link X509Certificate} identified by
     *     {@code thumbprint}.
     */
    Optional<CertificateGroup> getCertificateGroup(ByteString thumbprint);

    /**
     * Get the {@link CertificateGroup} identified by {@code certificateGroupId}.
     *
     * @param certificateGroupId the {@link NodeId} identifying the {@link CertificateGroup}.
     * @return the {@link CertificateGroup} identified by {@code certificateGroupId}.
     */
    Optional<CertificateGroup> getCertificateGroup(NodeId certificateGroupId);

    /**
     * Get the {@link CertificateGroup}s managed by this {@link CertificateManager}.
     *
     * @return the {@link CertificateGroup}s managed by this {@link CertificateManager}.
     */
    List<CertificateGroup> getCertificateGroups();

    /**
     * Get the Server's {@link CertificateQuarantine}.
     *
     * @return the Server's {@link CertificateQuarantine}.
     */
    CertificateQuarantine getCertificateQuarantine();

    /**
     * Get the DefaultApplicationGroup {@link CertificateGroup}, if it's configured.
     * <p>
     * Servers are required to support the DefaultApplicationGroup CertificateGroup.
     *
     * @return the DefaultApplicationGroup {@link CertificateGroup}, if it's configured.
     */
    default Optional<CertificateGroup> getDefaultApplicationGroup() {
        return getCertificateGroup(NodeIds.ServerConfiguration_CertificateGroups_DefaultApplicationGroup);
    }

    /**
     * Get the DefaultUserTokenGroup {@link CertificateGroup}, if it's configured.
     * <p>
     * Support for the DefaultUserTokenGroup CertificateGroup is optional.
     *
     * @return the DefaultUserTokenGroup {@link CertificateGroup}, if it's configured.
     */
    default Optional<CertificateGroup> getDefaultUserTokenGroup() {
        return getCertificateGroup(NodeIds.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup);
    }

    /**
     * Get the DefaultHttpsGroup {@link CertificateGroup}, if it's configured.
     * <p>
     * Support for the DefaultHttpsGroup CertificateGroup is optional.
     *
     * @return the DefaultHttpsGroup {@link CertificateGroup}, if it's configured.
     */
    default Optional<CertificateGroup> getDefaultHttpsGroup() {
        return getCertificateGroup(NodeIds.ServerConfiguration_CertificateGroups_DefaultHttpsGroup);
    }

    interface CertificateGroup {

        /**
         * Get the {@link NodeId} identifying this {@link CertificateGroup}.
         *
         * @return the {@link NodeId} identifying this {@link CertificateGroup}.
         */
        NodeId getCertificateGroupId();

        /**
         * Get the {@link NodeId}s identifying the types of certificates supported by this
         * {@link CertificateGroup}.
         *
         * @return the {@link NodeId}s identifying the types of certificates supported by this
         *     {@link CertificateGroup}.
         */
        List<NodeId> getSupportedCertificateTypeIds();

        /**
         * Get the {@link TrustListManager} for this {@link CertificateGroup}.
         *
         * @return the {@link TrustListManager} for this {@link CertificateGroup}.
         */
        TrustListManager getTrustListManager();

        /**
         * Get the {@link CertificateRecord}s belonging to this {@link CertificateGroup}.
         *
         * @return the {@link CertificateRecord}s belonging to this {@link CertificateGroup}.
         */
        List<CertificateRecord> getCertificateRecords();

        /**
         * Get the {@link KeyPair} associated with the certificate of the type identified by
         * {@code certificateTypeId}.
         *
         * @param certificateTypeId the {@link NodeId} identifying the type of certificate.
         * @return the {@link KeyPair} associated with the certificate of the type identified by
         *     {@code certificateTypeId}.
         */
        Optional<KeyPair> getKeyPair(NodeId certificateTypeId);

        /**
         * Get the {@link X509Certificate} chain associated with the certificate of the type
         * identified by {@code certificateTypeId}.
         *
         * @param certificateTypeId the {@link NodeId} identifying the type of certificate.
         * @return the {@link X509Certificate} chain associated with the certificate of the type
         *     identified by {@code certificateTypeId}.
         */
        Optional<X509Certificate[]> getCertificateChain(NodeId certificateTypeId);

        /**
         * Update the {@link KeyPair} and {@link X509Certificate} associated with the type
         * identified by {@code certificateTypeId}.
         *
         * @param certificateTypeId the {@link NodeId} identifying the type of certificate.
         * @param keyPair the new {@link KeyPair}.
         * @param certificateChain the new {@link X509Certificate} chain.
         * @throws Exception if the update fails.
         */
        void updateCertificate(
            NodeId certificateTypeId,
            KeyPair keyPair,
            X509Certificate[] certificateChain
        ) throws Exception;

        /**
         * Get the {@link CertificateFactory} for this {@link CertificateGroup}.
         *
         * @return the {@link CertificateFactory} for this {@link CertificateGroup}.
         */
        CertificateFactory getCertificateFactory();

        /**
         * Get the {@link ServerCertificateValidator} for this {@link CertificateGroup}.
         *
         * @return the {@link ServerCertificateValidator} for this {@link CertificateGroup}.
         */
        ServerCertificateValidator getCertificateValidator();

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

}
