package org.eclipse.milo.opcua.stack.core.security;

import java.nio.file.Path;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.CertificateManager.CertificateGroup.CertificateRecord;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;

public class DefaultCertificateManager implements CertificateManager {

    private final Map<NodeId, CertificateGroup> certificateGroups = new ConcurrentHashMap<>();

    public DefaultCertificateManager() {}

    public DefaultCertificateManager(CertificateGroup group) {
        this(List.of(group));
    }

    public DefaultCertificateManager(Collection<CertificateGroup> groups) {
        groups.forEach(g -> certificateGroups.put(g.getCertificateGroupId(), g));
    }

    @Override
    public Optional<KeyPair> getKeyPair(ByteString thumbprint) {
        return firstMatchingRecord(thumbprint).flatMap(entry -> {
            Optional<CertificateGroup> group = getCertificateGroup(entry.certificateGroupId);
            return group.flatMap(g -> g.getKeyPair(entry.certificateTypeId));
        });
    }

    @Override
    public Optional<X509Certificate> getCertificate(ByteString thumbprint) {
        return firstMatchingRecord(thumbprint).map(e -> e.certificateChain[0]);
    }

    @Override
    public Optional<X509Certificate[]> getCertificateChain(ByteString thumbprint) {
        return firstMatchingRecord(thumbprint).map(e -> e.certificateChain);
    }

    @Override
    public Optional<CertificateGroup> getCertificateGroup(NodeId certificateGroupId) {
        return Optional.ofNullable(certificateGroups.get(certificateGroupId));
    }

    @Override
    public List<CertificateGroup> getCertificateGroups() {
        return List.copyOf(certificateGroups.values());
    }

    private Optional<CertificateRecord> firstMatchingRecord(ByteString thumbprint) {
        return certificateGroups.values()
            .stream()
            .flatMap(group -> group.getCertificateRecords().stream())
            .filter(record -> {
                try {
                    return CertificateUtil.thumbprint(record.certificateChain[0]).equals(thumbprint);
                } catch (UaException e) {
                    return false;
                }
            })
            .findFirst();
    }

    /**
     * Create an instance of {@link DefaultCertificateManager} pre-populated with an instance of
     * {@link DefaultApplicationGroup}, with keys and certificates managed by a
     * {@link KeyStoreKeyManager} that will be initialized in {@code pkiDir}.
     *
     * @param pkiDir the base PKI directory this group will operate in.
     * @param certificateFactory a {@link CertificateFactory} to use for generating new
     *     certificates.
     * @return a new {@link DefaultCertificateManager} instance.
     * @throws Exception if an error occurs while initializing the {@link KeyStoreKeyManager} or
     *     {@link DefaultApplicationGroup}.
     */
    public static DefaultCertificateManager createWithDefaultApplicationGroup(
        Path pkiDir,
        CertificateFactory certificateFactory
    ) throws Exception {

        var keyManager = new KeyStoreKeyManager(pkiDir);
        keyManager.initialize();

        return createWithDefaultApplicationGroup(pkiDir, keyManager, certificateFactory);
    }

    /**
     * Create an instance of {@link DefaultCertificateManager} pre-populated with an instance of
     * {@link DefaultApplicationGroup}, with keys and certificates managed by {@code keyManager}.
     *
     * @param pkiDir the base PKI directory this group will operate in.
     * @param keyManager the {@link KeyManager} managing the keys and certificates.
     * @param certificateFactory a {@link CertificateFactory} to use for generating new
     *     certificates.
     * @return a new {@link DefaultCertificateManager} instance.
     * @throws Exception if an error occurs while initializing the {@link KeyStoreKeyManager} or
     *     {@link DefaultApplicationGroup}.
     */
    public static DefaultCertificateManager createWithDefaultApplicationGroup(
        Path pkiDir,
        KeyManager keyManager,
        CertificateFactory certificateFactory
    ) throws Exception {

        var trustListManager = new DefaultTrustListManager(pkiDir);

        var defaultGroup = new DefaultApplicationGroup(
            keyManager,
            trustListManager,
            certificateFactory
        );
        defaultGroup.initialize();

        return new DefaultCertificateManager(defaultGroup);
    }

}
