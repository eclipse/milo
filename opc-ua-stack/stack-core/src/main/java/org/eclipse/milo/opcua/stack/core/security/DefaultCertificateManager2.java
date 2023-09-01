package org.eclipse.milo.opcua.stack.core.security;

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.CertificateManager2.CertificateGroup.CertificateRecord;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class DefaultCertificateManager2 implements CertificateManager2 {

    private final Map<NodeId, CertificateGroup> certificateGroups = new ConcurrentHashMap<>();

    public DefaultCertificateManager2() {}

    /**
     * Create a {@link DefaultCertificateManager2} with a {@link KeyPair} and
     * {@link X509Certificate} belonging to the DefaultApplicationGroup instance.
     *
     * @param trustListDir the directory to manage the Trust List in.
     * @param keyPair the {@link KeyPair}.
     * @param certificate the {@link X509Certificate}.
     */
    public DefaultCertificateManager2(
        File trustListDir,
        KeyPair keyPair,
        X509Certificate certificate
    ) throws IOException {

        this(trustListDir, keyPair, new X509Certificate[]{certificate});
    }

    /**
     * Create a {@link DefaultCertificateManager2} with a {@link KeyPair} and
     * {@link X509Certificate} chain belonging to the DefaultApplicationGroup instance.
     *
     * @param pkiDir the directory to manage the Trust List in.
     * @param keyPair the {@link KeyPair}.
     * @param certificateChain the {@link X509Certificate} chain.
     */
    public DefaultCertificateManager2(
        File pkiDir,
        KeyPair keyPair,
        X509Certificate[] certificateChain
    ) throws IOException {

        checkNotNull(keyPair, "keyPair must be non-null");
        checkNotNull(certificateChain, "certificateChain must be non-null");
        checkArgument(certificateChain.length > 0, "certificateChain must be non-empty");

        KeyManager keyManager = null; // TODO
        TrustListManager trustListManager = new DefaultTrustListManager(pkiDir);
        CertificateFactory certificateFactory = null; // TODO

        var defaultGroup = new DefaultApplicationGroup(
            keyManager,
            trustListManager,
            certificateFactory
        );

        certificateGroups.put(defaultGroup.getCertificateGroupId(), defaultGroup);
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

}
